package net.finmath.modelling.modelfactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
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
import java.util.HashMap;
import java.util.Map;
import net.finmath.marketdata.model.curves.Curve;
import net.finmath.marketdata.products.AnalyticProduct;
import net.finmath.marketdata.products.Swap;
import net.finmath.marketdata.products.SwapLeg;
import net.finmath.modelling.DescribedModel;
import net.finmath.modelling.DescribedProduct;
import net.finmath.modelling.ProductDescriptor;
import net.finmath.modelling.descriptor.AnalyticModelDescriptor;
import net.finmath.modelling.descriptor.InterestRateSwapLegProductDescriptor;
import net.finmath.modelling.descriptor.InterestRateSwapProductDescriptor;
import net.finmath.modelling.descriptor.ScheduleDescriptor;
import net.finmath.modelling.modelfactory.AnalyticModelFactory.DescribedAnalyticModel;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AnalyticModelFactoryDiffblueTest {
  /**
   * Test DescribedAnalyticModel {@link DescribedAnalyticModel#getDescriptor()}.
   *
   * <p>Method under test: {@link DescribedAnalyticModel#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModelDescriptor DescribedAnalyticModel.getDescriptor()"})
  public void testDescribedAnalyticModelGetDescriptor() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    HashMap<String, Curve> curvesMap = new HashMap<>();

    DescribedAnalyticModel describedAnalyticModel =
        new DescribedAnalyticModel(referenceDate, curvesMap, new HashMap<>());

    // Act
    AnalyticModelDescriptor actualDescriptor = describedAnalyticModel.getDescriptor();

    // Assert
    LocalDate referenceDate2 = actualDescriptor.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertTrue(actualDescriptor.getCurvesMap().isEmpty());
    assertTrue(actualDescriptor.getVolatilitySurfaceMap().isEmpty());
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test DescribedAnalyticModel {@link
   * DescribedAnalyticModel#getProductFromDescriptor(ProductDescriptor)}.
   *
   * <p>Method under test: {@link
   * DescribedAnalyticModel#getProductFromDescriptor(ProductDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DescribedProduct DescribedAnalyticModel.getProductFromDescriptor(ProductDescriptor)"
  })
  public void testDescribedAnalyticModelGetProductFromDescriptor() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    HashMap<String, Curve> curvesMap = new HashMap<>();

    DescribedAnalyticModel describedAnalyticModel =
        new DescribedAnalyticModel(referenceDate, curvesMap, new HashMap<>());

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

    InterestRateSwapProductDescriptor productDescriptor =
        new InterestRateSwapProductDescriptor(legReceiver2, legPayer4);

    // Act
    DescribedProduct<? extends ProductDescriptor> actualProductFromDescriptor =
        describedAnalyticModel.getProductFromDescriptor(productDescriptor);

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
    ProductDescriptor descriptor = actualProductFromDescriptor.getDescriptor();
    assertTrue(
        ((InterestRateSwapProductDescriptor) descriptor).getLegPayer()
            instanceof InterestRateSwapProductDescriptor);
    assertTrue(
        ((InterestRateSwapProductDescriptor) descriptor).getLegReceiver()
            instanceof InterestRateSwapProductDescriptor);
  }

  /**
   * Test DescribedAnalyticModel {@link DescribedAnalyticModel#DescribedAnalyticModel(LocalDate,
   * Map, Map)}.
   *
   * <p>Method under test: {@link DescribedAnalyticModel#DescribedAnalyticModel(LocalDate, Map,
   * Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DescribedAnalyticModel.<init>(LocalDate, Map, Map)"})
  public void testDescribedAnalyticModelNewDescribedAnalyticModel() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    HashMap<String, Curve> curvesMap = new HashMap<>();

    // Act
    DescribedAnalyticModel actualDescribedAnalyticModel =
        new DescribedAnalyticModel(referenceDate, curvesMap, new HashMap<>());

    // Assert
    assertTrue(actualDescribedAnalyticModel.getCurves().isEmpty());
    assertTrue(actualDescribedAnalyticModel.getVolatilitySurfaces().isEmpty());
    AnalyticModelDescriptor descriptor = actualDescribedAnalyticModel.getDescriptor();
    assertTrue(descriptor.getCurvesMap().isEmpty());
    assertTrue(descriptor.getVolatilitySurfaceMap().isEmpty());
    assertSame(referenceDate, actualDescribedAnalyticModel.getReferenceDate());
    assertSame(referenceDate, descriptor.getReferenceDate());
  }

  /**
   * Test {@link AnalyticModelFactory#getModelFromDescriptor(AnalyticModelDescriptor)} with {@code
   * AnalyticModelDescriptor}.
   *
   * <p>Method under test: {@link
   * AnalyticModelFactory#getModelFromDescriptor(AnalyticModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DescribedModel AnalyticModelFactory.getModelFromDescriptor(AnalyticModelDescriptor)"
  })
  public void testGetModelFromDescriptorWithAnalyticModelDescriptor() {
    // Arrange
    AnalyticModelFactory analyticModelFactory = new AnalyticModelFactory();
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Curve> curves = new ArrayList<>();

    AnalyticModelDescriptor descriptor =
        new AnalyticModelDescriptor(referenceDate, curves, new ArrayList<>());

    // Act
    DescribedModel<AnalyticModelDescriptor> actualModelFromDescriptor =
        analyticModelFactory.getModelFromDescriptor(descriptor);

    // Assert
    assertTrue(actualModelFromDescriptor instanceof DescribedAnalyticModel);
    LocalDate referenceDate2 =
        ((DescribedAnalyticModel) actualModelFromDescriptor).getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertTrue(((DescribedAnalyticModel) actualModelFromDescriptor).getCurves().isEmpty());
    assertTrue(
        ((DescribedAnalyticModel) actualModelFromDescriptor).getVolatilitySurfaces().isEmpty());
    AnalyticModelDescriptor descriptor2 = actualModelFromDescriptor.getDescriptor();
    assertTrue(descriptor2.getCurvesMap().isEmpty());
    assertTrue(descriptor2.getVolatilitySurfaceMap().isEmpty());
    assertSame(referenceDate, referenceDate2);
    assertSame(referenceDate, descriptor2.getReferenceDate());
  }
}
