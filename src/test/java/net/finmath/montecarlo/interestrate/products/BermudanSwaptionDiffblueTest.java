package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
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
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.MonteCarloSimulationModel;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.RandomVariableLazyEvaluation;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.conditionalexpectation.MonteCarloConditionalExpectationRegression;
import net.finmath.montecarlo.conditionalexpectation.MonteCarloConditionalExpectationRegression.RegressionBasisFunctions;
import net.finmath.montecarlo.conditionalexpectation.MonteCarloConditionalExpectationRegression.RegressionBasisFunctionsGiven;
import net.finmath.montecarlo.conditionalexpectation.RegressionBasisFunctionsProvider;
import net.finmath.montecarlo.interestrate.LIBORModelMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.ConditionalExpectationEstimator;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class BermudanSwaptionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When array of {@code boolean} with {@code true} and {@code false}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BermudanSwaption#BermudanSwaption(boolean[], double[], double[], double[],
   *       double[], double[])}
   *   <li>{@link BermudanSwaption#getIsCallable()}
   *   <li>{@link BermudanSwaption#getPaymentDates()}
   *   <li>{@link BermudanSwaption#getPeriodLengths()}
   *   <li>{@link BermudanSwaption#getPeriodNotionals()}
   *   <li>{@link BermudanSwaption#getSwapRates()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BermudanSwaption.<init>(boolean[], double[], double[], double[], double[], double[])",
    "void BermudanSwaption.<init>(boolean[], double[], double[], double[], double[], double[], boolean)",
    "void BermudanSwaption.<init>(boolean[], double[], double[], double[], double[], double[], boolean, RegressionBasisFunctionsProvider)",
    "boolean BermudanSwaption.getIsCallable()",
    "double[] BermudanSwaption.getPaymentDates()",
    "double[] BermudanSwaption.getPeriodLengths()",
    "double[] BermudanSwaption.getPeriodNotionals()",
    "double[] BermudanSwaption.getSwapRates()"
  })
  public void testGettersAndSetters_whenArrayOfBooleanWithTrueAndFalse() {
    // Arrange
    double[] periodLength = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] paymentDates = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] periodNotionals = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] swaprates = new double[] {10.0d, 2.0d, 10.0d, 2.0d};

    // Act
    BermudanSwaption actualBermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            periodLength,
            paymentDates,
            periodNotionals,
            swaprates);
    boolean actualIsCallable = actualBermudanSwaption.getIsCallable();
    double[] actualPaymentDates = actualBermudanSwaption.getPaymentDates();
    double[] actualPeriodLengths = actualBermudanSwaption.getPeriodLengths();
    double[] actualPeriodNotionals = actualBermudanSwaption.getPeriodNotionals();
    double[] actualSwapRates = actualBermudanSwaption.getSwapRates();

    // Assert
    assertNull(actualBermudanSwaption.getCurrency());
    assertTrue(actualIsCallable);
    assertSame(paymentDates, actualPaymentDates);
    assertSame(periodLength, actualPeriodLengths);
    assertSame(periodNotionals, actualPeriodNotionals);
    assertSame(swaprates, actualSwapRates);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualPeriodLengths, 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link RegressionBasisFunctionsProvider}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BermudanSwaption#BermudanSwaption(boolean[], double[], double[], double[],
   *       double[], double[], boolean, RegressionBasisFunctionsProvider)}
   *   <li>{@link BermudanSwaption#getIsCallable()}
   *   <li>{@link BermudanSwaption#getPaymentDates()}
   *   <li>{@link BermudanSwaption#getPeriodLengths()}
   *   <li>{@link BermudanSwaption#getPeriodNotionals()}
   *   <li>{@link BermudanSwaption#getSwapRates()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BermudanSwaption.<init>(boolean[], double[], double[], double[], double[], double[])",
    "void BermudanSwaption.<init>(boolean[], double[], double[], double[], double[], double[], boolean)",
    "void BermudanSwaption.<init>(boolean[], double[], double[], double[], double[], double[], boolean, RegressionBasisFunctionsProvider)",
    "boolean BermudanSwaption.getIsCallable()",
    "double[] BermudanSwaption.getPaymentDates()",
    "double[] BermudanSwaption.getPeriodLengths()",
    "double[] BermudanSwaption.getPeriodNotionals()",
    "double[] BermudanSwaption.getSwapRates()"
  })
  public void testGettersAndSetters_whenRegressionBasisFunctionsProvider() {
    // Arrange
    double[] periodLength = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] paymentDates = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] periodNotionals = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] swaprates = new double[] {10.0d, 2.0d, 10.0d, 2.0d};

    // Act
    BermudanSwaption actualBermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            periodLength,
            paymentDates,
            periodNotionals,
            swaprates,
            true,
            mock(RegressionBasisFunctionsProvider.class));
    boolean actualIsCallable = actualBermudanSwaption.getIsCallable();
    double[] actualPaymentDates = actualBermudanSwaption.getPaymentDates();
    double[] actualPeriodLengths = actualBermudanSwaption.getPeriodLengths();
    double[] actualPeriodNotionals = actualBermudanSwaption.getPeriodNotionals();
    double[] actualSwapRates = actualBermudanSwaption.getSwapRates();

    // Assert
    assertNull(actualBermudanSwaption.getCurrency());
    assertTrue(actualIsCallable);
    assertSame(paymentDates, actualPaymentDates);
    assertSame(periodLength, actualPeriodLengths);
    assertSame(periodNotionals, actualPeriodNotionals);
    assertSame(swaprates, actualSwapRates);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualPeriodLengths, 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BermudanSwaption#BermudanSwaption(boolean[], double[], double[], double[],
   *       double[], double[], boolean)}
   *   <li>{@link BermudanSwaption#getIsCallable()}
   *   <li>{@link BermudanSwaption#getPaymentDates()}
   *   <li>{@link BermudanSwaption#getPeriodLengths()}
   *   <li>{@link BermudanSwaption#getPeriodNotionals()}
   *   <li>{@link BermudanSwaption#getSwapRates()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BermudanSwaption.<init>(boolean[], double[], double[], double[], double[], double[])",
    "void BermudanSwaption.<init>(boolean[], double[], double[], double[], double[], double[], boolean)",
    "void BermudanSwaption.<init>(boolean[], double[], double[], double[], double[], double[], boolean, RegressionBasisFunctionsProvider)",
    "boolean BermudanSwaption.getIsCallable()",
    "double[] BermudanSwaption.getPaymentDates()",
    "double[] BermudanSwaption.getPeriodLengths()",
    "double[] BermudanSwaption.getPeriodNotionals()",
    "double[] BermudanSwaption.getSwapRates()"
  })
  public void testGettersAndSetters_whenTrue() {
    // Arrange
    double[] periodLength = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] paymentDates = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] periodNotionals = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] swaprates = new double[] {10.0d, 2.0d, 10.0d, 2.0d};

    // Act
    BermudanSwaption actualBermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            periodLength,
            paymentDates,
            periodNotionals,
            swaprates,
            true);
    boolean actualIsCallable = actualBermudanSwaption.getIsCallable();
    double[] actualPaymentDates = actualBermudanSwaption.getPaymentDates();
    double[] actualPeriodLengths = actualBermudanSwaption.getPeriodLengths();
    double[] actualPeriodNotionals = actualBermudanSwaption.getPeriodNotionals();
    double[] actualSwapRates = actualBermudanSwaption.getSwapRates();

    // Assert
    assertNull(actualBermudanSwaption.getCurrency());
    assertTrue(actualIsCallable);
    assertSame(paymentDates, actualPaymentDates);
    assertSame(periodLength, actualPeriodLengths);
    assertSame(periodNotionals, actualPeriodNotionals);
    assertSame(swaprates, actualSwapRates);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualPeriodLengths, 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    Map<String, Object> actualValues = bermudanSwaption.getValues(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    assertEquals(3, actualValues.size());
    assertTrue(actualValues.get("value") instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.get("exerciseTime") instanceof Scalar);
    assertTrue(actualValues.containsKey("error"));
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getNumeraire(anyDouble())).thenThrow(new IllegalArgumentException());
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValues(10.0d, model));
    verify(model).getForwardRate(2.0d, 2.0d, 4.0d);
    verify(model).getNumeraire(2.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    Map<String, Object> actualValues = bermudanSwaption.getValues(10.0d, model);

    // Assert
    verify(regressionBasisFunctionsProvider, atLeast(1))
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    assertEquals(3, actualValues.size());
    Object getResult = actualValues.get("value");
    assertTrue(getResult instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.containsKey("error"));
    assertTrue(actualValues.containsKey("exerciseTime"));
    assertArrayEquals(
        new double[] {74.0d}, ((RandomVariableFromDoubleArray) getResult).getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel4()
      throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenThrow(new IllegalArgumentException());
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValues(10.0d, model));
    verify(regressionBasisFunctionsProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel5()
      throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {
              Double.POSITIVE_INFINITY,
              -32.0d,
              Double.POSITIVE_INFINITY,
              -32.0d,
              Double.POSITIVE_INFINITY,
              -32.0d,
              Double.POSITIVE_INFINITY,
              -32.0d
            },
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    Map<String, Object> actualValues = bermudanSwaption.getValues(10.0d, model);

    // Assert
    verify(regressionBasisFunctionsProvider, atLeast(1))
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    assertEquals(3, actualValues.size());
    assertTrue(actualValues.get("exerciseTime") instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.get("value") instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.containsKey("error"));
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel6()
      throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            false,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    Map<String, Object> actualValues = bermudanSwaption.getValues(10.0d, model);

    // Assert
    verify(regressionBasisFunctionsProvider, atLeast(1))
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    assertEquals(3, actualValues.size());
    assertTrue(actualValues.get("exerciseTime") instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.get("value") instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.containsKey("error"));
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel7()
      throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(Double.POSITIVE_INFINITY);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              new RandomVariableFromDoubleArray(Double.POSITIVE_INFINITY)
            });
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    Map<String, Object> actualValues = bermudanSwaption.getValues(10.0d, model);

    // Assert
    verify(regressionBasisFunctionsProvider, atLeast(1))
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    assertEquals(3, actualValues.size());
    Object getResult = actualValues.get("value");
    assertTrue(getResult instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.containsKey("error"));
    assertTrue(actualValues.containsKey("exerciseTime"));
    assertArrayEquals(
        new double[] {74.0d}, ((RandomVariableFromDoubleArray) getResult).getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel8()
      throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenThrow(new IllegalArgumentException());
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(Scalar.of(Double.POSITIVE_INFINITY));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValues(10.0d, model));
    verify(regressionBasisFunctionsProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel9()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            mock(RegressionBasisFunctionsProvider.class));

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenThrow(new IllegalArgumentException());
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValues(10.0d, model));
    verify(model).getForwardRate(2.0d, 2.0d, 4.0d);
    verify(model).getMonteCarloWeights(2.0d);
    verify(model).getNumeraire(2.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel10()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            mock(RegressionBasisFunctionsProvider.class));

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenThrow(new IllegalArgumentException());
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValues(10.0d, model));
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel11()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            mock(RegressionBasisFunctionsProvider.class));

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenThrow(new IllegalArgumentException());
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(Scalar.of(Double.POSITIVE_INFINITY));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValues(10.0d, model));
    verify(model).getForwardRate(2.0d, 2.0d, 4.0d);
    verify(model).getMonteCarloWeights(2.0d);
    verify(model).getNumeraire(2.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel12()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            mock(RegressionBasisFunctionsProvider.class));

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenThrow(new IllegalArgumentException());
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(Scalar.of(Double.POSITIVE_INFINITY));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValues(10.0d, model));
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar2).mult(Double.POSITIVE_INFINITY);
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel13()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            mock(RegressionBasisFunctionsProvider.class));

    Scalar scalar = mock(Scalar.class);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.get(anyInt())).thenReturn(10.0d);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble()))
        .thenReturn(new RandomVariableFromFloatArray(Double.POSITIVE_INFINITY));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    Scalar ofResult = Scalar.of(Double.POSITIVE_INFINITY);
    when(model.getRandomVariableForConstant(anyDouble())).thenReturn(ofResult);

    // Act
    Map<String, Object> actualValues = bermudanSwaption.getValues(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(scalar).get(0);
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar2).mult(Double.POSITIVE_INFINITY);
    assertEquals(3, actualValues.size());
    assertTrue(actualValues.get("value") instanceof RandomVariableFromFloatArray);
    Object getResult = actualValues.get("exerciseTime");
    assertTrue(getResult instanceof Scalar);
    assertTrue(actualValues.containsKey("error"));
    assertSame(ofResult, getResult);
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <ul>
   *   <li>Given {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel_givenScalar()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            mock(RegressionBasisFunctionsProvider.class));

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenThrow(new IllegalArgumentException());
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(mock(Scalar.class));
    when(model.getNumeraire(anyDouble())).thenReturn(scalar);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValues(10.0d, model));
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
  }

  /**
   * Test {@link BermudanSwaption#getValues(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <ul>
   *   <li>Then calls {@link Scalar#invert()}.
   * </ul>
   *
   * <p>Method under test: {@link BermudanSwaption#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaption.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel_thenCallsInvert()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            mock(RegressionBasisFunctionsProvider.class));

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenThrow(new IllegalArgumentException());
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.invert()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble())).thenReturn(Scalar.of(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValues(10.0d, model));
    verify(model).getForwardRate(2.0d, 2.0d, 4.0d);
    verify(model).getMonteCarloWeights(2.0d);
    verify(model).getNumeraire(2.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar2).invert();
    verify(scalar).isDeterministic();
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bermudanSwaption.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
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
    assertArrayEquals(new double[] {74.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getNumeraire(anyDouble())).thenThrow(new IllegalArgumentException());
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValue(10.0d, model));
    verify(model).getForwardRate(2.0d, 2.0d, 4.0d);
    verify(model).getNumeraire(2.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bermudanSwaption.getValue(10.0d, model);

    // Assert
    verify(regressionBasisFunctionsProvider, atLeast(1))
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
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
    assertArrayEquals(new double[] {74.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel4()
      throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenThrow(new IllegalArgumentException());
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValue(10.0d, model));
    verify(regressionBasisFunctionsProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel5()
      throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {
              Double.POSITIVE_INFINITY,
              -32.0d,
              Double.POSITIVE_INFINITY,
              -32.0d,
              Double.POSITIVE_INFINITY,
              -32.0d,
              Double.POSITIVE_INFINITY,
              -32.0d
            },
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bermudanSwaption.getValue(10.0d, model);

    // Assert
    verify(regressionBasisFunctionsProvider, atLeast(1))
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
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
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel6()
      throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            false,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bermudanSwaption.getValue(10.0d, model);

    // Assert
    verify(regressionBasisFunctionsProvider, atLeast(1))
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
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
    assertArrayEquals(new double[] {74.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel7()
      throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {Scalar.of(Double.POSITIVE_INFINITY)});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bermudanSwaption.getValue(10.0d, model);

    // Assert
    verify(regressionBasisFunctionsProvider, atLeast(1))
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
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
    assertArrayEquals(new double[] {74.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel8()
      throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(
            new RandomVariable[] {new RandomVariableFromFloatArray(Double.POSITIVE_INFINITY)});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bermudanSwaption.getValue(10.0d, model);

    // Assert
    verify(regressionBasisFunctionsProvider, atLeast(1))
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
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
    assertArrayEquals(new double[] {74.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel9()
      throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(
            new RandomVariable[] {new RandomVariableLazyEvaluation(Double.POSITIVE_INFINITY)});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bermudanSwaption.getValue(10.0d, model);

    // Assert
    verify(regressionBasisFunctionsProvider, atLeast(1))
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    assertTrue(actualValue instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualValue.variance() instanceof RandomVariableLazyEvaluation);
    assertNull(actualValue.getOperator());
    assertEquals(0, actualValue.getTypePriority());
    assertEquals(0.0d, actualValue.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.size());
    assertEquals(74.0d, actualValue.getAverage(), 0.0);
    assertEquals(74.0d, actualValue.getMax(), 0.0);
    assertEquals(74.0d, actualValue.getMin(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertArrayEquals(new double[] {74.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel10()
      throws CalculationException {
    // Arrange
    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any())).thenThrow(new IllegalArgumentException());

    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {scalar});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValue(10.0d, model));
    verify(regressionBasisFunctionsProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(scalar).mult(isA(RandomVariable.class));
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel11()
      throws CalculationException {
    // Arrange
    Scalar scalar = mock(Scalar.class);
    when(scalar.getTypePriority()).thenThrow(new IllegalArgumentException());
    when(scalar.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {scalar});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValue(10.0d, model));
    verify(regressionBasisFunctionsProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(scalar).getTypePriority();
    verify(scalar).mult(isA(RandomVariable.class));
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel12()
      throws CalculationException {
    // Arrange
    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenThrow(new IllegalArgumentException());
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);
    when(scalar.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {scalar});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValue(10.0d, model));
    verify(regressionBasisFunctionsProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar).mult(isA(RandomVariable.class));
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel13()
      throws CalculationException {
    // Arrange
    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenThrow(new IllegalArgumentException());
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);
    when(scalar.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));

    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {scalar});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            regressionBasisFunctionsProvider);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> bermudanSwaption.getValue(10.0d, model));
    verify(regressionBasisFunctionsProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar).mult(isA(RandomVariable.class));
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel14()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            mock(RegressionBasisFunctionsProvider.class));

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.doubleValue()).thenReturn(10.0d);
    when(randomVariable.isDeterministic()).thenReturn(true);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(randomVariable);
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bermudanSwaption.getValue(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(randomVariable).doubleValue();
    verify(randomVariable).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariable).isDeterministic();
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
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel15()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.doubleValue()).thenReturn(10.0d);
    when(randomVariable.isDeterministic()).thenReturn(true);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable.getTypePriority()).thenReturn(1);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.invert()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(randomVariable);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bermudanSwaption.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(randomVariable, atLeast(1)).doubleValue();
    verify(randomVariable, atLeast(1)).getFiltrationTime();
    verify(randomVariable, atLeast(1)).getTypePriority();
    verify(randomVariable, atLeast(1)).isDeterministic();
    verify(scalar, atLeast(1)).doubleValue();
    verify(scalar, atLeast(1)).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar, atLeast(1)).invert();
    verify(scalar, atLeast(1)).isDeterministic();
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
    assertArrayEquals(new double[] {74.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel16()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.doubleValue()).thenReturn(10.0d);
    when(randomVariable.isDeterministic()).thenReturn(true);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable.getTypePriority()).thenReturn(1);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.invert()).thenReturn(Scalar.of(Double.POSITIVE_INFINITY));
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(randomVariable);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bermudanSwaption.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(randomVariable, atLeast(1)).doubleValue();
    verify(randomVariable, atLeast(1)).getFiltrationTime();
    verify(randomVariable, atLeast(1)).getTypePriority();
    verify(randomVariable, atLeast(1)).isDeterministic();
    verify(scalar, atLeast(1)).doubleValue();
    verify(scalar, atLeast(1)).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar, atLeast(1)).invert();
    verify(scalar, atLeast(1)).isDeterministic();
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
    assertArrayEquals(new double[] {74.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel17()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.doubleValue()).thenReturn(10.0d);
    when(randomVariable.isDeterministic()).thenReturn(true);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable.getTypePriority()).thenReturn(1);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.invert()).thenReturn(null);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(randomVariable);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bermudanSwaption.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(randomVariable, atLeast(1)).doubleValue();
    verify(randomVariable, atLeast(1)).getFiltrationTime();
    verify(randomVariable, atLeast(1)).getTypePriority();
    verify(randomVariable, atLeast(1)).isDeterministic();
    verify(scalar, atLeast(1)).doubleValue();
    verify(scalar, atLeast(1)).getFiltrationTime();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar, atLeast(1)).invert();
    verify(scalar, atLeast(1)).isDeterministic();
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
    assertArrayEquals(new double[] {74.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel18()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.doubleValue()).thenReturn(10.0d);
    when(randomVariable.isDeterministic()).thenReturn(true);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable.getTypePriority()).thenReturn(1);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);
    when(scalar.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.doubleValue()).thenReturn(10.0d);
    when(scalar2.invert()).thenReturn(scalar);
    when(scalar2.isDeterministic()).thenReturn(true);
    when(scalar2.getFiltrationTime()).thenReturn(10.0d);
    when(scalar2.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(randomVariable);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(-1.7976931348623157E308d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bermudanSwaption.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(randomVariable, atLeast(1)).doubleValue();
    verify(randomVariable, atLeast(1)).getFiltrationTime();
    verify(randomVariable, atLeast(1)).getTypePriority();
    verify(randomVariable, atLeast(1)).isDeterministic();
    verify(scalar2, atLeast(1)).doubleValue();
    verify(scalar, atLeast(1)).doubleValue();
    verify(scalar2, atLeast(1)).getFiltrationTime();
    verify(scalar, atLeast(1)).getFiltrationTime();
    verify(scalar2, atLeast(1)).getTypePriority();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar2, atLeast(1)).invert();
    verify(scalar2, atLeast(1)).isDeterministic();
    verify(scalar, atLeast(1)).isDeterministic();
    verify(scalar, atLeast(1)).mult(isA(RandomVariable.class));
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getAverage(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getMax(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NEGATIVE_INFINITY}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel19()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.doubleValue()).thenReturn(10.0d);
    when(randomVariable.isDeterministic()).thenReturn(true);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable.getTypePriority()).thenReturn(1);

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);
    when(scalar.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.doubleValue()).thenReturn(10.0d);
    when(scalar2.invert()).thenReturn(scalar);
    when(scalar2.isDeterministic()).thenReturn(true);
    when(scalar2.getFiltrationTime()).thenReturn(10.0d);
    when(scalar2.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(randomVariable);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(Scalar.of(Double.POSITIVE_INFINITY));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bermudanSwaption.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(randomVariable, atLeast(1)).doubleValue();
    verify(randomVariable, atLeast(1)).getFiltrationTime();
    verify(randomVariable, atLeast(1)).getTypePriority();
    verify(randomVariable, atLeast(1)).isDeterministic();
    verify(scalar2).doubleValue();
    verify(scalar, atLeast(1)).doubleValue();
    verify(scalar2).getFiltrationTime();
    verify(scalar, atLeast(1)).getFiltrationTime();
    verify(scalar2).getTypePriority();
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar2, atLeast(1)).invert();
    verify(scalar2).isDeterministic();
    verify(scalar, atLeast(1)).isDeterministic();
    verify(scalar, atLeast(1)).mult(anyDouble());
    verify(scalar, atLeast(1)).mult(isA(RandomVariable.class));
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(410.0d, actualValue.getAverage(), 0.0);
    assertEquals(410.0d, actualValue.getMax(), 0.0);
    assertEquals(410.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {410.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaption.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator() throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaption.getConditionalExpectationEstimator(10.0d, model);
    actualConditionalExpectationEstimator.getConditionalExpectation(
        new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[5] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(6, basisFunctions.length);
  }

  /**
   * Test {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaption.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator2() throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaption.getConditionalExpectationEstimator(10.0d, model);
    actualConditionalExpectationEstimator.getConditionalExpectation(Scalar.of(Double.NaN));

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[5] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(6, basisFunctions.length);
  }

  /**
   * Test {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaption.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator3() throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaption.getConditionalExpectationEstimator(10.0d, model);
    actualConditionalExpectationEstimator.getConditionalExpectation(
        new RandomVariableFromFloatArray(Double.NaN));

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[5] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(6, basisFunctions.length);
  }

  /**
   * Test {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaption.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator4() throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaption.getConditionalExpectationEstimator(10.0d, model);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[5] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(6, basisFunctions.length);
  }

  /**
   * Test {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaption.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator5() throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 10.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaption.getConditionalExpectationEstimator(10.0d, model);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[5] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(6, basisFunctions.length);
    assertSame(
        basisFunctionsEstimator,
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsPredictor());
  }

  /**
   * Test {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaption.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator6() throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, Double.NaN, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaption.getConditionalExpectationEstimator(10.0d, model);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[5] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(6, basisFunctions.length);
    assertSame(
        basisFunctionsEstimator,
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsPredictor());
  }

  /**
   * Test {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaption.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator7() throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 2.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaption.getConditionalExpectationEstimator(10.0d, model);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[5] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(6, basisFunctions.length);
    assertSame(
        basisFunctionsEstimator,
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsPredictor());
  }

  /**
   * Test {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaption.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator8() throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            true,
            regressionBasisFunctionsProvider);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaption.getConditionalExpectationEstimator(10.0d, model);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(regressionBasisFunctionsProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctions.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaption.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator9() throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    Scalar ofResult = Scalar.of(10.0d);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {ofResult});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            true,
            regressionBasisFunctionsProvider);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaption.getConditionalExpectationEstimator(10.0d, model);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariable actualConditionalExpectation =
        actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(regressionBasisFunctionsProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertTrue(actualConditionalExpectation instanceof Scalar);
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[0];
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(1, basisFunctions.length);
    assertSame(ofResult, randomVariable);
  }

  /**
   * Test {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaption.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator10() throws CalculationException {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenThrow(new IllegalArgumentException());

    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {randomVariableAAD});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            true,
            regressionBasisFunctionsProvider);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            bermudanSwaption
                .getConditionalExpectationEstimator(10.0d, model)
                .getConditionalExpectation(mock(RandomVariableAAD.class)));
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(regressionBasisFunctionsProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
  }

  /**
   * Test {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaption.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator11() throws CalculationException {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenThrow(new IllegalArgumentException());
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {randomVariableAAD});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            true,
            regressionBasisFunctionsProvider);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaption.getConditionalExpectationEstimator(10.0d, model);
    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD2));
    verify(randomVariableAAD).mult(1.0d);
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(randomVariableAAD2).mult(isA(RandomVariable.class));
    verify(regressionBasisFunctionsProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
  }

  /**
   * Test {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaption.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator12() throws CalculationException {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(anyDouble())).thenThrow(new IllegalArgumentException());
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));

    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {randomVariableAAD});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            true,
            regressionBasisFunctionsProvider);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaption.getConditionalExpectationEstimator(10.0d, model);
    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD2));
    verify(randomVariableAAD).mult(0.0d);
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(randomVariableAAD2).mult(isA(RandomVariable.class));
    verify(regressionBasisFunctionsProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
  }

  /**
   * Test {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link BermudanSwaption#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaption.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator13() throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionsProvider =
        mock(RegressionBasisFunctionsProvider.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(regressionBasisFunctionsProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(
            new RandomVariable[] {randomVariableFromDoubleArray, randomVariableFromDoubleArray2});
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d},
            true,
            regressionBasisFunctionsProvider);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaption.getConditionalExpectationEstimator(10.0d, model);
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    actualConditionalExpectationEstimator.getConditionalExpectation(randomVariableAAD);

    // Assert
    verify(randomVariableAAD, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(regressionBasisFunctionsProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable = basisFunctions[1];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(2, basisFunctions.length);
    assertSame(randomVariableFromDoubleArray2, randomVariable);
  }

  /**
   * Test {@link BermudanSwaption#getBasisFunctions(double, LIBORModelMonteCarloSimulationModel)}
   * with {@code double}, {@code LIBORModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getBasisFunctions(double,
   * LIBORModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BermudanSwaption.getBasisFunctions(double, LIBORModelMonteCarloSimulationModel)"
  })
  public void testGetBasisFunctionsWithDoubleLIBORModelMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualBasisFunctions = bermudanSwaption.getBasisFunctions(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[5] instanceof RandomVariableFromDoubleArray);
    assertEquals(6, actualBasisFunctions.length);
  }

  /**
   * Test {@link BermudanSwaption#getBasisFunctions(double, LIBORModelMonteCarloSimulationModel)}
   * with {@code double}, {@code LIBORModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getBasisFunctions(double,
   * LIBORModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BermudanSwaption.getBasisFunctions(double, LIBORModelMonteCarloSimulationModel)"
  })
  public void testGetBasisFunctionsWithDoubleLIBORModelMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY},
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY},
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY},
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY},
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY},
            true);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualBasisFunctions = bermudanSwaption.getBasisFunctions(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[5] instanceof RandomVariableFromDoubleArray);
    assertEquals(6, actualBasisFunctions.length);
  }

  /**
   * Test {@link BermudanSwaption#getBasisFunctions(double, LIBORModelMonteCarloSimulationModel)}
   * with {@code double}, {@code LIBORModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getBasisFunctions(double,
   * LIBORModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BermudanSwaption.getBasisFunctions(double, LIBORModelMonteCarloSimulationModel)"
  })
  public void testGetBasisFunctionsWithDoubleLIBORModelMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualBasisFunctions = bermudanSwaption.getBasisFunctions(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[5] instanceof RandomVariableFromDoubleArray);
    assertEquals(6, actualBasisFunctions.length);
  }

  /**
   * Test {@link BermudanSwaption#getBasisFunctions(double, MonteCarloSimulationModel)} with {@code
   * double}, {@code MonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaption#getBasisFunctions(double,
   * MonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BermudanSwaption.getBasisFunctions(double, MonteCarloSimulationModel)"
  })
  public void testGetBasisFunctionsWithDoubleMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> bermudanSwaption.getBasisFunctions(10.0d, new MonteCarloAssetModel(process)));
    verify(brownianMotion).getTimeDiscretization();
  }

  /**
   * Test {@link BermudanSwaption#getExerciseTimes()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with ten and ten.
   * </ul>
   *
   * <p>Method under test: {@link BermudanSwaption#getExerciseTimes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BermudanSwaption.getExerciseTimes()"})
  public void testGetExerciseTimes_thenReturnArrayOfDoubleWithTenAndTen() {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertArrayEquals(new double[] {10.0d, 10.0d}, bermudanSwaption.getExerciseTimes(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getFixingDates(double)}.
   *
   * <p>Method under test: {@link BermudanSwaption#getFixingDates(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BermudanSwaption.getFixingDates(double)"})
  public void testGetFixingDates() {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertArrayEquals(new double[] {10.0d, 10.0d}, bermudanSwaption.getFixingDates(10.0d), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getSwap()}.
   *
   * <p>Method under test: {@link BermudanSwaption#getSwap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SimpleSwap BermudanSwaption.getSwap()"})
  public void testGetSwap() {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act
    SimpleSwap actualSwap = bermudanSwaption.getSwap();

    // Assert
    assertNull(actualSwap.getCurrency());
    assertEquals(10.0d, actualSwap.getStartTime(), 0.0);
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualSwap.getPeriodLengths(), 0.0);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSwap.getFixingDates(), 0.0);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSwap.getNotional(), 0.0);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSwap.getPaymentDates(), 0.0);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSwap.getSwapRates(), 0.0);
  }

  /**
   * Test {@link BermudanSwaption#getFinalMaturity()}.
   *
   * <ul>
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link BermudanSwaption#getFinalMaturity()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BermudanSwaption.getFinalMaturity()"})
  public void testGetFinalMaturity_thenReturnTwo() {
    // Arrange
    BermudanSwaption bermudanSwaption =
        new BermudanSwaption(
            new boolean[] {true, false, true, false},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(2.0d, bermudanSwaption.getFinalMaturity(), 0.0);
  }
}
