package net.finmath.time.businessdaycalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BusinessdayCalendarAnyDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link BusinessdayCalendarAny}
   *   <li>{@link BusinessdayCalendarAny#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BusinessdayCalendarAny.<init>()",
    "java.lang.String BusinessdayCalendarAny.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("BusinessdayCalendarAny", new BusinessdayCalendarAny().toString());
  }

  /**
   * Test {@link BusinessdayCalendarAny#isBusinessday(LocalDate)}.
   *
   * <p>Method under test: {@link BusinessdayCalendarAny#isBusinessday(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BusinessdayCalendarAny.isBusinessday(LocalDate)"})
  public void testIsBusinessday() {
    // Arrange, Act and Assert
    assertTrue(new BusinessdayCalendarAny().isBusinessday(LocalDate.of(1970, 1, 1)));
  }
}
