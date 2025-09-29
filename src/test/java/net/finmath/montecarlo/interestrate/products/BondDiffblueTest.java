package net.finmath.montecarlo.interestrate.products;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BondDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one atStartOfDay.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Bond#Bond(LocalDateTime, double)}
   *   <li>{@link Bond#setMaturity(double)}
   *   <li>{@link Bond#toString()}
   *   <li>{@link Bond#getMaturity()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Bond.<init>(double)",
    "void Bond.<init>(LocalDateTime, double)",
    "double Bond.getMaturity()",
    "void Bond.setMaturity(double)",
    "String Bond.toString()"
  })
  public void testGettersAndSetters_whenLocalDateWith1970AndOneAndOneAtStartOfDay() {
    // Arrange and Act
    Bond actualBond = new Bond(LocalDate.of(1970, 1, 1).atStartOfDay(), 10.0d);
    actualBond.setMaturity(10.0d);
    String actualToStringResult = actualBond.toString();
    double actualMaturity = actualBond.getMaturity();

    // Assert
    assertEquals("AbstractMonteCarloProduct [currency=null]\nmaturity: 10.0", actualToStringResult);
    assertNull(actualBond.getCurrency());
    assertEquals(10.0d, actualMaturity, 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When ten.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Bond#Bond(double)}
   *   <li>{@link Bond#setMaturity(double)}
   *   <li>{@link Bond#toString()}
   *   <li>{@link Bond#getMaturity()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Bond.<init>(double)",
    "void Bond.<init>(LocalDateTime, double)",
    "double Bond.getMaturity()",
    "void Bond.setMaturity(double)",
    "String Bond.toString()"
  })
  public void testGettersAndSetters_whenTen() {
    // Arrange and Act
    Bond actualBond = new Bond(10.0d);
    actualBond.setMaturity(10.0d);
    String actualToStringResult = actualBond.toString();
    double actualMaturity = actualBond.getMaturity();

    // Assert
    assertEquals("AbstractMonteCarloProduct [currency=null]\nmaturity: 10.0", actualToStringResult);
    assertNull(actualBond.getCurrency());
    assertEquals(10.0d, actualMaturity, 0.0);
  }

  /**
   * Test {@link Bond#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code double},
   * {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link Bond#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Bond.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    Bond bond = new Bond(10.0d);

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
        new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = bond.getValue(10.0d, model);

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
}
