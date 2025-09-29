package net.finmath.marketdata.model.volatilities;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.HashMap;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.marketdata.model.volatilities.VolatilitySurface.QuotingConvention;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OptionSurfaceDataDiffblueTest {
  /**
   * Test {@link OptionSurfaceData#OptionSurfaceData(String, LocalDate, double[], double[],
   * double[][], QuotingConvention, DiscountCurve, DiscountCurve)}.
   *
   * <p>Method under test: {@link OptionSurfaceData#OptionSurfaceData(String, LocalDate, double[],
   * double[], double[][], QuotingConvention, DiscountCurve, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptionSurfaceData.<init>(String, LocalDate, double[], double[], double[][], QuotingConvention, DiscountCurve, DiscountCurve)"
  })
  public void testNewOptionSurfaceData() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    double[][] values = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new OptionSurfaceData(
                "Underlying",
                referenceDate,
                new double[] {10.0d, 0.5d, 10.0d, 0.5d},
                new double[] {10.0d, 0.5d, 10.0d, 0.5d},
                values,
                QuotingConvention.VOLATILITYLOGNORMAL,
                discountCurve,
                new DiscountCurveFromForwardCurve("Forward Curve Name")));
  }

  /**
   * Test {@link OptionSurfaceData#OptionSurfaceData(OptionSmileData[], DiscountCurve,
   * DiscountCurve)}.
   *
   * <p>Method under test: {@link OptionSurfaceData#OptionSurfaceData(OptionSmileData[],
   * DiscountCurve, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptionSurfaceData.<init>(OptionSmileData[], DiscountCurve, DiscountCurve)"
  })
  public void testNewOptionSurfaceData2() {
    // Arrange
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "42",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYLOGNORMAL);
    OptionSmileData optionSmileData2 =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYLOGNORMAL);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new OptionSurfaceData(
                new OptionSmileData[] {optionSmileData, optionSmileData2},
                discountCurve,
                new DiscountCurveFromForwardCurve("Forward Curve Name")));
  }

  /**
   * Test {@link OptionSurfaceData#OptionSurfaceData(OptionSmileData[], DiscountCurve,
   * DiscountCurve)}.
   *
   * <p>Method under test: {@link OptionSurfaceData#OptionSurfaceData(OptionSmileData[],
   * DiscountCurve, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptionSurfaceData.<init>(OptionSmileData[], DiscountCurve, DiscountCurve)"
  })
  public void testNewOptionSurfaceData3() {
    // Arrange
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "42",
            LocalDate.now(),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYLOGNORMAL);
    OptionSmileData optionSmileData2 =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYLOGNORMAL);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new OptionSurfaceData(
                new OptionSmileData[] {optionSmileData, optionSmileData2},
                discountCurve,
                new DiscountCurveFromForwardCurve("Forward Curve Name")));
  }

  /**
   * Test {@link OptionSurfaceData#OptionSurfaceData(OptionSmileData[], DiscountCurve,
   * DiscountCurve)}.
   *
   * <ul>
   *   <li>Then return ReferenceDate toString is {@code 1970-01-01}.
   * </ul>
   *
   * <p>Method under test: {@link OptionSurfaceData#OptionSurfaceData(OptionSmileData[],
   * DiscountCurve, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptionSurfaceData.<init>(OptionSmileData[], DiscountCurve, DiscountCurve)"
  })
  public void testNewOptionSurfaceData_thenReturnReferenceDateToStringIs19700101() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            referenceDate,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYLOGNORMAL);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve equityForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    OptionSurfaceData actualOptionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Assert
    LocalDate referenceDate2 = actualOptionSurfaceData.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Underlying", actualOptionSurfaceData.getName());
    HashMap<Double, OptionSmileData> surface = actualOptionSurfaceData.getSurface();
    assertEquals(1, surface.size());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL, actualOptionSurfaceData.getQuotingConvention());
    assertSame(discountCurve, actualOptionSurfaceData.getDiscountCurve());
    assertSame(equityForwardCurve, actualOptionSurfaceData.getEquityForwardCurve());
    assertSame(optionSmileData, surface.get(10.0d));
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(new double[] {10.0d}, actualOptionSurfaceData.getMaturities(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link OptionSurfaceData#getDiscountCurve()}
   *   <li>{@link OptionSurfaceData#getEquityForwardCurve()}
   *   <li>{@link OptionSurfaceData#getMaturities()}
   *   <li>{@link OptionSurfaceData#getName()}
   *   <li>{@link OptionSurfaceData#getQuotingConvention()}
   *   <li>{@link OptionSurfaceData#getReferenceDate()}
   *   <li>{@link OptionSurfaceData#getSurface()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DiscountCurve OptionSurfaceData.getDiscountCurve()",
    "DiscountCurve OptionSurfaceData.getEquityForwardCurve()",
    "double[] OptionSurfaceData.getMaturities()",
    "String OptionSurfaceData.getName()",
    "QuotingConvention OptionSurfaceData.getQuotingConvention()",
    "LocalDate OptionSurfaceData.getReferenceDate()",
    "HashMap OptionSurfaceData.getSurface()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            referenceDate,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYLOGNORMAL);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve equityForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    DiscountCurve actualDiscountCurve = optionSurfaceData.getDiscountCurve();
    DiscountCurve actualEquityForwardCurve = optionSurfaceData.getEquityForwardCurve();
    double[] actualMaturities = optionSurfaceData.getMaturities();
    String actualName = optionSurfaceData.getName();
    QuotingConvention actualQuotingConvention = optionSurfaceData.getQuotingConvention();
    LocalDate actualReferenceDate = optionSurfaceData.getReferenceDate();
    HashMap<Double, OptionSmileData> actualSurface = optionSurfaceData.getSurface();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals("Underlying", actualName);
    assertEquals(1, actualSurface.size());
    assertEquals(QuotingConvention.VOLATILITYLOGNORMAL, actualQuotingConvention);
    assertSame(discountCurve, actualDiscountCurve);
    assertSame(equityForwardCurve, actualEquityForwardCurve);
    assertSame(optionSmileData, actualSurface.get(10.0d));
    assertSame(referenceDate, actualReferenceDate);
    assertArrayEquals(new double[] {10.0d}, actualMaturities, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double OptionSurfaceData.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention() {
    // Arrange
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYNORMAL);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(
            smiles, discountCurve, new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        0.0d, optionSurfaceData.getValue(10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL), 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double OptionSurfaceData.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention2() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getValue(anyDouble())).thenReturn(1.0E-15d);

    DiscountCurveInterpolation equityForwardCurve = mock(DiscountCurveInterpolation.class);
    when(equityForwardCurve.getValue(anyDouble())).thenReturn(10.0d);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    double actualValue =
        optionSurfaceData.getValue(10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve).getValue(10.0d);
    verify(equityForwardCurve).getValue(10.0d);
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double OptionSurfaceData.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention3() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getValue(anyDouble())).thenReturn(-0.5d);

    DiscountCurveInterpolation equityForwardCurve = mock(DiscountCurveInterpolation.class);
    when(equityForwardCurve.getValue(anyDouble())).thenReturn(10.0d);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    double actualValue =
        optionSurfaceData.getValue(10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve).getValue(10.0d);
    verify(equityForwardCurve).getValue(10.0d);
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@code 7.926654595212014E23}.
   * </ul>
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double OptionSurfaceData.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention_thenReturn7926654595212014e23() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getValue(anyDouble())).thenReturn(10.0d);

    DiscountCurveInterpolation equityForwardCurve = mock(DiscountCurveInterpolation.class);
    when(equityForwardCurve.getValue(anyDouble())).thenReturn(1.0E-15d);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    double actualValue =
        optionSurfaceData.getValue(10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve).getValue(10.0d);
    verify(equityForwardCurve).getValue(10.0d);
    assertEquals(7.926654595212014E23d, actualValue, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@code 0.07947521398129419}.
   * </ul>
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double OptionSurfaceData.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention_thenReturn007947521398129419() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getValue(anyDouble())).thenReturn(10.0d);

    DiscountCurveInterpolation equityForwardCurve = mock(DiscountCurveInterpolation.class);
    when(equityForwardCurve.getValue(anyDouble())).thenReturn(10.0d);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    double actualValue =
        optionSurfaceData.getValue(10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve).getValue(10.0d);
    verify(equityForwardCurve).getValue(10.0d);
    assertEquals(0.07947521398129419d, actualValue, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@code -0.048682734961622454}.
   * </ul>
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double OptionSurfaceData.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention_thenReturn0048682734961622454() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getValue(anyDouble())).thenReturn(10.0d);

    DiscountCurveInterpolation equityForwardCurve = mock(DiscountCurveInterpolation.class);
    when(equityForwardCurve.getValue(anyDouble())).thenReturn(10.0d);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    double actualValue =
        optionSurfaceData.getValue(10.0d, 0.5d, QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve).getValue(10.0d);
    verify(equityForwardCurve).getValue(10.0d);
    assertEquals(-0.048682734961622454d, actualValue, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@code 5.3829231976220155}.
   * </ul>
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double OptionSurfaceData.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention_thenReturn53829231976220155() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getValue(anyDouble())).thenReturn(10.0d);

    DiscountCurveInterpolation equityForwardCurve = mock(DiscountCurveInterpolation.class);
    when(equityForwardCurve.getValue(anyDouble())).thenReturn(1.0d);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    double actualValue =
        optionSurfaceData.getValue(10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve).getValue(10.0d);
    verify(equityForwardCurve).getValue(10.0d);
    assertEquals(5.3829231976220155d, actualValue, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double OptionSurfaceData.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention_thenReturnNaN() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getValue(anyDouble())).thenReturn(10.0d);

    DiscountCurveInterpolation equityForwardCurve = mock(DiscountCurveInterpolation.class);
    when(equityForwardCurve.getValue(anyDouble())).thenReturn(10.0d);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, Double.NaN, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    double actualValue =
        optionSurfaceData.getValue(10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve).getValue(10.0d);
    verify(equityForwardCurve).getValue(10.0d);
    assertEquals(Double.NaN, actualValue, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double OptionSurfaceData.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention_thenReturnTen() {
    // Arrange
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYLOGNORMAL);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(
            smiles, discountCurve, new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        10.0d,
        optionSurfaceData.getValue(10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>When {@code VOLATILITYNORMAL}.
   * </ul>
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double OptionSurfaceData.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention_whenVolatilitynormal() {
    // Arrange
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};
    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(
            smiles, mock(DiscountCurveInterpolation.class), mock(DiscountCurveInterpolation.class));

    // Act and Assert
    assertEquals(
        0.0d, optionSurfaceData.getValue(10.0d, 10.0d, QuotingConvention.VOLATILITYNORMAL), 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(double, double)} with {@code maturity}, {@code strike}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double OptionSurfaceData.getValue(double, double)"})
  public void testGetValueWithMaturityStrike_thenReturnTen() {
    // Arrange
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYLOGNORMAL);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(
            smiles, discountCurve, new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(10.0d, optionSurfaceData.getValue(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(double, double)} with {@code maturity}, {@code strike}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double OptionSurfaceData.getValue(double, double)"})
  public void testGetValueWithMaturityStrike_when05_thenReturn05() {
    // Arrange
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYLOGNORMAL);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(
            smiles, discountCurve, new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(0.5d, optionSurfaceData.getValue(10.0d, 0.5d), 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(AnalyticModel, double, double, QuotingConvention)} with
   * {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double OptionSurfaceData.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention() {
    // Arrange
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYNORMAL);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(
            smiles, discountCurve, new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        0.0d,
        optionSurfaceData.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(AnalyticModel, double, double, QuotingConvention)} with
   * {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double OptionSurfaceData.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention2() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getValue(anyDouble())).thenReturn(10.0d);

    DiscountCurveInterpolation equityForwardCurve = mock(DiscountCurveInterpolation.class);
    when(equityForwardCurve.getValue(anyDouble())).thenReturn(10.0d);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    double actualValue =
        optionSurfaceData.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve).getValue(10.0d);
    verify(equityForwardCurve).getValue(10.0d);
    assertEquals(0.07947521398129419d, actualValue, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(AnalyticModel, double, double, QuotingConvention)} with
   * {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double OptionSurfaceData.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention3() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getValue(anyDouble())).thenReturn(1.0E-15d);

    DiscountCurveInterpolation equityForwardCurve = mock(DiscountCurveInterpolation.class);
    when(equityForwardCurve.getValue(anyDouble())).thenReturn(10.0d);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    double actualValue =
        optionSurfaceData.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve).getValue(10.0d);
    verify(equityForwardCurve).getValue(10.0d);
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(AnalyticModel, double, double, QuotingConvention)} with
   * {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double OptionSurfaceData.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention4() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getValue(anyDouble())).thenReturn(-0.5d);

    DiscountCurveInterpolation equityForwardCurve = mock(DiscountCurveInterpolation.class);
    when(equityForwardCurve.getValue(anyDouble())).thenReturn(10.0d);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    double actualValue =
        optionSurfaceData.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve).getValue(10.0d);
    verify(equityForwardCurve).getValue(10.0d);
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(AnalyticModel, double, double, QuotingConvention)} with
   * {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double OptionSurfaceData.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention5() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getValue(anyDouble())).thenReturn(10.0d);

    DiscountCurveInterpolation equityForwardCurve = mock(DiscountCurveInterpolation.class);
    when(equityForwardCurve.getValue(anyDouble())).thenReturn(1.0E-15d);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    double actualValue =
        optionSurfaceData.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve).getValue(10.0d);
    verify(equityForwardCurve).getValue(10.0d);
    assertEquals(7.926654595212014E23d, actualValue, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(AnalyticModel, double, double, QuotingConvention)} with
   * {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double OptionSurfaceData.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention6() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getValue(anyDouble())).thenReturn(10.0d);

    DiscountCurveInterpolation equityForwardCurve = mock(DiscountCurveInterpolation.class);
    when(equityForwardCurve.getValue(anyDouble())).thenReturn(10.0d);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    double actualValue =
        optionSurfaceData.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            0.5d,
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve).getValue(10.0d);
    verify(equityForwardCurve).getValue(10.0d);
    assertEquals(-0.048682734961622454d, actualValue, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(AnalyticModel, double, double, QuotingConvention)} with
   * {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@code 5.3829231976220155}.
   * </ul>
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double OptionSurfaceData.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_thenReturn53829231976220155() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getValue(anyDouble())).thenReturn(10.0d);

    DiscountCurveInterpolation equityForwardCurve = mock(DiscountCurveInterpolation.class);
    when(equityForwardCurve.getValue(anyDouble())).thenReturn(1.0d);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    double actualValue =
        optionSurfaceData.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve).getValue(10.0d);
    verify(equityForwardCurve).getValue(10.0d);
    assertEquals(5.3829231976220155d, actualValue, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(AnalyticModel, double, double, QuotingConvention)} with
   * {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double OptionSurfaceData.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_thenReturnNaN() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getValue(anyDouble())).thenReturn(10.0d);

    DiscountCurveInterpolation equityForwardCurve = mock(DiscountCurveInterpolation.class);
    when(equityForwardCurve.getValue(anyDouble())).thenReturn(10.0d);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, Double.NaN, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(smiles, discountCurve, equityForwardCurve);

    // Act
    double actualValue =
        optionSurfaceData.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve).getValue(10.0d);
    verify(equityForwardCurve).getValue(10.0d);
    assertEquals(Double.NaN, actualValue, 0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(AnalyticModel, double, double, QuotingConvention)} with
   * {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double OptionSurfaceData.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_thenReturnTen() {
    // Arrange
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYLOGNORMAL);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(
            smiles, discountCurve, new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        10.0d,
        optionSurfaceData.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getValue(AnalyticModel, double, double, QuotingConvention)} with
   * {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>When {@code VOLATILITYNORMAL}.
   * </ul>
   *
   * <p>Method under test: {@link OptionSurfaceData#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double OptionSurfaceData.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_whenVolatilitynormal() {
    // Arrange
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.PRICE);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};
    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(
            smiles, mock(DiscountCurveInterpolation.class), mock(DiscountCurveInterpolation.class));

    // Act and Assert
    assertEquals(
        0.0d,
        optionSurfaceData.getValue(
            new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d, QuotingConvention.VOLATILITYNORMAL),
        0.0);
  }

  /**
   * Test {@link OptionSurfaceData#getSmile(double)}.
   *
   * <p>Method under test: {@link OptionSurfaceData#getSmile(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OptionSmileData OptionSurfaceData.getSmile(double)"})
  public void testGetSmile() {
    // Arrange
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYLOGNORMAL);
    OptionSmileData[] smiles = new OptionSmileData[] {optionSmileData};
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    OptionSurfaceData optionSurfaceData =
        new OptionSurfaceData(
            smiles, discountCurve, new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertSame(optionSmileData, optionSurfaceData.getSmile(10.0d));
  }
}
