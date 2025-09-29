package net.finmath.montecarlo.interestrate.products.components;

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
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AccruingNotionalDiffblueTest {
  /**
   * Test {@link AccruingNotional#AccruingNotional(Notional, AbstractPeriod)}.
   *
   * <p>Method under test: {@link AccruingNotional#AccruingNotional(Notional, AbstractPeriod)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AccruingNotional.<init>(Notional, AbstractPeriod)"})
  public void testNewAccruingNotional() {
    // Arrange
    NotionalFromConstant previousPeriodNotional = new NotionalFromConstant(10.0d);
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period previousPeriod =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    // Act
    AccruingNotional actualAccruingNotional =
        new AccruingNotional(previousPeriodNotional, previousPeriod);

    // Assert
    assertNull(actualAccruingNotional.getCurrency());
  }

  /**
   * Test {@link AccruingNotional#getCurrency()}.
   *
   * <p>Method under test: {@link AccruingNotional#getCurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AccruingNotional.getCurrency()"})
  public void testGetCurrency() {
    // Arrange
    NotionalFromConstant previousPeriodNotional = new NotionalFromConstant(10.0d);
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period previousPeriod =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    AccruingNotional previousPeriodNotional2 =
        new AccruingNotional(previousPeriodNotional, previousPeriod);
    NotionalFromConstant notional2 = new NotionalFromConstant(10.0d);
    Period previousPeriod2 =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional2, new Numeraire(), true, true, true);

    AccruingNotional accruingNotional =
        new AccruingNotional(previousPeriodNotional2, previousPeriod2);

    // Act and Assert
    assertNull(accruingNotional.getCurrency());
  }

  /**
   * Test {@link AccruingNotional#getCurrency()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AccruingNotional#getCurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AccruingNotional.getCurrency()"})
  public void testGetCurrency_thenReturnNull() {
    // Arrange
    NotionalFromConstant previousPeriodNotional = new NotionalFromConstant(10.0d);
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period previousPeriod =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    AccruingNotional accruingNotional =
        new AccruingNotional(previousPeriodNotional, previousPeriod);

    // Act and Assert
    assertNull(accruingNotional.getCurrency());
  }

  /**
   * Test {@link AccruingNotional#getNotionalAtPeriodStart(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <ul>
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link AccruingNotional#getNotionalAtPeriodStart(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AccruingNotional.getNotionalAtPeriodStart(AbstractPeriod, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetNotionalAtPeriodStart_thenReturnAverageIsOne() throws CalculationException {
    // Arrange
    NotionalFromComponent previousPeriodNotional = new NotionalFromComponent(new Numeraire());
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period previousPeriod =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    AccruingNotional accruingNotional =
        new AccruingNotional(previousPeriodNotional, previousPeriod);
    NotionalFromConstant notional2 = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional2, new Numeraire(), true, true, true);

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
    RandomVariable actualNotionalAtPeriodStart =
        accruingNotional.getNotionalAtPeriodStart(period, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNotionalAtPeriodStart instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualNotionalAtPeriodStart.getAverage(), 0.0);
    assertEquals(1.0d, actualNotionalAtPeriodStart.getMax(), 0.0);
    assertEquals(1.0d, actualNotionalAtPeriodStart.getMin(), 0.0);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualNotionalAtPeriodStart.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AccruingNotional#getNotionalAtPeriodStart(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <ul>
   *   <li>Then return Average is ten.
   * </ul>
   *
   * <p>Method under test: {@link AccruingNotional#getNotionalAtPeriodStart(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AccruingNotional.getNotionalAtPeriodStart(AbstractPeriod, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetNotionalAtPeriodStart_thenReturnAverageIsTen() throws CalculationException {
    // Arrange
    NotionalFromConstant previousPeriodNotional = new NotionalFromConstant(10.0d);
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period previousPeriod =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    AccruingNotional accruingNotional =
        new AccruingNotional(previousPeriodNotional, previousPeriod);
    NotionalFromConstant notional2 = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional2, new Numeraire(), true, true, true);

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
    RandomVariable actualNotionalAtPeriodStart =
        accruingNotional.getNotionalAtPeriodStart(period, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNotionalAtPeriodStart instanceof RandomVariableFromDoubleArray);
    assertEquals(10.0d, actualNotionalAtPeriodStart.getAverage(), 0.0);
    assertEquals(10.0d, actualNotionalAtPeriodStart.getMax(), 0.0);
    assertEquals(10.0d, actualNotionalAtPeriodStart.getMin(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualNotionalAtPeriodStart.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AccruingNotional#getNotionalAtPeriodEnd(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <ul>
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link AccruingNotional#getNotionalAtPeriodEnd(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AccruingNotional.getNotionalAtPeriodEnd(AbstractPeriod, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetNotionalAtPeriodEnd_thenReturnAverageIsOne() throws CalculationException {
    // Arrange
    NotionalFromComponent previousPeriodNotional = new NotionalFromComponent(new Numeraire());
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period previousPeriod =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    AccruingNotional accruingNotional =
        new AccruingNotional(previousPeriodNotional, previousPeriod);
    NotionalFromConstant notional2 = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional2, new Numeraire(), true, true, true);

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
    RandomVariable actualNotionalAtPeriodEnd =
        accruingNotional.getNotionalAtPeriodEnd(period, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNotionalAtPeriodEnd instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualNotionalAtPeriodEnd.getAverage(), 0.0);
    assertEquals(1.0d, actualNotionalAtPeriodEnd.getMax(), 0.0);
    assertEquals(1.0d, actualNotionalAtPeriodEnd.getMin(), 0.0);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualNotionalAtPeriodEnd.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AccruingNotional#getNotionalAtPeriodEnd(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <ul>
   *   <li>Then return Average is ten.
   * </ul>
   *
   * <p>Method under test: {@link AccruingNotional#getNotionalAtPeriodEnd(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AccruingNotional.getNotionalAtPeriodEnd(AbstractPeriod, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetNotionalAtPeriodEnd_thenReturnAverageIsTen() throws CalculationException {
    // Arrange
    NotionalFromConstant previousPeriodNotional = new NotionalFromConstant(10.0d);
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period previousPeriod =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    AccruingNotional accruingNotional =
        new AccruingNotional(previousPeriodNotional, previousPeriod);
    NotionalFromConstant notional2 = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional2, new Numeraire(), true, true, true);

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
    RandomVariable actualNotionalAtPeriodEnd =
        accruingNotional.getNotionalAtPeriodEnd(period, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNotionalAtPeriodEnd instanceof RandomVariableFromDoubleArray);
    assertEquals(10.0d, actualNotionalAtPeriodEnd.getAverage(), 0.0);
    assertEquals(10.0d, actualNotionalAtPeriodEnd.getMax(), 0.0);
    assertEquals(10.0d, actualNotionalAtPeriodEnd.getMin(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualNotionalAtPeriodEnd.getRealizations(),
        0.0);
  }
}
