package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LIBORVolatilityModelFourParameterExponentialFormIntegratedDiffblueTest {
  /**
   * Test {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#LIBORVolatilityModelFourParameterExponentialFormIntegrated(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double, double, double, double, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#LIBORVolatilityModelFourParameterExponentialFormIntegrated(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double, double, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelFourParameterExponentialFormIntegrated.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double, double, double, double, boolean)"
  })
  public void testNewLIBORVolatilityModelFourParameterExponentialFormIntegrated() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelFourParameterExponentialFormIntegrated
        actualLiborVolatilityModelFourParameterExponentialFormIntegrated =
            new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
                randomVariableFactory,
                timeDiscretization,
                liborPeriodDiscretization,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelFourParameterExponentialFormIntegrated.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        4, actualLiborVolatilityModelFourParameterExponentialFormIntegrated.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelFourParameterExponentialFormIntegrated
            .getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualLiborVolatilityModelFourParameterExponentialFormIntegrated.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#LIBORVolatilityModelFourParameterExponentialFormIntegrated(TimeDiscretization,
   * TimeDiscretization, double, double, double, double, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#LIBORVolatilityModelFourParameterExponentialFormIntegrated(TimeDiscretization,
   * TimeDiscretization, double, double, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelFourParameterExponentialFormIntegrated.<init>(TimeDiscretization, TimeDiscretization, double, double, double, double, boolean)"
  })
  public void testNewLIBORVolatilityModelFourParameterExponentialFormIntegrated2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelFourParameterExponentialFormIntegrated
        actualLiborVolatilityModelFourParameterExponentialFormIntegrated =
            new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
                timeDiscretization, liborPeriodDiscretization, 10.0d, 10.0d, 10.0d, 10.0d, true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelFourParameterExponentialFormIntegrated.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        4, actualLiborVolatilityModelFourParameterExponentialFormIntegrated.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelFourParameterExponentialFormIntegrated
            .getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualLiborVolatilityModelFourParameterExponentialFormIntegrated.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#LIBORVolatilityModelFourParameterExponentialFormIntegrated(TimeDiscretization,
   * TimeDiscretization, RandomVariable, RandomVariable, RandomVariable, RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#LIBORVolatilityModelFourParameterExponentialFormIntegrated(TimeDiscretization,
   * TimeDiscretization, RandomVariable, RandomVariable, RandomVariable, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelFourParameterExponentialFormIntegrated.<init>(TimeDiscretization, TimeDiscretization, RandomVariable, RandomVariable, RandomVariable, RandomVariable, boolean)"
  })
  public void testNewLIBORVolatilityModelFourParameterExponentialFormIntegrated3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFromDoubleArray a = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray b = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray c = new RandomVariableFromDoubleArray(10.0d);

    // Act
    LIBORVolatilityModelFourParameterExponentialFormIntegrated
        actualLiborVolatilityModelFourParameterExponentialFormIntegrated =
            new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
                timeDiscretization,
                liborPeriodDiscretization,
                a,
                b,
                c,
                new RandomVariableFromDoubleArray(10.0d),
                true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelFourParameterExponentialFormIntegrated.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        4, actualLiborVolatilityModelFourParameterExponentialFormIntegrated.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelFourParameterExponentialFormIntegrated
            .getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualLiborVolatilityModelFourParameterExponentialFormIntegrated.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialFormIntegrated#getParameter()}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORVolatilityModelFourParameterExponentialFormIntegrated.getParameter()"
  })
  public void testGetParameter_thenFirstElementReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialFormIntegrated
        liborVolatilityModelFourParameterExponentialFormIntegrated =
            new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    RandomVariable[] actualParameter =
        liborVolatilityModelFourParameterExponentialFormIntegrated.getParameter();

    // Assert
    assertTrue(actualParameter[0] instanceof Scalar);
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[2] instanceof Scalar);
    assertTrue(actualParameter[3] instanceof Scalar);
    assertEquals(4, actualParameter.length);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialFormIntegrated#getParameter()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORVolatilityModelFourParameterExponentialFormIntegrated.getParameter()"
  })
  public void testGetParameter_thenReturnNull() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialFormIntegrated
        liborVolatilityModelFourParameterExponentialFormIntegrated =
            new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                false);

    // Act and Assert
    assertNull(liborVolatilityModelFourParameterExponentialFormIntegrated.getParameter());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModelFourParameterExponentialFormIntegrated LIBORVolatilityModelFourParameterExponentialFormIntegrated.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialFormIntegrated
        liborVolatilityModelFourParameterExponentialFormIntegrated =
            new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                false);

    // Act
    LIBORVolatilityModelFourParameterExponentialFormIntegrated actualCloneWithModifiedParameter =
        liborVolatilityModelFourParameterExponentialFormIntegrated.getCloneWithModifiedParameter(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertSame(
        liborVolatilityModelFourParameterExponentialFormIntegrated,
        actualCloneWithModifiedParameter);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialFormIntegrated#getVolatility(int,
   * int)}.
   *
   * <ul>
   *   <li>Then CloneIndependent return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#getVolatility(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelFourParameterExponentialFormIntegrated.getVolatility(int, int)"
  })
  public void testGetVolatility_thenCloneIndependentReturnRandomVariableDifferentiableAAD() {
    // Arrange
    RandomVariableDifferentiableAADFactory randomVariableFactory =
        new RandomVariableDifferentiableAADFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelFourParameterExponentialFormIntegrated
        liborVolatilityModelFourParameterExponentialFormIntegrated =
            new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
                randomVariableFactory,
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    RandomVariable actualVolatility =
        liborVolatilityModelFourParameterExponentialFormIntegrated.getVolatility(0, 1);

    // Assert
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVolatility).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVolatility).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVolatility).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVolatility).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVolatility)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVolatility).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualVolatility).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVolatility instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVolatility.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVolatility.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVolatility.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVolatility.getValues() instanceof Scalar);
    assertTrue(actualVolatility.isNaN() instanceof Scalar);
    assertNull(actualVolatility.getRealizations());
    assertNull(actualVolatility.getOperator());
    assertNull(actualVolatility.getRealizationsStream());
    assertEquals(0.0d, actualVolatility.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualVolatility.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualVolatility.getStandardError(), 0.0);
    assertEquals(0.0d, actualVolatility.getVariance(), 0.0);
    assertEquals(1, actualVolatility.size());
    assertEquals(12.434535758272109d, actualVolatility.getAverage(), 0.0);
    assertEquals(12.434535758272109d, actualVolatility.getMax(), 0.0);
    assertEquals(12.434535758272109d, actualVolatility.getMin(), 0.0);
    assertEquals(3, actualVolatility.getTypePriority());
    assertEquals(4, ((RandomVariableDifferentiableAAD) actualVolatility).getGradient().size());
    assertTrue(actualVolatility.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualVolatility.getFiltrationTime(), 0.0);
    assertSame(
        randomVariableFactory, ((RandomVariableDifferentiableAAD) actualVolatility).getFactory());
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialFormIntegrated#getVolatility(int,
   * int)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 10.00000000000001}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#getVolatility(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelFourParameterExponentialFormIntegrated.getVolatility(int, int)"
  })
  public void testGetVolatility_thenReturnAverageIs1000000000000001() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(0.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialFormIntegrated
        liborVolatilityModelFourParameterExponentialFormIntegrated =
            new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    RandomVariable actualVolatility =
        liborVolatilityModelFourParameterExponentialFormIntegrated.getVolatility(1, 1);

    // Assert
    assertTrue(actualVolatility instanceof Scalar);
    assertEquals(10.00000000000001d, actualVolatility.getAverage(), 0.0);
    assertEquals(10.00000000000001d, actualVolatility.getMax(), 0.0);
    assertEquals(10.00000000000001d, actualVolatility.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialFormIntegrated#getVolatility(int,
   * int)}.
   *
   * <ul>
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#getVolatility(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelFourParameterExponentialFormIntegrated.getVolatility(int, int)"
  })
  public void testGetVolatility_thenReturnAverageIsZero() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialFormIntegrated
        liborVolatilityModelFourParameterExponentialFormIntegrated =
            new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    RandomVariable actualVolatility =
        liborVolatilityModelFourParameterExponentialFormIntegrated.getVolatility(1, 1);

    // Assert
    assertTrue(actualVolatility instanceof Scalar);
    assertEquals(0.0d, actualVolatility.getAverage(), 0.0);
    assertEquals(0.0d, actualVolatility.getMax(), 0.0);
    assertEquals(0.0d, actualVolatility.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialFormIntegrated#getVolatility(int,
   * int)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#getVolatility(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelFourParameterExponentialFormIntegrated.getVolatility(int, int)"
  })
  public void testGetVolatility_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelFourParameterExponentialFormIntegrated
        liborVolatilityModelFourParameterExponentialFormIntegrated =
            new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
                randomVariableFactory,
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    RandomVariable actualVolatility =
        liborVolatilityModelFourParameterExponentialFormIntegrated.getVolatility(0, 1);

    // Assert
    assertTrue(actualVolatility instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVolatility.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualVolatility.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualVolatility.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualVolatility.getStandardError(), 0.0);
    assertEquals(0.0d, actualVolatility.getVariance(), 0.0);
    assertEquals(1, actualVolatility.getTypePriority());
    assertEquals(1, actualVolatility.size());
    assertEquals(12.434535758272109d, actualVolatility.getAverage(), 0.0);
    assertEquals(12.434535758272109d, actualVolatility.getMax(), 0.0);
    assertEquals(12.434535758272109d, actualVolatility.getMin(), 0.0);
    assertTrue(actualVolatility.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualVolatility.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {12.434535758272109d}, actualVolatility.getRealizations(), 0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialFormIntegrated#getVolatility(int,
   * int)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#getVolatility(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelFourParameterExponentialFormIntegrated.getVolatility(int, int)"
  })
  public void testGetVolatility_thenReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialFormIntegrated
        liborVolatilityModelFourParameterExponentialFormIntegrated =
            new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    RandomVariable actualVolatility =
        liborVolatilityModelFourParameterExponentialFormIntegrated.getVolatility(0, 1);

    // Assert
    assertTrue(actualVolatility instanceof Scalar);
    assertEquals(12.434535758272109d, actualVolatility.getAverage(), 0.0);
    assertEquals(12.434535758272109d, actualVolatility.getMax(), 0.0);
    assertEquals(12.434535758272109d, actualVolatility.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialFormIntegrated#clone()}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORVolatilityModelFourParameterExponentialFormIntegrated.clone()"})
  public void testClone() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelFourParameterExponentialFormIntegrated
        liborVolatilityModelFourParameterExponentialFormIntegrated =
            new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
                timeDiscretization, liborPeriodDiscretization, 10.0d, 10.0d, 10.0d, 10.0d, true);

    // Act
    Object actualCloneResult = liborVolatilityModelFourParameterExponentialFormIntegrated.clone();

    // Assert
    assertTrue(
        actualCloneResult instanceof LIBORVolatilityModelFourParameterExponentialFormIntegrated);
    TimeDiscretization timeDiscretization2 =
        ((LIBORVolatilityModelFourParameterExponentialFormIntegrated) actualCloneResult)
            .getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        4,
        ((LIBORVolatilityModelFourParameterExponentialFormIntegrated) actualCloneResult)
            .getParameter()
            .length);
    assertSame(
        liborPeriodDiscretization,
        ((LIBORVolatilityModelFourParameterExponentialFormIntegrated) actualCloneResult)
            .getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        ((LIBORVolatilityModelFourParameterExponentialFormIntegrated) actualCloneResult)
            .getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialFormIntegrated#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModel LIBORVolatilityModelFourParameterExponentialFormIntegrated.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelFourParameterExponentialFormIntegrated
        liborVolatilityModelFourParameterExponentialFormIntegrated =
            new LIBORVolatilityModelFourParameterExponentialFormIntegrated(
                timeDiscretization, liborPeriodDiscretization, 10.0d, 10.0d, 10.0d, 10.0d, true);

    // Act
    LIBORVolatilityModel actualCloneWithModifiedData =
        liborVolatilityModelFourParameterExponentialFormIntegrated.getCloneWithModifiedData(null);

    // Assert
    assertTrue(
        actualCloneWithModifiedData
            instanceof LIBORVolatilityModelFourParameterExponentialFormIntegrated);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(4, actualCloneWithModifiedData.getParameter().length);
    assertSame(
        liborPeriodDiscretization, actualCloneWithModifiedData.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
  }
}
