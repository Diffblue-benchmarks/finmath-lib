package net.finmath.montecarlo.assetderivativevaluation.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BlackScholesModelDiffblueTest {
  /**
   * Test {@link BlackScholesModel#BlackScholesModel(double, double, double)}.
   *
   * <p>Method under test: {@link BlackScholesModel#BlackScholesModel(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BlackScholesModel.<init>(double, double, double)"})
  public void testNewBlackScholesModel() {
    // Arrange and Act
    BlackScholesModel actualBlackScholesModel = new BlackScholesModel(10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualBlackScholesModel.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualBlackScholesModel.getVolatility() instanceof Scalar);
    assertEquals(1, actualBlackScholesModel.getNumberOfComponents());
    assertEquals(1, actualBlackScholesModel.getNumberOfFactors());
  }

  /**
   * Test {@link BlackScholesModel#BlackScholesModel(RandomVariable, RandomVariable, RandomVariable,
   * RandomVariableFactory)}.
   *
   * <ul>
   *   <li>Then RiskFreeRate return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModel#BlackScholesModel(RandomVariable,
   * RandomVariable, RandomVariable, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlackScholesModel.<init>(RandomVariable, RandomVariable, RandomVariable, RandomVariableFactory)"
  })
  public void testNewBlackScholesModel_thenRiskFreeRateReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAAD riskFreeRate = RandomVariableDifferentiableAAD.of(2.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    BlackScholesModel actualBlackScholesModel =
        new BlackScholesModel(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    // Assert
    assertTrue(actualBlackScholesModel.getVolatility() instanceof RandomVariableFromDoubleArray);
    RandomVariable riskFreeRate2 = actualBlackScholesModel.getRiskFreeRate();
    assertTrue(riskFreeRate2 instanceof RandomVariableDifferentiableAAD);
    assertSame(riskFreeRate, riskFreeRate2);
  }

  /**
   * Test {@link BlackScholesModel#BlackScholesModel(RandomVariable, RandomVariable, RandomVariable,
   * RandomVariableFactory)}.
   *
   * <ul>
   *   <li>Then RiskFreeRate return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModel#BlackScholesModel(RandomVariable,
   * RandomVariable, RandomVariable, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlackScholesModel.<init>(RandomVariable, RandomVariable, RandomVariable, RandomVariableFactory)"
  })
  public void testNewBlackScholesModel_thenRiskFreeRateReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    BlackScholesModel actualBlackScholesModel =
        new BlackScholesModel(
            initialValue, riskFreeRate, volatility, new RandomVariableFloatFactory());

    // Assert
    RandomVariable riskFreeRate2 = actualBlackScholesModel.getRiskFreeRate();
    assertTrue(riskFreeRate2 instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBlackScholesModel.getVolatility() instanceof RandomVariableFromDoubleArray);
    assertSame(riskFreeRate, riskFreeRate2);
  }

  /**
   * Test {@link BlackScholesModel#BlackScholesModel(double, double, double,
   * RandomVariableFactory)}.
   *
   * <ul>
   *   <li>Then RiskFreeRate return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModel#BlackScholesModel(double, double, double,
   * RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlackScholesModel.<init>(double, double, double, RandomVariableFactory)"
  })
  public void testNewBlackScholesModel_thenRiskFreeRateReturnRandomVariableFromFloatArray() {
    // Arrange and Act
    BlackScholesModel actualBlackScholesModel =
        new BlackScholesModel(10.0d, 10.0d, 10.0d, new RandomVariableFloatFactory());

    // Assert
    assertTrue(actualBlackScholesModel.getRiskFreeRate() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBlackScholesModel.getVolatility() instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualBlackScholesModel.getNumberOfComponents());
    assertEquals(1, actualBlackScholesModel.getNumberOfFactors());
  }

  /**
   * Test {@link BlackScholesModel#getInitialState(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link BlackScholesModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlackScholesModel.getInitialState(MonteCarloProcess)"})
  public void testGetInitialState() {
    // Arrange
    BlackScholesModel blackScholesModel = new BlackScholesModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = blackScholesModel.getInitialState(process);

    // Assert
    assertTrue(actualInitialState[0] instanceof Scalar);
    assertEquals(1, actualInitialState.length);
  }

  /**
   * Test {@link BlackScholesModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link BlackScholesModel#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlackScholesModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift() {
    // Arrange
    BlackScholesModel blackScholesModel = new BlackScholesModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualDrift =
        blackScholesModel.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualDrift[0] instanceof Scalar);
    assertEquals(1, actualDrift.length);
  }

  /**
   * Test {@link BlackScholesModel#getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])}.
   *
   * <p>Method under test: {@link BlackScholesModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlackScholesModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading() {
    // Arrange
    BlackScholesModel blackScholesModel = new BlackScholesModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualFactorLoading =
        blackScholesModel.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertEquals(1, actualFactorLoading.length);
  }

  /**
   * Test {@link BlackScholesModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModel#applyStateSpaceTransform(MonteCarloProcess, int,
   * int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlackScholesModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    BlackScholesModel blackScholesModel = new BlackScholesModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        blackScholesModel.applyStateSpaceTransform(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
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
   * Test {@link BlackScholesModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BlackScholesModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlackScholesModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    BlackScholesModel blackScholesModel = new BlackScholesModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        blackScholesModel.applyStateSpaceTransformInverse(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
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
   * Test {@link BlackScholesModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BlackScholesModel.getNumeraire(MonteCarloProcess, double)"})
  public void testGetNumeraire_thenReturnScalar() {
    // Arrange
    BlackScholesModel blackScholesModel = new BlackScholesModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = blackScholesModel.getNumeraire(process, 10.0d);

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
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BlackScholesModel#toString()}
   *   <li>{@link BlackScholesModel#getNumberOfComponents()}
   *   <li>{@link BlackScholesModel#getNumberOfFactors()}
   *   <li>{@link BlackScholesModel#getRiskFreeRate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int BlackScholesModel.getNumberOfComponents()",
    "int BlackScholesModel.getNumberOfFactors()",
    "RandomVariable BlackScholesModel.getRiskFreeRate()",
    "String BlackScholesModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    BlackScholesModel blackScholesModel = new BlackScholesModel(10.0d, 10.0d, 10.0d);

    // Act
    String actualToStringResult = blackScholesModel.toString();
    int actualNumberOfComponents = blackScholesModel.getNumberOfComponents();
    int actualNumberOfFactors = blackScholesModel.getNumberOfFactors();

    // Assert
    assertTrue(blackScholesModel.getRiskFreeRate() instanceof Scalar);
    assertEquals(
        "BlackScholesModel [initialValue=Scalar [value=10.0, filtrationTime=-Infinity, typePriority()=0],"
            + " riskFreeRate=Scalar [value=10.0, filtrationTime=-Infinity, typePriority()=0], volatility=Scalar"
            + " [value=10.0, filtrationTime=-Infinity, typePriority()=0], randomVariableFactory=RandomVariableFrom"
            + "ArrayFactory [isUseDoublePrecisionFloatingPointImplementation=true], initialState=[Scalar [value=2"
            + ".302585092994046, filtrationTime=-Infinity, typePriority()=0]], drift=[Scalar [value=-40.0,"
            + " filtrationTime=-Infinity, typePriority()=0]], factorLoadings=[Scalar [value=10.0, filtrationTime=-Infinity,"
            + " typePriority()=0]]]",
        actualToStringResult);
    assertEquals(1, actualNumberOfComponents);
    assertEquals(1, actualNumberOfFactors);
  }

  /**
   * Test {@link BlackScholesModel#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BlackScholesModel.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant_thenReturnScalar() {
    // Arrange and Act
    RandomVariable actualRandomVariableForConstant =
        new BlackScholesModel(10.0d, 10.0d, 10.0d).getRandomVariableForConstant(10.0d);

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
   * Test {@link BlackScholesModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Given {@code initialValue}.
   *   <li>When {@link HashMap#HashMap()} {@code initialValue} is {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BlackScholesModel BlackScholesModel.getCloneWithModifiedData(Map)"})
  public void testGetCloneWithModifiedData_givenInitialValue_whenHashMapInitialValueIsA() {
    // Arrange
    BlackScholesModel blackScholesModel = new BlackScholesModel(10.0d, 10.0d, 10.0d);

    HashMap<String, Object> dataModified = new HashMap<>();
    dataModified.put("initialValue", (byte) 'A');
    dataModified.put("riskFreeRate", null);
    dataModified.put("volatility", null);

    // Act
    BlackScholesModel actualCloneWithModifiedData =
        blackScholesModel.getCloneWithModifiedData(dataModified);

    // Assert
    assertTrue(actualCloneWithModifiedData.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getVolatility() instanceof Scalar);
    assertEquals(1, actualCloneWithModifiedData.getNumberOfComponents());
    assertEquals(1, actualCloneWithModifiedData.getNumberOfFactors());
  }

  /**
   * Test {@link BlackScholesModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link BlackScholesModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BlackScholesModel BlackScholesModel.getCloneWithModifiedData(Map)"})
  public void testGetCloneWithModifiedData_whenHashMap() {
    // Arrange
    BlackScholesModel blackScholesModel = new BlackScholesModel(10.0d, 10.0d, 10.0d);

    // Act
    BlackScholesModel actualCloneWithModifiedData =
        blackScholesModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getVolatility() instanceof Scalar);
    assertEquals(1, actualCloneWithModifiedData.getNumberOfComponents());
    assertEquals(1, actualCloneWithModifiedData.getNumberOfFactors());
  }

  /**
   * Test {@link BlackScholesModel#getInitialValue(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link BlackScholesModel#getInitialValue(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlackScholesModel.getInitialValue(MonteCarloProcess)"})
  public void testGetInitialValue() {
    // Arrange
    BlackScholesModel blackScholesModel = new BlackScholesModel(10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialValue = blackScholesModel.getInitialValue(process);

    // Assert
    assertTrue(actualInitialValue[0] instanceof Scalar);
    assertEquals(1, actualInitialValue.length);
  }

  /**
   * Test {@link BlackScholesModel#getVolatility()}.
   *
   * <p>Method under test: {@link BlackScholesModel#getVolatility()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable BlackScholesModel.getVolatility()"})
  public void testGetVolatility() {
    // Arrange and Act
    RandomVariable actualVolatility = new BlackScholesModel(10.0d, 10.0d, 10.0d).getVolatility();

    // Assert
    assertTrue(actualVolatility instanceof Scalar);
    assertTrue(actualVolatility.abs() instanceof Scalar);
    assertTrue(actualVolatility.cos() instanceof Scalar);
    assertTrue(actualVolatility.exp() instanceof Scalar);
    assertTrue(actualVolatility.expm1() instanceof Scalar);
    assertTrue(actualVolatility.invert() instanceof Scalar);
    assertTrue(actualVolatility.isNaN() instanceof Scalar);
    assertTrue(actualVolatility.sin() instanceof Scalar);
    assertTrue(actualVolatility.sqrt() instanceof Scalar);
    assertTrue(actualVolatility.squared() instanceof Scalar);
    assertTrue(actualVolatility.variance() instanceof Scalar);
    assertNull(actualVolatility.getRealizations());
    assertNull(actualVolatility.getOperator());
    assertNull(actualVolatility.getRealizationsStream());
    assertEquals(0, actualVolatility.getTypePriority());
    assertEquals(0.0d, actualVolatility.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualVolatility.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualVolatility.getStandardError(), 0.0);
    assertEquals(0.0d, actualVolatility.getVariance(), 0.0);
    assertEquals(1, actualVolatility.size());
    assertEquals(10.0d, actualVolatility.getAverage(), 0.0);
    assertEquals(10.0d, actualVolatility.getMax(), 0.0);
    assertEquals(10.0d, actualVolatility.getMin(), 0.0);
    assertTrue(actualVolatility.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualVolatility.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }
}
