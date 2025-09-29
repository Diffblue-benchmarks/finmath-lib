package net.finmath.montecarlo.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.montecarlo.BrownianBridge;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.IndependentIncrements;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.model.ProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel.Scheme;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EulerSchemeFromProcessModelDiffblueTest {
  /**
   * Test {@link EulerSchemeFromProcessModel#EulerSchemeFromProcessModel(ProcessModel,
   * IndependentIncrements)}.
   *
   * <p>Method under test: {@link
   * EulerSchemeFromProcessModel#EulerSchemeFromProcessModel(ProcessModel, IndependentIncrements)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EulerSchemeFromProcessModel.<init>(ProcessModel, IndependentIncrements)"
  })
  public void testNewEulerSchemeFromProcessModel() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    // Act
    EulerSchemeFromProcessModel actualEulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Assert
    IndependentIncrements stochasticDriver2 =
        actualEulerSchemeFromProcessModel.getStochasticDriver();
    assertTrue(stochasticDriver2 instanceof BrownianMotionWithControlVariate);
    ProcessModel model2 = actualEulerSchemeFromProcessModel.getModel();
    assertTrue(model2 instanceof BachelierModel);
    TimeDiscretization timeDiscretization2 =
        actualEulerSchemeFromProcessModel.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualEulerSchemeFromProcessModel.getNumberOfComponents());
    assertEquals(1, actualEulerSchemeFromProcessModel.getInitialState().length);
    assertEquals(10, actualEulerSchemeFromProcessModel.getNumberOfPaths());
    assertEquals(3, actualEulerSchemeFromProcessModel.getNumberOfFactors());
    assertEquals(Scheme.EULER_FUNCTIONAL, actualEulerSchemeFromProcessModel.getScheme());
    assertSame(stochasticDriver, stochasticDriver2);
    assertSame(model, model2);
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link EulerSchemeFromProcessModel#EulerSchemeFromProcessModel(ProcessModel,
   * IndependentIncrements, Scheme)}.
   *
   * <p>Method under test: {@link
   * EulerSchemeFromProcessModel#EulerSchemeFromProcessModel(ProcessModel, IndependentIncrements,
   * Scheme)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EulerSchemeFromProcessModel.<init>(ProcessModel, IndependentIncrements, Scheme)"
  })
  public void testNewEulerSchemeFromProcessModel2() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    // Act
    EulerSchemeFromProcessModel actualEulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver, Scheme.EULER);

    // Assert
    IndependentIncrements stochasticDriver2 =
        actualEulerSchemeFromProcessModel.getStochasticDriver();
    assertTrue(stochasticDriver2 instanceof BrownianMotionWithControlVariate);
    ProcessModel model2 = actualEulerSchemeFromProcessModel.getModel();
    assertTrue(model2 instanceof BachelierModel);
    TimeDiscretization timeDiscretization2 =
        actualEulerSchemeFromProcessModel.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualEulerSchemeFromProcessModel.getNumberOfComponents());
    assertEquals(1, actualEulerSchemeFromProcessModel.getInitialState().length);
    assertEquals(10, actualEulerSchemeFromProcessModel.getNumberOfPaths());
    assertEquals(3, actualEulerSchemeFromProcessModel.getNumberOfFactors());
    assertEquals(Scheme.EULER, actualEulerSchemeFromProcessModel.getScheme());
    assertSame(stochasticDriver, stochasticDriver2);
    assertSame(model, model2);
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link EulerSchemeFromProcessModel#getProcessValue(int, int)} with {@code timeIndex},
   * {@code componentIndex}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getProcessValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getProcessValue(int, int)"})
  public void testGetProcessValueWithTimeIndexComponentIndex() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getNumberOfTimeSteps()).thenThrow(new UnsupportedOperationException());
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> eulerSchemeFromProcessModel.getProcessValue(1, 1));
    verify(timeDiscretization).getNumberOfTimeSteps();
  }

  /**
   * Test {@link EulerSchemeFromProcessModel#getProcessValue(int, int)} with {@code timeIndex},
   * {@code componentIndex}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getNumberOfTimes()}.
   * </ul>
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getProcessValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getProcessValue(int, int)"})
  public void testGetProcessValueWithTimeIndexComponentIndex_thenCallsGetNumberOfTimes() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new UnsupportedOperationException());
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> eulerSchemeFromProcessModel.getProcessValue(1, 1));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(0);
  }

  /**
   * Test {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getNumberOfTimeSteps()).thenThrow(new UnsupportedOperationException());
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> eulerSchemeFromProcessModel.getMonteCarloWeights(1));
    verify(timeDiscretization).getNumberOfTimeSteps();
  }

  /**
   * Test {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights2() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new UnsupportedOperationException());
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> eulerSchemeFromProcessModel.getMonteCarloWeights(1));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(0);
  }

  /**
   * Test {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights3() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualMonteCarloWeights = eulerSchemeFromProcessModel.getMonteCarloWeights(1);

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
   * Test {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights4() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers stochasticDriver =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualMonteCarloWeights = eulerSchemeFromProcessModel.getMonteCarloWeights(1);

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
   * Test {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights5() {
    // Arrange
    BachelierModel model = new BachelierModel(1.0d, 1.0d, 1.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable actualMonteCarloWeights = eulerSchemeFromProcessModel.getMonteCarloWeights(1);

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
   * Test {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights6() {
    // Arrange
    BachelierModel model = new BachelierModel(1.0d, 1.0d, 1.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge stochasticDriver = new BrownianBridge(generator, start, end);

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable actualMonteCarloWeights = eulerSchemeFromProcessModel.getMonteCarloWeights(1);

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
   * Test {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights7() {
    // Arrange
    BachelierModel model = new BachelierModel(1.0d, 1.0d, 1.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver, Scheme.PREDICTOR_CORRECTOR);

    // Act
    RandomVariable actualMonteCarloWeights = eulerSchemeFromProcessModel.getMonteCarloWeights(1);

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
   * Test {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights8() {
    // Arrange
    BachelierModel model = new BachelierModel(1.0d, 1.0d, 1.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, stochasticDriver, Scheme.PREDICTOR_CORRECTOR_FUNCTIONAL);

    // Act
    RandomVariable actualMonteCarloWeights = eulerSchemeFromProcessModel.getMonteCarloWeights(1);

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
   * Test {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights9() {
    // Arrange
    BachelierModel model = new BachelierModel(1.0d, 1.0d, 1.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {RandomVariableDifferentiableAAD.of(1.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge stochasticDriver = new BrownianBridge(generator, start, end);

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable actualMonteCarloWeights = eulerSchemeFromProcessModel.getMonteCarloWeights(1);

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
   * Test {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights10() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(1);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            new BachelierModel(1.0d, 1.0d, 1.0d), stochasticDriver, Scheme.PREDICTOR_CORRECTOR);

    // Act
    RandomVariable actualMonteCarloWeights = eulerSchemeFromProcessModel.getMonteCarloWeights(1);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(0);
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
   * Test {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights11() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(1);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            new BachelierModel(1.0d, 1.0d, 1.0d), stochasticDriver, Scheme.EULER_FUNCTIONAL);

    // Act
    RandomVariable actualMonteCarloWeights = eulerSchemeFromProcessModel.getMonteCarloWeights(1);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(0);
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
   * Test {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights12() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(1);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            new BachelierModel(1.0d, 1.0d, 1.0d),
            stochasticDriver,
            Scheme.PREDICTOR_CORRECTOR_FUNCTIONAL);

    // Act
    RandomVariable actualMonteCarloWeights = eulerSchemeFromProcessModel.getMonteCarloWeights(1);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(0);
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
   * Test {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights13() {
    // Arrange
    BachelierModel model = new BachelierModel(1.0d, 1.0d, 1.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {RandomVariableDifferentiableAAD.of(1.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge stochasticDriver = new BrownianBridge(generator, start, end);

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver, Scheme.PREDICTOR_CORRECTOR);

    // Act
    RandomVariable actualMonteCarloWeights = eulerSchemeFromProcessModel.getMonteCarloWeights(1);

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
   * Test {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getTimeStep(int)}.
   * </ul>
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable EulerSchemeFromProcessModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights_thenCallsGetTimeStep() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(1);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            new BachelierModel(1.0d, 1.0d, 1.0d), stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable actualMonteCarloWeights = eulerSchemeFromProcessModel.getMonteCarloWeights(1);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(0);
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
   * Test {@link EulerSchemeFromProcessModel#getNumberOfPaths()}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int EulerSchemeFromProcessModel.getNumberOfPaths()"})
  public void testGetNumberOfPaths() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act and Assert
    assertEquals(10, eulerSchemeFromProcessModel.getNumberOfPaths());
  }

  /**
   * Test {@link EulerSchemeFromProcessModel#getNumberOfFactors()}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int EulerSchemeFromProcessModel.getNumberOfFactors()"})
  public void testGetNumberOfFactors() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act and Assert
    assertEquals(3, eulerSchemeFromProcessModel.getNumberOfFactors());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link EulerSchemeFromProcessModel#toString()}
   *   <li>{@link EulerSchemeFromProcessModel#getScheme()}
   *   <li>{@link EulerSchemeFromProcessModel#getStochasticDriver()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Scheme EulerSchemeFromProcessModel.getScheme()",
    "IndependentIncrements EulerSchemeFromProcessModel.getStochasticDriver()",
    "String EulerSchemeFromProcessModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    eulerSchemeFromProcessModel.toString();
    Scheme actualScheme = eulerSchemeFromProcessModel.getScheme();

    // Assert
    assertEquals(Scheme.EULER_FUNCTIONAL, actualScheme);
    assertSame(stochasticDriver, eulerSchemeFromProcessModel.getStochasticDriver());
  }

  /**
   * Test {@link EulerSchemeFromProcessModel#clone()}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"EulerSchemeFromProcessModel EulerSchemeFromProcessModel.clone()"})
  public void testClone() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    EulerSchemeFromProcessModel actualCloneResult = eulerSchemeFromProcessModel.clone();

    // Assert
    RandomVariable[] initialState = actualCloneResult.getInitialState();
    assertTrue(initialState[0] instanceof Scalar);
    assertEquals(1, actualCloneResult.getNumberOfComponents());
    assertEquals(1, initialState.length);
    assertEquals(10, actualCloneResult.getNumberOfPaths());
    assertEquals(3, actualCloneResult.getNumberOfFactors());
    assertEquals(Scheme.EULER_FUNCTIONAL, actualCloneResult.getScheme());
    assertSame(stochasticDriver, actualCloneResult.getStochasticDriver());
    assertSame(model, actualCloneResult.getModel());
  }

  /**
   * Test {@link EulerSchemeFromProcessModel#getCloneWithModifiedModel(ProcessModel)}.
   *
   * <p>Method under test: {@link
   * EulerSchemeFromProcessModel#getCloneWithModifiedModel(ProcessModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MonteCarloProcess EulerSchemeFromProcessModel.getCloneWithModifiedModel(ProcessModel)"
  })
  public void testGetCloneWithModifiedModel() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver);
    BachelierModel model2 = new BachelierModel(10.0d, 10.0d, 10.0d);

    // Act
    MonteCarloProcess actualCloneWithModifiedModel =
        eulerSchemeFromProcessModel.getCloneWithModifiedModel(model2);

    // Assert
    IndependentIncrements stochasticDriver2 = actualCloneWithModifiedModel.getStochasticDriver();
    assertTrue(stochasticDriver2 instanceof BrownianMotionWithControlVariate);
    ProcessModel model3 = actualCloneWithModifiedModel.getModel();
    assertTrue(model3 instanceof BachelierModel);
    assertTrue(actualCloneWithModifiedModel instanceof EulerSchemeFromProcessModel);
    assertEquals(1, actualCloneWithModifiedModel.getNumberOfComponents());
    assertEquals(
        1, ((EulerSchemeFromProcessModel) actualCloneWithModifiedModel).getInitialState().length);
    assertEquals(10, actualCloneWithModifiedModel.getNumberOfPaths());
    assertEquals(3, actualCloneWithModifiedModel.getNumberOfFactors());
    assertEquals(
        Scheme.EULER_FUNCTIONAL,
        ((EulerSchemeFromProcessModel) actualCloneWithModifiedModel).getScheme());
    assertSame(stochasticDriver, stochasticDriver2);
    assertSame(model2, model3);
  }

  /**
   * Test {@link EulerSchemeFromProcessModel#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MonteCarloProcess EulerSchemeFromProcessModel.getCloneWithModifiedData(Map)"})
  public void testGetCloneWithModifiedData() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);

    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    MonteCarloProcess actualCloneWithModifiedData =
        eulerSchemeFromProcessModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    IndependentIncrements stochasticDriver2 = actualCloneWithModifiedData.getStochasticDriver();
    assertTrue(stochasticDriver2 instanceof BrownianMotionWithControlVariate);
    ProcessModel model2 = actualCloneWithModifiedData.getModel();
    assertTrue(model2 instanceof BachelierModel);
    assertTrue(actualCloneWithModifiedData instanceof EulerSchemeFromProcessModel);
    assertEquals(1, actualCloneWithModifiedData.getNumberOfComponents());
    assertEquals(
        1, ((EulerSchemeFromProcessModel) actualCloneWithModifiedData).getInitialState().length);
    assertEquals(10, actualCloneWithModifiedData.getNumberOfPaths());
    assertEquals(3, actualCloneWithModifiedData.getNumberOfFactors());
    assertEquals(
        Scheme.EULER_FUNCTIONAL,
        ((EulerSchemeFromProcessModel) actualCloneWithModifiedData).getScheme());
    assertSame(stochasticDriver, stochasticDriver2);
    assertSame(model, model2);
  }

  /**
   * Test {@link EulerSchemeFromProcessModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Given {@code model}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MonteCarloProcess EulerSchemeFromProcessModel.getCloneWithModifiedData(Map)"})
  public void testGetCloneWithModifiedData_givenModel_thenThrowIllegalArgumentException() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    HashMap<String, Object> dataModified = new HashMap<>();
    dataModified.put("model", new BachelierModel(10.0d, 10.0d, 10.0d));
    dataModified.put("seed", 1);
    BrownianMotionFromMersenneRandomNumbers brownianMotion2 =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    dataModified.put("stochasticDriver", new BrownianMotionWithControlVariate(brownianMotion2));
    dataModified.put("scheme", Scheme.EULER);
    dataModified.put("seed", "Data Modified");
    dataModified.put("stochasticDriver", "Data Modified");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> eulerSchemeFromProcessModel.getCloneWithModifiedData(dataModified));
  }

  /**
   * Test {@link EulerSchemeFromProcessModel#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link EulerSchemeFromProcessModel#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object EulerSchemeFromProcessModel.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel eulerSchemeFromProcessModel =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    Object actualCloneWithModifiedSeed = eulerSchemeFromProcessModel.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(
        ((EulerSchemeFromProcessModel) actualCloneWithModifiedSeed).getStochasticDriver()
            instanceof BrownianMotionWithControlVariate);
    ProcessModel model2 = ((EulerSchemeFromProcessModel) actualCloneWithModifiedSeed).getModel();
    assertTrue(model2 instanceof BachelierModel);
    assertTrue(actualCloneWithModifiedSeed instanceof EulerSchemeFromProcessModel);
    assertEquals(
        1, ((EulerSchemeFromProcessModel) actualCloneWithModifiedSeed).getNumberOfComponents());
    assertEquals(
        1, ((EulerSchemeFromProcessModel) actualCloneWithModifiedSeed).getInitialState().length);
    assertEquals(
        10, ((EulerSchemeFromProcessModel) actualCloneWithModifiedSeed).getNumberOfPaths());
    assertEquals(
        3, ((EulerSchemeFromProcessModel) actualCloneWithModifiedSeed).getNumberOfFactors());
    assertEquals(
        Scheme.EULER_FUNCTIONAL,
        ((EulerSchemeFromProcessModel) actualCloneWithModifiedSeed).getScheme());
    assertSame(model, model2);
  }
}
