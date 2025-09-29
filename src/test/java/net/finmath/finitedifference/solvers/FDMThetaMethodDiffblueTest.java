package net.finmath.finitedifference.solvers;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.DoubleUnaryOperator;
import net.finmath.finitedifference.models.FDMBlackScholesModel;
import net.finmath.finitedifference.products.FDMEuropeanCallOption;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FDMThetaMethodDiffblueTest {
  /**
   * Test {@link FDMThetaMethod#getValue(double, double, DoubleUnaryOperator)}.
   *
   * <p>Method under test: {@link FDMThetaMethod#getValue(double, double, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] FDMThetaMethod.getValue(double, double, DoubleUnaryOperator)"})
  public void testGetValue() {
    // Arrange
    FDMBlackScholesModel model =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    FDMThetaMethod fdmThetaMethod =
        new FDMThetaMethod(model, new FDMEuropeanCallOption(10.0d, 10.0d), 2.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> fdmThetaMethod.getValue(0.0d, 10.0d, mock(DoubleUnaryOperator.class)));
  }

  /**
   * Test {@link FDMThetaMethod#getValue(double, double, DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link FDMThetaMethod#getValue(double, double, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] FDMThetaMethod.getValue(double, double, DoubleUnaryOperator)"})
  public void testGetValue_givenIllegalArgumentException() {
    // Arrange
    FDMBlackScholesModel model =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    FDMThetaMethod fdmThetaMethod =
        new FDMThetaMethod(model, new FDMEuropeanCallOption(10.0d, 10.0d), 10.0d, 10.0d, 10.0d);

    DoubleUnaryOperator valueAtMaturity = mock(DoubleUnaryOperator.class);
    when(valueAtMaturity.applyAsDouble(anyDouble())).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> fdmThetaMethod.getValue(0.0d, 10.0d, valueAtMaturity));
    verify(valueAtMaturity).applyAsDouble(Double.POSITIVE_INFINITY);
  }

  /**
   * Test {@link FDMThetaMethod#getValue(double, double, DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link FDMThetaMethod#getValue(double, double, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] FDMThetaMethod.getValue(double, double, DoubleUnaryOperator)"})
  public void testGetValue_thenReturnArrayLengthIsTwo() {
    // Arrange
    FDMBlackScholesModel model =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    FDMThetaMethod fdmThetaMethod =
        new FDMThetaMethod(model, new FDMEuropeanCallOption(10.0d, 10.0d), 10.0d, 10.0d, 10.0d);

    DoubleUnaryOperator valueAtMaturity = mock(DoubleUnaryOperator.class);
    when(valueAtMaturity.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double[][] actualValue = fdmThetaMethod.getValue(0.0d, 10.0d, valueAtMaturity);

    // Assert
    verify(valueAtMaturity, atLeast(1)).applyAsDouble(Double.POSITIVE_INFINITY);
    assertEquals(2, actualValue.length);
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        actualValue[1],
        0.0);
    assertArrayEquals(
        new double[] {
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY,
          Double.POSITIVE_INFINITY
        },
        actualValue[0],
        0.0);
  }

  /**
   * Test {@link FDMThetaMethod#getValue(double, double, DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>When {@link DoubleUnaryOperator}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link FDMThetaMethod#getValue(double, double, DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] FDMThetaMethod.getValue(double, double, DoubleUnaryOperator)"})
  public void testGetValue_whenDoubleUnaryOperator_thenThrowIllegalArgumentException() {
    // Arrange
    FDMBlackScholesModel model =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    FDMThetaMethod fdmThetaMethod =
        new FDMThetaMethod(model, new FDMEuropeanCallOption(10.0d, 10.0d), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> fdmThetaMethod.getValue(10.0d, 10.0d, mock(DoubleUnaryOperator.class)));
  }
}
