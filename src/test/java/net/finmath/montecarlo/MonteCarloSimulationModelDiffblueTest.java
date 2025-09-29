package net.finmath.montecarlo;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import net.finmath.montecarlo.hybridassetinterestrate.CrossCurrencyLIBORMarketModelFromModels;
import net.finmath.montecarlo.interestrate.LIBORModelMonteCarloSimulationModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MonteCarloSimulationModelDiffblueTest {
  /**
   * Test {@link MonteCarloSimulationModel#getReferenceDate()}.
   *
   * <p>Method under test: {@link MonteCarloSimulationModel#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.time.LocalDateTime MonteCarloSimulationModel.getReferenceDate()"})
  public void testGetReferenceDate() {
    // Arrange
    HashMap<String, LIBORModelMonteCarloSimulationModel> interestRatesModels = new HashMap<>();
    CrossCurrencyLIBORMarketModelFromModels crossCurrencyLIBORMarketModelFromModels =
        new CrossCurrencyLIBORMarketModelFromModels(
            "Base Model", interestRatesModels, new HashMap<>());

    // Act and Assert
    assertNull(crossCurrencyLIBORMarketModelFromModels.getReferenceDate());
  }
}
