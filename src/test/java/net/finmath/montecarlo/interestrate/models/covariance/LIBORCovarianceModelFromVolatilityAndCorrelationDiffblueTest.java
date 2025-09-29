package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LIBORCovarianceModelFromVolatilityAndCorrelationDiffblueTest {
  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#LIBORCovarianceModelFromVolatilityAndCorrelation(TimeDiscretization,
   * TimeDiscretization, LIBORVolatilityModel, LIBORCorrelationModel)}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#LIBORCovarianceModelFromVolatilityAndCorrelation(TimeDiscretization,
   * TimeDiscretization, LIBORVolatilityModel, LIBORCorrelationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCovarianceModelFromVolatilityAndCorrelation.<init>(TimeDiscretization, TimeDiscretization, LIBORVolatilityModel, LIBORCorrelationModel)"
  })
  public void testNewLIBORCovarianceModelFromVolatilityAndCorrelation() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    // Act
    LIBORCovarianceModelFromVolatilityAndCorrelation
        actualLiborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Assert
    LIBORCorrelationModel correlationModel2 =
        actualLiborCovarianceModelFromVolatilityAndCorrelation.getCorrelationModel();
    assertTrue(correlationModel2 instanceof LIBORCorrelationModelExponentialDecay);
    LIBORVolatilityModel volatilityModel2 =
        actualLiborCovarianceModelFromVolatilityAndCorrelation.getVolatilityModel();
    assertTrue(volatilityModel2 instanceof LIBORVolatilityModelTwoParameterExponentialForm);
    TimeDiscretization timeDiscretization4 =
        actualLiborCovarianceModelFromVolatilityAndCorrelation.getTimeDiscretization();
    assertTrue(timeDiscretization4 instanceof TenorFromArray);
    assertEquals(2, actualLiborCovarianceModelFromVolatilityAndCorrelation.getParameter().length);
    assertEquals(3, actualLiborCovarianceModelFromVolatilityAndCorrelation.getNumberOfFactors());
    assertSame(correlationModel, correlationModel2);
    assertSame(volatilityModel, volatilityModel2);
    assertSame(
        liborPeriodDiscretization,
        actualLiborCovarianceModelFromVolatilityAndCorrelation.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization4);
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        actualLiborCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelFromVolatilityAndCorrelation.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelFourParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelFourParameterExponentialForm(
            randomVariableFactory,
            timeDiscretization2,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            true);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelFromVolatilityAndCorrelation.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromFloatArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromFloatArray);
    assertTrue(actualFactorLoading[2] instanceof RandomVariableFromFloatArray);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelFromVolatilityAndCorrelation.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay correlationModel =
        new LIBORCorrelationModelThreeParameterExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, 10.0d, 10.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelFromVolatilityAndCorrelation.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertTrue(actualFactorLoading[1] instanceof Scalar);
    assertTrue(actualFactorLoading[2] instanceof Scalar);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelFromVolatilityAndCorrelation.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFloatFactory randomVariableFactoryForNonDifferentiable =
        new RandomVariableFloatFactory();
    RandomVariableDifferentiableAADFactory randomVariableFactory =
        new RandomVariableDifferentiableAADFactory(
            randomVariableFactoryForNonDifferentiable, new HashMap<>());
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelFourParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelFourParameterExponentialForm(
            randomVariableFactory,
            timeDiscretization2,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            true);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelFromVolatilityAndCorrelation.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFactorLoading[2] instanceof RandomVariableDifferentiableAAD);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelFromVolatilityAndCorrelation.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(0.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelFromVolatilityAndCorrelation.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertTrue(actualFactorLoading[1] instanceof Scalar);
    assertTrue(actualFactorLoading[2] instanceof Scalar);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelFromVolatilityAndCorrelation.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable_thenFirstElementReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelFromVolatilityAndCorrelation.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertTrue(actualFactorLoading[1] instanceof Scalar);
    assertTrue(actualFactorLoading[2] instanceof Scalar);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoadingPseudoInverse(int,
   * int, int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return Average is {@code 3.9076097708226727E37}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoadingPseudoInverse(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORCovarianceModelFromVolatilityAndCorrelation.getFactorLoadingPseudoInverse(int, int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingPseudoInverse_thenReturnAverageIs39076097708226727e37() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TimeDiscretizationFromArray timeDiscretization2 =
        new TimeDiscretizationFromArray(1.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable actualFactorLoadingPseudoInverse =
        liborCovarianceModelFromVolatilityAndCorrelation.getFactorLoadingPseudoInverse(
            1, 1, 3, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoadingPseudoInverse instanceof Scalar);
    assertEquals(3.9076097708226727E37d, actualFactorLoadingPseudoInverse.getAverage(), 0.0);
    assertEquals(3.9076097708226727E37d, actualFactorLoadingPseudoInverse.getMax(), 0.0);
    assertEquals(3.9076097708226727E37d, actualFactorLoadingPseudoInverse.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualFactorLoadingPseudoInverse.expectation();
    assertSame(actualFactorLoadingPseudoInverse, actualExpectationResult);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoadingPseudoInverse(int,
   * int, int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return Average is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoadingPseudoInverse(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORCovarianceModelFromVolatilityAndCorrelation.getFactorLoadingPseudoInverse(int, int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingPseudoInverse_thenReturnAverageIsNaN() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(1.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay correlationModel =
        new LIBORCorrelationModelThreeParameterExponentialDecay(
            timeDiscretization3, new TenorFromArray(1.0d, 10, 0.5d), 10, 1.0d, 1.0d, 1.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable actualFactorLoadingPseudoInverse =
        liborCovarianceModelFromVolatilityAndCorrelation.getFactorLoadingPseudoInverse(
            1, 1, 3, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoadingPseudoInverse instanceof Scalar);
    assertEquals(Double.NaN, actualFactorLoadingPseudoInverse.getAverage(), 0.0);
    assertEquals(Double.NaN, actualFactorLoadingPseudoInverse.getMax(), 0.0);
    assertEquals(Double.NaN, actualFactorLoadingPseudoInverse.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualFactorLoadingPseudoInverse.expectation();
    assertSame(actualFactorLoadingPseudoInverse, actualExpectationResult);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoadingPseudoInverse(int,
   * int, int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return Average is {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoadingPseudoInverse(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORCovarianceModelFromVolatilityAndCorrelation.getFactorLoadingPseudoInverse(int, int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingPseudoInverse_thenReturnAverageIsPositive_infinity() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable actualFactorLoadingPseudoInverse =
        liborCovarianceModelFromVolatilityAndCorrelation.getFactorLoadingPseudoInverse(
            1, 1, 3, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoadingPseudoInverse instanceof Scalar);
    assertEquals(Double.POSITIVE_INFINITY, actualFactorLoadingPseudoInverse.getAverage(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualFactorLoadingPseudoInverse.getMax(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualFactorLoadingPseudoInverse.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualFactorLoadingPseudoInverse.expectation();
    assertSame(actualFactorLoadingPseudoInverse, actualExpectationResult);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoadingPseudoInverse(int,
   * int, int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getFactorLoadingPseudoInverse(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORCovarianceModelFromVolatilityAndCorrelation.getFactorLoadingPseudoInverse(int, int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingPseudoInverse_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization2 = new TenorFromArray(1.0d, 10, 0.5d);

    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            randomVariableFactory,
            timeDiscretization2,
            new TenorFromArray(1.0d, 10, 0.5d),
            1.0d,
            1.0d,
            true);
    TenorFromArray timeDiscretization3 = new TenorFromArray(1.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay correlationModel =
        new LIBORCorrelationModelThreeParameterExponentialDecay(
            timeDiscretization3, new TenorFromArray(1.0d, 10, 0.5d), 10, 1.0d, 1.0d, 1.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable actualFactorLoadingPseudoInverse =
        liborCovarianceModelFromVolatilityAndCorrelation.getFactorLoadingPseudoInverse(
            1, 1, 3, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoadingPseudoInverse instanceof RandomVariableFromFloatArray);
    assertTrue(actualFactorLoadingPseudoInverse.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFactorLoadingPseudoInverse.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFactorLoadingPseudoInverse.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualFactorLoadingPseudoInverse.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFactorLoadingPseudoInverse.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFactorLoadingPseudoInverse.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFactorLoadingPseudoInverse.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFactorLoadingPseudoInverse.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFactorLoadingPseudoInverse.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFactorLoadingPseudoInverse.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualFactorLoadingPseudoInverse.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualFactorLoadingPseudoInverse.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualFactorLoadingPseudoInverse.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualFactorLoadingPseudoInverse.getStandardError(), 0.0);
    assertEquals(0.0d, actualFactorLoadingPseudoInverse.getVariance(), 0.0);
    assertEquals(1, actualFactorLoadingPseudoInverse.getTypePriority());
    assertEquals(1, actualFactorLoadingPseudoInverse.size());
    assertTrue(actualFactorLoadingPseudoInverse.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualFactorLoadingPseudoInverse.getFiltrationTime(), 0.0);
    assertEquals(Double.NaN, actualFactorLoadingPseudoInverse.getAverage(), 0.0);
    assertEquals(Double.NaN, actualFactorLoadingPseudoInverse.getMax(), 0.0);
    assertEquals(Double.NaN, actualFactorLoadingPseudoInverse.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.NaN}, actualFactorLoadingPseudoInverse.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getCovariance(int, int, int,
   * RandomVariable[])} with {@code timeIndex}, {@code component1}, {@code component2}, {@code
   * realizationAtTimeIndex}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCovariance(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORCovarianceModelFromVolatilityAndCorrelation.getCovariance(int, int, int, RandomVariable[])"
  })
  public void testGetCovarianceWithTimeIndexComponent1Component2RealizationAtTimeIndex() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable actualCovariance =
        liborCovarianceModelFromVolatilityAndCorrelation.getCovariance(
            1, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualCovariance instanceof Scalar);
    assertTrue(actualCovariance.abs() instanceof Scalar);
    assertTrue(actualCovariance.cos() instanceof Scalar);
    assertTrue(actualCovariance.exp() instanceof Scalar);
    assertTrue(actualCovariance.expm1() instanceof Scalar);
    assertTrue(actualCovariance.invert() instanceof Scalar);
    assertTrue(actualCovariance.isNaN() instanceof Scalar);
    assertTrue(actualCovariance.sin() instanceof Scalar);
    assertTrue(actualCovariance.sqrt() instanceof Scalar);
    assertTrue(actualCovariance.squared() instanceof Scalar);
    assertTrue(actualCovariance.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualCovariance.expectation();
    assertSame(actualCovariance, actualExpectationResult);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getCovariance(int, int, int,
   * RandomVariable[])} with {@code timeIndex}, {@code component1}, {@code component2}, {@code
   * realizationAtTimeIndex}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCovariance(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORCovarianceModelFromVolatilityAndCorrelation.getCovariance(int, int, int, RandomVariable[])"
  })
  public void testGetCovarianceWithTimeIndexComponent1Component2RealizationAtTimeIndex2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialFormIntegrated volatilityModel =
        new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
            timeDiscretization2,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            true);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable actualCovariance =
        liborCovarianceModelFromVolatilityAndCorrelation.getCovariance(
            1, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualCovariance instanceof Scalar);
    assertTrue(actualCovariance.abs() instanceof Scalar);
    assertTrue(actualCovariance.cos() instanceof Scalar);
    assertTrue(actualCovariance.exp() instanceof Scalar);
    assertTrue(actualCovariance.expm1() instanceof Scalar);
    assertTrue(actualCovariance.invert() instanceof Scalar);
    assertTrue(actualCovariance.isNaN() instanceof Scalar);
    assertTrue(actualCovariance.sin() instanceof Scalar);
    assertTrue(actualCovariance.sqrt() instanceof Scalar);
    assertTrue(actualCovariance.squared() instanceof Scalar);
    assertTrue(actualCovariance.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualCovariance.expectation();
    assertSame(actualCovariance, actualExpectationResult);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getCovariance(int, int, int,
   * RandomVariable[])} with {@code timeIndex}, {@code component1}, {@code component2}, {@code
   * realizationAtTimeIndex}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCovariance(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORCovarianceModelFromVolatilityAndCorrelation.getCovariance(int, int, int, RandomVariable[])"
  })
  public void testGetCovarianceWithTimeIndexComponent1Component2RealizationAtTimeIndex3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelFourParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelFourParameterExponentialForm(
            randomVariableFactory,
            timeDiscretization2,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            true);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable actualCovariance =
        liborCovarianceModelFromVolatilityAndCorrelation.getCovariance(
            1, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualCovariance instanceof RandomVariableFromFloatArray);
    assertTrue(actualCovariance.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCovariance.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCovariance.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCovariance.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCovariance.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCovariance.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCovariance.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCovariance.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCovariance.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCovariance.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCovariance.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualCovariance.getAverage(), 0.0);
    assertEquals(0.0d, actualCovariance.getMax(), 0.0);
    assertEquals(0.0d, actualCovariance.getMin(), 0.0);
    assertEquals(0.0d, actualCovariance.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCovariance.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCovariance.getStandardError(), 0.0);
    assertEquals(0.0d, actualCovariance.getVariance(), 0.0);
    assertEquals(1, actualCovariance.getTypePriority());
    assertEquals(1, actualCovariance.size());
    assertTrue(actualCovariance.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualCovariance.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualCovariance.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getCovariance(int, int, int,
   * RandomVariable[])} with {@code timeIndex}, {@code component1}, {@code component2}, {@code
   * realizationAtTimeIndex}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCovariance(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORCovarianceModelFromVolatilityAndCorrelation.getCovariance(int, int, int, RandomVariable[])"
  })
  public void testGetCovarianceWithTimeIndexComponent1Component2RealizationAtTimeIndex4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay correlationModel =
        new LIBORCorrelationModelThreeParameterExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, 10.0d, 10.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable actualCovariance =
        liborCovarianceModelFromVolatilityAndCorrelation.getCovariance(
            1, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualCovariance instanceof Scalar);
    assertTrue(actualCovariance.abs() instanceof Scalar);
    assertTrue(actualCovariance.cos() instanceof Scalar);
    assertTrue(actualCovariance.exp() instanceof Scalar);
    assertTrue(actualCovariance.expm1() instanceof Scalar);
    assertTrue(actualCovariance.invert() instanceof Scalar);
    assertTrue(actualCovariance.isNaN() instanceof Scalar);
    assertTrue(actualCovariance.sin() instanceof Scalar);
    assertTrue(actualCovariance.sqrt() instanceof Scalar);
    assertTrue(actualCovariance.squared() instanceof Scalar);
    assertTrue(actualCovariance.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualCovariance.expectation();
    assertSame(actualCovariance, actualExpectationResult);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getCovariance(int, int, int,
   * RandomVariable[])} with {@code timeIndex}, {@code component1}, {@code component2}, {@code
   * realizationAtTimeIndex}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCovariance(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORCovarianceModelFromVolatilityAndCorrelation.getCovariance(int, int, int, RandomVariable[])"
  })
  public void testGetCovarianceWithTimeIndexComponent1Component2RealizationAtTimeIndex5() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFloatFactory randomVariableFactoryForNonDifferentiable =
        new RandomVariableFloatFactory();
    RandomVariableDifferentiableAADFactory randomVariableFactory =
        new RandomVariableDifferentiableAADFactory(
            randomVariableFactoryForNonDifferentiable, new HashMap<>());
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelFourParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelFourParameterExponentialForm(
            randomVariableFactory,
            timeDiscretization2,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            true);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable actualCovariance =
        liborCovarianceModelFromVolatilityAndCorrelation.getCovariance(
            1, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualCovariance.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCovariance.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCovariance).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCovariance).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCovariance).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCovariance).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCovariance)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCovariance).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCovariance).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCovariance instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCovariance.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCovariance.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCovariance.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualCovariance.getAverage(), 0.0);
    assertEquals(0.0d, actualCovariance.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualCovariance.getMax(), 0.0);
    assertEquals(0.0d, actualCovariance.getMin(), 0.0);
    assertEquals(0.0d, actualCovariance.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCovariance.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCovariance.getStandardError(), 0.0);
    assertEquals(0.0d, actualCovariance.getVariance(), 0.0);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualCovariance).getGradient().size());
    assertEquals(1, actualCovariance.size());
    assertEquals(3, actualCovariance.getTypePriority());
    assertTrue(actualCovariance.isDeterministic());
    assertSame(
        randomVariableFactory, ((RandomVariableDifferentiableAAD) actualCovariance).getFactory());
    assertArrayEquals(new double[] {0.0d}, actualCovariance.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getCovariance(int, int, int,
   * RandomVariable[])} with {@code timeIndex}, {@code component1}, {@code component2}, {@code
   * realizationAtTimeIndex}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCovariance(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORCovarianceModelFromVolatilityAndCorrelation.getCovariance(int, int, int, RandomVariable[])"
  })
  public void testGetCovarianceWithTimeIndexComponent1Component2RealizationAtTimeIndex6() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(0.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable actualCovariance =
        liborCovarianceModelFromVolatilityAndCorrelation.getCovariance(
            1, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualCovariance instanceof Scalar);
    assertEquals(1.3838965267367375E-85d, actualCovariance.getAverage(), 0.0);
    assertEquals(1.3838965267367375E-85d, actualCovariance.getMax(), 0.0);
    assertEquals(1.3838965267367375E-85d, actualCovariance.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualCovariance.expectation();
    assertSame(actualCovariance, actualExpectationResult);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getCovariance(int, int, int,
   * RandomVariable[])} with {@code timeIndex}, {@code component1}, {@code component2}, {@code
   * realizationAtTimeIndex}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCovariance(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORCovarianceModelFromVolatilityAndCorrelation.getCovariance(int, int, int, RandomVariable[])"
  })
  public void testGetCovarianceWithTimeIndexComponent1Component2RealizationAtTimeIndex7() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(0.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialFormIntegrated volatilityModel =
        new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
            timeDiscretization2,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            true);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable actualCovariance =
        liborCovarianceModelFromVolatilityAndCorrelation.getCovariance(
            1, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualCovariance instanceof Scalar);
    assertEquals(100.00000000000021d, actualCovariance.getAverage(), 0.0);
    assertEquals(100.00000000000021d, actualCovariance.getMax(), 0.0);
    assertEquals(100.00000000000021d, actualCovariance.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualCovariance.expectation();
    assertSame(actualCovariance, actualExpectationResult);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameter()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelFromVolatilityAndCorrelation.getParameter()"
  })
  public void testGetParameter() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix volatilityModel =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), volatility);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act and Assert
    assertEquals(0, liborCovarianceModelFromVolatilityAndCorrelation.getParameter().length);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameter()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelFromVolatilityAndCorrelation.getParameter()"
  })
  public void testGetParameter2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix volatilityModel =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization2,
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            volatility);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act and Assert
    assertEquals(0, liborCovarianceModelFromVolatilityAndCorrelation.getParameter().length);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameter()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelFromVolatilityAndCorrelation.getParameter()"
  })
  public void testGetParameter3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 =
        new TenorFromArray(new double[] {10.0d, 10.5d, 10.0d, 10.5d});
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix volatilityModel =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization2, liborPeriodDiscretization2, volatility);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act and Assert
    assertEquals(0, liborCovarianceModelFromVolatilityAndCorrelation.getParameter().length);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameter()}.
   *
   * <ul>
   *   <li>Then fourth element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelFromVolatilityAndCorrelation.getParameter()"
  })
  public void testGetParameter_thenFourthElementReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay correlationModel =
        new LIBORCorrelationModelThreeParameterExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, 10.0d, 10.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable[] actualParameter =
        liborCovarianceModelFromVolatilityAndCorrelation.getParameter();

    // Assert
    assertTrue(actualParameter[2] instanceof Scalar);
    assertTrue(actualParameter[3] instanceof Scalar);
    assertTrue(actualParameter[4] instanceof Scalar);
    assertEquals(5, actualParameter.length);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is three.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelFromVolatilityAndCorrelation.getParameter()"
  })
  public void testGetParameter_thenReturnArrayLengthIsThree() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable[] actualParameter =
        liborCovarianceModelFromVolatilityAndCorrelation.getParameter();

    // Assert
    assertTrue(actualParameter[0] instanceof Scalar);
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[2] instanceof Scalar);
    assertEquals(3, actualParameter.length);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelFromVolatilityAndCorrelation.getParameter()"
  })
  public void testGetParameter_thenReturnArrayLengthIsTwo() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    RandomVariable[] actualParameter =
        liborCovarianceModelFromVolatilityAndCorrelation.getParameter();

    // Assert
    assertTrue(actualParameter[0] instanceof Scalar);
    assertTrue(actualParameter[1] instanceof Scalar);
    assertEquals(2, actualParameter.length);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
                .getVolatilityModel()
            instanceof LIBORVolatilityModelTwoParameterExponentialForm);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(2, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 0.5d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant volatilityModel =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization2,
            liborPeriodDiscretization2,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    LIBORCorrelationModel correlationModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getCorrelationModel();
    assertTrue(correlationModel2 instanceof LIBORCorrelationModelExponentialDecay);
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    LIBORVolatilityModel volatilityModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getVolatilityModel();
    assertTrue(volatilityModel2 instanceof LIBORVolatilityModelPiecewiseConstant);
    TimeDiscretization liborPeriodDiscretization3 =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization4 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization4 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization4 =
        correlationModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization4 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization5 = correlationModel2.getTimeDiscretization();
    assertTrue(timeDiscretization5 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization5 = volatilityModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization5 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization6 = volatilityModel2.getTimeDiscretization();
    assertTrue(timeDiscretization6 instanceof TenorFromArray);
    TimeDiscretization simulationTimeDiscretization2 =
        ((LIBORVolatilityModelPiecewiseConstant) volatilityModel2)
            .getSimulationTimeDiscretization();
    assertTrue(simulationTimeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeToMaturityDiscretization =
        ((LIBORVolatilityModelPiecewiseConstant) volatilityModel2)
            .getTimeToMaturityDiscretization();
    assertTrue(timeToMaturityDiscretization instanceof TenorFromArray);
    assertEquals(timeDiscretization4, liborPeriodDiscretization4);
    assertEquals(timeDiscretization4, timeDiscretization5);
    assertEquals(timeDiscretization4, liborPeriodDiscretization5);
    assertEquals(timeDiscretization4, timeDiscretization6);
    assertEquals(timeDiscretization4, simulationTimeDiscretization2);
    assertEquals(timeDiscretization4, timeToMaturityDiscretization);
    assertArrayEquals(
        new double[] {}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization3.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization4.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(3, actualCloneWithModifiedParameters.getParameter().length);
    assertSame(
        correlationModel,
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getCorrelationModel());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix volatilityModel =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), volatility);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    LIBORCorrelationModel correlationModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getCorrelationModel();
    assertTrue(correlationModel2 instanceof LIBORCorrelationModelExponentialDecay);
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization4 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization4 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 =
        correlationModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization5 = correlationModel2.getTimeDiscretization();
    assertTrue(timeDiscretization5 instanceof TenorFromArray);
    assertEquals(timeDiscretization4, liborPeriodDiscretization3);
    assertEquals(timeDiscretization4, timeDiscretization5);
    assertSame(
        volatilityModel,
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getVolatilityModel());
    assertArrayEquals(
        new double[] {}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization4.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble5() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix volatilityModel =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization2,
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            volatility);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    LIBORCorrelationModel correlationModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getCorrelationModel();
    assertTrue(correlationModel2 instanceof LIBORCorrelationModelExponentialDecay);
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization4 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization4 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 =
        correlationModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization5 = correlationModel2.getTimeDiscretization();
    assertTrue(timeDiscretization5 instanceof TenorFromArray);
    assertEquals(timeDiscretization4, liborPeriodDiscretization3);
    assertEquals(timeDiscretization4, timeDiscretization5);
    assertSame(
        volatilityModel,
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getVolatilityModel());
    assertArrayEquals(
        new double[] {}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization4.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble6() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 =
        new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix volatilityModel =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization2, liborPeriodDiscretization2, volatility);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    LIBORCorrelationModel correlationModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getCorrelationModel();
    assertTrue(correlationModel2 instanceof LIBORCorrelationModelExponentialDecay);
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    TimeDiscretization liborPeriodDiscretization3 =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization4 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization4 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization4 =
        correlationModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization4 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization5 = correlationModel2.getTimeDiscretization();
    assertTrue(timeDiscretization5 instanceof TenorFromArray);
    assertEquals(timeDiscretization4, liborPeriodDiscretization4);
    assertEquals(timeDiscretization4, timeDiscretization5);
    assertSame(
        volatilityModel,
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getVolatilityModel());
    assertArrayEquals(
        new double[] {}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization3.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization4.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble7() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant volatilityModel =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization2,
            liborPeriodDiscretization2,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay correlationModel =
        new LIBORCorrelationModelThreeParameterExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, 10.0d, 10.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
                .getCorrelationModel()
            instanceof LIBORCorrelationModelThreeParameterExponentialDecay);
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
                .getVolatilityModel()
            instanceof LIBORVolatilityModelPiecewiseConstant);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(3, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble8() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 2.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
                .getVolatilityModel()
            instanceof LIBORVolatilityModelTwoParameterExponentialForm);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(2, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 0.5d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant volatilityModel =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization2,
            liborPeriodDiscretization2,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    LIBORCorrelationModel correlationModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getCorrelationModel();
    assertTrue(correlationModel2 instanceof LIBORCorrelationModelExponentialDecay);
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    LIBORVolatilityModel volatilityModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getVolatilityModel();
    assertTrue(volatilityModel2 instanceof LIBORVolatilityModelPiecewiseConstant);
    TimeDiscretization liborPeriodDiscretization3 =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization4 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization4 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization4 =
        correlationModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization4 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization5 = correlationModel2.getTimeDiscretization();
    assertTrue(timeDiscretization5 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization5 = volatilityModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization5 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization6 = volatilityModel2.getTimeDiscretization();
    assertTrue(timeDiscretization6 instanceof TenorFromArray);
    TimeDiscretization simulationTimeDiscretization2 =
        ((LIBORVolatilityModelPiecewiseConstant) volatilityModel2)
            .getSimulationTimeDiscretization();
    assertTrue(simulationTimeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeToMaturityDiscretization =
        ((LIBORVolatilityModelPiecewiseConstant) volatilityModel2)
            .getTimeToMaturityDiscretization();
    assertTrue(timeToMaturityDiscretization instanceof TenorFromArray);
    assertEquals(timeDiscretization4, liborPeriodDiscretization4);
    assertEquals(timeDiscretization4, timeDiscretization5);
    assertEquals(timeDiscretization4, liborPeriodDiscretization5);
    assertEquals(timeDiscretization4, timeDiscretization6);
    assertEquals(timeDiscretization4, simulationTimeDiscretization2);
    assertEquals(timeDiscretization4, timeToMaturityDiscretization);
    assertArrayEquals(
        new double[] {}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization3.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization4.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
                .getVolatilityModel()
            instanceof LIBORVolatilityModelTwoParameterExponentialForm);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(2, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix volatilityModel =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), volatility);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    LIBORCorrelationModel correlationModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getCorrelationModel();
    assertTrue(correlationModel2 instanceof LIBORCorrelationModelExponentialDecay);
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization4 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization4 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 =
        correlationModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization5 = correlationModel2.getTimeDiscretization();
    assertTrue(timeDiscretization5 instanceof TenorFromArray);
    assertEquals(timeDiscretization4, liborPeriodDiscretization3);
    assertEquals(timeDiscretization4, timeDiscretization5);
    assertSame(
        volatilityModel,
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getVolatilityModel());
    assertArrayEquals(
        new double[] {}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization4.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix volatilityModel =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization2,
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            volatility);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    LIBORCorrelationModel correlationModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getCorrelationModel();
    assertTrue(correlationModel2 instanceof LIBORCorrelationModelExponentialDecay);
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization4 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization4 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization3 =
        correlationModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization5 = correlationModel2.getTimeDiscretization();
    assertTrue(timeDiscretization5 instanceof TenorFromArray);
    assertEquals(timeDiscretization4, liborPeriodDiscretization3);
    assertEquals(timeDiscretization4, timeDiscretization5);
    assertSame(
        volatilityModel,
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getVolatilityModel());
    assertArrayEquals(
        new double[] {}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization4.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable5() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 =
        new TenorFromArray(new double[] {10.0d, 10.5d, 10.0d, 10.5d});
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix volatilityModel =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization2, liborPeriodDiscretization2, volatility);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    LIBORCorrelationModel correlationModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getCorrelationModel();
    assertTrue(correlationModel2 instanceof LIBORCorrelationModelExponentialDecay);
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    TimeDiscretization liborPeriodDiscretization3 =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization4 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization4 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization4 =
        correlationModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization4 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization5 = correlationModel2.getTimeDiscretization();
    assertTrue(timeDiscretization5 instanceof TenorFromArray);
    assertEquals(timeDiscretization4, liborPeriodDiscretization4);
    assertEquals(timeDiscretization4, timeDiscretization5);
    assertSame(
        volatilityModel,
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getVolatilityModel());
    assertArrayEquals(
        new double[] {}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization3.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization4.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable6() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant volatilityModel =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization2,
            liborPeriodDiscretization2,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 2.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    LIBORCorrelationModel correlationModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getCorrelationModel();
    assertTrue(correlationModel2 instanceof LIBORCorrelationModelExponentialDecay);
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    LIBORVolatilityModel volatilityModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getVolatilityModel();
    assertTrue(volatilityModel2 instanceof LIBORVolatilityModelPiecewiseConstant);
    TimeDiscretization liborPeriodDiscretization3 =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization4 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization4 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization4 =
        correlationModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization4 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization5 = correlationModel2.getTimeDiscretization();
    assertTrue(timeDiscretization5 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization5 = volatilityModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization5 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization6 = volatilityModel2.getTimeDiscretization();
    assertTrue(timeDiscretization6 instanceof TenorFromArray);
    TimeDiscretization simulationTimeDiscretization2 =
        ((LIBORVolatilityModelPiecewiseConstant) volatilityModel2)
            .getSimulationTimeDiscretization();
    assertTrue(simulationTimeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeToMaturityDiscretization =
        ((LIBORVolatilityModelPiecewiseConstant) volatilityModel2)
            .getTimeToMaturityDiscretization();
    assertTrue(timeToMaturityDiscretization instanceof TenorFromArray);
    assertEquals(timeDiscretization4, liborPeriodDiscretization4);
    assertEquals(timeDiscretization4, timeDiscretization5);
    assertEquals(timeDiscretization4, liborPeriodDiscretization5);
    assertEquals(timeDiscretization4, timeDiscretization6);
    assertEquals(timeDiscretization4, simulationTimeDiscretization2);
    assertEquals(timeDiscretization4, timeToMaturityDiscretization);
    assertArrayEquals(
        new double[] {}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization3.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization4.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable7() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant volatilityModel =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization2,
            liborPeriodDiscretization2,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay correlationModel =
        new LIBORCorrelationModelThreeParameterExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, 10.0d, 10.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
                .getCorrelationModel()
            instanceof LIBORCorrelationModelThreeParameterExponentialDecay);
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(3, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable8() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant volatilityModel =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization2,
            liborPeriodDiscretization2,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 2.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new RandomVariable[] {Scalar.of(10.0d)});

    // Assert
    LIBORCorrelationModel correlationModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getCorrelationModel();
    assertTrue(correlationModel2 instanceof LIBORCorrelationModelExponentialDecay);
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    LIBORVolatilityModel volatilityModel2 =
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
            .getVolatilityModel();
    assertTrue(volatilityModel2 instanceof LIBORVolatilityModelPiecewiseConstant);
    TimeDiscretization liborPeriodDiscretization3 =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization3 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization4 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization4 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization4 =
        correlationModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization4 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization5 = correlationModel2.getTimeDiscretization();
    assertTrue(timeDiscretization5 instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization5 = volatilityModel2.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization5 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization6 = volatilityModel2.getTimeDiscretization();
    assertTrue(timeDiscretization6 instanceof TenorFromArray);
    TimeDiscretization simulationTimeDiscretization2 =
        ((LIBORVolatilityModelPiecewiseConstant) volatilityModel2)
            .getSimulationTimeDiscretization();
    assertTrue(simulationTimeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeToMaturityDiscretization =
        ((LIBORVolatilityModelPiecewiseConstant) volatilityModel2)
            .getTimeToMaturityDiscretization();
    assertTrue(timeToMaturityDiscretization instanceof TenorFromArray);
    assertEquals(timeDiscretization4, liborPeriodDiscretization4);
    assertEquals(timeDiscretization4, timeDiscretization5);
    assertEquals(timeDiscretization4, liborPeriodDiscretization5);
    assertEquals(timeDiscretization4, timeDiscretization6);
    assertEquals(timeDiscretization4, simulationTimeDiscretization2);
    assertEquals(timeDiscretization4, timeToMaturityDiscretization);
    assertArrayEquals(
        new double[] {}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization3.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization4.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable_thenReturnArrayLengthIsOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant volatilityModel =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization2,
            liborPeriodDiscretization2,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
                .getVolatilityModel()
            instanceof LIBORVolatilityModelPiecewiseConstant);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(1, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable_thenReturnArrayLengthIsOne2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant volatilityModel =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization2,
            liborPeriodDiscretization2,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedParameters(
            new RandomVariable[] {Scalar.of(10.0d)});

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedParameters)
                .getVolatilityModel()
            instanceof LIBORVolatilityModelPiecewiseConstant);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(1, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble()"
  })
  public void testGetParameterAsDouble() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        liborCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble()"
  })
  public void testGetParameterAsDouble2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFromDoubleArray a = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray b = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray c = new RandomVariableFromDoubleArray(10.0d);

    LIBORVolatilityModelFourParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelFourParameterExponentialForm(
            timeDiscretization2,
            liborPeriodDiscretization2,
            a,
            b,
            c,
            new RandomVariableFromDoubleArray(10.0d),
            true);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        liborCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble()"
  })
  public void testGetParameterAsDouble3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        liborCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble()"
  })
  public void testGetParameterAsDouble4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay correlationModel =
        new LIBORCorrelationModelThreeParameterExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, 10.0d, 10.0d, true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        liborCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble()"
  })
  public void testGetParameterAsDouble5() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix volatilityModel =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), volatility);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act and Assert
    assertArrayEquals(
        new double[] {},
        liborCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble()"
  })
  public void testGetParameterAsDouble6() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix volatilityModel =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization2,
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            volatility);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act and Assert
    assertArrayEquals(
        new double[] {},
        liborCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble()"
  })
  public void testGetParameterAsDouble7() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 =
        new TenorFromArray(new double[] {10.0d, 10.5d, 10.0d, 10.5d});
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix volatilityModel =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization2, liborPeriodDiscretization2, volatility);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act and Assert
    assertArrayEquals(
        new double[] {},
        liborCovarianceModelFromVolatilityAndCorrelation.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#clone()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelFromVolatilityAndCorrelation#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCovarianceModelFromVolatilityAndCorrelation.clone()"})
  public void testClone() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(-0.12069849283645878d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay correlationModel =
        new LIBORCorrelationModelThreeParameterExponentialDecay(
            timeDiscretization3,
            new TenorFromArray(-0.12069849283645878d, 10, 0.5d),
            3,
            -0.12069849283645878d,
            -0.12069849283645878d,
            -0.12069849283645878d,
            true);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    Object actualCloneResult = liborCovarianceModelFromVolatilityAndCorrelation.clone();

    // Assert
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneResult).getCorrelationModel()
            instanceof LIBORCorrelationModelThreeParameterExponentialDecay);
    assertTrue(actualCloneResult instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneResult)
                .getLiborPeriodDiscretization()
            instanceof TenorFromArray);
    assertEquals(
        5,
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneResult)
            .getParameter()
            .length);
    assertArrayEquals(
        new double[] {
          10.0d, 10.0d, -0.12069849283645878d, -0.12069849283645878d, -0.12069849283645878d
        },
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneResult)
            .getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#clone()}.
   *
   * <ul>
   *   <li>Then CorrelationModel return {@link LIBORCorrelationModelExponentialDecay}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelFromVolatilityAndCorrelation#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCovarianceModelFromVolatilityAndCorrelation.clone()"})
  public void testClone_thenCorrelationModelReturnLIBORCorrelationModelExponentialDecay() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    Object actualCloneResult = liborCovarianceModelFromVolatilityAndCorrelation.clone();

    // Assert
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneResult).getCorrelationModel()
            instanceof LIBORCorrelationModelExponentialDecay);
    assertTrue(actualCloneResult instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneResult).getVolatilityModel()
            instanceof LIBORVolatilityModelTwoParameterExponentialForm);
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneResult)
                .getLiborPeriodDiscretization()
            instanceof TenorFromArray);
    assertEquals(
        2,
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneResult)
            .getParameter()
            .length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneResult)
            .getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#clone()}.
   *
   * <ul>
   *   <li>Then VolatilityModel return {@link LIBORVolatilityModelPiecewiseConstant}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelFromVolatilityAndCorrelation#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCovarianceModelFromVolatilityAndCorrelation.clone()"})
  public void testClone_thenVolatilityModelReturnLIBORVolatilityModelPiecewiseConstant() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(-0.12069849283645878d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization2 = new TenorFromArray(-0.12069849283645878d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization =
        new TenorFromArray(-0.12069849283645878d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant volatilityModel =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization2,
            liborPeriodDiscretization2,
            simulationTimeDiscretization,
            new TenorFromArray(-0.12069849283645878d, 10, 0.5d),
            -0.12069849283645878d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    Object actualCloneResult = liborCovarianceModelFromVolatilityAndCorrelation.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneResult).getVolatilityModel()
            instanceof LIBORVolatilityModelPiecewiseConstant);
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneResult)
                .getLiborPeriodDiscretization()
            instanceof TenorFromArray);
    assertEquals(
        66,
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneResult)
            .getParameter()
            .length);
    assertEquals(
        66,
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneResult)
            .getParameterAsDouble()
            .length);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LIBORCovarianceModelFromVolatilityAndCorrelation#getCorrelationModel()}
   *   <li>{@link LIBORCovarianceModelFromVolatilityAndCorrelation#getVolatilityModel()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORCorrelationModel LIBORCovarianceModelFromVolatilityAndCorrelation.getCorrelationModel()",
    "LIBORVolatilityModel LIBORCovarianceModelFromVolatilityAndCorrelation.getVolatilityModel()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    LIBORCorrelationModel actualCorrelationModel =
        liborCovarianceModelFromVolatilityAndCorrelation.getCorrelationModel();

    // Assert
    assertSame(correlationModel, actualCorrelationModel);
    assertSame(
        volatilityModel, liborCovarianceModelFromVolatilityAndCorrelation.getVolatilityModel());
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenHashMap() throws CalculationException {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedData)
                .getCorrelationModel()
            instanceof LIBORCorrelationModelExponentialDecay);
    assertTrue(
        actualCloneWithModifiedData instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedData)
                .getVolatilityModel()
            instanceof LIBORVolatilityModelTwoParameterExponentialForm);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization4 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization4 instanceof TenorFromArray);
    assertEquals(2, actualCloneWithModifiedData.getParameter().length);
    assertEquals(timeDiscretization4, liborPeriodDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedData.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelFromVolatilityAndCorrelation#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenNull() throws CalculationException {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation
        liborCovarianceModelFromVolatilityAndCorrelation =
            new LIBORCovarianceModelFromVolatilityAndCorrelation(
                timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        liborCovarianceModelFromVolatilityAndCorrelation.getCloneWithModifiedData(null);

    // Assert
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedData)
                .getCorrelationModel()
            instanceof LIBORCorrelationModelExponentialDecay);
    assertTrue(
        actualCloneWithModifiedData instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        ((LIBORCovarianceModelFromVolatilityAndCorrelation) actualCloneWithModifiedData)
                .getVolatilityModel()
            instanceof LIBORVolatilityModelTwoParameterExponentialForm);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization4 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization4 instanceof TenorFromArray);
    assertEquals(2, actualCloneWithModifiedData.getParameter().length);
    assertEquals(timeDiscretization4, liborPeriodDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedData.getParameterAsDouble(), 0.0);
  }
}
