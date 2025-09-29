package net.finmath.montecarlo.assetderivativevaluation.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.assetderivativevaluation.products.EuropeanOptionWithBoundary.ConstantBarrier;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel.Scheme;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EuropeanOptionWithBoundaryDiffblueTest {
  /**
   * Test ConstantBarrier {@link ConstantBarrier#getBarrierDirection(int, RandomVariable[])}.
   *
   * <p>Method under test: {@link ConstantBarrier#getBarrierDirection(int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableFromDoubleArray[] ConstantBarrier.getBarrierDirection(int, RandomVariable[])"
  })
  public void testConstantBarrierGetBarrierDirection() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);
    MonteCarloAssetModel scheme = new MonteCarloAssetModel(process);
    ConstantBarrier constantBarrier =
        new EuropeanOptionWithBoundary(10.0d, 10.0d).new ConstantBarrier(scheme);

    // Act
    RandomVariableFromDoubleArray[] actualBarrierDirection =
        constantBarrier.getBarrierDirection(
            1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    RandomVariableFromDoubleArray randomVariableFromDoubleArray = actualBarrierDirection[0];
    assertTrue(randomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        randomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualBarrierDirection.length);
    assertArrayEquals(new double[] {1.0d}, randomVariableFromDoubleArray.getRealizations(), 0.0);
  }

  /**
   * Test ConstantBarrier {@link ConstantBarrier#getBarrierDirection(int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getNumberOfTimeSteps()}.
   * </ul>
   *
   * <p>Method under test: {@link ConstantBarrier#getBarrierDirection(int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableFromDoubleArray[] ConstantBarrier.getBarrierDirection(int, RandomVariable[])"
  })
  public void testConstantBarrierGetBarrierDirection_thenCallsGetNumberOfTimeSteps() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            new BachelierModel(1.0d, 1.0d, 1.0d), stochasticDriver, Scheme.EULER);
    MonteCarloAssetModel scheme = new MonteCarloAssetModel(process);
    ConstantBarrier constantBarrier =
        new EuropeanOptionWithBoundary(10.0d, 10.0d).new ConstantBarrier(scheme);

    // Act
    RandomVariableFromDoubleArray[] actualBarrierDirection =
        constantBarrier.getBarrierDirection(
            1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization).getNumberOfTimeSteps();
    RandomVariableFromDoubleArray randomVariableFromDoubleArray = actualBarrierDirection[0];
    assertTrue(randomVariableFromDoubleArray.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        randomVariableFromDoubleArray.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariableFromDoubleArray.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualBarrierDirection.length);
    assertArrayEquals(new double[] {1.0d}, randomVariableFromDoubleArray.getRealizations(), 0.0);
  }

  /**
   * Test ConstantBarrier {@link ConstantBarrier#getBarrierDirection(int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ConstantBarrier#getBarrierDirection(int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableFromDoubleArray[] ConstantBarrier.getBarrierDirection(int, RandomVariable[])"
  })
  public void testConstantBarrierGetBarrierDirection_thenReturnNull() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization())
        .thenReturn(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);
    MonteCarloAssetModel scheme = new MonteCarloAssetModel(process);
    ConstantBarrier constantBarrier =
        new EuropeanOptionWithBoundary(10.0d, 10.0d).new ConstantBarrier(scheme);

    // Act
    RandomVariableFromDoubleArray[] actualBarrierDirection =
        constantBarrier.getBarrierDirection(
            1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertNull(actualBarrierDirection);
  }

  /**
   * Test ConstantBarrier {@link ConstantBarrier#getBarrierLevel(int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link ConstantBarrier#getBarrierLevel(int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableFromDoubleArray ConstantBarrier.getBarrierLevel(int, RandomVariable[])"
  })
  public void testConstantBarrierGetBarrierLevel_thenAbsReturnRandomVariableFromDoubleArray()
      throws CalculationException {
    // Arrange
    EuropeanOptionWithBoundary europeanOptionWithBoundary =
        new EuropeanOptionWithBoundary(10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    ConstantBarrier constantBarrier =
        europeanOptionWithBoundary
        .new ConstantBarrier(
            new MonteCarloBlackScholesModel(
                10.0d, 10.0d, 10.0d, new BrownianMotionWithControlVariate(brownianMotion)));

    // Act
    RandomVariableFromDoubleArray actualBarrierLevel =
        constantBarrier.getBarrierLevel(
            1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualBarrierLevel.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBarrierLevel.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBarrierLevel.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBarrierLevel.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBarrierLevel.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBarrierLevel.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBarrierLevel.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBarrierLevel.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBarrierLevel.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBarrierLevel.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualBarrierLevel.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBarrierLevel.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBarrierLevel.getStandardError(), 0.0);
    assertEquals(0.0d, actualBarrierLevel.getVariance(), 0.0);
    assertEquals(1, actualBarrierLevel.getTypePriority());
    assertEquals(1, actualBarrierLevel.size());
    assertEquals(10.5d, actualBarrierLevel.getFiltrationTime(), 0.0);
    assertEquals(108.24037034920393d, actualBarrierLevel.getAverage(), 0.0);
    assertEquals(108.24037034920393d, actualBarrierLevel.getMax(), 0.0);
    assertEquals(108.24037034920393d, actualBarrierLevel.getMin(), 0.0);
    assertTrue(actualBarrierLevel.isDeterministic());
    assertArrayEquals(
        new double[] {108.24037034920393d}, actualBarrierLevel.getRealizations(), 0.0);
  }

  /**
   * Test ConstantBarrier {@link ConstantBarrier#getBarrierLevel(int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ConstantBarrier#getBarrierLevel(int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableFromDoubleArray ConstantBarrier.getBarrierLevel(int, RandomVariable[])"
  })
  public void testConstantBarrierGetBarrierLevel_thenReturnNull() throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization())
        .thenReturn(
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);
    MonteCarloAssetModel scheme = new MonteCarloAssetModel(process);
    ConstantBarrier constantBarrier =
        new EuropeanOptionWithBoundary(10.0d, 10.0d).new ConstantBarrier(scheme);

    // Act
    RandomVariableFromDoubleArray actualBarrierLevel =
        constantBarrier.getBarrierLevel(
            1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertNull(actualBarrierLevel);
  }

  /**
   * Test {@link EuropeanOptionWithBoundary#EuropeanOptionWithBoundary(double, double)}.
   *
   * <p>Method under test: {@link EuropeanOptionWithBoundary#EuropeanOptionWithBoundary(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EuropeanOptionWithBoundary.<init>(double, double)"})
  public void testNewEuropeanOptionWithBoundary() {
    // Arrange, Act and Assert
    assertNull(new EuropeanOptionWithBoundary(10.0d, 10.0d).getCurrency());
  }

  /**
   * Test {@link EuropeanOptionWithBoundary#getBoundaryAdjustment(double, double,
   * AssetModelMonteCarloSimulationModel, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOptionWithBoundary#getBoundaryAdjustment(double, double,
   * AssetModelMonteCarloSimulationModel, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable EuropeanOptionWithBoundary.getBoundaryAdjustment(double, double, AssetModelMonteCarloSimulationModel, RandomVariable)"
  })
  public void testGetBoundaryAdjustment_thenReturnRandomVariableFromDoubleArray()
      throws CalculationException {
    // Arrange
    EuropeanOptionWithBoundary europeanOptionWithBoundary =
        new EuropeanOptionWithBoundary(10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    MonteCarloBlackScholesModel model =
        new MonteCarloBlackScholesModel(
            10.0d, 10.0d, 10.0d, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualBoundaryAdjustment =
        europeanOptionWithBoundary.getBoundaryAdjustment(
            10.0d, 10.0d, model, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBoundaryAdjustment instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBoundaryAdjustment.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBoundaryAdjustment.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBoundaryAdjustment.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBoundaryAdjustment.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBoundaryAdjustment.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBoundaryAdjustment.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBoundaryAdjustment.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBoundaryAdjustment.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBoundaryAdjustment.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBoundaryAdjustment.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualBoundaryAdjustment.getAverage(), 0.0);
    assertEquals(0.0d, actualBoundaryAdjustment.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualBoundaryAdjustment.getMax(), 0.0);
    assertEquals(0.0d, actualBoundaryAdjustment.getMin(), 0.0);
    assertEquals(0.0d, actualBoundaryAdjustment.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBoundaryAdjustment.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBoundaryAdjustment.getStandardError(), 0.0);
    assertEquals(0.0d, actualBoundaryAdjustment.getVariance(), 0.0);
    assertEquals(1, actualBoundaryAdjustment.getTypePriority());
    assertEquals(1, actualBoundaryAdjustment.size());
    assertTrue(actualBoundaryAdjustment.isDeterministic());
    assertArrayEquals(new double[] {0.0d}, actualBoundaryAdjustment.getRealizations(), 0.0);
  }
}
