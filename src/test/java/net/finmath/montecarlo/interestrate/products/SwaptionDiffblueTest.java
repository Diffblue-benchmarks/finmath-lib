package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.LIBORModelMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelExponentialForm5Param;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelStochasticHestonVolatility;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SwaptionDiffblueTest {
  /**
   * Test {@link Swaption#Swaption(double, double[], double[], double[])}.
   *
   * <p>Method under test: {@link Swaption#Swaption(double, double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Swaption.<init>(double, double[], double[], double[])"})
  public void testNewSwaption() {
    // Arrange and Act
    Swaption actualSwaption =
        new Swaption(
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertNull(actualSwaption.getPeriodLengths());
    assertNull(actualSwaption.getCurrency());
    assertEquals(1.0d, actualSwaption.getNotional(), 0.0);
    assertEquals(10.0d, actualSwaption.getExerciseDate(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualSwaption.getFixingDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualSwaption.getPaymentDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualSwaption.getSwaprates(), 0.0);
  }

  /**
   * Test {@link Swaption#Swaption(double, double[], double[], double[], double[])}.
   *
   * <p>Method under test: {@link Swaption#Swaption(double, double[], double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Swaption.<init>(double, double[], double[], double[], double[])"})
  public void testNewSwaption2() {
    // Arrange and Act
    Swaption actualSwaption =
        new Swaption(
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertNull(actualSwaption.getCurrency());
    assertEquals(1.0d, actualSwaption.getNotional(), 0.0);
    assertEquals(10.0d, actualSwaption.getExerciseDate(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualSwaption.getFixingDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualSwaption.getPaymentDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualSwaption.getPeriodLengths(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualSwaption.getSwaprates(), 0.0);
  }

  /**
   * Test {@link Swaption#Swaption(double, double[], double[], double[], double[], double)}.
   *
   * <p>Method under test: {@link Swaption#Swaption(double, double[], double[], double[], double[],
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Swaption.<init>(double, double[], double[], double[], double[], double)"
  })
  public void testNewSwaption3() {
    // Arrange and Act
    Swaption actualSwaption =
        new Swaption(
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            10.0d);

    // Assert
    assertNull(actualSwaption.getCurrency());
    assertEquals(10.0d, actualSwaption.getExerciseDate(), 0.0);
    assertEquals(10.0d, actualSwaption.getNotional(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualSwaption.getFixingDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualSwaption.getPaymentDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualSwaption.getPeriodLengths(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualSwaption.getSwaprates(), 0.0);
  }

  /**
   * Test {@link Swaption#Swaption(double, TimeDiscretization, double)}.
   *
   * <ul>
   *   <li>Then return PeriodLengths is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Swaption#Swaption(double, TimeDiscretization, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Swaption.<init>(double, TimeDiscretization, double)"})
  public void testNewSwaption_thenReturnPeriodLengthsIsNull() {
    // Arrange and Act
    Swaption actualSwaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);

    // Assert
    assertNull(actualSwaption.getPeriodLengths());
    assertNull(actualSwaption.getCurrency());
    assertEquals(1.0d, actualSwaption.getNotional(), 0.0);
    assertEquals(10.0d, actualSwaption.getExerciseDate(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualSwaption.getSwaprates(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d},
        actualSwaption.getFixingDates(),
        0.0);
    assertArrayEquals(
        new double[] {10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        actualSwaption.getPaymentDates(),
        0.0);
  }

  /**
   * Test {@link Swaption#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link Swaption#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);

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
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = swaption.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
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
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swaption#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link Swaption#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });
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
    RandomVariable actualValue = swaption.getValue(10.0d, model);

    // Assert
    verify(covarianceModel, atLeast(1))
        .getFactorLoading(anyInt(), eq(0), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
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
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swaption#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link Swaption#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = swaption.getValue(10.0d, model);

    // Assert
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
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swaption#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link Swaption#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swaption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel4()
      throws CalculationException {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    LIBORCovarianceModelStochasticHestonVolatility covarianceModel3 =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    BrownianMotionFromMersenneRandomNumbers brownianMotion2 =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel4,
            new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    RandomVariable actualValue = swaption.getValue(10.0d, model);

    // Assert
    verify(covarianceModel, atLeast(1))
        .getFactorLoading(anyInt(), eq(0), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
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
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swaption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code double}.
   *
   * <p>Method under test: {@link Swaption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swaption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble() {
    // Arrange
    Swaption swaption = new Swaption(-1.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(0.0d, swaption.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link Swaption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code double}.
   *
   * <p>Method under test: {@link Swaption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swaption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble2() {
    // Arrange
    Swaption swaption = new Swaption(0.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(Double.NaN, swaption.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link Swaption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code double}.
   *
   * <p>Method under test: {@link Swaption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swaption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble3() {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(-0.5d, 10, 0.5d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(Double.NaN, swaption.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link Swaption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code double}.
   *
   * <p>Method under test: {@link Swaption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swaption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble4() {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 10.0d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(0.0d, swaption.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link Swaption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code double}.
   *
   * <p>Method under test: {@link Swaption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swaption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble5() {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {1.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(0.0d, swaption.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link Swaption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code double}.
   *
   * <ul>
   *   <li>Then return {@code 0.6264290100259914}.
   * </ul>
   *
   * <p>Method under test: {@link Swaption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swaption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble_thenReturn06264290100259914() {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 10.5d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(0.6264290100259914d, swaption.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link Swaption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code double}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link Swaption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swaption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble_thenReturnNaN() {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(Double.NaN, swaption.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link Swaption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code double}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link Swaption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swaption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble_thenThrowRuntimeException() {
    // Arrange
    Swaption swaption =
        new Swaption(
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> swaption.getValue(forwardCurve, 10.0d));
  }

  /**
   * Test {@link Swaption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code double}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link Swaption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Swaption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble_whenMinusOne_thenReturnNaN() {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(Double.NaN, swaption.getValue(forwardCurve, -1.0d), 0.0);
  }

  /**
   * Test {@link Swaption#getExerciseIndicator(LIBORModelMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * Swaption#getExerciseIndicator(LIBORModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swaption.getExerciseIndicator(LIBORModelMonteCarloSimulationModel)"
  })
  public void testGetExerciseIndicator() throws CalculationException {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);

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
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualExerciseIndicator = swaption.getExerciseIndicator(model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualExerciseIndicator instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualExerciseIndicator.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swaption#getExerciseIndicator(LIBORModelMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * Swaption#getExerciseIndicator(LIBORModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swaption.getExerciseIndicator(LIBORModelMonteCarloSimulationModel)"
  })
  public void testGetExerciseIndicator2() throws CalculationException {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualExerciseIndicator = swaption.getExerciseIndicator(model);

    // Assert
    assertTrue(actualExerciseIndicator instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualExerciseIndicator.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swaption#getExerciseIndicator(LIBORModelMonteCarloSimulationModel)}.
   *
   * <ul>
   *   <li>Then calls {@link AbstractLIBORCovarianceModelParametric#getFactorLoading(int, int,
   *       RandomVariable[])}.
   * </ul>
   *
   * <p>Method under test: {@link
   * Swaption#getExerciseIndicator(LIBORModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swaption.getExerciseIndicator(LIBORModelMonteCarloSimulationModel)"
  })
  public void testGetExerciseIndicator_thenCallsGetFactorLoading() throws CalculationException {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });
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
    RandomVariable actualExerciseIndicator = swaption.getExerciseIndicator(model);

    // Assert
    verify(covarianceModel, atLeast(1))
        .getFactorLoading(anyInt(), eq(0), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualExerciseIndicator instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualExerciseIndicator.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Swaption#getExerciseIndicator(LIBORModelMonteCarloSimulationModel)}.
   *
   * <ul>
   *   <li>Then calls {@link AbstractLIBORCovarianceModelParametric#getFactorLoading(int, int,
   *       RandomVariable[])}.
   * </ul>
   *
   * <p>Method under test: {@link
   * Swaption#getExerciseIndicator(LIBORModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swaption.getExerciseIndicator(LIBORModelMonteCarloSimulationModel)"
  })
  public void testGetExerciseIndicator_thenCallsGetFactorLoading2() throws CalculationException {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    LIBORCovarianceModelStochasticHestonVolatility covarianceModel3 =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    BrownianMotionFromMersenneRandomNumbers brownianMotion2 =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel4,
            new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    RandomVariable actualExerciseIndicator = swaption.getExerciseIndicator(model);

    // Assert
    verify(covarianceModel, atLeast(1))
        .getFactorLoading(anyInt(), eq(0), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualExerciseIndicator instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExerciseIndicator.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualExerciseIndicator.getRealizations(),
        0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Swaption#toString()}
   *   <li>{@link Swaption#getExerciseDate()}
   *   <li>{@link Swaption#getFixingDates()}
   *   <li>{@link Swaption#getNotional()}
   *   <li>{@link Swaption#getPaymentDates()}
   *   <li>{@link Swaption#getPeriodLengths()}
   *   <li>{@link Swaption#getSwaprates()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Swaption.getExerciseDate()",
    "double[] Swaption.getFixingDates()",
    "double Swaption.getNotional()",
    "double[] Swaption.getPaymentDates()",
    "double[] Swaption.getPeriodLengths()",
    "double[] Swaption.getSwaprates()",
    "String Swaption.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Swaption swaption = new Swaption(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);

    // Act
    String actualToStringResult = swaption.toString();
    double actualExerciseDate = swaption.getExerciseDate();
    double[] actualFixingDates = swaption.getFixingDates();
    double actualNotional = swaption.getNotional();
    double[] actualPaymentDates = swaption.getPaymentDates();
    double[] actualPeriodLengths = swaption.getPeriodLengths();

    // Assert
    assertEquals(
        "AbstractMonteCarloProduct [currency=null]\n"
            + "exerciseDate: 10.0\n"
            + "fixingDates: [10.0, 10.5, 11.0, 11.5, 12.0, 12.5, 13.0, 13.5, 14.0, 14.5]\n"
            + "paymentDates: [10.5, 11.0, 11.5, 12.0, 12.5, 13.0, 13.5, 14.0, 14.5, 15.0]\n"
            + "periodLengths: null\n"
            + "swaprates: [10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0]",
        actualToStringResult);
    assertNull(actualPeriodLengths);
    assertEquals(1.0d, actualNotional, 0.0);
    assertEquals(10.0d, actualExerciseDate, 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        swaption.getSwaprates(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d},
        actualFixingDates,
        0.0);
    assertArrayEquals(
        new double[] {10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        actualPaymentDates,
        0.0);
  }
}
