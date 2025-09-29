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

public class CapletVolatilitiesParametricDisplacedFourParameterAnalyticDiffblueTest {
  /**
   * Test {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#CapletVolatilitiesParametricDisplacedFourParameterAnalytic(String,
   * LocalDate, ForwardCurve, DiscountCurve, double, boolean, double, double, double, double,
   * double)}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#CapletVolatilitiesParametricDisplacedFourParameterAnalytic(String,
   * LocalDate, ForwardCurve, DiscountCurve, double, boolean, double, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolatilitiesParametricDisplacedFourParameterAnalytic.<init>(String, LocalDate, ForwardCurve, DiscountCurve, double, boolean, double, double, double, double, double)"
  })
  public void testNewCapletVolatilitiesParametricDisplacedFourParameterAnalytic() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    CapletVolatilitiesParametricDisplacedFourParameterAnalytic
        actualCapletVolatilitiesParametricDisplacedFourParameterAnalytic =
            new CapletVolatilitiesParametricDisplacedFourParameterAnalytic(
                "Name",
                referenceDate,
                forwardCurve,
                discountCurve,
                10.0d,
                true,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Assert
    DiscountCurve discountCurve2 =
        actualCapletVolatilitiesParametricDisplacedFourParameterAnalytic.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardCurve2 =
        actualCapletVolatilitiesParametricDisplacedFourParameterAnalytic.getForwardCurve();
    assertTrue(forwardCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertEquals(
        "Name", actualCapletVolatilitiesParametricDisplacedFourParameterAnalytic.getName());
    assertNull(
        actualCapletVolatilitiesParametricDisplacedFourParameterAnalytic.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL,
        actualCapletVolatilitiesParametricDisplacedFourParameterAnalytic.getQuotingConvention());
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardCurve, forwardCurve2);
    assertSame(
        referenceDate,
        actualCapletVolatilitiesParametricDisplacedFourParameterAnalytic.getReferenceDate());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCapletVolatilitiesParametricDisplacedFourParameterAnalytic.getParameter(),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getValue(double, double,
   * QuotingConvention)} with {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametricDisplacedFourParameterAnalytic.getValue(double, double, QuotingConvention)"
  })
  public void testGetValueWithMaturityStrikeQuotingConvention() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametricDisplacedFourParameterAnalytic
        capletVolatilitiesParametricDisplacedFourParameterAnalytic =
            new CapletVolatilitiesParametricDisplacedFourParameterAnalytic(
                "Name",
                referenceDate,
                null,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                true,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolatilitiesParametricDisplacedFourParameterAnalytic.getValue(
                10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getValue(double, double,
   * QuotingConvention)} with {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametricDisplacedFourParameterAnalytic.getValue(double, double, QuotingConvention)"
  })
  public void testGetValueWithMaturityStrikeQuotingConvention2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitiesParametricDisplacedFourParameterAnalytic
        capletVolatilitiesParametricDisplacedFourParameterAnalytic =
            new CapletVolatilitiesParametricDisplacedFourParameterAnalytic(
                "Name",
                referenceDate,
                forwardCurve,
                null,
                10.0d,
                true,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolatilitiesParametricDisplacedFourParameterAnalytic.getValue(
                10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getValue(double, double,
   * QuotingConvention)} with {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametricDisplacedFourParameterAnalytic.getValue(double, double, QuotingConvention)"
  })
  public void testGetValueWithMaturityStrikeQuotingConvention_whenZero_thenReturnZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitiesParametricDisplacedFourParameterAnalytic
        capletVolatilitiesParametricDisplacedFourParameterAnalytic =
            new CapletVolatilitiesParametricDisplacedFourParameterAnalytic(
                "Name",
                referenceDate,
                forwardCurve,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                true,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        capletVolatilitiesParametricDisplacedFourParameterAnalytic.getValue(
            0.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getValue(AnalyticModel,
   * double, double, QuotingConvention)} with {@code model}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getValue(AnalyticModel, double,
   * double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametricDisplacedFourParameterAnalytic.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametricDisplacedFourParameterAnalytic
        capletVolatilitiesParametricDisplacedFourParameterAnalytic =
            new CapletVolatilitiesParametricDisplacedFourParameterAnalytic(
                "Name",
                referenceDate,
                null,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                true,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolatilitiesParametricDisplacedFourParameterAnalytic.getValue(
                new AnalyticModelFromCurvesAndVols(),
                10.0d,
                10.0d,
                QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getValue(AnalyticModel,
   * double, double, QuotingConvention)} with {@code model}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getValue(AnalyticModel, double,
   * double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametricDisplacedFourParameterAnalytic.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitiesParametricDisplacedFourParameterAnalytic
        capletVolatilitiesParametricDisplacedFourParameterAnalytic =
            new CapletVolatilitiesParametricDisplacedFourParameterAnalytic(
                "Name",
                referenceDate,
                forwardCurve,
                null,
                10.0d,
                true,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolatilitiesParametricDisplacedFourParameterAnalytic.getValue(
                new AnalyticModelFromCurvesAndVols(),
                10.0d,
                10.0d,
                QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getValue(AnalyticModel,
   * double, double, QuotingConvention)} with {@code model}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getValue(AnalyticModel, double,
   * double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametricDisplacedFourParameterAnalytic.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_whenZero_thenReturnZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitiesParametricDisplacedFourParameterAnalytic
        capletVolatilitiesParametricDisplacedFourParameterAnalytic =
            new CapletVolatilitiesParametricDisplacedFourParameterAnalytic(
                "Name",
                referenceDate,
                forwardCurve,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                true,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        capletVolatilitiesParametricDisplacedFourParameterAnalytic.getValue(
            new AnalyticModelFromCurvesAndVols(),
            0.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getParameter()}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] CapletVolatilitiesParametricDisplacedFourParameterAnalytic.getParameter()"
  })
  public void testGetParameter() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitiesParametricDisplacedFourParameterAnalytic
        capletVolatilitiesParametricDisplacedFourParameterAnalytic =
            new CapletVolatilitiesParametricDisplacedFourParameterAnalytic(
                "Name",
                referenceDate,
                forwardCurve,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                true,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        capletVolatilitiesParametricDisplacedFourParameterAnalytic.getParameter(),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getParameter()}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] CapletVolatilitiesParametricDisplacedFourParameterAnalytic.getParameter()"
  })
  public void testGetParameter2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitiesParametricDisplacedFourParameterAnalytic
        capletVolatilitiesParametricDisplacedFourParameterAnalytic =
            new CapletVolatilitiesParametricDisplacedFourParameterAnalytic(
                "Name",
                referenceDate,
                forwardCurve,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                false,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        capletVolatilitiesParametricDisplacedFourParameterAnalytic.getParameter(),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametricDisplacedFourParameterAnalytic#setParameter(double[])}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#setParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolatilitiesParametricDisplacedFourParameterAnalytic.setParameter(double[])"
  })
  public void testSetParameter() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitiesParametricDisplacedFourParameterAnalytic
        capletVolatilitiesParametricDisplacedFourParameterAnalytic =
            new CapletVolatilitiesParametricDisplacedFourParameterAnalytic(
                "Name",
                referenceDate,
                forwardCurve,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                true,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            capletVolatilitiesParametricDisplacedFourParameterAnalytic.setParameter(
                new double[] {10.0d, 1.0d, 10.0d, 1.0d}));
  }

  /**
   * Test {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getCloneForParameter(double[])}.
   *
   * <ul>
   *   <li>Then DiscountCurve return {@link DiscountCurveFromForwardCurve}.
   * </ul>
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractVolatilitySurfaceParametric CapletVolatilitiesParametricDisplacedFourParameterAnalytic.getCloneForParameter(double[])"
  })
  public void testGetCloneForParameter_thenDiscountCurveReturnDiscountCurveFromForwardCurve()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    CapletVolatilitiesParametricDisplacedFourParameterAnalytic
        capletVolatilitiesParametricDisplacedFourParameterAnalytic =
            new CapletVolatilitiesParametricDisplacedFourParameterAnalytic(
                "Name",
                referenceDate,
                forwardCurve,
                discountCurve,
                10.0d,
                false,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Act
    AbstractVolatilitySurfaceParametric actualCloneForParameter =
        capletVolatilitiesParametricDisplacedFourParameterAnalytic.getCloneForParameter(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    DiscountCurve discountCurve2 = actualCloneForParameter.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardCurve2 = actualCloneForParameter.getForwardCurve();
    assertTrue(forwardCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(
        actualCloneForParameter
            instanceof CapletVolatilitiesParametricDisplacedFourParameterAnalytic);
    assertEquals("Name", actualCloneForParameter.getName());
    assertNull(actualCloneForParameter.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL, actualCloneForParameter.getQuotingConvention());
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardCurve, forwardCurve2);
    assertSame(referenceDate, actualCloneForParameter.getReferenceDate());
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d, 1.0d}, actualCloneForParameter.getParameter(), 0.0);
  }

  /**
   * Test {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getCloneForParameter(double[])}.
   *
   * <ul>
   *   <li>Then DiscountCurve return {@link DiscountCurveFromForwardCurve}.
   * </ul>
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricDisplacedFourParameterAnalytic#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractVolatilitySurfaceParametric CapletVolatilitiesParametricDisplacedFourParameterAnalytic.getCloneForParameter(double[])"
  })
  public void testGetCloneForParameter_thenDiscountCurveReturnDiscountCurveFromForwardCurve2()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    CapletVolatilitiesParametricDisplacedFourParameterAnalytic
        capletVolatilitiesParametricDisplacedFourParameterAnalytic =
            new CapletVolatilitiesParametricDisplacedFourParameterAnalytic(
                "Name",
                referenceDate,
                forwardCurve,
                discountCurve,
                10.0d,
                true,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Act
    AbstractVolatilitySurfaceParametric actualCloneForParameter =
        capletVolatilitiesParametricDisplacedFourParameterAnalytic.getCloneForParameter(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    DiscountCurve discountCurve2 = actualCloneForParameter.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardCurve2 = actualCloneForParameter.getForwardCurve();
    assertTrue(forwardCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(
        actualCloneForParameter
            instanceof CapletVolatilitiesParametricDisplacedFourParameterAnalytic);
    assertEquals("Name", actualCloneForParameter.getName());
    assertNull(actualCloneForParameter.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL, actualCloneForParameter.getQuotingConvention());
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardCurve, forwardCurve2);
    assertSame(referenceDate, actualCloneForParameter.getReferenceDate());
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d},
        actualCloneForParameter.getParameter(),
        0.0);
  }
}
