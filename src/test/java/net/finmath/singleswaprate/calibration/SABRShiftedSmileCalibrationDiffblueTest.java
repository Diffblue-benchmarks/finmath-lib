package net.finmath.singleswaprate.calibration;

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
import java.util.TreeSet;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.volatilities.SwaptionDataLattice;
import net.finmath.optimizer.SolverException;
import net.finmath.singleswaprate.data.DataTable;
import net.finmath.singleswaprate.data.DataTableLight;
import net.finmath.singleswaprate.data.DataTableLinear;
import net.finmath.singleswaprate.model.volatilities.SABRVolatilityCube;
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

public class SABRShiftedSmileCalibrationDiffblueTest {
  /**
   * Test {@link SABRShiftedSmileCalibration#createSABRVolatilityCube(String, LocalDate,
   * SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Given empty array of {@code int}.
   * </ul>
   *
   * <p>Method under test: {@link SABRShiftedSmileCalibration#createSABRVolatilityCube(String,
   * LocalDate, SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SABRVolatilityCube SABRShiftedSmileCalibration.createSABRVolatilityCube(String, LocalDate, SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel, double, double, double, double)"
  })
  public void testCreateSABRVolatilityCube_givenEmptyArrayOfInt() throws SolverException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

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

    // Act
    SABRVolatilityCube actualCreateSABRVolatilityCubeResult =
        SABRShiftedSmileCalibration.createSABRVolatilityCube(
            "Name",
            referenceDate,
            cashPayerPremiums,
            cashReceiverPremiums,
            physicalPremiumsATM,
            mock(AnalyticModel.class),
            10.0d,
            10.0d,
            10.0d,
            10.0d);

    // Assert
    verify(cashPayerPremiums).getDiscountCurveName();
    verify(cashPayerPremiums).getFixMetaSchedule();
    verify(cashPayerPremiums).getFloatMetaSchedule();
    verify(cashPayerPremiums).getForwardCurveName();
    verify(cashPayerPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(cashReceiverPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(physicalPremiumsATM).getMaturities();
    verify(physicalPremiumsATM, atLeast(1)).getTenors(eq(0), anyInt());
    assertTrue(actualCreateSABRVolatilityCubeResult.getBaseVolTable() instanceof DataTableLinear);
    assertTrue(actualCreateSABRVolatilityCubeResult.getRhoTable() instanceof DataTableLinear);
    assertTrue(
        actualCreateSABRVolatilityCubeResult.getUnderlyingTable() instanceof DataTableLinear);
    assertTrue(actualCreateSABRVolatilityCubeResult.getVolvolTable() instanceof DataTableLinear);
    assertEquals("Name", actualCreateSABRVolatilityCubeResult.getName());
    assertEquals(10.0d, actualCreateSABRVolatilityCubeResult.getCorrelationDecay(), 0.0);
    assertEquals(10.0d, actualCreateSABRVolatilityCubeResult.getIborOisDecorrelation(), 0.0);
    Map<String, Object> parameters = actualCreateSABRVolatilityCubeResult.getParameters();
    assertEquals(8, parameters.size());
    assertTrue(parameters.containsKey("Inherent correlationDecay"));
    assertTrue(parameters.containsKey("baseVolTable"));
    assertTrue(parameters.containsKey("rhoTable"));
    assertTrue(parameters.containsKey("sabrBeta"));
    assertTrue(parameters.containsKey("sabrDisplacement"));
    assertTrue(parameters.containsKey("volvolTable"));
    assertSame(referenceDate, actualCreateSABRVolatilityCubeResult.getReferenceDate());
  }

  /**
   * Test {@link SABRShiftedSmileCalibration#createSABRVolatilityCube(String, LocalDate,
   * SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel, double, double,
   * double, double)}.
   *
   * <ul>
   *   <li>Then BaseVolTable return {@link DataTableLinear}.
   * </ul>
   *
   * <p>Method under test: {@link SABRShiftedSmileCalibration#createSABRVolatilityCube(String,
   * LocalDate, SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SABRVolatilityCube SABRShiftedSmileCalibration.createSABRVolatilityCube(String, LocalDate, SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel, double, double, double, double)"
  })
  public void testCreateSABRVolatilityCube_thenBaseVolTableReturnDataTableLinear()
      throws SolverException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

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

    // Act
    SABRVolatilityCube actualCreateSABRVolatilityCubeResult =
        SABRShiftedSmileCalibration.createSABRVolatilityCube(
            "Name",
            referenceDate,
            cashPayerPremiums,
            cashReceiverPremiums,
            physicalPremiumsATM,
            mock(AnalyticModel.class),
            10.0d,
            10.0d,
            10.0d,
            10.0d);

    // Assert
    verify(cashPayerPremiums).getDiscountCurveName();
    verify(cashPayerPremiums).getFixMetaSchedule();
    verify(cashPayerPremiums).getFloatMetaSchedule();
    verify(cashPayerPremiums).getForwardCurveName();
    verify(cashPayerPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(cashReceiverPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(physicalPremiumsATM).getMaturities();
    verify(physicalPremiumsATM, atLeast(1)).getTenors(eq(0), anyInt());
    assertTrue(actualCreateSABRVolatilityCubeResult.getBaseVolTable() instanceof DataTableLinear);
    assertTrue(actualCreateSABRVolatilityCubeResult.getRhoTable() instanceof DataTableLinear);
    assertTrue(
        actualCreateSABRVolatilityCubeResult.getUnderlyingTable() instanceof DataTableLinear);
    assertTrue(actualCreateSABRVolatilityCubeResult.getVolvolTable() instanceof DataTableLinear);
    assertEquals("Name", actualCreateSABRVolatilityCubeResult.getName());
    assertEquals(10.0d, actualCreateSABRVolatilityCubeResult.getCorrelationDecay(), 0.0);
    assertEquals(10.0d, actualCreateSABRVolatilityCubeResult.getIborOisDecorrelation(), 0.0);
    Map<String, Object> parameters = actualCreateSABRVolatilityCubeResult.getParameters();
    assertEquals(8, parameters.size());
    assertTrue(parameters.containsKey("Inherent correlationDecay"));
    assertTrue(parameters.containsKey("baseVolTable"));
    assertTrue(parameters.containsKey("rhoTable"));
    assertTrue(parameters.containsKey("sabrBeta"));
    assertTrue(parameters.containsKey("sabrDisplacement"));
    assertTrue(parameters.containsKey("volvolTable"));
    assertSame(referenceDate, actualCreateSABRVolatilityCubeResult.getReferenceDate());
  }

  /**
   * Test {@link SABRShiftedSmileCalibration#createVolatilityCubeLattice(String, LocalDate,
   * SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel)}.
   *
   * <ul>
   *   <li>Given empty array of {@code int}.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link SABRShiftedSmileCalibration#createVolatilityCubeLattice(String,
   * LocalDate, SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map SABRShiftedSmileCalibration.createVolatilityCubeLattice(String, LocalDate, SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel)"
  })
  public void testCreateVolatilityCubeLattice_givenEmptyArrayOfInt_thenReturnSizeIsThree() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

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

    // Act
    Map<Integer, DataTable> actualCreateVolatilityCubeLatticeResult =
        SABRShiftedSmileCalibration.createVolatilityCubeLattice(
            "Name",
            referenceDate,
            cashPayerPremiums,
            cashReceiverPremiums,
            physicalPremiumsATM,
            mock(AnalyticModel.class));

    // Assert
    verify(cashPayerPremiums).getDiscountCurveName();
    verify(cashPayerPremiums).getFixMetaSchedule();
    verify(cashPayerPremiums).getFloatMetaSchedule();
    verify(cashPayerPremiums).getForwardCurveName();
    verify(cashPayerPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(cashReceiverPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(physicalPremiumsATM).getMaturities();
    verify(physicalPremiumsATM, atLeast(1)).getTenors(eq(0), anyInt());
    assertEquals(3, actualCreateVolatilityCubeLatticeResult.size());
    assertTrue(actualCreateVolatilityCubeLatticeResult.get(-1) instanceof DataTableLight);
    assertTrue(actualCreateVolatilityCubeLatticeResult.get(0) instanceof DataTableLight);
    assertTrue(actualCreateVolatilityCubeLatticeResult.get(1) instanceof DataTableLight);
  }

  /**
   * Test {@link SABRShiftedSmileCalibration#createVolatilityCubeLattice(String, LocalDate,
   * SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel)}.
   *
   * <ul>
   *   <li>When {@link AnalyticModel}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link SABRShiftedSmileCalibration#createVolatilityCubeLattice(String,
   * LocalDate, SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map SABRShiftedSmileCalibration.createVolatilityCubeLattice(String, LocalDate, SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel)"
  })
  public void testCreateVolatilityCubeLattice_whenAnalyticModel_thenReturnSizeIsOne() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

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

    // Act
    Map<Integer, DataTable> actualCreateVolatilityCubeLatticeResult =
        SABRShiftedSmileCalibration.createVolatilityCubeLattice(
            "Name",
            referenceDate,
            cashPayerPremiums,
            cashReceiverPremiums,
            physicalPremiumsATM,
            mock(AnalyticModel.class));

    // Assert
    verify(cashPayerPremiums).getDiscountCurveName();
    verify(cashPayerPremiums).getFixMetaSchedule();
    verify(cashPayerPremiums).getFloatMetaSchedule();
    verify(cashPayerPremiums).getForwardCurveName();
    verify(cashPayerPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(cashReceiverPremiums, atLeast(1)).getGridNodesPerMoneyness();
    verify(physicalPremiumsATM).getMaturities();
    verify(physicalPremiumsATM, atLeast(1)).getTenors(eq(0), anyInt());
    assertEquals(1, actualCreateVolatilityCubeLatticeResult.size());
    DataTable getResult = actualCreateVolatilityCubeLatticeResult.get(0);
    assertTrue(getResult instanceof DataTableLight);
    TreeSet<Integer> maturities = getResult.getMaturities();
    assertTrue(maturities.isEmpty());
    assertEquals(maturities, getResult.getTerminations());
  }

  /**
   * Test {@link SABRShiftedSmileCalibration#SABRShiftedSmileCalibration(LocalDate,
   * SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel, double, double,
   * double, double)}.
   *
   * <p>Method under test: {@link SABRShiftedSmileCalibration#SABRShiftedSmileCalibration(LocalDate,
   * SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SABRShiftedSmileCalibration.<init>(LocalDate, SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, AnalyticModel, double, double, double, double)"
  })
  public void testNewSABRShiftedSmileCalibration() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    SwaptionDataLattice cashPayerPremiums = mock(SwaptionDataLattice.class);
    when(cashPayerPremiums.getDiscountCurveName()).thenReturn("3");
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

    // Act
    SABRShiftedSmileCalibration actualSabrShiftedSmileCalibration =
        new SABRShiftedSmileCalibration(
            referenceDate,
            cashPayerPremiums,
            mock(SwaptionDataLattice.class),
            mock(SwaptionDataLattice.class),
            mock(AnalyticModel.class),
            10.0d,
            10.0d,
            10.0d,
            10.0d);

    // Assert
    verify(cashPayerPremiums).getDiscountCurveName();
    verify(cashPayerPremiums).getFixMetaSchedule();
    verify(cashPayerPremiums).getFloatMetaSchedule();
    verify(cashPayerPremiums).getForwardCurveName();
    assertEquals(500, actualSabrShiftedSmileCalibration.getMaxIterations());
    assertEquals(8, actualSabrShiftedSmileCalibration.getNumberOfThreads());
    assertTrue(actualSabrShiftedSmileCalibration.isUseLinearInterpolation());
  }

  /**
   * Test {@link SABRShiftedSmileCalibration#setCalibrationParameters(int, int)}.
   *
   * <p>Method under test: {@link SABRShiftedSmileCalibration#setCalibrationParameters(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SABRShiftedSmileCalibration.setCalibrationParameters(int, int)"})
  public void testSetCalibrationParameters() {
    // Arrange
    SwaptionDataLattice cashPayerPremiums = mock(SwaptionDataLattice.class);
    when(cashPayerPremiums.getDiscountCurveName()).thenReturn("3");
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
    SABRShiftedSmileCalibration sabrShiftedSmileCalibration =
        new SABRShiftedSmileCalibration(
            LocalDate.of(1970, 1, 1),
            cashPayerPremiums,
            mock(SwaptionDataLattice.class),
            mock(SwaptionDataLattice.class),
            mock(AnalyticModel.class),
            10.0d,
            10.0d,
            10.0d,
            10.0d);

    // Act
    sabrShiftedSmileCalibration.setCalibrationParameters(3, 10);

    // Assert
    verify(cashPayerPremiums).getDiscountCurveName();
    verify(cashPayerPremiums).getFixMetaSchedule();
    verify(cashPayerPremiums).getFloatMetaSchedule();
    verify(cashPayerPremiums).getForwardCurveName();
    assertEquals(10, sabrShiftedSmileCalibration.getNumberOfThreads());
    assertEquals(3, sabrShiftedSmileCalibration.getMaxIterations());
  }
}
