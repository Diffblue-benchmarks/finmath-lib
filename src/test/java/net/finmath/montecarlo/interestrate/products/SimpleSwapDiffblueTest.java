package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

public class SimpleSwapDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When array of {@code double} with ten and two.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SimpleSwap#SimpleSwap(double[], double[], double[], double[])}
   *   <li>{@link SimpleSwap#toString()}
   *   <li>{@link SimpleSwap#getFixingDates()}
   *   <li>{@link SimpleSwap#getNotional()}
   *   <li>{@link SimpleSwap#getPaymentDates()}
   *   <li>{@link SimpleSwap#getSwapRates()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleSwap.<init>(double[], double[], double[], boolean, double[])",
    "void SimpleSwap.<init>(double[], double[], double[], double[])",
    "double[] SimpleSwap.getFixingDates()",
    "double[] SimpleSwap.getNotional()",
    "double[] SimpleSwap.getPaymentDates()",
    "double[] SimpleSwap.getSwapRates()",
    "String SimpleSwap.toString()"
  })
  public void testGettersAndSetters_whenArrayOfDoubleWithTenAndTwo() {
    // Arrange
    double[] fixingDates = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] paymentDates = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] swaprates = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] notional = new double[] {10.0d, 2.0d, 10.0d, 2.0d};

    // Act
    SimpleSwap actualSimpleSwap = new SimpleSwap(fixingDates, paymentDates, swaprates, notional);
    String actualToStringResult = actualSimpleSwap.toString();
    double[] actualFixingDates = actualSimpleSwap.getFixingDates();
    double[] actualNotional = actualSimpleSwap.getNotional();
    double[] actualPaymentDates = actualSimpleSwap.getPaymentDates();
    double[] actualSwapRates = actualSimpleSwap.getSwapRates();

    // Assert
    assertEquals(
        "AbstractMonteCarloProduct [currency=null]\n"
            + "fixingDates: [10.0, 2.0, 10.0, 2.0]\n"
            + "paymentDates: [10.0, 2.0, 10.0, 2.0]\n"
            + "swaprates: [10.0, 2.0, 10.0, 2.0]",
        actualToStringResult);
    assertNull(actualSimpleSwap.getCurrency());
    assertSame(fixingDates, actualFixingDates);
    assertSame(notional, actualNotional);
    assertSame(paymentDates, actualPaymentDates);
    assertSame(swaprates, actualSwapRates);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualFixingDates, 0.0);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualNotional, 0.0);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualPaymentDates, 0.0);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSwapRates, 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SimpleSwap#SimpleSwap(double[], double[], double[], boolean, double[])}
   *   <li>{@link SimpleSwap#toString()}
   *   <li>{@link SimpleSwap#getFixingDates()}
   *   <li>{@link SimpleSwap#getNotional()}
   *   <li>{@link SimpleSwap#getPaymentDates()}
   *   <li>{@link SimpleSwap#getSwapRates()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleSwap.<init>(double[], double[], double[], boolean, double[])",
    "void SimpleSwap.<init>(double[], double[], double[], double[])",
    "double[] SimpleSwap.getFixingDates()",
    "double[] SimpleSwap.getNotional()",
    "double[] SimpleSwap.getPaymentDates()",
    "double[] SimpleSwap.getSwapRates()",
    "String SimpleSwap.toString()"
  })
  public void testGettersAndSetters_whenTrue() {
    // Arrange
    double[] fixingDates = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] paymentDates = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] swaprates = new double[] {10.0d, 2.0d, 10.0d, 2.0d};
    double[] notional = new double[] {10.0d, 2.0d, 10.0d, 2.0d};

    // Act
    SimpleSwap actualSimpleSwap =
        new SimpleSwap(fixingDates, paymentDates, swaprates, true, notional);
    String actualToStringResult = actualSimpleSwap.toString();
    double[] actualFixingDates = actualSimpleSwap.getFixingDates();
    double[] actualNotional = actualSimpleSwap.getNotional();
    double[] actualPaymentDates = actualSimpleSwap.getPaymentDates();
    double[] actualSwapRates = actualSimpleSwap.getSwapRates();

    // Assert
    assertEquals(
        "AbstractMonteCarloProduct [currency=null]\n"
            + "fixingDates: [10.0, 2.0, 10.0, 2.0]\n"
            + "paymentDates: [10.0, 2.0, 10.0, 2.0]\n"
            + "swaprates: [10.0, 2.0, 10.0, 2.0]",
        actualToStringResult);
    assertNull(actualSimpleSwap.getCurrency());
    assertSame(fixingDates, actualFixingDates);
    assertSame(notional, actualNotional);
    assertSame(paymentDates, actualPaymentDates);
    assertSame(swaprates, actualSwapRates);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualFixingDates, 0.0);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualNotional, 0.0);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualPaymentDates, 0.0);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSwapRates, 0.0);
  }

  /**
   * Test {@link SimpleSwap#SimpleSwap(double[], double[], double[])}.
   *
   * <p>Method under test: {@link SimpleSwap#SimpleSwap(double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleSwap.<init>(double[], double[], double[])"})
  public void testNewSimpleSwap() {
    // Arrange and Act
    SimpleSwap actualSimpleSwap =
        new SimpleSwap(
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Assert
    assertNull(actualSimpleSwap.getCurrency());
    assertEquals(10.0d, actualSimpleSwap.getStartTime(), 0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualSimpleSwap.getPeriodLengths(), 0.0);
    assertArrayEquals(new double[] {1.0d, 1.0d, 1.0d, 1.0d}, actualSimpleSwap.getNotional(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSimpleSwap.getFixingDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSimpleSwap.getPaymentDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSimpleSwap.getSwapRates(), 0.0);
  }

  /**
   * Test {@link SimpleSwap#SimpleSwap(double[], double[], double[], double)}.
   *
   * <p>Method under test: {@link SimpleSwap#SimpleSwap(double[], double[], double[], double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleSwap.<init>(double[], double[], double[], double)"})
  public void testNewSimpleSwap2() {
    // Arrange and Act
    SimpleSwap actualSimpleSwap =
        new SimpleSwap(
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            10.0d);

    // Assert
    assertNull(actualSimpleSwap.getCurrency());
    assertEquals(10.0d, actualSimpleSwap.getStartTime(), 0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualSimpleSwap.getPeriodLengths(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualSimpleSwap.getNotional(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSimpleSwap.getFixingDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSimpleSwap.getPaymentDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSimpleSwap.getSwapRates(), 0.0);
  }

  /**
   * Test {@link SimpleSwap#SimpleSwap(double[], double[], double[], boolean, double)}.
   *
   * <p>Method under test: {@link SimpleSwap#SimpleSwap(double[], double[], double[], boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleSwap.<init>(double[], double[], double[], boolean, double)"})
  public void testNewSimpleSwap3() {
    // Arrange and Act
    SimpleSwap actualSimpleSwap =
        new SimpleSwap(
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            true,
            10.0d);

    // Assert
    assertNull(actualSimpleSwap.getCurrency());
    assertEquals(10.0d, actualSimpleSwap.getStartTime(), 0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualSimpleSwap.getPeriodLengths(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualSimpleSwap.getNotional(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSimpleSwap.getFixingDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSimpleSwap.getPaymentDates(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSimpleSwap.getSwapRates(), 0.0);
  }

  /**
   * Test {@link SimpleSwap#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SimpleSwap#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleSwap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    SimpleSwap simpleSwap =
        new SimpleSwap(
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

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
    RandomVariable actualValue = simpleSwap.getValue(10.0d, model);

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
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(10, actualValue.size());
    assertEquals(10.0d, actualValue.getFiltrationTime(), 0.0);
    assertFalse(actualValue.isDeterministic());
    assertEquals(Double.NaN, actualValue.getAverage(), 0.0);
    assertEquals(Double.NaN, actualValue.getMax(), 0.0);
    assertEquals(Double.NaN, actualValue.getMin(), 0.0);
    assertEquals(Double.NaN, actualValue.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualValue.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualValue.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualValue.getVariance(), 0.0);
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
   * Test {@link SimpleSwap#getStartTime()}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link SimpleSwap#getStartTime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SimpleSwap.getStartTime()"})
  public void testGetStartTime_thenReturnTen() {
    // Arrange
    SimpleSwap simpleSwap =
        new SimpleSwap(
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(10.0d, simpleSwap.getStartTime(), 0.0);
  }

  /**
   * Test {@link SimpleSwap#getPeriodLengths()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with zero and zero.
   * </ul>
   *
   * <p>Method under test: {@link SimpleSwap#getPeriodLengths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SimpleSwap.getPeriodLengths()"})
  public void testGetPeriodLengths_thenReturnArrayOfDoubleWithZeroAndZero() {
    // Arrange
    SimpleSwap simpleSwap =
        new SimpleSwap(
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d}, simpleSwap.getPeriodLengths(), 0.0);
  }

  /**
   * Test {@link SimpleSwap#getPeriodLengths()}.
   *
   * <ul>
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleSwap#getPeriodLengths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SimpleSwap.getPeriodLengths()"})
  public void testGetPeriodLengths_thenReturnEmptyArrayOfDouble() {
    // Arrange
    SimpleSwap simpleSwap =
        new SimpleSwap(
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertArrayEquals(new double[] {}, simpleSwap.getPeriodLengths(), 0.0);
  }
}
