package net.finmath.singleswaprate.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping.AnnuityMappingType;
import net.finmath.singleswaprate.annuitymapping.SimplifiedLinearAnnuityMapping;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import net.finmath.singleswaprate.model.volatilities.SABRVolatilityCubeSingleSmile;
import net.finmath.singleswaprate.model.volatilities.StaticVolatilityCube;
import net.finmath.time.Period;
import net.finmath.time.RegularSchedule;
import net.finmath.time.ScheduleFromPeriods;
import net.finmath.time.TenorFromArray;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AbstractSingleSwapRateProductDiffblueTest {
  /**
   * Test {@link AbstractSingleSwapRateProduct#setIntegrationParameters(double, double, int)}.
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#setIntegrationParameters(double,
   * double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractSingleSwapRateProduct.setIntegrationParameters(double, double, int)"
  })
  public void testSetIntegrationParameters() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    // Act
    annuityDummyProduct.setIntegrationParameters(10.0d, 10.0d, 10);

    // Assert
    assertEquals(10, annuityDummyProduct.getIntegrationNumberOfEvaluationPoints());
    assertEquals(10.0d, annuityDummyProduct.getIntegrationLowerBound(), 0.0);
    assertEquals(10.0d, annuityDummyProduct.getIntegrationUpperBound(), 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#getIntegrationLowerBound()}.
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#getIntegrationLowerBound()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractSingleSwapRateProduct.getIntegrationLowerBound()"})
  public void testGetIntegrationLowerBound() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    // Act and Assert
    assertEquals(-0.15d, annuityDummyProduct.getIntegrationLowerBound(), 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#getIntegrationUpperBound()}.
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#getIntegrationUpperBound()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractSingleSwapRateProduct.getIntegrationUpperBound()"})
  public void testGetIntegrationUpperBound() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    // Act and Assert
    assertEquals(0.15d, annuityDummyProduct.getIntegrationUpperBound(), 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#getIntegrationNumberOfEvaluationPoints()}.
   *
   * <p>Method under test: {@link
   * AbstractSingleSwapRateProduct#getIntegrationNumberOfEvaluationPoints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AbstractSingleSwapRateProduct.getIntegrationNumberOfEvaluationPoints()"})
  public void testGetIntegrationNumberOfEvaluationPoints() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    // Act and Assert
    assertEquals(500, annuityDummyProduct.getIntegrationNumberOfEvaluationPoints());
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#getValue(double, AnnuityMapping,
   * VolatilityCubeModel)} with {@code double}, {@code AnnuityMapping}, {@code VolatilityCubeModel}.
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#getValue(double, AnnuityMapping,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.getValue(double, AnnuityMapping, VolatilityCubeModel)"
  })
  public void testGetValueWithDoubleAnnuityMappingVolatilityCubeModel() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    RegularSchedule fixSchedule = new RegularSchedule(timeDiscretization);
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            annuityDummyProduct.getValue(
                10.0d, annuityMapping, new AnalyticModelWithVolatilityCubes()));
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valuePut(double, VolatilityCubeModel, double)}.
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valuePut(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valuePut(double, VolatilityCubeModel, double)"
  })
  public void testValuePut() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(sabrVolatilityCubeSingleSmile);

    // Act
    double actualValuePutResult = annuityDummyProduct.valuePut(10.0d, model, 10.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualValuePutResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valuePut(double, VolatilityCubeModel, double)}.
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valuePut(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valuePut(double, VolatilityCubeModel, double)"
  })
  public void testValuePut2() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(-0.5d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualValuePutResult = annuityDummyProduct.valuePut(10.0d, model, 10.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(0.0d, actualValuePutResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valuePut(double, VolatilityCubeModel, double)}.
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valuePut(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valuePut(double, VolatilityCubeModel, double)"
  })
  public void testValuePut3() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 1.0d, 1.0d, 1.0E-10d, 1.0d, 1.0d, 1.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(sabrVolatilityCubeSingleSmile);

    // Act
    double actualValuePutResult = annuityDummyProduct.valuePut(10.0d, model, 10.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualValuePutResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valuePut(double, VolatilityCubeModel, double)}.
   *
   * <ul>
   *   <li>Given {@link DayCountConvention_30E_360#DayCountConvention_30E_360(boolean)} with
   *       is30Eplus360 is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valuePut(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valuePut(double, VolatilityCubeModel, double)"
  })
  public void testValuePut_givenDayCountConvention_30E_360WithIs30Eplus360IsTrue() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualValuePutResult = annuityDummyProduct.valuePut(1.0d, model, 10.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(0.0d, actualValuePutResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valuePut(double, VolatilityCubeModel, double)}.
   *
   * <ul>
   *   <li>Then return {@code 37.00594365629569}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valuePut(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valuePut(double, VolatilityCubeModel, double)"
  })
  public void testValuePut_thenReturn3700594365629569() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(sabrVolatilityCubeSingleSmile);

    // Act
    double actualValuePutResult = annuityDummyProduct.valuePut(10.0d, model, 10.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(37.00594365629569d, actualValuePutResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valuePut(double, VolatilityCubeModel, double)}.
   *
   * <ul>
   *   <li>Then return {@code 12.615662610100802}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valuePut(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valuePut(double, VolatilityCubeModel, double)"
  })
  public void testValuePut_thenReturn12615662610100802() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualValuePutResult = annuityDummyProduct.valuePut(10.0d, model, 10.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(12.615662610100802d, actualValuePutResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valuePut(double, VolatilityCubeModel, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 8.62317587361213}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valuePut(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valuePut(double, VolatilityCubeModel, double)"
  })
  public void testValuePut_whenOne_thenReturn862317587361213() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualValuePutResult = annuityDummyProduct.valuePut(1.0d, model, 10.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(8.62317587361213d, actualValuePutResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valuePut(double, VolatilityCubeModel, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 17.623175873612126}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valuePut(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valuePut(double, VolatilityCubeModel, double)"
  })
  public void testValuePut_whenOne_thenReturn17623175873612126() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualValuePutResult = annuityDummyProduct.valuePut(10.0d, model, 1.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(17.623175873612126d, actualValuePutResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valueCall(double, VolatilityCubeModel, double)}.
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valueCall(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valueCall(double, VolatilityCubeModel, double)"
  })
  public void testValueCall() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(sabrVolatilityCubeSingleSmile);

    // Act
    double actualValueCallResult = annuityDummyProduct.valueCall(10.0d, model, 10.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualValueCallResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valueCall(double, VolatilityCubeModel, double)}.
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valueCall(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valueCall(double, VolatilityCubeModel, double)"
  })
  public void testValueCall2() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 1.0d, 1.0d, 1.0E-10d, 1.0d, 1.0d, 1.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(sabrVolatilityCubeSingleSmile);

    // Act
    double actualValueCallResult = annuityDummyProduct.valueCall(10.0d, model, 10.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(Double.NaN, actualValueCallResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valueCall(double, VolatilityCubeModel, double)}.
   *
   * <ul>
   *   <li>Then return {@code 37.00594365629569}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valueCall(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valueCall(double, VolatilityCubeModel, double)"
  })
  public void testValueCall_thenReturn3700594365629569() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(sabrVolatilityCubeSingleSmile);

    // Act
    double actualValueCallResult = annuityDummyProduct.valueCall(10.0d, model, 10.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(37.00594365629569d, actualValueCallResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valueCall(double, VolatilityCubeModel, double)}.
   *
   * <ul>
   *   <li>Then return {@code 12.615662610100802}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valueCall(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valueCall(double, VolatilityCubeModel, double)"
  })
  public void testValueCall_thenReturn12615662610100802() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualValueCallResult = annuityDummyProduct.valueCall(10.0d, model, 10.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(12.615662610100802d, actualValueCallResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valueCall(double, VolatilityCubeModel, double)}.
   *
   * <ul>
   *   <li>Then return nine.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valueCall(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valueCall(double, VolatilityCubeModel, double)"
  })
  public void testValueCall_thenReturnNine() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DayCountConvention_30E_360 daycountconvention = new DayCountConvention_30E_360(true);
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    ScheduleFromPeriods fixSchedule =
        new ScheduleFromPeriods(referenceDate, daycountconvention, period);
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualValueCallResult = annuityDummyProduct.valueCall(1.0d, model, 10.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(9.0d, actualValueCallResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valueCall(double, VolatilityCubeModel, double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valueCall(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valueCall(double, VolatilityCubeModel, double)"
  })
  public void testValueCall_thenReturnZero() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(-0.5d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualValueCallResult = annuityDummyProduct.valueCall(10.0d, model, 10.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(0.0d, actualValueCallResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valueCall(double, VolatilityCubeModel, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 17.62317587361213}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valueCall(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valueCall(double, VolatilityCubeModel, double)"
  })
  public void testValueCall_whenOne_thenReturn1762317587361213() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualValueCallResult = annuityDummyProduct.valueCall(1.0d, model, 10.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(17.62317587361213d, actualValueCallResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#valueCall(double, VolatilityCubeModel, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 8.623175873612128}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#valueCall(double,
   * VolatilityCubeModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractSingleSwapRateProduct.valueCall(double, VolatilityCubeModel, double)"
  })
  public void testValueCall_whenOne_thenReturn8623175873612128() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualValueCallResult = annuityDummyProduct.valueCall(10.0d, model, 1.0d);

    // Assert
    verify(model).getVolatilityCube("Volatility Cube Name");
    assertEquals(8.623175873612128d, actualValueCallResult, 0.0);
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#getFixSchedule()}.
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#getFixSchedule()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.time.Schedule AbstractSingleSwapRateProduct.getFixSchedule()"})
  public void testGetFixSchedule() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    // Act and Assert
    assertSame(fixSchedule, annuityDummyProduct.getFixSchedule());
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#getFloatSchedule()}.
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#getFloatSchedule()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.time.Schedule AbstractSingleSwapRateProduct.getFloatSchedule()"})
  public void testGetFloatSchedule() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            floatSchedule,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    // Act and Assert
    assertSame(floatSchedule, annuityDummyProduct.getFloatSchedule());
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#getDiscountCurveName()}.
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#getDiscountCurveName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSingleSwapRateProduct.getDiscountCurveName()"})
  public void testGetDiscountCurveName() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    // Act and Assert
    assertEquals("3", annuityDummyProduct.getDiscountCurveName());
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#getForwardCurveName()}.
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#getForwardCurveName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSingleSwapRateProduct.getForwardCurveName()"})
  public void testGetForwardCurveName() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    // Act and Assert
    assertEquals("Forward Curve Name", annuityDummyProduct.getForwardCurveName());
  }

  /**
   * Test {@link AbstractSingleSwapRateProduct#getVolatilityCubeName()}.
   *
   * <p>Method under test: {@link AbstractSingleSwapRateProduct#getVolatilityCubeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSingleSwapRateProduct.getVolatilityCubeName()"})
  public void testGetVolatilityCubeName() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    // Act and Assert
    assertEquals("Volatility Cube Name", annuityDummyProduct.getVolatilityCubeName());
  }
}
