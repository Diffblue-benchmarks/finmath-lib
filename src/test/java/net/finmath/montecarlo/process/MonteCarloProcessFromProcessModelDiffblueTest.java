package net.finmath.montecarlo.process;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.montecarlo.BrownianBridge;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BlackScholesModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BlackScholesModelWithCurves;
import net.finmath.montecarlo.assetderivativevaluation.models.InhomogeneousDisplacedLognomalModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel.Scheme;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MonteCarloProcessFromProcessModelDiffblueTest {
  /**
   * Test {@link MonteCarloProcessFromProcessModel#getModel()}.
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.montecarlo.model.ProcessModel MonteCarloProcessFromProcessModel.getModel()"
  })
  public void testGetModel() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act and Assert
    assertSame(model, eulerSchemeFromProcessModel.getModel());
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getNumberOfComponents()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getNumberOfComponents()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MonteCarloProcessFromProcessModel.getNumberOfComponents()"})
  public void testGetNumberOfComponents_thenReturnOne() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act and Assert
    assertEquals(1, eulerSchemeFromProcessModel.getNumberOfComponents());
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getInitialState()}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getInitialState()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] MonteCarloProcessFromProcessModel.getInitialState()"})
  public void testGetInitialState_thenFirstElementReturnScalar() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = eulerSchemeFromProcessModel.getInitialState();

    // Assert
    assertTrue(actualInitialState[0] instanceof Scalar);
    assertEquals(1, actualInitialState.length);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getDrift(int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getDrift(int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] MonteCarloProcessFromProcessModel.getDrift(int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenFirstElementReturnRandomVariableFromDoubleArray() {
    // Arrange
    InhomogeneousDisplacedLognomalModel model =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d, true);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualDrift =
        eulerSchemeFromProcessModel.getDrift(
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualDrift[0] instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualDrift.length);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getDrift(int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getDrift(int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] MonteCarloProcessFromProcessModel.getDrift(int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenFirstElementReturnRandomVariableFromFloatArray() {
    // Arrange
    DiscountCurveInterpolation discountCurveForForwardRate = mock(DiscountCurveInterpolation.class);
    when(discountCurveForForwardRate.getDiscountFactor(anyDouble())).thenReturn(10.0d);
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BlackScholesModelWithCurves model =
        new BlackScholesModelWithCurves(
            10.0d,
            discountCurveForForwardRate,
            10.0d,
            discountCurveForDiscountRate,
            new RandomVariableFloatFactory());
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualDrift =
        eulerSchemeFromProcessModel.getDrift(
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(discountCurveForForwardRate, atLeast(1)).getDiscountFactor(anyDouble());
    assertTrue(actualDrift[0] instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualDrift.length);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getDrift(int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@code -4.979356714195415E-45}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getDrift(int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] MonteCarloProcessFromProcessModel.getDrift(int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenReturnFirstElementAverageIs4979356714195415e45() {
    // Arrange
    InhomogeneousDisplacedLognomalModel model =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualDrift =
        eulerSchemeFromProcessModel.getDrift(
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualDrift[0];
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(-4.979356714195415E-45d, randomVariable.getAverage(), 0.0);
    assertEquals(-4.979356714195415E-45d, randomVariable.getMax(), 0.0);
    assertEquals(-4.979356714195415E-45d, randomVariable.getMin(), 0.0);
    assertEquals(1, actualDrift.length);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getDrift(int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getDrift(int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] MonteCarloProcessFromProcessModel.getDrift(int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenReturnFirstElementAverageIsZero() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualDrift =
        eulerSchemeFromProcessModel.getDrift(
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualDrift[0];
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(0.0d, randomVariable.getAverage(), 0.0);
    assertEquals(0.0d, randomVariable.getMax(), 0.0);
    assertEquals(0.0d, randomVariable.getMin(), 0.0);
    assertEquals(1, actualDrift.length);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getFactorLoading(int, int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] MonteCarloProcessFromProcessModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenFirstElementAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    InhomogeneousDisplacedLognomalModel model =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualFactorLoading =
        eulerSchemeFromProcessModel.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {7.440151952041671E-42d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getFactorLoading(int, int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] MonteCarloProcessFromProcessModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenFirstElementAbsReturnRandomVariableFromDoubleArray2() {
    // Arrange
    InhomogeneousDisplacedLognomalModel model =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization =
        new TenorFromArray(
            new double[] {Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d});
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualFactorLoading =
        eulerSchemeFromProcessModel.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {7.440151952041671E-42d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getFactorLoading(int, int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] MonteCarloProcessFromProcessModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenFirstElementReturnScalar() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualFactorLoading =
        eulerSchemeFromProcessModel.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertEquals(1, actualFactorLoading.length);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getFactorLoading(int, int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then second element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] MonteCarloProcessFromProcessModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenSecondElementReturnRandomVariableFromDoubleArray() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    InhomogeneousDisplacedLognomalModel model =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY);

    // Act
    RandomVariable[] actualFactorLoading =
        eulerSchemeFromProcessModel.getFactorLoading(
            1,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)
            });

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(1);
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromDoubleArray);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#applyStateSpaceTransform(int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 22026.465794806718}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#applyStateSpaceTransform(int,
   * int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MonteCarloProcessFromProcessModel.applyStateSpaceTransform(int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnAverageIs22026465794806718() {
    // Arrange
    BlackScholesModel model =
        new BlackScholesModel(
            Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        eulerSchemeFromProcessModel.applyStateSpaceTransform(
            1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyStateSpaceTransformResult instanceof RandomVariableFromDoubleArray);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {22026.465794806718d},
        actualApplyStateSpaceTransformResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#applyStateSpaceTransform(int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 2.6881171418161355E44}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#applyStateSpaceTransform(int,
   * int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MonteCarloProcessFromProcessModel.applyStateSpaceTransform(int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnAverageIs26881171418161355e44() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        eulerSchemeFromProcessModel.applyStateSpaceTransform(
            1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    assertTrue(actualApplyStateSpaceTransformResult instanceof RandomVariableFromDoubleArray);
    assertEquals(2.6881171418161355E44d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(2.6881171418161355E44d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(2.6881171418161355E44d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {2.6881171418161355E44d},
        actualApplyStateSpaceTransformResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#applyStateSpaceTransform(int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#applyStateSpaceTransform(int,
   * int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MonteCarloProcessFromProcessModel.applyStateSpaceTransform(int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnAverageIsZero() {
    // Arrange
    BachelierModel model =
        new BachelierModel(
            Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion2), Scheme.EULER);

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        eulerSchemeFromProcessModel.applyStateSpaceTransform(
            1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyStateSpaceTransformResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.0d}, actualApplyStateSpaceTransformResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#applyStateSpaceTransformInverse(int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 2.302585092994046}.
   * </ul>
   *
   * <p>Method under test: {@link
   * MonteCarloProcessFromProcessModel#applyStateSpaceTransformInverse(int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MonteCarloProcessFromProcessModel.applyStateSpaceTransformInverse(int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnAverageIs2302585092994046() {
    // Arrange
    BlackScholesModel model =
        new BlackScholesModel(
            Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        eulerSchemeFromProcessModel.applyStateSpaceTransformInverse(
            1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualApplyStateSpaceTransformInverseResult instanceof RandomVariableFromDoubleArray);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {2.302585092994046d},
        actualApplyStateSpaceTransformInverseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#applyStateSpaceTransformInverse(int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 3.720075976020836E-43}.
   * </ul>
   *
   * <p>Method under test: {@link
   * MonteCarloProcessFromProcessModel#applyStateSpaceTransformInverse(int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MonteCarloProcessFromProcessModel.applyStateSpaceTransformInverse(int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnAverageIs3720075976020836e43() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        eulerSchemeFromProcessModel.applyStateSpaceTransformInverse(
            1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult instanceof RandomVariableFromDoubleArray);
    assertEquals(
        3.720075976020836E-43d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(3.720075976020836E-43d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(3.720075976020836E-43d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {3.720075976020836E-43d},
        actualApplyStateSpaceTransformInverseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#applyStateSpaceTransformInverse(int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link
   * MonteCarloProcessFromProcessModel#applyStateSpaceTransformInverse(int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MonteCarloProcessFromProcessModel.applyStateSpaceTransformInverse(int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnAverageIsPositive_infinity() {
    // Arrange
    BachelierModel model =
        new BachelierModel(
            Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion2), Scheme.EULER);

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        eulerSchemeFromProcessModel.applyStateSpaceTransformInverse(
            1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualApplyStateSpaceTransformInverseResult instanceof RandomVariableFromDoubleArray);
    assertEquals(
        Double.POSITIVE_INFINITY, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(
        Double.POSITIVE_INFINITY, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(
        Double.POSITIVE_INFINITY, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY},
        actualApplyStateSpaceTransformInverseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization MonteCarloProcessFromProcessModel.getTimeDiscretization()"
  })
  public void testGetTimeDiscretization() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act and Assert
    assertNull(eulerSchemeFromProcessModel.getTimeDiscretization());
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getTime(int)}.
   *
   * <ul>
   *   <li>Given {@link TimeDiscretization} {@link TimeDiscretization#getTime(int)} return ten.
   *   <li>When one.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double MonteCarloProcessFromProcessModel.getTime(int)"})
  public void testGetTime_givenTimeDiscretizationGetTimeReturnTen_whenOne_thenReturnTen() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    double actualTime = eulerSchemeFromProcessModel.getTime(1);

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    assertEquals(10.0d, actualTime, 0.0);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getTime(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double MonteCarloProcessFromProcessModel.getTime(int)"})
  public void testGetTime_thenReturn105() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act and Assert
    assertEquals(10.5d, eulerSchemeFromProcessModel.getTime(1), 0.0);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getTimeIndex(double)}.
   *
   * <ul>
   *   <li>Given {@link TimeDiscretization} {@link TimeDiscretization#getTimeIndex(double)} return
   *       one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getTimeIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MonteCarloProcessFromProcessModel.getTimeIndex(double)"})
  public void testGetTimeIndex_givenTimeDiscretizationGetTimeIndexReturnOne_thenReturnOne() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    int actualTimeIndex = eulerSchemeFromProcessModel.getTimeIndex(10.0d);

    // Assert
    verify(timeDiscretization).getTimeIndex(10.0d);
    assertEquals(1, actualTimeIndex);
  }

  /**
   * Test {@link MonteCarloProcessFromProcessModel#getTimeIndex(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloProcessFromProcessModel#getTimeIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MonteCarloProcessFromProcessModel.getTimeIndex(double)"})
  public void testGetTimeIndex_thenReturnZero() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act and Assert
    assertEquals(0, eulerSchemeFromProcessModel.getTimeIndex(10.0d));
  }
}
