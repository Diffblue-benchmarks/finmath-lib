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
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ConstantMaturitySwaprateDiffblueTest {
  /**
   * Test {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(double, double[])}.
   *
   * <p>Method under test: {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConstantMaturitySwaprate.<init>(double, double[])"})
  public void testNewConstantMaturitySwaprate() {
    // Arrange and Act
    ConstantMaturitySwaprate actualConstantMaturitySwaprate =
        new ConstantMaturitySwaprate(10.0d, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertNull(actualConstantMaturitySwaprate.getCurrency());
    assertNull(actualConstantMaturitySwaprate.getName());
  }

  /**
   * Test {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(String, String, double,
   * double[])}.
   *
   * <p>Method under test: {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(String, String,
   * double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConstantMaturitySwaprate.<init>(String, String, double, double[])"})
  public void testNewConstantMaturitySwaprate2() {
    // Arrange and Act
    ConstantMaturitySwaprate actualConstantMaturitySwaprate =
        new ConstantMaturitySwaprate("Name", "GBP", 10.0d, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertEquals("GBP", actualConstantMaturitySwaprate.getCurrency());
    assertEquals("Name", actualConstantMaturitySwaprate.getName());
  }

  /**
   * Test {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(double[])}.
   *
   * <p>Method under test: {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConstantMaturitySwaprate.<init>(double[])"})
  public void testNewConstantMaturitySwaprate3() {
    // Arrange and Act
    ConstantMaturitySwaprate actualConstantMaturitySwaprate =
        new ConstantMaturitySwaprate(new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertNull(actualConstantMaturitySwaprate.getCurrency());
    assertNull(actualConstantMaturitySwaprate.getName());
  }

  /**
   * Test {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConstantMaturitySwaprate.<init>(double, double)"})
  public void testNewConstantMaturitySwaprate_when05_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new ConstantMaturitySwaprate(0.5d, 10.0d));
  }

  /**
   * Test {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConstantMaturitySwaprate.<init>(double, double, double)"})
  public void testNewConstantMaturitySwaprate_when05_thenThrowIllegalArgumentException2() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> new ConstantMaturitySwaprate(10.0d, 0.5d, 10.0d));
  }

  /**
   * Test {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(String, String, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(String, String,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConstantMaturitySwaprate.<init>(String, String, double, double, double)"
  })
  public void testNewConstantMaturitySwaprate_when05_thenThrowIllegalArgumentException3() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new ConstantMaturitySwaprate("Name", "GBP", 10.0d, 0.5d, 10.0d));
  }

  /**
   * Test {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(String, String, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return Currency is {@code GBP}.
   * </ul>
   *
   * <p>Method under test: {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(String, String,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConstantMaturitySwaprate.<init>(String, String, double, double, double)"
  })
  public void testNewConstantMaturitySwaprate_whenName_thenReturnCurrencyIsGbp() {
    // Arrange and Act
    ConstantMaturitySwaprate actualConstantMaturitySwaprate =
        new ConstantMaturitySwaprate("Name", "GBP", 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals("GBP", actualConstantMaturitySwaprate.getCurrency());
    assertEquals("Name", actualConstantMaturitySwaprate.getName());
  }

  /**
   * Test {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConstantMaturitySwaprate.<init>(double, double)"})
  public void testNewConstantMaturitySwaprate_whenTen_thenReturnCurrencyIsNull() {
    // Arrange and Act
    ConstantMaturitySwaprate actualConstantMaturitySwaprate =
        new ConstantMaturitySwaprate(10.0d, 10.0d);

    // Assert
    assertNull(actualConstantMaturitySwaprate.getCurrency());
    assertNull(actualConstantMaturitySwaprate.getName());
  }

  /**
   * Test {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(double, double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ConstantMaturitySwaprate#ConstantMaturitySwaprate(double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConstantMaturitySwaprate.<init>(double, double, double)"})
  public void testNewConstantMaturitySwaprate_whenTen_thenReturnCurrencyIsNull2() {
    // Arrange and Act
    ConstantMaturitySwaprate actualConstantMaturitySwaprate =
        new ConstantMaturitySwaprate(10.0d, 10.0d, 10.0d);

    // Assert
    assertNull(actualConstantMaturitySwaprate.getCurrency());
    assertNull(actualConstantMaturitySwaprate.getName());
  }

  /**
   * Test {@link ConstantMaturitySwaprate#getValue(double, TermStructureMonteCarloSimulationModel)}
   * with {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link ConstantMaturitySwaprate#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ConstantMaturitySwaprate.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    ConstantMaturitySwaprate constantMaturitySwaprate = new ConstantMaturitySwaprate(10.0d, 10.0d);

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
    RandomVariable actualValue = constantMaturitySwaprate.getValue(10.0d, model);

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
    assertEquals(0.0d, actualValue.getAverage(), 0.0);
    assertEquals(0.0d, actualValue.getMax(), 0.0);
    assertEquals(0.0d, actualValue.getMin(), 0.0);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(10, actualValue.size());
    assertEquals(10.0d, actualValue.getFiltrationTime(), 0.0);
    assertFalse(actualValue.isDeterministic());
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link ConstantMaturitySwaprate#queryUnderlyings()}.
   *
   * <p>Method under test: {@link ConstantMaturitySwaprate#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set ConstantMaturitySwaprate.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange and Act
    Set<String> actualQueryUnderlyingsResult =
        new ConstantMaturitySwaprate(10.0d, 10.0d).queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains(null));
  }

  /**
   * Test {@link ConstantMaturitySwaprate#toString()}.
   *
   * <p>Method under test: {@link ConstantMaturitySwaprate#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ConstantMaturitySwaprate.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "ConstantMaturitySwaprate [fixingOffset=0.0, periodLengths=[10.0]]",
        new ConstantMaturitySwaprate(10.0d, 10.0d).toString());
  }
}
