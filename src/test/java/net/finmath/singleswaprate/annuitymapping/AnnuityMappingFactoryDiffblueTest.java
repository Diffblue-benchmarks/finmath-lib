package net.finmath.singleswaprate.annuitymapping;

import static org.junit.Assert.assertEquals;
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
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping.AnnuityMappingType;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import net.finmath.singleswaprate.model.volatilities.StaticVolatilityCube;
import net.finmath.singleswaprate.model.volatilities.VolatilityCube;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AnnuityMappingFactoryDiffblueTest {
  /**
   * Test {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule, Schedule, String,
   * String, String, AnnuityMappingType, VolatilityCubeModel)} with {@code strike}, {@code
   * fixSchedule}, {@code floatSchedule}, {@code discountCurveName}, {@code forwardCurveName},
   * {@code volatilityCubeName}, {@code type}, {@code model}.
   *
   * <p>Method under test: {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule,
   * Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping AnnuityMappingFactory.buildAnnuityMapping(double, Schedule, Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel)"
  })
  public void
      testBuildAnnuityMappingWithStrikeFixScheduleFloatScheduleDiscountCurveNameForwardCurveNameVolatilityCubeNameTypeModel() {
    // Arrange
    RegularSchedule fixSchedule =
        new RegularSchedule(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        mock(AnalyticModelWithVolatilityCubes.class);
    when(analyticModelWithVolatilityCubes.getForwardCurve(Mockito.<String>any())).thenReturn(null);
    when(analyticModelWithVolatilityCubes.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.addVolatilityCube(Mockito.<VolatilityCube>any()))
        .thenReturn(analyticModelWithVolatilityCubes);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnnuityMappingFactory.buildAnnuityMapping(
                Double.NaN,
                fixSchedule,
                floatSchedule,
                "3",
                "Forward Curve Name",
                "Volatility Cube Name",
                AnnuityMappingType.MULTIPITERBARG,
                model));
    verify(model).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes).getDiscountCurve("3");
    verify(analyticModelWithVolatilityCubes).getForwardCurve("Forward Curve Name");
    verify(forwardCurveFromDiscountCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), anyDouble(), eq(0.5d));
    verify(model).addVolatilityCube(isA(VolatilityCube.class));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
  }

  /**
   * Test {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule, Schedule, String,
   * String, String, AnnuityMappingType, VolatilityCubeModel)} with {@code strike}, {@code
   * fixSchedule}, {@code floatSchedule}, {@code discountCurveName}, {@code forwardCurveName},
   * {@code volatilityCubeName}, {@code type}, {@code model}.
   *
   * <p>Method under test: {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule,
   * Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping AnnuityMappingFactory.buildAnnuityMapping(double, Schedule, Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel)"
  })
  public void
      testBuildAnnuityMappingWithStrikeFixScheduleFloatScheduleDiscountCurveNameForwardCurveNameVolatilityCubeNameTypeModel2() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act
    AnnuityMapping actualBuildAnnuityMappingResult =
        AnnuityMappingFactory.buildAnnuityMapping(
            Double.NaN,
            fixSchedule,
            floatSchedule,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.SIMPLIFIEDLINEAR,
            model);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(discountCurveFromForwardCurve, atLeast(1)).getReferenceDate();
    verify(forwardCurveFromDiscountCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), anyDouble(), eq(0.5d));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    assertTrue(actualBuildAnnuityMappingResult instanceof SimplifiedLinearAnnuityMapping);
    assertEquals(0.0d, actualBuildAnnuityMappingResult.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBuildAnnuityMappingResult.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBuildAnnuityMappingResult.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule, Schedule, String,
   * String, String, AnnuityMappingType, VolatilityCubeModel)} with {@code strike}, {@code
   * fixSchedule}, {@code floatSchedule}, {@code discountCurveName}, {@code forwardCurveName},
   * {@code volatilityCubeName}, {@code type}, {@code model}.
   *
   * <p>Method under test: {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule,
   * Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping AnnuityMappingFactory.buildAnnuityMapping(double, Schedule, Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel)"
  })
  public void
      testBuildAnnuityMappingWithStrikeFixScheduleFloatScheduleDiscountCurveNameForwardCurveNameVolatilityCubeNameTypeModel3() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);
    TenorFromArray timeDiscretization2 =
        new TenorFromArray(new double[] {1.0d, Double.NaN, 1.0d, Double.NaN});
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization2);

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnnuityMappingFactory.buildAnnuityMapping(
                Double.NaN,
                fixSchedule,
                floatSchedule,
                "3",
                "Forward Curve Name",
                "Volatility Cube Name",
                AnnuityMappingType.SIMPLIFIEDLINEAR,
                model));
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(discountCurveFromForwardCurve, atLeast(1)).getReferenceDate();
    verify(forwardCurveFromDiscountCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurveFromDiscountCurve)
        .getForward(isA(AnalyticModel.class), eq(1.0d), eq(Double.NaN));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
  }

  /**
   * Test {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule, Schedule, String,
   * String, String, AnnuityMappingType, VolatilityCubeModel)} with {@code strike}, {@code
   * fixSchedule}, {@code floatSchedule}, {@code discountCurveName}, {@code forwardCurveName},
   * {@code volatilityCubeName}, {@code type}, {@code model}.
   *
   * <p>Method under test: {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule,
   * Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping AnnuityMappingFactory.buildAnnuityMapping(double, Schedule, Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel)"
  })
  public void
      testBuildAnnuityMappingWithStrikeFixScheduleFloatScheduleDiscountCurveNameForwardCurveNameVolatilityCubeNameTypeModel4() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);

    TimeDiscretization timeDiscretization2 = mock(TimeDiscretization.class);
    when(timeDiscretization2.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization2.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization2.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization2);

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnnuityMappingFactory.buildAnnuityMapping(
                Double.NaN,
                fixSchedule,
                floatSchedule,
                "3",
                "Forward Curve Name",
                "Volatility Cube Name",
                AnnuityMappingType.SIMPLIFIEDLINEAR,
                model));
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(discountCurveFromForwardCurve, atLeast(1)).getReferenceDate();
    verify(forwardCurveFromDiscountCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), eq(10.0d));
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), eq(10.0d), eq(0.0d));
    verify(timeDiscretization2, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization2, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    verify(timeDiscretization2, atLeast(1)).getTimeStep(anyInt());
  }

  /**
   * Test {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule, Schedule, String,
   * String, String, AnnuityMappingType, VolatilityCubeModel)} with {@code strike}, {@code
   * fixSchedule}, {@code floatSchedule}, {@code discountCurveName}, {@code forwardCurveName},
   * {@code volatilityCubeName}, {@code type}, {@code model}.
   *
   * <p>Method under test: {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule,
   * Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping AnnuityMappingFactory.buildAnnuityMapping(double, Schedule, Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel)"
  })
  public void
      testBuildAnnuityMappingWithStrikeFixScheduleFloatScheduleDiscountCurveNameForwardCurveNameVolatilityCubeNameTypeModel5() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);

    TimeDiscretization timeDiscretization2 = mock(TimeDiscretization.class);
    when(timeDiscretization2.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization2.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization2.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization2);

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(null);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnnuityMappingFactory.buildAnnuityMapping(
                Double.NaN,
                fixSchedule,
                floatSchedule,
                "3",
                "Forward Curve Name",
                "Volatility Cube Name",
                AnnuityMappingType.SIMPLIFIEDLINEAR,
                model));
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(discountCurveFromForwardCurve).getReferenceDate();
    verify(forwardCurveFromDiscountCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), eq(10.0d));
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), eq(10.0d), eq(0.0d));
    verify(timeDiscretization2, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization2, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    verify(timeDiscretization2, atLeast(1)).getTimeStep(anyInt());
  }

  /**
   * Test {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule, Schedule, String,
   * String, String, AnnuityMappingType, VolatilityCubeModel, double, double, int)} with {@code
   * strike}, {@code fixSchedule}, {@code floatSchedule}, {@code discountCurveName}, {@code
   * forwardCurveName}, {@code volatilityCubeName}, {@code type}, {@code model}, {@code lowerBound},
   * {@code upperBound}, {@code numberOfEvaluationPoints}.
   *
   * <p>Method under test: {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule,
   * Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel, double, double,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping AnnuityMappingFactory.buildAnnuityMapping(double, Schedule, Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel, double, double, int)"
  })
  public void
      testBuildAnnuityMappingWithStrikeFixScheduleFloatScheduleDiscountCurveNameForwardCurveNameVolatilityCubeNameTypeModelLowerBoundUpperBoundNumberOfEvaluationPoints() {
    // Arrange
    RegularSchedule fixSchedule =
        new RegularSchedule(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        mock(AnalyticModelWithVolatilityCubes.class);
    when(analyticModelWithVolatilityCubes.getForwardCurve(Mockito.<String>any())).thenReturn(null);
    when(analyticModelWithVolatilityCubes.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);
    when(model.addVolatilityCube(Mockito.<VolatilityCube>any()))
        .thenReturn(analyticModelWithVolatilityCubes);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnnuityMappingFactory.buildAnnuityMapping(
                Double.NaN,
                fixSchedule,
                floatSchedule,
                "3",
                "Forward Curve Name",
                "Volatility Cube Name",
                AnnuityMappingType.MULTIPITERBARG,
                model,
                10.0d,
                10.0d,
                10));
    verify(model).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(analyticModelWithVolatilityCubes).getDiscountCurve("3");
    verify(analyticModelWithVolatilityCubes).getForwardCurve("Forward Curve Name");
    verify(forwardCurveFromDiscountCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), anyDouble(), eq(0.5d));
    verify(model).addVolatilityCube(isA(VolatilityCube.class));
    verify(model, atLeast(1)).getVolatilityCube("Volatility Cube Name");
  }

  /**
   * Test {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule, Schedule, String,
   * String, String, AnnuityMappingType, VolatilityCubeModel, double, double, int)} with {@code
   * strike}, {@code fixSchedule}, {@code floatSchedule}, {@code discountCurveName}, {@code
   * forwardCurveName}, {@code volatilityCubeName}, {@code type}, {@code model}, {@code lowerBound},
   * {@code upperBound}, {@code numberOfEvaluationPoints}.
   *
   * <p>Method under test: {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule,
   * Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel, double, double,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping AnnuityMappingFactory.buildAnnuityMapping(double, Schedule, Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel, double, double, int)"
  })
  public void
      testBuildAnnuityMappingWithStrikeFixScheduleFloatScheduleDiscountCurveNameForwardCurveNameVolatilityCubeNameTypeModelLowerBoundUpperBoundNumberOfEvaluationPoints2() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act
    AnnuityMapping actualBuildAnnuityMappingResult =
        AnnuityMappingFactory.buildAnnuityMapping(
            Double.NaN,
            fixSchedule,
            floatSchedule,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.SIMPLIFIEDLINEAR,
            model,
            10.0d,
            10.0d,
            10);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(discountCurveFromForwardCurve, atLeast(1)).getReferenceDate();
    verify(forwardCurveFromDiscountCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), anyDouble(), eq(0.5d));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    assertTrue(actualBuildAnnuityMappingResult instanceof SimplifiedLinearAnnuityMapping);
    assertEquals(0.0d, actualBuildAnnuityMappingResult.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBuildAnnuityMappingResult.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBuildAnnuityMappingResult.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule, Schedule, String,
   * String, String, AnnuityMappingType, VolatilityCubeModel, double, double, int)} with {@code
   * strike}, {@code fixSchedule}, {@code floatSchedule}, {@code discountCurveName}, {@code
   * forwardCurveName}, {@code volatilityCubeName}, {@code type}, {@code model}, {@code lowerBound},
   * {@code upperBound}, {@code numberOfEvaluationPoints}.
   *
   * <p>Method under test: {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule,
   * Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel, double, double,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping AnnuityMappingFactory.buildAnnuityMapping(double, Schedule, Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel, double, double, int)"
  })
  public void
      testBuildAnnuityMappingWithStrikeFixScheduleFloatScheduleDiscountCurveNameForwardCurveNameVolatilityCubeNameTypeModelLowerBoundUpperBoundNumberOfEvaluationPoints3() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);
    TenorFromArray timeDiscretization2 =
        new TenorFromArray(new double[] {1.0d, Double.NaN, 1.0d, Double.NaN});
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization2);

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnnuityMappingFactory.buildAnnuityMapping(
                Double.NaN,
                fixSchedule,
                floatSchedule,
                "3",
                "Forward Curve Name",
                "Volatility Cube Name",
                AnnuityMappingType.SIMPLIFIEDLINEAR,
                model,
                10.0d,
                10.0d,
                10));
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(discountCurveFromForwardCurve, atLeast(1)).getReferenceDate();
    verify(forwardCurveFromDiscountCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    verify(forwardCurveFromDiscountCurve)
        .getForward(isA(AnalyticModel.class), eq(1.0d), eq(Double.NaN));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
  }

  /**
   * Test {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule, Schedule, String,
   * String, String, AnnuityMappingType, VolatilityCubeModel, double, double, int)} with {@code
   * strike}, {@code fixSchedule}, {@code floatSchedule}, {@code discountCurveName}, {@code
   * forwardCurveName}, {@code volatilityCubeName}, {@code type}, {@code model}, {@code lowerBound},
   * {@code upperBound}, {@code numberOfEvaluationPoints}.
   *
   * <p>Method under test: {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule,
   * Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel, double, double,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping AnnuityMappingFactory.buildAnnuityMapping(double, Schedule, Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel, double, double, int)"
  })
  public void
      testBuildAnnuityMappingWithStrikeFixScheduleFloatScheduleDiscountCurveNameForwardCurveNameVolatilityCubeNameTypeModelLowerBoundUpperBoundNumberOfEvaluationPoints4() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);

    TimeDiscretization timeDiscretization2 = mock(TimeDiscretization.class);
    when(timeDiscretization2.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization2.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization2.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization2);

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnnuityMappingFactory.buildAnnuityMapping(
                Double.NaN,
                fixSchedule,
                floatSchedule,
                "3",
                "Forward Curve Name",
                "Volatility Cube Name",
                AnnuityMappingType.SIMPLIFIEDLINEAR,
                model,
                10.0d,
                10.0d,
                10));
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(discountCurveFromForwardCurve, atLeast(1)).getReferenceDate();
    verify(forwardCurveFromDiscountCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), eq(10.0d));
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), eq(10.0d), eq(0.0d));
    verify(timeDiscretization2, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization2, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    verify(timeDiscretization2, atLeast(1)).getTimeStep(anyInt());
  }

  /**
   * Test {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule, Schedule, String,
   * String, String, AnnuityMappingType, VolatilityCubeModel, double, double, int)} with {@code
   * strike}, {@code fixSchedule}, {@code floatSchedule}, {@code discountCurveName}, {@code
   * forwardCurveName}, {@code volatilityCubeName}, {@code type}, {@code model}, {@code lowerBound},
   * {@code upperBound}, {@code numberOfEvaluationPoints}.
   *
   * <p>Method under test: {@link AnnuityMappingFactory#buildAnnuityMapping(double, Schedule,
   * Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel, double, double,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping AnnuityMappingFactory.buildAnnuityMapping(double, Schedule, Schedule, String, String, String, AnnuityMappingType, VolatilityCubeModel, double, double, int)"
  })
  public void
      testBuildAnnuityMappingWithStrikeFixScheduleFloatScheduleDiscountCurveNameForwardCurveNameVolatilityCubeNameTypeModelLowerBoundUpperBoundNumberOfEvaluationPoints5() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);

    TimeDiscretization timeDiscretization2 = mock(TimeDiscretization.class);
    when(timeDiscretization2.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization2.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization2.getNumberOfTimeSteps()).thenReturn(10);
    RegularSchedule floatSchedule = new RegularSchedule(timeDiscretization2);

    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        mock(ForwardCurveFromDiscountCurve.class);
    when(forwardCurveFromDiscountCurve.getForward(
            Mockito.<AnalyticModel>any(), anyDouble(), anyDouble()))
        .thenReturn(10.0d);
    when(forwardCurveFromDiscountCurve.getDiscountCurveName()).thenReturn("3");

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(null);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            AnnuityMappingFactory.buildAnnuityMapping(
                Double.NaN,
                fixSchedule,
                floatSchedule,
                "3",
                "Forward Curve Name",
                "Volatility Cube Name",
                AnnuityMappingType.SIMPLIFIEDLINEAR,
                model,
                10.0d,
                10.0d,
                10));
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(discountCurveFromForwardCurve).getReferenceDate();
    verify(forwardCurveFromDiscountCurve).getDiscountCurveName();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), eq(10.0d));
    verify(forwardCurveFromDiscountCurve, atLeast(1))
        .getForward(isA(AnalyticModel.class), eq(10.0d), eq(0.0d));
    verify(timeDiscretization2, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization2, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    verify(timeDiscretization2, atLeast(1)).getTimeStep(anyInt());
  }
}
