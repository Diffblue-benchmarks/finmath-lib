package net.finmath.montecarlo.hybridassetinterestrate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.interestrate.LIBORModelMonteCarloSimulationModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel.Scheme;
import net.finmath.montecarlo.process.MonteCarloProcessFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CrossCurrencyLIBORMarketModelFromModelsDiffblueTest {
  /**
   * Test {@link
   * CrossCurrencyLIBORMarketModelFromModels#CrossCurrencyLIBORMarketModelFromModels(String, Map,
   * Map)}.
   *
   * <p>Method under test: {@link
   * CrossCurrencyLIBORMarketModelFromModels#CrossCurrencyLIBORMarketModelFromModels(String, Map,
   * Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CrossCurrencyLIBORMarketModelFromModels.<init>(String, Map, Map)"})
  public void testNewCrossCurrencyLIBORMarketModelFromModels() {
    // Arrange
    HashMap<String, LIBORModelMonteCarloSimulationModel> interestRatesModels = new HashMap<>();

    // Act
    CrossCurrencyLIBORMarketModelFromModels actualCrossCurrencyLIBORMarketModelFromModels =
        new CrossCurrencyLIBORMarketModelFromModels(
            "Base Model", interestRatesModels, new HashMap<>());

    // Assert
    assertNull(actualCrossCurrencyLIBORMarketModelFromModels.getReferenceDate());
    assertNull(actualCrossCurrencyLIBORMarketModelFromModels.getBaseModel());
  }

  /**
   * Test {@link CrossCurrencyLIBORMarketModelFromModels#getBaseModel()}.
   *
   * <p>Method under test: {@link CrossCurrencyLIBORMarketModelFromModels#getBaseModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORModelMonteCarloSimulationModel CrossCurrencyLIBORMarketModelFromModels.getBaseModel()"
  })
  public void testGetBaseModel() {
    // Arrange
    HashMap<String, LIBORModelMonteCarloSimulationModel> interestRatesModels = new HashMap<>();
    CrossCurrencyLIBORMarketModelFromModels crossCurrencyLIBORMarketModelFromModels =
        new CrossCurrencyLIBORMarketModelFromModels(
            "Base Model", interestRatesModels, new HashMap<>());

    // Act and Assert
    assertNull(crossCurrencyLIBORMarketModelFromModels.getBaseModel());
  }

  /**
   * Test {@link CrossCurrencyLIBORMarketModelFromModels#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link
   * CrossCurrencyLIBORMarketModelFromModels#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.montecarlo.MonteCarloSimulationModel CrossCurrencyLIBORMarketModelFromModels.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData() throws CalculationException {
    // Arrange
    HashMap<String, LIBORModelMonteCarloSimulationModel> interestRatesModels = new HashMap<>();
    CrossCurrencyLIBORMarketModelFromModels crossCurrencyLIBORMarketModelFromModels =
        new CrossCurrencyLIBORMarketModelFromModels(
            "Base Model", interestRatesModels, new HashMap<>());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> crossCurrencyLIBORMarketModelFromModels.getCloneWithModifiedData(new HashMap<>()));
  }

  /**
   * Test {@link CrossCurrencyLIBORMarketModelFromModels#getValue(RiskFactorID, double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link CrossCurrencyLIBORMarketModelFromModels#getValue(RiskFactorID,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CrossCurrencyLIBORMarketModelFromModels.getValue(RiskFactorID, double)"
  })
  public void testGetValue_thenReturnScalar() throws CalculationException {
    // Arrange
    HashMap<String, MonteCarloProcessFromProcessModel> fxModels = new HashMap<>();
    BachelierModel model = new BachelierModel(1.0d, 1.0d, 1.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion), Scheme.EULER);
    fxModels.put("Name", eulerSchemeFromProcessModel);
    CrossCurrencyLIBORMarketModelFromModels crossCurrencyLIBORMarketModelFromModels =
        new CrossCurrencyLIBORMarketModelFromModels("Name", new HashMap<>(), fxModels);

    // Act
    RandomVariable actualValue =
        crossCurrencyLIBORMarketModelFromModels.getValue(new RiskFactorFX("Name"), 10.0d);

    // Assert
    assertTrue(actualValue instanceof Scalar);
    assertTrue(actualValue.abs() instanceof Scalar);
    assertTrue(actualValue.cos() instanceof Scalar);
    assertTrue(actualValue.exp() instanceof Scalar);
    assertTrue(actualValue.expm1() instanceof Scalar);
    assertTrue(actualValue.invert() instanceof Scalar);
    assertTrue(actualValue.isNaN() instanceof Scalar);
    assertTrue(actualValue.sin() instanceof Scalar);
    assertTrue(actualValue.sqrt() instanceof Scalar);
    assertTrue(actualValue.squared() instanceof Scalar);
    assertTrue(actualValue.variance() instanceof Scalar);
    assertNull(actualValue.getRealizations());
    assertNull(actualValue.getOperator());
    assertNull(actualValue.getRealizationsStream());
    assertEquals(0, actualValue.getTypePriority());
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.size());
    assertEquals(1.0d, actualValue.getAverage(), 0.0);
    assertEquals(1.0d, actualValue.getMax(), 0.0);
    assertEquals(1.0d, actualValue.getMin(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualValue.expectation();
    assertSame(actualValue, actualExpectationResult);
  }

  /**
   * Test {@link CrossCurrencyLIBORMarketModelFromModels#getValue(RiskFactorID, double)}.
   *
   * <ul>
   *   <li>When {@link RiskFactorID}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CrossCurrencyLIBORMarketModelFromModels#getValue(RiskFactorID,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CrossCurrencyLIBORMarketModelFromModels.getValue(RiskFactorID, double)"
  })
  public void testGetValue_whenRiskFactorID_thenThrowIllegalArgumentException()
      throws CalculationException {
    // Arrange
    HashMap<String, LIBORModelMonteCarloSimulationModel> interestRatesModels = new HashMap<>();
    CrossCurrencyLIBORMarketModelFromModels crossCurrencyLIBORMarketModelFromModels =
        new CrossCurrencyLIBORMarketModelFromModels(
            "Base Model", interestRatesModels, new HashMap<>());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> crossCurrencyLIBORMarketModelFromModels.getValue(mock(RiskFactorID.class), 10.0d));
  }

  /**
   * Test {@link CrossCurrencyLIBORMarketModelFromModels#getInterestRateModel(String)}.
   *
   * <p>Method under test: {@link
   * CrossCurrencyLIBORMarketModelFromModels#getInterestRateModel(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORModelMonteCarloSimulationModel CrossCurrencyLIBORMarketModelFromModels.getInterestRateModel(String)"
  })
  public void testGetInterestRateModel() {
    // Arrange
    HashMap<String, LIBORModelMonteCarloSimulationModel> interestRatesModels = new HashMap<>();
    CrossCurrencyLIBORMarketModelFromModels crossCurrencyLIBORMarketModelFromModels =
        new CrossCurrencyLIBORMarketModelFromModels(
            "Base Model", interestRatesModels, new HashMap<>());

    // Act and Assert
    assertNull(crossCurrencyLIBORMarketModelFromModels.getInterestRateModel("Model"));
  }
}
