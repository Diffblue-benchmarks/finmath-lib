package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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

public class DiscountCurveFromForwardCurveDiffblueTest {
  /**
   * Test {@link DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(String)}.
   *
   * <p>Method under test: {@link
   * DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DiscountCurveFromForwardCurve.<init>(String)"})
  public void testNewDiscountCurveFromForwardCurve() {
    // Arrange and Act
    DiscountCurveFromForwardCurve actualDiscountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Assert
    assertEquals(
        "DiscountCurveFromForwardCurve(Forward Curve Name)",
        actualDiscountCurveFromForwardCurve.getName());
    assertNull(actualDiscountCurveFromForwardCurve.getParameter());
    assertNull(actualDiscountCurveFromForwardCurve.getReferenceDate());
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(String, double)}.
   *
   * <p>Method under test: {@link
   * DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DiscountCurveFromForwardCurve.<init>(String, double)"})
  public void testNewDiscountCurveFromForwardCurve2() {
    // Arrange and Act
    DiscountCurveFromForwardCurve actualDiscountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name", 10.0d);

    // Assert
    assertEquals(
        "DiscountCurveFromForwardCurve(Forward Curve Name)",
        actualDiscountCurveFromForwardCurve.getName());
    assertNull(actualDiscountCurveFromForwardCurve.getParameter());
    assertNull(actualDiscountCurveFromForwardCurve.getReferenceDate());
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(ForwardCurve)}.
   *
   * <p>Method under test: {@link
   * DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(ForwardCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DiscountCurveFromForwardCurve.<init>(ForwardCurve)"})
  public void testNewDiscountCurveFromForwardCurve3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", referenceDate, "Payment Offset Code");

    // Act
    DiscountCurveFromForwardCurve actualDiscountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Assert
    LocalDate referenceDate2 = actualDiscountCurveFromForwardCurve.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals(
        "DiscountCurveFromForwardCurveForwardCurveFromDiscountCurve(3,Payment Offset Code))",
        actualDiscountCurveFromForwardCurve.getName());
    assertNull(actualDiscountCurveFromForwardCurve.getParameter());
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(ForwardCurve, double)}.
   *
   * <p>Method under test: {@link
   * DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DiscountCurveFromForwardCurve.<init>(ForwardCurve, double)"})
  public void testNewDiscountCurveFromForwardCurve4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", referenceDate, "Payment Offset Code");

    // Act
    DiscountCurveFromForwardCurve actualDiscountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve, 10.0d);

    // Assert
    LocalDate referenceDate2 = actualDiscountCurveFromForwardCurve.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals(
        "DiscountCurveFromForwardCurveForwardCurveFromDiscountCurve(3,Payment Offset Code))",
        actualDiscountCurveFromForwardCurve.getName());
    assertNull(actualDiscountCurveFromForwardCurve.getParameter());
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then return {@code 2755.7319223985905}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenReturn27557319223985905() {
    // Arrange, Act and Assert
    assertEquals(
        2755.7319223985905d,
        new DiscountCurveFromForwardCurve(
                ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
                    "(?<=[0-9|\\.])(?=[A-Z|a-z])",
                    new double[] {1.0d, 10.0d, 1.0d, 10.0d},
                    new double[] {1.0d, 10.0d, 1.0d, 10.0d},
                    1.0d))
            .getDiscountFactor(10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new DiscountCurveFromForwardCurve("Forward Curve Name").getDiscountFactor(10.0d));
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenThrowRuntimeException() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "");

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d));
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then return {@code 2755.7319223985905}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenReturn27557319223985905() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(
            ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
                "(?<=[0-9|\\.])(?=[A-Z|a-z])",
                new double[] {1.0d, 10.0d, 1.0d, 10.0d},
                new double[] {1.0d, 10.0d, 1.0d, 10.0d},
                1.0d));

    // Act and Assert
    assertEquals(
        2755.7319223985905d,
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenThrowIllegalArgumentException() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            discountCurveFromForwardCurve.getDiscountFactor(
                new AnalyticModelFromCurvesAndVols(), 10.0d));
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenThrowRuntimeException() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "");
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            discountCurveFromForwardCurve.getDiscountFactor(
                new AnalyticModelFromCurvesAndVols(), 10.0d));
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_whenNull() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new DiscountCurveFromForwardCurve("Forward Curve Name").getDiscountFactor(null, 0.0d));
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Then return {@code 2755.7319223985905}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturn27557319223985905() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(
            ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
                "(?<=[0-9|\\.])(?=[A-Z|a-z])",
                new double[] {1.0d, 10.0d, 1.0d, 10.0d},
                new double[] {1.0d, 10.0d, 1.0d, 10.0d},
                1.0d));

    // Act and Assert
    assertEquals(
        2755.7319223985905d,
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenThrowIllegalArgumentException() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d));
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenThrowRuntimeException() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "");
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d));
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new DiscountCurveFromForwardCurve("Forward Curve Name").getValue(null, 0.0d));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DiscountCurveFromForwardCurve#setParameter(double[])}
   *   <li>{@link DiscountCurveFromForwardCurve#getParameter()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] DiscountCurveFromForwardCurve.getParameter()",
    "void DiscountCurveFromForwardCurve.setParameter(double[])"
  })
  public void testGettersAndSetters() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    discountCurveFromForwardCurve.setParameter(new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertNull(discountCurveFromForwardCurve.getParameter());
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getCloneBuilder()}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getCloneBuilder()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.marketdata.model.curves.CurveBuilder DiscountCurveFromForwardCurve.getCloneBuilder()"
  })
  public void testGetCloneBuilder() throws CloneNotSupportedException {
    // Arrange, Act and Assert
    assertThrows(
        CloneNotSupportedException.class,
        () -> new DiscountCurveFromForwardCurve("Forward Curve Name").getCloneBuilder());
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#equals(Object)}, and {@link
   * DiscountCurveFromForwardCurve#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DiscountCurveFromForwardCurve#equals(Object)}
   *   <li>{@link DiscountCurveFromForwardCurve#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DiscountCurveFromForwardCurve.equals(Object)",
    "int DiscountCurveFromForwardCurve.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve2 =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertEquals(discountCurveFromForwardCurve, discountCurveFromForwardCurve2);
    assertEquals(
        discountCurveFromForwardCurve.hashCode(), discountCurveFromForwardCurve2.hashCode());
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#equals(Object)}, and {@link
   * DiscountCurveFromForwardCurve#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DiscountCurveFromForwardCurve#equals(Object)}
   *   <li>{@link DiscountCurveFromForwardCurve#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DiscountCurveFromForwardCurve.equals(Object)",
    "int DiscountCurveFromForwardCurve.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve((String) null);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve2 =
        new DiscountCurveFromForwardCurve((String) null);

    // Act and Assert
    assertEquals(discountCurveFromForwardCurve, discountCurveFromForwardCurve2);
    assertEquals(
        discountCurveFromForwardCurve.hashCode(), discountCurveFromForwardCurve2.hashCode());
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#equals(Object)}, and {@link
   * DiscountCurveFromForwardCurve#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DiscountCurveFromForwardCurve#equals(Object)}
   *   <li>{@link DiscountCurveFromForwardCurve#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DiscountCurveFromForwardCurve.equals(Object)",
    "int DiscountCurveFromForwardCurve.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertEquals(discountCurveFromForwardCurve, discountCurveFromForwardCurve);
    int expectedHashCodeResult = discountCurveFromForwardCurve.hashCode();
    assertEquals(expectedHashCodeResult, discountCurveFromForwardCurve.hashCode());
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DiscountCurveFromForwardCurve.equals(Object)",
    "int DiscountCurveFromForwardCurve.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve((String) null);

    // Act and Assert
    assertNotEquals(
        discountCurveFromForwardCurve, new DiscountCurveFromForwardCurve("Forward Curve Name"));
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DiscountCurveFromForwardCurve.equals(Object)",
    "int DiscountCurveFromForwardCurve.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("net.finmath.marketdata.model.AnalyticModel");

    // Act and Assert
    assertNotEquals(
        discountCurveFromForwardCurve, new DiscountCurveFromForwardCurve("Forward Curve Name"));
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DiscountCurveFromForwardCurve.equals(Object)",
    "int DiscountCurveFromForwardCurve.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name", 10.0d);

    // Act and Assert
    assertNotEquals(
        discountCurveFromForwardCurve, new DiscountCurveFromForwardCurve("Forward Curve Name"));
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DiscountCurveFromForwardCurve.equals(Object)",
    "int DiscountCurveFromForwardCurve.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act and Assert
    assertNotEquals(
        discountCurveFromForwardCurve, new DiscountCurveFromForwardCurve("Forward Curve Name"));
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DiscountCurveFromForwardCurve.equals(Object)",
    "int DiscountCurveFromForwardCurve.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Forward Curve Name");

    // Act and Assert
    assertNotEquals(discountCurveFromForwardCurve, new DiscountCurveFromForwardCurve(forwardCurve));
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DiscountCurveFromForwardCurve.equals(Object)",
    "int DiscountCurveFromForwardCurve.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DiscountCurveFromForwardCurve("Forward Curve Name"), null);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DiscountCurveFromForwardCurve.equals(Object)",
    "int DiscountCurveFromForwardCurve.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new DiscountCurveFromForwardCurve("Forward Curve Name"),
        "Different type to DiscountCurveFromForwardCurve");
  }
}
