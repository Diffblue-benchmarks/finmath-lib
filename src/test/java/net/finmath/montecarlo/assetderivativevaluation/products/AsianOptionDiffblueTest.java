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
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BlackScholesModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AsianOptionDiffblueTest {
  /**
   * Test {@link AsianOption#AsianOption(double, double, TimeDiscretization)}.
   *
   * <p>Method under test: {@link AsianOption#AsianOption(double, double, TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AsianOption.<init>(double, double, TimeDiscretization)"})
  public void testNewAsianOption() {
    // Arrange
    TenorFromArray timesForAveraging = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    AsianOption actualAsianOption = new AsianOption(10.0d, 10.0d, timesForAveraging);

    // Assert
    assertNull(actualAsianOption.getCurrency());
    assertEquals(0, actualAsianOption.getUnderlyingIndex().intValue());
    assertEquals(10.0d, actualAsianOption.getMaturity(), 0.0);
    assertEquals(10.0d, actualAsianOption.getStrike(), 0.0);
    assertSame(timesForAveraging, actualAsianOption.getTimesForAveraging());
  }

  /**
   * Test {@link AsianOption#AsianOption(double, double, TimeDiscretization, Integer)}.
   *
   * <p>Method under test: {@link AsianOption#AsianOption(double, double, TimeDiscretization,
   * Integer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AsianOption.<init>(double, double, TimeDiscretization, Integer)"})
  public void testNewAsianOption2() {
    // Arrange
    TenorFromArray timesForAveraging = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    AsianOption actualAsianOption = new AsianOption(10.0d, 10.0d, timesForAveraging, 1);

    // Assert
    assertNull(actualAsianOption.getCurrency());
    assertEquals(1, actualAsianOption.getUnderlyingIndex().intValue());
    assertEquals(10.0d, actualAsianOption.getMaturity(), 0.0);
    assertEquals(10.0d, actualAsianOption.getStrike(), 0.0);
    assertSame(timesForAveraging, actualAsianOption.getTimesForAveraging());
  }

  /**
   * Test {@link AsianOption#getValue(double, AssetModelMonteCarloSimulationModel)} with {@code
   * double}, {@code AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link AsianOption#getValue(double, AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AsianOption.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    AsianOption asianOption = new AsianOption(10.0d, 10.0d, new TenorFromArray(10.0d, 10, 0.5d));

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
    RandomVariable actualValue = asianOption.getValue(10.0d, new MonteCarloAssetModel(process));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(1.2756036595465732E65d, actualValue.getAverage(), 0.0);
    assertEquals(1.2756036595465732E65d, actualValue.getMax(), 0.0);
    assertEquals(1.2756036595465732E65d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {1.2756036595465732E65d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link AsianOption#getValue(double, AssetModelMonteCarloSimulationModel)} with {@code
   * double}, {@code AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link AsianOption#getValue(double, AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AsianOption.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    AsianOption asianOption = new AsianOption(10.0d, 10.0d, new TenorFromArray(10.0d, 10, 0.5d));

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
    RandomVariable actualValue = asianOption.getValue(10.0d, new MonteCarloAssetModel(process));

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
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AsianOption#getMaturity()}
   *   <li>{@link AsianOption#getStrike()}
   *   <li>{@link AsianOption#getTimesForAveraging()}
   *   <li>{@link AsianOption#getUnderlyingIndex()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AsianOption.getMaturity()",
    "double AsianOption.getStrike()",
    "TimeDiscretization AsianOption.getTimesForAveraging()",
    "Integer AsianOption.getUnderlyingIndex()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray timesForAveraging = new TenorFromArray(10.0d, 10, 0.5d);
    AsianOption asianOption = new AsianOption(10.0d, 10.0d, timesForAveraging);

    // Act
    double actualMaturity = asianOption.getMaturity();
    double actualStrike = asianOption.getStrike();
    TimeDiscretization actualTimesForAveraging = asianOption.getTimesForAveraging();

    // Assert
    assertEquals(0, asianOption.getUnderlyingIndex().intValue());
    assertEquals(10.0d, actualMaturity, 0.0);
    assertEquals(10.0d, actualStrike, 0.0);
    assertSame(timesForAveraging, actualTimesForAveraging);
  }
}
