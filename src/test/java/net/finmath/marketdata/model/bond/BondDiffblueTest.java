package net.finmath.marketdata.model.bond;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
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
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.Curve;
import net.finmath.marketdata.model.curves.CurveInterpolation;
import net.finmath.marketdata.model.curves.CurveInterpolation.ExtrapolationMethod;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationEntity;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationMethod;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.time.Period;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.ScheduleFromPeriods;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class BondDiffblueTest {
  /**
   * Test {@link Bond#Bond(Schedule, String, double)}.
   *
   * <p>Method under test: {@link Bond#Bond(Schedule, String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Bond.<init>(Schedule, String, double)"})
  public void testNewBond() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Bond actualBond = new Bond(schedule, "3", 10.0d);

    // Assert
    assertEquals("3", actualBond.getDiscountCurveName());
    assertNull(actualBond.getBasisFactorCurveName());
    assertNull(actualBond.getForwardCurveName());
    assertNull(actualBond.getSurvivalProbabilityCurveName());
    assertEquals(0.0d, actualBond.getFloatingSpread(), 0.0);
    assertEquals(0.0d, actualBond.getRecoveryRate(), 0.0);
    assertEquals(10.0d, actualBond.getFixedCoupon(), 0.0);
    assertSame(schedule, actualBond.getSchedule());
  }

  /**
   * Test {@link Bond#Bond(Schedule, String, String, String, double)}.
   *
   * <p>Method under test: {@link Bond#Bond(Schedule, String, String, String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Bond.<init>(Schedule, String, String, String, double)"})
  public void testNewBond2() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Bond actualBond = new Bond(schedule, "3", "Doe", "Basis Factor Curve Name", 10.0d);

    // Assert
    assertEquals("3", actualBond.getDiscountCurveName());
    assertEquals("Basis Factor Curve Name", actualBond.getBasisFactorCurveName());
    assertEquals("Doe", actualBond.getSurvivalProbabilityCurveName());
    assertNull(actualBond.getForwardCurveName());
    assertEquals(0.0d, actualBond.getFloatingSpread(), 0.0);
    assertEquals(0.0d, actualBond.getRecoveryRate(), 0.0);
    assertEquals(10.0d, actualBond.getFixedCoupon(), 0.0);
    assertSame(schedule, actualBond.getSchedule());
  }

  /**
   * Test {@link Bond#Bond(Schedule, String, String, String, double, double)}.
   *
   * <p>Method under test: {@link Bond#Bond(Schedule, String, String, String, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Bond.<init>(Schedule, String, String, String, double, double)"})
  public void testNewBond3() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Bond actualBond = new Bond(schedule, "3", "Doe", "Basis Factor Curve Name", 10.0d, 10.0d);

    // Assert
    assertEquals("3", actualBond.getDiscountCurveName());
    assertEquals("Basis Factor Curve Name", actualBond.getBasisFactorCurveName());
    assertEquals("Doe", actualBond.getSurvivalProbabilityCurveName());
    assertNull(actualBond.getForwardCurveName());
    assertEquals(0.0d, actualBond.getFloatingSpread(), 0.0);
    assertEquals(10.0d, actualBond.getFixedCoupon(), 0.0);
    assertEquals(10.0d, actualBond.getRecoveryRate(), 0.0);
    assertSame(schedule, actualBond.getSchedule());
  }

  /**
   * Test {@link Bond#Bond(Schedule, String, String, String, String, double, double)}.
   *
   * <p>Method under test: {@link Bond#Bond(Schedule, String, String, String, String, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Bond.<init>(Schedule, String, String, String, String, double, double)"})
  public void testNewBond4() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Bond actualBond =
        new Bond(
            schedule, "3", "Forward Curve Name", "Doe", "Basis Factor Curve Name", 10.0d, 10.0d);

    // Assert
    assertEquals("3", actualBond.getDiscountCurveName());
    assertEquals("Basis Factor Curve Name", actualBond.getBasisFactorCurveName());
    assertEquals("Doe", actualBond.getSurvivalProbabilityCurveName());
    assertEquals("Forward Curve Name", actualBond.getForwardCurveName());
    assertEquals(0.0d, actualBond.getRecoveryRate(), 0.0);
    assertEquals(10.0d, actualBond.getFixedCoupon(), 0.0);
    assertEquals(10.0d, actualBond.getFloatingSpread(), 0.0);
    assertSame(schedule, actualBond.getSchedule());
  }

  /**
   * Test {@link Bond#Bond(Schedule, String, String, String, String, double, double, double)}.
   *
   * <p>Method under test: {@link Bond#Bond(Schedule, String, String, String, String, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Bond.<init>(Schedule, String, String, String, String, double, double, double)"
  })
  public void testNewBond5() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Bond actualBond =
        new Bond(
            schedule,
            "3",
            "Forward Curve Name",
            "Doe",
            "Basis Factor Curve Name",
            10.0d,
            10.0d,
            10.0d);

    // Assert
    assertEquals("3", actualBond.getDiscountCurveName());
    assertEquals("Basis Factor Curve Name", actualBond.getBasisFactorCurveName());
    assertEquals("Doe", actualBond.getSurvivalProbabilityCurveName());
    assertEquals("Forward Curve Name", actualBond.getForwardCurveName());
    assertEquals(10.0d, actualBond.getFixedCoupon(), 0.0);
    assertEquals(10.0d, actualBond.getFloatingSpread(), 0.0);
    assertEquals(10.0d, actualBond.getRecoveryRate(), 0.0);
    assertSame(schedule, actualBond.getSchedule());
  }

  /**
   * Test {@link Bond#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <p>Method under test: {@link Bond#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel() {
    // Arrange
    Bond bond = new Bond(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3", 10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> bond.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link Bond#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <p>Method under test: {@link Bond#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel2() {
    // Arrange
    Bond bond =
        new Bond(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Doe",
            "Basis Factor Curve Name",
            10.0d,
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> bond.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link Bond#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_whenZero() {
    // Arrange
    Bond bond = new Bond(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3", 10.0d);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bond.getValue(0.0d, (AnalyticModel) null));
  }

  /**
   * Test {@link Bond#getCouponPayment(int, AnalyticModel)}.
   *
   * <ul>
   *   <li>Then return five.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getCouponPayment(int, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getCouponPayment(int, AnalyticModel)"})
  public void testGetCouponPayment_thenReturnFive() {
    // Arrange
    Bond bond = new Bond(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3", 10.0d);

    // Act and Assert
    assertEquals(5.0d, bond.getCouponPayment(1, new AnalyticModelFromCurvesAndVols()), 0.0);
  }

  /**
   * Test {@link Bond#getValueWithGivenSpreadOverCurve(double, Curve, double, AnalyticModel)}.
   *
   * <p>Method under test: {@link Bond#getValueWithGivenSpreadOverCurve(double, Curve, double,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Bond.getValueWithGivenSpreadOverCurve(double, Curve, double, AnalyticModel)"
  })
  public void testGetValueWithGivenSpreadOverCurve() {
    // Arrange
    Bond bond =
        new Bond(
            new RegularSchedule(
                new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START)),
            "3",
            10.0d);
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertEquals(
        0.0d,
        bond.getValueWithGivenSpreadOverCurve(
            10.0d, referenceCurve, 10.0d, new AnalyticModelFromCurvesAndVols()),
        0.0);
  }

  /**
   * Test {@link Bond#getValueWithGivenSpreadOverCurve(double, Curve, double, AnalyticModel)}.
   *
   * <p>Method under test: {@link Bond#getValueWithGivenSpreadOverCurve(double, Curve, double,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Bond.getValueWithGivenSpreadOverCurve(double, Curve, double, AnalyticModel)"
  })
  public void testGetValueWithGivenSpreadOverCurve2() {
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
    Bond bond = new Bond(schedule, "3", 10.0d);
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertEquals(
        0.0d,
        bond.getValueWithGivenSpreadOverCurve(
            10.0d, referenceCurve, 10.0d, new AnalyticModelFromCurvesAndVols()),
        0.0);
  }

  /**
   * Test {@link Bond#getValueWithGivenSpreadOverCurve(double, Curve, double, AnalyticModel)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return {@code 1.2617855823279122E-44}.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getValueWithGivenSpreadOverCurve(double, Curve, double,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Bond.getValueWithGivenSpreadOverCurve(double, Curve, double, AnalyticModel)"
  })
  public void testGetValueWithGivenSpreadOverCurve_givenTen_thenReturn12617855823279122e44() {
    // Arrange
    Bond bond = new Bond(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3", 10.0d);

    DiscountCurveInterpolation referenceCurve = mock(DiscountCurveInterpolation.class);
    when(referenceCurve.getValue(anyDouble())).thenReturn(10.0d);

    // Act
    double actualValueWithGivenSpreadOverCurve =
        bond.getValueWithGivenSpreadOverCurve(
            10.0d, referenceCurve, 10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(referenceCurve, atLeast(1)).getValue(anyDouble());
    assertEquals(1.2617855823279122E-44d, actualValueWithGivenSpreadOverCurve, 0.0);
  }

  /**
   * Test {@link Bond#getValueWithGivenSpreadOverCurve(double, Curve, double, AnalyticModel)}.
   *
   * <ul>
   *   <li>Then return {@code 1.324449769225411E-44}.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getValueWithGivenSpreadOverCurve(double, Curve, double,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Bond.getValueWithGivenSpreadOverCurve(double, Curve, double, AnalyticModel)"
  })
  public void testGetValueWithGivenSpreadOverCurve_thenReturn1324449769225411e44() {
    // Arrange
    Bond bond = new Bond(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3", 10.0d);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 10.5d, 10.0d, 10.5d},
            new double[] {10.0d, 10.5d, 10.0d, 10.5d});

    // Act and Assert
    assertEquals(
        1.324449769225411E-44d,
        bond.getValueWithGivenSpreadOverCurve(
            10.0d, referenceCurve, 10.0d, new AnalyticModelFromCurvesAndVols()),
        0.0);
  }

  /**
   * Test {@link Bond#getValueWithGivenYield(double, double, AnalyticModel)}.
   *
   * <p>Method under test: {@link Bond#getValueWithGivenYield(double, double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getValueWithGivenYield(double, double, AnalyticModel)"})
  public void testGetValueWithGivenYield() {
    // Arrange
    Bond bond =
        new Bond(
            new RegularSchedule(
                new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START)),
            "3",
            10.0d);

    // Act and Assert
    assertEquals(
        0.0d, bond.getValueWithGivenYield(10.0d, 10.0d, new AnalyticModelFromCurvesAndVols()), 0.0);
  }

  /**
   * Test {@link Bond#getValueWithGivenYield(double, double, AnalyticModel)}.
   *
   * <p>Method under test: {@link Bond#getValueWithGivenYield(double, double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getValueWithGivenYield(double, double, AnalyticModel)"})
  public void testGetValueWithGivenYield2() {
    // Arrange
    TenorFromArray timeDiscretization =
        new TenorFromArray(new double[] {1.0d, Double.NaN, 1.0d, Double.NaN});
    RegularSchedule schedule = new RegularSchedule(timeDiscretization);
    Bond bond = new Bond(schedule, "3", 10.0d);

    // Act and Assert
    assertEquals(
        0.0d, bond.getValueWithGivenYield(10.0d, 10.0d, new AnalyticModelFromCurvesAndVols()), 0.0);
  }

  /**
   * Test {@link Bond#getValueWithGivenYield(double, double, AnalyticModel)}.
   *
   * <ul>
   *   <li>Then return {@code 1.2617855823279123E-45}.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getValueWithGivenYield(double, double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getValueWithGivenYield(double, double, AnalyticModel)"})
  public void testGetValueWithGivenYield_thenReturn12617855823279123e45() {
    // Arrange
    Bond bond = new Bond(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3", 10.0d);

    // Act and Assert
    assertEquals(
        1.2617855823279123E-45d,
        bond.getValueWithGivenYield(10.0d, 10.0d, new AnalyticModelFromCurvesAndVols()),
        0.0);
  }

  /**
   * Test {@link Bond#getSpread(double, Curve, AnalyticModel)}.
   *
   * <p>Method under test: {@link Bond#getSpread(double, Curve, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getSpread(double, Curve, AnalyticModel)"})
  public void testGetSpread() {
    // Arrange
    Bond bond =
        new Bond(
            new RegularSchedule(
                new TenorFromArray(-2.0d, -2.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START)),
            "3",
            10.0d);
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertEquals(
        0.4721359549995796d,
        bond.getSpread(10.0d, referenceCurve, new AnalyticModelFromCurvesAndVols()),
        0.0);
  }

  /**
   * Test {@link Bond#getSpread(double, Curve, AnalyticModel)}.
   *
   * <p>Method under test: {@link Bond#getSpread(double, Curve, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getSpread(double, Curve, AnalyticModel)"})
  public void testGetSpread2() {
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
    Bond bond = new Bond(schedule, "3", 10.0d);
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertEquals(
        0.4721359549995796d,
        bond.getSpread(10.0d, referenceCurve, new AnalyticModelFromCurvesAndVols()),
        0.0);
  }

  /**
   * Test {@link Bond#getSpread(double, Curve, AnalyticModel)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return {@code 0.3154040215736346}.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getSpread(double, Curve, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getSpread(double, Curve, AnalyticModel)"})
  public void testGetSpread_givenTen_thenReturn03154040215736346() {
    // Arrange
    Bond bond = new Bond(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3", 10.0d);

    DiscountCurveInterpolation referenceCurve = mock(DiscountCurveInterpolation.class);
    when(referenceCurve.getValue(anyDouble())).thenReturn(10.0d);

    // Act
    double actualSpread =
        bond.getSpread(10.0d, referenceCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(referenceCurve, atLeast(1)).getValue(anyDouble());
    assertEquals(0.3154040215736346d, actualSpread, 0.0);
  }

  /**
   * Test {@link Bond#getSpread(double, Curve, AnalyticModel)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getSpread(double, Curve, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getSpread(double, Curve, AnalyticModel)"})
  public void testGetSpread_thenThrowIllegalArgumentException() {
    // Arrange
    Bond bond =
        new Bond(
            new RegularSchedule(
                new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START)),
            "3",
            10.0d);

    DiscountCurveInterpolation referenceCurve = mock(DiscountCurveInterpolation.class);
    when(referenceCurve.getValue(anyDouble())).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> bond.getSpread(10.0d, referenceCurve, new AnalyticModelFromCurvesAndVols()));
    verify(referenceCurve).getValue(10.0d);
  }

  /**
   * Test {@link Bond#getSpread(double, Curve, AnalyticModel)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then return {@code 1.9999999933230348}.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getSpread(double, Curve, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getSpread(double, Curve, AnalyticModel)"})
  public void testGetSpread_whenLocalDateWith1970AndOneAndOne_thenReturn19999999933230348() {
    // Arrange
    Bond bond = new Bond(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3", 10.0d);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d},
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d});

    // Act and Assert
    assertEquals(
        1.9999999933230348d,
        bond.getSpread(10.0d, referenceCurve, new AnalyticModelFromCurvesAndVols()),
        0.0);
  }

  /**
   * Test {@link Bond#getYield(double, AnalyticModel)}.
   *
   * <p>Method under test: {@link Bond#getYield(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getYield(double, AnalyticModel)"})
  public void testGetYield() {
    // Arrange
    Bond bond =
        new Bond(
            new RegularSchedule(
                new TenorFromArray(-2.0d, -2.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START)),
            "3",
            10.0d);

    // Act and Assert
    assertEquals(
        0.4721359549995796d, bond.getYield(10.0d, new AnalyticModelFromCurvesAndVols()), 0.0);
  }

  /**
   * Test {@link Bond#getYield(double, AnalyticModel)}.
   *
   * <p>Method under test: {@link Bond#getYield(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getYield(double, AnalyticModel)"})
  public void testGetYield2() {
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
    Bond bond = new Bond(schedule, "3", 10.0d);

    // Act and Assert
    assertEquals(
        0.4721359549995796d, bond.getYield(10.0d, new AnalyticModelFromCurvesAndVols()), 0.0);
  }

  /**
   * Test {@link Bond#getYield(double, AnalyticModel)}.
   *
   * <ul>
   *   <li>Then return {@code 0.12871171786748561}.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getYield(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getYield(double, AnalyticModel)"})
  public void testGetYield_thenReturn012871171786748561() {
    // Arrange
    Bond bond = new Bond(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3", 10.0d);

    // Act and Assert
    assertEquals(
        0.12871171786748561d, bond.getYield(10.0d, new AnalyticModelFromCurvesAndVols()), 0.0);
  }

  /**
   * Test {@link Bond#getAccruedInterest(LocalDate, AnalyticModel)} with {@code date}, {@code
   * model}.
   *
   * <ul>
   *   <li>Then calls {@link ForwardCurveFromDiscountCurve#getForward(AnalyticModel, double)}.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getAccruedInterest(LocalDate, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getAccruedInterest(LocalDate, AnalyticModel)"})
  public void testGetAccruedInterestWithDateModel_thenCallsGetForward() {
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
    Bond bond = new Bond(schedule, "3", 10.0d);
    LocalDate date = LocalDate.of(1970, 1, 1);

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act
    double actualAccruedInterest = bond.getAccruedInterest(date, model);

    // Assert
    verify(model).getForwardCurve(null);
    verify(forwardCurveFromDiscountCurve).getForward(isA(AnalyticModel.class), eq(10.0d));
    verify(schedule).getDaycountconvention();
    verify(schedule).getFixing(1);
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule, atLeast(1)).getPeriodLength(1);
    assertEquals(0.0d, actualAccruedInterest, 0.0);
  }

  /**
   * Test {@link Bond#getAccruedInterest(LocalDate, AnalyticModel)} with {@code date}, {@code
   * model}.
   *
   * <ul>
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getAccruedInterest(LocalDate, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getAccruedInterest(LocalDate, AnalyticModel)"})
  public void testGetAccruedInterestWithDateModel_thenReturn00() {
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
    Bond bond = new Bond(schedule, "3", 10.0d);
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
    double actualAccruedInterest = bond.getAccruedInterest(date, model);

    // Assert
    verify(model).getForwardCurve(null);
    verify(schedule).getDaycountconvention();
    verify(schedule).getFixing(1);
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule, atLeast(1)).getPeriodLength(1);
    assertEquals(-0.0d, actualAccruedInterest, 0.0);
  }

  /**
   * Test {@link Bond#getAccruedInterest(LocalDate, AnalyticModel)} with {@code date}, {@code
   * model}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getAccruedInterest(LocalDate, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getAccruedInterest(LocalDate, AnalyticModel)"})
  public void testGetAccruedInterestWithDateModel_thenReturnZero() {
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
    Bond bond = new Bond(schedule, "3", 10.0d);
    LocalDate date = LocalDate.of(1970, 1, 1);

    // Act
    double actualAccruedInterest =
        bond.getAccruedInterest(date, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(schedule).getDaycountconvention();
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule, atLeast(1)).getPeriodLength(1);
    assertEquals(0.0d, actualAccruedInterest, 0.0);
  }

  /**
   * Test {@link Bond#getAccruedInterest(LocalDate, AnalyticModel)} with {@code date}, {@code
   * model}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getAccruedInterest(LocalDate, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getAccruedInterest(LocalDate, AnalyticModel)"})
  public void testGetAccruedInterestWithDateModel_thenThrowIllegalArgumentException() {
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
    Bond bond = new Bond(schedule, "3", 10.0d);
    LocalDate date = LocalDate.of(1970, 1, 1);

    AnalyticModel model = mock(AnalyticModel.class);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bond.getAccruedInterest(date, model));
    verify(model).getForwardCurve(null);
    verify(schedule).getDaycountconvention();
    verify(schedule).getFixing(1);
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule).getPeriodLength(1);
  }

  /**
   * Test {@link Bond#getAccruedInterest(double, AnalyticModel)} with {@code time}, {@code model}.
   *
   * <ul>
   *   <li>Then calls {@link ForwardCurveFromDiscountCurve#getForward(AnalyticModel, double)}.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getAccruedInterest(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getAccruedInterest(double, AnalyticModel)"})
  public void testGetAccruedInterestWithTimeModel_thenCallsGetForward() {
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
    Bond bond = new Bond(schedule, "3", 10.0d);

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act
    double actualAccruedInterest = bond.getAccruedInterest(10.0d, model);

    // Assert
    verify(model).getForwardCurve(null);
    verify(forwardCurveFromDiscountCurve).getForward(isA(AnalyticModel.class), eq(10.0d));
    verify(schedule).getDaycountconvention();
    verify(schedule).getFixing(1);
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule, atLeast(1)).getPeriodLength(1);
    verify(schedule).getReferenceDate();
    assertEquals(99.97222222222221d, actualAccruedInterest, 0.0);
  }

  /**
   * Test {@link Bond#getAccruedInterest(double, AnalyticModel)} with {@code time}, {@code model}.
   *
   * <ul>
   *   <li>Then return {@code 99.97222222222221}.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getAccruedInterest(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getAccruedInterest(double, AnalyticModel)"})
  public void testGetAccruedInterestWithTimeModel_thenReturn9997222222222221() {
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
    Bond bond = new Bond(schedule, "3", 10.0d);

    // Act
    double actualAccruedInterest =
        bond.getAccruedInterest(10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(schedule).getDaycountconvention();
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule, atLeast(1)).getPeriodLength(1);
    verify(schedule).getReferenceDate();
    assertEquals(99.97222222222221d, actualAccruedInterest, 0.0);
  }

  /**
   * Test {@link Bond#getAccruedInterest(double, AnalyticModel)} with {@code time}, {@code model}.
   *
   * <ul>
   *   <li>Then return {@code -0.027389649923896496}.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getAccruedInterest(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getAccruedInterest(double, AnalyticModel)"})
  public void testGetAccruedInterestWithTimeModel_thenReturn0027389649923896496() {
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
    Bond bond = new Bond(schedule, "3", 10.0d);

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
    double actualAccruedInterest = bond.getAccruedInterest(10.0d, model);

    // Assert
    verify(model).getForwardCurve(null);
    verify(schedule).getDaycountconvention();
    verify(schedule).getFixing(1);
    verify(schedule).getPeriod(1);
    verify(schedule).getPeriodIndex(isA(LocalDate.class));
    verify(schedule, atLeast(1)).getPeriodLength(1);
    verify(schedule).getReferenceDate();
    assertEquals(-0.027389649923896496d, actualAccruedInterest, 0.0);
  }

  /**
   * Test {@link Bond#getAccruedInterest(double, AnalyticModel)} with {@code time}, {@code model}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Bond#getAccruedInterest(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Bond.getAccruedInterest(double, AnalyticModel)"})
  public void testGetAccruedInterestWithTimeModel_thenThrowIllegalArgumentException() {
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
    Bond bond = new Bond(schedule, "3", 10.0d);

    AnalyticModel model = mock(AnalyticModel.class);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bond.getAccruedInterest(10.0d, model));
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
   *   <li>{@link Bond#toString()}
   *   <li>{@link Bond#getBasisFactorCurveName()}
   *   <li>{@link Bond#getDiscountCurveName()}
   *   <li>{@link Bond#getFixedCoupon()}
   *   <li>{@link Bond#getFloatingSpread()}
   *   <li>{@link Bond#getForwardCurveName()}
   *   <li>{@link Bond#getRecoveryRate()}
   *   <li>{@link Bond#getSchedule()}
   *   <li>{@link Bond#getSurvivalProbabilityCurveName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String Bond.getBasisFactorCurveName()",
    "String Bond.getDiscountCurveName()",
    "double Bond.getFixedCoupon()",
    "double Bond.getFloatingSpread()",
    "String Bond.getForwardCurveName()",
    "double Bond.getRecoveryRate()",
    "Schedule Bond.getSchedule()",
    "String Bond.getSurvivalProbabilityCurveName()",
    "String Bond.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    Bond bond = new Bond(schedule, "3", 10.0d);

    // Act
    bond.toString();
    String actualBasisFactorCurveName = bond.getBasisFactorCurveName();
    String actualDiscountCurveName = bond.getDiscountCurveName();
    double actualFixedCoupon = bond.getFixedCoupon();
    double actualFloatingSpread = bond.getFloatingSpread();
    String actualForwardCurveName = bond.getForwardCurveName();
    double actualRecoveryRate = bond.getRecoveryRate();
    Schedule actualSchedule = bond.getSchedule();

    // Assert
    assertEquals("3", actualDiscountCurveName);
    assertNull(actualBasisFactorCurveName);
    assertNull(actualForwardCurveName);
    assertNull(bond.getSurvivalProbabilityCurveName());
    assertEquals(0.0d, actualFloatingSpread, 0.0);
    assertEquals(0.0d, actualRecoveryRate, 0.0);
    assertEquals(10.0d, actualFixedCoupon, 0.0);
    assertSame(schedule, actualSchedule);
  }
}
