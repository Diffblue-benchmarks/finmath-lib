package net.finmath.fouriermethod.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexField;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BatesModelDiffblueTest {
  /**
   * Test {@link BatesModel#BatesModel(double, double, double, double, double, double, double,
   * double, double, double, double)}.
   *
   * <p>Method under test: {@link BatesModel#BatesModel(double, double, double, double, double,
   * double, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BatesModel.<init>(double, double, double, double, double, double, double, double, double, double, double)"
  })
  public void testNewBatesModel() {
    // Arrange and Act
    BatesModel actualBatesModel =
        new BatesModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d);

    // Assert
    assertNull(actualBatesModel.getReferenceDate());
    assertEquals(0.5d, actualBatesModel.getDelta(), 0.0);
    assertEquals(1, actualBatesModel.getNumberOfFactors());
    assertEquals(10.0d, actualBatesModel.getDiscountRate(), 0.0);
    assertEquals(10.0d, actualBatesModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualBatesModel.getK(), 0.0);
    assertEquals(10.0d, actualBatesModel.getRiskFreeRate(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualBatesModel.getAlpha(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualBatesModel.getBeta(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualBatesModel.getRho(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualBatesModel.getSigma(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualBatesModel.getVolatility(), 0.0);
    assertArrayEquals(new double[] {10.0d, 10.0d}, actualBatesModel.getLambda(), 0.0);
  }

  /**
   * Test {@link BatesModel#BatesModel(double, double, double, double[], double[], double[],
   * double[], double[], double[], double, double)}.
   *
   * <p>Method under test: {@link BatesModel#BatesModel(double, double, double, double[], double[],
   * double[], double[], double[], double[], double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BatesModel.<init>(double, double, double, double[], double[], double[], double[], double[], double[], double, double)"
  })
  public void testNewBatesModel2() {
    // Arrange and Act
    BatesModel actualBatesModel =
        new BatesModel(
            10.0d,
            10.0d,
            10.0d,
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            10.0d,
            0.5d);

    // Assert
    assertNull(actualBatesModel.getReferenceDate());
    assertEquals(0.5d, actualBatesModel.getDelta(), 0.0);
    assertEquals(10.0d, actualBatesModel.getDiscountRate(), 0.0);
    assertEquals(10.0d, actualBatesModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualBatesModel.getK(), 0.0);
    assertEquals(10.0d, actualBatesModel.getRiskFreeRate(), 0.0);
    assertEquals(4, actualBatesModel.getNumberOfFactors());
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getAlpha(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getBeta(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getLambda(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getRho(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getSigma(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getVolatility(), 0.0);
  }

  /**
   * Test {@link BatesModel#BatesModel(double, DiscountCurve, DiscountCurve, double[], double[],
   * double[], double[], double[], double[], double, double)}.
   *
   * <p>Method under test: {@link BatesModel#BatesModel(double, DiscountCurve, DiscountCurve,
   * double[], double[], double[], double[], double[], double[], double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BatesModel.<init>(double, DiscountCurve, DiscountCurve, double[], double[], double[], double[], double[], double[], double, double)"
  })
  public void testNewBatesModel3() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    BatesModel actualBatesModel =
        new BatesModel(
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            10.0d,
            0.5d);

    // Assert
    assertNull(actualBatesModel.getReferenceDate());
    assertEquals(0.5d, actualBatesModel.getDelta(), 0.0);
    assertEquals(10.0d, actualBatesModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualBatesModel.getK(), 0.0);
    assertEquals(4, actualBatesModel.getNumberOfFactors());
    assertEquals(Double.NaN, actualBatesModel.getDiscountRate(), 0.0);
    assertEquals(Double.NaN, actualBatesModel.getRiskFreeRate(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getAlpha(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getBeta(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getLambda(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getRho(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getSigma(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getVolatility(), 0.0);
  }

  /**
   * Test {@link BatesModel#BatesModel(LocalDate, double, DiscountCurve, DiscountCurve, double[],
   * double[], double[], double[], double[], double[], double, double)}.
   *
   * <p>Method under test: {@link BatesModel#BatesModel(LocalDate, double, DiscountCurve,
   * DiscountCurve, double[], double[], double[], double[], double[], double[], double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BatesModel.<init>(LocalDate, double, DiscountCurve, DiscountCurve, double[], double[], double[], double[], double[], double[], double, double)"
  })
  public void testNewBatesModel4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    BatesModel actualBatesModel =
        new BatesModel(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            new double[] {10.0d, Double.NaN, 10.0d, Double.NaN},
            10.0d,
            0.5d);

    // Assert
    assertEquals(0.5d, actualBatesModel.getDelta(), 0.0);
    assertEquals(10.0d, actualBatesModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualBatesModel.getK(), 0.0);
    assertEquals(4, actualBatesModel.getNumberOfFactors());
    assertEquals(Double.NaN, actualBatesModel.getDiscountRate(), 0.0);
    assertEquals(Double.NaN, actualBatesModel.getRiskFreeRate(), 0.0);
    assertSame(referenceDate, actualBatesModel.getReferenceDate());
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getAlpha(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getBeta(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getLambda(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getRho(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getSigma(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, Double.NaN, 10.0d, Double.NaN}, actualBatesModel.getVolatility(), 0.0);
  }

  /**
   * Test {@link BatesModel#apply(double)}.
   *
   * <p>Method under test: {@link BatesModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction BatesModel.apply(double)"})
  public void testApply() {
    // Arrange
    BatesModel batesModel =
        new BatesModel(-1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d);

    // Act and Assert
    assertSame(Complex.NaN, batesModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link BatesModel#apply(double)}.
   *
   * <p>Method under test: {@link BatesModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction BatesModel.apply(double)"})
  public void testApply2() {
    // Arrange
    BatesModel batesModel =
        new BatesModel(
            10.0d, 10.0d, Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d);

    // Act and Assert
    assertSame(Complex.NaN, batesModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link BatesModel#apply(double)}.
   *
   * <p>Method under test: {@link BatesModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction BatesModel.apply(double)"})
  public void testApply3() {
    // Arrange, Act and Assert
    assertSame(
        Complex.NaN,
        new BatesModel(
                10.0d, 10.0d, 10.0d, Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d)
            .apply(10.0d)
            .apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link BatesModel#apply(double)}.
   *
   * <p>Method under test: {@link BatesModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction BatesModel.apply(double)"})
  public void testApply4() {
    // Arrange
    BatesModel batesModel =
        new BatesModel(
            10.0d, 10.0d, 10.0d, 10.0d, Double.NaN, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d);

    // Act and Assert
    assertSame(Complex.NaN, batesModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link BatesModel#apply(double)}.
   *
   * <p>Method under test: {@link BatesModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction BatesModel.apply(double)"})
  public void testApply5() {
    // Arrange
    BatesModel batesModel =
        new BatesModel(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Double.NaN, 10.0d, 0.5d);

    // Act and Assert
    assertSame(Complex.NaN, batesModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link BatesModel#apply(double)}.
   *
   * <p>Method under test: {@link BatesModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction BatesModel.apply(double)"})
  public void testApply6() {
    // Arrange
    BatesModel batesModel =
        new BatesModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, -1.0d, 0.5d);

    // Act and Assert
    assertSame(Complex.NaN, batesModel.apply(10.0d).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link BatesModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf ten Argument is {@code -3.036129332294285}.
   * </ul>
   *
   * <p>Method under test: {@link BatesModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction BatesModel.apply(double)"})
  public void testApply_thenReturnApplyValueOfTenArgumentIs3036129332294285() {
    // Arrange
    BatesModel batesModel =
        new BatesModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d);

    // Act
    Complex actualApplyResult = batesModel.apply(10.0d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(-3.036129332294285d, actualApplyResult.getArgument(), 0.0);
    assertEquals(-7.202892960721857E-126d, actualApplyResult.getReal(), 0.0);
    assertEquals(-7.62469973427594E-127d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(7.243136576373191E-126d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link BatesModel#apply(double)}.
   *
   * <ul>
   *   <li>Then return apply valueOf ten Real is {@code -1.7300495997435424E-237}.
   * </ul>
   *
   * <p>Method under test: {@link BatesModel#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.fouriermethod.CharacteristicFunction BatesModel.apply(double)"})
  public void testApply_thenReturnApplyValueOfTenRealIs17300495997435424e237() {
    // Arrange
    BatesModel batesModel =
        new BatesModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d, 10.0d, 10.0d, 10.0d, 0.5d);

    // Act
    Complex actualApplyResult = batesModel.apply(10.0d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(-1.7300495997435424E-237d, actualApplyResult.getReal(), 0.0);
    assertEquals(1.7609847387292215E-237d, actualApplyResult.abs(), 0.0);
    assertEquals(2.9538767517227558d, actualApplyResult.getArgument(), 0.0);
    assertEquals(3.286268894421655E-238d, actualApplyResult.getImaginary(), 0.0);
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
   *   <li>{@link BatesModel#toString()}
   *   <li>{@link BatesModel#getAlpha()}
   *   <li>{@link BatesModel#getBeta()}
   *   <li>{@link BatesModel#getDelta()}
   *   <li>{@link BatesModel#getDiscountRate()}
   *   <li>{@link BatesModel#getInitialValue()}
   *   <li>{@link BatesModel#getK()}
   *   <li>{@link BatesModel#getLambda()}
   *   <li>{@link BatesModel#getNumberOfFactors()}
   *   <li>{@link BatesModel#getReferenceDate()}
   *   <li>{@link BatesModel#getRho()}
   *   <li>{@link BatesModel#getRiskFreeRate()}
   *   <li>{@link BatesModel#getSigma()}
   *   <li>{@link BatesModel#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] BatesModel.getAlpha()",
    "double[] BatesModel.getBeta()",
    "double BatesModel.getDelta()",
    "double BatesModel.getDiscountRate()",
    "double BatesModel.getInitialValue()",
    "double BatesModel.getK()",
    "double[] BatesModel.getLambda()",
    "int BatesModel.getNumberOfFactors()",
    "LocalDate BatesModel.getReferenceDate()",
    "double[] BatesModel.getRho()",
    "double BatesModel.getRiskFreeRate()",
    "double[] BatesModel.getSigma()",
    "double[] BatesModel.getVolatility()",
    "String BatesModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    BatesModel batesModel =
        new BatesModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d);

    // Act
    String actualToStringResult = batesModel.toString();
    double[] actualAlpha = batesModel.getAlpha();
    double[] actualBeta = batesModel.getBeta();
    double actualDelta = batesModel.getDelta();
    double actualDiscountRate = batesModel.getDiscountRate();
    double actualInitialValue = batesModel.getInitialValue();
    double actualK = batesModel.getK();
    double[] actualLambda = batesModel.getLambda();
    int actualNumberOfFactors = batesModel.getNumberOfFactors();
    LocalDate actualReferenceDate = batesModel.getReferenceDate();
    double[] actualRho = batesModel.getRho();
    double actualRiskFreeRate = batesModel.getRiskFreeRate();
    double[] actualSigma = batesModel.getSigma();

    // Assert
    assertEquals(
        "BatesModel [initialValue=10.0, riskFreeRate=10.0, volatility=[10.0], discountRate=10.0, alpha=[10.0],"
            + " beta=[10.0], sigma=[10.0], rho=[10.0], lambda=[10.0, 10.0], k=10.0, delta=0.5, numberOfFactors=1]",
        actualToStringResult);
    assertNull(actualReferenceDate);
    assertEquals(0.5d, actualDelta, 0.0);
    assertEquals(1, actualNumberOfFactors);
    assertEquals(10.0d, actualDiscountRate, 0.0);
    assertEquals(10.0d, actualInitialValue, 0.0);
    assertEquals(10.0d, actualK, 0.0);
    assertEquals(10.0d, actualRiskFreeRate, 0.0);
    assertArrayEquals(new double[] {10.0d}, actualAlpha, 0.0);
    assertArrayEquals(new double[] {10.0d}, actualBeta, 0.0);
    assertArrayEquals(new double[] {10.0d}, actualRho, 0.0);
    assertArrayEquals(new double[] {10.0d}, actualSigma, 0.0);
    assertArrayEquals(new double[] {10.0d}, batesModel.getVolatility(), 0.0);
    assertArrayEquals(new double[] {10.0d, 10.0d}, actualLambda, 0.0);
  }
}
