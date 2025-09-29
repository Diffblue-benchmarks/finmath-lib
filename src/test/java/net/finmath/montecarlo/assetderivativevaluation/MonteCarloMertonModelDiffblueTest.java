package net.finmath.montecarlo.assetderivativevaluation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MonteCarloMertonModelDiffblueTest {
  /**
   * Test {@link MonteCarloMertonModel#MonteCarloMertonModel(TimeDiscretization, int, int, double,
   * double, double, double, double, double)}.
   *
   * <p>Method under test: {@link MonteCarloMertonModel#MonteCarloMertonModel(TimeDiscretization,
   * int, int, double, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloMertonModel.<init>(TimeDiscretization, int, int, double, double, double, double, double, double)"
  })
  public void testNewMonteCarloMertonModel() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    MonteCarloMertonModel actualMonteCarloMertonModel =
        new MonteCarloMertonModel(
            timeDiscretization, 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1, actualMonteCarloMertonModel.getNumberOfAssets());
    assertEquals(10, actualMonteCarloMertonModel.getNumberOfPaths());
    assertSame(timeDiscretization, actualMonteCarloMertonModel.getTimeDiscretization());
  }

  /**
   * Test {@link MonteCarloMertonModel#MonteCarloMertonModel(TimeDiscretization, int, int, double,
   * double, double, double, double, double, RandomVariableFactory)}.
   *
   * <ul>
   *   <li>Then return NumberOfAssets is one.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloMertonModel#MonteCarloMertonModel(TimeDiscretization,
   * int, int, double, double, double, double, double, double, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloMertonModel.<init>(TimeDiscretization, int, int, double, double, double, double, double, double, RandomVariableFactory)"
  })
  public void testNewMonteCarloMertonModel_thenReturnNumberOfAssetsIsOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    MonteCarloMertonModel actualMonteCarloMertonModel =
        new MonteCarloMertonModel(
            timeDiscretization,
            10,
            42,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            new RandomVariableFloatFactory());

    // Assert
    assertEquals(1, actualMonteCarloMertonModel.getNumberOfAssets());
    assertEquals(10, actualMonteCarloMertonModel.getNumberOfPaths());
    assertSame(timeDiscretization, actualMonteCarloMertonModel.getTimeDiscretization());
  }

  /**
   * Test {@link MonteCarloMertonModel#getReferenceDate()}.
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.time.LocalDateTime MonteCarloMertonModel.getReferenceDate()"})
  public void testGetReferenceDate() {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> monteCarloMertonModel.getReferenceDate());
  }

  /**
   * Test {@link MonteCarloMertonModel#getAssetValue(double, int)} with {@code time}, {@code
   * assetIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getAssetValue(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloMertonModel.getAssetValue(double, int)"})
  public void testGetAssetValueWithTimeAssetIndex_thenThrowIllegalArgumentException()
      throws CalculationException {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            10,
            42,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> monteCarloMertonModel.getAssetValue(10.0d, 1));
  }

  /**
   * Test {@link MonteCarloMertonModel#getAssetValue(double, int)} with {@code time}, {@code
   * assetIndex}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getAssetValue(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloMertonModel.getAssetValue(double, int)"})
  public void testGetAssetValueWithTimeAssetIndex_whenZero_thenReturnScalar()
      throws CalculationException {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualAssetValue = monteCarloMertonModel.getAssetValue(10.0d, 0);

    // Assert
    assertTrue(actualAssetValue instanceof Scalar);
    assertTrue(actualAssetValue.abs() instanceof Scalar);
    assertTrue(actualAssetValue.cos() instanceof Scalar);
    assertTrue(actualAssetValue.exp() instanceof Scalar);
    assertTrue(actualAssetValue.expm1() instanceof Scalar);
    assertTrue(actualAssetValue.invert() instanceof Scalar);
    assertTrue(actualAssetValue.isNaN() instanceof Scalar);
    assertTrue(actualAssetValue.sin() instanceof Scalar);
    assertTrue(actualAssetValue.sqrt() instanceof Scalar);
    assertTrue(actualAssetValue.squared() instanceof Scalar);
    assertTrue(actualAssetValue.variance() instanceof Scalar);
    assertNull(actualAssetValue.getRealizations());
    assertNull(actualAssetValue.getOperator());
    assertNull(actualAssetValue.getRealizationsStream());
    assertEquals(0, actualAssetValue.getTypePriority());
    assertEquals(0.0d, actualAssetValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAssetValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAssetValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualAssetValue.getVariance(), 0.0);
    assertEquals(1, actualAssetValue.size());
    assertEquals(10.000000000000002d, actualAssetValue.getAverage(), 0.0);
    assertEquals(10.000000000000002d, actualAssetValue.getMax(), 0.0);
    assertEquals(10.000000000000002d, actualAssetValue.getMin(), 0.0);
    assertTrue(actualAssetValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAssetValue.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualAssetValue.expectation();
    assertSame(actualAssetValue, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloMertonModel#getAssetValue(int, int)} with {@code timeIndex}, {@code
   * assetIndex}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getAssetValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloMertonModel.getAssetValue(int, int)"})
  public void testGetAssetValueWithTimeIndexAssetIndex_thenReturnRandomVariableFromDoubleArray()
      throws CalculationException {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualAssetValue = monteCarloMertonModel.getAssetValue(1, 0);

    // Assert
    assertTrue(actualAssetValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualAssetValue.getAverage(), 0.0);
    assertEquals(0.0d, actualAssetValue.getMax(), 0.0);
    assertEquals(0.0d, actualAssetValue.getMin(), 0.0);
    assertEquals(0.0d, actualAssetValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAssetValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAssetValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualAssetValue.getVariance(), 0.0);
    assertEquals(1, actualAssetValue.getTypePriority());
    assertEquals(10, actualAssetValue.size());
    assertEquals(10.5d, actualAssetValue.getFiltrationTime(), 0.0);
    assertFalse(actualAssetValue.isDeterministic());
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualAssetValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloMertonModel#getNumeraire(double)} with {@code time}.
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloMertonModel.getNumeraire(double)"})
  public void testGetNumeraireWithTime() throws CalculationException {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualNumeraire = monteCarloMertonModel.getNumeraire(10.0d);

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
    assertNull(actualNumeraire.getRealizations());
    assertNull(actualNumeraire.getOperator());
    assertNull(actualNumeraire.getRealizationsStream());
    assertEquals(0, actualNumeraire.getTypePriority());
    assertEquals(0.0d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(0.0d, actualNumeraire.getVariance(), 0.0);
    assertEquals(1, actualNumeraire.size());
    assertEquals(2.6881171418161356E43d, actualNumeraire.getAverage(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getMax(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getMin(), 0.0);
    assertTrue(actualNumeraire.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualNumeraire.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloMertonModel#getNumeraire(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getNumeraire(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloMertonModel.getNumeraire(int)"})
  public void testGetNumeraireWithTimeIndex_thenReturnScalar() throws CalculationException {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualNumeraire = monteCarloMertonModel.getNumeraire(1);

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
    assertNull(actualNumeraire.getRealizations());
    assertNull(actualNumeraire.getOperator());
    assertNull(actualNumeraire.getRealizationsStream());
    assertEquals(0, actualNumeraire.getTypePriority());
    assertEquals(0.0d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(0.0d, actualNumeraire.getVariance(), 0.0);
    assertEquals(1, actualNumeraire.size());
    assertEquals(3.989519570547216E45d, actualNumeraire.getAverage(), 0.0);
    assertEquals(3.989519570547216E45d, actualNumeraire.getMax(), 0.0);
    assertEquals(3.989519570547216E45d, actualNumeraire.getMin(), 0.0);
    assertTrue(actualNumeraire.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualNumeraire.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloMertonModel#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloMertonModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex_thenReturnScalar() throws CalculationException {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloMertonModel.getMonteCarloWeights(1);

    // Assert
    assertTrue(actualMonteCarloWeights instanceof Scalar);
    assertTrue(actualMonteCarloWeights.abs() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.cos() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.exp() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.expm1() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.invert() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sin() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.squared() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.variance() instanceof Scalar);
    assertNull(actualMonteCarloWeights.getRealizations());
    assertNull(actualMonteCarloWeights.getOperator());
    assertNull(actualMonteCarloWeights.getRealizationsStream());
    assertEquals(0, actualMonteCarloWeights.getTypePriority());
    assertEquals(0.0d, actualMonteCarloWeights.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualMonteCarloWeights.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualMonteCarloWeights.getStandardError(), 0.0);
    assertEquals(0.0d, actualMonteCarloWeights.getVariance(), 0.0);
    assertEquals(0.1d, actualMonteCarloWeights.getAverage(), 0.0);
    assertEquals(0.1d, actualMonteCarloWeights.getMax(), 0.0);
    assertEquals(0.1d, actualMonteCarloWeights.getMin(), 0.0);
    assertEquals(1, actualMonteCarloWeights.size());
    assertTrue(actualMonteCarloWeights.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualMonteCarloWeights.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualMonteCarloWeights.expectation();
    assertSame(actualMonteCarloWeights, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloMertonModel#getMonteCarloWeights(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getMonteCarloWeights(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloMertonModel.getMonteCarloWeights(double)"})
  public void testGetMonteCarloWeightsWithTime_thenReturnScalar() throws CalculationException {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloMertonModel.getMonteCarloWeights(10.0d);

    // Assert
    assertTrue(actualMonteCarloWeights instanceof Scalar);
    assertTrue(actualMonteCarloWeights.abs() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.cos() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.exp() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.expm1() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.invert() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sin() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.squared() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.variance() instanceof Scalar);
    assertNull(actualMonteCarloWeights.getRealizations());
    assertNull(actualMonteCarloWeights.getOperator());
    assertNull(actualMonteCarloWeights.getRealizationsStream());
    assertEquals(0, actualMonteCarloWeights.getTypePriority());
    assertEquals(0.0d, actualMonteCarloWeights.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualMonteCarloWeights.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualMonteCarloWeights.getStandardError(), 0.0);
    assertEquals(0.0d, actualMonteCarloWeights.getVariance(), 0.0);
    assertEquals(0.1d, actualMonteCarloWeights.getAverage(), 0.0);
    assertEquals(0.1d, actualMonteCarloWeights.getMax(), 0.0);
    assertEquals(0.1d, actualMonteCarloWeights.getMin(), 0.0);
    assertEquals(1, actualMonteCarloWeights.size());
    assertTrue(actualMonteCarloWeights.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualMonteCarloWeights.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualMonteCarloWeights.expectation();
    assertSame(actualMonteCarloWeights, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloMertonModel#getNumberOfAssets()}.
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getNumberOfAssets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MonteCarloMertonModel.getNumberOfAssets()"})
  public void testGetNumberOfAssets() {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(1, monteCarloMertonModel.getNumberOfAssets());
  }

  /**
   * Test {@link MonteCarloMertonModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Then return {@link MonteCarloMertonModel}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AssetModelMonteCarloSimulationModel MonteCarloMertonModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_thenReturnMonteCarloMertonModel() {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    AssetModelMonteCarloSimulationModel actualCloneWithModifiedData =
        monteCarloMertonModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof MonteCarloMertonModel);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(1, actualCloneWithModifiedData.getNumberOfAssets());
    assertEquals(1.1415525114155251E-4d, timeDiscretization.getTickSize(), 0.0);
    assertEquals(10, actualCloneWithModifiedData.getNumberOfPaths());
    assertEquals(10, timeDiscretization.getNumberOfTimeSteps());
    assertEquals(10.0d, timeDiscretization.getFirstTime(), 0.0);
    assertEquals(11, timeDiscretization.getAsArrayList().size());
    assertEquals(11, timeDiscretization.getNumberOfTimes());
    assertEquals(15.0d, timeDiscretization.getLastTime(), 0.0);
    assertTrue(timeDiscretization.iterator().hasNext());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link MonteCarloMertonModel#getCloneWithModifiedSeed(int)}.
   *
   * <ul>
   *   <li>Then return {@link MonteCarloMertonModel}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AssetModelMonteCarloSimulationModel MonteCarloMertonModel.getCloneWithModifiedSeed(int)"
  })
  public void testGetCloneWithModifiedSeed_thenReturnMonteCarloMertonModel() {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    AssetModelMonteCarloSimulationModel actualCloneWithModifiedSeed =
        monteCarloMertonModel.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof MonteCarloMertonModel);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedSeed.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(1, actualCloneWithModifiedSeed.getNumberOfAssets());
    assertEquals(1.1415525114155251E-4d, timeDiscretization.getTickSize(), 0.0);
    assertEquals(10, actualCloneWithModifiedSeed.getNumberOfPaths());
    assertEquals(10, timeDiscretization.getNumberOfTimeSteps());
    assertEquals(10.0d, timeDiscretization.getFirstTime(), 0.0);
    assertEquals(11, timeDiscretization.getAsArrayList().size());
    assertEquals(11, timeDiscretization.getNumberOfTimes());
    assertEquals(15.0d, timeDiscretization.getLastTime(), 0.0);
    assertTrue(timeDiscretization.iterator().hasNext());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link MonteCarloMertonModel#getNumberOfPaths()}.
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MonteCarloMertonModel.getNumberOfPaths()"})
  public void testGetNumberOfPaths() {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10, monteCarloMertonModel.getNumberOfPaths());
  }

  /**
   * Test {@link MonteCarloMertonModel#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization MonteCarloMertonModel.getTimeDiscretization()"})
  public void testGetTimeDiscretization() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            timeDiscretization, 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertSame(timeDiscretization, monteCarloMertonModel.getTimeDiscretization());
  }

  /**
   * Test {@link MonteCarloMertonModel#getTime(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double MonteCarloMertonModel.getTime(int)"})
  public void testGetTime_thenReturn105() {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10.5d, monteCarloMertonModel.getTime(1), 0.0);
  }

  /**
   * Test {@link MonteCarloMertonModel#getTimeIndex(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getTimeIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MonteCarloMertonModel.getTimeIndex(double)"})
  public void testGetTimeIndex_thenReturnZero() {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(0, monteCarloMertonModel.getTimeIndex(10.0d));
  }

  /**
   * Test {@link MonteCarloMertonModel#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link MonteCarloMertonModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloMertonModel.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant() {
    // Arrange
    MonteCarloMertonModel monteCarloMertonModel =
        new MonteCarloMertonModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualRandomVariableForConstant =
        monteCarloMertonModel.getRandomVariableForConstant(10.0d);

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
}
