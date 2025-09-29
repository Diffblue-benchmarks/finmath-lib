package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractForwardCurveDiffblueTest {
  /**
   * Test {@link AbstractForwardCurve#toString()}.
   *
   * <p>Method under test: {@link AbstractForwardCurve#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractForwardCurve.toString()"})
  public void testToString() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", null, "Payment Offset Code");
    forwardCurveFromDiscountCurve.addPoint(10.0d, 10.0d, true);

    // Act and Assert
    assertEquals(
        "ForwardCurveFromDiscountCurve [AbstractForwardCurve [CurveFromInterpolationPoints [points=[Point"
            + " [time=10.0, value=10.0, isParameter=true]], pointsBeingParameters=[Point [time=10.0, value=10.0,"
            + " isParameter=true]], interpolationMethod=LINEAR, extrapolationMethod=CONSTANT, interpolationEntity=VALUE,"
            + " rationalFunctionInterpolation=null, toString()=AbstractCurve [name=ForwardCurveFromDiscountCurve(3,Payment"
            + " Offset Code), referenceDate=null],\n"
            + "1.00000000E1\tnull\t10.0\n"
            + "], discountCurveName=3, paymentOffsetCode=Payment Offset Code, paymentBusinessdayCalendar=Businessda"
            + "yCalendarExcludingWeekends [baseCalendar=null], paymentDateRollConvention=FOLLOWING], referenceDisco"
            + "untCurveForForwardsName=3, daycountScaling=1.0, periodOffset=0.0]",
        forwardCurveFromDiscountCurve.toString());
  }

  /**
   * Test {@link AbstractForwardCurve#toString()}.
   *
   * <ul>
   *   <li>Given {@link ForwardCurveFromDiscountCurve#addPoint(double, double, boolean)} with time
   *       is one and value is ten and isParameter is {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractForwardCurve#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractForwardCurve.toString()"})
  public void testToString_givenAddPointWithTimeIsOneAndValueIsTenAndIsParameterIsFalse() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    forwardCurveFromDiscountCurve.addPoint(1.0d, 10.0d, false);
    forwardCurveFromDiscountCurve.addPoint(10.0d, 10.0d, true);

    // Act and Assert
    assertEquals(
        "ForwardCurveFromDiscountCurve [AbstractForwardCurve [CurveFromInterpolationPoints [points=[Point"
            + " [time=1.0, value=10.0, isParameter=false], Point [time=10.0, value=10.0, isParameter=true]],"
            + " pointsBeingParameters=[Point [time=10.0, value=10.0, isParameter=true]], interpolationMethod=LINEAR,"
            + " extrapolationMethod=CONSTANT, interpolationEntity=VALUE, rationalFunctionInterpolation=null,"
            + " toString()=AbstractCurve [name=ForwardCurveFromDiscountCurve(3,Payment Offset Code), referenceDate"
            + "=1970-01-01],\n"
            + "1.00000000E0\t1971-01-01\t10.0\n"
            + "1.00000000E1\t1979-12-30\t10.0\n"
            + "], discountCurveName=3, paymentOffsetCode=Payment Offset Code, paymentBusinessdayCalendar=Businessda"
            + "yCalendarExcludingWeekends [baseCalendar=null], paymentDateRollConvention=FOLLOWING], referenceDisco"
            + "untCurveForForwardsName=3, daycountScaling=1.0, periodOffset=0.0]",
        forwardCurveFromDiscountCurve.toString());
  }

  /**
   * Test {@link AbstractForwardCurve#toString()}.
   *
   * <ul>
   *   <li>Given {@link LocalDate} with {@code 1970} and one and one.
   * </ul>
   *
   * <p>Method under test: {@link AbstractForwardCurve#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractForwardCurve.toString()"})
  public void testToString_givenLocalDateWith1970AndOneAndOne() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act and Assert
    assertEquals(
        "ForwardCurveFromDiscountCurve [AbstractForwardCurve [CurveFromInterpolationPoints [points=[],"
            + " pointsBeingParameters=[], interpolationMethod=LINEAR, extrapolationMethod=CONSTANT, interpolationEntity"
            + "=VALUE, rationalFunctionInterpolation=null, toString()=AbstractCurve [name=ForwardCurveFromDiscountCurve"
            + "(3,Payment Offset Code), referenceDate=1970-01-01],\n"
            + "], discountCurveName=3, paymentOffsetCode=Payment Offset Code, paymentBusinessdayCalendar=Businessda"
            + "yCalendarExcludingWeekends [baseCalendar=null], paymentDateRollConvention=FOLLOWING], referenceDisco"
            + "untCurveForForwardsName=3, daycountScaling=1.0, periodOffset=0.0]",
        forwardCurveFromDiscountCurve.toString());
  }

  /**
   * Test {@link AbstractForwardCurve#toString()}.
   *
   * <ul>
   *   <li>Given {@link LocalDate} with {@code 1970} and one and one.
   * </ul>
   *
   * <p>Method under test: {@link AbstractForwardCurve#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractForwardCurve.toString()"})
  public void testToString_givenLocalDateWith1970AndOneAndOne2() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    forwardCurveFromDiscountCurve.addPoint(10.0d, 10.0d, true);

    // Act and Assert
    assertEquals(
        "ForwardCurveFromDiscountCurve [AbstractForwardCurve [CurveFromInterpolationPoints [points=[Point"
            + " [time=10.0, value=10.0, isParameter=true]], pointsBeingParameters=[Point [time=10.0, value=10.0,"
            + " isParameter=true]], interpolationMethod=LINEAR, extrapolationMethod=CONSTANT, interpolationEntity=VALUE,"
            + " rationalFunctionInterpolation=null, toString()=AbstractCurve [name=ForwardCurveFromDiscountCurve(3,Payment"
            + " Offset Code), referenceDate=1970-01-01],\n"
            + "1.00000000E1\t1979-12-30\t10.0\n"
            + "], discountCurveName=3, paymentOffsetCode=Payment Offset Code, paymentBusinessdayCalendar=Businessda"
            + "yCalendarExcludingWeekends [baseCalendar=null], paymentDateRollConvention=FOLLOWING], referenceDisco"
            + "untCurveForForwardsName=3, daycountScaling=1.0, periodOffset=0.0]",
        forwardCurveFromDiscountCurve.toString());
  }
}
