package net.finmath.singleswaprate.annuitymapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
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
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SimplifiedLinearAnnuityMappingDiffblueTest {
  /**
   * Test {@link SimplifiedLinearAnnuityMapping#SimplifiedLinearAnnuityMapping(Schedule, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then return SecondDerivative is ten is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * SimplifiedLinearAnnuityMapping#SimplifiedLinearAnnuityMapping(Schedule, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimplifiedLinearAnnuityMapping.<init>(Schedule, double, double, double)"
  })
  public void testNewSimplifiedLinearAnnuityMapping_thenReturnSecondDerivativeIsTenIsZero() {
    // Arrange and Act
    SimplifiedLinearAnnuityMapping actualSimplifiedLinearAnnuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualSimplifiedLinearAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(0.8d, actualSimplifiedLinearAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(10.0d, actualSimplifiedLinearAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link SimplifiedLinearAnnuityMapping#SimplifiedLinearAnnuityMapping(Schedule, Schedule,
   * AnalyticModel, String)}.
   *
   * <ul>
   *   <li>Then return SecondDerivative is ten is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * SimplifiedLinearAnnuityMapping#SimplifiedLinearAnnuityMapping(Schedule, Schedule,
   * AnalyticModel, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimplifiedLinearAnnuityMapping.<init>(Schedule, Schedule, AnalyticModel, String)"
  })
  public void testNewSimplifiedLinearAnnuityMapping_thenReturnSecondDerivativeIsTenIsZero2() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);

    AnalyticModelFromCurvesAndVols model = mock(AnalyticModelFromCurvesAndVols.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    SimplifiedLinearAnnuityMapping actualSimplifiedLinearAnnuityMapping =
        new SimplifiedLinearAnnuityMapping(fixSchedule, floatSchedule, model, "3");

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(forwardCurveFromDiscountCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), anyDouble());
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    assertEquals(0.0d, actualSimplifiedLinearAnnuityMapping.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualSimplifiedLinearAnnuityMapping.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualSimplifiedLinearAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link SimplifiedLinearAnnuityMapping#SimplifiedLinearAnnuityMapping(Schedule, Schedule,
   * AnalyticModel, String)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SimplifiedLinearAnnuityMapping#SimplifiedLinearAnnuityMapping(Schedule, Schedule,
   * AnalyticModel, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimplifiedLinearAnnuityMapping.<init>(Schedule, Schedule, AnalyticModel, String)"
  })
  public void testNewSimplifiedLinearAnnuityMapping_thenThrowIllegalArgumentException() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);
    TenorFromArray timeDiscretization2 =
        new TenorFromArray(new double[] {1.0d, Double.NaN, 1.0d, Double.NaN});
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization2);

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);

    AnalyticModelFromCurvesAndVols model = mock(AnalyticModelFromCurvesAndVols.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new SimplifiedLinearAnnuityMapping(fixSchedule, floatSchedule, model, "3"));
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(forwardCurveFromDiscountCurve, atLeast(1)).getPaymentOffset(0.0d);
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), eq(0.0d));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
  }

  /**
   * Test {@link SimplifiedLinearAnnuityMapping#SimplifiedLinearAnnuityMapping(Schedule, Schedule,
   * AnalyticModel, String)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SimplifiedLinearAnnuityMapping#SimplifiedLinearAnnuityMapping(Schedule, Schedule,
   * AnalyticModel, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimplifiedLinearAnnuityMapping.<init>(Schedule, Schedule, AnalyticModel, String)"
  })
  public void testNewSimplifiedLinearAnnuityMapping_thenThrowIllegalArgumentException2() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);

    TenorFromArray timeDiscretization2 = mock(TenorFromArray.class);
    when(timeDiscretization2.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization2.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization2.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization2);

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);

    AnalyticModelFromCurvesAndVols model = mock(AnalyticModelFromCurvesAndVols.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new SimplifiedLinearAnnuityMapping(fixSchedule, floatSchedule, model, "3"));
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(forwardCurveFromDiscountCurve, atLeast(1)).getPaymentOffset(0.0d);
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), eq(0.0d));
    verify(timeDiscretization2).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization2, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    verify(timeDiscretization2).getTimeStep(0);
  }

  /**
   * Test {@link SimplifiedLinearAnnuityMapping#getValue(double)}.
   *
   * <p>Method under test: {@link SimplifiedLinearAnnuityMapping#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SimplifiedLinearAnnuityMapping.getValue(double)"})
  public void testGetValue() {
    // Arrange
    SimplifiedLinearAnnuityMapping simplifiedLinearAnnuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10.0d, simplifiedLinearAnnuityMapping.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link SimplifiedLinearAnnuityMapping#getFirstDerivative(double)}.
   *
   * <p>Method under test: {@link SimplifiedLinearAnnuityMapping#getFirstDerivative(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SimplifiedLinearAnnuityMapping.getFirstDerivative(double)"})
  public void testGetFirstDerivative() {
    // Arrange
    SimplifiedLinearAnnuityMapping simplifiedLinearAnnuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(0.8d, simplifiedLinearAnnuityMapping.getFirstDerivative(10.0d), 0.0);
  }

  /**
   * Test {@link SimplifiedLinearAnnuityMapping#getSecondDerivative(double)}.
   *
   * <p>Method under test: {@link SimplifiedLinearAnnuityMapping#getSecondDerivative(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SimplifiedLinearAnnuityMapping.getSecondDerivative(double)"})
  public void testGetSecondDerivative() {
    // Arrange
    SimplifiedLinearAnnuityMapping simplifiedLinearAnnuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(0.0d, simplifiedLinearAnnuityMapping.getSecondDerivative(10.0d), 0.0);
  }
}
