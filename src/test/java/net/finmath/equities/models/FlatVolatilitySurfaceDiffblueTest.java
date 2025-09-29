package net.finmath.equities.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.equities.marketdata.AffineDividend;
import net.finmath.equities.marketdata.AffineDividendStream;
import net.finmath.equities.marketdata.FlatYieldCurve;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FlatVolatilitySurfaceDiffblueTest {
  /**
   * Test {@link FlatVolatilitySurface#FlatVolatilitySurface(double)}.
   *
   * <p>Method under test: {@link FlatVolatilitySurface#FlatVolatilitySurface(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FlatVolatilitySurface.<init>(double)"})
  public void testNewFlatVolatilitySurface() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new FlatVolatilitySurface(10.0d).getShift(), 0.0);
  }

  /**
   * Test {@link FlatVolatilitySurface#FlatVolatilitySurface(double, double)}.
   *
   * <p>Method under test: {@link FlatVolatilitySurface#FlatVolatilitySurface(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FlatVolatilitySurface.<init>(double, double)"})
  public void testNewFlatVolatilitySurface2() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new FlatVolatilitySurface(10.0d, 10.0d).getShift(), 0.0);
  }

  /**
   * Test {@link FlatVolatilitySurface#getShiftedSurface(double)}.
   *
   * <p>Method under test: {@link FlatVolatilitySurface#getShiftedSurface(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ShiftedVolatilitySurface FlatVolatilitySurface.getShiftedSurface(double)"})
  public void testGetShiftedSurface() {
    // Arrange and Act
    ShiftedVolatilitySurface actualShiftedSurface =
        new FlatVolatilitySurface(10.0d).getShiftedSurface(10.0d);

    // Assert
    assertTrue(actualShiftedSurface instanceof FlatVolatilitySurface);
    assertEquals(10.0d, actualShiftedSurface.getShift(), 0.0);
  }

  /**
   * Test {@link FlatVolatilitySurface#getShift()}.
   *
   * <p>Method under test: {@link FlatVolatilitySurface#getShift()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double FlatVolatilitySurface.getShift()"})
  public void testGetShift() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new FlatVolatilitySurface(10.0d).getShift(), 0.0);
  }

  /**
   * Test {@link FlatVolatilitySurface#getVolatility(double, LocalDate, EquityForwardStructure)}
   * with {@code strike}, {@code expiryDate}, {@code currentForwardStructure}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link FlatVolatilitySurface#getVolatility(double, LocalDate,
   * EquityForwardStructure)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double FlatVolatilitySurface.getVolatility(double, LocalDate, EquityForwardStructure)"
  })
  public void testGetVolatilityWithStrikeExpiryDateCurrentForwardStructure_thenReturnTen() {
    // Arrange
    FlatVolatilitySurface flatVolatilitySurface = new FlatVolatilitySurface(10.0d);
    LocalDate expiryDate = LocalDate.of(1970, 1, 1);
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        10.0d,
        flatVolatilitySurface.getVolatility(
            10.0d,
            expiryDate,
            new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))),
        0.0);
  }

  /**
   * Test {@link FlatVolatilitySurface#getVolatility(double, double, EquityForwardStructure)} with
   * {@code strike}, {@code timeToMaturity}, {@code currentForwardStructure}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link FlatVolatilitySurface#getVolatility(double, double,
   * EquityForwardStructure)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double FlatVolatilitySurface.getVolatility(double, double, EquityForwardStructure)"
  })
  public void testGetVolatilityWithStrikeTimeToMaturityCurrentForwardStructure_thenReturnTen() {
    // Arrange
    FlatVolatilitySurface flatVolatilitySurface = new FlatVolatilitySurface(10.0d);
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        10.0d,
        flatVolatilitySurface.getVolatility(
            10.0d,
            10.0d,
            new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))),
        0.0);
  }

  /**
   * Test {@link FlatVolatilitySurface#getLocalVolatility(double, double, EquityForwardStructure,
   * double, double)} with {@code logStrike}, {@code timeToMaturity}, {@code
   * currentForwardStructure}, {@code strikeShift}, {@code timeShift}.
   *
   * <p>Method under test: {@link FlatVolatilitySurface#getLocalVolatility(double, double,
   * EquityForwardStructure, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double FlatVolatilitySurface.getLocalVolatility(double, double, EquityForwardStructure, double, double)"
  })
  public void
      testGetLocalVolatilityWithLogStrikeTimeToMaturityCurrentForwardStructureStrikeShiftTimeShift() {
    // Arrange
    FlatVolatilitySurface flatVolatilitySurface = new FlatVolatilitySurface(10.0d);
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        10.0d,
        flatVolatilitySurface.getLocalVolatility(
            10.0d,
            10.0d,
            new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true)),
            10.0d,
            10.0d),
        0.0);
  }

  /**
   * Test {@link FlatVolatilitySurface#getLocalVolatility(double, LocalDate, EquityForwardStructure,
   * double, double)} with {@code strike}, {@code expiryDate}, {@code currentForwardStructure},
   * {@code strikeShift}, {@code timeShift}.
   *
   * <p>Method under test: {@link FlatVolatilitySurface#getLocalVolatility(double, LocalDate,
   * EquityForwardStructure, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double FlatVolatilitySurface.getLocalVolatility(double, LocalDate, EquityForwardStructure, double, double)"
  })
  public void
      testGetLocalVolatilityWithStrikeExpiryDateCurrentForwardStructureStrikeShiftTimeShift() {
    // Arrange
    FlatVolatilitySurface flatVolatilitySurface = new FlatVolatilitySurface(10.0d);
    LocalDate expiryDate = LocalDate.of(1970, 1, 1);
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        10.0d,
        flatVolatilitySurface.getLocalVolatility(
            10.0d,
            expiryDate,
            new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true)),
            10.0d,
            10.0d),
        0.0);
  }
}
