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

public class AnalyticModelForwardCurveIndexDiffblueTest {
  /**
   * Test {@link AnalyticModelForwardCurveIndex#AnalyticModelForwardCurveIndex(String, String,
   * double, double)}.
   *
   * <p>Method under test: {@link
   * AnalyticModelForwardCurveIndex#AnalyticModelForwardCurveIndex(String, String, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelForwardCurveIndex.<init>(String, String, double, double)"})
  public void testNewAnalyticModelForwardCurveIndex() {
    // Arrange and Act
    AnalyticModelForwardCurveIndex actualAnalyticModelForwardCurveIndex =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);

    // Assert
    assertEquals("Name", actualAnalyticModelForwardCurveIndex.getName());
    assertNull(actualAnalyticModelForwardCurveIndex.getCurrency());
    assertEquals(10.0d, actualAnalyticModelForwardCurveIndex.getPeriodStartOffset(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AnalyticModelForwardCurveIndex#toString()}
   *   <li>{@link AnalyticModelForwardCurveIndex#getPeriodStartOffset()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnalyticModelForwardCurveIndex.getPeriodStartOffset()",
    "String AnalyticModelForwardCurveIndex.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    AnalyticModelForwardCurveIndex analyticModelForwardCurveIndex =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);

    // Act
    String actualToStringResult = analyticModelForwardCurveIndex.toString();

    // Assert
    assertEquals(
        "AnalyticModelIndex [curveName=Curve Name, fixingOffet=10.0]", actualToStringResult);
    assertEquals(10.0d, analyticModelForwardCurveIndex.getPeriodStartOffset(), 0.0);
  }

  /**
   * Test {@link AnalyticModelForwardCurveIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link AnalyticModelForwardCurveIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set AnalyticModelForwardCurveIndex.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange
    AnalyticModelForwardCurveIndex analyticModelForwardCurveIndex =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);

    // Act
    Set<String> actualQueryUnderlyingsResult = analyticModelForwardCurveIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }
}
