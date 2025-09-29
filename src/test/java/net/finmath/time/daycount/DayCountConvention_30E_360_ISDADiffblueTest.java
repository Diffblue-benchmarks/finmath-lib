package net.finmath.time.daycount;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DayCountConvention_30E_360_ISDADiffblueTest {
  /**
   * Test {@link DayCountConvention_30E_360_ISDA#getDaycount(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_30E_360_ISDA#getDaycount(LocalDate, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConvention_30E_360_ISDA.getDaycount(LocalDate, LocalDate)"})
  public void testGetDaycount_whenLocalDateWith1970AndOneAndOne_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        new DayCountConvention_30E_360_ISDA(true)
            .getDaycount(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_30E_360_ISDA#getDaycount(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When ofEpochDay minus one.
   *   <li>Then return {@code -708478.0}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_30E_360_ISDA#getDaycount(LocalDate, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConvention_30E_360_ISDA.getDaycount(LocalDate, LocalDate)"})
  public void testGetDaycount_whenOfEpochDayMinusOne_thenReturn7084780() {
    // Arrange, Act and Assert
    assertEquals(
        -708478.0d,
        new DayCountConvention_30E_360_ISDA(true)
            .getDaycount(LocalDate.ofEpochDay(-1L), LocalDate.ofYearDay(2, 2)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_30E_360_ISDA#getDaycount(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When ofEpochDay minus one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_30E_360_ISDA#getDaycount(LocalDate, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConvention_30E_360_ISDA.getDaycount(LocalDate, LocalDate)"})
  public void testGetDaycount_whenOfEpochDayMinusOne_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(
        1.0d,
        new DayCountConvention_30E_360_ISDA(true)
            .getDaycount(LocalDate.ofEpochDay(-1L), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_30E_360_ISDA#getDaycount(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When ofEpochDay thirty-one.
   *   <li>Then return {@code -708509.0}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_30E_360_ISDA#getDaycount(LocalDate, LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConvention_30E_360_ISDA.getDaycount(LocalDate, LocalDate)"})
  public void testGetDaycount_whenOfEpochDayThirtyOne_thenReturn7085090() {
    // Arrange, Act and Assert
    assertEquals(
        -708509.0d,
        new DayCountConvention_30E_360_ISDA(true)
            .getDaycount(LocalDate.ofEpochDay(31L), LocalDate.ofYearDay(2, 2)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_30E_360_ISDA#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_30E_360_ISDA#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_30E_360_ISDA.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_whenLocalDateWith1970AndOneAndOne_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        new DayCountConvention_30E_360_ISDA(true)
            .getDaycountFraction(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_30E_360_ISDA#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When ofEpochDay minus one.
   *   <li>Then return {@code 0.002777777777777778}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_30E_360_ISDA#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_30E_360_ISDA.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_whenOfEpochDayMinusOne_thenReturn0002777777777777778() {
    // Arrange, Act and Assert
    assertEquals(
        0.002777777777777778d,
        new DayCountConvention_30E_360_ISDA(true)
            .getDaycountFraction(LocalDate.ofEpochDay(-1L), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_30E_360_ISDA#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When ofEpochDay minus one.
   *   <li>Then return {@code -1967.9944444444445}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_30E_360_ISDA#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_30E_360_ISDA.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_whenOfEpochDayMinusOne_thenReturn19679944444444445() {
    // Arrange, Act and Assert
    assertEquals(
        -1967.9944444444445d,
        new DayCountConvention_30E_360_ISDA(true)
            .getDaycountFraction(LocalDate.ofEpochDay(-1L), LocalDate.ofYearDay(2, 2)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_30E_360_ISDA#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When ofEpochDay thirty-one.
   *   <li>Then return {@code -1968.0805555555555}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_30E_360_ISDA#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_30E_360_ISDA.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_whenOfEpochDayThirtyOne_thenReturn19680805555555555() {
    // Arrange, Act and Assert
    assertEquals(
        -1968.0805555555555d,
        new DayCountConvention_30E_360_ISDA(true)
            .getDaycountFraction(LocalDate.ofEpochDay(31L), LocalDate.ofYearDay(2, 2)),
        0.0);
  }
}
