package net.finmath.montecarlo.interestrate.products.indices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Set;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AnalyticModelIndexDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AnalyticModelIndex#AnalyticModelIndex(String, String, double)}
   *   <li>{@link AnalyticModelIndex#toString()}
   *   <li>{@link AnalyticModelIndex#getPeriodStartOffset()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnalyticModelIndex.<init>(String, String, double)",
    "double AnalyticModelIndex.getPeriodStartOffset()",
    "String AnalyticModelIndex.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    AnalyticModelIndex actualAnalyticModelIndex =
        new AnalyticModelIndex("Name", "Curve Name", 10.0d);
    String actualToStringResult = actualAnalyticModelIndex.toString();
    double actualPeriodStartOffset = actualAnalyticModelIndex.getPeriodStartOffset();

    // Assert
    assertEquals(
        "AnalyticModelIndex [curveName=Curve Name, fixingOffet=10.0]", actualToStringResult);
    assertEquals("Name", actualAnalyticModelIndex.getName());
    assertNull(actualAnalyticModelIndex.getCurrency());
    assertEquals(10.0d, actualPeriodStartOffset, 0.0);
  }

  /**
   * Test {@link AnalyticModelIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link AnalyticModelIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set AnalyticModelIndex.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange
    AnalyticModelIndex analyticModelIndex = new AnalyticModelIndex("Name", "Curve Name", 10.0d);

    // Act
    Set<String> actualQueryUnderlyingsResult = analyticModelIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }
}
