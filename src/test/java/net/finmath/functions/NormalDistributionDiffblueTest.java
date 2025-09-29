package net.finmath.functions;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NormalDistributionDiffblueTest {
  /**
   * Test {@link NormalDistribution#density(double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link NormalDistribution#density(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NormalDistribution.density(double)"})
  public void testDensity_whenNaN_thenReturnNaN() {
    // Arrange, Act and Assert
    assertEquals(Double.NaN, NormalDistribution.density(Double.NaN), 0.0);
  }

  /**
   * Test {@link NormalDistribution#density(double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 0.053990966513188056}.
   * </ul>
   *
   * <p>Method under test: {@link NormalDistribution#density(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NormalDistribution.density(double)"})
  public void testDensity_whenTwo_thenReturn0053990966513188056() {
    // Arrange, Act and Assert
    assertEquals(0.053990966513188056d, NormalDistribution.density(2.0d), 0.0);
  }

  /**
   * Test {@link NormalDistribution#cumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return {@code 0.308537538725987}.
   * </ul>
   *
   * <p>Method under test: {@link NormalDistribution#cumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NormalDistribution.cumulativeDistribution(double)"})
  public void testCumulativeDistribution_when05_thenReturn0308537538725987() {
    // Arrange, Act and Assert
    assertEquals(0.308537538725987d, NormalDistribution.cumulativeDistribution(-0.5d), 0.0);
  }

  /**
   * Test {@link NormalDistribution#cumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When {@code 0.425}.
   *   <li>Then return {@code 0.6645816626298304}.
   * </ul>
   *
   * <p>Method under test: {@link NormalDistribution#cumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NormalDistribution.cumulativeDistribution(double)"})
  public void testCumulativeDistribution_when0425_thenReturn06645816626298304() {
    // Arrange, Act and Assert
    assertEquals(0.6645816626298304d, NormalDistribution.cumulativeDistribution(0.425d), 0.0);
  }

  /**
   * Test {@link NormalDistribution#cumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link NormalDistribution#cumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NormalDistribution.cumulativeDistribution(double)"})
  public void testCumulativeDistribution_whenNaN_thenReturnNaN() {
    // Arrange, Act and Assert
    assertEquals(Double.NaN, NormalDistribution.cumulativeDistribution(Double.NaN), 0.0);
  }

  /**
   * Test {@link NormalDistribution#cumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link NormalDistribution#cumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NormalDistribution.cumulativeDistribution(double)"})
  public void testCumulativeDistribution_whenTen_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1.0d, NormalDistribution.cumulativeDistribution(10.0d), 0.0);
  }

  /**
   * Test {@link NormalDistribution#cumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 0.9772498680518208}.
   * </ul>
   *
   * <p>Method under test: {@link NormalDistribution#cumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NormalDistribution.cumulativeDistribution(double)"})
  public void testCumulativeDistribution_whenTwo_thenReturn09772498680518208() {
    // Arrange, Act and Assert
    assertEquals(0.9772498680518208d, NormalDistribution.cumulativeDistribution(2.0d), 0.0);
  }

  /**
   * Test {@link NormalDistribution#inverseCumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link NormalDistribution#inverseCumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NormalDistribution.inverseCumulativeDistribution(double)"})
  public void testInverseCumulativeDistribution_when05_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0.0d, NormalDistribution.inverseCumulativeDistribution(0.5d), 0.0);
  }

  /**
   * Test {@link NormalDistribution#inverseCumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-10}.
   *   <li>Then return {@code -6.361340902404056}.
   * </ul>
   *
   * <p>Method under test: {@link NormalDistribution#inverseCumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NormalDistribution.inverseCumulativeDistribution(double)"})
  public void testInverseCumulativeDistribution_when10e10_thenReturn6361340902404056() {
    // Arrange, Act and Assert
    assertEquals(
        -6.361340902404056d, NormalDistribution.inverseCumulativeDistribution(1.0E-10d), 0.0);
  }

  /**
   * Test {@link NormalDistribution#inverseCumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link NormalDistribution#inverseCumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NormalDistribution.inverseCumulativeDistribution(double)"})
  public void testInverseCumulativeDistribution_whenNaN_thenReturnNaN() {
    // Arrange, Act and Assert
    assertEquals(Double.NaN, NormalDistribution.inverseCumulativeDistribution(Double.NaN), 0.0);
  }

  /**
   * Test {@link NormalDistribution#inverseCumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link NormalDistribution#inverseCumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NormalDistribution.inverseCumulativeDistribution(double)"})
  public void testInverseCumulativeDistribution_whenTen_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0.0d, NormalDistribution.inverseCumulativeDistribution(10.0d), 0.0);
  }

  /**
   * Test {@link NormalDistribution#inverseCumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link NormalDistribution#inverseCumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NormalDistribution.inverseCumulativeDistribution(double)"})
  public void testInverseCumulativeDistribution_whenZero_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0.0d, NormalDistribution.inverseCumulativeDistribution(0.0d), 0.0);
  }

  /**
   * Test {@link NormalDistribution#inverseCumulativeNormalDistributionWichura(double)}.
   *
   * <ul>
   *   <li>Then return {@code -6.361340902404056}.
   * </ul>
   *
   * <p>Method under test: {@link
   * NormalDistribution#inverseCumulativeNormalDistributionWichura(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double NormalDistribution.inverseCumulativeNormalDistributionWichura(double)"
  })
  public void testInverseCumulativeNormalDistributionWichura_thenReturn6361340902404056() {
    // Arrange, Act and Assert
    assertEquals(
        -6.361340902404056d,
        NormalDistribution.inverseCumulativeNormalDistributionWichura(1.0E-10d),
        0.0);
  }

  /**
   * Test {@link NormalDistribution#inverseCumulativeNormalDistributionWichura(double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * NormalDistribution#inverseCumulativeNormalDistributionWichura(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double NormalDistribution.inverseCumulativeNormalDistributionWichura(double)"
  })
  public void testInverseCumulativeNormalDistributionWichura_when05_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0.0d, NormalDistribution.inverseCumulativeNormalDistributionWichura(0.5d), 0.0);
  }

  /**
   * Test {@link NormalDistribution#inverseCumulativeNormalDistributionWichura(double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * NormalDistribution#inverseCumulativeNormalDistributionWichura(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double NormalDistribution.inverseCumulativeNormalDistributionWichura(double)"
  })
  public void testInverseCumulativeNormalDistributionWichura_whenNaN_thenReturnNaN() {
    // Arrange, Act and Assert
    assertEquals(
        Double.NaN, NormalDistribution.inverseCumulativeNormalDistributionWichura(Double.NaN), 0.0);
  }

  /**
   * Test {@link NormalDistribution#inverseCumulativeNormalDistributionWichura(double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * NormalDistribution#inverseCumulativeNormalDistributionWichura(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double NormalDistribution.inverseCumulativeNormalDistributionWichura(double)"
  })
  public void testInverseCumulativeNormalDistributionWichura_whenTen_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0.0d, NormalDistribution.inverseCumulativeNormalDistributionWichura(10.0d), 0.0);
  }

  /**
   * Test {@link NormalDistribution#inverseCumulativeNormalDistributionWichura(double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * NormalDistribution#inverseCumulativeNormalDistributionWichura(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double NormalDistribution.inverseCumulativeNormalDistributionWichura(double)"
  })
  public void testInverseCumulativeNormalDistributionWichura_whenZero_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0.0d, NormalDistribution.inverseCumulativeNormalDistributionWichura(0.0d), 0.0);
  }
}
