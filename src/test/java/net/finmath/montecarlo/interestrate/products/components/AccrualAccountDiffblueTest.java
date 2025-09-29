package net.finmath.montecarlo.interestrate.products.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Set;
import net.finmath.montecarlo.interestrate.products.indices.AbstractIndex;
import net.finmath.montecarlo.interestrate.products.indices.AnalyticModelForwardCurveIndex;
import net.finmath.montecarlo.interestrate.products.indices.AnalyticModelIndex;
import net.finmath.montecarlo.interestrate.products.indices.FixedCoupon;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AccrualAccountDiffblueTest {
  /**
   * Test {@link AccrualAccount#AccrualAccount(String, AnalyticModelIndex, AbstractIndex, double)}.
   *
   * <p>Method under test: {@link AccrualAccount#AccrualAccount(String, AnalyticModelIndex,
   * AbstractIndex, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AccrualAccount.<init>(String, AnalyticModelIndex, AbstractIndex, double)"
  })
  public void testNewAccrualAccount() {
    // Arrange
    AnalyticModelIndex pastFixings = new AnalyticModelIndex("Name", "Curve Name", 10.0d);

    // Act
    AccrualAccount actualAccrualAccount =
        new AccrualAccount("GBP", pastFixings, new FixedCoupon(10.0d), 10.0d);

    // Assert
    assertEquals("GBP", actualAccrualAccount.getCurrency());
  }

  /**
   * Test {@link AccrualAccount#queryUnderlyings()}.
   *
   * <p>Method under test: {@link AccrualAccount#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set AccrualAccount.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange
    AnalyticModelIndex pastFixings = new AnalyticModelIndex("Name", "Curve Name", 10.0d);
    AnalyticModelForwardCurveIndex accrualIndex =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);

    AccrualAccount accrualAccount = new AccrualAccount("GBP", pastFixings, accrualIndex, 10.0d);

    // Act
    Set<String> actualQueryUnderlyingsResult = accrualAccount.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }

  /**
   * Test {@link AccrualAccount#queryUnderlyings()}.
   *
   * <p>Method under test: {@link AccrualAccount#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set AccrualAccount.queryUnderlyings()"})
  public void testQueryUnderlyings2() {
    // Arrange
    AnalyticModelIndex pastFixings = new AnalyticModelIndex("Name", "Curve Name", 10.0d);
    AnalyticModelIndex accrualIndex = new AnalyticModelIndex("Name", "Curve Name", 10.0d);

    AccrualAccount accrualAccount = new AccrualAccount("GBP", pastFixings, accrualIndex, 10.0d);

    // Act
    Set<String> actualQueryUnderlyingsResult = accrualAccount.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }

  /**
   * Test {@link AccrualAccount#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Given {@link FixedCoupon#FixedCoupon(double)} with coupon is ten.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AccrualAccount#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set AccrualAccount.queryUnderlyings()"})
  public void testQueryUnderlyings_givenFixedCouponWithCouponIsTen_thenReturnNull() {
    // Arrange
    AnalyticModelIndex pastFixings = new AnalyticModelIndex("Name", "Curve Name", 10.0d);
    AccrualAccount accrualAccount =
        new AccrualAccount("GBP", pastFixings, new FixedCoupon(10.0d), 10.0d);

    // Act and Assert
    assertNull(accrualAccount.queryUnderlyings());
  }
}
