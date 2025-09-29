package net.finmath.montecarlo.assetderivativevaluation.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.assetderivativevaluation.products.BermudanDigitalOption.ExerciseMethod;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel.Scheme;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BermudanDigitalOptionDiffblueTest {
  /**
   * Test {@link BermudanDigitalOption#BermudanDigitalOption(double[], double[], double[],
   * ExerciseMethod, Map)}.
   *
   * <p>Method under test: {@link BermudanDigitalOption#BermudanDigitalOption(double[], double[],
   * double[], ExerciseMethod, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BermudanDigitalOption.<init>(double[], double[], double[], ExerciseMethod, Map)"
  })
  public void testNewBermudanDigitalOption() {
    // Arrange and Act
    BermudanDigitalOption actualBermudanDigitalOption =
        new BermudanDigitalOption(
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            ExerciseMethod.ESTIMATE_COND_EXPECTATION,
            new HashMap<>());

    // Assert
    assertNull(actualBermudanDigitalOption.getCurrency());
  }

  /**
   * Test {@link BermudanDigitalOption#getValue(double, AssetModelMonteCarloSimulationModel)} with
   * {@code double}, {@code AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanDigitalOption#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanDigitalOption.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    BermudanDigitalOption bermudanDigitalOption =
        new BermudanDigitalOption(
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            ExerciseMethod.ESTIMATE_COND_EXPECTATION,
            new HashMap<>());

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(1);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            new BachelierModel(1.0d, 1.0d, 1.0d), stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable actualValue =
        bermudanDigitalOption.getValue(10.0d, new MonteCarloAssetModel(process));

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeIndex(anyDouble());
    verify(timeDiscretization, atLeast(1)).getTimeStep(0);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(-23943.656686079128d, actualValue.getAverage(), 0.0);
    assertEquals(29337.11817735026d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(8.60666502951815E8d, actualValue.getVariance(), 0.0);
    assertEquals(9.562961143909056E8d, actualValue.getSampleVariance(), 0.0);
    assertEquals(9277.211342595441d, actualValue.getStandardError(), 0.0);
    assertArrayEquals(
        new double[] {
          10.0d,
          10.0d,
          -59874.141715197824d,
          10.0d,
          10.0d,
          -59874.141715197824d,
          10.0d,
          10.0d,
          -59874.141715197824d,
          -59874.141715197824d
        },
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BermudanDigitalOption#getValue(double, AssetModelMonteCarloSimulationModel)} with
   * {@code double}, {@code AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanDigitalOption#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanDigitalOption.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    BermudanDigitalOption bermudanDigitalOption =
        new BermudanDigitalOption(
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            ExerciseMethod.ESTIMATE_COND_EXPECTATION,
            new HashMap<>());

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(3);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            new BachelierModel(1.0d, 1.0d, 1.0d), stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable actualValue =
        bermudanDigitalOption.getValue(10.0d, new MonteCarloAssetModel(process));

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeIndex(anyDouble());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(-17955.242514559348d, actualValue.getAverage(), 0.0);
    assertEquals(27442.361233735668d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(7.530831900828383E8d, actualValue.getVariance(), 0.0);
    assertEquals(8.367591000920424E8d, actualValue.getSampleVariance(), 0.0);
    assertEquals(8678.036587171306d, actualValue.getStandardError(), 0.0);
    assertArrayEquals(
        new double[] {
          10.0d,
          10.0d,
          10.0d,
          -59874.141715197824d,
          10.0d,
          -59874.141715197824d,
          10.0d,
          10.0d,
          10.0d,
          -59874.141715197824d
        },
        actualValue.getRealizations(),
        0.0);
  }
}
