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

public class MertonModelDiffblueTest {
  /**
   * Test {@link MertonModel#MertonModel(double, double, double, double, double, double)}.
   *
   * <p>Method under test: {@link MertonModel#MertonModel(double, double, double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MertonModel.<init>(double, double, double, double, double, double)"})
  public void testNewMertonModel() {
    // Arrange and Act
    MertonModel actualMertonModel = new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertNull(actualMertonModel.getReferenceDate());
    assertNull(actualMertonModel.getDiscountCurveForDiscountRate());
    assertNull(actualMertonModel.getDiscountCurveForForwardRate());
    assertEquals(10.0d, actualMertonModel.getDiscountRate(), 0.0);
    assertEquals(10.0d, actualMertonModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualMertonModel.getJumpIntensity(), 0.0);
    assertEquals(10.0d, actualMertonModel.getJumpSizeMean(), 0.0);
    assertEquals(10.0d, actualMertonModel.getJumpSizeStdDev(), 0.0);
    assertEquals(10.0d, actualMertonModel.getRiskFreeRate(), 0.0);
    assertEquals(10.0d, actualMertonModel.getVolatility(), 0.0);
  }

  /**
   * Test {@link MertonModel#MertonModel(double, double, double, double, double, double, double)}.
   *
   * <p>Method under test: {@link MertonModel#MertonModel(double, double, double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MertonModel.<init>(double, double, double, double, double, double, double)"
  })
  public void testNewMertonModel2() {
    // Arrange and Act
    MertonModel actualMertonModel =
        new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertNull(actualMertonModel.getReferenceDate());
    assertNull(actualMertonModel.getDiscountCurveForDiscountRate());
    assertNull(actualMertonModel.getDiscountCurveForForwardRate());
    assertEquals(10.0d, actualMertonModel.getDiscountRate(), 0.0);
    assertEquals(10.0d, actualMertonModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualMertonModel.getJumpIntensity(), 0.0);
    assertEquals(10.0d, actualMertonModel.getJumpSizeMean(), 0.0);
    assertEquals(10.0d, actualMertonModel.getJumpSizeStdDev(), 0.0);
    assertEquals(10.0d, actualMertonModel.getRiskFreeRate(), 0.0);
    assertEquals(10.0d, actualMertonModel.getVolatility(), 0.0);
  }

  /**
   * Test {@link MertonModel#MertonModel(LocalDate, double, DiscountCurve, DiscountCurve, double,
   * double, double, double)}.
   *
   * <p>Method under test: {@link MertonModel#MertonModel(LocalDate, double, DiscountCurve,
   * DiscountCurve, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MertonModel.<init>(LocalDate, double, DiscountCurve, DiscountCurve, double, double, double, double)"
  })
  public void testNewMertonModel3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    MertonModel actualMertonModel =
        new MertonModel(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            10.0d,
            10.0d,
            10.0d,
            10.0d);

    // Assert
    assertEquals(10.0d, actualMertonModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualMertonModel.getJumpIntensity(), 0.0);
    assertEquals(10.0d, actualMertonModel.getJumpSizeMean(), 0.0);
    assertEquals(10.0d, actualMertonModel.getJumpSizeStdDev(), 0.0);
    assertEquals(10.0d, actualMertonModel.getVolatility(), 0.0);
    assertEquals(Double.NaN, actualMertonModel.getDiscountRate(), 0.0);
    assertEquals(Double.NaN, actualMertonModel.getRiskFreeRate(), 0.0);
    assertSame(discountCurveForDiscountRate, actualMertonModel.getDiscountCurveForDiscountRate());
    assertSame(discountCurveForForwardRate, actualMertonModel.getDiscountCurveForForwardRate());
    assertSame(referenceDate, actualMertonModel.getReferenceDate());
  }

  /**
   * Test {@link MertonModel#apply(double)}.
   *
   * <p>Method under test: {@link MertonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction MertonModel.apply(double)"})
  public void testApply() {
    // Arrange
    MertonModel mertonModel = new MertonModel(Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertSame(Complex.NaN, mertonModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link MertonModel#apply(double)}.
   *
   * <p>Method under test: {@link MertonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction MertonModel.apply(double)"})
  public void testApply2() {
    // Arrange
    MertonModel mertonModel = new MertonModel(10.0d, 10.0d, Double.NaN, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertSame(Complex.NaN, mertonModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link MertonModel#apply(double)}.
   *
   * <p>Method under test: {@link MertonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction MertonModel.apply(double)"})
  public void testApply3() {
    // Arrange, Act and Assert
    assertSame(
        Complex.NaN,
        new MertonModel(10.0d, 10.0d, 10.0d, Double.NaN, 10.0d, 10.0d)
            .apply(10.0d)
            .apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link MertonModel#apply(double)}.
   *
   * <p>Method under test: {@link MertonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction MertonModel.apply(double)"})
  public void testApply4() {
    // Arrange
    MertonModel mertonModel = new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, Double.NaN, 10.0d);

    // Act and Assert
    assertSame(Complex.NaN, mertonModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link MertonModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf {@link Double#NaN} is {@link Complex#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link MertonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction MertonModel.apply(double)"})
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
        new MertonModel(
                LocalDate.of(1970, 1, 1),
                2.0d,
                discountCurveForForwardRate,
                discountCurveForDiscountRate,
                2.0d,
                2.0d,
                2.0d,
                2.0d)
            .apply(10.0d)
            .apply(Complex.valueOf(Double.NaN));

    // Assert
    verify(discountCurveForForwardRate).getDiscountFactor(isNull(), eq(10.0d));
    verify(discountCurveForDiscountRate).getDiscountFactor(isNull(), eq(10.0d));
    assertSame(Complex.NaN, actualApplyResult);
  }

  /**
   * Test {@link MertonModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf ten Imaginary is {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link MertonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction MertonModel.apply(double)"})
  public void testApply_thenReturnApplyValueOfTenImaginaryIs00() {
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
        new MertonModel(
                LocalDate.of(1970, 1, 1),
                2.0d,
                discountCurveForForwardRate,
                discountCurveForDiscountRate,
                2.0d,
                2.0d,
                2.0d,
                2.0d)
            .apply(10.0d)
            .apply(Complex.valueOf(10.0d));

    // Assert
    verify(discountCurveForForwardRate).getDiscountFactor(isNull(), eq(10.0d));
    verify(discountCurveForDiscountRate).getDiscountFactor(isNull(), eq(10.0d));
    assertEquals(-0.0d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(-0.0d, actualApplyResult.getReal(), 0.0);
    assertEquals(-3.141592653589793d, actualApplyResult.getArgument(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link MertonModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf ten is {@link Complex#INF}.
   * </ul>
   *
   * <p>Method under test: {@link MertonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction MertonModel.apply(double)"})
  public void testApply_thenReturnApplyValueOfTenIsInf() {
    // Arrange
    MertonModel mertonModel = new MertonModel(10.0d, 10.0d, 10.0d, -0.5d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(Complex.INF, mertonModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link MertonModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf ten is {@link Complex#ZERO}.
   * </ul>
   *
   * <p>Method under test: {@link MertonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction MertonModel.apply(double)"})
  public void testApply_thenReturnApplyValueOfTenIsZero() {
    // Arrange
    MertonModel mertonModel = new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(Complex.ZERO, mertonModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MertonModel#toString()}
   *   <li>{@link MertonModel#getDiscountCurveForDiscountRate()}
   *   <li>{@link MertonModel#getDiscountCurveForForwardRate()}
   *   <li>{@link MertonModel#getDiscountRate()}
   *   <li>{@link MertonModel#getInitialValue()}
   *   <li>{@link MertonModel#getJumpIntensity()}
   *   <li>{@link MertonModel#getJumpSizeMean()}
   *   <li>{@link MertonModel#getJumpSizeStdDev()}
   *   <li>{@link MertonModel#getReferenceDate()}
   *   <li>{@link MertonModel#getRiskFreeRate()}
   *   <li>{@link MertonModel#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DiscountCurve MertonModel.getDiscountCurveForDiscountRate()",
    "DiscountCurve MertonModel.getDiscountCurveForForwardRate()",
    "double MertonModel.getDiscountRate()",
    "double MertonModel.getInitialValue()",
    "double MertonModel.getJumpIntensity()",
    "double MertonModel.getJumpSizeMean()",
    "double MertonModel.getJumpSizeStdDev()",
    "LocalDate MertonModel.getReferenceDate()",
    "double MertonModel.getRiskFreeRate()",
    "double MertonModel.getVolatility()",
    "String MertonModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    MertonModel mertonModel = new MertonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    String actualToStringResult = mertonModel.toString();
    DiscountCurve actualDiscountCurveForDiscountRate =
        mertonModel.getDiscountCurveForDiscountRate();
    DiscountCurve actualDiscountCurveForForwardRate = mertonModel.getDiscountCurveForForwardRate();
    double actualDiscountRate = mertonModel.getDiscountRate();
    double actualInitialValue = mertonModel.getInitialValue();
    double actualJumpIntensity = mertonModel.getJumpIntensity();
    double actualJumpSizeMean = mertonModel.getJumpSizeMean();
    double actualJumpSizeStdDev = mertonModel.getJumpSizeStdDev();
    LocalDate actualReferenceDate = mertonModel.getReferenceDate();
    double actualRiskFreeRate = mertonModel.getRiskFreeRate();

    // Assert
    assertEquals(
        "MertonModel [initialValue=10.0, discountCurveForForwardRate=null, riskFreeRate=10.0, discountCurveFo"
            + "rDiscountRate=null, discountRate=10.0, volatility=10.0, jumpIntensity=10.0, jumpSizeMean=10.0,"
            + " jumpSizeStdDev=10.0]",
        actualToStringResult);
    assertNull(actualReferenceDate);
    assertNull(actualDiscountCurveForDiscountRate);
    assertNull(actualDiscountCurveForForwardRate);
    assertEquals(10.0d, actualDiscountRate, 0.0);
    assertEquals(10.0d, actualInitialValue, 0.0);
    assertEquals(10.0d, actualJumpIntensity, 0.0);
    assertEquals(10.0d, actualJumpSizeMean, 0.0);
    assertEquals(10.0d, actualJumpSizeStdDev, 0.0);
    assertEquals(10.0d, actualRiskFreeRate, 0.0);
    assertEquals(10.0d, mertonModel.getVolatility(), 0.0);
  }
}
