package net.finmath.modelling.productfactory;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import net.finmath.exception.CalculationException;
import net.finmath.modelling.InterestRateProductDescriptor;
import net.finmath.modelling.ProductDescriptor;
import net.finmath.modelling.descriptor.InterestRateSwapLegProductDescriptor;
import net.finmath.modelling.descriptor.InterestRateSwapProductDescriptor;
import net.finmath.modelling.descriptor.InterestRateSwaptionProductDescriptor;
import net.finmath.modelling.descriptor.ScheduleDescriptor;
import net.finmath.modelling.descriptor.SingleAssetDigitalOptionProductDescriptor;
import net.finmath.modelling.productfactory.InterestRateMonteCarloProductFactory.SwapLegMonteCarlo;
import net.finmath.modelling.productfactory.InterestRateMonteCarloProductFactory.SwapMonteCarlo;
import net.finmath.modelling.productfactory.InterestRateMonteCarloProductFactory.SwaptionPhysicalMonteCarlo;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.Period;
import net.finmath.time.Schedule;
import net.finmath.time.ScheduleFromPeriods;
import net.finmath.time.TenorFromArray;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class InterestRateMonteCarloProductFactoryDiffblueTest {
  /**
   * Test {@link InterestRateMonteCarloProductFactory#getProductFromDescriptor(ProductDescriptor)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InterestRateMonteCarloProductFactory#getProductFromDescriptor(ProductDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.modelling.DescribedProduct InterestRateMonteCarloProductFactory.getProductFromDescriptor(ProductDescriptor)"
  })
  public void testGetProductFromDescriptor_thenThrowIllegalArgumentException() {
    // Arrange
    InterestRateMonteCarloProductFactory interestRateMonteCarloProductFactory =
        new InterestRateMonteCarloProductFactory(LocalDate.of(1970, 1, 1));
    SingleAssetDigitalOptionProductDescriptor descriptor =
        new SingleAssetDigitalOptionProductDescriptor(
            "Name Of Underlying", LocalDate.of(1970, 1, 1), 10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> interestRateMonteCarloProductFactory.getProductFromDescriptor(descriptor));
  }

  /**
   * Test SwapLegMonteCarlo {@link SwapLegMonteCarlo#getDescriptor()}.
   *
   * <p>Method under test: {@link SwapLegMonteCarlo#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InterestRateSwapLegProductDescriptor SwapLegMonteCarlo.getDescriptor()"})
  public void testSwapLegMonteCarloGetDescriptor() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    InterestRateSwapLegProductDescriptor descriptor =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", new ScheduleDescriptor(schedule), 10.0d, 10.0d, true);
    SwapLegMonteCarlo swapLegMonteCarlo =
        new SwapLegMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertSame(descriptor, swapLegMonteCarlo.getDescriptor());
  }

  /**
   * Test SwapLegMonteCarlo {@link
   * SwapLegMonteCarlo#SwapLegMonteCarlo(InterestRateSwapLegProductDescriptor, LocalDate)}.
   *
   * <p>Method under test: {@link
   * SwapLegMonteCarlo#SwapLegMonteCarlo(InterestRateSwapLegProductDescriptor, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLegMonteCarlo.<init>(InterestRateSwapLegProductDescriptor, LocalDate)"
  })
  public void testSwapLegMonteCarloNewSwapLegMonteCarlo() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    InterestRateSwapLegProductDescriptor descriptor =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", new ScheduleDescriptor(schedule), 10.0d, 10.0d, true);

    // Act
    SwapLegMonteCarlo actualSwapLegMonteCarlo =
        new SwapLegMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Assert
    assertSame(descriptor, actualSwapLegMonteCarlo.getDescriptor());
  }

  /**
   * Test SwapLegMonteCarlo {@link
   * SwapLegMonteCarlo#SwapLegMonteCarlo(InterestRateSwapLegProductDescriptor, LocalDate)}.
   *
   * <p>Method under test: {@link
   * SwapLegMonteCarlo#SwapLegMonteCarlo(InterestRateSwapLegProductDescriptor, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLegMonteCarlo.<init>(InterestRateSwapLegProductDescriptor, LocalDate)"
  })
  public void testSwapLegMonteCarloNewSwapLegMonteCarlo2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    InterestRateSwapLegProductDescriptor descriptor =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", new ScheduleDescriptor(schedule), 10.0d, 10.0d, true);

    // Act
    SwapLegMonteCarlo actualSwapLegMonteCarlo =
        new SwapLegMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Assert
    assertSame(descriptor, actualSwapLegMonteCarlo.getDescriptor());
  }

  /**
   * Test SwapLegMonteCarlo {@link
   * SwapLegMonteCarlo#SwapLegMonteCarlo(InterestRateSwapLegProductDescriptor, LocalDate)}.
   *
   * <p>Method under test: {@link
   * SwapLegMonteCarlo#SwapLegMonteCarlo(InterestRateSwapLegProductDescriptor, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLegMonteCarlo.<init>(InterestRateSwapLegProductDescriptor, LocalDate)"
  })
  public void testSwapLegMonteCarloNewSwapLegMonteCarlo3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.now(),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    InterestRateSwapLegProductDescriptor descriptor =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", new ScheduleDescriptor(schedule), 10.0d, 10.0d, true);

    // Act
    SwapLegMonteCarlo actualSwapLegMonteCarlo =
        new SwapLegMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Assert
    assertSame(descriptor, actualSwapLegMonteCarlo.getDescriptor());
  }

  /**
   * Test SwapLegMonteCarlo {@link
   * SwapLegMonteCarlo#SwapLegMonteCarlo(InterestRateSwapLegProductDescriptor, LocalDate)}.
   *
   * <p>Method under test: {@link
   * SwapLegMonteCarlo#SwapLegMonteCarlo(InterestRateSwapLegProductDescriptor, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLegMonteCarlo.<init>(InterestRateSwapLegProductDescriptor, LocalDate)"
  })
  public void testSwapLegMonteCarloNewSwapLegMonteCarlo4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.now(),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    InterestRateSwapLegProductDescriptor descriptor =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", new ScheduleDescriptor(schedule), 10.0d, 0.0d, true);

    // Act
    SwapLegMonteCarlo actualSwapLegMonteCarlo =
        new SwapLegMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Assert
    assertSame(descriptor, actualSwapLegMonteCarlo.getDescriptor());
  }

  /**
   * Test SwapLegMonteCarlo {@link
   * SwapLegMonteCarlo#SwapLegMonteCarlo(InterestRateSwapLegProductDescriptor, LocalDate)}.
   *
   * <p>Method under test: {@link
   * SwapLegMonteCarlo#SwapLegMonteCarlo(InterestRateSwapLegProductDescriptor, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLegMonteCarlo.<init>(InterestRateSwapLegProductDescriptor, LocalDate)"
  })
  public void testSwapLegMonteCarloNewSwapLegMonteCarlo5() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    InterestRateSwapLegProductDescriptor descriptor =
        new InterestRateSwapLegProductDescriptor(
            null, "3", new ScheduleDescriptor(schedule), 10.0d, 10.0d, true);

    // Act
    SwapLegMonteCarlo actualSwapLegMonteCarlo =
        new SwapLegMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Assert
    assertSame(descriptor, actualSwapLegMonteCarlo.getDescriptor());
  }

  /**
   * Test SwapLegMonteCarlo {@link
   * SwapLegMonteCarlo#SwapLegMonteCarlo(InterestRateSwapLegProductDescriptor, LocalDate)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SwapLegMonteCarlo#SwapLegMonteCarlo(InterestRateSwapLegProductDescriptor, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLegMonteCarlo.<init>(InterestRateSwapLegProductDescriptor, LocalDate)"
  })
  public void testSwapLegMonteCarloNewSwapLegMonteCarlo_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Period> periods = new ArrayList<>();
    ScheduleDescriptor legSchedule =
        new ScheduleDescriptor(periods, new DayCountConvention_30E_360(true));
    InterestRateSwapLegProductDescriptor descriptor =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name",
            "3",
            legSchedule,
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            true);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new SwapLegMonteCarlo(descriptor, LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test SwapMonteCarlo {@link SwapMonteCarlo#getDescriptor()}.
   *
   * <p>Method under test: {@link SwapMonteCarlo#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InterestRateSwapProductDescriptor SwapMonteCarlo.getDescriptor()"})
  public void testSwapMonteCarloGetDescriptor() {
    // Arrange
    Schedule schedule = mock(Schedule.class);
    when(schedule.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule = new ScheduleDescriptor(schedule);
    InterestRateSwapLegProductDescriptor legReceiver =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule, 10.0d, 10.0d, true);

    Schedule schedule2 = mock(Schedule.class);
    when(schedule2.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule2.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule2 = new ScheduleDescriptor(schedule2);
    InterestRateSwapLegProductDescriptor legPayer =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule2, 10.0d, 10.0d, true);

    InterestRateSwapProductDescriptor descriptor =
        new InterestRateSwapProductDescriptor(legReceiver, legPayer);
    SwapMonteCarlo swapMonteCarlo = new SwapMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Act
    InterestRateSwapProductDescriptor actualDescriptor = swapMonteCarlo.getDescriptor();

    // Assert
    verify(schedule).getDaycountconvention();
    verify(schedule2).getDaycountconvention();
    verify(schedule).getPeriods();
    verify(schedule2).getPeriods();
    InterestRateProductDescriptor legPayer2 = actualDescriptor.getLegPayer();
    assertTrue(legPayer2 instanceof InterestRateSwapLegProductDescriptor);
    assertSame(legReceiver, actualDescriptor.getLegReceiver());
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legPayer2).getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legPayer2).getSpreads(), 0.0);
  }

  /**
   * Test SwapMonteCarlo {@link SwapMonteCarlo#getDescriptor()}.
   *
   * <p>Method under test: {@link SwapMonteCarlo#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InterestRateSwapProductDescriptor SwapMonteCarlo.getDescriptor()"})
  public void testSwapMonteCarloGetDescriptor2() {
    // Arrange
    Schedule schedule = mock(Schedule.class);
    when(schedule.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule = new ScheduleDescriptor(schedule);
    InterestRateSwapLegProductDescriptor legReceiver =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule, 10.0d, 10.0d, true);

    Schedule schedule2 = mock(Schedule.class);
    when(schedule2.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule2.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule2 = new ScheduleDescriptor(schedule2);
    InterestRateSwapLegProductDescriptor legPayer =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule2, 10.0d, 10.0d, true);

    InterestRateSwapProductDescriptor legReceiver2 =
        new InterestRateSwapProductDescriptor(legReceiver, legPayer);

    Schedule schedule3 = mock(Schedule.class);
    when(schedule3.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule3.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule3 = new ScheduleDescriptor(schedule3);
    InterestRateSwapLegProductDescriptor legPayer2 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule3, 10.0d, 10.0d, true);

    InterestRateSwapProductDescriptor descriptor =
        new InterestRateSwapProductDescriptor(legReceiver2, legPayer2);
    SwapMonteCarlo swapMonteCarlo = new SwapMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Act
    InterestRateSwapProductDescriptor actualDescriptor = swapMonteCarlo.getDescriptor();

    // Assert
    verify(schedule).getDaycountconvention();
    verify(schedule2).getDaycountconvention();
    verify(schedule3).getDaycountconvention();
    verify(schedule).getPeriods();
    verify(schedule2).getPeriods();
    verify(schedule3).getPeriods();
    InterestRateProductDescriptor legPayer3 = actualDescriptor.getLegPayer();
    assertTrue(legPayer3 instanceof InterestRateSwapLegProductDescriptor);
    InterestRateProductDescriptor legReceiver3 = actualDescriptor.getLegReceiver();
    InterestRateProductDescriptor legReceiver4 =
        ((InterestRateSwapProductDescriptor) legReceiver3).getLegReceiver();
    assertTrue(legReceiver4 instanceof InterestRateSwapLegProductDescriptor);
    assertTrue(legReceiver3 instanceof InterestRateSwapProductDescriptor);
    assertSame(legPayer, ((InterestRateSwapProductDescriptor) legReceiver3).getLegPayer());
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legPayer3).getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legReceiver4).getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legPayer3).getSpreads(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legReceiver4).getSpreads(), 0.0);
  }

  /**
   * Test SwapMonteCarlo {@link SwapMonteCarlo#getDescriptor()}.
   *
   * <p>Method under test: {@link SwapMonteCarlo#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InterestRateSwapProductDescriptor SwapMonteCarlo.getDescriptor()"})
  public void testSwapMonteCarloGetDescriptor3() {
    // Arrange
    Schedule schedule = mock(Schedule.class);
    when(schedule.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule = new ScheduleDescriptor(schedule);
    InterestRateSwapLegProductDescriptor legReceiver =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule, 10.0d, 10.0d, true);

    Schedule schedule2 = mock(Schedule.class);
    when(schedule2.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule2.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule2 = new ScheduleDescriptor(schedule2);
    InterestRateSwapLegProductDescriptor legReceiver2 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule2, 10.0d, 10.0d, true);

    Schedule schedule3 = mock(Schedule.class);
    when(schedule3.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule3.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule3 = new ScheduleDescriptor(schedule3);
    InterestRateSwapLegProductDescriptor legPayer =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule3, 10.0d, 10.0d, true);

    InterestRateSwapProductDescriptor legPayer2 =
        new InterestRateSwapProductDescriptor(legReceiver2, legPayer);

    InterestRateSwapProductDescriptor legReceiver3 =
        new InterestRateSwapProductDescriptor(legReceiver, legPayer2);

    Schedule schedule4 = mock(Schedule.class);
    when(schedule4.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule4.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule4 = new ScheduleDescriptor(schedule4);
    InterestRateSwapLegProductDescriptor legPayer3 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule4, 10.0d, 10.0d, true);

    InterestRateSwapProductDescriptor descriptor =
        new InterestRateSwapProductDescriptor(legReceiver3, legPayer3);
    SwapMonteCarlo swapMonteCarlo = new SwapMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Act
    InterestRateSwapProductDescriptor actualDescriptor = swapMonteCarlo.getDescriptor();

    // Assert
    verify(schedule).getDaycountconvention();
    verify(schedule2).getDaycountconvention();
    verify(schedule3).getDaycountconvention();
    verify(schedule4).getDaycountconvention();
    verify(schedule).getPeriods();
    verify(schedule2).getPeriods();
    verify(schedule3).getPeriods();
    verify(schedule4).getPeriods();
    InterestRateProductDescriptor legPayer4 = actualDescriptor.getLegPayer();
    assertTrue(legPayer4 instanceof InterestRateSwapLegProductDescriptor);
    InterestRateProductDescriptor legReceiver4 = actualDescriptor.getLegReceiver();
    InterestRateProductDescriptor legPayer5 =
        ((InterestRateSwapProductDescriptor) legReceiver4).getLegPayer();
    InterestRateProductDescriptor legPayer6 =
        ((InterestRateSwapProductDescriptor) legPayer5).getLegPayer();
    assertTrue(legPayer6 instanceof InterestRateSwapLegProductDescriptor);
    InterestRateProductDescriptor legReceiver5 =
        ((InterestRateSwapProductDescriptor) legPayer5).getLegReceiver();
    assertTrue(legReceiver5 instanceof InterestRateSwapLegProductDescriptor);
    InterestRateProductDescriptor legReceiver6 =
        ((InterestRateSwapProductDescriptor) legReceiver4).getLegReceiver();
    assertTrue(legReceiver6 instanceof InterestRateSwapLegProductDescriptor);
    assertTrue(legPayer5 instanceof InterestRateSwapProductDescriptor);
    assertTrue(legReceiver4 instanceof InterestRateSwapProductDescriptor);
    assertSame(legPayer, legPayer6);
    assertSame(legReceiver2, legReceiver5);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legPayer4).getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legReceiver6).getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legPayer4).getSpreads(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legReceiver6).getSpreads(), 0.0);
  }

  /**
   * Test SwapMonteCarlo {@link SwapMonteCarlo#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwapMonteCarlo#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapMonteCarlo.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testSwapMonteCarloGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    Schedule schedule = mock(Schedule.class);
    when(schedule.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule = new ScheduleDescriptor(schedule);
    InterestRateSwapLegProductDescriptor legReceiver =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule, 10.0d, 10.0d, true);

    Schedule schedule2 = mock(Schedule.class);
    when(schedule2.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule2.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule2 = new ScheduleDescriptor(schedule2);
    InterestRateSwapLegProductDescriptor legPayer =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule2, 10.0d, 10.0d, true);

    InterestRateSwapProductDescriptor descriptor =
        new InterestRateSwapProductDescriptor(legReceiver, legPayer);
    SwapMonteCarlo swapMonteCarlo = new SwapMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(covarianceModel, Double.NEGATIVE_INFINITY, true);
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(covarianceModel2, Double.NEGATIVE_INFINITY);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(Double.NEGATIVE_INFINITY, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            new TenorFromArray(Double.NEGATIVE_INFINITY, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d},
            covarianceModel3,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = swapMonteCarlo.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(schedule).getDaycountconvention();
    verify(schedule2).getDaycountconvention();
    verify(schedule).getPeriods();
    verify(schedule2).getPeriods();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test SwapMonteCarlo {@link SwapMonteCarlo#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwapMonteCarlo#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapMonteCarlo.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testSwapMonteCarloGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    Schedule schedule = mock(Schedule.class);
    when(schedule.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule = new ScheduleDescriptor(schedule);
    InterestRateSwapLegProductDescriptor legReceiver =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name",
            "3",
            legSchedule,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            true);

    Schedule schedule2 = mock(Schedule.class);
    when(schedule2.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule2.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule2 = new ScheduleDescriptor(schedule2);
    InterestRateSwapLegProductDescriptor legPayer =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name",
            "3",
            legSchedule2,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            true);

    InterestRateSwapProductDescriptor legReceiver2 =
        new InterestRateSwapProductDescriptor(legReceiver, legPayer);

    Schedule schedule3 = mock(Schedule.class);
    when(schedule3.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule3.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule3 = new ScheduleDescriptor(schedule3);
    InterestRateSwapLegProductDescriptor legPayer2 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule3, 10.0d, 10.0d, true);

    InterestRateSwapProductDescriptor descriptor =
        new InterestRateSwapProductDescriptor(legReceiver2, legPayer2);
    SwapMonteCarlo swapMonteCarlo = new SwapMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(covarianceModel, Double.NEGATIVE_INFINITY, true);
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(covarianceModel2, Double.NEGATIVE_INFINITY);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(Double.NEGATIVE_INFINITY, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            new TenorFromArray(Double.NEGATIVE_INFINITY, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d},
            covarianceModel3,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = swapMonteCarlo.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(schedule).getDaycountconvention();
    verify(schedule2).getDaycountconvention();
    verify(schedule3).getDaycountconvention();
    verify(schedule).getPeriods();
    verify(schedule2).getPeriods();
    verify(schedule3).getPeriods();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test SwapMonteCarlo {@link SwapMonteCarlo#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwapMonteCarlo#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapMonteCarlo.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testSwapMonteCarloGetValueWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    Schedule schedule = mock(Schedule.class);
    when(schedule.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule = new ScheduleDescriptor(schedule);
    InterestRateSwapLegProductDescriptor legReceiver =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name",
            "3",
            legSchedule,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            true);

    Schedule schedule2 = mock(Schedule.class);
    when(schedule2.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule2.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule2 = new ScheduleDescriptor(schedule2);
    InterestRateSwapLegProductDescriptor legReceiver2 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name",
            "3",
            legSchedule2,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            true);

    Schedule schedule3 = mock(Schedule.class);
    when(schedule3.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule3.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule3 = new ScheduleDescriptor(schedule3);
    InterestRateSwapLegProductDescriptor legPayer =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name",
            "3",
            legSchedule3,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            true);

    InterestRateSwapProductDescriptor legPayer2 =
        new InterestRateSwapProductDescriptor(legReceiver2, legPayer);

    InterestRateSwapProductDescriptor legReceiver3 =
        new InterestRateSwapProductDescriptor(legReceiver, legPayer2);

    Schedule schedule4 = mock(Schedule.class);
    when(schedule4.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule4.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule4 = new ScheduleDescriptor(schedule4);
    InterestRateSwapLegProductDescriptor legPayer3 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule4, 10.0d, 10.0d, true);

    InterestRateSwapProductDescriptor descriptor =
        new InterestRateSwapProductDescriptor(legReceiver3, legPayer3);
    SwapMonteCarlo swapMonteCarlo = new SwapMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(covarianceModel, Double.NEGATIVE_INFINITY, true);
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(covarianceModel2, Double.NEGATIVE_INFINITY);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(Double.NEGATIVE_INFINITY, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            new TenorFromArray(Double.NEGATIVE_INFINITY, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d},
            covarianceModel3,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = swapMonteCarlo.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(schedule).getDaycountconvention();
    verify(schedule2).getDaycountconvention();
    verify(schedule3).getDaycountconvention();
    verify(schedule4).getDaycountconvention();
    verify(schedule).getPeriods();
    verify(schedule2).getPeriods();
    verify(schedule3).getPeriods();
    verify(schedule4).getPeriods();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test SwaptionPhysicalMonteCarlo {@link
   * SwaptionPhysicalMonteCarlo#SwaptionPhysicalMonteCarlo(InterestRateSwaptionProductDescriptor,
   * LocalDate)}.
   *
   * <p>Method under test: {@link
   * SwaptionPhysicalMonteCarlo#SwaptionPhysicalMonteCarlo(InterestRateSwaptionProductDescriptor,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionPhysicalMonteCarlo.<init>(InterestRateSwaptionProductDescriptor, LocalDate)"
  })
  public void testSwaptionPhysicalMonteCarloNewSwaptionPhysicalMonteCarlo() {
    // Arrange
    Schedule schedule = mock(Schedule.class);
    when(schedule.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule = new ScheduleDescriptor(schedule);
    InterestRateSwapLegProductDescriptor legReceiver =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule, 10.0d, 10.0d, true);

    Schedule schedule2 = mock(Schedule.class);
    when(schedule2.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule2.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule2 = new ScheduleDescriptor(schedule2);
    InterestRateSwapLegProductDescriptor legReceiver2 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule2, 365.0d, 365.0d, true);

    Schedule schedule3 = mock(Schedule.class);
    when(schedule3.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule3.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule3 = new ScheduleDescriptor(schedule3);
    InterestRateSwapLegProductDescriptor legPayer =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule3, 365.0d, 365.0d, true);

    InterestRateSwapProductDescriptor legPayer2 =
        new InterestRateSwapProductDescriptor(legReceiver2, legPayer);

    InterestRateSwapProductDescriptor swap =
        new InterestRateSwapProductDescriptor(legReceiver, legPayer2);
    InterestRateSwaptionProductDescriptor descriptor =
        new InterestRateSwaptionProductDescriptor(swap, LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    SwaptionPhysicalMonteCarlo actualSwaptionPhysicalMonteCarlo =
        new SwaptionPhysicalMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Assert
    verify(schedule).getDaycountconvention();
    verify(schedule2).getDaycountconvention();
    verify(schedule3).getDaycountconvention();
    verify(schedule).getPeriods();
    verify(schedule2).getPeriods();
    verify(schedule3).getPeriods();
    InterestRateSwapProductDescriptor underlyingSwap =
        actualSwaptionPhysicalMonteCarlo.getDescriptor().getUnderlyingSwap();
    InterestRateProductDescriptor legPayer3 = underlyingSwap.getLegPayer();
    InterestRateProductDescriptor legPayer4 =
        ((InterestRateSwapProductDescriptor) legPayer3).getLegPayer();
    assertTrue(legPayer4 instanceof InterestRateSwapLegProductDescriptor);
    InterestRateProductDescriptor legReceiver3 = underlyingSwap.getLegReceiver();
    assertTrue(legReceiver3 instanceof InterestRateSwapLegProductDescriptor);
    InterestRateProductDescriptor legReceiver4 =
        ((InterestRateSwapProductDescriptor) legPayer3).getLegReceiver();
    assertTrue(legReceiver4 instanceof InterestRateSwapLegProductDescriptor);
    assertTrue(legPayer3 instanceof InterestRateSwapProductDescriptor);
    ScheduleDescriptor legScheduleDescriptor =
        ((InterestRateSwapLegProductDescriptor) legReceiver3).getLegScheduleDescriptor();
    assertEquals(0, legScheduleDescriptor.getNumberOfPeriods());
    assertTrue(legScheduleDescriptor.getPeriods().isEmpty());
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legPayer4).getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legReceiver3).getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legReceiver4).getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legPayer4).getSpreads(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legReceiver3).getSpreads(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legReceiver4).getSpreads(), 0.0);
  }

  /**
   * Test SwaptionPhysicalMonteCarlo {@link
   * SwaptionPhysicalMonteCarlo#SwaptionPhysicalMonteCarlo(InterestRateSwaptionProductDescriptor,
   * LocalDate)}.
   *
   * <p>Method under test: {@link
   * SwaptionPhysicalMonteCarlo#SwaptionPhysicalMonteCarlo(InterestRateSwaptionProductDescriptor,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionPhysicalMonteCarlo.<init>(InterestRateSwaptionProductDescriptor, LocalDate)"
  })
  public void testSwaptionPhysicalMonteCarloNewSwaptionPhysicalMonteCarlo2() {
    // Arrange
    ArrayList<Period> periodList = new ArrayList<>();
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periodList.add(period);

    Schedule schedule = mock(Schedule.class);
    when(schedule.getPeriods()).thenReturn(periodList);
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule = new ScheduleDescriptor(schedule);
    InterestRateSwapLegProductDescriptor legReceiver =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule, 10.0d, 10.0d, true);

    Schedule schedule2 = mock(Schedule.class);
    when(schedule2.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule2.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule2 = new ScheduleDescriptor(schedule2);
    InterestRateSwapLegProductDescriptor legReceiver2 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule2, 365.0d, 365.0d, true);

    Schedule schedule3 = mock(Schedule.class);
    when(schedule3.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule3.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule3 = new ScheduleDescriptor(schedule3);
    InterestRateSwapLegProductDescriptor legPayer =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule3, 365.0d, 365.0d, true);

    InterestRateSwapProductDescriptor legPayer2 =
        new InterestRateSwapProductDescriptor(legReceiver2, legPayer);

    InterestRateSwapProductDescriptor swap =
        new InterestRateSwapProductDescriptor(legReceiver, legPayer2);
    InterestRateSwaptionProductDescriptor descriptor =
        new InterestRateSwaptionProductDescriptor(swap, LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    SwaptionPhysicalMonteCarlo actualSwaptionPhysicalMonteCarlo =
        new SwaptionPhysicalMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Assert
    verify(schedule).getDaycountconvention();
    verify(schedule2).getDaycountconvention();
    verify(schedule3).getDaycountconvention();
    verify(schedule).getPeriods();
    verify(schedule2).getPeriods();
    verify(schedule3).getPeriods();
    InterestRateSwapProductDescriptor underlyingSwap =
        actualSwaptionPhysicalMonteCarlo.getDescriptor().getUnderlyingSwap();
    InterestRateProductDescriptor legPayer3 = underlyingSwap.getLegPayer();
    InterestRateProductDescriptor legPayer4 =
        ((InterestRateSwapProductDescriptor) legPayer3).getLegPayer();
    assertTrue(legPayer4 instanceof InterestRateSwapLegProductDescriptor);
    InterestRateProductDescriptor legReceiver3 = underlyingSwap.getLegReceiver();
    assertTrue(legReceiver3 instanceof InterestRateSwapLegProductDescriptor);
    InterestRateProductDescriptor legReceiver4 =
        ((InterestRateSwapProductDescriptor) legPayer3).getLegReceiver();
    assertTrue(legReceiver4 instanceof InterestRateSwapLegProductDescriptor);
    assertTrue(legPayer3 instanceof InterestRateSwapProductDescriptor);
    List<Period> periods =
        ((InterestRateSwapLegProductDescriptor) legReceiver3)
            .getLegScheduleDescriptor()
            .getPeriods();
    assertEquals(1, periods.size());
    assertSame(period, periods.get(0));
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legPayer4).getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legReceiver4).getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legPayer4).getSpreads(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legReceiver4).getSpreads(), 0.0);
    assertArrayEquals(
        new double[] {10.0d},
        ((InterestRateSwapLegProductDescriptor) legReceiver3).getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d},
        ((InterestRateSwapLegProductDescriptor) legReceiver3).getSpreads(),
        0.0);
  }

  /**
   * Test SwaptionPhysicalMonteCarlo {@link
   * SwaptionPhysicalMonteCarlo#SwaptionPhysicalMonteCarlo(InterestRateSwaptionProductDescriptor,
   * LocalDate)}.
   *
   * <p>Method under test: {@link
   * SwaptionPhysicalMonteCarlo#SwaptionPhysicalMonteCarlo(InterestRateSwaptionProductDescriptor,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionPhysicalMonteCarlo.<init>(InterestRateSwaptionProductDescriptor, LocalDate)"
  })
  public void testSwaptionPhysicalMonteCarloNewSwaptionPhysicalMonteCarlo3() {
    // Arrange
    ArrayList<Period> periodList = new ArrayList<>();
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.now(),
            LocalDate.of(1970, 1, 1));
    periodList.add(period);

    Schedule schedule = mock(Schedule.class);
    when(schedule.getPeriods()).thenReturn(periodList);
    when(schedule.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule = new ScheduleDescriptor(schedule);
    InterestRateSwapLegProductDescriptor legReceiver =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule, 10.0d, 10.0d, true);

    Schedule schedule2 = mock(Schedule.class);
    when(schedule2.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule2.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule2 = new ScheduleDescriptor(schedule2);
    InterestRateSwapLegProductDescriptor legReceiver2 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule2, 365.0d, 365.0d, true);

    Schedule schedule3 = mock(Schedule.class);
    when(schedule3.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule3.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule3 = new ScheduleDescriptor(schedule3);
    InterestRateSwapLegProductDescriptor legPayer =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule3, 365.0d, 365.0d, true);

    InterestRateSwapProductDescriptor legPayer2 =
        new InterestRateSwapProductDescriptor(legReceiver2, legPayer);

    InterestRateSwapProductDescriptor swap =
        new InterestRateSwapProductDescriptor(legReceiver, legPayer2);
    InterestRateSwaptionProductDescriptor descriptor =
        new InterestRateSwaptionProductDescriptor(swap, LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    SwaptionPhysicalMonteCarlo actualSwaptionPhysicalMonteCarlo =
        new SwaptionPhysicalMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Assert
    verify(schedule).getDaycountconvention();
    verify(schedule2).getDaycountconvention();
    verify(schedule3).getDaycountconvention();
    verify(schedule).getPeriods();
    verify(schedule2).getPeriods();
    verify(schedule3).getPeriods();
    InterestRateSwapProductDescriptor underlyingSwap =
        actualSwaptionPhysicalMonteCarlo.getDescriptor().getUnderlyingSwap();
    InterestRateProductDescriptor legPayer3 = underlyingSwap.getLegPayer();
    InterestRateProductDescriptor legPayer4 =
        ((InterestRateSwapProductDescriptor) legPayer3).getLegPayer();
    assertTrue(legPayer4 instanceof InterestRateSwapLegProductDescriptor);
    InterestRateProductDescriptor legReceiver3 = underlyingSwap.getLegReceiver();
    assertTrue(legReceiver3 instanceof InterestRateSwapLegProductDescriptor);
    InterestRateProductDescriptor legReceiver4 =
        ((InterestRateSwapProductDescriptor) legPayer3).getLegReceiver();
    assertTrue(legReceiver4 instanceof InterestRateSwapLegProductDescriptor);
    assertTrue(legPayer3 instanceof InterestRateSwapProductDescriptor);
    List<Period> periods =
        ((InterestRateSwapLegProductDescriptor) legReceiver3)
            .getLegScheduleDescriptor()
            .getPeriods();
    assertEquals(1, periods.size());
    assertSame(period, periods.get(0));
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legPayer4).getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legReceiver4).getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legPayer4).getSpreads(), 0.0);
    assertArrayEquals(
        new double[] {}, ((InterestRateSwapLegProductDescriptor) legReceiver4).getSpreads(), 0.0);
    assertArrayEquals(
        new double[] {10.0d},
        ((InterestRateSwapLegProductDescriptor) legReceiver3).getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d},
        ((InterestRateSwapLegProductDescriptor) legReceiver3).getSpreads(),
        0.0);
  }
}
