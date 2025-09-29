package net.finmath.montecarlo.interestrate.products.indices;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Set;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.products.components.AbstractProductComponent;
import net.finmath.montecarlo.interestrate.products.components.Numeraire;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MinIndexDiffblueTest {
  /**
   * Test {@link MinIndex#MinIndex(AbstractProductComponent[])}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link MinIndex#MinIndex(AbstractProductComponent[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MinIndex.<init>(AbstractProductComponent[])"})
  public void testNewMinIndex_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new MinIndex());
  }

  /**
   * Test {@link MinIndex#MinIndex(AbstractProductComponent[])}.
   *
   * <ul>
   *   <li>When {@link Numeraire} (default constructor).
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MinIndex#MinIndex(AbstractProductComponent[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MinIndex.<init>(AbstractProductComponent[])"})
  public void testNewMinIndex_whenNumeraire_thenReturnCurrencyIsNull() {
    // Arrange and Act
    MinIndex actualMinIndex = new MinIndex(new Numeraire());

    // Assert
    assertNull(actualMinIndex.getCurrency());
    assertNull(actualMinIndex.getName());
  }

  /**
   * Test {@link MinIndex#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link MinIndex#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MinIndex.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    MinIndex minIndex = new MinIndex(new Numeraire());

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
    RandomVariable actualValue = minIndex.getValue(10.0d, model);

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
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(1.0d, actualValue.getAverage(), 0.0);
    assertEquals(1.0d, actualValue.getMax(), 0.0);
    assertEquals(1.0d, actualValue.getMin(), 0.0);
    assertEquals(10, actualValue.size());
    assertEquals(10.0d, actualValue.getFiltrationTime(), 0.0);
    assertFalse(actualValue.isDeterministic());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MinIndex#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Given {@link MinIndex#MinIndex(AbstractProductComponent[])} with indexArguments is {@link
   *       Numeraire} (default constructor).
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MinIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set MinIndex.queryUnderlyings()"})
  public void testQueryUnderlyings_givenMinIndexWithIndexArgumentsIsNumeraire_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new MinIndex(new Numeraire()).queryUnderlyings());
  }

  /**
   * Test {@link MinIndex#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link MinIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set MinIndex.queryUnderlyings()"})
  public void testQueryUnderlyings_thenReturnSizeIsOne() {
    // Arrange and Act
    Set<String> actualQueryUnderlyingsResult =
        new MinIndex(new ConstantMaturitySwaprate(10.0d, 10.0d)).queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains(null));
  }

  /**
   * Test {@link MinIndex#toString()}.
   *
   * <p>Method under test: {@link MinIndex#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MinIndex.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "MinIndex [indexArguments=[AbstractMonteCarloProduct [currency=null]]]",
        new MinIndex(new Numeraire()).toString());
  }
}
