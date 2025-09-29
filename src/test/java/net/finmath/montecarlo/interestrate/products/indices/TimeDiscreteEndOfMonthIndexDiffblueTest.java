package net.finmath.montecarlo.interestrate.products.indices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Set;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TimeDiscreteEndOfMonthIndexDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TimeDiscreteEndOfMonthIndex#TimeDiscreteEndOfMonthIndex(String, AbstractIndex,
   *       int)}
   *   <li>{@link TimeDiscreteEndOfMonthIndex#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TimeDiscreteEndOfMonthIndex.<init>(String, AbstractIndex, int)",
    "String TimeDiscreteEndOfMonthIndex.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    TimeDiscreteEndOfMonthIndex actualTimeDiscreteEndOfMonthIndex =
        new TimeDiscreteEndOfMonthIndex("Name", new FixedCoupon(10.0d), 1);
    String actualToStringResult = actualTimeDiscreteEndOfMonthIndex.toString();

    // Assert
    assertEquals("Name", actualTimeDiscreteEndOfMonthIndex.getName());
    assertEquals(
        "TimeDiscreteEndOfMonthIndex [baseIndex=FixedCoupon [coupon=RandomVariableFromDoubleArray[ realizations=10.0,"
            + " isDeterministic()=true, filtrationTime=-Infinity, typePriority=1], toString()=AbstractMonteCarloProduct"
            + " [currency=null]], fixingOffsetMonths=1]",
        actualToStringResult);
    assertNull(actualTimeDiscreteEndOfMonthIndex.getCurrency());
  }

  /**
   * Test {@link TimeDiscreteEndOfMonthIndex#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Given {@link FixedCoupon#FixedCoupon(double)} with coupon is ten.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscreteEndOfMonthIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set TimeDiscreteEndOfMonthIndex.queryUnderlyings()"})
  public void testQueryUnderlyings_givenFixedCouponWithCouponIsTen_thenReturnNull() {
    // Arrange
    TimeDiscreteEndOfMonthIndex timeDiscreteEndOfMonthIndex =
        new TimeDiscreteEndOfMonthIndex("Name", new FixedCoupon(10.0d), 1);

    // Act and Assert
    assertNull(timeDiscreteEndOfMonthIndex.queryUnderlyings());
  }

  /**
   * Test {@link TimeDiscreteEndOfMonthIndex#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscreteEndOfMonthIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set TimeDiscreteEndOfMonthIndex.queryUnderlyings()"})
  public void testQueryUnderlyings_thenReturnSizeIsOne() {
    // Arrange
    AnalyticModelForwardCurveIndex baseIndex =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);
    TimeDiscreteEndOfMonthIndex timeDiscreteEndOfMonthIndex =
        new TimeDiscreteEndOfMonthIndex("Name", baseIndex, 1);

    // Act
    Set<String> actualQueryUnderlyingsResult = timeDiscreteEndOfMonthIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }
}
