package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
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
import java.time.LocalDate;
import java.util.ArrayList;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelExponentialForm5Param;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelStochasticHestonVolatility;
import net.finmath.montecarlo.interestrate.products.components.Notional;
import net.finmath.montecarlo.interestrate.products.components.NotionalFromConstant;
import net.finmath.montecarlo.interestrate.products.indices.AbstractIndex;
import net.finmath.montecarlo.interestrate.products.indices.FixedCoupon;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
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
import org.mockito.Mockito;

public class SwapLegDiffblueTest {
  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double, boolean)}.
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, Notional, AbstractIndex, double, boolean)"})
  public void testNewSwapLeg() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);

    // Act
    SwapLeg actualSwapLeg = new SwapLeg(legSchedule, notional, new FixedCoupon(10.0d), 10.0d, true);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double, boolean)}.
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, Notional, AbstractIndex, double, boolean)"})
  public void testNewSwapLeg2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods legSchedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(legSchedule, new NotionalFromConstant(10.0d), null, 0.0d, true);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double, boolean)}.
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, Notional, AbstractIndex, double, boolean)"})
  public void testNewSwapLeg3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods legSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);

    // Act
    SwapLeg actualSwapLeg = new SwapLeg(legSchedule, notional, new FixedCoupon(10.0d), 10.0d, true);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double, boolean)}.
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, Notional, AbstractIndex, double, boolean)"})
  public void testNewSwapLeg4() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(legSchedule, new NotionalFromConstant(10.0d), null, 10.0d, true);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double, boolean, boolean,
   * boolean)}.
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(Schedule, Notional, AbstractIndex, double, boolean, boolean, boolean)"
  })
  public void testNewSwapLeg5() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(legSchedule, notional, new FixedCoupon(10.0d), 10.0d, true, true, true);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double, boolean, boolean,
   * boolean)}.
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(Schedule, Notional, AbstractIndex, double, boolean, boolean, boolean)"
  })
  public void testNewSwapLeg6() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods legSchedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(legSchedule, new NotionalFromConstant(10.0d), null, 0.0d, true, true, false);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double, boolean, boolean,
   * boolean)}.
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(Schedule, Notional, AbstractIndex, double, boolean, boolean, boolean)"
  })
  public void testNewSwapLeg7() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods legSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(legSchedule, notional, new FixedCoupon(10.0d), 10.0d, true, true, true);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional[], AbstractIndex, double[], boolean, boolean)}.
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional[], AbstractIndex, double[],
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(Schedule, Notional[], AbstractIndex, double[], boolean, boolean)"
  })
  public void testNewSwapLeg8() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods legSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    Notional[] notionals = new Notional[] {new NotionalFromConstant(10.0d)};

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(
            legSchedule,
            notionals,
            new FixedCoupon(10.0d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            true,
            true);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional[], AbstractIndex, double[], boolean, boolean)}.
   *
   * <ul>
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional[], AbstractIndex, double[],
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(Schedule, Notional[], AbstractIndex, double[], boolean, boolean)"
  })
  public void testNewSwapLeg_thenReturnCurrencyIsNull() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.now(),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods legSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    Notional[] notionals = new Notional[] {new NotionalFromConstant(10.0d)};

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(
            legSchedule,
            notionals,
            new FixedCoupon(10.0d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            true,
            true);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional[], AbstractIndex, double[], boolean, boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional[], AbstractIndex, double[],
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(Schedule, Notional[], AbstractIndex, double[], boolean, boolean)"
  })
  public void testNewSwapLeg_thenThrowIllegalArgumentException() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    Notional[] notionals = new Notional[] {new NotionalFromConstant(10.0d)};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new SwapLeg(
                legSchedule,
                notionals,
                new FixedCoupon(10.0d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                true,
                true));
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional[], AbstractIndex, double[], boolean, boolean)}.
   *
   * <ul>
   *   <li>When array of {@code double} with zero and zero.
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional[], AbstractIndex, double[],
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(Schedule, Notional[], AbstractIndex, double[], boolean, boolean)"
  })
  public void testNewSwapLeg_whenArrayOfDoubleWithZeroAndZero_thenReturnCurrencyIsNull() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.now(),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods legSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    Notional[] notionals = new Notional[] {new NotionalFromConstant(10.0d)};

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(
            legSchedule,
            notionals,
            new FixedCoupon(10.0d),
            new double[] {
              0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d,
              0.0d, 0.0d, 0.0d, 0.0d
            },
            true,
            true);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(Schedule, Notional, AbstractIndex, double, boolean, boolean, boolean)"
  })
  public void testNewSwapLeg_whenFalse_thenReturnCurrencyIsNull() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(legSchedule, notional, new FixedCoupon(10.0d), 10.0d, true, true, false);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(Schedule, Notional, AbstractIndex, double, boolean, boolean, boolean)"
  })
  public void testNewSwapLeg_whenNull_thenReturnCurrencyIsNull() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(legSchedule, new NotionalFromConstant(10.0d), null, 10.0d, true, true, true);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional[], AbstractIndex, double[], boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional[], AbstractIndex, double[],
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(Schedule, Notional[], AbstractIndex, double[], boolean, boolean)"
  })
  public void testNewSwapLeg_whenNull_thenReturnCurrencyIsNull2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.now(),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods legSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    Notional[] notionals = new Notional[] {new NotionalFromConstant(10.0d)};

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(
            legSchedule, notionals, null, new double[] {10.0d, 1.0d, 10.0d, 1.0d}, true, true);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double, boolean)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, Notional, AbstractIndex, double, boolean)"})
  public void testNewSwapLeg_whenZero_thenReturnCurrencyIsNull() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);

    // Act
    SwapLeg actualSwapLeg = new SwapLeg(legSchedule, notional, new FixedCoupon(10.0d), 0.0d, true);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, Notional, AbstractIndex, double,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(Schedule, Notional, AbstractIndex, double, boolean, boolean, boolean)"
  })
  public void testNewSwapLeg_whenZero_thenReturnCurrencyIsNull2() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(legSchedule, notional, new FixedCoupon(10.0d), 0.0d, true, true, true);

    // Assert
    assertNull(actualSwapLeg.getCurrency());
  }

  /**
   * Test {@link SwapLeg#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwapLeg#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapLeg.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);

    SwapLeg swapLeg = new SwapLeg(legSchedule, notional, new FixedCoupon(10.0d), 10.0d, true);

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
        new TenorFromArray(10.5d, 10.5d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = swapLeg.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(1010.0d, actualValue.getAverage(), 0.0);
    assertEquals(1010.0d, actualValue.getMax(), 0.0);
    assertEquals(1010.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(
        new double[] {
          1010.0d, 1010.0d, 1010.0d, 1010.0d, 1010.0d, 1010.0d, 1010.0d, 1010.0d, 1010.0d, 1010.0d
        },
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SwapLeg#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwapLeg#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapLeg.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);

    SwapLeg swapLeg = new SwapLeg(legSchedule, notional, new FixedCoupon(10.0d), 10.0d, true);

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
    RandomVariable actualValue = swapLeg.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(96960.0d, actualValue.getAverage(), 0.0);
    assertEquals(96960.0d, actualValue.getMax(), 0.0);
    assertEquals(96960.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(
        new double[] {
          96960.0d, 96960.0d, 96960.0d, 96960.0d, 96960.0d, 96960.0d, 96960.0d, 96960.0d, 96960.0d,
          96960.0d
        },
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SwapLeg#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwapLeg#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapLeg.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);

    SwapLeg swapLeg = new SwapLeg(legSchedule, notional, new FixedCoupon(10.0d), 10.0d, true);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.5d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.5d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.5d)
            });
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
            new double[] {
              10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d
            },
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = swapLeg.getValue(10.0d, model);

    // Assert
    verify(covarianceModel, atLeast(1))
        .getFactorLoading(eq(0), anyInt(), (RandomVariable[]) isNull());
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
   * Test {@link SwapLeg#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwapLeg#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapLeg.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel4()
      throws CalculationException {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);

    SwapLeg swapLeg = new SwapLeg(legSchedule, notional, new FixedCoupon(10.0d), 10.0d, true);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {
              10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d
            },
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = swapLeg.getValue(10.0d, model);

    // Assert
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.23457258019275015d, actualValue.getStandardError(), 0.0);
    assertEquals(0.550242953782842d, actualValue.getVariance(), 0.0);
    assertEquals(0.6113810597587134d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.7417836300315895d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(18.145297072503162d, actualValue.getMin(), 0.0);
    assertEquals(19.124943231888203d, actualValue.getAverage(), 0.0);
    assertEquals(20.433512181626803d, actualValue.getMax(), 0.0);
    assertArrayEquals(
        new double[] {
          19.158200639129294d,
          19.01693613848735d,
          18.199436572962526d,
          19.289443713944543d,
          18.998743978419185d,
          18.302692605783474d,
          20.433512181626803d,
          19.550070995036652d,
          20.155098420989038d,
          18.145297072503162d
        },
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SwapLeg#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwapLeg#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapLeg.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel5()
      throws CalculationException {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(1.0d, 10, 0.5d));
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);

    SwapLeg swapLeg = new SwapLeg(legSchedule, notional, new FixedCoupon(10.0d), 10.0d, true);

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
            new double[] {
              10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d
            },
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = swapLeg.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualValue.getAverage(), 0.0);
    assertEquals(0.0d, actualValue.getMax(), 0.0);
    assertEquals(0.0d, actualValue.getMin(), 0.0);
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwapLeg#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwapLeg#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwapLeg.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel6()
      throws CalculationException {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);

    SwapLeg swapLeg = new SwapLeg(legSchedule, notional, new FixedCoupon(10.0d), 10.0d, true);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.5d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.5d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.5d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    LIBORCovarianceModelStochasticHestonVolatility covarianceModel3 =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion2 =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {
              10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d, 10.5d, 10.0d
            },
            covarianceModel4,
            new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    RandomVariable actualValue = swapLeg.getValue(10.0d, model);

    // Assert
    verify(covarianceModel, atLeast(1))
        .getFactorLoading(eq(0), anyInt(), (RandomVariable[]) isNull());
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
}
