package net.finmath.montecarlo.interestrate.products.components;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
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

public class NotionalFromComponentDiffblueTest {
  /**
   * Test {@link NotionalFromComponent#NotionalFromComponent(AbstractProductComponent)}.
   *
   * <p>Method under test: {@link
   * NotionalFromComponent#NotionalFromComponent(AbstractProductComponent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NotionalFromComponent.<init>(AbstractProductComponent)"})
  public void testNewNotionalFromComponent() {
    // Arrange, Act and Assert
    assertNull(new NotionalFromComponent(new Numeraire()).getCurrency());
  }

  /**
   * Test {@link NotionalFromComponent#getCurrency()}.
   *
   * <p>Method under test: {@link NotionalFromComponent#getCurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String NotionalFromComponent.getCurrency()"})
  public void testGetCurrency() {
    // Arrange
    NotionalFromComponent notional = new NotionalFromComponent(new Numeraire());
    Period notional2 =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    // Act and Assert
    assertNull(new NotionalFromComponent(notional2).getCurrency());
  }

  /**
   * Test {@link NotionalFromComponent#getCurrency()}.
   *
   * <ul>
   *   <li>Given {@link NotionalFromComponent#NotionalFromComponent(AbstractProductComponent)} with
   *       notional is {@link Numeraire} (default constructor).
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NotionalFromComponent#getCurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String NotionalFromComponent.getCurrency()"})
  public void testGetCurrency_givenNotionalFromComponentWithNotionalIsNumeraire_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new NotionalFromComponent(new Numeraire()).getCurrency());
  }

  /**
   * Test {@link NotionalFromComponent#getNotionalAtPeriodEnd(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <ul>
   *   <li>Given three.
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link NotionalFromComponent#getNotionalAtPeriodEnd(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable NotionalFromComponent.getNotionalAtPeriodEnd(AbstractPeriod, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetNotionalAtPeriodEnd_givenThree_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    NotionalFromComponent notionalFromComponent = new NotionalFromComponent(new Numeraire());
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

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
        notionalFromComponent.getNotionalAtPeriodEnd(period, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNotionalAtPeriodEnd instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualNotionalAtPeriodEnd.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNotionalAtPeriodEnd.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNotionalAtPeriodEnd.getStandardError(), 0.0);
    assertEquals(0.0d, actualNotionalAtPeriodEnd.getVariance(), 0.0);
    assertEquals(1, actualNotionalAtPeriodEnd.getTypePriority());
    assertEquals(1.0d, actualNotionalAtPeriodEnd.getAverage(), 0.0);
    assertEquals(1.0d, actualNotionalAtPeriodEnd.getMax(), 0.0);
    assertEquals(1.0d, actualNotionalAtPeriodEnd.getMin(), 0.0);
    assertEquals(10, actualNotionalAtPeriodEnd.size());
    assertEquals(10.0d, actualNotionalAtPeriodEnd.getFiltrationTime(), 0.0);
    assertFalse(actualNotionalAtPeriodEnd.isDeterministic());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualNotionalAtPeriodEnd.getRealizations(),
        0.0);
  }

  /**
   * Test {@link NotionalFromComponent#getNotionalAtPeriodStart(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <ul>
   *   <li>Given three.
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link NotionalFromComponent#getNotionalAtPeriodStart(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable NotionalFromComponent.getNotionalAtPeriodStart(AbstractPeriod, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetNotionalAtPeriodStart_givenThree_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    NotionalFromComponent notionalFromComponent = new NotionalFromComponent(new Numeraire());
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

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
        notionalFromComponent.getNotionalAtPeriodStart(period, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNotionalAtPeriodStart instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualNotionalAtPeriodStart.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNotionalAtPeriodStart.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNotionalAtPeriodStart.getStandardError(), 0.0);
    assertEquals(0.0d, actualNotionalAtPeriodStart.getVariance(), 0.0);
    assertEquals(1, actualNotionalAtPeriodStart.getTypePriority());
    assertEquals(1.0d, actualNotionalAtPeriodStart.getAverage(), 0.0);
    assertEquals(1.0d, actualNotionalAtPeriodStart.getMax(), 0.0);
    assertEquals(1.0d, actualNotionalAtPeriodStart.getMin(), 0.0);
    assertEquals(10, actualNotionalAtPeriodStart.size());
    assertEquals(10.0d, actualNotionalAtPeriodStart.getFiltrationTime(), 0.0);
    assertFalse(actualNotionalAtPeriodStart.isDeterministic());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualNotionalAtPeriodStart.getRealizations(),
        0.0);
  }
}
