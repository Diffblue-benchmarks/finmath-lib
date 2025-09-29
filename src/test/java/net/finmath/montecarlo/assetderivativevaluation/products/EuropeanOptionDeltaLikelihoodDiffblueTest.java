package net.finmath.montecarlo.assetderivativevaluation.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloBlackScholesModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EuropeanOptionDeltaLikelihoodDiffblueTest {
  /**
   * Test {@link EuropeanOptionDeltaLikelihood#EuropeanOptionDeltaLikelihood(double, double)}.
   *
   * <p>Method under test: {@link
   * EuropeanOptionDeltaLikelihood#EuropeanOptionDeltaLikelihood(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EuropeanOptionDeltaLikelihood.<init>(double, double)"})
  public void testNewEuropeanOptionDeltaLikelihood() {
    // Arrange, Act and Assert
    assertNull(new EuropeanOptionDeltaLikelihood(10.0d, 10.0d).getCurrency());
  }

  /**
   * Test {@link EuropeanOptionDeltaLikelihood#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOptionDeltaLikelihood#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable EuropeanOptionDeltaLikelihood.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel_thenReturnScalar()
      throws CalculationException {
    // Arrange
    EuropeanOptionDeltaLikelihood europeanOptionDeltaLikelihood =
        new EuropeanOptionDeltaLikelihood(10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    // Act
    RandomVariable actualValue =
        europeanOptionDeltaLikelihood.getValue(
            10.0d,
            new MonteCarloBlackScholesModel(
                10.0d, 10.0d, 10.0d, new BrownianMotionWithControlVariate(brownianMotion)));

    // Assert
    assertTrue(actualValue instanceof Scalar);
    assertTrue(actualValue.abs() instanceof Scalar);
    assertTrue(actualValue.cos() instanceof Scalar);
    assertTrue(actualValue.exp() instanceof Scalar);
    assertTrue(actualValue.expm1() instanceof Scalar);
    assertTrue(actualValue.invert() instanceof Scalar);
    assertTrue(actualValue.isNaN() instanceof Scalar);
    assertTrue(actualValue.sin() instanceof Scalar);
    assertTrue(actualValue.sqrt() instanceof Scalar);
    assertTrue(actualValue.squared() instanceof Scalar);
    assertTrue(actualValue.variance() instanceof Scalar);
    assertNull(actualValue.getRealizations());
    assertNull(actualValue.getOperator());
    assertNull(actualValue.getRealizationsStream());
    assertEquals(0, actualValue.getTypePriority());
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.size());
    assertEquals(7.105427945506698E-17d, actualValue.getAverage(), 0.0);
    assertEquals(7.105427945506698E-17d, actualValue.getMax(), 0.0);
    assertEquals(7.105427945506698E-17d, actualValue.getMin(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualValue.expectation();
    assertSame(actualValue, actualExpectationResult);
  }
}
