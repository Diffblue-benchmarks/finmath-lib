package net.finmath.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FloatingpointDateDiffblueTest {
  /**
   * Test {@link FloatingpointDate#getDateFromFloatingPointDate(LocalDate, double)} with {@code
   * LocalDate}, {@code double}.
   *
   * <ul>
   *   <li>Then return toString is {@code 1979-12-30}.
   * </ul>
   *
   * <p>Method under test: {@link FloatingpointDate#getDateFromFloatingPointDate(LocalDate, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate FloatingpointDate.getDateFromFloatingPointDate(LocalDate, double)"})
  public void testGetDateFromFloatingPointDateWithLocalDateDouble_thenReturnToStringIs19791230() {
    // Arrange, Act and Assert
    assertEquals(
        "1979-12-30",
        FloatingpointDate.getDateFromFloatingPointDate(LocalDate.of(1970, 1, 1), 10.0d).toString());
  }

  /**
   * Test {@link FloatingpointDate#getDateFromFloatingPointDate(LocalDate, double)} with {@code
   * LocalDate}, {@code double}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FloatingpointDate#getDateFromFloatingPointDate(LocalDate, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate FloatingpointDate.getDateFromFloatingPointDate(LocalDate, double)"})
  public void testGetDateFromFloatingPointDateWithLocalDateDouble_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(FloatingpointDate.getDateFromFloatingPointDate((LocalDate) null, 10.0d));
  }

  /**
   * Test {@link FloatingpointDate#getDateFromFloatingPointDate(LocalDateTime, double)} with {@code
   * LocalDateTime}, {@code double}.
   *
   * <p>Method under test: {@link FloatingpointDate#getDateFromFloatingPointDate(LocalDateTime,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDateTime FloatingpointDate.getDateFromFloatingPointDate(LocalDateTime, double)"
  })
  public void testGetDateFromFloatingPointDateWithLocalDateTimeDouble() {
    // Arrange and Act
    LocalDateTime actualDateFromFloatingPointDate =
        FloatingpointDate.getDateFromFloatingPointDate(
            LocalDate.of(1970, 1, 1).atStartOfDay(), 10.0d);

    // Assert
    assertEquals("00:00", actualDateFromFloatingPointDate.toLocalTime().toString());
    assertEquals("1979-12-30", actualDateFromFloatingPointDate.toLocalDate().toString());
  }

  /**
   * Test {@link FloatingpointDate#getDateFromFloatingPointDate(LocalDateTime, double)} with {@code
   * LocalDateTime}, {@code double}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FloatingpointDate#getDateFromFloatingPointDate(LocalDateTime,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDateTime FloatingpointDate.getDateFromFloatingPointDate(LocalDateTime, double)"
  })
  public void testGetDateFromFloatingPointDateWithLocalDateTimeDouble_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(FloatingpointDate.getDateFromFloatingPointDate((LocalDateTime) null, 10.0d));
  }

  /**
   * Test {@link FloatingpointDate#getFloatingPointDateFromDate(LocalDate, LocalDate)} with {@code
   * LocalDate}, {@code LocalDate}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link FloatingpointDate#getFloatingPointDateFromDate(LocalDate,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double FloatingpointDate.getFloatingPointDateFromDate(LocalDate, LocalDate)"})
  public void testGetFloatingPointDateFromDateWithLocalDateLocalDate_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        FloatingpointDate.getFloatingPointDateFromDate(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)),
        0.0);
  }

  /**
   * Test {@link FloatingpointDate#getFloatingPointDateFromDate(LocalDateTime, LocalDateTime)} with
   * {@code LocalDateTime}, {@code LocalDateTime}.
   *
   * <p>Method under test: {@link FloatingpointDate#getFloatingPointDateFromDate(LocalDateTime,
   * LocalDateTime)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double FloatingpointDate.getFloatingPointDateFromDate(LocalDateTime, LocalDateTime)"
  })
  public void testGetFloatingPointDateFromDateWithLocalDateTimeLocalDateTime() {
    // Arrange and Act
    double actualFloatingPointDateFromDate =
        FloatingpointDate.getFloatingPointDateFromDate(
            LocalDate.of(1970, 1, 1).atStartOfDay(), LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    assertEquals(0.0d, actualFloatingPointDateFromDate, 0.0);
  }
}
