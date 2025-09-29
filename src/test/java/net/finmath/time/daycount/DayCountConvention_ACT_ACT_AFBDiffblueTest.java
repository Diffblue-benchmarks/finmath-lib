package net.finmath.time.daycount;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DayCountConvention_ACT_ACT_AFBDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DayCountConvention_ACT_ACT_AFB}
   *   <li>{@link DayCountConvention_ACT_ACT_AFB#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DayCountConvention_ACT_ACT_AFB.<init>()",
    "java.lang.String DayCountConvention_ACT_ACT_AFB.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("DayCountConvention_ACT_ACT_AFB", new DayCountConvention_ACT_ACT_AFB().toString());
  }

  /**
   * Test {@link DayCountConvention_ACT_ACT_AFB#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>Then return {@code 1938.9178082191781}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_ACT_ACT_AFB#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_ACT_ACT_AFB.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_thenReturn19389178082191781() {
    // Arrange, Act and Assert
    assertEquals(
        1938.9178082191781d,
        new DayCountConvention_ACT_ACT_AFB()
            .getDaycountFraction(LocalDate.ofYearDay(31, 31), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_ACT_ACT_AFB#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>Then return {@code 1969.9178082191781}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_ACT_ACT_AFB#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_ACT_ACT_AFB.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_thenReturn19699178082191781() {
    // Arrange, Act and Assert
    assertEquals(
        1969.9178082191781d,
        new DayCountConvention_ACT_ACT_AFB()
            .getDaycountFraction(LocalDate.ofYearDay(0, 31), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_ACT_ACT_AFB#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_ACT_ACT_AFB#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_ACT_ACT_AFB.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_whenLocalDateWith1970AndOneAndOne_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        new DayCountConvention_ACT_ACT_AFB()
            .getDaycountFraction(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_ACT_ACT_AFB#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>When ofYearDay thirty-one and thirty-one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_ACT_ACT_AFB#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_ACT_ACT_AFB.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_whenOfYearDayThirtyOneAndThirtyOne_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        new DayCountConvention_ACT_ACT_AFB()
            .getDaycountFraction(LocalDate.ofYearDay(31, 31), LocalDate.ofYearDay(31, 31)),
        0.0);
  }
}
