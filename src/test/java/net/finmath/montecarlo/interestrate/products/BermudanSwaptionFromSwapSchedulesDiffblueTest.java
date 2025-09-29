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
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.MonteCarloSimulationModel;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.RandomVariableLazyEvaluation;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.montecarlo.conditionalexpectation.MonteCarloConditionalExpectationRegression;
import net.finmath.montecarlo.conditionalexpectation.MonteCarloConditionalExpectationRegression.RegressionBasisFunctions;
import net.finmath.montecarlo.conditionalexpectation.MonteCarloConditionalExpectationRegression.RegressionBasisFunctionsGiven;
import net.finmath.montecarlo.conditionalexpectation.MonteCarloConditionalExpectationRegressionFactory;
import net.finmath.montecarlo.conditionalexpectation.RegressionBasisFunctionsProvider;
import net.finmath.montecarlo.interestrate.LIBORModelMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.HullWhiteModel;
import net.finmath.montecarlo.interestrate.products.BermudanSwaptionFromSwapSchedules.SwaptionType;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.ConditionalExpectationEstimator;
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

public class BermudanSwaptionFromSwapSchedulesDiffblueTest {
  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double, double, Schedule[], Schedule[])}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double, double, Schedule[], Schedule[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BermudanSwaptionFromSwapSchedules.<init>(LocalDateTime, SwaptionType, LocalDate[], LocalDate, double, double, Schedule[], Schedule[])"
  })
  public void testNewBermudanSwaptionFromSwapSchedules() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates =
        new LocalDate[] {LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    // Act
    BermudanSwaptionFromSwapSchedules actualBermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Assert
    assertSame(exerciseDates, actualBermudanSwaptionFromSwapSchedules.getExerciseDates());
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[])}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BermudanSwaptionFromSwapSchedules.<init>(LocalDateTime, SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[])"
  })
  public void testNewBermudanSwaptionFromSwapSchedules2() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate ofResult = LocalDate.of(1970, 1, 1);
    LocalDate[] exerciseDates = new LocalDate[] {ofResult};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    // Act
    BermudanSwaptionFromSwapSchedules actualBermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules);

    // Assert
    assertNull(actualBermudanSwaptionFromSwapSchedules.getCurrency());
    LocalDate[] exerciseDates2 = actualBermudanSwaptionFromSwapSchedules.getExerciseDates();
    assertEquals(1, exerciseDates2.length);
    assertEquals(SwaptionType.PAYER, actualBermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(swapEndDate, actualBermudanSwaptionFromSwapSchedules.getSwapEndDate());
    assertSame(ofResult, exerciseDates2[0]);
    assertSame(exerciseDates, exerciseDates2);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double, double, Schedule[], Schedule[])}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double, double, Schedule[], Schedule[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BermudanSwaptionFromSwapSchedules.<init>(LocalDateTime, SwaptionType, LocalDate[], LocalDate, double, double, Schedule[], Schedule[])"
  })
  public void testNewBermudanSwaptionFromSwapSchedules_thenReturnArrayLengthIsOne() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate ofResult = LocalDate.of(1970, 1, 1);
    LocalDate[] exerciseDates = new LocalDate[] {ofResult};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    // Act
    BermudanSwaptionFromSwapSchedules actualBermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Assert
    assertNull(actualBermudanSwaptionFromSwapSchedules.getCurrency());
    LocalDate[] exerciseDates2 = actualBermudanSwaptionFromSwapSchedules.getExerciseDates();
    assertEquals(1, exerciseDates2.length);
    assertEquals(SwaptionType.PAYER, actualBermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(swapEndDate, actualBermudanSwaptionFromSwapSchedules.getSwapEndDate());
    assertSame(ofResult, exerciseDates2[0]);
    assertSame(exerciseDates, exerciseDates2);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double, double, Schedule[], Schedule[])}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double, double, Schedule[], Schedule[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BermudanSwaptionFromSwapSchedules.<init>(LocalDateTime, SwaptionType, LocalDate[], LocalDate, double, double, Schedule[], Schedule[])"
  })
  public void testNewBermudanSwaptionFromSwapSchedules_thenReturnArrayLengthIsZero() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    // Act
    BermudanSwaptionFromSwapSchedules actualBermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Assert
    assertNull(actualBermudanSwaptionFromSwapSchedules.getCurrency());
    assertEquals(0, actualBermudanSwaptionFromSwapSchedules.getExerciseDates().length);
    assertEquals(SwaptionType.PAYER, actualBermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(swapEndDate, actualBermudanSwaptionFromSwapSchedules.getSwapEndDate());
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[],
   * MonteCarloConditionalExpectationRegressionFactory, RegressionBasisFunctionsProvider)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[],
   * MonteCarloConditionalExpectationRegressionFactory, RegressionBasisFunctionsProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BermudanSwaptionFromSwapSchedules.<init>(LocalDateTime, SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[], MonteCarloConditionalExpectationRegressionFactory, RegressionBasisFunctionsProvider)"
  })
  public void testNewBermudanSwaptionFromSwapSchedules_whenNull() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate ofResult = LocalDate.of(1970, 1, 1);
    LocalDate[] exerciseDates = new LocalDate[] {ofResult};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    // Act
    BermudanSwaptionFromSwapSchedules actualBermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            mock(MonteCarloConditionalExpectationRegressionFactory.class),
            null);

    // Assert
    assertNull(actualBermudanSwaptionFromSwapSchedules.getCurrency());
    LocalDate[] exerciseDates2 = actualBermudanSwaptionFromSwapSchedules.getExerciseDates();
    assertEquals(1, exerciseDates2.length);
    assertEquals(SwaptionType.PAYER, actualBermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(swapEndDate, actualBermudanSwaptionFromSwapSchedules.getSwapEndDate());
    assertSame(ofResult, exerciseDates2[0]);
    assertSame(exerciseDates, exerciseDates2);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[],
   * RegressionBasisFunctionsProvider)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[],
   * RegressionBasisFunctionsProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BermudanSwaptionFromSwapSchedules.<init>(LocalDateTime, SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[], RegressionBasisFunctionsProvider)"
  })
  public void testNewBermudanSwaptionFromSwapSchedules_whenNull2() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate ofResult = LocalDate.of(1970, 1, 1);
    LocalDate[] exerciseDates = new LocalDate[] {ofResult};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    // Act
    BermudanSwaptionFromSwapSchedules actualBermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            null);

    // Assert
    assertNull(actualBermudanSwaptionFromSwapSchedules.getCurrency());
    LocalDate[] exerciseDates2 = actualBermudanSwaptionFromSwapSchedules.getExerciseDates();
    assertEquals(1, exerciseDates2.length);
    assertEquals(SwaptionType.PAYER, actualBermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(swapEndDate, actualBermudanSwaptionFromSwapSchedules.getSwapEndDate());
    assertSame(ofResult, exerciseDates2[0]);
    assertSame(exerciseDates, exerciseDates2);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[],
   * MonteCarloConditionalExpectationRegressionFactory, RegressionBasisFunctionsProvider)}.
   *
   * <ul>
   *   <li>When {@link RegressionBasisFunctionsProvider}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[],
   * MonteCarloConditionalExpectationRegressionFactory, RegressionBasisFunctionsProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BermudanSwaptionFromSwapSchedules.<init>(LocalDateTime, SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[], MonteCarloConditionalExpectationRegressionFactory, RegressionBasisFunctionsProvider)"
  })
  public void testNewBermudanSwaptionFromSwapSchedules_whenRegressionBasisFunctionsProvider() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate ofResult = LocalDate.of(1970, 1, 1);
    LocalDate[] exerciseDates = new LocalDate[] {ofResult};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    // Act
    BermudanSwaptionFromSwapSchedules actualBermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            mock(MonteCarloConditionalExpectationRegressionFactory.class),
            mock(RegressionBasisFunctionsProvider.class));

    // Assert
    assertNull(actualBermudanSwaptionFromSwapSchedules.getCurrency());
    LocalDate[] exerciseDates2 = actualBermudanSwaptionFromSwapSchedules.getExerciseDates();
    assertEquals(1, exerciseDates2.length);
    assertEquals(SwaptionType.PAYER, actualBermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(swapEndDate, actualBermudanSwaptionFromSwapSchedules.getSwapEndDate());
    assertSame(ofResult, exerciseDates2[0]);
    assertSame(exerciseDates, exerciseDates2);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[],
   * RegressionBasisFunctionsProvider)}.
   *
   * <ul>
   *   <li>When {@link RegressionBasisFunctionsProvider}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#BermudanSwaptionFromSwapSchedules(LocalDateTime,
   * SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[],
   * RegressionBasisFunctionsProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BermudanSwaptionFromSwapSchedules.<init>(LocalDateTime, SwaptionType, LocalDate[], LocalDate, double[], double[], Schedule[], Schedule[], RegressionBasisFunctionsProvider)"
  })
  public void testNewBermudanSwaptionFromSwapSchedules_whenRegressionBasisFunctionsProvider2() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate ofResult = LocalDate.of(1970, 1, 1);
    LocalDate[] exerciseDates = new LocalDate[] {ofResult};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    // Act
    BermudanSwaptionFromSwapSchedules actualBermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            mock(RegressionBasisFunctionsProvider.class));

    // Assert
    assertNull(actualBermudanSwaptionFromSwapSchedules.getCurrency());
    LocalDate[] exerciseDates2 = actualBermudanSwaptionFromSwapSchedules.getExerciseDates();
    assertEquals(1, exerciseDates2.length);
    assertEquals(SwaptionType.PAYER, actualBermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(swapEndDate, actualBermudanSwaptionFromSwapSchedules.getSwapEndDate());
    assertSame(ofResult, exerciseDates2[0]);
    assertSame(exerciseDates, exerciseDates2);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BermudanSwaptionFromSwapSchedules#toString()}
   *   <li>{@link BermudanSwaptionFromSwapSchedules#getExerciseDates()}
   *   <li>{@link BermudanSwaptionFromSwapSchedules#getSwapEndDate()}
   *   <li>{@link BermudanSwaptionFromSwapSchedules#getSwaptionType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate[] BermudanSwaptionFromSwapSchedules.getExerciseDates()",
    "LocalDate BermudanSwaptionFromSwapSchedules.getSwapEndDate()",
    "SwaptionType BermudanSwaptionFromSwapSchedules.getSwaptionType()",
    "String BermudanSwaptionFromSwapSchedules.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate ofResult = LocalDate.of(1970, 1, 1);
    LocalDate[] exerciseDates = new LocalDate[] {ofResult};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    bermudanSwaptionFromSwapSchedules.toString();
    LocalDate[] actualExerciseDates = bermudanSwaptionFromSwapSchedules.getExerciseDates();
    LocalDate actualSwapEndDate = bermudanSwaptionFromSwapSchedules.getSwapEndDate();

    // Assert
    assertEquals("1970-01-01", actualSwapEndDate.toString());
    assertEquals(1, actualExerciseDates.length);
    assertEquals(SwaptionType.PAYER, bermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(swapEndDate, actualSwapEndDate);
    assertSame(ofResult, actualExerciseDates[0]);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaptionFromSwapSchedules.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    Map<String, Object> actualValues = bermudanSwaptionFromSwapSchedules.getValues(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    assertEquals(2, actualValues.size());
    Object getResult = actualValues.get("values");
    assertTrue(getResult instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.containsKey("exerciseTimes"));
    assertArrayEquals(
        new double[] {10.0d}, ((RandomVariableFromDoubleArray) getResult).getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaptionFromSwapSchedules.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getNumeraire(anyDouble())).thenThrow(new CalculationException("An error occurred"));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act and Assert
    assertThrows(
        CalculationException.class,
        () -> bermudanSwaptionFromSwapSchedules.getValues(10.0d, model));
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaptionFromSwapSchedules.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(Scalar.of(Double.POSITIVE_INFINITY));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    Map<String, Object> actualValues = bermudanSwaptionFromSwapSchedules.getValues(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    assertEquals(2, actualValues.size());
    Object getResult = actualValues.get("values");
    assertTrue(getResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, ((RandomVariableFromDoubleArray) getResult).getAverage(), 0.0);
    assertEquals(0.0d, ((RandomVariableFromDoubleArray) getResult).getMax(), 0.0);
    assertEquals(0.0d, ((RandomVariableFromDoubleArray) getResult).getMin(), 0.0);
    assertTrue(actualValues.containsKey("exerciseTimes"));
    assertArrayEquals(
        new double[] {0.0d}, ((RandomVariableFromDoubleArray) getResult).getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaptionFromSwapSchedules.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel4()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(Scalar.of(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    Map<String, Object> actualValues = bermudanSwaptionFromSwapSchedules.getValues(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
    assertEquals(2, actualValues.size());
    Object getResult = actualValues.get("values");
    assertTrue(getResult instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableFromDoubleArray) getResult).variance()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.containsKey("exerciseTimes"));
    assertArrayEquals(
        new double[] {10.0d}, ((RandomVariableFromDoubleArray) getResult).getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaptionFromSwapSchedules.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel5()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble())).thenReturn(Scalar.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    Map<String, Object> actualValues = bermudanSwaptionFromSwapSchedules.getValues(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar2).mult(10.0d);
    assertEquals(2, actualValues.size());
    Object getResult = actualValues.get("values");
    assertTrue(getResult instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, ((RandomVariableFromDoubleArray) getResult).getAverage(), 0.0);
    assertEquals(1.0d, ((RandomVariableFromDoubleArray) getResult).getMax(), 0.0);
    assertEquals(1.0d, ((RandomVariableFromDoubleArray) getResult).getMin(), 0.0);
    assertTrue(actualValues.containsKey("exerciseTimes"));
    assertArrayEquals(
        new double[] {1.0d}, ((RandomVariableFromDoubleArray) getResult).getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaptionFromSwapSchedules.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel6()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.get(anyInt())).thenReturn(10.0d);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble()))
        .thenReturn(new RandomVariableFromFloatArray(Double.POSITIVE_INFINITY));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble())).thenReturn(Scalar.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    Map<String, Object> actualValues = bermudanSwaptionFromSwapSchedules.getValues(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar).get(0);
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar2).mult(10.0d);
    assertEquals(2, actualValues.size());
    assertTrue(actualValues.get("values") instanceof RandomVariableFromFloatArray);
    assertTrue(actualValues.containsKey("exerciseTimes"));
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaptionFromSwapSchedules.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel7()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.invert()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble())).thenReturn(Scalar.of(Double.POSITIVE_INFINITY));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble())).thenReturn(Scalar.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    Map<String, Object> actualValues = bermudanSwaptionFromSwapSchedules.getValues(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar).invert();
    verify(scalar2).mult(10.0d);
    assertEquals(2, actualValues.size());
    Object getResult = actualValues.get("values");
    assertTrue(getResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues.containsKey("exerciseTimes"));
    assertEquals(
        Double.POSITIVE_INFINITY, ((RandomVariableFromDoubleArray) getResult).getAverage(), 0.0);
    assertEquals(
        Double.POSITIVE_INFINITY, ((RandomVariableFromDoubleArray) getResult).getMax(), 0.0);
    assertEquals(
        Double.POSITIVE_INFINITY, ((RandomVariableFromDoubleArray) getResult).getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY},
        ((RandomVariableFromDoubleArray) getResult).getRealizations(),
        0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaptionFromSwapSchedules.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel8()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.invert())
        .thenReturn(RandomVariableDifferentiableAADPathwise.of(Double.POSITIVE_INFINITY));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble())).thenReturn(Scalar.of(Double.POSITIVE_INFINITY));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble())).thenReturn(Scalar.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    Map<String, Object> actualValues = bermudanSwaptionFromSwapSchedules.getValues(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar).invert();
    verify(scalar2).mult(10.0d);
    assertEquals(2, actualValues.size());
    assertTrue(actualValues.get("values") instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualValues.containsKey("exerciseTimes"));
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaptionFromSwapSchedules.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel9()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(Double.POSITIVE_INFINITY));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble())).thenReturn(Scalar.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    Map<String, Object> actualValues = bermudanSwaptionFromSwapSchedules.getValues(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar, atLeast(1)).getValues();
    verify(scalar).getTypePriority();
    verify(scalar2).mult(10.0d);
    assertEquals(2, actualValues.size());
    Object getResult = actualValues.get("values");
    assertTrue(getResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValues.containsKey("exerciseTimes"));
    assertEquals(
        Double.POSITIVE_INFINITY, ((RandomVariableDifferentiableAAD) getResult).getAverage(), 0.0);
    assertEquals(
        Double.POSITIVE_INFINITY, ((RandomVariableDifferentiableAAD) getResult).getMax(), 0.0);
    assertEquals(
        Double.POSITIVE_INFINITY, ((RandomVariableDifferentiableAAD) getResult).getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY},
        ((RandomVariableDifferentiableAAD) getResult).getRealizations(),
        0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaptionFromSwapSchedules.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel10()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar2.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    Map<String, Object> actualValues = bermudanSwaptionFromSwapSchedules.getValues(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar, atLeast(1)).getValues();
    verify(scalar2, atLeast(1)).getValues();
    verify(scalar).getTypePriority();
    verify(scalar2).getTypePriority();
    assertEquals(2, actualValues.size());
    Object getResult = actualValues.get("values");
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(getResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) getResult).getGradient().size());
    assertTrue(actualValues.containsKey("exerciseTimes"));
    assertArrayEquals(
        new double[] {10.0d}, ((RandomVariableDifferentiableAAD) getResult).getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaptionFromSwapSchedules.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel11()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.getValues())
        .thenReturn(RandomVariableDifferentiableAAD.of(Double.POSITIVE_INFINITY));
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar2.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    Map<String, Object> actualValues = bermudanSwaptionFromSwapSchedules.getValues(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar, atLeast(1)).getValues();
    verify(scalar2, atLeast(1)).getValues();
    verify(scalar).getTypePriority();
    verify(scalar2).getTypePriority();
    assertEquals(2, actualValues.size());
    Object getResult = actualValues.get("values");
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(getResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getValues()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) getResult).getGradient().size());
    assertTrue(actualValues.containsKey("exerciseTimes"));
    assertArrayEquals(
        new double[] {0.0d}, ((RandomVariableDifferentiableAAD) getResult).getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaptionFromSwapSchedules.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel12()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    RandomVariableFromDoubleArray values =
        new RandomVariableFromDoubleArray(Double.POSITIVE_INFINITY);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    when(scalar.getValues()).thenReturn(randomVariableDifferentiableAAD);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar2.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    Map<String, Object> actualValues = bermudanSwaptionFromSwapSchedules.getValues(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar, atLeast(1)).getValues();
    verify(scalar2, atLeast(1)).getValues();
    verify(scalar).getTypePriority();
    verify(scalar2).getTypePriority();
    assertEquals(2, actualValues.size());
    Object getResult = actualValues.get("values");
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(getResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getValues()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) getResult).variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) getResult).getGradient().size());
    assertTrue(actualValues.containsKey("exerciseTimes"));
    assertArrayEquals(
        new double[] {0.0d}, ((RandomVariableDifferentiableAAD) getResult).getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValues(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map BermudanSwaptionFromSwapSchedules.getValues(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValuesWithDoubleTermStructureMonteCarloSimulationModel13()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        mock(RandomVariableDifferentiableAAD.class);
    when(randomVariableDifferentiableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableDifferentiableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableDifferentiableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableDifferentiableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableDifferentiableAAD.size()).thenReturn(3);

    Scalar scalar = mock(Scalar.class);
    when(scalar.getValues()).thenReturn(randomVariableDifferentiableAAD);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar2.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    Map<String, Object> actualValues = bermudanSwaptionFromSwapSchedules.getValues(10.0d, model);

    // Assert
    verify(randomVariableDifferentiableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableDifferentiableAAD).getFiltrationTime();
    verify(randomVariableDifferentiableAAD).getTypePriority();
    verify(randomVariableDifferentiableAAD).isDeterministic();
    verify(randomVariableDifferentiableAAD).size();
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar, atLeast(1)).getValues();
    verify(scalar2, atLeast(1)).getValues();
    verify(scalar).getTypePriority();
    verify(scalar2).getTypePriority();
    assertEquals(2, actualValues.size());
    Object getResult = actualValues.get("values");
    assertTrue(getResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(10.0d, ((RandomVariableDifferentiableAAD) getResult).getFiltrationTime(), 0.0);
    assertEquals(3, ((RandomVariableDifferentiableAAD) getResult).size());
    assertFalse(((RandomVariableDifferentiableAAD) getResult).isDeterministic());
    assertTrue(actualValues.containsKey("exerciseTimes"));
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        ((RandomVariableDifferentiableAAD) getResult).getRealizations(),
        0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = bermudanSwaptionFromSwapSchedules.getValue(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
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
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getNumeraire(anyDouble())).thenThrow(new CalculationException("An error occurred"));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act and Assert
    assertThrows(
        CalculationException.class, () -> bermudanSwaptionFromSwapSchedules.getValue(10.0d, model));
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(Scalar.of(Double.POSITIVE_INFINITY));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = bermudanSwaptionFromSwapSchedules.getValue(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualValue.getAverage(), 0.0);
    assertEquals(0.0d, actualValue.getMax(), 0.0);
    assertEquals(0.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel4()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(Scalar.of(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = bermudanSwaptionFromSwapSchedules.getValue(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
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
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel5()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenReturn(10.0d);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble())).thenReturn(Scalar.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = bermudanSwaptionFromSwapSchedules.getValue(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar2).mult(10.0d);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualValue.getAverage(), 0.0);
    assertEquals(1.0d, actualValue.getMax(), 0.0);
    assertEquals(1.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel6()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.get(anyInt())).thenReturn(10.0d);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble()))
        .thenReturn(new RandomVariableFromFloatArray(Double.POSITIVE_INFINITY));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble())).thenReturn(Scalar.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = bermudanSwaptionFromSwapSchedules.getValue(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar).get(0);
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
    verify(scalar2).mult(10.0d);
    assertTrue(actualValue instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(1, actualValue.size());
    assertEquals(10.0d, actualValue.getFiltrationTime(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.POSITIVE_INFINITY, actualValue.getAverage(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualValue.getMax(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.POSITIVE_INFINITY}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel7()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.invert())
        .thenReturn(RandomVariableDifferentiableAADPathwise.of(Double.POSITIVE_INFINITY));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble())).thenReturn(Scalar.of(Double.POSITIVE_INFINITY));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble())).thenReturn(Scalar.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = bermudanSwaptionFromSwapSchedules.getValue(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar).invert();
    verify(scalar2).mult(10.0d);
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualValue).getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualValue).getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualValue).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualValue).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualValue)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualValue)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualValue)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualValue).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualValue instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualValue.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualValue.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualValue.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.size());
    assertEquals(3, actualValue.getTypePriority());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualValue.getAverage(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualValue.getMax(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualValue.getMin(), 0.0);
    assertSame(randomVariable, actualValue.getValues());
    assertArrayEquals(new double[] {Double.POSITIVE_INFINITY}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel8()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(Double.POSITIVE_INFINITY));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble())).thenReturn(Scalar.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = bermudanSwaptionFromSwapSchedules.getValue(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar, atLeast(1)).getValues();
    verify(scalar).getTypePriority();
    verify(scalar2).mult(10.0d);
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertEquals(Double.POSITIVE_INFINITY, actualValue.getAverage(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualValue.getMax(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.POSITIVE_INFINITY}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel9()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar2.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = bermudanSwaptionFromSwapSchedules.getValue(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar, atLeast(1)).getValues();
    verify(scalar2, atLeast(1)).getValues();
    verify(scalar).getTypePriority();
    verify(scalar2).getTypePriority();
    assertTrue(actualValue.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualValue).getGradient().size());
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel10()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.getValues())
        .thenReturn(RandomVariableDifferentiableAAD.of(Double.POSITIVE_INFINITY));
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar2.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = bermudanSwaptionFromSwapSchedules.getValue(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar, atLeast(1)).getValues();
    verify(scalar2, atLeast(1)).getValues();
    verify(scalar).getTypePriority();
    verify(scalar2).getTypePriority();
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualValue).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel11()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    RandomVariableFromDoubleArray values =
        new RandomVariableFromDoubleArray(Double.POSITIVE_INFINITY);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    when(scalar.getValues()).thenReturn(randomVariableDifferentiableAAD);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar2.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = bermudanSwaptionFromSwapSchedules.getValue(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar, atLeast(1)).getValues();
    verify(scalar2, atLeast(1)).getValues();
    verify(scalar).getTypePriority();
    verify(scalar2).getTypePriority();
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualValue).getGradient().size());
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel12()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        mock(RandomVariableDifferentiableAAD.class);
    when(randomVariableDifferentiableAAD.isDeterministic()).thenReturn(false);
    when(randomVariableDifferentiableAAD.get(anyInt())).thenReturn(10.0d);
    when(randomVariableDifferentiableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableDifferentiableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableDifferentiableAAD.size()).thenReturn(3);

    Scalar scalar = mock(Scalar.class);
    when(scalar.getValues()).thenReturn(randomVariableDifferentiableAAD);
    when(scalar.getTypePriority()).thenReturn(1);

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.getValues()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar2.getTypePriority()).thenReturn(1);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = bermudanSwaptionFromSwapSchedules.getValue(10.0d, model);

    // Assert
    verify(randomVariableDifferentiableAAD, atLeast(1)).get(anyInt());
    verify(randomVariableDifferentiableAAD).getFiltrationTime();
    verify(randomVariableDifferentiableAAD).getTypePriority();
    verify(randomVariableDifferentiableAAD).isDeterministic();
    verify(randomVariableDifferentiableAAD).size();
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar, atLeast(1)).getValues();
    verify(scalar2, atLeast(1)).getValues();
    verify(scalar).getTypePriority();
    verify(scalar2).getTypePriority();
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertEquals(10.0d, actualValue.getFiltrationTime(), 0.0);
    assertEquals(3, actualValue.size());
    assertFalse(actualValue.isDeterministic());
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <ul>
   *   <li>Then calls {@link Scalar#invert()}.
   * </ul>
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BermudanSwaptionFromSwapSchedules.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel_thenCallsInvert()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    Scalar scalar = mock(Scalar.class);
    when(scalar.invert()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(anyDouble())).thenReturn(Scalar.of(Double.POSITIVE_INFINITY));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble())).thenReturn(scalar);
    when(model.getNumeraire(anyDouble())).thenReturn(scalar2);
    when(model.getRandomVariableForConstant(anyDouble())).thenReturn(Scalar.of(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = bermudanSwaptionFromSwapSchedules.getValue(10.0d, model);

    // Assert
    verify(model).getMonteCarloWeights(10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(model).getReferenceDate();
    verify(scalar).invert();
    verify(scalar2).mult(10.0d);
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(Double.POSITIVE_INFINITY, actualValue.getAverage(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualValue.getMax(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.POSITIVE_INFINITY}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getExerciseProbabilitiesFromTimes(LocalDateTime,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getExerciseProbabilitiesFromTimes(LocalDateTime,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] BermudanSwaptionFromSwapSchedules.getExerciseProbabilitiesFromTimes(LocalDateTime, RandomVariable)"
  })
  public void testGetExerciseProbabilitiesFromTimes() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);
    LocalDateTime localDateTime = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act and Assert
    assertArrayEquals(
        new double[] {0.0d, 1.0d},
        bermudanSwaptionFromSwapSchedules.getExerciseProbabilitiesFromTimes(
            localDateTime, new RandomVariableFromDoubleArray(10.0d)),
        0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getExerciseProbabilitiesFromTimes(LocalDateTime,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getExerciseProbabilitiesFromTimes(LocalDateTime,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] BermudanSwaptionFromSwapSchedules.getExerciseProbabilitiesFromTimes(LocalDateTime, RandomVariable)"
  })
  public void testGetExerciseProbabilitiesFromTimes2() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);
    LocalDateTime localDateTime = LocalDate.of(1970, 1, 1).atStartOfDay();

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(
            1.0d, new double[] {1.0d, 3.1536E7d, 1.0d, 3.1536E7d});
    when(randomVariableAAD.choose(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableDifferentiableAADPathwise);

    RandomVariableAAD exerciseTimes = mock(RandomVariableAAD.class);
    when(exerciseTimes.sub(anyDouble())).thenReturn(randomVariableAAD);

    // Act
    double[] actualExerciseProbabilitiesFromTimes =
        bermudanSwaptionFromSwapSchedules.getExerciseProbabilitiesFromTimes(
            localDateTime, exerciseTimes);

    // Assert
    verify(randomVariableAAD).choose(isA(RandomVariable.class), isA(RandomVariable.class));
    verify(exerciseTimes).sub(0.0027397260273972603d);
    assertArrayEquals(
        new double[] {-1.57679995E7d, 1.57680005E7d}, actualExerciseProbabilitiesFromTimes, 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getExerciseProbabilitiesFromTimes(LocalDateTime,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with minus nine and ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getExerciseProbabilitiesFromTimes(LocalDateTime,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] BermudanSwaptionFromSwapSchedules.getExerciseProbabilitiesFromTimes(LocalDateTime, RandomVariable)"
  })
  public void testGetExerciseProbabilitiesFromTimes_thenReturnArrayOfDoubleWithMinusNineAndTen() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);
    LocalDateTime localDateTime = LocalDate.of(1970, 1, 1).atStartOfDay();

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.choose(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD exerciseTimes = mock(RandomVariableAAD.class);
    when(exerciseTimes.sub(anyDouble())).thenReturn(randomVariableAAD);

    // Act
    double[] actualExerciseProbabilitiesFromTimes =
        bermudanSwaptionFromSwapSchedules.getExerciseProbabilitiesFromTimes(
            localDateTime, exerciseTimes);

    // Assert
    verify(randomVariableAAD).choose(isA(RandomVariable.class), isA(RandomVariable.class));
    verify(exerciseTimes).sub(0.0027397260273972603d);
    assertArrayEquals(new double[] {-9.0d, 10.0d}, actualExerciseProbabilitiesFromTimes, 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getExerciseProbabilitiesFromTimes(LocalDateTime,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@link Double#NaN} and {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getExerciseProbabilitiesFromTimes(LocalDateTime,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] BermudanSwaptionFromSwapSchedules.getExerciseProbabilitiesFromTimes(LocalDateTime, RandomVariable)"
  })
  public void testGetExerciseProbabilitiesFromTimes_thenReturnArrayOfDoubleWithNaNAndNaN() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);
    LocalDateTime localDateTime = LocalDate.of(1970, 1, 1).atStartOfDay();

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(1.0d, new double[] {});
    when(randomVariableAAD.choose(Mockito.<RandomVariable>any(), Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableDifferentiableAADPathwise);

    RandomVariableAAD exerciseTimes = mock(RandomVariableAAD.class);
    when(exerciseTimes.sub(anyDouble())).thenReturn(randomVariableAAD);

    // Act
    double[] actualExerciseProbabilitiesFromTimes =
        bermudanSwaptionFromSwapSchedules.getExerciseProbabilitiesFromTimes(
            localDateTime, exerciseTimes);

    // Assert
    verify(randomVariableAAD).choose(isA(RandomVariable.class), isA(RandomVariable.class));
    verify(exerciseTimes).sub(0.0027397260273972603d);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN}, actualExerciseProbabilitiesFromTimes, 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getExerciseProbabilitiesFromTimes(LocalDateTime,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with one and zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getExerciseProbabilitiesFromTimes(LocalDateTime,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] BermudanSwaptionFromSwapSchedules.getExerciseProbabilitiesFromTimes(LocalDateTime, RandomVariable)"
  })
  public void testGetExerciseProbabilitiesFromTimes_thenReturnArrayOfDoubleWithOneAndZero() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);
    LocalDateTime localDateTime = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act and Assert
    assertArrayEquals(
        new double[] {1.0d, 0.0d},
        bermudanSwaptionFromSwapSchedules.getExerciseProbabilitiesFromTimes(
            localDateTime, new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)),
        0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getExerciseProbabilitiesFromTimes(LocalDateTime,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getExerciseProbabilitiesFromTimes(LocalDateTime,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] BermudanSwaptionFromSwapSchedules.getExerciseProbabilitiesFromTimes(LocalDateTime, RandomVariable)"
  })
  public void testGetExerciseProbabilitiesFromTimes_whenScalarWithValueIsOne() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);
    LocalDateTime localDateTime = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act and Assert
    assertArrayEquals(
        new double[] {0.0d, 1.0d},
        bermudanSwaptionFromSwapSchedules.getExerciseProbabilitiesFromTimes(
            localDateTime, Scalar.of(1.0d)),
        0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getProcessTimeDiscretization(LocalDateTime)}.
   *
   * <ul>
   *   <li>Then return {@link TimeDiscretizationFromArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getProcessTimeDiscretization(LocalDateTime)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization BermudanSwaptionFromSwapSchedules.getProcessTimeDiscretization(LocalDateTime)"
  })
  public void testGetProcessTimeDiscretization_thenReturnTimeDiscretizationFromArray() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            new LocalDate[] {},
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    TimeDiscretization actualProcessTimeDiscretization =
        bermudanSwaptionFromSwapSchedules.getProcessTimeDiscretization(
            LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    assertTrue(actualProcessTimeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(-1, actualProcessTimeDiscretization.getNumberOfTimeSteps());
    assertEquals(0, actualProcessTimeDiscretization.getNumberOfTimes());
    assertEquals(1.1415525114155251E-4d, actualProcessTimeDiscretization.getTickSize(), 0.0);
    assertFalse(actualProcessTimeDiscretization.iterator().hasNext());
    assertTrue(actualProcessTimeDiscretization.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualProcessTimeDiscretization.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator() throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules = new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            regressionBasisFunctionProvider);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(
            10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));
    actualConditionalExpectationEstimator.getConditionalExpectation(
        new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(regressionBasisFunctionProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctions.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator2() throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules = new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            regressionBasisFunctionProvider);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(
            10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));
    actualConditionalExpectationEstimator.getConditionalExpectation(
        new RandomVariableFromFloatArray(10.0d));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(regressionBasisFunctionProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctions.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator3() throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules = new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            regressionBasisFunctionProvider);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(
            10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));
    actualConditionalExpectationEstimator.getConditionalExpectation(
        RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(regressionBasisFunctionProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctions.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator4() throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules = new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            regressionBasisFunctionProvider);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(
            10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));
    actualConditionalExpectationEstimator.getConditionalExpectation(
        new RandomVariableLazyEvaluation(10.0d, 10, 10.0d));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(regressionBasisFunctionProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(1, basisFunctions.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator5() throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {Scalar.of(10.0d)});
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules = new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            regressionBasisFunctionProvider);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(
            10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));
    RandomVariable actualConditionalExpectation =
        actualConditionalExpectationEstimator.getConditionalExpectation(
            new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(regressionBasisFunctionProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertTrue(actualConditionalExpectation instanceof Scalar);
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[0];
    assertTrue(randomVariable.abs() instanceof Scalar);
    assertTrue(randomVariable.cos() instanceof Scalar);
    assertTrue(randomVariable.exp() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(1, basisFunctions.length);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator6() throws CalculationException {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RegressionBasisFunctionsProvider regressionBasisFunctionProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {randomVariableAAD});
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules = new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            regressionBasisFunctionProvider);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(
            10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));
    RandomVariable actualConditionalExpectation =
        actualConditionalExpectationEstimator.getConditionalExpectation(
            new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).mult(10.0d);
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(regressionBasisFunctionProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    assertTrue(actualConditionalExpectation instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualConditionalExpectation.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator7() throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionProvider =
        mock(RegressionBasisFunctionsProvider.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(regressionBasisFunctionProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules = new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            regressionBasisFunctionProvider);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(
            10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));
    actualConditionalExpectationEstimator.getConditionalExpectation(
        new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(regressionBasisFunctionProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    assertTrue(basisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(basisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(2, basisFunctions.length);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator8() throws CalculationException {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RegressionBasisFunctionsProvider regressionBasisFunctionProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {randomVariableAAD});
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules = new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            regressionBasisFunctionProvider);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(
            10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(10.0d, new double[] {10.0d, 2.0d, 10.0d, 2.0d});
    RandomVariable actualConditionalExpectation =
        actualConditionalExpectationEstimator.getConditionalExpectation(
            randomVariableDifferentiableAADPathwise);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).mult(6.0d);
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(regressionBasisFunctionProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    assertTrue(actualConditionalExpectation instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualConditionalExpectation.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator9() throws CalculationException {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.doubleValue()).thenReturn(10.0d);
    when(randomVariableAAD.isDeterministic()).thenReturn(true);
    when(randomVariableAAD.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariableAAD.getTypePriority()).thenReturn(1);
    when(randomVariableAAD.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RegressionBasisFunctionsProvider regressionBasisFunctionProvider =
        mock(RegressionBasisFunctionsProvider.class);
    when(regressionBasisFunctionProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {randomVariableAAD});
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules = new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            regressionBasisFunctionProvider);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(
            10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(10.0d, new double[] {});
    RandomVariable actualConditionalExpectation =
        actualConditionalExpectationEstimator.getConditionalExpectation(
            randomVariableDifferentiableAADPathwise);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(randomVariableAAD).doubleValue();
    verify(randomVariableAAD).getFiltrationTime();
    verify(randomVariableAAD).getTypePriority();
    verify(randomVariableAAD).isDeterministic();
    verify(randomVariableAAD).mult(Double.NaN);
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(regressionBasisFunctionProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    assertTrue(actualConditionalExpectation instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualConditionalExpectation.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualConditionalExpectation.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator10() throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionProvider =
        mock(RegressionBasisFunctionsProvider.class);
    Scalar ofResult = Scalar.of(10.0d);
    when(regressionBasisFunctionProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(new RandomVariable[] {ofResult, new RandomVariableFromDoubleArray(10.0d)});
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules = new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            regressionBasisFunctionProvider);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(
            10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));
    actualConditionalExpectationEstimator.getConditionalExpectation(
        new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(regressionBasisFunctionProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[1];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    assertEquals(2, basisFunctions.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getConditionalExpectationEstimator(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConditionalExpectationEstimator BermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetConditionalExpectationEstimator11() throws CalculationException {
    // Arrange
    RegressionBasisFunctionsProvider regressionBasisFunctionProvider =
        mock(RegressionBasisFunctionsProvider.class);
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    when(regressionBasisFunctionProvider.getBasisFunctions(
            anyDouble(), Mockito.<MonteCarloSimulationModel>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromFloatArray, new RandomVariableFromDoubleArray(10.0d)
            });
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules = new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(mock(TimeDiscretization.class))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            new double[] {10.0d, 0.0027397260273972603d, 10.0d, 0.0027397260273972603d},
            fixSchedules,
            floatSchedules,
            regressionBasisFunctionProvider);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    ConditionalExpectationEstimator actualConditionalExpectationEstimator =
        bermudanSwaptionFromSwapSchedules.getConditionalExpectationEstimator(
            10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));
    RandomVariable actualConditionalExpectation =
        actualConditionalExpectationEstimator.getConditionalExpectation(
            new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(regressionBasisFunctionProvider)
        .getBasisFunctions(eq(10.0d), isA(MonteCarloSimulationModel.class));
    assertTrue(actualConditionalExpectation instanceof RandomVariableFromFloatArray);
    RegressionBasisFunctions basisFunctionsEstimator =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsEstimator();
    RandomVariable[] basisFunctions = basisFunctionsEstimator.getBasisFunctions();
    RandomVariable randomVariable = basisFunctions[0];
    assertTrue(randomVariable instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualConditionalExpectationEstimator
            instanceof MonteCarloConditionalExpectationRegression);
    assertTrue(basisFunctionsEstimator instanceof RegressionBasisFunctionsGiven);
    RegressionBasisFunctions basisFunctionsPredictor =
        ((MonteCarloConditionalExpectationRegression) actualConditionalExpectationEstimator)
            .getBasisFunctionsPredictor();
    assertTrue(basisFunctionsPredictor instanceof RegressionBasisFunctionsGiven);
    assertEquals(2, basisFunctions.length);
    RandomVariable[] basisFunctions2 = basisFunctionsPredictor.getBasisFunctions();
    assertEquals(2, basisFunctions2.length);
    assertSame(randomVariableFromFloatArray, randomVariable);
    assertSame(randomVariableFromFloatArray, basisFunctions2[0]);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctions(double,
   * LIBORModelMonteCarloSimulationModel)} with {@code double}, {@code
   * LIBORModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link BermudanSwaptionFromSwapSchedules#getBasisFunctions(double,
   * LIBORModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BermudanSwaptionFromSwapSchedules.getBasisFunctions(double, LIBORModelMonteCarloSimulationModel)"
  })
  public void testGetBasisFunctionsWithDoubleLIBORModelMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getNumeraire(anyDouble())).thenThrow(new CalculationException("An error occurred"));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act and Assert
    assertThrows(
        CalculationException.class,
        () -> bermudanSwaptionFromSwapSchedules.getBasisFunctions(10.0d, model));
    verify(model).getNumeraire(10.0d);
    verify(model).getReferenceDate();
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithSwapRates()}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithSwapRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithSwapRates()"
  })
  public void testGetBasisFunctionsProviderWithSwapRates() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate ofResult = LocalDate.of(1970, 1, 1);
    LocalDate[] exerciseDates = new LocalDate[] {ofResult};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithSwapRates();

    // Assert that nothing has changed
    LocalDate swapEndDate2 = bermudanSwaptionFromSwapSchedules.getSwapEndDate();
    assertEquals("1970-01-01", swapEndDate2.toString());
    LocalDate[] exerciseDates2 = bermudanSwaptionFromSwapSchedules.getExerciseDates();
    LocalDate localDate = exerciseDates2[0];
    assertEquals("1970-01-01", localDate.toString());
    assertEquals(1, exerciseDates2.length);
    assertEquals(SwaptionType.PAYER, bermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(swapEndDate, swapEndDate2);
    assertSame(ofResult, localDate);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithSwapRates()}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithSwapRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithSwapRates()"
  })
  public void testGetBasisFunctionsProviderWithSwapRates2() throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate2, daycountconvention, period);
    Schedule[] fixSchedules = new Schedule[] {scheduleFromPeriods};
    LocalDate referenceDate3 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention2 = new DayCountConvention_30E_360(true);
    Period period2 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFromPeriods2 =
        new ScheduleFromPeriods(referenceDate3, daycountconvention2, period2);
    Schedule[] floatSchedules = new Schedule[] {scheduleFromPeriods2};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    RegressionBasisFunctionsProvider actualBasisFunctionsProviderWithSwapRates =
        bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithSwapRates();
    RandomVariable randomVariable = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable.div(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray);
    RandomVariable randomVariable2 = mock(RandomVariable.class);
    when(randomVariable2.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable2.add(Mockito.<RandomVariable>any())).thenReturn(randomVariable);
    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    when(hullWhiteModel.getForwardDiscountBond(
            Mockito.<MonteCarloProcess>any(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariable randomVariable3 = mock(RandomVariable.class);
    when(randomVariable3.mult(Mockito.<RandomVariable>any()))
        .thenReturn(mock(RandomVariable.class));
    RandomVariable randomVariable4 = mock(RandomVariable.class);
    when(randomVariable4.mult(anyDouble())).thenReturn(randomVariable3);
    RandomVariable randomVariable5 = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable5.pow(anyDouble())).thenReturn(randomVariableFromDoubleArray2);
    when(randomVariable5.mult(anyDouble())).thenReturn(randomVariable4);
    RandomVariable randomVariable6 = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray3 =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable6.invert()).thenReturn(randomVariableFromDoubleArray3);
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(liborMonteCarloSimulationFromLIBORModel.getNumeraire(anyDouble()))
        .thenReturn(randomVariable6);
    when(liborMonteCarloSimulationFromLIBORModel.getForwardRate(
            anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(randomVariable5);
    when(liborMonteCarloSimulationFromLIBORModel.getModel()).thenReturn(hullWhiteModel);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    when(liborMonteCarloSimulationFromLIBORModel.getProcess())
        .thenReturn(eulerSchemeFromProcessModel);
    when(liborMonteCarloSimulationFromLIBORModel.getRandomVariableForConstant(anyDouble()))
        .thenReturn(randomVariable2);
    when(liborMonteCarloSimulationFromLIBORModel.getReferenceDate())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    RandomVariable[] actualBasisFunctions =
        actualBasisFunctionsProviderWithSwapRates.getBasisFunctions(
            10.0d, liborMonteCarloSimulationFromLIBORModel);

    // Assert
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1))
        .getForwardRate(eq(10.0d), anyDouble(), eq(0.0d));
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1)).getModel();
    verify(liborMonteCarloSimulationFromLIBORModel).getNumeraire(10.0d);
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1)).getProcess();
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1)).getReferenceDate();
    verify(hullWhiteModel, atLeast(1))
        .getForwardDiscountBond(isA(MonteCarloProcess.class), eq(10.0d), eq(0.0d));
    verify(randomVariable2, atLeast(1)).add(Mockito.<RandomVariable>any());
    verify(randomVariable).div(isA(RandomVariable.class));
    verify(randomVariable6).invert();
    verify(randomVariable5).mult(0.0d);
    verify(randomVariable4).mult(1.0d);
    verify(randomVariable3).mult(isA(RandomVariable.class));
    verify(randomVariable2).mult(isA(RandomVariable.class));
    verify(randomVariable5).pow(2.0d);
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable7 = actualBasisFunctions[1];
    assertTrue(randomVariable7 instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable8 = actualBasisFunctions[3];
    assertTrue(randomVariable8 instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable9 = actualBasisFunctions[4];
    assertTrue(randomVariable9 instanceof RandomVariableFromDoubleArray);
    assertNull(bermudanSwaptionFromSwapSchedules.getCurrency());
    assertEquals(1, bermudanSwaptionFromSwapSchedules.getExerciseDates().length);
    assertEquals(5, actualBasisFunctions.length);
    assertEquals(SwaptionType.PAYER, bermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(randomVariableFromDoubleArray, randomVariable7);
    assertSame(randomVariableFromDoubleArray2, randomVariable8);
    assertSame(randomVariableFromDoubleArray3, randomVariable9);
    assertSame(swapEndDate, bermudanSwaptionFromSwapSchedules.getSwapEndDate());
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithSwapRates()}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariable#div(RandomVariable)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithSwapRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithSwapRates()"
  })
  public void testGetBasisFunctionsProviderWithSwapRates_thenCallsDiv()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {
          new RegularSchedule(
              new TenorFromArray(
                  3.1536E7d, 3.1536E7d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START))
        };
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate2, daycountconvention, period);
    Schedule[] floatSchedules = new Schedule[] {scheduleFromPeriods};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    RegressionBasisFunctionsProvider actualBasisFunctionsProviderWithSwapRates =
        bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithSwapRates();
    RandomVariable randomVariable = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable.div(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray);
    RandomVariable randomVariable2 = mock(RandomVariable.class);
    when(randomVariable2.add(Mockito.<RandomVariable>any())).thenReturn(randomVariable);
    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    when(hullWhiteModel.getForwardDiscountBond(
            Mockito.<MonteCarloProcess>any(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariable randomVariable3 = mock(RandomVariable.class);
    when(randomVariable3.mult(Mockito.<RandomVariable>any()))
        .thenReturn(mock(RandomVariable.class));
    RandomVariable randomVariable4 = mock(RandomVariable.class);
    when(randomVariable4.mult(anyDouble())).thenReturn(randomVariable3);
    RandomVariable randomVariable5 = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable5.pow(anyDouble())).thenReturn(randomVariableFromDoubleArray2);
    when(randomVariable5.mult(anyDouble())).thenReturn(randomVariable4);
    RandomVariable randomVariable6 = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray3 =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable6.invert()).thenReturn(randomVariableFromDoubleArray3);
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(liborMonteCarloSimulationFromLIBORModel.getNumeraire(anyDouble()))
        .thenReturn(randomVariable6);
    when(liborMonteCarloSimulationFromLIBORModel.getForwardRate(
            anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(randomVariable5);
    when(liborMonteCarloSimulationFromLIBORModel.getModel()).thenReturn(hullWhiteModel);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    when(liborMonteCarloSimulationFromLIBORModel.getProcess())
        .thenReturn(eulerSchemeFromProcessModel);
    when(liborMonteCarloSimulationFromLIBORModel.getRandomVariableForConstant(anyDouble()))
        .thenReturn(randomVariable2);
    when(liborMonteCarloSimulationFromLIBORModel.getReferenceDate())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    RandomVariable[] actualBasisFunctions =
        actualBasisFunctionsProviderWithSwapRates.getBasisFunctions(
            10.0d, liborMonteCarloSimulationFromLIBORModel);

    // Assert
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1))
        .getForwardRate(eq(10.0d), anyDouble(), eq(0.0d));
    verify(liborMonteCarloSimulationFromLIBORModel).getModel();
    verify(liborMonteCarloSimulationFromLIBORModel).getNumeraire(10.0d);
    verify(liborMonteCarloSimulationFromLIBORModel).getProcess();
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1)).getReferenceDate();
    verify(hullWhiteModel)
        .getForwardDiscountBond(isA(MonteCarloProcess.class), eq(10.0d), eq(0.0d));
    verify(randomVariable2).add(isA(RandomVariable.class));
    verify(randomVariable).div(isA(RandomVariable.class));
    verify(randomVariable6).invert();
    verify(randomVariable5).mult(0.0d);
    verify(randomVariable4).mult(1.0d);
    verify(randomVariable3).mult(isA(RandomVariable.class));
    verify(randomVariable5).pow(2.0d);
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable7 = actualBasisFunctions[1];
    assertTrue(randomVariable7 instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable8 = actualBasisFunctions[3];
    assertTrue(randomVariable8 instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable9 = actualBasisFunctions[4];
    assertTrue(randomVariable9 instanceof RandomVariableFromDoubleArray);
    assertNull(bermudanSwaptionFromSwapSchedules.getCurrency());
    assertEquals(1, bermudanSwaptionFromSwapSchedules.getExerciseDates().length);
    assertEquals(5, actualBasisFunctions.length);
    assertEquals(SwaptionType.PAYER, bermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(randomVariableFromDoubleArray, randomVariable7);
    assertSame(randomVariableFromDoubleArray2, randomVariable8);
    assertSame(randomVariableFromDoubleArray3, randomVariable9);
    assertSame(swapEndDate, bermudanSwaptionFromSwapSchedules.getSwapEndDate());
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithSwapRates()}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariable#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithSwapRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithSwapRates()"
  })
  public void testGetBasisFunctionsProviderWithSwapRates_thenCallsDoubleValue()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {
          new RegularSchedule(
              new TenorFromArray(
                  3.1536E7d, 3.1536E7d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START))
        };
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate2, daycountconvention, period);
    Schedule[] floatSchedules = new Schedule[] {scheduleFromPeriods};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    RegressionBasisFunctionsProvider actualBasisFunctionsProviderWithSwapRates =
        bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithSwapRates();
    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.doubleValue()).thenReturn(10.0d);
    when(randomVariable.isDeterministic()).thenReturn(true);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable.getTypePriority()).thenReturn(1);
    when(randomVariable.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    when(hullWhiteModel.getForwardDiscountBond(
            Mockito.<MonteCarloProcess>any(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariable randomVariable2 = mock(RandomVariable.class);
    when(randomVariable2.mult(Mockito.<RandomVariable>any()))
        .thenReturn(mock(RandomVariable.class));
    RandomVariable randomVariable3 = mock(RandomVariable.class);
    when(randomVariable3.mult(anyDouble())).thenReturn(randomVariable2);
    RandomVariable randomVariable4 = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable4.pow(anyDouble())).thenReturn(randomVariableFromDoubleArray);
    when(randomVariable4.mult(anyDouble())).thenReturn(randomVariable3);
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(liborMonteCarloSimulationFromLIBORModel.getNumeraire(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(liborMonteCarloSimulationFromLIBORModel.getForwardRate(
            anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(randomVariable4);
    when(liborMonteCarloSimulationFromLIBORModel.getModel()).thenReturn(hullWhiteModel);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    when(liborMonteCarloSimulationFromLIBORModel.getProcess())
        .thenReturn(eulerSchemeFromProcessModel);
    when(liborMonteCarloSimulationFromLIBORModel.getRandomVariableForConstant(anyDouble()))
        .thenReturn(randomVariable);
    when(liborMonteCarloSimulationFromLIBORModel.getReferenceDate())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    RandomVariable[] actualBasisFunctions =
        actualBasisFunctionsProviderWithSwapRates.getBasisFunctions(
            10.0d, liborMonteCarloSimulationFromLIBORModel);

    // Assert
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1))
        .getForwardRate(eq(10.0d), anyDouble(), eq(0.0d));
    verify(liborMonteCarloSimulationFromLIBORModel).getModel();
    verify(liborMonteCarloSimulationFromLIBORModel).getNumeraire(10.0d);
    verify(liborMonteCarloSimulationFromLIBORModel).getProcess();
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1)).getReferenceDate();
    verify(hullWhiteModel)
        .getForwardDiscountBond(isA(MonteCarloProcess.class), eq(10.0d), eq(0.0d));
    verify(randomVariable).add(isA(RandomVariable.class));
    verify(randomVariable).doubleValue();
    verify(randomVariable).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariable).isDeterministic();
    verify(randomVariable4).mult(0.0d);
    verify(randomVariable3).mult(1.0d);
    verify(randomVariable2).mult(isA(RandomVariable.class));
    verify(randomVariable4).pow(2.0d);
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[1] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable5 = actualBasisFunctions[3];
    assertTrue(randomVariable5 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertNull(bermudanSwaptionFromSwapSchedules.getCurrency());
    assertEquals(1, bermudanSwaptionFromSwapSchedules.getExerciseDates().length);
    assertEquals(5, actualBasisFunctions.length);
    assertEquals(SwaptionType.PAYER, bermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(randomVariableFromDoubleArray, randomVariable5);
    assertSame(swapEndDate, bermudanSwaptionFromSwapSchedules.getSwapEndDate());
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithSwapRates()}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariable#vid(RandomVariable)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithSwapRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithSwapRates()"
  })
  public void testGetBasisFunctionsProviderWithSwapRates_thenCallsVid()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {
          new RegularSchedule(
              new TenorFromArray(
                  3.1536E7d, 3.1536E7d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START))
        };
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate2, daycountconvention, period);
    Schedule[] floatSchedules = new Schedule[] {scheduleFromPeriods};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    RegressionBasisFunctionsProvider actualBasisFunctionsProviderWithSwapRates =
        bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithSwapRates();
    RandomVariable randomVariable = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable.vid(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray);
    when(randomVariable.getTypePriority()).thenReturn(1);
    when(randomVariable.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(3.1536E7d, 3.1536E7d, -3));
    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    when(hullWhiteModel.getForwardDiscountBond(
            Mockito.<MonteCarloProcess>any(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariable randomVariable2 = mock(RandomVariable.class);
    when(randomVariable2.mult(Mockito.<RandomVariable>any()))
        .thenReturn(mock(RandomVariable.class));
    RandomVariable randomVariable3 = mock(RandomVariable.class);
    when(randomVariable3.mult(anyDouble())).thenReturn(randomVariable2);
    RandomVariable randomVariable4 = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable4.pow(anyDouble())).thenReturn(randomVariableFromDoubleArray2);
    when(randomVariable4.mult(anyDouble())).thenReturn(randomVariable3);
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(liborMonteCarloSimulationFromLIBORModel.getNumeraire(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(liborMonteCarloSimulationFromLIBORModel.getForwardRate(
            anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(randomVariable4);
    when(liborMonteCarloSimulationFromLIBORModel.getModel()).thenReturn(hullWhiteModel);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    when(liborMonteCarloSimulationFromLIBORModel.getProcess())
        .thenReturn(eulerSchemeFromProcessModel);
    when(liborMonteCarloSimulationFromLIBORModel.getRandomVariableForConstant(anyDouble()))
        .thenReturn(randomVariable);
    when(liborMonteCarloSimulationFromLIBORModel.getReferenceDate())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    RandomVariable[] actualBasisFunctions =
        actualBasisFunctionsProviderWithSwapRates.getBasisFunctions(
            10.0d, liborMonteCarloSimulationFromLIBORModel);

    // Assert
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1))
        .getForwardRate(eq(10.0d), anyDouble(), eq(0.0d));
    verify(liborMonteCarloSimulationFromLIBORModel).getModel();
    verify(liborMonteCarloSimulationFromLIBORModel).getNumeraire(10.0d);
    verify(liborMonteCarloSimulationFromLIBORModel).getProcess();
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1)).getReferenceDate();
    verify(hullWhiteModel)
        .getForwardDiscountBond(isA(MonteCarloProcess.class), eq(10.0d), eq(0.0d));
    verify(randomVariable).add(isA(RandomVariable.class));
    verify(randomVariable).getTypePriority();
    verify(randomVariable4).mult(0.0d);
    verify(randomVariable3).mult(1.0d);
    verify(randomVariable2).mult(isA(RandomVariable.class));
    verify(randomVariable4).pow(2.0d);
    verify(randomVariable).vid(isA(RandomVariable.class));
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable5 = actualBasisFunctions[1];
    assertTrue(randomVariable5 instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable6 = actualBasisFunctions[3];
    assertTrue(randomVariable6 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertNull(bermudanSwaptionFromSwapSchedules.getCurrency());
    assertEquals(1, bermudanSwaptionFromSwapSchedules.getExerciseDates().length);
    assertEquals(5, actualBasisFunctions.length);
    assertEquals(SwaptionType.PAYER, bermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(randomVariableFromDoubleArray, randomVariable5);
    assertSame(randomVariableFromDoubleArray2, randomVariable6);
    assertSame(swapEndDate, bermudanSwaptionFromSwapSchedules.getSwapEndDate());
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithSwapRates()}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariable#vid(RandomVariable)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithSwapRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithSwapRates()"
  })
  public void testGetBasisFunctionsProviderWithSwapRates_thenCallsVid2()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {
          new RegularSchedule(
              new TenorFromArray(
                  3.1536E7d, 3.1536E7d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START))
        };
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods scheduleFromPeriods =
        new ScheduleFromPeriods(referenceDate2, daycountconvention, period);
    Schedule[] floatSchedules = new Schedule[] {scheduleFromPeriods};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    RegressionBasisFunctionsProvider actualBasisFunctionsProviderWithSwapRates =
        bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithSwapRates();
    RandomVariable randomVariable = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable.vid(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray);
    when(randomVariable.getTypePriority()).thenReturn(1);
    when(randomVariable.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(3.1536E7d, 3.1536E7d, -3));
    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    when(hullWhiteModel.getForwardDiscountBond(
            Mockito.<MonteCarloProcess>any(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariable randomVariable2 = mock(RandomVariable.class);
    when(randomVariable2.mult(Mockito.<RandomVariable>any()))
        .thenReturn(mock(RandomVariable.class));
    RandomVariable randomVariable3 = mock(RandomVariable.class);
    when(randomVariable3.mult(anyDouble())).thenReturn(randomVariable2);
    RandomVariable randomVariable4 = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable4.pow(anyDouble())).thenReturn(randomVariableFromDoubleArray2);
    when(randomVariable4.mult(anyDouble())).thenReturn(randomVariable3);
    RandomVariable randomVariable5 = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray3 =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable5.invert()).thenReturn(randomVariableFromDoubleArray3);
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(liborMonteCarloSimulationFromLIBORModel.getNumeraire(anyDouble()))
        .thenReturn(randomVariable5);
    when(liborMonteCarloSimulationFromLIBORModel.getForwardRate(
            anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(randomVariable4);
    when(liborMonteCarloSimulationFromLIBORModel.getModel()).thenReturn(hullWhiteModel);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    when(liborMonteCarloSimulationFromLIBORModel.getProcess())
        .thenReturn(eulerSchemeFromProcessModel);
    when(liborMonteCarloSimulationFromLIBORModel.getRandomVariableForConstant(anyDouble()))
        .thenReturn(randomVariable);
    when(liborMonteCarloSimulationFromLIBORModel.getReferenceDate())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    RandomVariable[] actualBasisFunctions =
        actualBasisFunctionsProviderWithSwapRates.getBasisFunctions(
            10.0d, liborMonteCarloSimulationFromLIBORModel);

    // Assert
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1))
        .getForwardRate(eq(10.0d), anyDouble(), eq(0.0d));
    verify(liborMonteCarloSimulationFromLIBORModel).getModel();
    verify(liborMonteCarloSimulationFromLIBORModel).getNumeraire(10.0d);
    verify(liborMonteCarloSimulationFromLIBORModel).getProcess();
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1)).getRandomVariableForConstant(0.0d);
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1)).getReferenceDate();
    verify(hullWhiteModel)
        .getForwardDiscountBond(isA(MonteCarloProcess.class), eq(10.0d), eq(0.0d));
    verify(randomVariable).add(isA(RandomVariable.class));
    verify(randomVariable).getTypePriority();
    verify(randomVariable5).invert();
    verify(randomVariable4).mult(0.0d);
    verify(randomVariable3).mult(1.0d);
    verify(randomVariable2).mult(isA(RandomVariable.class));
    verify(randomVariable4).pow(2.0d);
    verify(randomVariable).vid(isA(RandomVariable.class));
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable6 = actualBasisFunctions[1];
    assertTrue(randomVariable6 instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable7 = actualBasisFunctions[3];
    assertTrue(randomVariable7 instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable8 = actualBasisFunctions[4];
    assertTrue(randomVariable8 instanceof RandomVariableFromDoubleArray);
    assertNull(bermudanSwaptionFromSwapSchedules.getCurrency());
    assertEquals(1, bermudanSwaptionFromSwapSchedules.getExerciseDates().length);
    assertEquals(5, actualBasisFunctions.length);
    assertEquals(SwaptionType.PAYER, bermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(randomVariableFromDoubleArray, randomVariable6);
    assertSame(randomVariableFromDoubleArray2, randomVariable7);
    assertSame(randomVariableFromDoubleArray3, randomVariable8);
    assertSame(swapEndDate, bermudanSwaptionFromSwapSchedules.getSwapEndDate());
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates()"
  })
  public void testGetBasisFunctionsProviderWithForwardRates() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate ofResult = LocalDate.of(1970, 1, 1);
    LocalDate[] exerciseDates = new LocalDate[] {ofResult};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates();

    // Assert that nothing has changed
    LocalDate swapEndDate2 = bermudanSwaptionFromSwapSchedules.getSwapEndDate();
    assertEquals("1970-01-01", swapEndDate2.toString());
    LocalDate[] exerciseDates2 = bermudanSwaptionFromSwapSchedules.getExerciseDates();
    LocalDate localDate = exerciseDates2[0];
    assertEquals("1970-01-01", localDate.toString());
    assertEquals(1, exerciseDates2.length);
    assertEquals(SwaptionType.PAYER, bermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(swapEndDate, swapEndDate2);
    assertSame(ofResult, localDate);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates()"
  })
  public void testGetBasisFunctionsProviderWithForwardRates2() throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    RegressionBasisFunctionsProvider actualBasisFunctionsProviderWithForwardRates =
        bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates();
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(liborMonteCarloSimulationFromLIBORModel.getNumeraire(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(liborMonteCarloSimulationFromLIBORModel.getForwardRate(
            anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(randomVariableFromDoubleArray);
    when(liborMonteCarloSimulationFromLIBORModel.getReferenceDate())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    RandomVariable[] actualBasisFunctions =
        actualBasisFunctionsProviderWithForwardRates.getBasisFunctions(
            10.0d, liborMonteCarloSimulationFromLIBORModel);

    // Assert
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1))
        .getForwardRate(eq(10.0d), anyDouble(), eq(0.0d));
    verify(liborMonteCarloSimulationFromLIBORModel).getNumeraire(10.0d);
    verify(liborMonteCarloSimulationFromLIBORModel).getReferenceDate();
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable = actualBasisFunctions[1];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[5] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[6] instanceof RandomVariableFromDoubleArray);
    assertNull(bermudanSwaptionFromSwapSchedules.getCurrency());
    assertEquals(1, bermudanSwaptionFromSwapSchedules.getExerciseDates().length);
    assertEquals(7, actualBasisFunctions.length);
    assertEquals(SwaptionType.PAYER, bermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(randomVariableFromDoubleArray, randomVariable);
    assertSame(randomVariableFromDoubleArray, actualBasisFunctions[3]);
    assertSame(swapEndDate, bermudanSwaptionFromSwapSchedules.getSwapEndDate());
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates()"
  })
  public void testGetBasisFunctionsProviderWithForwardRates3() throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    RegressionBasisFunctionsProvider actualBasisFunctionsProviderWithForwardRates =
        bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates();
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(liborMonteCarloSimulationFromLIBORModel.getNumeraire(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(3.1536E7d, 3.1536E7d, -3);
    when(liborMonteCarloSimulationFromLIBORModel.getForwardRate(
            anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(randomVariableFromDoubleArray);
    when(liborMonteCarloSimulationFromLIBORModel.getReferenceDate())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    RandomVariable[] actualBasisFunctions =
        actualBasisFunctionsProviderWithForwardRates.getBasisFunctions(
            10.0d, liborMonteCarloSimulationFromLIBORModel);

    // Assert
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1))
        .getForwardRate(eq(10.0d), anyDouble(), eq(0.0d));
    verify(liborMonteCarloSimulationFromLIBORModel).getNumeraire(10.0d);
    verify(liborMonteCarloSimulationFromLIBORModel).getReferenceDate();
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable = actualBasisFunctions[1];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[5] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[6] instanceof RandomVariableFromDoubleArray);
    assertNull(bermudanSwaptionFromSwapSchedules.getCurrency());
    assertEquals(1, bermudanSwaptionFromSwapSchedules.getExerciseDates().length);
    assertEquals(7, actualBasisFunctions.length);
    assertEquals(SwaptionType.PAYER, bermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(randomVariableFromDoubleArray, randomVariable);
    assertSame(randomVariableFromDoubleArray, actualBasisFunctions[3]);
    assertSame(swapEndDate, bermudanSwaptionFromSwapSchedules.getSwapEndDate());
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates()"
  })
  public void testGetBasisFunctionsProviderWithForwardRates4() throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    RegressionBasisFunctionsProvider actualBasisFunctionsProviderWithForwardRates =
        bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates();
    RandomVariable randomVariable = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable.invert()).thenReturn(randomVariableFromDoubleArray);
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(liborMonteCarloSimulationFromLIBORModel.getNumeraire(anyDouble()))
        .thenReturn(randomVariable);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(liborMonteCarloSimulationFromLIBORModel.getForwardRate(
            anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(randomVariableFromDoubleArray2);
    when(liborMonteCarloSimulationFromLIBORModel.getReferenceDate())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    RandomVariable[] actualBasisFunctions =
        actualBasisFunctionsProviderWithForwardRates.getBasisFunctions(
            10.0d, liborMonteCarloSimulationFromLIBORModel);

    // Assert
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1))
        .getForwardRate(eq(10.0d), anyDouble(), eq(0.0d));
    verify(liborMonteCarloSimulationFromLIBORModel).getNumeraire(10.0d);
    verify(liborMonteCarloSimulationFromLIBORModel).getReferenceDate();
    verify(randomVariable).invert();
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable2 = actualBasisFunctions[1];
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[4] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable3 = actualBasisFunctions[5];
    assertTrue(randomVariable3 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[6] instanceof RandomVariableFromDoubleArray);
    assertNull(bermudanSwaptionFromSwapSchedules.getCurrency());
    assertEquals(1, bermudanSwaptionFromSwapSchedules.getExerciseDates().length);
    assertEquals(7, actualBasisFunctions.length);
    assertEquals(SwaptionType.PAYER, bermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(randomVariableFromDoubleArray2, randomVariable2);
    assertSame(randomVariableFromDoubleArray2, actualBasisFunctions[3]);
    assertSame(randomVariableFromDoubleArray, randomVariable3);
    assertSame(swapEndDate, bermudanSwaptionFromSwapSchedules.getSwapEndDate());
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates()"
  })
  public void testGetBasisFunctionsProviderWithForwardRates5() throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    RegressionBasisFunctionsProvider actualBasisFunctionsProviderWithForwardRates =
        bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates();
    RandomVariable randomVariable = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray);
    when(randomVariable.getTypePriority()).thenReturn(1);
    RandomVariable randomVariable2 = mock(RandomVariable.class);
    when(randomVariable2.invert()).thenReturn(randomVariable);
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(liborMonteCarloSimulationFromLIBORModel.getNumeraire(anyDouble()))
        .thenReturn(randomVariable2);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(3.1536E7d, 3.1536E7d, -3);
    when(liborMonteCarloSimulationFromLIBORModel.getForwardRate(
            anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(randomVariableFromDoubleArray2);
    when(liborMonteCarloSimulationFromLIBORModel.getReferenceDate())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    RandomVariable[] actualBasisFunctions =
        actualBasisFunctionsProviderWithForwardRates.getBasisFunctions(
            10.0d, liborMonteCarloSimulationFromLIBORModel);

    // Assert
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1))
        .getForwardRate(eq(10.0d), anyDouble(), eq(0.0d));
    verify(liborMonteCarloSimulationFromLIBORModel).getNumeraire(10.0d);
    verify(liborMonteCarloSimulationFromLIBORModel).getReferenceDate();
    verify(randomVariable).getTypePriority();
    verify(randomVariable2).invert();
    verify(randomVariable).mult(isA(RandomVariable.class));
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable3 = actualBasisFunctions[1];
    assertTrue(randomVariable3 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[4] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable4 = actualBasisFunctions[6];
    assertTrue(randomVariable4 instanceof RandomVariableFromDoubleArray);
    assertNull(bermudanSwaptionFromSwapSchedules.getCurrency());
    assertEquals(1, bermudanSwaptionFromSwapSchedules.getExerciseDates().length);
    assertEquals(7, actualBasisFunctions.length);
    assertEquals(SwaptionType.PAYER, bermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(randomVariableFromDoubleArray, randomVariable4);
    assertSame(randomVariableFromDoubleArray2, randomVariable3);
    assertSame(randomVariableFromDoubleArray2, actualBasisFunctions[3]);
    assertSame(swapEndDate, bermudanSwaptionFromSwapSchedules.getSwapEndDate());
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}.
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates()"
  })
  public void testGetBasisFunctionsProviderWithForwardRates6() throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    RegressionBasisFunctionsProvider actualBasisFunctionsProviderWithForwardRates =
        bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates();
    RandomVariable randomVariable = mock(RandomVariable.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariable.pow(anyDouble())).thenReturn(randomVariableFromDoubleArray2);
    RandomVariable randomVariable2 = mock(RandomVariable.class);
    when(randomVariable2.invert()).thenReturn(mock(RandomVariable.class));
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(liborMonteCarloSimulationFromLIBORModel.getNumeraire(anyDouble()))
        .thenReturn(randomVariable2);
    when(liborMonteCarloSimulationFromLIBORModel.getForwardRate(
            anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(randomVariable);
    when(liborMonteCarloSimulationFromLIBORModel.getReferenceDate())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    RandomVariable[] actualBasisFunctions =
        actualBasisFunctionsProviderWithForwardRates.getBasisFunctions(
            10.0d, liborMonteCarloSimulationFromLIBORModel);

    // Assert
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1))
        .getForwardRate(eq(10.0d), anyDouble(), eq(0.0d));
    verify(liborMonteCarloSimulationFromLIBORModel).getNumeraire(10.0d);
    verify(liborMonteCarloSimulationFromLIBORModel).getReferenceDate();
    verify(randomVariable2).invert();
    verify(randomVariable).mult(isA(RandomVariable.class));
    verify(randomVariable, atLeast(1)).pow(2.0d);
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable3 = actualBasisFunctions[2];
    assertTrue(randomVariable3 instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable4 = actualBasisFunctions[6];
    assertTrue(randomVariable4 instanceof RandomVariableFromDoubleArray);
    assertNull(bermudanSwaptionFromSwapSchedules.getCurrency());
    assertEquals(1, bermudanSwaptionFromSwapSchedules.getExerciseDates().length);
    assertEquals(7, actualBasisFunctions.length);
    assertEquals(SwaptionType.PAYER, bermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(randomVariableFromDoubleArray2, randomVariable3);
    assertSame(randomVariableFromDoubleArray2, actualBasisFunctions[4]);
    assertSame(randomVariableFromDoubleArray, randomVariable4);
    assertSame(swapEndDate, bermudanSwaptionFromSwapSchedules.getSwapEndDate());
    RandomVariable expectedRandomVariable = actualBasisFunctions[1];
    assertSame(expectedRandomVariable, actualBasisFunctions[3]);
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariable#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates()"
  })
  public void testGetBasisFunctionsProviderWithForwardRates_thenCallsDoubleValue()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    RegressionBasisFunctionsProvider actualBasisFunctionsProviderWithForwardRates =
        bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates();
    RandomVariable randomVariable = mock(RandomVariable.class);
    when(randomVariable.doubleValue()).thenReturn(10.0d);
    when(randomVariable.isDeterministic()).thenReturn(true);
    when(randomVariable.getFiltrationTime()).thenReturn(10.0d);
    when(randomVariable.getTypePriority()).thenReturn(1);
    RandomVariable randomVariable2 = mock(RandomVariable.class);
    when(randomVariable2.invert()).thenReturn(randomVariable);
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(liborMonteCarloSimulationFromLIBORModel.getNumeraire(anyDouble()))
        .thenReturn(randomVariable2);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(liborMonteCarloSimulationFromLIBORModel.getForwardRate(
            anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(randomVariableFromDoubleArray);
    when(liborMonteCarloSimulationFromLIBORModel.getReferenceDate())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    RandomVariable[] actualBasisFunctions =
        actualBasisFunctionsProviderWithForwardRates.getBasisFunctions(
            10.0d, liborMonteCarloSimulationFromLIBORModel);

    // Assert
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1))
        .getForwardRate(eq(10.0d), anyDouble(), eq(0.0d));
    verify(liborMonteCarloSimulationFromLIBORModel).getNumeraire(10.0d);
    verify(liborMonteCarloSimulationFromLIBORModel).getReferenceDate();
    verify(randomVariable).doubleValue();
    verify(randomVariable).getFiltrationTime();
    verify(randomVariable).getTypePriority();
    verify(randomVariable2).invert();
    verify(randomVariable).isDeterministic();
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    RandomVariable randomVariable3 = actualBasisFunctions[1];
    assertTrue(randomVariable3 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[6] instanceof RandomVariableFromDoubleArray);
    assertNull(bermudanSwaptionFromSwapSchedules.getCurrency());
    assertEquals(1, bermudanSwaptionFromSwapSchedules.getExerciseDates().length);
    assertEquals(7, actualBasisFunctions.length);
    assertEquals(SwaptionType.PAYER, bermudanSwaptionFromSwapSchedules.getSwaptionType());
    assertSame(randomVariableFromDoubleArray, randomVariable3);
    assertSame(randomVariableFromDoubleArray, actualBasisFunctions[3]);
    assertSame(swapEndDate, bermudanSwaptionFromSwapSchedules.getSwapEndDate());
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}.
   *
   * <ul>
   *   <li>Then throw {@link CalculationException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates()"
  })
  public void testGetBasisFunctionsProviderWithForwardRates_thenThrowCalculationException()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    RegressionBasisFunctionsProvider actualBasisFunctionsProviderWithForwardRates =
        bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates();
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(liborMonteCarloSimulationFromLIBORModel.getForwardRate(
            anyDouble(), anyDouble(), anyDouble()))
        .thenThrow(new CalculationException("An error occurred"));
    when(liborMonteCarloSimulationFromLIBORModel.getReferenceDate())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    assertThrows(
        CalculationException.class,
        () ->
            actualBasisFunctionsProviderWithForwardRates.getBasisFunctions(
                10.0d, liborMonteCarloSimulationFromLIBORModel));
    verify(liborMonteCarloSimulationFromLIBORModel).getForwardRate(10.0d, 10.0d, 0.0d);
    verify(liborMonteCarloSimulationFromLIBORModel).getReferenceDate();
  }

  /**
   * Test {@link BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}.
   *
   * <ul>
   *   <li>Then throw {@link CalculationException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BermudanSwaptionFromSwapSchedules#getBasisFunctionsProviderWithForwardRates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RegressionBasisFunctionsProvider BermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates()"
  })
  public void testGetBasisFunctionsProviderWithForwardRates_thenThrowCalculationException2()
      throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDate[] exerciseDates = new LocalDate[] {LocalDate.of(1970, 1, 1)};
    LocalDate swapEndDate = LocalDate.of(1970, 1, 1);
    Schedule[] fixSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};
    Schedule[] floatSchedules =
        new Schedule[] {new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d))};

    BermudanSwaptionFromSwapSchedules bermudanSwaptionFromSwapSchedules =
        new BermudanSwaptionFromSwapSchedules(
            referenceDate,
            SwaptionType.PAYER,
            exerciseDates,
            swapEndDate,
            10.0d,
            10.0d,
            fixSchedules,
            floatSchedules);

    // Act
    RegressionBasisFunctionsProvider actualBasisFunctionsProviderWithForwardRates =
        bermudanSwaptionFromSwapSchedules.getBasisFunctionsProviderWithForwardRates();
    LIBORMonteCarloSimulationFromLIBORModel liborMonteCarloSimulationFromLIBORModel =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(liborMonteCarloSimulationFromLIBORModel.getNumeraire(anyDouble()))
        .thenThrow(new CalculationException("An error occurred"));
    when(liborMonteCarloSimulationFromLIBORModel.getForwardRate(
            anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(liborMonteCarloSimulationFromLIBORModel.getReferenceDate())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    assertThrows(
        CalculationException.class,
        () ->
            actualBasisFunctionsProviderWithForwardRates.getBasisFunctions(
                10.0d, liborMonteCarloSimulationFromLIBORModel));
    verify(liborMonteCarloSimulationFromLIBORModel, atLeast(1))
        .getForwardRate(eq(10.0d), anyDouble(), eq(0.0d));
    verify(liborMonteCarloSimulationFromLIBORModel).getNumeraire(10.0d);
    verify(liborMonteCarloSimulationFromLIBORModel).getReferenceDate();
  }
}
