package net.finmath.equities.marketdata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AffineDividendStreamDiffblueTest {
  /**
   * Test {@link AffineDividendStream#AffineDividendStream(AffineDividend[])}.
   *
   * <ul>
   *   <li>Then return DividendDates size is one.
   * </ul>
   *
   * <p>Method under test: {@link AffineDividendStream#AffineDividendStream(AffineDividend[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AffineDividendStream.<init>(AffineDividend[])"})
  public void testNewAffineDividendStream_thenReturnDividendDatesSizeIsOne() {
    // Arrange
    LocalDate date = LocalDate.of(1970, 1, 1);
    AffineDividend[] dividendStream = new AffineDividend[] {new AffineDividend(date, 10.0d, 10.0d)};

    // Act
    AffineDividendStream actualAffineDividendStream = new AffineDividendStream(dividendStream);

    // Assert
    ArrayList<LocalDate> dividendDates = actualAffineDividendStream.getDividendDates();
    assertEquals(1, dividendDates.size());
    LocalDate getResult = dividendDates.get(0);
    assertEquals("1970-01-01", getResult.toString());
    assertSame(date, getResult);
  }

  /**
   * Test {@link AffineDividendStream#AffineDividendStream(AffineDividend[])}.
   *
   * <ul>
   *   <li>Then return DividendDates size is two.
   * </ul>
   *
   * <p>Method under test: {@link AffineDividendStream#AffineDividendStream(AffineDividend[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AffineDividendStream.<init>(AffineDividend[])"})
  public void testNewAffineDividendStream_thenReturnDividendDatesSizeIsTwo() {
    // Arrange
    LocalDate date = LocalDate.of(1970, 1, 1);
    AffineDividend affineDividend = new AffineDividend(date, 10.0d, 10.0d);
    LocalDate date2 = LocalDate.of(1970, 1, 1);

    // Act
    AffineDividendStream actualAffineDividendStream =
        new AffineDividendStream(
            new AffineDividend[] {affineDividend, new AffineDividend(date2, 10.0d, 10.0d)});

    // Assert
    ArrayList<LocalDate> dividendDates = actualAffineDividendStream.getDividendDates();
    assertEquals(2, dividendDates.size());
    LocalDate getResult = dividendDates.get(0);
    assertEquals("1970-01-01", getResult.toString());
    LocalDate getResult2 = dividendDates.get(1);
    assertEquals("1970-01-01", getResult2.toString());
    assertSame(date, getResult);
    assertSame(date2, getResult2);
  }

  /**
   * Test {@link AffineDividendStream#getDividendDates()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link AffineDividendStream#getDividendDates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ArrayList AffineDividendStream.getDividendDates()"})
  public void testGetDividendDates_thenReturnSizeIsOne() {
    // Arrange
    LocalDate date = LocalDate.of(1970, 1, 1);
    AffineDividend[] dividendStream = new AffineDividend[] {new AffineDividend(date, 10.0d, 10.0d)};
    AffineDividendStream affineDividendStream = new AffineDividendStream(dividendStream);

    // Act
    ArrayList<LocalDate> actualDividendDates = affineDividendStream.getDividendDates();

    // Assert
    assertEquals(1, actualDividendDates.size());
    LocalDate getResult = actualDividendDates.get(0);
    assertEquals("1970-01-01", getResult.toString());
    assertSame(date, getResult);
  }

  /**
   * Test {@link AffineDividendStream#getDividend(LocalDate, double)}.
   *
   * <ul>
   *   <li>Then return one hundred ten.
   * </ul>
   *
   * <p>Method under test: {@link AffineDividendStream#getDividend(LocalDate, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AffineDividendStream.getDividend(LocalDate, double)"})
  public void testGetDividend_thenReturnOneHundredTen() {
    // Arrange
    AffineDividend[] dividendStream = new AffineDividend[] {new AffineDividend(null, 10.0d, 10.0d)};
    AffineDividendStream affineDividendStream = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(110.0d, affineDividendStream.getDividend(null, 10.0d), 0.0);
  }

  /**
   * Test {@link AffineDividendStream#getDividend(LocalDate, double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AffineDividendStream#getDividend(LocalDate, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AffineDividendStream.getDividend(LocalDate, double)"})
  public void testGetDividend_thenReturnZero() {
    // Arrange
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream affineDividendStream = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(0.0d, affineDividendStream.getDividend(LocalDate.of(1970, 1, 1), 10.0d), 0.0);
  }

  /**
   * Test {@link AffineDividendStream#getProportionalDividendFactor(LocalDate)}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link AffineDividendStream#getProportionalDividendFactor(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AffineDividendStream.getProportionalDividendFactor(LocalDate)"})
  public void testGetProportionalDividendFactor_thenReturnOne() {
    // Arrange
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream affineDividendStream = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        1.0d, affineDividendStream.getProportionalDividendFactor(LocalDate.of(1970, 1, 1)), 0.0);
  }

  /**
   * Test {@link AffineDividendStream#getProportionalDividendFactor(LocalDate)}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link AffineDividendStream#getProportionalDividendFactor(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AffineDividendStream.getProportionalDividendFactor(LocalDate)"})
  public void testGetProportionalDividendFactor_thenReturnTen() {
    // Arrange
    AffineDividend[] dividendStream = new AffineDividend[] {new AffineDividend(null, 10.0d, 10.0d)};
    AffineDividendStream affineDividendStream = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(10.0d, affineDividendStream.getProportionalDividendFactor(null), 0.0);
  }

  /**
   * Test {@link AffineDividendStream#getCashDividend(LocalDate)}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link AffineDividendStream#getCashDividend(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AffineDividendStream.getCashDividend(LocalDate)"})
  public void testGetCashDividend_thenReturnTen() {
    // Arrange
    AffineDividend[] dividendStream = new AffineDividend[] {new AffineDividend(null, 10.0d, 10.0d)};
    AffineDividendStream affineDividendStream = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(10.0d, affineDividendStream.getCashDividend(null), 0.0);
  }

  /**
   * Test {@link AffineDividendStream#getCashDividend(LocalDate)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AffineDividendStream#getCashDividend(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AffineDividendStream.getCashDividend(LocalDate)"})
  public void testGetCashDividend_thenReturnZero() {
    // Arrange
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream affineDividendStream = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(0.0d, affineDividendStream.getCashDividend(LocalDate.of(1970, 1, 1)), 0.0);
  }

  /**
   * Test {@link AffineDividendStream#getAffineDividendsFromCashDividends(AffineDividendStream,
   * HashMap, LocalDate, double, YieldCurve)}.
   *
   * <ul>
   *   <li>Then return DividendDates Empty.
   * </ul>
   *
   * <p>Method under test: {@link
   * AffineDividendStream#getAffineDividendsFromCashDividends(AffineDividendStream, HashMap,
   * LocalDate, double, YieldCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AffineDividendStream AffineDividendStream.getAffineDividendsFromCashDividends(AffineDividendStream, HashMap, LocalDate, double, YieldCurve)"
  })
  public void testGetAffineDividendsFromCashDividends_thenReturnDividendDatesEmpty() {
    // Arrange
    LocalDate date = LocalDate.ofYearDay(1, 1);
    AffineDividend[] dividendStream = new AffineDividend[] {new AffineDividend(date, 10.0d, 10.0d)};
    AffineDividendStream cashDividends = new AffineDividendStream(dividendStream);
    HashMap<LocalDate, Double> transformationFactors = new HashMap<>();
    LocalDate valDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);

    // Act
    AffineDividendStream actualAffineDividendsFromCashDividends =
        AffineDividendStream.getAffineDividendsFromCashDividends(
            cashDividends,
            transformationFactors,
            valDate,
            10.0d,
            new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true)));

    // Assert
    assertTrue(actualAffineDividendsFromCashDividends.getDividendDates().isEmpty());
  }
}
