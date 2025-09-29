package net.finmath.stochastic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.RandomVariableLazyEvaluation;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class RandomVariableDiffblueTest {
  /**
   * Test {@link RandomVariable#getValues()}.
   *
   * <p>Method under test: {@link RandomVariable#getValues()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariable.getValues()"})
  public void testGetValues() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualValues = randomVariableFromDoubleArray.getValues();

    // Assert
    assertSame(randomVariableFromDoubleArray, actualValues);
  }

  /**
   * Test {@link RandomVariable#appy(RandomOperator)}.
   *
   * <p>Method under test: {@link RandomVariable#appy(RandomOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariable.appy(RandomOperator)"})
  public void testAppy() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    RandomOperator operator = mock(RandomOperator.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(operator.apply(Mockito.<RandomVariable>any())).thenReturn(randomVariableFromDoubleArray2);

    // Act
    RandomVariable actualAppyResult = randomVariableFromDoubleArray.appy(operator);

    // Assert
    verify(operator).apply(isA(RandomVariable.class));
    assertSame(randomVariableFromDoubleArray2, actualAppyResult);
  }

  /**
   * Test {@link RandomVariable#bus(double)} with {@code double}.
   *
   * <p>Method under test: {@link RandomVariable#bus(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariable.bus(double)"})
  public void testBusWithDouble() {
    // Arrange and Act
    RandomVariable actualBusResult = new RandomVariableLazyEvaluation(10.0d).bus(10.0d);

    // Assert
    assertTrue(actualBusResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.variance() instanceof RandomVariableLazyEvaluation);
    assertNull(actualBusResult.getOperator());
    assertEquals(0, actualBusResult.getTypePriority());
    assertEquals(0.0d, actualBusResult.getAverage(), 0.0);
    assertEquals(0.0d, actualBusResult.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualBusResult.getMax(), 0.0);
    assertEquals(0.0d, actualBusResult.getMin(), 0.0);
    assertEquals(0.0d, actualBusResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBusResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBusResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualBusResult.getVariance(), 0.0);
    assertEquals(1, actualBusResult.size());
    assertTrue(actualBusResult.isDeterministic());
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariable#vid(double)} with {@code double}.
   *
   * <p>Method under test: {@link RandomVariable#vid(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariable.vid(double)"})
  public void testVidWithDouble() {
    // Arrange and Act
    RandomVariable actualVidResult = new RandomVariableLazyEvaluation(10.0d).vid(10.0d);

    // Assert
    assertTrue(actualVidResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.variance() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult instanceof RandomVariableLazyEvaluation);
    assertNull(actualVidResult.getOperator());
    assertEquals(0, actualVidResult.getTypePriority());
    assertEquals(0.0d, actualVidResult.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualVidResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualVidResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualVidResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualVidResult.getVariance(), 0.0);
    assertEquals(1, actualVidResult.size());
    assertEquals(1.0d, actualVidResult.getAverage(), 0.0);
    assertEquals(1.0d, actualVidResult.getMax(), 0.0);
    assertEquals(1.0d, actualVidResult.getMin(), 0.0);
    assertTrue(actualVidResult.isDeterministic());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariable#expectation()}.
   *
   * <p>Method under test: {@link RandomVariable#expectation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariable.expectation()"})
  public void testExpectation() {
    // Arrange and Act
    RandomVariable actualExpectationResult = new RandomVariableFromDoubleArray(10.0d).expectation();

    // Assert
    assertTrue(actualExpectationResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpectationResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpectationResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpectationResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpectationResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpectationResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpectationResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpectationResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpectationResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpectationResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualExpectationResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualExpectationResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualExpectationResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualExpectationResult.getVariance(), 0.0);
    assertEquals(1, actualExpectationResult.getTypePriority());
    assertEquals(1, actualExpectationResult.size());
    assertEquals(10.0d, actualExpectationResult.getAverage(), 0.0);
    assertEquals(10.0d, actualExpectationResult.getMax(), 0.0);
    assertEquals(10.0d, actualExpectationResult.getMin(), 0.0);
    assertTrue(actualExpectationResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualExpectationResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualExpectationResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariable#variance()}.
   *
   * <p>Method under test: {@link RandomVariable#variance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariable.variance()"})
  public void testVariance() {
    // Arrange and Act
    RandomVariable actualVarianceResult = new RandomVariableFromDoubleArray(10.0d).variance();

    // Assert
    assertTrue(actualVarianceResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVarianceResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualVarianceResult.getAverage(), 0.0);
    assertEquals(0.0d, actualVarianceResult.getMax(), 0.0);
    assertEquals(0.0d, actualVarianceResult.getMin(), 0.0);
    assertEquals(0.0d, actualVarianceResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualVarianceResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualVarianceResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualVarianceResult.getVariance(), 0.0);
    assertEquals(1, actualVarianceResult.getTypePriority());
    assertEquals(1, actualVarianceResult.size());
    assertTrue(actualVarianceResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualVarianceResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualVarianceResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariable#covariance(RandomVariable)}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariable#covariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariable.covariance(RandomVariable)"})
  public void testCovariance_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualCovarianceResult =
        randomVariableFromDoubleArray.covariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualCovarianceResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovarianceResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovarianceResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovarianceResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovarianceResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovarianceResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovarianceResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovarianceResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovarianceResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovarianceResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovarianceResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualCovarianceResult.getAverage(), 0.0);
    assertEquals(0.0d, actualCovarianceResult.getMax(), 0.0);
    assertEquals(0.0d, actualCovarianceResult.getMin(), 0.0);
    assertEquals(0.0d, actualCovarianceResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCovarianceResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCovarianceResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCovarianceResult.getVariance(), 0.0);
    assertEquals(1, actualCovarianceResult.getTypePriority());
    assertEquals(1, actualCovarianceResult.size());
    assertTrue(actualCovarianceResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualCovarianceResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualCovarianceResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariable#covariance(RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariable#covariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariable.covariance(RandomVariable)"})
  public void testCovariance_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.average()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(value.sub(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualCovarianceResult = randomVariableFromDoubleArray.covariance(value);

    // Assert
    verify(value).average();
    verify(value).sub(isA(RandomVariable.class));
    assertTrue(actualCovarianceResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovarianceResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCovarianceResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCovarianceResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCovarianceResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCovarianceResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCovarianceResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCovarianceResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualCovarianceResult).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCovarianceResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCovarianceResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCovarianceResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCovarianceResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualCovarianceResult.getAverage(), 0.0);
    assertEquals(0.0d, actualCovarianceResult.getMax(), 0.0);
    assertEquals(0.0d, actualCovarianceResult.getMin(), 0.0);
    assertEquals(0.0d, actualCovarianceResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCovarianceResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCovarianceResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCovarianceResult.getVariance(), 0.0);
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualCovarianceResult).getGradient().size());
    assertEquals(1, actualCovarianceResult.size());
    assertEquals(3, actualCovarianceResult.getTypePriority());
    assertTrue(actualCovarianceResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualCovarianceResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualCovarianceResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariable#getConditionalExpectation(ConditionalExpectationEstimator)}.
   *
   * <p>Method under test: {@link
   * RandomVariable#getConditionalExpectation(ConditionalExpectationEstimator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariable.getConditionalExpectation(ConditionalExpectationEstimator)"
  })
  public void testGetConditionalExpectation() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    ConditionalExpectationEstimator conditionalExpectationOperator =
        mock(ConditionalExpectationEstimator.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(conditionalExpectationOperator.getConditionalExpectation(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray);

    // Act
    RandomVariable actualConditionalExpectation =
        randomVariableLazyEvaluation.getConditionalExpectation(conditionalExpectationOperator);

    // Assert
    verify(conditionalExpectationOperator).getConditionalExpectation(isA(RandomVariable.class));
    assertSame(randomVariableFromDoubleArray, actualConditionalExpectation);
  }

  /**
   * Test {@link RandomVariable#expm1()}.
   *
   * <p>Method under test: {@link RandomVariable#expm1()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariable.expm1()"})
  public void testExpm1() {
    // Arrange and Act
    RandomVariable actualExpm1Result = new RandomVariableFromFloatArray(10.0d).expm1();

    // Assert
    assertTrue(actualExpm1Result.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpm1Result.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpm1Result.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpm1Result.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpm1Result instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpm1Result.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpm1Result.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpm1Result.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpm1Result.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpm1Result.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualExpm1Result.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualExpm1Result.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualExpm1Result.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualExpm1Result.getStandardError(), 0.0);
    assertEquals(0.0d, actualExpm1Result.getVariance(), 0.0);
    assertEquals(1, actualExpm1Result.getTypePriority());
    assertEquals(1, actualExpm1Result.size());
    assertEquals(22025.465794806718d, actualExpm1Result.getAverage(), 0.0);
    assertEquals(22025.465794806718d, actualExpm1Result.getMax(), 0.0);
    assertEquals(22025.465794806718d, actualExpm1Result.getMin(), 0.0);
    assertTrue(actualExpm1Result.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualExpm1Result.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {22025.465794806718d}, actualExpm1Result.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariable#addSumProduct(List, List)} with {@code List}, {@code List}.
   *
   * <p>Method under test: {@link RandomVariable#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariable.addSumProduct(List, List)"})
  public void testAddSumProductWithListList() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableLazyEvaluation.addSumProduct(factor1, new ArrayList<>());

    // Assert
    assertSame(randomVariableLazyEvaluation, actualAddSumProductResult);
  }

  /**
   * Test {@link RandomVariable#addSumProduct(List, List)} with {@code List}, {@code List}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariable#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariable.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableLazyEvaluation.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariable#addSumProduct(List, List)} with {@code List}, {@code List}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariable#addSumProduct(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariable.addSumProduct(List, List)"})
  public void testAddSumProductWithListList_thenReturnRandomVariableLazyEvaluation2() {
    // Arrange
    RandomVariableLazyEvaluation randomVariableLazyEvaluation =
        new RandomVariableLazyEvaluation(10.0d);

    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    factor1.add(new RandomVariableFromDoubleArray(10.0d));

    ArrayList<RandomVariable> factor2 = new ArrayList<>();
    factor2.add(new RandomVariableFromDoubleArray(10.0d));
    factor2.add(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableLazyEvaluation.addSumProduct(factor1, factor2);

    // Assert
    assertTrue(actualAddSumProductResult instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableLazyEvaluation);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariable#addSumProduct(RandomVariable[], RandomVariable[])} with {@code
   * RandomVariable[]}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link RandomVariable#addSumProduct(RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariable.addSumProduct(RandomVariable[], RandomVariable[])"
  })
  public void testAddSumProductWithRandomVariableRandomVariable() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualAddSumProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualAddSumProductResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAddSumProductResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAddSumProductResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAddSumProductResult.getVariance(), 0.0);
    assertEquals(1, actualAddSumProductResult.getTypePriority());
    assertEquals(1, actualAddSumProductResult.size());
    assertEquals(110.0d, actualAddSumProductResult.getAverage(), 0.0);
    assertEquals(110.0d, actualAddSumProductResult.getMax(), 0.0);
    assertEquals(110.0d, actualAddSumProductResult.getMin(), 0.0);
    assertTrue(actualAddSumProductResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAddSumProductResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariable#addSumProduct(RandomVariable[], RandomVariable[])} with {@code
   * RandomVariable[]}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link RandomVariable#addSumProduct(RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariable.addSumProduct(RandomVariable[], RandomVariable[])"
  })
  public void testAddSumProductWithRandomVariableRandomVariable2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddSumProductResult =
        randomVariableFromDoubleArray.addSumProduct(
            new RandomVariable[] {RandomVariableDifferentiableAAD.of(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualAddSumProductResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddSumProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualAddSumProductResult)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualAddSumProductResult.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualAddSumProductResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAddSumProductResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAddSumProductResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAddSumProductResult.getVariance(), 0.0);
    assertEquals(
        1, ((RandomVariableDifferentiableAAD) actualAddSumProductResult).getGradient().size());
    assertEquals(1, actualAddSumProductResult.size());
    assertEquals(110.0d, actualAddSumProductResult.getAverage(), 0.0);
    assertEquals(110.0d, actualAddSumProductResult.getMax(), 0.0);
    assertEquals(110.0d, actualAddSumProductResult.getMin(), 0.0);
    assertEquals(3, actualAddSumProductResult.getTypePriority());
    assertTrue(actualAddSumProductResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAddSumProductResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {110.0d}, actualAddSumProductResult.getRealizations(), 0.0);
  }
}
