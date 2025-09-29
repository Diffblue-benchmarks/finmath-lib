package net.finmath.singleswaprate.data;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.volatilities.SwaptionDataLattice;
import net.finmath.marketdata.model.volatilities.SwaptionDataLattice.QuotingConvention;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping.AnnuityMappingType;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import net.finmath.time.ScheduleGenerator;
import net.finmath.time.ScheduleGenerator.DaycountConvention;
import net.finmath.time.ScheduleGenerator.Frequency;
import net.finmath.time.ScheduleGenerator.ShortPeriodConvention;
import net.finmath.time.SchedulePrototype;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ErrorEstimationDiffblueTest {
  /**
   * Test {@link ErrorEstimation#evaluate(SwaptionDataLattice, VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link ErrorEstimation#evaluate(SwaptionDataLattice,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ErrorEstimation.evaluate(SwaptionDataLattice, VolatilityCubeModel)"})
  public void testEvaluate() throws IOException {
    // Arrange
    SwaptionDataLattice physicalPremiumsATM = mock(SwaptionDataLattice.class);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype floatMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    when(physicalPremiumsATM.append(
            Mockito.<SwaptionDataLattice>any(), Mockito.<AnalyticModel>any()))
        .thenReturn(swaptionDataLattice);

    SwaptionDataLattice cashReceiverPremiums = mock(SwaptionDataLattice.class);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    SchedulePrototype floatMetaSchedule2 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype fixMetaSchedule2 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    SwaptionDataLattice swaptionDataLattice2 =
        new SwaptionDataLattice(
            referenceDate2,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule2,
            fixMetaSchedule2,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    when(cashReceiverPremiums.convertLattice(
            Mockito.<QuotingConvention>any(), anyDouble(), Mockito.<AnalyticModel>any()))
        .thenReturn(swaptionDataLattice2);

    ErrorEstimation errorEstimation =
        new ErrorEstimation(
            LocalDate.of(1970, 1, 1),
            mock(SchedulePrototype.class),
            mock(SchedulePrototype.class),
            AnnuityMappingType.BASICPITERBARG,
            physicalPremiumsATM,
            mock(SwaptionDataLattice.class),
            cashReceiverPremiums,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            10.0d,
            10.0d,
            10);

    // Act
    errorEstimation.evaluate(null, new AnalyticModelWithVolatilityCubes());

    // Assert
    verify(physicalPremiumsATM).append(isA(SwaptionDataLattice.class), isA(AnalyticModel.class));
    verify(cashReceiverPremiums)
        .convertLattice(
            eq(QuotingConvention.PAYERVOLATILITYLOGNORMAL), eq(0.0d), isA(AnalyticModel.class));
    assertEquals(0.0d, errorEstimation.getCashMaxError(), 0.0);
    assertEquals(0.0d, errorEstimation.getCashMaxErrorPercent(), 0.0);
    assertEquals(0.0d, errorEstimation.getPhysicalMaxError(), 0.0);
    assertEquals(0.0d, errorEstimation.getPhysicalMaxErrorPercent(), 0.0);
    assertEquals(Double.NaN, errorEstimation.getCashAverageError(), 0.0);
    assertEquals(Double.NaN, errorEstimation.getCashAverageErrorPercent(), 0.0);
    assertEquals(Double.NaN, errorEstimation.getPhysicalAverageError(), 0.0);
    assertEquals(Double.NaN, errorEstimation.getPhysicalAverageErrorPercent(), 0.0);
  }
}
