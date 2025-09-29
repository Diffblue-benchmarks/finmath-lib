package net.finmath.marketdata2.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
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
import net.finmath.marketdata2.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata2.model.curves.DiscountCurveInterface;
import net.finmath.marketdata2.model.curves.DiscountCurveInterpolation;
import net.finmath.marketdata2.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata2.model.curves.ForwardCurveInterface;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.RandomVariableLazyEvaluation;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.Period;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.ScheduleFromPeriods;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SwapAnnuityDiffblueTest {
  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel() {
    // Arrange
    SwapAnnuity swapAnnuity =
        new SwapAnnuity(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3");

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.doubleValue()).thenReturn(10.0d);
    when(randomVariable.isDeterministic()).thenReturn(true);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable.getTypePriority()).thenReturn(1);
    when(randomVariable.mult(anyDouble())).thenReturn(Scalar.of(Double.NEGATIVE_INFINITY));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable).doubleValue();
    verify(randomVariable).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariable).isDeterministic();
    verify(randomVariable, atLeast(1)).mult(0.5d);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getAverage(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getMax(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NEGATIVE_INFINITY}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel2() {
    // Arrange
    SwapAnnuity swapAnnuity =
        new SwapAnnuity(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3");

    Scalar scalar = mock(Scalar.class);
    when(scalar.add(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.getTypePriority()).thenReturn(1);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable).getTypePriority();
    verify(randomVariable, atLeast(1)).getValues();
    verify(randomVariable, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).add(Mockito.<RandomVariable>any());
    assertTrue(actualValue.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.size());
    assertEquals(1.0d, actualValue.getAverage(), 0.0);
    assertEquals(1.0d, actualValue.getMin(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertArrayEquals(new double[] {1.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return Average is {@code 0.26596470171236347}.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnAverageIs026596470171236347() {
    // Arrange
    SwapAnnuity swapAnnuity =
        new SwapAnnuity(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3");

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(forwardCurveFromDiscountCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    RandomVariable actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(forwardCurveFromDiscountCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.26596470171236347d, actualValue.getAverage(), 0.0);
    assertEquals(0.26596470171236347d, actualValue.getMax(), 0.0);
    assertEquals(0.26596470171236347d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {0.26596470171236347d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return Average is five.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnAverageIsFive() {
    // Arrange
    SwapAnnuity swapAnnuity =
        new SwapAnnuity(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(5.0d, actualValue.getAverage(), 0.0);
    assertEquals(5.0d, actualValue.getMax(), 0.0);
    assertEquals(5.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {5.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return Average is one hundred.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnAverageIsOneHundred() {
    // Arrange
    SwapAnnuity swapAnnuity =
        new SwapAnnuity(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3");

    Scalar scalar = mock(Scalar.class);
    when(scalar.add(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.invert()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(scalar);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable).invert();
    verify(randomVariable, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).add(Mockito.<RandomVariable>any());
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(100.0d, actualValue.getAverage(), 0.0);
    assertEquals(100.0d, actualValue.getMax(), 0.0);
    assertEquals(100.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {100.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return Average is ten.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnAverageIsTen() {
    // Arrange
    SwapAnnuity swapAnnuity =
        new SwapAnnuity(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3");

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.doubleValue()).thenReturn(10.0d);
    when(randomVariable.isDeterministic()).thenReturn(true);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable.getTypePriority()).thenReturn(1);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable).doubleValue();
    verify(randomVariable).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariable).isDeterministic();
    verify(randomVariable, atLeast(1)).mult(0.5d);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(10.0d, actualValue.getAverage(), 0.0);
    assertEquals(10.0d, actualValue.getMax(), 0.0);
    assertEquals(10.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnAverageIsZero() {
    // Arrange
    TenorFromArray timeDiscretization =
        new TenorFromArray(
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});
    RegularSchedule schedule = new RegularSchedule(timeDiscretization);
    SwapAnnuity swapAnnuity = new SwapAnnuity(schedule, "3");

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(forwardCurveFromDiscountCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    RandomVariable actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(forwardCurveFromDiscountCurve).getPaymentOffset(0.0d);
    verify(forwardCurveFromDiscountCurve).getForward(isA(AnalyticModel.class), eq(0.0d));
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualValue.getAverage(), 0.0);
    assertEquals(0.0d, actualValue.getMax(), 0.0);
    assertEquals(0.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnFiltrationTimeIsNegative_infinity() {
    // Arrange
    SwapAnnuity swapAnnuity =
        new SwapAnnuity(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3");

    Scalar scalar = mock(Scalar.class);
    when(scalar.add(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.invert())
        .thenReturn(RandomVariableDifferentiableAADPathwise.of(Double.NEGATIVE_INFINITY));
    when(randomVariable.mult(anyDouble())).thenReturn(scalar);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable).invert();
    verify(randomVariable, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).add(Mockito.<RandomVariable>any());
    assertTrue(actualValue instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getAverage(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getMax(), 0.0);
    assertArrayEquals(new double[] {Double.NEGATIVE_INFINITY}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return Max is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnMaxIsNaN() {
    // Arrange
    SwapAnnuity swapAnnuity =
        new SwapAnnuity(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3");

    Scalar scalar = mock(Scalar.class);
    RandomVariableFromDoubleArray values =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);
    when(scalar.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableDifferentiableAAD);

    RandomVariable randomVariable = mock(RandomVariable.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(
            Double.NEGATIVE_INFINITY,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});
    when(randomVariable.getValues()).thenReturn(randomVariableDifferentiableAADPathwise);
    when(randomVariable.getTypePriority()).thenReturn(1);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable).getTypePriority();
    verify(randomVariable, atLeast(1)).getValues();
    verify(randomVariable, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).add(Mockito.<RandomVariable>any());
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertEquals(Double.NaN, actualValue.getAverage(), 0.0);
    assertEquals(Double.NaN, actualValue.getMax(), 0.0);
    assertEquals(Double.NaN, actualValue.getMin(), 0.0);
    assertEquals(Double.NaN, actualValue.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualValue.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualValue.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualValue.getVariance(), 0.0);
    assertSame(factory, ((RandomVariableDifferentiableAAD) actualValue).getFactory());
    assertArrayEquals(
        new double[] {Double.NaN, Double.NEGATIVE_INFINITY, Double.NaN, Double.NEGATIVE_INFINITY},
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return Max is one.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnMaxIsOne() {
    // Arrange
    SwapAnnuity swapAnnuity =
        new SwapAnnuity(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3");

    Scalar scalar = mock(Scalar.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(
            10.0d, new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});
    when(scalar.add(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableDifferentiableAADPathwise);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.get(anyInt())).thenReturn(10.0d);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable.getTypePriority()).thenReturn(1);
    when(randomVariable.size()).thenReturn(3);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).get(anyInt());
    verify(randomVariable).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariable, atLeast(1)).mult(0.5d);
    verify(randomVariable).size();
    verify(scalar, atLeast(1)).add(Mockito.<RandomVariable>any());
    assertTrue(actualValue instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(1.0d, actualValue.getMax(), 0.0);
    assertEquals(10.0d, actualValue.getFiltrationTime(), 0.0);
    assertEquals(4, actualValue.size());
    assertFalse(actualValue.isDeterministic());
    assertEquals(Double.NaN, actualValue.getAverage(), 0.0);
    assertEquals(Double.NaN, actualValue.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualValue.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualValue.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualValue.getVariance(), 0.0);
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY, 1.0d},
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return Min is {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnMinIs00() {
    // Arrange
    SwapAnnuity swapAnnuity =
        new SwapAnnuity(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3");

    Scalar scalar = mock(Scalar.class);
    when(scalar.add(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(
            Double.NEGATIVE_INFINITY,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});
    when(randomVariable.getValues()).thenReturn(randomVariableDifferentiableAADPathwise);
    when(randomVariable.getTypePriority()).thenReturn(1);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable).getTypePriority();
    verify(randomVariable, atLeast(1)).getValues();
    verify(randomVariable, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).add(Mockito.<RandomVariable>any());
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertEquals(-0.0d, actualValue.getMin(), 0.0);
    assertEquals(0.25d, actualValue.getStandardError(), 0.0);
    assertEquals(0.25d, actualValue.getVariance(), 0.0);
    assertEquals(0.3333333333333333d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.5d, actualValue.getAverage(), 0.0);
    assertEquals(0.5d, actualValue.getStandardDeviation(), 0.0);
    assertArrayEquals(new double[] {-0.0d, 1.0d, -0.0d, 1.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    SwapAnnuity swapAnnuity =
        new SwapAnnuity(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3");

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.isDeterministic()).thenReturn(true);
    when(randomVariable.get(anyInt())).thenReturn(10.0d);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable.getTypePriority()).thenReturn(1);
    when(randomVariable.mult(anyDouble()))
        .thenReturn(new RandomVariableFromFloatArray(Double.NEGATIVE_INFINITY));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable).get(0);
    verify(randomVariable).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariable).isDeterministic();
    verify(randomVariable, atLeast(1)).mult(0.5d);
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
    assertEquals(10.0d, actualValue.getFiltrationTime(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getAverage(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getMax(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NEGATIVE_INFINITY}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnRandomVariableLazyEvaluation() {
    // Arrange
    SwapAnnuity swapAnnuity =
        new SwapAnnuity(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), "3");

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.isDeterministic()).thenReturn(true);
    when(randomVariable.get(anyInt())).thenReturn(10.0d);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable.mult(anyDouble()))
        .thenReturn(new RandomVariableLazyEvaluation(Double.NEGATIVE_INFINITY));

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualValue = swapAnnuity.getValue(10.0d, model);

    // Assert
    verify(model).getCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable).get(0);
    verify(randomVariable).getFiltrationTime();
    verify(randomVariable).isDeterministic();
    verify(randomVariable, atLeast(1)).mult(0.5d);
    assertTrue(actualValue instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.variance() instanceof RandomVariableLazyEvaluation);
    assertNull(actualValue.getOperator());
    assertEquals(0, actualValue.getTypePriority());
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.size());
    assertEquals(10.0d, actualValue.getFiltrationTime(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getAverage(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getMax(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NEGATIVE_INFINITY}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(double, Schedule, DiscountCurveInterface,
   * AnalyticModel)} with {@code evaluationTime}, {@code schedule}, {@code discountCurve}, {@code
   * model}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(double, Schedule,
   * DiscountCurveInterface, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(double, Schedule, DiscountCurveInterface, AnalyticModel)"
  })
  public void testGetSwapAnnuityWithEvaluationTimeScheduleDiscountCurveModel() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualSwapAnnuity =
        SwapAnnuity.getSwapAnnuity(
            10.0d, schedule, discountCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertEquals(5.0d, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(5.0d, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(5.0d, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {5.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(double, Schedule, DiscountCurveInterface,
   * AnalyticModel)} with {@code evaluationTime}, {@code schedule}, {@code discountCurve}, {@code
   * model}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(double, Schedule,
   * DiscountCurveInterface, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(double, Schedule, DiscountCurveInterface, AnalyticModel)"
  })
  public void testGetSwapAnnuityWithEvaluationTimeScheduleDiscountCurveModel2() {
    // Arrange
    TenorFromArray timeDiscretization =
        new TenorFromArray(
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});
    RegularSchedule schedule = new RegularSchedule(timeDiscretization);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualSwapAnnuity =
        SwapAnnuity.getSwapAnnuity(
            10.0d, schedule, discountCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(discountCurve).getDiscountFactor(isA(AnalyticModel.class), eq(10.0d));
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(double, Schedule, DiscountCurveInterface,
   * AnalyticModel)} with {@code evaluationTime}, {@code schedule}, {@code discountCurve}, {@code
   * model}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(double, Schedule,
   * DiscountCurveInterface, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(double, Schedule, DiscountCurveInterface, AnalyticModel)"
  })
  public void testGetSwapAnnuityWithEvaluationTimeScheduleDiscountCurveModel3() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(Scalar.of(Double.NEGATIVE_INFINITY));

    // Act
    RandomVariable actualSwapAnnuity =
        SwapAnnuity.getSwapAnnuity(
            10.0d, schedule, discountCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(double, Schedule, DiscountCurveInterface,
   * AnalyticModel)} with {@code evaluationTime}, {@code schedule}, {@code discountCurve}, {@code
   * model}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(double, Schedule,
   * DiscountCurveInterface, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(double, Schedule, DiscountCurveInterface, AnalyticModel)"
  })
  public void testGetSwapAnnuityWithEvaluationTimeScheduleDiscountCurveModel4() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromFloatArray(Double.NEGATIVE_INFINITY));

    // Act
    RandomVariable actualSwapAnnuity =
        SwapAnnuity.getSwapAnnuity(
            10.0d, schedule, discountCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualSwapAnnuity.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.variance() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualSwapAnnuity.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardError(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getVariance(), 0.0);
    assertEquals(1, actualSwapAnnuity.getTypePriority());
    assertEquals(1, actualSwapAnnuity.size());
    assertTrue(actualSwapAnnuity.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getFiltrationTime(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(double, Schedule, DiscountCurveInterface,
   * AnalyticModel)} with {@code evaluationTime}, {@code schedule}, {@code discountCurve}, {@code
   * model}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(double, Schedule,
   * DiscountCurveInterface, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(double, Schedule, DiscountCurveInterface, AnalyticModel)"
  })
  public void testGetSwapAnnuityWithEvaluationTimeScheduleDiscountCurveModel5() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.invert()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariableAAD2);

    // Act
    RandomVariable actualSwapAnnuity =
        SwapAnnuity.getSwapAnnuity(
            10.0d, schedule, discountCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(randomVariableAAD, atLeast(1)).add(Mockito.<RandomVariable>any());
    verify(randomVariableAAD2).invert();
    verify(randomVariableAAD2, atLeast(1)).mult(0.5d);
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertEquals(100.0d, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(100.0d, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(100.0d, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {100.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(double, Schedule, DiscountCurveInterface,
   * AnalyticModel)} with {@code evaluationTime}, {@code schedule}, {@code discountCurve}, {@code
   * model}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(double, Schedule,
   * DiscountCurveInterface, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(double, Schedule, DiscountCurveInterface, AnalyticModel)"
  })
  public void testGetSwapAnnuityWithEvaluationTimeScheduleDiscountCurveModel6() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.invert())
        .thenReturn(RandomVariableDifferentiableAADPathwise.of(Double.NEGATIVE_INFINITY));
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariableAAD2);

    // Act
    RandomVariable actualSwapAnnuity =
        SwapAnnuity.getSwapAnnuity(
            10.0d, schedule, discountCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(randomVariableAAD, atLeast(1)).add(Mockito.<RandomVariable>any());
    verify(randomVariableAAD2).invert();
    verify(randomVariableAAD2, atLeast(1)).mult(0.5d);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSwapAnnuity.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSwapAnnuity.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSwapAnnuity.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSwapAnnuity instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualSwapAnnuity.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardError(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getVariance(), 0.0);
    assertEquals(1, actualSwapAnnuity.size());
    assertEquals(3, actualSwapAnnuity.getTypePriority());
    assertTrue(actualSwapAnnuity.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getFiltrationTime(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getMin(), 0.0);
    assertSame(randomVariable, actualSwapAnnuity.getValues());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(double, Schedule, DiscountCurveInterface,
   * AnalyticModel)} with {@code evaluationTime}, {@code schedule}, {@code discountCurve}, {@code
   * model}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(double, Schedule,
   * DiscountCurveInterface, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(double, Schedule, DiscountCurveInterface, AnalyticModel)"
  })
  public void testGetSwapAnnuityWithEvaluationTimeScheduleDiscountCurveModel7() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariableAAD2);

    // Act
    RandomVariable actualSwapAnnuity =
        SwapAnnuity.getSwapAnnuity(
            10.0d, schedule, discountCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(randomVariableAAD, atLeast(1)).add(Mockito.<RandomVariable>any());
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD2, atLeast(1)).mult(0.5d);
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(actualSwapAnnuity.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualSwapAnnuity.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardError(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getVariance(), 0.0);
    assertEquals(1, actualSwapAnnuity.size());
    assertEquals(1.0d, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(1.0d, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(1.0d, actualSwapAnnuity.getMin(), 0.0);
    assertEquals(3, actualSwapAnnuity.getTypePriority());
    assertTrue(actualSwapAnnuity.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurveInterface)} with {@code
   * schedule}, {@code discountCurve}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getSwapAnnuity(Schedule, DiscountCurveInterface)"})
  public void testGetSwapAnnuityWithScheduleDiscountCurve() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(Scalar.of(Double.NEGATIVE_INFINITY));

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(schedule, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurveInterface)} with {@code
   * schedule}, {@code discountCurve}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getSwapAnnuity(Schedule, DiscountCurveInterface)"})
  public void testGetSwapAnnuityWithScheduleDiscountCurve2() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromFloatArray(Double.NEGATIVE_INFINITY));

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(schedule, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    assertTrue(actualSwapAnnuity.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.variance() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualSwapAnnuity.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardError(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getVariance(), 0.0);
    assertEquals(1, actualSwapAnnuity.getTypePriority());
    assertEquals(1, actualSwapAnnuity.size());
    assertTrue(actualSwapAnnuity.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getFiltrationTime(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurveInterface)} with {@code
   * schedule}, {@code discountCurve}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getSwapAnnuity(Schedule, DiscountCurveInterface)"})
  public void testGetSwapAnnuityWithScheduleDiscountCurve3() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.invert())
        .thenReturn(RandomVariableDifferentiableAADPathwise.of(Double.NEGATIVE_INFINITY));
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariableAAD2);

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(schedule, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    verify(randomVariableAAD, atLeast(1)).add(Mockito.<RandomVariable>any());
    verify(randomVariableAAD2).invert();
    verify(randomVariableAAD2, atLeast(1)).mult(0.5d);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSwapAnnuity.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSwapAnnuity.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSwapAnnuity.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSwapAnnuity instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualSwapAnnuity.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardError(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getVariance(), 0.0);
    assertEquals(1, actualSwapAnnuity.size());
    assertEquals(3, actualSwapAnnuity.getTypePriority());
    assertTrue(actualSwapAnnuity.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getFiltrationTime(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getMin(), 0.0);
    assertSame(randomVariable, actualSwapAnnuity.getValues());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurveInterface)} with {@code
   * schedule}, {@code discountCurve}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getSwapAnnuity(Schedule, DiscountCurveInterface)"})
  public void testGetSwapAnnuityWithScheduleDiscountCurve4() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariableAAD2);

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(schedule, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    verify(randomVariableAAD, atLeast(1)).add(Mockito.<RandomVariable>any());
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD2, atLeast(1)).mult(0.5d);
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(actualSwapAnnuity.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualSwapAnnuity.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardError(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getVariance(), 0.0);
    assertEquals(1, actualSwapAnnuity.size());
    assertEquals(1.0d, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(1.0d, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(1.0d, actualSwapAnnuity.getMin(), 0.0);
    assertEquals(3, actualSwapAnnuity.getTypePriority());
    assertTrue(actualSwapAnnuity.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurveInterface)} with {@code
   * schedule}, {@code discountCurve}.
   *
   * <ul>
   *   <li>Then return Average is five.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getSwapAnnuity(Schedule, DiscountCurveInterface)"})
  public void testGetSwapAnnuityWithScheduleDiscountCurve_thenReturnAverageIsFive() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(schedule, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertEquals(5.0d, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(5.0d, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(5.0d, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {5.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurveInterface)} with {@code
   * schedule}, {@code discountCurve}.
   *
   * <ul>
   *   <li>Then return Average is one hundred.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getSwapAnnuity(Schedule, DiscountCurveInterface)"})
  public void testGetSwapAnnuityWithScheduleDiscountCurve_thenReturnAverageIsOneHundred() {
    // Arrange
    RegularSchedule schedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.invert()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariableAAD2);

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(schedule, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    verify(randomVariableAAD, atLeast(1)).add(Mockito.<RandomVariable>any());
    verify(randomVariableAAD2).invert();
    verify(randomVariableAAD2, atLeast(1)).mult(0.5d);
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertEquals(100.0d, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(100.0d, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(100.0d, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {100.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurveInterface)} with {@code
   * schedule}, {@code discountCurve}.
   *
   * <ul>
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getSwapAnnuity(Schedule, DiscountCurveInterface)"})
  public void testGetSwapAnnuityWithScheduleDiscountCurve_thenReturnAverageIsZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(schedule, discountCurve);

    // Assert
    verify(discountCurve).getDiscountFactor(isNull(), eq(0.0d));
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, ForwardCurveInterface)} with {@code schedule},
   * {@code forwardCurve}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getSwapAnnuity(Schedule, ForwardCurveInterface)"})
  public void testGetSwapAnnuityWithScheduleForwardCurve() {
    // Arrange
    RegularSchedule schedule =
        new RegularSchedule(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(schedule, forwardCurve);

    // Assert
    assertTrue(actualSwapAnnuity.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, ForwardCurveInterface)} with {@code schedule},
   * {@code forwardCurve}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getSwapAnnuity(Schedule, ForwardCurveInterface)"})
  public void testGetSwapAnnuityWithScheduleForwardCurve2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(schedule, forwardCurve);

    // Assert
    assertTrue(actualSwapAnnuity.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(Schedule, ForwardCurveInterface)} with {@code schedule},
   * {@code forwardCurve}.
   *
   * <ul>
   *   <li>Then return Average is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(Schedule, ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable SwapAnnuity.getSwapAnnuity(Schedule, ForwardCurveInterface)"})
  public void testGetSwapAnnuityWithScheduleForwardCurve_thenReturnAverageIsNaN() {
    // Arrange
    RegularSchedule schedule =
        new RegularSchedule(new TenorFromArray(10.0d, 10, Double.NEGATIVE_INFINITY));
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(schedule, forwardCurve);

    // Assert
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, DiscountCurveInterface)} with {@code
   * tenor}, {@code discountCurve}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization,
   * DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(TimeDiscretization, DiscountCurveInterface)"
  })
  public void testGetSwapAnnuityWithTenorDiscountCurve() {
    // Arrange
    TenorFromArray tenor = new TenorFromArray(10.0d, 10, 0.5d);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromFloatArray(Double.NEGATIVE_INFINITY));

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(tenor, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    assertTrue(actualSwapAnnuity.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity.variance() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualSwapAnnuity.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardError(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getVariance(), 0.0);
    assertEquals(1, actualSwapAnnuity.getTypePriority());
    assertEquals(1, actualSwapAnnuity.size());
    assertTrue(actualSwapAnnuity.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getFiltrationTime(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, DiscountCurveInterface)} with {@code
   * tenor}, {@code discountCurve}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization,
   * DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(TimeDiscretization, DiscountCurveInterface)"
  })
  public void testGetSwapAnnuityWithTenorDiscountCurve2() {
    // Arrange
    TenorFromArray tenor = new TenorFromArray(10.0d, 10, 0.5d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.invert())
        .thenReturn(RandomVariableDifferentiableAADPathwise.of(Double.NEGATIVE_INFINITY));
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariableAAD2);

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(tenor, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    verify(randomVariableAAD, atLeast(1)).add(Mockito.<RandomVariable>any());
    verify(randomVariableAAD2).invert();
    verify(randomVariableAAD2, atLeast(1)).mult(0.5d);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualSwapAnnuity)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSwapAnnuity.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSwapAnnuity.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSwapAnnuity.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSwapAnnuity instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualSwapAnnuity.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getStandardError(), 0.0);
    assertEquals(0.0d, actualSwapAnnuity.getVariance(), 0.0);
    assertEquals(1, actualSwapAnnuity.size());
    assertEquals(3, actualSwapAnnuity.getTypePriority());
    assertTrue(actualSwapAnnuity.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getFiltrationTime(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualSwapAnnuity.getMin(), 0.0);
    assertSame(randomVariable, actualSwapAnnuity.getValues());
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, DiscountCurveInterface)} with {@code
   * tenor}, {@code discountCurve}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization,
   * DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(TimeDiscretization, DiscountCurveInterface)"
  })
  public void testGetSwapAnnuityWithTenorDiscountCurve3() {
    // Arrange
    TenorFromArray tenor = new TenorFromArray(10.0d, 10, 0.5d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariableAAD2);

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(tenor, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    verify(randomVariableAAD, atLeast(1)).add(Mockito.<RandomVariable>any());
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD2, atLeast(1)).mult(0.5d);
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(actualSwapAnnuity.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {1.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, DiscountCurveInterface)} with {@code
   * tenor}, {@code discountCurve}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization,
   * DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(TimeDiscretization, DiscountCurveInterface)"
  })
  public void testGetSwapAnnuityWithTenorDiscountCurve4() {
    // Arrange
    TenorFromArray tenor = new TenorFromArray(-0.5d, 10, 0.5d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariableAAD2);

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(tenor, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    verify(randomVariableAAD, atLeast(1)).add(Mockito.<RandomVariable>any());
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD2, atLeast(1)).mult(0.5d);
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(actualSwapAnnuity.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSwapAnnuity).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSwapAnnuity instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(new double[] {1.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, DiscountCurveInterface)} with {@code
   * tenor}, {@code discountCurve}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization,
   * DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(TimeDiscretization, DiscountCurveInterface)"
  })
  public void testGetSwapAnnuityWithTenorDiscountCurve_givenScalarWithValueIsNegative_infinity() {
    // Arrange
    TenorFromArray tenor = new TenorFromArray(10.0d, 10, 0.5d);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(Scalar.of(Double.NEGATIVE_INFINITY));

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(tenor, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, DiscountCurveInterface)} with {@code
   * tenor}, {@code discountCurve}.
   *
   * <ul>
   *   <li>Then return Average is five.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization,
   * DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(TimeDiscretization, DiscountCurveInterface)"
  })
  public void testGetSwapAnnuityWithTenorDiscountCurve_thenReturnAverageIsFive() {
    // Arrange
    TenorFromArray tenor = new TenorFromArray(10.0d, 10, 0.5d);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(tenor, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertEquals(5.0d, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(5.0d, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(5.0d, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {5.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, DiscountCurveInterface)} with {@code
   * tenor}, {@code discountCurve}.
   *
   * <ul>
   *   <li>Then return Average is one hundred.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization,
   * DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(TimeDiscretization, DiscountCurveInterface)"
  })
  public void testGetSwapAnnuityWithTenorDiscountCurve_thenReturnAverageIsOneHundred() {
    // Arrange
    TenorFromArray tenor = new TenorFromArray(10.0d, 10, 0.5d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.invert()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariableAAD2);

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(tenor, discountCurve);

    // Assert
    verify(discountCurve, atLeast(1)).getDiscountFactor(isNull(), anyDouble());
    verify(randomVariableAAD, atLeast(1)).add(Mockito.<RandomVariable>any());
    verify(randomVariableAAD2).invert();
    verify(randomVariableAAD2, atLeast(1)).mult(0.5d);
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertEquals(100.0d, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(100.0d, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(100.0d, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {100.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, ForwardCurveInterface)} with {@code
   * tenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetSwapAnnuityWithTenorForwardCurve() {
    // Arrange
    TenorFromArray tenor =
        new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(tenor, forwardCurve);

    // Assert
    assertTrue(actualSwapAnnuity.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, ForwardCurveInterface)} with {@code
   * tenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetSwapAnnuityWithTenorForwardCurve2() {
    // Arrange
    TenorFromArray tenor = new TenorFromArray(-0.5d, 1, 0.5d);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(tenor, forwardCurve);

    // Assert
    assertTrue(actualSwapAnnuity.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualSwapAnnuity.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization, ForwardCurveInterface)} with {@code
   * tenor}, {@code forwardCurve}.
   *
   * <ul>
   *   <li>Then return Average is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link SwapAnnuity#getSwapAnnuity(TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapAnnuity.getSwapAnnuity(TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetSwapAnnuityWithTenorForwardCurve_thenReturnAverageIsNaN() {
    // Arrange
    TenorFromArray tenor = new TenorFromArray(10.0d, 10, Double.NEGATIVE_INFINITY);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    RandomVariable actualSwapAnnuity = SwapAnnuity.getSwapAnnuity(tenor, forwardCurve);

    // Assert
    assertTrue(actualSwapAnnuity instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualSwapAnnuity.getAverage(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMax(), 0.0);
    assertEquals(Double.NaN, actualSwapAnnuity.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, actualSwapAnnuity.getRealizations(), 0.0);
  }
}
