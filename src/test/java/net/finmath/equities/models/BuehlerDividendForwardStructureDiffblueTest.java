package net.finmath.equities.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import net.finmath.equities.marketdata.AffineDividend;
import net.finmath.equities.marketdata.AffineDividendStream;
import net.finmath.equities.marketdata.FlatYieldCurve;
import net.finmath.equities.marketdata.YieldCurve;
import net.finmath.equities.models.EquityForwardStructure.DividendModelType;
import net.finmath.time.daycount.DayCountConvention;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BuehlerDividendForwardStructureDiffblueTest {
  /**
   * Test {@link BuehlerDividendForwardStructure#BuehlerDividendForwardStructure(LocalDate, double,
   * YieldCurve, AffineDividendStream, DayCountConvention)}.
   *
   * <ul>
   *   <li>Then RepoCurve return {@link FlatYieldCurve}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BuehlerDividendForwardStructure#BuehlerDividendForwardStructure(LocalDate, double, YieldCurve,
   * AffineDividendStream, DayCountConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BuehlerDividendForwardStructure.<init>(LocalDate, double, YieldCurve, AffineDividendStream, DayCountConvention)"
  })
  public void testNewBuehlerDividendForwardStructure_thenRepoCurveReturnFlatYieldCurve() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    LocalDate date = LocalDate.of(1970, 1, 1);
    AffineDividend[] dividendStream = new AffineDividend[] {new AffineDividend(date, 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act
    BuehlerDividendForwardStructure actualBuehlerDividendForwardStructure =
        new BuehlerDividendForwardStructure(
            valuationDate, 10.0d, repoCurve, dividendStream2, new DayCountConvention_30E_360(true));

    // Assert
    YieldCurve repoCurve2 = actualBuehlerDividendForwardStructure.getRepoCurve();
    assertTrue(repoCurve2 instanceof FlatYieldCurve);
    AffineDividendStream dividendStream3 =
        actualBuehlerDividendForwardStructure.getDividendStream();
    ArrayList<LocalDate> dividendDates = dividendStream3.getDividendDates();
    assertEquals(1, dividendDates.size());
    assertEquals(10.0d, actualBuehlerDividendForwardStructure.getSpot(), 0.0);
    assertEquals(
        DividendModelType.Buehler, actualBuehlerDividendForwardStructure.getDividendModel());
    assertSame(dividendStream2, dividendStream3);
    assertSame(repoCurve, repoCurve2);
    assertSame(date, dividendDates.get(0));
    assertSame(valuationDate, actualBuehlerDividendForwardStructure.getValuationDate());
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#validate()}.
   *
   * <ul>
   *   <li>Then calls {@link AffineDividend#getDate()}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#validate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BuehlerDividendForwardStructure.validate()"})
  public void testValidate_thenCallsGetDate() {
    // Arrange
    AffineDividend affineDividend = mock(AffineDividend.class);
    when(affineDividend.getDate()).thenReturn(LocalDate.of(1970, 1, 1));
    AffineDividend[] dividendStream = new AffineDividend[] {affineDividend};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));

    // Act
    new BuehlerDividendForwardStructure(
            valuationDate, 10.0d, repoCurve, dividendStream2, new DayCountConvention_30E_360(true))
        .validate();

    // Assert
    verify(affineDividend, atLeast(1)).getDate();
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#validate()}.
   *
   * <ul>
   *   <li>Then calls {@link AffineDividendStream#getDividendDates()}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#validate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BuehlerDividendForwardStructure.validate()"})
  public void testValidate_thenCallsGetDividendDates() {
    // Arrange
    AffineDividendStream dividendStream = mock(AffineDividendStream.class);
    when(dividendStream.getDividendDates()).thenReturn(new ArrayList<>());
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));

    // Act
    new BuehlerDividendForwardStructure(
            valuationDate, 10.0d, repoCurve, dividendStream, new DayCountConvention_30E_360(true))
        .validate();

    // Assert
    verify(dividendStream, atLeast(1)).getDividendDates();
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#cloneWithNewSpot(double)}.
   *
   * <ul>
   *   <li>Then RepoCurve return {@link FlatYieldCurve}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#cloneWithNewSpot(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BuehlerDividendForwardStructure BuehlerDividendForwardStructure.cloneWithNewSpot(double)"
  })
  public void testCloneWithNewSpot_thenRepoCurveReturnFlatYieldCurve() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act
    BuehlerDividendForwardStructure actualCloneWithNewSpotResult =
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .cloneWithNewSpot(10.0d);

    // Assert
    YieldCurve repoCurve2 = actualCloneWithNewSpotResult.getRepoCurve();
    assertTrue(repoCurve2 instanceof FlatYieldCurve);
    LocalDate valuationDate2 = actualCloneWithNewSpotResult.getValuationDate();
    assertEquals("1970-01-01", valuationDate2.toString());
    assertEquals(10.0d, actualCloneWithNewSpotResult.getSpot(), 0.0);
    assertEquals(DividendModelType.Buehler, actualCloneWithNewSpotResult.getDividendModel());
    assertSame(dividendStream2, actualCloneWithNewSpotResult.getDividendStream());
    assertSame(repoCurve, repoCurve2);
    assertSame(valuationDate, valuationDate2);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#cloneWithNewDate(LocalDate)}.
   *
   * <ul>
   *   <li>Then RepoCurve return {@link FlatYieldCurve}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#cloneWithNewDate(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BuehlerDividendForwardStructure BuehlerDividendForwardStructure.cloneWithNewDate(LocalDate)"
  })
  public void testCloneWithNewDate_thenRepoCurveReturnFlatYieldCurve() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.ofYearDay(1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    LocalDate date = LocalDate.of(1970, 1, 1);
    AffineDividend[] dividendStream = new AffineDividend[] {new AffineDividend(date, 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);
    LocalDate newDate = LocalDate.of(1970, 1, 1);

    // Act
    BuehlerDividendForwardStructure actualCloneWithNewDateResult =
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .cloneWithNewDate(newDate);

    // Assert
    assertTrue(actualCloneWithNewDateResult.getRepoCurve() instanceof FlatYieldCurve);
    AffineDividendStream dividendStream3 = actualCloneWithNewDateResult.getDividendStream();
    ArrayList<LocalDate> dividendDates = dividendStream3.getDividendDates();
    assertEquals(1, dividendDates.size());
    assertEquals(10.0d, actualCloneWithNewDateResult.getSpot(), 0.0);
    assertEquals(DividendModelType.Buehler, actualCloneWithNewDateResult.getDividendModel());
    assertSame(dividendStream2, dividendStream3);
    assertSame(date, dividendDates.get(0));
    assertSame(newDate, actualCloneWithNewDateResult.getValuationDate());
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getGrowthDiscountFactor(LocalDate, LocalDate)} with
   * {@code startDate}, {@code endDate}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getGrowthDiscountFactor(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BuehlerDividendForwardStructure.getGrowthDiscountFactor(LocalDate, LocalDate)"
  })
  public void testGetGrowthDiscountFactorWithStartDateEndDate_thenReturnNaN() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getGrowthDiscountFactor(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getGrowthDiscountFactor(double, double)} with
   * {@code startTime}, {@code endTime}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getGrowthDiscountFactor(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BuehlerDividendForwardStructure.getGrowthDiscountFactor(double, double)"
  })
  public void testGetGrowthDiscountFactorWithStartTimeEndTime_thenReturnNaN() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getGrowthDiscountFactor(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getGrowthDiscountFactor(double, double)} with
   * {@code startTime}, {@code endTime}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getGrowthDiscountFactor(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BuehlerDividendForwardStructure.getGrowthDiscountFactor(double, double)"
  })
  public void testGetGrowthDiscountFactorWithStartTimeEndTime_thenReturnNaN2() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getGrowthDiscountFactor(0.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getGrowthDiscountFactor(double, double)} with
   * {@code startTime}, {@code endTime}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getGrowthDiscountFactor(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BuehlerDividendForwardStructure.getGrowthDiscountFactor(double, double)"
  })
  public void testGetGrowthDiscountFactorWithStartTimeEndTime_when05_thenReturnNaN() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getGrowthDiscountFactor(0.5d, 10.0d),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getGrowthDiscountFactor(double, double)} with
   * {@code startTime}, {@code endTime}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getGrowthDiscountFactor(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BuehlerDividendForwardStructure.getGrowthDiscountFactor(double, double)"
  })
  public void testGetGrowthDiscountFactorWithStartTimeEndTime_whenOne_thenReturnNaN() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getGrowthDiscountFactor(1.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getFutureDividendFactor(LocalDate)} with {@code
   * valDate}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * BuehlerDividendForwardStructure#getFutureDividendFactor(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BuehlerDividendForwardStructure.getFutureDividendFactor(LocalDate)"})
  public void testGetFutureDividendFactorWithValDate_thenReturnZero() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        0.0d,
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getFutureDividendFactor(LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getFutureDividendFactor(double)} with {@code
   * valTime}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getFutureDividendFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BuehlerDividendForwardStructure.getFutureDividendFactor(double)"})
  public void testGetFutureDividendFactorWithValTime_thenReturnZero() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        0.0d,
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getFutureDividendFactor(10.0d),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getForward(LocalDate)} with {@code expiryDate}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getForward(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BuehlerDividendForwardStructure.getForward(LocalDate)"})
  public void testGetForwardWithExpiryDate_thenReturnNaN() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getForward(LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getForward(double)} with {@code expiryTime}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getForward(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BuehlerDividendForwardStructure.getForward(double)"})
  public void testGetForwardWithExpiryTime_thenReturnNaN() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getForward(10.0d),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getDividendAdjustedStrike(double, LocalDate)} with
   * {@code strike}, {@code expiryDate}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getDividendAdjustedStrike(double,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BuehlerDividendForwardStructure.getDividendAdjustedStrike(double, LocalDate)"
  })
  public void testGetDividendAdjustedStrikeWithStrikeExpiryDate_thenReturnTen() {
    // Arrange
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
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getDividendAdjustedStrike(10.0d, LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getDividendAdjustedStrike(double, double)} with
   * {@code strike}, {@code expiryTime}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getDividendAdjustedStrike(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BuehlerDividendForwardStructure.getDividendAdjustedStrike(double, double)"
  })
  public void testGetDividendAdjustedStrikeWithStrikeExpiryTime_thenReturnTen() {
    // Arrange
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
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getDividendAdjustedStrike(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getDividendAdjustedStrike(double, double)} with
   * {@code strike}, {@code expiryTime}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getDividendAdjustedStrike(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BuehlerDividendForwardStructure.getDividendAdjustedStrike(double, double)"
  })
  public void testGetDividendAdjustedStrikeWithStrikeExpiryTime_when05_thenReturn05() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        0.5d,
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getDividendAdjustedStrike(0.5d, 10.0d),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getDividendAdjustedStrike(double, double)} with
   * {@code strike}, {@code expiryTime}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getDividendAdjustedStrike(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BuehlerDividendForwardStructure.getDividendAdjustedStrike(double, double)"
  })
  public void testGetDividendAdjustedStrikeWithStrikeExpiryTime_when05_thenReturn052() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        -0.5d,
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getDividendAdjustedStrike(-0.5d, 10.0d),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getDividendAdjustedStrike(double, double)} with
   * {@code strike}, {@code expiryTime}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getDividendAdjustedStrike(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BuehlerDividendForwardStructure.getDividendAdjustedStrike(double, double)"
  })
  public void testGetDividendAdjustedStrikeWithStrikeExpiryTime_whenZero_thenReturnZero() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        0.0d,
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getDividendAdjustedStrike(0.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getLogMoneyness(double, LocalDate)} with {@code
   * strike}, {@code expiryDate}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getLogMoneyness(double,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BuehlerDividendForwardStructure.getLogMoneyness(double, LocalDate)"})
  public void testGetLogMoneynessWithStrikeExpiryDate_thenReturnNaN() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getLogMoneyness(10.0d, LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link BuehlerDividendForwardStructure#getLogMoneyness(double, double)} with {@code
   * strike}, {@code expiryTime}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link BuehlerDividendForwardStructure#getLogMoneyness(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BuehlerDividendForwardStructure.getLogMoneyness(double, double)"})
  public void testGetLogMoneynessWithStrikeExpiryTime_thenReturnNaN() {
    // Arrange
    LocalDate valuationDate = LocalDate.of(1970, 1, 1);
    LocalDate curveDate = LocalDate.of(1970, 1, 1);
    FlatYieldCurve repoCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));
    AffineDividend[] dividendStream =
        new AffineDividend[] {new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d)};
    AffineDividendStream dividendStream2 = new AffineDividendStream(dividendStream);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new BuehlerDividendForwardStructure(
                valuationDate,
                10.0d,
                repoCurve,
                dividendStream2,
                new DayCountConvention_30E_360(true))
            .getLogMoneyness(10.0d, 10.0d),
        0.0);
  }
}
