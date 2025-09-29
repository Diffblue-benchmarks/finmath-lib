package net.finmath.montecarlo.automaticdifferentiation.backward.alternative;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomVariableAADDiffblueTest {
  /**
   * Test {@link RandomVariableAAD#constructNewAADRandomVariable(RandomVariable)} with {@code
   * randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableAAD#constructNewAADRandomVariable(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableAAD RandomVariableAAD.constructNewAADRandomVariable(RandomVariable)"
  })
  public void testConstructNewAADRandomVariableWithRandomVariable() {
    // Arrange and Act
    RandomVariableAAD actualConstructNewAADRandomVariableResult =
        RandomVariableAAD.constructNewAADRandomVariable(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualConstructNewAADRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConstructNewAADRandomVariableResult.getAverageAsRandomVariableAAD()
            instanceof RandomVariableAAD);
    assertTrue(
        actualConstructNewAADRandomVariableResult.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableAAD);
    assertTrue(
        actualConstructNewAADRandomVariableResult.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableAAD);
    assertTrue(
        actualConstructNewAADRandomVariableResult.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableAAD);
    assertTrue(
        actualConstructNewAADRandomVariableResult.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableAAD);
    assertTrue(
        actualConstructNewAADRandomVariableResult.expectation() instanceof RandomVariableAAD);
    assertTrue(actualConstructNewAADRandomVariableResult.expm1() instanceof RandomVariableAAD);
    assertTrue(actualConstructNewAADRandomVariableResult.variance() instanceof RandomVariableAAD);
    assertArrayEquals(
        new double[] {10.0d}, actualConstructNewAADRandomVariableResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableAAD#constructNewAADRandomVariable(double, double[])} with {@code
   * time}, {@code realisations}.
   *
   * <p>Method under test: {@link RandomVariableAAD#constructNewAADRandomVariable(double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableAAD RandomVariableAAD.constructNewAADRandomVariable(double, double[])"
  })
  public void testConstructNewAADRandomVariableWithTimeRealisations() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            RandomVariableAAD.constructNewAADRandomVariable(
                10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d}));
  }

  /**
   * Test {@link RandomVariableAAD#constructNewAADRandomVariable(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableAAD#constructNewAADRandomVariable(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariableAAD RandomVariableAAD.constructNewAADRandomVariable(double)"})
  public void testConstructNewAADRandomVariableWithValue() {
    // Arrange and Act
    RandomVariableAAD actualConstructNewAADRandomVariableResult =
        RandomVariableAAD.constructNewAADRandomVariable(10.0d);

    // Assert
    assertTrue(
        actualConstructNewAADRandomVariableResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualConstructNewAADRandomVariableResult.getAverageAsRandomVariableAAD()
            instanceof RandomVariableAAD);
    assertTrue(
        actualConstructNewAADRandomVariableResult.getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableAAD);
    assertTrue(
        actualConstructNewAADRandomVariableResult.getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableAAD);
    assertTrue(
        actualConstructNewAADRandomVariableResult.getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableAAD);
    assertTrue(
        actualConstructNewAADRandomVariableResult.getVarianceAsRandomVariableAAD()
            instanceof RandomVariableAAD);
    assertTrue(
        actualConstructNewAADRandomVariableResult.expectation() instanceof RandomVariableAAD);
    assertTrue(actualConstructNewAADRandomVariableResult.expm1() instanceof RandomVariableAAD);
    assertTrue(actualConstructNewAADRandomVariableResult.variance() instanceof RandomVariableAAD);
    assertEquals(0.0d, actualConstructNewAADRandomVariableResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualConstructNewAADRandomVariableResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualConstructNewAADRandomVariableResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualConstructNewAADRandomVariableResult.getVariance(), 0.0);
    assertEquals(1, actualConstructNewAADRandomVariableResult.size());
    assertEquals(10.0d, actualConstructNewAADRandomVariableResult.getAverage(), 0.0);
    assertEquals(3, actualConstructNewAADRandomVariableResult.getTypePriority());
    assertTrue(actualConstructNewAADRandomVariableResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualConstructNewAADRandomVariableResult.getFiltrationTime(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d}, actualConstructNewAADRandomVariableResult.getRealizations(), 0.0);
  }
}
