package net.finmath.montecarlo.templatemethoddesign;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
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
import net.finmath.montecarlo.BrownianBridge;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionLazyInit;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.CorrelatedBrownianMotion;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwiseFactory;
import net.finmath.montecarlo.templatemethoddesign.LogNormalProcess.Scheme;
import net.finmath.montecarlo.templatemethoddesign.assetderivativevaluation.MonteCarloBlackScholesModel2;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class LogNormalProcessDiffblueTest {
  /**
   * Test {@link LogNormalProcess#getDrift(int, RandomVariable[], RandomVariable[])} with {@code
   * int}, {@code RandomVariable[]}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LogNormalProcess#getDrift(int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LogNormalProcess.getDrift(int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDriftWithIntRandomVariableRandomVariable() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable[] actualDrift =
        monteCarloBlackScholesModel2.getDrift(
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualDrift[0] instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualDrift.length);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LogNormalProcess.getProcessValue(int)"})
  public void testGetProcessValueWithTimeIndex() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable[] actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1);

    // Assert
    RandomVariable randomVariable = actualProcessValue[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualProcessValue.length);
    assertEquals(1.000487857410993E-5d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(1.0009759528268394E-10d, randomVariable.getVariance(), 0.0);
    assertEquals(1.1121955031409326E-10d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(3.16382040076051E-6d, randomVariable.getStandardError(), 0.0);
    assertEquals(3.3355121976649804E-5d, randomVariable.getMax(), 0.0);
    assertEquals(3.340499660198141E-6d, randomVariable.getAverage(), 0.0);
    assertEquals(6.734821234855193E-16d, randomVariable.getMin(), 0.0);
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
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LogNormalProcess.getProcessValue(int)"})
  public void testGetProcessValueWithTimeIndex2() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1);

    // Assert
    RandomVariable randomVariable = actualProcessValue[0];
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
    assertEquals(1, actualProcessValue.length);
    assertArrayEquals(
        new double[] {
          1.9223180032457833E-9d,
          5.8111611393116146E-8d,
          3.621907248287583E-6d,
          9.097487310472636E-10d,
          2.5445681509156037E-6d,
          1.0003359809086347E-6d,
          5.073261833055087E-15d,
          2.0607840216767027E-8d,
          3.709085326769818E-11d,
          0.0038089925913262213d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LogNormalProcess.getProcessValue(int)"})
  public void testGetProcessValueWithTimeIndex3() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableFloatFactory());
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1);

    // Assert
    RandomVariable randomVariable = actualProcessValue[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0011424563128783864d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(0.0038089909338202632d, randomVariable.getMax(), 0.0);
    assertEquals(1, actualProcessValue.length);
    assertEquals(1.3052064268356777E-6d, randomVariable.getVariance(), 0.0);
    assertEquals(1.450229363150753E-6d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(3.6127640759336574E-4d, randomVariable.getStandardError(), 0.0);
    assertEquals(3.816239330896335E-4d, randomVariable.getAverage(), 0.0);
    assertEquals(5.073264725772966E-15d, randomVariable.getMin(), 0.0);
    assertArrayEquals(
        new double[] {
          1.9223181502048565E-9d,
          5.811161136627301E-8d,
          3.6219056612741427E-6d,
          9.097486462397259E-10d,
          2.5445670355397825E-6d,
          1.000335764306117E-6d,
          5.073264725772966E-15d,
          2.0607840859157926E-8d,
          3.7090856351356E-11d,
          0.0038089909338202632d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LogNormalProcess.getProcessValue(int)"})
  public void testGetProcessValueWithTimeIndex4() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    RandomVariable[] actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1);

    // Assert
    RandomVariable randomVariable = actualProcessValue[0];
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
    assertEquals(1, actualProcessValue.length);
    assertArrayEquals(
        new double[] {
          1.9223180032457833E-9d,
          5.8111611393116146E-8d,
          3.621907248287583E-6d,
          9.097487310472669E-10d,
          2.5445681509155995E-6d,
          1.0003359809086347E-6d,
          5.073261833055123E-15d,
          2.0607840216767027E-8d,
          3.709085326769818E-11d,
          0.0038089925913262143d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LogNormalProcess.getProcessValue(int)"})
  public void testGetProcessValueWithTimeIndex5() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableDifferentiableAADFactory());
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1);

    // Assert
    RandomVariable randomVariable = actualProcessValue[0];
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
    assertEquals(1, actualProcessValue.length);
    assertArrayEquals(
        new double[] {
          1.9223180032457833E-9d,
          5.8111611393116146E-8d,
          3.621907248287583E-6d,
          9.097487310472636E-10d,
          2.5445681509156037E-6d,
          1.0003359809086347E-6d,
          5.073261833055087E-15d,
          2.0607840216767027E-8d,
          3.709085326769818E-11d,
          0.0038089925913262213d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LogNormalProcess.getProcessValue(int)"})
  public void testGetProcessValueWithTimeIndex6() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableFloatFactory());
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    RandomVariable[] actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1);

    // Assert
    RandomVariable randomVariable = actualProcessValue[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0011424568016189928d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(0.003808992563228411d, randomVariable.getMax(), 0.0);
    assertEquals(1, actualProcessValue.length);
    assertEquals(1.3052075435654988E-6d, randomVariable.getVariance(), 0.0);
    assertEquals(1.4502306039616656E-6d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(3.6127656214671584E-4d, randomVariable.getStandardError(), 0.0);
    assertEquals(3.816240962765049E-4d, randomVariable.getAverage(), 0.0);
    assertEquals(5.07326237534991E-15d, randomVariable.getMin(), 0.0);
    assertArrayEquals(
        new double[] {
          1.9223181162455525E-9d,
          5.811162169042773E-8d,
          3.621906964425275E-6d,
          9.097486716546877E-10d,
          2.5445679045012968E-6d,
          1.0003360393752275E-6d,
          5.07326237534991E-15d,
          2.0607843943409032E-8d,
          3.709084086884506E-11d,
          0.003808992563228411d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LogNormalProcess.getProcessValue(int)"})
  public void testGetProcessValueWithTimeIndex7() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableDifferentiableAADPathwiseFactory());
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1);

    // Assert
    RandomVariable randomVariable = actualProcessValue[0];
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
    assertEquals(1, actualProcessValue.length);
    assertArrayEquals(
        new double[] {
          1.9223180032457833E-9d,
          5.8111611393116146E-8d,
          3.621907248287583E-6d,
          9.097487310472636E-10d,
          2.5445681509156037E-6d,
          1.0003359809086347E-6d,
          5.073261833055087E-15d,
          2.0607840216767027E-8d,
          3.709085326769818E-11d,
          0.0038089925913262213d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LogNormalProcess.getProcessValue(int)"})
  public void testGetProcessValueWithTimeIndex8() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableDifferentiableAADFactory());
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    RandomVariable[] actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1);

    // Assert
    RandomVariable randomVariable = actualProcessValue[0];
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
    assertEquals(1, actualProcessValue.length);
    assertArrayEquals(
        new double[] {
          1.9223180032457833E-9d,
          5.8111611393116146E-8d,
          3.621907248287583E-6d,
          9.097487310472669E-10d,
          2.5445681509155995E-6d,
          1.0003359809086347E-6d,
          5.073261833055123E-15d,
          2.0607840216767027E-8d,
          3.709085326769818E-11d,
          0.0038089925913262143d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LogNormalProcess.getProcessValue(int)"})
  public void testGetProcessValueWithTimeIndex9() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 10, 10, 42, new RandomVariableFloatFactory());
    double[][] factorLoadings = new double[][] {new double[] {1.0d, 10.0d, 1.0d, 10.0d}};
    CorrelatedBrownianMotion brownianMotion2 =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    RandomVariable[] actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1);

    // Assert
    RandomVariable randomVariable = actualProcessValue[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualProcessValue.length);
    assertEquals(1.251066638468917E-4d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(1.565167733889916E-8d, randomVariable.getVariance(), 0.0);
    assertEquals(1.7390752598776842E-8d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(3.9562200822122066E-5d, randomVariable.getStandardError(), 0.0);
    assertEquals(4.198845205940376E-4d, randomVariable.getMax(), 0.0);
    assertEquals(4.4897475018055736E-14d, randomVariable.getMin(), 0.0);
    assertEquals(5.8243482387632346E-5d, randomVariable.getAverage(), 0.0);
    assertArrayEquals(
        new double[] {
          1.248913504903544E-7d,
          4.198845205940376E-4d,
          4.4897475018055736E-14d,
          9.544982716789121E-12d,
          1.1341124400996118E-9d,
          1.0305966365557195E-4d,
          1.7483282852597513E-10d,
          7.803157042050642E-9d,
          5.929149167865617E-5d,
          6.51349053765059E-8d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int, int)} with {@code timeIndex}, {@code
   * componentIndex}.
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getProcessValue(int, int)"})
  public void testGetProcessValueWithTimeIndexComponentIndex() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new RuntimeException());
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(timeDiscretization, 10, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> monteCarloBlackScholesModel2.getProcessValue(1, 1));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization).getTime(1);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int, int)} with {@code timeIndex}, {@code
   * componentIndex}.
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getProcessValue(int, int)"})
  public void testGetProcessValueWithTimeIndexComponentIndex2() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(timeDiscretization, 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1, 0);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    assertTrue(actualProcessValue instanceof RandomVariableFromDoubleArray);
    assertEquals(2.1302906006200062E14d, actualProcessValue.getStandardError(), 0.0);
    assertEquals(2.245523458669115E15d, actualProcessValue.getMax(), 0.0);
    assertEquals(2.2455234586691753E14d, actualProcessValue.getAverage(), 0.0);
    assertEquals(3.33084887087965E-33d, actualProcessValue.getMin(), 0.0);
    assertEquals(4.538138043089948E29d, actualProcessValue.getVariance(), 0.0);
    assertEquals(5.042375603433276E29d, actualProcessValue.getSampleVariance(), 0.0);
    assertEquals(6.736570376007325E14d, actualProcessValue.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {
          2.239273465279986d,
          3.6624425376739553E-19d,
          57.973152894432104d,
          1.0163353848119173E-22d,
          6.766594602092315E-18d,
          1.4410972274226902E-27d,
          2.245523458669115E15d,
          6.036660874114392E-12d,
          3.33084887087965E-33d,
          0.011641345757371385d
        },
        actualProcessValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int, int)} with {@code timeIndex}, {@code
   * componentIndex}.
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getProcessValue(int, int)"})
  public void testGetProcessValueWithTimeIndexComponentIndex3() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);

    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(Mockito.<RandomVariable>any())).thenThrow(new RuntimeException());
    when(scalar.getTypePriority()).thenReturn(11);

    BrownianBridge brownianMotion = mock(BrownianBridge.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(scalar);

    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(timeDiscretization, 10, 10.0d, 10.0d, 10.0d);
    monteCarloBlackScholesModel2.setBrownianMotion(brownianMotion);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> monteCarloBlackScholesModel2.getProcessValue(1, 1));
    verify(brownianMotion).getBrownianIncrement(0, 0);
    verify(scalar, atLeast(1)).getTypePriority();
    verify(scalar).mult(isA(RandomVariable.class));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int, int)} with {@code timeIndex}, {@code
   * componentIndex}.
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getProcessValue(int, int)"})
  public void testGetProcessValueWithTimeIndexComponentIndex4() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);

    Scalar scalar = mock(Scalar.class);
    when(scalar.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(scalar2.getTypePriority()).thenReturn(11);

    BrownianBridge brownianMotion = mock(BrownianBridge.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(scalar2);

    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(timeDiscretization, 10, 10.0d, 10.0d, 10.0d);
    monteCarloBlackScholesModel2.setBrownianMotion(brownianMotion);

    // Act
    RandomVariable actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1, 0);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), eq(0));
    verify(scalar, atLeast(1)).add(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).getTypePriority();
    verify(scalar2, atLeast(1)).mult(isA(RandomVariable.class));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    assertTrue(actualProcessValue instanceof RandomVariableFromDoubleArray);
    assertEquals(220264.65794806718d, actualProcessValue.getAverage(), 0.0);
    assertEquals(220264.65794806718d, actualProcessValue.getMax(), 0.0);
    assertEquals(220264.65794806718d, actualProcessValue.getMin(), 0.0);
    assertArrayEquals(
        new double[] {
          220264.65794806718d,
          220264.65794806718d,
          220264.65794806718d,
          220264.65794806718d,
          220264.65794806718d,
          220264.65794806718d,
          220264.65794806718d,
          220264.65794806718d,
          220264.65794806718d,
          220264.65794806718d
        },
        actualProcessValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int, int)} with {@code timeIndex}, {@code
   * componentIndex}.
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getProcessValue(int, int)"})
  public void testGetProcessValueWithTimeIndexComponentIndex5() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);

    Scalar scalar = mock(Scalar.class);
    when(scalar.add(Mockito.<RandomVariable>any())).thenReturn(Scalar.of(1.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.mult(Mockito.<RandomVariable>any())).thenReturn(scalar);
    when(scalar2.getTypePriority()).thenReturn(11);

    BrownianBridge brownianMotion = mock(BrownianBridge.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(scalar2);

    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(timeDiscretization, 10, 10.0d, 10.0d, 10.0d);
    monteCarloBlackScholesModel2.setBrownianMotion(brownianMotion);

    // Act
    RandomVariable actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1, 0);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), eq(0));
    verify(scalar, atLeast(1)).add(Mockito.<RandomVariable>any());
    verify(scalar2, atLeast(1)).getTypePriority();
    verify(scalar2, atLeast(1)).mult(isA(RandomVariable.class));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    assertTrue(actualProcessValue instanceof RandomVariableFromDoubleArray);
    assertEquals(27.18281828459045d, actualProcessValue.getAverage(), 0.0);
    assertEquals(27.18281828459045d, actualProcessValue.getMax(), 0.0);
    assertEquals(27.18281828459045d, actualProcessValue.getMin(), 0.0);
    assertArrayEquals(
        new double[] {
          27.18281828459045d,
          27.18281828459045d,
          27.18281828459045d,
          27.18281828459045d,
          27.18281828459045d,
          27.18281828459045d,
          27.18281828459045d,
          27.18281828459045d,
          27.18281828459045d,
          27.18281828459045d
        },
        actualProcessValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int, int)} with {@code timeIndex}, {@code
   * componentIndex}.
   *
   * <ul>
   *   <li>Then calls {@link Scalar#doubleValue()}.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getProcessValue(int, int)"})
  public void testGetProcessValueWithTimeIndexComponentIndex_thenCallsDoubleValue() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);

    Scalar scalar = mock(Scalar.class);
    when(scalar.doubleValue()).thenThrow(new RuntimeException());
    when(scalar.isDeterministic()).thenReturn(true);
    when(scalar.getFiltrationTime()).thenReturn(10.0d);
    when(scalar.getTypePriority()).thenReturn(1);

    BrownianBridge brownianMotion = mock(BrownianBridge.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt())).thenReturn(scalar);

    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(timeDiscretization, 10, 10.0d, 10.0d, 10.0d);
    monteCarloBlackScholesModel2.setBrownianMotion(brownianMotion);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> monteCarloBlackScholesModel2.getProcessValue(1, 1));
    verify(brownianMotion).getBrownianIncrement(0, 0);
    verify(scalar).doubleValue();
    verify(scalar).getFiltrationTime();
    verify(scalar).getTypePriority();
    verify(scalar).isDeterministic();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int, int)} with {@code timeIndex}, {@code
   * componentIndex}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is zero.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getProcessValue(int, int)"})
  public void testGetProcessValueWithTimeIndexComponentIndex_thenReturnFiltrationTimeIsZero() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(mock(TenorFromArray.class), 10, 10.0d, 10.0d, 10.0d);
    monteCarloBlackScholesModel2.setBrownianMotion(mock(BrownianBridge.class));

    // Act
    RandomVariable actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(0, 0);

    // Assert
    assertTrue(actualProcessValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualProcessValue.getFiltrationTime(), 0.0);
    assertEquals(1, actualProcessValue.size());
    assertEquals(10.0d, actualProcessValue.getAverage(), 0.0);
    assertEquals(10.0d, actualProcessValue.getMax(), 0.0);
    assertEquals(10.0d, actualProcessValue.getMin(), 0.0);
    assertTrue(actualProcessValue.isDeterministic());
    assertArrayEquals(new double[] {10.0d}, actualProcessValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int, int)} with {@code timeIndex}, {@code
   * componentIndex}.
   *
   * <ul>
   *   <li>Then return Min is {@code 0.3583005839949591}.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getProcessValue(int, int)"})
  public void testGetProcessValueWithTimeIndexComponentIndex_thenReturnMinIs03583005839949591() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(1.0d, 10, 0.5d), 10, 1.0d, 1.0d, 1.0d, 42);

    // Act
    RandomVariable actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1, 0);

    // Assert
    assertTrue(actualProcessValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.3583005839949591d, actualProcessValue.getMin(), 0.0);
    assertEquals(0.391799225621071d, actualProcessValue.getStandardError(), 0.0);
    assertEquals(1.2389779384527835d, actualProcessValue.getStandardDeviation(), 0.0);
    assertEquals(1.5350663319727096d, actualProcessValue.getVariance(), 0.0);
    assertEquals(1.5d, actualProcessValue.getFiltrationTime(), 0.0);
    assertEquals(1.6718260363027262d, actualProcessValue.getAverage(), 0.0);
    assertEquals(1.705629257747455d, actualProcessValue.getSampleVariance(), 0.0);
    assertEquals(4.9418797920470245d, actualProcessValue.getMax(), 0.0);
    assertArrayEquals(
        new double[] {
          1.1414895043292217d,
          0.3583005839949591d,
          4.9418797920470245d,
          1.673749765999195d,
          0.8068129816241415d,
          1.0202255724079528d,
          2.6618929572303927d,
          1.7212490069662487d,
          1.343135144930219d,
          1.0495250534979095d
        },
        actualProcessValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return first element Max is {@code 0.0020442804048399063}.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LogNormalProcess.getProcessValue(int)"})
  public void testGetProcessValueWithTimeIndex_thenReturnFirstElementMaxIs00020442804048399063() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    monteCarloBlackScholesModel2.setBrownianMotion(brownianMotion2);

    // Act
    RandomVariable[] actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1);

    // Assert
    RandomVariable randomVariable = actualProcessValue[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0020442804048399063d, randomVariable.getMax(), 0.0);
    assertEquals(1, actualProcessValue.length);
    assertEquals(1.1077207255945935E-14d, randomVariable.getMin(), 0.0);
    assertEquals(1.9387798927502224E-4d, randomVariable.getStandardError(), 0.0);
    assertEquals(2.049944496029655E-4d, randomVariable.getAverage(), 0.0);
    assertEquals(3.7588674725325646E-7d, randomVariable.getVariance(), 0.0);
    assertEquals(4.1765194139250716E-7d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(6.130960342827675E-4d, randomVariable.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {
          2.1711811916925005E-9d,
          5.510139242501634E-8d,
          2.778050961465498E-6d,
          1.0677394365749115E-9d,
          1.9873987564748627E-6d,
          8.196418171279989E-7d,
          1.1077207255945935E-14d,
          2.060802986755475E-8d,
          5.130068238808409E-11d,
          0.0020442804048399063d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getProcessValue(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return first element SampleVariance is zero.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalProcess#getProcessValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LogNormalProcess.getProcessValue(int)"})
  public void testGetProcessValueWithTimeIndex_thenReturnFirstElementSampleVarianceIsZero() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {}};
    CorrelatedBrownianMotion brownianMotion2 =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    RandomVariable[] actualProcessValue = monteCarloBlackScholesModel2.getProcessValue(1);

    // Assert
    RandomVariable randomVariable = actualProcessValue[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(0.0d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(0.0d, randomVariable.getStandardError(), 0.0);
    assertEquals(0.0d, randomVariable.getVariance(), 0.0);
    assertEquals(1, actualProcessValue.length);
    assertEquals(2.061153622438558E-8d, randomVariable.getAverage(), 0.0);
    assertEquals(2.061153622438558E-8d, randomVariable.getMax(), 0.0);
    assertEquals(2.061153622438558E-8d, randomVariable.getMin(), 0.0);
    assertArrayEquals(
        new double[] {
          2.061153622438558E-8d,
          2.061153622438558E-8d,
          2.061153622438558E-8d,
          2.061153622438558E-8d,
          2.061153622438558E-8d,
          2.061153622438558E-8d,
          2.061153622438558E-8d,
          2.061153622438558E-8d,
          2.061153622438558E-8d,
          2.061153622438558E-8d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LogNormalProcess#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link LogNormalProcess#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloBlackScholesModel2.getMonteCarloWeights(1);

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
   * Test {@link LogNormalProcess#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link LogNormalProcess#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights2() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloBlackScholesModel2.getMonteCarloWeights(1);

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
   * Test {@link LogNormalProcess#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link LogNormalProcess#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights3() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableFloatFactory());
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloBlackScholesModel2.getMonteCarloWeights(1);

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
   * Test {@link LogNormalProcess#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link LogNormalProcess#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights4() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloBlackScholesModel2.getMonteCarloWeights(1);

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
   * Test {@link LogNormalProcess#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link LogNormalProcess#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights5() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    monteCarloBlackScholesModel2.setBrownianMotion(brownianMotion2);

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloBlackScholesModel2.getMonteCarloWeights(1);

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
   * Test {@link LogNormalProcess#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link LogNormalProcess#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights6() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableDifferentiableAADFactory());
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloBlackScholesModel2.getMonteCarloWeights(1);

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
   * Test {@link LogNormalProcess#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link LogNormalProcess#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights7() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableFloatFactory());
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloBlackScholesModel2.getMonteCarloWeights(1);

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
   * Test {@link LogNormalProcess#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link LogNormalProcess#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights8() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableDifferentiableAADPathwiseFactory());
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloBlackScholesModel2.getMonteCarloWeights(1);

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
   * Test {@link LogNormalProcess#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link LogNormalProcess#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights9() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableDifferentiableAADFactory());
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloBlackScholesModel2.getMonteCarloWeights(1);

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
   * Test {@link LogNormalProcess#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link LogNormalProcess#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights10() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 10, 10, 42, new RandomVariableFloatFactory());
    double[][] factorLoadings = new double[][] {new double[] {1.0d, 10.0d, 1.0d, 10.0d}};
    CorrelatedBrownianMotion brownianMotion2 =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloBlackScholesModel2.getMonteCarloWeights(1);

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
   * Test {@link LogNormalProcess#getMonteCarloWeights(int)}.
   *
   * <ul>
   *   <li>Given empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalProcess#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LogNormalProcess.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeights_givenEmptyArrayOfDouble() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {}};
    CorrelatedBrownianMotion brownianMotion2 =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);
    monteCarloBlackScholesModel2.setBrownianMotion(
        new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloBlackScholesModel2.getMonteCarloWeights(1);

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
   * Test {@link LogNormalProcess#getNumberOfComponents()}.
   *
   * <p>Method under test: {@link LogNormalProcess#getNumberOfComponents()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LogNormalProcess.getNumberOfComponents()"})
  public void testGetNumberOfComponents() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(1, monteCarloBlackScholesModel2.getNumberOfComponents());
  }

  /**
   * Test {@link LogNormalProcess#getNumberOfPaths()}.
   *
   * <p>Method under test: {@link LogNormalProcess#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LogNormalProcess.getNumberOfPaths()"})
  public void testGetNumberOfPaths() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10, monteCarloBlackScholesModel2.getNumberOfPaths());
  }

  /**
   * Test {@link LogNormalProcess#getNumberOfFactors()}.
   *
   * <p>Method under test: {@link LogNormalProcess#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LogNormalProcess.getNumberOfFactors()"})
  public void testGetNumberOfFactors() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(1, monteCarloBlackScholesModel2.getNumberOfFactors());
  }

  /**
   * Test {@link LogNormalProcess#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link LogNormalProcess#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.TimeDiscretization LogNormalProcess.getTimeDiscretization()"
  })
  public void testGetTimeDiscretization() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(timeDiscretization, 10, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertSame(timeDiscretization, monteCarloBlackScholesModel2.getTimeDiscretization());
  }

  /**
   * Test {@link LogNormalProcess#getTime(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalProcess#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LogNormalProcess.getTime(int)"})
  public void testGetTime_thenReturn105() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10.5d, monteCarloBlackScholesModel2.getTime(1), 0.0);
  }

  /**
   * Test {@link LogNormalProcess#getTimeIndex(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalProcess#getTimeIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LogNormalProcess.getTimeIndex(double)"})
  public void testGetTimeIndex_thenReturnZero() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(0, monteCarloBlackScholesModel2.getTimeIndex(10.0d));
  }

  /**
   * Test {@link LogNormalProcess#getBrownianMotion()}.
   *
   * <p>Method under test: {@link LogNormalProcess#getBrownianMotion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BrownianMotion LogNormalProcess.getBrownianMotion()"})
  public void testGetBrownianMotion() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(timeDiscretization, 10, 10.0d, 10.0d, 10.0d);

    // Act
    BrownianMotion actualBrownianMotion = monteCarloBlackScholesModel2.getBrownianMotion();

    // Assert
    assertTrue(actualBrownianMotion instanceof BrownianMotionLazyInit);
    assertEquals(1, actualBrownianMotion.getNumberOfFactors());
    assertEquals(10, actualBrownianMotion.getNumberOfPaths());
    assertEquals(3141, ((BrownianMotionLazyInit) actualBrownianMotion).getSeed());
    assertSame(timeDiscretization, actualBrownianMotion.getTimeDiscretization());
  }

  /**
   * Test {@link LogNormalProcess#getScheme()}.
   *
   * <p>Method under test: {@link LogNormalProcess#getScheme()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Scheme LogNormalProcess.getScheme()"})
  public void testGetScheme() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(Scheme.EULER, monteCarloBlackScholesModel2.getScheme());
  }

  /**
   * Test {@link LogNormalProcess#setBrownianMotion(BrownianMotion)}.
   *
   * <p>Method under test: {@link LogNormalProcess#setBrownianMotion(BrownianMotion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LogNormalProcess.setBrownianMotion(BrownianMotion)"})
  public void testSetBrownianMotion() {
    // Arrange
    MonteCarloBlackScholesModel2 monteCarloBlackScholesModel2 =
        new MonteCarloBlackScholesModel2(
            new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    // Act
    monteCarloBlackScholesModel2.setBrownianMotion(brownianMotion2);

    // Assert
    RandomVariable[] initialValue = monteCarloBlackScholesModel2.getInitialValue();
    RandomVariable randomVariable = initialValue[0];
    RandomVariable expectationResult = randomVariable.expectation();
    RandomVariable expResult = expectationResult.exp();
    assertTrue(expResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(expResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(expResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(expectationResult instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, initialValue.length);
    assertSame(brownianMotion2, monteCarloBlackScholesModel2.getBrownianMotion());
  }
}
