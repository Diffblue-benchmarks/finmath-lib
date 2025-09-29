package net.finmath.marketdata.model.volatilities;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.volatilities.VolatilitySurface.QuotingConvention;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CapletVolatilitiesParametricDiffblueTest {
  /**
   * Test {@link CapletVolatilitiesParametric#CapletVolatilitiesParametric(String, LocalDate,
   * double, double, double, double)}.
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#CapletVolatilitiesParametric(String,
   * LocalDate, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolatilitiesParametric.<init>(String, LocalDate, double, double, double, double)"
  })
  public void testNewCapletVolatilitiesParametric() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    CapletVolatilitiesParametric actualCapletVolatilitiesParametric =
        new CapletVolatilitiesParametric("Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals("Name", actualCapletVolatilitiesParametric.getName());
    assertNull(actualCapletVolatilitiesParametric.getDiscountCurve());
    assertNull(actualCapletVolatilitiesParametric.getForwardCurve());
    assertNull(actualCapletVolatilitiesParametric.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL,
        actualCapletVolatilitiesParametric.getQuotingConvention());
    assertSame(referenceDate, actualCapletVolatilitiesParametric.getReferenceDate());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCapletVolatilitiesParametric.getParameter(),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametric#CapletVolatilitiesParametric(String, LocalDate,
   * double, double, double, double, double)}.
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#CapletVolatilitiesParametric(String,
   * LocalDate, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolatilitiesParametric.<init>(String, LocalDate, double, double, double, double, double)"
  })
  public void testNewCapletVolatilitiesParametric2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    CapletVolatilitiesParametric actualCapletVolatilitiesParametric =
        new CapletVolatilitiesParametric("Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals("Name", actualCapletVolatilitiesParametric.getName());
    assertNull(actualCapletVolatilitiesParametric.getDiscountCurve());
    assertNull(actualCapletVolatilitiesParametric.getForwardCurve());
    assertNull(actualCapletVolatilitiesParametric.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL,
        actualCapletVolatilitiesParametric.getQuotingConvention());
    assertSame(referenceDate, actualCapletVolatilitiesParametric.getReferenceDate());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCapletVolatilitiesParametric.getParameter(),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametric#CapletVolatilitiesParametric(String, LocalDate,
   * ForwardCurve, DiscountCurve, double, double, double, double, double)}.
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#CapletVolatilitiesParametric(String,
   * LocalDate, ForwardCurve, DiscountCurve, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolatilitiesParametric.<init>(String, LocalDate, ForwardCurve, DiscountCurve, double, double, double, double, double)"
  })
  public void testNewCapletVolatilitiesParametric3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    CapletVolatilitiesParametric actualCapletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", referenceDate, forwardCurve, discountCurve, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    DiscountCurve discountCurve2 = actualCapletVolatilitiesParametric.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardCurve2 = actualCapletVolatilitiesParametric.getForwardCurve();
    assertTrue(forwardCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertEquals("Name", actualCapletVolatilitiesParametric.getName());
    assertNull(actualCapletVolatilitiesParametric.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL,
        actualCapletVolatilitiesParametric.getQuotingConvention());
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardCurve, forwardCurve2);
    assertSame(referenceDate, actualCapletVolatilitiesParametric.getReferenceDate());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCapletVolatilitiesParametric.getParameter(),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametric#CapletVolatilitiesParametric(String, LocalDate,
   * ForwardCurve, DiscountCurve, double, double, double, double, double, QuotingConvention)}.
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#CapletVolatilitiesParametric(String,
   * LocalDate, ForwardCurve, DiscountCurve, double, double, double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolatilitiesParametric.<init>(String, LocalDate, ForwardCurve, DiscountCurve, double, double, double, double, double, QuotingConvention)"
  })
  public void testNewCapletVolatilitiesParametric4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    CapletVolatilitiesParametric actualCapletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name",
            referenceDate,
            forwardCurve,
            discountCurve,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    DiscountCurve discountCurve2 = actualCapletVolatilitiesParametric.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardCurve2 = actualCapletVolatilitiesParametric.getForwardCurve();
    assertTrue(forwardCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertEquals("Name", actualCapletVolatilitiesParametric.getName());
    assertNull(actualCapletVolatilitiesParametric.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL,
        actualCapletVolatilitiesParametric.getQuotingConvention());
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardCurve, forwardCurve2);
    assertSame(referenceDate, actualCapletVolatilitiesParametric.getReferenceDate());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCapletVolatilitiesParametric.getParameter(),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametric#getValue(double, double, QuotingConvention)} with
   * {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametric.getValue(double, double, QuotingConvention)"
  })
  public void testGetValueWithMaturityStrikeQuotingConvention() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolatilitiesParametric.getValue(
                10.0d, 10.0d, QuotingConvention.VOLATILITYNORMAL));
  }

  /**
   * Test {@link CapletVolatilitiesParametric#getValue(double, double, QuotingConvention)} with
   * {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@code 10.136690781512476}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametric.getValue(double, double, QuotingConvention)"
  })
  public void testGetValueWithMaturityStrikeQuotingConvention_thenReturn10136690781512476() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        10.136690781512476d,
        capletVolatilitiesParametric.getValue(10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametric#getValue(double, double, QuotingConvention)} with
   * {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@code 20.000000000500002}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametric.getValue(double, double, QuotingConvention)"
  })
  public void testGetValueWithMaturityStrikeQuotingConvention_thenReturn20000000000500002() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        20.000000000500002d,
        capletVolatilitiesParametric.getValue(
            1.0E-10d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametric#getValue(double, double, QuotingConvention)} with
   * {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametric.getValue(double, double, QuotingConvention)"
  })
  public void testGetValueWithMaturityStrikeQuotingConvention_whenZero_thenReturnZero() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        capletVolatilitiesParametric.getValue(0.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametric#getValue(AnalyticModel, double, double,
   * QuotingConvention)} with {@code model}, {@code maturity}, {@code strike}, {@code
   * quotingConvention}.
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#getValue(AnalyticModel, double,
   * double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametric.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolatilitiesParametric.getValue(
                new AnalyticModelFromCurvesAndVols(),
                10.0d,
                10.0d,
                QuotingConvention.VOLATILITYNORMAL));
  }

  /**
   * Test {@link CapletVolatilitiesParametric#getValue(AnalyticModel, double, double,
   * QuotingConvention)} with {@code model}, {@code maturity}, {@code strike}, {@code
   * quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@code 10.136690781512476}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#getValue(AnalyticModel, double,
   * double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametric.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_thenReturn10136690781512476() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        10.136690781512476d,
        capletVolatilitiesParametric.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametric#getValue(AnalyticModel, double, double,
   * QuotingConvention)} with {@code model}, {@code maturity}, {@code strike}, {@code
   * quotingConvention}.
   *
   * <ul>
   *   <li>Then return {@code 20.000000000500002}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#getValue(AnalyticModel, double,
   * double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametric.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_thenReturn20000000000500002() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        20.000000000500002d,
        capletVolatilitiesParametric.getValue(
            new AnalyticModelFromCurvesAndVols(),
            1.0E-10d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametric#getValue(AnalyticModel, double, double,
   * QuotingConvention)} with {@code model}, {@code maturity}, {@code strike}, {@code
   * quotingConvention}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#getValue(AnalyticModel, double,
   * double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametric.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_whenZero_thenReturnZero() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        capletVolatilitiesParametric.getValue(
            new AnalyticModelFromCurvesAndVols(),
            0.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametric#getParameter()}.
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] CapletVolatilitiesParametric.getParameter()"})
  public void testGetParameter() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        capletVolatilitiesParametric.getParameter(),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametric#setParameter(double[])}.
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#setParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CapletVolatilitiesParametric.setParameter(double[])"})
  public void testSetParameter() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            capletVolatilitiesParametric.setParameter(
                new double[] {10.0d, 1.0E-5d, 10.0d, 1.0E-5d}));
  }

  /**
   * Test {@link CapletVolatilitiesParametric#getCloneForParameter(double[])}.
   *
   * <ul>
   *   <li>Then return {@link CapletVolatilitiesParametric}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilitiesParametric#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractVolatilitySurfaceParametric CapletVolatilitiesParametric.getCloneForParameter(double[])"
  })
  public void testGetCloneForParameter_thenReturnCapletVolatilitiesParametric()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric("Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    AbstractVolatilitySurfaceParametric actualCloneForParameter =
        capletVolatilitiesParametric.getCloneForParameter(
            new double[] {10.0d, 1.0E-5d, 10.0d, 1.0E-5d});

    // Assert
    assertTrue(actualCloneForParameter instanceof CapletVolatilitiesParametric);
    LocalDate referenceDate2 = actualCloneForParameter.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneForParameter.getName());
    assertNull(actualCloneForParameter.getDiscountCurve());
    assertNull(actualCloneForParameter.getForwardCurve());
    assertNull(actualCloneForParameter.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL, actualCloneForParameter.getQuotingConvention());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {10.0d, 1.0E-5d, 10.0d, 1.0E-5d}, actualCloneForParameter.getParameter(), 0.0);
  }
}
