package net.finmath.montecarlo.assetderivativevaluation.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
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
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloBlackScholesModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloMertonModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BlackScholesModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EuropeanOptionDeltaPathwiseDiffblueTest {
  /**
   * Test {@link EuropeanOptionDeltaPathwise#EuropeanOptionDeltaPathwise(double, double)}.
   *
   * <p>Method under test: {@link EuropeanOptionDeltaPathwise#EuropeanOptionDeltaPathwise(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EuropeanOptionDeltaPathwise.<init>(double, double)"})
  public void testNewEuropeanOptionDeltaPathwise() {
    // Arrange, Act and Assert
    assertNull(new EuropeanOptionDeltaPathwise(10.0d, 10.0d).getCurrency());
  }

  /**
   * Test {@link EuropeanOptionDeltaPathwise#getValue(double, AssetModelMonteCarloSimulationModel)}
   * with {@code double}, {@code AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link EuropeanOptionDeltaPathwise#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable EuropeanOptionDeltaPathwise.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    EuropeanOptionDeltaPathwise europeanOptionDeltaPathwise =
        new EuropeanOptionDeltaPathwise(10.0d, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualValue =
        europeanOptionDeltaPathwise.getValue(10.0d, new MonteCarloAssetModel(process));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.9999999999999998d, actualValue.getAverage(), 0.0);
    assertEquals(0.9999999999999998d, actualValue.getMax(), 0.0);
    assertEquals(0.9999999999999998d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {0.9999999999999998d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link EuropeanOptionDeltaPathwise#getValue(double, AssetModelMonteCarloSimulationModel)}
   * with {@code double}, {@code AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link EuropeanOptionDeltaPathwise#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable EuropeanOptionDeltaPathwise.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    EuropeanOptionDeltaPathwise europeanOptionDeltaPathwise =
        new EuropeanOptionDeltaPathwise(10.0d, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BlackScholesModel(1.0d, 1.0d, 1.0d), stochasticDriver);

    // Act
    RandomVariable actualValue =
        europeanOptionDeltaPathwise.getValue(10.0d, new MonteCarloAssetModel(process));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualValue.getAverage(), 0.0);
    assertEquals(0.0d, actualValue.getMax(), 0.0);
    assertEquals(0.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link EuropeanOptionDeltaPathwise#getValue(double, AssetModelMonteCarloSimulationModel)}
   * with {@code double}, {@code AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link EuropeanOptionDeltaPathwise#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable EuropeanOptionDeltaPathwise.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    EuropeanOptionDeltaPathwise europeanOptionDeltaPathwise =
        new EuropeanOptionDeltaPathwise(10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    // Act
    RandomVariable actualValue =
        europeanOptionDeltaPathwise.getValue(
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
    RandomVariable actualExpectationResult = actualValue.expectation();
    assertSame(actualValue, actualExpectationResult);
  }

  /**
   * Test {@link EuropeanOptionDeltaPathwise#getValue(double, AssetModelMonteCarloSimulationModel)}
   * with {@code double}, {@code AssetModelMonteCarloSimulationModel}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOptionDeltaPathwise#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable EuropeanOptionDeltaPathwise.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel_thenReturnScalar()
      throws CalculationException {
    // Arrange
    EuropeanOptionDeltaPathwise europeanOptionDeltaPathwise =
        new EuropeanOptionDeltaPathwise(10.0d, 10.0d);
    MonteCarloMertonModel model =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualValue = europeanOptionDeltaPathwise.getValue(10.0d, model);

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
    RandomVariable actualExpectationResult = actualValue.expectation();
    assertSame(actualValue, actualExpectationResult);
  }
}
