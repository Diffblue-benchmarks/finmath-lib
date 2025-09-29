package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation.InterpolationEntityForward;
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
    ForwardCurveInterpolation createForwardCurveFromDiscountFactorsResult =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act
    String actualToStringResult = createForwardCurveFromDiscountFactorsResult.toString();

    // Assert
    assertEquals(
        "ForwardCurve [AbstractForwardCurve [CurveFromInterpolationPoints [points=[Point [time=0.0, value=-0.09,"
            + " isParameter=true], Point [time=1.0, value=-0.1, isParameter=true], Point [time=10.0, value=-1.0,"
            + " isParameter=true]], pointsBeingParameters=[Point [time=0.0, value=-0.09, isParameter=true], Point"
            + " [time=1.0, value=-0.1, isParameter=true], Point [time=10.0, value=-1.0, isParameter=true]],"
            + " interpolationMethod=LINEAR, extrapolationMethod=CONSTANT, interpolationEntity=VALUE, rationalFuncti"
            + "onInterpolation=null, toString()=AbstractCurve [name=Name, referenceDate=null],\n"
            + "0.00000000E0\tnull\t-0.09\n"
            + "1.00000000E0\tnull\t-0.1\n"
            + "1.00000000E1\tnull\t-1.0\n"
            + "], discountCurveName=null, paymentOffsetCode=null, paymentBusinessdayCalendar=null, paymentDateRollConvention"
            + "=null], interpolationEntityForward=FORWARD]",
        actualToStringResult);
    assertEquals(
        InterpolationEntityForward.FORWARD,
        createForwardCurveFromDiscountFactorsResult.getInterpolationEntityForward());
  }
}
