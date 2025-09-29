package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwiseFactory;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CorrelatedBrownianMotionDiffblueTest {
  /**
   * Test {@link CorrelatedBrownianMotion#CorrelatedBrownianMotion(BrownianMotion, double[][])}.
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#CorrelatedBrownianMotion(BrownianMotion,
   * double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CorrelatedBrownianMotion.<init>(BrownianMotion, double[][])"})
  public void testNewCorrelatedBrownianMotion() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    // Act
    CorrelatedBrownianMotion actualCorrelatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);

    // Assert
    assertEquals(1, actualCorrelatedBrownianMotion.getNumberOfFactors());
    assertEquals(10, actualCorrelatedBrownianMotion.getNumberOfPaths());
    assertSame(timeDiscretization, actualCorrelatedBrownianMotion.getTimeDiscretization());
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getBrownianIncrement(int, int)} with {@code timeIndex},
   * {@code factor}.
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getBrownianIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable CorrelatedBrownianMotion.getBrownianIncrement(int, int)"})
  public void testGetBrownianIncrementWithTimeIndexFactor() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 4, 1, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);

    // Act
    RandomVariable actualBrownianIncrement = correlatedBrownianMotion.getBrownianIncrement(1, 0);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    assertTrue(actualBrownianIncrement instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualBrownianIncrement.getRealizations(), 0.0);
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getBrownianIncrement(int, int)} with {@code timeIndex},
   * {@code factor}.
   *
   * <ul>
   *   <li>Then return Deterministic.
   * </ul>
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getBrownianIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable CorrelatedBrownianMotion.getBrownianIncrement(int, int)"})
  public void testGetBrownianIncrementWithTimeIndexFactor_thenReturnDeterministic() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);

    // Act
    RandomVariable actualBrownianIncrement = correlatedBrownianMotion.getBrownianIncrement(1, 0);

    // Assert
    assertTrue(actualBrownianIncrement instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBrownianIncrement.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualBrownianIncrement.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualBrownianIncrement.getRealizations(), 0.0);
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getBrownianIncrement(int, int)} with {@code timeIndex},
   * {@code factor}.
   *
   * <ul>
   *   <li>Then return Min is {@code -56.04669004302242}.
   * </ul>
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getBrownianIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable CorrelatedBrownianMotion.getBrownianIncrement(int, int)"})
  public void testGetBrownianIncrementWithTimeIndexFactor_thenReturnMinIs5604669004302242() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 0.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);

    // Act
    RandomVariable actualBrownianIncrement = correlatedBrownianMotion.getBrownianIncrement(1, 0);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    assertTrue(actualBrownianIncrement instanceof RandomVariableFromDoubleArray);
    assertEquals(-56.04669004302242d, actualBrownianIncrement.getMin(), 0.0);
    assertEquals(-6.217248937900876E-16d, actualBrownianIncrement.getAverage(), 0.0);
    assertEquals(10.741605434256384d, actualBrownianIncrement.getStandardError(), 0.0);
    assertEquals(1153.8208730524625d, actualBrownianIncrement.getVariance(), 0.0);
    assertEquals(1282.0231922805137d, actualBrownianIncrement.getSampleVariance(), 0.0);
    assertEquals(33.967938899092225d, actualBrownianIncrement.getStandardDeviation(), 0.0);
    assertEquals(56.10997461636715d, actualBrownianIncrement.getMax(), 0.0);
    assertArrayEquals(
        new double[] {
          23.852139611170646d,
          -10.036024889754298d,
          56.10997461636715d,
          2.6277113943191353d,
          -16.069975146022244d,
          -56.04669004302242d,
          45.77498372153984d,
          -46.280832525753084d,
          -6.153766330882792d,
          6.222479592038067d
        },
        actualBrownianIncrement.getRealizations(),
        0.0);
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getBrownianIncrement(int, int)} with {@code timeIndex},
   * {@code factor}.
   *
   * <ul>
   *   <li>Then return Min is {@code -75.57000557555476}.
   * </ul>
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getBrownianIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable CorrelatedBrownianMotion.getBrownianIncrement(int, int)"})
  public void testGetBrownianIncrementWithTimeIndexFactor_thenReturnMinIs7557000557555476() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 4, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);

    // Act
    RandomVariable actualBrownianIncrement = correlatedBrownianMotion.getBrownianIncrement(1, 0);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    assertTrue(actualBrownianIncrement instanceof RandomVariableFromDoubleArray);
    assertEquals(-75.57000557555476d, actualBrownianIncrement.getMin(), 0.0);
    assertEquals(13.74231285198914d, actualBrownianIncrement.getStandardError(), 0.0);
    assertEquals(1888.5116252194591d, actualBrownianIncrement.getVariance(), 0.0);
    assertEquals(2098.3462502438433d, actualBrownianIncrement.getSampleVariance(), 0.0);
    assertEquals(43.45700893089007d, actualBrownianIncrement.getStandardDeviation(), 0.0);
    assertEquals(60.444537873606414d, actualBrownianIncrement.getMax(), 0.0);
    assertEquals(7.105427357601002E-16d, actualBrownianIncrement.getAverage(), 0.0);
    assertArrayEquals(
        new double[] {
          -75.57000557555476d,
          1.4474919037366174d,
          13.658189873714512d,
          41.119632664868895d,
          -46.87034099118746d,
          -52.06806528218303d,
          45.408855382585415d,
          60.444537873606414d,
          -11.02112149999321d,
          23.45082565040661d
        },
        actualBrownianIncrement.getRealizations(),
        0.0);
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization CorrelatedBrownianMotion.getTimeDiscretization()"})
  public void testGetTimeDiscretization() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion brownianMotion2 =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);
    double[][] factorLoadings2 = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion2), factorLoadings2);

    // Act and Assert
    assertSame(timeDiscretization, correlatedBrownianMotion.getTimeDiscretization());
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getNumberOfFactors()}.
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int CorrelatedBrownianMotion.getNumberOfFactors()"})
  public void testGetNumberOfFactors() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);

    // Act and Assert
    assertEquals(1, correlatedBrownianMotion.getNumberOfFactors());
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getNumberOfPaths()}.
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int CorrelatedBrownianMotion.getNumberOfPaths()"})
  public void testGetNumberOfPaths() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);

    // Act and Assert
    assertEquals(10, correlatedBrownianMotion.getNumberOfPaths());
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getNumberOfPaths()}.
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int CorrelatedBrownianMotion.getNumberOfPaths()"})
  public void testGetNumberOfPaths2() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion brownianMotion2 =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);
    double[][] factorLoadings2 = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion2), factorLoadings2);

    // Act and Assert
    assertEquals(10, correlatedBrownianMotion.getNumberOfPaths());
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CorrelatedBrownianMotion.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);

    // Act
    RandomVariable actualRandomVariableForConstant =
        correlatedBrownianMotion.getRandomVariableForConstant(10.0d);

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
    RandomVariable actualExpectationResult = actualRandomVariableForConstant.expectation();
    assertSame(actualRandomVariableForConstant, actualExpectationResult);
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CorrelatedBrownianMotion.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant2() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion brownianMotion2 =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);
    double[][] factorLoadings2 = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion2), factorLoadings2);

    // Act
    RandomVariable actualRandomVariableForConstant =
        correlatedBrownianMotion.getRandomVariableForConstant(10.0d);

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
    RandomVariable actualExpectationResult = actualRandomVariableForConstant.expectation();
    assertSame(actualRandomVariableForConstant, actualExpectationResult);
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CorrelatedBrownianMotion.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableDifferentiableAADPathwiseFactory());
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);

    // Act
    RandomVariable actualRandomVariableForConstant =
        correlatedBrownianMotion.getRandomVariableForConstant(10.0d);

    // Assert
    RandomVariable randomVariable =
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
            .getRandomVariable();
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableForConstant.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableForConstant instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
                .getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableForConstant.expectation()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableForConstant.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualRandomVariableForConstant.variance()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(0.0d, actualRandomVariableForConstant.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAADPathwise) actualRandomVariableForConstant)
            .getGradient()
            .size());
    assertEquals(1, actualRandomVariableForConstant.size());
    assertEquals(10.0d, actualRandomVariableForConstant.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMin(), 0.0);
    assertEquals(3, actualRandomVariableForConstant.getTypePriority());
    assertTrue(actualRandomVariableForConstant.isDeterministic());
    assertSame(randomVariable, actualRandomVariableForConstant.getValues());
    assertArrayEquals(new double[] {10.0d}, actualRandomVariableForConstant.getRealizations(), 0.0);
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BrownianMotion CorrelatedBrownianMotion.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion brownianMotion2 =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);
    double[][] factorLoadings2 = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion2), factorLoadings2);

    // Act
    BrownianMotion actualCloneWithModifiedSeed =
        correlatedBrownianMotion.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof CorrelatedBrownianMotion);
    assertSame(timeDiscretization, actualCloneWithModifiedSeed.getTimeDiscretization());
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BrownianMotion CorrelatedBrownianMotion.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion brownianMotion2 =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion2);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion3 = new BrownianBridge(generator, start, end);
    double[][] factorLoadings2 = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion3), factorLoadings2);

    // Act
    BrownianMotion actualCloneWithModifiedSeed =
        correlatedBrownianMotion.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof CorrelatedBrownianMotion);
    assertSame(timeDiscretization, actualCloneWithModifiedSeed.getTimeDiscretization());
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BrownianMotion CorrelatedBrownianMotion.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion brownianMotion2 =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);
    double[][] factorLoadings2 = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion brownianMotion3 =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion2), factorLoadings2);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion3);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion4 = new BrownianBridge(generator, start, end);
    double[][] factorLoadings3 = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion4), factorLoadings3);

    // Act
    BrownianMotion actualCloneWithModifiedSeed =
        correlatedBrownianMotion.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof CorrelatedBrownianMotion);
    assertSame(timeDiscretization, actualCloneWithModifiedSeed.getTimeDiscretization());
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getCloneWithModifiedSeed(int)}.
   *
   * <ul>
   *   <li>Then return NumberOfFactors is one.
   * </ul>
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BrownianMotion CorrelatedBrownianMotion.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed_thenReturnNumberOfFactorsIsOne() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);

    // Act
    BrownianMotion actualCloneWithModifiedSeed =
        correlatedBrownianMotion.getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof CorrelatedBrownianMotion);
    assertEquals(1, actualCloneWithModifiedSeed.getNumberOfFactors());
    assertEquals(10, actualCloneWithModifiedSeed.getNumberOfPaths());
  }

  /**
   * Test {@link
   * CorrelatedBrownianMotion#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * CorrelatedBrownianMotion#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion CorrelatedBrownianMotion.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
  })
  public void testGetCloneWithModifiedTimeDiscretization() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);
    TenorFromArray newTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    BrownianMotion actualCloneWithModifiedTimeDiscretization =
        correlatedBrownianMotion.getCloneWithModifiedTimeDiscretization(newTimeDiscretization);

    // Assert
    assertTrue(actualCloneWithModifiedTimeDiscretization instanceof CorrelatedBrownianMotion);
    assertEquals(1, actualCloneWithModifiedTimeDiscretization.getNumberOfFactors());
    assertEquals(10, actualCloneWithModifiedTimeDiscretization.getNumberOfPaths());
    assertSame(
        newTimeDiscretization, actualCloneWithModifiedTimeDiscretization.getTimeDiscretization());
  }

  /**
   * Test {@link
   * CorrelatedBrownianMotion#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * CorrelatedBrownianMotion#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion CorrelatedBrownianMotion.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
  })
  public void testGetCloneWithModifiedTimeDiscretization2() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion brownianMotion2 =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);
    double[][] factorLoadings2 = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion2), factorLoadings2);
    TenorFromArray newTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    BrownianMotion actualCloneWithModifiedTimeDiscretization =
        correlatedBrownianMotion.getCloneWithModifiedTimeDiscretization(newTimeDiscretization);

    // Assert
    assertTrue(actualCloneWithModifiedTimeDiscretization instanceof CorrelatedBrownianMotion);
    assertEquals(1, actualCloneWithModifiedTimeDiscretization.getNumberOfFactors());
    assertEquals(10, actualCloneWithModifiedTimeDiscretization.getNumberOfPaths());
    assertSame(
        newTimeDiscretization, actualCloneWithModifiedTimeDiscretization.getTimeDiscretization());
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getIncrement(int, int)} with {@code timeIndex}, {@code
   * factor}.
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable CorrelatedBrownianMotion.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 4, 1, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);

    // Act
    RandomVariable actualIncrement = correlatedBrownianMotion.getIncrement(1, 0);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
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
    assertArrayEquals(new double[] {0.0d}, actualIncrement.getRealizations(), 0.0);
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getIncrement(int, int)} with {@code timeIndex}, {@code
   * factor}.
   *
   * <ul>
   *   <li>Then return Deterministic.
   * </ul>
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable CorrelatedBrownianMotion.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor_thenReturnDeterministic() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);

    // Act
    RandomVariable actualIncrement = correlatedBrownianMotion.getIncrement(1, 0);

    // Assert
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
    assertTrue(actualIncrement.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualIncrement.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualIncrement.getRealizations(), 0.0);
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getIncrement(int, int)} with {@code timeIndex}, {@code
   * factor}.
   *
   * <ul>
   *   <li>Then return Min is {@code -56.04669004302242}.
   * </ul>
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable CorrelatedBrownianMotion.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor_thenReturnMinIs5604669004302242() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 0.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);

    // Act
    RandomVariable actualIncrement = correlatedBrownianMotion.getIncrement(1, 0);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    assertTrue(actualIncrement instanceof RandomVariableFromDoubleArray);
    assertEquals(-56.04669004302242d, actualIncrement.getMin(), 0.0);
    assertEquals(-6.217248937900876E-16d, actualIncrement.getAverage(), 0.0);
    assertEquals(10.741605434256384d, actualIncrement.getStandardError(), 0.0);
    assertEquals(1153.8208730524625d, actualIncrement.getVariance(), 0.0);
    assertEquals(1282.0231922805137d, actualIncrement.getSampleVariance(), 0.0);
    assertEquals(33.967938899092225d, actualIncrement.getStandardDeviation(), 0.0);
    assertEquals(56.10997461636715d, actualIncrement.getMax(), 0.0);
    assertArrayEquals(
        new double[] {
          23.852139611170646d,
          -10.036024889754298d,
          56.10997461636715d,
          2.6277113943191353d,
          -16.069975146022244d,
          -56.04669004302242d,
          45.77498372153984d,
          -46.280832525753084d,
          -6.153766330882792d,
          6.222479592038067d
        },
        actualIncrement.getRealizations(),
        0.0);
  }

  /**
   * Test {@link CorrelatedBrownianMotion#getIncrement(int, int)} with {@code timeIndex}, {@code
   * factor}.
   *
   * <ul>
   *   <li>Then return Min is {@code -75.57000557555476}.
   * </ul>
   *
   * <p>Method under test: {@link CorrelatedBrownianMotion#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable CorrelatedBrownianMotion.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor_thenReturnMinIs7557000557555476() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 4, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    CorrelatedBrownianMotion correlatedBrownianMotion =
        new CorrelatedBrownianMotion(
            new BrownianMotionWithControlVariate(brownianMotion), factorLoadings);

    // Act
    RandomVariable actualIncrement = correlatedBrownianMotion.getIncrement(1, 0);

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    assertTrue(actualIncrement instanceof RandomVariableFromDoubleArray);
    assertEquals(-75.57000557555476d, actualIncrement.getMin(), 0.0);
    assertEquals(13.74231285198914d, actualIncrement.getStandardError(), 0.0);
    assertEquals(1888.5116252194591d, actualIncrement.getVariance(), 0.0);
    assertEquals(2098.3462502438433d, actualIncrement.getSampleVariance(), 0.0);
    assertEquals(43.45700893089007d, actualIncrement.getStandardDeviation(), 0.0);
    assertEquals(60.444537873606414d, actualIncrement.getMax(), 0.0);
    assertEquals(7.105427357601002E-16d, actualIncrement.getAverage(), 0.0);
    assertArrayEquals(
        new double[] {
          -75.57000557555476d,
          1.4474919037366174d,
          13.658189873714512d,
          41.119632664868895d,
          -46.87034099118746d,
          -52.06806528218303d,
          45.408855382585415d,
          60.444537873606414d,
          -11.02112149999321d,
          23.45082565040661d
        },
        actualIncrement.getRealizations(),
        0.0);
  }
}
