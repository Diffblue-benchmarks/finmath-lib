package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.interestrate.models.funding.FundingCapacity;
import net.finmath.montecarlo.interestrate.products.indices.AbstractIndex;
import net.finmath.montecarlo.interestrate.products.indices.FixedCoupon;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SwapLegWithFundingProviderDiffblueTest {
  /**
   * Test {@link SwapLegWithFundingProvider#SwapLegWithFundingProvider(Schedule, double[],
   * AbstractIndex, double[], FundingCapacity)}.
   *
   * <ul>
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLegWithFundingProvider#SwapLegWithFundingProvider(Schedule,
   * double[], AbstractIndex, double[], FundingCapacity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLegWithFundingProvider.<init>(Schedule, double[], AbstractIndex, double[], FundingCapacity)"
  })
  public void testNewSwapLegWithFundingProvider_thenReturnCurrencyIsNull() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 4, 0.5d));

    // Act
    SwapLegWithFundingProvider actualSwapLegWithFundingProvider =
        new SwapLegWithFundingProvider(
            legSchedule,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new FixedCoupon(10.0d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            mock(FundingCapacity.class));

    // Assert
    assertNull(actualSwapLegWithFundingProvider.getCurrency());
  }

  /**
   * Test {@link SwapLegWithFundingProvider#SwapLegWithFundingProvider(Schedule, double[],
   * AbstractIndex, double[], FundingCapacity)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLegWithFundingProvider#SwapLegWithFundingProvider(Schedule,
   * double[], AbstractIndex, double[], FundingCapacity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLegWithFundingProvider.<init>(Schedule, double[], AbstractIndex, double[], FundingCapacity)"
  })
  public void testNewSwapLegWithFundingProvider_thenThrowIllegalArgumentException() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new SwapLegWithFundingProvider(
                legSchedule,
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new FixedCoupon(10.0d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                mock(FundingCapacity.class)));
  }
}
