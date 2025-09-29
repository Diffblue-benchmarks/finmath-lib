package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import net.finmath.time.daycount.DayCountConvention;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ForwardCurveNelsonSiegelSvenssonDiffblueTest {
  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#ForwardCurveNelsonSiegelSvensson(String,
   * LocalDate, String, BusinessdayCalendar, DateRollConvention, DayCountConvention, double[],
   * double)}.
   *
   * <p>Method under test: {@link
   * ForwardCurveNelsonSiegelSvensson#ForwardCurveNelsonSiegelSvensson(String, LocalDate, String,
   * BusinessdayCalendar, DateRollConvention, DayCountConvention, double[], double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ForwardCurveNelsonSiegelSvensson.<init>(String, LocalDate, String, BusinessdayCalendar, DateRollConvention, DayCountConvention, double[], double)"
  })
  public void testNewForwardCurveNelsonSiegelSvensson() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    // Act
    ForwardCurveNelsonSiegelSvensson actualForwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Assert
    assertEquals("Name", actualForwardCurveNelsonSiegelSvensson.getName());
    assertNull(actualForwardCurveNelsonSiegelSvensson.getDiscountCurveName());
    assertSame(referenceDate, actualForwardCurveNelsonSiegelSvensson.getReferenceDate());
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d, 1.0d},
        actualForwardCurveNelsonSiegelSvensson.getParameter(),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#ForwardCurveNelsonSiegelSvensson(String,
   * LocalDate, String, BusinessdayCalendar, DateRollConvention, DayCountConvention, double[],
   * double, double)}.
   *
   * <p>Method under test: {@link
   * ForwardCurveNelsonSiegelSvensson#ForwardCurveNelsonSiegelSvensson(String, LocalDate, String,
   * BusinessdayCalendar, DateRollConvention, DayCountConvention, double[], double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ForwardCurveNelsonSiegelSvensson.<init>(String, LocalDate, String, BusinessdayCalendar, DateRollConvention, DayCountConvention, double[], double, double)"
  })
  public void testNewForwardCurveNelsonSiegelSvensson2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    // Act
    ForwardCurveNelsonSiegelSvensson actualForwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d,
            10.0d);

    // Assert
    assertEquals("Name", actualForwardCurveNelsonSiegelSvensson.getName());
    assertNull(actualForwardCurveNelsonSiegelSvensson.getDiscountCurveName());
    assertSame(referenceDate, actualForwardCurveNelsonSiegelSvensson.getReferenceDate());
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d, 1.0d},
        actualForwardCurveNelsonSiegelSvensson.getParameter(),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getForward(AnalyticModel, double)} with {@code
   * model}, {@code fixingTime}.
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getForward(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveNelsonSiegelSvensson.getForward(AnalyticModel, double)"})
  public void testGetForwardWithModelFixingTime() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "42",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {365.0d, 10.0d, 365.0d, 10.0d, 365.0d, 10.0d, 365.0d, 10.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        Double.NaN,
        forwardCurveNelsonSiegelSvensson.getForward(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getForward(AnalyticModel, double)} with {@code
   * model}, {@code fixingTime}.
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getForward(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveNelsonSiegelSvensson.getForward(AnalyticModel, double)"})
  public void testGetForwardWithModelFixingTime2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "42",
            new BusinessdayCalendarAny(),
            DateRollConvention.UNADJUSTED,
            null,
            new double[] {365.0d, 10.0d, 365.0d, 10.0d, 365.0d, 10.0d, 365.0d, 10.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        Double.NaN,
        forwardCurveNelsonSiegelSvensson.getForward(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getForward(AnalyticModel, double, double)} with
   * {@code model}, {@code fixingTime}, {@code paymentOffset}.
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getForward(AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double ForwardCurveNelsonSiegelSvensson.getForward(AnalyticModel, double, double)"
  })
  public void testGetForwardWithModelFixingTimePaymentOffset() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {365.0d, 360.0d, 365.0d, 360.0d, 365.0d, 360.0d, 365.0d, 360.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        Double.NaN,
        forwardCurveNelsonSiegelSvensson.getForward(
            new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getForward(AnalyticModel, double, double)} with
   * {@code model}, {@code fixingTime}, {@code paymentOffset}.
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getForward(AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double ForwardCurveNelsonSiegelSvensson.getForward(AnalyticModel, double, double)"
  })
  public void testGetForwardWithModelFixingTimePaymentOffset2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "Payment Offset Code",
            new BusinessdayCalendarAny(),
            DateRollConvention.UNADJUSTED,
            null,
            new double[] {365.0d, 360.0d, 365.0d, 360.0d, 365.0d, 360.0d, 365.0d, 360.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        Double.NaN,
        forwardCurveNelsonSiegelSvensson.getForward(
            new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getForward(AnalyticModel, double, double)} with
   * {@code model}, {@code fixingTime}, {@code paymentOffset}.
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getForward(AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double ForwardCurveNelsonSiegelSvensson.getForward(AnalyticModel, double, double)"
  })
  public void testGetForwardWithModelFixingTimePaymentOffset3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {365.0d, 360.0d, 365.0d, 360.0d, 0.0d, 360.0d, 365.0d, 360.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        Double.NaN,
        forwardCurveNelsonSiegelSvensson.getForward(
            new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getForward(AnalyticModel, double, double)} with
   * {@code model}, {@code fixingTime}, {@code paymentOffset}.
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getForward(AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double ForwardCurveNelsonSiegelSvensson.getForward(AnalyticModel, double, double)"
  })
  public void testGetForwardWithModelFixingTimePaymentOffset4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {365.0d, 360.0d, 365.0d, 360.0d, 365.0d, 0.0d, 365.0d, 360.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        Double.NaN,
        forwardCurveNelsonSiegelSvensson.getForward(
            new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getForward(AnalyticModel, double, double)} with
   * {@code model}, {@code fixingTime}, {@code paymentOffset}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getForward(AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double ForwardCurveNelsonSiegelSvensson.getForward(AnalyticModel, double, double)"
  })
  public void testGetForwardWithModelFixingTimePaymentOffset_thenReturnZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {365.0d, 360.0d, 365.0d, 360.0d, 365.0d, 360.0d, 365.0d, 360.0d},
            0.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        forwardCurveNelsonSiegelSvensson.getForward(
            new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getDiscountCurveName()}.
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getDiscountCurveName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ForwardCurveNelsonSiegelSvensson.getDiscountCurveName()"})
  public void testGetDiscountCurveName() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertNull(forwardCurveNelsonSiegelSvensson.getDiscountCurveName());
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#clone()}.
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ForwardCurveNelsonSiegelSvensson ForwardCurveNelsonSiegelSvensson.clone()"})
  public void testClone() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act
    ForwardCurveNelsonSiegelSvensson actualCloneResult = forwardCurveNelsonSiegelSvensson.clone();

    // Assert
    LocalDate referenceDate2 = actualCloneResult.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneResult.getName());
    assertNull(actualCloneResult.getDiscountCurveName());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d, 1.0d}, actualCloneResult.getParameter(), 0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ForwardCurveNelsonSiegelSvensson ForwardCurveNelsonSiegelSvensson.getCloneForParameter(double[])"
  })
  public void testGetCloneForParameter() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act
    ForwardCurveNelsonSiegelSvensson actualCloneForParameter =
        forwardCurveNelsonSiegelSvensson.getCloneForParameter(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    LocalDate referenceDate2 = actualCloneForParameter.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneForParameter.getName());
    assertNull(actualCloneForParameter.getDiscountCurveName());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d, 1.0d}, actualCloneForParameter.getParameter(), 0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getValue(AnalyticModel, double)} with {@code
   * model}, {@code time}.
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveNelsonSiegelSvensson.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "42",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {365.0d, 10.0d, 365.0d, 10.0d, 365.0d, 10.0d, 365.0d, 10.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        Double.NaN,
        forwardCurveNelsonSiegelSvensson.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getValue(AnalyticModel, double)} with {@code
   * model}, {@code time}.
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveNelsonSiegelSvensson.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "42",
            new BusinessdayCalendarAny(),
            DateRollConvention.UNADJUSTED,
            null,
            new double[] {365.0d, 10.0d, 365.0d, 10.0d, 365.0d, 10.0d, 365.0d, 10.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        Double.NaN,
        forwardCurveNelsonSiegelSvensson.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getParameter()}.
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ForwardCurveNelsonSiegelSvensson.getParameter()"})
  public void testGetParameter() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d, 1.0d},
        forwardCurveNelsonSiegelSvensson.getParameter(),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getForwards(AnalyticModel, double[])}.
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getForwards(AnalyticModel,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] ForwardCurveNelsonSiegelSvensson.getForwards(AnalyticModel, double[])"
  })
  public void testGetForwards() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "42",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {365.0d, 10.0d, 365.0d, 10.0d, 365.0d, 10.0d, 365.0d, 10.0d},
            10.0d);

    // Act and Assert
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        forwardCurveNelsonSiegelSvensson.getForwards(
            new AnalyticModelFromCurvesAndVols(), new double[] {10.0d, 1.0d, 10.0d, 1.0d}),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getForwards(AnalyticModel, double[])}.
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getForwards(AnalyticModel,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] ForwardCurveNelsonSiegelSvensson.getForwards(AnalyticModel, double[])"
  })
  public void testGetForwards2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "42",
            new BusinessdayCalendarAny(),
            DateRollConvention.UNADJUSTED,
            null,
            new double[] {365.0d, 10.0d, 365.0d, 10.0d, 365.0d, 10.0d, 365.0d, 10.0d},
            10.0d);

    // Act and Assert
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        forwardCurveNelsonSiegelSvensson.getForwards(
            new AnalyticModelFromCurvesAndVols(), new double[] {10.0d, 1.0d, 10.0d, 1.0d}),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getForwards(AnalyticModel, double[])}.
   *
   * <ul>
   *   <li>When empty array of {@code double}.
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getForwards(AnalyticModel,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] ForwardCurveNelsonSiegelSvensson.getForwards(AnalyticModel, double[])"
  })
  public void testGetForwards_whenEmptyArrayOfDouble_thenReturnEmptyArrayOfDouble() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertArrayEquals(
        new double[] {},
        forwardCurveNelsonSiegelSvensson.getForwards(
            new AnalyticModelFromCurvesAndVols(), new double[] {}),
        0.0);
  }

  /**
   * Test {@link ForwardCurveNelsonSiegelSvensson#getPaymentOffset(double)}.
   *
   * <ul>
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link ForwardCurveNelsonSiegelSvensson#getPaymentOffset(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ForwardCurveNelsonSiegelSvensson.getPaymentOffset(double)"})
  public void testGetPaymentOffset_thenReturnFortyTwo() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurveNelsonSiegelSvensson =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "42",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(42.0d, forwardCurveNelsonSiegelSvensson.getPaymentOffset(10.0d), 0.0);
  }
}
