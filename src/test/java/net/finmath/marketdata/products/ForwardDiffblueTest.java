package net.finmath.marketdata.products;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ForwardDiffblueTest {
  /**
   * Test {@link Forward#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Forward#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Forward.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenThrowIllegalArgumentException() {
    // Arrange
    Forward forward = new Forward(10.0d, 10.0d, "Forward Curve Name", 10.0d, "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> forward.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }
}
