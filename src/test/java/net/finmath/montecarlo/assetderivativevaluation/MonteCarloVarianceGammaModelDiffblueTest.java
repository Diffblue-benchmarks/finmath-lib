package net.finmath.montecarlo.assetderivativevaluation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MonteCarloVarianceGammaModelDiffblueTest {
  /**
   * Test {@link MonteCarloVarianceGammaModel#MonteCarloVarianceGammaModel(TimeDiscretization, int,
   * int, double, double, double, double, double)}.
   *
   * <p>Method under test: {@link
   * MonteCarloVarianceGammaModel#MonteCarloVarianceGammaModel(TimeDiscretization, int, int, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloVarianceGammaModel.<init>(TimeDiscretization, int, int, double, double, double, double, double)"
  })
  public void testNewMonteCarloVarianceGammaModel() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    MonteCarloVarianceGammaModel actualMonteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            timeDiscretization, 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(1, actualMonteCarloVarianceGammaModel.getNumberOfAssets());
    assertEquals(10, actualMonteCarloVarianceGammaModel.getNumberOfPaths());
    assertSame(timeDiscretization, actualMonteCarloVarianceGammaModel.getTimeDiscretization());
  }

  /**
   * Test {@link MonteCarloVarianceGammaModel#getReferenceDate()}.
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.time.LocalDateTime MonteCarloVarianceGammaModel.getReferenceDate()"})
  public void testGetReferenceDate() {
    // Arrange
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> monteCarloVarianceGammaModel.getReferenceDate());
  }

  /**
   * Test {@link MonteCarloVarianceGammaModel#getNumeraire(double)} with {@code time}.
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloVarianceGammaModel.getNumeraire(double)"})
  public void testGetNumeraireWithTime() throws CalculationException {
    // Arrange
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualNumeraire = monteCarloVarianceGammaModel.getNumeraire(10.0d);

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
   * Test {@link MonteCarloVarianceGammaModel#getNumeraire(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getNumeraire(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloVarianceGammaModel.getNumeraire(int)"})
  public void testGetNumeraireWithTimeIndex_thenReturnScalar() throws CalculationException {
    // Arrange
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualNumeraire = monteCarloVarianceGammaModel.getNumeraire(1);

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
   * Test {@link MonteCarloVarianceGammaModel#getMonteCarloWeights(double)} with {@code time}.
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getMonteCarloWeights(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloVarianceGammaModel.getMonteCarloWeights(double)"})
  public void testGetMonteCarloWeightsWithTime() throws CalculationException {
    // Arrange
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, -0.5d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualMonteCarloWeights =
        monteCarloVarianceGammaModel.getMonteCarloWeights(10.0d);

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
    RandomVariable actualExpectationResult = actualMonteCarloWeights.expectation();
    assertSame(actualMonteCarloWeights, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloVarianceGammaModel#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloVarianceGammaModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex() throws CalculationException {
    // Arrange
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 0.180625d, 10.0d, 10.0d);

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloVarianceGammaModel.getMonteCarloWeights(1);

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
    RandomVariable actualExpectationResult = actualMonteCarloWeights.expectation();
    assertSame(actualMonteCarloWeights, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloVarianceGammaModel#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloVarianceGammaModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex_thenReturnScalar() throws CalculationException {
    // Arrange
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloVarianceGammaModel.getMonteCarloWeights(1);

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
    RandomVariable actualExpectationResult = actualMonteCarloWeights.expectation();
    assertSame(actualMonteCarloWeights, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloVarianceGammaModel#getMonteCarloWeights(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getMonteCarloWeights(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloVarianceGammaModel.getMonteCarloWeights(double)"})
  public void testGetMonteCarloWeightsWithTime_thenReturnScalar() throws CalculationException {
    // Arrange
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualMonteCarloWeights =
        monteCarloVarianceGammaModel.getMonteCarloWeights(10.0d);

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
    RandomVariable actualExpectationResult = actualMonteCarloWeights.expectation();
    assertSame(actualMonteCarloWeights, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloVarianceGammaModel#getNumberOfAssets()}.
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getNumberOfAssets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MonteCarloVarianceGammaModel.getNumberOfAssets()"})
  public void testGetNumberOfAssets() {
    // Arrange
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(1, monteCarloVarianceGammaModel.getNumberOfAssets());
  }

  /**
   * Test {@link MonteCarloVarianceGammaModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Then return {@link MonteCarloVarianceGammaModel}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AssetModelMonteCarloSimulationModel MonteCarloVarianceGammaModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_thenReturnMonteCarloVarianceGammaModel() {
    // Arrange
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    AssetModelMonteCarloSimulationModel actualCloneWithModifiedData =
        monteCarloVarianceGammaModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof MonteCarloVarianceGammaModel);
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
   * Test {@link MonteCarloVarianceGammaModel#getCloneWithModifiedSeed(int)}.
   *
   * <ul>
   *   <li>Then return {@link MonteCarloVarianceGammaModel}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AssetModelMonteCarloSimulationModel MonteCarloVarianceGammaModel.getCloneWithModifiedSeed(int)"
  })
  public void testGetCloneWithModifiedSeed_thenReturnMonteCarloVarianceGammaModel() {
    // Arrange
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    AssetModelMonteCarloSimulationModel actualCloneWithModifiedSeed =
        monteCarloVarianceGammaModel.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof MonteCarloVarianceGammaModel);
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
   * Test {@link MonteCarloVarianceGammaModel#getNumberOfPaths()}.
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MonteCarloVarianceGammaModel.getNumberOfPaths()"})
  public void testGetNumberOfPaths() {
    // Arrange
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10, monteCarloVarianceGammaModel.getNumberOfPaths());
  }

  /**
   * Test {@link MonteCarloVarianceGammaModel#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization MonteCarloVarianceGammaModel.getTimeDiscretization()"})
  public void testGetTimeDiscretization() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            timeDiscretization, 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertSame(timeDiscretization, monteCarloVarianceGammaModel.getTimeDiscretization());
  }

  /**
   * Test {@link MonteCarloVarianceGammaModel#getTime(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double MonteCarloVarianceGammaModel.getTime(int)"})
  public void testGetTime_thenReturn105() {
    // Arrange
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10.5d, monteCarloVarianceGammaModel.getTime(1), 0.0);
  }

  /**
   * Test {@link MonteCarloVarianceGammaModel#getTimeIndex(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getTimeIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MonteCarloVarianceGammaModel.getTimeIndex(double)"})
  public void testGetTimeIndex_thenReturnZero() {
    // Arrange
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(0, monteCarloVarianceGammaModel.getTimeIndex(10.0d));
  }

  /**
   * Test {@link MonteCarloVarianceGammaModel#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link MonteCarloVarianceGammaModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MonteCarloVarianceGammaModel.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant() {
    // Arrange
    MonteCarloVarianceGammaModel monteCarloVarianceGammaModel =
        new MonteCarloVarianceGammaModel(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 42, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualRandomVariableForConstant =
        monteCarloVarianceGammaModel.getRandomVariableForConstant(10.0d);

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
