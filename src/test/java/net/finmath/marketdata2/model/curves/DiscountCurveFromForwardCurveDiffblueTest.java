package net.finmath.marketdata2.model.curves;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata2.model.AnalyticModel;
import net.finmath.marketdata2.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata2.model.curves.ForwardCurveInterpolation.InterpolationEntityForward;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.RandomVariableLazyEvaluation;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

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
    assertNull(actualDiscountCurveFromForwardCurve.getReferenceDate());
    assertNull(actualDiscountCurveFromForwardCurve.getParameter());
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
    assertNull(actualDiscountCurveFromForwardCurve.getReferenceDate());
    assertNull(actualDiscountCurveFromForwardCurve.getParameter());
  }

  /**
   * Test {@link
   * DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(ForwardCurveInterface)}.
   *
   * <p>Method under test: {@link
   * DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DiscountCurveFromForwardCurve.<init>(ForwardCurveInterface)"})
  public void testNewDiscountCurveFromForwardCurve3() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    DiscountCurveFromForwardCurve actualDiscountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Assert
    assertEquals(
        "DiscountCurveFromForwardCurveForwardCurveFromDiscountCurve(3,Payment Offset Code))",
        actualDiscountCurveFromForwardCurve.getName());
    assertNull(actualDiscountCurveFromForwardCurve.getReferenceDate());
    assertNull(actualDiscountCurveFromForwardCurve.getParameter());
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(ForwardCurveInterface,
   * double)}.
   *
   * <p>Method under test: {@link
   * DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(ForwardCurveInterface, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DiscountCurveFromForwardCurve.<init>(ForwardCurveInterface, double)"})
  public void testNewDiscountCurveFromForwardCurve4() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    DiscountCurveFromForwardCurve actualDiscountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve, 10.0d);

    // Assert
    assertEquals(
        "DiscountCurveFromForwardCurveForwardCurveFromDiscountCurve(3,Payment Offset Code))",
        actualDiscountCurveFromForwardCurve.getName());
    assertNull(actualDiscountCurveFromForwardCurve.getReferenceDate());
    assertNull(actualDiscountCurveFromForwardCurve.getParameter());
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {9.765625E-4d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity2() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(1.0d, new RandomVariableFromDoubleArray(1.0d), false);
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(1.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {Double.NaN}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity3() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.DISCOUNTFACTOR, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {1.0d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity4() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.mult(anyDouble())).thenThrow(new IllegalArgumentException());

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d));
    verify(value).mult(1.0d);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity5() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.getTypePriority()).thenThrow(new IllegalArgumentException());

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d));
    verify(value).getTypePriority();
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity6() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.doubleValue()).thenReturn(10.0d);
    when(value.isDeterministic()).thenReturn(true);
    when(value.getFiltrationTime()).thenReturn(10.0d);
    when(value.getTypePriority()).thenReturn(1);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(value).doubleValue();
    verify(value).getFiltrationTime();
    verify(value).getTypePriority();
    verify(value).isDeterministic();
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {3.855432894295318E-11d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity7() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.doubleValue()).thenThrow(new IllegalArgumentException());
    when(value.isDeterministic()).thenReturn(true);
    when(value.getFiltrationTime()).thenReturn(10.0d);
    when(value.getTypePriority()).thenReturn(1);

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d));
    verify(value).doubleValue();
    verify(value).getFiltrationTime();
    verify(value).getTypePriority();
    verify(value).isDeterministic();
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity8() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.doubleValue()).thenReturn(10.0d);
    when(value.isDeterministic()).thenReturn(true);
    when(value.getFiltrationTime()).thenReturn(10.0d);
    when(value.getTypePriority()).thenReturn(1);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.DISCOUNTFACTOR, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(value).doubleValue();
    verify(value).getFiltrationTime();
    verify(value).getTypePriority();
    verify(value).isDeterministic();
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {1.0d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity9() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {3.855432894295318E-11d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity10() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.pow(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualDiscountFactor instanceof RandomVariableDifferentiableAAD);
    assertEquals(1.0E10d, actualDiscountFactor.getAverage(), 0.0);
    assertEquals(1.0E10d, actualDiscountFactor.getMax(), 0.0);
    assertEquals(1.0E10d, actualDiscountFactor.getMin(), 0.0);
    assertArrayEquals(new double[] {1.0E10d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity11() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.pow(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAADPathwise.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualDiscountFactor.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDiscountFactor.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualDiscountFactor.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDiscountFactor.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDiscountFactor.getStandardError(), 0.0);
    assertEquals(0.0d, actualDiscountFactor.getVariance(), 0.0);
    assertEquals(1, actualDiscountFactor.size());
    assertEquals(1.0E10d, actualDiscountFactor.getAverage(), 0.0);
    assertEquals(1.0E10d, actualDiscountFactor.getMax(), 0.0);
    assertEquals(1.0E10d, actualDiscountFactor.getMin(), 0.0);
    assertEquals(3, actualDiscountFactor.getTypePriority());
    assertTrue(actualDiscountFactor.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDiscountFactor.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualDiscountFactor.getValues());
    assertArrayEquals(new double[] {1.0E10d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is one.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_givenScalarWithValueIsOne() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, Scalar.of(1.0d), false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {9.765625E-4d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then return Average is {@code 4.539992976248487E-5}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenReturnAverageIs4539992976248487e5() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.ZERO, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertEquals(4.539992976248487E-5d, actualDiscountFactor.getAverage(), 0.0);
    assertEquals(4.539992976248487E-5d, actualDiscountFactor.getMax(), 0.0);
    assertEquals(4.539992976248487E-5d, actualDiscountFactor.getMin(), 0.0);
    assertArrayEquals(
        new double[] {4.539992976248487E-5d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenReturnAverageIsOne() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(1.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);
    when(randomVariableAAD.pow(anyDouble())).thenReturn(randomVariableDifferentiableAAD);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualDiscountFactor instanceof RandomVariableDifferentiableAAD);
    assertEquals(1.0d, actualDiscountFactor.getAverage(), 0.0);
    assertEquals(1.0d, actualDiscountFactor.getMax(), 0.0);
    assertEquals(1.0d, actualDiscountFactor.getMin(), 0.0);
    assertSame(factory, ((RandomVariableDifferentiableAAD) actualDiscountFactor).getFactory());
    assertArrayEquals(new double[] {1.0d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenReturnOperatorIsNull() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.pow(anyDouble())).thenReturn(new RandomVariableLazyEvaluation(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualDiscountFactor instanceof RandomVariableLazyEvaluation);
    assertNull(actualDiscountFactor.getOperator());
    assertEquals(0.0d, actualDiscountFactor.getFiltrationTime(), 0.0);
    assertEquals(1, actualDiscountFactor.size());
    assertTrue(actualDiscountFactor.isDeterministic());
    assertArrayEquals(new double[] {1.0E10d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromFloatArray(1.0d), false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    assertTrue(actualDiscountFactor instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualDiscountFactor.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDiscountFactor.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDiscountFactor.getStandardError(), 0.0);
    assertEquals(0.0d, actualDiscountFactor.getVariance(), 0.0);
    assertEquals(1, actualDiscountFactor.getTypePriority());
    assertEquals(1, actualDiscountFactor.size());
    assertEquals(9.765625E-4d, actualDiscountFactor.getAverage(), 0.0);
    assertEquals(9.765625E-4d, actualDiscountFactor.getMax(), 0.0);
    assertEquals(9.765625E-4d, actualDiscountFactor.getMin(), 0.0);
    assertTrue(actualDiscountFactor.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDiscountFactor.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {9.765625E-4d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenReturnRealizationsIsArrayOfDoubleWithNaN() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(1.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {Double.NaN}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)} with {@code maturity}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.pow(anyDouble()))
        .thenReturn(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);

    // Act
    RandomVariable actualDiscountFactor =
        new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualDiscountFactor instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualDiscountFactor.size());
    assertEquals(10.0d, actualDiscountFactor.getFiltrationTime(), 0.0);
    assertFalse(actualDiscountFactor.isDeterministic());
    assertArrayEquals(
        new double[] {
          1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d
        },
        actualDiscountFactor.getRealizations(),
        0.0);
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
  @MethodsUnderTest({"RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithMaturity_thenThrowRuntimeException() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            Double.NEGATIVE_INFINITY,
            InterpolationEntityForward.FORWARD,
            "3");

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> new DiscountCurveFromForwardCurve(forwardCurve).getDiscountFactor(10.0d));
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity() {
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
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity2() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {9.765625E-4d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity3() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(1.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {Double.NaN}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity4() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(1.0d, new RandomVariableFromDoubleArray(1.0d), false);
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(1.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {Double.NaN}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity5() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.DISCOUNTFACTOR, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {1.0d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity6() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.mult(anyDouble())).thenThrow(new IllegalArgumentException());

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            discountCurveFromForwardCurve.getDiscountFactor(
                new AnalyticModelFromCurvesAndVols(), 10.0d));
    verify(value).mult(1.0d);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity7() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.getTypePriority()).thenThrow(new IllegalArgumentException());

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            discountCurveFromForwardCurve.getDiscountFactor(
                new AnalyticModelFromCurvesAndVols(), 10.0d));
    verify(value).getTypePriority();
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity8() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.doubleValue()).thenReturn(10.0d);
    when(value.isDeterministic()).thenReturn(true);
    when(value.getFiltrationTime()).thenReturn(10.0d);
    when(value.getTypePriority()).thenReturn(1);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(value).doubleValue();
    verify(value).getFiltrationTime();
    verify(value).getTypePriority();
    verify(value).isDeterministic();
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {3.855432894295318E-11d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity9() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.doubleValue()).thenThrow(new IllegalArgumentException());
    when(value.isDeterministic()).thenReturn(true);
    when(value.getFiltrationTime()).thenReturn(10.0d);
    when(value.getTypePriority()).thenReturn(1);

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            discountCurveFromForwardCurve.getDiscountFactor(
                new AnalyticModelFromCurvesAndVols(), 10.0d));
    verify(value).doubleValue();
    verify(value).getFiltrationTime();
    verify(value).getTypePriority();
    verify(value).isDeterministic();
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity10() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.doubleValue()).thenReturn(10.0d);
    when(value.isDeterministic()).thenReturn(true);
    when(value.getFiltrationTime()).thenReturn(10.0d);
    when(value.getTypePriority()).thenReturn(1);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.DISCOUNTFACTOR, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(value).doubleValue();
    verify(value).getFiltrationTime();
    verify(value).getTypePriority();
    verify(value).isDeterministic();
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {1.0d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity11() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {3.855432894295318E-11d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity12() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.pow(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualDiscountFactor instanceof RandomVariableDifferentiableAAD);
    assertEquals(1.0E10d, actualDiscountFactor.getAverage(), 0.0);
    assertEquals(1.0E10d, actualDiscountFactor.getMax(), 0.0);
    assertEquals(1.0E10d, actualDiscountFactor.getMin(), 0.0);
    assertArrayEquals(new double[] {1.0E10d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity13() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.pow(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAADPathwise.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualDiscountFactor)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualDiscountFactor.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDiscountFactor.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualDiscountFactor.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDiscountFactor.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDiscountFactor.getStandardError(), 0.0);
    assertEquals(0.0d, actualDiscountFactor.getVariance(), 0.0);
    assertEquals(1, actualDiscountFactor.size());
    assertEquals(1.0E10d, actualDiscountFactor.getAverage(), 0.0);
    assertEquals(1.0E10d, actualDiscountFactor.getMax(), 0.0);
    assertEquals(1.0E10d, actualDiscountFactor.getMin(), 0.0);
    assertEquals(3, actualDiscountFactor.getTypePriority());
    assertTrue(actualDiscountFactor.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDiscountFactor.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualDiscountFactor.getValues());
    assertArrayEquals(new double[] {1.0E10d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is one.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_givenScalarWithValueIsOne() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, Scalar.of(1.0d), false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {9.765625E-4d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then return Average is {@code 4.539992976248487E-5}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenReturnAverageIs4539992976248487e5() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.ZERO, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    assertTrue(actualDiscountFactor instanceof RandomVariableFromDoubleArray);
    assertEquals(4.539992976248487E-5d, actualDiscountFactor.getAverage(), 0.0);
    assertEquals(4.539992976248487E-5d, actualDiscountFactor.getMax(), 0.0);
    assertEquals(4.539992976248487E-5d, actualDiscountFactor.getMin(), 0.0);
    assertArrayEquals(
        new double[] {4.539992976248487E-5d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenReturnAverageIsOne() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(1.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);
    when(randomVariableAAD.pow(anyDouble())).thenReturn(randomVariableDifferentiableAAD);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualDiscountFactor instanceof RandomVariableDifferentiableAAD);
    assertEquals(1.0d, actualDiscountFactor.getAverage(), 0.0);
    assertEquals(1.0d, actualDiscountFactor.getMax(), 0.0);
    assertEquals(1.0d, actualDiscountFactor.getMin(), 0.0);
    assertSame(factory, ((RandomVariableDifferentiableAAD) actualDiscountFactor).getFactory());
    assertArrayEquals(new double[] {1.0d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenReturnOperatorIsNull() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.pow(anyDouble())).thenReturn(new RandomVariableLazyEvaluation(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualDiscountFactor instanceof RandomVariableLazyEvaluation);
    assertNull(actualDiscountFactor.getOperator());
    assertEquals(0.0d, actualDiscountFactor.getFiltrationTime(), 0.0);
    assertEquals(1, actualDiscountFactor.size());
    assertTrue(actualDiscountFactor.isDeterministic());
    assertArrayEquals(new double[] {1.0E10d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromFloatArray(1.0d), false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    assertTrue(actualDiscountFactor instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDiscountFactor.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualDiscountFactor.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDiscountFactor.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDiscountFactor.getStandardError(), 0.0);
    assertEquals(0.0d, actualDiscountFactor.getVariance(), 0.0);
    assertEquals(1, actualDiscountFactor.getTypePriority());
    assertEquals(1, actualDiscountFactor.size());
    assertEquals(9.765625E-4d, actualDiscountFactor.getAverage(), 0.0);
    assertEquals(9.765625E-4d, actualDiscountFactor.getMax(), 0.0);
    assertEquals(9.765625E-4d, actualDiscountFactor.getMin(), 0.0);
    assertTrue(actualDiscountFactor.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDiscountFactor.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {9.765625E-4d}, actualDiscountFactor.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel, double)} with {@code
   * model}, {@code maturity}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getDiscountFactor(AnalyticModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.pow(anyDouble()))
        .thenReturn(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualDiscountFactor =
        discountCurveFromForwardCurve.getDiscountFactor(
            new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualDiscountFactor instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualDiscountFactor.size());
    assertEquals(10.0d, actualDiscountFactor.getFiltrationTime(), 0.0);
    assertFalse(actualDiscountFactor.isDeterministic());
    assertArrayEquals(
        new double[] {
          1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d
        },
        actualDiscountFactor.getRealizations(),
        0.0);
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
    "RandomVariable DiscountCurveFromForwardCurve.getDiscountFactor(AnalyticModel, double)"
  })
  public void testGetDiscountFactorWithModelMaturity_thenThrowRuntimeException() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            Double.NEGATIVE_INFINITY,
            InterpolationEntityForward.FORWARD,
            "3");
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
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(1.0d, new RandomVariableFromDoubleArray(1.0d), false);
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(1.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {Double.NaN}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime2() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.DISCOUNTFACTOR, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {1.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime3() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.mult(anyDouble())).thenThrow(new IllegalArgumentException());

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d));
    verify(value).mult(1.0d);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime4() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.getTypePriority()).thenThrow(new IllegalArgumentException());

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d));
    verify(value).getTypePriority();
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime5() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.doubleValue()).thenReturn(10.0d);
    when(value.isDeterministic()).thenReturn(true);
    when(value.getFiltrationTime()).thenReturn(10.0d);
    when(value.getTypePriority()).thenReturn(1);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(value).doubleValue();
    verify(value).getFiltrationTime();
    verify(value).getTypePriority();
    verify(value).isDeterministic();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {3.855432894295318E-11d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime6() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.doubleValue()).thenThrow(new IllegalArgumentException());
    when(value.isDeterministic()).thenReturn(true);
    when(value.getFiltrationTime()).thenReturn(10.0d);
    when(value.getTypePriority()).thenReturn(1);

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d));
    verify(value).doubleValue();
    verify(value).getFiltrationTime();
    verify(value).getTypePriority();
    verify(value).isDeterministic();
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime7() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.doubleValue()).thenReturn(10.0d);
    when(value.isDeterministic()).thenReturn(true);
    when(value.getFiltrationTime()).thenReturn(10.0d);
    when(value.getTypePriority()).thenReturn(1);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.DISCOUNTFACTOR, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(value).doubleValue();
    verify(value).getFiltrationTime();
    verify(value).getTypePriority();
    verify(value).isDeterministic();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {1.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime8() {
    // Arrange
    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {3.855432894295318E-11d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime9() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.pow(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertEquals(1.0E10d, actualValue.getAverage(), 0.0);
    assertEquals(1.0E10d, actualValue.getMax(), 0.0);
    assertEquals(1.0E10d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {1.0E10d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Given {@link DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(String)} with
   *       {@code Forward Curve Name}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime_givenDiscountCurveFromForwardCurveWithForwardCurveName() {
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
   *   <li>Given {@link Scalar} with value is one.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime_givenScalarWithValueIsOne() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, Scalar.of(1.0d), false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {9.765625E-4d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Then return Average is {@code 4.539992976248487E-5}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime_thenReturnAverageIs4539992976248487e5() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.ZERO, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(4.539992976248487E-5d, actualValue.getAverage(), 0.0);
    assertEquals(4.539992976248487E-5d, actualValue.getMax(), 0.0);
    assertEquals(4.539992976248487E-5d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {4.539992976248487E-5d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime_thenReturnAverageIsOne() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(1.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);
    when(randomVariableAAD.pow(anyDouble())).thenReturn(randomVariableDifferentiableAAD);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertEquals(1.0d, actualValue.getAverage(), 0.0);
    assertEquals(1.0d, actualValue.getMax(), 0.0);
    assertEquals(1.0d, actualValue.getMin(), 0.0);
    assertSame(factory, ((RandomVariableDifferentiableAAD) actualValue).getFactory());
    assertArrayEquals(new double[] {1.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Then return Operator is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime_thenReturnOperatorIsNull() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.pow(anyDouble())).thenReturn(new RandomVariableLazyEvaluation(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualValue instanceof RandomVariableLazyEvaluation);
    assertNull(actualValue.getOperator());
    assertEquals(0.0d, actualValue.getFiltrationTime(), 0.0);
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.isDeterministic());
    assertArrayEquals(new double[] {1.0E10d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime_thenReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.pow(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAADPathwise.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualValue).getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualValue).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualValue).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualValue).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualValue)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualValue)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualValue)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualValue).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualValue.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualValue.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualValue.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.size());
    assertEquals(1.0E10d, actualValue.getAverage(), 0.0);
    assertEquals(1.0E10d, actualValue.getMax(), 0.0);
    assertEquals(1.0E10d, actualValue.getMin(), 0.0);
    assertEquals(3, actualValue.getTypePriority());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertSame(randomVariable, actualValue.getValues());
    assertArrayEquals(new double[] {1.0E10d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromFloatArray(1.0d), false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    assertTrue(actualValue instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(1, actualValue.size());
    assertEquals(9.765625E-4d, actualValue.getAverage(), 0.0);
    assertEquals(9.765625E-4d, actualValue.getMax(), 0.0);
    assertEquals(9.765625E-4d, actualValue.getMin(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {9.765625E-4d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@code 9.765625E-4}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime_thenReturnRealizationsIsArrayOfDoubleWith9765625e4() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {9.765625E-4d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime_thenReturnRealizationsIsArrayOfDoubleWithNaN() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, new RandomVariableFromDoubleArray(1.0d), true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(1.0d), false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {Double.NaN}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveFromForwardCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime_thenReturnSizeIsTen() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.pow(anyDouble()))
        .thenReturn(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.add(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.addProduct(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableAAD3);

    RandomVariableAAD value2 = mock(RandomVariableAAD.class);
    when(value2.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(10.0d, value2, true);
    forwardCurve.addPoint(Double.NEGATIVE_INFINITY, value, false);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act
    RandomVariable actualValue =
        discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    verify(randomVariableAAD2, atLeast(1)).add(1.0d);
    verify(value, atLeast(1)).addProduct(isA(RandomVariable.class), Mockito.<RandomVariable>any());
    verify(randomVariableAAD3, atLeast(1)).mult(1.0d);
    verify(randomVariableAAD, atLeast(1)).pow(-1.0d);
    verify(value2).sub(isA(RandomVariable.class));
    assertTrue(actualValue instanceof RandomVariableLazyEvaluation);
    assertEquals(10, actualValue.size());
    assertEquals(10.0d, actualValue.getFiltrationTime(), 0.0);
    assertFalse(actualValue.isDeterministic());
    assertArrayEquals(
        new double[] {
          1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d, 1.0E10d
        },
        actualValue.getRealizations(),
        0.0);
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
  @MethodsUnderTest({
    "RandomVariable DiscountCurveFromForwardCurve.getValue(AnalyticModel, double)"
  })
  public void testGetValueWithModelTime_thenThrowRuntimeException() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            Double.NEGATIVE_INFINITY,
            InterpolationEntityForward.FORWARD,
            "3");
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve(forwardCurve);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> discountCurveFromForwardCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DiscountCurveFromForwardCurve#setParameter(RandomVariable[])}
   *   <li>{@link DiscountCurveFromForwardCurve#getParameter()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] DiscountCurveFromForwardCurve.getParameter()",
    "void DiscountCurveFromForwardCurve.setParameter(RandomVariable[])"
  })
  public void testGettersAndSetters() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    discountCurveFromForwardCurve.setParameter(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

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
    "net.finmath.marketdata2.model.curves.CurveBuilder DiscountCurveFromForwardCurve.getCloneBuilder()"
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
        new DiscountCurveFromForwardCurve("42");

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
