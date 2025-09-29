package net.finmath.equities.marketdata;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class YieldCurveDiffblueTest {
  /**
   * Test {@link YieldCurve#getRate(LocalDate)} with {@code date}.
   *
   * <ul>
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link YieldCurve#getRate(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double YieldCurve.getRate(LocalDate)"})
  public void testGetRateWithDate_thenReturnPositive_infinity() {
    // Arrange
    LocalDate curveDate = LocalDate.of(1970, 1, 1);

    // Act and Assert
    assertEquals(
        Double.POSITIVE_INFINITY,
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true))
            .getRate(LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link YieldCurve#getRate(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link YieldCurve#getRate(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double YieldCurve.getRate(double)"})
  public void testGetRateWithMaturity_thenReturnPositive_infinity() {
    // Arrange
    LocalDate curveDate = LocalDate.of(1970, 1, 1);

    // Act and Assert
    assertEquals(
        Double.POSITIVE_INFINITY,
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true)).getRate(10.0d),
        0.0);
  }

  /**
   * Test {@link YieldCurve#getRate(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link YieldCurve#getRate(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double YieldCurve.getRate(double)"})
  public void testGetRateWithMaturity_thenReturnPositive_infinity2() {
    // Arrange
    LocalDate curveDate = LocalDate.of(1970, 1, 1);

    // Act and Assert
    assertEquals(
        Double.POSITIVE_INFINITY,
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true)).getRate(0.0d),
        0.0);
  }

  /**
   * Test {@link YieldCurve#getDiscountFactor(LocalDate)} with {@code date}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link YieldCurve#getDiscountFactor(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double YieldCurve.getDiscountFactor(LocalDate)"})
  public void testGetDiscountFactorWithDate_thenReturnNaN() {
    // Arrange
    LocalDate curveDate = LocalDate.of(1970, 1, 1);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true))
            .getDiscountFactor(LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link YieldCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link YieldCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double YieldCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenReturnZero() {
    // Arrange
    LocalDate curveDate = LocalDate.of(1970, 1, 1);

    // Act and Assert
    assertEquals(
        0.0d,
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true))
            .getDiscountFactor(10.0d),
        0.0);
  }

  /**
   * Test {@link YieldCurve#getForwardDiscountFactor(LocalDate, LocalDate)} with {@code startDate},
   * {@code endDate}.
   *
   * <p>Method under test: {@link YieldCurve#getForwardDiscountFactor(LocalDate, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double YieldCurve.getForwardDiscountFactor(LocalDate, LocalDate)"})
  public void testGetForwardDiscountFactorWithStartDateEndDate() {
    // Arrange
    LocalDate curveDate = LocalDate.ofYearDay(1, 1);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true))
            .getForwardDiscountFactor(LocalDate.ofYearDay(1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link YieldCurve#getForwardDiscountFactor(LocalDate, LocalDate)} with {@code startDate},
   * {@code endDate}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link YieldCurve#getForwardDiscountFactor(LocalDate, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double YieldCurve.getForwardDiscountFactor(LocalDate, LocalDate)"})
  public void testGetForwardDiscountFactorWithStartDateEndDate_thenReturnNaN() {
    // Arrange
    LocalDate curveDate = LocalDate.of(1970, 1, 1);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true))
            .getForwardDiscountFactor(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link YieldCurve#getForwardDiscountFactor(double, double)} with {@code start}, {@code
   * expiry}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link YieldCurve#getForwardDiscountFactor(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double YieldCurve.getForwardDiscountFactor(double, double)"})
  public void testGetForwardDiscountFactorWithStartExpiry_thenReturnNaN() {
    // Arrange
    LocalDate curveDate = LocalDate.of(1970, 1, 1);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true))
            .getForwardDiscountFactor(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link YieldCurve#getForwardDiscountFactor(double, double)} with {@code start}, {@code
   * expiry}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link YieldCurve#getForwardDiscountFactor(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double YieldCurve.getForwardDiscountFactor(double, double)"})
  public void testGetForwardDiscountFactorWithStartExpiry_thenReturnNaN2() {
    // Arrange
    LocalDate curveDate = LocalDate.of(1970, 1, 1);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true))
            .getForwardDiscountFactor(0.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link YieldCurve#getForwardDiscountFactor(double, double)} with {@code start}, {@code
   * expiry}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link YieldCurve#getForwardDiscountFactor(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double YieldCurve.getForwardDiscountFactor(double, double)"})
  public void testGetForwardDiscountFactorWithStartExpiry_when05_thenReturnNaN() {
    // Arrange
    LocalDate curveDate = LocalDate.of(1970, 1, 1);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true))
            .getForwardDiscountFactor(0.5d, 10.0d),
        0.0);
  }

  /**
   * Test {@link YieldCurve#getForwardDiscountFactor(double, double)} with {@code start}, {@code
   * expiry}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link YieldCurve#getForwardDiscountFactor(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double YieldCurve.getForwardDiscountFactor(double, double)"})
  public void testGetForwardDiscountFactorWithStartExpiry_whenOne_thenReturnNaN() {
    // Arrange
    LocalDate curveDate = LocalDate.of(1970, 1, 1);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true))
            .getForwardDiscountFactor(1.0d, 10.0d),
        0.0);
  }
}
