package net.finmath.montecarlo.templatemethoddesign.assetderivativevaluation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.montecarlo.BrownianMotionLazyInit;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.templatemethoddesign.LogNormalProcess;
import net.finmath.montecarlo.templatemethoddesign.LogNormalProcess.Scheme;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MonteCarloBlackScholesModel2DiffblueTest {
  /**
   * Test {@link MonteCarloBlackScholesModel2#MonteCarloBlackScholesModel2(TimeDiscretization, int,
   * double, double, double)}.
   *
   * <p>Method under test: {@link
   * MonteCarloBlackScholesModel2#MonteCarloBlackScholesModel2(TimeDiscretization, int, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloBlackScholesModel2.<init>(TimeDiscretization, int, double, double, double)"
  })
  public void testNewMonteCarloBlackScholesModel2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    MonteCarloBlackScholesModel2 actualMonteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(timeDiscretization, 10, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(
        actualMonteCarloBlackScholesModel2.getBrownianMotion() instanceof BrownianMotionLazyInit);
    TimeDiscretization timeDiscretization2 =
        actualMonteCarloBlackScholesModel2.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualMonteCarloBlackScholesModel2.getNumberOfComponents());
    assertEquals(1, actualMonteCarloBlackScholesModel2.getNumberOfFactors());
    assertEquals(1, actualMonteCarloBlackScholesModel2.getNumberOfAssets());
    assertEquals(1, actualMonteCarloBlackScholesModel2.getInitialValue().length);
    assertEquals(10, actualMonteCarloBlackScholesModel2.getNumberOfPaths());
    assertEquals(10.0d, actualMonteCarloBlackScholesModel2.getRiskFreeRate(), 0.0);
    assertEquals(10.0d, actualMonteCarloBlackScholesModel2.getVolatility(), 0.0);
    assertEquals(Scheme.EULER, actualMonteCarloBlackScholesModel2.getScheme());
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#MonteCarloBlackScholesModel2(TimeDiscretization, int,
   * double, double, double, int)}.
   *
   * <p>Method under test: {@link
   * MonteCarloBlackScholesModel2#MonteCarloBlackScholesModel2(TimeDiscretization, int, double,
   * double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloBlackScholesModel2.<init>(TimeDiscretization, int, double, double, double, int)"
  })
  public void testNewMonteCarloBlackScholesModel22() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    MonteCarloBlackScholesModel2 actualMonteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(timeDiscretization, 10, 10.0d, 10.0d, 10.0d, 42);

    // Assert
    assertTrue(
        actualMonteCarloBlackScholesModel2.getBrownianMotion() instanceof BrownianMotionLazyInit);
    TimeDiscretization timeDiscretization2 =
        actualMonteCarloBlackScholesModel2.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualMonteCarloBlackScholesModel2.getNumberOfComponents());
    assertEquals(1, actualMonteCarloBlackScholesModel2.getNumberOfFactors());
    assertEquals(1, actualMonteCarloBlackScholesModel2.getNumberOfAssets());
    assertEquals(1, actualMonteCarloBlackScholesModel2.getInitialValue().length);
    assertEquals(10, actualMonteCarloBlackScholesModel2.getNumberOfPaths());
    assertEquals(10.0d, actualMonteCarloBlackScholesModel2.getRiskFreeRate(), 0.0);
    assertEquals(10.0d, actualMonteCarloBlackScholesModel2.getVolatility(), 0.0);
    assertEquals(Scheme.EULER, actualMonteCarloBlackScholesModel2.getScheme());
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getReferenceDate()}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.time.LocalDateTime MonteCarloBlackScholesModel2.getReferenceDate()"})
  public void testGetReferenceDate() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> monteCarloBlackScholesModel2.getReferenceDate());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MonteCarloBlackScholesModel2#toString()}
   *   <li>{@link MonteCarloBlackScholesModel2#getInitialValue()}
   *   <li>{@link MonteCarloBlackScholesModel2#getNumberOfAssets()}
   *   <li>{@link MonteCarloBlackScholesModel2#getRiskFreeRate()}
   *   <li>{@link MonteCarloBlackScholesModel2#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] MonteCarloBlackScholesModel2.getInitialValue()",
    "int MonteCarloBlackScholesModel2.getNumberOfAssets()",
    "double MonteCarloBlackScholesModel2.getRiskFreeRate()",
    "double MonteCarloBlackScholesModel2.getVolatility()",
    "java.lang.String MonteCarloBlackScholesModel2.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act
    monteCarloBlackScholesModel2.toString();
    RandomVariable[] actualInitialValue = monteCarloBlackScholesModel2.getInitialValue();
    int actualNumberOfAssets = monteCarloBlackScholesModel2.getNumberOfAssets();
    double actualRiskFreeRate = monteCarloBlackScholesModel2.getRiskFreeRate();

    // Assert
    assertTrue(actualInitialValue[0] instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualNumberOfAssets);
    assertEquals(1, actualInitialValue.length);
    assertEquals(10.0d, actualRiskFreeRate, 0.0);
    assertEquals(10.0d, monteCarloBlackScholesModel2.getVolatility(), 0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getDrift(int, int, RandomVariable[],
   * RandomVariable[])} with {@code timeIndex}, {@code componentIndex}, {@code
   * realizationAtTimeIndex}, {@code realizationPredictor}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getDrift(int, int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MonteCarloBlackScholesModel2.getDrift(int, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDriftWithTimeIndexComponentIndexRealizationAtTimeIndexRealizationPredictor() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualDrift =
        monteCarloBlackScholesModel2.getDrift(
            1,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualDrift instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualDrift.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualDrift.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDrift.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDrift.getStandardError(), 0.0);
    assertEquals(0.0d, actualDrift.getVariance(), 0.0);
    assertEquals(1, actualDrift.getTypePriority());
    assertEquals(1, actualDrift.size());
    assertEquals(10.0d, actualDrift.getAverage(), 0.0);
    assertEquals(10.0d, actualDrift.getMax(), 0.0);
    assertEquals(10.0d, actualDrift.getMin(), 0.0);
    assertTrue(actualDrift.isDeterministic());
    assertArrayEquals(new double[] {10.0d}, actualDrift.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getFactorLoading(int, int, int, RandomVariable[])}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getFactorLoading(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MonteCarloBlackScholesModel2.getFactorLoading(int, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualFactorLoading =
        monteCarloBlackScholesModel2.getFactorLoading(
            1, 3, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualFactorLoading.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualFactorLoading.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualFactorLoading.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualFactorLoading.getStandardError(), 0.0);
    assertEquals(0.0d, actualFactorLoading.getVariance(), 0.0);
    assertEquals(1, actualFactorLoading.getTypePriority());
    assertEquals(1, actualFactorLoading.size());
    assertEquals(10.0d, actualFactorLoading.getAverage(), 0.0);
    assertEquals(10.0d, actualFactorLoading.getMax(), 0.0);
    assertEquals(10.0d, actualFactorLoading.getMin(), 0.0);
    assertTrue(actualFactorLoading.isDeterministic());
    assertArrayEquals(new double[] {10.0d}, actualFactorLoading.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getAssetValue(double, int)} with {@code time}, {@code
   * assetIndex}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getAssetValue(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel2.getAssetValue(double, int)"})
  public void testGetAssetValueWithTimeAssetIndex() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(timeDiscretization, 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualAssetValue = monteCarloBlackScholesModel2.getAssetValue(10.0d, 0);

    // Assert
    assertTrue(actualAssetValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          2.1610243752514466E-165d,
          1.432469881306152E-150d,
          9.859323718075964E-155d,
          5.929410963121059E-142d,
          1.863137692725781E-158d,
          6.674043338267043E-168d,
          7.249076905888732E-132d,
          4.248199519963075E-154d,
          2.2237231012906393E-171d,
          1.2495464764335277E-158d
        },
        actualAssetValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getAssetValue(double, int)} with {@code time}, {@code
   * assetIndex}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getAssetValue(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel2.getAssetValue(double, int)"})
  public void testGetAssetValueWithTimeAssetIndex2() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, -0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualAssetValue = monteCarloBlackScholesModel2.getAssetValue(10.0d, 0);

    // Assert
    assertTrue(actualAssetValue instanceof RandomVariableFromDoubleArray);
    assertEquals(2.3064570959865733E-66d, actualAssetValue.getStandardError(), 0.0);
    assertEquals(2.4312192675397577E-65d, actualAssetValue.getMax(), 0.0);
    assertEquals(2.431219429072094E-66d, actualAssetValue.getAverage(), 0.0);
    assertEquals(4.675600113634559E-111d, actualAssetValue.getMin(), 0.0);
    assertEquals(5.319744335626818E-131d, actualAssetValue.getVariance(), 0.0);
    assertEquals(5.910827039585353E-131d, actualAssetValue.getSampleVariance(), 0.0);
    assertEquals(7.293657748775177E-66d, actualAssetValue.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {
          2.4312192675397577E-65d,
          2.921827047299219E-101d,
          5.9280059842E-101d,
          3.058605303029559E-93d,
          1.2428877842031355E-95d,
          4.675600113634559E-111d,
          1.6153233628545755E-72d,
          4.6692339785732095E-99d,
          1.113741541987789E-106d,
          5.0146614094360505E-86d
        },
        actualAssetValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getAssetValue(double, int)} with {@code time}, {@code
   * assetIndex}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getAssetValue(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel2.getAssetValue(double, int)"})
  public void testGetAssetValueWithTimeAssetIndex3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(timeDiscretization, 10, 10.0d, 10.0d, 10.0d);
    monteCarloBlackScholesModel2.setScheme(Scheme.PREDICTOR_USING_LASTREALIZATION);

    // Act
    RandomVariable actualAssetValue = monteCarloBlackScholesModel2.getAssetValue(10.0d, 0);

    // Assert
    assertTrue(actualAssetValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          2.1610243752514466E-165d,
          1.432469881306152E-150d,
          9.859323718075964E-155d,
          5.929410963121059E-142d,
          1.863137692725781E-158d,
          6.674043338267043E-168d,
          7.249076905888732E-132d,
          4.248199519963075E-154d,
          2.2237231012906393E-171d,
          1.2495464764335277E-158d
        },
        actualAssetValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getAssetValue(double, int)} with {@code time}, {@code
   * assetIndex}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getAssetValue(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel2.getAssetValue(double, int)"})
  public void testGetAssetValueWithTimeAssetIndex_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(timeDiscretization, 10, 10.0d, 10.0d, 10.0d);
    monteCarloBlackScholesModel2.setScheme(Scheme.PREDICTOR_USING_EULERSTEP);

    // Act
    RandomVariable actualAssetValue = monteCarloBlackScholesModel2.getAssetValue(10.0d, 0);

    // Assert
    assertTrue(actualAssetValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          2.1610243752514466E-165d,
          1.432469881306152E-150d,
          9.859323718075964E-155d,
          5.929410963121059E-142d,
          1.863137692725781E-158d,
          6.674043338267043E-168d,
          7.249076905888732E-132d,
          4.248199519963075E-154d,
          2.2237231012906393E-171d,
          1.2495464764335277E-158d
        },
        actualAssetValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getAssetValue(double, int)} with {@code time}, {@code
   * assetIndex}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is zero.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getAssetValue(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel2.getAssetValue(double, int)"})
  public void testGetAssetValueWithTimeAssetIndex_thenReturnFiltrationTimeIsZero() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualAssetValue = monteCarloBlackScholesModel2.getAssetValue(10.0d, 0);

    // Assert
    assertTrue(actualAssetValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualAssetValue.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualAssetValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAssetValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAssetValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualAssetValue.getVariance(), 0.0);
    assertEquals(1, actualAssetValue.size());
    assertEquals(10.0d, actualAssetValue.getAverage(), 0.0);
    assertEquals(10.0d, actualAssetValue.getMax(), 0.0);
    assertEquals(10.0d, actualAssetValue.getMin(), 0.0);
    assertTrue(actualAssetValue.isDeterministic());
    assertArrayEquals(new double[] {10.0d}, actualAssetValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getAssetValue(int, int)} with {@code timeIndex},
   * {@code assetIndex}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getAssetValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel2.getAssetValue(int, int)"})
  public void testGetAssetValueWithTimeIndexAssetIndex() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualAssetValue = monteCarloBlackScholesModel2.getAssetValue(1, 0);

    // Assert
    assertTrue(actualAssetValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          1.4749927962500068E-8d,
          9.290463780669235E-13d,
          3.053325392973894E-8d,
          1.4884270190704107E-13d,
          1.7834363601335773E-12d,
          1.2263231356651082E-14d,
          3.3355121976649804E-5d,
          3.8178025052073845E-11d,
          6.734821234855193E-16d,
          4.550391152164565E-9d
        },
        actualAssetValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getAssetValue(int, int)} with {@code timeIndex},
   * {@code assetIndex}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getAssetValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel2.getAssetValue(int, int)"})
  public void testGetAssetValueWithTimeIndexAssetIndex2() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    monteCarloBlackScholesModel2.setScheme(Scheme.PREDICTOR_USING_EULERSTEP);

    // Act
    RandomVariable actualAssetValue = monteCarloBlackScholesModel2.getAssetValue(1, 0);

    // Assert
    assertTrue(actualAssetValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          1.4749927962500068E-8d,
          9.290463780669235E-13d,
          3.053325392973894E-8d,
          1.4884270190704107E-13d,
          1.7834363601335773E-12d,
          1.2263231356651082E-14d,
          3.3355121976649804E-5d,
          3.8178025052073845E-11d,
          6.734821234855193E-16d,
          4.550391152164565E-9d
        },
        actualAssetValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getAssetValue(int, int)} with {@code timeIndex},
   * {@code assetIndex}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getAssetValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel2.getAssetValue(int, int)"})
  public void testGetAssetValueWithTimeIndexAssetIndex3() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    monteCarloBlackScholesModel2.setScheme(Scheme.PREDICTOR_USING_LASTREALIZATION);

    // Act
    RandomVariable actualAssetValue = monteCarloBlackScholesModel2.getAssetValue(1, 0);

    // Assert
    assertTrue(actualAssetValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAssetValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {
          1.4749927962500068E-8d,
          9.290463780669235E-13d,
          3.053325392973894E-8d,
          1.4884270190704107E-13d,
          1.7834363601335773E-12d,
          1.2263231356651082E-14d,
          3.3355121976649804E-5d,
          3.8178025052073845E-11d,
          6.734821234855193E-16d,
          4.550391152164565E-9d
        },
        actualAssetValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getAssetValue(int, int)} with {@code timeIndex},
   * {@code assetIndex}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is zero.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getAssetValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel2.getAssetValue(int, int)"})
  public void testGetAssetValueWithTimeIndexAssetIndex_thenReturnFiltrationTimeIsZero() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualAssetValue = monteCarloBlackScholesModel2.getAssetValue(0, 0);

    // Assert
    assertTrue(actualAssetValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualAssetValue.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualAssetValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAssetValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAssetValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualAssetValue.getVariance(), 0.0);
    assertEquals(1, actualAssetValue.size());
    assertEquals(10.0d, actualAssetValue.getAverage(), 0.0);
    assertEquals(10.0d, actualAssetValue.getMax(), 0.0);
    assertEquals(10.0d, actualAssetValue.getMin(), 0.0);
    assertTrue(actualAssetValue.isDeterministic());
    assertArrayEquals(new double[] {10.0d}, actualAssetValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getMonteCarloWeights(double)} with {@code time}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getMonteCarloWeights(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel2.getMonteCarloWeights(double)"})
  public void testGetMonteCarloWeightsWithTime() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualMonteCarloWeights =
        monteCarloBlackScholesModel2.getMonteCarloWeights(10.0d);

    // Assert
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.1d}, actualMonteCarloWeights.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getMonteCarloWeights(double)} with {@code time}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getMonteCarloWeights(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel2.getMonteCarloWeights(double)"})
  public void testGetMonteCarloWeightsWithTime2() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    monteCarloBlackScholesModel2.setScheme(Scheme.PREDICTOR_USING_EULERSTEP);

    // Act
    RandomVariable actualMonteCarloWeights =
        monteCarloBlackScholesModel2.getMonteCarloWeights(10.0d);

    // Assert
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.1d}, actualMonteCarloWeights.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getMonteCarloWeights(double)} with {@code time}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getMonteCarloWeights(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel2.getMonteCarloWeights(double)"})
  public void testGetMonteCarloWeightsWithTime3() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    monteCarloBlackScholesModel2.setScheme(Scheme.PREDICTOR_USING_LASTREALIZATION);

    // Act
    RandomVariable actualMonteCarloWeights =
        monteCarloBlackScholesModel2.getMonteCarloWeights(10.0d);

    // Assert
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.1d}, actualMonteCarloWeights.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getNumeraire(double)} with {@code time}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel2.getNumeraire(double)"})
  public void testGetNumeraireWithTime() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualNumeraire = monteCarloBlackScholesModel2.getNumeraire(10.0d);

    // Assert
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(0.0d, actualNumeraire.getVariance(), 0.0);
    assertEquals(1, actualNumeraire.getTypePriority());
    assertEquals(1, actualNumeraire.size());
    assertEquals(10.0d, actualNumeraire.getFiltrationTime(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getAverage(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getMax(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getMin(), 0.0);
    assertTrue(actualNumeraire.isDeterministic());
    assertArrayEquals(
        new double[] {2.6881171418161356E43d}, actualNumeraire.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getNumeraire(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getNumeraire(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel2.getNumeraire(int)"})
  public void testGetNumeraireWithTimeIndex_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualNumeraire = monteCarloBlackScholesModel2.getNumeraire(1);

    // Assert
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(0.0d, actualNumeraire.getVariance(), 0.0);
    assertEquals(1, actualNumeraire.getTypePriority());
    assertEquals(1, actualNumeraire.size());
    assertEquals(10.5d, actualNumeraire.getFiltrationTime(), 0.0);
    assertEquals(3.989519570547216E45d, actualNumeraire.getAverage(), 0.0);
    assertEquals(3.989519570547216E45d, actualNumeraire.getMax(), 0.0);
    assertEquals(3.989519570547216E45d, actualNumeraire.getMin(), 0.0);
    assertTrue(actualNumeraire.isDeterministic());
    assertArrayEquals(new double[] {3.989519570547216E45d}, actualNumeraire.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable MonteCarloBlackScholesModel2.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualRandomVariableForConstant =
        monteCarloBlackScholesModel2.getRandomVariableForConstant(10.0d);

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
   * Test {@link MonteCarloBlackScholesModel2#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AssetModelMonteCarloSimulationModel MonteCarloBlackScholesModel2.getCloneWithModifiedSeed(int)"
  })
  public void testGetCloneWithModifiedSeed() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(timeDiscretization, 10, 10.0d, 10.0d, 10.0d);

    // Act
    AssetModelMonteCarloSimulationModel actualCloneWithModifiedSeed =
        monteCarloBlackScholesModel2.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(
        ((MonteCarloBlackScholesModel2) actualCloneWithModifiedSeed).getBrownianMotion()
            instanceof BrownianMotionLazyInit);
    assertTrue(actualCloneWithModifiedSeed instanceof MonteCarloBlackScholesModel2);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedSeed.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualCloneWithModifiedSeed.getNumberOfAssets());
    assertEquals(
        1, ((MonteCarloBlackScholesModel2) actualCloneWithModifiedSeed).getNumberOfComponents());
    assertEquals(
        1, ((MonteCarloBlackScholesModel2) actualCloneWithModifiedSeed).getNumberOfFactors());
    assertEquals(
        1, ((MonteCarloBlackScholesModel2) actualCloneWithModifiedSeed).getInitialValue().length);
    assertEquals(10, actualCloneWithModifiedSeed.getNumberOfPaths());
    assertEquals(
        10.0d, ((MonteCarloBlackScholesModel2) actualCloneWithModifiedSeed).getRiskFreeRate(), 0.0);
    assertEquals(
        10.0d, ((MonteCarloBlackScholesModel2) actualCloneWithModifiedSeed).getVolatility(), 0.0);
    assertEquals(
        Scheme.EULER, ((MonteCarloBlackScholesModel2) actualCloneWithModifiedSeed).getScheme());
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel2#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel2#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AssetModelMonteCarloSimulationModel MonteCarloBlackScholesModel2.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> monteCarloBlackScholesModel2.getCloneWithModifiedData(new HashMap<>()));
  }
}
