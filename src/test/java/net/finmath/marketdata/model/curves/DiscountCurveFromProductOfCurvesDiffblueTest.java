package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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

public class DiscountCurveFromProductOfCurvesDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Curve Names}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DiscountCurveFromProductOfCurves#DiscountCurveFromProductOfCurves(String,
   *       LocalDate, String[])}
   *   <li>{@link DiscountCurveFromProductOfCurves#setParameter(double[])}
   *   <li>{@link DiscountCurveFromProductOfCurves#getCloneBuilder()}
   *   <li>{@link DiscountCurveFromProductOfCurves#getParameter()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DiscountCurveFromProductOfCurves.<init>(String, LocalDate, String[])",
    "void DiscountCurveFromProductOfCurves.<init>(String, LocalDate, DiscountCurve[])",
    "CurveBuilder DiscountCurveFromProductOfCurves.getCloneBuilder()",
    "double[] DiscountCurveFromProductOfCurves.getParameter()",
    "void DiscountCurveFromProductOfCurves.setParameter(double[])"
  })
  public void testGettersAndSetters_whenCurveNames() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    DiscountCurveFromProductOfCurves actualDiscountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves("Name", referenceDate, "Curve Names");
    actualDiscountCurveFromProductOfCurves.setParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    CurveBuilder actualCloneBuilder = actualDiscountCurveFromProductOfCurves.getCloneBuilder();
    double[] actualParameter = actualDiscountCurveFromProductOfCurves.getParameter();

    // Assert
    LocalDate referenceDate2 = actualDiscountCurveFromProductOfCurves.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualDiscountCurveFromProductOfCurves.getName());
    assertNull(actualParameter);
    assertNull(actualCloneBuilder);
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(String)} with
   *       {@code Forward Curve Name}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DiscountCurveFromProductOfCurves#DiscountCurveFromProductOfCurves(String,
   *       LocalDate, DiscountCurve[])}
   *   <li>{@link DiscountCurveFromProductOfCurves#setParameter(double[])}
   *   <li>{@link DiscountCurveFromProductOfCurves#getCloneBuilder()}
   *   <li>{@link DiscountCurveFromProductOfCurves#getParameter()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DiscountCurveFromProductOfCurves.<init>(String, LocalDate, String[])",
    "void DiscountCurveFromProductOfCurves.<init>(String, LocalDate, DiscountCurve[])",
    "CurveBuilder DiscountCurveFromProductOfCurves.getCloneBuilder()",
    "double[] DiscountCurveFromProductOfCurves.getParameter()",
    "void DiscountCurveFromProductOfCurves.setParameter(double[])"
  })
  public void testGettersAndSetters_whenDiscountCurveFromForwardCurveWithForwardCurveName() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    DiscountCurveFromProductOfCurves actualDiscountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves(
            "Name", referenceDate, new DiscountCurveFromForwardCurve("Forward Curve Name"));
    actualDiscountCurveFromProductOfCurves.setParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    CurveBuilder actualCloneBuilder = actualDiscountCurveFromProductOfCurves.getCloneBuilder();
    double[] actualParameter = actualDiscountCurveFromProductOfCurves.getParameter();

    // Assert
    LocalDate referenceDate2 = actualDiscountCurveFromProductOfCurves.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualDiscountCurveFromProductOfCurves.getName());
    assertNull(actualParameter);
    assertNull(actualCloneBuilder);
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link DiscountCurveFromProductOfCurves#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromProductOfCurves#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromProductOfCurves.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity() {
    // Arrange
    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves("Name", LocalDate.of(1970, 1, 1), "Curve Names");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> discountCurveFromProductOfCurves.getDiscountFactor(10.0d));
  }

  /**
   * Test {@link DiscountCurveFromProductOfCurves#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromProductOfCurves#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromProductOfCurves.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves(
            "Name",
            LocalDate.of(1970, 1, 1),
            "This object requires that a reference to an AnalyticModel is passed to a call this method.");

    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves2 =
        new DiscountCurveFromProductOfCurves(
            "This object requires that a reference to an AnalyticModel is passed to a call this method.",
            referenceDate,
            discountCurveFromProductOfCurves);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> discountCurveFromProductOfCurves2.getDiscountFactor(10.0d));
  }

  /**
   * Test {@link DiscountCurveFromProductOfCurves#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromProductOfCurves#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromProductOfCurves.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveInterpolation createDiscountCurveFromDiscountFactorsResult =
        DiscountCurveInterpolation.createDiscountCurveFromDiscountFactors(
            "Name",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d});

    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves(
            "This object requires that a reference to an AnalyticModel is passed to a call this method.",
            referenceDate,
            createDiscountCurveFromDiscountFactorsResult);

    // Act and Assert
    assertEquals(
        10.000000000000002d, discountCurveFromProductOfCurves.getDiscountFactor(10.0d), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromProductOfCurves#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromProductOfCurves#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromProductOfCurves.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    DiscountCurveInterpolation createDiscountCurveFromDiscountFactorsResult =
        DiscountCurveInterpolation.createDiscountCurveFromDiscountFactors(
            "Name",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d});

    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves(
            "Name", referenceDate2, createDiscountCurveFromDiscountFactorsResult);

    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves2 =
        new DiscountCurveFromProductOfCurves(
            "This object requires that a reference to an AnalyticModel is passed to a call this method.",
            referenceDate,
            discountCurveFromProductOfCurves);

    // Act and Assert
    assertEquals(
        10.000000000000002d, discountCurveFromProductOfCurves2.getDiscountFactor(10.0d), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromProductOfCurves#getDiscountFactor(AnalyticModel, double)} with
   * {@code model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then return {@code 10.000000000000002}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromProductOfCurves#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DiscountCurveFromProductOfCurves.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenReturn10000000000000002() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveInterpolation createDiscountCurveFromDiscountFactorsResult =
        DiscountCurveInterpolation.createDiscountCurveFromDiscountFactors(
            "Name",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d});

    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves(
            "Name", referenceDate, createDiscountCurveFromDiscountFactorsResult);

    // Act and Assert
    assertEquals(
        10.000000000000002d,
        discountCurveFromProductOfCurves.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveFromProductOfCurves#getDiscountFactor(AnalyticModel, double)} with
   * {@code model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromProductOfCurves#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DiscountCurveFromProductOfCurves.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenReturnOne() {
    // Arrange
    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves("Name", LocalDate.of(1970, 1, 1), new String[] {});

    // Act and Assert
    assertEquals(
        1.0d,
        discountCurveFromProductOfCurves.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveFromProductOfCurves#getDiscountFactor(AnalyticModel, double)} with
   * {@code model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromProductOfCurves#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DiscountCurveFromProductOfCurves.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenReturnOne2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves("Name", LocalDate.of(1970, 1, 1), new String[] {});

    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves2 =
        new DiscountCurveFromProductOfCurves(
            "Name", referenceDate, discountCurveFromProductOfCurves);

    // Act and Assert
    assertEquals(
        1.0d,
        discountCurveFromProductOfCurves2.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveFromProductOfCurves#getDiscountFactor(AnalyticModel, double)} with
   * {@code model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromProductOfCurves#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DiscountCurveFromProductOfCurves.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenThrowIllegalArgumentException() {
    // Arrange
    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves("Name", LocalDate.of(1970, 1, 1), "Curve Names");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> discountCurveFromProductOfCurves.getDiscountFactor(null, 10.0d));
  }

  /**
   * Test {@link DiscountCurveFromProductOfCurves#getValue(AnalyticModel, double)} with {@code
   * model}, {@code time}.
   *
   * <ul>
   *   <li>Then return {@code 10.000000000000002}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromProductOfCurves#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromProductOfCurves.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturn10000000000000002() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveInterpolation createDiscountCurveFromDiscountFactorsResult =
        DiscountCurveInterpolation.createDiscountCurveFromDiscountFactors(
            "Name",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d});

    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves(
            "Name", referenceDate, createDiscountCurveFromDiscountFactorsResult);

    // Act and Assert
    assertEquals(
        10.000000000000002d,
        discountCurveFromProductOfCurves.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveFromProductOfCurves#getValue(AnalyticModel, double)} with {@code
   * model}, {@code time}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromProductOfCurves#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromProductOfCurves.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturnOne() {
    // Arrange
    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves("Name", LocalDate.of(1970, 1, 1), new String[] {});

    // Act and Assert
    assertEquals(
        1.0d,
        discountCurveFromProductOfCurves.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveFromProductOfCurves#getValue(AnalyticModel, double)} with {@code
   * model}, {@code time}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromProductOfCurves#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromProductOfCurves.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturnOne2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves("Name", LocalDate.of(1970, 1, 1), new String[] {});

    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves2 =
        new DiscountCurveFromProductOfCurves(
            "Name", referenceDate, discountCurveFromProductOfCurves);

    // Act and Assert
    assertEquals(
        1.0d,
        discountCurveFromProductOfCurves2.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveFromProductOfCurves#getValue(AnalyticModel, double)} with {@code
   * model}, {@code time}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromProductOfCurves#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromProductOfCurves.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_whenNull_thenThrowIllegalArgumentException() {
    // Arrange
    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves("Name", LocalDate.of(1970, 1, 1), "Curve Names");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> discountCurveFromProductOfCurves.getValue(null, 10.0d));
  }
}
