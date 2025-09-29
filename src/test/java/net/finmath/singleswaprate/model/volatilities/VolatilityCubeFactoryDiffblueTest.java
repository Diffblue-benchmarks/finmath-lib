package net.finmath.singleswaprate.model.volatilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import net.finmath.marketdata.model.volatilities.SwaptionDataLattice;
import net.finmath.marketdata.model.volatilities.SwaptionDataLattice.QuotingConvention;
import net.finmath.optimizer.SolverException;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping.AnnuityMappingType;
import net.finmath.singleswaprate.data.DataTable;
import net.finmath.singleswaprate.data.DataTable.TableConvention;
import net.finmath.singleswaprate.data.DataTableInterpolated;
import net.finmath.singleswaprate.data.DataTableLight;
import net.finmath.singleswaprate.data.DataTableLinear;
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

public class VolatilityCubeFactoryDiffblueTest {
  /**
   * Test {@link VolatilityCubeFactory#VolatilityCubeFactory(LocalDate, SwaptionDataLattice,
   * SwaptionDataLattice, SwaptionDataLattice, double, double, double, double, AnnuityMappingType)}.
   *
   * <p>Method under test: {@link VolatilityCubeFactory#VolatilityCubeFactory(LocalDate,
   * SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, double, double, double, double,
   * AnnuityMapping.AnnuityMappingType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void VolatilityCubeFactory.<init>(LocalDate, SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, double, double, double, double, AnnuityMapping.AnnuityMappingType)"
  })
  public void testNewVolatilityCubeFactory() {
    // Arrange and Act
    VolatilityCubeFactory actualVolatilityCubeFactory =
        new VolatilityCubeFactory(
            LocalDate.of(1970, 1, 1),
            mock(SwaptionDataLattice.class),
            mock(SwaptionDataLattice.class),
            mock(SwaptionDataLattice.class),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            AnnuityMappingType.BASICPITERBARG);

    // Assert
    assertEquals(-0.15d, actualVolatilityCubeFactory.getReplicationLowerBound(), 0.0);
    assertEquals(0.15d, actualVolatilityCubeFactory.getReplicationUpperBound(), 0.0);
    assertEquals(250, actualVolatilityCubeFactory.getMaxIterations());
    assertEquals(500.0d, actualVolatilityCubeFactory.getReplicationNumberOfEvaluationPoints(), 0.0);
    assertEquals(8, actualVolatilityCubeFactory.getNumberOfThreads());
  }

  /**
   * Test {@link VolatilityCubeFactory#buildParallelSABRCube(String, double, double,
   * SwaptionDataLattice, VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then return Parameters size is eight.
   * </ul>
   *
   * <p>Method under test: {@link VolatilityCubeFactory#buildParallelSABRCube(String, double,
   * double, SwaptionDataLattice, VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SABRVolatilityCubeParallel VolatilityCubeFactory.buildParallelSABRCube(String, double, double, SwaptionDataLattice, VolatilityCubeModel)"
  })
  public void testBuildParallelSABRCube_thenReturnParametersSizeIsEight() {
    // Arrange
    SwaptionDataLattice cashPayerPremiums = mock(SwaptionDataLattice.class);
    when(cashPayerPremiums.getForwardCurveName()).thenReturn("Forward Curve Name");
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(cashPayerPremiums.getFixMetaSchedule()).thenReturn(schedulePrototype);

    SwaptionDataLattice cashReceiverPremiums = mock(SwaptionDataLattice.class);
    SchedulePrototype schedulePrototype2 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(cashReceiverPremiums.getFloatMetaSchedule()).thenReturn(schedulePrototype2);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    VolatilityCubeFactory volatilityCubeFactory =
        new VolatilityCubeFactory(
            referenceDate,
            cashPayerPremiums,
            cashReceiverPremiums,
            mock(SwaptionDataLattice.class),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            AnnuityMappingType.BASICPITERBARG);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
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

    SwaptionDataLattice physicalATMSwaptions =
        new SwaptionDataLattice(
            referenceDate2,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act
    SABRVolatilityCubeParallel actualBuildParallelSABRCubeResult =
        volatilityCubeFactory.buildParallelSABRCube(
            "Name", 10.0d, 10.0d, physicalATMSwaptions, new AnalyticModelWithVolatilityCubes());

    // Assert
    verify(cashPayerPremiums).getFixMetaSchedule();
    verify(cashReceiverPremiums).getFloatMetaSchedule();
    verify(cashPayerPremiums).getForwardCurveName();
    Map<String, Object> parameters = actualBuildParallelSABRCubeResult.getParameters();
    assertEquals(8, parameters.size());
    assertTrue(parameters.get("baseVolTable") instanceof DataTableInterpolated);
    LocalDate referenceDate3 = actualBuildParallelSABRCubeResult.getReferenceDate();
    assertEquals("1970-01-01", referenceDate3.toString());
    assertEquals("Name", actualBuildParallelSABRCubeResult.getName());
    assertEquals(10.0d, ((Double) parameters.get("Inherent correlationDecay")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("iborOisDecorrelation")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrBeta")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrDisplacement")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrRho")).doubleValue(), 0.0);
    assertEquals(10.0d, actualBuildParallelSABRCubeResult.getCorrelationDecay(), 0.0);
    assertEquals(10.0d, actualBuildParallelSABRCubeResult.getIborOisDecorrelation(), 0.0);
    assertSame(referenceDate, referenceDate3);
  }

  /**
   * Test {@link VolatilityCubeFactory#buildShiftedSmileSABRCube(String, VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link VolatilityCubeFactory#buildShiftedSmileSABRCube(String,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SABRVolatilityCube VolatilityCubeFactory.buildShiftedSmileSABRCube(String, VolatilityCubeModel)"
  })
  public void testBuildShiftedSmileSABRCube() throws SolverException {
    // Arrange
    HashMap<Integer, int[][]> integerIntArrayMap = new HashMap<>();
    integerIntArrayMap.put(1, new int[][] {new int[] {1, 500, 1, 500}});

    SwaptionDataLattice cashPayerPremiums = mock(SwaptionDataLattice.class);
    when(cashPayerPremiums.getDiscountCurveName()).thenReturn("3");
    when(cashPayerPremiums.getForwardCurveName()).thenReturn("Forward Curve Name");
    when(cashPayerPremiums.getGridNodesPerMoneyness()).thenReturn(integerIntArrayMap);
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(cashPayerPremiums.getFixMetaSchedule()).thenReturn(schedulePrototype);
    SchedulePrototype schedulePrototype2 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(cashPayerPremiums.getFloatMetaSchedule()).thenReturn(schedulePrototype2);

    HashMap<Integer, int[][]> integerIntArrayMap2 = new HashMap<>();
    integerIntArrayMap2.put(1, new int[][] {new int[] {1, 500, 1, 500}});

    SwaptionDataLattice cashReceiverPremiums = mock(SwaptionDataLattice.class);
    when(cashReceiverPremiums.getGridNodesPerMoneyness()).thenReturn(integerIntArrayMap2);

    SwaptionDataLattice physicalPremiumsATM = mock(SwaptionDataLattice.class);
    when(physicalPremiumsATM.getTenors(anyInt(), anyInt())).thenReturn(new int[] {});
    when(physicalPremiumsATM.getMaturities()).thenReturn(new int[] {1, 0, 1, 0});
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    VolatilityCubeFactory volatilityCubeFactory =
        new VolatilityCubeFactory(
            referenceDate,
            cashPayerPremiums,
            cashReceiverPremiums,
            physicalPremiumsATM,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            AnnuityMappingType.BASICPITERBARG);

    // Act
    SABRVolatilityCube actualBuildShiftedSmileSABRCubeResult =
        volatilityCubeFactory.buildShiftedSmileSABRCube(
            "Name", new AnalyticModelWithVolatilityCubes());

    // Assert
    verify(cashPayerPremiums).getDiscountCurveName();
    verify(cashPayerPremiums).getFixMetaSchedule();
    verify(cashPayerPremiums).getFloatMetaSchedule();
    verify(cashPayerPremiums).getForwardCurveName();
    verify(cashPayerPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(cashReceiverPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(physicalPremiumsATM).getMaturities();
    verify(physicalPremiumsATM, atLeast(1)).getTenors(eq(0), anyInt());
    assertTrue(actualBuildShiftedSmileSABRCubeResult.getBaseVolTable() instanceof DataTableLinear);
    assertTrue(actualBuildShiftedSmileSABRCubeResult.getRhoTable() instanceof DataTableLinear);
    assertTrue(
        actualBuildShiftedSmileSABRCubeResult.getUnderlyingTable() instanceof DataTableLinear);
    assertTrue(actualBuildShiftedSmileSABRCubeResult.getVolvolTable() instanceof DataTableLinear);
    assertEquals("Name", actualBuildShiftedSmileSABRCubeResult.getName());
    assertEquals(10.0d, actualBuildShiftedSmileSABRCubeResult.getCorrelationDecay(), 0.0);
    assertEquals(10.0d, actualBuildShiftedSmileSABRCubeResult.getIborOisDecorrelation(), 0.0);
    Map<String, Object> parameters = actualBuildShiftedSmileSABRCubeResult.getParameters();
    assertEquals(8, parameters.size());
    assertTrue(parameters.containsKey("Inherent correlationDecay"));
    assertTrue(parameters.containsKey("baseVolTable"));
    assertTrue(parameters.containsKey("rhoTable"));
    assertTrue(parameters.containsKey("sabrBeta"));
    assertTrue(parameters.containsKey("sabrDisplacement"));
    assertTrue(parameters.containsKey("volvolTable"));
    assertSame(referenceDate, actualBuildShiftedSmileSABRCubeResult.getReferenceDate());
  }

  /**
   * Test {@link VolatilityCubeFactory#buildShiftedSmileSABRCube(String, VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then BaseVolTable return {@link DataTableLinear}.
   * </ul>
   *
   * <p>Method under test: {@link VolatilityCubeFactory#buildShiftedSmileSABRCube(String,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SABRVolatilityCube VolatilityCubeFactory.buildShiftedSmileSABRCube(String, VolatilityCubeModel)"
  })
  public void testBuildShiftedSmileSABRCube_thenBaseVolTableReturnDataTableLinear()
      throws SolverException {
    // Arrange
    SwaptionDataLattice cashPayerPremiums = mock(SwaptionDataLattice.class);
    when(cashPayerPremiums.getDiscountCurveName()).thenReturn("3");
    when(cashPayerPremiums.getForwardCurveName()).thenReturn("Forward Curve Name");
    when(cashPayerPremiums.getGridNodesPerMoneyness()).thenReturn(new HashMap<>());
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(cashPayerPremiums.getFixMetaSchedule()).thenReturn(schedulePrototype);
    SchedulePrototype schedulePrototype2 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(cashPayerPremiums.getFloatMetaSchedule()).thenReturn(schedulePrototype2);

    SwaptionDataLattice cashReceiverPremiums = mock(SwaptionDataLattice.class);
    when(cashReceiverPremiums.getGridNodesPerMoneyness()).thenReturn(new HashMap<>());

    SwaptionDataLattice physicalPremiumsATM = mock(SwaptionDataLattice.class);
    when(physicalPremiumsATM.getTenors(anyInt(), anyInt())).thenReturn(new int[] {1, 0, 1, 0});
    when(physicalPremiumsATM.getMaturities()).thenReturn(new int[] {1, 0, 1, 0});
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    VolatilityCubeFactory volatilityCubeFactory =
        new VolatilityCubeFactory(
            referenceDate,
            cashPayerPremiums,
            cashReceiverPremiums,
            physicalPremiumsATM,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            AnnuityMappingType.BASICPITERBARG);

    // Act
    SABRVolatilityCube actualBuildShiftedSmileSABRCubeResult =
        volatilityCubeFactory.buildShiftedSmileSABRCube(
            "Name", new AnalyticModelWithVolatilityCubes());

    // Assert
    verify(cashPayerPremiums).getDiscountCurveName();
    verify(cashPayerPremiums).getFixMetaSchedule();
    verify(cashPayerPremiums).getFloatMetaSchedule();
    verify(cashPayerPremiums).getForwardCurveName();
    verify(cashPayerPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(cashReceiverPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(physicalPremiumsATM).getMaturities();
    verify(physicalPremiumsATM, atLeast(1)).getTenors(eq(0), anyInt());
    assertTrue(actualBuildShiftedSmileSABRCubeResult.getBaseVolTable() instanceof DataTableLinear);
    assertTrue(actualBuildShiftedSmileSABRCubeResult.getRhoTable() instanceof DataTableLinear);
    assertTrue(
        actualBuildShiftedSmileSABRCubeResult.getUnderlyingTable() instanceof DataTableLinear);
    assertTrue(actualBuildShiftedSmileSABRCubeResult.getVolvolTable() instanceof DataTableLinear);
    assertEquals("Name", actualBuildShiftedSmileSABRCubeResult.getName());
    assertEquals(10.0d, actualBuildShiftedSmileSABRCubeResult.getCorrelationDecay(), 0.0);
    assertEquals(10.0d, actualBuildShiftedSmileSABRCubeResult.getIborOisDecorrelation(), 0.0);
    Map<String, Object> parameters = actualBuildShiftedSmileSABRCubeResult.getParameters();
    assertEquals(8, parameters.size());
    assertTrue(parameters.containsKey("Inherent correlationDecay"));
    assertTrue(parameters.containsKey("baseVolTable"));
    assertTrue(parameters.containsKey("rhoTable"));
    assertTrue(parameters.containsKey("sabrBeta"));
    assertTrue(parameters.containsKey("sabrDisplacement"));
    assertTrue(parameters.containsKey("volvolTable"));
    assertSame(referenceDate, actualBuildShiftedSmileSABRCubeResult.getReferenceDate());
  }

  /**
   * Test {@link VolatilityCubeFactory#buildSABRVolatilityCube(String, VolatilityCubeModel, int[])}
   * with {@code name}, {@code model}, {@code terminations}.
   *
   * <p>Method under test: {@link VolatilityCubeFactory#buildSABRVolatilityCube(String,
   * VolatilityCubeModel, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SABRVolatilityCube VolatilityCubeFactory.buildSABRVolatilityCube(String, VolatilityCubeModel, int[])"
  })
  public void testBuildSABRVolatilityCubeWithNameModelTerminations() throws SolverException {
    // Arrange
    SwaptionDataLattice cashPayerPremiums = mock(SwaptionDataLattice.class);
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(cashPayerPremiums.getFixMetaSchedule()).thenReturn(schedulePrototype);
    SchedulePrototype schedulePrototype2 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(cashPayerPremiums.getFloatMetaSchedule()).thenReturn(schedulePrototype2);
    when(cashPayerPremiums.getTenors()).thenReturn(new int[] {1, 0, 1, 0});
    when(cashPayerPremiums.getMaturities()).thenReturn(new int[] {1, 0, 1, 0});
    when(cashPayerPremiums.getDiscountCurveName()).thenReturn("3");
    when(cashPayerPremiums.getForwardCurveName()).thenReturn("Forward Curve Name");
    when(cashPayerPremiums.getGridNodesPerMoneyness()).thenReturn(new HashMap<>());
    when(cashPayerPremiums.getQuotingConvention()).thenReturn(QuotingConvention.PAYERPRICE);

    SwaptionDataLattice cashReceiverPremiums = mock(SwaptionDataLattice.class);
    when(cashReceiverPremiums.getGridNodesPerMoneyness()).thenReturn(new HashMap<>());
    when(cashReceiverPremiums.getQuotingConvention()).thenReturn(QuotingConvention.RECEIVERPRICE);

    SwaptionDataLattice physicalPremiumsATM = mock(SwaptionDataLattice.class);
    when(physicalPremiumsATM.getTenors(anyInt(), anyInt())).thenReturn(new int[] {1, 0, 1, 0});
    when(physicalPremiumsATM.getMaturities()).thenReturn(new int[] {1, 0, 1, 0});
    SchedulePrototype schedulePrototype3 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(physicalPremiumsATM.getFloatMetaSchedule()).thenReturn(schedulePrototype3);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    VolatilityCubeFactory volatilityCubeFactory =
        new VolatilityCubeFactory(
            referenceDate,
            cashPayerPremiums,
            cashReceiverPremiums,
            physicalPremiumsATM,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            AnnuityMappingType.BASICPITERBARG);

    // Act
    SABRVolatilityCube actualBuildSABRVolatilityCubeResult =
        volatilityCubeFactory.buildSABRVolatilityCube(
            "Name", new AnalyticModelWithVolatilityCubes(), new int[] {1, -1, 1, -1});

    // Assert
    verify(cashPayerPremiums, atLeast(1)).getDiscountCurveName();
    verify(cashPayerPremiums).getFixMetaSchedule();
    verify(cashPayerPremiums).getFloatMetaSchedule();
    verify(physicalPremiumsATM).getFloatMetaSchedule();
    verify(cashPayerPremiums, atLeast(1)).getForwardCurveName();
    verify(cashPayerPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(cashReceiverPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(cashPayerPremiums).getMaturities();
    verify(physicalPremiumsATM).getMaturities();
    verify(cashPayerPremiums).getQuotingConvention();
    verify(cashReceiverPremiums).getQuotingConvention();
    verify(cashPayerPremiums, atLeast(1)).getTenors();
    verify(physicalPremiumsATM, atLeast(1)).getTenors(eq(0), anyInt());
    assertTrue(actualBuildSABRVolatilityCubeResult.getBaseVolTable() instanceof DataTableLinear);
    assertTrue(actualBuildSABRVolatilityCubeResult.getRhoTable() instanceof DataTableLinear);
    assertTrue(actualBuildSABRVolatilityCubeResult.getUnderlyingTable() instanceof DataTableLinear);
    assertTrue(actualBuildSABRVolatilityCubeResult.getVolvolTable() instanceof DataTableLinear);
    assertEquals("Name", actualBuildSABRVolatilityCubeResult.getName());
    assertEquals(10.0d, actualBuildSABRVolatilityCubeResult.getCorrelationDecay(), 0.0);
    assertEquals(10.0d, actualBuildSABRVolatilityCubeResult.getIborOisDecorrelation(), 0.0);
    Map<String, Object> parameters = actualBuildSABRVolatilityCubeResult.getParameters();
    assertEquals(8, parameters.size());
    assertTrue(parameters.containsKey("Inherent correlationDecay"));
    assertTrue(parameters.containsKey("baseVolTable"));
    assertTrue(parameters.containsKey("rhoTable"));
    assertTrue(parameters.containsKey("sabrBeta"));
    assertTrue(parameters.containsKey("sabrDisplacement"));
    assertTrue(parameters.containsKey("volvolTable"));
    assertSame(referenceDate, actualBuildSABRVolatilityCubeResult.getReferenceDate());
  }

  /**
   * Test {@link VolatilityCubeFactory#buildSABRVolatilityCube(String, VolatilityCubeModel, int[],
   * DataTable, DataTable, DataTable)} with {@code name}, {@code model}, {@code terminations},
   * {@code initialRhos}, {@code initialBaseVols}, {@code initialVolvols}.
   *
   * <p>Method under test: {@link VolatilityCubeFactory#buildSABRVolatilityCube(String,
   * VolatilityCubeModel, int[], DataTable, DataTable, DataTable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SABRVolatilityCube VolatilityCubeFactory.buildSABRVolatilityCube(String, VolatilityCubeModel, int[], DataTable, DataTable, DataTable)"
  })
  public void
      testBuildSABRVolatilityCubeWithNameModelTerminationsInitialRhosInitialBaseVolsInitialVolvols()
          throws SolverException {
    // Arrange
    HashMap<Integer, int[][]> integerIntArrayMap = new HashMap<>();
    integerIntArrayMap.put(1, new int[][] {new int[] {1, 250, 1, 250}});

    SwaptionDataLattice cashPayerPremiums = mock(SwaptionDataLattice.class);
    when(cashPayerPremiums.getTenors()).thenReturn(new int[] {});
    when(cashPayerPremiums.getMaturities()).thenReturn(new int[] {1, 0, 1, 0});
    when(cashPayerPremiums.getDiscountCurveName()).thenReturn("3");
    when(cashPayerPremiums.getForwardCurveName()).thenReturn("Forward Curve Name");
    when(cashPayerPremiums.getGridNodesPerMoneyness()).thenReturn(integerIntArrayMap);
    when(cashPayerPremiums.getQuotingConvention()).thenReturn(QuotingConvention.PAYERPRICE);

    HashMap<Integer, int[][]> integerIntArrayMap2 = new HashMap<>();
    integerIntArrayMap2.put(1, new int[][] {new int[] {1, 250, 1, 250}});

    SwaptionDataLattice cashReceiverPremiums = mock(SwaptionDataLattice.class);
    when(cashReceiverPremiums.getGridNodesPerMoneyness()).thenReturn(integerIntArrayMap2);
    when(cashReceiverPremiums.getQuotingConvention()).thenReturn(QuotingConvention.RECEIVERPRICE);

    SwaptionDataLattice physicalPremiumsATM = mock(SwaptionDataLattice.class);
    when(physicalPremiumsATM.getMaturities()).thenReturn(new int[] {1, 0, 1, 0});
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(physicalPremiumsATM.getFixMetaSchedule()).thenReturn(schedulePrototype);
    SchedulePrototype schedulePrototype2 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(physicalPremiumsATM.getFloatMetaSchedule()).thenReturn(schedulePrototype2);

    VolatilityCubeFactory volatilityCubeFactory =
        new VolatilityCubeFactory(
            LocalDate.of(1970, 1, 1),
            cashPayerPremiums,
            cashReceiverPremiums,
            physicalPremiumsATM,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            AnnuityMappingType.BASICPITERBARG);
    AnalyticModelWithVolatilityCubes model = new AnalyticModelWithVolatilityCubes();
    DataTableLight initialRhos = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight initialBaseVols = new DataTableLight("Name", TableConvention.MONTHS);

    // Act
    SABRVolatilityCube actualBuildSABRVolatilityCubeResult =
        volatilityCubeFactory.buildSABRVolatilityCube(
            "Name",
            model,
            new int[] {},
            initialRhos,
            initialBaseVols,
            new DataTableLight("Name", TableConvention.MONTHS));

    // Assert
    verify(cashPayerPremiums).getDiscountCurveName();
    verify(physicalPremiumsATM).getFixMetaSchedule();
    verify(physicalPremiumsATM, atLeast(1)).getFloatMetaSchedule();
    verify(cashPayerPremiums).getForwardCurveName();
    verify(cashPayerPremiums).getGridNodesPerMoneyness();
    verify(cashReceiverPremiums).getGridNodesPerMoneyness();
    verify(cashPayerPremiums).getMaturities();
    verify(physicalPremiumsATM).getMaturities();
    verify(cashPayerPremiums).getQuotingConvention();
    verify(cashReceiverPremiums).getQuotingConvention();
    verify(cashPayerPremiums, atLeast(1)).getTenors();
    assertTrue(actualBuildSABRVolatilityCubeResult.getBaseVolTable() instanceof DataTableLinear);
    assertTrue(actualBuildSABRVolatilityCubeResult.getRhoTable() instanceof DataTableLinear);
    assertTrue(actualBuildSABRVolatilityCubeResult.getUnderlyingTable() instanceof DataTableLinear);
    assertTrue(actualBuildSABRVolatilityCubeResult.getVolvolTable() instanceof DataTableLinear);
    Map<String, Object> parameters = actualBuildSABRVolatilityCubeResult.getParameters();
    assertEquals(8, parameters.size());
    assertTrue(parameters.containsKey("Inherent correlationDecay"));
    assertTrue(parameters.containsKey("baseVolTable"));
    assertTrue(parameters.containsKey("rhoTable"));
    assertTrue(parameters.containsKey("sabrBeta"));
    assertTrue(parameters.containsKey("sabrDisplacement"));
    assertTrue(parameters.containsKey("volvolTable"));
  }

  /**
   * Test {@link VolatilityCubeFactory#buildSABRVolatilityCube(String, VolatilityCubeModel, int[],
   * DataTable, DataTable, DataTable)} with {@code name}, {@code model}, {@code terminations},
   * {@code initialRhos}, {@code initialBaseVols}, {@code initialVolvols}.
   *
   * <p>Method under test: {@link VolatilityCubeFactory#buildSABRVolatilityCube(String,
   * VolatilityCubeModel, int[], DataTable, DataTable, DataTable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SABRVolatilityCube VolatilityCubeFactory.buildSABRVolatilityCube(String, VolatilityCubeModel, int[], DataTable, DataTable, DataTable)"
  })
  public void
      testBuildSABRVolatilityCubeWithNameModelTerminationsInitialRhosInitialBaseVolsInitialVolvols2()
          throws SolverException {
    // Arrange
    HashMap<Integer, int[][]> integerIntArrayMap = new HashMap<>();
    integerIntArrayMap.put(1, new int[][] {new int[] {1, 250, 1, 250}});

    SwaptionDataLattice cashPayerPremiums = mock(SwaptionDataLattice.class);
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(cashPayerPremiums.getFixMetaSchedule()).thenReturn(schedulePrototype);
    SchedulePrototype schedulePrototype2 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(cashPayerPremiums.getFloatMetaSchedule()).thenReturn(schedulePrototype2);
    when(cashPayerPremiums.getTenors(anyInt(), anyInt())).thenReturn(new int[] {});
    when(cashPayerPremiums.containsEntryFor(anyInt(), anyInt(), anyInt())).thenReturn(true);
    when(cashPayerPremiums.getTenors()).thenReturn(new int[] {1, 0, 1, 0});
    when(cashPayerPremiums.getMaturities()).thenReturn(new int[] {1, 0, 1, 0});
    when(cashPayerPremiums.getDiscountCurveName()).thenReturn("3");
    when(cashPayerPremiums.getForwardCurveName()).thenReturn("Forward Curve Name");
    when(cashPayerPremiums.getGridNodesPerMoneyness()).thenReturn(integerIntArrayMap);
    when(cashPayerPremiums.getQuotingConvention()).thenReturn(QuotingConvention.PAYERPRICE);

    HashMap<Integer, int[][]> integerIntArrayMap2 = new HashMap<>();
    integerIntArrayMap2.put(1, new int[][] {new int[] {1, 250, 1, 250}});

    SwaptionDataLattice cashReceiverPremiums = mock(SwaptionDataLattice.class);
    SchedulePrototype schedulePrototype3 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(cashReceiverPremiums.getFixMetaSchedule()).thenReturn(schedulePrototype3);
    SchedulePrototype schedulePrototype4 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(cashReceiverPremiums.getFloatMetaSchedule()).thenReturn(schedulePrototype4);
    when(cashReceiverPremiums.getTenors(anyInt(), anyInt())).thenReturn(new int[] {});
    when(cashReceiverPremiums.containsEntryFor(anyInt(), anyInt(), anyInt())).thenReturn(true);
    when(cashReceiverPremiums.getGridNodesPerMoneyness()).thenReturn(integerIntArrayMap2);
    when(cashReceiverPremiums.getQuotingConvention()).thenReturn(QuotingConvention.RECEIVERPRICE);

    SwaptionDataLattice physicalPremiumsATM = mock(SwaptionDataLattice.class);
    when(physicalPremiumsATM.getMaturities()).thenReturn(new int[] {1, 0, 1, 0});
    SchedulePrototype schedulePrototype5 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(physicalPremiumsATM.getFixMetaSchedule()).thenReturn(schedulePrototype5);
    SchedulePrototype schedulePrototype6 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(physicalPremiumsATM.getFloatMetaSchedule()).thenReturn(schedulePrototype6);

    VolatilityCubeFactory volatilityCubeFactory =
        new VolatilityCubeFactory(
            LocalDate.of(1970, 1, 1),
            cashPayerPremiums,
            cashReceiverPremiums,
            physicalPremiumsATM,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            AnnuityMappingType.BASICPITERBARG);
    AnalyticModelWithVolatilityCubes model = new AnalyticModelWithVolatilityCubes();
    DataTableLight initialRhos = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight initialBaseVols = new DataTableLight("Name", TableConvention.MONTHS);

    // Act
    SABRVolatilityCube actualBuildSABRVolatilityCubeResult =
        volatilityCubeFactory.buildSABRVolatilityCube(
            "Name",
            model,
            new int[] {},
            initialRhos,
            initialBaseVols,
            new DataTableLight("Name", TableConvention.MONTHS));

    // Assert
    verify(cashPayerPremiums, atLeast(1)).containsEntryFor(anyInt(), anyInt(), eq(1));
    verify(cashReceiverPremiums, atLeast(1)).containsEntryFor(anyInt(), anyInt(), eq(1));
    verify(cashPayerPremiums).getDiscountCurveName();
    verify(physicalPremiumsATM).getFixMetaSchedule();
    verify(cashPayerPremiums, atLeast(1)).getFixMetaSchedule();
    verify(cashReceiverPremiums, atLeast(1)).getFixMetaSchedule();
    verify(physicalPremiumsATM, atLeast(1)).getFloatMetaSchedule();
    verify(cashPayerPremiums, atLeast(1)).getFloatMetaSchedule();
    verify(cashReceiverPremiums, atLeast(1)).getFloatMetaSchedule();
    verify(cashPayerPremiums).getForwardCurveName();
    verify(cashPayerPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(cashReceiverPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(cashPayerPremiums).getMaturities();
    verify(physicalPremiumsATM).getMaturities();
    verify(cashPayerPremiums).getQuotingConvention();
    verify(cashReceiverPremiums).getQuotingConvention();
    verify(cashPayerPremiums, atLeast(1)).getTenors();
    verify(cashPayerPremiums, atLeast(1)).getTenors(eq(1), anyInt());
    verify(cashReceiverPremiums, atLeast(1)).getTenors(eq(1), anyInt());
    assertTrue(actualBuildSABRVolatilityCubeResult.getBaseVolTable() instanceof DataTableLinear);
    assertTrue(actualBuildSABRVolatilityCubeResult.getRhoTable() instanceof DataTableLinear);
    assertTrue(actualBuildSABRVolatilityCubeResult.getUnderlyingTable() instanceof DataTableLinear);
    assertTrue(actualBuildSABRVolatilityCubeResult.getVolvolTable() instanceof DataTableLinear);
    Map<String, Object> parameters = actualBuildSABRVolatilityCubeResult.getParameters();
    assertEquals(8, parameters.size());
    assertTrue(parameters.containsKey("Inherent correlationDecay"));
    assertTrue(parameters.containsKey("baseVolTable"));
    assertTrue(parameters.containsKey("rhoTable"));
    assertTrue(parameters.containsKey("sabrBeta"));
    assertTrue(parameters.containsKey("sabrDisplacement"));
    assertTrue(parameters.containsKey("volvolTable"));
  }

  /**
   * Test {@link VolatilityCubeFactory#buildSABRVolatilityCube(String, VolatilityCubeModel, int[],
   * DataTable, DataTable, DataTable)} with {@code name}, {@code model}, {@code terminations},
   * {@code initialRhos}, {@code initialBaseVols}, {@code initialVolvols}.
   *
   * <p>Method under test: {@link VolatilityCubeFactory#buildSABRVolatilityCube(String,
   * VolatilityCubeModel, int[], DataTable, DataTable, DataTable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SABRVolatilityCube VolatilityCubeFactory.buildSABRVolatilityCube(String, VolatilityCubeModel, int[], DataTable, DataTable, DataTable)"
  })
  public void
      testBuildSABRVolatilityCubeWithNameModelTerminationsInitialRhosInitialBaseVolsInitialVolvols3()
          throws SolverException {
    // Arrange
    HashMap<Integer, int[][]> integerIntArrayMap = new HashMap<>();
    integerIntArrayMap.put(1, new int[][] {new int[] {1, 250, 1, 250}});

    SwaptionDataLattice cashPayerPremiums = mock(SwaptionDataLattice.class);
    SchedulePrototype schedulePrototype =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(cashPayerPremiums.getFixMetaSchedule()).thenReturn(schedulePrototype);
    SchedulePrototype schedulePrototype2 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(cashPayerPremiums.getFloatMetaSchedule()).thenReturn(schedulePrototype2);
    when(cashPayerPremiums.getTenors()).thenReturn(new int[] {});
    when(cashPayerPremiums.getMaturities()).thenReturn(new int[] {1, 0, 1, 0});
    when(cashPayerPremiums.getDiscountCurveName()).thenReturn("3");
    when(cashPayerPremiums.getForwardCurveName()).thenReturn("Forward Curve Name");
    when(cashPayerPremiums.getGridNodesPerMoneyness()).thenReturn(integerIntArrayMap);
    when(cashPayerPremiums.getQuotingConvention()).thenReturn(QuotingConvention.PAYERPRICE);

    HashMap<Integer, int[][]> integerIntArrayMap2 = new HashMap<>();
    integerIntArrayMap2.put(1, new int[][] {new int[] {1, 250, 1, 250}});

    SwaptionDataLattice cashReceiverPremiums = mock(SwaptionDataLattice.class);
    when(cashReceiverPremiums.getGridNodesPerMoneyness()).thenReturn(integerIntArrayMap2);
    when(cashReceiverPremiums.getQuotingConvention()).thenReturn(QuotingConvention.RECEIVERPRICE);

    SwaptionDataLattice physicalPremiumsATM = mock(SwaptionDataLattice.class);
    when(physicalPremiumsATM.getTenors(anyInt(), anyInt())).thenReturn(new int[] {});
    when(physicalPremiumsATM.getMaturities()).thenReturn(new int[] {1, 0, 1, 0});
    SchedulePrototype schedulePrototype3 =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    when(physicalPremiumsATM.getFloatMetaSchedule()).thenReturn(schedulePrototype3);

    VolatilityCubeFactory volatilityCubeFactory =
        new VolatilityCubeFactory(
            LocalDate.of(1970, 1, 1),
            cashPayerPremiums,
            cashReceiverPremiums,
            physicalPremiumsATM,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            AnnuityMappingType.BASICPITERBARG);
    AnalyticModelWithVolatilityCubes model = new AnalyticModelWithVolatilityCubes();
    DataTableLight initialBaseVols = new DataTableLight("Name", TableConvention.MONTHS);

    // Act
    SABRVolatilityCube actualBuildSABRVolatilityCubeResult =
        volatilityCubeFactory.buildSABRVolatilityCube(
            "Name",
            model,
            new int[] {},
            null,
            initialBaseVols,
            new DataTableLight("Name", TableConvention.MONTHS));

    // Assert
    verify(cashPayerPremiums, atLeast(1)).getDiscountCurveName();
    verify(cashPayerPremiums).getFixMetaSchedule();
    verify(cashPayerPremiums).getFloatMetaSchedule();
    verify(physicalPremiumsATM).getFloatMetaSchedule();
    verify(cashPayerPremiums, atLeast(1)).getForwardCurveName();
    verify(cashPayerPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(cashReceiverPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(cashPayerPremiums).getMaturities();
    verify(physicalPremiumsATM).getMaturities();
    verify(cashPayerPremiums).getQuotingConvention();
    verify(cashReceiverPremiums).getQuotingConvention();
    verify(cashPayerPremiums, atLeast(1)).getTenors();
    verify(physicalPremiumsATM, atLeast(1)).getTenors(eq(0), anyInt());
    assertTrue(actualBuildSABRVolatilityCubeResult.getBaseVolTable() instanceof DataTableLinear);
    assertTrue(actualBuildSABRVolatilityCubeResult.getRhoTable() instanceof DataTableLinear);
    assertTrue(actualBuildSABRVolatilityCubeResult.getUnderlyingTable() instanceof DataTableLinear);
    assertTrue(actualBuildSABRVolatilityCubeResult.getVolvolTable() instanceof DataTableLinear);
    Map<String, Object> parameters = actualBuildSABRVolatilityCubeResult.getParameters();
    assertEquals(8, parameters.size());
    assertTrue(parameters.containsKey("Inherent correlationDecay"));
    assertTrue(parameters.containsKey("baseVolTable"));
    assertTrue(parameters.containsKey("rhoTable"));
    assertTrue(parameters.containsKey("sabrBeta"));
    assertTrue(parameters.containsKey("sabrDisplacement"));
    assertTrue(parameters.containsKey("volvolTable"));
  }

  /**
   * Test {@link VolatilityCubeFactory#setCalibrationParameters(int, int)}.
   *
   * <p>Method under test: {@link VolatilityCubeFactory#setCalibrationParameters(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void VolatilityCubeFactory.setCalibrationParameters(int, int)"})
  public void testSetCalibrationParameters() {
    // Arrange
    VolatilityCubeFactory volatilityCubeFactory =
        new VolatilityCubeFactory(
            LocalDate.of(1970, 1, 1),
            mock(SwaptionDataLattice.class),
            mock(SwaptionDataLattice.class),
            mock(SwaptionDataLattice.class),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            AnnuityMappingType.BASICPITERBARG);

    // Act
    volatilityCubeFactory.setCalibrationParameters(3, 10);

    // Assert
    assertEquals(10, volatilityCubeFactory.getNumberOfThreads());
    assertEquals(3, volatilityCubeFactory.getMaxIterations());
  }

  /**
   * Test {@link VolatilityCubeFactory#setReplicationParameters(double, double, int)}.
   *
   * <p>Method under test: {@link VolatilityCubeFactory#setReplicationParameters(double, double,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void VolatilityCubeFactory.setReplicationParameters(double, double, int)"})
  public void testSetReplicationParameters() {
    // Arrange
    VolatilityCubeFactory volatilityCubeFactory =
        new VolatilityCubeFactory(
            LocalDate.of(1970, 1, 1),
            mock(SwaptionDataLattice.class),
            mock(SwaptionDataLattice.class),
            mock(SwaptionDataLattice.class),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            AnnuityMappingType.BASICPITERBARG);

    // Act
    volatilityCubeFactory.setReplicationParameters(10.0d, 10.0d, 10);

    // Assert
    assertEquals(10.0d, volatilityCubeFactory.getReplicationLowerBound(), 0.0);
    assertEquals(10.0d, volatilityCubeFactory.getReplicationNumberOfEvaluationPoints(), 0.0);
    assertEquals(10.0d, volatilityCubeFactory.getReplicationUpperBound(), 0.0);
  }

  /**
   * Test {@link VolatilityCubeFactory#getReplicationNumberOfEvaluationPoints()}.
   *
   * <p>Method under test: {@link VolatilityCubeFactory#getReplicationNumberOfEvaluationPoints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double VolatilityCubeFactory.getReplicationNumberOfEvaluationPoints()"})
  public void testGetReplicationNumberOfEvaluationPoints() {
    // Arrange
    VolatilityCubeFactory volatilityCubeFactory =
        new VolatilityCubeFactory(
            LocalDate.of(1970, 1, 1),
            mock(SwaptionDataLattice.class),
            mock(SwaptionDataLattice.class),
            mock(SwaptionDataLattice.class),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            AnnuityMappingType.BASICPITERBARG);

    // Act and Assert
    assertEquals(500.0d, volatilityCubeFactory.getReplicationNumberOfEvaluationPoints(), 0.0);
  }
}
