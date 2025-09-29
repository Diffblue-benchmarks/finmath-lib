package net.finmath.montecarlo.automaticdifferentiation.backward;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.automaticdifferentiation.RandomVariableDifferentiable;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory.DiracDeltaApproximationMethod;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwiseFactory;
import net.finmath.montecarlo.automaticdifferentiation.forward.RandomVariableDifferentiableAD;
import net.finmath.montecarlo.automaticdifferentiation.forward.RandomVariableDifferentiableADFactory;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomVariableDifferentiableAADFactoryDiffblueTest {
  /**
   * Test {@link RandomVariableDifferentiableAADFactory#RandomVariableDifferentiableAADFactory()}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADFactory#RandomVariableDifferentiableAADFactory()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAADFactory.<init>()"})
  public void testNewRandomVariableDifferentiableAADFactory() {
    // Arrange and Act
    RandomVariableDifferentiableAADFactory actualRandomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory();

    // Assert
    assertEquals(0.05d, actualRandomVariableDifferentiableAADFactory.getBarrierDiracWidth(), 0.0);
    assertEquals(
        0.05d,
        actualRandomVariableDifferentiableAADFactory.getDiracDeltaApproximationWidthPerStdDev(),
        0.0);
    assertEquals(
        0.5d,
        actualRandomVariableDifferentiableAADFactory
            .getDiracDeltaApproximationDensityRegressionWidthPerStdDev(),
        0.0);
    assertEquals(
        DiracDeltaApproximationMethod.DISCRETE_DELTA,
        actualRandomVariableDifferentiableAADFactory.getDiracDeltaApproximationMethod());
    assertTrue(actualRandomVariableDifferentiableAADFactory.isGradientRetainsLeafNodesOnly());
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADFactory#RandomVariableDifferentiableAADFactory(RandomVariableFactory)}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADFactory#RandomVariableDifferentiableAADFactory(RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAADFactory.<init>(RandomVariableFactory)"})
  public void testNewRandomVariableDifferentiableAADFactory2() {
    // Arrange and Act
    RandomVariableDifferentiableAADFactory actualRandomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory(new RandomVariableFloatFactory());

    // Assert
    assertEquals(0.05d, actualRandomVariableDifferentiableAADFactory.getBarrierDiracWidth(), 0.0);
    assertEquals(
        0.05d,
        actualRandomVariableDifferentiableAADFactory.getDiracDeltaApproximationWidthPerStdDev(),
        0.0);
    assertEquals(
        0.5d,
        actualRandomVariableDifferentiableAADFactory
            .getDiracDeltaApproximationDensityRegressionWidthPerStdDev(),
        0.0);
    assertEquals(
        DiracDeltaApproximationMethod.DISCRETE_DELTA,
        actualRandomVariableDifferentiableAADFactory.getDiracDeltaApproximationMethod());
    assertTrue(actualRandomVariableDifferentiableAADFactory.isGradientRetainsLeafNodesOnly());
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADFactory#RandomVariableDifferentiableAADFactory(Map)}.
   *
   * <ul>
   *   <li>Then return BarrierDiracWidth is {@code 0.05}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADFactory#RandomVariableDifferentiableAADFactory(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableDifferentiableAADFactory.<init>(Map)"})
  public void testNewRandomVariableDifferentiableAADFactory_thenReturnBarrierDiracWidthIs005() {
    // Arrange and Act
    RandomVariableDifferentiableAADFactory actualRandomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory(new HashMap<>());

    // Assert
    assertEquals(0.05d, actualRandomVariableDifferentiableAADFactory.getBarrierDiracWidth(), 0.0);
    assertEquals(
        0.05d,
        actualRandomVariableDifferentiableAADFactory.getDiracDeltaApproximationWidthPerStdDev(),
        0.0);
    assertEquals(
        0.5d,
        actualRandomVariableDifferentiableAADFactory
            .getDiracDeltaApproximationDensityRegressionWidthPerStdDev(),
        0.0);
    assertEquals(
        DiracDeltaApproximationMethod.DISCRETE_DELTA,
        actualRandomVariableDifferentiableAADFactory.getDiracDeltaApproximationMethod());
    assertTrue(actualRandomVariableDifferentiableAADFactory.isGradientRetainsLeafNodesOnly());
  }

  /**
   * Test {@link
   * RandomVariableDifferentiableAADFactory#RandomVariableDifferentiableAADFactory(RandomVariableFactory,
   * Map)}.
   *
   * <ul>
   *   <li>Then return BarrierDiracWidth is {@code 0.05}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADFactory#RandomVariableDifferentiableAADFactory(RandomVariableFactory,
   * Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableDifferentiableAADFactory.<init>(RandomVariableFactory, Map)"
  })
  public void testNewRandomVariableDifferentiableAADFactory_thenReturnBarrierDiracWidthIs0052() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactoryForNonDifferentiable =
        new RandomVariableFloatFactory();

    // Act
    RandomVariableDifferentiableAADFactory actualRandomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory(
            randomVariableFactoryForNonDifferentiable, new HashMap<>());

    // Assert
    assertEquals(0.05d, actualRandomVariableDifferentiableAADFactory.getBarrierDiracWidth(), 0.0);
    assertEquals(
        0.05d,
        actualRandomVariableDifferentiableAADFactory.getDiracDeltaApproximationWidthPerStdDev(),
        0.0);
    assertEquals(
        0.5d,
        actualRandomVariableDifferentiableAADFactory
            .getDiracDeltaApproximationDensityRegressionWidthPerStdDev(),
        0.0);
    assertEquals(
        DiracDeltaApproximationMethod.DISCRETE_DELTA,
        actualRandomVariableDifferentiableAADFactory.getDiracDeltaApproximationMethod());
    assertTrue(actualRandomVariableDifferentiableAADFactory.isGradientRetainsLeafNodesOnly());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADFactory#createRandomVariable(double, double)} with
   * {@code time}, {@code value}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADFactory#createRandomVariable(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADFactory.createRandomVariable(double, double)"
  })
  public void testCreateRandomVariableWithTimeValue() {
    // Arrange
    RandomVariableDifferentiableAADFactory randomVariableFactoryForNonDifferentiable =
        new RandomVariableDifferentiableAADFactory();
    RandomVariableDifferentiableAADFactory randomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory(
            randomVariableFactoryForNonDifferentiable, new HashMap<>());

    // Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        randomVariableDifferentiableAADFactory.createRandomVariable(10.0d, 10.0d);

    // Assert
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
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAAD);
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
   * Test {@link RandomVariableDifferentiableAADFactory#createRandomVariable(double, double)} with
   * {@code time}, {@code value}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADFactory#createRandomVariable(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADFactory.createRandomVariable(double, double)"
  })
  public void testCreateRandomVariableWithTimeValue2() {
    // Arrange
    RandomVariableDifferentiableADFactory randomVariableFactoryForNonDifferentiable =
        new RandomVariableDifferentiableADFactory();
    RandomVariableDifferentiableAADFactory randomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory(
            randomVariableFactoryForNonDifferentiable, new HashMap<>());

    // Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        randomVariableDifferentiableAADFactory.createRandomVariable(10.0d, 10.0d);

    // Assert
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
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCreateRandomVariableResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof Scalar);
    assertEquals(1, actualCreateRandomVariableResult.getGradient().size());
    assertSame(
        randomVariableDifferentiableAADFactory,
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult).getFactory());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADFactory#createRandomVariable(double, double)} with
   * {@code time}, {@code value}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADFactory#createRandomVariable(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADFactory.createRandomVariable(double, double)"
  })
  public void testCreateRandomVariableWithTimeValue_thenNaNReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableDifferentiableAADPathwiseFactory randomVariableFactoryForNonDifferentiable =
        new RandomVariableDifferentiableAADPathwiseFactory();
    RandomVariableDifferentiableAADFactory randomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory(
            randomVariableFactoryForNonDifferentiable, new HashMap<>());

    // Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        randomVariableDifferentiableAADFactory.createRandomVariable(10.0d, 10.0d);

    // Assert
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableResult.getValues()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(10.0d, actualCreateRandomVariableResult.getFiltrationTime(), 0.0);
    assertSame(
        randomVariableDifferentiableAADFactory,
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult).getFactory());
    assertArrayEquals(
        new double[] {10.0d}, actualCreateRandomVariableResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADFactory#createRandomVariable(double, double)} with
   * {@code time}, {@code value}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADFactory#createRandomVariable(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADFactory.createRandomVariable(double, double)"
  })
  public void testCreateRandomVariableWithTimeValue_thenValuesReturnScalar() {
    // Arrange
    RandomVariableDifferentiableAADFactory randomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory();

    // Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        randomVariableDifferentiableAADFactory.createRandomVariable(10.0d, 10.0d);

    // Assert
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
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAAD);
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
   * Test {@link RandomVariableDifferentiableAADFactory#createRandomVariable(double, double[])} with
   * {@code time}, {@code values}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADFactory#createRandomVariable(double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADFactory.createRandomVariable(double, double[])"
  })
  public void testCreateRandomVariableWithTimeValues() {
    // Arrange
    RandomVariableDifferentiableAADFactory randomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory();

    // Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        randomVariableDifferentiableAADFactory.createRandomVariable(
            10.0d, new double[] {10.0d, 0.05d, 10.0d, 0.05d});

    // Assert
    assertTrue(
        actualCreateRandomVariableResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAAD);
    assertSame(
        randomVariableDifferentiableAADFactory,
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult).getFactory());
    assertArrayEquals(
        new double[] {10.0d, 0.05d, 10.0d, 0.05d},
        actualCreateRandomVariableResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADFactory#createRandomVariable(double, double[])} with
   * {@code time}, {@code values}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADFactory#createRandomVariable(double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADFactory.createRandomVariable(double, double[])"
  })
  public void testCreateRandomVariableWithTimeValues2() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactoryForNonDifferentiable =
        new RandomVariableFloatFactory();
    RandomVariableDifferentiableAADFactory randomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory(
            randomVariableFactoryForNonDifferentiable, new HashMap<>());

    // Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        randomVariableDifferentiableAADFactory.createRandomVariable(
            10.0d, new double[] {10.0d, 0.05d, 10.0d, 0.05d});

    // Assert
    assertTrue(
        actualCreateRandomVariableResult.getValues() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.05000000074505806d, actualCreateRandomVariableResult.getMin(), 0.0);
    assertEquals(2.4874999998137355d, actualCreateRandomVariableResult.getStandardError(), 0.0);
    assertEquals(24.750624996293336d, actualCreateRandomVariableResult.getVariance(), 0.0);
    assertEquals(33.00083332839112d, actualCreateRandomVariableResult.getSampleVariance(), 0.0);
    assertEquals(4.974999999627471d, actualCreateRandomVariableResult.getStandardDeviation(), 0.0);
    assertEquals(5.025000000372529d, actualCreateRandomVariableResult.getAverage(), 0.0);
    assertSame(
        randomVariableDifferentiableAADFactory,
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult).getFactory());
    assertArrayEquals(
        new double[] {10.0d, 0.05000000074505806d, 10.0d, 0.05000000074505806d},
        actualCreateRandomVariableResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADFactory#createRandomVariable(double, double[])} with
   * {@code time}, {@code values}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADFactory#createRandomVariable(double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADFactory.createRandomVariable(double, double[])"
  })
  public void testCreateRandomVariableWithTimeValues3() {
    // Arrange
    RandomVariableDifferentiableAADFactory randomVariableFactoryForNonDifferentiable =
        new RandomVariableDifferentiableAADFactory();
    RandomVariableDifferentiableAADFactory randomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory(
            randomVariableFactoryForNonDifferentiable, new HashMap<>());

    // Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        randomVariableDifferentiableAADFactory.createRandomVariable(
            10.0d, new double[] {10.0d, 0.05d, 10.0d, 0.05d});

    // Assert
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableResult.getValues() instanceof RandomVariableDifferentiableAAD);
    assertSame(
        randomVariableDifferentiableAADFactory,
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult).getFactory());
    assertArrayEquals(
        new double[] {10.0d, 0.05d, 10.0d, 0.05d},
        actualCreateRandomVariableResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableDifferentiableAADFactory#createRandomVariable(double, double[])} with
   * {@code time}, {@code values}.
   *
   * <p>Method under test: {@link
   * RandomVariableDifferentiableAADFactory#createRandomVariable(double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiableAADFactory.createRandomVariable(double, double[])"
  })
  public void testCreateRandomVariableWithTimeValues4() {
    // Arrange
    RandomVariableDifferentiableADFactory randomVariableFactoryForNonDifferentiable =
        new RandomVariableDifferentiableADFactory();
    RandomVariableDifferentiableAADFactory randomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory(
            randomVariableFactoryForNonDifferentiable, new HashMap<>());

    // Act
    RandomVariableDifferentiable actualCreateRandomVariableResult =
        randomVariableDifferentiableAADFactory.createRandomVariable(
            10.0d, new double[] {10.0d, 0.05d, 10.0d, 0.05d});

    // Assert
    assertTrue(actualCreateRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCreateRandomVariableResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualCreateRandomVariableResult.getValues() instanceof RandomVariableDifferentiableAD);
    assertSame(
        randomVariableDifferentiableAADFactory,
        ((RandomVariableDifferentiableAAD) actualCreateRandomVariableResult).getFactory());
    assertArrayEquals(
        new double[] {10.0d, 0.05d, 10.0d, 0.05d},
        actualCreateRandomVariableResult.getRealizations(),
        0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RandomVariableDifferentiableAADFactory#toString()}
   *   <li>{@link
   *       RandomVariableDifferentiableAADFactory#getDiracDeltaApproximationDensityRegressionWidthPerStdDev()}
   *   <li>{@link RandomVariableDifferentiableAADFactory#getDiracDeltaApproximationMethod()}
   *   <li>{@link RandomVariableDifferentiableAADFactory#getDiracDeltaApproximationWidthPerStdDev()}
   *   <li>{@link RandomVariableDifferentiableAADFactory#isGradientRetainsLeafNodesOnly()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableDifferentiableAADFactory.getDiracDeltaApproximationDensityRegressionWidthPerStdDev()",
    "DiracDeltaApproximationMethod RandomVariableDifferentiableAADFactory.getDiracDeltaApproximationMethod()",
    "double RandomVariableDifferentiableAADFactory.getDiracDeltaApproximationWidthPerStdDev()",
    "boolean RandomVariableDifferentiableAADFactory.isGradientRetainsLeafNodesOnly()",
    "String RandomVariableDifferentiableAADFactory.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableDifferentiableAADFactory randomVariableDifferentiableAADFactory =
        new RandomVariableDifferentiableAADFactory();

    // Act
    String actualToStringResult = randomVariableDifferentiableAADFactory.toString();
    double actualDiracDeltaApproximationDensityRegressionWidthPerStdDev =
        randomVariableDifferentiableAADFactory
            .getDiracDeltaApproximationDensityRegressionWidthPerStdDev();
    DiracDeltaApproximationMethod actualDiracDeltaApproximationMethod =
        randomVariableDifferentiableAADFactory.getDiracDeltaApproximationMethod();
    double actualDiracDeltaApproximationWidthPerStdDev =
        randomVariableDifferentiableAADFactory.getDiracDeltaApproximationWidthPerStdDev();

    // Assert
    assertEquals(
        "RandomVariableDifferentiableAADFactory [diracDeltaApproximationMethod=DISCRETE_DELTA, diracDeltaAppr"
            + "oximationWidthPerStdDev=0.05, diracDeltaApproximationDensityRegressionWidthPerStdDev=0.5, isGradient"
            + "RetainsLeafNodesOnly=true, toString()=AbstractRandomVariableDifferentiableFactory [randomVariableFac"
            + "toryForNonDifferentiable=RandomVariableFromArrayFactory [isUseDoublePrecisionFloatingPointImplementation"
            + "=true]]]",
        actualToStringResult);
    assertEquals(0.05d, actualDiracDeltaApproximationWidthPerStdDev, 0.0);
    assertEquals(0.5d, actualDiracDeltaApproximationDensityRegressionWidthPerStdDev, 0.0);
    assertEquals(DiracDeltaApproximationMethod.DISCRETE_DELTA, actualDiracDeltaApproximationMethod);
    assertTrue(randomVariableDifferentiableAADFactory.isGradientRetainsLeafNodesOnly());
  }

  /**
   * Test {@link RandomVariableDifferentiableAADFactory#getBarrierDiracWidth()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiableAADFactory#getBarrierDiracWidth()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableDifferentiableAADFactory.getBarrierDiracWidth()"})
  public void testGetBarrierDiracWidth() {
    // Arrange, Act and Assert
    assertEquals(0.05d, new RandomVariableDifferentiableAADFactory().getBarrierDiracWidth(), 0.0);
  }
}
