package net.finmath.finitedifference.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.DoubleUnaryOperator;
import net.finmath.finitedifference.products.FDMEuropeanCallOption;
import net.finmath.finitedifference.products.FDMEuropeanPutOption;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FDMBlackScholesModelDiffblueTest {
  /**
   * Test {@link FDMBlackScholesModel#FDMBlackScholesModel(int, int, int, double, double, double,
   * double, double)}.
   *
   * <p>Method under test: {@link FDMBlackScholesModel#FDMBlackScholesModel(int, int, int, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FDMBlackScholesModel.<init>(int, int, int, double, double, double, double, double)"
  })
  public void testNewFDMBlackScholesModel() {
    // Arrange and Act
    FDMBlackScholesModel actualFdmBlackScholesModel =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(10, actualFdmBlackScholesModel.getNumSpacesteps());
    assertEquals(10, actualFdmBlackScholesModel.getNumTimesteps());
    assertEquals(10.0d, actualFdmBlackScholesModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualFdmBlackScholesModel.getNumStandardDeviations(), 0.0);
    assertEquals(10.0d, actualFdmBlackScholesModel.getRiskFreeRate(), 0.0);
    assertEquals(10.0d, actualFdmBlackScholesModel.getVolatility(), 0.0);
  }

  /**
   * Test {@link FDMBlackScholesModel#varianceOfStockPrice(double)}.
   *
   * <p>Method under test: {@link FDMBlackScholesModel#varianceOfStockPrice(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double FDMBlackScholesModel.varianceOfStockPrice(double)"})
  public void testVarianceOfStockPrice() {
    // Arrange
    FDMBlackScholesModel fdmBlackScholesModel =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(Double.POSITIVE_INFINITY, fdmBlackScholesModel.varianceOfStockPrice(10.0d), 0.0);
  }

  /**
   * Test {@link FDMBlackScholesModel#getForwardValue(double)}.
   *
   * <p>Method under test: {@link FDMBlackScholesModel#getForwardValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double FDMBlackScholesModel.getForwardValue(double)"})
  public void testGetForwardValue() {
    // Arrange
    FDMBlackScholesModel fdmBlackScholesModel =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(2.6881171418161355E44d, fdmBlackScholesModel.getForwardValue(10.0d), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FDMBlackScholesModel#getInitialValue()}
   *   <li>{@link FDMBlackScholesModel#getNumSpacesteps()}
   *   <li>{@link FDMBlackScholesModel#getNumTimesteps()}
   *   <li>{@link FDMBlackScholesModel#getRiskFreeRate()}
   *   <li>{@link FDMBlackScholesModel#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double FDMBlackScholesModel.getInitialValue()",
    "int FDMBlackScholesModel.getNumSpacesteps()",
    "int FDMBlackScholesModel.getNumTimesteps()",
    "double FDMBlackScholesModel.getRiskFreeRate()",
    "double FDMBlackScholesModel.getVolatility()"
  })
  public void testGettersAndSetters() {
    // Arrange
    FDMBlackScholesModel fdmBlackScholesModel =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    double actualInitialValue = fdmBlackScholesModel.getInitialValue();
    int actualNumSpacesteps = fdmBlackScholesModel.getNumSpacesteps();
    int actualNumTimesteps = fdmBlackScholesModel.getNumTimesteps();
    double actualRiskFreeRate = fdmBlackScholesModel.getRiskFreeRate();

    // Assert
    assertEquals(10, actualNumSpacesteps);
    assertEquals(10, actualNumTimesteps);
    assertEquals(10.0d, actualInitialValue, 0.0);
    assertEquals(10.0d, actualRiskFreeRate, 0.0);
    assertEquals(10.0d, fdmBlackScholesModel.getVolatility(), 0.0);
  }

  /**
   * Test {@link FDMBlackScholesModel#getLocalVolatility(double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link FDMBlackScholesModel#getLocalVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double FDMBlackScholesModel.getLocalVolatility(double, double)"})
  public void testGetLocalVolatility_when05() {
    // Arrange
    FDMBlackScholesModel fdmBlackScholesModel =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10.0d, fdmBlackScholesModel.getLocalVolatility(0.5d, 10.0d), 0.0);
  }

  /**
   * Test {@link FDMBlackScholesModel#getLocalVolatility(double, double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link FDMBlackScholesModel#getLocalVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double FDMBlackScholesModel.getLocalVolatility(double, double)"})
  public void testGetLocalVolatility_when052() {
    // Arrange
    FDMBlackScholesModel fdmBlackScholesModel =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10.0d, fdmBlackScholesModel.getLocalVolatility(-0.5d, 10.0d), 0.0);
  }

  /**
   * Test {@link FDMBlackScholesModel#getLocalVolatility(double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link FDMBlackScholesModel#getLocalVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double FDMBlackScholesModel.getLocalVolatility(double, double)"})
  public void testGetLocalVolatility_whenNaN() {
    // Arrange
    FDMBlackScholesModel fdmBlackScholesModel =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10.0d, fdmBlackScholesModel.getLocalVolatility(Double.NaN, 10.0d), 0.0);
  }

  /**
   * Test {@link FDMBlackScholesModel#getLocalVolatility(double, double)}.
   *
   * <ul>
   *   <li>When ten.
   * </ul>
   *
   * <p>Method under test: {@link FDMBlackScholesModel#getLocalVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double FDMBlackScholesModel.getLocalVolatility(double, double)"})
  public void testGetLocalVolatility_whenTen() {
    // Arrange
    FDMBlackScholesModel fdmBlackScholesModel =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10.0d, fdmBlackScholesModel.getLocalVolatility(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link FDMBlackScholesModel#getNumStandardDeviations()}.
   *
   * <p>Method under test: {@link FDMBlackScholesModel#getNumStandardDeviations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double FDMBlackScholesModel.getNumStandardDeviations()"})
  public void testGetNumStandardDeviations() {
    // Arrange
    FDMBlackScholesModel fdmBlackScholesModel =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10.0d, fdmBlackScholesModel.getNumStandardDeviations(), 0.0);
  }

  /**
   * Test {@link FDMBlackScholesModel#getValue(double, double, DoubleUnaryOperator,
   * FiniteDifference1DBoundary)}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link FDMBlackScholesModel#getValue(double, double, DoubleUnaryOperator,
   * FiniteDifference1DBoundary)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] FDMBlackScholesModel.getValue(double, double, DoubleUnaryOperator, FiniteDifference1DBoundary)"
  })
  public void testGetValue_thenReturnArrayLengthIsTwo() {
    // Arrange
    FDMBlackScholesModel fdmBlackScholesModel =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    DoubleUnaryOperator values = mock(DoubleUnaryOperator.class);
    when(values.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double[][] actualValue =
        fdmBlackScholesModel.getValue(0.0d, 10.0d, values, new FDMEuropeanCallOption(10.0d, 10.0d));

    // Assert
    verify(values, atLeast(1)).applyAsDouble(Double.POSITIVE_INFINITY);
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
   * Test {@link FDMBlackScholesModel#getValue(double, double, DoubleUnaryOperator,
   * FiniteDifference1DBoundary)}.
   *
   * <ul>
   *   <li>When {@link FDMEuropeanPutOption#FDMEuropeanPutOption(double, double)} with
   *       optionMaturity is ten and optionStrike is ten.
   * </ul>
   *
   * <p>Method under test: {@link FDMBlackScholesModel#getValue(double, double, DoubleUnaryOperator,
   * FiniteDifference1DBoundary)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] FDMBlackScholesModel.getValue(double, double, DoubleUnaryOperator, FiniteDifference1DBoundary)"
  })
  public void testGetValue_whenFDMEuropeanPutOptionWithOptionMaturityIsTenAndOptionStrikeIsTen() {
    // Arrange
    FDMBlackScholesModel fdmBlackScholesModel =
        new FDMBlackScholesModel(10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    DoubleUnaryOperator values = mock(DoubleUnaryOperator.class);
    when(values.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double[][] actualValue =
        fdmBlackScholesModel.getValue(0.0d, 10.0d, values, new FDMEuropeanPutOption(10.0d, 10.0d));

    // Assert
    verify(values, atLeast(1)).applyAsDouble(Double.POSITIVE_INFINITY);
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
}
