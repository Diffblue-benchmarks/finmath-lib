package net.finmath.montecarlo.assetderivativevaluation.products;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EuropeanOptionGammaLikelihoodDiffblueTest {
  /**
   * Test {@link EuropeanOptionGammaLikelihood#EuropeanOptionGammaLikelihood(double, double)}.
   *
   * <p>Method under test: {@link
   * EuropeanOptionGammaLikelihood#EuropeanOptionGammaLikelihood(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EuropeanOptionGammaLikelihood.<init>(double, double)"})
  public void testNewEuropeanOptionGammaLikelihood() {
    // Arrange, Act and Assert
    assertNull(new EuropeanOptionGammaLikelihood(10.0d, 10.0d).getCurrency());
  }

  /**
   * Test {@link EuropeanOptionGammaLikelihood#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link EuropeanOptionGammaLikelihood#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariableAccumulator EuropeanOptionGammaLikelihood.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel() {
    // Arrange
    EuropeanOptionGammaLikelihood europeanOptionGammaLikelihood =
        new EuropeanOptionGammaLikelihood(10.0d, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> europeanOptionGammaLikelihood.getValue(10.0d, new MonteCarloAssetModel(process)));
    verify(brownianMotion).getTimeDiscretization();
  }
}
