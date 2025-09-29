package net.finmath.modelling.descriptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import net.finmath.time.Schedule;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class InterestRateSwaptionProductDescriptorDiffblueTest {
  /**
   * Test {@link InterestRateSwaptionProductDescriptor#version()}.
   *
   * <p>Method under test: {@link InterestRateSwaptionProductDescriptor#version()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Integer InterestRateSwaptionProductDescriptor.version()"})
  public void testVersion() {
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

    InterestRateSwapProductDescriptor swap =
        new InterestRateSwapProductDescriptor(legReceiver, legPayer);
    InterestRateSwaptionProductDescriptor interestRateSwaptionProductDescriptor =
        new InterestRateSwaptionProductDescriptor(swap, LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    Integer actualVersionResult = interestRateSwaptionProductDescriptor.version();

    // Assert
    verify(schedule).getDaycountconvention();
    verify(schedule2).getDaycountconvention();
    verify(schedule).getPeriods();
    verify(schedule2).getPeriods();
    assertEquals(1, actualVersionResult.intValue());
  }

  /**
   * Test {@link InterestRateSwaptionProductDescriptor#name()}.
   *
   * <p>Method under test: {@link InterestRateSwaptionProductDescriptor#name()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InterestRateSwaptionProductDescriptor.name()"})
  public void testName() {
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

    InterestRateSwapProductDescriptor swap =
        new InterestRateSwapProductDescriptor(legReceiver, legPayer);
    InterestRateSwaptionProductDescriptor interestRateSwaptionProductDescriptor =
        new InterestRateSwaptionProductDescriptor(swap, LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    String actualNameResult = interestRateSwaptionProductDescriptor.name();

    // Assert
    verify(schedule).getDaycountconvention();
    verify(schedule2).getDaycountconvention();
    verify(schedule).getPeriods();
    verify(schedule2).getPeriods();
    assertEquals("Interest Rate Swap", actualNameResult);
  }
}
