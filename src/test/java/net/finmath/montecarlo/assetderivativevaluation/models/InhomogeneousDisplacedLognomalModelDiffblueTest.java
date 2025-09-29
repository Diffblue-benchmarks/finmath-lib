package net.finmath.montecarlo.assetderivativevaluation.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
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
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromArrayFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel.Scheme;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class InhomogeneousDisplacedLognomalModelDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link
   *       InhomogeneousDisplacedLognomalModel#InhomogeneousDisplacedLognomalModel(RandomVariableFactory,
   *       RandomVariable, RandomVariable, RandomVariable, RandomVariable, boolean)}
   *   <li>{@link InhomogeneousDisplacedLognomalModel#toString()}
   *   <li>{@link InhomogeneousDisplacedLognomalModel#getDisplacement()}
   *   <li>{@link InhomogeneousDisplacedLognomalModel#getInitialValue()}
   *   <li>{@link InhomogeneousDisplacedLognomalModel#getNumberOfComponents()}
   *   <li>{@link InhomogeneousDisplacedLognomalModel#getNumberOfFactors()}
   *   <li>{@link InhomogeneousDisplacedLognomalModel#getRandomVariableFactory()}
   *   <li>{@link InhomogeneousDisplacedLognomalModel#getRiskFreeRate()}
   *   <li>{@link InhomogeneousDisplacedLognomalModel#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InhomogeneousDisplacedLognomalModel.<init>(RandomVariableFactory, RandomVariable, RandomVariable, RandomVariable, RandomVariable, boolean)",
    "RandomVariable InhomogeneousDisplacedLognomalModel.getDisplacement()",
    "RandomVariable InhomogeneousDisplacedLognomalModel.getInitialValue()",
    "int InhomogeneousDisplacedLognomalModel.getNumberOfComponents()",
    "int InhomogeneousDisplacedLognomalModel.getNumberOfFactors()",
    "RandomVariableFactory InhomogeneousDisplacedLognomalModel.getRandomVariableFactory()",
    "RandomVariable InhomogeneousDisplacedLognomalModel.getRiskFreeRate()",
    "RandomVariable InhomogeneousDisplacedLognomalModel.getVolatility()",
    "java.lang.String InhomogeneousDisplacedLognomalModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray displacement = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);

    // Act
    InhomogeneousDisplacedLognomalModel actualInhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(
            randomVariableFactory, initialValue, riskFreeRate, displacement, volatility, true);
    actualInhomogeneousDisplacedLognomalModel.toString();
    RandomVariable actualDisplacement = actualInhomogeneousDisplacedLognomalModel.getDisplacement();
    RandomVariable actualInitialValue = actualInhomogeneousDisplacedLognomalModel.getInitialValue();
    int actualNumberOfComponents =
        actualInhomogeneousDisplacedLognomalModel.getNumberOfComponents();
    int actualNumberOfFactors = actualInhomogeneousDisplacedLognomalModel.getNumberOfFactors();
    RandomVariableFactory actualRandomVariableFactory =
        actualInhomogeneousDisplacedLognomalModel.getRandomVariableFactory();
    RandomVariable actualRiskFreeRate = actualInhomogeneousDisplacedLognomalModel.getRiskFreeRate();
    RandomVariable actualVolatility = actualInhomogeneousDisplacedLognomalModel.getVolatility();

    // Assert
    assertTrue(actualRandomVariableFactory instanceof RandomVariableFloatFactory);
    assertTrue(actualInitialValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRiskFreeRate instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVolatility instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualNumberOfComponents);
    assertEquals(1, actualNumberOfFactors);
    assertSame(randomVariableFactory, actualRandomVariableFactory);
    assertSame(displacement, actualDisplacement);
    assertSame(initialValue, actualInitialValue);
    assertSame(riskFreeRate, actualRiskFreeRate);
    assertSame(volatility, actualVolatility);
  }

  /**
   * Test {@link InhomogeneousDisplacedLognomalModel#InhomogeneousDisplacedLognomalModel(double,
   * double, double, double)}.
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#InhomogeneousDisplacedLognomalModel(double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InhomogeneousDisplacedLognomalModel.<init>(double, double, double, double)"
  })
  public void testNewInhomogeneousDisplacedLognomalModel() {
    // Arrange and Act
    InhomogeneousDisplacedLognomalModel actualInhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(
        actualInhomogeneousDisplacedLognomalModel.getRandomVariableFactory()
            instanceof RandomVariableFromArrayFactory);
    assertTrue(actualInhomogeneousDisplacedLognomalModel.getDisplacement() instanceof Scalar);
    assertTrue(actualInhomogeneousDisplacedLognomalModel.getInitialValue() instanceof Scalar);
    assertTrue(actualInhomogeneousDisplacedLognomalModel.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualInhomogeneousDisplacedLognomalModel.getVolatility() instanceof Scalar);
    assertEquals(1, actualInhomogeneousDisplacedLognomalModel.getNumberOfComponents());
    assertEquals(1, actualInhomogeneousDisplacedLognomalModel.getNumberOfFactors());
  }

  /**
   * Test {@link InhomogeneousDisplacedLognomalModel#InhomogeneousDisplacedLognomalModel(double,
   * double, double, double, boolean)}.
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#InhomogeneousDisplacedLognomalModel(double, double, double,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InhomogeneousDisplacedLognomalModel.<init>(double, double, double, double, boolean)"
  })
  public void testNewInhomogeneousDisplacedLognomalModel2() {
    // Arrange and Act
    InhomogeneousDisplacedLognomalModel actualInhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d, true);

    // Assert
    assertTrue(
        actualInhomogeneousDisplacedLognomalModel.getRandomVariableFactory()
            instanceof RandomVariableFromArrayFactory);
    assertTrue(actualInhomogeneousDisplacedLognomalModel.getDisplacement() instanceof Scalar);
    assertTrue(actualInhomogeneousDisplacedLognomalModel.getInitialValue() instanceof Scalar);
    assertTrue(actualInhomogeneousDisplacedLognomalModel.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualInhomogeneousDisplacedLognomalModel.getVolatility() instanceof Scalar);
    assertEquals(1, actualInhomogeneousDisplacedLognomalModel.getNumberOfComponents());
    assertEquals(1, actualInhomogeneousDisplacedLognomalModel.getNumberOfFactors());
  }

  /**
   * Test {@link
   * InhomogeneousDisplacedLognomalModel#InhomogeneousDisplacedLognomalModel(RandomVariableFactory,
   * double, double, double, double, boolean)}.
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#InhomogeneousDisplacedLognomalModel(RandomVariableFactory,
   * double, double, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InhomogeneousDisplacedLognomalModel.<init>(RandomVariableFactory, double, double, double, double, boolean)"
  })
  public void testNewInhomogeneousDisplacedLognomalModel3() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    // Act
    InhomogeneousDisplacedLognomalModel actualInhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(
            randomVariableFactory, 10.0d, 10.0d, 10.0d, 10.0d, true);

    // Assert
    RandomVariableFactory randomVariableFactory2 =
        actualInhomogeneousDisplacedLognomalModel.getRandomVariableFactory();
    assertTrue(randomVariableFactory2 instanceof RandomVariableFloatFactory);
    assertTrue(
        actualInhomogeneousDisplacedLognomalModel.getDisplacement()
            instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualInhomogeneousDisplacedLognomalModel.getInitialValue()
            instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualInhomogeneousDisplacedLognomalModel.getRiskFreeRate()
            instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualInhomogeneousDisplacedLognomalModel.getVolatility()
            instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualInhomogeneousDisplacedLognomalModel.getNumberOfComponents());
    assertEquals(1, actualInhomogeneousDisplacedLognomalModel.getNumberOfFactors());
    assertSame(randomVariableFactory, randomVariableFactory2);
  }

  /**
   * Test {@link InhomogeneousDisplacedLognomalModel#getInitialState(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] InhomogeneousDisplacedLognomalModel.getInitialState(MonteCarloProcess)"
  })
  public void testGetInitialState() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState =
        inhomogeneousDisplacedLognomalModel.getInitialState(process);

    // Assert
    assertTrue(actualInitialState[0] instanceof Scalar);
    assertEquals(1, actualInitialState.length);
  }

  /**
   * Test {@link InhomogeneousDisplacedLognomalModel#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link InhomogeneousDisplacedLognomalModel#getDrift(MonteCarloProcess,
   * int, RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] InhomogeneousDisplacedLognomalModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenFirstElementReturnScalar() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable[] actualDrift =
        inhomogeneousDisplacedLognomalModel.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    assertTrue(actualDrift[0] instanceof Scalar);
    assertEquals(1, actualDrift.length);
  }

  /**
   * Test {@link InhomogeneousDisplacedLognomalModel#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@code -0.7869386805747328}.
   * </ul>
   *
   * <p>Method under test: {@link InhomogeneousDisplacedLognomalModel#getDrift(MonteCarloProcess,
   * int, RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] InhomogeneousDisplacedLognomalModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenReturnFirstElementAverageIs07869386805747328() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(-1.0d, -1.0d, -1.0d, -1.0d, true);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(-1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualDrift =
        inhomogeneousDisplacedLognomalModel.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualDrift[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(-0.7869386805747328d, randomVariable.getAverage(), 0.0);
    assertEquals(-2.1516297122487784d, randomVariable.getMin(), 0.0);
    assertEquals(0.0d, randomVariable.getFiltrationTime(), 0.0);
    assertEquals(0.6545610383080581d, randomVariable.getStandardError(), 0.0);
    assertEquals(1, actualDrift.length);
    assertEquals(2.069903748658191d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(4.2845015287092325d, randomVariable.getVariance(), 0.0);
    assertEquals(4.76055725412137d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(4.99165196204034d, randomVariable.getMax(), 0.0);
    assertArrayEquals(
        new double[] {
          -2.1516297122487784d,
          -2.112629276917514d,
          -0.1660130361927813d,
          -2.112094529097246d,
          4.99165196204034d,
          -2.1466786382774323d,
          -0.36921946044933146d,
          -0.7553838337871994d,
          -0.9844903303964863d,
          -2.062899950420899d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link InhomogeneousDisplacedLognomalModel#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element FiltrationTime is ten.
   * </ul>
   *
   * <p>Method under test: {@link InhomogeneousDisplacedLognomalModel#getDrift(MonteCarloProcess,
   * int, RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] InhomogeneousDisplacedLognomalModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenReturnFirstElementFiltrationTimeIsTen() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(-1.0d, -1.0d, -1.0d, -1.0d, true);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable[] actualDrift =
        inhomogeneousDisplacedLognomalModel.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    RandomVariable randomVariable = actualDrift[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualDrift.length);
    assertEquals(10.0d, randomVariable.getFiltrationTime(), 0.0);
    assertEquals(Double.NaN, randomVariable.getAverage(), 0.0);
    assertEquals(Double.NaN, randomVariable.getMax(), 0.0);
    assertEquals(Double.NaN, randomVariable.getMin(), 0.0);
    assertEquals(Double.NaN, randomVariable.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, randomVariable.getStandardError(), 0.0);
    assertEquals(Double.NaN, randomVariable.getVariance(), 0.0);
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link InhomogeneousDisplacedLognomalModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] InhomogeneousDisplacedLognomalModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenFirstElementReturnRandomVariableDifferentiableAAD() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    BachelierModel model =
        new BachelierModel(
            Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(model, stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable[] actualFactorLoading =
        inhomogeneousDisplacedLognomalModel.getFactorLoading(
            process,
            1,
            1,
            new RandomVariable[] {RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY)});

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    assertTrue(actualFactorLoading[0] instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualFactorLoading.length);
  }

  /**
   * Test {@link InhomogeneousDisplacedLognomalModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@code 7.440151952041671E-42}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] InhomogeneousDisplacedLognomalModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenReturnFirstElementAverageIs7440151952041671e42() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable[] actualFactorLoading =
        inhomogeneousDisplacedLognomalModel.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertEquals(7.440151952041671E-42d, randomVariable.getAverage(), 0.0);
    assertEquals(7.440151952041671E-42d, randomVariable.getMax(), 0.0);
    assertEquals(7.440151952041671E-42d, randomVariable.getMin(), 0.0);
    assertArrayEquals(new double[] {7.440151952041671E-42d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link InhomogeneousDisplacedLognomalModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is one hundred.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] InhomogeneousDisplacedLognomalModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenReturnFirstElementAverageIsOneHundred() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);
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
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion2), Scheme.EULER);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.div(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.addSumProduct(
            Mockito.<RandomVariable[]>any(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD2.add(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);
    randomVariableAAD2.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)});

    // Act
    RandomVariable[] actualFactorLoading =
        inhomogeneousDisplacedLognomalModel.getFactorLoading(
            process, 1, 1, new RandomVariable[] {randomVariableAAD2});

    // Assert
    verify(randomVariableAAD2).add(isA(RandomVariable.class));
    verify(randomVariableAAD).div(isA(RandomVariable.class));
    verify(randomVariableAAD2)
        .addSumProduct(isA(RandomVariable[].class), isA(RandomVariable[].class));
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertEquals(100.0d, randomVariable.getAverage(), 0.0);
    assertEquals(100.0d, randomVariable.getMax(), 0.0);
    assertEquals(100.0d, randomVariable.getMin(), 0.0);
    assertArrayEquals(new double[] {100.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link InhomogeneousDisplacedLognomalModel#applyStateSpaceTransform(MonteCarloProcess,
   * int, int, RandomVariable)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return Average is {@code 2.6881171418161355E44}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogeneousDisplacedLognomalModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_givenTen_thenReturnAverageIs26881171418161355e44() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        inhomogeneousDisplacedLognomalModel.applyStateSpaceTransform(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

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
   * Test {@link InhomogeneousDisplacedLognomalModel#applyStateSpaceTransform(MonteCarloProcess,
   * int, int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 3.989519570547216E46}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogeneousDisplacedLognomalModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnAverageIs3989519570547216e46() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            new TimeDiscretizationFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        inhomogeneousDisplacedLognomalModel.applyStateSpaceTransform(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyStateSpaceTransformResult instanceof RandomVariableFromDoubleArray);
    assertEquals(3.989519570547216E46d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(3.989519570547216E46d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(3.989519570547216E46d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {3.989519570547216E46d},
        actualApplyStateSpaceTransformResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link InhomogeneousDisplacedLognomalModel#applyStateSpaceTransform(MonteCarloProcess,
   * int, int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogeneousDisplacedLognomalModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnRandomVariableDifferentiableAAD() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            new TimeDiscretizationFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        inhomogeneousDisplacedLognomalModel.applyStateSpaceTransform(process, 1, 1, randomVariable);

    // Assert
    verify(randomVariable).mult(isA(RandomVariable.class));
    assertTrue(actualApplyStateSpaceTransformResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualApplyStateSpaceTransformResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualApplyStateSpaceTransformResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualApplyStateSpaceTransformResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualApplyStateSpaceTransformResult.getValues() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformResult.isNaN() instanceof Scalar);
    assertNull(actualApplyStateSpaceTransformResult.getRealizations());
    assertNull(actualApplyStateSpaceTransformResult.getOperator());
    assertNull(actualApplyStateSpaceTransformResult.getRealizationsStream());
    assertEquals(-0.0d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(-0.0d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(-0.0d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformResult)
            .getGradient()
            .size());
    assertEquals(1, actualApplyStateSpaceTransformResult.size());
    assertEquals(3, actualApplyStateSpaceTransformResult.getTypePriority());
    assertTrue(actualApplyStateSpaceTransformResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformResult.getFiltrationTime(), 0.0);
  }

  /**
   * Test {@link
   * InhomogeneousDisplacedLognomalModel#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 7.440151952041672E-43}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogeneousDisplacedLognomalModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnAverageIs7440151952041672e43() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        inhomogeneousDisplacedLognomalModel.applyStateSpaceTransformInverse(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult instanceof RandomVariableFromDoubleArray);
    assertEquals(
        7.440151952041672E-43d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(7.440151952041672E-43d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(7.440151952041672E-43d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {7.440151952041672E-43d},
        actualApplyStateSpaceTransformInverseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link
   * InhomogeneousDisplacedLognomalModel#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 2.5065674758999532E-45}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogeneousDisplacedLognomalModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnAverageIs25065674758999532e45() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);
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
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion2), Scheme.EULER);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.addSumProduct(
            Mockito.<RandomVariable[]>any(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariable.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)});

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        inhomogeneousDisplacedLognomalModel.applyStateSpaceTransformInverse(
            process, 1, 1, randomVariable);

    // Assert
    verify(randomVariable).add(isA(RandomVariable.class));
    verify(randomVariable).addSumProduct(isA(RandomVariable[].class), isA(RandomVariable[].class));
    assertTrue(
        actualApplyStateSpaceTransformInverseResult instanceof RandomVariableFromDoubleArray);
    assertEquals(
        2.5065674758999532E-45d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(
        2.5065674758999532E-45d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(
        2.5065674758999532E-45d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {2.5065674758999532E-45d},
        actualApplyStateSpaceTransformInverseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link
   * InhomogeneousDisplacedLognomalModel#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#applyStateSpaceTransformInverse(MonteCarloProcess, int,
   * int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogeneousDisplacedLognomalModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnRandomVariableDifferentiableAAD() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    BachelierModel model =
        new BachelierModel(
            Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(model, stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        inhomogeneousDisplacedLognomalModel.applyStateSpaceTransformInverse(
            process, 1, 1, RandomVariableDifferentiableAAD.of(Double.NEGATIVE_INFINITY));

    // Assert
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(1);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualApplyStateSpaceTransformInverseResult.getValues() instanceof Scalar);
    assertTrue(actualApplyStateSpaceTransformInverseResult.isNaN() instanceof Scalar);
    assertNull(actualApplyStateSpaceTransformInverseResult.getRealizations());
    assertNull(actualApplyStateSpaceTransformInverseResult.getOperator());
    assertNull(actualApplyStateSpaceTransformInverseResult.getRealizationsStream());
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualApplyStateSpaceTransformInverseResult)
            .getGradient()
            .size());
    assertEquals(1, actualApplyStateSpaceTransformInverseResult.size());
    assertEquals(3, actualApplyStateSpaceTransformInverseResult.getTypePriority());
    assertTrue(actualApplyStateSpaceTransformInverseResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualApplyStateSpaceTransformInverseResult.getFiltrationTime(),
        0.0);
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
  }

  /**
   * Test {@link InhomogeneousDisplacedLognomalModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogeneousDisplacedLognomalModel.getNumeraire(MonteCarloProcess, double)"
  })
  public void testGetNumeraire() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire =
        inhomogeneousDisplacedLognomalModel.getNumeraire(process, 10.0d);

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
   * Test {@link InhomogeneousDisplacedLognomalModel#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link
   * InhomogeneousDisplacedLognomalModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable InhomogeneousDisplacedLognomalModel.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualRandomVariableForConstant =
        inhomogeneousDisplacedLognomalModel.getRandomVariableForConstant(10.0d);

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
   * Test {@link InhomogeneousDisplacedLognomalModel#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link InhomogeneousDisplacedLognomalModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "InhomogeneousDisplacedLognomalModel InhomogeneousDisplacedLognomalModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData() {
    // Arrange
    InhomogeneousDisplacedLognomalModel inhomogeneousDisplacedLognomalModel =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    InhomogeneousDisplacedLognomalModel actualCloneWithModifiedData =
        inhomogeneousDisplacedLognomalModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(
        actualCloneWithModifiedData.getRandomVariableFactory()
            instanceof RandomVariableFromArrayFactory);
    assertTrue(actualCloneWithModifiedData.getDisplacement() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getInitialValue() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getVolatility() instanceof Scalar);
    assertEquals(1, actualCloneWithModifiedData.getNumberOfComponents());
    assertEquals(1, actualCloneWithModifiedData.getNumberOfFactors());
  }
}
