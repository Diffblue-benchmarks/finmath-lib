package net.finmath.interpolation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.interpolation.RationalFunctionInterpolation.ExtrapolationMethod;
import net.finmath.interpolation.RationalFunctionInterpolation.InterpolationMethod;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RationalFunctionInterpolationDiffblueTest {
  /**
   * Test {@link RationalFunctionInterpolation#RationalFunctionInterpolation(double[], double[])}.
   *
   * <p>Method under test: {@link
   * RationalFunctionInterpolation#RationalFunctionInterpolation(double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RationalFunctionInterpolation.<init>(double[], double[])"})
  public void testNewRationalFunctionInterpolation() {
    // Arrange and Act
    RationalFunctionInterpolation actualRationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    assertEquals(
        InterpolationMethod.LINEAR, actualRationalFunctionInterpolation.getInterpolationMethod());
  }

  /**
   * Test {@link RationalFunctionInterpolation#RationalFunctionInterpolation(double[], double[],
   * InterpolationMethod, ExtrapolationMethod)}.
   *
   * <p>Method under test: {@link
   * RationalFunctionInterpolation#RationalFunctionInterpolation(double[], double[],
   * InterpolationMethod, ExtrapolationMethod)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RationalFunctionInterpolation.<init>(double[], double[], InterpolationMethod, ExtrapolationMethod)"
  })
  public void testNewRationalFunctionInterpolation2() {
    // Arrange and Act
    RationalFunctionInterpolation actualRationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT);

    // Assert
    assertEquals(
        InterpolationMethod.PIECEWISE_CONSTANT,
        actualRationalFunctionInterpolation.getInterpolationMethod());
  }

  /**
   * Test {@link RationalFunctionInterpolation#getInterpolationMethod()}.
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#getInterpolationMethod()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InterpolationMethod RationalFunctionInterpolation.getInterpolationMethod()"})
  public void testGetInterpolationMethod() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(
        InterpolationMethod.LINEAR, rationalFunctionInterpolation.getInterpolationMethod());
  }

  /**
   * Test {@link RationalFunctionInterpolation#getValue(double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.getValue(double)"})
  public void testGetValue_thenReturnNaN() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            InterpolationMethod.AKIMA,
            ExtrapolationMethod.DEFAULT);

    // Act and Assert
    assertEquals(Double.NaN, rationalFunctionInterpolation.getValue(2.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#getValue(double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.getValue(double)"})
  public void testGetValue_thenReturnNaN2() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d},
            InterpolationMethod.AKIMA,
            ExtrapolationMethod.DEFAULT);

    // Act and Assert
    assertEquals(Double.NaN, rationalFunctionInterpolation.getValue(2.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#getValue(double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.getValue(double)"})
  public void testGetValue_thenReturnNegative_infinity() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 1.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(Double.NEGATIVE_INFINITY, rationalFunctionInterpolation.getValue(2.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#getValue(double)}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.getValue(double)"})
  public void testGetValue_thenReturnOne() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT);

    // Act and Assert
    assertEquals(1.0d, rationalFunctionInterpolation.getValue(2.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#getValue(double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.getValue(double)"})
  public void testGetValue_thenReturnPositive_infinity() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 10.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(Double.POSITIVE_INFINITY, rationalFunctionInterpolation.getValue(2.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#getValue(double)}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.getValue(double)"})
  public void testGetValue_thenReturnTen() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            InterpolationMethod.PIECEWISE_CONSTANT_RIGHTPOINT,
            ExtrapolationMethod.DEFAULT);

    // Act and Assert
    assertEquals(10.0d, rationalFunctionInterpolation.getValue(2.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#getValue(double)}.
   *
   * <ul>
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.getValue(double)"})
  public void testGetValue_thenReturnTwo() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(2.0d, rationalFunctionInterpolation.getValue(2.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#getValue(double)}.
   *
   * <ul>
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.getValue(double)"})
  public void testGetValue_thenReturnTwo2() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            InterpolationMethod.CUBIC_SPLINE,
            ExtrapolationMethod.DEFAULT);

    // Act and Assert
    assertEquals(2.0d, rationalFunctionInterpolation.getValue(2.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#getValue(double)}.
   *
   * <ul>
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.getValue(double)"})
  public void testGetValue_thenReturnTwo3() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d},
            InterpolationMethod.CUBIC_SPLINE,
            ExtrapolationMethod.DEFAULT);

    // Act and Assert
    assertEquals(2.0d, rationalFunctionInterpolation.getValue(2.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#getValue(double)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.getValue(double)"})
  public void testGetValue_thenThrowIllegalArgumentException() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(new double[] {}, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> rationalFunctionInterpolation.getValue(2.0d));
  }

  /**
   * Test {@link RationalFunctionInterpolation#getValue(double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.getValue(double)"})
  public void testGetValue_whenOne_thenReturnOne() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(1.0d, rationalFunctionInterpolation.getValue(1.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#applyAsDouble(double)}.
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#applyAsDouble(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.applyAsDouble(double)"})
  public void testApplyAsDouble() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(10.0d, rationalFunctionInterpolation.applyAsDouble(10.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#applyAsDouble(double)}.
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#applyAsDouble(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.applyAsDouble(double)"})
  public void testApplyAsDouble2() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT);

    // Act and Assert
    assertEquals(10.0d, rationalFunctionInterpolation.applyAsDouble(10.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#applyAsDouble(double)}.
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#applyAsDouble(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.applyAsDouble(double)"})
  public void testApplyAsDouble3() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            InterpolationMethod.PIECEWISE_CONSTANT_RIGHTPOINT,
            ExtrapolationMethod.DEFAULT);

    // Act and Assert
    assertEquals(10.0d, rationalFunctionInterpolation.applyAsDouble(10.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#applyAsDouble(double)}.
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#applyAsDouble(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.applyAsDouble(double)"})
  public void testApplyAsDouble4() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            InterpolationMethod.CUBIC_SPLINE,
            ExtrapolationMethod.DEFAULT);

    // Act and Assert
    assertEquals(10.0d, rationalFunctionInterpolation.applyAsDouble(10.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#applyAsDouble(double)}.
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#applyAsDouble(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.applyAsDouble(double)"})
  public void testApplyAsDouble5() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d},
            InterpolationMethod.CUBIC_SPLINE,
            ExtrapolationMethod.DEFAULT);

    // Act and Assert
    assertEquals(10.0d, rationalFunctionInterpolation.applyAsDouble(10.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#applyAsDouble(double)}.
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#applyAsDouble(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.applyAsDouble(double)"})
  public void testApplyAsDouble6() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            InterpolationMethod.AKIMA,
            ExtrapolationMethod.DEFAULT);

    // Act and Assert
    assertEquals(10.0d, rationalFunctionInterpolation.applyAsDouble(10.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#applyAsDouble(double)}.
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#applyAsDouble(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.applyAsDouble(double)"})
  public void testApplyAsDouble7() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d},
            InterpolationMethod.AKIMA,
            ExtrapolationMethod.DEFAULT);

    // Act and Assert
    assertEquals(10.0d, rationalFunctionInterpolation.applyAsDouble(10.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#applyAsDouble(double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#applyAsDouble(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.applyAsDouble(double)"})
  public void testApplyAsDouble_thenReturnNegative_infinity() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 1.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(Double.NEGATIVE_INFINITY, rationalFunctionInterpolation.applyAsDouble(10.0d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#applyAsDouble(double)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#applyAsDouble(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.applyAsDouble(double)"})
  public void testApplyAsDouble_thenThrowIllegalArgumentException() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(new double[] {}, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> rationalFunctionInterpolation.applyAsDouble(10.0d));
  }

  /**
   * Test {@link RationalFunctionInterpolation#applyAsDouble(double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#applyAsDouble(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.applyAsDouble(double)"})
  public void testApplyAsDouble_when05_thenReturn05() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(0.5d, rationalFunctionInterpolation.applyAsDouble(0.5d), 0.0);
  }

  /**
   * Test {@link RationalFunctionInterpolation#applyAsDouble(double)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link RationalFunctionInterpolation#applyAsDouble(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RationalFunctionInterpolation.applyAsDouble(double)"})
  public void testApplyAsDouble_whenThree_thenReturnThree() {
    // Arrange
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act and Assert
    assertEquals(3.0d, rationalFunctionInterpolation.applyAsDouble(3.0d), 0.0);
  }
}
