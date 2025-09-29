package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LIBORVolatilityModelFromGivenMatrixDiffblueTest {
  /**
   * Test {@link
   * LIBORVolatilityModelFromGivenMatrix#LIBORVolatilityModelFromGivenMatrix(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[][])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFromGivenMatrix#LIBORVolatilityModelFromGivenMatrix(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelFromGivenMatrix.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double[][])"
  })
  public void testNewLIBORVolatilityModelFromGivenMatrix() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    // Act
    LIBORVolatilityModelFromGivenMatrix actualLiborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            randomVariableFactory, timeDiscretization, liborPeriodDiscretization, volatility);

    // Assert
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelFromGivenMatrix.getLiborPeriodDiscretization());
    assertSame(
        timeDiscretization, actualLiborVolatilityModelFromGivenMatrix.getTimeDiscretization());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFromGivenMatrix#LIBORVolatilityModelFromGivenMatrix(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[][], boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFromGivenMatrix#LIBORVolatilityModelFromGivenMatrix(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[][], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelFromGivenMatrix.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double[][], boolean)"
  })
  public void testNewLIBORVolatilityModelFromGivenMatrix2() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    // Act
    LIBORVolatilityModelFromGivenMatrix actualLiborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            randomVariableFactory, timeDiscretization, liborPeriodDiscretization, volatility, true);

    // Assert
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelFromGivenMatrix.getLiborPeriodDiscretization());
    assertSame(
        timeDiscretization, actualLiborVolatilityModelFromGivenMatrix.getTimeDiscretization());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFromGivenMatrix#LIBORVolatilityModelFromGivenMatrix(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, RandomVariable[][], boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFromGivenMatrix#LIBORVolatilityModelFromGivenMatrix(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, RandomVariable[][], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelFromGivenMatrix.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, RandomVariable[][], boolean)"
  })
  public void testNewLIBORVolatilityModelFromGivenMatrix3() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariable[][] volatility =
        new RandomVariable[][] {new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}};

    // Act
    LIBORVolatilityModelFromGivenMatrix actualLiborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            randomVariableFactory, timeDiscretization, liborPeriodDiscretization, volatility, true);

    // Assert
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelFromGivenMatrix.getLiborPeriodDiscretization());
    assertSame(
        timeDiscretization, actualLiborVolatilityModelFromGivenMatrix.getTimeDiscretization());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFromGivenMatrix#LIBORVolatilityModelFromGivenMatrix(TimeDiscretization,
   * TimeDiscretization, double[][])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFromGivenMatrix#LIBORVolatilityModelFromGivenMatrix(TimeDiscretization,
   * TimeDiscretization, double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelFromGivenMatrix.<init>(TimeDiscretization, TimeDiscretization, double[][])"
  })
  public void testNewLIBORVolatilityModelFromGivenMatrix4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    // Act
    LIBORVolatilityModelFromGivenMatrix actualLiborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization, liborPeriodDiscretization, volatility);

    // Assert
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelFromGivenMatrix.getLiborPeriodDiscretization());
    assertSame(
        timeDiscretization, actualLiborVolatilityModelFromGivenMatrix.getTimeDiscretization());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFromGivenMatrix#LIBORVolatilityModelFromGivenMatrix(TimeDiscretization,
   * TimeDiscretization, RandomVariable[][])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFromGivenMatrix#LIBORVolatilityModelFromGivenMatrix(TimeDiscretization,
   * TimeDiscretization, RandomVariable[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelFromGivenMatrix.<init>(TimeDiscretization, TimeDiscretization, RandomVariable[][])"
  })
  public void testNewLIBORVolatilityModelFromGivenMatrix5() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariable[][] volatility =
        new RandomVariable[][] {new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}};

    // Act
    LIBORVolatilityModelFromGivenMatrix actualLiborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization, liborPeriodDiscretization, volatility);

    // Assert
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelFromGivenMatrix.getLiborPeriodDiscretization());
    assertSame(
        timeDiscretization, actualLiborVolatilityModelFromGivenMatrix.getTimeDiscretization());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFromGivenMatrix#LIBORVolatilityModelFromGivenMatrix(TimeDiscretization,
   * TimeDiscretization, RandomVariable[][], boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFromGivenMatrix#LIBORVolatilityModelFromGivenMatrix(TimeDiscretization,
   * TimeDiscretization, RandomVariable[][], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelFromGivenMatrix.<init>(TimeDiscretization, TimeDiscretization, RandomVariable[][], boolean)"
  })
  public void testNewLIBORVolatilityModelFromGivenMatrix6() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariable[][] volatility =
        new RandomVariable[][] {new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}};

    // Act
    LIBORVolatilityModelFromGivenMatrix actualLiborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization, liborPeriodDiscretization, volatility, true);

    // Assert
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelFromGivenMatrix.getLiborPeriodDiscretization());
    assertSame(
        timeDiscretization, actualLiborVolatilityModelFromGivenMatrix.getTimeDiscretization());
  }

  /**
   * Test {@link LIBORVolatilityModelFromGivenMatrix#getVolatility(int, int)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModelFromGivenMatrix#getVolatility(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable LIBORVolatilityModelFromGivenMatrix.getVolatility(int, int)"})
  public void testGetVolatility_thenReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFromGivenMatrix liborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[][] {
              new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d}
            });

    // Act
    RandomVariable actualVolatility = liborVolatilityModelFromGivenMatrix.getVolatility(1, 1);

    // Assert
    assertTrue(actualVolatility instanceof Scalar);
    assertTrue(actualVolatility.abs() instanceof Scalar);
    assertTrue(actualVolatility.cos() instanceof Scalar);
    assertTrue(actualVolatility.exp() instanceof Scalar);
    assertTrue(actualVolatility.expm1() instanceof Scalar);
    assertTrue(actualVolatility.invert() instanceof Scalar);
    assertTrue(actualVolatility.isNaN() instanceof Scalar);
    assertTrue(actualVolatility.sin() instanceof Scalar);
    assertTrue(actualVolatility.sqrt() instanceof Scalar);
    assertTrue(actualVolatility.squared() instanceof Scalar);
    assertTrue(actualVolatility.variance() instanceof Scalar);
    assertNull(actualVolatility.getRealizations());
    assertNull(actualVolatility.getOperator());
    assertNull(actualVolatility.getRealizationsStream());
    assertEquals(0, actualVolatility.getTypePriority());
    assertEquals(0.0d, actualVolatility.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualVolatility.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualVolatility.getStandardError(), 0.0);
    assertEquals(0.0d, actualVolatility.getVariance(), 0.0);
    assertEquals(0.5d, actualVolatility.getAverage(), 0.0);
    assertEquals(0.5d, actualVolatility.getMax(), 0.0);
    assertEquals(0.5d, actualVolatility.getMin(), 0.0);
    assertEquals(1, actualVolatility.size());
    assertTrue(actualVolatility.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualVolatility.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link LIBORVolatilityModelFromGivenMatrix#getParameter()}.
   *
   * <p>Method under test: {@link LIBORVolatilityModelFromGivenMatrix#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORVolatilityModelFromGivenMatrix.getParameter()"})
  public void testGetParameter() {
    // Arrange
    TenorFromArray timeDiscretization =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix liborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), volatility);

    // Act and Assert
    assertEquals(0, liborVolatilityModelFromGivenMatrix.getParameter().length);
  }

  /**
   * Test {@link LIBORVolatilityModelFromGivenMatrix#getParameter()}.
   *
   * <p>Method under test: {@link LIBORVolatilityModelFromGivenMatrix#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORVolatilityModelFromGivenMatrix.getParameter()"})
  public void testGetParameter2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix liborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization,
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            volatility);

    // Act and Assert
    assertEquals(0, liborVolatilityModelFromGivenMatrix.getParameter().length);
  }

  /**
   * Test {@link LIBORVolatilityModelFromGivenMatrix#getParameter()}.
   *
   * <p>Method under test: {@link LIBORVolatilityModelFromGivenMatrix#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORVolatilityModelFromGivenMatrix.getParameter()"})
  public void testGetParameter3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 10.5d, 10.0d, 10.5d});
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix liborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization, liborPeriodDiscretization, volatility);

    // Act and Assert
    assertEquals(0, liborVolatilityModelFromGivenMatrix.getParameter().length);
  }

  /**
   * Test {@link LIBORVolatilityModelFromGivenMatrix#getParameter()}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModelFromGivenMatrix#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORVolatilityModelFromGivenMatrix.getParameter()"})
  public void testGetParameter_thenFirstElementReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix liborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization, new TenorFromArray(10.0d, 2, 0.5d), volatility);

    // Act
    RandomVariable[] actualParameter = liborVolatilityModelFromGivenMatrix.getParameter();

    // Assert
    assertTrue(actualParameter[0] instanceof Scalar);
    assertEquals(1, actualParameter.length);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFromGivenMatrix#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFromGivenMatrix#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModelFromGivenMatrix LIBORVolatilityModelFromGivenMatrix.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter() {
    // Arrange
    TenorFromArray timeDiscretization =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix liborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization, liborPeriodDiscretization, volatility);

    // Act
    LIBORVolatilityModelFromGivenMatrix actualCloneWithModifiedParameter =
        liborVolatilityModelFromGivenMatrix.getCloneWithModifiedParameter(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertSame(
        liborPeriodDiscretization, actualCloneWithModifiedParameter.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, actualCloneWithModifiedParameter.getTimeDiscretization());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFromGivenMatrix#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFromGivenMatrix#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModelFromGivenMatrix LIBORVolatilityModelFromGivenMatrix.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix liborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization, liborPeriodDiscretization, volatility);

    // Act
    LIBORVolatilityModelFromGivenMatrix actualCloneWithModifiedParameter =
        liborVolatilityModelFromGivenMatrix.getCloneWithModifiedParameter(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameter.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertSame(
        liborPeriodDiscretization, actualCloneWithModifiedParameter.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFromGivenMatrix#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFromGivenMatrix#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModelFromGivenMatrix LIBORVolatilityModelFromGivenMatrix.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 10.5d, 10.0d, 10.5d});
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix liborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization, liborPeriodDiscretization, volatility);

    // Act
    LIBORVolatilityModelFromGivenMatrix actualCloneWithModifiedParameter =
        liborVolatilityModelFromGivenMatrix.getCloneWithModifiedParameter(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameter.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertSame(
        liborPeriodDiscretization, actualCloneWithModifiedParameter.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFromGivenMatrix#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFromGivenMatrix#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModelFromGivenMatrix LIBORVolatilityModelFromGivenMatrix.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 2, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix liborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization, liborPeriodDiscretization, volatility);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    LIBORVolatilityModelFromGivenMatrix actualCloneWithModifiedParameter =
        liborVolatilityModelFromGivenMatrix.getCloneWithModifiedParameter(
            new RandomVariable[] {randomVariableFromDoubleArray});

    // Assert
    RandomVariable[] parameter = actualCloneWithModifiedParameter.getParameter();
    RandomVariable randomVariable = parameter[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameter.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, parameter.length);
    assertSame(randomVariableFromDoubleArray, randomVariable);
    assertSame(
        liborPeriodDiscretization, actualCloneWithModifiedParameter.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d}, actualCloneWithModifiedParameter.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelFromGivenMatrix#clone()}.
   *
   * <p>Method under test: {@link LIBORVolatilityModelFromGivenMatrix#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORVolatilityModelFromGivenMatrix.clone()"})
  public void testClone() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix liborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization, liborPeriodDiscretization, volatility);

    // Act
    Object actualCloneResult = liborVolatilityModelFromGivenMatrix.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORVolatilityModelFromGivenMatrix);
    TimeDiscretization timeDiscretization2 =
        ((LIBORVolatilityModelFromGivenMatrix) actualCloneResult).getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertSame(
        liborPeriodDiscretization,
        ((LIBORVolatilityModelFromGivenMatrix) actualCloneResult).getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link LIBORVolatilityModelFromGivenMatrix#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link LIBORVolatilityModelFromGivenMatrix#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModel LIBORVolatilityModelFromGivenMatrix.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    LIBORVolatilityModelFromGivenMatrix liborVolatilityModelFromGivenMatrix =
        new LIBORVolatilityModelFromGivenMatrix(
            timeDiscretization, liborPeriodDiscretization, volatility);

    // Act
    LIBORVolatilityModel actualCloneWithModifiedData =
        liborVolatilityModelFromGivenMatrix.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof LIBORVolatilityModelFromGivenMatrix);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertSame(
        liborPeriodDiscretization, actualCloneWithModifiedData.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
  }
}
