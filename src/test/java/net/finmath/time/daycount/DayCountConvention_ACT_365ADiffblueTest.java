package net.finmath.time.daycount;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DayCountConvention_ACT_365ADiffblueTest {
  /**
   * Test {@link DayCountConvention_ACT_365A#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_ACT_365A#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_ACT_365A.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_whenLocalDateWith1970AndOneAndOne_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        new DayCountConvention_ACT_365A()
            .getDaycountFraction(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_ACT_365A#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When ofYearDay minus one and one.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_ACT_365A#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_ACT_365A.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_whenOfYearDayMinusOneAndOne_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(
        -1.0d,
        new DayCountConvention_ACT_365A()
            .getDaycountFraction(LocalDate.ofYearDay(0, 1), LocalDate.ofYearDay(-1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_ACT_365A#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When ofYearDay zero and one.
   *   <li>Then return {@code 1965.9234972677596}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_ACT_365A#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_ACT_365A.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_whenOfYearDayZeroAndOne_thenReturn19659234972677596() {
    // Arrange, Act and Assert
    assertEquals(
        1965.9234972677596d,
        new DayCountConvention_ACT_365A()
            .getDaycountFraction(LocalDate.ofYearDay(0, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_ACT_365A#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When ofYearDay zero and one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_ACT_365A#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_ACT_365A.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_whenOfYearDayZeroAndOne_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        new DayCountConvention_ACT_365A()
            .getDaycountFraction(LocalDate.ofYearDay(0, 1), LocalDate.ofYearDay(0, 1)),
        0.0);
  }
}
