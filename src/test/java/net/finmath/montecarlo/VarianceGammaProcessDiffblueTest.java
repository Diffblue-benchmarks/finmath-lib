package net.finmath.montecarlo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class VarianceGammaProcessDiffblueTest {
  /**
   * Test {@link VarianceGammaProcess#VarianceGammaProcess(double, double, double,
   * TimeDiscretization, int, int, int)}.
   *
   * <p>Method under test: {@link VarianceGammaProcess#VarianceGammaProcess(double, double, double,
   * TimeDiscretization, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void VarianceGammaProcess.<init>(double, double, double, TimeDiscretization, int, int, int)"
  })
  public void testNewVarianceGammaProcess() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    VarianceGammaProcess actualVarianceGammaProcess =
        new VarianceGammaProcess(10.0d, 10.0d, 10.0d, timeDiscretization, 3, 10, 42);

    // Assert
    TimeDiscretization timeDiscretization2 = actualVarianceGammaProcess.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertNull(actualVarianceGammaProcess.getBrownianMotion());
    assertNull(actualVarianceGammaProcess.getGammaProcess());
    assertEquals(10, actualVarianceGammaProcess.getNumberOfPaths());
    assertEquals(10.0d, actualVarianceGammaProcess.getNu(), 0.0);
    assertEquals(10.0d, actualVarianceGammaProcess.getSigma(), 0.0);
    assertEquals(10.0d, actualVarianceGammaProcess.getTheta(), 0.0);
    assertEquals(3, actualVarianceGammaProcess.getNumberOfFactors());
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link VarianceGammaProcess#getIncrement(int, int)} with {@code timeIndex}, {@code
   * factor}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link VarianceGammaProcess#getIncrement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable VarianceGammaProcess.getIncrement(int, int)"})
  public void testGetIncrementWithTimeIndexFactor_thenReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualIncrement =
        new VarianceGammaProcess(
                10.0d, 10.0d, 10.0d, new TenorFromArray(10.0d, 10, 0.5d), 10, 1, 42)
            .getIncrement(1, 3);

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
    assertEquals(0.0d, actualIncrement.getAverage(), 0.0);
    assertEquals(0.0d, actualIncrement.getMax(), 0.0);
    assertEquals(0.0d, actualIncrement.getMin(), 0.0);
    assertEquals(0.0d, actualIncrement.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualIncrement.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualIncrement.getStandardError(), 0.0);
    assertEquals(0.0d, actualIncrement.getVariance(), 0.0);
    assertEquals(1, actualIncrement.getTypePriority());
    assertEquals(1, actualIncrement.size());
    assertEquals(11.0d, actualIncrement.getFiltrationTime(), 0.0);
    assertFalse(actualIncrement.isDeterministic());
    assertArrayEquals(new double[] {0.0d}, actualIncrement.getRealizations(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link VarianceGammaProcess#getBrownianMotion()}
   *   <li>{@link VarianceGammaProcess#getGammaProcess()}
   *   <li>{@link VarianceGammaProcess#getNu()}
   *   <li>{@link VarianceGammaProcess#getNumberOfFactors()}
   *   <li>{@link VarianceGammaProcess#getNumberOfPaths()}
   *   <li>{@link VarianceGammaProcess#getSigma()}
   *   <li>{@link VarianceGammaProcess#getTheta()}
   *   <li>{@link VarianceGammaProcess#getTimeDiscretization()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BrownianMotion VarianceGammaProcess.getBrownianMotion()",
    "GammaProcess VarianceGammaProcess.getGammaProcess()",
    "double VarianceGammaProcess.getNu()",
    "int VarianceGammaProcess.getNumberOfFactors()",
    "int VarianceGammaProcess.getNumberOfPaths()",
    "double VarianceGammaProcess.getSigma()",
    "double VarianceGammaProcess.getTheta()",
    "TimeDiscretization VarianceGammaProcess.getTimeDiscretization()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    VarianceGammaProcess varianceGammaProcess =
        new VarianceGammaProcess(10.0d, 10.0d, 10.0d, timeDiscretization, 3, 10, 42);

    // Act
    BrownianMotion actualBrownianMotion = varianceGammaProcess.getBrownianMotion();
    GammaProcess actualGammaProcess = varianceGammaProcess.getGammaProcess();
    double actualNu = varianceGammaProcess.getNu();
    int actualNumberOfFactors = varianceGammaProcess.getNumberOfFactors();
    int actualNumberOfPaths = varianceGammaProcess.getNumberOfPaths();
    double actualSigma = varianceGammaProcess.getSigma();
    double actualTheta = varianceGammaProcess.getTheta();

    // Assert
    assertNull(actualBrownianMotion);
    assertNull(actualGammaProcess);
    assertEquals(10, actualNumberOfPaths);
    assertEquals(10.0d, actualNu, 0.0);
    assertEquals(10.0d, actualSigma, 0.0);
    assertEquals(10.0d, actualTheta, 0.0);
    assertEquals(3, actualNumberOfFactors);
    assertSame(timeDiscretization, varianceGammaProcess.getTimeDiscretization());
  }

  /**
   * Test {@link VarianceGammaProcess#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link VarianceGammaProcess#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable VarianceGammaProcess.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant() {
    // Arrange and Act
    RandomVariable actualRandomVariableForConstant =
        new VarianceGammaProcess(
                10.0d, 10.0d, 10.0d, new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42)
            .getRandomVariableForConstant(10.0d);

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
   * Test {@link VarianceGammaProcess#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link VarianceGammaProcess#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IndependentIncrements VarianceGammaProcess.getCloneWithModifiedSeed(int)"})
  public void testGetCloneWithModifiedSeed() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    IndependentIncrements actualCloneWithModifiedSeed =
        new VarianceGammaProcess(10.0d, 10.0d, 10.0d, timeDiscretization, 3, 10, 42)
            .getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof VarianceGammaProcess);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedSeed.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertNull(((VarianceGammaProcess) actualCloneWithModifiedSeed).getBrownianMotion());
    assertNull(((VarianceGammaProcess) actualCloneWithModifiedSeed).getGammaProcess());
    assertEquals(10, actualCloneWithModifiedSeed.getNumberOfPaths());
    assertEquals(10.0d, ((VarianceGammaProcess) actualCloneWithModifiedSeed).getNu(), 0.0);
    assertEquals(10.0d, ((VarianceGammaProcess) actualCloneWithModifiedSeed).getSigma(), 0.0);
    assertEquals(10.0d, ((VarianceGammaProcess) actualCloneWithModifiedSeed).getTheta(), 0.0);
    assertEquals(3, actualCloneWithModifiedSeed.getNumberOfFactors());
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link VarianceGammaProcess#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * VarianceGammaProcess#getCloneWithModifiedTimeDiscretization(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "IndependentIncrements VarianceGammaProcess.getCloneWithModifiedTimeDiscretization(TimeDiscretization)"
  })
  public void testGetCloneWithModifiedTimeDiscretization() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    VarianceGammaProcess varianceGammaProcess =
        new VarianceGammaProcess(10.0d, 10.0d, 10.0d, timeDiscretization, 3, 10, 42);
    TenorFromArray newTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    IndependentIncrements actualCloneWithModifiedTimeDiscretization =
        varianceGammaProcess.getCloneWithModifiedTimeDiscretization(newTimeDiscretization);

    // Assert
    assertTrue(actualCloneWithModifiedTimeDiscretization instanceof VarianceGammaProcess);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedTimeDiscretization.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertNull(
        ((VarianceGammaProcess) actualCloneWithModifiedTimeDiscretization).getBrownianMotion());
    assertNull(
        ((VarianceGammaProcess) actualCloneWithModifiedTimeDiscretization).getGammaProcess());
    assertEquals(10, actualCloneWithModifiedTimeDiscretization.getNumberOfPaths());
    assertEquals(
        10.0d, ((VarianceGammaProcess) actualCloneWithModifiedTimeDiscretization).getNu(), 0.0);
    assertEquals(
        10.0d, ((VarianceGammaProcess) actualCloneWithModifiedTimeDiscretization).getSigma(), 0.0);
    assertEquals(
        10.0d, ((VarianceGammaProcess) actualCloneWithModifiedTimeDiscretization).getTheta(), 0.0);
    assertEquals(3, actualCloneWithModifiedTimeDiscretization.getNumberOfFactors());
    assertEquals(timeDiscretization, timeDiscretization2);
    assertSame(newTimeDiscretization, timeDiscretization2);
  }
}
