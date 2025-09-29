package net.finmath.montecarlo.interestrate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.models.HullWhiteModel;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelFromCovarianceModel;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelStandard;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TermStructureMonteCarloSimulationModelDiffblueTest {
  /**
   * Test {@link TermStructureMonteCarloSimulationModel#getForwardRate(LocalDateTime, LocalDateTime,
   * LocalDateTime)} with {@code LocalDateTime}, {@code LocalDateTime}, {@code LocalDateTime}.
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationModel#getForwardRate(LocalDateTime, LocalDateTime,
   * LocalDateTime)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable TermStructureMonteCarloSimulationModel.getForwardRate(LocalDateTime, LocalDateTime, LocalDateTime)"
  })
  public void testGetForwardRateWithLocalDateTimeLocalDateTimeLocalDateTime()
      throws CalculationException {
    // Arrange
    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(hullWhiteModel.getForwardRate(
            Mockito.<MonteCarloProcess>any(), anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(randomVariableFromDoubleArray);
    when(hullWhiteModel.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getModel()).thenReturn(hullWhiteModel);

    // Act
    RandomVariable actualForwardRate =
        new LIBORMonteCarloSimulationFromLIBORModel(process)
            .getForwardRate(
                LocalDate.of(1970, 1, 1).atStartOfDay(),
                LocalDate.of(1970, 1, 1).atStartOfDay(),
                LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    verify(hullWhiteModel)
        .getForwardRate(isA(MonteCarloProcess.class), eq(0.0d), eq(0.0d), eq(0.0d));
    verify(hullWhiteModel).getReferenceDate();
    verify(process).getModel();
    assertSame(randomVariableFromDoubleArray, actualForwardRate);
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationModel#getForwardRate(LocalDateTime, LocalDateTime,
   * LocalDateTime)} with {@code LocalDateTime}, {@code LocalDateTime}, {@code LocalDateTime}.
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationModel#getForwardRate(LocalDateTime, LocalDateTime,
   * LocalDateTime)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable TermStructureMonteCarloSimulationModel.getForwardRate(LocalDateTime, LocalDateTime, LocalDateTime)"
  })
  public void testGetForwardRateWithLocalDateTimeLocalDateTimeLocalDateTime2()
      throws CalculationException {
    // Arrange
    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    when(hullWhiteModel.getForwardRate(
            Mockito.<MonteCarloProcess>any(), anyDouble(), anyDouble(), anyDouble()))
        .thenThrow(new CalculationException("An error occurred"));
    when(hullWhiteModel.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getModel()).thenReturn(hullWhiteModel);

    // Act and Assert
    assertThrows(
        CalculationException.class,
        () ->
            new LIBORMonteCarloSimulationFromLIBORModel(process)
                .getForwardRate(
                    LocalDate.of(1970, 1, 1).atStartOfDay(),
                    LocalDate.of(1970, 1, 1).atStartOfDay(),
                    LocalDate.of(1970, 1, 1).atStartOfDay()));
    verify(hullWhiteModel)
        .getForwardRate(isA(MonteCarloProcess.class), eq(0.0d), eq(0.0d), eq(0.0d));
    verify(hullWhiteModel).getReferenceDate();
    verify(process).getModel();
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationModel#getNumeraire(LocalDateTime)} with {@code
   * LocalDateTime}.
   *
   * <ul>
   *   <li>Then calls {@link BrownianMotion#getTimeDiscretization()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructureMonteCarloSimulationModel#getNumeraire(LocalDateTime)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable TermStructureMonteCarloSimulationModel.getNumeraire(LocalDateTime)"
  })
  public void testGetNumeraireWithLocalDateTime_thenCallsGetTimeDiscretization()
      throws CalculationException {
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
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.now(), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel model =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    new LIBORMonteCarloSimulationFromLIBORModel(process)
        .getNumeraire(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationModel#getLIBOR(LocalDateTime, LocalDateTime,
   * LocalDateTime)} with {@code date}, {@code periodStartDate}, {@code periodEndDate}.
   *
   * <p>Method under test: {@link TermStructureMonteCarloSimulationModel#getLIBOR(LocalDateTime,
   * LocalDateTime, LocalDateTime)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable TermStructureMonteCarloSimulationModel.getLIBOR(LocalDateTime, LocalDateTime, LocalDateTime)"
  })
  public void testGetLIBORWithDatePeriodStartDatePeriodEndDate() throws CalculationException {
    // Arrange
    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(hullWhiteModel.getForwardRate(
            Mockito.<MonteCarloProcess>any(), anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(randomVariableFromDoubleArray);
    when(hullWhiteModel.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getModel()).thenReturn(hullWhiteModel);

    // Act
    RandomVariable actualLIBOR =
        new LIBORMonteCarloSimulationFromLIBORModel(process)
            .getLIBOR(
                LocalDate.of(1970, 1, 1).atStartOfDay(),
                LocalDate.of(1970, 1, 1).atStartOfDay(),
                LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    verify(hullWhiteModel)
        .getForwardRate(isA(MonteCarloProcess.class), eq(0.0d), eq(0.0d), eq(0.0d));
    verify(hullWhiteModel).getReferenceDate();
    verify(process).getModel();
    assertSame(randomVariableFromDoubleArray, actualLIBOR);
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationModel#getLIBOR(LocalDateTime, LocalDateTime,
   * LocalDateTime)} with {@code date}, {@code periodStartDate}, {@code periodEndDate}.
   *
   * <ul>
   *   <li>Then throw {@link CalculationException}.
   * </ul>
   *
   * <p>Method under test: {@link TermStructureMonteCarloSimulationModel#getLIBOR(LocalDateTime,
   * LocalDateTime, LocalDateTime)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable TermStructureMonteCarloSimulationModel.getLIBOR(LocalDateTime, LocalDateTime, LocalDateTime)"
  })
  public void testGetLIBORWithDatePeriodStartDatePeriodEndDate_thenThrowCalculationException()
      throws CalculationException {
    // Arrange
    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    when(hullWhiteModel.getForwardRate(
            Mockito.<MonteCarloProcess>any(), anyDouble(), anyDouble(), anyDouble()))
        .thenThrow(new CalculationException("An error occurred"));
    when(hullWhiteModel.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getModel()).thenReturn(hullWhiteModel);

    // Act and Assert
    assertThrows(
        CalculationException.class,
        () ->
            new LIBORMonteCarloSimulationFromLIBORModel(process)
                .getLIBOR(
                    LocalDate.of(1970, 1, 1).atStartOfDay(),
                    LocalDate.of(1970, 1, 1).atStartOfDay(),
                    LocalDate.of(1970, 1, 1).atStartOfDay()));
    verify(hullWhiteModel)
        .getForwardRate(isA(MonteCarloProcess.class), eq(0.0d), eq(0.0d), eq(0.0d));
    verify(hullWhiteModel).getReferenceDate();
    verify(process).getModel();
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationModel#getLIBOR(double, double, double)} with
   * {@code time}, {@code periodStart}, {@code periodEnd}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link TermStructureMonteCarloSimulationModel#getLIBOR(double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable TermStructureMonteCarloSimulationModel.getLIBOR(double, double, double)"
  })
  public void testGetLIBORWithTimePeriodStartPeriodEnd_thenReturnScalar()
      throws CalculationException {
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
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelStandard model =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualLIBOR =
        new LIBORMonteCarloSimulationFromLIBORModel(process).getLIBOR(10.0d, 10.0d, 10.0d);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualLIBOR instanceof Scalar);
    assertTrue(actualLIBOR.abs() instanceof Scalar);
    assertTrue(actualLIBOR.cos() instanceof Scalar);
    assertTrue(actualLIBOR.exp() instanceof Scalar);
    assertTrue(actualLIBOR.expm1() instanceof Scalar);
    assertTrue(actualLIBOR.invert() instanceof Scalar);
    assertTrue(actualLIBOR.isNaN() instanceof Scalar);
    assertTrue(actualLIBOR.sin() instanceof Scalar);
    assertTrue(actualLIBOR.sqrt() instanceof Scalar);
    assertTrue(actualLIBOR.squared() instanceof Scalar);
    assertTrue(actualLIBOR.variance() instanceof Scalar);
    assertNull(actualLIBOR.getRealizations());
    assertNull(actualLIBOR.getOperator());
    assertNull(actualLIBOR.getRealizationsStream());
    assertEquals(0, actualLIBOR.getTypePriority());
    assertEquals(0.0d, actualLIBOR.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualLIBOR.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualLIBOR.getStandardError(), 0.0);
    assertEquals(0.0d, actualLIBOR.getVariance(), 0.0);
    assertEquals(1, actualLIBOR.size());
    assertTrue(actualLIBOR.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualLIBOR.getFiltrationTime(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getAverage(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getMax(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualLIBOR.expectation();
    assertSame(actualLIBOR, actualExpectationResult);
  }

  /**
   * Test {@link TermStructureMonteCarloSimulationModel#getBrownianMotion()}.
   *
   * <p>Method under test: {@link TermStructureMonteCarloSimulationModel#getBrownianMotion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BrownianMotion TermStructureMonteCarloSimulationModel.getBrownianMotion()"})
  public void testGetBrownianMotion() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    BrownianMotion actualBrownianMotion =
        new LIBORMonteCarloSimulationFromTermStructureModel(process).getBrownianMotion();

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertSame(stochasticDriver, actualBrownianMotion);
  }
}
