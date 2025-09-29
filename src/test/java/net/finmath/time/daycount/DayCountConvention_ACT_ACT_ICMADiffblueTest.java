package net.finmath.time.daycount;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import net.finmath.time.Period;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DayCountConvention_ACT_ACT_ICMADiffblueTest {
  /**
   * Test {@link DayCountConvention_ACT_ACT_ICMA#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_ACT_ACT_ICMA#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_ACT_ACT_ICMA.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_thenReturnNaN() {
    // Arrange
    ArrayList<Period> periods = new ArrayList<>();
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period);
    Period period2 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period2);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new DayCountConvention_ACT_ACT_ICMA(periods, 1)
            .getDaycountFraction(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_ACT_ACT_ICMA#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When ofEpochDay minus one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_ACT_ACT_ICMA#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_ACT_ACT_ICMA.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_whenOfEpochDayMinusOne_thenReturnNaN() {
    // Arrange
    ArrayList<Period> periods = new ArrayList<>();
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    periods.add(period);

    // Act and Assert
    assertEquals(
        Double.NaN,
        new DayCountConvention_ACT_ACT_ICMA(periods, 1)
            .getDaycountFraction(LocalDate.of(1970, 1, 1), LocalDate.ofEpochDay(-1L)),
        0.0);
  }
}
