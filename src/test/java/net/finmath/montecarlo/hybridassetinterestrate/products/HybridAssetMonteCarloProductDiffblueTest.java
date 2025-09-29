package net.finmath.montecarlo.hybridassetinterestrate.products;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.hybridassetinterestrate.CrossCurrencyLIBORMarketModelFromModels;
import net.finmath.montecarlo.hybridassetinterestrate.HybridAssetMonteCarloSimulation;
import net.finmath.montecarlo.interestrate.LIBORModelMonteCarloSimulationModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HybridAssetMonteCarloProductDiffblueTest {
  /**
   * Test {@link HybridAssetMonteCarloProduct#getValues(double, HybridAssetMonteCarloSimulation)}
   * with {@code double}, {@code HybridAssetMonteCarloSimulation}.
   *
   * <p>Method under test: {@link HybridAssetMonteCarloProduct#getValues(double,
   * HybridAssetMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.Map HybridAssetMonteCarloProduct.getValues(double, HybridAssetMonteCarloSimulation)"
  })
  public void testGetValuesWithDoubleHybridAssetMonteCarloSimulation() throws CalculationException {
    // Arrange
    Bond bond = new Bond("GBP", 10.0d);
    HashMap<String, LIBORModelMonteCarloSimulationModel> interestRatesModels = new HashMap<>();
    CrossCurrencyLIBORMarketModelFromModels model =
        new CrossCurrencyLIBORMarketModelFromModels(
            "Base Model", interestRatesModels, new HashMap<>());

    // Act and Assert
    assertNull(bond.getValues(10.0d, model));
  }
}
