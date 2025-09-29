package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ForwardCurveFromDiscountCurveDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ForwardCurveFromDiscountCurve#toString()}
   *   <li>{@link ForwardCurveFromDiscountCurve#getParameter()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] ForwardCurveFromDiscountCurve.getParameter()",
    "String ForwardCurveFromDiscountCurve.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    String actualToStringResult = forwardCurveFromDiscountCurve.toString();

    // Assert
    assertEquals(
        "ForwardCurveFromDiscountCurve [AbstractForwardCurve [CurveFromInterpolationPoints [points=[],"
            + " pointsBeingParameters=[], interpolationMethod=LINEAR, extrapolationMethod=CONSTANT, interpolationEntity"
            + "=VALUE, rationalFunctionInterpolation=null, toString()=AbstractCurve [name=ForwardCurveFromDiscountCurve"
            + "(3,Payment Offset Code), referenceDate=1970-01-01],\n"
            + "], discountCurveName=3, paymentOffsetCode=Payment Offset Code, paymentBusinessdayCalendar=Businessda"
            + "yCalendarExcludingWeekends [baseCalendar=null], paymentDateRollConvention=FOLLOWING], referenceDisco"
            + "untCurveForForwardsName=3, daycountScaling=1.0, periodOffset=0.0]",
        actualToStringResult);
    assertNull(forwardCurveFromDiscountCurve.getParameter());
  }
}
