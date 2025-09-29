package net.finmath.montecarlo.assetderivativevaluation.products;

import static org.junit.Assert.assertArrayEquals;
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
import java.util.ArrayList;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.RandomVariableLazyEvaluation;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BlackScholesModel;
import net.finmath.montecarlo.assetderivativevaluation.products.FiniteDifferenceHedgedPortfolio.HedgeStrategy;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FiniteDifferenceHedgedPortfolioDiffblueTest {
  /**
   * Test {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FiniteDifferenceHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.doubleValue()).thenReturn(10.0d);
    when(volatility.isDeterministic()).thenReturn(true);
    when(volatility.getFiltrationTime()).thenReturn(10.0d);
    when(volatility.getTypePriority()).thenReturn(1);
    when(volatility.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableLazyEvaluation riskFreeRate = new RandomVariableLazyEvaluation(1.0d, 10, 1.0d);

    BlackScholesModel model =
        new BlackScholesModel(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.sub(anyDouble())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD3.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD3);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    MonteCarloAssetModel modelUsedForHedging = new MonteCarloAssetModel(process);
    DigitalOption productToHedge = new DigitalOption(10.0d, 10.0d);

    FiniteDifferenceHedgedPortfolio finiteDifferenceHedgedPortfolio =
        new FiniteDifferenceHedgedPortfolio(
            productToHedge, modelUsedForHedging, new ArrayList<>(), HedgeStrategy.deltaHedge);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion2.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfPaths()).thenReturn(10);
    TenorFromArray tenorFromArray2 = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion2.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    RandomVariable actualValue =
        finiteDifferenceHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion2, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion2, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion2, atLeast(1)).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion2).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(brownianMotion2, atLeast(1)).getTimeDiscretization();
    verify(volatility).doubleValue();
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD3, atLeast(1)).getAverage();
    verify(volatility).getFiltrationTime();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD3, atLeast(1)).getStandardDeviation();
    verify(volatility).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(volatility).isDeterministic();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD2, atLeast(1)).mult(0.31622776601683794d);
    verify(volatility).squared();
    verify(randomVariableAAD3, atLeast(1)).sub(10.0d);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FiniteDifferenceHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.doubleValue()).thenReturn(10.0d);
    when(volatility.isDeterministic()).thenReturn(true);
    when(volatility.getFiltrationTime()).thenReturn(10.0d);
    when(volatility.getTypePriority()).thenReturn(1);
    when(volatility.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    Scalar riskFreeRate = Scalar.of(1.0d);

    BlackScholesModel model =
        new BlackScholesModel(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.sub(anyDouble())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD3.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD3);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    MonteCarloAssetModel modelUsedForHedging = new MonteCarloAssetModel(process);
    DigitalOption productToHedge = new DigitalOption(10.0d, 10.0d);

    FiniteDifferenceHedgedPortfolio finiteDifferenceHedgedPortfolio =
        new FiniteDifferenceHedgedPortfolio(
            productToHedge, modelUsedForHedging, new ArrayList<>(), HedgeStrategy.deltaHedge);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion2.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfPaths()).thenReturn(10);
    TenorFromArray tenorFromArray2 = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion2.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    RandomVariable actualValue =
        finiteDifferenceHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion2, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion2, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion2, atLeast(1)).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion2).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(brownianMotion2, atLeast(1)).getTimeDiscretization();
    verify(volatility).doubleValue();
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD3, atLeast(1)).getAverage();
    verify(volatility).getFiltrationTime();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD3, atLeast(1)).getStandardDeviation();
    verify(volatility).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(volatility).isDeterministic();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD2, atLeast(1)).mult(0.31622776601683794d);
    verify(volatility).squared();
    verify(randomVariableAAD3, atLeast(1)).sub(10.0d);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FiniteDifferenceHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.doubleValue()).thenReturn(10.0d);
    when(volatility.isDeterministic()).thenReturn(true);
    when(volatility.getFiltrationTime()).thenReturn(10.0d);
    when(volatility.getTypePriority()).thenReturn(1);
    when(volatility.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(1.0d);

    BlackScholesModel model =
        new BlackScholesModel(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.sub(anyDouble())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD3.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD3);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    MonteCarloAssetModel modelUsedForHedging = new MonteCarloAssetModel(process);
    DigitalOption productToHedge = new DigitalOption(10.0d, 10.0d);

    FiniteDifferenceHedgedPortfolio finiteDifferenceHedgedPortfolio =
        new FiniteDifferenceHedgedPortfolio(
            productToHedge, modelUsedForHedging, new ArrayList<>(), HedgeStrategy.deltaHedge);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion2.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfPaths()).thenReturn(10);
    TenorFromArray tenorFromArray2 = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion2.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    RandomVariable actualValue =
        finiteDifferenceHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion2, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion2, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion2, atLeast(1)).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion2).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(brownianMotion2, atLeast(1)).getTimeDiscretization();
    verify(volatility).doubleValue();
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD3, atLeast(1)).getAverage();
    verify(volatility).getFiltrationTime();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD3, atLeast(1)).getStandardDeviation();
    verify(volatility).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(volatility).isDeterministic();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD2, atLeast(1)).mult(0.31622776601683794d);
    verify(volatility).squared();
    verify(randomVariableAAD3, atLeast(1)).sub(10.0d);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FiniteDifferenceHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel4()
      throws CalculationException {
    // Arrange
    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.doubleValue()).thenReturn(10.0d);
    when(volatility.isDeterministic()).thenReturn(true);
    when(volatility.getFiltrationTime()).thenReturn(10.0d);
    when(volatility.getTypePriority()).thenReturn(1);
    when(volatility.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromFloatArray riskFreeRate = new RandomVariableFromFloatArray(1.0d);

    BlackScholesModel model =
        new BlackScholesModel(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.sub(anyDouble())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD3.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD3);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    MonteCarloAssetModel modelUsedForHedging = new MonteCarloAssetModel(process);
    DigitalOption productToHedge = new DigitalOption(10.0d, 10.0d);

    FiniteDifferenceHedgedPortfolio finiteDifferenceHedgedPortfolio =
        new FiniteDifferenceHedgedPortfolio(
            productToHedge, modelUsedForHedging, new ArrayList<>(), HedgeStrategy.deltaHedge);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion2.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfPaths()).thenReturn(10);
    TenorFromArray tenorFromArray2 = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion2.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    RandomVariable actualValue =
        finiteDifferenceHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion2, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion2, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion2, atLeast(1)).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion2).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(brownianMotion2, atLeast(1)).getTimeDiscretization();
    verify(volatility).doubleValue();
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD3, atLeast(1)).getAverage();
    verify(volatility).getFiltrationTime();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD3, atLeast(1)).getStandardDeviation();
    verify(volatility).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(volatility).isDeterministic();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD2, atLeast(1)).mult(0.31622776601683794d);
    verify(volatility).squared();
    verify(randomVariableAAD3, atLeast(1)).sub(10.0d);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FiniteDifferenceHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel5()
      throws CalculationException {
    // Arrange
    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.doubleValue()).thenReturn(10.0d);
    when(volatility.isDeterministic()).thenReturn(true);
    when(volatility.getFiltrationTime()).thenReturn(10.0d);
    when(volatility.getTypePriority()).thenReturn(1);
    when(volatility.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADPathwise riskFreeRate =
        RandomVariableDifferentiableAADPathwise.of(1.0d);

    BlackScholesModel model =
        new BlackScholesModel(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.sub(anyDouble())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD3.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD3);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    MonteCarloAssetModel modelUsedForHedging = new MonteCarloAssetModel(process);
    DigitalOption productToHedge = new DigitalOption(10.0d, 10.0d);

    FiniteDifferenceHedgedPortfolio finiteDifferenceHedgedPortfolio =
        new FiniteDifferenceHedgedPortfolio(
            productToHedge, modelUsedForHedging, new ArrayList<>(), HedgeStrategy.deltaHedge);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion2.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfPaths()).thenReturn(10);
    TenorFromArray tenorFromArray2 = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion2.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    RandomVariable actualValue =
        finiteDifferenceHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion2, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion2, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion2, atLeast(1)).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion2).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(brownianMotion2, atLeast(1)).getTimeDiscretization();
    verify(volatility).doubleValue();
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD3, atLeast(1)).getAverage();
    verify(volatility).getFiltrationTime();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD3, atLeast(1)).getStandardDeviation();
    verify(volatility).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(volatility).isDeterministic();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD2, atLeast(1)).mult(0.31622776601683794d);
    verify(volatility).squared();
    verify(randomVariableAAD3, atLeast(1)).sub(10.0d);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FiniteDifferenceHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel6()
      throws CalculationException {
    // Arrange
    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.getValues()).thenReturn(Scalar.of(1.0d));
    when(volatility.getTypePriority()).thenReturn(1);
    when(volatility.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD riskFreeRate = RandomVariableDifferentiableAAD.of(1.0d);

    BlackScholesModel model =
        new BlackScholesModel(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.sub(anyDouble())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD3.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD3);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    MonteCarloAssetModel modelUsedForHedging = new MonteCarloAssetModel(process);
    DigitalOption productToHedge = new DigitalOption(10.0d, 10.0d);

    FiniteDifferenceHedgedPortfolio finiteDifferenceHedgedPortfolio =
        new FiniteDifferenceHedgedPortfolio(
            productToHedge, modelUsedForHedging, new ArrayList<>(), HedgeStrategy.deltaHedge);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion2.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfPaths()).thenReturn(10);
    TenorFromArray tenorFromArray2 = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion2.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    RandomVariable actualValue =
        finiteDifferenceHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion2, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion2, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion2, atLeast(1)).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion2).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(brownianMotion2, atLeast(1)).getTimeDiscretization();
    verify(randomVariableAAD3, atLeast(1)).getAverage();
    verify(randomVariableAAD3, atLeast(1)).getStandardDeviation();
    verify(volatility).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD2, atLeast(1)).mult(0.31622776601683794d);
    verify(volatility).squared();
    verify(randomVariableAAD3, atLeast(1)).sub(10.0d);
    verify(volatility, atLeast(1)).getValues();
    verify(randomVariableAAD, atLeast(1)).getValues();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FiniteDifferenceHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel7()
      throws CalculationException {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.getValues()).thenReturn(randomVariableAAD);
    when(volatility.getTypePriority()).thenReturn(1);
    when(volatility.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD riskFreeRate = RandomVariableDifferentiableAAD.of(1.0d);

    BlackScholesModel model =
        new BlackScholesModel(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.sub(anyDouble())).thenReturn(randomVariableAAD3);
    when(randomVariableAAD4.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD4.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD4);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    MonteCarloAssetModel modelUsedForHedging = new MonteCarloAssetModel(process);
    BlackScholesDeltaHedgedPortfolio productToHedge =
        new BlackScholesDeltaHedgedPortfolio(10.0d, 10.0d, 10.0d, 10.0d);

    FiniteDifferenceHedgedPortfolio finiteDifferenceHedgedPortfolio =
        new FiniteDifferenceHedgedPortfolio(
            productToHedge, modelUsedForHedging, new ArrayList<>(), HedgeStrategy.deltaHedge);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion2.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfPaths()).thenReturn(10);
    TenorFromArray tenorFromArray2 = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion2.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    RandomVariable actualValue =
        finiteDifferenceHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion2, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion2, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion2, atLeast(1)).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion2).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(brownianMotion2, atLeast(1)).getTimeDiscretization();
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD4, atLeast(1)).getAverage();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD4, atLeast(1)).getStandardDeviation();
    verify(volatility).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD3, atLeast(1)).mult(0.31622776601683794d);
    verify(volatility).squared();
    verify(randomVariableAAD4, atLeast(1)).sub(10.0d);
    verify(volatility, atLeast(1)).getValues();
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FiniteDifferenceHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel8()
      throws CalculationException {
    // Arrange
    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.doubleValue()).thenReturn(10.0d);
    when(volatility.isDeterministic()).thenReturn(true);
    when(volatility.getFiltrationTime()).thenReturn(10.0d);
    when(volatility.getTypePriority()).thenReturn(1);
    when(volatility.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableLazyEvaluation riskFreeRate = new RandomVariableLazyEvaluation(1.0d, 10, 1.0d);

    BlackScholesModel model =
        new BlackScholesModel(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.sub(anyDouble())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD3.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD3);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    MonteCarloAssetModel modelUsedForHedging = new MonteCarloAssetModel(process);
    BlackScholesDeltaHedgedPortfolio productToHedge =
        new BlackScholesDeltaHedgedPortfolio(10.0d, 10.0d, 10.0d, 10.0d);

    FiniteDifferenceHedgedPortfolio finiteDifferenceHedgedPortfolio =
        new FiniteDifferenceHedgedPortfolio(
            productToHedge, modelUsedForHedging, new ArrayList<>(), HedgeStrategy.deltaHedge);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion2.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfPaths()).thenReturn(10);
    TenorFromArray tenorFromArray2 = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion2.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    RandomVariable actualValue =
        finiteDifferenceHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion2, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion2, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion2, atLeast(1)).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion2).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(brownianMotion2, atLeast(1)).getTimeDiscretization();
    verify(volatility).doubleValue();
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD3, atLeast(1)).getAverage();
    verify(volatility).getFiltrationTime();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD3, atLeast(1)).getStandardDeviation();
    verify(volatility).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(volatility).isDeterministic();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD2, atLeast(1)).mult(0.31622776601683794d);
    verify(volatility).squared();
    verify(randomVariableAAD3, atLeast(1)).sub(10.0d);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FiniteDifferenceHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel9()
      throws CalculationException {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.getValues()).thenReturn(randomVariableAAD);
    when(volatility.getTypePriority()).thenReturn(1);
    when(volatility.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(1.0d);
    RandomVariableDifferentiableAAD riskFreeRate =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());

    BlackScholesModel model =
        new BlackScholesModel(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(anyDouble())).thenReturn(randomVariableAAD2);

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.sub(anyDouble())).thenReturn(randomVariableAAD3);
    when(randomVariableAAD4.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD4.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD4);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    MonteCarloAssetModel modelUsedForHedging = new MonteCarloAssetModel(process);
    BlackScholesDeltaHedgedPortfolio productToHedge =
        new BlackScholesDeltaHedgedPortfolio(10.0d, 10.0d, 10.0d, 10.0d);

    FiniteDifferenceHedgedPortfolio finiteDifferenceHedgedPortfolio =
        new FiniteDifferenceHedgedPortfolio(
            productToHedge, modelUsedForHedging, new ArrayList<>(), HedgeStrategy.deltaHedge);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion2.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfPaths()).thenReturn(10);
    TenorFromArray tenorFromArray2 = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion2.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    RandomVariable actualValue =
        finiteDifferenceHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion2, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion2, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion2, atLeast(1)).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion2).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(brownianMotion2, atLeast(1)).getTimeDiscretization();
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD4, atLeast(1)).getAverage();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD4, atLeast(1)).getStandardDeviation();
    verify(volatility).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD2).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD3, atLeast(1)).mult(0.31622776601683794d);
    verify(volatility).squared();
    verify(randomVariableAAD4, atLeast(1)).sub(10.0d);
    verify(volatility, atLeast(1)).getValues();
    verify(randomVariableAAD2, atLeast(1)).getValues();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FiniteDifferenceHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel10()
      throws CalculationException {
    // Arrange
    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.doubleValue()).thenReturn(10.0d);
    when(volatility.isDeterministic()).thenReturn(true);
    when(volatility.getFiltrationTime()).thenReturn(10.0d);
    when(volatility.getTypePriority()).thenReturn(1);
    when(volatility.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADPathwise riskFreeRate =
        RandomVariableDifferentiableAADPathwise.of(1.0d);

    BlackScholesModel model =
        new BlackScholesModel(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.sub(anyDouble())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD3.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD3);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    MonteCarloAssetModel modelUsedForHedging = new MonteCarloAssetModel(process);
    BlackScholesDeltaHedgedPortfolio productToHedge =
        new BlackScholesDeltaHedgedPortfolio(10.0d, 10.0d, 10.0d, 10.0d);

    FiniteDifferenceHedgedPortfolio finiteDifferenceHedgedPortfolio =
        new FiniteDifferenceHedgedPortfolio(
            productToHedge, modelUsedForHedging, new ArrayList<>(), HedgeStrategy.deltaHedge);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion2.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfPaths()).thenReturn(10);
    TenorFromArray tenorFromArray2 = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion2.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    RandomVariable actualValue =
        finiteDifferenceHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion2, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion2, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion2, atLeast(1)).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion2).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(brownianMotion2, atLeast(1)).getTimeDiscretization();
    verify(volatility).doubleValue();
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD3, atLeast(1)).getAverage();
    verify(volatility).getFiltrationTime();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD3, atLeast(1)).getStandardDeviation();
    verify(volatility).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(volatility).isDeterministic();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD2, atLeast(1)).mult(0.31622776601683794d);
    verify(volatility).squared();
    verify(randomVariableAAD3, atLeast(1)).sub(10.0d);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FiniteDifferenceHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel11()
      throws CalculationException {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.sub(anyDouble())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD3.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD3);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(1.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(1.0d);

    BachelierModel model =
        new BachelierModel(
            randomVariableFactory,
            initialValue,
            riskFreeRate,
            new RandomVariableFromDoubleArray(1.0d));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    MonteCarloAssetModel modelUsedForHedging = new MonteCarloAssetModel(process);
    BlackScholesDeltaHedgedPortfolio productToHedge =
        new BlackScholesDeltaHedgedPortfolio(10.0d, 10.0d, 10.0d, 10.0d);

    FiniteDifferenceHedgedPortfolio finiteDifferenceHedgedPortfolio =
        new FiniteDifferenceHedgedPortfolio(
            productToHedge, modelUsedForHedging, new ArrayList<>(), HedgeStrategy.deltaHedge);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion2.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfPaths()).thenReturn(10);
    TenorFromArray tenorFromArray2 = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion2.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    RandomVariable actualValue =
        finiteDifferenceHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion2, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion2, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion2, atLeast(1)).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion2).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(brownianMotion2, atLeast(1)).getTimeDiscretization();
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD3, atLeast(1)).getAverage();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD3, atLeast(1)).getStandardDeviation();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD2, atLeast(1)).mult(0.31622776601683794d);
    verify(randomVariableAAD3, atLeast(1)).sub(10.0d);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FiniteDifferenceHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel12()
      throws CalculationException {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.sub(anyDouble())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD3.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD3);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(1.0d, 1.0d, 1.0d), stochasticDriver);
    MonteCarloAssetModel modelUsedForHedging = new MonteCarloAssetModel(process);
    BlackScholesDeltaHedgedPortfolio productToHedge =
        new BlackScholesDeltaHedgedPortfolio(10.0d, 10.0d, 10.0d, 10.0d);

    FiniteDifferenceHedgedPortfolio finiteDifferenceHedgedPortfolio =
        new FiniteDifferenceHedgedPortfolio(
            productToHedge, modelUsedForHedging, new ArrayList<>(), HedgeStrategy.deltaHedge);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion2.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfPaths()).thenReturn(10);
    TenorFromArray tenorFromArray2 = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion2.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    RandomVariable actualValue =
        finiteDifferenceHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion2, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion2, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion2, atLeast(1)).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion2).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(brownianMotion2, atLeast(1)).getTimeDiscretization();
    verify(randomVariableAAD3, atLeast(1)).getAverage();
    verify(randomVariableAAD3, atLeast(1)).getStandardDeviation();
    verify(randomVariableAAD2, atLeast(1)).mult(0.31622776601683794d);
    verify(randomVariableAAD).mult(1.0d);
    verify(randomVariableAAD3, atLeast(1)).sub(10.0d);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FiniteDifferenceHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel13()
      throws CalculationException {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(Scalar.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.sub(anyDouble())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD3.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD3);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(1.0d, 1.0d, 1.0d), stochasticDriver);
    MonteCarloAssetModel modelUsedForHedging = new MonteCarloAssetModel(process);
    BlackScholesDeltaHedgedPortfolio productToHedge =
        new BlackScholesDeltaHedgedPortfolio(10.0d, 10.0d, 10.0d, 10.0d);

    FiniteDifferenceHedgedPortfolio finiteDifferenceHedgedPortfolio =
        new FiniteDifferenceHedgedPortfolio(
            productToHedge, modelUsedForHedging, new ArrayList<>(), HedgeStrategy.deltaHedge);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion2.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfPaths()).thenReturn(10);
    TenorFromArray tenorFromArray2 = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion2.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    RandomVariable actualValue =
        finiteDifferenceHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion2, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion2, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion2, atLeast(1)).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion2).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(brownianMotion2, atLeast(1)).getTimeDiscretization();
    verify(randomVariableAAD3, atLeast(1)).getAverage();
    verify(randomVariableAAD3, atLeast(1)).getStandardDeviation();
    verify(randomVariableAAD2, atLeast(1)).mult(0.31622776601683794d);
    verify(randomVariableAAD).mult(1.0d);
    verify(randomVariableAAD3, atLeast(1)).sub(10.0d);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#getValues()}.
   * </ul>
   *
   * <p>Method under test: {@link FiniteDifferenceHedgedPortfolio#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FiniteDifferenceHedgedPortfolio.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel_thenCallsGetValues()
      throws CalculationException {
    // Arrange
    RandomVariableAAD volatility = mock(RandomVariableAAD.class);
    when(volatility.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(volatility.getTypePriority()).thenReturn(1);
    when(volatility.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD riskFreeRate = RandomVariableDifferentiableAAD.of(1.0d);

    BlackScholesModel model =
        new BlackScholesModel(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(anyDouble())).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.sub(anyDouble())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD3.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD3);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    TenorFromArray tenorFromArray = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    MonteCarloAssetModel modelUsedForHedging = new MonteCarloAssetModel(process);
    DigitalOption productToHedge = new DigitalOption(10.0d, 10.0d);

    FiniteDifferenceHedgedPortfolio finiteDifferenceHedgedPortfolio =
        new FiniteDifferenceHedgedPortfolio(
            productToHedge, modelUsedForHedging, new ArrayList<>(), HedgeStrategy.deltaHedge);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion2.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion2.getNumberOfPaths()).thenReturn(10);
    TenorFromArray tenorFromArray2 = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    when(brownianMotion2.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    RandomVariable actualValue =
        finiteDifferenceHedgedPortfolio.getValue(10.0d, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion2, atLeast(1)).getBrownianIncrement(eq(0), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion2, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion2, atLeast(1)).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion2).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(brownianMotion2, atLeast(1)).getTimeDiscretization();
    verify(randomVariableAAD3, atLeast(1)).getAverage();
    verify(randomVariableAAD3, atLeast(1)).getStandardDeviation();
    verify(volatility).getTypePriority();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD2, atLeast(1)).mult(0.31622776601683794d);
    verify(volatility).squared();
    verify(randomVariableAAD3, atLeast(1)).sub(10.0d);
    verify(volatility, atLeast(1)).getValues();
    verify(randomVariableAAD, atLeast(1)).getValues();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }
}
