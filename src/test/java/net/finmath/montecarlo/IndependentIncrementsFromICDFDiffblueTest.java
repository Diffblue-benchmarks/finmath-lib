package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntFunction;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class IndependentIncrementsFromICDFDiffblueTest {
  /**
   * Test {@link IndependentIncrementsFromICDF#IndependentIncrementsFromICDF(TimeDiscretization,
   * int, int, int, IntFunction)}.
   *
   * <p>Method under test: {@link
   * IndependentIncrementsFromICDF#IndependentIncrementsFromICDF(TimeDiscretization, int, int, int,
   * IntFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void IndependentIncrementsFromICDF.<init>(TimeDiscretization, int, int, int, IntFunction)"
  })
  public void testNewIndependentIncrementsFromICDF() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    IndependentIncrementsFromICDF actualIndependentIncrementsFromICDF =
        new IndependentIncrementsFromICDF(timeDiscretization, 3, 10, 42, mock(IntFunction.class));

    // Assert
    assertEquals(10, actualIndependentIncrementsFromICDF.getNumberOfPaths());
    assertEquals(3, actualIndependentIncrementsFromICDF.getNumberOfFactors());
    assertEquals(42, actualIndependentIncrementsFromICDF.getSeed());
    assertSame(timeDiscretization, actualIndependentIncrementsFromICDF.getTimeDiscretization());
  }

  /**
   * Test {@link IndependentIncrementsFromICDF#IndependentIncrementsFromICDF(TimeDiscretization,
   * int, int, int, IntFunction, RandomVariableFactory)}.
   *
   * <p>Method under test: {@link
   * IndependentIncrementsFromICDF#IndependentIncrementsFromICDF(TimeDiscretization, int, int, int,
   * IntFunction, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void IndependentIncrementsFromICDF.<init>(TimeDiscretization, int, int, int, IntFunction, RandomVariableFactory)"
  })
  public void testNewIndependentIncrementsFromICDF2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    IntFunction<IntFunction<DoubleUnaryOperator>> inverseCumulativeDistributionFunctions =
        mock(IntFunction.class);

    // Act
    IndependentIncrementsFromICDF actualIndependentIncrementsFromICDF =
        new IndependentIncrementsFromICDF(
            timeDiscretization,
            3,
            10,
            42,
            inverseCumulativeDistributionFunctions,
            new RandomVariableFloatFactory());

    // Assert
    assertEquals(10, actualIndependentIncrementsFromICDF.getNumberOfPaths());
    assertEquals(3, actualIndependentIncrementsFromICDF.getNumberOfFactors());
    assertEquals(42, actualIndependentIncrementsFromICDF.getSeed());
    assertSame(timeDiscretization, actualIndependentIncrementsFromICDF.getTimeDiscretization());
  }

  /**
   * Test {@link IndependentIncrementsFromICDF#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link IndependentIncrementsFromICDF#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "IndependentIncrements IndependentIncrementsFromICDF.getCloneWithModifiedSeed(int)"
  })
  public void testGetCloneWithModifiedSeed() {
    // Arrange
    IndependentIncrementsFromICDF independentIncrementsFromICDF =
        new IndependentIncrementsFromICDF(
            new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, mock(IntFunction.class));

    // Act
    IndependentIncrements actualCloneWithModifiedSeed =
        independentIncrementsFromICDF.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof IndependentIncrementsFromICDF);
    assertEquals(independentIncrementsFromICDF, actualCloneWithModifiedSeed);
  }

  /**
   * Test {@link
   * IndependentIncrementsFromICDF#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * IndependentIncrementsFromICDF#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "IndependentIncrements IndependentIncrementsFromICDF.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
  })
  public void testGetCloneWithModifiedTimeDiscretization() {
    // Arrange
    IndependentIncrementsFromICDF independentIncrementsFromICDF =
        new IndependentIncrementsFromICDF(
            new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, mock(IntFunction.class));

    // Act
    IndependentIncrements actualCloneWithModifiedTimeDiscretization =
        independentIncrementsFromICDF.getCloneWithModifiedTimeDiscretization(
            new TenorFromArray(10.0d, 10, 0.5d));

    // Assert
    assertTrue(actualCloneWithModifiedTimeDiscretization instanceof IndependentIncrementsFromICDF);
    assertEquals(independentIncrementsFromICDF, actualCloneWithModifiedTimeDiscretization);
  }

  /**
   * Test {@link IndependentIncrementsFromICDF#getIncrement(int, int)} with {@code timeIndex},
   * {@code factor}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link IndependentIncrementsFromICDF#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable IndependentIncrementsFromICDF.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    DoubleUnaryOperator doubleUnaryOperator = mock(DoubleUnaryOperator.class);
    when(doubleUnaryOperator.applyAsDouble(anyDouble())).thenReturn(10.0d);

    IntFunction<DoubleUnaryOperator> intFunction = mock(IntFunction.class);
    when(intFunction.apply(anyInt())).thenReturn(doubleUnaryOperator);

    IntFunction<IntFunction<DoubleUnaryOperator>> inverseCumulativeDistributionFunctions =
        mock(IntFunction.class);
    when(inverseCumulativeDistributionFunctions.apply(anyInt())).thenReturn(intFunction);
    IndependentIncrementsFromICDF independentIncrementsFromICDF =
        new IndependentIncrementsFromICDF(
            new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, inverseCumulativeDistributionFunctions);

    // Act
    RandomVariable actualIncrement = independentIncrementsFromICDF.getIncrement(1, 1);

    // Assert
    verify(doubleUnaryOperator, atLeast(1)).applyAsDouble(anyDouble());
    verify(intFunction, atLeast(1)).apply(anyInt());
    verify(inverseCumulativeDistributionFunctions, atLeast(1)).apply(anyInt());
    assertTrue(actualIncrement instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualIncrement.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualIncrement.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualIncrement.getStandardError(), 0.0);
    assertEquals(0.0d, actualIncrement.getVariance(), 0.0);
    assertEquals(1, actualIncrement.getTypePriority());
    assertEquals(10, actualIncrement.size());
    assertEquals(10.0d, actualIncrement.getAverage(), 0.0);
    assertEquals(10.0d, actualIncrement.getMax(), 0.0);
    assertEquals(10.0d, actualIncrement.getMin(), 0.0);
    assertEquals(11.0d, actualIncrement.getFiltrationTime(), 0.0);
    assertFalse(actualIncrement.isDeterministic());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualIncrement.getRealizations(),
        0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link IndependentIncrementsFromICDF#getNumberOfFactors()}
   *   <li>{@link IndependentIncrementsFromICDF#getNumberOfPaths()}
   *   <li>{@link IndependentIncrementsFromICDF#getSeed()}
   *   <li>{@link IndependentIncrementsFromICDF#getTimeDiscretization()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int IndependentIncrementsFromICDF.getNumberOfFactors()",
    "int IndependentIncrementsFromICDF.getNumberOfPaths()",
    "int IndependentIncrementsFromICDF.getSeed()",
    "TimeDiscretization IndependentIncrementsFromICDF.getTimeDiscretization()",
    "java.lang.String IndependentIncrementsFromICDF.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    IndependentIncrementsFromICDF independentIncrementsFromICDF =
        new IndependentIncrementsFromICDF(timeDiscretization, 3, 10, 42, mock(IntFunction.class));

    // Act
    int actualNumberOfFactors = independentIncrementsFromICDF.getNumberOfFactors();
    int actualNumberOfPaths = independentIncrementsFromICDF.getNumberOfPaths();
    int actualSeed = independentIncrementsFromICDF.getSeed();

    // Assert
    assertEquals(10, actualNumberOfPaths);
    assertEquals(3, actualNumberOfFactors);
    assertEquals(42, actualSeed);
    assertSame(timeDiscretization, independentIncrementsFromICDF.getTimeDiscretization());
  }

  /**
   * Test {@link IndependentIncrementsFromICDF#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * IndependentIncrementsFromICDF#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable IndependentIncrementsFromICDF.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant_thenReturnScalar() {
    // Arrange
    IndependentIncrementsFromICDF independentIncrementsFromICDF =
        new IndependentIncrementsFromICDF(
            new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, mock(IntFunction.class));

    // Act
    RandomVariable actualRandomVariableForConstant =
        independentIncrementsFromICDF.getRandomVariableForConstant(10.0d);

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
   * Test {@link IndependentIncrementsFromICDF#equals(Object)}, and {@link
   * IndependentIncrementsFromICDF#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link IndependentIncrementsFromICDF#equals(Object)}
   *   <li>{@link IndependentIncrementsFromICDF#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean IndependentIncrementsFromICDF.equals(Object)",
    "int IndependentIncrementsFromICDF.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    IndependentIncrementsFromICDF independentIncrementsFromICDF =
        new IndependentIncrementsFromICDF(
            new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, mock(IntFunction.class));
    IndependentIncrementsFromICDF independentIncrementsFromICDF2 =
        new IndependentIncrementsFromICDF(
            new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, mock(IntFunction.class));

    // Act and Assert
    assertEquals(independentIncrementsFromICDF, independentIncrementsFromICDF2);
    assertEquals(
        independentIncrementsFromICDF.hashCode(), independentIncrementsFromICDF2.hashCode());
  }

  /**
   * Test {@link IndependentIncrementsFromICDF#equals(Object)}, and {@link
   * IndependentIncrementsFromICDF#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link IndependentIncrementsFromICDF#equals(Object)}
   *   <li>{@link IndependentIncrementsFromICDF#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean IndependentIncrementsFromICDF.equals(Object)",
    "int IndependentIncrementsFromICDF.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    IndependentIncrementsFromICDF independentIncrementsFromICDF =
        new IndependentIncrementsFromICDF(
            new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, mock(IntFunction.class));

    // Act and Assert
    assertEquals(independentIncrementsFromICDF, independentIncrementsFromICDF);
    int expectedHashCodeResult = independentIncrementsFromICDF.hashCode();
    assertEquals(expectedHashCodeResult, independentIncrementsFromICDF.hashCode());
  }

  /**
   * Test {@link IndependentIncrementsFromICDF#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link IndependentIncrementsFromICDF#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean IndependentIncrementsFromICDF.equals(Object)",
    "int IndependentIncrementsFromICDF.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    IndependentIncrementsFromICDF independentIncrementsFromICDF =
        new IndependentIncrementsFromICDF(
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            3,
            10,
            42,
            mock(IntFunction.class));

    // Act and Assert
    assertNotEquals(
        independentIncrementsFromICDF,
        new IndependentIncrementsFromICDF(
            new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, mock(IntFunction.class)));
  }

  /**
   * Test {@link IndependentIncrementsFromICDF#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link IndependentIncrementsFromICDF#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean IndependentIncrementsFromICDF.equals(Object)",
    "int IndependentIncrementsFromICDF.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new IndependentIncrementsFromICDF(
            new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, mock(IntFunction.class)),
        null);
  }

  /**
   * Test {@link IndependentIncrementsFromICDF#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link IndependentIncrementsFromICDF#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean IndependentIncrementsFromICDF.equals(Object)",
    "int IndependentIncrementsFromICDF.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new IndependentIncrementsFromICDF(
            new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42, mock(IntFunction.class)),
        "Different type to IndependentIncrementsFromICDF");
  }
}
