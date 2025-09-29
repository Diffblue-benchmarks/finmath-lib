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
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MultiAssetBlackScholesModelDiffblueTest {
  /**
   * Test {@link MultiAssetBlackScholesModel#MultiAssetBlackScholesModel(RandomVariableFactory,
   * double[], double, double[][])}.
   *
   * <ul>
   *   <li>Then return NumberOfComponents is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * MultiAssetBlackScholesModel#MultiAssetBlackScholesModel(RandomVariableFactory, double[],
   * double, double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MultiAssetBlackScholesModel.<init>(RandomVariableFactory, double[], double, double[][])"
  })
  public void testNewMultiAssetBlackScholesModel_thenReturnNumberOfComponentsIsZero() {
    // Arrange
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    // Act
    MultiAssetBlackScholesModel actualMultiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(
            new RandomVariableFloatFactory(), new double[] {}, 10.0d, factorLoadings);

    // Assert
    assertEquals(0, actualMultiAssetBlackScholesModel.getNumberOfComponents());
    double[][] correlationMatrix = actualMultiAssetBlackScholesModel.getCorrelationMatrix();
    assertEquals(1, correlationMatrix.length);
    double[][] factorLoadingMatrix = actualMultiAssetBlackScholesModel.getFactorLoadingMatrix();
    assertEquals(1, factorLoadingMatrix.length);
    assertEquals(10.0d, actualMultiAssetBlackScholesModel.getRiskFreeRate(), 0.0);
    assertEquals(4, actualMultiAssetBlackScholesModel.getNumberOfFactors());
    assertSame(factorLoadings, factorLoadingMatrix);
    assertArrayEquals(new double[] {1.0d}, correlationMatrix[0], 0.0);
    assertArrayEquals(
        new double[] {14.212670403551895d},
        actualMultiAssetBlackScholesModel.getVolatilityVector(),
        0.0);
    assertArrayEquals(new double[] {10.0d, 1.0d, 10.0d, 1.0d}, factorLoadingMatrix[0], 0.0);
  }

  /**
   * Test {@link MultiAssetBlackScholesModel#MultiAssetBlackScholesModel(double[], double,
   * double[][])}.
   *
   * <ul>
   *   <li>Then return NumberOfComponents is zero.
   * </ul>
   *
   * <p>Method under test: {@link MultiAssetBlackScholesModel#MultiAssetBlackScholesModel(double[],
   * double, double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultiAssetBlackScholesModel.<init>(double[], double, double[][])"})
  public void testNewMultiAssetBlackScholesModel_thenReturnNumberOfComponentsIsZero2() {
    // Arrange
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    // Act
    MultiAssetBlackScholesModel actualMultiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(new double[] {}, 10.0d, factorLoadings);

    // Assert
    assertEquals(0, actualMultiAssetBlackScholesModel.getNumberOfComponents());
    double[][] correlationMatrix = actualMultiAssetBlackScholesModel.getCorrelationMatrix();
    assertEquals(1, correlationMatrix.length);
    double[][] factorLoadingMatrix = actualMultiAssetBlackScholesModel.getFactorLoadingMatrix();
    assertEquals(1, factorLoadingMatrix.length);
    assertEquals(10.0d, actualMultiAssetBlackScholesModel.getRiskFreeRate(), 0.0);
    assertEquals(4, actualMultiAssetBlackScholesModel.getNumberOfFactors());
    assertSame(factorLoadings, factorLoadingMatrix);
    assertArrayEquals(new double[] {1.0d}, correlationMatrix[0], 0.0);
    assertArrayEquals(
        new double[] {14.212670403551895d},
        actualMultiAssetBlackScholesModel.getVolatilityVector(),
        0.0);
    assertArrayEquals(new double[] {10.0d, 1.0d, 10.0d, 1.0d}, factorLoadingMatrix[0], 0.0);
  }

  /**
   * Test {@link MultiAssetBlackScholesModel#getInitialState(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link MultiAssetBlackScholesModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] MultiAssetBlackScholesModel.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState_thenReturnArrayLengthIsZero() {
    // Arrange
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    MultiAssetBlackScholesModel multiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(new double[] {}, 10.0d, factorLoadings);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = multiAssetBlackScholesModel.getInitialState(process);

    // Assert
    assertEquals(0, actualInitialState.length);
  }

  /**
   * Test {@link MultiAssetBlackScholesModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link MultiAssetBlackScholesModel#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] MultiAssetBlackScholesModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenReturnArrayLengthIsZero() {
    // Arrange
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    MultiAssetBlackScholesModel multiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(new double[] {}, 10.0d, factorLoadings);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act and Assert
    assertEquals(
        0,
        multiAssetBlackScholesModel.getDrift(
                process,
                1,
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)})
            .length);
  }

  /**
   * Test {@link MultiAssetBlackScholesModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * MultiAssetBlackScholesModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MultiAssetBlackScholesModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    MultiAssetBlackScholesModel multiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(new double[] {}, 10.0d, factorLoadings);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        multiAssetBlackScholesModel.applyStateSpaceTransform(
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
   * Test {@link MultiAssetBlackScholesModel#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * MultiAssetBlackScholesModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MultiAssetBlackScholesModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    MultiAssetBlackScholesModel multiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(new double[] {}, 10.0d, factorLoadings);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        multiAssetBlackScholesModel.applyStateSpaceTransformInverse(
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
   * Test {@link MultiAssetBlackScholesModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link MultiAssetBlackScholesModel#getNumeraire(MonteCarloProcess,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MultiAssetBlackScholesModel.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire_thenReturnScalar() {
    // Arrange
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    MultiAssetBlackScholesModel multiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(new double[] {}, 10.0d, factorLoadings);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = multiAssetBlackScholesModel.getNumeraire(process, 10.0d);

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
   * Test {@link MultiAssetBlackScholesModel#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link MultiAssetBlackScholesModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MultiAssetBlackScholesModel.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant_thenReturnScalar() {
    // Arrange
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    MultiAssetBlackScholesModel multiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(new double[] {}, 10.0d, factorLoadings);

    // Act
    RandomVariable actualRandomVariableForConstant =
        multiAssetBlackScholesModel.getRandomVariableForConstant(10.0d);

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
   * Test {@link MultiAssetBlackScholesModel#getNumberOfComponents()}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link MultiAssetBlackScholesModel#getNumberOfComponents()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MultiAssetBlackScholesModel.getNumberOfComponents()"})
  public void testGetNumberOfComponents_thenReturnZero() {
    // Arrange
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    MultiAssetBlackScholesModel multiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(new double[] {}, 10.0d, factorLoadings);

    // Act and Assert
    assertEquals(0, multiAssetBlackScholesModel.getNumberOfComponents());
  }

  /**
   * Test {@link MultiAssetBlackScholesModel#getNumberOfFactors()}.
   *
   * <ul>
   *   <li>Then return four.
   * </ul>
   *
   * <p>Method under test: {@link MultiAssetBlackScholesModel#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MultiAssetBlackScholesModel.getNumberOfFactors()"})
  public void testGetNumberOfFactors_thenReturnFour() {
    // Arrange
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    MultiAssetBlackScholesModel multiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(new double[] {}, 10.0d, factorLoadings);

    // Act and Assert
    assertEquals(4, multiAssetBlackScholesModel.getNumberOfFactors());
  }

  /**
   * Test {@link MultiAssetBlackScholesModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Then return NumberOfComponents is zero.
   * </ul>
   *
   * <p>Method under test: {@link MultiAssetBlackScholesModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MultiAssetBlackScholesModel MultiAssetBlackScholesModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_thenReturnNumberOfComponentsIsZero() {
    // Arrange
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    MultiAssetBlackScholesModel multiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(new double[] {}, 10.0d, factorLoadings);

    // Act
    MultiAssetBlackScholesModel actualCloneWithModifiedData =
        multiAssetBlackScholesModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertEquals(0, actualCloneWithModifiedData.getNumberOfComponents());
    double[][] correlationMatrix = actualCloneWithModifiedData.getCorrelationMatrix();
    assertEquals(1, correlationMatrix.length);
    double[][] factorLoadingMatrix = actualCloneWithModifiedData.getFactorLoadingMatrix();
    assertEquals(1, factorLoadingMatrix.length);
    assertEquals(10.0d, actualCloneWithModifiedData.getRiskFreeRate(), 0.0);
    assertEquals(4, actualCloneWithModifiedData.getNumberOfFactors());
    assertArrayEquals(new double[] {1.0d}, correlationMatrix[0], 0.0);
    assertArrayEquals(
        new double[] {14.212670403551895d}, actualCloneWithModifiedData.getVolatilityVector(), 0.0);
    assertArrayEquals(new double[] {10.0d, 1.0d, 10.0d, 1.0d}, factorLoadingMatrix[0], 0.0);
  }

  /**
   * Test {@link MultiAssetBlackScholesModel#getVolatilityVector()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@code 14.212670403551895}.
   * </ul>
   *
   * <p>Method under test: {@link MultiAssetBlackScholesModel#getVolatilityVector()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] MultiAssetBlackScholesModel.getVolatilityVector()"})
  public void testGetVolatilityVector_thenReturnArrayOfDoubleWith14212670403551895() {
    // Arrange
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    MultiAssetBlackScholesModel multiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(new double[] {}, 10.0d, factorLoadings);

    // Act and Assert
    assertArrayEquals(
        new double[] {14.212670403551895d}, multiAssetBlackScholesModel.getVolatilityVector(), 0.0);
  }

  /**
   * Test {@link MultiAssetBlackScholesModel#getCorrelationMatrix()}.
   *
   * <p>Method under test: {@link MultiAssetBlackScholesModel#getCorrelationMatrix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] MultiAssetBlackScholesModel.getCorrelationMatrix()"})
  public void testGetCorrelationMatrix() {
    // Arrange
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    MultiAssetBlackScholesModel multiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(new double[] {}, 10.0d, factorLoadings);

    // Act
    double[][] actualCorrelationMatrix = multiAssetBlackScholesModel.getCorrelationMatrix();

    // Assert
    assertEquals(1, actualCorrelationMatrix.length);
    assertArrayEquals(new double[] {1.0d}, actualCorrelationMatrix[0], 0.0);
  }

  /**
   * Test {@link MultiAssetBlackScholesModel#getCorrelationMatrix()}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link MultiAssetBlackScholesModel#getCorrelationMatrix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] MultiAssetBlackScholesModel.getCorrelationMatrix()"})
  public void testGetCorrelationMatrix_thenReturnArrayLengthIsOne() {
    // Arrange
    double[][] factorLoadings = new double[][] {new double[] {}};
    MultiAssetBlackScholesModel multiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(new double[] {}, 10.0d, factorLoadings);

    // Act
    double[][] actualCorrelationMatrix = multiAssetBlackScholesModel.getCorrelationMatrix();

    // Assert
    assertEquals(1, actualCorrelationMatrix.length);
    assertArrayEquals(new double[] {1.0d}, actualCorrelationMatrix[0], 0.0);
  }

  /**
   * Test {@link MultiAssetBlackScholesModel#getCorrelationMatrix()}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link MultiAssetBlackScholesModel#getCorrelationMatrix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] MultiAssetBlackScholesModel.getCorrelationMatrix()"})
  public void testGetCorrelationMatrix_thenReturnArrayLengthIsTwo() {
    // Arrange
    MultiAssetBlackScholesModel multiAssetBlackScholesModel =
        new MultiAssetBlackScholesModel(
            new double[] {}, 10.0d, new double[][] {new double[] {}, new double[] {}});

    // Act
    double[][] actualCorrelationMatrix = multiAssetBlackScholesModel.getCorrelationMatrix();

    // Assert
    assertEquals(2, actualCorrelationMatrix.length);
    assertArrayEquals(new double[] {0.0d, 1.0d}, actualCorrelationMatrix[1], 0.0);
    assertArrayEquals(new double[] {1.0d, 0.0d}, actualCorrelationMatrix[0], 0.0);
  }
}
