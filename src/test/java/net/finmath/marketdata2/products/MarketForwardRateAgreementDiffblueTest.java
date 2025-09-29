package net.finmath.marketdata2.products;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata2.model.AnalyticModel;
import net.finmath.marketdata2.model.AnalyticModelFromCurvesAndVols;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MarketForwardRateAgreementDiffblueTest {
  /**
   * Test {@link MarketForwardRateAgreement#getValue(double, AnalyticModel)} with {@code double},
   * {@code AnalyticModel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link MarketForwardRateAgreement#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable MarketForwardRateAgreement.getValue(double, AnalyticModel)"
  })
  public void testGetValueWithDoubleAnalyticModel_thenThrowIllegalArgumentException() {
    // Arrange
    MarketForwardRateAgreement marketForwardRateAgreement =
        new MarketForwardRateAgreement(10.0d, 10.0d, "Forward Curve Name", 10.0d, "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> marketForwardRateAgreement.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }
}
