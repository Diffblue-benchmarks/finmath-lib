package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DiscountCurveNelsonSiegelSvenssonDiffblueTest {
  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#DiscountCurveNelsonSiegelSvensson(String,
   * LocalDate, double[], double)}.
   *
   * <p>Method under test: {@link
   * DiscountCurveNelsonSiegelSvensson#DiscountCurveNelsonSiegelSvensson(String, LocalDate,
   * double[], double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DiscountCurveNelsonSiegelSvensson.<init>(String, LocalDate, double[], double)"
  })
  public void testNewDiscountCurveNelsonSiegelSvensson() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    DiscountCurveNelsonSiegelSvensson actualDiscountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name", referenceDate, new double[] {10.0d, 1.0d, 10.0d, 1.0d}, 10.0d);

    // Assert
    assertEquals("Name", actualDiscountCurveNelsonSiegelSvensson.getName());
    assertEquals(10.0d, actualDiscountCurveNelsonSiegelSvensson.getTimeScaling(), 0.0);
    assertSame(referenceDate, actualDiscountCurveNelsonSiegelSvensson.getReferenceDate());
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d, 1.0d},
        actualDiscountCurveNelsonSiegelSvensson.getParameter(),
        0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveNelsonSiegelSvensson.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenReturnOne() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d},
            0.0d);

    // Act and Assert
    assertEquals(1.0d, discountCurveNelsonSiegelSvensson.getDiscountFactor(10.0d), 0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveNelsonSiegelSvensson.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenReturnZero() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(0.0d, discountCurveNelsonSiegelSvensson.getDiscountFactor(10.0d), 0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveNelsonSiegelSvensson.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenReturnZero2() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 0.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(0.0d, discountCurveNelsonSiegelSvensson.getDiscountFactor(10.0d), 0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveNelsonSiegelSvensson.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenReturnZero3() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 0.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(0.0d, discountCurveNelsonSiegelSvensson.getDiscountFactor(10.0d), 0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(AnalyticModel, double)} with
   * {@code model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DiscountCurveNelsonSiegelSvensson.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenReturnOne() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d},
            0.0d);

    // Act and Assert
    assertEquals(
        1.0d,
        discountCurveNelsonSiegelSvensson.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(AnalyticModel, double)} with
   * {@code model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DiscountCurveNelsonSiegelSvensson.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenReturnZero() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        discountCurveNelsonSiegelSvensson.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(AnalyticModel, double)} with
   * {@code model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DiscountCurveNelsonSiegelSvensson.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenReturnZero2() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 0.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        discountCurveNelsonSiegelSvensson.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(AnalyticModel, double)} with
   * {@code model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DiscountCurveNelsonSiegelSvensson.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenReturnZero3() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 0.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        discountCurveNelsonSiegelSvensson.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getValue(AnalyticModel, double)} with {@code
   * model}, {@code time}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveNelsonSiegelSvensson.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturnOne() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d},
            0.0d);

    // Act and Assert
    assertEquals(
        1.0d,
        discountCurveNelsonSiegelSvensson.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getValue(AnalyticModel, double)} with {@code
   * model}, {@code time}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveNelsonSiegelSvensson.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturnZero() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        discountCurveNelsonSiegelSvensson.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getValue(AnalyticModel, double)} with {@code
   * model}, {@code time}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveNelsonSiegelSvensson.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturnZero2() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 0.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        discountCurveNelsonSiegelSvensson.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getValue(AnalyticModel, double)} with {@code
   * model}, {@code time}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveNelsonSiegelSvensson.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturnZero3() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 0.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        discountCurveNelsonSiegelSvensson.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getZeroRate(double)}.
   *
   * <ul>
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getZeroRate(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveNelsonSiegelSvensson.getZeroRate(double)"})
  public void testGetZeroRate_thenReturn00() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d},
            0.0d);

    // Act and Assert
    assertEquals(-0.0d, discountCurveNelsonSiegelSvensson.getZeroRate(10.0d), 0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getZeroRate(double)}.
   *
   * <ul>
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getZeroRate(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveNelsonSiegelSvensson.getZeroRate(double)"})
  public void testGetZeroRate_thenReturn002() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 10.0d, 10.0d, 0.0d},
            0.0d);

    // Act and Assert
    assertEquals(-0.0d, discountCurveNelsonSiegelSvensson.getZeroRate(10.0d), 0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getZeroRate(double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getZeroRate(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveNelsonSiegelSvensson.getZeroRate(double)"})
  public void testGetZeroRate_thenReturnPositive_infinity() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        Double.POSITIVE_INFINITY, discountCurveNelsonSiegelSvensson.getZeroRate(10.0d), 0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getZeroRate(double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getZeroRate(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveNelsonSiegelSvensson.getZeroRate(double)"})
  public void testGetZeroRate_thenReturnPositive_infinity2() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.0d, 10.0d, 0.0d, 0.0d, 0.0d, 10.0d, 0.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        Double.POSITIVE_INFINITY, discountCurveNelsonSiegelSvensson.getZeroRate(10.0d), 0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getZeroRate(double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getZeroRate(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveNelsonSiegelSvensson.getZeroRate(double)"})
  public void testGetZeroRate_thenReturnPositive_infinity3() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 10.0d, 10.0d, 0.0d},
            10.0d);

    // Act and Assert
    assertEquals(
        Double.POSITIVE_INFINITY, discountCurveNelsonSiegelSvensson.getZeroRate(10.0d), 0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getZeroRate(double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code 99.92007221631401}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getZeroRate(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveNelsonSiegelSvensson.getZeroRate(double)"})
  public void testGetZeroRate_whenZero_thenReturn9992007221631401() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d},
            10.0d);

    // Act and Assert
    assertEquals(99.92007221631401d, discountCurveNelsonSiegelSvensson.getZeroRate(0.0d), 0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getZeroRates(double[])}.
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getZeroRates(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DiscountCurveNelsonSiegelSvensson.getZeroRates(double[])"})
  public void testGetZeroRates() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d},
            10.0d);

    // Act
    double[] actualZeroRates =
        discountCurveNelsonSiegelSvensson.getZeroRates(new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertArrayEquals(
        new double[] {
          Double.POSITIVE_INFINITY,
          126.42411176571153d,
          Double.POSITIVE_INFINITY,
          126.42411176571153d
        },
        actualZeroRates,
        0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getZeroRates(double[])}.
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getZeroRates(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DiscountCurveNelsonSiegelSvensson.getZeroRates(double[])"})
  public void testGetZeroRates2() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 10.0d, 10.0d, 0.0d},
            10.0d);

    // Act
    double[] actualZeroRates =
        discountCurveNelsonSiegelSvensson.getZeroRates(new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertArrayEquals(
        new double[] {
          Double.POSITIVE_INFINITY,
          126.42411176571153d,
          Double.POSITIVE_INFINITY,
          126.42411176571153d
        },
        actualZeroRates,
        0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getZeroRates(double[])}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@code -0.0} and {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getZeroRates(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DiscountCurveNelsonSiegelSvensson.getZeroRates(double[])"})
  public void testGetZeroRates_thenReturnArrayOfDoubleWith00And00() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d},
            0.0d);

    // Act
    double[] actualZeroRates =
        discountCurveNelsonSiegelSvensson.getZeroRates(new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertArrayEquals(new double[] {-0.0d, -0.0d, -0.0d, -0.0d}, actualZeroRates, 0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getZeroRates(double[])}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@code -0.0} and {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getZeroRates(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DiscountCurveNelsonSiegelSvensson.getZeroRates(double[])"})
  public void testGetZeroRates_thenReturnArrayOfDoubleWith00And002() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 10.0d, 10.0d, 0.0d},
            0.0d);

    // Act
    double[] actualZeroRates =
        discountCurveNelsonSiegelSvensson.getZeroRates(new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertArrayEquals(new double[] {-0.0d, -0.0d, -0.0d, -0.0d}, actualZeroRates, 0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getZeroRates(double[])}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@link Double#POSITIVE_INFINITY} and {@code
   *       99.92007221631401}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getZeroRates(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DiscountCurveNelsonSiegelSvensson.getZeroRates(double[])"})
  public void testGetZeroRates_thenReturnArrayOfDoubleWithPositive_infinityAnd9992007221631401() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d},
            10.0d);

    // Act
    double[] actualZeroRates =
        discountCurveNelsonSiegelSvensson.getZeroRates(
            new double[] {10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d});

    // Assert
    assertArrayEquals(
        new double[] {
          Double.POSITIVE_INFINITY,
          99.92007221631401d,
          Double.POSITIVE_INFINITY,
          99.92007221631401d,
          Double.POSITIVE_INFINITY,
          99.92007221631401d,
          Double.POSITIVE_INFINITY,
          99.92007221631401d
        },
        actualZeroRates,
        0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getZeroRates(double[])}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@link Double#POSITIVE_INFINITY} and one
   *       hundred.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getZeroRates(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DiscountCurveNelsonSiegelSvensson.getZeroRates(double[])"})
  public void testGetZeroRates_thenReturnArrayOfDoubleWithPositive_infinityAndOneHundred() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name",
            LocalDate.of(1970, 1, 1),
            new double[] {10.0d, 0.0d, 10.0d, 0.0d, 0.0d, 0.0d, 10.0d, 0.0d},
            10.0d);

    // Act
    double[] actualZeroRates =
        discountCurveNelsonSiegelSvensson.getZeroRates(new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY, 100.0d, Double.POSITIVE_INFINITY, 100.0d},
        actualZeroRates,
        0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getZeroRates(double[])}.
   *
   * <ul>
   *   <li>When empty array of {@code double}.
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getZeroRates(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DiscountCurveNelsonSiegelSvensson.getZeroRates(double[])"})
  public void testGetZeroRates_whenEmptyArrayOfDouble_thenReturnEmptyArrayOfDouble() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name", LocalDate.of(1970, 1, 1), new double[] {10.0d, 1.0d, 10.0d, 1.0d}, 10.0d);

    // Act
    double[] actualZeroRates = discountCurveNelsonSiegelSvensson.getZeroRates(new double[] {});

    // Assert
    assertArrayEquals(new double[] {}, actualZeroRates, 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DiscountCurveNelsonSiegelSvensson#toString()}
   *   <li>{@link DiscountCurveNelsonSiegelSvensson#getParameter()}
   *   <li>{@link DiscountCurveNelsonSiegelSvensson#getTimeScaling()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] DiscountCurveNelsonSiegelSvensson.getParameter()",
    "double DiscountCurveNelsonSiegelSvensson.getTimeScaling()",
    "String DiscountCurveNelsonSiegelSvensson.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name", LocalDate.of(1970, 1, 1), new double[] {10.0d, 1.0d, 10.0d, 1.0d}, 10.0d);

    // Act
    String actualToStringResult = discountCurveNelsonSiegelSvensson.toString();
    double[] actualParameter = discountCurveNelsonSiegelSvensson.getParameter();

    // Assert
    assertEquals(
        "DiscountCurveNelsonSiegelSvensson [timeScaling=10.0, parameter=[10.0, 1.0, 10.0, 1.0], toString()"
            + "=AbstractCurve [name=Name, referenceDate=1970-01-01]]",
        actualToStringResult);
    assertEquals(10.0d, discountCurveNelsonSiegelSvensson.getTimeScaling(), 0.0);
    assertArrayEquals(new double[] {10.0d, 1.0d, 10.0d, 1.0d}, actualParameter, 0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#setParameter(double[])}.
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#setParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DiscountCurveNelsonSiegelSvensson.setParameter(double[])"})
  public void testSetParameter() {
    // Arrange
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name", LocalDate.of(1970, 1, 1), new double[] {10.0d, 1.0d, 10.0d, 1.0d}, 10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            discountCurveNelsonSiegelSvensson.setParameter(
                new double[] {10.0d, 1.0d, 10.0d, 1.0d}));
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#clone()}.
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DiscountCurveNelsonSiegelSvensson DiscountCurveNelsonSiegelSvensson.clone()"})
  public void testClone() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name", referenceDate, new double[] {10.0d, 1.0d, 10.0d, 1.0d}, 10.0d);

    // Act
    DiscountCurveNelsonSiegelSvensson actualCloneResult = discountCurveNelsonSiegelSvensson.clone();

    // Assert
    LocalDate referenceDate2 = actualCloneResult.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneResult.getName());
    assertEquals(10.0d, actualCloneResult.getTimeScaling(), 0.0);
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d, 1.0d}, actualCloneResult.getParameter(), 0.0);
  }

  /**
   * Test {@link DiscountCurveNelsonSiegelSvensson#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link DiscountCurveNelsonSiegelSvensson#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DiscountCurveNelsonSiegelSvensson DiscountCurveNelsonSiegelSvensson.getCloneForParameter(double[])"
  })
  public void testGetCloneForParameter() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "Name", referenceDate, new double[] {10.0d, 1.0d, 10.0d, 1.0d}, 10.0d);

    // Act
    DiscountCurveNelsonSiegelSvensson actualCloneForParameter =
        discountCurveNelsonSiegelSvensson.getCloneForParameter(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    LocalDate referenceDate2 = actualCloneForParameter.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneForParameter.getName());
    assertEquals(10.0d, actualCloneForParameter.getTimeScaling(), 0.0);
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d, 1.0d}, actualCloneForParameter.getParameter(), 0.0);
  }
}
