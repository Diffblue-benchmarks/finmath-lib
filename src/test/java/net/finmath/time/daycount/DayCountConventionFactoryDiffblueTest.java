package net.finmath.time.daycount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DayCountConventionFactoryDiffblueTest {
  /**
   * Test {@link DayCountConventionFactory#getDayCountConvention(String)}.
   *
   * <ul>
   *   <li>When {@code 30e/360}.
   *   <li>Then return {@link DayCountConvention_30E_360}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDayCountConvention(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.daycount.DayCountConvention DayCountConventionFactory.getDayCountConvention(String)"
  })
  public void testGetDayCountConvention_when30e360_thenReturnDayCountConvention_30E_360() {
    // Arrange, Act and Assert
    assertTrue(
        DayCountConventionFactory.getDayCountConvention("30e/360")
            instanceof DayCountConvention_30E_360);
  }

  /**
   * Test {@link DayCountConventionFactory#getDayCountConvention(String)}.
   *
   * <ul>
   *   <li>When {@code 30u/360}.
   *   <li>Then return {@link DayCountConvention_30U_360}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDayCountConvention(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.daycount.DayCountConvention DayCountConventionFactory.getDayCountConvention(String)"
  })
  public void testGetDayCountConvention_when30u360_thenReturnDayCountConvention_30U_360() {
    // Arrange, Act and Assert
    assertTrue(
        DayCountConventionFactory.getDayCountConvention("30u/360")
            instanceof DayCountConvention_30U_360);
  }

  /**
   * Test {@link DayCountConventionFactory#getDayCountConvention(String)}.
   *
   * <ul>
   *   <li>When {@code 30/360}.
   *   <li>Then return {@link DayCountConvention_30E_360_ISDA}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDayCountConvention(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.daycount.DayCountConvention DayCountConventionFactory.getDayCountConvention(String)"
  })
  public void testGetDayCountConvention_when30360_thenReturnDayCountConvention_30E_360_ISDA() {
    // Arrange, Act and Assert
    assertTrue(
        DayCountConventionFactory.getDayCountConvention("30/360")
            instanceof DayCountConvention_30E_360_ISDA);
  }

  /**
   * Test {@link DayCountConventionFactory#getDayCountConvention(String)}.
   *
   * <ul>
   *   <li>When {@code act/360}.
   *   <li>Then return {@link DayCountConvention_ACT_360}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDayCountConvention(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.daycount.DayCountConvention DayCountConventionFactory.getDayCountConvention(String)"
  })
  public void testGetDayCountConvention_whenAct360_thenReturnDayCountConvention_ACT_360() {
    // Arrange, Act and Assert
    assertTrue(
        DayCountConventionFactory.getDayCountConvention("act/360")
            instanceof DayCountConvention_ACT_360);
  }

  /**
   * Test {@link DayCountConventionFactory#getDayCountConvention(String)}.
   *
   * <ul>
   *   <li>When {@code act/365}.
   *   <li>Then return {@link DayCountConvention_ACT_365}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDayCountConvention(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.daycount.DayCountConvention DayCountConventionFactory.getDayCountConvention(String)"
  })
  public void testGetDayCountConvention_whenAct365_thenReturnDayCountConvention_ACT_365() {
    // Arrange, Act and Assert
    assertTrue(
        DayCountConventionFactory.getDayCountConvention("act/365")
            instanceof DayCountConvention_ACT_365);
  }

  /**
   * Test {@link DayCountConventionFactory#getDayCountConvention(String)}.
   *
   * <ul>
   *   <li>When {@code act/act}.
   *   <li>Then return {@link DayCountConvention_ACT_ACT_ISDA}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDayCountConvention(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.daycount.DayCountConvention DayCountConventionFactory.getDayCountConvention(String)"
  })
  public void testGetDayCountConvention_whenActAct_thenReturnDayCountConvention_ACT_ACT_ISDA() {
    // Arrange, Act and Assert
    assertTrue(
        DayCountConventionFactory.getDayCountConvention("act/act")
            instanceof DayCountConvention_ACT_ACT_ISDA);
  }

  /**
   * Test {@link DayCountConventionFactory#getDayCountConvention(String)}.
   *
   * <ul>
   *   <li>When {@code Convention}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDayCountConvention(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.daycount.DayCountConvention DayCountConventionFactory.getDayCountConvention(String)"
  })
  public void testGetDayCountConvention_whenConvention_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DayCountConventionFactory.getDayCountConvention("Convention"));
  }

  /**
   * Test {@link DayCountConventionFactory#getDayCountConvention(String)}.
   *
   * <ul>
   *   <li>When {@code e30/360}.
   *   <li>Then return {@link DayCountConvention_30E_360}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDayCountConvention(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.daycount.DayCountConvention DayCountConventionFactory.getDayCountConvention(String)"
  })
  public void testGetDayCountConvention_whenE30360_thenReturnDayCountConvention_30E_360() {
    // Arrange, Act and Assert
    assertTrue(
        DayCountConventionFactory.getDayCountConvention("e30/360")
            instanceof DayCountConvention_30E_360);
  }

  /**
   * Test {@link DayCountConventionFactory#getDayCountConvention(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDayCountConvention(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.daycount.DayCountConvention DayCountConventionFactory.getDayCountConvention(String)"
  })
  public void testGetDayCountConvention_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DayCountConventionFactory.getDayCountConvention(null));
  }

  /**
   * Test {@link DayCountConventionFactory#getDayCountConvention(String)}.
   *
   * <ul>
   *   <li>When {@code u30/360}.
   *   <li>Then return {@link DayCountConvention_30U_360}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDayCountConvention(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.daycount.DayCountConvention DayCountConventionFactory.getDayCountConvention(String)"
  })
  public void testGetDayCountConvention_whenU30360_thenReturnDayCountConvention_30U_360() {
    // Arrange, Act and Assert
    assertTrue(
        DayCountConventionFactory.getDayCountConvention("u30/360")
            instanceof DayCountConvention_30U_360);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code 30e/360}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConventionFactory.getDaycount(LocalDate, LocalDate, String)"})
  public void testGetDaycount_when30e360_thenReturnZero() {
    // Arrange and Act
    double actualDaycount =
        DayCountConventionFactory.getDaycount(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "30e/360");

    // Assert
    assertEquals(0.0d, actualDaycount, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code 30u/360}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConventionFactory.getDaycount(LocalDate, LocalDate, String)"})
  public void testGetDaycount_when30u360_thenReturnZero() {
    // Arrange and Act
    double actualDaycount =
        DayCountConventionFactory.getDaycount(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "30u/360");

    // Assert
    assertEquals(0.0d, actualDaycount, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code 30/360}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConventionFactory.getDaycount(LocalDate, LocalDate, String)"})
  public void testGetDaycount_when30360_thenReturnZero() {
    // Arrange and Act
    double actualDaycount =
        DayCountConventionFactory.getDaycount(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "30/360");

    // Assert
    assertEquals(0.0d, actualDaycount, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code act/360}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConventionFactory.getDaycount(LocalDate, LocalDate, String)"})
  public void testGetDaycount_whenAct360_thenReturnZero() {
    // Arrange and Act
    double actualDaycount =
        DayCountConventionFactory.getDaycount(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "act/360");

    // Assert
    assertEquals(0.0d, actualDaycount, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code act/365}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConventionFactory.getDaycount(LocalDate, LocalDate, String)"})
  public void testGetDaycount_whenAct365_thenReturnZero() {
    // Arrange and Act
    double actualDaycount =
        DayCountConventionFactory.getDaycount(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "act/365");

    // Assert
    assertEquals(0.0d, actualDaycount, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code act/act}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConventionFactory.getDaycount(LocalDate, LocalDate, String)"})
  public void testGetDaycount_whenActAct_thenReturnZero() {
    // Arrange and Act
    double actualDaycount =
        DayCountConventionFactory.getDaycount(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "act/act");

    // Assert
    assertEquals(0.0d, actualDaycount, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code Convention}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConventionFactory.getDaycount(LocalDate, LocalDate, String)"})
  public void testGetDaycount_whenConvention_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DayCountConventionFactory.getDaycount(
                LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "Convention"));
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code e30/360}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConventionFactory.getDaycount(LocalDate, LocalDate, String)"})
  public void testGetDaycount_whenE30360_thenReturnZero() {
    // Arrange and Act
    double actualDaycount =
        DayCountConventionFactory.getDaycount(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "e30/360");

    // Assert
    assertEquals(0.0d, actualDaycount, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConventionFactory.getDaycount(LocalDate, LocalDate, String)"})
  public void testGetDaycount_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DayCountConventionFactory.getDaycount(
                LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), null));
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code u30/360}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycount(LocalDate, LocalDate,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DayCountConventionFactory.getDaycount(LocalDate, LocalDate, String)"})
  public void testGetDaycount_whenU30360_thenReturnZero() {
    // Arrange and Act
    double actualDaycount =
        DayCountConventionFactory.getDaycount(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "u30/360");

    // Assert
    assertEquals(0.0d, actualDaycount, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycountFraction(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code 30e/360}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycountFraction(LocalDate,
   * LocalDate, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConventionFactory.getDaycountFraction(LocalDate, LocalDate, String)"
  })
  public void testGetDaycountFraction_when30e360_thenReturnZero() {
    // Arrange and Act
    double actualDaycountFraction =
        DayCountConventionFactory.getDaycountFraction(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "30e/360");

    // Assert
    assertEquals(0.0d, actualDaycountFraction, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycountFraction(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code 30u/360}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycountFraction(LocalDate,
   * LocalDate, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConventionFactory.getDaycountFraction(LocalDate, LocalDate, String)"
  })
  public void testGetDaycountFraction_when30u360_thenReturnZero() {
    // Arrange and Act
    double actualDaycountFraction =
        DayCountConventionFactory.getDaycountFraction(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "30u/360");

    // Assert
    assertEquals(0.0d, actualDaycountFraction, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycountFraction(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code 30/360}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycountFraction(LocalDate,
   * LocalDate, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConventionFactory.getDaycountFraction(LocalDate, LocalDate, String)"
  })
  public void testGetDaycountFraction_when30360_thenReturnZero() {
    // Arrange and Act
    double actualDaycountFraction =
        DayCountConventionFactory.getDaycountFraction(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "30/360");

    // Assert
    assertEquals(0.0d, actualDaycountFraction, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycountFraction(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code act/360}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycountFraction(LocalDate,
   * LocalDate, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConventionFactory.getDaycountFraction(LocalDate, LocalDate, String)"
  })
  public void testGetDaycountFraction_whenAct360_thenReturnZero() {
    // Arrange and Act
    double actualDaycountFraction =
        DayCountConventionFactory.getDaycountFraction(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "act/360");

    // Assert
    assertEquals(0.0d, actualDaycountFraction, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycountFraction(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code act/365}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycountFraction(LocalDate,
   * LocalDate, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConventionFactory.getDaycountFraction(LocalDate, LocalDate, String)"
  })
  public void testGetDaycountFraction_whenAct365_thenReturnZero() {
    // Arrange and Act
    double actualDaycountFraction =
        DayCountConventionFactory.getDaycountFraction(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "act/365");

    // Assert
    assertEquals(0.0d, actualDaycountFraction, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycountFraction(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code act/act}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycountFraction(LocalDate,
   * LocalDate, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConventionFactory.getDaycountFraction(LocalDate, LocalDate, String)"
  })
  public void testGetDaycountFraction_whenActAct_thenReturnZero() {
    // Arrange and Act
    double actualDaycountFraction =
        DayCountConventionFactory.getDaycountFraction(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "act/act");

    // Assert
    assertEquals(0.0d, actualDaycountFraction, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycountFraction(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code Convention}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycountFraction(LocalDate,
   * LocalDate, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConventionFactory.getDaycountFraction(LocalDate, LocalDate, String)"
  })
  public void testGetDaycountFraction_whenConvention_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DayCountConventionFactory.getDaycountFraction(
                LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "Convention"));
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycountFraction(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code e30/360}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycountFraction(LocalDate,
   * LocalDate, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConventionFactory.getDaycountFraction(LocalDate, LocalDate, String)"
  })
  public void testGetDaycountFraction_whenE30360_thenReturnZero() {
    // Arrange and Act
    double actualDaycountFraction =
        DayCountConventionFactory.getDaycountFraction(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "e30/360");

    // Assert
    assertEquals(0.0d, actualDaycountFraction, 0.0);
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycountFraction(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycountFraction(LocalDate,
   * LocalDate, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConventionFactory.getDaycountFraction(LocalDate, LocalDate, String)"
  })
  public void testGetDaycountFraction_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DayCountConventionFactory.getDaycountFraction(
                LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), null));
  }

  /**
   * Test {@link DayCountConventionFactory#getDaycountFraction(LocalDate, LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code u30/360}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DayCountConventionFactory#getDaycountFraction(LocalDate,
   * LocalDate, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DayCountConventionFactory.getDaycountFraction(LocalDate, LocalDate, String)"
  })
  public void testGetDaycountFraction_whenU30360_thenReturnZero() {
    // Arrange and Act
    double actualDaycountFraction =
        DayCountConventionFactory.getDaycountFraction(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "u30/360");

    // Assert
    assertEquals(0.0d, actualDaycountFraction, 0.0);
  }
}
