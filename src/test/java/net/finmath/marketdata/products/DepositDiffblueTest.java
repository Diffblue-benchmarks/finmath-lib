package net.finmath.marketdata.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DepositDiffblueTest {
  /**
   * Test {@link Deposit#Deposit(Schedule, double, String)}.
   *
   * <ul>
   *   <li>Then return DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link Deposit#Deposit(Schedule, double, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Deposit.<init>(Schedule, double, String)"})
  public void testNewDeposit_thenReturnDiscountCurveNameIs3() {
    // Arrange
    RegularSchedule schedule =
        new RegularSchedule(
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));

    // Act
    Deposit actualDeposit = new Deposit(schedule, 10.0d, "3");

    // Assert
    assertEquals("3", actualDeposit.getDiscountCurveName());
    assertEquals(10.0d, actualDeposit.getFixingTime(), 0.0);
    assertEquals(10.0d, actualDeposit.getRate(), 0.0);
    assertSame(schedule, actualDeposit.getSchedule());
  }

  /**
   * Test {@link Deposit#Deposit(Schedule, double, String)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Deposit#Deposit(Schedule, double, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Deposit.<init>(Schedule, double, String)"})
  public void testNewDeposit_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new Deposit(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, "3"));
  }

  /**
   * Test {@link Deposit#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Deposit#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Deposit.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenThrowIllegalArgumentException() {
    // Arrange
    Deposit deposit =
        new Deposit(new RegularSchedule(new TenorFromArray(10.0d, 1, 0.5d)), 10.0d, "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> deposit.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link Deposit#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Deposit#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Deposit.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenThrowIllegalArgumentException2() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new Deposit(new RegularSchedule(new TenorFromArray(10.0d, 1, 0.5d)), 10.0d, "3")
                .getValue(10.0d, (AnalyticModel) null));
  }

  /**
   * Test {@link Deposit#getRate(AnalyticModel)} with {@code AnalyticModel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Deposit#getRate(AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Deposit.getRate(AnalyticModel)"})
  public void testGetRateWithAnalyticModel_thenThrowIllegalArgumentException() {
    // Arrange
    Deposit deposit =
        new Deposit(new RegularSchedule(new TenorFromArray(10.0d, 1, 0.5d)), 10.0d, "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> deposit.getRate(new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link Deposit#getRate(AnalyticModel)} with {@code AnalyticModel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Deposit#getRate(AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Deposit.getRate(AnalyticModel)"})
  public void testGetRateWithAnalyticModel_thenThrowIllegalArgumentException2() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new Deposit(new RegularSchedule(new TenorFromArray(10.0d, 1, 0.5d)), 10.0d, "3")
                .getRate(null));
  }

  /**
   * Test {@link Deposit#getPeriodEndTime()}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link Deposit#getPeriodEndTime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Deposit.getPeriodEndTime()"})
  public void testGetPeriodEndTime_thenReturn105() {
    // Arrange, Act and Assert
    assertEquals(
        10.5d,
        new Deposit(new RegularSchedule(new TenorFromArray(10.0d, 1, 0.5d)), 10.0d, "3")
            .getPeriodEndTime(),
        0.0);
  }

  /**
   * Test {@link Deposit#getFixingTime()}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link Deposit#getFixingTime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Deposit.getFixingTime()"})
  public void testGetFixingTime_thenReturnTen() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d,
        new Deposit(new RegularSchedule(new TenorFromArray(10.0d, 1, 0.5d)), 10.0d, "3")
            .getFixingTime(),
        0.0);
  }
}
