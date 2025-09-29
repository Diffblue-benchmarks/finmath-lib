package net.finmath.singleswaprate.model.volatilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.Map;
import net.finmath.marketdata.model.volatilities.SwaptionDataLattice;
import net.finmath.marketdata.model.volatilities.SwaptionDataLattice.QuotingConvention;
import net.finmath.singleswaprate.data.DataTableInterpolated;
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

public class SABRVolatilityCubeParallelFactoryDiffblueTest {
  /**
   * Test {@link SABRVolatilityCubeParallelFactory#createSABRVolatilityCubeParallel(String,
   * LocalDate, SchedulePrototype, SchedulePrototype, double, double, double, double, double,
   * double, SwaptionDataLattice, VolatilityCubeModel, String)}.
   *
   * <ul>
   *   <li>Then return Parameters size is eight.
   * </ul>
   *
   * <p>Method under test: {@link
   * SABRVolatilityCubeParallelFactory#createSABRVolatilityCubeParallel(String, LocalDate,
   * SchedulePrototype, SchedulePrototype, double, double, double, double, double, double,
   * SwaptionDataLattice, VolatilityCubeModel, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SABRVolatilityCubeParallel SABRVolatilityCubeParallelFactory.createSABRVolatilityCubeParallel(String, LocalDate, SchedulePrototype, SchedulePrototype, double, double, double, double, double, double, SwaptionDataLattice, VolatilityCubeModel, String)"
  })
  public void testCreateSABRVolatilityCubeParallel_thenReturnParametersSizeIsEight() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice physicalATMSwaptions =
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

    // Act
    SABRVolatilityCubeParallel actualCreateSABRVolatilityCubeParallelResult =
        SABRVolatilityCubeParallelFactory.createSABRVolatilityCubeParallel(
            "Cube Name",
            referenceDate,
            fixMetaSchedule,
            floatMetaSchedule,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            physicalATMSwaptions,
            new AnalyticModelWithVolatilityCubes(),
            "Forward Curve Name");

    // Assert
    Map<String, Object> parameters = actualCreateSABRVolatilityCubeParallelResult.getParameters();
    assertEquals(8, parameters.size());
    assertTrue(parameters.get("baseVolTable") instanceof DataTableInterpolated);
    assertEquals("Cube Name", actualCreateSABRVolatilityCubeParallelResult.getName());
    assertEquals(10.0d, ((Double) parameters.get("Inherent correlationDecay")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("iborOisDecorrelation")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrBeta")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrDisplacement")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrRho")).doubleValue(), 0.0);
    assertEquals(10.0d, actualCreateSABRVolatilityCubeParallelResult.getCorrelationDecay(), 0.0);
    assertEquals(
        10.0d, actualCreateSABRVolatilityCubeParallelResult.getIborOisDecorrelation(), 0.0);
    assertSame(referenceDate, actualCreateSABRVolatilityCubeParallelResult.getReferenceDate());
  }
}
