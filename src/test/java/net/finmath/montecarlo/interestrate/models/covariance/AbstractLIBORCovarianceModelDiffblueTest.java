package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AbstractLIBORCovarianceModelDiffblueTest {
  /**
   * Test {@link AbstractLIBORCovarianceModel#getFactorLoading(double, double, RandomVariable[])}
   * with {@code double}, {@code double}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getFactorLoading(double, double,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractLIBORCovarianceModel.getFactorLoading(double, double, RandomVariable[])"
  })
  public void testGetFactorLoadingWithDoubleDoubleRandomVariable() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            10.0d, 10.0d, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getFactorLoading(eq(0), eq(0), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {-80800.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getFactorLoading(double, double, RandomVariable[])}
   * with {@code double}, {@code double}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getFactorLoading(double, double,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractLIBORCovarianceModel.getFactorLoading(double, double, RandomVariable[])"
  })
  public void testGetFactorLoadingWithDoubleDoubleRandomVariable2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            10.0d, 10.0d, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getFactorLoading(eq(0), eq(0), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {-80800.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getFactorLoading(double, double, RandomVariable[])}
   * with {@code double}, {@code double}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getFactorLoading(double, double,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractLIBORCovarianceModel.getFactorLoading(double, double, RandomVariable[])"
  })
  public void testGetFactorLoadingWithDoubleDoubleRandomVariable3() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization())
        .thenReturn(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            10.0d, 10.0d, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getFactorLoading(eq(0), eq(0), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {-80800.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getFactorLoading(double, double, RandomVariable[])}
   * with {@code double}, {@code double}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getFactorLoading(double, double,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractLIBORCovarianceModel.getFactorLoading(double, double, RandomVariable[])"
  })
  public void testGetFactorLoadingWithDoubleDoubleRandomVariable4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            10.0d, 10.0d, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromDoubleArray);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getFactorLoading(double, double, RandomVariable[])}
   * with {@code double}, {@code double}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getFactorLoading(double, double,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractLIBORCovarianceModel.getFactorLoading(double, double, RandomVariable[])"
  })
  public void testGetFactorLoadingWithDoubleDoubleRandomVariable5() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            10.0d, 10.0d, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[2] instanceof RandomVariableFromDoubleArray);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getFactorLoading(double, double, RandomVariable[])}
   * with {@code double}, {@code double}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getFactorLoading(double, double,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractLIBORCovarianceModel.getFactorLoading(double, double, RandomVariable[])"
  })
  public void testGetFactorLoadingWithDoubleDoubleRandomVariable6() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            10.0d, 10.0d, new RandomVariable[] {RandomVariableDifferentiableAAD.of(1.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getFactorLoading(double, double, RandomVariable[])}
   * with {@code double}, {@code double}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getFactorLoading(double, double,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractLIBORCovarianceModel.getFactorLoading(double, double, RandomVariable[])"
  })
  public void testGetFactorLoadingWithDoubleDoubleRandomVariable7() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d),
            10.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            10.0d, 10.0d, new RandomVariable[] {RandomVariableDifferentiableAAD.of(1.0d)});

    // Assert
    verify(covarianceModel).getFactorLoading(eq(0), eq(0), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) randomVariable).getGradient().size());
    assertEquals(1, actualFactorLoading.length);
    assertEquals(220.0d, randomVariable.getAverage(), 0.0);
    assertEquals(220.0d, randomVariable.getMax(), 0.0);
    assertEquals(220.0d, randomVariable.getMin(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, randomVariable.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {220.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getFactorLoading(double, int, RandomVariable[])} with
   * {@code double}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getFactorLoading(double, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractLIBORCovarianceModel.getFactorLoading(double, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithDoubleIntRandomVariable() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            10.0d,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(1.0d)
            });

    // Assert
    verify(covarianceModel).getFactorLoading(eq(0), eq(1), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {110.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getFactorLoading(double, int, RandomVariable[])} with
   * {@code double}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getFactorLoading(double, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractLIBORCovarianceModel.getFactorLoading(double, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithDoubleIntRandomVariable2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization())
        .thenReturn(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            10.0d,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(1.0d)
            });

    // Assert
    verify(covarianceModel).getFactorLoading(eq(0), eq(1), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {110.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getFactorLoading(double, int, RandomVariable[])} with
   * {@code double}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getFactorLoading(double, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractLIBORCovarianceModel.getFactorLoading(double, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithDoubleIntRandomVariable3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            10.0d,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(1.0d)
            });

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[2] instanceof RandomVariableFromDoubleArray);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getFactorLoading(double, int, RandomVariable[])} with
   * {@code double}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getFactorLoading(double, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractLIBORCovarianceModel.getFactorLoading(double, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithDoubleIntRandomVariable4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param covarianceModel =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            10.0d,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(1.0d)
            });

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[2] instanceof RandomVariableFromDoubleArray);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getFactorLoading(double, int, RandomVariable[])} with
   * {@code double}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getFactorLoading(double, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractLIBORCovarianceModel.getFactorLoading(double, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithDoubleIntRandomVariable5() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            10.0d,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, RandomVariableDifferentiableAAD.of(0.5d)
            });

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getFactorLoading(double, int, RandomVariable[])} with
   * {@code double}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getFactorLoading(double, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractLIBORCovarianceModel.getFactorLoading(double, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithDoubleIntRandomVariable6() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d),
            10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            10.0d,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, RandomVariableDifferentiableAAD.of(0.5d)
            });

    // Assert
    verify(covarianceModel).getFactorLoading(eq(0), eq(1), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) randomVariable).getGradient().size());
    assertEquals(1, actualFactorLoading.length);
    assertEquals(90.0d, randomVariable.getAverage(), 0.0);
    assertEquals(90.0d, randomVariable.getMax(), 0.0);
    assertEquals(90.0d, randomVariable.getMin(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, randomVariable.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {90.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getFactorLoading(double, int, RandomVariable[])} with
   * {@code double}, {@code int}, {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getFactorLoading(double, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] AbstractLIBORCovarianceModel.getFactorLoading(double, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithDoubleIntRandomVariable_thenReturnArrayLengthIsTwo() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            10.0d,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(1.0d)
            });

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromDoubleArray);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getCovariance(double, int, int, RandomVariable[])}
   * with {@code time}, {@code component1}, {@code component2}, {@code realizationAtTimeIndex}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getCovariance(double, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AbstractLIBORCovarianceModel.getCovariance(double, int, int, RandomVariable[])"
  })
  public void testGetCovarianceWithTimeComponent1Component2RealizationAtTimeIndex() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(1.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(1.0d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray3 =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable actualCovariance =
        hullWhiteLocalVolatilityModel.getCovariance(
            10.0d,
            1,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray3, new RandomVariableFromDoubleArray(1.0d)
            });

    // Assert
    verify(covarianceModel, atLeast(1)).getFactorLoading(eq(0), eq(1), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCovariance instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {43923.0d}, actualCovariance.getRealizations(), 0.0);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getCovariance(double, int, int, RandomVariable[])}
   * with {@code time}, {@code component1}, {@code component2}, {@code realizationAtTimeIndex}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getCovariance(double, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AbstractLIBORCovarianceModel.getCovariance(double, int, int, RandomVariable[])"
  })
  public void testGetCovarianceWithTimeComponent1Component2RealizationAtTimeIndex2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(1.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(1.0d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization())
        .thenReturn(
            new TenorFromArray(1.0d, 1.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray3 =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable actualCovariance =
        hullWhiteLocalVolatilityModel.getCovariance(
            10.0d,
            1,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray3, new RandomVariableFromDoubleArray(1.0d)
            });

    // Assert
    verify(covarianceModel, atLeast(1)).getFactorLoading(eq(0), eq(1), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCovariance instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {43923.0d}, actualCovariance.getRealizations(), 0.0);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getCovariance(int, int, int, RandomVariable[])} with
   * {@code timeIndex}, {@code component1}, {@code component2}, {@code realizationAtTimeIndex}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getCovariance(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AbstractLIBORCovarianceModel.getCovariance(int, int, int, RandomVariable[])"
  })
  public void testGetCovarianceWithTimeIndexComponent1Component2RealizationAtTimeIndex() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(1.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(1.0d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray3 =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable actualCovariance =
        hullWhiteLocalVolatilityModel.getCovariance(
            1,
            1,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray3, new RandomVariableFromDoubleArray(1.0d)
            });

    // Assert
    verify(covarianceModel, atLeast(1)).getFactorLoading(eq(1), eq(1), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCovariance instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCovariance.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualCovariance.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCovariance.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCovariance.getStandardError(), 0.0);
    assertEquals(0.0d, actualCovariance.getVariance(), 0.0);
    assertEquals(1, actualCovariance.getTypePriority());
    assertEquals(1, actualCovariance.size());
    assertEquals(43923.0d, actualCovariance.getAverage(), 0.0);
    assertEquals(43923.0d, actualCovariance.getMax(), 0.0);
    assertEquals(43923.0d, actualCovariance.getMin(), 0.0);
    assertTrue(actualCovariance.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualCovariance.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {43923.0d}, actualCovariance.getRealizations(), 0.0);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization AbstractLIBORCovarianceModel.getTimeDiscretization()"})
  public void testGetTimeDiscretization() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    TenorFromArray tenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    when(covarianceModel.getTimeDiscretization()).thenReturn(tenorFromArray);

    // Act
    TimeDiscretization actualTimeDiscretization =
        new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d)
            .getTimeDiscretization();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertSame(tenorFromArray, actualTimeDiscretization);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getLiborPeriodDiscretization()}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getLiborPeriodDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization AbstractLIBORCovarianceModel.getLiborPeriodDiscretization()"
  })
  public void testGetLiborPeriodDiscretization() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    TenorFromArray tenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    when(covarianceModel.getLiborPeriodDiscretization()).thenReturn(tenorFromArray);
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    TimeDiscretization actualLiborPeriodDiscretization =
        new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d)
            .getLiborPeriodDiscretization();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertSame(tenorFromArray, actualLiborPeriodDiscretization);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModel#getNumberOfFactors()}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModel#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AbstractLIBORCovarianceModel.getNumberOfFactors()"})
  public void testGetNumberOfFactors() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    int actualNumberOfFactors =
        new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d)
            .getNumberOfFactors();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(3, actualNumberOfFactors);
  }
}
