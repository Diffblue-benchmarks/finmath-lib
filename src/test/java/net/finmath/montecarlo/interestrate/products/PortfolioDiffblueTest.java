package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
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
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.products.components.Numeraire;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PortfolioDiffblueTest {
  /**
   * Test {@link Portfolio#Portfolio(String, AbstractTermStructureMonteCarloProduct[], double[])}.
   *
   * <ul>
   *   <li>Then Products return {@code AbstractTermStructureMonteCarloProduct[]}.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(String,
   * AbstractTermStructureMonteCarloProduct[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Portfolio.<init>(String, AbstractTermStructureMonteCarloProduct[], double[])"
  })
  public void testNewPortfolio_thenProductsReturnAbstractTermStructureMonteCarloProduct() {
    // Arrange and Act
    Portfolio actualPortfolio =
        new Portfolio(
            "GBP",
            new AbstractTermStructureMonteCarloProduct[] {},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    TermStructureMonteCarloProduct[] products = actualPortfolio.getProducts();
    assertTrue(products instanceof AbstractTermStructureMonteCarloProduct[]);
    assertNull(actualPortfolio.getCurrency());
    assertEquals(0, products.length);
    assertArrayEquals(new double[] {10.0d, 1.0d, 10.0d, 1.0d}, actualPortfolio.getWeights(), 0.0);
  }

  /**
   * Test {@link Portfolio#Portfolio(String, AbstractTermStructureMonteCarloProduct[], double[])}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(String,
   * AbstractTermStructureMonteCarloProduct[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Portfolio.<init>(String, AbstractTermStructureMonteCarloProduct[], double[])"
  })
  public void testNewPortfolio_thenThrowIllegalArgumentException() {
    // Arrange
    AbstractTermStructureMonteCarloProduct[] products =
        new AbstractTermStructureMonteCarloProduct[] {
          new ForwardRateVolatilitySurfaceCurvature(10.0d)
        };

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new Portfolio("GBP", products, new double[] {10.0d, 1.0d, 10.0d, 1.0d}));
  }

  /**
   * Test {@link Portfolio#getCurrency()}.
   *
   * <p>Method under test: {@link Portfolio#getCurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Portfolio.getCurrency()"})
  public void testGetCurrency() {
    // Arrange, Act and Assert
    assertNull(
        new Portfolio(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d).getCurrency());
  }

  /**
   * Test {@link Portfolio#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set Portfolio.queryUnderlyings()"})
  public void testQueryUnderlyings_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new Portfolio(new Numeraire(), 10.0d).queryUnderlyings());
  }

  /**
   * Test {@link Portfolio#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set Portfolio.queryUnderlyings()"})
  public void testQueryUnderlyings_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new Portfolio(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d)
                .queryUnderlyings());
  }

  /**
   * Test {@link Portfolio#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link Portfolio#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Portfolio.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    Portfolio portfolio = new Portfolio(new Numeraire(), 10.0d);

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
    RandomVariable actualValue = portfolio.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(10.0d, actualValue.getAverage(), 0.0);
    assertEquals(10.0d, actualValue.getMax(), 0.0);
    assertEquals(10.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Portfolio#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link Portfolio#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Portfolio.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    Portfolio portfolio = new Portfolio(new Numeraire(), 10.0d);

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
        new TenorFromArray(
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = portfolio.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.NaN, actualValue.getAverage(), 0.0);
    assertEquals(Double.NaN, actualValue.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualValue.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualValue.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualValue.getVariance(), 0.0);
    assertArrayEquals(
        new double[] {
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY
        },
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Portfolio#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Portfolio.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel_thenReturnSizeIsOne()
      throws CalculationException {
    // Arrange
    Portfolio portfolio = new Portfolio(new MoneyMarketAccount(), 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        portfolio.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualValue.getAverage(), 0.0);
    assertArrayEquals(new double[] {Double.POSITIVE_INFINITY}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link Portfolio#getWeights()}.
   *
   * <p>Method under test: {@link Portfolio#getWeights()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] Portfolio.getWeights()"})
  public void testGetWeights() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {10.0d},
        new Portfolio(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d).getWeights(),
        0.0);
  }
}
