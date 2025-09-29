package net.finmath.marketdata.products;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.time.RegularSchedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CapDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Cap#toString()}
   *   <li>{@link Cap#getDiscountCurveName()}
   *   <li>{@link Cap#getForwardCurveName()}
   *   <li>{@link Cap#getStrike()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String Cap.getDiscountCurveName()",
    "String Cap.getForwardCurveName()",
    "double Cap.getStrike()",
    "String Cap.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Cap cap =
        new Cap(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            10.0d,
            true,
            "3",
            "Doe");

    // Act
    cap.toString();
    String actualDiscountCurveName = cap.getDiscountCurveName();
    String actualForwardCurveName = cap.getForwardCurveName();

    // Assert
    assertEquals("3", actualDiscountCurveName);
    assertEquals("Forward Curve Name", actualForwardCurveName);
    assertEquals(10.0d, cap.getStrike(), 0.0);
  }
}
