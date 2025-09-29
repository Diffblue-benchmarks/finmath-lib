package net.finmath.finitedifference.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.finitedifference.models.FDMBlackScholesModel;
import net.finmath.finitedifference.models.FiniteDifference1DModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FDMEuropeanPutOptionDiffblueTest {
  /**
   * Test {@link FDMEuropeanPutOption#getValue(double, FiniteDifference1DModel)} with {@code
   * double}, {@code FiniteDifference1DModel}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link FDMEuropeanPutOption#getValue(double, FiniteDifference1DModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] FDMEuropeanPutOption.getValue(double, FiniteDifference1DModel)"})
  public void testGetValueWithDoubleFiniteDifference1DModel_thenReturnArrayLengthIsTwo() {
    // Arrange
    FDMEuropeanPutOption fdmEuropeanPutOption = new FDMEuropeanPutOption(10.0d, 10.0d);
    FDMBlackScholesModel model =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    double[][] actualValue = fdmEuropeanPutOption.getValue(0.0d, model);

    // Assert
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
   * Test {@link FDMEuropeanPutOption#getValueAtLowerBoundary(FiniteDifference1DModel, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * FDMEuropeanPutOption#getValueAtLowerBoundary(FiniteDifference1DModel, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double FDMEuropeanPutOption.getValueAtLowerBoundary(FiniteDifference1DModel, double, double)"
  })
  public void testGetValueAtLowerBoundary_thenReturnTen() {
    // Arrange
    FDMEuropeanPutOption fdmEuropeanPutOption = new FDMEuropeanPutOption(10.0d, 10.0d);
    FDMBlackScholesModel model =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10.0d, fdmEuropeanPutOption.getValueAtLowerBoundary(model, 10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link FDMEuropeanPutOption#getValueAtUpperBoundary(FiniteDifference1DModel, double,
   * double)}.
   *
   * <p>Method under test: {@link
   * FDMEuropeanPutOption#getValueAtUpperBoundary(FiniteDifference1DModel, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double FDMEuropeanPutOption.getValueAtUpperBoundary(FiniteDifference1DModel, double, double)"
  })
  public void testGetValueAtUpperBoundary() {
    // Arrange
    FDMEuropeanPutOption fdmEuropeanPutOption = new FDMEuropeanPutOption(10.0d, 10.0d);
    FDMBlackScholesModel model =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(0.0d, fdmEuropeanPutOption.getValueAtUpperBoundary(model, 10.0d, 10.0d), 0.0);
  }
}
