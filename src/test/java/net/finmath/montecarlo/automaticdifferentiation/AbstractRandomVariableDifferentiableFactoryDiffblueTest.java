package net.finmath.montecarlo.automaticdifferentiation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwiseFactory;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractRandomVariableDifferentiableFactoryDiffblueTest {
  /**
   * Test {@link AbstractRandomVariableDifferentiableFactory#createRandomVariable(double)} with
   * {@code value}.
   *
   * <p>Method under test: {@link
   * AbstractRandomVariableDifferentiableFactory#createRandomVariable(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable AbstractRandomVariableDifferentiableFactory.createRandomVariable(double)"
  })
  public void testCreateRandomVariableWithValue() {
    // Arrange
    RandomVariableDifferentiableAADFactory randomVariableFactoryForNonDifferentiable =
        new RandomVariableDifferentiableAADFactory();
    RandomVariableDifferentiableAADFactory randomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory(
            randomVariableFactoryForNonDifferentiable, new HashMap<>());

    // Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        randomVariableDifferentiableAADFactory.createRandomVariable(10.0d);

    // Assert
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableResult.getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof Scalar);
    assertEquals(1, actualCreateRandomVariableResult.getGradient().size());
    assertSame(
        randomVariableDifferentiableAADFactory,
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult).getFactory());
  }

  /**
   * Test {@link AbstractRandomVariableDifferentiableFactory#createRandomVariable(double)} with
   * {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractRandomVariableDifferentiableFactory#createRandomVariable(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable AbstractRandomVariableDifferentiableFactory.createRandomVariable(double)"
  })
  public void testCreateRandomVariableWithValue_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAADFactory randomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory();

    // Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        randomVariableDifferentiableAADFactory.createRandomVariable(10.0d);

    // Assert
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableResult.getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCreateRandomVariableResult.getValues() instanceof Scalar);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof Scalar);
    assertEquals(1, actualCreateRandomVariableResult.getGradient().size());
    assertSame(
        randomVariableDifferentiableAADFactory,
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult).getFactory());
  }

  /**
   * Test {@link
   * AbstractRandomVariableDifferentiableFactory#createRandomVariableNonDifferentiable(double,
   * double)} with {@code time}, {@code value}.
   *
   * <p>Method under test: {@link
   * AbstractRandomVariableDifferentiableFactory#createRandomVariableNonDifferentiable(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AbstractRandomVariableDifferentiableFactory.createRandomVariableNonDifferentiable(double, double)"
  })
  public void testCreateRandomVariableNonDifferentiableWithTimeValue() {
    // Arrange
    RandomVariableDifferentiableAADFactory randomVariableFactoryForNonDifferentiable =
        new RandomVariableDifferentiableAADFactory();
    RandomVariableDifferentiableAADFactory randomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory(
            randomVariableFactoryForNonDifferentiable, new HashMap<>());

    // Act
    RandomVariable actualCreateRandomVariableNonDifferentiableResult =
        randomVariableDifferentiableAADFactory.createRandomVariableNonDifferentiable(10.0d, 10.0d);

    // Assert
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCreateRandomVariableNonDifferentiableResult.getValues() instanceof Scalar);
    assertTrue(actualCreateRandomVariableNonDifferentiableResult.isNaN() instanceof Scalar);
    assertNull(actualCreateRandomVariableNonDifferentiableResult.getRealizations());
    assertNull(actualCreateRandomVariableNonDifferentiableResult.getOperator());
    assertNull(actualCreateRandomVariableNonDifferentiableResult.getRealizationsStream());
    assertEquals(0.0d, actualCreateRandomVariableNonDifferentiableResult.getSampleVariance(), 0.0);
    assertEquals(
        0.0d, actualCreateRandomVariableNonDifferentiableResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableNonDifferentiableResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableNonDifferentiableResult.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
            .getGradient()
            .size());
    assertEquals(1, actualCreateRandomVariableNonDifferentiableResult.size());
    assertEquals(10.0d, actualCreateRandomVariableNonDifferentiableResult.getAverage(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableNonDifferentiableResult.getMax(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableNonDifferentiableResult.getMin(), 0.0);
    assertEquals(3, actualCreateRandomVariableNonDifferentiableResult.getTypePriority());
    assertTrue(actualCreateRandomVariableNonDifferentiableResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualCreateRandomVariableNonDifferentiableResult.getFiltrationTime(),
        0.0);
    assertSame(
        randomVariableFactoryForNonDifferentiable,
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
            .getFactory());
  }

  /**
   * Test {@link
   * AbstractRandomVariableDifferentiableFactory#createRandomVariableNonDifferentiable(double,
   * double)} with {@code time}, {@code value}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractRandomVariableDifferentiableFactory#createRandomVariableNonDifferentiable(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AbstractRandomVariableDifferentiableFactory.createRandomVariableNonDifferentiable(double, double)"
  })
  public void testCreateRandomVariableNonDifferentiableWithTimeValue_thenReturnScalar() {
    // Arrange and Act
    RandomVariable actualCreateRandomVariableNonDifferentiableResult =
        new RandomVariableDifferentiableAADFactory()
            .createRandomVariableNonDifferentiable(10.0d, 10.0d);

    // Assert
    assertTrue(actualCreateRandomVariableNonDifferentiableResult instanceof Scalar);
    assertTrue(actualCreateRandomVariableNonDifferentiableResult.abs() instanceof Scalar);
    assertTrue(actualCreateRandomVariableNonDifferentiableResult.cos() instanceof Scalar);
    assertTrue(actualCreateRandomVariableNonDifferentiableResult.exp() instanceof Scalar);
    assertTrue(actualCreateRandomVariableNonDifferentiableResult.expm1() instanceof Scalar);
    assertTrue(actualCreateRandomVariableNonDifferentiableResult.invert() instanceof Scalar);
    assertTrue(actualCreateRandomVariableNonDifferentiableResult.isNaN() instanceof Scalar);
    assertTrue(actualCreateRandomVariableNonDifferentiableResult.sin() instanceof Scalar);
    assertTrue(actualCreateRandomVariableNonDifferentiableResult.sqrt() instanceof Scalar);
    assertTrue(actualCreateRandomVariableNonDifferentiableResult.squared() instanceof Scalar);
    assertTrue(actualCreateRandomVariableNonDifferentiableResult.variance() instanceof Scalar);
    assertNull(actualCreateRandomVariableNonDifferentiableResult.getRealizations());
    assertNull(actualCreateRandomVariableNonDifferentiableResult.getOperator());
    assertNull(actualCreateRandomVariableNonDifferentiableResult.getRealizationsStream());
    assertEquals(0, actualCreateRandomVariableNonDifferentiableResult.getTypePriority());
    assertEquals(0.0d, actualCreateRandomVariableNonDifferentiableResult.getSampleVariance(), 0.0);
    assertEquals(
        0.0d, actualCreateRandomVariableNonDifferentiableResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableNonDifferentiableResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCreateRandomVariableNonDifferentiableResult.getVariance(), 0.0);
    assertEquals(1, actualCreateRandomVariableNonDifferentiableResult.size());
    assertEquals(10.0d, actualCreateRandomVariableNonDifferentiableResult.getAverage(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableNonDifferentiableResult.getMax(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableNonDifferentiableResult.getMin(), 0.0);
    assertTrue(actualCreateRandomVariableNonDifferentiableResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualCreateRandomVariableNonDifferentiableResult.getFiltrationTime(),
        0.0);
    RandomVariable actualExpectationResult =
        actualCreateRandomVariableNonDifferentiableResult.expectation();
    assertSame(actualCreateRandomVariableNonDifferentiableResult, actualExpectationResult);
  }

  /**
   * Test {@link
   * AbstractRandomVariableDifferentiableFactory#createRandomVariableNonDifferentiable(double,
   * double[])} with {@code time}, {@code values}.
   *
   * <p>Method under test: {@link
   * AbstractRandomVariableDifferentiableFactory#createRandomVariableNonDifferentiable(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AbstractRandomVariableDifferentiableFactory.createRandomVariableNonDifferentiable(double, double[])"
  })
  public void testCreateRandomVariableNonDifferentiableWithTimeValues() {
    // Arrange and Act
    RandomVariable actualCreateRandomVariableNonDifferentiableResult =
        new RandomVariableDifferentiableAADFactory()
            .createRandomVariableNonDifferentiable(10.0d, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.abs()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.cos()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.sin()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertEquals(0.5d, actualCreateRandomVariableNonDifferentiableResult.getMin(), 0.0);
    assertEquals(1, actualCreateRandomVariableNonDifferentiableResult.getTypePriority());
    assertEquals(10.0d, actualCreateRandomVariableNonDifferentiableResult.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableNonDifferentiableResult.getMax(), 0.0);
    assertEquals(2.375d, actualCreateRandomVariableNonDifferentiableResult.getStandardError(), 0.0);
    assertEquals(22.5625d, actualCreateRandomVariableNonDifferentiableResult.getVariance(), 0.0);
    assertEquals(
        30.083333333333332d,
        actualCreateRandomVariableNonDifferentiableResult.getSampleVariance(),
        0.0);
    assertEquals(4, actualCreateRandomVariableNonDifferentiableResult.size());
    assertEquals(
        4.75d, actualCreateRandomVariableNonDifferentiableResult.getStandardDeviation(), 0.0);
    assertEquals(5.25d, actualCreateRandomVariableNonDifferentiableResult.getAverage(), 0.0);
    assertFalse(actualCreateRandomVariableNonDifferentiableResult.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCreateRandomVariableNonDifferentiableResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link
   * AbstractRandomVariableDifferentiableFactory#createRandomVariableNonDifferentiable(double,
   * double[])} with {@code time}, {@code values}.
   *
   * <p>Method under test: {@link
   * AbstractRandomVariableDifferentiableFactory#createRandomVariableNonDifferentiable(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AbstractRandomVariableDifferentiableFactory.createRandomVariableNonDifferentiable(double, double[])"
  })
  public void testCreateRandomVariableNonDifferentiableWithTimeValues2() {
    // Arrange
    RandomVariableDifferentiableAADFactory randomVariableFactoryForNonDifferentiable =
        new RandomVariableDifferentiableAADFactory();
    RandomVariableDifferentiableAADFactory randomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory(
            randomVariableFactoryForNonDifferentiable, new HashMap<>());

    // Act
    RandomVariable actualCreateRandomVariableNonDifferentiableResult =
        randomVariableDifferentiableAADFactory.createRandomVariableNonDifferentiable(
            10.0d, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableNonDifferentiableResult.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.5d, actualCreateRandomVariableNonDifferentiableResult.getMin(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
            .getGradient()
            .size());
    assertEquals(10.0d, actualCreateRandomVariableNonDifferentiableResult.getFiltrationTime(), 0.0);
    assertEquals(10.0d, actualCreateRandomVariableNonDifferentiableResult.getMax(), 0.0);
    assertEquals(2.375d, actualCreateRandomVariableNonDifferentiableResult.getStandardError(), 0.0);
    assertEquals(22.5625d, actualCreateRandomVariableNonDifferentiableResult.getVariance(), 0.0);
    assertEquals(3, actualCreateRandomVariableNonDifferentiableResult.getTypePriority());
    assertEquals(
        30.083333333333332d,
        actualCreateRandomVariableNonDifferentiableResult.getSampleVariance(),
        0.0);
    assertEquals(4, actualCreateRandomVariableNonDifferentiableResult.size());
    assertEquals(
        4.75d, actualCreateRandomVariableNonDifferentiableResult.getStandardDeviation(), 0.0);
    assertEquals(5.25d, actualCreateRandomVariableNonDifferentiableResult.getAverage(), 0.0);
    assertFalse(actualCreateRandomVariableNonDifferentiableResult.isDeterministic());
    assertSame(
        randomVariableFactoryForNonDifferentiable,
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableNonDifferentiableResult)
            .getFactory());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCreateRandomVariableNonDifferentiableResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AbstractRandomVariableDifferentiableFactory#toString()}.
   *
   * <p>Method under test: {@link AbstractRandomVariableDifferentiableFactory#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractRandomVariableDifferentiableFactory.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "AbstractRandomVariableDifferentiableFactory [randomVariableFactoryForNonDifferentiable=RandomVaria"
            + "bleDifferentiableAADFactory [diracDeltaApproximationMethod=DISCRETE_DELTA, diracDeltaApproximation"
            + "WidthPerStdDev=0.05, diracDeltaApproximationDensityRegressionWidthPerStdDev=0.5, isGradientRetains"
            + "LeafNodesOnly=true, toString()=AbstractRandomVariableDifferentiableFactory [randomVariableFactoryF"
            + "orNonDifferentiable=RandomVariableFromArrayFactory [isUseDoublePrecisionFloatingPointImplementation"
            + "=true]]]]",
        new RandomVariableDifferentiableAADPathwiseFactory(
                new RandomVariableDifferentiableAADFactory())
            .toString());
  }

  /**
   * Test {@link AbstractRandomVariableDifferentiableFactory#toString()}.
   *
   * <ul>
   *   <li>Given {@link
   *       RandomVariableDifferentiableAADPathwiseFactory#RandomVariableDifferentiableAADPathwiseFactory()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractRandomVariableDifferentiableFactory#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractRandomVariableDifferentiableFactory.toString()"})
  public void testToString_givenRandomVariableDifferentiableAADPathwiseFactory() {
    // Arrange, Act and Assert
    assertEquals(
        "AbstractRandomVariableDifferentiableFactory [randomVariableFactoryForNonDifferentiable=RandomVariabl"
            + "eFromArrayFactory [isUseDoublePrecisionFloatingPointImplementation=true]]",
        new RandomVariableDifferentiableAADPathwiseFactory().toString());
  }
}
