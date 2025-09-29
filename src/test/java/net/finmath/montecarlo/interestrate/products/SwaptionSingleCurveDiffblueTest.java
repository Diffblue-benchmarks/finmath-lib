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
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
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

public class SwaptionSingleCurveDiffblueTest {
  /**
   * Test {@link SwaptionSingleCurve#SwaptionSingleCurve(double, double[], double[], double[])}.
   *
   * <p>Method under test: {@link SwaptionSingleCurve#SwaptionSingleCurve(double, double[],
   * double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwaptionSingleCurve.<init>(double, double[], double[], double[])"})
  public void testNewSwaptionSingleCurve() {
    // Arrange and Act
    SwaptionSingleCurve actualSwaptionSingleCurve =
        new SwaptionSingleCurve(
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertNull(actualSwaptionSingleCurve.getCurrency());
  }

  /**
   * Test {@link SwaptionSingleCurve#SwaptionSingleCurve(double, double[], double[], double[],
   * double[])}.
   *
   * <p>Method under test: {@link SwaptionSingleCurve#SwaptionSingleCurve(double, double[],
   * double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionSingleCurve.<init>(double, double[], double[], double[], double[])"
  })
  public void testNewSwaptionSingleCurve2() {
    // Arrange and Act
    SwaptionSingleCurve actualSwaptionSingleCurve =
        new SwaptionSingleCurve(
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertNull(actualSwaptionSingleCurve.getCurrency());
  }

  /**
   * Test {@link SwaptionSingleCurve#SwaptionSingleCurve(double, TimeDiscretization, double)}.
   *
   * <ul>
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionSingleCurve#SwaptionSingleCurve(double,
   * TimeDiscretization, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwaptionSingleCurve.<init>(double, TimeDiscretization, double)"})
  public void testNewSwaptionSingleCurve_thenReturnCurrencyIsNull() {
    // Arrange and Act
    SwaptionSingleCurve actualSwaptionSingleCurve =
        new SwaptionSingleCurve(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);

    // Assert
    assertNull(actualSwaptionSingleCurve.getCurrency());
  }

  /**
   * Test {@link SwaptionSingleCurve#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwaptionSingleCurve#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionSingleCurve.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    SwaptionSingleCurve swaptionSingleCurve =
        new SwaptionSingleCurve(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);

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
    RandomVariable actualValue = swaptionSingleCurve.getValue(10.0d, model);

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
   * Test {@link SwaptionSingleCurve#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwaptionSingleCurve#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionSingleCurve.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    SwaptionSingleCurve swaptionSingleCurve =
        new SwaptionSingleCurve(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(1.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(1.0d)
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
    RandomVariable actualValue = swaptionSingleCurve.getValue(10.0d, model);

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
   * Test {@link SwaptionSingleCurve#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwaptionSingleCurve#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionSingleCurve.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    SwaptionSingleCurve swaptionSingleCurve =
        new SwaptionSingleCurve(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(1.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(1.0d)
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
    RandomVariable actualValue = swaptionSingleCurve.getValue(10.0d, model);

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
   * Test {@link SwaptionSingleCurve#getValue(ForwardCurve, double)} with {@code ForwardCurve},
   * {@code double}.
   *
   * <p>Method under test: {@link SwaptionSingleCurve#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionSingleCurve.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble() {
    // Arrange
    SwaptionSingleCurve swaptionSingleCurve =
        new SwaptionSingleCurve(0.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(Double.NaN, swaptionSingleCurve.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionSingleCurve#getValue(ForwardCurve, double)} with {@code ForwardCurve},
   * {@code double}.
   *
   * <p>Method under test: {@link SwaptionSingleCurve#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionSingleCurve.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble2() {
    // Arrange
    SwaptionSingleCurve swaptionSingleCurve =
        new SwaptionSingleCurve(-0.5d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(0.0d, swaptionSingleCurve.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionSingleCurve#getValue(ForwardCurve, double)} with {@code ForwardCurve},
   * {@code double}.
   *
   * <p>Method under test: {@link SwaptionSingleCurve#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionSingleCurve.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble3() {
    // Arrange
    SwaptionSingleCurve swaptionSingleCurve =
        new SwaptionSingleCurve(10.0d, new TenorFromArray(-0.5d, 10, 0.5d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(Double.NaN, swaptionSingleCurve.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionSingleCurve#getValue(ForwardCurve, double)} with {@code ForwardCurve},
   * {@code double}.
   *
   * <p>Method under test: {@link SwaptionSingleCurve#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionSingleCurve.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble4() {
    // Arrange
    SwaptionSingleCurve swaptionSingleCurve =
        new SwaptionSingleCurve(10.0d, new TenorFromArray(10.0d, 10, 10.0d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(0.0d, swaptionSingleCurve.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionSingleCurve#getValue(ForwardCurve, double)} with {@code ForwardCurve},
   * {@code double}.
   *
   * <p>Method under test: {@link SwaptionSingleCurve#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionSingleCurve.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble5() {
    // Arrange
    SwaptionSingleCurve swaptionSingleCurve =
        new SwaptionSingleCurve(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {1.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(0.0d, swaptionSingleCurve.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionSingleCurve#getValue(ForwardCurve, double)} with {@code ForwardCurve},
   * {@code double}.
   *
   * <ul>
   *   <li>Then return {@code 0.6264290100259914}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionSingleCurve#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionSingleCurve.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble_thenReturn06264290100259914() {
    // Arrange
    SwaptionSingleCurve swaptionSingleCurve =
        new SwaptionSingleCurve(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 10.5d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(0.6264290100259914d, swaptionSingleCurve.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionSingleCurve#getValue(ForwardCurve, double)} with {@code ForwardCurve},
   * {@code double}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionSingleCurve#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionSingleCurve.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble_thenReturnNaN() {
    // Arrange
    SwaptionSingleCurve swaptionSingleCurve =
        new SwaptionSingleCurve(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(Double.NaN, swaptionSingleCurve.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionSingleCurve#getValue(ForwardCurve, double)} with {@code ForwardCurve},
   * {@code double}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionSingleCurve#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionSingleCurve.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble_thenThrowRuntimeException() {
    // Arrange
    SwaptionSingleCurve swaptionSingleCurve =
        new SwaptionSingleCurve(
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> swaptionSingleCurve.getValue(forwardCurve, 10.0d));
  }

  /**
   * Test {@link SwaptionSingleCurve#getValue(ForwardCurve, double)} with {@code ForwardCurve},
   * {@code double}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionSingleCurve#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionSingleCurve.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble_whenZero_thenReturnNaN() {
    // Arrange
    SwaptionSingleCurve swaptionSingleCurve =
        new SwaptionSingleCurve(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertEquals(Double.NaN, swaptionSingleCurve.getValue(forwardCurve, 0.0d), 0.0);
  }

  /**
   * Test {@link SwaptionSingleCurve#toString()}.
   *
   * <p>Method under test: {@link SwaptionSingleCurve#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String SwaptionSingleCurve.toString()"})
  public void testToString() {
    // Arrange
    SwaptionSingleCurve swaptionSingleCurve =
        new SwaptionSingleCurve(10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10.0d);

    // Act and Assert
    assertEquals(
        "AbstractMonteCarloProduct [currency=null]\n"
            + "exerciseDate: 10.0\n"
            + "fixingDates: [10.0, 10.5, 11.0, 11.5, 12.0, 12.5, 13.0, 13.5, 14.0, 14.5]\n"
            + "paymentDates: [10.5, 11.0, 11.5, 12.0, 12.5, 13.0, 13.5, 14.0, 14.5, 15.0]\n"
            + "periodLengths: null\n"
            + "swaprates: [10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0]",
        swaptionSingleCurve.toString());
  }
}
