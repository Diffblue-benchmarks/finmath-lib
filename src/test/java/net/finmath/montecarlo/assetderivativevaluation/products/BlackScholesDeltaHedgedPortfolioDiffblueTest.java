package net.finmath.montecarlo.assetderivativevaluation.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
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
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloVarianceGammaModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BlackScholesDeltaHedgedPortfolioDiffblueTest {
  /**
   * Test {@link BlackScholesDeltaHedgedPortfolio#BlackScholesDeltaHedgedPortfolio(double, double,
   * double, double)}.
   *
   * <p>Method under test: {@link
   * BlackScholesDeltaHedgedPortfolio#BlackScholesDeltaHedgedPortfolio(double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlackScholesDeltaHedgedPortfolio.<init>(double, double, double, double)"
  })
  public void testNewBlackScholesDeltaHedgedPortfolio() {
    // Arrange and Act
    BlackScholesDeltaHedgedPortfolio actualBlackScholesDeltaHedgedPortfolio =
        new BlackScholesDeltaHedgedPortfolio(10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertNull(actualBlackScholesDeltaHedgedPortfolio.getCurrency());
  }

  /**
   * Test {@link BlackScholesDeltaHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BlackScholesDeltaHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlackScholesDeltaHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    BlackScholesDeltaHedgedPortfolio blackScholesDeltaHedgedPortfolio =
        new BlackScholesDeltaHedgedPortfolio(10.0d, 10.0d, 10.0d, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualValue =
        blackScholesDeltaHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.size());
    assertEquals(2.6881171418161355E44d, actualValue.getAverage(), 0.0);
    assertEquals(2.6881171418161355E44d, actualValue.getMax(), 0.0);
    assertEquals(2.6881171418161355E44d, actualValue.getMin(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {2.6881171418161355E44d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BlackScholesDeltaHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesDeltaHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlackScholesDeltaHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel_thenReturnSizeIsTen()
      throws CalculationException {
    // Arrange
    BlackScholesDeltaHedgedPortfolio blackScholesDeltaHedgedPortfolio =
        new BlackScholesDeltaHedgedPortfolio(10.0d, 10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    MonteCarloVarianceGammaModel model =
        new MonteCarloVarianceGammaModel(
            timeDiscretization, 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualValue = blackScholesDeltaHedgedPortfolio.getValue(10.0d, model);

    // Assert
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualValue.size());
    assertEquals(10.0d, actualValue.getFiltrationTime(), 0.0);
    assertFalse(actualValue.isDeterministic());
    assertEquals(Double.NaN, actualValue.getAverage(), 0.0);
    assertEquals(Double.NaN, actualValue.getMax(), 0.0);
    assertEquals(Double.NaN, actualValue.getMin(), 0.0);
    assertEquals(Double.NaN, actualValue.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualValue.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualValue.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualValue.getVariance(), 0.0);
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        actualValue.getRealizations(),
        0.0);
  }
}
