package net.finmath.fouriermethod.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
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

public class BlackScholesModelDiffblueTest {
  /**
   * Test {@link BlackScholesModel#BlackScholesModel(double, double, double)}.
   *
   * <p>Method under test: {@link BlackScholesModel#BlackScholesModel(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BlackScholesModel.<init>(double, double, double)"})
  public void testNewBlackScholesModel() {
    // Arrange and Act
    BlackScholesModel actualBlackScholesModel = new BlackScholesModel(10.0d, 10.0d, 10.0d);

    // Assert
    assertNull(actualBlackScholesModel.getReferenceDate());
    assertNull(actualBlackScholesModel.getDiscountCurveForDiscountRate());
    assertNull(actualBlackScholesModel.getDiscountCurveForForwardRate());
    assertEquals(10.0d, actualBlackScholesModel.getDiscountRate(), 0.0);
    assertEquals(10.0d, actualBlackScholesModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualBlackScholesModel.getRiskFreeRate(), 0.0);
    assertEquals(10.0d, actualBlackScholesModel.getVolatility(), 0.0);
  }

  /**
   * Test {@link BlackScholesModel#BlackScholesModel(double, double, double, double)}.
   *
   * <p>Method under test: {@link BlackScholesModel#BlackScholesModel(double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BlackScholesModel.<init>(double, double, double, double)"})
  public void testNewBlackScholesModel2() {
    // Arrange and Act
    BlackScholesModel actualBlackScholesModel = new BlackScholesModel(10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertNull(actualBlackScholesModel.getReferenceDate());
    assertNull(actualBlackScholesModel.getDiscountCurveForDiscountRate());
    assertNull(actualBlackScholesModel.getDiscountCurveForForwardRate());
    assertEquals(10.0d, actualBlackScholesModel.getDiscountRate(), 0.0);
    assertEquals(10.0d, actualBlackScholesModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualBlackScholesModel.getRiskFreeRate(), 0.0);
    assertEquals(10.0d, actualBlackScholesModel.getVolatility(), 0.0);
  }

  /**
   * Test {@link BlackScholesModel#BlackScholesModel(LocalDate, double, DiscountCurve,
   * DiscountCurve, double)}.
   *
   * <p>Method under test: {@link BlackScholesModel#BlackScholesModel(LocalDate, double,
   * DiscountCurve, DiscountCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlackScholesModel.<init>(LocalDate, double, DiscountCurve, DiscountCurve, double)"
  })
  public void testNewBlackScholesModel3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    BlackScholesModel actualBlackScholesModel =
        new BlackScholesModel(
            referenceDate, 10.0d, discountCurveForForwardRate, discountCurveForDiscountRate, 10.0d);

    // Assert
    assertEquals(10.0d, actualBlackScholesModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualBlackScholesModel.getVolatility(), 0.0);
    assertEquals(Double.NaN, actualBlackScholesModel.getDiscountRate(), 0.0);
    assertEquals(Double.NaN, actualBlackScholesModel.getRiskFreeRate(), 0.0);
    assertSame(
        discountCurveForDiscountRate, actualBlackScholesModel.getDiscountCurveForDiscountRate());
    assertSame(
        discountCurveForForwardRate, actualBlackScholesModel.getDiscountCurveForForwardRate());
    assertSame(referenceDate, actualBlackScholesModel.getReferenceDate());
  }

  /**
   * Test {@link BlackScholesModel#apply(double)}.
   *
   * <p>Method under test: {@link BlackScholesModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.fouriermethod.CharacteristicFunction BlackScholesModel.apply(double)"
  })
  public void testApply() {
    // Arrange, Act and Assert
    assertSame(
        Complex.NaN,
        new BlackScholesModel(Double.NaN, 10.0d, 10.0d).apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link BlackScholesModel#apply(double)}.
   *
   * <p>Method under test: {@link BlackScholesModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.fouriermethod.CharacteristicFunction BlackScholesModel.apply(double)"
  })
  public void testApply2() {
    // Arrange, Act and Assert
    assertSame(
        Complex.NaN,
        new BlackScholesModel(10.0d, 10.0d, Double.NaN).apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link BlackScholesModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf {@link Double#NaN} is {@link Complex#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.fouriermethod.CharacteristicFunction BlackScholesModel.apply(double)"
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
        new BlackScholesModel(
                LocalDate.of(1970, 1, 1),
                0.5d,
                discountCurveForForwardRate,
                discountCurveForDiscountRate,
                0.5d)
            .apply(10.0d)
            .apply(Complex.valueOf(Double.NaN));

    // Assert
    verify(discountCurveForForwardRate).getDiscountFactor(isNull(), eq(10.0d));
    verify(discountCurveForDiscountRate).getDiscountFactor(isNull(), eq(10.0d));
    assertSame(Complex.NaN, actualApplyResult);
  }

  /**
   * Test {@link BlackScholesModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf ten Argument is {@code 1.5249744147171942}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.fouriermethod.CharacteristicFunction BlackScholesModel.apply(double)"
  })
  public void testApply_thenReturnApplyValueOfTenArgumentIs15249744147171942() {
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
        new BlackScholesModel(
                LocalDate.of(1970, 1, 1),
                0.5d,
                discountCurveForForwardRate,
                discountCurveForDiscountRate,
                0.5d)
            .apply(10.0d)
            .apply(Complex.valueOf(10.0d));

    // Assert
    verify(discountCurveForForwardRate).getDiscountFactor(isNull(), eq(10.0d));
    verify(discountCurveForDiscountRate).getDiscountFactor(isNull(), eq(10.0d));
    assertEquals(1.5249744147171942d, actualApplyResult.getArgument(), 0.0);
    assertEquals(2.366524372490024E-55d, actualApplyResult.getReal(), 0.0);
    assertEquals(5.1609977503731885E-54d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(5.166420632837885E-54d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link BlackScholesModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf ten is {@link Complex#ZERO}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.fouriermethod.CharacteristicFunction BlackScholesModel.apply(double)"
  })
  public void testApply_thenReturnApplyValueOfTenIsZero() {
    // Arrange, Act and Assert
    assertEquals(
        Complex.ZERO,
        new BlackScholesModel(10.0d, 10.0d, 10.0d).apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link BlackScholesModel#apply(double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return apply valueOf ten Argument is {@code 2.356194490192345}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.fouriermethod.CharacteristicFunction BlackScholesModel.apply(double)"
  })
  public void testApply_when05_thenReturnApplyValueOfTenArgumentIs2356194490192345() {
    // Arrange and Act
    Complex actualApplyResult =
        new BlackScholesModel(10.0d, 10.0d, 10.0d).apply(-0.5d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(2.356194490192345d, actualApplyResult.getArgument(), 0.0);
    assertTrue(actualApplyResult.isInfinite());
    assertEquals(Double.NEGATIVE_INFINITY, actualApplyResult.getReal(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualApplyResult.abs(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualApplyResult.getImaginary(), 0.0);
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
   *   <li>{@link BlackScholesModel#toString()}
   *   <li>{@link BlackScholesModel#getDiscountCurveForDiscountRate()}
   *   <li>{@link BlackScholesModel#getDiscountCurveForForwardRate()}
   *   <li>{@link BlackScholesModel#getDiscountRate()}
   *   <li>{@link BlackScholesModel#getInitialValue()}
   *   <li>{@link BlackScholesModel#getReferenceDate()}
   *   <li>{@link BlackScholesModel#getRiskFreeRate()}
   *   <li>{@link BlackScholesModel#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DiscountCurve BlackScholesModel.getDiscountCurveForDiscountRate()",
    "DiscountCurve BlackScholesModel.getDiscountCurveForForwardRate()",
    "double BlackScholesModel.getDiscountRate()",
    "double BlackScholesModel.getInitialValue()",
    "LocalDate BlackScholesModel.getReferenceDate()",
    "double BlackScholesModel.getRiskFreeRate()",
    "double BlackScholesModel.getVolatility()",
    "String BlackScholesModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    BlackScholesModel blackScholesModel = new BlackScholesModel(10.0d, 10.0d, 10.0d);

    // Act
    String actualToStringResult = blackScholesModel.toString();
    DiscountCurve actualDiscountCurveForDiscountRate =
        blackScholesModel.getDiscountCurveForDiscountRate();
    DiscountCurve actualDiscountCurveForForwardRate =
        blackScholesModel.getDiscountCurveForForwardRate();
    double actualDiscountRate = blackScholesModel.getDiscountRate();
    double actualInitialValue = blackScholesModel.getInitialValue();
    LocalDate actualReferenceDate = blackScholesModel.getReferenceDate();
    double actualRiskFreeRate = blackScholesModel.getRiskFreeRate();

    // Assert
    assertEquals(
        "BlackScholesModel [initialValue=10.0, discountCurveForForwardRate=null, riskFreeRate=10.0, discountC"
            + "urveForDiscountRate=null, discountRate=10.0, volatility=10.0]",
        actualToStringResult);
    assertNull(actualReferenceDate);
    assertNull(actualDiscountCurveForDiscountRate);
    assertNull(actualDiscountCurveForForwardRate);
    assertEquals(10.0d, actualDiscountRate, 0.0);
    assertEquals(10.0d, actualInitialValue, 0.0);
    assertEquals(10.0d, actualRiskFreeRate, 0.0);
    assertEquals(10.0d, blackScholesModel.getVolatility(), 0.0);
  }
}
