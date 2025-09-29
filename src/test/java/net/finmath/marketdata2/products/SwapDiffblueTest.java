package net.finmath.marketdata2.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
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
import net.finmath.marketdata2.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata2.model.curves.ForwardCurveInterface;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.RandomVariableLazyEvaluation;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.Period;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.ScheduleFromPeriods;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SwapDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Swap#Swap(AnalyticProduct, AnalyticProduct)}
   *   <li>{@link Swap#toString()}
   *   <li>{@link Swap#getLegPayer()}
   *   <li>{@link Swap#getLegReceiver()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Swap.<init>(AnalyticProduct, AnalyticProduct)",
    "AnalyticProduct Swap.getLegPayer()",
    "AnalyticProduct Swap.getLegReceiver()",
    "String Swap.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Cashflow legReceiver = new Cashflow("GBP", 10.0d, 10.0d, true, "3");
    Cashflow legPayer = new Cashflow("GBP", 10.0d, 10.0d, true, "3");

    // Act
    Swap actualSwap = new Swap(legReceiver, legPayer);
    actualSwap.toString();
    AnalyticProduct actualLegPayer = actualSwap.getLegPayer();
    AnalyticProduct actualLegReceiver = actualSwap.getLegReceiver();

    // Assert
    assertTrue(actualLegPayer instanceof Cashflow);
    assertTrue(actualLegReceiver instanceof Cashflow);
    assertSame(legPayer, actualLegPayer);
    assertSame(legReceiver, actualLegReceiver);
  }

  /**
   * Test {@link Swap#Swap(Schedule, String, double, String, Schedule, String, double, String)}.
   *
   * <ul>
   *   <li>When {@code 3}.
   *   <li>Then return LegPayer DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#Swap(Schedule, String, double, String, Schedule, String,
   * double, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Swap.<init>(Schedule, String, double, String, Schedule, String, double, String)"
  })
  public void testNewSwap_when3_thenReturnLegPayerDiscountCurveNameIs3() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule schedulePayLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Swap actualSwap =
        new Swap(
            scheduleReceiveLeg,
            "Forward Curve Receive Name",
            10.0d,
            "3",
            schedulePayLeg,
            "Forward Curve Pay Name",
            10.0d,
            "3");

    // Assert
    AnalyticProduct legPayer = actualSwap.getLegPayer();
    assertTrue(legPayer instanceof SwapLeg);
    AnalyticProduct legReceiver = actualSwap.getLegReceiver();
    assertTrue(legReceiver instanceof SwapLeg);
    Schedule schedule = ((SwapLeg) legPayer).getSchedule();
    assertTrue(schedule instanceof RegularSchedule);
    Schedule schedule2 = ((SwapLeg) legReceiver).getSchedule();
    assertTrue(schedule2 instanceof RegularSchedule);
    assertEquals("3", ((SwapLeg) legPayer).getDiscountCurveName());
    assertEquals("3", ((SwapLeg) legReceiver).getDiscountCurveName());
    assertEquals("Forward Curve Pay Name", ((SwapLeg) legPayer).getForwardCurveName());
    assertEquals("Forward Curve Receive Name", ((SwapLeg) legReceiver).getForwardCurveName());
    assertEquals(10.0d, ((SwapLeg) legPayer).getSpread(), 0.0);
    assertEquals(10.0d, ((SwapLeg) legReceiver).getSpread(), 0.0);
    assertTrue(((SwapLeg) legPayer).isNotionalExchanged());
    assertTrue(((SwapLeg) legReceiver).isNotionalExchanged());
    assertSame(schedulePayLeg, schedule);
    assertSame(scheduleReceiveLeg, schedule2);
  }

  /**
   * Test {@link Swap#Swap(Schedule, String, double, String, Schedule, String, double, String,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code 3}.
   *   <li>Then return LegPayer DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#Swap(Schedule, String, double, String, Schedule, String,
   * double, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Swap.<init>(Schedule, String, double, String, Schedule, String, double, String, boolean)"
  })
  public void testNewSwap_when3_thenReturnLegPayerDiscountCurveNameIs32() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule schedulePayLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Swap actualSwap =
        new Swap(
            scheduleReceiveLeg,
            "Forward Curve Receive Name",
            10.0d,
            "3",
            schedulePayLeg,
            "Forward Curve Pay Name",
            10.0d,
            "3",
            true);

    // Assert
    AnalyticProduct legPayer = actualSwap.getLegPayer();
    assertTrue(legPayer instanceof SwapLeg);
    AnalyticProduct legReceiver = actualSwap.getLegReceiver();
    assertTrue(legReceiver instanceof SwapLeg);
    Schedule schedule = ((SwapLeg) legPayer).getSchedule();
    assertTrue(schedule instanceof RegularSchedule);
    Schedule schedule2 = ((SwapLeg) legReceiver).getSchedule();
    assertTrue(schedule2 instanceof RegularSchedule);
    assertEquals("3", ((SwapLeg) legPayer).getDiscountCurveName());
    assertEquals("3", ((SwapLeg) legReceiver).getDiscountCurveName());
    assertEquals("Forward Curve Pay Name", ((SwapLeg) legPayer).getForwardCurveName());
    assertEquals("Forward Curve Receive Name", ((SwapLeg) legReceiver).getForwardCurveName());
    assertEquals(10.0d, ((SwapLeg) legPayer).getSpread(), 0.0);
    assertEquals(10.0d, ((SwapLeg) legReceiver).getSpread(), 0.0);
    assertTrue(((SwapLeg) legPayer).isNotionalExchanged());
    assertTrue(((SwapLeg) legReceiver).isNotionalExchanged());
    assertSame(schedulePayLeg, schedule);
    assertSame(scheduleReceiveLeg, schedule2);
  }

  /**
   * Test {@link Swap#Swap(Schedule, String, double, String, Schedule, String, double, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return LegPayer DiscountCurveName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link Swap#Swap(Schedule, String, double, String, Schedule, String,
   * double, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Swap.<init>(Schedule, String, double, String, Schedule, String, double, String)"
  })
  public void testNewSwap_whenEmptyString_thenReturnLegPayerDiscountCurveNameIsEmptyString() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule schedulePayLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Swap actualSwap =
        new Swap(
            scheduleReceiveLeg,
            "Forward Curve Receive Name",
            10.0d,
            "",
            schedulePayLeg,
            "Forward Curve Pay Name",
            10.0d,
            "");

    // Assert
    AnalyticProduct legPayer = actualSwap.getLegPayer();
    assertTrue(legPayer instanceof SwapLeg);
    AnalyticProduct legReceiver = actualSwap.getLegReceiver();
    assertTrue(legReceiver instanceof SwapLeg);
    Schedule schedule = ((SwapLeg) legPayer).getSchedule();
    assertTrue(schedule instanceof RegularSchedule);
    Schedule schedule2 = ((SwapLeg) legReceiver).getSchedule();
    assertTrue(schedule2 instanceof RegularSchedule);
    assertEquals("", ((SwapLeg) legPayer).getDiscountCurveName());
    assertEquals("", ((SwapLeg) legReceiver).getDiscountCurveName());
    assertEquals("Forward Curve Pay Name", ((SwapLeg) legPayer).getForwardCurveName());
    assertEquals("Forward Curve Receive Name", ((SwapLeg) legReceiver).getForwardCurveName());
    assertEquals(10.0d, ((SwapLeg) legPayer).getSpread(), 0.0);
    assertEquals(10.0d, ((SwapLeg) legReceiver).getSpread(), 0.0);
    assertTrue(((SwapLeg) legPayer).isNotionalExchanged());
    assertTrue(((SwapLeg) legReceiver).isNotionalExchanged());
    assertSame(schedulePayLeg, schedule);
    assertSame(scheduleReceiveLeg, schedule2);
  }

  /**
   * Test {@link Swap#Swap(Schedule, String, double, String, Schedule, String, double, String,
   * boolean)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return LegPayer DiscountCurveName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link Swap#Swap(Schedule, String, double, String, Schedule, String,
   * double, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Swap.<init>(Schedule, String, double, String, Schedule, String, double, String, boolean)"
  })
  public void testNewSwap_whenEmptyString_thenReturnLegPayerDiscountCurveNameIsEmptyString2() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule schedulePayLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Swap actualSwap =
        new Swap(
            scheduleReceiveLeg,
            "Forward Curve Receive Name",
            10.0d,
            "",
            schedulePayLeg,
            "Forward Curve Pay Name",
            10.0d,
            "",
            true);

    // Assert
    AnalyticProduct legPayer = actualSwap.getLegPayer();
    assertTrue(legPayer instanceof SwapLeg);
    AnalyticProduct legReceiver = actualSwap.getLegReceiver();
    assertTrue(legReceiver instanceof SwapLeg);
    Schedule schedule = ((SwapLeg) legPayer).getSchedule();
    assertTrue(schedule instanceof RegularSchedule);
    Schedule schedule2 = ((SwapLeg) legReceiver).getSchedule();
    assertTrue(schedule2 instanceof RegularSchedule);
    assertEquals("", ((SwapLeg) legPayer).getDiscountCurveName());
    assertEquals("", ((SwapLeg) legReceiver).getDiscountCurveName());
    assertEquals("Forward Curve Pay Name", ((SwapLeg) legPayer).getForwardCurveName());
    assertEquals("Forward Curve Receive Name", ((SwapLeg) legReceiver).getForwardCurveName());
    assertEquals(10.0d, ((SwapLeg) legPayer).getSpread(), 0.0);
    assertEquals(10.0d, ((SwapLeg) legReceiver).getSpread(), 0.0);
    assertTrue(((SwapLeg) legPayer).isNotionalExchanged());
    assertTrue(((SwapLeg) legReceiver).isNotionalExchanged());
    assertSame(schedulePayLeg, schedule);
    assertSame(scheduleReceiveLeg, schedule2);
  }

  /**
   * Test {@link Swap#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <p>Method under test: {@link Swap#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Swap.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel() {
    // Arrange
    Scalar scalar = mock(Scalar.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(scalar.sub(Mockito.<RandomVariable>any())).thenReturn(randomVariableFromDoubleArray);

    AnalyticProduct legReceiver = mock(AnalyticProduct.class);
    when(legReceiver.getValue(anyDouble(), Mockito.<AnalyticModel>any())).thenReturn(scalar);

    AnalyticProduct legPayer = mock(AnalyticProduct.class);
    when(legPayer.getValue(anyDouble(), Mockito.<AnalyticModel>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Swap swap = new Swap(legReceiver, legPayer);

    // Act
    RandomVariable actualValue = swap.getValue(10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(legReceiver).getValue(eq(10.0d), isA(AnalyticModel.class));
    verify(legPayer).getValue(eq(10.0d), isA(AnalyticModel.class));
    verify(scalar).sub(isA(RandomVariable.class));
    assertSame(randomVariableFromDoubleArray, actualValue);
  }

  /**
   * Test {@link Swap#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <ul>
   *   <li>Then return Average is {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Swap.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnAverageIs00() {
    // Arrange
    AnalyticProduct legReceiver = mock(AnalyticProduct.class);
    when(legReceiver.getValue(anyDouble(), Mockito.<AnalyticModel>any()))
        .thenReturn(Scalar.of(10.0d));

    AnalyticProduct legPayer = mock(AnalyticProduct.class);
    when(legPayer.getValue(anyDouble(), Mockito.<AnalyticModel>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Swap swap = new Swap(legReceiver, legPayer);

    // Act
    RandomVariable actualValue = swap.getValue(10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(legReceiver).getValue(eq(10.0d), isA(AnalyticModel.class));
    verify(legPayer).getValue(eq(10.0d), isA(AnalyticModel.class));
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(-0.0d, actualValue.getAverage(), 0.0);
    assertEquals(-0.0d, actualValue.getMax(), 0.0);
    assertEquals(-0.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {-0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Swap.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    AnalyticProduct legReceiver = mock(AnalyticProduct.class);
    when(legReceiver.getValue(anyDouble(), Mockito.<AnalyticModel>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    AnalyticProduct legPayer = mock(AnalyticProduct.class);
    when(legPayer.getValue(anyDouble(), Mockito.<AnalyticModel>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Swap swap = new Swap(legReceiver, legPayer);

    // Act
    RandomVariable actualValue = swap.getValue(10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(legReceiver).getValue(eq(10.0d), isA(AnalyticModel.class));
    verify(legPayer).getValue(eq(10.0d), isA(AnalyticModel.class));
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualValue.getAverage(), 0.0);
    assertEquals(0.0d, actualValue.getMax(), 0.0);
    assertEquals(0.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Swap.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    AnalyticProduct legReceiver = mock(AnalyticProduct.class);
    when(legReceiver.getValue(anyDouble(), Mockito.<AnalyticModel>any()))
        .thenReturn(new RandomVariableFromFloatArray(10.0d));

    AnalyticProduct legPayer = mock(AnalyticProduct.class);
    when(legPayer.getValue(anyDouble(), Mockito.<AnalyticModel>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Swap swap = new Swap(legReceiver, legPayer);

    // Act
    RandomVariable actualValue = swap.getValue(10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(legReceiver).getValue(eq(10.0d), isA(AnalyticModel.class));
    verify(legPayer).getValue(eq(10.0d), isA(AnalyticModel.class));
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
    assertEquals(0.0d, actualValue.getAverage(), 0.0);
    assertEquals(0.0d, actualValue.getMax(), 0.0);
    assertEquals(0.0d, actualValue.getMin(), 0.0);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Swap.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnRandomVariableLazyEvaluation() {
    // Arrange
    AnalyticProduct legReceiver = mock(AnalyticProduct.class);
    when(legReceiver.getValue(anyDouble(), Mockito.<AnalyticModel>any()))
        .thenReturn(new RandomVariableLazyEvaluation(10.0d));

    AnalyticProduct legPayer = mock(AnalyticProduct.class);
    when(legPayer.getValue(anyDouble(), Mockito.<AnalyticModel>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Swap swap = new Swap(legReceiver, legPayer);

    // Act
    RandomVariable actualValue = swap.getValue(10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(legReceiver).getValue(eq(10.0d), isA(AnalyticModel.class));
    verify(legPayer).getValue(eq(10.0d), isA(AnalyticModel.class));
    assertTrue(actualValue instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.variance() instanceof RandomVariableLazyEvaluation);
    assertNull(actualValue.getOperator());
    assertEquals(0, actualValue.getTypePriority());
    assertEquals(0.0d, actualValue.getAverage(), 0.0);
    assertEquals(0.0d, actualValue.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualValue.getMax(), 0.0);
    assertEquals(0.0d, actualValue.getMin(), 0.0);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.isDeterministic());
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve3() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(1210.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(1210.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(1210.0d, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(new double[] {1210.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve4() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(1.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(121.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(121.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(121.0d, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(new double[] {121.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve5() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromFloatArray(1.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualForwardSwapRate.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardError(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getVariance(), 0.0);
    assertEquals(1, actualForwardSwapRate.getTypePriority());
    assertEquals(1, actualForwardSwapRate.size());
    assertEquals(121.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(121.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(121.0d, actualForwardSwapRate.getMin(), 0.0);
    assertTrue(actualForwardSwapRate.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {121.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve6() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableLazyEvaluation(1.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate instanceof RandomVariableLazyEvaluation);
    assertNull(actualForwardSwapRate.getOperator());
    assertEquals(0, actualForwardSwapRate.getTypePriority());
    assertEquals(0.0d, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardError(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getVariance(), 0.0);
    assertEquals(1, actualForwardSwapRate.size());
    assertEquals(121.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(121.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(121.0d, actualForwardSwapRate.getMin(), 0.0);
    assertTrue(actualForwardSwapRate.isDeterministic());
    assertArrayEquals(new double[] {121.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve7() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertEquals(2420.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(2420.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(2420.0d, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(new double[] {2420.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve8() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAADPathwise.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(2420.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(2420.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(2420.0d, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(new double[] {2420.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve9() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(Scalar.of(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2420.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve10() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromFloatArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2420.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve11() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableLazyEvaluation(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {2420.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve12() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar4);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve13() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar4);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertEquals(2.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(2.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(2.0d, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(new double[] {2.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve14() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar4);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualForwardSwapRate.size());
    assertFalse(actualForwardSwapRate.isDeterministic());
    assertArrayEquals(
        new double[] {2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d},
        actualForwardSwapRate.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve15() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(1.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);
    when(scalar.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableDifferentiableAAD);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar4);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertEquals(20.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(20.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(20.0d, actualForwardSwapRate.getMin(), 0.0);
    assertSame(factory, ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getFactory());
    assertArrayEquals(new double[] {20.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve16() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAADPathwise.of(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar4);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(2.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(2.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(2.0d, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(new double[] {2.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve17() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(
            10.0d, new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});
    when(scalar.mult(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableDifferentiableAADPathwise);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar4);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate).getRandomVariable()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualForwardSwapRate.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(Double.NaN, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(Double.NaN, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(
        new double[] {20.0d, Double.NaN, 20.0d, Double.NaN},
        actualForwardSwapRate.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve18() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.mult(anyDouble())).thenReturn(Scalar.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar4);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve19() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.doubleValue()).thenReturn(10.0d);
    when(scalar4.isDeterministic()).thenReturn(true);
    when(scalar4.getFiltrationTime()).thenReturn(10.0d);
    when(scalar4.getTypePriority()).thenReturn(1);
    when(scalar4.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.mult(anyDouble())).thenReturn(scalar4);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4).add(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).doubleValue();
    verify(scalar4, atLeast(1)).getFiltrationTime();
    verify(scalar4, atLeast(1)).getTypePriority();
    verify(scalar4, atLeast(1)).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(1.8d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(1.8d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(1.8d, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(new double[] {1.8d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel3() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve, null);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel4() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(1210.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(1210.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(1210.0d, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(new double[] {1210.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel5() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(1.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(121.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(121.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(121.0d, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(new double[] {121.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel6() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromFloatArray(1.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualForwardSwapRate.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardError(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getVariance(), 0.0);
    assertEquals(1, actualForwardSwapRate.getTypePriority());
    assertEquals(1, actualForwardSwapRate.size());
    assertEquals(121.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(121.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(121.0d, actualForwardSwapRate.getMin(), 0.0);
    assertTrue(actualForwardSwapRate.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {121.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel7() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableLazyEvaluation(1.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate instanceof RandomVariableLazyEvaluation);
    assertNull(actualForwardSwapRate.getOperator());
    assertEquals(0, actualForwardSwapRate.getTypePriority());
    assertEquals(0.0d, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardError(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getVariance(), 0.0);
    assertEquals(1, actualForwardSwapRate.size());
    assertEquals(121.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(121.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(121.0d, actualForwardSwapRate.getMin(), 0.0);
    assertTrue(actualForwardSwapRate.isDeterministic());
    assertArrayEquals(new double[] {121.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel8() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertEquals(2420.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(2420.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(2420.0d, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(new double[] {2420.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel9() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAADPathwise.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(2420.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(2420.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(2420.0d, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(new double[] {2420.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel10() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(Scalar.of(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2420.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel11() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromFloatArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2420.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel12() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableLazyEvaluation(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {2420.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel13() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar4);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel14() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar4);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertEquals(2.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(2.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(2.0d, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(new double[] {2.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel15() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar4);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualForwardSwapRate.size());
    assertFalse(actualForwardSwapRate.isDeterministic());
    assertArrayEquals(
        new double[] {2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d},
        actualForwardSwapRate.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel16() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(1.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);
    when(scalar.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableDifferentiableAAD);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar4);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertEquals(20.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(20.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(20.0d, actualForwardSwapRate.getMin(), 0.0);
    assertSame(factory, ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getFactory());
    assertArrayEquals(new double[] {20.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel17() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAADPathwise.of(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar4);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(2.0d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(2.0d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(2.0d, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(new double[] {2.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel18() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar4);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    AnalyticModelFromCurvesAndVols model = mock(AnalyticModelFromCurvesAndVols.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(null);

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve, model);

    // Assert
    verify(model).getDiscountCurve("3");
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel19() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    AnalyticModelFromCurvesAndVols model = mock(AnalyticModelFromCurvesAndVols.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve, model);

    // Assert
    verify(model).getDiscountCurve("3");
    verify(forwardCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar, atLeast(1)).mult(0.5d);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel20() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);
    when(scalar.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble())).thenReturn(scalar);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar2);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    AnalyticModelFromCurvesAndVols model = mock(AnalyticModelFromCurvesAndVols.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve, model);

    // Assert
    verify(model).getDiscountCurve("3");
    verify(forwardCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar).add(isA(RandomVariable.class));
    verify(scalar, atLeast(1)).doubleValue();
    verify(scalar, atLeast(1)).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar, atLeast(1)).isDeterministic();
    verify(scalar2, atLeast(1)).mult(0.5d);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(1.8d, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(1.8d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(1.8d, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(new double[] {1.8d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel21() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    TenorFromArray timeDiscretization =
        new TenorFromArray(
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization);

    Scalar scalar = mock(Scalar.class);
    when(scalar.div(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble())).thenReturn(scalar);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar2);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    AnalyticModelFromCurvesAndVols model = mock(AnalyticModelFromCurvesAndVols.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve, model);

    // Assert
    verify(model).getDiscountCurve("3");
    verify(forwardCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurve).getForward(isA(AnalyticModel.class), eq(Double.NEGATIVE_INFINITY));
    verify(randomVariable).mult(isA(RandomVariable.class));
    verify(scalar).div(isA(RandomVariable.class));
    verify(scalar2).mult(Double.POSITIVE_INFINITY);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {2.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel22() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    TenorFromArray timeDiscretization =
        new TenorFromArray(
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization);

    Scalar scalar = mock(Scalar.class);
    when(scalar.div(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(Double.NEGATIVE_INFINITY));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble())).thenReturn(scalar);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar2);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    AnalyticModelFromCurvesAndVols model = mock(AnalyticModelFromCurvesAndVols.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve, model);

    // Assert
    verify(model).getDiscountCurve("3");
    verify(forwardCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurve).getForward(isA(AnalyticModel.class), eq(Double.NEGATIVE_INFINITY));
    verify(randomVariable).mult(isA(RandomVariable.class));
    verify(scalar).div(isA(RandomVariable.class));
    verify(scalar2).mult(Double.POSITIVE_INFINITY);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.NEGATIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)}
   * with {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel23() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    TenorFromArray timeDiscretization =
        new TenorFromArray(
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization);

    Scalar scalar = mock(Scalar.class);
    when(scalar.div(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.div(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.mult(anyDouble())).thenReturn(scalar2);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    AnalyticModelFromCurvesAndVols model = mock(AnalyticModelFromCurvesAndVols.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve, model);

    // Assert
    verify(model).getDiscountCurve("3");
    verify(forwardCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurve).getForward(isA(AnalyticModel.class), eq(Double.NEGATIVE_INFINITY));
    verify(randomVariable).mult(isA(RandomVariable.class));
    verify(scalar2).div(isA(RandomVariable.class));
    verify(scalar).div(isA(RandomVariable.class));
    verify(scalar3).mult(Double.POSITIVE_INFINITY);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <ul>
   *   <li>Then calls {@link Scalar#div(RandomVariable)}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve_thenCallsDiv() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    TenorFromArray timeDiscretization =
        new TenorFromArray(
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization);

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.div(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.div(Mockito.<RandomVariable>any())).thenReturn(scalar4);

    Scalar scalar6 = mock(Scalar.class);
    when(scalar6.mult(anyDouble())).thenReturn(scalar5);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar6);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar5).div(isA(RandomVariable.class));
    verify(scalar4).div(isA(RandomVariable.class));
    verify(scalar6).mult(Double.POSITIVE_INFINITY);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <ul>
   *   <li>Then return Max is {@code 0.2}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve_thenReturnMaxIs02() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.pow(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.add(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(
            10.0d, new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});
    when(scalar4.mult(anyDouble())).thenReturn(randomVariableDifferentiableAADPathwise);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar4);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar3);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar3, atLeast(1)).add(1.0d);
    verify(scalar4, atLeast(1)).mult(0.5d);
    verify(scalar, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).pow(-1.0d);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate).getRandomVariable()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualForwardSwapRate)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualForwardSwapRate.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.2d, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.2d, Double.NEGATIVE_INFINITY, 0.2d, Double.NEGATIVE_INFINITY},
        actualForwardSwapRate.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve() {
    // Arrange
    TenorFromArray fixTenor = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve2() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve3() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve4() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(1.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve5() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromFloatArray(1.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromFloatArray);
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve6() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableLazyEvaluation(1.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate instanceof RandomVariableLazyEvaluation);
    assertNull(actualForwardSwapRate.getOperator());
    assertEquals(0, actualForwardSwapRate.getTypePriority());
    assertEquals(0.0d, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardError(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getVariance(), 0.0);
    assertEquals(1, actualForwardSwapRate.size());
    assertTrue(actualForwardSwapRate.isDeterministic());
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve7() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve8() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAADPathwise.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualForwardSwapRate.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardError(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getVariance(), 0.0);
    assertEquals(1, actualForwardSwapRate.size());
    assertTrue(actualForwardSwapRate.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve9() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(Scalar.of(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve10() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromFloatArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve11() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableLazyEvaluation(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve12() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.get(anyInt())).thenReturn(10.0d);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.mult(anyDouble())).thenReturn(new RandomVariableFromFloatArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar).doubleValue();
    verify(scalar).get(0);
    verify(scalar, atLeast(1)).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar, atLeast(1)).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromFloatArray);
    assertEquals(10.0d, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve13() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.getValues()).thenReturn(new RandomVariableFromFloatArray(10.0d));
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(scalar, atLeast(1)).getValues();
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve14() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.getValues()).thenReturn(RandomVariableDifferentiableAADPathwise.of(10.0d));
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(scalar, atLeast(1)).getValues();
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualForwardSwapRate.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve15() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.get(anyInt())).thenReturn(10.0d);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);
    when(scalar.size()).thenReturn(3);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(
            10.0d, new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});
    when(scalar5.mult(anyDouble())).thenReturn(randomVariableDifferentiableAADPathwise);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar).doubleValue();
    verify(scalar, atLeast(1)).get(anyInt());
    verify(scalar, atLeast(1)).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(scalar).size();
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(10.0d, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertEquals(4, actualForwardSwapRate.size());
    assertFalse(actualForwardSwapRate.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardSwapRate.getMin(), 0.0);
    assertEquals(Double.NaN, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(Double.NaN, actualForwardSwapRate.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualForwardSwapRate.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualForwardSwapRate.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualForwardSwapRate.getVariance(), 0.0);
    assertArrayEquals(
        new double[] {
          Double.POSITIVE_INFINITY,
          Double.NEGATIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.NEGATIVE_INFINITY
        },
        actualForwardSwapRate.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve16() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor =
        new TenorFromArray(
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.div(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    Scalar scalar6 = mock(Scalar.class);
    when(scalar6.mult(anyDouble())).thenReturn(scalar5);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar6);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(0.0d);
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar5).div(isA(RandomVariable.class));
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar6).mult(Double.POSITIVE_INFINITY);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve17() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor =
        new TenorFromArray(
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.div(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar6 = mock(Scalar.class);
    when(scalar6.div(Mockito.<RandomVariable>any())).thenReturn(scalar5);

    Scalar scalar7 = mock(Scalar.class);
    when(scalar7.mult(anyDouble())).thenReturn(scalar6);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar7);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(0.0d);
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar6).div(isA(RandomVariable.class));
    verify(scalar5).div(isA(RandomVariable.class));
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar7).mult(Double.POSITIVE_INFINITY);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve() {
    // Arrange
    TenorFromArray fixTenor = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve2() {
    // Arrange
    TenorFromArray fixTenor = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve, null);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve3() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve4() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve5() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(1.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve6() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromFloatArray(1.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromFloatArray);
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve7() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableLazyEvaluation(1.0d));
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualForwardSwapRate instanceof RandomVariableLazyEvaluation);
    assertNull(actualForwardSwapRate.getOperator());
    assertEquals(0, actualForwardSwapRate.getTypePriority());
    assertEquals(0.0d, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardError(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getVariance(), 0.0);
    assertEquals(1, actualForwardSwapRate.size());
    assertTrue(actualForwardSwapRate.isDeterministic());
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve8() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve9() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAADPathwise.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualForwardSwapRate.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getStandardError(), 0.0);
    assertEquals(0.0d, actualForwardSwapRate.getVariance(), 0.0);
    assertEquals(1, actualForwardSwapRate.size());
    assertTrue(actualForwardSwapRate.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve10() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(Scalar.of(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve11() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableFromFloatArray(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve12() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(randomVariable.mult(anyDouble())).thenReturn(new RandomVariableLazyEvaluation(10.0d));

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar, atLeast(1)).mult(0.5d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve13() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.invert()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.mult(anyDouble())).thenReturn(Scalar.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).invert();
    verify(scalar).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve14() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.get(anyInt())).thenReturn(10.0d);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.mult(anyDouble())).thenReturn(new RandomVariableFromFloatArray(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar).doubleValue();
    verify(scalar).get(0);
    verify(scalar, atLeast(1)).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar, atLeast(1)).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromFloatArray);
    assertEquals(10.0d, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve15() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.getValues()).thenReturn(new RandomVariableFromFloatArray(10.0d));
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(scalar, atLeast(1)).getValues();
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve16() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.getValues()).thenReturn(RandomVariableDifferentiableAADPathwise.of(10.0d));
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(scalar, atLeast(1)).getValues();
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualForwardSwapRate).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualForwardSwapRate.getValues() instanceof RandomVariableDifferentiableAADPathwise);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve17() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar.isDeterministic()).thenReturn(false);
    when(scalar.get(anyInt())).thenReturn(10.0d);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);
    when(scalar.size()).thenReturn(3);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(scalar, atLeast(1)).getValues();
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar, atLeast(1)).get(anyInt());
    verify(scalar).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(scalar).size();
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertEquals(3, actualForwardSwapRate.size());
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
        actualForwardSwapRate.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve18() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.get(anyInt())).thenReturn(10.0d);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);
    when(scalar.size()).thenReturn(3);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(
            10.0d, new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});
    when(scalar5.mult(anyDouble())).thenReturn(randomVariableDifferentiableAADPathwise);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar).doubleValue();
    verify(scalar, atLeast(1)).get(anyInt());
    verify(scalar, atLeast(1)).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(scalar).size();
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(10.0d, actualForwardSwapRate.getFiltrationTime(), 0.0);
    assertEquals(4, actualForwardSwapRate.size());
    assertFalse(actualForwardSwapRate.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardSwapRate.getMin(), 0.0);
    assertEquals(Double.NaN, actualForwardSwapRate.getAverage(), 0.0);
    assertEquals(Double.NaN, actualForwardSwapRate.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualForwardSwapRate.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualForwardSwapRate.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualForwardSwapRate.getVariance(), 0.0);
    assertArrayEquals(
        new double[] {
          Double.POSITIVE_INFINITY,
          Double.NEGATIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.NEGATIVE_INFINITY
        },
        actualForwardSwapRate.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve19() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(
            10.0d, new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});
    when(scalar.getValues()).thenReturn(randomVariableDifferentiableAADPathwise);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(scalar, atLeast(1)).getValues();
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertEquals(4, actualForwardSwapRate.size());
    assertEquals(Double.NaN, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(Double.NaN, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY, Double.NaN, Double.POSITIVE_INFINITY, Double.NaN},
        actualForwardSwapRate.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve20() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.doubleValue()).thenReturn(10.0d);
    when(scalar5.isDeterministic()).thenReturn(true);
    when(scalar5.getFiltrationTime()).thenReturn(10.0d);
    when(scalar5.getTypePriority()).thenReturn(1);
    when(scalar5.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar6 = mock(Scalar.class);
    when(scalar6.mult(anyDouble())).thenReturn(scalar5);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar6);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar5).add(isA(RandomVariable.class));
    verify(scalar, atLeast(1)).doubleValue();
    verify(scalar5, atLeast(1)).doubleValue();
    verify(scalar, atLeast(1)).getFiltrationTime();
    verify(scalar5, atLeast(1)).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar5, atLeast(1)).getTypePriority();
    verify(scalar, atLeast(1)).isDeterministic();
    verify(scalar5, atLeast(1)).isDeterministic();
    verify(scalar6, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve21() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor =
        new TenorFromArray(
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.div(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    Scalar scalar6 = mock(Scalar.class);
    when(scalar6.mult(anyDouble())).thenReturn(scalar5);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar6);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(0.0d);
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar5).div(isA(RandomVariable.class));
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar6).mult(Double.POSITIVE_INFINITY);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)} with {@code fixTenor}, {@code floatTenor},
   * {@code forwardCurve}, {@code discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface, DiscountCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface, DiscountCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve22() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor =
        new TenorFromArray(
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.div(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar6 = mock(Scalar.class);
    when(scalar6.div(Mockito.<RandomVariable>any())).thenReturn(scalar5);

    Scalar scalar7 = mock(Scalar.class);
    when(scalar7.mult(anyDouble())).thenReturn(scalar6);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar7);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");
    when(forwardCurve.getDiscountCurveName()).thenReturn("3");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve).getDiscountCurveName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(0.0d);
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar6).div(isA(RandomVariable.class));
    verify(scalar5).div(isA(RandomVariable.class));
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar7).mult(Double.POSITIVE_INFINITY);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <ul>
   *   <li>Then calls {@link Scalar#add(RandomVariable)}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve_thenCallsAdd() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.doubleValue()).thenReturn(10.0d);
    when(scalar5.isDeterministic()).thenReturn(true);
    when(scalar5.getFiltrationTime()).thenReturn(10.0d);
    when(scalar5.getTypePriority()).thenReturn(1);
    when(scalar5.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar6 = mock(Scalar.class);
    when(scalar6.mult(anyDouble())).thenReturn(scalar5);

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar6);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar5).add(isA(RandomVariable.class));
    verify(scalar, atLeast(1)).doubleValue();
    verify(scalar5, atLeast(1)).doubleValue();
    verify(scalar, atLeast(1)).getFiltrationTime();
    verify(scalar5, atLeast(1)).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar5, atLeast(1)).getTypePriority();
    verify(scalar, atLeast(1)).isDeterministic();
    verify(scalar5, atLeast(1)).isDeterministic();
    verify(scalar6, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <ul>
   *   <li>Then calls {@link Scalar#invert()}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve_thenCallsInvert() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.invert()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.mult(anyDouble())).thenReturn(Scalar.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).invert();
    verify(scalar).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY}, actualForwardSwapRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <ul>
   *   <li>Then return Max is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve_thenReturnMaxIsNaN() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(
            10.0d, new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});
    when(scalar.getValues()).thenReturn(randomVariableDifferentiableAADPathwise);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(scalar, atLeast(1)).getValues();
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertEquals(4, actualForwardSwapRate.size());
    assertEquals(Double.NaN, actualForwardSwapRate.getMax(), 0.0);
    assertEquals(Double.NaN, actualForwardSwapRate.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY, Double.NaN, Double.POSITIVE_INFINITY, Double.NaN},
        actualForwardSwapRate.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <ul>
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurveInterface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurveInterface)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve_thenReturnSizeIsThree() {
    // Arrange
    TimeDiscretization fixTenor = mock(TimeDiscretization.class);
    when(fixTenor.getTime(anyInt())).thenReturn(10.0d);
    when(fixTenor.getNumberOfTimeSteps()).thenReturn(10);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar.isDeterministic()).thenReturn(false);
    when(scalar.get(anyInt())).thenReturn(10.0d);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);
    when(scalar.size()).thenReturn(3);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.pow(anyDouble())).thenReturn(scalar2);

    Scalar scalar4 = mock(Scalar.class);
    when(scalar4.add(anyDouble())).thenReturn(scalar3);

    Scalar scalar5 = mock(Scalar.class);
    when(scalar5.mult(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any())).thenReturn(scalar5);
    when(randomVariable.mult(anyDouble())).thenReturn(scalar4);

    ForwardCurveFromDiscountCurve forwardCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(randomVariable);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getName()).thenReturn("Name");

    // Act
    RandomVariable actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    verify(forwardCurve, atLeast(1)).getName();
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurve, atLeast(1)).getForward(isA(AnalyticModel.class), anyDouble());
    verify(scalar, atLeast(1)).getValues();
    verify(randomVariable, atLeast(1)).mult(anyDouble());
    verify(randomVariable, atLeast(1)).mult(isA(RandomVariable.class));
    verify(scalar4, atLeast(1)).add(1.0d);
    verify(scalar, atLeast(1)).get(anyInt());
    verify(scalar).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar5, atLeast(1)).mult(0.5d);
    verify(scalar2, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(scalar3, atLeast(1)).pow(-1.0d);
    verify(scalar).size();
    verify(fixTenor, atLeast(1)).getNumberOfTimeSteps();
    verify(fixTenor, atLeast(1)).getTime(anyInt());
    assertTrue(actualForwardSwapRate.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardSwapRate instanceof RandomVariableDifferentiableAAD);
    assertEquals(3, actualForwardSwapRate.size());
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
        actualForwardSwapRate.getRealizations(),
        0.0);
  }
}
