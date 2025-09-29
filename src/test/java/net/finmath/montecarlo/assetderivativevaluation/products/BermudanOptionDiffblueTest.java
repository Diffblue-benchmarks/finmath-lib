package net.finmath.montecarlo.assetderivativevaluation.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
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
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BlackScholesModel;
import net.finmath.montecarlo.assetderivativevaluation.products.BermudanOption.ExerciseMethod;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class BermudanOptionDiffblueTest {
  /**
   * Test {@link BermudanOption#BermudanOption(double[], double[], double[])}.
   *
   * <p>Method under test: {@link BermudanOption#BermudanOption(double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BermudanOption.<init>(double[], double[], double[])"})
  public void testNewBermudanOption() {
    // Arrange and Act
    BermudanOption actualBermudanOption =
        new BermudanOption(
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertNull(actualBermudanOption.getCurrency());
    assertNull(actualBermudanOption.getLastValuationExerciseTime());
    assertNull(actualBermudanOption.getLastValuationContinuationValueAtExerciseTime());
    assertNull(actualBermudanOption.getLastValuationContinuationValueEstimatedAtExerciseTime());
    assertNull(actualBermudanOption.getLastValuationExerciseValueAtExerciseTime());
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualBermudanOption.getExerciseDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualBermudanOption.getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualBermudanOption.getStrikes(), 0.0);
  }

  /**
   * Test {@link BermudanOption#BermudanOption(double[], double[], double[], ExerciseMethod)}.
   *
   * <p>Method under test: {@link BermudanOption#BermudanOption(double[], double[], double[],
   * ExerciseMethod)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BermudanOption.<init>(double[], double[], double[], ExerciseMethod)"})
  public void testNewBermudanOption2() {
    // Arrange and Act
    BermudanOption actualBermudanOption =
        new BermudanOption(
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            ExerciseMethod.ESTIMATE_COND_EXPECTATION);

    // Assert
    assertNull(actualBermudanOption.getCurrency());
    assertNull(actualBermudanOption.getLastValuationExerciseTime());
    assertNull(actualBermudanOption.getLastValuationContinuationValueAtExerciseTime());
    assertNull(actualBermudanOption.getLastValuationContinuationValueEstimatedAtExerciseTime());
    assertNull(actualBermudanOption.getLastValuationExerciseValueAtExerciseTime());
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualBermudanOption.getExerciseDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualBermudanOption.getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualBermudanOption.getStrikes(), 0.0);
  }

  /**
   * Test {@link BermudanOption#BermudanOption(double[], double[], double[], ExerciseMethod, int,
   * boolean, boolean)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link BermudanOption#BermudanOption(double[], double[], double[],
   * ExerciseMethod, int, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BermudanOption.<init>(double[], double[], double[], ExerciseMethod, int, boolean, boolean)"
  })
  public void testNewBermudanOption_whenTen_thenReturnCurrencyIsNull() {
    // Arrange and Act
    BermudanOption actualBermudanOption =
        new BermudanOption(
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            ExerciseMethod.ESTIMATE_COND_EXPECTATION,
            10,
            true,
            true);

    // Assert
    assertNull(actualBermudanOption.getCurrency());
    assertNull(actualBermudanOption.getLastValuationExerciseTime());
    assertNull(actualBermudanOption.getLastValuationContinuationValueAtExerciseTime());
    assertNull(actualBermudanOption.getLastValuationContinuationValueEstimatedAtExerciseTime());
    assertNull(actualBermudanOption.getLastValuationExerciseValueAtExerciseTime());
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualBermudanOption.getExerciseDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualBermudanOption.getNotionals(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualBermudanOption.getStrikes(), 0.0);
  }

  /**
   * Test {@link BermudanOption#getValue(double, AssetModelMonteCarloSimulationModel)} with {@code
   * double}, {@code AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanOption#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanOption.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    BermudanOption bermudanOption =
        new BermudanOption(
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            ExerciseMethod.ESTIMATE_COND_EXPECTATION);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenThrow(new IllegalArgumentException());
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.exp()).thenReturn(randomVariableAAD);

    RandomVariableAAD riskFreeRate = mock(RandomVariableAAD.class);
    when(riskFreeRate.mult(anyDouble())).thenReturn(randomVariableAAD2);
    when(riskFreeRate.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    BlackScholesModel model =
        new BlackScholesModel(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.sub(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD3.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD3.getStandardDeviation()).thenReturn(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(randomVariableAAD3);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> bermudanOption.getValue(10.0d, new MonteCarloAssetModel(process)));
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD2).exp();
    verify(randomVariableAAD3, atLeast(1)).getAverage();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD3, atLeast(1)).getStandardDeviation();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(riskFreeRate).mult(10.0d);
    verify(randomVariableAAD3, atLeast(1)).sub(10.0d);
    verify(riskFreeRate).sub(isA(RandomVariable.class));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BermudanOption#getExerciseDates()}
   *   <li>{@link BermudanOption#getLastValuationContinuationValueAtExerciseTime()}
   *   <li>{@link BermudanOption#getLastValuationContinuationValueEstimatedAtExerciseTime()}
   *   <li>{@link BermudanOption#getLastValuationExerciseTime()}
   *   <li>{@link BermudanOption#getLastValuationExerciseValueAtExerciseTime()}
   *   <li>{@link BermudanOption#getNotionals()}
   *   <li>{@link BermudanOption#getStrikes()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] BermudanOption.getExerciseDates()",
    "RandomVariable[] BermudanOption.getLastValuationContinuationValueAtExerciseTime()",
    "RandomVariable[] BermudanOption.getLastValuationContinuationValueEstimatedAtExerciseTime()",
    "RandomVariable BermudanOption.getLastValuationExerciseTime()",
    "RandomVariable[] BermudanOption.getLastValuationExerciseValueAtExerciseTime()",
    "double[] BermudanOption.getNotionals()",
    "double[] BermudanOption.getStrikes()"
  })
  public void testGettersAndSetters() {
    // Arrange
    BermudanOption bermudanOption =
        new BermudanOption(
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    double[] actualExerciseDates = bermudanOption.getExerciseDates();
    RandomVariable[] actualLastValuationContinuationValueAtExerciseTime =
        bermudanOption.getLastValuationContinuationValueAtExerciseTime();
    RandomVariable[] actualLastValuationContinuationValueEstimatedAtExerciseTime =
        bermudanOption.getLastValuationContinuationValueEstimatedAtExerciseTime();
    RandomVariable actualLastValuationExerciseTime = bermudanOption.getLastValuationExerciseTime();
    RandomVariable[] actualLastValuationExerciseValueAtExerciseTime =
        bermudanOption.getLastValuationExerciseValueAtExerciseTime();
    double[] actualNotionals = bermudanOption.getNotionals();

    // Assert
    assertNull(actualLastValuationExerciseTime);
    assertNull(actualLastValuationContinuationValueAtExerciseTime);
    assertNull(actualLastValuationContinuationValueEstimatedAtExerciseTime);
    assertNull(actualLastValuationExerciseValueAtExerciseTime);
    assertArrayEquals(new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualExerciseDates, 0.0);
    assertArrayEquals(new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualNotionals, 0.0);
    assertArrayEquals(new double[] {10.0d, -1.0d, 10.0d, -1.0d}, bermudanOption.getStrikes(), 0.0);
  }
}
