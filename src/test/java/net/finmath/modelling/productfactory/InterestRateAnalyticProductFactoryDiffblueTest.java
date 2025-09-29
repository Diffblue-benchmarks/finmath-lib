package net.finmath.modelling.productfactory;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import net.finmath.marketdata.products.AnalyticProduct;
import net.finmath.marketdata.products.Swap;
import net.finmath.marketdata.products.SwapLeg;
import net.finmath.modelling.DescribedProduct;
import net.finmath.modelling.InterestRateProductDescriptor;
import net.finmath.modelling.ProductDescriptor;
import net.finmath.modelling.descriptor.InterestRateSwapLegProductDescriptor;
import net.finmath.modelling.descriptor.InterestRateSwapProductDescriptor;
import net.finmath.modelling.descriptor.ScheduleDescriptor;
import net.finmath.modelling.descriptor.SingleAssetDigitalOptionProductDescriptor;
import net.finmath.time.Period;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class InterestRateAnalyticProductFactoryDiffblueTest {
  /**
   * Test {@link InterestRateAnalyticProductFactory#getProductFromDescriptor(ProductDescriptor)}.
   *
   * <p>Method under test: {@link
   * InterestRateAnalyticProductFactory#getProductFromDescriptor(ProductDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DescribedProduct InterestRateAnalyticProductFactory.getProductFromDescriptor(ProductDescriptor)"
  })
  public void testGetProductFromDescriptor() {
    // Arrange
    InterestRateAnalyticProductFactory interestRateAnalyticProductFactory =
        new InterestRateAnalyticProductFactory(LocalDate.of(1970, 1, 1));

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
    InterestRateSwapLegProductDescriptor legPayer =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule2, 10.0d, 10.0d, true);

    InterestRateSwapProductDescriptor legReceiver2 =
        new InterestRateSwapProductDescriptor(legReceiver, legPayer);

    Schedule schedule3 = mock(Schedule.class);
    when(schedule3.getPeriods()).thenReturn(new ArrayList<>());
    when(schedule3.getDaycountconvention()).thenReturn(new DayCountConvention_30E_360(true));
    ScheduleDescriptor legSchedule3 = new ScheduleDescriptor(schedule3);
    InterestRateSwapLegProductDescriptor legReceiver3 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule3, 10.0d, 10.0d, true);

    ScheduleDescriptor legSchedule4 = mock(ScheduleDescriptor.class);
    when(legSchedule4.getNumberOfPeriods()).thenReturn(10);
    when(legSchedule4.getSchedule(Mockito.<LocalDate>any()))
        .thenReturn(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));
    InterestRateSwapLegProductDescriptor legReceiver4 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule4, 10.0d, 10.0d, true);

    ScheduleDescriptor legSchedule5 = mock(ScheduleDescriptor.class);
    when(legSchedule5.getNumberOfPeriods()).thenReturn(10);
    when(legSchedule5.getSchedule(Mockito.<LocalDate>any()))
        .thenReturn(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));
    InterestRateSwapLegProductDescriptor legPayer2 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule5, 10.0d, 10.0d, true);

    InterestRateSwapProductDescriptor legPayer3 =
        new InterestRateSwapProductDescriptor(legReceiver4, legPayer2);

    InterestRateSwapProductDescriptor legPayer4 =
        new InterestRateSwapProductDescriptor(legReceiver3, legPayer3);

    InterestRateSwapProductDescriptor descriptor =
        new InterestRateSwapProductDescriptor(legReceiver2, legPayer4);

    // Act
    DescribedProduct<? extends InterestRateProductDescriptor> actualProductFromDescriptor =
        interestRateAnalyticProductFactory.getProductFromDescriptor(descriptor);

    // Assert
    verify(legSchedule4, atLeast(1)).getNumberOfPeriods();
    verify(legSchedule5, atLeast(1)).getNumberOfPeriods();
    verify(legSchedule4).getSchedule(isA(LocalDate.class));
    verify(legSchedule5).getSchedule(isA(LocalDate.class));
    verify(schedule).getDaycountconvention();
    verify(schedule2).getDaycountconvention();
    verify(schedule3).getDaycountconvention();
    verify(schedule).getPeriods();
    verify(schedule2).getPeriods();
    verify(schedule3).getPeriods();
    AnalyticProduct legPayer5 = ((Swap) actualProductFromDescriptor).getLegPayer();
    assertTrue(((Swap) legPayer5).getLegPayer() instanceof Swap);
    assertTrue(legPayer5 instanceof Swap);
    AnalyticProduct legReceiver5 = ((Swap) actualProductFromDescriptor).getLegReceiver();
    assertTrue(legReceiver5 instanceof Swap);
    assertTrue(actualProductFromDescriptor instanceof Swap);
    assertTrue(((Swap) legReceiver5).getLegPayer() instanceof SwapLeg);
    assertTrue(((Swap) legPayer5).getLegReceiver() instanceof SwapLeg);
    assertTrue(((Swap) legReceiver5).getLegReceiver() instanceof SwapLeg);
    InterestRateProductDescriptor descriptor2 = actualProductFromDescriptor.getDescriptor();
    assertTrue(
        ((InterestRateSwapProductDescriptor) descriptor2).getLegPayer()
            instanceof InterestRateSwapProductDescriptor);
    assertTrue(
        ((InterestRateSwapProductDescriptor) descriptor2).getLegReceiver()
            instanceof InterestRateSwapProductDescriptor);
  }

  /**
   * Test {@link InterestRateAnalyticProductFactory#getProductFromDescriptor(ProductDescriptor)}.
   *
   * <ul>
   *   <li>Then LegPayer LegPayer return {@link Swap}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InterestRateAnalyticProductFactory#getProductFromDescriptor(ProductDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DescribedProduct InterestRateAnalyticProductFactory.getProductFromDescriptor(ProductDescriptor)"
  })
  public void testGetProductFromDescriptor_thenLegPayerLegPayerReturnSwap() {
    // Arrange
    InterestRateAnalyticProductFactory interestRateAnalyticProductFactory =
        new InterestRateAnalyticProductFactory(LocalDate.of(1970, 1, 1));

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
    InterestRateSwapLegProductDescriptor legReceiver3 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule3, 10.0d, 10.0d, true);

    ScheduleDescriptor legSchedule4 = mock(ScheduleDescriptor.class);
    when(legSchedule4.getNumberOfPeriods()).thenReturn(10);
    when(legSchedule4.getSchedule(Mockito.<LocalDate>any()))
        .thenReturn(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));
    InterestRateSwapLegProductDescriptor legReceiver4 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule4, 10.0d, 10.0d, true);

    ScheduleDescriptor legSchedule5 = mock(ScheduleDescriptor.class);
    when(legSchedule5.getNumberOfPeriods()).thenReturn(10);
    when(legSchedule5.getSchedule(Mockito.<LocalDate>any()))
        .thenReturn(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));
    InterestRateSwapLegProductDescriptor legPayer2 =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule5, 10.0d, 10.0d, true);

    InterestRateSwapProductDescriptor legPayer3 =
        new InterestRateSwapProductDescriptor(legReceiver4, legPayer2);

    InterestRateSwapProductDescriptor legPayer4 =
        new InterestRateSwapProductDescriptor(legReceiver3, legPayer3);

    InterestRateSwapProductDescriptor descriptor =
        new InterestRateSwapProductDescriptor(legReceiver2, legPayer4);

    // Act
    DescribedProduct<? extends InterestRateProductDescriptor> actualProductFromDescriptor =
        interestRateAnalyticProductFactory.getProductFromDescriptor(descriptor);

    // Assert
    verify(legSchedule4, atLeast(1)).getNumberOfPeriods();
    verify(legSchedule5, atLeast(1)).getNumberOfPeriods();
    verify(legSchedule4).getSchedule(isA(LocalDate.class));
    verify(legSchedule5).getSchedule(isA(LocalDate.class));
    verify(schedule).getDaycountconvention();
    verify(schedule2).getDaycountconvention();
    verify(schedule3).getDaycountconvention();
    verify(schedule).getPeriods();
    verify(schedule2).getPeriods();
    verify(schedule3).getPeriods();
    AnalyticProduct legPayer5 = ((Swap) actualProductFromDescriptor).getLegPayer();
    assertTrue(((Swap) legPayer5).getLegPayer() instanceof Swap);
    assertTrue(legPayer5 instanceof Swap);
    AnalyticProduct legReceiver5 = ((Swap) actualProductFromDescriptor).getLegReceiver();
    assertTrue(legReceiver5 instanceof Swap);
    assertTrue(actualProductFromDescriptor instanceof Swap);
    assertTrue(((Swap) legReceiver5).getLegPayer() instanceof SwapLeg);
    assertTrue(((Swap) legPayer5).getLegReceiver() instanceof SwapLeg);
    assertTrue(((Swap) legReceiver5).getLegReceiver() instanceof SwapLeg);
    InterestRateProductDescriptor descriptor2 = actualProductFromDescriptor.getDescriptor();
    assertTrue(
        ((InterestRateSwapProductDescriptor) descriptor2).getLegPayer()
            instanceof InterestRateSwapProductDescriptor);
    assertTrue(
        ((InterestRateSwapProductDescriptor) descriptor2).getLegReceiver()
            instanceof InterestRateSwapProductDescriptor);
  }

  /**
   * Test {@link InterestRateAnalyticProductFactory#getProductFromDescriptor(ProductDescriptor)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InterestRateAnalyticProductFactory#getProductFromDescriptor(ProductDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DescribedProduct InterestRateAnalyticProductFactory.getProductFromDescriptor(ProductDescriptor)"
  })
  public void testGetProductFromDescriptor_thenThrowIllegalArgumentException() {
    // Arrange
    InterestRateAnalyticProductFactory interestRateAnalyticProductFactory =
        new InterestRateAnalyticProductFactory(LocalDate.of(1970, 1, 1));
    SingleAssetDigitalOptionProductDescriptor descriptor =
        new SingleAssetDigitalOptionProductDescriptor(
            "Name Of Underlying", LocalDate.of(1970, 1, 1), 10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> interestRateAnalyticProductFactory.getProductFromDescriptor(descriptor));
  }
}
