package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LIBORCovarianceModelExponentialForm5ParamDiffblueTest {
  /**
   * Test {@link
   * LIBORCovarianceModelExponentialForm5Param#LIBORCovarianceModelExponentialForm5Param(TimeDiscretization,
   * TimeDiscretization, int)}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm5Param#LIBORCovarianceModelExponentialForm5Param(TimeDiscretization,
   * TimeDiscretization, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCovarianceModelExponentialForm5Param.<init>(TimeDiscretization, TimeDiscretization, int)"
  })
  public void testNewLIBORCovarianceModelExponentialForm5Param() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORCovarianceModelExponentialForm5Param actualLiborCovarianceModelExponentialForm5Param =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, liborPeriodDiscretization, 3);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborCovarianceModelExponentialForm5Param.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(3, actualLiborCovarianceModelExponentialForm5Param.getNumberOfFactors());
    assertSame(
        liborPeriodDiscretization,
        actualLiborCovarianceModelExponentialForm5Param.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d},
        actualLiborCovarianceModelExponentialForm5Param.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelExponentialForm5Param#LIBORCovarianceModelExponentialForm5Param(TimeDiscretization,
   * TimeDiscretization, int, double[])}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm5Param#LIBORCovarianceModelExponentialForm5Param(TimeDiscretization,
   * TimeDiscretization, int, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCovarianceModelExponentialForm5Param.<init>(TimeDiscretization, TimeDiscretization, int, double[])"
  })
  public void testNewLIBORCovarianceModelExponentialForm5Param2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORCovarianceModelExponentialForm5Param actualLiborCovarianceModelExponentialForm5Param =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization,
            liborPeriodDiscretization,
            3,
            new double[] {10.0d, 0.1d, 10.0d, 0.1d, 10.0d, 0.1d, 10.0d, 0.1d});

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborCovarianceModelExponentialForm5Param.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertSame(
        liborPeriodDiscretization,
        actualLiborCovarianceModelExponentialForm5Param.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 0.1d, 10.0d, 0.1d, 10.0d, 0.1d, 10.0d, 0.1d},
        actualLiborCovarianceModelExponentialForm5Param.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelExponentialForm5Param#LIBORCovarianceModelExponentialForm5Param(TimeDiscretization,
   * TimeDiscretization, int, double[])}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm5Param#LIBORCovarianceModelExponentialForm5Param(TimeDiscretization,
   * TimeDiscretization, int, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCovarianceModelExponentialForm5Param.<init>(TimeDiscretization, TimeDiscretization, int, double[])"
  })
  public void testNewLIBORCovarianceModelExponentialForm5Param3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 10.0d);

    // Act
    LIBORCovarianceModelExponentialForm5Param actualLiborCovarianceModelExponentialForm5Param =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization,
            liborPeriodDiscretization,
            3,
            new double[] {10.0d, 0.1d, 10.0d, 0.1d, 10.0d, 0.1d, 10.0d, 0.1d});

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborCovarianceModelExponentialForm5Param.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertSame(
        liborPeriodDiscretization,
        actualLiborCovarianceModelExponentialForm5Param.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 0.1d, 10.0d, 0.1d, 10.0d, 0.1d, 10.0d, 0.1d},
        actualLiborCovarianceModelExponentialForm5Param.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelExponentialForm5Param#LIBORCovarianceModelExponentialForm5Param(TimeDiscretization,
   * TimeDiscretization, int)}.
   *
   * <ul>
   *   <li>Then return NumberOfFactors is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm5Param#LIBORCovarianceModelExponentialForm5Param(TimeDiscretization,
   * TimeDiscretization, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCovarianceModelExponentialForm5Param.<init>(TimeDiscretization, TimeDiscretization, int)"
  })
  public void testNewLIBORCovarianceModelExponentialForm5Param_thenReturnNumberOfFactorsIsOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {0.2d, 0.05d, 0.2d, 0.05d});

    // Act
    LIBORCovarianceModelExponentialForm5Param actualLiborCovarianceModelExponentialForm5Param =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, liborPeriodDiscretization, 1);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborCovarianceModelExponentialForm5Param.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualLiborCovarianceModelExponentialForm5Param.getNumberOfFactors());
    assertEquals(11, timeDiscretization2.getAsArrayList().size());
    assertSame(
        liborPeriodDiscretization,
        actualLiborCovarianceModelExponentialForm5Param.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d},
        actualLiborCovarianceModelExponentialForm5Param.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelExponentialForm5Param#LIBORCovarianceModelExponentialForm5Param(TimeDiscretization,
   * TimeDiscretization, int, double[])}.
   *
   * <ul>
   *   <li>Then return NumberOfFactors is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm5Param#LIBORCovarianceModelExponentialForm5Param(TimeDiscretization,
   * TimeDiscretization, int, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCovarianceModelExponentialForm5Param.<init>(TimeDiscretization, TimeDiscretization, int, double[])"
  })
  public void testNewLIBORCovarianceModelExponentialForm5Param_thenReturnNumberOfFactorsIsOne2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(
            new double[] {-0.12069849283645878d, -1.0d, -0.12069849283645878d, -1.0d});

    // Act
    LIBORCovarianceModelExponentialForm5Param actualLiborCovarianceModelExponentialForm5Param =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization,
            liborPeriodDiscretization,
            1,
            new double[] {10.0d, 0.1d, 10.0d, 0.1d, 10.0d, 0.1d, 10.0d, 0.1d});

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborCovarianceModelExponentialForm5Param.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualLiborCovarianceModelExponentialForm5Param.getNumberOfFactors());
    assertSame(
        liborPeriodDiscretization,
        actualLiborCovarianceModelExponentialForm5Param.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 0.1d, 10.0d, 0.1d, 10.0d, 0.1d, 10.0d, 0.1d},
        actualLiborCovarianceModelExponentialForm5Param.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelExponentialForm5Param#clone()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelExponentialForm5Param#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCovarianceModelExponentialForm5Param.clone()"})
  public void testClone() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORCovarianceModelExponentialForm5Param liborCovarianceModelExponentialForm5Param =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, liborPeriodDiscretization, 3);

    // Act
    Object actualCloneResult = liborCovarianceModelExponentialForm5Param.clone();

    // Assert
    RandomVariable[] parameter =
        ((LIBORCovarianceModelExponentialForm5Param) actualCloneResult).getParameter();
    assertTrue(parameter instanceof Scalar[]);
    assertTrue(actualCloneResult instanceof LIBORCovarianceModelExponentialForm5Param);
    TimeDiscretization timeDiscretization2 =
        ((LIBORCovarianceModelExponentialForm5Param) actualCloneResult).getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        3, ((LIBORCovarianceModelExponentialForm5Param) actualCloneResult).getNumberOfFactors());
    assertEquals(5, parameter.length);
    assertSame(
        liborPeriodDiscretization,
        ((LIBORCovarianceModelExponentialForm5Param) actualCloneResult)
            .getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d},
        ((LIBORCovarianceModelExponentialForm5Param) actualCloneResult).getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelExponentialForm5Param#getCloneWithModifiedParameters(double[])}
   * with {@code double[]}.
   *
   * <ul>
   *   <li>Then Parameter return {@code Scalar[]}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm5Param#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelExponentialForm5Param.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble_thenParameterReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORCovarianceModelExponentialForm5Param liborCovarianceModelExponentialForm5Param =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, liborPeriodDiscretization, 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelExponentialForm5Param.getCloneWithModifiedParameters(
            new double[] {
              -0.2982272594017918d,
              -1.0d,
              -0.2982272594017918d,
              -1.0d,
              -0.2982272594017918d,
              -1.0d,
              -0.2982272594017918d,
              -1.0d
            });

    // Assert
    RandomVariable[] parameter = actualCloneWithModifiedParameters.getParameter();
    assertTrue(parameter instanceof Scalar[]);
    assertTrue(
        actualCloneWithModifiedParameters instanceof LIBORCovarianceModelExponentialForm5Param);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(3, actualCloneWithModifiedParameters.getNumberOfFactors());
    assertEquals(8, parameter.length);
    assertSame(
        liborPeriodDiscretization,
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {
          -0.2982272594017918d,
          -1.0d,
          -0.2982272594017918d,
          -1.0d,
          -0.2982272594017918d,
          -1.0d,
          -0.2982272594017918d,
          -1.0d
        },
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelExponentialForm5Param#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelExponentialForm5Param#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORCovarianceModelExponentialForm5Param.getParameterAsDouble()"})
  public void testGetParameterAsDouble() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param liborCovarianceModelExponentialForm5Param =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act and Assert
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d},
        liborCovarianceModelExponentialForm5Param.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelExponentialForm5Param#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelExponentialForm5Param#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelExponentialForm5Param.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(0.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param liborCovarianceModelExponentialForm5Param =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelExponentialForm5Param.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertTrue(actualFactorLoading[1] instanceof Scalar);
    assertTrue(actualFactorLoading[2] instanceof Scalar);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelExponentialForm5Param#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelExponentialForm5Param#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelExponentialForm5Param.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable_thenFirstElementReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param liborCovarianceModelExponentialForm5Param =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelExponentialForm5Param.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertTrue(actualFactorLoading[1] instanceof Scalar);
    assertTrue(actualFactorLoading[2] instanceof Scalar);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelExponentialForm5Param#getFactorLoadingPseudoInverse(int, int,
   * int, RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm5Param#getFactorLoadingPseudoInverse(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableFromDoubleArray LIBORCovarianceModelExponentialForm5Param.getFactorLoadingPseudoInverse(int, int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingPseudoInverse() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param liborCovarianceModelExponentialForm5Param =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            liborCovarianceModelExponentialForm5Param.getFactorLoadingPseudoInverse(
                1, 1, 3, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
  }

  /**
   * Test {@link LIBORCovarianceModelExponentialForm5Param#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm5Param#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelExponentialForm5Param.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenHashMap() throws CalculationException {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param liborCovarianceModelExponentialForm5Param =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        liborCovarianceModelExponentialForm5Param.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof LIBORCovarianceModelExponentialForm5Param);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelExponentialForm5Param#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm5Param#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelExponentialForm5Param.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenNull() throws CalculationException {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param liborCovarianceModelExponentialForm5Param =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        liborCovarianceModelExponentialForm5Param.getCloneWithModifiedData(null);

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof LIBORCovarianceModelExponentialForm5Param);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }
}
