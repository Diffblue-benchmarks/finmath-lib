package net.finmath.fouriermethod.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexField;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class VarianceGammaModelDiffblueTest {
  /**
   * Test {@link VarianceGammaModel#VarianceGammaModel(double, double, double, double, double,
   * double)}.
   *
   * <p>Method under test: {@link VarianceGammaModel#VarianceGammaModel(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void VarianceGammaModel.<init>(double, double, double, double, double, double)"
  })
  public void testNewVarianceGammaModel() {
    // Arrange and Act
    VarianceGammaModel actualVarianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertNull(actualVarianceGammaModel.getReferenceDate());
    assertNull(actualVarianceGammaModel.getDiscountCurveForDiscountRate());
    assertNull(actualVarianceGammaModel.getDiscountCurveForForwardRate());
    assertEquals(10.0d, actualVarianceGammaModel.getDiscountRate(), 0.0);
    assertEquals(10.0d, actualVarianceGammaModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualVarianceGammaModel.getNu(), 0.0);
    assertEquals(10.0d, actualVarianceGammaModel.getRiskFreeRate(), 0.0);
    assertEquals(10.0d, actualVarianceGammaModel.getSigma(), 0.0);
    assertEquals(10.0d, actualVarianceGammaModel.getTheta(), 0.0);
  }

  /**
   * Test {@link VarianceGammaModel#VarianceGammaModel(LocalDate, double, DiscountCurve,
   * DiscountCurve, double, double, double)}.
   *
   * <p>Method under test: {@link VarianceGammaModel#VarianceGammaModel(LocalDate, double,
   * DiscountCurve, DiscountCurve, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void VarianceGammaModel.<init>(LocalDate, double, DiscountCurve, DiscountCurve, double, double, double)"
  })
  public void testNewVarianceGammaModel2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    VarianceGammaModel actualVarianceGammaModel =
        new VarianceGammaModel(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            10.0d,
            10.0d,
            10.0d);

    // Assert
    assertEquals(10.0d, actualVarianceGammaModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualVarianceGammaModel.getNu(), 0.0);
    assertEquals(10.0d, actualVarianceGammaModel.getSigma(), 0.0);
    assertEquals(10.0d, actualVarianceGammaModel.getTheta(), 0.0);
    assertEquals(Double.NaN, actualVarianceGammaModel.getDiscountRate(), 0.0);
    assertEquals(Double.NaN, actualVarianceGammaModel.getRiskFreeRate(), 0.0);
    assertSame(
        discountCurveForDiscountRate, actualVarianceGammaModel.getDiscountCurveForDiscountRate());
    assertSame(
        discountCurveForForwardRate, actualVarianceGammaModel.getDiscountCurveForForwardRate());
    assertSame(referenceDate, actualVarianceGammaModel.getReferenceDate());
  }

  /**
   * Test {@link VarianceGammaModel#apply(double)}.
   *
   * <p>Method under test: {@link VarianceGammaModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.fouriermethod.CharacteristicFunction VarianceGammaModel.apply(double)"
  })
  public void testApply() {
    // Arrange, Act and Assert
    assertSame(
        Complex.NaN,
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, Double.NaN, 10.0d, 10.0d)
            .apply(10.0d)
            .apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link VarianceGammaModel#apply(double)}.
   *
   * <p>Method under test: {@link VarianceGammaModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.fouriermethod.CharacteristicFunction VarianceGammaModel.apply(double)"
  })
  public void testApply2() {
    // Arrange
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, Double.NaN, 10.0d, 10.0d, -0.5d);

    // Act and Assert
    assertSame(Complex.NaN, varianceGammaModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link VarianceGammaModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf {@code 0.5} and {@code 0.5} is {@link Complex#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link VarianceGammaModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.fouriermethod.CharacteristicFunction VarianceGammaModel.apply(double)"
  })
  public void testApply_thenReturnApplyValueOf05And05IsNaN() {
    // Arrange
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertSame(Complex.NaN, varianceGammaModel.apply(10.0d).apply(Complex.valueOf(0.5d, 0.5d)));
  }

  /**
   * Test {@link VarianceGammaModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf {@link Double#NaN} is {@link Complex#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link VarianceGammaModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.fouriermethod.CharacteristicFunction VarianceGammaModel.apply(double)"
  })
  public void testApply_thenReturnApplyValueOfNaNIsNaN() {
    // Arrange
    DiscountCurveInterpolation discountCurveForForwardRate = mock(DiscountCurveInterpolation.class);
    when(discountCurveForForwardRate.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    DiscountCurveInterpolation discountCurveForDiscountRate =
        mock(DiscountCurveInterpolation.class);
    when(discountCurveForDiscountRate.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    // Act
    Complex actualApplyResult =
        new VarianceGammaModel(
                LocalDate.of(1970, 1, 1),
                0.5d,
                discountCurveForForwardRate,
                discountCurveForDiscountRate,
                0.5d,
                0.5d,
                0.5d)
            .apply(10.0d)
            .apply(Complex.valueOf(Double.NaN));

    // Assert
    verify(discountCurveForForwardRate).getDiscountFactor(isNull(), eq(10.0d));
    verify(discountCurveForDiscountRate).getDiscountFactor(isNull(), eq(10.0d));
    assertSame(Complex.NaN, actualApplyResult);
  }

  /**
   * Test {@link VarianceGammaModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf ten is {@link Complex#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link VarianceGammaModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.fouriermethod.CharacteristicFunction VarianceGammaModel.apply(double)"
  })
  public void testApply_thenReturnApplyValueOfTenIsNaN() {
    // Arrange
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertSame(Complex.NaN, varianceGammaModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link VarianceGammaModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf ten Real is {@code -1.3094154514889131E-17}.
   * </ul>
   *
   * <p>Method under test: {@link VarianceGammaModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.fouriermethod.CharacteristicFunction VarianceGammaModel.apply(double)"
  })
  public void testApply_thenReturnApplyValueOfTenRealIs13094154514889131e17() {
    // Arrange
    DiscountCurveInterpolation discountCurveForForwardRate = mock(DiscountCurveInterpolation.class);
    when(discountCurveForForwardRate.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    DiscountCurveInterpolation discountCurveForDiscountRate =
        mock(DiscountCurveInterpolation.class);
    when(discountCurveForDiscountRate.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    // Act
    Complex actualApplyResult =
        new VarianceGammaModel(
                LocalDate.of(1970, 1, 1),
                0.5d,
                discountCurveForForwardRate,
                discountCurveForDiscountRate,
                0.5d,
                0.5d,
                0.5d)
            .apply(10.0d)
            .apply(Complex.valueOf(10.0d));

    // Assert
    verify(discountCurveForForwardRate).getDiscountFactor(isNull(), eq(10.0d));
    verify(discountCurveForDiscountRate).getDiscountFactor(isNull(), eq(10.0d));
    assertEquals(-1.3094154514889131E-17d, actualApplyResult.getReal(), 0.0);
    assertEquals(1.5378340276457007E-17d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(2.0197777900509033E-17d, actualApplyResult.abs(), 0.0);
    assertEquals(2.2761416419090534d, actualApplyResult.getArgument(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link VarianceGammaModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf ten Real is {@code -3.1965702786101716E24}.
   * </ul>
   *
   * <p>Method under test: {@link VarianceGammaModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.fouriermethod.CharacteristicFunction VarianceGammaModel.apply(double)"
  })
  public void testApply_thenReturnApplyValueOfTenRealIs31965702786101716e24() {
    // Arrange
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, -0.5d);

    // Act
    Complex actualApplyResult = varianceGammaModel.apply(10.0d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(-3.1965702786101716E24d, actualApplyResult.getReal(), 0.0);
    assertEquals(1.0668023011259092E24d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(2.8194820729301058d, actualApplyResult.getArgument(), 0.0);
    assertEquals(3.3698855612292603E24d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link VarianceGammaModel#toString()}
   *   <li>{@link VarianceGammaModel#getDiscountCurveForDiscountRate()}
   *   <li>{@link VarianceGammaModel#getDiscountCurveForForwardRate()}
   *   <li>{@link VarianceGammaModel#getDiscountRate()}
   *   <li>{@link VarianceGammaModel#getInitialValue()}
   *   <li>{@link VarianceGammaModel#getNu()}
   *   <li>{@link VarianceGammaModel#getReferenceDate()}
   *   <li>{@link VarianceGammaModel#getRiskFreeRate()}
   *   <li>{@link VarianceGammaModel#getSigma()}
   *   <li>{@link VarianceGammaModel#getTheta()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DiscountCurve VarianceGammaModel.getDiscountCurveForDiscountRate()",
    "DiscountCurve VarianceGammaModel.getDiscountCurveForForwardRate()",
    "double VarianceGammaModel.getDiscountRate()",
    "double VarianceGammaModel.getInitialValue()",
    "double VarianceGammaModel.getNu()",
    "LocalDate VarianceGammaModel.getReferenceDate()",
    "double VarianceGammaModel.getRiskFreeRate()",
    "double VarianceGammaModel.getSigma()",
    "double VarianceGammaModel.getTheta()",
    "String VarianceGammaModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    String actualToStringResult = varianceGammaModel.toString();
    DiscountCurve actualDiscountCurveForDiscountRate =
        varianceGammaModel.getDiscountCurveForDiscountRate();
    DiscountCurve actualDiscountCurveForForwardRate =
        varianceGammaModel.getDiscountCurveForForwardRate();
    double actualDiscountRate = varianceGammaModel.getDiscountRate();
    double actualInitialValue = varianceGammaModel.getInitialValue();
    double actualNu = varianceGammaModel.getNu();
    LocalDate actualReferenceDate = varianceGammaModel.getReferenceDate();
    double actualRiskFreeRate = varianceGammaModel.getRiskFreeRate();
    double actualSigma = varianceGammaModel.getSigma();

    // Assert
    assertEquals(
        "VarianceGammaModel [referenceDate=null, initialValue=10.0, discountCurveForForwardRate=null,"
            + " riskFreeRate=10.0, discountCurveForDiscountRate=null, discountRate=10.0, sigma=10.0, theta=10.0,"
            + " nu=10.0]",
        actualToStringResult);
    assertNull(actualReferenceDate);
    assertNull(actualDiscountCurveForDiscountRate);
    assertNull(actualDiscountCurveForForwardRate);
    assertEquals(10.0d, actualDiscountRate, 0.0);
    assertEquals(10.0d, actualInitialValue, 0.0);
    assertEquals(10.0d, actualNu, 0.0);
    assertEquals(10.0d, actualRiskFreeRate, 0.0);
    assertEquals(10.0d, actualSigma, 0.0);
    assertEquals(10.0d, varianceGammaModel.getTheta(), 0.0);
  }
}
