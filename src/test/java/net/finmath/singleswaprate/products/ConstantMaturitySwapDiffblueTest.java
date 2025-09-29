package net.finmath.singleswaprate.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
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
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping.AnnuityMappingType;
import net.finmath.singleswaprate.annuitymapping.SimplifiedLinearAnnuityMapping;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConstantMaturitySwapDiffblueTest {
  @InjectMocks private ConstantMaturitySwap constantMaturitySwap;

  @Mock private Schedule schedule;

  /**
   * Test {@link ConstantMaturitySwap#ConstantMaturitySwap(Schedule, Schedule, String, String,
   * String, AnnuityMappingType)}.
   *
   * <p>Method under test: {@link ConstantMaturitySwap#ConstantMaturitySwap(Schedule, Schedule,
   * String, String, String, AnnuityMapping.AnnuityMappingType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConstantMaturitySwap.<init>(Schedule, Schedule, String, String, String, AnnuityMapping.AnnuityMappingType)"
  })
  public void testNewConstantMaturitySwap() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    ConstantMaturitySwap actualConstantMaturitySwap =
        new ConstantMaturitySwap(
            fixSchedule,
            floatSchedule,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    // Assert
    Schedule fixSchedule2 = actualConstantMaturitySwap.getFixSchedule();
    assertTrue(fixSchedule2 instanceof RegularSchedule);
    Schedule floatSchedule2 = actualConstantMaturitySwap.getFloatSchedule();
    assertTrue(floatSchedule2 instanceof RegularSchedule);
    assertEquals("3", actualConstantMaturitySwap.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualConstantMaturitySwap.getForwardCurveName());
    assertEquals("Volatility Cube Name", actualConstantMaturitySwap.getVolatilityCubeName());
    assertEquals(-0.15d, actualConstantMaturitySwap.getIntegrationLowerBound(), 0.0);
    assertEquals(0.15d, actualConstantMaturitySwap.getIntegrationUpperBound(), 0.0);
    assertEquals(500, actualConstantMaturitySwap.getIntegrationNumberOfEvaluationPoints());
    assertSame(fixSchedule, fixSchedule2);
    assertSame(floatSchedule, floatSchedule2);
  }

  /**
   * Test {@link ConstantMaturitySwap#payoffFunction(double, AnnuityMapping, VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link ConstantMaturitySwap#payoffFunction(double, AnnuityMapping,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double ConstantMaturitySwap.payoffFunction(double, AnnuityMapping, VolatilityCubeModel)"
  })
  public void testPayoffFunction_thenReturnOneHundred() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    ConstantMaturitySwap constantMaturitySwap =
        new ConstantMaturitySwap(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        100.0d,
        constantMaturitySwap.payoffFunction(
            10.0d, annuityMapping, new AnalyticModelWithVolatilityCubes()),
        0.0);
  }

  /**
   * Test {@link ConstantMaturitySwap#hedgeWeight(double, AnnuityMapping, VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then return {@code 1.6}.
   * </ul>
   *
   * <p>Method under test: {@link ConstantMaturitySwap#hedgeWeight(double, AnnuityMapping,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double ConstantMaturitySwap.hedgeWeight(double, AnnuityMapping, VolatilityCubeModel)"
  })
  public void testHedgeWeight_thenReturn16() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    ConstantMaturitySwap constantMaturitySwap =
        new ConstantMaturitySwap(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        1.6d,
        constantMaturitySwap.hedgeWeight(
            10.0d, annuityMapping, new AnalyticModelWithVolatilityCubes()),
        0.0);
  }

  /**
   * Test {@link ConstantMaturitySwap#singularAddon(double, AnnuityMapping, VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link ConstantMaturitySwap#singularAddon(double, AnnuityMapping,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double ConstantMaturitySwap.singularAddon(double, AnnuityMapping, VolatilityCubeModel)"
  })
  public void testSingularAddon() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    ConstantMaturitySwap constantMaturitySwap =
        new ConstantMaturitySwap(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        constantMaturitySwap.singularAddon(
            10.0d, annuityMapping, new AnalyticModelWithVolatilityCubes()),
        0.0);
  }

  /**
   * Test {@link ConstantMaturitySwap#buildAnnuityMapping(VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ConstantMaturitySwap#buildAnnuityMapping(VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping ConstantMaturitySwap.buildAnnuityMapping(VolatilityCubeModel)"
  })
  public void testBuildAnnuityMapping_thenThrowIllegalArgumentException() {
    // Arrange
    when(schedule.getPayment(anyInt())).thenReturn(10.0d);
    when(schedule.getPeriodLength(anyInt())).thenReturn(10.0d);
    when(schedule.getFixing(anyInt())).thenReturn(10.0d);
    when(schedule.getNumberOfPeriods()).thenReturn(10);
    when(schedule.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> constantMaturitySwap.buildAnnuityMapping(model));
    verify(model, atLeast(1)).getDiscountCurve(null);
    verify(model).getForwardCurve("Forward Curve Name");
    verify(forwardCurveFromDiscountCurve).getPaymentOffset(0.0d);
    verify(forwardCurveFromDiscountCurve).getForward(isA(AnalyticModel.class), eq(0.0d));
    verify(schedule, atLeast(1)).getFixing(0);
    verify(schedule, atLeast(1)).getNumberOfPeriods();
    verify(schedule, atLeast(1)).getPayment(anyInt());
    verify(schedule, atLeast(1)).getPeriodLength(anyInt());
    verify(schedule).getReferenceDate();
  }

  /**
   * Test {@link ConstantMaturitySwap#analyticApproximation(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link ConstantMaturitySwap#analyticApproximation(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double ConstantMaturitySwap.analyticApproximation(double, double, double, double, double, double)"
  })
  public void testAnalyticApproximation_when05() {
    // Arrange and Act
    double actualAnalyticApproximationResult =
        ConstantMaturitySwap.analyticApproximation(0.5d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualAnalyticApproximationResult, 0.0);
  }

  /**
   * Test {@link ConstantMaturitySwap#analyticApproximation(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link ConstantMaturitySwap#analyticApproximation(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double ConstantMaturitySwap.analyticApproximation(double, double, double, double, double, double)"
  })
  public void testAnalyticApproximation_when052() {
    // Arrange and Act
    double actualAnalyticApproximationResult =
        ConstantMaturitySwap.analyticApproximation(-0.5d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualAnalyticApproximationResult, 0.0);
  }

  /**
   * Test {@link ConstantMaturitySwap#analyticApproximation(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When one.
   * </ul>
   *
   * <p>Method under test: {@link ConstantMaturitySwap#analyticApproximation(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double ConstantMaturitySwap.analyticApproximation(double, double, double, double, double, double)"
  })
  public void testAnalyticApproximation_whenOne() {
    // Arrange and Act
    double actualAnalyticApproximationResult =
        ConstantMaturitySwap.analyticApproximation(1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualAnalyticApproximationResult, 0.0);
  }

  /**
   * Test {@link ConstantMaturitySwap#analyticApproximation(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   * </ul>
   *
   * <p>Method under test: {@link ConstantMaturitySwap#analyticApproximation(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double ConstantMaturitySwap.analyticApproximation(double, double, double, double, double, double)"
  })
  public void testAnalyticApproximation_whenTen() {
    // Arrange and Act
    double actualAnalyticApproximationResult =
        ConstantMaturitySwap.analyticApproximation(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualAnalyticApproximationResult, 0.0);
  }
}
