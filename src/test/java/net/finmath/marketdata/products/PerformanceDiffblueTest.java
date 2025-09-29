package net.finmath.marketdata.products;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class PerformanceDiffblueTest {
  /**
   * Test {@link Performance#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link Performance#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Performance.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnOne() {
    // Arrange
    AbstractAnalyticProduct productNumerator = mock(AbstractAnalyticProduct.class);
    when(productNumerator.getValue(anyDouble(), Mockito.<AnalyticModel>any())).thenReturn(10.0d);

    AbstractAnalyticProduct productDenominator = mock(AbstractAnalyticProduct.class);
    when(productDenominator.getValue(anyDouble(), Mockito.<AnalyticModel>any())).thenReturn(10.0d);

    Performance performance = new Performance(productNumerator, productDenominator);

    // Act
    double actualValue = performance.getValue(10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(productNumerator).getValue(eq(10.0d), isA(AnalyticModel.class));
    verify(productDenominator).getValue(eq(10.0d), isA(AnalyticModel.class));
    assertEquals(1.0d, actualValue, 0.0);
  }
}
