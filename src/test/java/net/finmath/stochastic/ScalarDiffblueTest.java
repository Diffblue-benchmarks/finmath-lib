package net.finmath.stochastic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;
import java.util.stream.DoubleStream;
import net.finmath.functions.DoubleTernaryOperator;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ScalarDiffblueTest {
  /**
   * Test {@link Scalar#of(double)}.
   *
   * <p>Method under test: {@link Scalar#of(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Scalar Scalar.of(double)"})
  public void testOf() {
    // Arrange and Act
    Scalar actualOfResult = Scalar.of(10.0d);

    // Assert
    assertTrue(actualOfResult.variance() instanceof Scalar);
    assertTrue(actualOfResult.abs() instanceof Scalar);
    assertTrue(actualOfResult.cos() instanceof Scalar);
    assertTrue(actualOfResult.exp() instanceof Scalar);
    assertTrue(actualOfResult.expm1() instanceof Scalar);
    assertTrue(actualOfResult.invert() instanceof Scalar);
    assertTrue(actualOfResult.isNaN() instanceof Scalar);
    assertTrue(actualOfResult.sin() instanceof Scalar);
    assertTrue(actualOfResult.sqrt() instanceof Scalar);
    assertTrue(actualOfResult.squared() instanceof Scalar);
    assertNull(actualOfResult.getRealizations());
    assertNull(actualOfResult.getOperator());
    assertNull(actualOfResult.getRealizationsStream());
    assertEquals(0, actualOfResult.getTypePriority());
    assertEquals(0.0d, actualOfResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualOfResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualOfResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualOfResult.getVariance(), 0.0);
    assertEquals(1, actualOfResult.size());
    assertEquals(10.0d, actualOfResult.getAverage(), 0.0);
    assertEquals(10.0d, actualOfResult.getMax(), 0.0);
    assertEquals(10.0d, actualOfResult.getMin(), 0.0);
    assertTrue(actualOfResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualOfResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualOfResult.expectation();
    assertSame(actualOfResult, actualExpectationResult);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Scalar#Scalar(double)}
   *   <li>{@link Scalar#toString()}
   *   <li>{@link Scalar#getAverage()}
   *   <li>{@link Scalar#getFiltrationTime()}
   *   <li>{@link Scalar#getMax()}
   *   <li>{@link Scalar#getMin()}
   *   <li>{@link Scalar#getOperator()}
   *   <li>{@link Scalar#getRealizations()}
   *   <li>{@link Scalar#getRealizationsStream()}
   *   <li>{@link Scalar#getSampleVariance()}
   *   <li>{@link Scalar#getStandardDeviation()}
   *   <li>{@link Scalar#getStandardError()}
   *   <li>{@link Scalar#getTypePriority()}
   *   <li>{@link Scalar#getVariance()}
   *   <li>{@link Scalar#isDeterministic()}
   *   <li>{@link Scalar#size()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Scalar.<init>(double)",
    "double Scalar.getAverage()",
    "double Scalar.getFiltrationTime()",
    "double Scalar.getMax()",
    "double Scalar.getMin()",
    "IntToDoubleFunction Scalar.getOperator()",
    "double[] Scalar.getRealizations()",
    "DoubleStream Scalar.getRealizationsStream()",
    "double Scalar.getSampleVariance()",
    "double Scalar.getStandardDeviation()",
    "double Scalar.getStandardError()",
    "int Scalar.getTypePriority()",
    "double Scalar.getVariance()",
    "boolean Scalar.isDeterministic()",
    "int Scalar.size()",
    "String Scalar.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    Scalar actualScalar = new Scalar(10.0d);
    String actualToStringResult = actualScalar.toString();
    double actualAverage = actualScalar.getAverage();
    double actualFiltrationTime = actualScalar.getFiltrationTime();
    double actualMax = actualScalar.getMax();
    double actualMin = actualScalar.getMin();
    IntToDoubleFunction actualOperator = actualScalar.getOperator();
    double[] actualRealizations = actualScalar.getRealizations();
    DoubleStream actualRealizationsStream = actualScalar.getRealizationsStream();
    double actualSampleVariance = actualScalar.getSampleVariance();
    double actualStandardDeviation = actualScalar.getStandardDeviation();
    double actualStandardError = actualScalar.getStandardError();
    int actualTypePriority = actualScalar.getTypePriority();
    double actualVariance = actualScalar.getVariance();
    boolean actualIsDeterministicResult = actualScalar.isDeterministic();

    // Assert
    assertEquals(
        "Scalar [value=10.0, filtrationTime=-Infinity, typePriority()=0]", actualToStringResult);
    assertNull(actualRealizations);
    assertNull(actualOperator);
    assertNull(actualRealizationsStream);
    assertEquals(0, actualTypePriority);
    assertEquals(0.0d, actualSampleVariance, 0.0);
    assertEquals(0.0d, actualStandardDeviation, 0.0);
    assertEquals(0.0d, actualStandardError, 0.0);
    assertEquals(0.0d, actualVariance, 0.0);
    assertEquals(1, actualScalar.size());
    assertEquals(10.0d, actualAverage, 0.0);
    assertEquals(10.0d, actualMax, 0.0);
    assertEquals(10.0d, actualMin, 0.0);
    assertTrue(actualIsDeterministicResult);
    assertEquals(Double.NEGATIVE_INFINITY, actualFiltrationTime, 0.0);
  }

  /**
   * Test {@link Scalar#arrayOf(double[])}.
   *
   * <p>Method under test: {@link Scalar#arrayOf(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Scalar[] Scalar.arrayOf(double[])"})
  public void testArrayOf() {
    // Arrange and Act
    Scalar[] actualArrayOfResult = Scalar.arrayOf(new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    Scalar scalar = actualArrayOfResult[0];
    assertTrue(scalar.variance() instanceof Scalar);
    Scalar scalar2 = actualArrayOfResult[1];
    assertTrue(scalar2.variance() instanceof Scalar);
    Scalar scalar3 = actualArrayOfResult[2];
    assertTrue(scalar3.variance() instanceof Scalar);
    Scalar scalar4 = actualArrayOfResult[3];
    assertTrue(scalar4.variance() instanceof Scalar);
    assertTrue(scalar.abs() instanceof Scalar);
    assertTrue(scalar2.abs() instanceof Scalar);
    assertTrue(scalar3.abs() instanceof Scalar);
    assertTrue(scalar4.abs() instanceof Scalar);
    assertTrue(scalar.cos() instanceof Scalar);
    assertTrue(scalar2.cos() instanceof Scalar);
    assertTrue(scalar3.cos() instanceof Scalar);
    assertTrue(scalar4.cos() instanceof Scalar);
    assertTrue(scalar.exp() instanceof Scalar);
    assertTrue(scalar2.exp() instanceof Scalar);
    assertTrue(scalar3.exp() instanceof Scalar);
    assertTrue(scalar4.exp() instanceof Scalar);
    assertTrue(scalar.expm1() instanceof Scalar);
    assertTrue(scalar2.expm1() instanceof Scalar);
    assertTrue(scalar3.expm1() instanceof Scalar);
    assertTrue(scalar4.expm1() instanceof Scalar);
    assertTrue(scalar.invert() instanceof Scalar);
    assertTrue(scalar2.invert() instanceof Scalar);
    assertTrue(scalar3.invert() instanceof Scalar);
    assertTrue(scalar4.invert() instanceof Scalar);
    assertTrue(scalar.isNaN() instanceof Scalar);
    assertTrue(scalar2.isNaN() instanceof Scalar);
    assertTrue(scalar3.isNaN() instanceof Scalar);
    assertTrue(scalar4.isNaN() instanceof Scalar);
    assertTrue(scalar.sin() instanceof Scalar);
    assertTrue(scalar2.sin() instanceof Scalar);
    assertTrue(scalar3.sin() instanceof Scalar);
    assertTrue(scalar4.sin() instanceof Scalar);
    assertTrue(scalar.sqrt() instanceof Scalar);
    assertTrue(scalar2.sqrt() instanceof Scalar);
    assertTrue(scalar3.sqrt() instanceof Scalar);
    assertTrue(scalar4.sqrt() instanceof Scalar);
    assertTrue(scalar.squared() instanceof Scalar);
    assertTrue(scalar2.squared() instanceof Scalar);
    assertTrue(scalar3.squared() instanceof Scalar);
    assertTrue(scalar4.squared() instanceof Scalar);
    assertNull(scalar.getRealizations());
    assertNull(scalar2.getRealizations());
    assertNull(scalar3.getRealizations());
    assertNull(scalar4.getRealizations());
    assertNull(scalar.getOperator());
    assertNull(scalar2.getOperator());
    assertNull(scalar3.getOperator());
    assertNull(scalar4.getOperator());
    assertNull(scalar.getRealizationsStream());
    assertNull(scalar2.getRealizationsStream());
    assertNull(scalar3.getRealizationsStream());
    assertNull(scalar4.getRealizationsStream());
    assertEquals(0, scalar.getTypePriority());
    assertEquals(0, scalar2.getTypePriority());
    assertEquals(0, scalar3.getTypePriority());
    assertEquals(0, scalar4.getTypePriority());
    assertEquals(0.0d, scalar.getSampleVariance(), 0.0);
    assertEquals(0.0d, scalar2.getSampleVariance(), 0.0);
    assertEquals(0.0d, scalar3.getSampleVariance(), 0.0);
    assertEquals(0.0d, scalar4.getSampleVariance(), 0.0);
    assertEquals(0.0d, scalar.getStandardDeviation(), 0.0);
    assertEquals(0.0d, scalar2.getStandardDeviation(), 0.0);
    assertEquals(0.0d, scalar3.getStandardDeviation(), 0.0);
    assertEquals(0.0d, scalar4.getStandardDeviation(), 0.0);
    assertEquals(0.0d, scalar.getStandardError(), 0.0);
    assertEquals(0.0d, scalar2.getStandardError(), 0.0);
    assertEquals(0.0d, scalar3.getStandardError(), 0.0);
    assertEquals(0.0d, scalar4.getStandardError(), 0.0);
    assertEquals(0.0d, scalar.getVariance(), 0.0);
    assertEquals(0.0d, scalar2.getVariance(), 0.0);
    assertEquals(0.0d, scalar3.getVariance(), 0.0);
    assertEquals(0.0d, scalar4.getVariance(), 0.0);
    assertEquals(1, scalar.size());
    assertEquals(1, scalar2.size());
    assertEquals(1, scalar3.size());
    assertEquals(1, scalar4.size());
    assertEquals(1.0d, scalar2.getAverage(), 0.0);
    assertEquals(1.0d, scalar4.getAverage(), 0.0);
    assertEquals(1.0d, scalar2.getMax(), 0.0);
    assertEquals(1.0d, scalar4.getMax(), 0.0);
    assertEquals(1.0d, scalar2.getMin(), 0.0);
    assertEquals(1.0d, scalar4.getMin(), 0.0);
    assertEquals(10.0d, scalar.getAverage(), 0.0);
    assertEquals(10.0d, scalar3.getAverage(), 0.0);
    assertEquals(10.0d, scalar.getMax(), 0.0);
    assertEquals(10.0d, scalar3.getMax(), 0.0);
    assertEquals(10.0d, scalar.getMin(), 0.0);
    assertEquals(10.0d, scalar3.getMin(), 0.0);
    assertEquals(4, actualArrayOfResult.length);
    assertTrue(scalar.isDeterministic());
    assertTrue(scalar2.isDeterministic());
    assertTrue(scalar3.isDeterministic());
    assertTrue(scalar4.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, scalar.getFiltrationTime(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, scalar2.getFiltrationTime(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, scalar3.getFiltrationTime(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, scalar4.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = scalar.expectation();
    assertSame(scalar, actualExpectationResult);
    RandomVariable actualExpectationResult2 = scalar2.expectation();
    assertSame(scalar2, actualExpectationResult2);
    RandomVariable actualExpectationResult3 = scalar3.expectation();
    assertSame(scalar3, actualExpectationResult3);
    RandomVariable actualExpectationResult4 = scalar4.expectation();
    assertSame(scalar4, actualExpectationResult4);
  }

  /**
   * Test {@link Scalar#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link Scalar#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Scalar.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    boolean actualEqualsResult =
        ofResult.equals(
            (RandomVariable) new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link Scalar#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Given {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Scalar.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_givenFalse() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.isDeterministic()).thenReturn(false);

    // Act
    boolean actualEqualsResult = ofResult.equals((RandomVariable) randomVariable);

    // Assert
    verify(randomVariable).isDeterministic();
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link Scalar#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Scalar.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    boolean actualEqualsResult =
        ofResult.equals((RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualEqualsResult);
  }

  /**
   * Test {@link Scalar#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Scalar.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable_whenScalarWithValueIsTen_thenReturnTrue() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act and Assert
    assertTrue(ofResult.equals((RandomVariable) Scalar.of(10.0d)));
  }

  /**
   * Test {@link Scalar#get(int)}.
   *
   * <p>Method under test: {@link Scalar#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Scalar.get(int)"})
  public void testGet() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act and Assert
    assertEquals(10.0d, ofResult.get(1), 0.0);
  }

  /**
   * Test {@link Scalar#doubleValue()}.
   *
   * <p>Method under test: {@link Scalar#doubleValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double Scalar.doubleValue()"})
  public void testDoubleValue() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act and Assert
    assertEquals(10.0d, ofResult.doubleValue().doubleValue(), 0.0);
  }

  /**
   * Test {@link Scalar#getAverage(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Scalar.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    double actualAverage = ofResult.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(100.0d, actualAverage, 0.0);
  }

  /**
   * Test {@link Scalar#getAverage(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Scalar.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable_whenScalarWithValueIsTen_thenReturnOneHundred() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act and Assert
    assertEquals(100.0d, ofResult.getAverage(Scalar.of(10.0d)), 0.0);
  }

  /**
   * Test {@link Scalar#getVariance(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link Scalar#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Scalar.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    double actualVariance = ofResult.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link Scalar#getStandardDeviation(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link Scalar#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Scalar.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    double actualStandardDeviation =
        ofResult.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link Scalar#getStandardError(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link Scalar#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Scalar.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    double actualStandardError =
        ofResult.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardError, 0.0);
  }

  /**
   * Test {@link Scalar#getQuantile(double)} with {@code quantile}.
   *
   * <p>Method under test: {@link Scalar#getQuantile(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Scalar.getQuantile(double)"})
  public void testGetQuantileWithQuantile() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act and Assert
    assertEquals(10.0d, ofResult.getQuantile(10.0d), 0.0);
  }

  /**
   * Test {@link Scalar#getQuantile(double, RandomVariable)} with {@code quantile}, {@code
   * probabilities}.
   *
   * <p>Method under test: {@link Scalar#getQuantile(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Scalar.getQuantile(double, RandomVariable)"})
  public void testGetQuantileWithQuantileProbabilities() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act and Assert
    assertEquals(10.0d, ofResult.getQuantile(10.0d, new RandomVariableFromDoubleArray(10.0d)), 0.0);
  }

  /**
   * Test {@link Scalar#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#getQuantileExpectation(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Scalar.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_when05() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act and Assert
    assertEquals(10.0d, ofResult.getQuantileExpectation(0.5d, 10.0d), 0.0);
  }

  /**
   * Test {@link Scalar#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When one.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#getQuantileExpectation(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Scalar.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_whenOne() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act and Assert
    assertEquals(10.0d, ofResult.getQuantileExpectation(1.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link Scalar#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When ten.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#getQuantileExpectation(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Scalar.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_whenTen() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act and Assert
    assertEquals(10.0d, ofResult.getQuantileExpectation(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link Scalar#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When two.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#getQuantileExpectation(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Scalar.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_whenTwo() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act and Assert
    assertEquals(10.0d, ofResult.getQuantileExpectation(2.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link Scalar#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <p>Method under test: {@link Scalar#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] Scalar.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> ofResult.getHistogram(new double[] {10.0d, 1.0d, 10.0d, 1.0d}));
  }

  /**
   * Test {@link Scalar#getHistogram(int, double)} with {@code numberOfPoints}, {@code
   * standardDeviations}.
   *
   * <p>Method under test: {@link Scalar#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] Scalar.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.getHistogram(10, 10.0d));
  }

  /**
   * Test {@link Scalar#cache()}.
   *
   * <p>Method under test: {@link Scalar#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.cache()"})
  public void testCache() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualCacheResult = ofResult.cache();

    // Assert
    assertSame(ofResult, actualCacheResult);
  }

  /**
   * Test {@link Scalar#apply(DoubleBinaryOperator, RandomVariable)} with {@code operator}, {@code
   * argument}.
   *
   * <p>Method under test: {@link Scalar#apply(DoubleBinaryOperator, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.apply(DoubleBinaryOperator, RandomVariable)"})
  public void testApplyWithOperatorArgument() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);
    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);

    // Act and Assert
    assertNull(ofResult.apply(operator, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link Scalar#apply(DoubleTernaryOperator, RandomVariable, RandomVariable)} with {@code
   * operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link Scalar#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Scalar.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);
    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertNull(ofResult.apply(operator, argument1, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link Scalar#apply(DoubleUnaryOperator)} with {@code operator}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.apply(DoubleUnaryOperator)"})
  public void testApplyWithOperator_givenTen_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    DoubleUnaryOperator operator = mock(DoubleUnaryOperator.class);
    when(operator.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    RandomVariable actualApplyResult = ofResult.apply(operator);

    // Assert
    verify(operator).applyAsDouble(10.0d);
    assertTrue(actualApplyResult.abs() instanceof Scalar);
    assertTrue(actualApplyResult.cos() instanceof Scalar);
    assertTrue(actualApplyResult.exp() instanceof Scalar);
    assertTrue(actualApplyResult.expm1() instanceof Scalar);
    assertTrue(actualApplyResult.invert() instanceof Scalar);
    assertTrue(actualApplyResult.isNaN() instanceof Scalar);
    assertTrue(actualApplyResult.sin() instanceof Scalar);
    assertTrue(actualApplyResult.sqrt() instanceof Scalar);
    assertTrue(actualApplyResult.squared() instanceof Scalar);
    assertTrue(actualApplyResult.variance() instanceof Scalar);
    assertTrue(actualApplyResult instanceof Scalar);
    assertNull(actualApplyResult.getRealizations());
    assertNull(actualApplyResult.getOperator());
    assertNull(actualApplyResult.getRealizationsStream());
    assertEquals(0, actualApplyResult.getTypePriority());
    assertEquals(0.0d, actualApplyResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyResult.getVariance(), 0.0);
    assertEquals(1, actualApplyResult.size());
    assertEquals(10.0d, actualApplyResult.getAverage(), 0.0);
    assertEquals(10.0d, actualApplyResult.getMax(), 0.0);
    assertEquals(10.0d, actualApplyResult.getMin(), 0.0);
    assertTrue(actualApplyResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualApplyResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualApplyResult.expectation();
    assertSame(actualApplyResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#apply(DoubleUnaryOperator)} with {@code operator}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.apply(DoubleUnaryOperator)"})
  public void testApplyWithOperator_thenThrowUnsupportedOperationException() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    DoubleUnaryOperator operator = mock(DoubleUnaryOperator.class);
    when(operator.applyAsDouble(anyDouble())).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.apply(operator));
    verify(operator).applyAsDouble(10.0d);
  }

  /**
   * Test {@link Scalar#cap(double)} with {@code double}.
   *
   * <p>Method under test: {@link Scalar#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.cap(double)"})
  public void testCapWithDouble() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(10.0d);

    // Assert
    assertTrue(actualCapResult.abs() instanceof Scalar);
    assertTrue(actualCapResult.cos() instanceof Scalar);
    assertTrue(actualCapResult.exp() instanceof Scalar);
    assertTrue(actualCapResult.expm1() instanceof Scalar);
    assertTrue(actualCapResult.invert() instanceof Scalar);
    assertTrue(actualCapResult.isNaN() instanceof Scalar);
    assertTrue(actualCapResult.sin() instanceof Scalar);
    assertTrue(actualCapResult.sqrt() instanceof Scalar);
    assertTrue(actualCapResult.squared() instanceof Scalar);
    assertTrue(actualCapResult.variance() instanceof Scalar);
    assertTrue(actualCapResult instanceof Scalar);
    assertNull(actualCapResult.getRealizations());
    assertNull(actualCapResult.getOperator());
    assertNull(actualCapResult.getRealizationsStream());
    assertEquals(0, actualCapResult.getTypePriority());
    assertEquals(0.0d, actualCapResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCapResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCapResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCapResult.getVariance(), 0.0);
    assertEquals(1, actualCapResult.size());
    assertEquals(10.0d, actualCapResult.getAverage(), 0.0);
    assertEquals(10.0d, actualCapResult.getMax(), 0.0);
    assertEquals(10.0d, actualCapResult.getMin(), 0.0);
    assertTrue(actualCapResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualCapResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualCapResult.expectation();
    assertSame(actualCapResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#cap(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualCapResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualCapResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCapResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCapResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCapResult.getVariance(), 0.0);
    assertEquals(1, actualCapResult.getTypePriority());
    assertEquals(1, actualCapResult.size());
    assertEquals(10.0d, actualCapResult.getAverage(), 0.0);
    assertEquals(10.0d, actualCapResult.getMax(), 0.0);
    assertEquals(10.0d, actualCapResult.getMin(), 0.0);
    assertTrue(actualCapResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualCapResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#cap(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD cap = new RandomVariableDifferentiableAAD(values, factory);

    // Act
    RandomVariable actualCapResult = ofResult.cap(cap);

    // Assert
    assertTrue(actualCapResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertSame(factory, ((RandomVariableDifferentiableAAD) actualCapResult).getFactory());
    assertArrayEquals(new double[] {10.0d}, actualCapResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#cap(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.cap(RandomVariable)"})
  public void testCapWithRandomVariable_thenValuesReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualCapResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualCapResult.getValues() instanceof Scalar);
    assertTrue(actualCapResult.isNaN() instanceof Scalar);
    assertNull(actualCapResult.getRealizations());
    assertNull(actualCapResult.getOperator());
    assertNull(actualCapResult.getRealizationsStream());
  }

  /**
   * Test {@link Scalar#cap(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.cap(RandomVariable)"})
  public void testCapWithRandomVariable_whenScalarWithValueIsTen_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualCapResult = ofResult.cap(Scalar.of(10.0d));

    // Assert
    assertTrue(actualCapResult.abs() instanceof Scalar);
    assertTrue(actualCapResult.cos() instanceof Scalar);
    assertTrue(actualCapResult.exp() instanceof Scalar);
    assertTrue(actualCapResult.expm1() instanceof Scalar);
    assertTrue(actualCapResult.invert() instanceof Scalar);
    assertTrue(actualCapResult.isNaN() instanceof Scalar);
    assertTrue(actualCapResult.sin() instanceof Scalar);
    assertTrue(actualCapResult.sqrt() instanceof Scalar);
    assertTrue(actualCapResult.squared() instanceof Scalar);
    assertTrue(actualCapResult.variance() instanceof Scalar);
    assertTrue(actualCapResult instanceof Scalar);
    assertNull(actualCapResult.getRealizations());
    assertNull(actualCapResult.getOperator());
    assertNull(actualCapResult.getRealizationsStream());
    assertEquals(0, actualCapResult.getTypePriority());
    assertEquals(0.0d, actualCapResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCapResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCapResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCapResult.getVariance(), 0.0);
    assertEquals(1, actualCapResult.size());
    assertEquals(10.0d, actualCapResult.getAverage(), 0.0);
    assertEquals(10.0d, actualCapResult.getMax(), 0.0);
    assertEquals(10.0d, actualCapResult.getMin(), 0.0);
    assertTrue(actualCapResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualCapResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualCapResult.expectation();
    assertSame(actualCapResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#floor(double)} with {@code double}.
   *
   * <p>Method under test: {@link Scalar#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.floor(double)"})
  public void testFloorWithDouble() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(10.0d);

    // Assert
    assertTrue(actualFloorResult.abs() instanceof Scalar);
    assertTrue(actualFloorResult.cos() instanceof Scalar);
    assertTrue(actualFloorResult.exp() instanceof Scalar);
    assertTrue(actualFloorResult.expm1() instanceof Scalar);
    assertTrue(actualFloorResult.invert() instanceof Scalar);
    assertTrue(actualFloorResult.isNaN() instanceof Scalar);
    assertTrue(actualFloorResult.sin() instanceof Scalar);
    assertTrue(actualFloorResult.sqrt() instanceof Scalar);
    assertTrue(actualFloorResult.squared() instanceof Scalar);
    assertTrue(actualFloorResult.variance() instanceof Scalar);
    assertTrue(actualFloorResult instanceof Scalar);
    assertNull(actualFloorResult.getRealizations());
    assertNull(actualFloorResult.getOperator());
    assertNull(actualFloorResult.getRealizationsStream());
    assertEquals(0, actualFloorResult.getTypePriority());
    assertEquals(0.0d, actualFloorResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualFloorResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualFloorResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualFloorResult.getVariance(), 0.0);
    assertEquals(1, actualFloorResult.size());
    assertEquals(10.0d, actualFloorResult.getAverage(), 0.0);
    assertEquals(10.0d, actualFloorResult.getMax(), 0.0);
    assertEquals(10.0d, actualFloorResult.getMin(), 0.0);
    assertTrue(actualFloorResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualFloorResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualFloorResult.expectation();
    assertSame(actualFloorResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualFloorResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualFloorResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualFloorResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualFloorResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualFloorResult.getVariance(), 0.0);
    assertEquals(1, actualFloorResult.getTypePriority());
    assertEquals(1, actualFloorResult.size());
    assertEquals(10.0d, actualFloorResult.getAverage(), 0.0);
    assertEquals(10.0d, actualFloorResult.getMax(), 0.0);
    assertEquals(10.0d, actualFloorResult.getMin(), 0.0);
    assertTrue(actualFloorResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualFloorResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD floor = new RandomVariableDifferentiableAAD(values, factory);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(floor);

    // Assert
    assertTrue(actualFloorResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertSame(factory, ((RandomVariableDifferentiableAAD) actualFloorResult).getFactory());
    assertArrayEquals(new double[] {10.0d}, actualFloorResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_thenValuesReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualFloorResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFloorResult.getValues() instanceof Scalar);
    assertTrue(actualFloorResult.isNaN() instanceof Scalar);
    assertNull(actualFloorResult.getRealizations());
    assertNull(actualFloorResult.getOperator());
    assertNull(actualFloorResult.getRealizationsStream());
  }

  /**
   * Test {@link Scalar#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.floor(RandomVariable)"})
  public void testFloorWithRandomVariable_whenScalarWithValueIsTen_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualFloorResult = ofResult.floor(Scalar.of(10.0d));

    // Assert
    assertTrue(actualFloorResult.abs() instanceof Scalar);
    assertTrue(actualFloorResult.cos() instanceof Scalar);
    assertTrue(actualFloorResult.exp() instanceof Scalar);
    assertTrue(actualFloorResult.expm1() instanceof Scalar);
    assertTrue(actualFloorResult.invert() instanceof Scalar);
    assertTrue(actualFloorResult.isNaN() instanceof Scalar);
    assertTrue(actualFloorResult.sin() instanceof Scalar);
    assertTrue(actualFloorResult.sqrt() instanceof Scalar);
    assertTrue(actualFloorResult.squared() instanceof Scalar);
    assertTrue(actualFloorResult.variance() instanceof Scalar);
    assertTrue(actualFloorResult instanceof Scalar);
    assertNull(actualFloorResult.getRealizations());
    assertNull(actualFloorResult.getOperator());
    assertNull(actualFloorResult.getRealizationsStream());
    assertEquals(0, actualFloorResult.getTypePriority());
    assertEquals(0.0d, actualFloorResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualFloorResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualFloorResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualFloorResult.getVariance(), 0.0);
    assertEquals(1, actualFloorResult.size());
    assertEquals(10.0d, actualFloorResult.getAverage(), 0.0);
    assertEquals(10.0d, actualFloorResult.getMax(), 0.0);
    assertEquals(10.0d, actualFloorResult.getMin(), 0.0);
    assertTrue(actualFloorResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualFloorResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualFloorResult.expectation();
    assertSame(actualFloorResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualAddResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAddResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAddResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAddResult.getVariance(), 0.0);
    assertEquals(1, actualAddResult.getTypePriority());
    assertEquals(1, actualAddResult.size());
    assertEquals(20.0d, actualAddResult.getAverage(), 0.0);
    assertEquals(20.0d, actualAddResult.getMax(), 0.0);
    assertEquals(20.0d, actualAddResult.getMin(), 0.0);
    assertTrue(actualAddResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAddResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenScalarWithValueIsTen_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(Scalar.of(10.0d));

    // Assert
    assertTrue(actualAddResult.abs() instanceof Scalar);
    assertTrue(actualAddResult.cos() instanceof Scalar);
    assertTrue(actualAddResult.exp() instanceof Scalar);
    assertTrue(actualAddResult.expm1() instanceof Scalar);
    assertTrue(actualAddResult.invert() instanceof Scalar);
    assertTrue(actualAddResult.isNaN() instanceof Scalar);
    assertTrue(actualAddResult.sin() instanceof Scalar);
    assertTrue(actualAddResult.sqrt() instanceof Scalar);
    assertTrue(actualAddResult.squared() instanceof Scalar);
    assertTrue(actualAddResult.variance() instanceof Scalar);
    assertTrue(actualAddResult instanceof Scalar);
    assertNull(actualAddResult.getRealizations());
    assertNull(actualAddResult.getOperator());
    assertNull(actualAddResult.getRealizationsStream());
    assertEquals(0, actualAddResult.getTypePriority());
    assertEquals(0.0d, actualAddResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAddResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAddResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAddResult.getVariance(), 0.0);
    assertEquals(1, actualAddResult.size());
    assertEquals(20.0d, actualAddResult.getAverage(), 0.0);
    assertEquals(20.0d, actualAddResult.getMax(), 0.0);
    assertEquals(20.0d, actualAddResult.getMin(), 0.0);
    assertTrue(actualAddResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAddResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualAddResult.expectation();
    assertSame(actualAddResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#add(double)} with {@code value}.
   *
   * <p>Method under test: {@link Scalar#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.add(double)"})
  public void testAddWithValue() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddResult = ofResult.add(10.0d);

    // Assert
    assertTrue(actualAddResult.abs() instanceof Scalar);
    assertTrue(actualAddResult.cos() instanceof Scalar);
    assertTrue(actualAddResult.exp() instanceof Scalar);
    assertTrue(actualAddResult.expm1() instanceof Scalar);
    assertTrue(actualAddResult.invert() instanceof Scalar);
    assertTrue(actualAddResult.isNaN() instanceof Scalar);
    assertTrue(actualAddResult.sin() instanceof Scalar);
    assertTrue(actualAddResult.sqrt() instanceof Scalar);
    assertTrue(actualAddResult.squared() instanceof Scalar);
    assertTrue(actualAddResult.variance() instanceof Scalar);
    assertTrue(actualAddResult instanceof Scalar);
    assertNull(actualAddResult.getRealizations());
    assertNull(actualAddResult.getOperator());
    assertNull(actualAddResult.getRealizationsStream());
    assertEquals(0, actualAddResult.getTypePriority());
    assertEquals(0.0d, actualAddResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAddResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAddResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAddResult.getVariance(), 0.0);
    assertEquals(1, actualAddResult.size());
    assertEquals(20.0d, actualAddResult.getAverage(), 0.0);
    assertEquals(20.0d, actualAddResult.getMax(), 0.0);
    assertEquals(20.0d, actualAddResult.getMin(), 0.0);
    assertTrue(actualAddResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAddResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualAddResult.expectation();
    assertSame(actualAddResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableFromDoubleArray);
    assertEquals(-0.0d, actualSubResult.getAverage(), 0.0);
    assertEquals(-0.0d, actualSubResult.getMax(), 0.0);
    assertEquals(-0.0d, actualSubResult.getMin(), 0.0);
    assertEquals(0.0d, actualSubResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSubResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSubResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSubResult.getVariance(), 0.0);
    assertEquals(1, actualSubResult.getTypePriority());
    assertEquals(1, actualSubResult.size());
    assertTrue(actualSubResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSubResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {-0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(-1.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);
    when(randomVariable.sub(anyDouble())).thenReturn(randomVariableDifferentiableAAD);

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    verify(randomVariable).sub(10.0d);
    assertTrue(actualSubResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1.0d, actualSubResult.getAverage(), 0.0);
    assertEquals(1.0d, actualSubResult.getMax(), 0.0);
    assertEquals(1.0d, actualSubResult.getMin(), 0.0);
    assertSame(factory, ((RandomVariableDifferentiableAAD) actualSubResult).getFactory());
    assertArrayEquals(new double[] {1.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenValuesReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.sub(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualSubResult = ofResult.sub(randomVariable);

    // Assert
    verify(randomVariable).sub(10.0d);
    assertTrue(actualSubResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubResult.getValues() instanceof Scalar);
    assertTrue(actualSubResult.isNaN() instanceof Scalar);
    assertNull(actualSubResult.getRealizations());
    assertNull(actualSubResult.getOperator());
    assertNull(actualSubResult.getRealizationsStream());
    assertEquals(-10.0d, actualSubResult.getAverage(), 0.0);
    assertEquals(-10.0d, actualSubResult.getMax(), 0.0);
    assertEquals(-10.0d, actualSubResult.getMin(), 0.0);
  }

  /**
   * Test {@link Scalar#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is minus one.
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenScalarWithValueIsMinusOne_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(Scalar.of(-1.0d));

    // Assert
    assertTrue(actualSubResult.abs() instanceof Scalar);
    assertTrue(actualSubResult.cos() instanceof Scalar);
    assertTrue(actualSubResult.exp() instanceof Scalar);
    assertTrue(actualSubResult.expm1() instanceof Scalar);
    assertTrue(actualSubResult.invert() instanceof Scalar);
    assertTrue(actualSubResult.isNaN() instanceof Scalar);
    assertTrue(actualSubResult.sin() instanceof Scalar);
    assertTrue(actualSubResult.sqrt() instanceof Scalar);
    assertTrue(actualSubResult.squared() instanceof Scalar);
    assertTrue(actualSubResult.variance() instanceof Scalar);
    assertTrue(actualSubResult instanceof Scalar);
    assertNull(actualSubResult.getRealizations());
    assertNull(actualSubResult.getOperator());
    assertNull(actualSubResult.getRealizationsStream());
    assertEquals(0, actualSubResult.getTypePriority());
    assertEquals(0.0d, actualSubResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSubResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSubResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSubResult.getVariance(), 0.0);
    assertEquals(1, actualSubResult.size());
    assertEquals(11.0d, actualSubResult.getAverage(), 0.0);
    assertEquals(11.0d, actualSubResult.getMax(), 0.0);
    assertEquals(11.0d, actualSubResult.getMin(), 0.0);
    assertTrue(actualSubResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSubResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualSubResult.expectation();
    assertSame(actualSubResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#sub(double)} with {@code value}.
   *
   * <p>Method under test: {@link Scalar#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.sub(double)"})
  public void testSubWithValue() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualSubResult = ofResult.sub(10.0d);

    // Assert
    assertTrue(actualSubResult.abs() instanceof Scalar);
    assertTrue(actualSubResult.cos() instanceof Scalar);
    assertTrue(actualSubResult.exp() instanceof Scalar);
    assertTrue(actualSubResult.expm1() instanceof Scalar);
    assertTrue(actualSubResult.invert() instanceof Scalar);
    assertTrue(actualSubResult.isNaN() instanceof Scalar);
    assertTrue(actualSubResult.sin() instanceof Scalar);
    assertTrue(actualSubResult.sqrt() instanceof Scalar);
    assertTrue(actualSubResult.squared() instanceof Scalar);
    assertTrue(actualSubResult.variance() instanceof Scalar);
    assertTrue(actualSubResult instanceof Scalar);
    assertNull(actualSubResult.getRealizations());
    assertNull(actualSubResult.getOperator());
    assertNull(actualSubResult.getRealizationsStream());
    assertEquals(0, actualSubResult.getTypePriority());
    assertEquals(0.0d, actualSubResult.getAverage(), 0.0);
    assertEquals(0.0d, actualSubResult.getMax(), 0.0);
    assertEquals(0.0d, actualSubResult.getMin(), 0.0);
    assertEquals(0.0d, actualSubResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSubResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSubResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSubResult.getVariance(), 0.0);
    assertEquals(1, actualSubResult.size());
    assertTrue(actualSubResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSubResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualSubResult.expectation();
    assertSame(actualSubResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualMultResult = ofResult.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualMultResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualMultResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualMultResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualMultResult.getVariance(), 0.0);
    assertEquals(1, actualMultResult.getTypePriority());
    assertEquals(1, actualMultResult.size());
    assertEquals(100.0d, actualMultResult.getAverage(), 0.0);
    assertEquals(100.0d, actualMultResult.getMax(), 0.0);
    assertEquals(100.0d, actualMultResult.getMin(), 0.0);
    assertTrue(actualMultResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualMultResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariable =
        new RandomVariableDifferentiableAAD(values, factory);

    // Act
    RandomVariable actualMultResult = ofResult.mult(randomVariable);

    // Assert
    assertTrue(actualMultResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertSame(factory, ((RandomVariableDifferentiableAAD) actualMultResult).getFactory());
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenValuesReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualMultResult = ofResult.mult(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualMultResult.getValues() instanceof Scalar);
    assertTrue(actualMultResult.isNaN() instanceof Scalar);
    assertNull(actualMultResult.getRealizations());
    assertNull(actualMultResult.getOperator());
    assertNull(actualMultResult.getRealizationsStream());
  }

  /**
   * Test {@link Scalar#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenScalarWithValueIsTen_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualMultResult = ofResult.mult(Scalar.of(10.0d));

    // Assert
    assertTrue(actualMultResult.abs() instanceof Scalar);
    assertTrue(actualMultResult.cos() instanceof Scalar);
    assertTrue(actualMultResult.exp() instanceof Scalar);
    assertTrue(actualMultResult.expm1() instanceof Scalar);
    assertTrue(actualMultResult.invert() instanceof Scalar);
    assertTrue(actualMultResult.isNaN() instanceof Scalar);
    assertTrue(actualMultResult.sin() instanceof Scalar);
    assertTrue(actualMultResult.sqrt() instanceof Scalar);
    assertTrue(actualMultResult.squared() instanceof Scalar);
    assertTrue(actualMultResult.variance() instanceof Scalar);
    assertTrue(actualMultResult instanceof Scalar);
    assertNull(actualMultResult.getRealizations());
    assertNull(actualMultResult.getOperator());
    assertNull(actualMultResult.getRealizationsStream());
    assertEquals(0, actualMultResult.getTypePriority());
    assertEquals(0.0d, actualMultResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualMultResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualMultResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualMultResult.getVariance(), 0.0);
    assertEquals(1, actualMultResult.size());
    assertEquals(100.0d, actualMultResult.getAverage(), 0.0);
    assertEquals(100.0d, actualMultResult.getMax(), 0.0);
    assertEquals(100.0d, actualMultResult.getMin(), 0.0);
    assertTrue(actualMultResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualMultResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualMultResult.expectation();
    assertSame(actualMultResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#mult(double)} with {@code value}.
   *
   * <p>Method under test: {@link Scalar#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.mult(double)"})
  public void testMultWithValue() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualMultResult = ofResult.mult(10.0d);

    // Assert
    assertTrue(actualMultResult.abs() instanceof Scalar);
    assertTrue(actualMultResult.cos() instanceof Scalar);
    assertTrue(actualMultResult.exp() instanceof Scalar);
    assertTrue(actualMultResult.expm1() instanceof Scalar);
    assertTrue(actualMultResult.invert() instanceof Scalar);
    assertTrue(actualMultResult.isNaN() instanceof Scalar);
    assertTrue(actualMultResult.sin() instanceof Scalar);
    assertTrue(actualMultResult.sqrt() instanceof Scalar);
    assertTrue(actualMultResult.squared() instanceof Scalar);
    assertTrue(actualMultResult.variance() instanceof Scalar);
    assertTrue(actualMultResult instanceof Scalar);
    assertNull(actualMultResult.getRealizations());
    assertNull(actualMultResult.getOperator());
    assertNull(actualMultResult.getRealizationsStream());
    assertEquals(0, actualMultResult.getTypePriority());
    assertEquals(0.0d, actualMultResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualMultResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualMultResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualMultResult.getVariance(), 0.0);
    assertEquals(1, actualMultResult.size());
    assertEquals(100.0d, actualMultResult.getAverage(), 0.0);
    assertEquals(100.0d, actualMultResult.getMax(), 0.0);
    assertEquals(100.0d, actualMultResult.getMin(), 0.0);
    assertTrue(actualMultResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualMultResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualMultResult.expectation();
    assertSame(actualMultResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualDivResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDivResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDivResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualDivResult.getVariance(), 0.0);
    assertEquals(1, actualDivResult.getTypePriority());
    assertEquals(1, actualDivResult.size());
    assertEquals(1.0d, actualDivResult.getAverage(), 0.0);
    assertEquals(1.0d, actualDivResult.getMax(), 0.0);
    assertEquals(1.0d, actualDivResult.getMin(), 0.0);
    assertTrue(actualDivResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDivResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(1.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);
    when(randomVariable.invert()).thenReturn(randomVariableDifferentiableAAD);

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    verify(randomVariable).invert();
    assertTrue(actualDivResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(10.0d, actualDivResult.getAverage(), 0.0);
    assertEquals(10.0d, actualDivResult.getMax(), 0.0);
    assertEquals(10.0d, actualDivResult.getMin(), 0.0);
    assertSame(factory, ((RandomVariableDifferentiableAAD) actualDivResult).getFactory());
    assertArrayEquals(new double[] {10.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenValuesReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.invert()).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    RandomVariable actualDivResult = ofResult.div(randomVariable);

    // Assert
    verify(randomVariable).invert();
    assertTrue(actualDivResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualDivResult.getValues() instanceof Scalar);
    assertTrue(actualDivResult.isNaN() instanceof Scalar);
    assertNull(actualDivResult.getRealizations());
    assertNull(actualDivResult.getOperator());
    assertNull(actualDivResult.getRealizationsStream());
    assertEquals(100.0d, actualDivResult.getAverage(), 0.0);
    assertEquals(100.0d, actualDivResult.getMax(), 0.0);
    assertEquals(100.0d, actualDivResult.getMin(), 0.0);
  }

  /**
   * Test {@link Scalar#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is one.
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenScalarWithValueIsOne_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(Scalar.of(1.0d));

    // Assert
    assertTrue(actualDivResult.abs() instanceof Scalar);
    assertTrue(actualDivResult.cos() instanceof Scalar);
    assertTrue(actualDivResult.exp() instanceof Scalar);
    assertTrue(actualDivResult.expm1() instanceof Scalar);
    assertTrue(actualDivResult.invert() instanceof Scalar);
    assertTrue(actualDivResult.isNaN() instanceof Scalar);
    assertTrue(actualDivResult.sin() instanceof Scalar);
    assertTrue(actualDivResult.sqrt() instanceof Scalar);
    assertTrue(actualDivResult.squared() instanceof Scalar);
    assertTrue(actualDivResult.variance() instanceof Scalar);
    assertTrue(actualDivResult instanceof Scalar);
    assertNull(actualDivResult.getRealizations());
    assertNull(actualDivResult.getOperator());
    assertNull(actualDivResult.getRealizationsStream());
    assertEquals(0, actualDivResult.getTypePriority());
    assertEquals(0.0d, actualDivResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDivResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDivResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualDivResult.getVariance(), 0.0);
    assertEquals(1, actualDivResult.size());
    assertEquals(10.0d, actualDivResult.getAverage(), 0.0);
    assertEquals(10.0d, actualDivResult.getMax(), 0.0);
    assertEquals(10.0d, actualDivResult.getMin(), 0.0);
    assertTrue(actualDivResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDivResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualDivResult.expectation();
    assertSame(actualDivResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#div(double)} with {@code value}.
   *
   * <p>Method under test: {@link Scalar#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.div(double)"})
  public void testDivWithValue() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualDivResult = ofResult.div(10.0d);

    // Assert
    assertTrue(actualDivResult.abs() instanceof Scalar);
    assertTrue(actualDivResult.cos() instanceof Scalar);
    assertTrue(actualDivResult.exp() instanceof Scalar);
    assertTrue(actualDivResult.expm1() instanceof Scalar);
    assertTrue(actualDivResult.invert() instanceof Scalar);
    assertTrue(actualDivResult.isNaN() instanceof Scalar);
    assertTrue(actualDivResult.sin() instanceof Scalar);
    assertTrue(actualDivResult.sqrt() instanceof Scalar);
    assertTrue(actualDivResult.squared() instanceof Scalar);
    assertTrue(actualDivResult.variance() instanceof Scalar);
    assertTrue(actualDivResult instanceof Scalar);
    assertNull(actualDivResult.getRealizations());
    assertNull(actualDivResult.getOperator());
    assertNull(actualDivResult.getRealizationsStream());
    assertEquals(0, actualDivResult.getTypePriority());
    assertEquals(0.0d, actualDivResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDivResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDivResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualDivResult.getVariance(), 0.0);
    assertEquals(1, actualDivResult.size());
    assertEquals(1.0d, actualDivResult.getAverage(), 0.0);
    assertEquals(1.0d, actualDivResult.getMax(), 0.0);
    assertEquals(1.0d, actualDivResult.getMin(), 0.0);
    assertTrue(actualDivResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDivResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualDivResult.expectation();
    assertSame(actualDivResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualVidResult = ofResult.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualVidResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualVidResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualVidResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualVidResult.getVariance(), 0.0);
    assertEquals(1, actualVidResult.getTypePriority());
    assertEquals(1, actualVidResult.size());
    assertEquals(1.0d, actualVidResult.getAverage(), 0.0);
    assertEquals(1.0d, actualVidResult.getMax(), 0.0);
    assertEquals(1.0d, actualVidResult.getMin(), 0.0);
    assertTrue(actualVidResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualVidResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariable =
        new RandomVariableDifferentiableAAD(values, factory);

    // Act
    RandomVariable actualVidResult = ofResult.vid(randomVariable);

    // Assert
    assertTrue(actualVidResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertSame(factory, ((RandomVariableDifferentiableAAD) actualVidResult).getFactory());
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenValuesReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualVidResult = ofResult.vid(RandomVariableDifferentiableAAD.of(10.0d));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualVidResult.getValues() instanceof Scalar);
    assertTrue(actualVidResult.isNaN() instanceof Scalar);
    assertNull(actualVidResult.getRealizations());
    assertNull(actualVidResult.getOperator());
    assertNull(actualVidResult.getRealizationsStream());
  }

  /**
   * Test {@link Scalar#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenScalarWithValueIsTen_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualVidResult = ofResult.vid(Scalar.of(10.0d));

    // Assert
    assertTrue(actualVidResult.abs() instanceof Scalar);
    assertTrue(actualVidResult.cos() instanceof Scalar);
    assertTrue(actualVidResult.exp() instanceof Scalar);
    assertTrue(actualVidResult.expm1() instanceof Scalar);
    assertTrue(actualVidResult.invert() instanceof Scalar);
    assertTrue(actualVidResult.isNaN() instanceof Scalar);
    assertTrue(actualVidResult.sin() instanceof Scalar);
    assertTrue(actualVidResult.sqrt() instanceof Scalar);
    assertTrue(actualVidResult.squared() instanceof Scalar);
    assertTrue(actualVidResult.variance() instanceof Scalar);
    assertTrue(actualVidResult instanceof Scalar);
    assertNull(actualVidResult.getRealizations());
    assertNull(actualVidResult.getOperator());
    assertNull(actualVidResult.getRealizationsStream());
    assertEquals(0, actualVidResult.getTypePriority());
    assertEquals(0.0d, actualVidResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualVidResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualVidResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualVidResult.getVariance(), 0.0);
    assertEquals(1, actualVidResult.size());
    assertEquals(1.0d, actualVidResult.getAverage(), 0.0);
    assertEquals(1.0d, actualVidResult.getMax(), 0.0);
    assertEquals(1.0d, actualVidResult.getMin(), 0.0);
    assertTrue(actualVidResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualVidResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualVidResult.expectation();
    assertSame(actualVidResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#pow(double)}.
   *
   * <p>Method under test: {@link Scalar#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.pow(double)"})
  public void testPow() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualPowResult = ofResult.pow(10.0d);

    // Assert
    assertTrue(actualPowResult.abs() instanceof Scalar);
    assertTrue(actualPowResult.cos() instanceof Scalar);
    assertTrue(actualPowResult.exp() instanceof Scalar);
    assertTrue(actualPowResult.expm1() instanceof Scalar);
    assertTrue(actualPowResult.invert() instanceof Scalar);
    assertTrue(actualPowResult.isNaN() instanceof Scalar);
    assertTrue(actualPowResult.sin() instanceof Scalar);
    assertTrue(actualPowResult.sqrt() instanceof Scalar);
    assertTrue(actualPowResult.squared() instanceof Scalar);
    assertTrue(actualPowResult.variance() instanceof Scalar);
    assertTrue(actualPowResult instanceof Scalar);
    assertNull(actualPowResult.getRealizations());
    assertNull(actualPowResult.getOperator());
    assertNull(actualPowResult.getRealizationsStream());
    assertEquals(0, actualPowResult.getTypePriority());
    assertEquals(0.0d, actualPowResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualPowResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualPowResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualPowResult.getVariance(), 0.0);
    assertEquals(1, actualPowResult.size());
    assertEquals(1.0E10d, actualPowResult.getAverage(), 0.0);
    assertEquals(1.0E10d, actualPowResult.getMax(), 0.0);
    assertEquals(1.0E10d, actualPowResult.getMin(), 0.0);
    assertTrue(actualPowResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualPowResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualPowResult.expectation();
    assertSame(actualPowResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualBusResult = ofResult.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualBusResult.getAverage(), 0.0);
    assertEquals(0.0d, actualBusResult.getMax(), 0.0);
    assertEquals(0.0d, actualBusResult.getMin(), 0.0);
    assertEquals(0.0d, actualBusResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBusResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBusResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualBusResult.getVariance(), 0.0);
    assertEquals(1, actualBusResult.getTypePriority());
    assertEquals(1, actualBusResult.size());
    assertTrue(actualBusResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualBusResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenScalarWithValueIsTen_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualBusResult = ofResult.bus(Scalar.of(10.0d));

    // Assert
    assertTrue(actualBusResult.abs() instanceof Scalar);
    assertTrue(actualBusResult.cos() instanceof Scalar);
    assertTrue(actualBusResult.exp() instanceof Scalar);
    assertTrue(actualBusResult.expm1() instanceof Scalar);
    assertTrue(actualBusResult.invert() instanceof Scalar);
    assertTrue(actualBusResult.isNaN() instanceof Scalar);
    assertTrue(actualBusResult.sin() instanceof Scalar);
    assertTrue(actualBusResult.sqrt() instanceof Scalar);
    assertTrue(actualBusResult.squared() instanceof Scalar);
    assertTrue(actualBusResult.variance() instanceof Scalar);
    assertTrue(actualBusResult instanceof Scalar);
    assertNull(actualBusResult.getRealizations());
    assertNull(actualBusResult.getOperator());
    assertNull(actualBusResult.getRealizationsStream());
    assertEquals(0, actualBusResult.getTypePriority());
    assertEquals(0.0d, actualBusResult.getAverage(), 0.0);
    assertEquals(0.0d, actualBusResult.getMax(), 0.0);
    assertEquals(0.0d, actualBusResult.getMin(), 0.0);
    assertEquals(0.0d, actualBusResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualBusResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualBusResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualBusResult.getVariance(), 0.0);
    assertEquals(1, actualBusResult.size());
    assertTrue(actualBusResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualBusResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualBusResult.expectation();
    assertSame(actualBusResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#average()}.
   *
   * <p>Method under test: {@link Scalar#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.average()"})
  public void testAverage() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualAverageResult = ofResult.average();

    // Assert
    assertSame(ofResult, actualAverageResult);
  }

  /**
   * Test {@link Scalar#squared()}.
   *
   * <p>Method under test: {@link Scalar#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.squared()"})
  public void testSquared() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualSquaredResult = ofResult.squared();

    // Assert
    assertTrue(actualSquaredResult.abs() instanceof Scalar);
    assertTrue(actualSquaredResult.cos() instanceof Scalar);
    assertTrue(actualSquaredResult.exp() instanceof Scalar);
    assertTrue(actualSquaredResult.expm1() instanceof Scalar);
    assertTrue(actualSquaredResult.invert() instanceof Scalar);
    assertTrue(actualSquaredResult.isNaN() instanceof Scalar);
    assertTrue(actualSquaredResult.sin() instanceof Scalar);
    assertTrue(actualSquaredResult.sqrt() instanceof Scalar);
    assertTrue(actualSquaredResult.variance() instanceof Scalar);
    assertTrue(actualSquaredResult instanceof Scalar);
    assertNull(actualSquaredResult.getRealizations());
    assertNull(actualSquaredResult.getOperator());
    assertNull(actualSquaredResult.getRealizationsStream());
    assertEquals(0, actualSquaredResult.getTypePriority());
    assertEquals(0.0d, actualSquaredResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSquaredResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSquaredResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSquaredResult.getVariance(), 0.0);
    assertEquals(1, actualSquaredResult.size());
    assertEquals(100.0d, actualSquaredResult.getAverage(), 0.0);
    assertEquals(100.0d, actualSquaredResult.getMax(), 0.0);
    assertEquals(100.0d, actualSquaredResult.getMin(), 0.0);
    assertTrue(actualSquaredResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSquaredResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualSquaredResult.expectation();
    assertSame(actualSquaredResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#sqrt()}.
   *
   * <p>Method under test: {@link Scalar#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.sqrt()"})
  public void testSqrt() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualSqrtResult = ofResult.sqrt();

    // Assert
    assertTrue(actualSqrtResult.abs() instanceof Scalar);
    assertTrue(actualSqrtResult.cos() instanceof Scalar);
    assertTrue(actualSqrtResult.exp() instanceof Scalar);
    assertTrue(actualSqrtResult.expm1() instanceof Scalar);
    assertTrue(actualSqrtResult.invert() instanceof Scalar);
    assertTrue(actualSqrtResult.isNaN() instanceof Scalar);
    assertTrue(actualSqrtResult.sin() instanceof Scalar);
    assertTrue(actualSqrtResult.squared() instanceof Scalar);
    assertTrue(actualSqrtResult.variance() instanceof Scalar);
    assertTrue(actualSqrtResult instanceof Scalar);
    assertNull(actualSqrtResult.getRealizations());
    assertNull(actualSqrtResult.getOperator());
    assertNull(actualSqrtResult.getRealizationsStream());
    assertEquals(0, actualSqrtResult.getTypePriority());
    assertEquals(0.0d, actualSqrtResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSqrtResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSqrtResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSqrtResult.getVariance(), 0.0);
    assertEquals(1, actualSqrtResult.size());
    assertEquals(3.1622776601683795d, actualSqrtResult.getAverage(), 0.0);
    assertEquals(3.1622776601683795d, actualSqrtResult.getMax(), 0.0);
    assertEquals(3.1622776601683795d, actualSqrtResult.getMin(), 0.0);
    assertTrue(actualSqrtResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSqrtResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualSqrtResult.expectation();
    assertSame(actualSqrtResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#exp()}.
   *
   * <p>Method under test: {@link Scalar#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.exp()"})
  public void testExp() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualExpResult = ofResult.exp();

    // Assert
    assertTrue(actualExpResult.abs() instanceof Scalar);
    assertTrue(actualExpResult.cos() instanceof Scalar);
    assertTrue(actualExpResult.expm1() instanceof Scalar);
    assertTrue(actualExpResult.invert() instanceof Scalar);
    assertTrue(actualExpResult.isNaN() instanceof Scalar);
    assertTrue(actualExpResult.sin() instanceof Scalar);
    assertTrue(actualExpResult.sqrt() instanceof Scalar);
    assertTrue(actualExpResult.squared() instanceof Scalar);
    assertTrue(actualExpResult.variance() instanceof Scalar);
    assertTrue(actualExpResult instanceof Scalar);
    assertNull(actualExpResult.getRealizations());
    assertNull(actualExpResult.getOperator());
    assertNull(actualExpResult.getRealizationsStream());
    assertEquals(0, actualExpResult.getTypePriority());
    assertEquals(0.0d, actualExpResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualExpResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualExpResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualExpResult.getVariance(), 0.0);
    assertEquals(1, actualExpResult.size());
    assertEquals(22026.465794806718d, actualExpResult.getAverage(), 0.0);
    assertEquals(22026.465794806718d, actualExpResult.getMax(), 0.0);
    assertEquals(22026.465794806718d, actualExpResult.getMin(), 0.0);
    assertTrue(actualExpResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualExpResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualExpResult.expectation();
    assertSame(actualExpResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#expm1()}.
   *
   * <p>Method under test: {@link Scalar#expm1()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.expm1()"})
  public void testExpm1() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualExpm1Result = ofResult.expm1();

    // Assert
    assertTrue(actualExpm1Result.abs() instanceof Scalar);
    assertTrue(actualExpm1Result.cos() instanceof Scalar);
    assertTrue(actualExpm1Result.exp() instanceof Scalar);
    assertTrue(actualExpm1Result.invert() instanceof Scalar);
    assertTrue(actualExpm1Result.isNaN() instanceof Scalar);
    assertTrue(actualExpm1Result.sin() instanceof Scalar);
    assertTrue(actualExpm1Result.sqrt() instanceof Scalar);
    assertTrue(actualExpm1Result.squared() instanceof Scalar);
    assertTrue(actualExpm1Result.variance() instanceof Scalar);
    assertTrue(actualExpm1Result instanceof Scalar);
    assertNull(actualExpm1Result.getRealizations());
    assertNull(actualExpm1Result.getOperator());
    assertNull(actualExpm1Result.getRealizationsStream());
    assertEquals(0, actualExpm1Result.getTypePriority());
    assertEquals(0.0d, actualExpm1Result.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualExpm1Result.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualExpm1Result.getStandardError(), 0.0);
    assertEquals(0.0d, actualExpm1Result.getVariance(), 0.0);
    assertEquals(1, actualExpm1Result.size());
    assertEquals(22025.465794806718d, actualExpm1Result.getAverage(), 0.0);
    assertEquals(22025.465794806718d, actualExpm1Result.getMax(), 0.0);
    assertEquals(22025.465794806718d, actualExpm1Result.getMin(), 0.0);
    assertTrue(actualExpm1Result.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualExpm1Result.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualExpm1Result.expectation();
    assertSame(actualExpm1Result, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#log()}.
   *
   * <p>Method under test: {@link Scalar#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.log()"})
  public void testLog() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualLogResult = ofResult.log();

    // Assert
    assertTrue(actualLogResult.abs() instanceof Scalar);
    assertTrue(actualLogResult.cos() instanceof Scalar);
    assertTrue(actualLogResult.exp() instanceof Scalar);
    assertTrue(actualLogResult.expm1() instanceof Scalar);
    assertTrue(actualLogResult.invert() instanceof Scalar);
    assertTrue(actualLogResult.isNaN() instanceof Scalar);
    assertTrue(actualLogResult.sin() instanceof Scalar);
    assertTrue(actualLogResult.sqrt() instanceof Scalar);
    assertTrue(actualLogResult.squared() instanceof Scalar);
    assertTrue(actualLogResult.variance() instanceof Scalar);
    assertTrue(actualLogResult instanceof Scalar);
    assertNull(actualLogResult.getRealizations());
    assertNull(actualLogResult.getOperator());
    assertNull(actualLogResult.getRealizationsStream());
    assertEquals(0, actualLogResult.getTypePriority());
    assertEquals(0.0d, actualLogResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualLogResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualLogResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualLogResult.getVariance(), 0.0);
    assertEquals(1, actualLogResult.size());
    assertEquals(2.302585092994046d, actualLogResult.getAverage(), 0.0);
    assertEquals(2.302585092994046d, actualLogResult.getMax(), 0.0);
    assertEquals(2.302585092994046d, actualLogResult.getMin(), 0.0);
    assertTrue(actualLogResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualLogResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualLogResult.expectation();
    assertSame(actualLogResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#sin()}.
   *
   * <p>Method under test: {@link Scalar#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.sin()"})
  public void testSin() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualSinResult = ofResult.sin();

    // Assert
    assertTrue(actualSinResult.abs() instanceof Scalar);
    assertTrue(actualSinResult.cos() instanceof Scalar);
    assertTrue(actualSinResult.exp() instanceof Scalar);
    assertTrue(actualSinResult.expm1() instanceof Scalar);
    assertTrue(actualSinResult.invert() instanceof Scalar);
    assertTrue(actualSinResult.isNaN() instanceof Scalar);
    assertTrue(actualSinResult.sqrt() instanceof Scalar);
    assertTrue(actualSinResult.squared() instanceof Scalar);
    assertTrue(actualSinResult.variance() instanceof Scalar);
    assertTrue(actualSinResult instanceof Scalar);
    assertNull(actualSinResult.getRealizations());
    assertNull(actualSinResult.getOperator());
    assertNull(actualSinResult.getRealizationsStream());
    assertEquals(-0.5440211108893698d, actualSinResult.getAverage(), 0.0);
    assertEquals(-0.5440211108893698d, actualSinResult.getMax(), 0.0);
    assertEquals(-0.5440211108893698d, actualSinResult.getMin(), 0.0);
    assertEquals(0, actualSinResult.getTypePriority());
    assertEquals(0.0d, actualSinResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSinResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSinResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSinResult.getVariance(), 0.0);
    assertEquals(1, actualSinResult.size());
    assertTrue(actualSinResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSinResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualSinResult.expectation();
    assertSame(actualSinResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#cos()}.
   *
   * <p>Method under test: {@link Scalar#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.cos()"})
  public void testCos() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualCosResult = ofResult.cos();

    // Assert
    assertTrue(actualCosResult.abs() instanceof Scalar);
    assertTrue(actualCosResult.exp() instanceof Scalar);
    assertTrue(actualCosResult.expm1() instanceof Scalar);
    assertTrue(actualCosResult.invert() instanceof Scalar);
    assertTrue(actualCosResult.isNaN() instanceof Scalar);
    assertTrue(actualCosResult.sin() instanceof Scalar);
    assertTrue(actualCosResult.sqrt() instanceof Scalar);
    assertTrue(actualCosResult.squared() instanceof Scalar);
    assertTrue(actualCosResult.variance() instanceof Scalar);
    assertTrue(actualCosResult instanceof Scalar);
    assertNull(actualCosResult.getRealizations());
    assertNull(actualCosResult.getOperator());
    assertNull(actualCosResult.getRealizationsStream());
    assertEquals(-0.8390715290764524d, actualCosResult.getAverage(), 0.0);
    assertEquals(-0.8390715290764524d, actualCosResult.getMax(), 0.0);
    assertEquals(-0.8390715290764524d, actualCosResult.getMin(), 0.0);
    assertEquals(0, actualCosResult.getTypePriority());
    assertEquals(0.0d, actualCosResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCosResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCosResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualCosResult.getVariance(), 0.0);
    assertEquals(1, actualCosResult.size());
    assertTrue(actualCosResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualCosResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualCosResult.expectation();
    assertSame(actualCosResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.accrue(RandomVariable, double)"})
  public void testAccrue_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualAccrueResult =
        ofResult.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAccrueResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualAccrueResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getVariance(), 0.0);
    assertEquals(1, actualAccrueResult.getTypePriority());
    assertEquals(1, actualAccrueResult.size());
    assertEquals(1010.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(1010.0d, actualAccrueResult.getMax(), 0.0);
    assertEquals(1010.0d, actualAccrueResult.getMin(), 0.0);
    assertTrue(actualAccrueResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAccrueResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1010.0d}, actualAccrueResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#accrue(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.accrue(RandomVariable, double)"})
  public void testAccrue_whenScalarWithValueIsTen_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualAccrueResult = ofResult.accrue(Scalar.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualAccrueResult.abs() instanceof Scalar);
    assertTrue(actualAccrueResult.cos() instanceof Scalar);
    assertTrue(actualAccrueResult.exp() instanceof Scalar);
    assertTrue(actualAccrueResult.expm1() instanceof Scalar);
    assertTrue(actualAccrueResult.invert() instanceof Scalar);
    assertTrue(actualAccrueResult.isNaN() instanceof Scalar);
    assertTrue(actualAccrueResult.sin() instanceof Scalar);
    assertTrue(actualAccrueResult.sqrt() instanceof Scalar);
    assertTrue(actualAccrueResult.squared() instanceof Scalar);
    assertTrue(actualAccrueResult.variance() instanceof Scalar);
    assertTrue(actualAccrueResult instanceof Scalar);
    assertNull(actualAccrueResult.getRealizations());
    assertNull(actualAccrueResult.getOperator());
    assertNull(actualAccrueResult.getRealizationsStream());
    assertEquals(0, actualAccrueResult.getTypePriority());
    assertEquals(0.0d, actualAccrueResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAccrueResult.getVariance(), 0.0);
    assertEquals(1, actualAccrueResult.size());
    assertEquals(1010.0d, actualAccrueResult.getAverage(), 0.0);
    assertEquals(1010.0d, actualAccrueResult.getMax(), 0.0);
    assertEquals(1010.0d, actualAccrueResult.getMin(), 0.0);
    assertTrue(actualAccrueResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAccrueResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualAccrueResult.expectation();
    assertSame(actualAccrueResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Given {@link Scalar#Scalar(double)} with value is zero.
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.discount(RandomVariable, double)"})
  public void testDiscount_givenScalarWithValueIsZero_thenReturnAverageIsZero() {
    // Arrange
    Scalar scalar = new Scalar(0.0d);

    // Act
    RandomVariable actualDiscountResult =
        scalar.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getMin(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@code 0.09900990099009901}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.discount(RandomVariable, double)"})
  public void testDiscount_thenReturnRealizationsIsArrayOfDoubleWith009900990099009901() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualDiscountResult =
        ofResult.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.09900990099009901d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.09900990099009901d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.09900990099009901d, actualDiscountResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.09900990099009901d}, actualDiscountResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#discount(RandomVariable, double)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.discount(RandomVariable, double)"})
  public void testDiscount_whenScalarWithValueIsTen_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualDiscountResult = ofResult.discount(Scalar.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualDiscountResult.abs() instanceof Scalar);
    assertTrue(actualDiscountResult.cos() instanceof Scalar);
    assertTrue(actualDiscountResult.exp() instanceof Scalar);
    assertTrue(actualDiscountResult.expm1() instanceof Scalar);
    assertTrue(actualDiscountResult.invert() instanceof Scalar);
    assertTrue(actualDiscountResult.isNaN() instanceof Scalar);
    assertTrue(actualDiscountResult.sin() instanceof Scalar);
    assertTrue(actualDiscountResult.sqrt() instanceof Scalar);
    assertTrue(actualDiscountResult.squared() instanceof Scalar);
    assertTrue(actualDiscountResult.variance() instanceof Scalar);
    assertTrue(actualDiscountResult instanceof Scalar);
    assertNull(actualDiscountResult.getRealizations());
    assertNull(actualDiscountResult.getOperator());
    assertNull(actualDiscountResult.getRealizationsStream());
    assertEquals(0, actualDiscountResult.getTypePriority());
    assertEquals(0.09900990099009901d, actualDiscountResult.getAverage(), 0.0);
    assertEquals(0.09900990099009901d, actualDiscountResult.getMax(), 0.0);
    assertEquals(0.09900990099009901d, actualDiscountResult.getMin(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualDiscountResult.getVariance(), 0.0);
    assertEquals(1, actualDiscountResult.size());
    assertTrue(actualDiscountResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDiscountResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualDiscountResult.expectation();
    assertSame(actualDiscountResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link Scalar#Scalar(double)} with value is {@code -1.0E-10}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#choose(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.choose(RandomVariable, RandomVariable)"})
  public void testChoose_givenScalarWithValueIs10e10() {
    // Arrange
    Scalar scalar = new Scalar(-1.0E-10d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        scalar.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#choose(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#choose(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.choose(RandomVariable, RandomVariable)"})
  public void testChoose_givenScalarWithValueIsTen() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualChooseResult =
        ofResult.choose(valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualChooseResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualChooseResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualChooseResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#invert()}.
   *
   * <p>Method under test: {@link Scalar#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.invert()"})
  public void testInvert() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualInvertResult = ofResult.invert();

    // Assert
    assertTrue(actualInvertResult.abs() instanceof Scalar);
    assertTrue(actualInvertResult.cos() instanceof Scalar);
    assertTrue(actualInvertResult.exp() instanceof Scalar);
    assertTrue(actualInvertResult.expm1() instanceof Scalar);
    assertTrue(actualInvertResult.isNaN() instanceof Scalar);
    assertTrue(actualInvertResult.sin() instanceof Scalar);
    assertTrue(actualInvertResult.sqrt() instanceof Scalar);
    assertTrue(actualInvertResult.squared() instanceof Scalar);
    assertTrue(actualInvertResult.variance() instanceof Scalar);
    assertTrue(actualInvertResult instanceof Scalar);
    assertNull(actualInvertResult.getRealizations());
    assertNull(actualInvertResult.getOperator());
    assertNull(actualInvertResult.getRealizationsStream());
    assertEquals(0, actualInvertResult.getTypePriority());
    assertEquals(0.0d, actualInvertResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualInvertResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualInvertResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualInvertResult.getVariance(), 0.0);
    assertEquals(0.1d, actualInvertResult.getAverage(), 0.0);
    assertEquals(0.1d, actualInvertResult.getMax(), 0.0);
    assertEquals(0.1d, actualInvertResult.getMin(), 0.0);
    assertEquals(1, actualInvertResult.size());
    assertTrue(actualInvertResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualInvertResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualInvertResult.expectation();
    assertSame(actualInvertResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#abs()}.
   *
   * <p>Method under test: {@link Scalar#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.abs()"})
  public void testAbs() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualAbsResult = ofResult.abs();

    // Assert
    assertTrue(actualAbsResult.cos() instanceof Scalar);
    assertTrue(actualAbsResult.exp() instanceof Scalar);
    assertTrue(actualAbsResult.expm1() instanceof Scalar);
    assertTrue(actualAbsResult.invert() instanceof Scalar);
    assertTrue(actualAbsResult.isNaN() instanceof Scalar);
    assertTrue(actualAbsResult.sin() instanceof Scalar);
    assertTrue(actualAbsResult.sqrt() instanceof Scalar);
    assertTrue(actualAbsResult.squared() instanceof Scalar);
    assertTrue(actualAbsResult.variance() instanceof Scalar);
    assertTrue(actualAbsResult instanceof Scalar);
    assertNull(actualAbsResult.getRealizations());
    assertNull(actualAbsResult.getOperator());
    assertNull(actualAbsResult.getRealizationsStream());
    assertEquals(0, actualAbsResult.getTypePriority());
    assertEquals(0.0d, actualAbsResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAbsResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAbsResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAbsResult.getVariance(), 0.0);
    assertEquals(1, actualAbsResult.size());
    assertEquals(10.0d, actualAbsResult.getAverage(), 0.0);
    assertEquals(10.0d, actualAbsResult.getMax(), 0.0);
    assertEquals(10.0d, actualAbsResult.getMin(), 0.0);
    assertTrue(actualAbsResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAbsResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualAbsResult.expectation();
    assertSame(actualAbsResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#addProduct(RandomVariable, double)} with {@code RandomVariable}, {@code
   * double}.
   *
   * <p>Method under test: {@link Scalar#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.addProduct(RandomVariable, double)"})
  public void testAddProductWithRandomVariableDouble() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(new RandomVariableFromDoubleArray(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualAddProductResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getVariance(), 0.0);
    assertEquals(1, actualAddProductResult.getTypePriority());
    assertEquals(1, actualAddProductResult.size());
    assertEquals(110.0d, actualAddProductResult.getAverage(), 0.0);
    assertEquals(110.0d, actualAddProductResult.getMax(), 0.0);
    assertEquals(110.0d, actualAddProductResult.getMin(), 0.0);
    assertTrue(actualAddProductResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAddProductResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#addProduct(RandomVariable, double)} with {@code RandomVariable}, {@code
   * double}.
   *
   * <ul>
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.addProduct(RandomVariable, double)"})
  public void testAddProductWithRandomVariableDouble_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult = ofResult.addProduct(Scalar.of(10.0d), 10.0d);

    // Assert
    assertTrue(actualAddProductResult.abs() instanceof Scalar);
    assertTrue(actualAddProductResult.cos() instanceof Scalar);
    assertTrue(actualAddProductResult.exp() instanceof Scalar);
    assertTrue(actualAddProductResult.expm1() instanceof Scalar);
    assertTrue(actualAddProductResult.invert() instanceof Scalar);
    assertTrue(actualAddProductResult.isNaN() instanceof Scalar);
    assertTrue(actualAddProductResult.sin() instanceof Scalar);
    assertTrue(actualAddProductResult.sqrt() instanceof Scalar);
    assertTrue(actualAddProductResult.squared() instanceof Scalar);
    assertTrue(actualAddProductResult.variance() instanceof Scalar);
    assertTrue(actualAddProductResult instanceof Scalar);
    assertNull(actualAddProductResult.getRealizations());
    assertNull(actualAddProductResult.getOperator());
    assertNull(actualAddProductResult.getRealizationsStream());
    assertEquals(0, actualAddProductResult.getTypePriority());
    assertEquals(0.0d, actualAddProductResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getVariance(), 0.0);
    assertEquals(1, actualAddProductResult.size());
    assertEquals(110.0d, actualAddProductResult.getAverage(), 0.0);
    assertEquals(110.0d, actualAddProductResult.getMax(), 0.0);
    assertEquals(110.0d, actualAddProductResult.getMin(), 0.0);
    assertTrue(actualAddProductResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAddProductResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualAddProductResult.expectation();
    assertSame(actualAddProductResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#addProduct(RandomVariable, RandomVariable)} with {@code RandomVariable},
   * {@code RandomVariable}.
   *
   * <p>Method under test: {@link Scalar#addProduct(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.addProduct(RandomVariable, RandomVariable)"})
  public void testAddProductWithRandomVariableRandomVariable() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#addProduct(RandomVariable, RandomVariable)} with {@code RandomVariable},
   * {@code RandomVariable}.
   *
   * <ul>
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#addProduct(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.addProduct(RandomVariable, RandomVariable)"})
  public void testAddProductWithRandomVariableRandomVariable_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    RandomVariableAAD factor1 = mock(RandomVariableAAD.class);
    when(factor1.mult(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(factor1).mult(isA(RandomVariable.class));
    assertTrue(actualAddProductResult.abs() instanceof Scalar);
    assertTrue(actualAddProductResult.cos() instanceof Scalar);
    assertTrue(actualAddProductResult.exp() instanceof Scalar);
    assertTrue(actualAddProductResult.expm1() instanceof Scalar);
    assertTrue(actualAddProductResult.invert() instanceof Scalar);
    assertTrue(actualAddProductResult.isNaN() instanceof Scalar);
    assertTrue(actualAddProductResult.sin() instanceof Scalar);
    assertTrue(actualAddProductResult.sqrt() instanceof Scalar);
    assertTrue(actualAddProductResult.squared() instanceof Scalar);
    assertTrue(actualAddProductResult.variance() instanceof Scalar);
    assertTrue(actualAddProductResult instanceof Scalar);
    assertNull(actualAddProductResult.getRealizations());
    assertNull(actualAddProductResult.getOperator());
    assertNull(actualAddProductResult.getRealizationsStream());
    assertEquals(0, actualAddProductResult.getTypePriority());
    assertEquals(0.0d, actualAddProductResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAddProductResult.getVariance(), 0.0);
    assertEquals(1, actualAddProductResult.size());
    assertEquals(20.0d, actualAddProductResult.getAverage(), 0.0);
    assertEquals(20.0d, actualAddProductResult.getMax(), 0.0);
    assertEquals(20.0d, actualAddProductResult.getMin(), 0.0);
    assertTrue(actualAddProductResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAddProductResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualAddProductResult.expectation();
    assertSame(actualAddProductResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#addProduct(RandomVariable, RandomVariable)} with {@code RandomVariable},
   * {@code RandomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#addProduct(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.addProduct(RandomVariable, RandomVariable)"})
  public void testAddProductWithRandomVariableRandomVariable_whenScalarWithValueIsTen() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);
    Scalar factor1 = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddProductResult =
        ofResult.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddProductResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddProductResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {110.0d}, actualAddProductResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.addRatio(RandomVariable, RandomVariable)"})
  public void testAddRatio_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.addRatio(RandomVariable, RandomVariable)"})
  public void testAddRatio_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.div(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(numerator).div(isA(RandomVariable.class));
    assertTrue(actualAddRatioResult.abs() instanceof Scalar);
    assertTrue(actualAddRatioResult.cos() instanceof Scalar);
    assertTrue(actualAddRatioResult.exp() instanceof Scalar);
    assertTrue(actualAddRatioResult.expm1() instanceof Scalar);
    assertTrue(actualAddRatioResult.invert() instanceof Scalar);
    assertTrue(actualAddRatioResult.isNaN() instanceof Scalar);
    assertTrue(actualAddRatioResult.sin() instanceof Scalar);
    assertTrue(actualAddRatioResult.sqrt() instanceof Scalar);
    assertTrue(actualAddRatioResult.squared() instanceof Scalar);
    assertTrue(actualAddRatioResult.variance() instanceof Scalar);
    assertTrue(actualAddRatioResult instanceof Scalar);
    assertNull(actualAddRatioResult.getRealizations());
    assertNull(actualAddRatioResult.getOperator());
    assertNull(actualAddRatioResult.getRealizationsStream());
    assertEquals(0, actualAddRatioResult.getTypePriority());
    assertEquals(0.0d, actualAddRatioResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAddRatioResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAddRatioResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualAddRatioResult.getVariance(), 0.0);
    assertEquals(1, actualAddRatioResult.size());
    assertEquals(20.0d, actualAddRatioResult.getAverage(), 0.0);
    assertEquals(20.0d, actualAddRatioResult.getMax(), 0.0);
    assertEquals(20.0d, actualAddRatioResult.getMin(), 0.0);
    assertTrue(actualAddRatioResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAddRatioResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualAddRatioResult.expectation();
    assertSame(actualAddRatioResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#addRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is ten.
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#addRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.addRatio(RandomVariable, RandomVariable)"})
  public void testAddRatio_whenScalarWithValueIsTen_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);
    Scalar numerator = Scalar.of(10.0d);

    // Act
    RandomVariable actualAddRatioResult =
        ofResult.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddRatioResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddRatioResult instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {11.0d}, actualAddRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.subRatio(RandomVariable, RandomVariable)"})
  public void testSubRatio_thenAbsReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.div(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(10.0d));

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(numerator).div(isA(RandomVariable.class));
    assertTrue(actualSubRatioResult.abs() instanceof Scalar);
    assertTrue(actualSubRatioResult.cos() instanceof Scalar);
    assertTrue(actualSubRatioResult.exp() instanceof Scalar);
    assertTrue(actualSubRatioResult.expm1() instanceof Scalar);
    assertTrue(actualSubRatioResult.invert() instanceof Scalar);
    assertTrue(actualSubRatioResult.isNaN() instanceof Scalar);
    assertTrue(actualSubRatioResult.sin() instanceof Scalar);
    assertTrue(actualSubRatioResult.sqrt() instanceof Scalar);
    assertTrue(actualSubRatioResult.squared() instanceof Scalar);
    assertTrue(actualSubRatioResult.variance() instanceof Scalar);
    assertTrue(actualSubRatioResult instanceof Scalar);
    assertNull(actualSubRatioResult.getRealizations());
    assertNull(actualSubRatioResult.getOperator());
    assertNull(actualSubRatioResult.getRealizationsStream());
    assertEquals(-0.0d, actualSubRatioResult.getAverage(), 0.0);
    assertEquals(-0.0d, actualSubRatioResult.getMax(), 0.0);
    assertEquals(-0.0d, actualSubRatioResult.getMin(), 0.0);
    assertEquals(0, actualSubRatioResult.getTypePriority());
    assertEquals(0.0d, actualSubRatioResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSubRatioResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSubRatioResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualSubRatioResult.getVariance(), 0.0);
    assertEquals(1, actualSubRatioResult.size());
    assertTrue(actualSubRatioResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualSubRatioResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualSubRatioResult.expectation();
    assertSame(actualSubRatioResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then return Average is nine.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.subRatio(RandomVariable, RandomVariable)"})
  public void testSubRatio_thenReturnAverageIsNine() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromDoubleArray);
    assertEquals(9.0d, actualSubRatioResult.getAverage(), 0.0);
    assertEquals(9.0d, actualSubRatioResult.getMax(), 0.0);
    assertEquals(9.0d, actualSubRatioResult.getMin(), 0.0);
    assertArrayEquals(new double[] {9.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.subRatio(RandomVariable, RandomVariable)"})
  public void testSubRatio_thenValuesReturnRandomVariableFromDoubleArray() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray values = new RandomVariableFromDoubleArray(-1.0d);
    RandomVariableDifferentiableAADFactory factory = new RandomVariableDifferentiableAADFactory();

    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, factory);
    when(randomVariableAAD.sub(anyDouble())).thenReturn(randomVariableDifferentiableAAD);

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(numerator).div(isA(RandomVariable.class));
    verify(randomVariableAAD).sub(10.0d);
    assertTrue(actualSubRatioResult.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertEquals(1.0d, actualSubRatioResult.getAverage(), 0.0);
    assertEquals(1.0d, actualSubRatioResult.getMax(), 0.0);
    assertEquals(1.0d, actualSubRatioResult.getMin(), 0.0);
    assertSame(factory, ((RandomVariableDifferentiableAAD) actualSubRatioResult).getFactory());
    assertArrayEquals(new double[] {1.0d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>Then Values return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.subRatio(RandomVariable, RandomVariable)"})
  public void testSubRatio_thenValuesReturnScalar() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.sub(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD numerator = mock(RandomVariableAAD.class);
    when(numerator.div(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(numerator).div(isA(RandomVariable.class));
    verify(randomVariableAAD).sub(10.0d);
    assertTrue(actualSubRatioResult instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSubRatioResult.getValues() instanceof Scalar);
    assertTrue(actualSubRatioResult.isNaN() instanceof Scalar);
    assertNull(actualSubRatioResult.getRealizations());
    assertNull(actualSubRatioResult.getOperator());
    assertNull(actualSubRatioResult.getRealizationsStream());
    assertEquals(-10.0d, actualSubRatioResult.getAverage(), 0.0);
    assertEquals(-10.0d, actualSubRatioResult.getMax(), 0.0);
    assertEquals(-10.0d, actualSubRatioResult.getMin(), 0.0);
  }

  /**
   * Test {@link Scalar#subRatio(RandomVariable, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is minus one.
   *   <li>Then return Average is {@code 10.1}.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#subRatio(RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.subRatio(RandomVariable, RandomVariable)"})
  public void testSubRatio_whenScalarWithValueIsMinusOne_thenReturnAverageIs101() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);
    Scalar numerator = Scalar.of(-1.0d);

    // Act
    RandomVariable actualSubRatioResult =
        ofResult.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubRatioResult instanceof RandomVariableFromDoubleArray);
    assertEquals(10.1d, actualSubRatioResult.getAverage(), 0.0);
    assertEquals(10.1d, actualSubRatioResult.getMax(), 0.0);
    assertEquals(10.1d, actualSubRatioResult.getMin(), 0.0);
    assertArrayEquals(new double[] {10.1d}, actualSubRatioResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link Scalar#isNaN()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is {@link Double#NaN}.
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.isNaN()"})
  public void testIsNaN_givenScalarWithValueIsNaN_thenReturnAverageIsOne() {
    // Arrange and Act
    RandomVariable actualIsNaNResult = Scalar.of(Double.NaN).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof Scalar);
    assertEquals(1.0d, actualIsNaNResult.getAverage(), 0.0);
    assertEquals(1.0d, actualIsNaNResult.getMax(), 0.0);
    assertEquals(1.0d, actualIsNaNResult.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualIsNaNResult.expectation();
    assertSame(actualIsNaNResult, actualExpectationResult);
  }

  /**
   * Test {@link Scalar#isNaN()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link Scalar#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Scalar.isNaN()"})
  public void testIsNaN_givenScalarWithValueIsTen_thenReturnAverageIsZero() {
    // Arrange
    Scalar ofResult = Scalar.of(10.0d);

    // Act
    RandomVariable actualIsNaNResult = ofResult.isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof Scalar);
    assertEquals(0.0d, actualIsNaNResult.getAverage(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getMax(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualIsNaNResult.expectation();
    assertSame(actualIsNaNResult, actualExpectationResult);
  }
}
