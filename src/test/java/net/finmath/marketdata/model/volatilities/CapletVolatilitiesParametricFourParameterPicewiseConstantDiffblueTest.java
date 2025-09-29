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
import net.finmath.marketdata.model.volatilities.VolatilitySurface.QuotingConvention;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CapletVolatilitiesParametricFourParameterPicewiseConstantDiffblueTest {
  /**
   * Test {@link
   * CapletVolatilitiesParametricFourParameterPicewiseConstant#CapletVolatilitiesParametricFourParameterPicewiseConstant(String,
   * LocalDate, double, double, double, double, TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricFourParameterPicewiseConstant#CapletVolatilitiesParametricFourParameterPicewiseConstant(String,
   * LocalDate, double, double, double, double, TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolatilitiesParametricFourParameterPicewiseConstant.<init>(String, LocalDate, double, double, double, double, TimeDiscretization)"
  })
  public void testNewCapletVolatilitiesParametricFourParameterPicewiseConstant() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    CapletVolatilitiesParametricFourParameterPicewiseConstant
        actualCapletVolatilitiesParametricFourParameterPicewiseConstant =
            new CapletVolatilitiesParametricFourParameterPicewiseConstant(
                "Name",
                referenceDate,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                new TenorFromArray(10.0d, 10, 0.5d));

    // Assert
    assertEquals("Name", actualCapletVolatilitiesParametricFourParameterPicewiseConstant.getName());
    assertNull(actualCapletVolatilitiesParametricFourParameterPicewiseConstant.getDiscountCurve());
    assertNull(actualCapletVolatilitiesParametricFourParameterPicewiseConstant.getForwardCurve());
    assertNull(
        actualCapletVolatilitiesParametricFourParameterPicewiseConstant.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL,
        actualCapletVolatilitiesParametricFourParameterPicewiseConstant.getQuotingConvention());
    assertSame(
        referenceDate,
        actualCapletVolatilitiesParametricFourParameterPicewiseConstant.getReferenceDate());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCapletVolatilitiesParametricFourParameterPicewiseConstant.getParameter(),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(double, double,
   * QuotingConvention)} with {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametricFourParameterPicewiseConstant.getValue(double, double, QuotingConvention)"
  })
  public void testGetValueWithMaturityStrikeQuotingConvention() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametricFourParameterPicewiseConstant
        capletVolatilitiesParametricFourParameterPicewiseConstant =
            new CapletVolatilitiesParametricFourParameterPicewiseConstant(
                "Name",
                referenceDate,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));

    // Act and Assert
    assertEquals(
        0.0d,
        capletVolatilitiesParametricFourParameterPicewiseConstant.getValue(
            10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(double, double,
   * QuotingConvention)} with {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametricFourParameterPicewiseConstant.getValue(double, double, QuotingConvention)"
  })
  public void testGetValueWithMaturityStrikeQuotingConvention2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametricFourParameterPicewiseConstant
        capletVolatilitiesParametricFourParameterPicewiseConstant =
            new CapletVolatilitiesParametricFourParameterPicewiseConstant(
                "Name",
                referenceDate,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolatilitiesParametricFourParameterPicewiseConstant.getValue(
                10.0d, 10.0d, QuotingConvention.VOLATILITYNORMAL));
  }

  /**
   * Test {@link CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(double, double,
   * QuotingConvention)} with {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametricFourParameterPicewiseConstant.getValue(double, double, QuotingConvention)"
  })
  public void testGetValueWithMaturityStrikeQuotingConvention_thenReturnZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametricFourParameterPicewiseConstant
        capletVolatilitiesParametricFourParameterPicewiseConstant =
            new CapletVolatilitiesParametricFourParameterPicewiseConstant(
                "Name",
                referenceDate,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertEquals(
        0.0d,
        capletVolatilitiesParametricFourParameterPicewiseConstant.getValue(
            10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(double, double,
   * QuotingConvention)} with {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametricFourParameterPicewiseConstant.getValue(double, double, QuotingConvention)"
  })
  public void testGetValueWithMaturityStrikeQuotingConvention_whenZero_thenReturnZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametricFourParameterPicewiseConstant
        capletVolatilitiesParametricFourParameterPicewiseConstant =
            new CapletVolatilitiesParametricFourParameterPicewiseConstant(
                "Name",
                referenceDate,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertEquals(
        0.0d,
        capletVolatilitiesParametricFourParameterPicewiseConstant.getValue(
            0.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(AnalyticModel,
   * double, double, QuotingConvention)} with {@code model}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(AnalyticModel, double,
   * double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametricFourParameterPicewiseConstant.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametricFourParameterPicewiseConstant
        capletVolatilitiesParametricFourParameterPicewiseConstant =
            new CapletVolatilitiesParametricFourParameterPicewiseConstant(
                "Name",
                referenceDate,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));

    // Act and Assert
    assertEquals(
        0.0d,
        capletVolatilitiesParametricFourParameterPicewiseConstant.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(AnalyticModel,
   * double, double, QuotingConvention)} with {@code model}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(AnalyticModel, double,
   * double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametricFourParameterPicewiseConstant.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametricFourParameterPicewiseConstant
        capletVolatilitiesParametricFourParameterPicewiseConstant =
            new CapletVolatilitiesParametricFourParameterPicewiseConstant(
                "Name",
                referenceDate,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolatilitiesParametricFourParameterPicewiseConstant.getValue(
                new AnalyticModelFromCurvesAndVols(),
                10.0d,
                10.0d,
                QuotingConvention.VOLATILITYNORMAL));
  }

  /**
   * Test {@link CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(AnalyticModel,
   * double, double, QuotingConvention)} with {@code model}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(AnalyticModel, double,
   * double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametricFourParameterPicewiseConstant.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_thenReturnZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametricFourParameterPicewiseConstant
        capletVolatilitiesParametricFourParameterPicewiseConstant =
            new CapletVolatilitiesParametricFourParameterPicewiseConstant(
                "Name",
                referenceDate,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertEquals(
        0.0d,
        capletVolatilitiesParametricFourParameterPicewiseConstant.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(AnalyticModel,
   * double, double, QuotingConvention)} with {@code model}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricFourParameterPicewiseConstant#getValue(AnalyticModel, double,
   * double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitiesParametricFourParameterPicewiseConstant.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_whenZero_thenReturnZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametricFourParameterPicewiseConstant
        capletVolatilitiesParametricFourParameterPicewiseConstant =
            new CapletVolatilitiesParametricFourParameterPicewiseConstant(
                "Name",
                referenceDate,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertEquals(
        0.0d,
        capletVolatilitiesParametricFourParameterPicewiseConstant.getValue(
            new AnalyticModelFromCurvesAndVols(),
            0.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametricFourParameterPicewiseConstant#getParameter()}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricFourParameterPicewiseConstant#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] CapletVolatilitiesParametricFourParameterPicewiseConstant.getParameter()"
  })
  public void testGetParameter() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametricFourParameterPicewiseConstant
        capletVolatilitiesParametricFourParameterPicewiseConstant =
            new CapletVolatilitiesParametricFourParameterPicewiseConstant(
                "Name",
                referenceDate,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        capletVolatilitiesParametricFourParameterPicewiseConstant.getParameter(),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitiesParametricFourParameterPicewiseConstant#setParameter(double[])}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricFourParameterPicewiseConstant#setParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolatilitiesParametricFourParameterPicewiseConstant.setParameter(double[])"
  })
  public void testSetParameter() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametricFourParameterPicewiseConstant
        capletVolatilitiesParametricFourParameterPicewiseConstant =
            new CapletVolatilitiesParametricFourParameterPicewiseConstant(
                "Name",
                referenceDate,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            capletVolatilitiesParametricFourParameterPicewiseConstant.setParameter(
                new double[] {10.0d, 1.0d, 10.0d, 1.0d}));
  }

  /**
   * Test {@link
   * CapletVolatilitiesParametricFourParameterPicewiseConstant#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link
   * CapletVolatilitiesParametricFourParameterPicewiseConstant#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractVolatilitySurfaceParametric CapletVolatilitiesParametricFourParameterPicewiseConstant.getCloneForParameter(double[])"
  })
  public void testGetCloneForParameter() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametricFourParameterPicewiseConstant
        capletVolatilitiesParametricFourParameterPicewiseConstant =
            new CapletVolatilitiesParametricFourParameterPicewiseConstant(
                "Name",
                referenceDate,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    AbstractVolatilitySurfaceParametric actualCloneForParameter =
        capletVolatilitiesParametricFourParameterPicewiseConstant.getCloneForParameter(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertTrue(
        actualCloneForParameter
            instanceof CapletVolatilitiesParametricFourParameterPicewiseConstant);
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
        new double[] {10.0d, 1.0d, 10.0d, 1.0d}, actualCloneForParameter.getParameter(), 0.0);
  }
}
