package net.finmath.marketdata2.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata2.model.AnalyticModel;
import net.finmath.marketdata2.model.AnalyticModelFromCurvesAndVols;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SwapLegDiffblueTest {
  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, String, double, String)}.
   *
   * <ul>
   *   <li>When {@code 3}.
   *   <li>Then return DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, String, double, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, String, double, String)"})
  public void testNewSwapLeg_when3_thenReturnDiscountCurveNameIs3() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg = new SwapLeg(legSchedule, "Forward Curve Name", 10.0d, "3");

    // Assert
    assertEquals("3", actualSwapLeg.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualSwapLeg.getForwardCurveName());
    assertEquals(10.0d, actualSwapLeg.getSpread(), 0.0);
    assertFalse(actualSwapLeg.isNotionalExchanged());
    assertSame(legSchedule, actualSwapLeg.getSchedule());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, String, double, String, boolean)}.
   *
   * <ul>
   *   <li>When {@code 3}.
   *   <li>Then return DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, String, double, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, String, double, String, boolean)"})
  public void testNewSwapLeg_when3_thenReturnDiscountCurveNameIs32() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg = new SwapLeg(legSchedule, "Forward Curve Name", 10.0d, "3", true);

    // Assert
    assertEquals("3", actualSwapLeg.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualSwapLeg.getForwardCurveName());
    assertEquals(10.0d, actualSwapLeg.getSpread(), 0.0);
    assertTrue(actualSwapLeg.isNotionalExchanged());
    assertSame(legSchedule, actualSwapLeg.getSchedule());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, String, double, String, String, boolean)}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, String, double, String, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, String, double, String, String, boolean)"})
  public void testNewSwapLeg_whenEmptyString() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg = new SwapLeg(legSchedule, "Forward Curve Name", 10.0d, "3", "", true);

    // Assert
    assertEquals("3", actualSwapLeg.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualSwapLeg.getForwardCurveName());
    assertEquals(10.0d, actualSwapLeg.getSpread(), 0.0);
    assertTrue(actualSwapLeg.isNotionalExchanged());
    assertSame(legSchedule, actualSwapLeg.getSchedule());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, String, double, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return DiscountCurveName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, String, double, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, String, double, String)"})
  public void testNewSwapLeg_whenEmptyString_thenReturnDiscountCurveNameIsEmptyString() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg = new SwapLeg(legSchedule, "Forward Curve Name", 10.0d, "");

    // Assert
    assertEquals("", actualSwapLeg.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualSwapLeg.getForwardCurveName());
    assertEquals(10.0d, actualSwapLeg.getSpread(), 0.0);
    assertFalse(actualSwapLeg.isNotionalExchanged());
    assertSame(legSchedule, actualSwapLeg.getSchedule());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, String, double, String, boolean)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return DiscountCurveName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, String, double, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, String, double, String, boolean)"})
  public void testNewSwapLeg_whenEmptyString_thenReturnDiscountCurveNameIsEmptyString2() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg = new SwapLeg(legSchedule, "Forward Curve Name", 10.0d, "", true);

    // Assert
    assertEquals("", actualSwapLeg.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualSwapLeg.getForwardCurveName());
    assertEquals(10.0d, actualSwapLeg.getSpread(), 0.0);
    assertTrue(actualSwapLeg.isNotionalExchanged());
    assertSame(legSchedule, actualSwapLeg.getSchedule());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, String, double, String, String, boolean)}.
   *
   * <ul>
   *   <li>When {@link RegularSchedule#RegularSchedule(TimeDiscretization)} with timeDiscretization
   *       is {@link TenorFromArray#TenorFromArray(double, int, double)}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, String, double, String, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, String, double, String, String, boolean)"})
  public void testNewSwapLeg_whenRegularScheduleWithTimeDiscretizationIsTenorFromArray() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg = new SwapLeg(legSchedule, "Forward Curve Name", 10.0d, "3", "3", true);

    // Assert
    assertEquals("3", actualSwapLeg.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualSwapLeg.getForwardCurveName());
    assertEquals(10.0d, actualSwapLeg.getSpread(), 0.0);
    assertTrue(actualSwapLeg.isNotionalExchanged());
    assertSame(legSchedule, actualSwapLeg.getSchedule());
  }

  /**
   * Test {@link SwapLeg#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>When {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols()}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable SwapLeg.getValue(double, AnalyticModel)"
  })
  public void testGetValueWithDoubleAnalyticModel_whenAnalyticModelFromCurvesAndVols() {
    // Arrange
    SwapLeg swapLeg =
        new SwapLeg(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            10.0d,
            "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> swapLeg.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link SwapLeg#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable SwapLeg.getValue(double, AnalyticModel)"
  })
  public void testGetValueWithDoubleAnalyticModel_whenNull() {
    // Arrange
    SwapLeg swapLeg =
        new SwapLeg(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            10.0d,
            "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> swapLeg.getValue(10.0d, (AnalyticModel) null));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SwapLeg#toString()}
   *   <li>{@link SwapLeg#getDiscountCurveName()}
   *   <li>{@link SwapLeg#getForwardCurveName()}
   *   <li>{@link SwapLeg#getSchedule()}
   *   <li>{@link SwapLeg#getSpread()}
   *   <li>{@link SwapLeg#isNotionalExchanged()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SwapLeg.getDiscountCurveName()",
    "String SwapLeg.getForwardCurveName()",
    "Schedule SwapLeg.getSchedule()",
    "double SwapLeg.getSpread()",
    "boolean SwapLeg.isNotionalExchanged()",
    "String SwapLeg.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    SwapLeg swapLeg = new SwapLeg(legSchedule, "Forward Curve Name", 10.0d, "3");

    // Act
    swapLeg.toString();
    String actualDiscountCurveName = swapLeg.getDiscountCurveName();
    String actualForwardCurveName = swapLeg.getForwardCurveName();
    Schedule actualSchedule = swapLeg.getSchedule();
    double actualSpread = swapLeg.getSpread();

    // Assert
    assertEquals("3", actualDiscountCurveName);
    assertEquals("Forward Curve Name", actualForwardCurveName);
    assertEquals(10.0d, actualSpread, 0.0);
    assertFalse(swapLeg.isNotionalExchanged());
    assertSame(legSchedule, actualSchedule);
  }
}
