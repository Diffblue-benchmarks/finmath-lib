package net.finmath.marketdata.model.volatility.caplet.tenorconversion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.volatility.caplet.CapVolMarketData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CorrelationProviderTenorBasisDiffblueTest {
  @Mock private CapVolMarketData capVolMarketData;

  @InjectMocks private CorrelationProviderTenorBasis correlationProviderTenorBasis;

  /**
   * Test {@link CorrelationProviderTenorBasis#get6MCorrelation(double, double, AnalyticModel)}.
   *
   * <p>Method under test: {@link CorrelationProviderTenorBasis#get6MCorrelation(double, double,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CorrelationProviderTenorBasis.get6MCorrelation(double, double, AnalyticModel)"
  })
  public void testGet6MCorrelation() throws CalculationException {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        correlationProviderTenorBasis.get6MCorrelation(
            10.0d, 10.0d, new AnalyticModelFromCurvesAndVols()),
        0.0);
  }

  /**
   * Test {@link CorrelationProviderTenorBasis#get3MCorrelation(double, double, AnalyticModel)}.
   *
   * <p>Method under test: {@link CorrelationProviderTenorBasis#get3MCorrelation(double, double,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CorrelationProviderTenorBasis.get3MCorrelation(double, double, AnalyticModel)"
  })
  public void testGet3MCorrelation() throws CalculationException {
    // Arrange
    when(capVolMarketData.getMaxExpiryInMonths()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            correlationProviderTenorBasis.get3MCorrelation(
                10.0d, 10.0d, new AnalyticModelFromCurvesAndVols()));
    verify(capVolMarketData).getMaxExpiryInMonths();
  }

  /**
   * Test {@link CorrelationProviderTenorBasis#get1MCorrelation(double, double, AnalyticModel)}.
   *
   * <p>Method under test: {@link CorrelationProviderTenorBasis#get1MCorrelation(double, double,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CorrelationProviderTenorBasis.get1MCorrelation(double, double, AnalyticModel)"
  })
  public void testGet1MCorrelation() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        correlationProviderTenorBasis.get1MCorrelation(
            10.0d, 10.0d, new AnalyticModelFromCurvesAndVols()),
        0.0);
  }

  /**
   * Test {@link CorrelationProviderTenorBasis#getiCap3MCapletVolMatrix()}.
   *
   * <p>Method under test: {@link CorrelationProviderTenorBasis#getiCap3MCapletVolMatrix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] CorrelationProviderTenorBasis.getiCap3MCapletVolMatrix()"})
  public void testGetiCap3MCapletVolMatrix() {
    // Arrange, Act and Assert
    assertNull(correlationProviderTenorBasis.getiCap3MCapletVolMatrix());
  }

  /**
   * Test {@link CorrelationProviderTenorBasis#getiCap6MCapletVolMatrix()}.
   *
   * <p>Method under test: {@link CorrelationProviderTenorBasis#getiCap6MCapletVolMatrix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] CorrelationProviderTenorBasis.getiCap6MCapletVolMatrix()"})
  public void testGetiCap6MCapletVolMatrix() {
    // Arrange, Act and Assert
    assertNull(correlationProviderTenorBasis.getiCap6MCapletVolMatrix());
  }

  /**
   * Test {@link CorrelationProviderTenorBasis#getCorrelation(int, double, double, AnalyticModel,
   * String)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CorrelationProviderTenorBasis#getCorrelation(int, double, double,
   * AnalyticModel, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CorrelationProviderTenorBasis.getCorrelation(int, double, double, AnalyticModel, String)"
  })
  public void testGetCorrelation_thenThrowIllegalArgumentException() throws CalculationException {
    // Arrange
    when(capVolMarketData.getMaxExpiryInMonths()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            correlationProviderTenorBasis.getCorrelation(
                3, 10.0d, 10.0d, new AnalyticModelFromCurvesAndVols(), "3"));
    verify(capVolMarketData).getMaxExpiryInMonths();
  }

  /**
   * Test {@link CorrelationProviderTenorBasis#getCorrelation(int, double, double, AnalyticModel,
   * String)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link CorrelationProviderTenorBasis#getCorrelation(int, double, double,
   * AnalyticModel, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CorrelationProviderTenorBasis.getCorrelation(int, double, double, AnalyticModel, String)"
  })
  public void testGetCorrelation_whenOne_thenReturnZero() throws CalculationException {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        correlationProviderTenorBasis.getCorrelation(
            1, 10.0d, 10.0d, new AnalyticModelFromCurvesAndVols(), "3"),
        0.0);
  }

  /**
   * Test {@link CorrelationProviderTenorBasis#getCorrelation(int, double, double, AnalyticModel,
   * String)}.
   *
   * <ul>
   *   <li>When six.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link CorrelationProviderTenorBasis#getCorrelation(int, double, double,
   * AnalyticModel, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CorrelationProviderTenorBasis.getCorrelation(int, double, double, AnalyticModel, String)"
  })
  public void testGetCorrelation_whenSix_thenReturnZero() throws CalculationException {
    // Arrange, Act and Assert
    assertEquals(
        0.0d,
        correlationProviderTenorBasis.getCorrelation(
            6, 10.0d, 10.0d, new AnalyticModelFromCurvesAndVols(), "3"),
        0.0);
  }
}
