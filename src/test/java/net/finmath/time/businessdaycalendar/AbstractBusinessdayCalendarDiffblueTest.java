package net.finmath.time.businessdaycalendar;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractBusinessdayCalendarDiffblueTest {
  /**
   * Test {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, String, DateRollConvention)}
   * with {@code baseDate}, {@code dateOffsetCode}, {@code dateRollConvention}.
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, String,
   * DateRollConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate AbstractBusinessdayCalendar.getAdjustedDate(LocalDate, String, DateRollConvention)"
  })
  public void testGetAdjustedDateWithBaseDateDateOffsetCodeDateRollConvention() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new BusinessdayCalendarAny()
                .getAdjustedDate(
                    LocalDate.of(1970, 1, 1),
                    "(?<=[0-9|\\.])(?=[A-Z|a-z])",
                    DateRollConvention.UNADJUSTED));
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, String, DateRollConvention)}
   * with {@code baseDate}, {@code dateOffsetCode}, {@code dateRollConvention}.
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, String,
   * DateRollConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate AbstractBusinessdayCalendar.getAdjustedDate(LocalDate, String, DateRollConvention)"
  })
  public void testGetAdjustedDateWithBaseDateDateOffsetCodeDateRollConvention2() {
    // Arrange, Act and Assert
    assertEquals(
        "-3329583-01-20",
        new BusinessdayCalendarAny()
            .getAdjustedDate(
                LocalDate.of(1970, 1, 1), "20200301", DateRollConvention.MODIFIED_FOLLOWING)
            .toString());
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, String, DateRollConvention)}
   * with {@code baseDate}, {@code dateOffsetCode}, {@code dateRollConvention}.
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, String,
   * DateRollConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate AbstractBusinessdayCalendar.getAdjustedDate(LocalDate, String, DateRollConvention)"
  })
  public void testGetAdjustedDateWithBaseDateDateOffsetCodeDateRollConvention3() {
    // Arrange, Act and Assert
    assertEquals(
        "-3329583-01-20",
        new BusinessdayCalendarAny()
            .getAdjustedDate(
                LocalDate.of(1970, 1, 1), "20200301", DateRollConvention.MODIFIED_PRECEDING)
            .toString());
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, String, DateRollConvention)}
   * with {@code baseDate}, {@code dateOffsetCode}, {@code dateRollConvention}.
   *
   * <ul>
   *   <li>When {@code FOLLOWING}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, String,
   * DateRollConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate AbstractBusinessdayCalendar.getAdjustedDate(LocalDate, String, DateRollConvention)"
  })
  public void testGetAdjustedDateWithBaseDateDateOffsetCodeDateRollConvention_whenFollowing() {
    // Arrange, Act and Assert
    assertEquals(
        "-3329583-01-20",
        new BusinessdayCalendarAny()
            .getAdjustedDate(LocalDate.of(1970, 1, 1), "20200301", DateRollConvention.FOLLOWING)
            .toString());
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, String, DateRollConvention)}
   * with {@code baseDate}, {@code dateOffsetCode}, {@code dateRollConvention}.
   *
   * <ul>
   *   <li>When {@code PRECEDING}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, String,
   * DateRollConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate AbstractBusinessdayCalendar.getAdjustedDate(LocalDate, String, DateRollConvention)"
  })
  public void testGetAdjustedDateWithBaseDateDateOffsetCodeDateRollConvention_whenPreceding() {
    // Arrange, Act and Assert
    assertEquals(
        "-3329583-01-20",
        new BusinessdayCalendarAny()
            .getAdjustedDate(LocalDate.of(1970, 1, 1), "20200301", DateRollConvention.PRECEDING)
            .toString());
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, String, DateRollConvention)}
   * with {@code baseDate}, {@code dateOffsetCode}, {@code dateRollConvention}.
   *
   * <ul>
   *   <li>When {@code UNADJUSTED}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, String,
   * DateRollConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate AbstractBusinessdayCalendar.getAdjustedDate(LocalDate, String, DateRollConvention)"
  })
  public void testGetAdjustedDateWithBaseDateDateOffsetCodeDateRollConvention_whenUnadjusted() {
    // Arrange, Act and Assert
    assertEquals(
        "-3329583-01-20",
        new BusinessdayCalendarAny()
            .getAdjustedDate(LocalDate.of(1970, 1, 1), "20200301", DateRollConvention.UNADJUSTED)
            .toString());
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, DateRollConvention)} with
   * {@code date}, {@code dateRollConvention}.
   *
   * <ul>
   *   <li>When {@link DateRollConvention#MODIFIED_FOLLOWING}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate,
   * DateRollConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate AbstractBusinessdayCalendar.getAdjustedDate(LocalDate, DateRollConvention)"
  })
  public void testGetAdjustedDateWithDateDateRollConvention_whenModified_following() {
    // Arrange
    LocalDate date = LocalDate.of(1970, 1, 1);

    // Act
    LocalDate actualAdjustedDate =
        new BusinessdayCalendarAny().getAdjustedDate(date, DateRollConvention.MODIFIED_FOLLOWING);

    // Assert
    assertSame(date, actualAdjustedDate);
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, DateRollConvention)} with
   * {@code date}, {@code dateRollConvention}.
   *
   * <ul>
   *   <li>When {@link DateRollConvention#MODIFIED_PRECEDING}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate,
   * DateRollConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate AbstractBusinessdayCalendar.getAdjustedDate(LocalDate, DateRollConvention)"
  })
  public void testGetAdjustedDateWithDateDateRollConvention_whenModified_preceding() {
    // Arrange
    LocalDate date = LocalDate.of(1970, 1, 1);

    // Act
    LocalDate actualAdjustedDate =
        new BusinessdayCalendarAny().getAdjustedDate(date, DateRollConvention.MODIFIED_PRECEDING);

    // Assert
    assertSame(date, actualAdjustedDate);
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate, DateRollConvention)} with
   * {@code date}, {@code dateRollConvention}.
   *
   * <ul>
   *   <li>When {@code UNADJUSTED}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#getAdjustedDate(LocalDate,
   * DateRollConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate AbstractBusinessdayCalendar.getAdjustedDate(LocalDate, DateRollConvention)"
  })
  public void testGetAdjustedDateWithDateDateRollConvention_whenUnadjusted() {
    // Arrange
    LocalDate date = LocalDate.of(1970, 1, 1);

    // Act
    LocalDate actualAdjustedDate =
        new BusinessdayCalendarAny().getAdjustedDate(date, DateRollConvention.UNADJUSTED);

    // Assert
    assertSame(date, actualAdjustedDate);
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#getRolledDate(LocalDate, int)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return toString is {@code 1969-12-31}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#getRolledDate(LocalDate, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate AbstractBusinessdayCalendar.getRolledDate(LocalDate, int)"})
  public void testGetRolledDate_whenMinusOne_thenReturnToStringIs19691231() {
    // Arrange, Act and Assert
    assertEquals(
        "1969-12-31",
        new BusinessdayCalendarAny().getRolledDate(LocalDate.of(1970, 1, 1), -1).toString());
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#getRolledDate(LocalDate, int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return toString is {@code 1970-01-02}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#getRolledDate(LocalDate, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate AbstractBusinessdayCalendar.getRolledDate(LocalDate, int)"})
  public void testGetRolledDate_whenOne_thenReturnToStringIs19700102() {
    // Arrange, Act and Assert
    assertEquals(
        "1970-01-02",
        new BusinessdayCalendarAny().getRolledDate(LocalDate.of(1970, 1, 1), 1).toString());
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#getDateFromDateAndOffsetCode(LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code (?<=[0-9|\.])(?=[A-Z|a-z])}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractBusinessdayCalendar#getDateFromDateAndOffsetCode(LocalDate, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate AbstractBusinessdayCalendar.getDateFromDateAndOffsetCode(LocalDate, String)"
  })
  public void testGetDateFromDateAndOffsetCode_when09AZAZ_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new BusinessdayCalendarAny()
                .getDateFromDateAndOffsetCode(
                    LocalDate.of(1970, 1, 1), "(?<=[0-9|\\.])(?=[A-Z|a-z])"));
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#getDateFromDateAndOffsetCode(LocalDate, String)}.
   *
   * <ul>
   *   <li>When {@code 20200301}.
   *   <li>Then return toString is {@code -3329583-01-20}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractBusinessdayCalendar#getDateFromDateAndOffsetCode(LocalDate, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate AbstractBusinessdayCalendar.getDateFromDateAndOffsetCode(LocalDate, String)"
  })
  public void testGetDateFromDateAndOffsetCode_when20200301_thenReturnToStringIs33295830120() {
    // Arrange, Act and Assert
    assertEquals(
        "-3329583-01-20",
        new BusinessdayCalendarAny()
            .getDateFromDateAndOffsetCode(LocalDate.of(1970, 1, 1), "20200301")
            .toString());
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#createDateFromDateAndOffsetCodes(LocalDate, String[])}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractBusinessdayCalendar#createDateFromDateAndOffsetCodes(LocalDate, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate[] AbstractBusinessdayCalendar.createDateFromDateAndOffsetCodes(LocalDate, String[])"
  })
  public void testCreateDateFromDateAndOffsetCodes_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(
        0,
        new BusinessdayCalendarAny()
            .createDateFromDateAndOffsetCodes(LocalDate.of(1970, 1, 1), new String[] {})
            .length);
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#createDateFromDateAndOffsetCodes(LocalDate, String[])}.
   *
   * <ul>
   *   <li>Then return first element toString is {@code -3329583-01-20}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractBusinessdayCalendar#createDateFromDateAndOffsetCodes(LocalDate, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate[] AbstractBusinessdayCalendar.createDateFromDateAndOffsetCodes(LocalDate, String[])"
  })
  public void testCreateDateFromDateAndOffsetCodes_thenReturnFirstElementToStringIs33295830120() {
    // Arrange and Act
    LocalDate[] actualCreateDateFromDateAndOffsetCodesResult =
        new BusinessdayCalendarAny()
            .createDateFromDateAndOffsetCodes(LocalDate.of(1970, 1, 1), new String[] {"20200301"});

    // Assert
    assertEquals("-3329583-01-20", actualCreateDateFromDateAndOffsetCodesResult[0].toString());
    assertEquals(1, actualCreateDateFromDateAndOffsetCodesResult.length);
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#createDateFromDateAndOffsetCodes(LocalDate, String[])}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractBusinessdayCalendar#createDateFromDateAndOffsetCodes(LocalDate, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate[] AbstractBusinessdayCalendar.createDateFromDateAndOffsetCodes(LocalDate, String[])"
  })
  public void testCreateDateFromDateAndOffsetCodes_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new BusinessdayCalendarAny()
                .createDateFromDateAndOffsetCodes(
                    LocalDate.of(1970, 1, 1), new String[] {"(?<=[0-9|\\.])(?=[A-Z|a-z])"}));
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#convertOffsetCodesToTimes(String[])}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@code 2.0200301E7}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#convertOffsetCodesToTimes(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] AbstractBusinessdayCalendar.convertOffsetCodesToTimes(String[])"})
  public void testConvertOffsetCodesToTimes_thenReturnArrayOfDoubleWith20200301e7() {
    // Arrange and Act
    double[] actualConvertOffsetCodesToTimesResult =
        new BusinessdayCalendarAny().convertOffsetCodesToTimes(new String[] {"20200301"});

    // Assert
    assertArrayEquals(new double[] {2.0200301E7d}, actualConvertOffsetCodesToTimesResult, 0.0);
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#convertOffsetCodesToTimes(String[])}.
   *
   * <ul>
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#convertOffsetCodesToTimes(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] AbstractBusinessdayCalendar.convertOffsetCodesToTimes(String[])"})
  public void testConvertOffsetCodesToTimes_thenReturnEmptyArrayOfDouble() {
    // Arrange and Act
    double[] actualConvertOffsetCodesToTimesResult =
        new BusinessdayCalendarAny().convertOffsetCodesToTimes(new String[] {});

    // Assert
    assertArrayEquals(new double[] {}, actualConvertOffsetCodesToTimesResult, 0.0);
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#convertOffsetCodesToTimes(String[])}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#convertOffsetCodesToTimes(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] AbstractBusinessdayCalendar.convertOffsetCodesToTimes(String[])"})
  public void testConvertOffsetCodesToTimes_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new BusinessdayCalendarAny()
                .convertOffsetCodesToTimes(new String[] {"(?<=[0-9|\\.])(?=[A-Z|a-z])"}));
  }

  /**
   * Test {@link AbstractBusinessdayCalendar#toString()}.
   *
   * <p>Method under test: {@link AbstractBusinessdayCalendar#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractBusinessdayCalendar.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals("BusinessdayCalendarAny", new BusinessdayCalendarAny().toString());
  }
}
