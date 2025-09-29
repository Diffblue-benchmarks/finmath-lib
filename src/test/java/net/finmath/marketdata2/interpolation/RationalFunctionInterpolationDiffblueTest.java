package net.finmath.marketdata2.interpolation;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata2.interpolation.RationalFunctionInterpolation.ExtrapolationMethod;
import net.finmath.marketdata2.interpolation.RationalFunctionInterpolation.InterpolationMethod;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RationalFunctionInterpolationDiffblueTest {
  /**
   * Test {@link RationalFunctionInterpolation#RationalFunctionInterpolation(double[],
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * RationalFunctionInterpolation#RationalFunctionInterpolation(double[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RationalFunctionInterpolation.<init>(double[], RandomVariable[])"})
  public void testNewRationalFunctionInterpolation() {
    // Arrange
    RandomVariable[] values = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    RationalFunctionInterpolation actualRationalFunctionInterpolation =
        new RationalFunctionInterpolation(new double[] {10.0d, 0.5d, 10.0d, 0.5d}, values);

    // Assert
    assertEquals(
        InterpolationMethod.LINEAR, actualRationalFunctionInterpolation.getInterpolationMethod());
  }

  /**
   * Test {@link RationalFunctionInterpolation#RationalFunctionInterpolation(double[],
   * RandomVariable[], InterpolationMethod, ExtrapolationMethod)}.
   *
   * <p>Method under test: {@link
   * RationalFunctionInterpolation#RationalFunctionInterpolation(double[], RandomVariable[],
   * InterpolationMethod, ExtrapolationMethod)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RationalFunctionInterpolation.<init>(double[], RandomVariable[], InterpolationMethod, ExtrapolationMethod)"
  })
  public void testNewRationalFunctionInterpolation2() {
    // Arrange
    RandomVariable[] values = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    RationalFunctionInterpolation actualRationalFunctionInterpolation =
        new RationalFunctionInterpolation(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            values,
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
    RandomVariable[] values = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RationalFunctionInterpolation rationalFunctionInterpolation =
        new RationalFunctionInterpolation(new double[] {10.0d, 0.5d, 10.0d, 0.5d}, values);

    // Act and Assert
    assertEquals(
        InterpolationMethod.LINEAR, rationalFunctionInterpolation.getInterpolationMethod());
  }
}
