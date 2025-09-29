package net.finmath.modelling.descriptor;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import net.finmath.time.Period;
import net.finmath.time.RegularSchedule;
import net.finmath.time.ScheduleFromPeriods;
import net.finmath.time.TenorFromArray;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class InterestRateSwapLegProductDescriptorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link InterestRateSwapLegProductDescriptor#InterestRateSwapLegProductDescriptor(String,
   *       String, ScheduleDescriptor, double[], double[], boolean)}
   *   <li>{@link InterestRateSwapLegProductDescriptor#getDiscountCurveName()}
   *   <li>{@link InterestRateSwapLegProductDescriptor#getForwardCurveName()}
   *   <li>{@link InterestRateSwapLegProductDescriptor#getLegScheduleDescriptor()}
   *   <li>{@link InterestRateSwapLegProductDescriptor#getSpreads()}
   *   <li>{@link InterestRateSwapLegProductDescriptor#isNotionalExchanged()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InterestRateSwapLegProductDescriptor.<init>(String, String, ScheduleDescriptor, double[], double[], boolean)",
    "String InterestRateSwapLegProductDescriptor.getDiscountCurveName()",
    "String InterestRateSwapLegProductDescriptor.getForwardCurveName()",
    "ScheduleDescriptor InterestRateSwapLegProductDescriptor.getLegScheduleDescriptor()",
    "double[] InterestRateSwapLegProductDescriptor.getSpreads()",
    "boolean InterestRateSwapLegProductDescriptor.isNotionalExchanged()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ScheduleDescriptor legSchedule =
        new ScheduleDescriptor(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));
    double[] spreads = new double[] {10.0d, 0.5d, 10.0d, 0.5d};

    // Act
    InterestRateSwapLegProductDescriptor actualInterestRateSwapLegProductDescriptor =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name",
            "3",
            legSchedule,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            spreads,
            true);
    String actualDiscountCurveName =
        actualInterestRateSwapLegProductDescriptor.getDiscountCurveName();
    String actualForwardCurveName =
        actualInterestRateSwapLegProductDescriptor.getForwardCurveName();
    ScheduleDescriptor actualLegScheduleDescriptor =
        actualInterestRateSwapLegProductDescriptor.getLegScheduleDescriptor();
    double[] actualSpreads = actualInterestRateSwapLegProductDescriptor.getSpreads();

    // Assert
    assertEquals("3", actualDiscountCurveName);
    assertEquals("Forward Curve Name", actualForwardCurveName);
    assertTrue(actualInterestRateSwapLegProductDescriptor.isNotionalExchanged());
    assertSame(legSchedule, actualLegScheduleDescriptor);
    assertSame(spreads, actualSpreads);
    assertArrayEquals(new double[] {10.0d, 0.5d, 10.0d, 0.5d}, actualSpreads, 0.0);
  }

  /**
   * Test {@link InterestRateSwapLegProductDescriptor#InterestRateSwapLegProductDescriptor(String,
   * String, ScheduleDescriptor, double, double, boolean)}.
   *
   * <ul>
   *   <li>Then return DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InterestRateSwapLegProductDescriptor#InterestRateSwapLegProductDescriptor(String, String,
   * ScheduleDescriptor, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InterestRateSwapLegProductDescriptor.<init>(String, String, ScheduleDescriptor, double, double, boolean)"
  })
  public void testNewInterestRateSwapLegProductDescriptor_thenReturnDiscountCurveNameIs3() {
    // Arrange
    ArrayList<Period> periods = new ArrayList<>();
    ScheduleDescriptor legSchedule =
        new ScheduleDescriptor(periods, new DayCountConvention_30E_360(true));

    // Act
    InterestRateSwapLegProductDescriptor actualInterestRateSwapLegProductDescriptor =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", legSchedule, 10.0d, 10.0d, true);

    // Assert
    assertEquals("3", actualInterestRateSwapLegProductDescriptor.getDiscountCurveName());
    assertEquals(
        "Forward Curve Name", actualInterestRateSwapLegProductDescriptor.getForwardCurveName());
    assertTrue(actualInterestRateSwapLegProductDescriptor.isNotionalExchanged());
    assertSame(legSchedule, actualInterestRateSwapLegProductDescriptor.getLegScheduleDescriptor());
    assertArrayEquals(
        new double[] {}, actualInterestRateSwapLegProductDescriptor.getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {}, actualInterestRateSwapLegProductDescriptor.getSpreads(), 0.0);
  }

  /**
   * Test {@link InterestRateSwapLegProductDescriptor#getNotionals()}.
   *
   * <ul>
   *   <li>Given {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link InterestRateSwapLegProductDescriptor#getNotionals()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] InterestRateSwapLegProductDescriptor.getNotionals()"})
  public void testGetNotionals_givenLocalDateWith1970AndOneAndOne_thenReturnEmptyArrayOfDouble() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    InterestRateSwapLegProductDescriptor interestRateSwapLegProductDescriptor =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", new ScheduleDescriptor(schedule), 10.0d, 10.0d, true);

    // Act and Assert
    assertArrayEquals(new double[] {}, interestRateSwapLegProductDescriptor.getNotionals(), 0.0);
  }

  /**
   * Test {@link InterestRateSwapLegProductDescriptor#version()}.
   *
   * <ul>
   *   <li>Given {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link InterestRateSwapLegProductDescriptor#version()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Integer InterestRateSwapLegProductDescriptor.version()"})
  public void testVersion_givenLocalDateWith1970AndOneAndOne_thenReturnIntValueIsOne() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    InterestRateSwapLegProductDescriptor interestRateSwapLegProductDescriptor =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", new ScheduleDescriptor(schedule), 10.0d, 10.0d, true);

    // Act and Assert
    assertEquals(1, interestRateSwapLegProductDescriptor.version().intValue());
  }

  /**
   * Test {@link InterestRateSwapLegProductDescriptor#name()}.
   *
   * <ul>
   *   <li>Given {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then return {@code Interest Rate Swap Leg}.
   * </ul>
   *
   * <p>Method under test: {@link InterestRateSwapLegProductDescriptor#name()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InterestRateSwapLegProductDescriptor.name()"})
  public void testName_givenLocalDateWith1970AndOneAndOne_thenReturnInterestRateSwapLeg() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    InterestRateSwapLegProductDescriptor interestRateSwapLegProductDescriptor =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name", "3", new ScheduleDescriptor(schedule), 10.0d, 10.0d, true);

    // Act and Assert
    assertEquals("Interest Rate Swap Leg", interestRateSwapLegProductDescriptor.name());
  }
}
