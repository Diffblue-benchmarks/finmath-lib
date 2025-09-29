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
import java.util.ArrayList;
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
import net.finmath.montecarlo.interestrate.products.components.Notional;
import net.finmath.montecarlo.interestrate.products.components.NotionalFromConstant;
import net.finmath.montecarlo.interestrate.products.indices.AbstractIndex;
import net.finmath.montecarlo.interestrate.products.indices.FixedCoupon;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.Period;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.ScheduleFromPeriods;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SwapDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Swap#Swap(TermStructureMonteCarloProduct, TermStructureMonteCarloProduct)}
   *   <li>{@link Swap#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Swap.<init>(TermStructureMonteCarloProduct, TermStructureMonteCarloProduct)",
    "java.lang.String Swap.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature legReceiver =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);

    // Act
    Swap actualSwap = new Swap(legReceiver, new ForwardRateVolatilitySurfaceCurvature(10.0d));

    // Assert
    assertEquals(
        "Swap [legReceiver=AbstractMonteCarloProduct [currency=null], legPayer=AbstractMonteCarloProduct"
            + " [currency=null]]",
        actualSwap.toString());
    assertNull(actualSwap.getCurrency());
  }

  /**
   * Test {@link Swap#Swap(Notional, Schedule, AbstractIndex, double, Schedule, AbstractIndex,
   * double)}.
   *
   * <p>Method under test: {@link Swap#Swap(Notional, Schedule, AbstractIndex, double, Schedule,
   * AbstractIndex, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Swap.<init>(Notional, Schedule, AbstractIndex, double, Schedule, AbstractIndex, double)"
  })
  public void testNewSwap() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods scheduleReceiveLeg =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods2 = new ArrayList<>();

    ScheduleFromPeriods schedulePayLeg =
        new ScheduleFromPeriods(referenceDate2, periods2, new DayCountConvention_30E_360(true));

    // Act
    Swap actualSwap =
        new Swap(notional, scheduleReceiveLeg, null, 0.0d, schedulePayLeg, null, 0.0d);

    // Assert
    assertNull(actualSwap.getCurrency());
  }

  /**
   * Test {@link Swap#Swap(Notional, Schedule, AbstractIndex, double, Schedule, AbstractIndex,
   * double)}.
   *
   * <p>Method under test: {@link Swap#Swap(Notional, Schedule, AbstractIndex, double, Schedule,
   * AbstractIndex, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Swap.<init>(Notional, Schedule, AbstractIndex, double, Schedule, AbstractIndex, double)"
  })
  public void testNewSwap2() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleReceiveLeg =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    FixedCoupon indexReceiveLeg = new FixedCoupon(10.0d);
    RegularSchedule schedulePayLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Swap actualSwap =
        new Swap(
            notional,
            scheduleReceiveLeg,
            indexReceiveLeg,
            10.0d,
            schedulePayLeg,
            new FixedCoupon(10.0d),
            10.0d);

    // Assert
    assertNull(actualSwap.getCurrency());
  }

  /**
   * Test {@link Swap#Swap(double[], double[], double[])}.
   *
   * <p>Method under test: {@link Swap#Swap(double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Swap.<init>(double[], double[], double[])"})
  public void testNewSwap3() {
    // Arrange and Act
    Swap actualSwap =
        new Swap(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertNull(actualSwap.getCurrency());
  }

  /**
   * Test {@link Swap#Swap(Notional, Schedule, AbstractIndex, double, Schedule, AbstractIndex,
   * double)}.
   *
   * <ul>
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#Swap(Notional, Schedule, AbstractIndex, double, Schedule,
   * AbstractIndex, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Swap.<init>(Notional, Schedule, AbstractIndex, double, Schedule, AbstractIndex, double)"
  })
  public void testNewSwap_thenReturnCurrencyIsNull() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    FixedCoupon indexReceiveLeg = new FixedCoupon(10.0d);
    RegularSchedule schedulePayLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Swap actualSwap =
        new Swap(
            notional,
            scheduleReceiveLeg,
            indexReceiveLeg,
            10.0d,
            schedulePayLeg,
            new FixedCoupon(10.0d),
            10.0d);

    // Assert
    assertNull(actualSwap.getCurrency());
  }

  /**
   * Test {@link Swap#Swap(Notional, Schedule, AbstractIndex, double, Schedule, AbstractIndex,
   * double)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#Swap(Notional, Schedule, AbstractIndex, double, Schedule,
   * AbstractIndex, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Swap.<init>(Notional, Schedule, AbstractIndex, double, Schedule, AbstractIndex, double)"
  })
  public void testNewSwap_whenNull_thenReturnCurrencyIsNull() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule schedulePayLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Swap actualSwap =
        new Swap(
            notional,
            scheduleReceiveLeg,
            null,
            10.0d,
            schedulePayLeg,
            new FixedCoupon(10.0d),
            10.0d);

    // Assert
    assertNull(actualSwap.getCurrency());
  }

  /**
   * Test {@link Swap#Swap(Notional, Schedule, AbstractIndex, double, Schedule, AbstractIndex,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Swap#Swap(Notional, Schedule, AbstractIndex, double, Schedule,
   * AbstractIndex, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Swap.<init>(Notional, Schedule, AbstractIndex, double, Schedule, AbstractIndex, double)"
  })
  public void testNewSwap_whenZero_thenReturnCurrencyIsNull() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    RegularSchedule scheduleReceiveLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    FixedCoupon indexReceiveLeg = new FixedCoupon(10.0d);
    RegularSchedule schedulePayLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Swap actualSwap =
        new Swap(
            notional,
            scheduleReceiveLeg,
            indexReceiveLeg,
            0.0d,
            schedulePayLeg,
            new FixedCoupon(10.0d),
            10.0d);

    // Assert
    assertNull(actualSwap.getCurrency());
  }

  /**
   * Test {@link Swap#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code double},
   * {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link Swap#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    Swap swap = new Swap(new MoneyMarketAccount(), null);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        swap.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.MAX_VALUE, actualValue.getAverage(), 0.0);
    assertEquals(Double.MAX_VALUE, actualValue.getMax(), 0.0);
    assertEquals(Double.MAX_VALUE, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.MAX_VALUE}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code double},
   * {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link Swap#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    MoneyMarketAccount legReceiver = new MoneyMarketAccount();
    Swap swap = new Swap(legReceiver, new MoneyMarketAccount());

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        swap.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualValue.getAverage(), 0.0);
    assertEquals(0.0d, actualValue.getMax(), 0.0);
    assertEquals(0.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link Swap#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code double},
   * {@code TermStructureMonteCarloSimulationModel}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link Swap#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Swap.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel_thenReturnSizeIsTen()
      throws CalculationException {
    // Arrange
    Swap swap =
        new Swap(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

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
    RandomVariable actualValue = swap.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
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
}
