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
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import net.finmath.singleswaprate.model.volatilities.StaticVolatilityCube;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ExponentialNormalizerDiffblueTest {
  /**
   * Test {@link ExponentialNormalizer#ExponentialNormalizer(double, double)}.
   *
   * <p>Method under test: {@link ExponentialNormalizer#ExponentialNormalizer(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExponentialNormalizer.<init>(double, double)"})
  public void testNewExponentialNormalizer() {
    // Arrange and Act
    ExponentialNormalizer actualExponentialNormalizer = new ExponentialNormalizer(10.0d, 10.0d);

    // Assert
    assertEquals(-0.7357588823428847d, actualExponentialNormalizer.getFirstDerivative(10.0d), 0.0);
    assertEquals(0.07357588823428847d, actualExponentialNormalizer.getSecondDerivative(10.0d), 0.0);
    assertEquals(3.6787944117144233d, actualExponentialNormalizer.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link ExponentialNormalizer#ExponentialNormalizer(Schedule, Schedule, String, String,
   * String, VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link ExponentialNormalizer#ExponentialNormalizer(Schedule, Schedule,
   * String, String, String, VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExponentialNormalizer.<init>(Schedule, Schedule, String, String, String, VolatilityCubeModel)"
  })
  public void testNewExponentialNormalizer2() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
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

    AnalyticModelWithVolatilityCubes model = mock(AnalyticModelWithVolatilityCubes.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ExponentialNormalizer(
                fixSchedule,
                floatSchedule,
                "3",
                "Forward Curve Name",
                "Volatility Cube Name",
                model));
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(forwardCurveFromDiscountCurve).getPaymentOffset(0.0d);
    verify(forwardCurveFromDiscountCurve).getForward(isA(AnalyticModel.class), eq(0.0d));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
  }

  /**
   * Test {@link ExponentialNormalizer#ExponentialNormalizer(Schedule, Schedule, String, String,
   * String, VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Given {@link Double#NaN}.
   *   <li>Then calls {@link DiscountCurveFromForwardCurve#getReferenceDate()}.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialNormalizer#ExponentialNormalizer(Schedule, Schedule,
   * String, String, String, VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExponentialNormalizer.<init>(Schedule, Schedule, String, String, String, VolatilityCubeModel)"
  })
  public void testNewExponentialNormalizer_givenNaN_thenCallsGetReferenceDate() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(Double.NaN);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);

    TenorFromArray timeDiscretization2 = mock(TenorFromArray.class);
    when(timeDiscretization2.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization2.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization2.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization2);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));

    AnalyticModelWithVolatilityCubes model = mock(AnalyticModelWithVolatilityCubes.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ExponentialNormalizer(
                fixSchedule,
                floatSchedule,
                "3",
                "Forward Curve Name",
                "Volatility Cube Name",
                model));
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve).getReferenceDate();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), eq(Double.NaN));
    verify(timeDiscretization2).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization2, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    verify(timeDiscretization2).getTimeStep(0);
  }

  /**
   * Test {@link ExponentialNormalizer#ExponentialNormalizer(Schedule, Schedule, String, String,
   * String, VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>Then return FirstDerivative is ten is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialNormalizer#ExponentialNormalizer(Schedule, Schedule,
   * String, String, String, VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExponentialNormalizer.<init>(Schedule, Schedule, String, String, String, VolatilityCubeModel)"
  })
  public void testNewExponentialNormalizer_givenZero_thenReturnFirstDerivativeIsTenIsNaN() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(Double.NaN);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);

    TenorFromArray timeDiscretization2 = mock(TenorFromArray.class);
    when(timeDiscretization2.getNumberOfTimeSteps()).thenReturn(0);
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization2);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getDiscountCurveName()).thenReturn("3");

    AnalyticModelWithVolatilityCubes model = mock(AnalyticModelWithVolatilityCubes.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    ExponentialNormalizer actualExponentialNormalizer =
        new ExponentialNormalizer(
            fixSchedule, floatSchedule, "3", "Forward Curve Name", "Volatility Cube Name", model);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(discountCurveFromForwardCurve).getReferenceDate();
    verify(forwardCurveFromDiscountCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), eq(Double.NaN));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    verify(timeDiscretization2, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    assertEquals(Double.NaN, actualExponentialNormalizer.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualExponentialNormalizer.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualExponentialNormalizer.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link ExponentialNormalizer#ExponentialNormalizer(Schedule, Schedule, String, String,
   * String, VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then calls {@link ForwardCurveFromDiscountCurve#getForward(AnalyticModel, double,
   *       double)}.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialNormalizer#ExponentialNormalizer(Schedule, Schedule,
   * String, String, String, VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExponentialNormalizer.<init>(Schedule, Schedule, String, String, String, VolatilityCubeModel)"
  })
  public void testNewExponentialNormalizer_thenCallsGetForward() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getDiscountCurveName()).thenReturn("3");
    when(forwardCurveFromDiscountCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);

    AnalyticModelWithVolatilityCubes model = mock(AnalyticModelWithVolatilityCubes.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    ExponentialNormalizer actualExponentialNormalizer =
        new ExponentialNormalizer(
            fixSchedule, floatSchedule, "3", "Forward Curve Name", "Volatility Cube Name", model);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model, atLeast(1)).getForwardCurve("Forward Curve Name");
    verify(forwardCurveFromDiscountCurve).getDiscountCurveName();
    verify(forwardCurveFromDiscountCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), anyDouble(), eq(0.5d));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    assertEquals(Double.NaN, actualExponentialNormalizer.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualExponentialNormalizer.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualExponentialNormalizer.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link ExponentialNormalizer#ExponentialNormalizer(Schedule, Schedule, String, String,
   * String, VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then calls {@link ForwardCurveFromDiscountCurve#getPaymentOffset(double)}.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialNormalizer#ExponentialNormalizer(Schedule, Schedule,
   * String, String, String, VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExponentialNormalizer.<init>(Schedule, Schedule, String, String, String, VolatilityCubeModel)"
  })
  public void testNewExponentialNormalizer_thenCallsGetPaymentOffset() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
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

    AnalyticModelWithVolatilityCubes model = mock(AnalyticModelWithVolatilityCubes.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ExponentialNormalizer(
                fixSchedule,
                floatSchedule,
                "3",
                "Forward Curve Name",
                "Volatility Cube Name",
                model));
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(forwardCurveFromDiscountCurve).getPaymentOffset(0.0d);
    verify(forwardCurveFromDiscountCurve).getForward(isA(AnalyticModel.class), eq(0.0d));
    verify(timeDiscretization2).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization2, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization2).getTimeStep(0);
  }

  /**
   * Test {@link ExponentialNormalizer#ExponentialNormalizer(Schedule, Schedule, String, String,
   * String, VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then calls {@link DiscountCurveFromForwardCurve#getReferenceDate()}.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialNormalizer#ExponentialNormalizer(Schedule, Schedule,
   * String, String, String, VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExponentialNormalizer.<init>(Schedule, Schedule, String, String, String, VolatilityCubeModel)"
  })
  public void testNewExponentialNormalizer_thenCallsGetReferenceDate() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);

    TenorFromArray timeDiscretization2 = mock(TenorFromArray.class);
    when(timeDiscretization2.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization2.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization2.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization2);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));

    AnalyticModelWithVolatilityCubes model = mock(AnalyticModelWithVolatilityCubes.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ExponentialNormalizer(
                fixSchedule,
                floatSchedule,
                "3",
                "Forward Curve Name",
                "Volatility Cube Name",
                model));
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve).getReferenceDate();
    verify(discountCurveFromForwardCurve).getDiscountFactor(isA(AnalyticModel.class), eq(10.0d));
    verify(timeDiscretization2).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization2, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization2).getTimeStep(0);
  }

  /**
   * Test {@link ExponentialNormalizer#getValue(double)}.
   *
   * <p>Method under test: {@link ExponentialNormalizer#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ExponentialNormalizer.getValue(double)"})
  public void testGetValue() {
    // Arrange, Act and Assert
    assertEquals(3.6787944117144233d, new ExponentialNormalizer(10.0d, 10.0d).getValue(10.0d), 0.0);
  }

  /**
   * Test {@link ExponentialNormalizer#getFirstDerivative(double)}.
   *
   * <p>Method under test: {@link ExponentialNormalizer#getFirstDerivative(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ExponentialNormalizer.getFirstDerivative(double)"})
  public void testGetFirstDerivative() {
    // Arrange, Act and Assert
    assertEquals(
        -0.7357588823428847d,
        new ExponentialNormalizer(10.0d, 10.0d).getFirstDerivative(10.0d),
        0.0);
  }

  /**
   * Test {@link ExponentialNormalizer#getSecondDerivative(double)}.
   *
   * <p>Method under test: {@link ExponentialNormalizer#getSecondDerivative(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ExponentialNormalizer.getSecondDerivative(double)"})
  public void testGetSecondDerivative() {
    // Arrange, Act and Assert
    assertEquals(
        0.07357588823428847d,
        new ExponentialNormalizer(10.0d, 10.0d).getSecondDerivative(10.0d),
        0.0);
  }
}
