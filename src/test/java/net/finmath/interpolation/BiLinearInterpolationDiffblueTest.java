package net.finmath.interpolation;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BiLinearInterpolationDiffblueTest {
  /**
   * Test {@link BiLinearInterpolation#apply(Double, Double)} with {@code Double}, {@code Double}.
   *
   * <ul>
   *   <li>Then return doubleValue is one.
   * </ul>
   *
   * <p>Method under test: {@link BiLinearInterpolation#apply(Double, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double BiLinearInterpolation.apply(Double, Double)"})
  public void testApplyWithDoubleDouble_thenReturnDoubleValueIsOne() {
    // Arrange
    BiLinearInterpolation biLinearInterpolation =
        new BiLinearInterpolation(
            new double[] {2.0d, 10.0d, 2.0d, 10.0d},
            new double[] {3.0d, 10.0d, 3.0d, 10.0d},
            new double[][] {
              new double[] {1.0d, 10.0d, 1.0d, 10.0d}, new double[] {1.0d, 10.0d, 1.0d, 10.0d}
            });

    // Act and Assert
    assertEquals(1.0d, biLinearInterpolation.apply(2.0d, 3.0d).doubleValue(), 0.0);
  }

  /**
   * Test {@link BiLinearInterpolation#apply(Double, Double)} with {@code Double}, {@code Double}.
   *
   * <ul>
   *   <li>Then return doubleValue is three.
   * </ul>
   *
   * <p>Method under test: {@link BiLinearInterpolation#apply(Double, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double BiLinearInterpolation.apply(Double, Double)"})
  public void testApplyWithDoubleDouble_thenReturnDoubleValueIsThree() {
    // Arrange
    BiLinearInterpolation biLinearInterpolation =
        new BiLinearInterpolation(
            new double[] {2.0d, 10.0d, 2.0d, 10.0d},
            new double[] {1.0d, 10.0d, 3.0d, 10.0d},
            new double[][] {
              new double[] {1.0d, 10.0d, 1.0d, 10.0d}, new double[] {1.0d, 10.0d, 1.0d, 10.0d}
            });

    // Act and Assert
    assertEquals(3.0d, biLinearInterpolation.apply(2.0d, 3.0d).doubleValue(), 0.0);
  }

  /**
   * Test {@link BiLinearInterpolation#apply(Double, Double)} with {@code Double}, {@code Double}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return doubleValue is one.
   * </ul>
   *
   * <p>Method under test: {@link BiLinearInterpolation#apply(Double, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double BiLinearInterpolation.apply(Double, Double)"})
  public void testApplyWithDoubleDouble_when05_thenReturnDoubleValueIsOne() {
    // Arrange
    BiLinearInterpolation biLinearInterpolation =
        new BiLinearInterpolation(
            new double[] {2.0d, 10.0d, 2.0d, 10.0d},
            new double[] {3.0d, 10.0d, 3.0d, 10.0d},
            new double[][] {
              new double[] {1.0d, 10.0d, 1.0d, 10.0d}, new double[] {1.0d, 10.0d, 1.0d, 10.0d}
            });

    // Act and Assert
    assertEquals(1.0d, biLinearInterpolation.apply(0.5d, 3.0d).doubleValue(), 0.0);
  }

  /**
   * Test {@link BiLinearInterpolation#apply(Double, Double)} with {@code Double}, {@code Double}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return doubleValue is one.
   * </ul>
   *
   * <p>Method under test: {@link BiLinearInterpolation#apply(Double, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double BiLinearInterpolation.apply(Double, Double)"})
  public void testApplyWithDoubleDouble_whenOne_thenReturnDoubleValueIsOne() {
    // Arrange
    BiLinearInterpolation biLinearInterpolation =
        new BiLinearInterpolation(
            new double[] {2.0d, 10.0d, 2.0d, 10.0d},
            new double[] {3.0d, 10.0d, 3.0d, 10.0d},
            new double[][] {
              new double[] {1.0d, 10.0d, 1.0d, 10.0d}, new double[] {1.0d, 10.0d, 1.0d, 10.0d}
            });

    // Act and Assert
    assertEquals(1.0d, biLinearInterpolation.apply(1.0d, 3.0d).doubleValue(), 0.0);
  }

  /**
   * Test {@link BiLinearInterpolation#apply(Double, Double)} with {@code Double}, {@code Double}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return doubleValue is one.
   * </ul>
   *
   * <p>Method under test: {@link BiLinearInterpolation#apply(Double, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double BiLinearInterpolation.apply(Double, Double)"})
  public void testApplyWithDoubleDouble_whenTen_thenReturnDoubleValueIsOne() {
    // Arrange
    BiLinearInterpolation biLinearInterpolation =
        new BiLinearInterpolation(
            new double[] {2.0d, 10.0d, 2.0d, 10.0d},
            new double[] {3.0d, 10.0d, 3.0d, 10.0d},
            new double[][] {
              new double[] {1.0d, 10.0d, 1.0d, 10.0d}, new double[] {1.0d, 10.0d, 1.0d, 10.0d}
            });

    // Act and Assert
    assertEquals(1.0d, biLinearInterpolation.apply(10.0d, 3.0d).doubleValue(), 0.0);
  }

  /**
   * Test {@link BiLinearInterpolation#getValue(double, double)}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link BiLinearInterpolation#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BiLinearInterpolation.getValue(double, double)"})
  public void testGetValue_thenReturnOne() {
    // Arrange
    BiLinearInterpolation biLinearInterpolation =
        new BiLinearInterpolation(
            new double[] {2.0d, 10.0d, 2.0d, 10.0d},
            new double[] {3.0d, 10.0d, 3.0d, 10.0d},
            new double[][] {
              new double[] {1.0d, 10.0d, 1.0d, 10.0d}, new double[] {1.0d, 10.0d, 1.0d, 10.0d}
            });

    // Act and Assert
    assertEquals(1.0d, biLinearInterpolation.getValue(2.0d, 3.0d), 0.0);
  }

  /**
   * Test {@link BiLinearInterpolation#getValue(double, double)}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link BiLinearInterpolation#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BiLinearInterpolation.getValue(double, double)"})
  public void testGetValue_thenReturnThree() {
    // Arrange
    BiLinearInterpolation biLinearInterpolation =
        new BiLinearInterpolation(
            new double[] {2.0d, 10.0d, 2.0d, 10.0d},
            new double[] {1.0d, 10.0d, 3.0d, 10.0d},
            new double[][] {
              new double[] {1.0d, 10.0d, 1.0d, 10.0d}, new double[] {1.0d, 10.0d, 1.0d, 10.0d}
            });

    // Act and Assert
    assertEquals(3.0d, biLinearInterpolation.getValue(2.0d, 3.0d), 0.0);
  }

  /**
   * Test {@link BiLinearInterpolation#getValue(double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link BiLinearInterpolation#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BiLinearInterpolation.getValue(double, double)"})
  public void testGetValue_when05_thenReturnOne() {
    // Arrange
    BiLinearInterpolation biLinearInterpolation =
        new BiLinearInterpolation(
            new double[] {2.0d, 10.0d, 2.0d, 10.0d},
            new double[] {3.0d, 10.0d, 3.0d, 10.0d},
            new double[][] {
              new double[] {1.0d, 10.0d, 1.0d, 10.0d}, new double[] {1.0d, 10.0d, 1.0d, 10.0d}
            });

    // Act and Assert
    assertEquals(1.0d, biLinearInterpolation.getValue(0.5d, 3.0d), 0.0);
  }

  /**
   * Test {@link BiLinearInterpolation#getValue(double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link BiLinearInterpolation#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BiLinearInterpolation.getValue(double, double)"})
  public void testGetValue_whenOne_thenReturnOne() {
    // Arrange
    BiLinearInterpolation biLinearInterpolation =
        new BiLinearInterpolation(
            new double[] {2.0d, 10.0d, 2.0d, 10.0d},
            new double[] {3.0d, 10.0d, 3.0d, 10.0d},
            new double[][] {
              new double[] {1.0d, 10.0d, 1.0d, 10.0d}, new double[] {1.0d, 10.0d, 1.0d, 10.0d}
            });

    // Act and Assert
    assertEquals(1.0d, biLinearInterpolation.getValue(1.0d, 3.0d), 0.0);
  }

  /**
   * Test {@link BiLinearInterpolation#getValue(double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link BiLinearInterpolation#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BiLinearInterpolation.getValue(double, double)"})
  public void testGetValue_whenTen_thenReturnOne() {
    // Arrange
    BiLinearInterpolation biLinearInterpolation =
        new BiLinearInterpolation(
            new double[] {2.0d, 10.0d, 2.0d, 10.0d},
            new double[] {3.0d, 10.0d, 3.0d, 10.0d},
            new double[][] {
              new double[] {1.0d, 10.0d, 1.0d, 10.0d}, new double[] {1.0d, 10.0d, 1.0d, 10.0d}
            });

    // Act and Assert
    assertEquals(1.0d, biLinearInterpolation.getValue(10.0d, 3.0d), 0.0);
  }
}
