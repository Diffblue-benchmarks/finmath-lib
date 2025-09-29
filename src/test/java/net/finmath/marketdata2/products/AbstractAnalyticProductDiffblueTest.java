package net.finmath.marketdata2.products;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.modelling.Model;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractAnalyticProductDiffblueTest {
  /**
   * Test {@link AbstractAnalyticProduct#getValue(double, Model)} with {@code evaluationTime},
   * {@code model}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAnalyticProduct#getValue(double, Model)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object AbstractAnalyticProduct.getValue(double, Model)"})
  public void testGetValueWithEvaluationTimeModel_thenThrowIllegalArgumentException() {
    // Arrange
    Cashflow cashflow = new Cashflow("GBP", 10.0d, 10.0d, true, "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> cashflow.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }
}
