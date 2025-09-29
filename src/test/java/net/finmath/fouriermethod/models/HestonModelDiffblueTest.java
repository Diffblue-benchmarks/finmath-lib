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

public class HestonModelDiffblueTest {
  /**
   * Test {@link HestonModel#HestonModel(double, double, double, double, double, double, double)}.
   *
   * <p>Method under test: {@link HestonModel#HestonModel(double, double, double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HestonModel.<init>(double, double, double, double, double, double, double)"
  })
  public void testNewHestonModel() {
    // Arrange and Act
    HestonModel actualHestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertNull(actualHestonModel.getReferenceDate());
    assertNull(actualHestonModel.getDiscountCurveForDiscountRate());
    assertNull(actualHestonModel.getDiscountCurveForForwardRate());
    assertEquals(10.0d, actualHestonModel.getDiscountRate(), 0.0);
    assertEquals(10.0d, actualHestonModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualHestonModel.getKappa(), 0.0);
    assertEquals(10.0d, actualHestonModel.getRho(), 0.0);
    assertEquals(10.0d, actualHestonModel.getRiskFreeRate(), 0.0);
    assertEquals(10.0d, actualHestonModel.getTheta(), 0.0);
    assertEquals(10.0d, actualHestonModel.getVolatility(), 0.0);
    assertEquals(10.0d, actualHestonModel.getXi(), 0.0);
  }

  /**
   * Test {@link HestonModel#HestonModel(double, double, double, double, double, double, double,
   * double)}.
   *
   * <p>Method under test: {@link HestonModel#HestonModel(double, double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HestonModel.<init>(double, double, double, double, double, double, double, double)"
  })
  public void testNewHestonModel2() {
    // Arrange and Act
    HestonModel actualHestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertNull(actualHestonModel.getReferenceDate());
    assertNull(actualHestonModel.getDiscountCurveForDiscountRate());
    assertNull(actualHestonModel.getDiscountCurveForForwardRate());
    assertEquals(10.0d, actualHestonModel.getDiscountRate(), 0.0);
    assertEquals(10.0d, actualHestonModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualHestonModel.getKappa(), 0.0);
    assertEquals(10.0d, actualHestonModel.getRho(), 0.0);
    assertEquals(10.0d, actualHestonModel.getRiskFreeRate(), 0.0);
    assertEquals(10.0d, actualHestonModel.getTheta(), 0.0);
    assertEquals(10.0d, actualHestonModel.getVolatility(), 0.0);
    assertEquals(10.0d, actualHestonModel.getXi(), 0.0);
  }

  /**
   * Test {@link HestonModel#HestonModel(double, DiscountCurve, DiscountCurve, double, double,
   * double, double, double)}.
   *
   * <p>Method under test: {@link HestonModel#HestonModel(double, DiscountCurve, DiscountCurve,
   * double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HestonModel.<init>(double, DiscountCurve, DiscountCurve, double, double, double, double, double)"
  })
  public void testNewHestonModel3() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    HestonModel actualHestonModel =
        new HestonModel(
            10.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);

    // Assert
    DiscountCurve discountCurveForForwardRate2 = actualHestonModel.getDiscountCurveForForwardRate();
    assertTrue(discountCurveForForwardRate2 instanceof DiscountCurveFromForwardCurve);
    assertNull(actualHestonModel.getReferenceDate());
    assertEquals(10.0d, actualHestonModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualHestonModel.getKappa(), 0.0);
    assertEquals(10.0d, actualHestonModel.getRho(), 0.0);
    assertEquals(10.0d, actualHestonModel.getTheta(), 0.0);
    assertEquals(10.0d, actualHestonModel.getVolatility(), 0.0);
    assertEquals(10.0d, actualHestonModel.getXi(), 0.0);
    assertEquals(Double.NaN, actualHestonModel.getDiscountRate(), 0.0);
    assertEquals(Double.NaN, actualHestonModel.getRiskFreeRate(), 0.0);
    assertSame(discountCurveForDiscountRate, actualHestonModel.getDiscountCurveForDiscountRate());
    assertSame(discountCurveForForwardRate, discountCurveForForwardRate2);
  }

  /**
   * Test {@link HestonModel#HestonModel(LocalDate, double, DiscountCurve, DiscountCurve, double,
   * double, double, double, double)}.
   *
   * <p>Method under test: {@link HestonModel#HestonModel(LocalDate, double, DiscountCurve,
   * DiscountCurve, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HestonModel.<init>(LocalDate, double, DiscountCurve, DiscountCurve, double, double, double, double, double)"
  })
  public void testNewHestonModel4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    HestonModel actualHestonModel =
        new HestonModel(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);

    // Assert
    DiscountCurve discountCurveForForwardRate2 = actualHestonModel.getDiscountCurveForForwardRate();
    assertTrue(discountCurveForForwardRate2 instanceof DiscountCurveFromForwardCurve);
    assertEquals(10.0d, actualHestonModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualHestonModel.getKappa(), 0.0);
    assertEquals(10.0d, actualHestonModel.getRho(), 0.0);
    assertEquals(10.0d, actualHestonModel.getTheta(), 0.0);
    assertEquals(10.0d, actualHestonModel.getVolatility(), 0.0);
    assertEquals(10.0d, actualHestonModel.getXi(), 0.0);
    assertEquals(Double.NaN, actualHestonModel.getDiscountRate(), 0.0);
    assertEquals(Double.NaN, actualHestonModel.getRiskFreeRate(), 0.0);
    assertSame(discountCurveForDiscountRate, actualHestonModel.getDiscountCurveForDiscountRate());
    assertSame(discountCurveForForwardRate, discountCurveForForwardRate2);
    assertSame(referenceDate, actualHestonModel.getReferenceDate());
  }

  /**
   * Test {@link HestonModel#apply(double)}.
   *
   * <p>Method under test: {@link HestonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction HestonModel.apply(double)"})
  public void testApply() {
    // Arrange
    HestonModel hestonModel = new HestonModel(Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertSame(Complex.NaN, hestonModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link HestonModel#apply(double)}.
   *
   * <p>Method under test: {@link HestonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction HestonModel.apply(double)"})
  public void testApply2() {
    // Arrange
    HestonModel hestonModel = new HestonModel(10.0d, 10.0d, Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertSame(Complex.NaN, hestonModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link HestonModel#apply(double)}.
   *
   * <p>Method under test: {@link HestonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction HestonModel.apply(double)"})
  public void testApply3() {
    // Arrange, Act and Assert
    assertSame(
        Complex.NaN,
        new HestonModel(10.0d, 10.0d, 10.0d, Double.NaN, 10.0d, 10.0d, 10.0d)
            .apply(10.0d)
            .apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link HestonModel#apply(double)}.
   *
   * <p>Method under test: {@link HestonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction HestonModel.apply(double)"})
  public void testApply4() {
    // Arrange
    HestonModel hestonModel = new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, Double.NaN, 10.0d, 10.0d);

    // Act and Assert
    assertSame(Complex.NaN, hestonModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link HestonModel#apply(double)}.
   *
   * <ul>
   *   <li>Given {@link DiscountCurveInterpolation} {@link
   *       DiscountCurveInterpolation#getDiscountFactor(AnalyticModel, double)} return {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction HestonModel.apply(double)"})
  public void testApply_givenDiscountCurveInterpolationGetDiscountFactorReturn05() {
    // Arrange
    DiscountCurveInterpolation discountCurveForForwardRate = mock(DiscountCurveInterpolation.class);
    when(discountCurveForForwardRate.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    DiscountCurveInterpolation discountCurveForDiscountRate =
        mock(DiscountCurveInterpolation.class);
    when(discountCurveForDiscountRate.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(-0.5d);

    HestonModel hestonModel =
        new HestonModel(
            2.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            2.0d,
            2.0d,
            2.0d,
            2.0d,
            2.0d);

    // Act
    Complex actualApplyResult = hestonModel.apply(10.0d).apply(Complex.valueOf(10.0d));

    // Assert
    verify(discountCurveForForwardRate).getDiscountFactor(isNull(), eq(10.0d));
    verify(discountCurveForDiscountRate).getDiscountFactor(isNull(), eq(10.0d));
    assertSame(Complex.NaN, actualApplyResult);
  }

  /**
   * Test {@link HestonModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf {@link Double#NaN} is {@link Complex#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction HestonModel.apply(double)"})
  public void testApply_thenReturnApplyValueOfNaNIsNaN() {
    // Arrange
    DiscountCurveInterpolation discountCurveForForwardRate = mock(DiscountCurveInterpolation.class);
    when(discountCurveForForwardRate.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    DiscountCurveInterpolation discountCurveForDiscountRate =
        mock(DiscountCurveInterpolation.class);
    when(discountCurveForDiscountRate.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    HestonModel hestonModel =
        new HestonModel(
            2.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            2.0d,
            2.0d,
            2.0d,
            2.0d,
            2.0d);

    // Act
    Complex actualApplyResult = hestonModel.apply(10.0d).apply(Complex.valueOf(Double.NaN));

    // Assert
    verify(discountCurveForForwardRate).getDiscountFactor(isNull(), eq(10.0d));
    verify(discountCurveForDiscountRate).getDiscountFactor(isNull(), eq(10.0d));
    assertSame(Complex.NaN, actualApplyResult);
  }

  /**
   * Test {@link HestonModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf ten Argument is {@code 0.19665883755920116}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction HestonModel.apply(double)"})
  public void testApply_thenReturnApplyValueOfTenArgumentIs019665883755920116() {
    // Arrange
    DiscountCurveInterpolation discountCurveForForwardRate = mock(DiscountCurveInterpolation.class);
    when(discountCurveForForwardRate.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    DiscountCurveInterpolation discountCurveForDiscountRate =
        mock(DiscountCurveInterpolation.class);
    when(discountCurveForDiscountRate.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    HestonModel hestonModel =
        new HestonModel(
            2.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            2.0d,
            2.0d,
            2.0d,
            2.0d,
            2.0d);

    // Act
    Complex actualApplyResult = hestonModel.apply(10.0d).apply(Complex.valueOf(10.0d));

    // Assert
    verify(discountCurveForForwardRate).getDiscountFactor(isNull(), eq(10.0d));
    verify(discountCurveForDiscountRate).getDiscountFactor(isNull(), eq(10.0d));
    assertEquals(0.19665883755920116d, actualApplyResult.getArgument(), 0.0);
    assertEquals(159.68234702937264d, actualApplyResult.getReal(), 0.0);
    assertEquals(162.8207341514037d, actualApplyResult.abs(), 0.0);
    assertEquals(31.81414020200904d, actualApplyResult.getImaginary(), 0.0);
  }

  /**
   * Test {@link HestonModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf ten Argument is {@code 0.22786524904606642}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction HestonModel.apply(double)"})
  public void testApply_thenReturnApplyValueOfTenArgumentIs022786524904606642() {
    // Arrange
    HestonModel hestonModel = new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    Complex actualApplyResult = hestonModel.apply(10.0d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(0.22786524904606642d, actualApplyResult.getArgument(), 0.0);
    assertEquals(1.2079732096130943E-42d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(5.209190343295132E-42d, actualApplyResult.getReal(), 0.0);
    assertEquals(5.34741650779348E-42d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link HestonModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf two and two Argument is {@code -1.3185409491966174}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction HestonModel.apply(double)"})
  public void testApply_thenReturnApplyValueOfTwoAndTwoArgumentIs13185409491966174() {
    // Arrange
    HestonModel hestonModel = new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    Complex actualApplyResult = hestonModel.apply(10.0d).apply(Complex.valueOf(2.0d, 2.0d));

    // Assert
    assertEquals(-1.3185409491966174d, actualApplyResult.getArgument(), 0.0);
    assertEquals(-3.902155485046351E-126d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(1.0057639402842391E-126d, actualApplyResult.getReal(), 0.0);
    assertEquals(4.029687150766595E-126d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link HestonModel#apply(double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction HestonModel.apply(double)"})
  public void testApply_whenNaN() {
    // Arrange
    HestonModel hestonModel = new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertSame(Complex.NaN, hestonModel.apply(Double.NaN).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link HestonModel#toString()}
   *   <li>{@link HestonModel#getDiscountCurveForDiscountRate()}
   *   <li>{@link HestonModel#getDiscountCurveForForwardRate()}
   *   <li>{@link HestonModel#getDiscountRate()}
   *   <li>{@link HestonModel#getInitialValue()}
   *   <li>{@link HestonModel#getKappa()}
   *   <li>{@link HestonModel#getReferenceDate()}
   *   <li>{@link HestonModel#getRho()}
   *   <li>{@link HestonModel#getRiskFreeRate()}
   *   <li>{@link HestonModel#getTheta()}
   *   <li>{@link HestonModel#getVolatility()}
   *   <li>{@link HestonModel#getXi()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DiscountCurve HestonModel.getDiscountCurveForDiscountRate()",
    "DiscountCurve HestonModel.getDiscountCurveForForwardRate()",
    "double HestonModel.getDiscountRate()",
    "double HestonModel.getInitialValue()",
    "double HestonModel.getKappa()",
    "LocalDate HestonModel.getReferenceDate()",
    "double HestonModel.getRho()",
    "double HestonModel.getRiskFreeRate()",
    "double HestonModel.getTheta()",
    "double HestonModel.getVolatility()",
    "double HestonModel.getXi()",
    "String HestonModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    HestonModel hestonModel = new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    String actualToStringResult = hestonModel.toString();
    DiscountCurve actualDiscountCurveForDiscountRate =
        hestonModel.getDiscountCurveForDiscountRate();
    DiscountCurve actualDiscountCurveForForwardRate = hestonModel.getDiscountCurveForForwardRate();
    double actualDiscountRate = hestonModel.getDiscountRate();
    double actualInitialValue = hestonModel.getInitialValue();
    double actualKappa = hestonModel.getKappa();
    LocalDate actualReferenceDate = hestonModel.getReferenceDate();
    double actualRho = hestonModel.getRho();
    double actualRiskFreeRate = hestonModel.getRiskFreeRate();
    double actualTheta = hestonModel.getTheta();
    double actualVolatility = hestonModel.getVolatility();

    // Assert
    assertEquals(
        "HestonModel [referenceDate=null, initialValue=10.0, discountCurveForForwardRate=null, riskFreeRate=10.0,"
            + " discountCurveForDiscountRate=null, discountRate=10.0, volatility=10.0, theta=10.0, kappa=10.0, xi=10.0,"
            + " rho=10.0]",
        actualToStringResult);
    assertNull(actualReferenceDate);
    assertNull(actualDiscountCurveForDiscountRate);
    assertNull(actualDiscountCurveForForwardRate);
    assertEquals(10.0d, actualDiscountRate, 0.0);
    assertEquals(10.0d, actualInitialValue, 0.0);
    assertEquals(10.0d, actualKappa, 0.0);
    assertEquals(10.0d, actualRho, 0.0);
    assertEquals(10.0d, actualRiskFreeRate, 0.0);
    assertEquals(10.0d, actualTheta, 0.0);
    assertEquals(10.0d, actualVolatility, 0.0);
    assertEquals(10.0d, hestonModel.getXi(), 0.0);
  }
}
