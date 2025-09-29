package net.finmath.marketdata.model.volatility.caplet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromProductOfCurves;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.volatilities.VolatilitySurface;
import net.finmath.marketdata.model.volatility.caplet.tenorconversion.CorrelationProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class CapletVolBootstrappingDiffblueTest {
  /**
   * Test {@link CapletVolBootstrapping#CapletVolBootstrapping(CapVolMarketData, AnalyticModel)}.
   *
   * <ul>
   *   <li>Given {@code USD}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolBootstrapping#CapletVolBootstrapping(CapVolMarketData,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CapletVolBootstrapping.<init>(CapVolMarketData, AnalyticModel)"})
  public void testNewCapletVolBootstrapping_givenUsd() {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.USD);
    AnalyticModelFromCurvesAndVols parsedModel = new AnalyticModelFromCurvesAndVols();

    // Act
    CapletVolBootstrapping actualCapletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, parsedModel);

    // Assert
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData).getIndex();
    assertNull(actualCapletVolBootstrapping.getCapletFixingTimeVectorInYears());
    assertNull(actualCapletVolBootstrapping.getDiscountCurve());
    assertNull(actualCapletVolBootstrapping.getForwardCurve());
    assertSame(parsedModel, actualCapletVolBootstrapping.getParsedModel());
  }

  /**
   * Test {@link CapletVolBootstrapping#CapletVolBootstrapping(CorrelationProvider,
   * CapVolMarketData, AnalyticModel)}.
   *
   * <ul>
   *   <li>Given {@code USD}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolBootstrapping#CapletVolBootstrapping(CorrelationProvider,
   * CapVolMarketData, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolBootstrapping.<init>(CorrelationProvider, CapVolMarketData, AnalyticModel)"
  })
  public void testNewCapletVolBootstrapping_givenUsd2() {
    // Arrange
    CorrelationProvider correlationProvider = mock(CorrelationProvider.class);

    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.USD);
    AnalyticModelFromCurvesAndVols parsedModel = new AnalyticModelFromCurvesAndVols();

    // Act
    CapletVolBootstrapping actualCapletVolBootstrapping =
        new CapletVolBootstrapping(correlationProvider, capVolMarketData, parsedModel);

    // Assert
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData).getIndex();
    assertNull(actualCapletVolBootstrapping.getCapletFixingTimeVectorInYears());
    assertNull(actualCapletVolBootstrapping.getDiscountCurve());
    assertNull(actualCapletVolBootstrapping.getForwardCurve());
    assertSame(parsedModel, actualCapletVolBootstrapping.getParsedModel());
  }

  /**
   * Test {@link CapletVolBootstrapping#CapletVolBootstrapping(CapVolMarketData, AnalyticModel)}.
   *
   * <ul>
   *   <li>Then return CapletFixingTimeVectorInYears is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolBootstrapping#CapletVolBootstrapping(CapVolMarketData,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CapletVolBootstrapping.<init>(CapVolMarketData, AnalyticModel)"})
  public void testNewCapletVolBootstrapping_thenReturnCapletFixingTimeVectorInYearsIsNull() {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);
    AnalyticModelFromCurvesAndVols parsedModel = new AnalyticModelFromCurvesAndVols();

    // Act
    CapletVolBootstrapping actualCapletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, parsedModel);

    // Assert
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData).getIndex();
    assertNull(actualCapletVolBootstrapping.getCapletFixingTimeVectorInYears());
    assertNull(actualCapletVolBootstrapping.getDiscountCurve());
    assertNull(actualCapletVolBootstrapping.getForwardCurve());
    assertSame(parsedModel, actualCapletVolBootstrapping.getParsedModel());
  }

  /**
   * Test {@link CapletVolBootstrapping#CapletVolBootstrapping(CorrelationProvider,
   * CapVolMarketData, AnalyticModel)}.
   *
   * <ul>
   *   <li>Then return CapletFixingTimeVectorInYears is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolBootstrapping#CapletVolBootstrapping(CorrelationProvider,
   * CapVolMarketData, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolBootstrapping.<init>(CorrelationProvider, CapVolMarketData, AnalyticModel)"
  })
  public void testNewCapletVolBootstrapping_thenReturnCapletFixingTimeVectorInYearsIsNull2() {
    // Arrange
    CorrelationProvider correlationProvider = mock(CorrelationProvider.class);

    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);
    AnalyticModelFromCurvesAndVols parsedModel = new AnalyticModelFromCurvesAndVols();

    // Act
    CapletVolBootstrapping actualCapletVolBootstrapping =
        new CapletVolBootstrapping(correlationProvider, capVolMarketData, parsedModel);

    // Assert
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData).getIndex();
    assertNull(actualCapletVolBootstrapping.getCapletFixingTimeVectorInYears());
    assertNull(actualCapletVolBootstrapping.getDiscountCurve());
    assertNull(actualCapletVolBootstrapping.getForwardCurve());
    assertSame(parsedModel, actualCapletVolBootstrapping.getParsedModel());
  }

  /**
   * Test {@link CapletVolBootstrapping#CapletVolBootstrapping(CapVolMarketData, AnalyticModel)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolBootstrapping#CapletVolBootstrapping(CapVolMarketData,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CapletVolBootstrapping.<init>(CapVolMarketData, AnalyticModel)"})
  public void testNewCapletVolBootstrapping_thenThrowIllegalArgumentException() {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getIndex()).thenThrow(new IllegalArgumentException());
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CapletVolBootstrapping(capVolMarketData, new AnalyticModelFromCurvesAndVols()));
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getIndex();
  }

  /**
   * Test {@link CapletVolBootstrapping#CapletVolBootstrapping(CorrelationProvider,
   * CapVolMarketData, AnalyticModel)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolBootstrapping#CapletVolBootstrapping(CorrelationProvider,
   * CapVolMarketData, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolBootstrapping.<init>(CorrelationProvider, CapVolMarketData, AnalyticModel)"
  })
  public void testNewCapletVolBootstrapping_thenThrowIllegalArgumentException2() {
    // Arrange
    CorrelationProvider correlationProvider = mock(CorrelationProvider.class);

    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getIndex()).thenThrow(new IllegalArgumentException());
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CapletVolBootstrapping(
                correlationProvider, capVolMarketData, new AnalyticModelFromCurvesAndVols()));
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getIndex();
  }

  /**
   * Test {@link CapletVolBootstrapping#getCapletVolMatrix()}.
   *
   * <p>Method under test: {@link CapletVolBootstrapping#getCapletVolMatrix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] CapletVolBootstrapping.getCapletVolMatrix()"})
  public void testGetCapletVolMatrix() throws CalculationException {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getNumberOfStrikes()).thenThrow(new IllegalArgumentException());
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(1);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);
    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, new AnalyticModelFromCurvesAndVols());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> capletVolBootstrapping.getCapletVolMatrix());
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData).getMaxExpiryInMonths();
    verify(capVolMarketData).getNumberOfStrikes();
    verify(capVolMarketData).getUnderlyingTenorInMonths();
  }

  /**
   * Test {@link CapletVolBootstrapping#getCapletVolMatrix()}.
   *
   * <p>Method under test: {@link CapletVolBootstrapping#getCapletVolMatrix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] CapletVolBootstrapping.getCapletVolMatrix()"})
  public void testGetCapletVolMatrix2() throws CalculationException {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getRowIndex(anyInt())).thenThrow(new IllegalArgumentException());
    when(capVolMarketData.getExpiryInMonths(anyInt())).thenReturn(1);
    when(capVolMarketData.getNumberOfStrikes()).thenReturn(10);
    when(capVolMarketData.getUnderlyingTenorInMonthsBeforeChange()).thenReturn(1);
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(1);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);
    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, new AnalyticModelFromCurvesAndVols());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> capletVolBootstrapping.getCapletVolMatrix());
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData).getExpiryInMonths(0);
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData, atLeast(1)).getMaxExpiryInMonths();
    verify(capVolMarketData, atLeast(1)).getNumberOfStrikes();
    verify(capVolMarketData).getRowIndex(1);
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonths();
    verify(capVolMarketData).getUnderlyingTenorInMonthsBeforeChange();
  }

  /**
   * Test {@link CapletVolBootstrapping#getCapletVolMatrix()}.
   *
   * <p>Method under test: {@link CapletVolBootstrapping#getCapletVolMatrix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] CapletVolBootstrapping.getCapletVolMatrix()"})
  public void testGetCapletVolMatrix3() throws CalculationException {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getCapVolData(anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());
    when(capVolMarketData.getExpiryInMonths(anyInt())).thenReturn(2);
    when(capVolMarketData.getNumberOfStrikes()).thenReturn(10);
    when(capVolMarketData.getUnderlyingTenorInMonthsBeforeChange()).thenReturn(1);
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(1);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);
    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, new AnalyticModelFromCurvesAndVols());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> capletVolBootstrapping.getCapletVolMatrix());
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getCapVolData(0, 0);
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData).getExpiryInMonths(0);
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData, atLeast(1)).getMaxExpiryInMonths();
    verify(capVolMarketData, atLeast(1)).getNumberOfStrikes();
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonths();
    verify(capVolMarketData).getUnderlyingTenorInMonthsBeforeChange();
  }

  /**
   * Test {@link CapletVolBootstrapping#getCapletVolMatrix()}.
   *
   * <ul>
   *   <li>Given {@link CapVolMarketData} {@link CapVolMarketData#getNumberOfExpiryDates()} return
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolBootstrapping#getCapletVolMatrix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] CapletVolBootstrapping.getCapletVolMatrix()"})
  public void testGetCapletVolMatrix_givenCapVolMarketDataGetNumberOfExpiryDatesReturnOne()
      throws CalculationException {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getNumberOfExpiryDates()).thenReturn(1);
    when(capVolMarketData.getRowIndex(anyInt())).thenReturn(1);
    when(capVolMarketData.getExpiryInMonths(anyInt())).thenReturn(1);
    when(capVolMarketData.getNumberOfStrikes()).thenReturn(10);
    when(capVolMarketData.getUnderlyingTenorInMonthsBeforeChange()).thenReturn(1);
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(1);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);
    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, new AnalyticModelFromCurvesAndVols());

    // Act
    double[][] actualCapletVolMatrix = capletVolBootstrapping.getCapletVolMatrix();

    // Assert
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData, atLeast(1)).getExpiryInMonths(0);
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData, atLeast(1)).getMaxExpiryInMonths();
    verify(capVolMarketData, atLeast(1)).getNumberOfExpiryDates();
    verify(capVolMarketData, atLeast(1)).getNumberOfStrikes();
    verify(capVolMarketData, atLeast(1)).getRowIndex(1);
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonths();
    verify(capVolMarketData).getUnderlyingTenorInMonthsBeforeChange();
    assertEquals(2, actualCapletVolMatrix.length);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualCapletVolMatrix[0],
        0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualCapletVolMatrix[1],
        0.0);
  }

  /**
   * Test {@link CapletVolBootstrapping#getCapletVolMatrix()}.
   *
   * <ul>
   *   <li>Given {@link CapVolMarketData} {@link CapVolMarketData#getUnderlyingTenorInMonths()}
   *       return two.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolBootstrapping#getCapletVolMatrix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] CapletVolBootstrapping.getCapletVolMatrix()"})
  public void testGetCapletVolMatrix_givenCapVolMarketDataGetUnderlyingTenorInMonthsReturnTwo()
      throws CalculationException {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getNumberOfStrikes()).thenReturn(10);
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(2);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);
    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, new AnalyticModelFromCurvesAndVols());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> capletVolBootstrapping.getCapletVolMatrix());
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData, atLeast(1)).getMaxExpiryInMonths();
    verify(capVolMarketData).getNumberOfStrikes();
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonths();
  }

  /**
   * Test {@link CapletVolBootstrapping#getCapletVolMatrix()}.
   *
   * <ul>
   *   <li>Then calls {@link CapVolMarketData#getIndexBeforeChange()}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolBootstrapping#getCapletVolMatrix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] CapletVolBootstrapping.getCapletVolMatrix()"})
  public void testGetCapletVolMatrix_thenCallsGetIndexBeforeChange() throws CalculationException {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getIndexBeforeChange()).thenThrow(new IllegalArgumentException());
    when(capVolMarketData.getExpiryInMonths(anyInt())).thenReturn(2);
    when(capVolMarketData.getNumberOfStrikes()).thenReturn(10);
    when(capVolMarketData.getTenorChangeTimeInMonths()).thenReturn(1);
    when(capVolMarketData.getUnderlyingTenorInMonthsBeforeChange()).thenReturn(2);
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(1);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);
    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, new AnalyticModelFromCurvesAndVols());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> capletVolBootstrapping.getCapletVolMatrix());
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData).getExpiryInMonths(0);
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData).getIndexBeforeChange();
    verify(capVolMarketData, atLeast(1)).getMaxExpiryInMonths();
    verify(capVolMarketData, atLeast(1)).getNumberOfStrikes();
    verify(capVolMarketData).getTenorChangeTimeInMonths();
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonths();
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonthsBeforeChange();
  }

  /**
   * Test {@link CapletVolBootstrapping#getCapletVolMatrix()}.
   *
   * <ul>
   *   <li>Then return first element is array of {@code double} with ten and ten.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolBootstrapping#getCapletVolMatrix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] CapletVolBootstrapping.getCapletVolMatrix()"})
  public void testGetCapletVolMatrix_thenReturnFirstElementIsArrayOfDoubleWithTenAndTen()
      throws CalculationException {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getCapVolData(anyInt(), anyInt())).thenReturn(10.0d);
    when(capVolMarketData.getNumberOfExpiryDates()).thenReturn(10);
    when(capVolMarketData.getRowIndex(anyInt())).thenReturn(1);
    when(capVolMarketData.getExpiryInMonths(anyInt())).thenReturn(2);
    when(capVolMarketData.getNumberOfStrikes()).thenReturn(10);
    when(capVolMarketData.getUnderlyingTenorInMonthsBeforeChange()).thenReturn(1);
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(1);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);
    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, new AnalyticModelFromCurvesAndVols());

    // Act
    double[][] actualCapletVolMatrix = capletVolBootstrapping.getCapletVolMatrix();

    // Assert
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData, atLeast(1)).getCapVolData(eq(0), anyInt());
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData, atLeast(1)).getExpiryInMonths(anyInt());
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData, atLeast(1)).getMaxExpiryInMonths();
    verify(capVolMarketData, atLeast(1)).getNumberOfExpiryDates();
    verify(capVolMarketData, atLeast(1)).getNumberOfStrikes();
    verify(capVolMarketData, atLeast(1)).getRowIndex(2);
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonths();
    verify(capVolMarketData).getUnderlyingTenorInMonthsBeforeChange();
    assertEquals(2, actualCapletVolMatrix.length);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualCapletVolMatrix[1],
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCapletVolMatrix[0],
        0.0);
  }

  /**
   * Test {@link CapletVolBootstrapping#getCapletVolMatrix()}.
   *
   * <ul>
   *   <li>Then return first element is array of {@code double} with zero and zero.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolBootstrapping#getCapletVolMatrix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] CapletVolBootstrapping.getCapletVolMatrix()"})
  public void testGetCapletVolMatrix_thenReturnFirstElementIsArrayOfDoubleWithZeroAndZero()
      throws CalculationException {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getNumberOfExpiryDates()).thenReturn(10);
    when(capVolMarketData.getRowIndex(anyInt())).thenReturn(1);
    when(capVolMarketData.getExpiryInMonths(anyInt())).thenReturn(1);
    when(capVolMarketData.getNumberOfStrikes()).thenReturn(10);
    when(capVolMarketData.getUnderlyingTenorInMonthsBeforeChange()).thenReturn(1);
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(1);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);
    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, new AnalyticModelFromCurvesAndVols());

    // Act
    double[][] actualCapletVolMatrix = capletVolBootstrapping.getCapletVolMatrix();

    // Assert
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData, atLeast(1)).getExpiryInMonths(anyInt());
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData, atLeast(1)).getMaxExpiryInMonths();
    verify(capVolMarketData, atLeast(1)).getNumberOfExpiryDates();
    verify(capVolMarketData, atLeast(1)).getNumberOfStrikes();
    verify(capVolMarketData, atLeast(1)).getRowIndex(1);
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonths();
    verify(capVolMarketData).getUnderlyingTenorInMonthsBeforeChange();
    assertEquals(2, actualCapletVolMatrix.length);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualCapletVolMatrix[0],
        0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualCapletVolMatrix[1],
        0.0);
  }

  /**
   * Test {@link CapletVolBootstrapping#calculateCapVolsFromCapletVols(double[][])}.
   *
   * <p>Method under test: {@link CapletVolBootstrapping#calculateCapVolsFromCapletVols(double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] CapletVolBootstrapping.calculateCapVolsFromCapletVols(double[][])"
  })
  public void testCalculateCapVolsFromCapletVols() {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getExpiryInMonths(anyInt())).thenThrow(new IllegalArgumentException());
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getNumberOfExpiryDates()).thenReturn(10);
    when(capVolMarketData.getNumberOfStrikes()).thenReturn(10);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(1);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);
    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, new AnalyticModelFromCurvesAndVols());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolBootstrapping.calculateCapVolsFromCapletVols(
                new double[][] {new double[] {10.0d, 0.1d, 10.0d, 0.1d}}));
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData).getExpiryInMonths(0);
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData).getMaxExpiryInMonths();
    verify(capVolMarketData).getNumberOfExpiryDates();
    verify(capVolMarketData, atLeast(1)).getNumberOfStrikes();
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonths();
  }

  /**
   * Test {@link CapletVolBootstrapping#calculateCapVolsFromCapletVols(double[][])}.
   *
   * <p>Method under test: {@link CapletVolBootstrapping#calculateCapVolsFromCapletVols(double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] CapletVolBootstrapping.calculateCapVolsFromCapletVols(double[][])"
  })
  public void testCalculateCapVolsFromCapletVols2() {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getStrikeVector()).thenThrow(new IllegalArgumentException());
    when(capVolMarketData.getExpiryInMonths(anyInt())).thenReturn(1);
    when(capVolMarketData.getRowIndex(anyInt())).thenReturn(1);
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getNumberOfExpiryDates()).thenReturn(10);
    when(capVolMarketData.getNumberOfStrikes()).thenReturn(10);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(1);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);

    AnalyticModelFromCurvesAndVols parsedModel = mock(AnalyticModelFromCurvesAndVols.class);
    when(parsedModel.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(parsedModel.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);

    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, parsedModel);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolBootstrapping.calculateCapVolsFromCapletVols(
                new double[][] {new double[] {10.0d, 0.1d, 10.0d, 0.1d}}));
    verify(parsedModel).getDiscountCurve("EUR_3");
    verify(parsedModel).getForwardCurve("Forward_EUR_Index");
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData, atLeast(1)).getExpiryInMonths(anyInt());
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData).getMaxExpiryInMonths();
    verify(capVolMarketData, atLeast(1)).getNumberOfExpiryDates();
    verify(capVolMarketData, atLeast(1)).getNumberOfStrikes();
    verify(capVolMarketData, atLeast(1)).getRowIndex(1);
    verify(capVolMarketData).getStrikeVector();
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonths();
  }

  /**
   * Test {@link CapletVolBootstrapping#calculateCapVolsFromCapletVols(double[][])}.
   *
   * <p>Method under test: {@link CapletVolBootstrapping#calculateCapVolsFromCapletVols(double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] CapletVolBootstrapping.calculateCapVolsFromCapletVols(double[][])"
  })
  public void testCalculateCapVolsFromCapletVols3() {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getStrike(anyInt())).thenThrow(new IllegalArgumentException());
    when(capVolMarketData.getStrikeVector()).thenReturn(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    when(capVolMarketData.getExpiryInMonths(anyInt())).thenReturn(1);
    when(capVolMarketData.getRowIndex(anyInt())).thenReturn(1);
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getNumberOfExpiryDates()).thenReturn(10);
    when(capVolMarketData.getNumberOfStrikes()).thenReturn(10);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(1);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);

    AnalyticModelFromCurvesAndVols parsedModel = mock(AnalyticModelFromCurvesAndVols.class);
    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves(
            "Cap volatility surface", LocalDate.of(1970, 1, 1), "Cap volatility surface");
    when(parsedModel.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(discountCurveFromProductOfCurves);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(parsedModel.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);

    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, parsedModel);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolBootstrapping.calculateCapVolsFromCapletVols(
                new double[][] {new double[] {10.0d, 0.1d, 10.0d, 0.1d}}));
    verify(parsedModel).getDiscountCurve("EUR_3");
    verify(parsedModel).getForwardCurve("Forward_EUR_Index");
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData, atLeast(1)).getExpiryInMonths(anyInt());
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData).getMaxExpiryInMonths();
    verify(capVolMarketData, atLeast(1)).getNumberOfExpiryDates();
    verify(capVolMarketData, atLeast(1)).getNumberOfStrikes();
    verify(capVolMarketData, atLeast(1)).getRowIndex(1);
    verify(capVolMarketData).getStrike(0);
    verify(capVolMarketData).getStrikeVector();
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonths();
  }

  /**
   * Test {@link CapletVolBootstrapping#calculateCapVolsFromCapletVols(double[][])}.
   *
   * <p>Method under test: {@link CapletVolBootstrapping#calculateCapVolsFromCapletVols(double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] CapletVolBootstrapping.calculateCapVolsFromCapletVols(double[][])"
  })
  public void testCalculateCapVolsFromCapletVols4() {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getNumberOfExpiryDates()).thenReturn(10);
    when(capVolMarketData.getNumberOfStrikes()).thenReturn(10);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(2);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);

    AnalyticModelFromCurvesAndVols parsedModel = mock(AnalyticModelFromCurvesAndVols.class);
    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves(
            "Cap volatility surface", LocalDate.of(1970, 1, 1), "Cap volatility surface");
    when(parsedModel.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(discountCurveFromProductOfCurves);
    when(parsedModel.getForwardCurve(Mockito.<String>any()))
        .thenReturn(mock(ForwardCurveFromDiscountCurve.class));

    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, parsedModel);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolBootstrapping.calculateCapVolsFromCapletVols(
                new double[][] {new double[] {10.0d, 0.1d, 10.0d, 0.1d}}));
    verify(parsedModel).getDiscountCurve("EUR_3");
    verify(parsedModel).getForwardCurve("Forward_EUR_Index");
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData).getMaxExpiryInMonths();
    verify(capVolMarketData).getNumberOfExpiryDates();
    verify(capVolMarketData).getNumberOfStrikes();
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonths();
  }

  /**
   * Test {@link CapletVolBootstrapping#calculateCapVolsFromCapletVols(double[][])}.
   *
   * <ul>
   *   <li>Then calls {@link
   *       AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(VolatilitySurface[])}.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolBootstrapping#calculateCapVolsFromCapletVols(double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] CapletVolBootstrapping.calculateCapVolsFromCapletVols(double[][])"
  })
  public void testCalculateCapVolsFromCapletVols_thenCallsAddVolatilitySurfaces() {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getShift()).thenReturn(10.0d);
    when(capVolMarketData.getStrike(anyInt())).thenReturn(10.0d);
    when(capVolMarketData.getStrikeVector()).thenReturn(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    when(capVolMarketData.getExpiryInMonths(anyInt())).thenReturn(1);
    when(capVolMarketData.getRowIndex(anyInt())).thenReturn(1);
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getNumberOfExpiryDates()).thenReturn(10);
    when(capVolMarketData.getNumberOfStrikes()).thenReturn(10);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(1);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);

    AnalyticModelFromCurvesAndVols parsedModel = mock(AnalyticModelFromCurvesAndVols.class);
    when(parsedModel.addVolatilitySurfaces(isA(VolatilitySurface[].class)))
        .thenThrow(new IllegalArgumentException());
    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves(
            "Cap volatility surface", LocalDate.of(1970, 1, 1), "Cap volatility surface");
    when(parsedModel.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(discountCurveFromProductOfCurves);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(parsedModel.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);

    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, parsedModel);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolBootstrapping.calculateCapVolsFromCapletVols(
                new double[][] {new double[] {10.0d, 0.1d, 10.0d, 0.1d}}));
    verify(parsedModel).addVolatilitySurfaces(isA(VolatilitySurface[].class));
    verify(parsedModel).getDiscountCurve("EUR_3");
    verify(parsedModel).getForwardCurve("Forward_EUR_Index");
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData, atLeast(1)).getExpiryInMonths(anyInt());
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData).getMaxExpiryInMonths();
    verify(capVolMarketData, atLeast(1)).getNumberOfExpiryDates();
    verify(capVolMarketData, atLeast(1)).getNumberOfStrikes();
    verify(capVolMarketData, atLeast(1)).getRowIndex(1);
    verify(capVolMarketData).getShift();
    verify(capVolMarketData).getStrike(0);
    verify(capVolMarketData).getStrikeVector();
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonths();
  }

  /**
   * Test {@link CapletVolBootstrapping#calculateCapVolsFromCapletVols(double[][])}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolBootstrapping#calculateCapVolsFromCapletVols(double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] CapletVolBootstrapping.calculateCapVolsFromCapletVols(double[][])"
  })
  public void testCalculateCapVolsFromCapletVols_thenReturnArrayLengthIsOne() {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getExpiryInMonths(anyInt())).thenReturn(1);
    when(capVolMarketData.getRowIndex(anyInt())).thenReturn(1);
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getNumberOfExpiryDates()).thenReturn(1);
    when(capVolMarketData.getNumberOfStrikes()).thenReturn(10);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(1);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);

    AnalyticModelFromCurvesAndVols parsedModel = mock(AnalyticModelFromCurvesAndVols.class);
    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves(
            "Cap volatility surface", LocalDate.of(1970, 1, 1), "Cap volatility surface");
    when(parsedModel.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(discountCurveFromProductOfCurves);
    when(parsedModel.getForwardCurve(Mockito.<String>any()))
        .thenReturn(mock(ForwardCurveFromDiscountCurve.class));

    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, parsedModel);

    // Act
    double[][] actualCalculateCapVolsFromCapletVolsResult =
        capletVolBootstrapping.calculateCapVolsFromCapletVols(
            new double[][] {
              new double[] {
                12.0d, 10.0d, 12.0d, 10.0d, 12.0d, 10.0d, 12.0d, 10.0d, 12.0d, 10.0d, 12.0d, 10.0d
              }
            });

    // Assert
    verify(parsedModel).getDiscountCurve("EUR_3");
    verify(parsedModel).getForwardCurve("Forward_EUR_Index");
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData, atLeast(1)).getExpiryInMonths(0);
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData).getMaxExpiryInMonths();
    verify(capVolMarketData, atLeast(1)).getNumberOfExpiryDates();
    verify(capVolMarketData, atLeast(1)).getNumberOfStrikes();
    verify(capVolMarketData, atLeast(1)).getRowIndex(1);
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonths();
    assertEquals(1, actualCalculateCapVolsFromCapletVolsResult.length);
    assertArrayEquals(
        new double[] {12.0d, 10.0d, 12.0d, 10.0d, 12.0d, 10.0d, 12.0d, 10.0d, 12.0d, 10.0d},
        actualCalculateCapVolsFromCapletVolsResult[0],
        0.0);
  }

  /**
   * Test {@link CapletVolBootstrapping#calculateCapVolsFromCapletVols(double[][])}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolBootstrapping#calculateCapVolsFromCapletVols(double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] CapletVolBootstrapping.calculateCapVolsFromCapletVols(double[][])"
  })
  public void testCalculateCapVolsFromCapletVols_thenReturnArrayLengthIsZero() {
    // Arrange
    CapVolMarketData capVolMarketData = mock(CapVolMarketData.class);
    when(capVolMarketData.getExpiryInMonths(anyInt())).thenReturn(1);
    when(capVolMarketData.getMaxExpiryInMonths()).thenReturn(3);
    when(capVolMarketData.getNumberOfExpiryDates()).thenReturn(0);
    when(capVolMarketData.getNumberOfStrikes()).thenReturn(10);
    when(capVolMarketData.getUnderlyingTenorInMonths()).thenReturn(1);
    when(capVolMarketData.getDiscountIndex()).thenReturn("3");
    when(capVolMarketData.getIndex()).thenReturn("Index");
    when(capVolMarketData.getCapTenorStructure()).thenReturn(CapTenorStructure.EUR);

    AnalyticModelFromCurvesAndVols parsedModel = mock(AnalyticModelFromCurvesAndVols.class);
    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves(
            "Cap volatility surface", LocalDate.of(1970, 1, 1), "Cap volatility surface");
    when(parsedModel.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(discountCurveFromProductOfCurves);
    when(parsedModel.getForwardCurve(Mockito.<String>any()))
        .thenReturn(mock(ForwardCurveFromDiscountCurve.class));

    CapletVolBootstrapping capletVolBootstrapping =
        new CapletVolBootstrapping(capVolMarketData, parsedModel);

    // Act
    double[][] actualCalculateCapVolsFromCapletVolsResult =
        capletVolBootstrapping.calculateCapVolsFromCapletVols(
            new double[][] {new double[] {10.0d, 0.1d, 10.0d, 0.1d}});

    // Assert
    verify(parsedModel).getDiscountCurve("EUR_3");
    verify(parsedModel).getForwardCurve("Forward_EUR_Index");
    verify(capVolMarketData).getCapTenorStructure();
    verify(capVolMarketData).getDiscountIndex();
    verify(capVolMarketData, atLeast(1)).getExpiryInMonths(0);
    verify(capVolMarketData).getIndex();
    verify(capVolMarketData).getMaxExpiryInMonths();
    verify(capVolMarketData, atLeast(1)).getNumberOfExpiryDates();
    verify(capVolMarketData, atLeast(1)).getNumberOfStrikes();
    verify(capVolMarketData, atLeast(1)).getUnderlyingTenorInMonths();
    assertEquals(0, actualCalculateCapVolsFromCapletVolsResult.length);
  }
}
