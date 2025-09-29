package net.finmath.singleswaprate.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
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
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping.AnnuityMappingType;
import net.finmath.singleswaprate.annuitymapping.SimplifiedLinearAnnuityMapping;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class CashSettledPayerSwaptionDiffblueTest {
  /**
   * Test {@link CashSettledPayerSwaption#CashSettledPayerSwaption(Schedule, Schedule, double,
   * String, String, String, AnnuityMappingType)}.
   *
   * <p>Method under test: {@link CashSettledPayerSwaption#CashSettledPayerSwaption(Schedule,
   * Schedule, double, String, String, String, AnnuityMapping.AnnuityMappingType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CashSettledPayerSwaption.<init>(Schedule, Schedule, double, String, String, String, AnnuityMapping.AnnuityMappingType)"
  })
  public void testNewCashSettledPayerSwaption() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    CashSettledPayerSwaption actualCashSettledPayerSwaption =
        new CashSettledPayerSwaption(
            fixSchedule,
            floatSchedule,
            10.0d,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    // Assert
    Schedule fixSchedule2 = actualCashSettledPayerSwaption.getFixSchedule();
    assertTrue(fixSchedule2 instanceof RegularSchedule);
    Schedule floatSchedule2 = actualCashSettledPayerSwaption.getFloatSchedule();
    assertTrue(floatSchedule2 instanceof RegularSchedule);
    assertEquals("3", actualCashSettledPayerSwaption.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualCashSettledPayerSwaption.getForwardCurveName());
    assertEquals("Volatility Cube Name", actualCashSettledPayerSwaption.getVolatilityCubeName());
    assertEquals(-0.15d, actualCashSettledPayerSwaption.getIntegrationLowerBound(), 0.0);
    assertEquals(0.15d, actualCashSettledPayerSwaption.getIntegrationUpperBound(), 0.0);
    assertEquals(500, actualCashSettledPayerSwaption.getIntegrationNumberOfEvaluationPoints());
    assertSame(fixSchedule, fixSchedule2);
    assertSame(floatSchedule, floatSchedule2);
  }

  /**
   * Test {@link CashSettledPayerSwaption#CashSettledPayerSwaption(Schedule, Schedule, double,
   * String, String, String, AnnuityMappingType, double, double, int)}.
   *
   * <p>Method under test: {@link CashSettledPayerSwaption#CashSettledPayerSwaption(Schedule,
   * Schedule, double, String, String, String, AnnuityMapping.AnnuityMappingType, double, double,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CashSettledPayerSwaption.<init>(Schedule, Schedule, double, String, String, String, AnnuityMapping.AnnuityMappingType, double, double, int)"
  })
  public void testNewCashSettledPayerSwaption2() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    CashSettledPayerSwaption actualCashSettledPayerSwaption =
        new CashSettledPayerSwaption(
            fixSchedule,
            floatSchedule,
            10.0d,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG,
            10.0d,
            10.0d,
            10);

    // Assert
    Schedule fixSchedule2 = actualCashSettledPayerSwaption.getFixSchedule();
    assertTrue(fixSchedule2 instanceof RegularSchedule);
    Schedule floatSchedule2 = actualCashSettledPayerSwaption.getFloatSchedule();
    assertTrue(floatSchedule2 instanceof RegularSchedule);
    assertEquals("3", actualCashSettledPayerSwaption.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualCashSettledPayerSwaption.getForwardCurveName());
    assertEquals("Volatility Cube Name", actualCashSettledPayerSwaption.getVolatilityCubeName());
    assertEquals(10, actualCashSettledPayerSwaption.getIntegrationNumberOfEvaluationPoints());
    assertEquals(10.0d, actualCashSettledPayerSwaption.getIntegrationLowerBound(), 0.0);
    assertEquals(10.0d, actualCashSettledPayerSwaption.getIntegrationUpperBound(), 0.0);
    assertSame(fixSchedule, fixSchedule2);
    assertSame(floatSchedule, floatSchedule2);
  }

  /**
   * Test {@link CashSettledPayerSwaption#payoffFunction(double, AnnuityMapping,
   * VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link CashSettledPayerSwaption#payoffFunction(double, AnnuityMapping,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CashSettledPayerSwaption.payoffFunction(double, AnnuityMapping, VolatilityCubeModel)"
  })
  public void testPayoffFunction_whenTen_thenReturnZero() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    CashSettledPayerSwaption cashSettledPayerSwaption =
        new CashSettledPayerSwaption(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            10.0d,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        cashSettledPayerSwaption.payoffFunction(
            10.0d, annuityMapping, new AnalyticModelWithVolatilityCubes()),
        0.0);
  }

  /**
   * Test {@link CashSettledPayerSwaption#payoffFunction(double, AnnuityMapping,
   * VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link CashSettledPayerSwaption#payoffFunction(double, AnnuityMapping,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CashSettledPayerSwaption.payoffFunction(double, AnnuityMapping, VolatilityCubeModel)"
  })
  public void testPayoffFunction_whenZero_thenReturnZero() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    CashSettledPayerSwaption cashSettledPayerSwaption =
        new CashSettledPayerSwaption(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            10.0d,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        cashSettledPayerSwaption.payoffFunction(
            0.0d, annuityMapping, new AnalyticModelWithVolatilityCubes()),
        0.0);
  }

  /**
   * Test {@link CashSettledPayerSwaption#hedgeWeight(double, AnnuityMapping, VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then return {@code -0.004000091031609712}.
   * </ul>
   *
   * <p>Method under test: {@link CashSettledPayerSwaption#hedgeWeight(double, AnnuityMapping,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CashSettledPayerSwaption.hedgeWeight(double, AnnuityMapping, VolatilityCubeModel)"
  })
  public void testHedgeWeight_thenReturn0004000091031609712() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    CashSettledPayerSwaption cashSettledPayerSwaption =
        new CashSettledPayerSwaption(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            1.0d,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        -0.004000091031609712d,
        cashSettledPayerSwaption.hedgeWeight(
            10.0d, annuityMapping, new AnalyticModelWithVolatilityCubes()),
        0.0);
  }

  /**
   * Test {@link CashSettledPayerSwaption#hedgeWeight(double, AnnuityMapping, VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link CashSettledPayerSwaption#hedgeWeight(double, AnnuityMapping,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CashSettledPayerSwaption.hedgeWeight(double, AnnuityMapping, VolatilityCubeModel)"
  })
  public void testHedgeWeight_thenReturnZero() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    CashSettledPayerSwaption cashSettledPayerSwaption =
        new CashSettledPayerSwaption(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            10.0d,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        cashSettledPayerSwaption.hedgeWeight(
            10.0d, annuityMapping, new AnalyticModelWithVolatilityCubes()),
        0.0);
  }

  /**
   * Test {@link CashSettledPayerSwaption#buildAnnuityMapping(VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link CashSettledPayerSwaption#buildAnnuityMapping(VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping CashSettledPayerSwaption.buildAnnuityMapping(VolatilityCubeModel)"
  })
  public void testBuildAnnuityMapping() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    CashSettledPayerSwaption cashSettledPayerSwaption =
        new CashSettledPayerSwaption(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            10.0d,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.SIMPLIFIEDLINEAR);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    AnnuityMapping actualBuildAnnuityMappingResult =
        cashSettledPayerSwaption.buildAnnuityMapping(model);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1)).getReferenceDate();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualBuildAnnuityMappingResult instanceof SimplifiedLinearAnnuityMapping);
    assertEquals(0.0d, actualBuildAnnuityMappingResult.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBuildAnnuityMappingResult.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBuildAnnuityMappingResult.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link CashSettledPayerSwaption#buildAnnuityMapping(VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link CashSettledPayerSwaption#buildAnnuityMapping(VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping CashSettledPayerSwaption.buildAnnuityMapping(VolatilityCubeModel)"
  })
  public void testBuildAnnuityMapping2() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    CashSettledPayerSwaption cashSettledPayerSwaption =
        new CashSettledPayerSwaption(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            Double.NaN,
            "3",
            "",
            "Volatility Cube Name",
            AnnuityMappingType.SIMPLIFIEDLINEAR);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);

    // Act
    AnnuityMapping actualBuildAnnuityMappingResult =
        cashSettledPayerSwaption.buildAnnuityMapping(model);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(discountCurveFromForwardCurve, atLeast(1)).getReferenceDate();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualBuildAnnuityMappingResult instanceof SimplifiedLinearAnnuityMapping);
    assertEquals(0.0d, actualBuildAnnuityMappingResult.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBuildAnnuityMappingResult.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBuildAnnuityMappingResult.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link CashSettledPayerSwaption#buildAnnuityMapping(VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link CashSettledPayerSwaption#buildAnnuityMapping(VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping CashSettledPayerSwaption.buildAnnuityMapping(VolatilityCubeModel)"
  })
  public void testBuildAnnuityMapping3() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    CashSettledPayerSwaption cashSettledPayerSwaption =
        new CashSettledPayerSwaption(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            Double.NaN,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.SIMPLIFIEDLINEAR);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);
    ForwardCurveInterpolation createForwardCurveFromDiscountFactorsResult =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);
    when(model.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromDiscountFactorsResult);

    // Act
    AnnuityMapping actualBuildAnnuityMappingResult =
        cashSettledPayerSwaption.buildAnnuityMapping(model);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve(Mockito.<String>any());
    verify(model).getForwardCurve("Forward Curve Name");
    verify(discountCurveFromForwardCurve, atLeast(1)).getReferenceDate();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualBuildAnnuityMappingResult instanceof SimplifiedLinearAnnuityMapping);
    assertEquals(0.0d, actualBuildAnnuityMappingResult.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBuildAnnuityMappingResult.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBuildAnnuityMappingResult.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link CashSettledPayerSwaption#buildAnnuityMapping(VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link CashSettledPayerSwaption#buildAnnuityMapping(VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping CashSettledPayerSwaption.buildAnnuityMapping(VolatilityCubeModel)"
  })
  public void testBuildAnnuityMapping4() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    CashSettledPayerSwaption cashSettledPayerSwaption =
        new CashSettledPayerSwaption(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            Double.NaN,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.SIMPLIFIEDLINEAR);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(null);
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act
    AnnuityMapping actualBuildAnnuityMappingResult =
        cashSettledPayerSwaption.buildAnnuityMapping(model);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(discountCurveFromForwardCurve).getReferenceDate();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualBuildAnnuityMappingResult instanceof SimplifiedLinearAnnuityMapping);
    assertEquals(0.0d, actualBuildAnnuityMappingResult.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBuildAnnuityMappingResult.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBuildAnnuityMappingResult.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link CashSettledPayerSwaption#buildAnnuityMapping(VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then calls {@link VolatilityCubeModel#getForwardCurve(String)}.
   * </ul>
   *
   * <p>Method under test: {@link CashSettledPayerSwaption#buildAnnuityMapping(VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping CashSettledPayerSwaption.buildAnnuityMapping(VolatilityCubeModel)"
  })
  public void testBuildAnnuityMapping_thenCallsGetForwardCurve() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    CashSettledPayerSwaption cashSettledPayerSwaption =
        new CashSettledPayerSwaption(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            Double.NaN,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.SIMPLIFIEDLINEAR);

    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        mock(DiscountCurveFromForwardCurve.class);
    when(discountCurveFromForwardCurve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    when(discountCurveFromForwardCurve.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    when(model.getDiscountCurve(Mockito.<String>any())).thenReturn(discountCurveFromForwardCurve);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(model.getForwardCurve(Mockito.<String>any())).thenReturn(forwardCurveFromDiscountCurve);

    // Act
    AnnuityMapping actualBuildAnnuityMappingResult =
        cashSettledPayerSwaption.buildAnnuityMapping(model);

    // Assert
    verify(model, atLeast(1)).getDiscountCurve("3");
    verify(model).getForwardCurve("Forward Curve Name");
    verify(discountCurveFromForwardCurve, atLeast(1)).getReferenceDate();
    verify(discountCurveFromForwardCurve, atLeast(1))
        .getDiscountFactor(isA(AnalyticModel.class), anyDouble());
    assertTrue(actualBuildAnnuityMappingResult instanceof SimplifiedLinearAnnuityMapping);
    assertEquals(0.0d, actualBuildAnnuityMappingResult.getSecondDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBuildAnnuityMappingResult.getFirstDerivative(10.0d), 0.0);
    assertEquals(Double.NaN, actualBuildAnnuityMappingResult.getValue(10.0d), 0.0);
  }
}
