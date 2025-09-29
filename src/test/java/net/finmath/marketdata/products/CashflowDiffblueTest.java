package net.finmath.marketdata.products;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CashflowDiffblueTest {
  /**
   * Test {@link Cashflow#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <p>Method under test: {@link Cashflow#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Cashflow.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel() {
    // Arrange
    Cashflow cashflow = new Cashflow("GBP", 10.0d, 10.0d, true, "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> cashflow.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link Cashflow#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <p>Method under test: {@link Cashflow#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Cashflow.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel2() {
    // Arrange
    Cashflow cashflow = new Cashflow("GBP", 10.0d, 10.0d, false, "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> cashflow.getValue(10.0d, (AnalyticModel) null));
  }
}
