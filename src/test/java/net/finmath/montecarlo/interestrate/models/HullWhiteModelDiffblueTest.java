package net.finmath.montecarlo.interestrate.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.interestrate.CalibrationProduct;
import net.finmath.montecarlo.interestrate.LIBORModel;
import net.finmath.montecarlo.interestrate.models.covariance.ShortRateVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.ShortRateVolatilityModelAsGiven;
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

public class HullWhiteModelDiffblueTest {
  /**
   * Test {@link HullWhiteModel#HullWhiteModel(RandomVariableFactory, TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#HullWhiteModel(RandomVariableFactory,
   * TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HullWhiteModel.<init>(RandomVariableFactory, TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)"
  })
  public void testNewHullWhiteModel_given42() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HashMap<String, Object> properties = new HashMap<>();
    properties.put("isInterpolateDiscountFactorsOnLiborPeriodDiscretization", "42");

    // Act
    HullWhiteModel actualHullWhiteModel =
        new HullWhiteModel(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            properties);

    // Assert
    AnalyticModel analyticModel2 = actualHullWhiteModel.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualHullWhiteModel.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualHullWhiteModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    ShortRateVolatilityModel volatilityModel2 = actualHullWhiteModel.getVolatilityModel();
    assertTrue(volatilityModel2 instanceof ShortRateVolatilityModelHoLee);
    TimeDiscretization liborPeriodDiscretization2 =
        actualHullWhiteModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualHullWhiteModel.getNumberOfFactors());
    assertEquals(10, actualHullWhiteModel.getNumberOfLibors());
    assertEquals(2, actualHullWhiteModel.getNumberOfComponents());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(volatilityModel, volatilityModel2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link HullWhiteModel#HullWhiteModel(TimeDiscretization, AnalyticModel, ForwardCurve,
   * DiscountCurve, ShortRateVolatilityModel, Map)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#HullWhiteModel(TimeDiscretization, AnalyticModel,
   * ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HullWhiteModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)"
  })
  public void testNewHullWhiteModel_given422() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HashMap<String, Object> properties = new HashMap<>();
    properties.put("isInterpolateDiscountFactorsOnLiborPeriodDiscretization", "42");

    // Act
    HullWhiteModel actualHullWhiteModel =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            properties);

    // Assert
    AnalyticModel analyticModel2 = actualHullWhiteModel.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualHullWhiteModel.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualHullWhiteModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    ShortRateVolatilityModel volatilityModel2 = actualHullWhiteModel.getVolatilityModel();
    assertTrue(volatilityModel2 instanceof ShortRateVolatilityModelHoLee);
    TimeDiscretization liborPeriodDiscretization2 =
        actualHullWhiteModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualHullWhiteModel.getNumberOfFactors());
    assertEquals(10, actualHullWhiteModel.getNumberOfLibors());
    assertEquals(2, actualHullWhiteModel.getNumberOfComponents());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(volatilityModel, volatilityModel2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link HullWhiteModel#HullWhiteModel(RandomVariableFactory, TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#HullWhiteModel(RandomVariableFactory,
   * TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HullWhiteModel.<init>(RandomVariableFactory, TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)"
  })
  public void testNewHullWhiteModel_whenHashMap() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    // Act
    HullWhiteModel actualHullWhiteModel =
        new HullWhiteModel(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Assert
    AnalyticModel analyticModel2 = actualHullWhiteModel.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualHullWhiteModel.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualHullWhiteModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    ShortRateVolatilityModel volatilityModel2 = actualHullWhiteModel.getVolatilityModel();
    assertTrue(volatilityModel2 instanceof ShortRateVolatilityModelHoLee);
    TimeDiscretization liborPeriodDiscretization2 =
        actualHullWhiteModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualHullWhiteModel.getNumberOfFactors());
    assertEquals(10, actualHullWhiteModel.getNumberOfLibors());
    assertEquals(2, actualHullWhiteModel.getNumberOfComponents());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(volatilityModel, volatilityModel2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link HullWhiteModel#HullWhiteModel(TimeDiscretization, AnalyticModel, ForwardCurve,
   * DiscountCurve, ShortRateVolatilityModel, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#HullWhiteModel(TimeDiscretization, AnalyticModel,
   * ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HullWhiteModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)"
  })
  public void testNewHullWhiteModel_whenHashMap2() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    // Act
    HullWhiteModel actualHullWhiteModel =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new HashMap<>());

    // Assert
    AnalyticModel analyticModel2 = actualHullWhiteModel.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualHullWhiteModel.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualHullWhiteModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    ShortRateVolatilityModel volatilityModel2 = actualHullWhiteModel.getVolatilityModel();
    assertTrue(volatilityModel2 instanceof ShortRateVolatilityModelHoLee);
    TimeDiscretization liborPeriodDiscretization2 =
        actualHullWhiteModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualHullWhiteModel.getNumberOfFactors());
    assertEquals(10, actualHullWhiteModel.getNumberOfLibors());
    assertEquals(2, actualHullWhiteModel.getNumberOfComponents());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(volatilityModel, volatilityModel2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link HullWhiteModel#HullWhiteModel(RandomVariableFactory, TimeDiscretization,
   * AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#HullWhiteModel(RandomVariableFactory,
   * TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HullWhiteModel.<init>(RandomVariableFactory, TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)"
  })
  public void testNewHullWhiteModel_whenNull() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    // Act
    HullWhiteModel actualHullWhiteModel =
        new HullWhiteModel(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            null);

    // Assert
    AnalyticModel analyticModel2 = actualHullWhiteModel.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualHullWhiteModel.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualHullWhiteModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    ShortRateVolatilityModel volatilityModel2 = actualHullWhiteModel.getVolatilityModel();
    assertTrue(volatilityModel2 instanceof ShortRateVolatilityModelHoLee);
    TimeDiscretization liborPeriodDiscretization2 =
        actualHullWhiteModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualHullWhiteModel.getNumberOfFactors());
    assertEquals(10, actualHullWhiteModel.getNumberOfLibors());
    assertEquals(2, actualHullWhiteModel.getNumberOfComponents());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(volatilityModel, volatilityModel2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link HullWhiteModel#HullWhiteModel(TimeDiscretization, AnalyticModel, ForwardCurve,
   * DiscountCurve, ShortRateVolatilityModel, Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#HullWhiteModel(TimeDiscretization, AnalyticModel,
   * ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HullWhiteModel.<init>(TimeDiscretization, AnalyticModel, ForwardCurve, DiscountCurve, ShortRateVolatilityModel, Map)"
  })
  public void testNewHullWhiteModel_whenNull2() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    // Act
    HullWhiteModel actualHullWhiteModel =
        new HullWhiteModel(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            null);

    // Assert
    AnalyticModel analyticModel2 = actualHullWhiteModel.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualHullWhiteModel.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualHullWhiteModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    ShortRateVolatilityModel volatilityModel2 = actualHullWhiteModel.getVolatilityModel();
    assertTrue(volatilityModel2 instanceof ShortRateVolatilityModelHoLee);
    TimeDiscretization liborPeriodDiscretization2 =
        actualHullWhiteModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualHullWhiteModel.getNumberOfFactors());
    assertEquals(10, actualHullWhiteModel.getNumberOfLibors());
    assertEquals(2, actualHullWhiteModel.getNumberOfComponents());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(volatilityModel, volatilityModel2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link HullWhiteModel#getReferenceDate()}.
   *
   * <ul>
   *   <li>Then return toLocalTime toString is {@code 00:00}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDateTime HullWhiteModel.getReferenceDate()"})
  public void testGetReferenceDate_thenReturnToLocalTimeToStringIs0000()
      throws CalculationException {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    LocalDate ofResult = LocalDate.of(1970, 1, 1);
    when(discountCurve.getReferenceDate()).thenReturn(ofResult);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult2 =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act
    LocalDateTime actualReferenceDate = ofResult2.getReferenceDate();

    // Assert
    verify(discountCurve).getReferenceDate();
    assertEquals("00:00", actualReferenceDate.toLocalTime().toString());
    LocalDate toLocalDateResult = actualReferenceDate.toLocalDate();
    assertEquals("1970-01-01", toLocalDateResult.toString());
    assertSame(ofResult, toLocalDateResult);
  }

  /**
   * Test {@link HullWhiteModel#getReferenceDate()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDateTime HullWhiteModel.getReferenceDate()"})
  public void testGetReferenceDate_thenThrowIllegalArgumentException() throws CalculationException {
    // Arrange
    DiscountCurveInterpolation discountCurve = mock(DiscountCurveInterpolation.class);
    when(discountCurve.getReferenceDate()).thenThrow(new IllegalArgumentException());
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> ofResult.getReferenceDate());
    verify(discountCurve).getReferenceDate();
  }

  /**
   * Test {@link HullWhiteModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link HullWhiteModel#applyStateSpaceTransform(MonteCarloProcess, int,
   * int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
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
        ofResult.applyStateSpaceTransform(process, 1, 1, randomVariable);

    // Assert
    assertSame(randomVariable, actualApplyStateSpaceTransformResult);
  }

  /**
   * Test {@link HullWhiteModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link HullWhiteModel#applyStateSpaceTransformInverse(MonteCarloProcess,
   * int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
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
        ofResult.applyStateSpaceTransformInverse(process, 1, 1, randomVariable);

    // Assert
    assertSame(randomVariable, actualApplyStateSpaceTransformInverseResult);
  }

  /**
   * Test {@link HullWhiteModel#getInitialState(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] HullWhiteModel.getInitialState(MonteCarloProcess)"})
  public void testGetInitialState_thenFirstElementReturnRandomVariableFromFloatArray()
      throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = ofResult.getInitialState(process);

    // Assert
    RandomVariable randomVariable = actualInitialState[0];
    assertTrue(randomVariable instanceof RandomVariableFromFloatArray);
    assertEquals(2, actualInitialState.length);
    assertSame(randomVariable, actualInitialState[1]);
  }

  /**
   * Test {@link HullWhiteModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <p>Method under test: {@link HullWhiteModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable HullWhiteModel.getNumeraire(MonteCarloProcess, double)"})
  public void testGetNumeraire() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = ofResult.getNumeraire(process, 10.0d);

    // Assert
    assertTrue(actualNumeraire instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {1.0d}, actualNumeraire.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getNumberOfTimes()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable HullWhiteModel.getNumeraire(MonteCarloProcess, double)"})
  public void testGetNumeraire_thenCallsGetNumberOfTimes() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
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
    RandomVariable actualNumeraire = ofResult.getNumeraire(process, 10.0d);

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(0);
    assertTrue(actualNumeraire instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.variance() instanceof RandomVariableFromFloatArray);
    assertArrayEquals(new double[] {1.0d}, actualNumeraire.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModel#getForwardDiscountBond(MonteCarloProcess, double, double)}.
   *
   * <p>Method under test: {@link HullWhiteModel#getForwardDiscountBond(MonteCarloProcess, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getForwardDiscountBond(MonteCarloProcess, double, double)"
  })
  public void testGetForwardDiscountBond() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getProcessValue(anyInt(), anyInt())).thenThrow(new IllegalArgumentException());
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> ofResult.getForwardDiscountBond(process, 10.0d, 10.0d));
    verify(process).getProcessValue(1, 0);
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTime(anyInt());
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTimeStep(anyInt());
  }

  /**
   * Test {@link HullWhiteModel#getForwardDiscountBond(MonteCarloProcess, double, double)}.
   *
   * <ul>
   *   <li>Then calls {@link TenorFromArray#getNumberOfTimes()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getForwardDiscountBond(MonteCarloProcess, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getForwardDiscountBond(MonteCarloProcess, double, double)"
  })
  public void testGetForwardDiscountBond_thenCallsGetNumberOfTimes() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getNumberOfTimes()).thenThrow(new IllegalArgumentException());
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(-1);

    ForwardCurveFromDiscountCurve forwardRateCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardRateCurve.getName()).thenReturn("Name");
    when(forwardRateCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> ofResult.getForwardDiscountBond(process, 10.0d, 10.0d));
    verify(forwardRateCurve).getName();
    verify(forwardRateCurve).getReferenceDate();
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization).getNumberOfTimes();
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
  }

  /**
   * Test {@link HullWhiteModel#getForwardDiscountBond(MonteCarloProcess, double, double)}.
   *
   * <ul>
   *   <li>Then calls {@link ForwardCurveFromDiscountCurve#getPaymentOffset(double)}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getForwardDiscountBond(MonteCarloProcess, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getForwardDiscountBond(MonteCarloProcess, double, double)"
  })
  public void testGetForwardDiscountBond_thenCallsGetPaymentOffset() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);

    ForwardCurveFromDiscountCurve forwardRateCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardRateCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenThrow(new IllegalArgumentException());
    when(forwardRateCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardRateCurve.getName()).thenReturn("Name");
    when(forwardRateCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> ofResult.getForwardDiscountBond(process, 10.0d, 10.0d));
    verify(forwardRateCurve).getName();
    verify(forwardRateCurve).getReferenceDate();
    verify(forwardRateCurve).getPaymentOffset(0.0d);
    verify(forwardRateCurve).getForward(isA(AnalyticModel.class), eq(0.0d));
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization).getTime(0);
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization).getTimeStep(1);
  }

  /**
   * Test {@link HullWhiteModel#getForwardDiscountBond(MonteCarloProcess, double, double)}.
   *
   * <ul>
   *   <li>Then calls {@link EulerSchemeFromProcessModel#getTimeDiscretization()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getForwardDiscountBond(MonteCarloProcess, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getForwardDiscountBond(MonteCarloProcess, double, double)"
  })
  public void testGetForwardDiscountBond_thenCallsGetTimeDiscretization()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTimeDiscretization()).thenThrow(new IllegalArgumentException());
    when(process.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> ofResult.getForwardDiscountBond(process, 10.0d, 10.0d));
    verify(process).getProcessValue(1, 0);
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process).getTimeDiscretization();
    verify(process, atLeast(1)).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTime(anyInt());
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTimeStep(anyInt());
  }

  /**
   * Test {@link HullWhiteModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_givenTen_thenReturnFirstElementIsNull() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
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
        ofResult.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    assertNull(actualDrift[0]);
    assertNull(actualDrift[1]);
    assertEquals(2, actualDrift.length);
  }

  /**
   * Test {@link HullWhiteModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenFirstElementReturnRandomVariableFromDoubleArray()
      throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualDrift =
        ofResult.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualDrift[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift[1] instanceof RandomVariableFromDoubleArray);
    assertEquals(2, actualDrift.length);
  }

  /**
   * Test {@link HullWhiteModel#getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])}.
   *
   * <p>Method under test: {@link HullWhiteModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
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

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
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
        ofResult.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertTrue(actualFactorLoading[1] instanceof Scalar);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link HullWhiteModel#getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])}.
   *
   * <p>Method under test: {@link HullWhiteModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading2() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(-2.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualFactorLoading =
        ofResult.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertTrue(actualFactorLoading[1] instanceof Scalar);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link HullWhiteModel#getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getNumberOfTimes()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenCallsGetNumberOfTimes() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
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
        ofResult.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertTrue(actualFactorLoading[1] instanceof Scalar);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link HullWhiteModel#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable HullWhiteModel.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant_thenReturnRandomVariableFromFloatArray()
      throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act
    RandomVariable actualRandomVariableForConstant = ofResult.getRandomVariableForConstant(10.0d);

    // Assert
    assertTrue(actualRandomVariableForConstant instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualRandomVariableForConstant.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualRandomVariableForConstant.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualRandomVariableForConstant.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableForConstant.getTypePriority());
    assertEquals(1, actualRandomVariableForConstant.size());
    assertEquals(10.0d, actualRandomVariableForConstant.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMin(), 0.0);
    assertTrue(actualRandomVariableForConstant.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualRandomVariableForConstant.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualRandomVariableForConstant.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModel#getForwardRate(MonteCarloProcess, double, double, double)}.
   *
   * <p>Method under test: {@link HullWhiteModel#getForwardRate(MonteCarloProcess, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getProcessValue(anyInt(), anyInt())).thenThrow(new IllegalArgumentException());
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> ofResult.getForwardRate(process, 10.0d, 10.0d, 10.0d));
    verify(process).getProcessValue(1, 0);
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTime(anyInt());
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTimeStep(anyInt());
  }

  /**
   * Test {@link HullWhiteModel#getForwardRate(MonteCarloProcess, double, double, double)}.
   *
   * <p>Method under test: {@link HullWhiteModel#getForwardRate(MonteCarloProcess, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate2() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTimeDiscretization()).thenThrow(new IllegalArgumentException());
    when(process.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> ofResult.getForwardRate(process, 10.0d, 10.0d, 10.0d));
    verify(process).getProcessValue(1, 0);
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process).getTimeDiscretization();
    verify(process, atLeast(1)).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTime(anyInt());
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTimeStep(anyInt());
  }

  /**
   * Test {@link HullWhiteModel#getForwardRate(MonteCarloProcess, double, double, double)}.
   *
   * <ul>
   *   <li>Then calls {@link TenorFromArray#getNumberOfTimes()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getForwardRate(MonteCarloProcess, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate_thenCallsGetNumberOfTimes() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getNumberOfTimes()).thenThrow(new IllegalArgumentException());
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(-1);

    ForwardCurveFromDiscountCurve forwardRateCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardRateCurve.getName()).thenReturn("Name");
    when(forwardRateCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> ofResult.getForwardRate(process, 10.0d, 10.0d, 10.0d));
    verify(forwardRateCurve).getName();
    verify(forwardRateCurve).getReferenceDate();
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization).getNumberOfTimes();
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
  }

  /**
   * Test {@link HullWhiteModel#getForwardRate(MonteCarloProcess, double, double, double)}.
   *
   * <ul>
   *   <li>Then calls {@link ForwardCurveFromDiscountCurve#getPaymentOffset(double)}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getForwardRate(MonteCarloProcess, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate_thenCallsGetPaymentOffset() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);

    ForwardCurveFromDiscountCurve forwardRateCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardRateCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenThrow(new IllegalArgumentException());
    when(forwardRateCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardRateCurve.getName()).thenReturn("Name");
    when(forwardRateCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> ofResult.getForwardRate(process, 10.0d, 10.0d, 10.0d));
    verify(forwardRateCurve).getName();
    verify(forwardRateCurve).getReferenceDate();
    verify(forwardRateCurve).getPaymentOffset(0.0d);
    verify(forwardRateCurve).getForward(isA(AnalyticModel.class), eq(0.0d));
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization).getTime(0);
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization).getTimeStep(1);
  }

  /**
   * Test {@link HullWhiteModel#getForwardRate(MonteCarloProcess, double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getForwardRate(MonteCarloProcess, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getForwardRate(MonteCarloProcess, double, double, double)"
  })
  public void testGetForwardRate_thenReturnRandomVariableFromDoubleArray()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(process.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act
    RandomVariable actualForwardRate = ofResult.getForwardRate(process, 10.0d, 10.0d, 10.0d);

    // Assert
    verify(process, atLeast(1)).getProcessValue(1, 0);
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process, atLeast(1)).getTimeDiscretization();
    verify(process, atLeast(1)).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTime(anyInt());
    verify(liborPeriodDiscretization, atLeast(1)).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTimeStep(anyInt());
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
   * Test {@link HullWhiteModel#getLIBOR(MonteCarloProcess, int, int)} with {@code process}, {@code
   * timeIndex}, {@code liborIndex}.
   *
   * <p>Method under test: {@link HullWhiteModel#getLIBOR(MonteCarloProcess, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable HullWhiteModel.getLIBOR(MonteCarloProcess, int, int)"})
  public void testGetLIBORWithProcessTimeIndexLiborIndex() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getProcessValue(anyInt(), anyInt())).thenThrow(new IllegalArgumentException());
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> ofResult.getLIBOR(process, 1, 1));
    verify(process).getProcessValue(1, 0);
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTime(anyInt());
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTimeStep(anyInt());
  }

  /**
   * Test {@link HullWhiteModel#getLIBOR(MonteCarloProcess, int, int)} with {@code process}, {@code
   * timeIndex}, {@code liborIndex}.
   *
   * <p>Method under test: {@link HullWhiteModel#getLIBOR(MonteCarloProcess, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable HullWhiteModel.getLIBOR(MonteCarloProcess, int, int)"})
  public void testGetLIBORWithProcessTimeIndexLiborIndex2() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(process.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act
    RandomVariable actualLIBOR = ofResult.getLIBOR(process, 1, 1);

    // Assert
    verify(process, atLeast(1)).getProcessValue(1, 0);
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process, atLeast(1)).getTimeDiscretization();
    verify(process, atLeast(1)).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTime(anyInt());
    verify(liborPeriodDiscretization, atLeast(1)).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTimeStep(anyInt());
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
    assertArrayEquals(new double[] {Double.NaN}, actualLIBOR.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModel#getLIBOR(MonteCarloProcess, int, int)} with {@code process}, {@code
   * timeIndex}, {@code liborIndex}.
   *
   * <p>Method under test: {@link HullWhiteModel#getLIBOR(MonteCarloProcess, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable HullWhiteModel.getLIBOR(MonteCarloProcess, int, int)"})
  public void testGetLIBORWithProcessTimeIndexLiborIndex3() throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveInterpolation forwardRateCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTimeDiscretization()).thenThrow(new IllegalArgumentException());
    when(process.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> ofResult.getLIBOR(process, 1, 1));
    verify(process).getProcessValue(1, 0);
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process).getTimeDiscretization();
    verify(process, atLeast(1)).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTime(anyInt());
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTimeStep(anyInt());
  }

  /**
   * Test {@link HullWhiteModel#getLIBOR(MonteCarloProcess, int, int)} with {@code process}, {@code
   * timeIndex}, {@code liborIndex}.
   *
   * <ul>
   *   <li>Then calls {@link Scalar#add(RandomVariable)}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getLIBOR(MonteCarloProcess, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable HullWhiteModel.getLIBOR(MonteCarloProcess, int, int)"})
  public void testGetLIBORWithProcessTimeIndexLiborIndex_thenCallsAdd()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(0.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);

    ForwardCurveFromDiscountCurve forwardRateCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardRateCurve.getName()).thenReturn("Name");
    when(forwardRateCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    Scalar scalar = mock(Scalar.class);
    when(scalar.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(process.getProcessValue(anyInt(), anyInt())).thenReturn(scalar);
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act
    RandomVariable actualLIBOR = ofResult.getLIBOR(process, 1, 1);

    // Assert
    verify(forwardRateCurve).getName();
    verify(forwardRateCurve).getReferenceDate();
    verify(process, atLeast(1)).getProcessValue(1, 0);
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process, atLeast(1)).getTimeDiscretization();
    verify(process, atLeast(1)).getTimeIndex(10.0d);
    verify(scalar, atLeast(1)).add(Mockito.<RandomVariable>any());
    verify(liborPeriodDiscretization, atLeast(1)).getTime(anyInt());
    verify(liborPeriodDiscretization, atLeast(1)).getTimeIndex(anyDouble());
    verify(liborPeriodDiscretization, atLeast(1)).getTimeStep(anyInt());
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
    assertArrayEquals(new double[] {Double.NaN}, actualLIBOR.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteModel#getLIBOR(MonteCarloProcess, int, int)} with {@code process}, {@code
   * timeIndex}, {@code liborIndex}.
   *
   * <ul>
   *   <li>Then calls {@link TenorFromArray#getNumberOfTimes()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getLIBOR(MonteCarloProcess, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable HullWhiteModel.getLIBOR(MonteCarloProcess, int, int)"})
  public void testGetLIBORWithProcessTimeIndexLiborIndex_thenCallsGetNumberOfTimes()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getNumberOfTimes()).thenThrow(new IllegalArgumentException());
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(-1);

    ForwardCurveFromDiscountCurve forwardRateCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardRateCurve.getName()).thenReturn("Name");
    when(forwardRateCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> ofResult.getLIBOR(process, 1, 1));
    verify(forwardRateCurve).getName();
    verify(forwardRateCurve).getReferenceDate();
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization).getNumberOfTimes();
    verify(liborPeriodDiscretization).getTime(1);
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
  }

  /**
   * Test {@link HullWhiteModel#getLIBOR(MonteCarloProcess, int, int)} with {@code process}, {@code
   * timeIndex}, {@code liborIndex}.
   *
   * <ul>
   *   <li>Then calls {@link ForwardCurveFromDiscountCurve#getPaymentOffset(double)}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getLIBOR(MonteCarloProcess, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable HullWhiteModel.getLIBOR(MonteCarloProcess, int, int)"})
  public void testGetLIBORWithProcessTimeIndexLiborIndex_thenCallsGetPaymentOffset()
      throws CalculationException {
    // Arrange
    TenorFromArray liborPeriodDiscretization = mock(TenorFromArray.class);
    when(liborPeriodDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(liborPeriodDiscretization.getTimeIndex(anyDouble())).thenReturn(1);

    ForwardCurveFromDiscountCurve forwardRateCurve = mock(ForwardCurveFromDiscountCurve.class);
    when(forwardRateCurve.getForward(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenThrow(new IllegalArgumentException());
    when(forwardRateCurve.getPaymentOffset(anyDouble())).thenReturn(10.0d);
    when(forwardRateCurve.getName()).thenReturn("Name");
    when(forwardRateCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getTime(anyInt())).thenReturn(10.0d);
    when(process.getTimeIndex(anyDouble())).thenReturn(1);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> ofResult.getLIBOR(process, 1, 1));
    verify(forwardRateCurve).getName();
    verify(forwardRateCurve).getReferenceDate();
    verify(forwardRateCurve).getPaymentOffset(0.0d);
    verify(forwardRateCurve).getForward(isA(AnalyticModel.class), eq(0.0d));
    verify(process, atLeast(1)).getTime(anyInt());
    verify(process).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization, atLeast(1)).getTime(anyInt());
    verify(liborPeriodDiscretization).getTimeIndex(10.0d);
    verify(liborPeriodDiscretization).getTimeStep(1);
  }

  /**
   * Test {@link HullWhiteModel#getNumberOfLibors()}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getNumberOfLibors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModel.getNumberOfLibors()"})
  public void testGetNumberOfLibors_thenReturnTen() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act and Assert
    assertEquals(10, ofResult.getNumberOfLibors());
  }

  /**
   * Test {@link HullWhiteModel#getLiborPeriod(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getLiborPeriod(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HullWhiteModel.getLiborPeriod(int)"})
  public void testGetLiborPeriod_thenReturn105() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act and Assert
    assertEquals(10.5d, ofResult.getLiborPeriod(1), 0.0);
  }

  /**
   * Test {@link HullWhiteModel#getLiborPeriodIndex(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getLiborPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HullWhiteModel.getLiborPeriodIndex(double)"})
  public void testGetLiborPeriodIndex_thenReturnZero() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act and Assert
    assertEquals(0, ofResult.getLiborPeriodIndex(10.0d));
  }

  /**
   * Test {@link HullWhiteModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LIBORModel HullWhiteModel.getCloneWithModifiedData(Map)"})
  public void testGetCloneWithModifiedData_whenHashMap() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act
    LIBORModel actualCloneWithModifiedData = ofResult.getCloneWithModifiedData(new HashMap<>());

    // Assert
    AnalyticModel analyticModel2 = actualCloneWithModifiedData.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualCloneWithModifiedData.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualCloneWithModifiedData.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(actualCloneWithModifiedData instanceof HullWhiteModel);
    ShortRateVolatilityModel volatilityModel2 =
        ((HullWhiteModel) actualCloneWithModifiedData).getVolatilityModel();
    assertTrue(volatilityModel2 instanceof ShortRateVolatilityModelHoLee);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualCloneWithModifiedData.getNumberOfFactors());
    assertEquals(10, actualCloneWithModifiedData.getNumberOfLibors());
    assertEquals(2, actualCloneWithModifiedData.getNumberOfComponents());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(volatilityModel, volatilityModel2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link HullWhiteModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LIBORModel HullWhiteModel.getCloneWithModifiedData(Map)"})
  public void testGetCloneWithModifiedData_whenNull() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act
    LIBORModel actualCloneWithModifiedData = ofResult.getCloneWithModifiedData(null);

    // Assert
    AnalyticModel analyticModel2 = actualCloneWithModifiedData.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualCloneWithModifiedData.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualCloneWithModifiedData.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(actualCloneWithModifiedData instanceof HullWhiteModel);
    ShortRateVolatilityModel volatilityModel2 =
        ((HullWhiteModel) actualCloneWithModifiedData).getVolatilityModel();
    assertTrue(volatilityModel2 instanceof ShortRateVolatilityModelHoLee);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualCloneWithModifiedData.getNumberOfFactors());
    assertEquals(10, actualCloneWithModifiedData.getNumberOfLibors());
    assertEquals(2, actualCloneWithModifiedData.getNumberOfComponents());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(volatilityModel, volatilityModel2);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link HullWhiteModel#getShortRateConditionalVariance(double, double)}.
   *
   * <ul>
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_thenAbsReturnScalar()
      throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act
    RandomVariable actualShortRateConditionalVariance =
        ofResult.getShortRateConditionalVariance(10.0d, 10.0d);

    // Assert
    assertTrue(actualShortRateConditionalVariance instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.abs() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.cos() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.exp() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.expm1() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.invert() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.isNaN() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.sin() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.sqrt() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.squared() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualShortRateConditionalVariance.expectation();
    assertSame(actualShortRateConditionalVariance, actualExpectationResult);
  }

  /**
   * Test {@link HullWhiteModel#getShortRateConditionalVariance(double, double)}.
   *
   * <ul>
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_thenAbsReturnScalar2()
      throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act
    RandomVariable actualShortRateConditionalVariance =
        ofResult.getShortRateConditionalVariance(-2.0d, 10.0d);

    // Assert
    assertTrue(actualShortRateConditionalVariance instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.abs() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.cos() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.exp() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.expm1() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.invert() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.isNaN() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.sin() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.sqrt() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.squared() instanceof Scalar);
    assertTrue(actualShortRateConditionalVariance.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualShortRateConditionalVariance.expectation();
    assertSame(actualShortRateConditionalVariance, actualExpectationResult);
  }

  /**
   * Test {@link HullWhiteModel#getShortRateConditionalVariance(double, double)}.
   *
   * <ul>
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getShortRateConditionalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getShortRateConditionalVariance(double, double)"
  })
  public void testGetShortRateConditionalVariance_thenReturnAverageIsZero()
      throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
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

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act
    RandomVariable actualShortRateConditionalVariance =
        ofResult.getShortRateConditionalVariance(10.0d, 10.0d);

    // Assert
    assertTrue(actualShortRateConditionalVariance instanceof Scalar);
    assertEquals(0.0d, actualShortRateConditionalVariance.getAverage(), 0.0);
    assertEquals(0.0d, actualShortRateConditionalVariance.getMax(), 0.0);
    assertEquals(0.0d, actualShortRateConditionalVariance.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualShortRateConditionalVariance.expectation();
    assertSame(actualShortRateConditionalVariance, actualExpectationResult);
  }

  /**
   * Test {@link HullWhiteModel#getIntegratedBondSquaredVolatility(double, double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_thenReturnScalar()
      throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act
    RandomVariable actualIntegratedBondSquaredVolatility =
        ofResult.getIntegratedBondSquaredVolatility(10.0d, 10.0d);

    // Assert
    assertTrue(actualIntegratedBondSquaredVolatility instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.abs() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.cos() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.exp() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.expm1() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.invert() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.isNaN() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.sin() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.sqrt() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.squared() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualIntegratedBondSquaredVolatility.expectation();
    assertSame(actualIntegratedBondSquaredVolatility, actualExpectationResult);
  }

  /**
   * Test {@link HullWhiteModel#getIntegratedBondSquaredVolatility(double, double)}.
   *
   * <ul>
   *   <li>When minus two.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_whenMinusTwo_thenReturnScalar()
      throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act
    RandomVariable actualIntegratedBondSquaredVolatility =
        ofResult.getIntegratedBondSquaredVolatility(-2.0d, 10.0d);

    // Assert
    assertTrue(actualIntegratedBondSquaredVolatility instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.abs() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.cos() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.exp() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.expm1() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.invert() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.isNaN() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.sin() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.sqrt() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.squared() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualIntegratedBondSquaredVolatility.expectation();
    assertSame(actualIntegratedBondSquaredVolatility, actualExpectationResult);
  }

  /**
   * Test {@link HullWhiteModel#getIntegratedBondSquaredVolatility(double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_whenZero_thenReturnScalar()
      throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act
    RandomVariable actualIntegratedBondSquaredVolatility =
        ofResult.getIntegratedBondSquaredVolatility(0.0d, 10.0d);

    // Assert
    assertTrue(actualIntegratedBondSquaredVolatility instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.abs() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.cos() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.exp() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.expm1() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.invert() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.isNaN() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.sin() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.sqrt() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.squared() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualIntegratedBondSquaredVolatility.expectation();
    assertSame(actualIntegratedBondSquaredVolatility, actualExpectationResult);
  }

  /**
   * Test {@link HullWhiteModel#getIntegratedBondSquaredVolatility(double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getIntegratedBondSquaredVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteModel.getIntegratedBondSquaredVolatility(double, double)"
  })
  public void testGetIntegratedBondSquaredVolatility_whenZero_thenReturnScalar2()
      throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act
    RandomVariable actualIntegratedBondSquaredVolatility =
        ofResult.getIntegratedBondSquaredVolatility(10.0d, 0.0d);

    // Assert
    assertTrue(actualIntegratedBondSquaredVolatility instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.abs() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.cos() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.exp() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.expm1() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.invert() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.isNaN() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.sin() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.sqrt() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.squared() instanceof Scalar);
    assertTrue(actualIntegratedBondSquaredVolatility.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualIntegratedBondSquaredVolatility.expectation();
    assertSame(actualIntegratedBondSquaredVolatility, actualExpectationResult);
  }

  /**
   * Test {@link HullWhiteModel#getCloneWithModifiedVolatilityModel(ShortRateVolatilityModel)}.
   *
   * <p>Method under test: {@link
   * HullWhiteModel#getCloneWithModifiedVolatilityModel(ShortRateVolatilityModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "HullWhiteModel HullWhiteModel.getCloneWithModifiedVolatilityModel(ShortRateVolatilityModel)"
  })
  public void testGetCloneWithModifiedVolatilityModel() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());
    ShortRateVolatilityModelHoLee volatilityModel2 = new ShortRateVolatilityModelHoLee(10.0d);

    // Act
    HullWhiteModel actualCloneWithModifiedVolatilityModel =
        ofResult.getCloneWithModifiedVolatilityModel(volatilityModel2);

    // Assert
    AnalyticModel analyticModel2 = actualCloneWithModifiedVolatilityModel.getAnalyticModel();
    assertTrue(analyticModel2 instanceof AnalyticModelFromCurvesAndVols);
    DiscountCurve discountCurve2 = actualCloneWithModifiedVolatilityModel.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardRateCurve2 = actualCloneWithModifiedVolatilityModel.getForwardRateCurve();
    assertTrue(forwardRateCurve2 instanceof ForwardCurveFromDiscountCurve);
    ShortRateVolatilityModel volatilityModel3 =
        actualCloneWithModifiedVolatilityModel.getVolatilityModel();
    assertTrue(volatilityModel3 instanceof ShortRateVolatilityModelHoLee);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualCloneWithModifiedVolatilityModel.getNumberOfFactors());
    assertEquals(10, actualCloneWithModifiedVolatilityModel.getNumberOfLibors());
    assertEquals(2, actualCloneWithModifiedVolatilityModel.getNumberOfComponents());
    assertSame(analyticModel, analyticModel2);
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardRateCurve, forwardRateCurve2);
    assertSame(volatilityModel2, volatilityModel3);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
  }

  /**
   * Test {@link HullWhiteModel#getModelParameters()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteModel#getModelParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map HullWhiteModel.getModelParameters()"})
  public void testGetModelParameters_thenReturnEmpty() throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    ShortRateVolatilityModelHoLee volatilityModel = new ShortRateVolatilityModelHoLee(10.0d);

    HullWhiteModel ofResult =
        HullWhiteModel.of(
            randomVariableFactory,
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            volatilityModel,
            new CalibrationProduct[] {},
            new HashMap<>());

    // Act and Assert
    assertTrue(ofResult.getModelParameters().isEmpty());
  }
}
