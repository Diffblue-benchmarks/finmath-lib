package net.finmath.marketdata.model.volatility.caplet.smile;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LinearSmileInterpolaterDiffblueTest {
  /**
   * Test {@link LinearSmileInterpolater#calculateInterpolatedExtrapolatedSmileVolatility(double,
   * int)}.
   *
   * <ul>
   *   <li>Then return four.
   * </ul>
   *
   * <p>Method under test: {@link
   * LinearSmileInterpolater#calculateInterpolatedExtrapolatedSmileVolatility(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LinearSmileInterpolater.calculateInterpolatedExtrapolatedSmileVolatility(double, int)"
  })
  public void testCalculateInterpolatedExtrapolatedSmileVolatility_thenReturnFour() {
    // Arrange
    LinearSmileInterpolater linearSmileInterpolater =
        new LinearSmileInterpolater(
            new double[][] {
              new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d}
            },
            new double[] {-0.5d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(
        4.0d,
        linearSmileInterpolater.calculateInterpolatedExtrapolatedSmileVolatility(0.5d, 1),
        0.0);
  }

  /**
   * Test {@link LinearSmileInterpolater#calculateInterpolatedExtrapolatedSmileVolatility(double,
   * int)}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * LinearSmileInterpolater#calculateInterpolatedExtrapolatedSmileVolatility(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LinearSmileInterpolater.calculateInterpolatedExtrapolatedSmileVolatility(double, int)"
  })
  public void testCalculateInterpolatedExtrapolatedSmileVolatility_thenReturnTen() {
    // Arrange
    LinearSmileInterpolater linearSmileInterpolater =
        new LinearSmileInterpolater(
            new double[][] {
              new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d}
            },
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(
        10.0d,
        linearSmileInterpolater.calculateInterpolatedExtrapolatedSmileVolatility(1.0d, 1),
        0.0);
  }

  /**
   * Test {@link LinearSmileInterpolater#calculateInterpolatedExtrapolatedSmileVolatility(double,
   * int)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * LinearSmileInterpolater#calculateInterpolatedExtrapolatedSmileVolatility(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LinearSmileInterpolater.calculateInterpolatedExtrapolatedSmileVolatility(double, int)"
  })
  public void testCalculateInterpolatedExtrapolatedSmileVolatility_when05_thenReturnTen() {
    // Arrange
    LinearSmileInterpolater linearSmileInterpolater =
        new LinearSmileInterpolater(
            new double[][] {
              new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d}
            },
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(
        10.0d,
        linearSmileInterpolater.calculateInterpolatedExtrapolatedSmileVolatility(0.5d, 1),
        0.0);
  }

  /**
   * Test {@link LinearSmileInterpolater#calculateInterpolatedExtrapolatedSmileVolatility(double,
   * int)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * LinearSmileInterpolater#calculateInterpolatedExtrapolatedSmileVolatility(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LinearSmileInterpolater.calculateInterpolatedExtrapolatedSmileVolatility(double, int)"
  })
  public void testCalculateInterpolatedExtrapolatedSmileVolatility_when05_thenReturnTen2() {
    // Arrange
    LinearSmileInterpolater linearSmileInterpolater =
        new LinearSmileInterpolater(
            new double[][] {
              new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d}
            },
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(
        10.0d,
        linearSmileInterpolater.calculateInterpolatedExtrapolatedSmileVolatility(-0.5d, 1),
        0.0);
  }

  /**
   * Test {@link LinearSmileInterpolater#calculateInterpolatedExtrapolatedSmileVolatility(double,
   * int)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LinearSmileInterpolater#calculateInterpolatedExtrapolatedSmileVolatility(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LinearSmileInterpolater.calculateInterpolatedExtrapolatedSmileVolatility(double, int)"
  })
  public void testCalculateInterpolatedExtrapolatedSmileVolatility_whenTen_thenReturnOne() {
    // Arrange
    LinearSmileInterpolater linearSmileInterpolater =
        new LinearSmileInterpolater(
            new double[][] {
              new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d}
            },
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(
        1.0d,
        linearSmileInterpolater.calculateInterpolatedExtrapolatedSmileVolatility(10.0d, 1),
        0.0);
  }

  /**
   * Test {@link LinearSmileInterpolater#calculateInterpolatedExtrapolatedSmileVolatility(double,
   * int)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * LinearSmileInterpolater#calculateInterpolatedExtrapolatedSmileVolatility(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LinearSmileInterpolater.calculateInterpolatedExtrapolatedSmileVolatility(double, int)"
  })
  public void testCalculateInterpolatedExtrapolatedSmileVolatility_whenTen_thenReturnTen() {
    // Arrange
    LinearSmileInterpolater linearSmileInterpolater =
        new LinearSmileInterpolater(
            new double[][] {
              new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d}
            },
            new double[] {10.0d, 1.0d, 10.0d, Double.NaN});

    // Act and Assert
    assertEquals(
        10.0d,
        linearSmileInterpolater.calculateInterpolatedExtrapolatedSmileVolatility(10.0d, 1),
        0.0);
  }

  /**
   * Test {@link LinearSmileInterpolater#calculateInterpolatedExtrapolatedSmileVolatility(double,
   * int)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * LinearSmileInterpolater#calculateInterpolatedExtrapolatedSmileVolatility(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LinearSmileInterpolater.calculateInterpolatedExtrapolatedSmileVolatility(double, int)"
  })
  public void testCalculateInterpolatedExtrapolatedSmileVolatility_whenTen_thenReturnTen2() {
    // Arrange
    LinearSmileInterpolater linearSmileInterpolater =
        new LinearSmileInterpolater(
            new double[][] {
              new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 0.0d}
            },
            new double[] {10.0d, 1.0d, 10.0d, Double.NaN});

    // Act and Assert
    assertEquals(
        10.0d,
        linearSmileInterpolater.calculateInterpolatedExtrapolatedSmileVolatility(10.0d, 1),
        0.0);
  }
}
