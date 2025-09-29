package net.finmath.montecarlo.assetderivativevaluation.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DeltaHedgedPortfolioWithAADDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link AssetMonteCarloProduct}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DeltaHedgedPortfolioWithAAD#DeltaHedgedPortfolioWithAAD(AssetMonteCarloProduct)}
   *   <li>{@link DeltaHedgedPortfolioWithAAD#getLastOperationTimingDerivative()}
   *   <li>{@link DeltaHedgedPortfolioWithAAD#getLastOperationTimingValuation()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DeltaHedgedPortfolioWithAAD.<init>(AssetMonteCarloProduct)",
    "void DeltaHedgedPortfolioWithAAD.<init>(AssetMonteCarloProduct, int)",
    "double DeltaHedgedPortfolioWithAAD.getLastOperationTimingDerivative()",
    "double DeltaHedgedPortfolioWithAAD.getLastOperationTimingValuation()"
  })
  public void testGettersAndSetters_whenAssetMonteCarloProduct() {
    // Arrange and Act
    DeltaHedgedPortfolioWithAAD actualDeltaHedgedPortfolioWithAAD =
        new DeltaHedgedPortfolioWithAAD(mock(AssetMonteCarloProduct.class));
    double actualLastOperationTimingDerivative =
        actualDeltaHedgedPortfolioWithAAD.getLastOperationTimingDerivative();
    double actualLastOperationTimingValuation =
        actualDeltaHedgedPortfolioWithAAD.getLastOperationTimingValuation();

    // Assert
    assertNull(actualDeltaHedgedPortfolioWithAAD.getCurrency());
    assertEquals(Double.NaN, actualLastOperationTimingDerivative, 0.0);
    assertEquals(Double.NaN, actualLastOperationTimingValuation, 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When ten.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DeltaHedgedPortfolioWithAAD#DeltaHedgedPortfolioWithAAD(AssetMonteCarloProduct,
   *       int)}
   *   <li>{@link DeltaHedgedPortfolioWithAAD#getLastOperationTimingDerivative()}
   *   <li>{@link DeltaHedgedPortfolioWithAAD#getLastOperationTimingValuation()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DeltaHedgedPortfolioWithAAD.<init>(AssetMonteCarloProduct)",
    "void DeltaHedgedPortfolioWithAAD.<init>(AssetMonteCarloProduct, int)",
    "double DeltaHedgedPortfolioWithAAD.getLastOperationTimingDerivative()",
    "double DeltaHedgedPortfolioWithAAD.getLastOperationTimingValuation()"
  })
  public void testGettersAndSetters_whenTen() {
    // Arrange and Act
    DeltaHedgedPortfolioWithAAD actualDeltaHedgedPortfolioWithAAD =
        new DeltaHedgedPortfolioWithAAD(mock(AssetMonteCarloProduct.class), 10);
    double actualLastOperationTimingDerivative =
        actualDeltaHedgedPortfolioWithAAD.getLastOperationTimingDerivative();
    double actualLastOperationTimingValuation =
        actualDeltaHedgedPortfolioWithAAD.getLastOperationTimingValuation();

    // Assert
    assertNull(actualDeltaHedgedPortfolioWithAAD.getCurrency());
    assertEquals(Double.NaN, actualLastOperationTimingDerivative, 0.0);
    assertEquals(Double.NaN, actualLastOperationTimingValuation, 0.0);
  }

  /**
   * Test {@link DeltaHedgedPortfolioWithAAD#getValue(double, AssetModelMonteCarloSimulationModel)}
   * with {@code double}, {@code AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link DeltaHedgedPortfolioWithAAD#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable DeltaHedgedPortfolioWithAAD.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    AssetMonteCarloProduct productToReplicate = mock(AssetMonteCarloProduct.class);
    when(productToReplicate.getValue(
            anyDouble(), Mockito.<AssetModelMonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    DeltaHedgedPortfolioWithAAD deltaHedgedPortfolioWithAAD =
        new DeltaHedgedPortfolioWithAAD(productToReplicate, 10);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> deltaHedgedPortfolioWithAAD.getValue(10.0d, new MonteCarloAssetModel(process)));
    verify(brownianMotion).getTimeDiscretization();
    verify(productToReplicate).getValue(eq(10.0d), isA(AssetModelMonteCarloSimulationModel.class));
  }

  /**
   * Test {@link DeltaHedgedPortfolioWithAAD#getValue(double, AssetModelMonteCarloSimulationModel)}
   * with {@code double}, {@code AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link DeltaHedgedPortfolioWithAAD#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable DeltaHedgedPortfolioWithAAD.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    AssetMonteCarloProduct productToReplicate = mock(AssetMonteCarloProduct.class);
    when(productToReplicate.getValue(
            anyDouble(), Mockito.<AssetModelMonteCarloSimulationModel>any()))
        .thenThrow(new IllegalArgumentException());
    DeltaHedgedPortfolioWithAAD deltaHedgedPortfolioWithAAD =
        new DeltaHedgedPortfolioWithAAD(productToReplicate, 10);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> deltaHedgedPortfolioWithAAD.getValue(10.0d, new MonteCarloAssetModel(process)));
    verify(brownianMotion).getTimeDiscretization();
    verify(productToReplicate).getValue(eq(10.0d), isA(AssetModelMonteCarloSimulationModel.class));
  }
}
