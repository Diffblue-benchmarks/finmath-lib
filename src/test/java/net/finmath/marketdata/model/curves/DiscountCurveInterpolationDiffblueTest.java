package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DiscountCurveInterpolationDiffblueTest {
  /**
   * Test {@link DiscountCurveInterpolation#toString()}.
   *
   * <p>Method under test: {@link DiscountCurveInterpolation#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String DiscountCurveInterpolation.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "DiscountCurveInterpolation [CurveFromInterpolationPoints [points=[Point [time=1.0, value=0.0,"
            + " isParameter=true], Point [time=10.0, value=0.23025850929940458, isParameter=true]], pointsBeingParameters"
            + "=[Point [time=1.0, value=0.0, isParameter=true], Point [time=10.0, value=0.23025850929940458,"
            + " isParameter=true]], interpolationMethod=LINEAR, extrapolationMethod=CONSTANT, interpolationEntity"
            + "=LOG_OF_VALUE_PER_TIME, rationalFunctionInterpolation=null, toString()=AbstractCurve [name=Name,"
            + " referenceDate=null],\n"
            + "1.00000000E0\tnull\t1.0\n"
            + "1.00000000E1\tnull\t10.000000000000002\n"
            + "]]",
        DiscountCurveInterpolation.createDiscountCurveFromDiscountFactors(
                "Name",
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d})
            .toString());
  }
}
