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

public class FDMConstantElasticityOfVarianceModelDiffblueTest {
  /**
   * Test {@link FDMConstantElasticityOfVarianceModel#FDMConstantElasticityOfVarianceModel(int, int,
   * int, double, double, double, double, double, double)}.
   *
   * <p>Method under test: {@link
   * FDMConstantElasticityOfVarianceModel#FDMConstantElasticityOfVarianceModel(int, int, int,
   * double, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FDMConstantElasticityOfVarianceModel.<init>(int, int, int, double, double, double, double, double, double)"
  })
  public void testNewFDMConstantElasticityOfVarianceModel() {
    // Arrange and Act
    FDMConstantElasticityOfVarianceModel actualFdmConstantElasticityOfVarianceModel =
        new FDMConstantElasticityOfVarianceModel(
            10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(10, actualFdmConstantElasticityOfVarianceModel.getNumSpacesteps());
    assertEquals(10, actualFdmConstantElasticityOfVarianceModel.getNumTimesteps());
    assertEquals(10.0d, actualFdmConstantElasticityOfVarianceModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualFdmConstantElasticityOfVarianceModel.getNumStandardDeviations(), 0.0);
    assertEquals(10.0d, actualFdmConstantElasticityOfVarianceModel.getRiskFreeRate(), 0.0);
    assertEquals(10.0d, actualFdmConstantElasticityOfVarianceModel.getVolatility(), 0.0);
  }

  /**
   * Test {@link FDMConstantElasticityOfVarianceModel#varianceOfStockPrice(double)}.
   *
   * <p>Method under test: {@link FDMConstantElasticityOfVarianceModel#varianceOfStockPrice(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double FDMConstantElasticityOfVarianceModel.varianceOfStockPrice(double)"})
  public void testVarianceOfStockPrice() {
    // Arrange
    FDMConstantElasticityOfVarianceModel fdmConstantElasticityOfVarianceModel =
        new FDMConstantElasticityOfVarianceModel(
            10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        Double.POSITIVE_INFINITY,
        fdmConstantElasticityOfVarianceModel.varianceOfStockPrice(10.0d),
        0.0);
  }

  /**
   * Test {@link FDMConstantElasticityOfVarianceModel#getForwardValue(double)}.
   *
   * <p>Method under test: {@link FDMConstantElasticityOfVarianceModel#getForwardValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double FDMConstantElasticityOfVarianceModel.getForwardValue(double)"})
  public void testGetForwardValue() {
    // Arrange
    FDMConstantElasticityOfVarianceModel fdmConstantElasticityOfVarianceModel =
        new FDMConstantElasticityOfVarianceModel(
            10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        2.6881171418161355E44d, fdmConstantElasticityOfVarianceModel.getForwardValue(10.0d), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FDMConstantElasticityOfVarianceModel#getInitialValue()}
   *   <li>{@link FDMConstantElasticityOfVarianceModel#getNumSpacesteps()}
   *   <li>{@link FDMConstantElasticityOfVarianceModel#getNumTimesteps()}
   *   <li>{@link FDMConstantElasticityOfVarianceModel#getRiskFreeRate()}
   *   <li>{@link FDMConstantElasticityOfVarianceModel#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double FDMConstantElasticityOfVarianceModel.getInitialValue()",
    "int FDMConstantElasticityOfVarianceModel.getNumSpacesteps()",
    "int FDMConstantElasticityOfVarianceModel.getNumTimesteps()",
    "double FDMConstantElasticityOfVarianceModel.getRiskFreeRate()",
    "double FDMConstantElasticityOfVarianceModel.getVolatility()"
  })
  public void testGettersAndSetters() {
    // Arrange
    FDMConstantElasticityOfVarianceModel fdmConstantElasticityOfVarianceModel =
        new FDMConstantElasticityOfVarianceModel(
            10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    double actualInitialValue = fdmConstantElasticityOfVarianceModel.getInitialValue();
    int actualNumSpacesteps = fdmConstantElasticityOfVarianceModel.getNumSpacesteps();
    int actualNumTimesteps = fdmConstantElasticityOfVarianceModel.getNumTimesteps();
    double actualRiskFreeRate = fdmConstantElasticityOfVarianceModel.getRiskFreeRate();

    // Assert
    assertEquals(10, actualNumSpacesteps);
    assertEquals(10, actualNumTimesteps);
    assertEquals(10.0d, actualInitialValue, 0.0);
    assertEquals(10.0d, actualRiskFreeRate, 0.0);
    assertEquals(10.0d, fdmConstantElasticityOfVarianceModel.getVolatility(), 0.0);
  }

  /**
   * Test {@link FDMConstantElasticityOfVarianceModel#getLocalVolatility(double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 0.01953125}.
   * </ul>
   *
   * <p>Method under test: {@link FDMConstantElasticityOfVarianceModel#getLocalVolatility(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double FDMConstantElasticityOfVarianceModel.getLocalVolatility(double, double)"
  })
  public void testGetLocalVolatility_when05_thenReturn001953125() {
    // Arrange
    FDMConstantElasticityOfVarianceModel fdmConstantElasticityOfVarianceModel =
        new FDMConstantElasticityOfVarianceModel(
            10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        0.01953125d, fdmConstantElasticityOfVarianceModel.getLocalVolatility(0.5d, 10.0d), 0.0);
  }

  /**
   * Test {@link FDMConstantElasticityOfVarianceModel#getLocalVolatility(double, double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return {@code -0.01953125}.
   * </ul>
   *
   * <p>Method under test: {@link FDMConstantElasticityOfVarianceModel#getLocalVolatility(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double FDMConstantElasticityOfVarianceModel.getLocalVolatility(double, double)"
  })
  public void testGetLocalVolatility_when05_thenReturn0019531252() {
    // Arrange
    FDMConstantElasticityOfVarianceModel fdmConstantElasticityOfVarianceModel =
        new FDMConstantElasticityOfVarianceModel(
            10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        -0.01953125d, fdmConstantElasticityOfVarianceModel.getLocalVolatility(-0.5d, 10.0d), 0.0);
  }

  /**
   * Test {@link FDMConstantElasticityOfVarianceModel#getLocalVolatility(double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link FDMConstantElasticityOfVarianceModel#getLocalVolatility(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double FDMConstantElasticityOfVarianceModel.getLocalVolatility(double, double)"
  })
  public void testGetLocalVolatility_whenOne_thenReturnTen() {
    // Arrange
    FDMConstantElasticityOfVarianceModel fdmConstantElasticityOfVarianceModel =
        new FDMConstantElasticityOfVarianceModel(
            10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10.0d, fdmConstantElasticityOfVarianceModel.getLocalVolatility(1.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link FDMConstantElasticityOfVarianceModel#getLocalVolatility(double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 1.0E10}.
   * </ul>
   *
   * <p>Method under test: {@link FDMConstantElasticityOfVarianceModel#getLocalVolatility(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double FDMConstantElasticityOfVarianceModel.getLocalVolatility(double, double)"
  })
  public void testGetLocalVolatility_whenTen_thenReturn10e10() {
    // Arrange
    FDMConstantElasticityOfVarianceModel fdmConstantElasticityOfVarianceModel =
        new FDMConstantElasticityOfVarianceModel(
            10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        1.0E10d, fdmConstantElasticityOfVarianceModel.getLocalVolatility(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link FDMConstantElasticityOfVarianceModel#getNumStandardDeviations()}.
   *
   * <p>Method under test: {@link FDMConstantElasticityOfVarianceModel#getNumStandardDeviations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double FDMConstantElasticityOfVarianceModel.getNumStandardDeviations()"})
  public void testGetNumStandardDeviations() {
    // Arrange
    FDMConstantElasticityOfVarianceModel fdmConstantElasticityOfVarianceModel =
        new FDMConstantElasticityOfVarianceModel(
            10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10.0d, fdmConstantElasticityOfVarianceModel.getNumStandardDeviations(), 0.0);
  }

  /**
   * Test {@link FDMConstantElasticityOfVarianceModel#getValue(double, double, DoubleUnaryOperator,
   * FiniteDifference1DBoundary)}.
   *
   * <p>Method under test: {@link FDMConstantElasticityOfVarianceModel#getValue(double, double,
   * DoubleUnaryOperator, FiniteDifference1DBoundary)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] FDMConstantElasticityOfVarianceModel.getValue(double, double, DoubleUnaryOperator, FiniteDifference1DBoundary)"
  })
  public void testGetValue() {
    // Arrange
    FDMConstantElasticityOfVarianceModel fdmConstantElasticityOfVarianceModel =
        new FDMConstantElasticityOfVarianceModel(
            10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 1.0d);

    DoubleUnaryOperator values = mock(DoubleUnaryOperator.class);
    when(values.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double[][] actualValue =
        fdmConstantElasticityOfVarianceModel.getValue(
            0.0d, 10.0d, values, new FDMEuropeanCallOption(10.0d, 10.0d));

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
   * Test {@link FDMConstantElasticityOfVarianceModel#getValue(double, double, DoubleUnaryOperator,
   * FiniteDifference1DBoundary)}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link FDMConstantElasticityOfVarianceModel#getValue(double, double,
   * DoubleUnaryOperator, FiniteDifference1DBoundary)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] FDMConstantElasticityOfVarianceModel.getValue(double, double, DoubleUnaryOperator, FiniteDifference1DBoundary)"
  })
  public void testGetValue_thenReturnArrayLengthIsTwo() {
    // Arrange
    FDMConstantElasticityOfVarianceModel fdmConstantElasticityOfVarianceModel =
        new FDMConstantElasticityOfVarianceModel(
            10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    DoubleUnaryOperator values = mock(DoubleUnaryOperator.class);
    when(values.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double[][] actualValue =
        fdmConstantElasticityOfVarianceModel.getValue(
            0.0d, 10.0d, values, new FDMEuropeanCallOption(10.0d, 10.0d));

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
   * Test {@link FDMConstantElasticityOfVarianceModel#getValue(double, double, DoubleUnaryOperator,
   * FiniteDifference1DBoundary)}.
   *
   * <ul>
   *   <li>When {@link FDMEuropeanPutOption#FDMEuropeanPutOption(double, double)} with
   *       optionMaturity is ten and optionStrike is ten.
   * </ul>
   *
   * <p>Method under test: {@link FDMConstantElasticityOfVarianceModel#getValue(double, double,
   * DoubleUnaryOperator, FiniteDifference1DBoundary)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[][] FDMConstantElasticityOfVarianceModel.getValue(double, double, DoubleUnaryOperator, FiniteDifference1DBoundary)"
  })
  public void testGetValue_whenFDMEuropeanPutOptionWithOptionMaturityIsTenAndOptionStrikeIsTen() {
    // Arrange
    FDMConstantElasticityOfVarianceModel fdmConstantElasticityOfVarianceModel =
        new FDMConstantElasticityOfVarianceModel(
            10, 10, 10, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    DoubleUnaryOperator values = mock(DoubleUnaryOperator.class);
    when(values.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double[][] actualValue =
        fdmConstantElasticityOfVarianceModel.getValue(
            0.0d, 10.0d, values, new FDMEuropeanPutOption(10.0d, 10.0d));

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
