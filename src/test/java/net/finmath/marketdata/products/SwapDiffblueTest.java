package net.finmath.marketdata.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
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
import java.util.ArrayList;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation.InterpolationEntityForward;
import net.finmath.modelling.InterestRateProductDescriptor;
import net.finmath.modelling.descriptor.InterestRateSwapLegProductDescriptor;
import net.finmath.modelling.descriptor.InterestRateSwapProductDescriptor;
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
   * Test {@link Swap#Swap(Schedule, double, String, Schedule, String, String)}.
   *
   * <ul>
   *   <li>When {@code 3}.
   *   <li>Then return LegPayer DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#Swap(Schedule, double, String, Schedule, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Swap.<init>(Schedule, double, String, Schedule, String, String)"})
  public void testNewSwap_when3_thenReturnLegPayerDiscountCurveNameIs3() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Swap actualSwap =
        new Swap(
            scheduleReceiveLeg,
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Pay Name",
            "3");

    // Assert
    AnalyticProduct legPayer = actualSwap.getLegPayer();
    assertTrue(legPayer instanceof SwapLeg);
    AnalyticProduct legReceiver = actualSwap.getLegReceiver();
    assertTrue(legReceiver instanceof SwapLeg);
    assertEquals("3", ((SwapLeg) legPayer).getDiscountCurveName());
    assertEquals("3", ((SwapLeg) legReceiver).getDiscountCurveName());
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        ((SwapLeg) legPayer).getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        ((SwapLeg) legReceiver).getSpreads(),
        0.0);
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
  public void testNewSwap_when3_thenReturnLegPayerDiscountCurveNameIs32() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Swap actualSwap =
        new Swap(
            scheduleReceiveLeg,
            "Forward Curve Receive Name",
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Pay Name",
            10.0d,
            "3");

    // Assert
    AnalyticProduct legPayer = actualSwap.getLegPayer();
    assertTrue(legPayer instanceof SwapLeg);
    AnalyticProduct legReceiver = actualSwap.getLegReceiver();
    assertTrue(legReceiver instanceof SwapLeg);
    assertEquals("3", ((SwapLeg) legPayer).getDiscountCurveName());
    assertEquals("3", ((SwapLeg) legReceiver).getDiscountCurveName());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        ((SwapLeg) legPayer).getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        ((SwapLeg) legReceiver).getSpreads(),
        0.0);
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
  public void testNewSwap_when3_thenReturnLegPayerDiscountCurveNameIs33() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Swap actualSwap =
        new Swap(
            scheduleReceiveLeg,
            "Forward Curve Receive Name",
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Pay Name",
            10.0d,
            "3",
            true);

    // Assert
    AnalyticProduct legPayer = actualSwap.getLegPayer();
    assertTrue(legPayer instanceof SwapLeg);
    AnalyticProduct legReceiver = actualSwap.getLegReceiver();
    assertTrue(legReceiver instanceof SwapLeg);
    assertEquals("3", ((SwapLeg) legPayer).getDiscountCurveName());
    assertEquals("3", ((SwapLeg) legReceiver).getDiscountCurveName());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        ((SwapLeg) legPayer).getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        ((SwapLeg) legReceiver).getSpreads(),
        0.0);
  }

  /**
   * Test {@link Swap#Swap(Schedule, double, String, Schedule, String, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return LegPayer DiscountCurveName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link Swap#Swap(Schedule, double, String, Schedule, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Swap.<init>(Schedule, double, String, Schedule, String, String)"})
  public void testNewSwap_whenEmptyString_thenReturnLegPayerDiscountCurveNameIsEmptyString() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Swap actualSwap =
        new Swap(
            scheduleReceiveLeg,
            10.0d,
            "",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Pay Name",
            "");

    // Assert
    AnalyticProduct legPayer = actualSwap.getLegPayer();
    assertTrue(legPayer instanceof SwapLeg);
    AnalyticProduct legReceiver = actualSwap.getLegReceiver();
    assertTrue(legReceiver instanceof SwapLeg);
    assertEquals("", ((SwapLeg) legPayer).getDiscountCurveName());
    assertEquals("", ((SwapLeg) legReceiver).getDiscountCurveName());
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        ((SwapLeg) legPayer).getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        ((SwapLeg) legReceiver).getSpreads(),
        0.0);
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
  public void testNewSwap_whenEmptyString_thenReturnLegPayerDiscountCurveNameIsEmptyString2() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Swap actualSwap =
        new Swap(
            scheduleReceiveLeg,
            "Forward Curve Receive Name",
            10.0d,
            "",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Pay Name",
            10.0d,
            "");

    // Assert
    AnalyticProduct legPayer = actualSwap.getLegPayer();
    assertTrue(legPayer instanceof SwapLeg);
    AnalyticProduct legReceiver = actualSwap.getLegReceiver();
    assertTrue(legReceiver instanceof SwapLeg);
    assertEquals("", ((SwapLeg) legPayer).getDiscountCurveName());
    assertEquals("", ((SwapLeg) legReceiver).getDiscountCurveName());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        ((SwapLeg) legPayer).getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        ((SwapLeg) legReceiver).getSpreads(),
        0.0);
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
  public void testNewSwap_whenEmptyString_thenReturnLegPayerDiscountCurveNameIsEmptyString3() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Swap actualSwap =
        new Swap(
            scheduleReceiveLeg,
            "Forward Curve Receive Name",
            10.0d,
            "",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Pay Name",
            10.0d,
            "",
            true);

    // Assert
    AnalyticProduct legPayer = actualSwap.getLegPayer();
    assertTrue(legPayer instanceof SwapLeg);
    AnalyticProduct legReceiver = actualSwap.getLegReceiver();
    assertTrue(legReceiver instanceof SwapLeg);
    assertEquals("", ((SwapLeg) legPayer).getDiscountCurveName());
    assertEquals("", ((SwapLeg) legReceiver).getDiscountCurveName());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        ((SwapLeg) legPayer).getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        ((SwapLeg) legReceiver).getSpreads(),
        0.0);
  }

  /**
   * Test {@link Swap#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <p>Method under test: {@link Swap#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swap.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {0.5d, 1.0d, 0.5d, 1.0d});
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(timeDiscretization);
    Swap swap =
        new Swap(
            scheduleReceiveLeg,
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Pay Name",
            "3");

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenThrow(new RuntimeException());
    when(forwardCurveFromDiscountCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> swap.getValue(10.0d, model));
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve(Mockito.<String>any());
    verify(forwardCurveFromDiscountCurve).getPaymentOffset(0.0d);
    verify(forwardCurveFromDiscountCurve).getForward(isA(AnalyticModel.class), eq(0.0d));
    verify(forwardCurveFromDiscountCurve).getForward(isA(AnalyticModel.class), eq(0.5d), eq(0.5d));
  }

  /**
   * Test {@link Swap#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <p>Method under test: {@link Swap#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swap.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel2() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    Swap swap =
        new Swap(
            scheduleReceiveLeg,
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Pay Name",
            "3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenThrow(new RuntimeException());

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> swap.getValue(10.0d, model));
    verify(model).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve).getReferenceDate();
  }

  /**
   * Test {@link Swap#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <p>Method under test: {@link Swap#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swap.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods scheduleReceiveLeg =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    Swap swap =
        new Swap(
            scheduleReceiveLeg,
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Pay Name",
            "3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(null);

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    double actualValue = swap.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve(Mockito.<String>any());
    verify(discountCurveFromForwardCurve, atLeast(1)).getReferenceDate();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), anyDouble(), eq(0.5d));
    assertEquals(-51.0d, actualValue, 0.0);
  }

  /**
   * Test {@link Swap#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <ul>
   *   <li>Then calls {@link ForwardCurveFromDiscountCurve#getPaymentOffset(double)}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swap.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenCallsGetPaymentOffset() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    Swap swap =
        new Swap(
            scheduleReceiveLeg,
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Pay Name",
            "3");

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenThrow(new RuntimeException());
    when(forwardCurveFromDiscountCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> swap.getValue(10.0d, model));
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve(Mockito.<String>any());
    verify(forwardCurveFromDiscountCurve).getPaymentOffset(0.0d);
    verify(forwardCurveFromDiscountCurve).getForward(isA(AnalyticModel.class), eq(0.0d));
    verify(forwardCurveFromDiscountCurve).getForward(isA(AnalyticModel.class), eq(10.0d), eq(0.5d));
  }

  /**
   * Test {@link Swap#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <ul>
   *   <li>Then return fifty.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swap.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnFifty() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    Swap swap =
        new Swap(
            scheduleReceiveLeg,
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Pay Name",
            "3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    double actualValue = swap.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve(Mockito.<String>any());
    verify(discountCurveFromForwardCurve, atLeast(1)).getReferenceDate();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), anyDouble(), eq(0.5d));
    assertEquals(50.0d, actualValue, 0.0);
  }

  /**
   * Test {@link Swap#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <ul>
   *   <li>Then return minus fifty-one.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swap.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnMinusFiftyOne() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods scheduleReceiveLeg =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    Swap swap =
        new Swap(
            scheduleReceiveLeg,
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Pay Name",
            "3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);

    AnalyticModel model = mock(AnalyticModel.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    double actualValue = swap.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve(Mockito.<String>any());
    verify(discountCurveFromForwardCurve, atLeast(1)).getReferenceDate();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), anyDouble(), eq(0.5d));
    assertEquals(-51.0d, actualValue, 0.0);
  }

  /**
   * Test {@link Swap#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swap.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnNaN() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    Swap swap =
        new Swap(
            scheduleReceiveLeg,
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Pay Name",
            "3");

    AnalyticModel model = mock(AnalyticModel.class);
    ForwardCurveInterpolation createForwardCurveFromDiscountFactorsResult =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {0.5d, 1.0d, 0.5d, 1.0d},
            new double[] {0.5d, 1.0d, 0.5d, 1.0d},
            0.5d);
    when(model.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromDiscountFactorsResult);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    double actualValue = swap.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve(Mockito.<String>any());
    assertEquals(Double.NaN, actualValue, 0.0);
  }

  /**
   * Test {@link Swap#getValue(double, AnalyticModel)} with {@code double}, {@code AnalyticModel}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swap.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnZero() {
    // Arrange
    AnalyticProduct legReceiver = mock(AnalyticProduct.class);
    when(legReceiver.getValue(anyDouble(), Mockito.<AnalyticModel>any())).thenReturn(10.0d);

    AnalyticProduct legPayer = mock(AnalyticProduct.class);
    when(legPayer.getValue(anyDouble(), Mockito.<AnalyticModel>any())).thenReturn(10.0d);

    Swap swap = new Swap(legReceiver, legPayer);

    // Act
    double actualValue = swap.getValue(10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(legReceiver).getValue(eq(10.0d), isA(AnalyticModel.class));
    verify(legPayer).getValue(eq(10.0d), isA(AnalyticModel.class));
    assertEquals(0.0d, actualValue, 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurve)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurve)"})
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);

    // Act
    double actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    assertEquals(Double.NaN, actualForwardSwapRate, 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurve, AnalyticModel)} with
   * {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurve,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurve, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);

    // Act
    double actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    assertEquals(Double.NaN, actualForwardSwapRate, 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurve, AnalyticModel)} with
   * {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurve,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurve, AnalyticModel)"
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
    RegularSchedule floatSchedule =
        new RegularSchedule(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    double actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixSchedule, floatSchedule, forwardCurve, new AnalyticModelFromCurvesAndVols());

    // Assert
    assertEquals(Double.NaN, actualForwardSwapRate, 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurve, AnalyticModel)} with
   * {@code fixSchedule}, {@code floatSchedule}, {@code forwardCurve}, {@code model}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurve,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurve, AnalyticModel)"
  })
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurveModel3() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);

    // Act
    double actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve, null);

    // Assert
    assertEquals(Double.NaN, actualForwardSwapRate, 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurve)} with {@code
   * fixSchedule}, {@code floatSchedule}, {@code forwardCurve}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(Schedule, Schedule, ForwardCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swap.getForwardSwapRate(Schedule, Schedule, ForwardCurve)"})
  public void testGetForwardSwapRateWithFixScheduleFloatScheduleForwardCurve_thenReturnNaN() {
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
    RegularSchedule floatSchedule =
        new RegularSchedule(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    double actualForwardSwapRate =
        Swap.getForwardSwapRate(fixSchedule, floatSchedule, forwardCurve);

    // Assert
    assertEquals(Double.NaN, actualForwardSwapRate, 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurve)} with
   * {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurve)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve() {
    // Arrange
    TenorFromArray fixTenor = new TenorFromArray(Double.NaN, 10, 0.5d);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromForwards(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            referenceDate,
            "42",
            InterpolationEntityForward.FORWARD,
            "3",
            new AnalyticModelFromCurvesAndVols(),
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d});

    // Act
    double actualForwardSwapRate = Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    assertEquals(Double.POSITIVE_INFINITY, actualForwardSwapRate, 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurve,
   * DiscountCurve)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}, {@code
   * discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurve, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurve, DiscountCurve)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve() {
    // Arrange
    TenorFromArray fixTenor = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);

    // Act
    double actualForwardSwapRate =
        Swap.getForwardSwapRate(
            fixTenor,
            floatTenor,
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    assertEquals(Double.NaN, actualForwardSwapRate, 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurve,
   * DiscountCurve)} with {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}, {@code
   * discountCurve}.
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurve, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurve, DiscountCurve)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurveDiscountCurve2() {
    // Arrange
    TenorFromArray fixTenor = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            -0.5d);

    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    when(discountCurve.getName()).thenReturn(null);

    // Act
    double actualForwardSwapRate =
        Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve, discountCurve);

    // Assert
    verify(discountCurve).getName();
    verify(discountCurve, atLeast(1)).getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    assertEquals(-0.666666666666667d, actualForwardSwapRate, 0.0);
  }

  /**
   * Test {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurve)} with
   * {@code fixTenor}, {@code floatTenor}, {@code forwardCurve}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getForwardSwapRate(TimeDiscretization, TimeDiscretization,
   * ForwardCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Swap.getForwardSwapRate(TimeDiscretization, TimeDiscretization, ForwardCurve)"
  })
  public void testGetForwardSwapRateWithFixTenorFloatTenorForwardCurve_thenReturnNaN() {
    // Arrange
    TenorFromArray fixTenor = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray floatTenor = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);

    // Act
    double actualForwardSwapRate = Swap.getForwardSwapRate(fixTenor, floatTenor, forwardCurve);

    // Assert
    assertEquals(Double.NaN, actualForwardSwapRate, 0.0);
  }

  /**
   * Test {@link Swap#getDescriptor()}.
   *
   * <ul>
   *   <li>Given {@link Swap#Swap(AnalyticProduct, AnalyticProduct)} with legReceiver is {@link
   *       Swap#Swap(AnalyticProduct, AnalyticProduct)} and legPayer is {@link
   *       Cashflow#Cashflow(String, double, double, boolean, String)}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InterestRateSwapProductDescriptor Swap.getDescriptor()"})
  public void testGetDescriptor_givenSwapWithLegReceiverIsSwapAndLegPayerIsCashflow() {
    // Arrange
    Cashflow legReceiver = new Cashflow("GBP", 10.0d, 10.0d, true, "3");
    Cashflow legPayer = new Cashflow("GBP", 10.0d, 10.0d, true, "3");

    Swap legReceiver2 = new Swap(legReceiver, legPayer);
    Cashflow legPayer2 = new Cashflow("GBP", 10.0d, 10.0d, true, "3");

    Swap swap = new Swap(legReceiver2, legPayer2);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> swap.getDescriptor());
  }

  /**
   * Test {@link Swap#getDescriptor()}.
   *
   * <ul>
   *   <li>Given {@link Swap#Swap(AnalyticProduct, AnalyticProduct)} with legReceiver is {@link
   *       Swap#Swap(AnalyticProduct, AnalyticProduct)} and legPayer is {@link
   *       Swap#Swap(AnalyticProduct, AnalyticProduct)}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InterestRateSwapProductDescriptor Swap.getDescriptor()"})
  public void testGetDescriptor_givenSwapWithLegReceiverIsSwapAndLegPayerIsSwap() {
    // Arrange
    Cashflow legReceiver = new Cashflow("GBP", 10.0d, 10.0d, true, "3");
    Cashflow legPayer = new Cashflow("GBP", 10.0d, 10.0d, true, "3");

    Swap legReceiver2 = new Swap(legReceiver, legPayer);
    Cashflow legReceiver3 = new Cashflow("GBP", 10.0d, 10.0d, true, "3");
    Cashflow legPayer2 = new Cashflow("GBP", 10.0d, 10.0d, true, "3");

    Swap legPayer3 = new Swap(legReceiver3, legPayer2);

    Swap swap = new Swap(legReceiver2, legPayer3);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> swap.getDescriptor());
  }

  /**
   * Test {@link Swap#getDescriptor()}.
   *
   * <ul>
   *   <li>Given {@link Swap#Swap(AnalyticProduct, AnalyticProduct)} with legReceiver is {@link
   *       Swap#Swap(Schedule, double, String, Schedule, String, String)} and legPayer is {@link
   *       Swap#Swap(AnalyticProduct, AnalyticProduct)}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InterestRateSwapProductDescriptor Swap.getDescriptor()"})
  public void testGetDescriptor_givenSwapWithLegReceiverIsSwapAndLegPayerIsSwap2() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    Swap legReceiver =
        new Swap(
            scheduleReceiveLeg,
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "One or both of the legs of this swap do not support extraction of a descriptor.",
            "3");
    Cashflow legReceiver2 = new Cashflow("GBP", 10.0d, 10.0d, true, "3");
    Cashflow legPayer = new Cashflow("GBP", 10.0d, 10.0d, true, "3");

    Swap legPayer2 = new Swap(legReceiver2, legPayer);

    Swap swap = new Swap(legReceiver, legPayer2);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> swap.getDescriptor());
  }

  /**
   * Test {@link Swap#getDescriptor()}.
   *
   * <ul>
   *   <li>Then LegPayer return {@link InterestRateSwapLegProductDescriptor}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InterestRateSwapProductDescriptor Swap.getDescriptor()"})
  public void testGetDescriptor_thenLegPayerReturnInterestRateSwapLegProductDescriptor() {
    // Arrange
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    InterestRateSwapProductDescriptor actualDescriptor =
        new Swap(
                scheduleReceiveLeg,
                10.0d,
                "3",
                new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
                "One or both of the legs of this swap do not support extraction of a descriptor.",
                "3")
            .getDescriptor();

    // Assert
    InterestRateProductDescriptor legPayer = actualDescriptor.getLegPayer();
    assertTrue(legPayer instanceof InterestRateSwapLegProductDescriptor);
    InterestRateProductDescriptor legReceiver = actualDescriptor.getLegReceiver();
    assertTrue(legReceiver instanceof InterestRateSwapLegProductDescriptor);
    assertEquals("3", ((InterestRateSwapLegProductDescriptor) legPayer).getDiscountCurveName());
    assertEquals("3", ((InterestRateSwapLegProductDescriptor) legReceiver).getDiscountCurveName());
    assertEquals(
        "One or both of the legs of this swap do not support extraction of a descriptor.",
        ((InterestRateSwapLegProductDescriptor) legPayer).getForwardCurveName());
    assertNull(((InterestRateSwapLegProductDescriptor) legReceiver).getForwardCurveName());
    assertTrue(((InterestRateSwapLegProductDescriptor) legPayer).isNotionalExchanged());
    assertTrue(((InterestRateSwapLegProductDescriptor) legReceiver).isNotionalExchanged());
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        ((InterestRateSwapLegProductDescriptor) legPayer).getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        ((InterestRateSwapLegProductDescriptor) legPayer).getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        ((InterestRateSwapLegProductDescriptor) legReceiver).getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        ((InterestRateSwapLegProductDescriptor) legReceiver).getSpreads(),
        0.0);
  }

  /**
   * Test {@link Swap#getDescriptor()}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InterestRateSwapProductDescriptor Swap.getDescriptor()"})
  public void testGetDescriptor_thenThrowRuntimeException() {
    // Arrange
    Cashflow legReceiver = new Cashflow("GBP", 10.0d, 10.0d, true, "3");
    Cashflow legPayer = new Cashflow("GBP", 10.0d, 10.0d, true, "3");

    Swap swap = new Swap(legReceiver, legPayer);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> swap.getDescriptor());
  }
}
