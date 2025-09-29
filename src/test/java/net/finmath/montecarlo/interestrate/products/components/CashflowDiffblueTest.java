package net.finmath.montecarlo.interestrate.products.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CashflowDiffblueTest {
  /**
   * Test {@link Cashflow#Cashflow(double, double, boolean)}.
   *
   * <p>Method under test: {@link Cashflow#Cashflow(double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Cashflow.<init>(double, double, boolean)"})
  public void testNewCashflow() {
    // Arrange, Act and Assert
    assertNull(new Cashflow(10.0d, 10.0d, true).getCurrency());
  }

  /**
   * Test {@link Cashflow#Cashflow(String, double, double, boolean)}.
   *
   * <p>Method under test: {@link Cashflow#Cashflow(String, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Cashflow.<init>(String, double, double, boolean)"})
  public void testNewCashflow2() {
    // Arrange and Act
    Cashflow actualCashflow = new Cashflow("GBP", 10.0d, 10.0d, true);

    // Assert
    assertEquals("GBP", actualCashflow.getCurrency());
  }

  /**
   * Test {@link Cashflow#queryUnderlyings()}.
   *
   * <p>Method under test: {@link Cashflow#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set Cashflow.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange, Act and Assert
    assertNull(new Cashflow(10.0d, 10.0d, true).queryUnderlyings());
  }

  /**
   * Test {@link Cashflow#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link Cashflow#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Cashflow.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    Cashflow cashflow = new Cashflow(10.0d, 10.0d, true);

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
    RandomVariable actualValue = cashflow.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof Scalar);
    assertEquals(-10.0d, actualValue.getAverage(), 0.0);
    assertEquals(-10.0d, actualValue.getMax(), 0.0);
    assertEquals(-10.0d, actualValue.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualValue.expectation();
    assertSame(actualValue, actualExpectationResult);
  }

  /**
   * Test {@link Cashflow#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link Cashflow#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Cashflow.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    Cashflow cashflow = new Cashflow(10.0d, -1.0d, true);

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
    RandomVariable actualValue = cashflow.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof Scalar);
    assertEquals(0.0d, actualValue.getAverage(), 0.0);
    assertEquals(0.0d, actualValue.getMax(), 0.0);
    assertEquals(0.0d, actualValue.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualValue.expectation();
    assertSame(actualValue, actualExpectationResult);
  }

  /**
   * Test {@link Cashflow#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link Cashflow#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Cashflow.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    Cashflow cashflow = new Cashflow(10.0d, 10.0d, false);

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
    RandomVariable actualValue = cashflow.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof Scalar);
    assertEquals(10.0d, actualValue.getAverage(), 0.0);
    assertEquals(10.0d, actualValue.getMax(), 0.0);
    assertEquals(10.0d, actualValue.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualValue.expectation();
    assertSame(actualValue, actualExpectationResult);
  }

  /**
   * Test {@link Cashflow#toString()}.
   *
   * <p>Method under test: {@link Cashflow#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Cashflow.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "Cashflow [flowAmount=10.0, flowDate=10.0, isPayer=true, toString()=AbstractMonteCarloProduct"
            + " [currency=null]]",
        new Cashflow(10.0d, 10.0d, true).toString());
  }
}
