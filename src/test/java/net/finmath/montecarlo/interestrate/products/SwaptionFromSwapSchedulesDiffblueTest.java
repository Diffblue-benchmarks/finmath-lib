package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import net.finmath.exception.CalculationException;
import net.finmath.modelling.products.Swaption;
import net.finmath.modelling.products.Swaption.ValueUnit;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.HullWhiteModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.products.SwaptionFromSwapSchedules.SwaptionType;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.Period;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.ScheduleFromPeriods;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SwaptionFromSwapSchedulesDiffblueTest {
  /**
   * Test {@link SwaptionFromSwapSchedules#SwaptionFromSwapSchedules(LocalDateTime, SwaptionType,
   * LocalDate, Schedule, Schedule, double, double, ValueUnit)}.
   *
   * <p>Method under test: {@link SwaptionFromSwapSchedules#SwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate, Schedule, Schedule, double, double, Swaption.ValueUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionFromSwapSchedules.<init>(LocalDateTime, SwaptionType, LocalDate, Schedule, Schedule, double, double, Swaption.ValueUnit)"
  })
  public void testNewSwaptionFromSwapSchedules() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate exerciseDate = LocalDate.of(1970, 1, 1);
    RegularSchedule scheduleFixedLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwaptionFromSwapSchedules actualSwaptionFromSwapSchedules =
        new SwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDate,
            scheduleFixedLeg,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            10.0d,
            10.0d,
            ValueUnit.VALUE);

    // Assert
    assertNull(actualSwaptionFromSwapSchedules.getCurrency());
    assertSame(exerciseDate, actualSwaptionFromSwapSchedules.getExerciseDate());
  }

  /**
   * Test {@link SwaptionFromSwapSchedules#getValue(double, TermStructureMonteCarloSimulationModel)}
   * with {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate exerciseDate = LocalDate.of(1970, 1, 1);
    RegularSchedule scheduleFixedLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    SwaptionFromSwapSchedules swaptionFromSwapSchedules =
        new SwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDate,
            scheduleFixedLeg,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            10.0d,
            10.0d,
            ValueUnit.VALUE);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenThrow(new UnsupportedOperationException());
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> swaptionFromSwapSchedules.getValue(10.0d, model));
    verify(model).getRandomVariableForConstant(0.0d);
    verify(model, atLeast(1)).getReferenceDate();
  }

  /**
   * Test {@link SwaptionFromSwapSchedules#getValue(double, TermStructureMonteCarloSimulationModel)}
   * with {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate exerciseDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFixedLeg =
        new ScheduleFromPeriods(referenceDate2, daycountconvention, period);

    SwaptionFromSwapSchedules swaptionFromSwapSchedules =
        new SwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDate,
            scheduleFixedLeg,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            10.0d,
            10.0d,
            ValueUnit.VALUE);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getModel()).thenThrow(new UnsupportedOperationException());
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> swaptionFromSwapSchedules.getValue(10.0d, model));
    verify(model).getModel();
    verify(model).getRandomVariableForConstant(0.0d);
    verify(model, atLeast(1)).getReferenceDate();
  }

  /**
   * Test {@link SwaptionFromSwapSchedules#getValue(double, TermStructureMonteCarloSimulationModel)}
   * with {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate exerciseDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFixedLeg =
        new ScheduleFromPeriods(referenceDate2, daycountconvention, period);

    SwaptionFromSwapSchedules swaptionFromSwapSchedules =
        new SwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDate,
            scheduleFixedLeg,
            new RegularSchedule(
                new TenorFromArray(
                    365.0d, 365.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START)),
            10.0d,
            10.0d,
            ValueUnit.VALUE);

    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    when(hullWhiteModel.getForwardDiscountBond(
            Mockito.<MonteCarloProcess>any(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getModel()).thenReturn(hullWhiteModel);
    BachelierModel model2 = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model2, new BrownianMotionWithControlVariate(brownianMotion));
    when(model.getProcess()).thenReturn(eulerSchemeFromProcessModel);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = swaptionFromSwapSchedules.getValue(10.0d, model);

    // Assert
    verify(model).getModel();
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model).getProcess();
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model, atLeast(1)).getReferenceDate();
    verify(hullWhiteModel).getForwardDiscountBond(isA(MonteCarloProcess.class), eq(0.0d), eq(0.0d));
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
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwaptionFromSwapSchedules#getValue(double, TermStructureMonteCarloSimulationModel)}
   * with {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel4()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate exerciseDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFixedLeg =
        new ScheduleFromPeriods(referenceDate2, daycountconvention, period);

    SwaptionFromSwapSchedules swaptionFromSwapSchedules =
        new SwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDate,
            scheduleFixedLeg,
            new RegularSchedule(
                new TenorFromArray(
                    365.0d, 365.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START)),
            10.0d,
            10.0d,
            ValueUnit.VALUE);

    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    when(hullWhiteModel.getForwardDiscountBond(
            Mockito.<MonteCarloProcess>any(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getNumeraire(anyDouble())).thenThrow(new UnsupportedOperationException());
    when(model.getModel()).thenReturn(hullWhiteModel);
    BachelierModel model2 = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model2, new BrownianMotionWithControlVariate(brownianMotion));
    when(model.getProcess()).thenReturn(eulerSchemeFromProcessModel);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> swaptionFromSwapSchedules.getValue(10.0d, model));
    verify(model).getModel();
    verify(model).getNumeraire(0.0d);
    verify(model).getProcess();
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model, atLeast(1)).getReferenceDate();
    verify(hullWhiteModel).getForwardDiscountBond(isA(MonteCarloProcess.class), eq(0.0d), eq(0.0d));
  }

  /**
   * Test {@link SwaptionFromSwapSchedules#getValue(double, TermStructureMonteCarloSimulationModel)}
   * with {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel5()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate exerciseDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFixedLeg =
        new ScheduleFromPeriods(referenceDate2, daycountconvention, period);
    LocalDate referenceDate3 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention2 = new DayCountConvention_30E_360(true);
    Period period2 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFloatLeg =
        new ScheduleFromPeriods(referenceDate3, daycountconvention2, period2);

    SwaptionFromSwapSchedules swaptionFromSwapSchedules =
        new SwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDate,
            scheduleFixedLeg,
            scheduleFloatLeg,
            10.0d,
            10.0d,
            ValueUnit.VALUE);

    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    when(hullWhiteModel.getForwardDiscountBond(
            Mockito.<MonteCarloProcess>any(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getModel()).thenReturn(hullWhiteModel);
    BachelierModel model2 = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model2, new BrownianMotionWithControlVariate(brownianMotion));
    when(model.getProcess()).thenReturn(eulerSchemeFromProcessModel);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = swaptionFromSwapSchedules.getValue(10.0d, model);

    // Assert
    verify(model).getForwardRate(0.0d, 0.0d, 0.0d);
    verify(model, atLeast(1)).getModel();
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getProcess();
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model, atLeast(1)).getReferenceDate();
    verify(hullWhiteModel, atLeast(1))
        .getForwardDiscountBond(isA(MonteCarloProcess.class), eq(0.0d), eq(0.0d));
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
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SwaptionFromSwapSchedules#getValue(double, TermStructureMonteCarloSimulationModel)}
   * with {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel6()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate exerciseDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFixedLeg =
        new ScheduleFromPeriods(referenceDate2, daycountconvention, period);
    LocalDate referenceDate3 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention2 = new DayCountConvention_30E_360(true);
    Period period2 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFloatLeg =
        new ScheduleFromPeriods(referenceDate3, daycountconvention2, period2);

    SwaptionFromSwapSchedules swaptionFromSwapSchedules =
        new SwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDate,
            scheduleFixedLeg,
            scheduleFloatLeg,
            10.0d,
            10.0d,
            ValueUnit.VALUE);

    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    when(hullWhiteModel.getForwardDiscountBond(
            Mockito.<MonteCarloProcess>any(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenThrow(new UnsupportedOperationException());
    when(model.getModel()).thenReturn(hullWhiteModel);
    BachelierModel model2 = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model2, new BrownianMotionWithControlVariate(brownianMotion));
    when(model.getProcess()).thenReturn(eulerSchemeFromProcessModel);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> swaptionFromSwapSchedules.getValue(10.0d, model));
    verify(model).getForwardRate(0.0d, 0.0d, 0.0d);
    verify(model, atLeast(1)).getModel();
    verify(model, atLeast(1)).getProcess();
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model, atLeast(1)).getReferenceDate();
    verify(hullWhiteModel, atLeast(1))
        .getForwardDiscountBond(isA(MonteCarloProcess.class), eq(0.0d), eq(0.0d));
  }

  /**
   * Test {@link SwaptionFromSwapSchedules#getProcessTimeDiscretization(LocalDateTime)}.
   *
   * <p>Method under test: {@link
   * SwaptionFromSwapSchedules#getProcessTimeDiscretization(LocalDateTime)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization SwaptionFromSwapSchedules.getProcessTimeDiscretization(LocalDateTime)"
  })
  public void testGetProcessTimeDiscretization() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate exerciseDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods scheduleFixedLeg =
        new ScheduleFromPeriods(referenceDate2, periods, new DayCountConvention_30E_360(true));
    LocalDate referenceDate3 = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods2 = new ArrayList<>();

    ScheduleFromPeriods scheduleFloatLeg =
        new ScheduleFromPeriods(referenceDate3, periods2, new DayCountConvention_30E_360(true));

    SwaptionFromSwapSchedules swaptionFromSwapSchedules =
        new SwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDate,
            scheduleFixedLeg,
            scheduleFloatLeg,
            10.0d,
            10.0d,
            ValueUnit.VALUE);

    // Act
    TimeDiscretization actualProcessTimeDiscretization =
        swaptionFromSwapSchedules.getProcessTimeDiscretization(
            LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    assertTrue(actualProcessTimeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(0, actualProcessTimeDiscretization.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualProcessTimeDiscretization.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(0.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualProcessTimeDiscretization.iterator();
    assertEquals(0.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.0d, actualProcessTimeDiscretization.getFirstTime(), 0.0);
    assertEquals(0.0d, actualProcessTimeDiscretization.getLastTime(), 0.0);
    assertEquals(1, actualProcessTimeDiscretization.getNumberOfTimes());
    assertEquals(1.1415525114155251E-4d, actualProcessTimeDiscretization.getTickSize(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {0.0d}, actualProcessTimeDiscretization.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link SwaptionFromSwapSchedules#getProcessTimeDiscretization(LocalDateTime)}.
   *
   * <p>Method under test: {@link
   * SwaptionFromSwapSchedules#getProcessTimeDiscretization(LocalDateTime)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization SwaptionFromSwapSchedules.getProcessTimeDiscretization(LocalDateTime)"
  })
  public void testGetProcessTimeDiscretization2() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate exerciseDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods scheduleFixedLeg =
        new ScheduleFromPeriods(referenceDate2, periods, new DayCountConvention_30E_360(true));
    LocalDate referenceDate3 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFloatLeg =
        new ScheduleFromPeriods(referenceDate3, daycountconvention, period);

    SwaptionFromSwapSchedules swaptionFromSwapSchedules =
        new SwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDate,
            scheduleFixedLeg,
            scheduleFloatLeg,
            10.0d,
            10.0d,
            ValueUnit.VALUE);

    // Act
    TimeDiscretization actualProcessTimeDiscretization =
        swaptionFromSwapSchedules.getProcessTimeDiscretization(
            LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    assertTrue(actualProcessTimeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(0, actualProcessTimeDiscretization.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualProcessTimeDiscretization.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(0.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualProcessTimeDiscretization.iterator();
    assertEquals(0.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.0d, actualProcessTimeDiscretization.getFirstTime(), 0.0);
    assertEquals(0.0d, actualProcessTimeDiscretization.getLastTime(), 0.0);
    assertEquals(1, actualProcessTimeDiscretization.getNumberOfTimes());
    assertEquals(1.1415525114155251E-4d, actualProcessTimeDiscretization.getTickSize(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {0.0d}, actualProcessTimeDiscretization.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link SwaptionFromSwapSchedules#getProcessTimeDiscretization(LocalDateTime)}.
   *
   * <p>Method under test: {@link
   * SwaptionFromSwapSchedules#getProcessTimeDiscretization(LocalDateTime)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization SwaptionFromSwapSchedules.getProcessTimeDiscretization(LocalDateTime)"
  })
  public void testGetProcessTimeDiscretization3() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate exerciseDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFixedLeg =
        new ScheduleFromPeriods(referenceDate2, daycountconvention, period);
    LocalDate referenceDate3 = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods scheduleFloatLeg =
        new ScheduleFromPeriods(referenceDate3, periods, new DayCountConvention_30E_360(true));

    SwaptionFromSwapSchedules swaptionFromSwapSchedules =
        new SwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDate,
            scheduleFixedLeg,
            scheduleFloatLeg,
            10.0d,
            10.0d,
            ValueUnit.VALUE);

    // Act
    TimeDiscretization actualProcessTimeDiscretization =
        swaptionFromSwapSchedules.getProcessTimeDiscretization(
            LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    assertTrue(actualProcessTimeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(0, actualProcessTimeDiscretization.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualProcessTimeDiscretization.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(0.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualProcessTimeDiscretization.iterator();
    assertEquals(0.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.0d, actualProcessTimeDiscretization.getFirstTime(), 0.0);
    assertEquals(0.0d, actualProcessTimeDiscretization.getLastTime(), 0.0);
    assertEquals(1, actualProcessTimeDiscretization.getNumberOfTimes());
    assertEquals(1.1415525114155251E-4d, actualProcessTimeDiscretization.getTickSize(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {0.0d}, actualProcessTimeDiscretization.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link SwaptionFromSwapSchedules#getProcessTimeDiscretization(LocalDateTime)}.
   *
   * <p>Method under test: {@link
   * SwaptionFromSwapSchedules#getProcessTimeDiscretization(LocalDateTime)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization SwaptionFromSwapSchedules.getProcessTimeDiscretization(LocalDateTime)"
  })
  public void testGetProcessTimeDiscretization4() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate exerciseDate = LocalDate.now();
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods scheduleFixedLeg =
        new ScheduleFromPeriods(referenceDate2, periods, new DayCountConvention_30E_360(true));
    LocalDate referenceDate3 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFloatLeg =
        new ScheduleFromPeriods(referenceDate3, daycountconvention, period);

    SwaptionFromSwapSchedules swaptionFromSwapSchedules =
        new SwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDate,
            scheduleFixedLeg,
            scheduleFloatLeg,
            10.0d,
            10.0d,
            ValueUnit.VALUE);

    // Act
    TimeDiscretization actualProcessTimeDiscretization =
        swaptionFromSwapSchedules.getProcessTimeDiscretization(
            LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    assertTrue(actualProcessTimeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(0.0d, actualProcessTimeDiscretization.getAsArrayList().get(0).doubleValue(), 0.0);
    assertEquals(0.0d, actualProcessTimeDiscretization.iterator().next().doubleValue(), 0.0);
    assertEquals(0.0d, actualProcessTimeDiscretization.getFirstTime(), 0.0);
    assertEquals(1.1415525114155251E-4d, actualProcessTimeDiscretization.getTickSize(), 0.0);
  }

  /**
   * Test {@link SwaptionFromSwapSchedules#getProcessTimeDiscretization(LocalDateTime)}.
   *
   * <p>Method under test: {@link
   * SwaptionFromSwapSchedules#getProcessTimeDiscretization(LocalDateTime)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization SwaptionFromSwapSchedules.getProcessTimeDiscretization(LocalDateTime)"
  })
  public void testGetProcessTimeDiscretization5() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate exerciseDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    Period period2 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFixedLeg =
        new ScheduleFromPeriods(referenceDate2, daycountconvention, period, period2);
    LocalDate referenceDate3 = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods scheduleFloatLeg =
        new ScheduleFromPeriods(referenceDate3, periods, new DayCountConvention_30E_360(true));

    SwaptionFromSwapSchedules swaptionFromSwapSchedules =
        new SwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDate,
            scheduleFixedLeg,
            scheduleFloatLeg,
            10.0d,
            10.0d,
            ValueUnit.VALUE);

    // Act
    TimeDiscretization actualProcessTimeDiscretization =
        swaptionFromSwapSchedules.getProcessTimeDiscretization(
            LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    assertTrue(actualProcessTimeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(0, actualProcessTimeDiscretization.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualProcessTimeDiscretization.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(0.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualProcessTimeDiscretization.iterator();
    assertEquals(0.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.0d, actualProcessTimeDiscretization.getFirstTime(), 0.0);
    assertEquals(0.0d, actualProcessTimeDiscretization.getLastTime(), 0.0);
    assertEquals(1, actualProcessTimeDiscretization.getNumberOfTimes());
    assertEquals(1.1415525114155251E-4d, actualProcessTimeDiscretization.getTickSize(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {0.0d}, actualProcessTimeDiscretization.getAsDoubleArray(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SwaptionFromSwapSchedules#toString()}
   *   <li>{@link SwaptionFromSwapSchedules#getExerciseDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate SwaptionFromSwapSchedules.getExerciseDate()",
    "java.lang.String SwaptionFromSwapSchedules.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate exerciseDate = LocalDate.of(1970, 1, 1);
    RegularSchedule scheduleFixedLeg = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    SwaptionFromSwapSchedules swaptionFromSwapSchedules =
        new SwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDate,
            scheduleFixedLeg,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            10.0d,
            10.0d,
            ValueUnit.VALUE);

    // Act
    swaptionFromSwapSchedules.toString();
    LocalDate actualExerciseDate = swaptionFromSwapSchedules.getExerciseDate();

    // Assert
    assertEquals("1970-01-01", actualExerciseDate.toString());
    assertSame(exerciseDate, actualExerciseDate);
  }

  /**
   * Test {@link SwaptionFromSwapSchedules#getValueOfLegAnalytic(double,
   * TermStructureMonteCarloSimulationModel, Schedule, boolean, double, double)}.
   *
   * <p>Method under test: {@link SwaptionFromSwapSchedules#getValueOfLegAnalytic(double,
   * TermStructureMonteCarloSimulationModel, Schedule, boolean, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionFromSwapSchedules.getValueOfLegAnalytic(double, TermStructureMonteCarloSimulationModel, Schedule, boolean, double, double)"
  })
  public void testGetValueOfLegAnalytic() throws CalculationException {
    // Arrange
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
    RandomVariable actualValueOfLegAnalytic =
        SwaptionFromSwapSchedules.getValueOfLegAnalytic(
            10.0d,
            model,
            new RegularSchedule(
                new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START)),
            true,
            10.0d,
            10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValueOfLegAnalytic.abs() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.cos() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.exp() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.expm1() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.invert() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.isNaN() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.sin() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.sqrt() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.squared() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.variance() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic instanceof Scalar);
    RandomVariable actualExpectationResult = actualValueOfLegAnalytic.expectation();
    assertSame(actualValueOfLegAnalytic, actualExpectationResult);
  }

  /**
   * Test {@link SwaptionFromSwapSchedules#getValueOfLegAnalytic(double,
   * TermStructureMonteCarloSimulationModel, Schedule, boolean, double, double)}.
   *
   * <p>Method under test: {@link SwaptionFromSwapSchedules#getValueOfLegAnalytic(double,
   * TermStructureMonteCarloSimulationModel, Schedule, boolean, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionFromSwapSchedules.getValueOfLegAnalytic(double, TermStructureMonteCarloSimulationModel, Schedule, boolean, double, double)"
  })
  public void testGetValueOfLegAnalytic2() throws CalculationException {
    // Arrange
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
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Period> periods = new ArrayList<>();

    ScheduleFromPeriods schedule =
        new ScheduleFromPeriods(referenceDate, periods, new DayCountConvention_30E_360(true));

    // Act
    RandomVariable actualValueOfLegAnalytic =
        SwaptionFromSwapSchedules.getValueOfLegAnalytic(10.0d, model, schedule, true, 10.0d, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValueOfLegAnalytic.abs() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.cos() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.exp() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.expm1() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.invert() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.isNaN() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.sin() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.sqrt() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.squared() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic.variance() instanceof Scalar);
    assertTrue(actualValueOfLegAnalytic instanceof Scalar);
    RandomVariable actualExpectationResult = actualValueOfLegAnalytic.expectation();
    assertSame(actualValueOfLegAnalytic, actualExpectationResult);
  }
}
