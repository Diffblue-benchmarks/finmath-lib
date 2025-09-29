package net.finmath.marketdata2.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata2.model.AnalyticModel;
import net.finmath.marketdata2.model.AnalyticModelFromCurvesAndVols;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class PerformanceDiffblueTest {
  /**
   * Test {@link Performance#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <p>Method under test: {@link Performance#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Performance.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel() {
    // Arrange
    AbstractAnalyticProduct productNumerator = mock(AbstractAnalyticProduct.class);
    when(productNumerator.getValue(anyDouble(), Mockito.<AnalyticModel>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    AbstractAnalyticProduct productDenominator = mock(AbstractAnalyticProduct.class);
    when(productDenominator.getValue(anyDouble(), Mockito.<AnalyticModel>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Performance performance = new Performance(productNumerator, productDenominator);

    // Act
    RandomVariable actualValue = performance.getValue(10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(productNumerator).getValue(eq(10.0d), isA(AnalyticModel.class));
    verify(productDenominator).getValue(eq(10.0d), isA(AnalyticModel.class));
    assertTrue(actualValue.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualValue).getGradient().size());
    assertEquals(1, actualValue.size());
    assertEquals(1.0d, actualValue.getAverage(), 0.0);
    assertEquals(1.0d, actualValue.getMax(), 0.0);
    assertEquals(1.0d, actualValue.getMin(), 0.0);
    assertEquals(3, actualValue.getTypePriority());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link Performance#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Performance#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Performance.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    AbstractAnalyticProduct productNumerator = mock(AbstractAnalyticProduct.class);
    when(productNumerator.getValue(anyDouble(), Mockito.<AnalyticModel>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    AbstractAnalyticProduct productDenominator = mock(AbstractAnalyticProduct.class);
    when(productDenominator.getValue(anyDouble(), Mockito.<AnalyticModel>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Performance performance = new Performance(productNumerator, productDenominator);

    // Act
    RandomVariable actualValue = performance.getValue(10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
    verify(productNumerator).getValue(eq(10.0d), isA(AnalyticModel.class));
    verify(productDenominator).getValue(eq(10.0d), isA(AnalyticModel.class));
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(1, actualValue.size());
    assertEquals(1.0d, actualValue.getAverage(), 0.0);
    assertEquals(1.0d, actualValue.getMax(), 0.0);
    assertEquals(1.0d, actualValue.getMin(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualValue.getRealizations(), 0.0);
  }
}
