package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class CMSOptionDiffblueTest {
  /**
   * Test {@link CMSOption#CMSOption(double, double[], double[], double[], double)}.
   *
   * <p>Method under test: {@link CMSOption#CMSOption(double, double[], double[], double[], double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CMSOption.<init>(double, double[], double[], double[], double)"})
  public void testNewCMSOption() {
    // Arrange and Act
    CMSOption actualCmsOption =
        new CMSOption(
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            10.0d);

    // Assert
    assertNull(actualCmsOption.getCurrency());
  }

  /**
   * Test {@link CMSOption#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CMSOption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CMSOption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    CMSOption cmsOption =
        new CMSOption(
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            10.0d);

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
        new TenorFromArray(-1.0d, -1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = cmsOption.getValue(10.0d, model);

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
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link CMSOption#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CMSOption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CMSOption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    CMSOption cmsOption =
        new CMSOption(
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(-1.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(-1.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(-1.0d)
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
    RandomVariable actualValue = cmsOption.getValue(10.0d, model);

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
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link CMSOption#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CMSOption#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CMSOption.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    CMSOption cmsOption =
        new CMSOption(
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(-1.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(-1.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(-1.0d)
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
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableFloatFactory());

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = cmsOption.getValue(10.0d, model);

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
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link CMSOption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code
   * double}.
   *
   * <p>Method under test: {@link CMSOption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CMSOption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble() {
    // Arrange
    CMSOption cmsOption =
        new CMSOption(
            Double.NaN,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, -1.0d, 1.0d, -1.0d},
            new double[] {1.0d, -1.0d, 1.0d, -1.0d},
            1.0d);

    // Act and Assert
    assertEquals(Double.NaN, cmsOption.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link CMSOption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code
   * double}.
   *
   * <p>Method under test: {@link CMSOption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CMSOption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble2() {
    // Arrange
    CMSOption cmsOption =
        new CMSOption(
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            -1.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, -1.0d, 1.0d, -1.0d},
            new double[] {1.0d, -1.0d, 1.0d, -1.0d},
            1.0d);

    // Act and Assert
    assertEquals(Double.NaN, cmsOption.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link CMSOption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code
   * double}.
   *
   * <p>Method under test: {@link CMSOption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CMSOption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble3() {
    // Arrange
    CMSOption cmsOption =
        new CMSOption(
            10.0d,
            new double[] {0.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, -1.0d, 1.0d, -1.0d},
            new double[] {1.0d, -1.0d, 1.0d, -1.0d},
            1.0d);

    // Act and Assert
    assertEquals(Double.NaN, cmsOption.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link CMSOption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code
   * double}.
   *
   * <p>Method under test: {@link CMSOption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CMSOption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble4() {
    // Arrange
    CMSOption cmsOption =
        new CMSOption(
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, -1.0d},
            new double[] {1.0d, -1.0d, 1.0d, -1.0d},
            1.0d);

    // Act and Assert
    assertEquals(Double.NaN, cmsOption.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link CMSOption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code
   * double}.
   *
   * <ul>
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link CMSOption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CMSOption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble_thenReturn00() {
    // Arrange
    CMSOption cmsOption =
        new CMSOption(
            -1.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, -1.0d, 1.0d, -1.0d},
            new double[] {1.0d, -1.0d, 1.0d, -1.0d},
            1.0d);

    // Act and Assert
    assertEquals(-0.0d, cmsOption.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link CMSOption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code
   * double}.
   *
   * <ul>
   *   <li>Then return {@code 0.01953125}.
   * </ul>
   *
   * <p>Method under test: {@link CMSOption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CMSOption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble_thenReturn001953125() {
    // Arrange
    CMSOption cmsOption =
        new CMSOption(
            0.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            -1.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, -1.0d, 1.0d, -1.0d},
            new double[] {1.0d, -1.0d, 1.0d, -1.0d},
            1.0d);

    // Act and Assert
    assertEquals(0.01953125d, cmsOption.getValue(forwardCurve, 10.0d), 0.0);
  }

  /**
   * Test {@link CMSOption#getValue(ForwardCurve, double)} with {@code ForwardCurve}, {@code
   * double}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link CMSOption#getValue(ForwardCurve, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CMSOption.getValue(ForwardCurve, double)"})
  public void testGetValueWithForwardCurveDouble_thenReturnNaN() {
    // Arrange
    CMSOption cmsOption =
        new CMSOption(
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            10.0d);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, -1.0d, 1.0d, -1.0d},
            new double[] {1.0d, -1.0d, 1.0d, -1.0d},
            1.0d);

    // Act and Assert
    assertEquals(Double.NaN, cmsOption.getValue(forwardCurve, 10.0d), 0.0);
  }
}
