package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.modelling.products.Swaption;
import net.finmath.modelling.products.Swaption.ValueUnit;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SwaptionATMDiffblueTest {
  /**
   * Test {@link SwaptionATM#SwaptionATM(double[], ValueUnit)}.
   *
   * <ul>
   *   <li>When array of {@code double} with ten and {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATM#SwaptionATM(double[], Swaption.ValueUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwaptionATM.<init>(double[], Swaption.ValueUnit)"})
  public void testNewSwaptionATM_whenArrayOfDoubleWithTenAnd05() {
    // Arrange and Act
    SwaptionATM actualSwaptionATM =
        new SwaptionATM(new double[] {10.0d, 0.5d, 10.0d, 0.5d}, ValueUnit.VALUE);

    // Assert
    assertNull(actualSwaptionATM.getCurrency());
  }

  /**
   * Test {@link SwaptionATM#SwaptionATM(double[], ValueUnit)}.
   *
   * <ul>
   *   <li>When empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATM#SwaptionATM(double[], Swaption.ValueUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwaptionATM.<init>(double[], Swaption.ValueUnit)"})
  public void testNewSwaptionATM_whenEmptyArrayOfDouble() {
    // Arrange and Act
    SwaptionATM actualSwaptionATM = new SwaptionATM(new double[] {}, ValueUnit.VALUE);

    // Assert
    assertNull(actualSwaptionATM.getCurrency());
  }

  /**
   * Test {@link SwaptionATM#getImpliedBachelierATMOptionVolatility(RandomVariable, double,
   * double)}.
   *
   * <p>Method under test: {@link SwaptionATM#getImpliedBachelierATMOptionVolatility(RandomVariable,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionATM.getImpliedBachelierATMOptionVolatility(RandomVariable, double, double)"
  })
  public void testGetImpliedBachelierATMOptionVolatility() {
    // Arrange
    SwaptionATM swaptionATM =
        new SwaptionATM(new double[] {10.0d, 0.5d, 10.0d, 0.5d}, ValueUnit.VALUE);

    // Act
    RandomVariable actualImpliedBachelierATMOptionVolatility =
        swaptionATM.getImpliedBachelierATMOptionVolatility(
            new RandomVariableFromDoubleArray(10.0d), 10.0d, 10.0d);

    // Assert
    assertTrue(actualImpliedBachelierATMOptionVolatility instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualImpliedBachelierATMOptionVolatility.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualImpliedBachelierATMOptionVolatility.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualImpliedBachelierATMOptionVolatility.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualImpliedBachelierATMOptionVolatility.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualImpliedBachelierATMOptionVolatility.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualImpliedBachelierATMOptionVolatility.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualImpliedBachelierATMOptionVolatility.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualImpliedBachelierATMOptionVolatility.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualImpliedBachelierATMOptionVolatility.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualImpliedBachelierATMOptionVolatility.variance()
            instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualImpliedBachelierATMOptionVolatility.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierATMOptionVolatility.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierATMOptionVolatility.getStandardError(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierATMOptionVolatility.getVariance(), 0.0);
    assertEquals(0.7926654595212022d, actualImpliedBachelierATMOptionVolatility.getAverage(), 0.0);
    assertEquals(0.7926654595212022d, actualImpliedBachelierATMOptionVolatility.getMax(), 0.0);
    assertEquals(0.7926654595212022d, actualImpliedBachelierATMOptionVolatility.getMin(), 0.0);
    assertEquals(1, actualImpliedBachelierATMOptionVolatility.getTypePriority());
    assertEquals(1, actualImpliedBachelierATMOptionVolatility.size());
    assertTrue(actualImpliedBachelierATMOptionVolatility.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualImpliedBachelierATMOptionVolatility.getFiltrationTime(),
        0.0);
    assertArrayEquals(
        new double[] {0.7926654595212022d},
        actualImpliedBachelierATMOptionVolatility.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SwaptionATM#getImpliedBachelierATMOptionVolatility(RandomVariable, double,
   * double)}.
   *
   * <p>Method under test: {@link SwaptionATM#getImpliedBachelierATMOptionVolatility(RandomVariable,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionATM.getImpliedBachelierATMOptionVolatility(RandomVariable, double, double)"
  })
  public void testGetImpliedBachelierATMOptionVolatility2() {
    // Arrange
    SwaptionATM swaptionATM =
        new SwaptionATM(new double[] {10.0d, 0.5d, 10.0d, 0.5d}, ValueUnit.VALUE);

    RandomVariableAAD optionValue = mock(RandomVariableAAD.class);
    when(optionValue.average()).thenReturn(RandomVariableDifferentiableAADPathwise.of(10.0d));

    // Act
    RandomVariable actualImpliedBachelierATMOptionVolatility =
        swaptionATM.getImpliedBachelierATMOptionVolatility(optionValue, 10.0d, 10.0d);

    // Assert
    verify(optionValue).average();
    assertTrue(
        actualImpliedBachelierATMOptionVolatility
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualImpliedBachelierATMOptionVolatility.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierATMOptionVolatility.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierATMOptionVolatility.getStandardError(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierATMOptionVolatility.getVariance(), 0.0);
    assertEquals(0.7926654595212022d, actualImpliedBachelierATMOptionVolatility.getAverage(), 0.0);
    assertEquals(0.7926654595212022d, actualImpliedBachelierATMOptionVolatility.getMax(), 0.0);
    assertEquals(0.7926654595212022d, actualImpliedBachelierATMOptionVolatility.getMin(), 0.0);
    assertEquals(1, actualImpliedBachelierATMOptionVolatility.size());
    assertTrue(actualImpliedBachelierATMOptionVolatility.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualImpliedBachelierATMOptionVolatility.getFiltrationTime(),
        0.0);
    assertArrayEquals(
        new double[] {0.7926654595212022d},
        actualImpliedBachelierATMOptionVolatility.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SwaptionATM#getImpliedBachelierATMOptionVolatility(RandomVariable, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return Max is {@code 0.49804639687723723}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATM#getImpliedBachelierATMOptionVolatility(RandomVariable,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionATM.getImpliedBachelierATMOptionVolatility(RandomVariable, double, double)"
  })
  public void testGetImpliedBachelierATMOptionVolatility_thenReturnMaxIs049804639687723723() {
    // Arrange
    SwaptionATM swaptionATM =
        new SwaptionATM(new double[] {10.0d, 0.5d, 10.0d, 0.5d}, ValueUnit.VALUE);

    RandomVariableAAD optionValue = mock(RandomVariableAAD.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(
            10.0d,
            new double[] {
              Double.NEGATIVE_INFINITY,
              6.283185307179586d,
              Double.NEGATIVE_INFINITY,
              6.283185307179586d
            });
    when(optionValue.average()).thenReturn(randomVariableDifferentiableAADPathwise);

    // Act
    RandomVariable actualImpliedBachelierATMOptionVolatility =
        swaptionATM.getImpliedBachelierATMOptionVolatility(optionValue, 10.0d, 10.0d);

    // Assert
    verify(optionValue).average();
    assertTrue(
        actualImpliedBachelierATMOptionVolatility
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.49804639687723723d, actualImpliedBachelierATMOptionVolatility.getMax(), 0.0);
    assertEquals(10.0d, actualImpliedBachelierATMOptionVolatility.getFiltrationTime(), 0.0);
    assertEquals(4, actualImpliedBachelierATMOptionVolatility.size());
    assertFalse(actualImpliedBachelierATMOptionVolatility.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualImpliedBachelierATMOptionVolatility.getMin(), 0.0);
    assertEquals(Double.NaN, actualImpliedBachelierATMOptionVolatility.getAverage(), 0.0);
    assertEquals(Double.NaN, actualImpliedBachelierATMOptionVolatility.getSampleVariance(), 0.0);
    assertEquals(Double.NaN, actualImpliedBachelierATMOptionVolatility.getStandardDeviation(), 0.0);
    assertEquals(Double.NaN, actualImpliedBachelierATMOptionVolatility.getStandardError(), 0.0);
    assertEquals(Double.NaN, actualImpliedBachelierATMOptionVolatility.getVariance(), 0.0);
    assertArrayEquals(
        new double[] {
          Double.NEGATIVE_INFINITY,
          0.49804639687723723d,
          Double.NEGATIVE_INFINITY,
          0.49804639687723723d
        },
        actualImpliedBachelierATMOptionVolatility.getRealizations(),
        0.0);
  }

  /**
   * Test {@link SwaptionATM#getImpliedBachelierATMOptionVolatility(RandomVariable, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATM#getImpliedBachelierATMOptionVolatility(RandomVariable,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SwaptionATM.getImpliedBachelierATMOptionVolatility(RandomVariable, double, double)"
  })
  public void testGetImpliedBachelierATMOptionVolatility_thenReturnScalar() {
    // Arrange
    SwaptionATM swaptionATM =
        new SwaptionATM(new double[] {10.0d, 0.5d, 10.0d, 0.5d}, ValueUnit.VALUE);

    // Act
    RandomVariable actualImpliedBachelierATMOptionVolatility =
        swaptionATM.getImpliedBachelierATMOptionVolatility(
            Scalar.of(Double.NEGATIVE_INFINITY), 10.0d, 10.0d);

    // Assert
    assertTrue(actualImpliedBachelierATMOptionVolatility instanceof Scalar);
    assertTrue(actualImpliedBachelierATMOptionVolatility.abs() instanceof Scalar);
    assertTrue(actualImpliedBachelierATMOptionVolatility.cos() instanceof Scalar);
    assertTrue(actualImpliedBachelierATMOptionVolatility.exp() instanceof Scalar);
    assertTrue(actualImpliedBachelierATMOptionVolatility.expm1() instanceof Scalar);
    assertTrue(actualImpliedBachelierATMOptionVolatility.invert() instanceof Scalar);
    assertTrue(actualImpliedBachelierATMOptionVolatility.isNaN() instanceof Scalar);
    assertTrue(actualImpliedBachelierATMOptionVolatility.sin() instanceof Scalar);
    assertTrue(actualImpliedBachelierATMOptionVolatility.sqrt() instanceof Scalar);
    assertTrue(actualImpliedBachelierATMOptionVolatility.squared() instanceof Scalar);
    assertTrue(actualImpliedBachelierATMOptionVolatility.variance() instanceof Scalar);
    assertNull(actualImpliedBachelierATMOptionVolatility.getRealizations());
    assertNull(actualImpliedBachelierATMOptionVolatility.getOperator());
    assertNull(actualImpliedBachelierATMOptionVolatility.getRealizationsStream());
    assertEquals(0, actualImpliedBachelierATMOptionVolatility.getTypePriority());
    assertEquals(0.0d, actualImpliedBachelierATMOptionVolatility.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierATMOptionVolatility.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierATMOptionVolatility.getStandardError(), 0.0);
    assertEquals(0.0d, actualImpliedBachelierATMOptionVolatility.getVariance(), 0.0);
    assertEquals(1, actualImpliedBachelierATMOptionVolatility.size());
    assertTrue(actualImpliedBachelierATMOptionVolatility.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualImpliedBachelierATMOptionVolatility.getAverage(), 0.0);
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualImpliedBachelierATMOptionVolatility.getFiltrationTime(),
        0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualImpliedBachelierATMOptionVolatility.getMax(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualImpliedBachelierATMOptionVolatility.getMin(), 0.0);
    RandomVariable actualExpectationResult =
        actualImpliedBachelierATMOptionVolatility.expectation();
    assertSame(actualImpliedBachelierATMOptionVolatility, actualExpectationResult);
  }
}
