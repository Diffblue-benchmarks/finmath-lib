package net.finmath.montecarlo.interestrate.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
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
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelStandard.Driftapproximation;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelStandard.Measure;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelBH;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelExponentialForm5Param;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelExponentialForm7Param;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelStochasticHestonVolatility;
import net.finmath.montecarlo.interestrate.models.covariance.ShortRateVolatilityModelHoLee;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class LIBORMarketModelStandardDiffblueTest {
  /**
   * Test {@link LIBORMarketModelStandard#LIBORMarketModelStandard(TimeDiscretization, ForwardCurve,
   * DiscountCurve, LIBORCovarianceModel)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelStandard#LIBORMarketModelStandard(TimeDiscretization, ForwardCurve,
   * DiscountCurve, LIBORCovarianceModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelStandard.<init>(TimeDiscretization, ForwardCurve, DiscountCurve, LIBORCovarianceModel)"
  })
  public void testNewLIBORMarketModelStandard() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    // Act
    LIBORMarketModelStandard actualLiborMarketModelStandard =
        new LIBORMarketModelStandard(
            liborPeriodDiscretization, forwardRateCurve, discountCurve, covarianceModel2);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    DiscountCurve discountCurve2 = actualLiborMarketModelStandard.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualLiborMarketModelStandard.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 = actualLiborMarketModelStandard.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelStandard.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertNull(actualLiborMarketModelStandard.getAnalyticModel());
    assertNull(actualLiborMarketModelStandard.getSwaptionMarketData());
    assertEquals(10, actualLiborMarketModelStandard.getNumberOfComponents());
    assertEquals(10, actualLiborMarketModelStandard.getNumberOfLibors());
    assertEquals(3, actualLiborMarketModelStandard.getNumberOfFactors());
    assertEquals(
        Driftapproximation.EULER, actualLiborMarketModelStandard.getDriftApproximationMethod());
    assertEquals(Measure.SPOT, actualLiborMarketModelStandard.getMeasure());
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(covarianceModel2, covarianceModel3);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link LIBORMarketModelStandard#LIBORMarketModelStandard(TimeDiscretization, ForwardCurve,
   * LIBORCovarianceModel)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelStandard#LIBORMarketModelStandard(TimeDiscretization, ForwardCurve,
   * LIBORCovarianceModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORMarketModelStandard.<init>(TimeDiscretization, ForwardCurve, LIBORCovarianceModel)"
  })
  public void testNewLIBORMarketModelStandard2() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    // Act
    LIBORMarketModelStandard actualLiborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        actualLiborMarketModelStandard.getDiscountCurve() instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualLiborMarketModelStandard.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 = actualLiborMarketModelStandard.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborMarketModelStandard.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertNull(actualLiborMarketModelStandard.getAnalyticModel());
    assertNull(actualLiborMarketModelStandard.getSwaptionMarketData());
    assertEquals(10, actualLiborMarketModelStandard.getNumberOfComponents());
    assertEquals(10, actualLiborMarketModelStandard.getNumberOfLibors());
    assertEquals(3, actualLiborMarketModelStandard.getNumberOfFactors());
    assertEquals(
        Driftapproximation.EULER, actualLiborMarketModelStandard.getDriftApproximationMethod());
    assertEquals(Measure.SPOT, actualLiborMarketModelStandard.getMeasure());
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(covarianceModel2, covarianceModel3);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getNumeraire(MonteCarloProcess, double)}.
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire() throws CalculationException {
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
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {1.0d, 10.0d, 1.0d, 10.0d});
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualNumeraire = liborMarketModelStandard.getNumeraire(process, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {1982382.9215326046d}, actualNumeraire.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getNumeraire(MonteCarloProcess, double)}.
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire2() throws CalculationException {
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
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {1.0d, 10.0d, 1.0d, 10.0d});
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableFloatFactory());
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = liborMarketModelStandard.getNumeraire(process, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {1982382.9215326046d}, actualNumeraire.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getNumeraire(MonteCarloProcess, double)}.
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire3() throws CalculationException {
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
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {1.0d, 10.0d, 1.0d, 10.0d});
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers stochasticDriver =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualNumeraire = liborMarketModelStandard.getNumeraire(process, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {1982382.9215326046d}, actualNumeraire.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_thenReturnAverageIsOne() throws CalculationException {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = liborMarketModelStandard.getNumeraire(process, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualNumeraire.getAverage(), 0.0);
    assertEquals(1.0d, actualNumeraire.getMax(), 0.0);
    assertEquals(1.0d, actualNumeraire.getMin(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualNumeraire.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getInitialState(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelStandard.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState_thenFirstElementReturnRandomVariableFromDoubleArray() {
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
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {365.0d, 10.0d, 365.0d, 10.0d},
            new double[] {365.0d, 10.0d, 365.0d, 10.0d},
            365.0d);

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = liborMarketModelStandard.getInitialState(process);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualInitialState[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialState[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialState[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialState[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialState[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialState[5] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialState[6] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialState[7] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialState[8] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualInitialState[9] instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualInitialState.length);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getInitialState(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelStandard.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState_thenReturnArrayLengthIsZero() {
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
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(365.0d, 365.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = liborMarketModelStandard.getInitialState(process);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(0, actualInitialState.length);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])} with {@code process}, {@code timeIndex}, {@code realizationAtTimeIndex},
   * {@code realizationPredictor}.
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelStandard.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDriftWithProcessTimeIndexRealizationAtTimeIndexRealizationPredictor() {
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
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            0.5d,
            ShortPeriodLocation.SHORT_PERIOD_AT_START);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable[] actualDrift =
        liborMarketModelStandard.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    assertEquals(0, actualDrift.length);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])} with {@code process}, {@code timeIndex}, {@code realizationAtTimeIndex},
   * {@code realizationPredictor}.
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelStandard.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDriftWithProcessTimeIndexRealizationAtTimeIndexRealizationPredictor2() {
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
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable[] actualDrift =
        liborMarketModelStandard.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    assertNull(actualDrift[0]);
    assertEquals(1, actualDrift.length);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getFactorLoading(MonteCarloProcess, int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelStandard.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
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
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel4);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion2 =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion2));
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        liborMarketModelStandard.getFactorLoading(
            process,
            1,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getFactorLoading(MonteCarloProcess, int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelStandard.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenFirstElementAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        liborMarketModelStandard.getFactorLoading(
            process,
            1,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(1.0d)
            });

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
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
    assertEquals(1, actualFactorLoading.length);
    assertEquals(110.0d, randomVariable.getAverage(), 0.0);
    assertEquals(110.0d, randomVariable.getMax(), 0.0);
    assertEquals(110.0d, randomVariable.getMin(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, randomVariable.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {110.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getFactorLoading(MonteCarloProcess, int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelStandard.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenFirstElementReturnScalar() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualFactorLoading =
        liborMarketModelStandard.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertTrue(actualFactorLoading[1] instanceof Scalar);
    assertTrue(actualFactorLoading[2] instanceof Scalar);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then second element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getFactorLoading(MonteCarloProcess, int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORMarketModelStandard.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenSecondElementReturnRandomVariableFromDoubleArray() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualFactorLoading =
        liborMarketModelStandard.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromDoubleArray);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getDriftEuler(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getDriftEuler(MonteCarloProcess, int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.getDriftEuler(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetDriftEuler() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(
            liborPeriodDiscretization,
            forwardRateCurve,
            new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d));

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTime(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariable actualDriftEuler =
        liborMarketModelStandard.getDriftEuler(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(process).getTime(1);
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
    assertTrue(actualDriftEuler instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualDriftEuler.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getDriftEuler(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getDriftEuler(MonteCarloProcess, int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.getDriftEuler(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetDriftEuler2() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param covarianceModel =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(
            liborPeriodDiscretization,
            forwardRateCurve,
            new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d));

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTime(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariable actualDriftEuler =
        liborMarketModelStandard.getDriftEuler(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(process).getTime(1);
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
    assertTrue(actualDriftEuler instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDriftEuler.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualDriftEuler.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getDriftEuler(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return Average is {@code -1.5}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getDriftEuler(MonteCarloProcess, int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.getDriftEuler(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetDriftEuler_thenReturnAverageIs15() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);

    BlendedLocalVolatilityModel covarianceModel = mock(BlendedLocalVolatilityModel.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(1.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(1.0d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTime(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariable actualDriftEuler =
        liborMarketModelStandard.getDriftEuler(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel, atLeast(1)).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(process).getTime(1);
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
    assertTrue(actualDriftEuler instanceof RandomVariableFromDoubleArray);
    assertEquals(-1.5d, actualDriftEuler.getAverage(), 0.0);
    assertEquals(-1.5d, actualDriftEuler.getMax(), 0.0);
    assertEquals(-1.5d, actualDriftEuler.getMin(), 0.0);
    assertArrayEquals(new double[] {-1.5d}, actualDriftEuler.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getDriftEuler(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return Average is minus five.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getDriftEuler(MonteCarloProcess, int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.getDriftEuler(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetDriftEuler_thenReturnAverageIsMinusFive() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);

    BlendedLocalVolatilityModel covarianceModel = mock(BlendedLocalVolatilityModel.class);
    when(covarianceModel.getCovariance(
            anyInt(), anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel);

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTime(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariable actualDriftEuler =
        liborMarketModelStandard.getDriftEuler(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getCovariance(eq(1), eq(1), eq(1), (RandomVariable[]) isNull());
    verify(process).getTime(1);
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
    assertTrue(actualDriftEuler instanceof RandomVariableFromDoubleArray);
    assertEquals(-5.0d, actualDriftEuler.getAverage(), 0.0);
    assertEquals(-5.0d, actualDriftEuler.getMax(), 0.0);
    assertEquals(-5.0d, actualDriftEuler.getMin(), 0.0);
    assertArrayEquals(new double[] {-5.0d}, actualDriftEuler.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getIntegratedLIBORCovariance(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelStandard#getIntegratedLIBORCovariance(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][][] LIBORMarketModelStandard.getIntegratedLIBORCovariance(TimeDiscretization)"
  })
  public void testGetIntegratedLIBORCovariance() {
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
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act
    double[][][] actualIntegratedLIBORCovariance =
        liborMarketModelStandard.getIntegratedLIBORCovariance(new TenorFromArray(10.0d, 10, 0.5d));

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(0, actualIntegratedLIBORCovariance[0].length);
    assertEquals(0, actualIntegratedLIBORCovariance[1].length);
    assertEquals(0, actualIntegratedLIBORCovariance[2].length);
    assertEquals(0, actualIntegratedLIBORCovariance[3].length);
    assertEquals(0, actualIntegratedLIBORCovariance[4].length);
    assertEquals(0, actualIntegratedLIBORCovariance[5].length);
    assertEquals(0, actualIntegratedLIBORCovariance[6].length);
    assertEquals(0, actualIntegratedLIBORCovariance[7].length);
    assertEquals(0, actualIntegratedLIBORCovariance[8].length);
    assertEquals(0, actualIntegratedLIBORCovariance[9].length);
    assertEquals(10, actualIntegratedLIBORCovariance.length);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getIntegratedLIBORCovariance(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelStandard#getIntegratedLIBORCovariance(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][][] LIBORMarketModelStandard.getIntegratedLIBORCovariance(TimeDiscretization)"
  })
  public void testGetIntegratedLIBORCovariance2() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act
    double[][][] actualIntegratedLIBORCovariance =
        liborMarketModelStandard.getIntegratedLIBORCovariance(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(0, actualIntegratedLIBORCovariance.length);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getIntegratedLIBORCovariance(TimeDiscretization)}.
   *
   * <ul>
   *   <li>Then calls {@link AbstractLIBORCovarianceModelParametric#getFactorLoading(int, int,
   *       RandomVariable[])}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelStandard#getIntegratedLIBORCovariance(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][][] LIBORMarketModelStandard.getIntegratedLIBORCovariance(TimeDiscretization)"
  })
  public void testGetIntegratedLIBORCovariance_thenCallsGetFactorLoading() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(1);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act
    double[][][] actualIntegratedLIBORCovariance =
        liborMarketModelStandard.getIntegratedLIBORCovariance(new TenorFromArray(10.0d, 10, 0.5d));

    // Assert
    verify(covarianceModel, atLeast(1))
        .getFactorLoading(anyInt(), eq(0), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(10, actualIntegratedLIBORCovariance.length);
    assertEquals(10, actualIntegratedLIBORCovariance[0].length);
    assertEquals(10, actualIntegratedLIBORCovariance[1].length);
    assertEquals(10, actualIntegratedLIBORCovariance[2].length);
    assertEquals(10, actualIntegratedLIBORCovariance[3].length);
    assertEquals(10, actualIntegratedLIBORCovariance[4].length);
    assertEquals(10, actualIntegratedLIBORCovariance[5].length);
    assertEquals(10, actualIntegratedLIBORCovariance[6].length);
    assertEquals(10, actualIntegratedLIBORCovariance[7].length);
    assertEquals(10, actualIntegratedLIBORCovariance[8].length);
    assertEquals(10, actualIntegratedLIBORCovariance[9].length);
  }

  /**
   * Test {@link LIBORMarketModelStandard#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelStandard#applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    RandomVariableDifferentiableAADPathwise randomVariable =
        new RandomVariableDifferentiableAADPathwise(10.0d, new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        liborMarketModelStandard.applyStateSpaceTransform(process, 1, 1, randomVariable);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualApplyStateSpaceTransformResult instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualApplyStateSpaceTransformResult.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualApplyStateSpaceTransformResult.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualApplyStateSpaceTransformResult.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformResult)
            .getGradient()
            .size());
    assertEquals(1.2120993510627559E8d, actualApplyStateSpaceTransformResult.getVariance(), 0.0);
    assertEquals(
        1.6161324680836746E8d, actualApplyStateSpaceTransformResult.getSampleVariance(), 0.0);
    assertEquals(10.0d, actualApplyStateSpaceTransformResult.getFiltrationTime(), 0.0);
    assertEquals(
        11009.538369353893d, actualApplyStateSpaceTransformResult.getStandardDeviation(), 0.0);
    assertEquals(11016.927425452825d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(3, actualApplyStateSpaceTransformResult.getTypePriority());
    assertEquals(4, actualApplyStateSpaceTransformResult.size());
    assertEquals(5504.769184676947d, actualApplyStateSpaceTransformResult.getStandardError(), 0.0);
    assertEquals(7.38905609893065d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertFalse(actualApplyStateSpaceTransformResult.isDeterministic());
    assertSame(randomVariable2, actualApplyStateSpaceTransformResult.getValues());
    assertArrayEquals(
        new double[] {
          22026.465794806718d, 7.38905609893065d, 22026.465794806718d, 7.38905609893065d
        },
        actualApplyStateSpaceTransformResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelStandard#applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnRandomVariableFromDoubleArray() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        liborMarketModelStandard.applyStateSpaceTransform(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualApplyStateSpaceTransformResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyStateSpaceTransformResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyStateSpaceTransformResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyStateSpaceTransformResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getVariance(), 0.0);
    assertEquals(1, actualApplyStateSpaceTransformResult.getTypePriority());
    assertEquals(1, actualApplyStateSpaceTransformResult.size());
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertTrue(actualApplyStateSpaceTransformResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {22026.465794806718d},
        actualApplyStateSpaceTransformResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelStandard#applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_whenScalarWithValueIsTen_thenReturnScalar() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        liborMarketModelStandard.applyStateSpaceTransform(process, 1, 1, Scalar.of(10.0d));

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualApplyStateSpaceTransformResult instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.abs() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.cos() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.exp() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.expm1() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.invert() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.isNaN() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.sin() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.sqrt() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.squared() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.variance() instanceof Scalar);
    assertNull(actualApplyStateSpaceTransformResult.getRealizations());
    assertNull(actualApplyStateSpaceTransformResult.getOperator());
    assertNull(actualApplyStateSpaceTransformResult.getRealizationsStream());
    assertEquals(0, actualApplyStateSpaceTransformResult.getTypePriority());
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getVariance(), 0.0);
    assertEquals(1, actualApplyStateSpaceTransformResult.size());
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertTrue(actualApplyStateSpaceTransformResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualApplyStateSpaceTransformResult.expectation();
    assertSame(actualApplyStateSpaceTransformResult, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelStandard#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelStandard#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    RandomVariableDifferentiableAADPathwise randomVariable =
        new RandomVariableDifferentiableAADPathwise(10.0d, new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        liborMarketModelStandard.applyStateSpaceTransformInverse(process, 1, 1, randomVariable);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformInverseResult)
            .getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformInverseResult)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformInverseResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformInverseResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformInverseResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformInverseResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformInverseResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformInverseResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.expm1()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(
        0.4023594781085251d, actualApplyStateSpaceTransformInverseResult.getStandardError(), 0.0);
    assertEquals(
        0.6475725984950589d, actualApplyStateSpaceTransformInverseResult.getVariance(), 0.0);
    assertEquals(0.6931471805599453d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertEquals(
        0.8047189562170503d,
        actualApplyStateSpaceTransformInverseResult.getStandardDeviation(),
        0.0);
    assertEquals(
        0.8634301313267452d, actualApplyStateSpaceTransformInverseResult.getSampleVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualApplyStateSpaceTransformInverseResult)
            .getGradient()
            .size());
    assertEquals(
        1.4978661367769956d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(10.0d, actualApplyStateSpaceTransformInverseResult.getFiltrationTime(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(3, actualApplyStateSpaceTransformInverseResult.getTypePriority());
    assertEquals(4, actualApplyStateSpaceTransformInverseResult.size());
    assertFalse(actualApplyStateSpaceTransformInverseResult.isDeterministic());
    assertSame(randomVariable2, actualApplyStateSpaceTransformInverseResult.getValues());
    assertArrayEquals(
        new double[] {
          2.302585092994046d, 0.6931471805599453d, 2.302585092994046d, 0.6931471805599453d
        },
        actualApplyStateSpaceTransformInverseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelStandard#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnRandomVariableFromDoubleArray() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        liborMarketModelStandard.applyStateSpaceTransformInverse(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        actualApplyStateSpaceTransformInverseResult instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getVariance(), 0.0);
    assertEquals(1, actualApplyStateSpaceTransformInverseResult.getTypePriority());
    assertEquals(1, actualApplyStateSpaceTransformInverseResult.size());
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertTrue(actualApplyStateSpaceTransformInverseResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualApplyStateSpaceTransformInverseResult.getFiltrationTime(),
        0.0);
    assertArrayEquals(
        new double[] {2.302585092994046d},
        actualApplyStateSpaceTransformInverseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORMarketModelStandard#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_whenScalarWithValueIsTen_thenReturnScalar() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        liborMarketModelStandard.applyStateSpaceTransformInverse(process, 1, 1, Scalar.of(10.0d));

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualApplyStateSpaceTransformInverseResult instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.abs() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.cos() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.exp() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.expm1() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.invert() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.isNaN() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.sin() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.sqrt() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.squared() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.variance() instanceof Scalar);
    assertNull(actualApplyStateSpaceTransformInverseResult.getRealizations());
    assertNull(actualApplyStateSpaceTransformInverseResult.getOperator());
    assertNull(actualApplyStateSpaceTransformInverseResult.getRealizationsStream());
    assertEquals(0, actualApplyStateSpaceTransformInverseResult.getTypePriority());
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getVariance(), 0.0);
    assertEquals(1, actualApplyStateSpaceTransformInverseResult.size());
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertTrue(actualApplyStateSpaceTransformInverseResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualApplyStateSpaceTransformInverseResult.getFiltrationTime(),
        0.0);
    RandomVariable actualExpectationResult =
        actualApplyStateSpaceTransformInverseResult.expectation();
    assertSame(actualApplyStateSpaceTransformInverseResult, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act
    RandomVariable actualRandomVariableForConstant =
        liborMarketModelStandard.getRandomVariableForConstant(10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualRandomVariableForConstant instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.abs() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.cos() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.exp() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.expm1() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.invert() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.isNaN() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.sin() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.sqrt() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.squared() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.variance() instanceof Scalar);
    assertNull(actualRandomVariableForConstant.getRealizations());
    assertNull(actualRandomVariableForConstant.getOperator());
    assertNull(actualRandomVariableForConstant.getRealizationsStream());
    assertEquals(0, actualRandomVariableForConstant.getTypePriority());
    assertEquals(0.0d, actualRandomVariableForConstant.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableForConstant.size());
    assertEquals(10.0d, actualRandomVariableForConstant.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMin(), 0.0);
    assertTrue(actualRandomVariableForConstant.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualRandomVariableForConstant.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualRandomVariableForConstant.expectation();
    assertSame(actualRandomVariableForConstant, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getForwardRate(MonteCarloProcess, double, double,
   * double)}.
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getForwardRate(MonteCarloProcess, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate() throws CalculationException {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualForwardRate =
        liborMarketModelStandard.getForwardRate(process, 10.0d, 10.0d, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualForwardRate instanceof Scalar);
    assertTrue(actualForwardRate.abs() instanceof Scalar);
    assertTrue(actualForwardRate.cos() instanceof Scalar);
    assertTrue(actualForwardRate.exp() instanceof Scalar);
    assertTrue(actualForwardRate.expm1() instanceof Scalar);
    assertTrue(actualForwardRate.invert() instanceof Scalar);
    assertTrue(actualForwardRate.isNaN() instanceof Scalar);
    assertTrue(actualForwardRate.sin() instanceof Scalar);
    assertTrue(actualForwardRate.sqrt() instanceof Scalar);
    assertTrue(actualForwardRate.squared() instanceof Scalar);
    assertTrue(actualForwardRate.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualForwardRate.expectation();
    assertSame(actualForwardRate, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getForwardRate(MonteCarloProcess, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getTimeIndex(double)}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getForwardRate(MonteCarloProcess, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate_thenCallsGetTimeIndex() throws CalculationException {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualForwardRate =
        liborMarketModelStandard.getForwardRate(process, 10.0d, 10.0d, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization).getTimeIndex(10.0d);
    assertTrue(actualForwardRate instanceof Scalar);
    assertTrue(actualForwardRate.abs() instanceof Scalar);
    assertTrue(actualForwardRate.cos() instanceof Scalar);
    assertTrue(actualForwardRate.exp() instanceof Scalar);
    assertTrue(actualForwardRate.expm1() instanceof Scalar);
    assertTrue(actualForwardRate.invert() instanceof Scalar);
    assertTrue(actualForwardRate.isNaN() instanceof Scalar);
    assertTrue(actualForwardRate.sin() instanceof Scalar);
    assertTrue(actualForwardRate.sqrt() instanceof Scalar);
    assertTrue(actualForwardRate.squared() instanceof Scalar);
    assertTrue(actualForwardRate.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualForwardRate.expectation();
    assertSame(actualForwardRate, actualExpectationResult);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getLIBOR(MonteCarloProcess, int, int)} with {@code
   * process}, {@code timeIndex}, {@code liborIndex}.
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getLIBOR(MonteCarloProcess, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORMarketModelStandard.getLIBOR(MonteCarloProcess, int, int)"
  })
  public void testGetLIBORWithProcessTimeIndexLiborIndex() throws CalculationException {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel model =
        new HullWhiteModel(
            liborPeriodDiscretization2,
            analyticModel,
            forwardRateCurve2,
            discountCurve,
            volatilityModel,
            new HashMap<>());
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualLIBOR = liborMarketModelStandard.getLIBOR(process, 1, 1);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualLIBOR instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualLIBOR.getTypePriority());
    assertEquals(10, actualLIBOR.size());
    assertEquals(10.5d, actualLIBOR.getFiltrationTime(), 0.0);
    assertFalse(actualLIBOR.isDeterministic());
    assertEquals(Double.NaN, actualLIBOR.getAverage(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getMax(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getMin(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getVariance(), 0.0);
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
        actualLIBOR.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getNumberOfComponents()}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getNumberOfComponents()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMarketModelStandard.getNumberOfComponents()"})
  public void testGetNumberOfComponents_thenReturnTen() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act
    int actualNumberOfComponents = liborMarketModelStandard.getNumberOfComponents();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(10, actualNumberOfComponents);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getNumberOfFactors()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMarketModelStandard.getNumberOfFactors()"})
  public void testGetNumberOfFactors_thenReturnThree() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act
    int actualNumberOfFactors = liborMarketModelStandard.getNumberOfFactors();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(3, actualNumberOfFactors);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getNumberOfLibors()}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getNumberOfLibors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMarketModelStandard.getNumberOfLibors()"})
  public void testGetNumberOfLibors_thenReturnTen() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act
    int actualNumberOfLibors = liborMarketModelStandard.getNumberOfLibors();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(10, actualNumberOfLibors);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getLiborPeriod(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getLiborPeriod(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LIBORMarketModelStandard.getLiborPeriod(int)"})
  public void testGetLiborPeriod_thenReturn105() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act
    double actualLiborPeriod = liborMarketModelStandard.getLiborPeriod(1);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(10.5d, actualLiborPeriod, 0.0);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getLiborPeriodIndex(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getLiborPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORMarketModelStandard.getLiborPeriodIndex(double)"})
  public void testGetLiborPeriodIndex_thenReturnZero() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act
    int actualLiborPeriodIndex = liborMarketModelStandard.getLiborPeriodIndex(10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(0, actualLiborPeriodIndex);
  }

  /**
   * Test {@link LIBORMarketModelStandard#clone()}.
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORMarketModelStandard.clone()"})
  public void testClone() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act
    Object actualCloneResult = liborMarketModelStandard.clone();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((LIBORMarketModelStandard) actualCloneResult).getDiscountCurve()
            instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 =
        ((LIBORMarketModelStandard) actualCloneResult).getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(actualCloneResult instanceof LIBORMarketModelStandard);
    LIBORCovarianceModel covarianceModel3 =
        ((LIBORMarketModelStandard) actualCloneResult).getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        ((LIBORMarketModelStandard) actualCloneResult).getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertNull(((LIBORMarketModelStandard) actualCloneResult).getAnalyticModel());
    assertNull(((LIBORMarketModelStandard) actualCloneResult).getSwaptionMarketData());
    assertEquals(10, ((LIBORMarketModelStandard) actualCloneResult).getNumberOfComponents());
    assertEquals(10, ((LIBORMarketModelStandard) actualCloneResult).getNumberOfLibors());
    assertEquals(3, ((LIBORMarketModelStandard) actualCloneResult).getNumberOfFactors());
    assertEquals(
        Driftapproximation.EULER,
        ((LIBORMarketModelStandard) actualCloneResult).getDriftApproximationMethod());
    assertEquals(Measure.SPOT, ((LIBORMarketModelStandard) actualCloneResult).getMeasure());
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(covarianceModel2, covarianceModel3);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getDiscountCurve()}.
   *
   * <ul>
   *   <li>Then return {@link DiscountCurveFromForwardCurve}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getDiscountCurve()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DiscountCurve LIBORMarketModelStandard.getDiscountCurve()"})
  public void testGetDiscountCurve_thenReturnDiscountCurveFromForwardCurve() {
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
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", referenceDate, "Payment Offset Code");

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act
    DiscountCurve actualDiscountCurve = liborMarketModelStandard.getDiscountCurve();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualDiscountCurve instanceof DiscountCurveFromForwardCurve);
    LocalDate referenceDate2 = actualDiscountCurve.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals(
        "DiscountCurveFromForwardCurveForwardCurveFromDiscountCurve(3,Payment Offset Code))",
        actualDiscountCurve.getName());
    assertNull(actualDiscountCurve.getParameter());
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getDiscountCurve()}.
   *
   * <ul>
   *   <li>Then return {@link DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(String)}
   *       with {@code Forward Curve Name}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getDiscountCurve()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DiscountCurve LIBORMarketModelStandard.getDiscountCurve()"})
  public void testGetDiscountCurve_thenReturnDiscountCurveFromForwardCurveWithForwardCurveName() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(
            liborPeriodDiscretization, forwardRateCurve, discountCurve, covarianceModel2);

    // Act
    DiscountCurve actualDiscountCurve = liborMarketModelStandard.getDiscountCurve();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertSame(discountCurve, actualDiscountCurve);
  }

  /**
   * Test {@link
   * LIBORMarketModelStandard#getCloneWithModifiedCovarianceModel(LIBORCovarianceModel)}.
   *
   * <p>Method under test: {@link
   * LIBORMarketModelStandard#getCloneWithModifiedCovarianceModel(LIBORCovarianceModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORMarketModelStandard LIBORMarketModelStandard.getCloneWithModifiedCovarianceModel(LIBORCovarianceModel)"
  })
  public void testGetCloneWithModifiedCovarianceModel() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    AbstractLIBORCovarianceModelParametric covarianceModel3 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel3.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel3.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel3.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true), 10.0d);

    // Act
    LIBORMarketModelStandard actualCloneWithModifiedCovarianceModel =
        liborMarketModelStandard.getCloneWithModifiedCovarianceModel(covarianceModel4);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel3).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel3).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel3).getTimeDiscretization();
    assertTrue(
        actualCloneWithModifiedCovarianceModel.getDiscountCurve()
            instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualCloneWithModifiedCovarianceModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel5 =
        actualCloneWithModifiedCovarianceModel.getCovarianceModel();
    assertTrue(covarianceModel5 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedCovarianceModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertNull(actualCloneWithModifiedCovarianceModel.getAnalyticModel());
    assertNull(actualCloneWithModifiedCovarianceModel.getSwaptionMarketData());
    assertEquals(10, actualCloneWithModifiedCovarianceModel.getNumberOfComponents());
    assertEquals(10, actualCloneWithModifiedCovarianceModel.getNumberOfLibors());
    assertEquals(3, actualCloneWithModifiedCovarianceModel.getNumberOfFactors());
    assertEquals(
        Driftapproximation.EULER,
        actualCloneWithModifiedCovarianceModel.getDriftApproximationMethod());
    assertEquals(Measure.SPOT, actualCloneWithModifiedCovarianceModel.getMeasure());
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(covarianceModel4, covarianceModel5);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORMarketModelStandard LIBORMarketModelStandard.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData() throws CalculationException {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act
    LIBORMarketModelStandard actualCloneWithModifiedData =
        liborMarketModelStandard.getCloneWithModifiedData(new HashMap<>());

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        actualCloneWithModifiedData.getDiscountCurve() instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualCloneWithModifiedData.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    LIBORCovarianceModel covarianceModel3 = actualCloneWithModifiedData.getCovarianceModel();
    assertTrue(covarianceModel3 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertNull(actualCloneWithModifiedData.getAnalyticModel());
    assertNull(actualCloneWithModifiedData.getSwaptionMarketData());
    assertEquals(10, actualCloneWithModifiedData.getNumberOfComponents());
    assertEquals(10, actualCloneWithModifiedData.getNumberOfLibors());
    assertEquals(3, actualCloneWithModifiedData.getNumberOfFactors());
    assertEquals(
        Driftapproximation.EULER, actualCloneWithModifiedData.getDriftApproximationMethod());
    assertEquals(Measure.SPOT, actualCloneWithModifiedData.getMeasure());
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(covarianceModel2, covarianceModel3);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link LIBORMarketModelStandard#getModelParameters()}.
   *
   * <p>Method under test: {@link LIBORMarketModelStandard#getModelParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map LIBORMarketModelStandard.getModelParameters()"})
  public void testGetModelParameters() {
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

    LIBORMarketModelStandard liborMarketModelStandard =
        new LIBORMarketModelStandard(liborPeriodDiscretization, forwardRateCurve, covarianceModel2);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> liborMarketModelStandard.getModelParameters());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
  }
}
