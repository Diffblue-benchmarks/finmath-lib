package net.finmath.time.businessdaycalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateOffsetUnit;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BusinessdayCalendarDiffblueTest {
  /**
   * Test DateOffsetUnit {@link DateOffsetUnit#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code b}.
   *   <li>Then return {@code BUSINESS_DAYS}.
   * </ul>
   *
   * <p>Method under test: {@link DateOffsetUnit#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateOffsetUnit DateOffsetUnit.getEnum(String)"})
  public void testDateOffsetUnitGetEnum_whenB_thenReturnBusinessDays() {
    // Arrange, Act and Assert
    assertEquals(DateOffsetUnit.BUSINESS_DAYS, DateOffsetUnit.getEnum("b"));
  }

  /**
   * Test DateOffsetUnit {@link DateOffsetUnit#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code bd}.
   *   <li>Then return {@code BUSINESS_DAYS}.
   * </ul>
   *
   * <p>Method under test: {@link DateOffsetUnit#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateOffsetUnit DateOffsetUnit.getEnum(String)"})
  public void testDateOffsetUnitGetEnum_whenBd_thenReturnBusinessDays() {
    // Arrange, Act and Assert
    assertEquals(DateOffsetUnit.BUSINESS_DAYS, DateOffsetUnit.getEnum("bd"));
  }

  /**
   * Test DateOffsetUnit {@link DateOffsetUnit#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code BUSINESS_DAYS}.
   *   <li>Then return {@code BUSINESS_DAYS}.
   * </ul>
   *
   * <p>Method under test: {@link DateOffsetUnit#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateOffsetUnit DateOffsetUnit.getEnum(String)"})
  public void testDateOffsetUnitGetEnum_whenBusinessDays_thenReturnBusinessDays() {
    // Arrange, Act and Assert
    assertEquals(DateOffsetUnit.BUSINESS_DAYS, DateOffsetUnit.getEnum("BUSINESS_DAYS"));
  }

  /**
   * Test DateOffsetUnit {@link DateOffsetUnit#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code d}.
   *   <li>Then return {@code DAYS}.
   * </ul>
   *
   * <p>Method under test: {@link DateOffsetUnit#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateOffsetUnit DateOffsetUnit.getEnum(String)"})
  public void testDateOffsetUnitGetEnum_whenD_thenReturnDays() {
    // Arrange, Act and Assert
    assertEquals(DateOffsetUnit.DAYS, DateOffsetUnit.getEnum("d"));
  }

  /**
   * Test DateOffsetUnit {@link DateOffsetUnit#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code m}.
   *   <li>Then return {@code MONTHS}.
   * </ul>
   *
   * <p>Method under test: {@link DateOffsetUnit#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateOffsetUnit DateOffsetUnit.getEnum(String)"})
  public void testDateOffsetUnitGetEnum_whenM_thenReturnMonths() {
    // Arrange, Act and Assert
    assertEquals(DateOffsetUnit.MONTHS, DateOffsetUnit.getEnum("m"));
  }

  /**
   * Test DateOffsetUnit {@link DateOffsetUnit#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DateOffsetUnit#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateOffsetUnit DateOffsetUnit.getEnum(String)"})
  public void testDateOffsetUnitGetEnum_whenString_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DateOffsetUnit.getEnum("String"));
  }

  /**
   * Test DateOffsetUnit {@link DateOffsetUnit#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code w}.
   *   <li>Then return {@code WEEKS}.
   * </ul>
   *
   * <p>Method under test: {@link DateOffsetUnit#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateOffsetUnit DateOffsetUnit.getEnum(String)"})
  public void testDateOffsetUnitGetEnum_whenW_thenReturnWeeks() {
    // Arrange, Act and Assert
    assertEquals(DateOffsetUnit.WEEKS, DateOffsetUnit.getEnum("w"));
  }

  /**
   * Test DateOffsetUnit {@link DateOffsetUnit#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code y}.
   *   <li>Then return {@code YEARS}.
   * </ul>
   *
   * <p>Method under test: {@link DateOffsetUnit#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateOffsetUnit DateOffsetUnit.getEnum(String)"})
  public void testDateOffsetUnitGetEnum_whenY_thenReturnYears() {
    // Arrange, Act and Assert
    assertEquals(DateOffsetUnit.YEARS, DateOffsetUnit.getEnum("y"));
  }

  /**
   * Test DateRollConvention {@link DateRollConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code actual}.
   *   <li>Then return {@code UNADJUSTED}.
   * </ul>
   *
   * <p>Method under test: {@link DateRollConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateRollConvention DateRollConvention.getEnum(String)"})
  public void testDateRollConventionGetEnum_whenActual_thenReturnUnadjusted() {
    // Arrange, Act and Assert
    assertEquals(DateRollConvention.UNADJUSTED, DateRollConvention.getEnum("actual"));
  }

  /**
   * Test DateRollConvention {@link DateRollConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code follow}.
   *   <li>Then return {@code FOLLOWING}.
   * </ul>
   *
   * <p>Method under test: {@link DateRollConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateRollConvention DateRollConvention.getEnum(String)"})
  public void testDateRollConventionGetEnum_whenFollow_thenReturnFollowing() {
    // Arrange, Act and Assert
    assertEquals(DateRollConvention.FOLLOWING, DateRollConvention.getEnum("follow"));
  }

  /**
   * Test DateRollConvention {@link DateRollConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code FOLLOWING}.
   *   <li>Then return {@code FOLLOWING}.
   * </ul>
   *
   * <p>Method under test: {@link DateRollConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateRollConvention DateRollConvention.getEnum(String)"})
  public void testDateRollConventionGetEnum_whenFollowing_thenReturnFollowing() {
    // Arrange, Act and Assert
    assertEquals(DateRollConvention.FOLLOWING, DateRollConvention.getEnum("FOLLOWING"));
  }

  /**
   * Test DateRollConvention {@link DateRollConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code modfollow}.
   *   <li>Then return {@code MODIFIED_FOLLOWING}.
   * </ul>
   *
   * <p>Method under test: {@link DateRollConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateRollConvention DateRollConvention.getEnum(String)"})
  public void testDateRollConventionGetEnum_whenModfollow_thenReturnModifiedFollowing() {
    // Arrange, Act and Assert
    assertEquals(DateRollConvention.MODIFIED_FOLLOWING, DateRollConvention.getEnum("modfollow"));
  }

  /**
   * Test DateRollConvention {@link DateRollConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code modpreced}.
   *   <li>Then return {@code MODIFIED_PRECEDING}.
   * </ul>
   *
   * <p>Method under test: {@link DateRollConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateRollConvention DateRollConvention.getEnum(String)"})
  public void testDateRollConventionGetEnum_whenModpreced_thenReturnModifiedPreceding() {
    // Arrange, Act and Assert
    assertEquals(DateRollConvention.MODIFIED_PRECEDING, DateRollConvention.getEnum("modpreced"));
  }

  /**
   * Test DateRollConvention {@link DateRollConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code preced}.
   *   <li>Then return {@code PRECEDING}.
   * </ul>
   *
   * <p>Method under test: {@link DateRollConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateRollConvention DateRollConvention.getEnum(String)"})
  public void testDateRollConventionGetEnum_whenPreced_thenReturnPreceding() {
    // Arrange, Act and Assert
    assertEquals(DateRollConvention.PRECEDING, DateRollConvention.getEnum("preced"));
  }

  /**
   * Test DateRollConvention {@link DateRollConvention#getEnum(String)}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DateRollConvention#getEnum(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateRollConvention DateRollConvention.getEnum(String)"})
  public void testDateRollConventionGetEnum_whenString_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DateRollConvention.getEnum("String"));
  }
}
