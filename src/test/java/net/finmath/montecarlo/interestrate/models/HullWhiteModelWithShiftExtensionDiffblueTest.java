package net.finmath.montecarlo.interestrate.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.ShortRateVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.ShortRateVolatilityModelAsGiven;
import net.finmath.montecarlo.interestrate.models.covariance.ShortRateVolatilityModelHoLee;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel.Scheme;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HullWhiteModelWithShiftExtensionDiffblueTest {
  @Mock private ForwardCurve forwardCurve;

  @InjectMocks private HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension;

  @Mock private MonteCarloProcess monteCarloProcess;

  @Mock private ShortRateVolatilityModel shortRateVolatilityModel;

  @Mock private TimeDiscretization timeDiscretization;

  /**
   * Test {@link
   * HullWhiteModelWithShiftExtension#HullWhiteModelWithShiftExtension(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#HullWhiteModelWithShiftExtension(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HullWhiteModelWithShiftExtension.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)"
  })
  public void testNewHullWhiteModelWithShiftExtension_whenHashMap() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    // Act
    HullWhiteModelWithShiftExtension actualHullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Assert
    AnalyticModel analyticModel2 = actualHullWhiteModelWithShiftExtension.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualHullWhiteModelWithShiftExtension.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualHullWhiteModelWithShiftExtension.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    TimeDiscretization liborPeriodDiscretization2 =
        actualHullWhiteModelWithShiftExtension.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualHullWhiteModelWithShiftExtension.getNumberOfComponents());
    assertEquals(1, actualHullWhiteModelWithShiftExtension.getNumberOfFactors());
    assertEquals(10, actualHullWhiteModelWithShiftExtension.getNumberOfLibors());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link
   * HullWhiteModelWithShiftExtension#HullWhiteModelWithShiftExtension(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#HullWhiteModelWithShiftExtension(TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HullWhiteModelWithShiftExtension.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)"
  })
  public void testNewHullWhiteModelWithShiftExtension_whenNull() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    HullWhiteModelWithShiftExtension actualHullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            new ShortRateVolatilityModelHoLee(10.0d),
            null);

    // Assert
    AnalyticModel analyticModel2 = actualHullWhiteModelWithShiftExtension.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualHullWhiteModelWithShiftExtension.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualHullWhiteModelWithShiftExtension.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    TimeDiscretization liborPeriodDiscretization2 =
        actualHullWhiteModelWithShiftExtension.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualHullWhiteModelWithShiftExtension.getNumberOfComponents());
    assertEquals(1, actualHullWhiteModelWithShiftExtension.getNumberOfFactors());
    assertEquals(10, actualHullWhiteModelWithShiftExtension.getNumberOfLibors());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getReferenceDate()}.
   *
   * <ul>
   *   <li>Then return toLocalTime toString is {@code 00:00}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDateTime HullWhiteModelWithShiftExtension.getReferenceDate()"})
  public void testGetReferenceDate_thenReturnToLocalTimeToStringIs0000() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    LocalDate ofResult = LocalDate.of(1970, 1, 1);
    when(discountCurve.getReferenceDate()).thenReturn(ofResult);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act
    LocalDateTime actualReferenceDate = hullWhiteModelWithShiftExtension.getReferenceDate();

    // Assert
    verify(discountCurve).getReferenceDate();
    assertEquals("00:00", actualReferenceDate.toLocalTime().toString());
    LocalDate toLocalDateResult = actualReferenceDate.toLocalDate();
    assertEquals("1970-01-01", toLocalDateResult.toString());
    assertSame(ofResult, toLocalDateResult);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getReferenceDate()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDateTime HullWhiteModelWithShiftExtension.getReferenceDate()"})
  public void testGetReferenceDate_thenThrowUnsupportedOperationException() {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getReferenceDate()).thenThrow(new UnsupportedOperationException());
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getReferenceDate());
    verify(discountCurve).getReferenceDate();
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#applyStateSpaceTransform(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithShiftExtension.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        hullWhiteModelWithShiftExtension.applyStateSpaceTransform(process, 1, 1, randomVariable);

    // Assert
    assertSame(randomVariable, actualApplyStateSpaceTransformResult);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#applyStateSpaceTransformInverse(MonteCarloProcess,
   * int, int, RandomVariable)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithShiftExtension.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        hullWhiteModelWithShiftExtension.applyStateSpaceTransformInverse(
            process, 1, 1, randomVariable);

    // Assert
    assertSame(randomVariable, actualApplyStateSpaceTransformInverseResult);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getInitialState(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithShiftExtension.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = hullWhiteModelWithShiftExtension.getInitialState(process);

    // Assert
    assertTrue(actualInitialState[0] instanceof Scalar);
    assertEquals(1, actualInitialState.length);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getNumeraire(MonteCarloProcess, double)}.
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getNumeraire(MonteCarloProcess,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithShiftExtension.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = hullWhiteModelWithShiftExtension.getNumeraire(process, 10.0d);

    // Assert
    assertTrue(actualNumeraire instanceof Scalar);
    assertTrue(actualNumeraire.abs() instanceof Scalar);
    assertTrue(actualNumeraire.cos() instanceof Scalar);
    assertTrue(actualNumeraire.exp() instanceof Scalar);
    assertTrue(actualNumeraire.expm1() instanceof Scalar);
    assertTrue(actualNumeraire.invert() instanceof Scalar);
    assertTrue(actualNumeraire.isNaN() instanceof Scalar);
    assertTrue(actualNumeraire.sin() instanceof Scalar);
    assertTrue(actualNumeraire.sqrt() instanceof Scalar);
    assertTrue(actualNumeraire.squared() instanceof Scalar);
    assertTrue(actualNumeraire.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return Average is ten.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getNumeraire(MonteCarloProcess,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithShiftExtension.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_thenReturnAverageIsTen() throws CalculationException {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel model =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization2, forwardRateCurve2, covarianceModel2);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion), Scheme.EULER);

    // Act
    RandomVariable actualNumeraire = hullWhiteModelWithShiftExtension.getNumeraire(process, -0.5d);

    // Assert
    verify(discountCurve).getDiscountFactor(isA(AnalyticModel.class), eq(-0.5d));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof Scalar);
    assertEquals(10.0d, actualNumeraire.getAverage(), 0.0);
    assertEquals(10.0d, actualNumeraire.getMax(), 0.0);
    assertEquals(10.0d, actualNumeraire.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getNumeraire(MonteCarloProcess,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithShiftExtension.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_thenReturnNull() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            null,
            volatilityModel,
            new HashMap<>());

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    ForwardCurveFromDiscountCurve forwardRateCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    LIBORMarketModelFromCovarianceModel model =
        new LIBORMarketModelFromCovarianceModel(
            liborPeriodDiscretization2, forwardRateCurve2, covarianceModel2);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(0.5d);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(0);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion), Scheme.EULER);

    // Act
    RandomVariable actualNumeraire = hullWhiteModelWithShiftExtension.getNumeraire(process, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(0);
    verify(timeDiscretization).getTimeIndex(10.0d);
    assertNull(actualNumeraire);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>When {@link TimeDiscretization} {@link TimeDiscretization#getTime(int)} return ten.
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getNumeraire(MonteCarloProcess,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithShiftExtension.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_whenTimeDiscretizationGetTimeReturnTen_thenAbsReturnScalar()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

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
    RandomVariable actualNumeraire = hullWhiteModelWithShiftExtension.getNumeraire(process, 10.0d);

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(0);
    assertTrue(actualNumeraire instanceof Scalar);
    assertTrue(actualNumeraire.abs() instanceof Scalar);
    assertTrue(actualNumeraire.cos() instanceof Scalar);
    assertTrue(actualNumeraire.exp() instanceof Scalar);
    assertTrue(actualNumeraire.expm1() instanceof Scalar);
    assertTrue(actualNumeraire.invert() instanceof Scalar);
    assertTrue(actualNumeraire.isNaN() instanceof Scalar);
    assertTrue(actualNumeraire.sin() instanceof Scalar);
    assertTrue(actualNumeraire.sqrt() instanceof Scalar);
    assertTrue(actualNumeraire.squared() instanceof Scalar);
    assertTrue(actualNumeraire.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithShiftExtension.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelAsGiven volatilityModel =
        new ShortRateVolatilityModelAsGiven(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualDrift =
        hullWhiteModelWithShiftExtension.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualDrift[0];
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
    assertEquals(1, actualDrift.length);
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithShiftExtension.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift2() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTimeIndex(anyDouble()))
        .thenThrow(new UnsupportedOperationException());
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hullWhiteModelWithShiftExtension.getDrift(
                monteCarloProcess,
                1,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(shortRateVolatilityModel).getTimeDiscretization();
    verify(monteCarloProcess, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeIndex(10.0d);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Given {@link ShortRateVolatilityModelHoLee#ShortRateVolatilityModelHoLee(double)} with
   *       volatility is ten.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithShiftExtension.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_givenShortRateVolatilityModelHoLeeWithVolatilityIsTen() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualDrift =
        hullWhiteModelWithShiftExtension.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualDrift[0];
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
    assertEquals(1, actualDrift.length);
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Given {@link TimeDiscretization} {@link TimeDiscretization#getTime(int)} throw {@link
   *       UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithShiftExtension.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_givenTimeDiscretizationGetTimeThrowUnsupportedOperationException() {
    // Arrange
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new UnsupportedOperationException());
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(Integer.MIN_VALUE);
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hullWhiteModelWithShiftExtension.getDrift(
                monteCarloProcess,
                1,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(shortRateVolatilityModel).getMeanReversion(2147483646);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(monteCarloProcess, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTime(-2147483648);
    verify(timeDiscretization, atLeast(1)).getTimeIndex(10.0d);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Given {@link UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithShiftExtension.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_givenUnsupportedOperationException() {
    // Arrange
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTime(anyInt())).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hullWhiteModelWithShiftExtension.getDrift(
                monteCarloProcess,
                1,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(monteCarloProcess, atLeast(1)).getTime(1);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then calls {@link ShortRateVolatilityModel#getMeanReversion(int)}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithShiftExtension.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenCallsGetMeanReversion() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);

    // Act
    RandomVariable[] actualDrift =
        hullWhiteModelWithShiftExtension.getDrift(
            monteCarloProcess,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(shortRateVolatilityModel, atLeast(1)).getMeanReversion(1);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(monteCarloProcess, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeIndex(10.0d);
    RandomVariable randomVariable = actualDrift[0];
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
    assertEquals(1, actualDrift.length);
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getNumberOfTimes()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithShiftExtension.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenCallsGetNumberOfTimes() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

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
        hullWhiteModelWithShiftExtension.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    RandomVariable randomVariable = actualDrift[0];
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
    assertEquals(1, actualDrift.length);
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getNumberOfTimes()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithShiftExtension.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenCallsGetNumberOfTimes2() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelAsGiven volatilityModel =
        new ShortRateVolatilityModelAsGiven(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

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
        hullWhiteModelWithShiftExtension.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    RandomVariable randomVariable = actualDrift[0];
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
    assertEquals(1, actualDrift.length);
    assertArrayEquals(new double[] {Double.NaN}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithShiftExtension.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteModelWithShiftExtension.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof Scalar);
    assertTrue(randomVariable.cos() instanceof Scalar);
    assertTrue(randomVariable.exp() instanceof Scalar);
    assertTrue(randomVariable.expm1() instanceof Scalar);
    assertTrue(randomVariable.invert() instanceof Scalar);
    assertTrue(randomVariable.isNaN() instanceof Scalar);
    assertTrue(randomVariable.sin() instanceof Scalar);
    assertTrue(randomVariable.sqrt() instanceof Scalar);
    assertTrue(randomVariable.squared() instanceof Scalar);
    assertTrue(randomVariable.variance() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(1, actualFactorLoading.length);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithShiftExtension.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading2() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelAsGiven volatilityModel =
        new ShortRateVolatilityModelAsGiven(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            new BachelierModel(1.0d, 1.0d, 1.0d), stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteModelWithShiftExtension.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof Scalar);
    assertTrue(randomVariable.cos() instanceof Scalar);
    assertTrue(randomVariable.exp() instanceof Scalar);
    assertTrue(randomVariable.expm1() instanceof Scalar);
    assertTrue(randomVariable.invert() instanceof Scalar);
    assertTrue(randomVariable.isNaN() instanceof Scalar);
    assertTrue(randomVariable.sin() instanceof Scalar);
    assertTrue(randomVariable.sqrt() instanceof Scalar);
    assertTrue(randomVariable.squared() instanceof Scalar);
    assertTrue(randomVariable.variance() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(1, actualFactorLoading.length);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getNumberOfTimes()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModelWithShiftExtension.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenCallsGetNumberOfTimes() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

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
    RandomVariable[] actualFactorLoading =
        hullWhiteModelWithShiftExtension.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof Scalar);
    assertTrue(randomVariable.cos() instanceof Scalar);
    assertTrue(randomVariable.exp() instanceof Scalar);
    assertTrue(randomVariable.expm1() instanceof Scalar);
    assertTrue(randomVariable.invert() instanceof Scalar);
    assertTrue(randomVariable.isNaN() instanceof Scalar);
    assertTrue(randomVariable.sin() instanceof Scalar);
    assertTrue(randomVariable.sqrt() instanceof Scalar);
    assertTrue(randomVariable.squared() instanceof Scalar);
    assertTrue(randomVariable.variance() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(1, actualFactorLoading.length);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithShiftExtension.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act
    RandomVariable actualRandomVariableForConstant =
        hullWhiteModelWithShiftExtension.getRandomVariableForConstant(10.0d);

    // Assert
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
   * Test {@link HullWhiteModelWithShiftExtension#getForwardRate(MonteCarloProcess, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getForwardRate(MonteCarloProcess,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithShiftExtension.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate_thenReturnRandomVariableFromDoubleArray()
      throws CalculationException {
    // Arrange
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTimeIndex(anyDouble())).thenReturn(1);
    when(monteCarloProcess.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(monteCarloProcess.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTimeIndex(anyDouble())).thenReturn(1);
    when(monteCarloProcess.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualForwardRate =
        hullWhiteModelWithShiftExtension.getForwardRate(monteCarloProcess, 10.0d, 10.0d, 10.0d);

    // Assert
    verify(forwardCurve, atLeast(1)).getForward(isNull(), anyDouble());
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(shortRateVolatilityModel, atLeast(1)).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel, atLeast(1)).getVolatility(0);
    verify(monteCarloProcess, atLeast(1)).getProcessValue(1, 0);
    verify(monteCarloProcess, atLeast(1)).getTime(anyInt());
    verify(monteCarloProcess, atLeast(1)).getTimeDiscretization();
    verify(monteCarloProcess, atLeast(1)).getTimeIndex(10.0d);
    assertTrue(actualForwardRate instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualForwardRate.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualForwardRate.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualForwardRate.getStandardError(), 0.0);
    assertEquals(0.0d, actualForwardRate.getVariance(), 0.0);
    assertEquals(1, actualForwardRate.getTypePriority());
    assertEquals(1, actualForwardRate.size());
    assertTrue(actualForwardRate.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualForwardRate.getFiltrationTime(), 0.0);
    assertEquals(Double.NaN, actualForwardRate.getAverage(), 0.0);
    assertEquals(Double.NaN, actualForwardRate.getMax(), 0.0);
    assertEquals(Double.NaN, actualForwardRate.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, actualForwardRate.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getLIBOR(MonteCarloProcess, int, int)} with {@code
   * process}, {@code timeIndex}, {@code liborIndex}.
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getLIBOR(MonteCarloProcess, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModelWithShiftExtension.getLIBOR(MonteCarloProcess, int, int)"
  })
  public void testGetLIBORWithProcessTimeIndexLiborIndex() throws CalculationException {
    // Arrange
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(forwardCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble())).thenReturn(10.0d);
    when(forwardCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTimeIndex(anyDouble())).thenReturn(1);
    when(monteCarloProcess.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(monteCarloProcess.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(monteCarloProcess.getTime(anyInt())).thenReturn(10.0d);
    when(monteCarloProcess.getTimeIndex(anyDouble())).thenReturn(1);
    when(monteCarloProcess.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualLIBOR = hullWhiteModelWithShiftExtension.getLIBOR(monteCarloProcess, 1, 1);

    // Assert
    verify(forwardCurve, atLeast(1)).getForward(isNull(), anyDouble());
    verify(forwardCurve, atLeast(1)).getPaymentOffset(anyDouble());
    verify(shortRateVolatilityModel, atLeast(1)).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel, atLeast(1)).getVolatility(0);
    verify(monteCarloProcess, atLeast(1)).getProcessValue(1, 0);
    verify(monteCarloProcess, atLeast(1)).getTime(anyInt());
    verify(monteCarloProcess, atLeast(1)).getTimeDiscretization();
    verify(monteCarloProcess, atLeast(1)).getTimeIndex(10.0d);
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization).getTimeStep(1);
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
    assertEquals(0.0d, actualLIBOR.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualLIBOR.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualLIBOR.getStandardError(), 0.0);
    assertEquals(0.0d, actualLIBOR.getVariance(), 0.0);
    assertEquals(1, actualLIBOR.getTypePriority());
    assertEquals(1, actualLIBOR.size());
    assertTrue(actualLIBOR.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualLIBOR.getFiltrationTime(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getAverage(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getMax(), 0.0);
    assertEquals(Double.NaN, actualLIBOR.getMin(), 0.0);
    assertArrayEquals(new double[] {Double.NaN}, actualLIBOR.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getNumberOfLibors()}.
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getNumberOfLibors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModelWithShiftExtension.getNumberOfLibors()"})
  public void testGetNumberOfLibors() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(10, hullWhiteModelWithShiftExtension.getNumberOfLibors());
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getNumberOfLibors()}.
   *
   * <ul>
   *   <li>Given {@link TimeDiscretization} {@link TimeDiscretization#getNumberOfTimeSteps()} return
   *       ten.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getNumberOfLibors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModelWithShiftExtension.getNumberOfLibors()"})
  public void testGetNumberOfLibors_givenTimeDiscretizationGetNumberOfTimeStepsReturnTen() {
    // Arrange
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);

    // Act
    int actualNumberOfLibors = hullWhiteModelWithShiftExtension.getNumberOfLibors();

    // Assert
    verify(timeDiscretization).getNumberOfTimeSteps();
    assertEquals(10, actualNumberOfLibors);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getNumberOfLibors()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getNumberOfLibors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModelWithShiftExtension.getNumberOfLibors()"})
  public void testGetNumberOfLibors_thenThrowUnsupportedOperationException() {
    // Arrange
    when(timeDiscretization.getNumberOfTimeSteps()).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getNumberOfLibors());
    verify(timeDiscretization).getNumberOfTimeSteps();
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getLiborPeriod(int)}.
   *
   * <ul>
   *   <li>Given {@link TimeDiscretization} {@link TimeDiscretization#getTime(int)} return ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getLiborPeriod(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HullWhiteModelWithShiftExtension.getLiborPeriod(int)"})
  public void testGetLiborPeriod_givenTimeDiscretizationGetTimeReturnTen_thenReturnTen() {
    // Arrange
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);

    // Act
    double actualLiborPeriod = hullWhiteModelWithShiftExtension.getLiborPeriod(1);

    // Assert
    verify(timeDiscretization).getTime(1);
    assertEquals(10.0d, actualLiborPeriod, 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getLiborPeriod(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getLiborPeriod(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HullWhiteModelWithShiftExtension.getLiborPeriod(int)"})
  public void testGetLiborPeriod_thenReturn105() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(10.5d, hullWhiteModelWithShiftExtension.getLiborPeriod(1), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getLiborPeriod(int)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getLiborPeriod(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HullWhiteModelWithShiftExtension.getLiborPeriod(int)"})
  public void testGetLiborPeriod_thenThrowUnsupportedOperationException() {
    // Arrange
    when(timeDiscretization.getTime(anyInt())).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getLiborPeriod(1));
    verify(timeDiscretization).getTime(1);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getLiborPeriodIndex(double)}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getLiborPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModelWithShiftExtension.getLiborPeriodIndex(double)"})
  public void testGetLiborPeriodIndex_thenReturnOne() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);

    // Act
    int actualLiborPeriodIndex = hullWhiteModelWithShiftExtension.getLiborPeriodIndex(10.0d);

    // Assert
    verify(timeDiscretization).getTimeIndex(10.0d);
    assertEquals(1, actualLiborPeriodIndex);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getLiborPeriodIndex(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getLiborPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModelWithShiftExtension.getLiborPeriodIndex(double)"})
  public void testGetLiborPeriodIndex_thenReturnZero() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(0, hullWhiteModelWithShiftExtension.getLiborPeriodIndex(10.0d));
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getLiborPeriodIndex(double)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getLiborPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModelWithShiftExtension.getLiborPeriodIndex(double)"})
  public void testGetLiborPeriodIndex_thenThrowUnsupportedOperationException() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble()))
        .thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getLiborPeriodIndex(10.0d));
    verify(timeDiscretization).getTimeIndex(10.0d);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link HullWhiteModelWithShiftExtension#getAnalyticModel()}
   *   <li>{@link HullWhiteModelWithShiftExtension#getDiscountCurve()}
   *   <li>{@link HullWhiteModelWithShiftExtension#getForwardRateCurve()}
   *   <li>{@link HullWhiteModelWithShiftExtension#getLiborPeriodDiscretization()}
   *   <li>{@link HullWhiteModelWithShiftExtension#getNumberOfComponents()}
   *   <li>{@link HullWhiteModelWithShiftExtension#getNumberOfFactors()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticModel HullWhiteModelWithShiftExtension.getAnalyticModel()",
    "DiscountCurve HullWhiteModelWithShiftExtension.getDiscountCurve()",
    "ForwardCurve HullWhiteModelWithShiftExtension.getForwardRateCurve()",
    "TimeDiscretization HullWhiteModelWithShiftExtension.getLiborPeriodDiscretization()",
    "int HullWhiteModelWithShiftExtension.getNumberOfComponents()",
    "int HullWhiteModelWithShiftExtension.getNumberOfFactors()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act
    AnalyticModel actualAnalyticModel = hullWhiteModelWithShiftExtension.getAnalyticModel();
    DiscountCurve actualDiscountCurve = hullWhiteModelWithShiftExtension.getDiscountCurve();
    ForwardCurve actualForwardRateCurve = hullWhiteModelWithShiftExtension.getForwardRateCurve();
    TimeDiscretization actualLiborPeriodDiscretization =
        hullWhiteModelWithShiftExtension.getLiborPeriodDiscretization();
    int actualNumberOfComponents = hullWhiteModelWithShiftExtension.getNumberOfComponents();

    // Assert
    assertEquals(1, actualNumberOfComponents);
    assertEquals(1, hullWhiteModelWithShiftExtension.getNumberOfFactors());
    assertSame(analyticModel, actualAnalyticModel);
    assertSame(discountCurve, actualDiscountCurve);
    assertSame(forwardRateCurve, actualForwardRateCurve);
    assertSame(liborPeriodDiscretization, actualLiborPeriodDiscretization);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.montecarlo.interestrate.LIBORMarketModel HullWhiteModelWithShiftExtension.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getCloneWithModifiedData(new HashMap<>()));
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelAsGiven volatilityModel =
        new ShortRateVolatilityModelAsGiven(
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d},
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d});

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        0.0d, hullWhiteModelWithShiftExtension.getShortRateConditionalVariance(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance2() {
    // Arrange
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getShortRateConditionalVariance(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getTimeDiscretization();
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance3() {
    // Arrange
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenThrow(new UnsupportedOperationException());
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getShortRateConditionalVariance(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance4() {
    // Arrange
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenThrow(new UnsupportedOperationException());
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getShortRateConditionalVariance(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel).getVolatility(0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance5() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTimeIndex(anyDouble()))
        .thenThrow(new UnsupportedOperationException());
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getShortRateConditionalVariance(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getTimeDiscretization();
    verify(timeDiscretization, atLeast(1)).getTimeIndex(10.0d);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getTime(int)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_thenCallsGetTime() {
    // Arrange
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new UnsupportedOperationException());
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(Integer.MIN_VALUE);
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getShortRateConditionalVariance(10.0d, 10.0d));
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(timeDiscretization, atLeast(1)).getTime(-2147483648);
    verify(timeDiscretization, atLeast(1)).getTimeIndex(10.0d);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 7.016735912097631E20}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_thenReturn7016735912097631e20() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    ShortRateVolatilityModelAsGiven volatilityModel =
        new ShortRateVolatilityModelAsGiven(
            timeDiscretization,
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d},
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d});

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        7.016735912097631E20d,
        hullWhiteModelWithShiftExtension.getShortRateConditionalVariance(-2.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_thenReturnNaN() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithShiftExtension.getShortRateConditionalVariance(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_thenReturnZero() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);

    // Act
    double actualShortRateConditionalVariance =
        hullWhiteModelWithShiftExtension.getShortRateConditionalVariance(10.0d, 10.0d);

    // Assert
    verify(shortRateVolatilityModel, atLeast(1)).getMeanReversion(1);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel).getVolatility(1);
    verify(timeDiscretization, atLeast(1)).getTimeIndex(10.0d);
    assertEquals(0.0d, actualShortRateConditionalVariance, 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}.
   *
   * <ul>
   *   <li>When minus two.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_whenMinusTwo_thenReturnNaN() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithShiftExtension.getShortRateConditionalVariance(-2.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_whenTwo_thenReturnNaN() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithShiftExtension.getShortRateConditionalVariance(2.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_whenZero_thenReturnNaN() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithShiftExtension.getShortRateConditionalVariance(0.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility2() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelAsGiven volatilityModel =
        new ShortRateVolatilityModelAsGiven(
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d},
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d});

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        0.0d,
        hullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility3() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    ShortRateVolatilityModelAsGiven volatilityModel =
        new ShortRateVolatilityModelAsGiven(
            timeDiscretization,
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d},
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d});

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        0.0d,
        hullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility4() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelAsGiven volatilityModel =
        new ShortRateVolatilityModelAsGiven(
            new TenorFromArray(-2.0d, 10, 0.5d),
            new double[] {-2.0d, 2.0d, -2.0d, 2.0d},
            new double[] {
              10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d
            });

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(-2.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility5() {
    // Arrange
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getTimeDiscretization();
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility6() {
    // Arrange
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenThrow(new UnsupportedOperationException());
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility7() {
    // Arrange
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenThrow(new UnsupportedOperationException());
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getMeanReversion(0);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel).getVolatility(0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility8() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTimeIndex(anyDouble()))
        .thenThrow(new UnsupportedOperationException());
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(10.0d, 10.0d));
    verify(shortRateVolatilityModel).getTimeDiscretization();
    verify(timeDiscretization, atLeast(1)).getTimeIndex(0.0d);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getTime(int)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_thenCallsGetTime() {
    // Arrange
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new UnsupportedOperationException());
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(Integer.MIN_VALUE);
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(10.0d, 10.0d));
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(timeDiscretization, atLeast(1)).getTime(-2147483648);
    verify(timeDiscretization, atLeast(1)).getTimeIndex(anyDouble());
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>Then calls {@link ShortRateVolatilityModel#getVolatility(int)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_thenCallsGetVolatility() {
    // Arrange
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    when(shortRateVolatilityModel.getVolatility(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getMeanReversion(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(shortRateVolatilityModel.getTimeDiscretization()).thenReturn(timeDiscretization);

    // Act
    double actualIntegratedBondSquaredVolatility =
        hullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(10.0d, 10.0d);

    // Assert
    verify(shortRateVolatilityModel, atLeast(1)).getMeanReversion(1);
    verify(shortRateVolatilityModel, atLeast(1)).getTimeDiscretization();
    verify(shortRateVolatilityModel).getVolatility(1);
    verify(timeDiscretization, atLeast(1)).getTimeIndex(anyDouble());
    assertEquals(0.0d, actualIntegratedBondSquaredVolatility, 0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>When minus two.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_whenMinusTwo() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(-2.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>When two.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_whenTwo() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(2.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double,
   * double)}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteModelWithShiftExtension#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double HullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_whenZero() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertEquals(
        Double.NaN,
        hullWhiteModelWithShiftExtension.getIntegratedBondSquaredVolatility(0.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link HullWhiteModelWithShiftExtension#getModelParameters()}.
   *
   * <p>Method under test: {@link HullWhiteModelWithShiftExtension#getModelParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map HullWhiteModelWithShiftExtension.getModelParameters()"})
  public void testGetModelParameters() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModelWithShiftExtension hullWhiteModelWithShiftExtension =
        new HullWhiteModelWithShiftExtension(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> hullWhiteModelWithShiftExtension.getModelParameters());
  }
}
