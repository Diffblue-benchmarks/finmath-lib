package net.finmath.marketdata2.model.curves;

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
   * <ul>
   *   <li>Given {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link AbstractForwardCurve#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractForwardCurve.toString()"})
  public void testToString_givenLocalDateWith1970AndOneAndOne_thenReturnAString() {
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
}
