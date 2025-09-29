package net.finmath.time.daycount;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DayCountConvention_ACT_ACT_ISDADiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DayCountConvention_ACT_ACT_ISDA#DayCountConvention_ACT_ACT_ISDA()}
   *   <li>{@link DayCountConvention_ACT_ACT_ISDA#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DayCountConvention_ACT_ACT_ISDA.<init>()",
    "void DayCountConvention_ACT_ACT_ISDA.<init>(boolean)",
    "java.lang.String DayCountConvention_ACT_ACT_ISDA.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "DayCountConvention_ACT_ACT_ISDA [isCountLastDayNotFirst=false]",
        new DayCountConvention_ACT_ACT_ISDA().toString());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DayCountConvention_ACT_ACT_ISDA#DayCountConvention_ACT_ACT_ISDA(boolean)}
   *   <li>{@link DayCountConvention_ACT_ACT_ISDA#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DayCountConvention_ACT_ACT_ISDA.<init>()",
    "void DayCountConvention_ACT_ACT_ISDA.<init>(boolean)",
    "java.lang.String DayCountConvention_ACT_ACT_ISDA.toString()"
  })
  public void testGettersAndSetters2() {
    // Arrange, Act and Assert
    assertEquals(
        "DayCountConvention_ACT_ACT_ISDA [isCountLastDayNotFirst=true]",
        new DayCountConvention_ACT_ACT_ISDA(true).toString());
  }

  /**
   * Test {@link DayCountConvention_ACT_ACT_ISDA#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>Then return {@code 1.1275702593849246E-17}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_ACT_ACT_ISDA#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_ACT_ACT_ISDA.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_thenReturn11275702593849246e17() {
    // Arrange, Act and Assert
    assertEquals(
        1.1275702593849246E-17d,
        new DayCountConvention_ACT_ACT_ISDA(true)
            .getDaycountFraction(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link DayCountConvention_ACT_ACT_ISDA#getDaycountFraction(LocalDate, LocalDate)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConvention_ACT_ACT_ISDA#getDaycountFraction(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConvention_ACT_ACT_ISDA.getDaycountFraction(LocalDate, LocalDate)"
  })
  public void testGetDaycountFraction_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        new DayCountConvention_ACT_ACT_ISDA(false)
            .getDaycountFraction(LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }
}
