package net.finmath.marketdata.model.volatilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.marketdata.model.volatilities.VolatilitySurface.QuotingConvention;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CapletVolatilitiesDiffblueTest {
  /**
   * Test {@link CapletVolatilities#CapletVolatilities(String, LocalDate, ForwardCurve, double[],
   * double[], double[], QuotingConvention, DiscountCurve)}.
   *
   * <ul>
   *   <li>Then DiscountCurve return {@link DiscountCurveFromForwardCurve}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilities#CapletVolatilities(String, LocalDate,
   * ForwardCurve, double[], double[], double[], QuotingConvention, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolatilities.<init>(String, LocalDate, ForwardCurve, double[], double[], double[], QuotingConvention, DiscountCurve)"
  })
  public void testNewCapletVolatilities_thenDiscountCurveReturnDiscountCurveFromForwardCurve() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    CapletVolatilities actualCapletVolatilities =
        new CapletVolatilities(
            "Name",
            referenceDate,
            forwardCurve,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            QuotingConvention.VOLATILITYLOGNORMAL,
            discountCurve);

    // Assert
    DiscountCurve discountCurve2 = actualCapletVolatilities.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardCurve2 = actualCapletVolatilities.getForwardCurve();
    assertTrue(forwardCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertEquals("Name", actualCapletVolatilities.getName());
    assertNull(actualCapletVolatilities.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL, actualCapletVolatilities.getQuotingConvention());
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardCurve, forwardCurve2);
    assertSame(referenceDate, actualCapletVolatilities.getReferenceDate());
  }

  /**
   * Test {@link CapletVolatilities#CapletVolatilities(String, LocalDate, ForwardCurve, double[],
   * double[], double[], QuotingConvention, DiscountCurve)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilities#CapletVolatilities(String, LocalDate,
   * ForwardCurve, double[], double[], double[], QuotingConvention, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolatilities.<init>(String, LocalDate, ForwardCurve, double[], double[], double[], QuotingConvention, DiscountCurve)"
  })
  public void testNewCapletVolatilities_thenThrowIllegalArgumentException() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CapletVolatilities(
                "Name",
                referenceDate,
                forwardCurve,
                new double[] {},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                QuotingConvention.VOLATILITYLOGNORMAL,
                new DiscountCurveFromForwardCurve("Forward Curve Name")));
  }

  /**
   * Test {@link CapletVolatilities#CapletVolatilities(String, LocalDate, ForwardCurve, double[],
   * double[], double[], QuotingConvention, DiscountCurve)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilities#CapletVolatilities(String, LocalDate,
   * ForwardCurve, double[], double[], double[], QuotingConvention, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolatilities.<init>(String, LocalDate, ForwardCurve, double[], double[], double[], QuotingConvention, DiscountCurve)"
  })
  public void testNewCapletVolatilities_thenThrowIllegalArgumentException2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CapletVolatilities(
                "Name",
                referenceDate,
                forwardCurve,
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {},
                QuotingConvention.VOLATILITYLOGNORMAL,
                new DiscountCurveFromForwardCurve("Forward Curve Name")));
  }

  /**
   * Test {@link CapletVolatilities#CapletVolatilities(String, LocalDate, ForwardCurve, double[],
   * double[], double[], QuotingConvention, DiscountCurve)}.
   *
   * <ul>
   *   <li>When array of {@code double} with {@link Double#NaN} and one.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilities#CapletVolatilities(String, LocalDate,
   * ForwardCurve, double[], double[], double[], QuotingConvention, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolatilities.<init>(String, LocalDate, ForwardCurve, double[], double[], double[], QuotingConvention, DiscountCurve)"
  })
  public void testNewCapletVolatilities_whenArrayOfDoubleWithNaNAndOne() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    CapletVolatilities actualCapletVolatilities =
        new CapletVolatilities(
            "Name",
            referenceDate,
            forwardCurve,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {Double.NaN, 1.0d, 10.0d, 1.0d},
            QuotingConvention.VOLATILITYLOGNORMAL,
            discountCurve);

    // Assert
    DiscountCurve discountCurve2 = actualCapletVolatilities.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardCurve2 = actualCapletVolatilities.getForwardCurve();
    assertTrue(forwardCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertEquals("Name", actualCapletVolatilities.getName());
    assertNull(actualCapletVolatilities.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL, actualCapletVolatilities.getQuotingConvention());
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardCurve, forwardCurve2);
    assertSame(referenceDate, actualCapletVolatilities.getReferenceDate());
  }

  /**
   * Test {@link CapletVolatilities#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@code 10.000000000000002}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilities#getValue(double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CapletVolatilities.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention_thenReturn10000000000000002() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilities capletVolatilities =
        new CapletVolatilities(
            "Name",
            referenceDate,
            forwardCurve,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            QuotingConvention.VOLATILITYLOGNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        10.000000000000002d,
        capletVolatilities.getValue(10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilities#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilities#getValue(double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CapletVolatilities.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention_thenReturnNaN() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    CapletVolatilities capletVolatilities =
        new CapletVolatilities(
            "Name",
            referenceDate,
            forwardCurve,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            QuotingConvention.VOLATILITYNORMAL,
            discountCurve);

    // Act
    double actualValue =
        capletVolatilities.getValue(10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(20.0d);
    assertEquals(Double.NaN, actualValue, 0.0);
  }

  /**
   * Test {@link CapletVolatilities#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilities#getValue(double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CapletVolatilities.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention_whenMinusOne_thenReturnNaN() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    CapletVolatilities capletVolatilities =
        new CapletVolatilities(
            "Name",
            referenceDate,
            forwardCurve,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            QuotingConvention.VOLATILITYNORMAL,
            discountCurve);

    // Act
    double actualValue =
        capletVolatilities.getValue(-1.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(9.0d);
    assertEquals(Double.NaN, actualValue, 0.0);
  }

  /**
   * Test {@link CapletVolatilities#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilities#getValue(double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CapletVolatilities.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention_whenZero_thenReturnZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilities capletVolatilities =
        new CapletVolatilities(
            "Name",
            referenceDate,
            forwardCurve,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            QuotingConvention.VOLATILITYLOGNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        0.0d, capletVolatilities.getValue(0.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL), 0.0);
  }

  /**
   * Test {@link CapletVolatilities#getValue(AnalyticModel, double, double, QuotingConvention)} with
   * {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@code 10.000000000000002}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilities#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilities.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_thenReturn10000000000000002() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilities capletVolatilities =
        new CapletVolatilities(
            "Name",
            referenceDate,
            forwardCurve,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            QuotingConvention.VOLATILITYLOGNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        10.000000000000002d,
        capletVolatilities.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilities#getValue(AnalyticModel, double, double, QuotingConvention)} with
   * {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilities#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilities.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_thenReturnNaN() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    CapletVolatilities capletVolatilities =
        new CapletVolatilities(
            "Name",
            referenceDate,
            forwardCurve,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            QuotingConvention.VOLATILITYNORMAL,
            discountCurve);

    // Act
    double actualValue =
        capletVolatilities.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(20.0d);
    assertEquals(Double.NaN, actualValue, 0.0);
  }

  /**
   * Test {@link CapletVolatilities#getValue(AnalyticModel, double, double, QuotingConvention)} with
   * {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilities#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilities.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_whenMinusOne_thenReturnNaN() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    CapletVolatilities capletVolatilities =
        new CapletVolatilities(
            "Name",
            referenceDate,
            forwardCurve,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            QuotingConvention.VOLATILITYNORMAL,
            discountCurve);

    // Act
    double actualValue =
        capletVolatilities.getValue(
            new AnalyticModelFromCurvesAndVols(),
            -1.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(9.0d);
    assertEquals(Double.NaN, actualValue, 0.0);
  }

  /**
   * Test {@link CapletVolatilities#getValue(AnalyticModel, double, double, QuotingConvention)} with
   * {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilities#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilities.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_whenZero_thenReturnZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilities capletVolatilities =
        new CapletVolatilities(
            "Name",
            referenceDate,
            forwardCurve,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            QuotingConvention.VOLATILITYLOGNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        0.0d,
        capletVolatilities.getValue(
            new AnalyticModelFromCurvesAndVols(),
            0.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilities#fromFile(File)}.
   *
   * <p>Method under test: {@link CapletVolatilities#fromFile(File)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AbstractVolatilitySurface CapletVolatilities.fromFile(File)"})
  public void testFromFile() throws FileNotFoundException {
    // Arrange and Act
    AbstractVolatilitySurface actualFromFileResult =
        CapletVolatilities.fromFile(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertTrue(actualFromFileResult instanceof CapletVolatilities);
    assertNull(actualFromFileResult.getName());
    assertNull(actualFromFileResult.getReferenceDate());
    assertNull(actualFromFileResult.getDiscountCurve());
    assertNull(actualFromFileResult.getForwardCurve());
    assertNull(actualFromFileResult.getQuotingConvention());
    assertNull(actualFromFileResult.getDaycountConvention());
  }
}
