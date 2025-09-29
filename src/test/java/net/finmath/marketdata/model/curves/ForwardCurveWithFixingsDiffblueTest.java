package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.CurveInterpolation.Builder;
import net.finmath.marketdata.model.curves.CurveInterpolation.ExtrapolationMethod;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationEntity;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationMethod;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarExcludingWeekends;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ForwardCurveWithFixingsDiffblueTest {
  /**
   * Test {@link ForwardCurveWithFixings#ForwardCurveWithFixings(ForwardCurve, ForwardCurve, double,
   * double)}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#ForwardCurveWithFixings(ForwardCurve,
   * ForwardCurve, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ForwardCurveWithFixings.<init>(ForwardCurve, ForwardCurve, double, double)"
  })
  public void testNewForwardCurveWithFixings() {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    ForwardCurveWithFixings actualForwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);

    // Assert
    Curve fixedPartCurve2 = actualForwardCurveWithFixings.getFixedPartCurve();
    assertTrue(fixedPartCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertSame(curveInterface, actualForwardCurveWithFixings.getBaseCurve());
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve2).getTimes(), 0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#ForwardCurveWithFixings(ForwardCurve, ForwardCurve, double,
   * double)}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#ForwardCurveWithFixings(ForwardCurve,
   * ForwardCurve, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ForwardCurveWithFixings.<init>(ForwardCurve, ForwardCurve, double, double)"
  })
  public void testNewForwardCurveWithFixings2() {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    ForwardCurveWithFixings actualForwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);

    // Assert
    Curve fixedPartCurve3 = actualForwardCurveWithFixings.getFixedPartCurve();
    assertTrue(fixedPartCurve3 instanceof ForwardCurveFromDiscountCurve);
    assertSame(curveInterface2, actualForwardCurveWithFixings.getBaseCurve());
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve3).getTimes(), 0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#ForwardCurveWithFixings(ForwardCurve, ForwardCurve, double,
   * double)}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#ForwardCurveWithFixings(ForwardCurve,
   * ForwardCurve, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ForwardCurveWithFixings.<init>(ForwardCurve, ForwardCurve, double, double)"
  })
  public void testNewForwardCurveWithFixings3() {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface3 =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve3 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    ForwardCurveWithFixings actualForwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface3, fixedPartCurve3, 10.0d, 10.0d);

    // Assert
    Curve fixedPartCurve4 = actualForwardCurveWithFixings.getFixedPartCurve();
    assertTrue(fixedPartCurve4 instanceof ForwardCurveFromDiscountCurve);
    assertSame(curveInterface3, actualForwardCurveWithFixings.getBaseCurve());
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve4).getTimes(), 0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getForward(AnalyticModel, double)} with {@code model},
   * {@code fixingTime}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getForward(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveWithFixings.getForward(AnalyticModel, double)"})
  public void testGetForwardWithModelFixingTime() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        -0.0027397260273972603d,
        forwardCurveWithFixings.getForward(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getForward(AnalyticModel, double)} with {@code model},
   * {@code fixingTime}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getForward(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveWithFixings.getForward(AnalyticModel, double)"})
  public void testGetForwardWithModelFixingTime2() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 0.5d, 10.0d);

    // Act and Assert
    assertEquals(
        -0.0027397260273972603d,
        forwardCurveWithFixings.getForward(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getForward(AnalyticModel, double)} with {@code model},
   * {@code fixingTime}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getForward(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveWithFixings.getForward(AnalyticModel, double)"})
  public void testGetForwardWithModelFixingTime3() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve(
            "3", LocalDate.of(1970, 1, 1), "(?<=[0-9|\\.])(?=[A-Z|a-z])");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        -0.0027397260273972603d,
        forwardCurveWithFixings.getForward(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getForward(AnalyticModel, double)} with {@code model},
   * {@code fixingTime}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getForward(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveWithFixings.getForward(AnalyticModel, double)"})
  public void testGetForwardWithModelFixingTime4() {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveInterpolation fixedPartCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            0.5d);

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 0.5d, 365.0d);

    // Act and Assert
    assertEquals(
        -0.0027397260273972603d,
        forwardCurveWithFixings.getForward(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getForward(AnalyticModel, double)} with {@code model},
   * {@code fixingTime}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getForward(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveWithFixings.getForward(AnalyticModel, double)"})
  public void testGetForwardWithModelFixingTime5() {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveInterpolation curveInterface2 =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve(
            "3", LocalDate.of(1970, 1, 1), "(?<=[0-9|\\.])(?=[A-Z|a-z])");

    ForwardCurveWithFixings fixedPartCurve2 =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve, 0.5d, 0.5d);

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve2, 0.5d, 365.0d);

    // Act and Assert
    assertEquals(
        -0.0027397260273972603d,
        forwardCurveWithFixings.getForward(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getForward(AnalyticModel, double, double)} with {@code
   * model}, {@code fixingTime}, {@code paymentOffset}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getForward(AnalyticModel, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveWithFixings.getForward(AnalyticModel, double, double)"})
  public void testGetForwardWithModelFixingTimePaymentOffset() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        -1.0d,
        forwardCurveWithFixings.getForward(new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getForward(AnalyticModel, double, double)} with {@code
   * model}, {@code fixingTime}, {@code paymentOffset}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getForward(AnalyticModel, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveWithFixings.getForward(AnalyticModel, double, double)"})
  public void testGetForwardWithModelFixingTimePaymentOffset2() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 0.5d, 10.0d);

    // Act and Assert
    assertEquals(
        -1.0d,
        forwardCurveWithFixings.getForward(new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getForward(AnalyticModel, double, double)} with {@code
   * model}, {@code fixingTime}, {@code paymentOffset}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getForward(AnalyticModel, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveWithFixings.getForward(AnalyticModel, double, double)"})
  public void testGetForwardWithModelFixingTimePaymentOffset3() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        -1.0d,
        forwardCurveWithFixings.getForward(new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getForwards(AnalyticModel, double[])}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getForwards(AnalyticModel, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ForwardCurveWithFixings.getForwards(AnalyticModel, double[])"})
  public void testGetForwards() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);

    // Act and Assert
    assertArrayEquals(
        new double[] {
          -0.0027397260273972603d,
          -0.08563698630136986d,
          -0.0027397260273972603d,
          -0.08563698630136986d
        },
        forwardCurveWithFixings.getForwards(
            new AnalyticModelFromCurvesAndVols(), new double[] {10.0d, 0.5d, 10.0d, 0.5d}),
        0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getForwards(AnalyticModel, double[])}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getForwards(AnalyticModel, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ForwardCurveWithFixings.getForwards(AnalyticModel, double[])"})
  public void testGetForwards2() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 0.5d, 10.0d);

    // Act and Assert
    assertArrayEquals(
        new double[] {
          -0.0027397260273972603d,
          -0.08563698630136986d,
          -0.0027397260273972603d,
          -0.08563698630136986d
        },
        forwardCurveWithFixings.getForwards(
            new AnalyticModelFromCurvesAndVols(), new double[] {10.0d, 0.5d, 10.0d, 0.5d}),
        0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getForwards(AnalyticModel, double[])}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getForwards(AnalyticModel, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ForwardCurveWithFixings.getForwards(AnalyticModel, double[])"})
  public void testGetForwards3() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve(
            "3", LocalDate.of(1970, 1, 1), "(?<=[0-9|\\.])(?=[A-Z|a-z])");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);

    // Act and Assert
    assertArrayEquals(
        new double[] {
          -0.0027397260273972603d,
          -0.08563698630136986d,
          -0.0027397260273972603d,
          -0.08563698630136986d
        },
        forwardCurveWithFixings.getForwards(
            new AnalyticModelFromCurvesAndVols(), new double[] {10.0d, 0.5d, 10.0d, 0.5d}),
        0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getForwards(AnalyticModel, double[])}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getForwards(AnalyticModel, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ForwardCurveWithFixings.getForwards(AnalyticModel, double[])"})
  public void testGetForwards4() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            10.0d);
    ForwardCurveInterpolation fixedPartCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            10.0d);

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 0.0d, 10.0d);

    // Act and Assert
    assertArrayEquals(
        new double[] {
          -0.0027397260273972603d,
          -0.08563698630136986d,
          -0.0027397260273972603d,
          -0.08563698630136986d
        },
        forwardCurveWithFixings.getForwards(
            new AnalyticModelFromCurvesAndVols(), new double[] {10.0d, 0.5d, 10.0d, 0.5d}),
        0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getForwards(AnalyticModel, double[])}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getForwards(AnalyticModel, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ForwardCurveWithFixings.getForwards(AnalyticModel, double[])"})
  public void testGetForwards5() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            10.0d);
    ForwardCurveInterpolation curveInterface2 =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            new double[] {10.0d, 365.0d, 10.0d, 365.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve(
            "3", LocalDate.of(1970, 1, 1), "(?<=[0-9|\\.])(?=[A-Z|a-z])");

    ForwardCurveWithFixings fixedPartCurve2 =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve, 10.0d, 10.0d);

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve2, 0.0d, 10.0d);

    // Act and Assert
    assertArrayEquals(
        new double[] {
          -0.0027397260273972603d,
          -0.08563698630136986d,
          -0.0027397260273972603d,
          -0.08563698630136986d
        },
        forwardCurveWithFixings.getForwards(
            new AnalyticModelFromCurvesAndVols(), new double[] {10.0d, 0.5d, 10.0d, 0.5d}),
        0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getDiscountCurveName()}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getDiscountCurveName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ForwardCurveWithFixings.getDiscountCurveName()"})
  public void testGetDiscountCurveName() {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);

    // Act and Assert
    assertEquals("3", forwardCurveWithFixings.getDiscountCurveName());
  }

  /**
   * Test {@link ForwardCurveWithFixings#getDiscountCurveName()}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getDiscountCurveName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ForwardCurveWithFixings.getDiscountCurveName()"})
  public void testGetDiscountCurveName2() {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);

    // Act and Assert
    assertEquals("3", forwardCurveWithFixings.getDiscountCurveName());
  }

  /**
   * Test {@link ForwardCurveWithFixings#getPaymentOffset(double)}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getPaymentOffset(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveWithFixings.getPaymentOffset(double)"})
  public void testGetPaymentOffset() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {365.0d, 10.0d, 365.0d, 10.0d},
            new double[] {365.0d, 10.0d, 365.0d, 10.0d},
            365.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(365.0d, forwardCurveWithFixings.getPaymentOffset(10.0d), 0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getPaymentOffset(double)}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getPaymentOffset(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveWithFixings.getPaymentOffset(double)"})
  public void testGetPaymentOffset2() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {365.0d, 10.0d, 365.0d, 10.0d},
            new double[] {365.0d, 10.0d, 365.0d, 10.0d},
            365.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve(
            "3", LocalDate.of(1970, 1, 1), "(?<=[0-9|\\.])(?=[A-Z|a-z])");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 365.0d, 365.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(365.0d, forwardCurveWithFixings.getPaymentOffset(10.0d), 0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getPaymentOffset(double)}.
   *
   * <ul>
   *   <li>Then return {@code 42.0027397260274}.
   * </ul>
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getPaymentOffset(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveWithFixings.getPaymentOffset(double)"})
  public void testGetPaymentOffset_thenReturn420027397260274() {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "42");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(42.0027397260274d, forwardCurveWithFixings.getPaymentOffset(10.0d), 0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve ForwardCurveWithFixings.getCloneForParameter(double[])"})
  public void testGetCloneForParameter() throws CloneNotSupportedException {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);

    // Act
    Curve actualCloneForParameter =
        forwardCurveWithFixings.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    Curve baseCurve = ((ForwardCurveWithFixings) actualCloneForParameter).getBaseCurve();
    assertTrue(
        ((ForwardCurveWithFixings) baseCurve).getBaseCurve()
            instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve3 = ((ForwardCurveWithFixings) actualCloneForParameter).getFixedPartCurve();
    assertTrue(fixedPartCurve3 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(
        ((ForwardCurveWithFixings) baseCurve).getFixedPartCurve()
            instanceof ForwardCurveFromDiscountCurve);
    assertTrue(actualCloneForParameter instanceof ForwardCurveWithFixings);
    assertTrue(baseCurve instanceof ForwardCurveWithFixings);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve3).getTimes(), 0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve ForwardCurveWithFixings.getCloneForParameter(double[])"})
  public void testGetCloneForParameter2() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", referenceDate, "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface3 =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve3 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface3, fixedPartCurve3, 10.0d, 10.0d);

    // Act
    Curve actualCloneForParameter =
        forwardCurveWithFixings.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    Curve baseCurve = ((ForwardCurveWithFixings) actualCloneForParameter).getBaseCurve();
    Curve baseCurve2 = ((ForwardCurveWithFixings) baseCurve).getBaseCurve();
    assertTrue(
        ((ForwardCurveWithFixings) baseCurve2).getBaseCurve()
            instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve4 = ((ForwardCurveWithFixings) actualCloneForParameter).getFixedPartCurve();
    assertTrue(fixedPartCurve4 instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve5 = ((ForwardCurveWithFixings) baseCurve).getFixedPartCurve();
    assertTrue(fixedPartCurve5 instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve6 = ((ForwardCurveWithFixings) baseCurve2).getFixedPartCurve();
    assertTrue(fixedPartCurve6 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(actualCloneForParameter instanceof ForwardCurveWithFixings);
    assertTrue(baseCurve instanceof ForwardCurveWithFixings);
    assertTrue(baseCurve2 instanceof ForwardCurveWithFixings);
    assertEquals("3", ((ForwardCurveWithFixings) baseCurve2).getDiscountCurveName());
    assertEquals("ForwardCurveFromDiscountCurve(3,Payment Offset Code)", baseCurve2.getName());
    assertNull(baseCurve2.getParameter());
    assertEquals(10.0d, ((ForwardCurveWithFixings) baseCurve2).getFixedPartEndTime(), 0.0);
    assertEquals(10.0d, ((ForwardCurveWithFixings) baseCurve2).getFixedPartStartTime(), 0.0);
    assertSame(fixedPartCurve, fixedPartCurve6);
    assertSame(referenceDate, baseCurve2.getReferenceDate());
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve4).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve5).getTimes(), 0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getCloneForParameter(double[])}.
   *
   * <ul>
   *   <li>Then BaseCurve CloneBuilder return {@link Builder}.
   * </ul>
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve ForwardCurveWithFixings.getCloneForParameter(double[])"})
  public void testGetCloneForParameter_thenBaseCurveCloneBuilderReturnBuilder()
      throws CloneNotSupportedException {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);

    // Act
    Curve actualCloneForParameter =
        forwardCurveWithFixings.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    Curve baseCurve = ((ForwardCurveWithFixings) actualCloneForParameter).getBaseCurve();
    assertTrue(baseCurve.getCloneBuilder() instanceof Builder);
    assertTrue(baseCurve instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve2 = ((ForwardCurveWithFixings) actualCloneForParameter).getFixedPartCurve();
    assertTrue(fixedPartCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(actualCloneForParameter instanceof ForwardCurveWithFixings);
    assertTrue(
        ((ForwardCurveFromDiscountCurve) baseCurve).getPaymentBusinessdayCalendar()
            instanceof BusinessdayCalendarExcludingWeekends);
    assertEquals("3", ((ForwardCurveFromDiscountCurve) baseCurve).getDiscountCurveName());
    assertEquals("ForwardCurveFromDiscountCurve(3,Payment Offset Code)", baseCurve.getName());
    assertEquals(
        "Payment Offset Code", ((ForwardCurveFromDiscountCurve) baseCurve).getPaymentOffsetCode());
    assertNull(baseCurve.getParameter());
    assertEquals(
        ExtrapolationMethod.CONSTANT,
        ((ForwardCurveFromDiscountCurve) baseCurve).getExtrapolationMethod());
    assertEquals(
        InterpolationEntity.VALUE,
        ((ForwardCurveFromDiscountCurve) baseCurve).getInterpolationEntity());
    assertEquals(
        InterpolationMethod.LINEAR,
        ((ForwardCurveFromDiscountCurve) baseCurve).getInterpolationMethod());
    assertEquals(
        DateRollConvention.FOLLOWING,
        ((ForwardCurveFromDiscountCurve) baseCurve).getPaymentDateRollConvention());
    assertTrue(((ForwardCurveFromDiscountCurve) baseCurve).getPoints().isEmpty());
    assertArrayEquals(new double[] {}, ((ForwardCurveFromDiscountCurve) baseCurve).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve2).getTimes(), 0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#getCloneForParameter(double[])}.
   *
   * <ul>
   *   <li>Then BaseCurve return {@link ForwardCurveNelsonSiegelSvensson}.
   * </ul>
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve ForwardCurveWithFixings.getCloneForParameter(double[])"})
  public void testGetCloneForParameter_thenBaseCurveReturnForwardCurveNelsonSiegelSvensson()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson curveInterface =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);

    // Act
    Curve actualCloneForParameter =
        forwardCurveWithFixings.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    Curve fixedPartCurve2 = ((ForwardCurveWithFixings) actualCloneForParameter).getFixedPartCurve();
    assertTrue(fixedPartCurve2 instanceof ForwardCurveFromDiscountCurve);
    Curve baseCurve = ((ForwardCurveWithFixings) actualCloneForParameter).getBaseCurve();
    assertTrue(baseCurve instanceof ForwardCurveNelsonSiegelSvensson);
    assertTrue(actualCloneForParameter instanceof ForwardCurveWithFixings);
    assertEquals("Name", actualCloneForParameter.getName());
    assertEquals("Name", baseCurve.getName());
    assertNull(((ForwardCurveNelsonSiegelSvensson) baseCurve).getDiscountCurveName());
    assertNull(((ForwardCurveWithFixings) actualCloneForParameter).getDiscountCurveName());
    double[] parameter = actualCloneForParameter.getParameter();
    assertSame(parameter, baseCurve.getParameter());
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve2).getTimes(), 0.0);
    assertArrayEquals(new double[] {10.0d, 0.5d, 10.0d, 0.5d}, parameter, 0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#clone()}.
   *
   * <ul>
   *   <li>Then BaseCurve BaseCurve BaseCurve return {@link ForwardCurveFromDiscountCurve}.
   * </ul>
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ForwardCurveWithFixings ForwardCurveWithFixings.clone()"})
  public void testClone_thenBaseCurveBaseCurveBaseCurveReturnForwardCurveFromDiscountCurve()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", referenceDate, "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface3 =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve3 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface3, fixedPartCurve3, 10.0d, 10.0d);

    // Act
    ForwardCurveWithFixings actualCloneResult = forwardCurveWithFixings.clone();

    // Assert
    Curve baseCurve = actualCloneResult.getBaseCurve();
    Curve baseCurve2 = ((ForwardCurveWithFixings) baseCurve).getBaseCurve();
    assertTrue(
        ((ForwardCurveWithFixings) baseCurve2).getBaseCurve()
            instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve4 = actualCloneResult.getFixedPartCurve();
    assertTrue(fixedPartCurve4 instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve5 = ((ForwardCurveWithFixings) baseCurve).getFixedPartCurve();
    assertTrue(fixedPartCurve5 instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve6 = ((ForwardCurveWithFixings) baseCurve2).getFixedPartCurve();
    assertTrue(fixedPartCurve6 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(baseCurve instanceof ForwardCurveWithFixings);
    assertTrue(baseCurve2 instanceof ForwardCurveWithFixings);
    assertEquals("3", ((ForwardCurveWithFixings) baseCurve2).getDiscountCurveName());
    assertEquals("ForwardCurveFromDiscountCurve(3,Payment Offset Code)", baseCurve2.getName());
    assertNull(baseCurve2.getParameter());
    assertEquals(10.0d, ((ForwardCurveWithFixings) baseCurve2).getFixedPartEndTime(), 0.0);
    assertEquals(10.0d, ((ForwardCurveWithFixings) baseCurve2).getFixedPartStartTime(), 0.0);
    assertSame(fixedPartCurve, fixedPartCurve6);
    assertSame(referenceDate, baseCurve2.getReferenceDate());
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve4).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve5).getTimes(), 0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#clone()}.
   *
   * <ul>
   *   <li>Then BaseCurve BaseCurve return {@link ForwardCurveFromDiscountCurve}.
   * </ul>
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ForwardCurveWithFixings ForwardCurveWithFixings.clone()"})
  public void testClone_thenBaseCurveBaseCurveReturnForwardCurveFromDiscountCurve()
      throws CloneNotSupportedException {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);

    // Act
    ForwardCurveWithFixings actualCloneResult = forwardCurveWithFixings.clone();

    // Assert
    Curve baseCurve = actualCloneResult.getBaseCurve();
    assertTrue(
        ((ForwardCurveWithFixings) baseCurve).getBaseCurve()
            instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve3 = actualCloneResult.getFixedPartCurve();
    assertTrue(fixedPartCurve3 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(
        ((ForwardCurveWithFixings) baseCurve).getFixedPartCurve()
            instanceof ForwardCurveFromDiscountCurve);
    assertTrue(baseCurve instanceof ForwardCurveWithFixings);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve3).getTimes(), 0.0);
  }

  /**
   * Test {@link ForwardCurveWithFixings#clone()}.
   *
   * <ul>
   *   <li>Then BaseCurve CloneBuilder return {@link Builder}.
   * </ul>
   *
   * <p>Method under test: {@link ForwardCurveWithFixings#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ForwardCurveWithFixings ForwardCurveWithFixings.clone()"})
  public void testClone_thenBaseCurveCloneBuilderReturnBuilder() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", referenceDate, "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);

    // Act
    ForwardCurveWithFixings actualCloneResult = forwardCurveWithFixings.clone();

    // Assert
    Curve baseCurve = actualCloneResult.getBaseCurve();
    assertTrue(baseCurve.getCloneBuilder() instanceof Builder);
    assertTrue(baseCurve instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve2 = actualCloneResult.getFixedPartCurve();
    assertTrue(fixedPartCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(
        ((ForwardCurveFromDiscountCurve) baseCurve).getPaymentBusinessdayCalendar()
            instanceof BusinessdayCalendarExcludingWeekends);
    assertEquals("3", ((ForwardCurveFromDiscountCurve) baseCurve).getDiscountCurveName());
    assertEquals("ForwardCurveFromDiscountCurve(3,Payment Offset Code)", baseCurve.getName());
    assertEquals(
        "Payment Offset Code", ((ForwardCurveFromDiscountCurve) baseCurve).getPaymentOffsetCode());
    assertNull(baseCurve.getParameter());
    assertEquals(
        ExtrapolationMethod.CONSTANT,
        ((ForwardCurveFromDiscountCurve) baseCurve).getExtrapolationMethod());
    assertEquals(
        InterpolationEntity.VALUE,
        ((ForwardCurveFromDiscountCurve) baseCurve).getInterpolationEntity());
    assertEquals(
        InterpolationMethod.LINEAR,
        ((ForwardCurveFromDiscountCurve) baseCurve).getInterpolationMethod());
    assertEquals(
        DateRollConvention.FOLLOWING,
        ((ForwardCurveFromDiscountCurve) baseCurve).getPaymentDateRollConvention());
    assertTrue(((ForwardCurveFromDiscountCurve) baseCurve).getPoints().isEmpty());
    assertSame(referenceDate, baseCurve.getReferenceDate());
    assertArrayEquals(new double[] {}, ((ForwardCurveFromDiscountCurve) baseCurve).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve2).getTimes(), 0.0);
  }
}
