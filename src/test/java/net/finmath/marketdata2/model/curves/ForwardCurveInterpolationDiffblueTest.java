package net.finmath.marketdata2.model.curves;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata2.model.curves.ForwardCurveInterpolation.InterpolationEntityForward;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ForwardCurveInterpolationDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ForwardCurveInterpolation#toString()}
   *   <li>{@link ForwardCurveInterpolation#getInterpolationEntityForward()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "InterpolationEntityForward ForwardCurveInterpolation.getInterpolationEntityForward()",
    "String ForwardCurveInterpolation.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ForwardCurveInterpolation forwardCurveInterpolation =
        new ForwardCurveInterpolation("Name", 10.0d, InterpolationEntityForward.FORWARD, "3");

    // Act
    String actualToStringResult = forwardCurveInterpolation.toString();

    // Assert
    assertEquals(
        "ForwardCurve [AbstractForwardCurve [CurveFromInterpolationPoints [points=[], pointsBeingParameters=[],"
            + " interpolationMethod=LINEAR, extrapolationMethod=CONSTANT, interpolationEntity=VALUE, rationalFuncti"
            + "onInterpolation=null, toString()=AbstractCurve [name=Name, referenceDate=null],\n"
            + "], discountCurveName=3, paymentOffsetCode=null, paymentBusinessdayCalendar=null, paymentDateRollConvention"
            + "=null], interpolationEntityForward=FORWARD]",
        actualToStringResult);
    assertEquals(
        InterpolationEntityForward.FORWARD,
        forwardCurveInterpolation.getInterpolationEntityForward());
  }
}
