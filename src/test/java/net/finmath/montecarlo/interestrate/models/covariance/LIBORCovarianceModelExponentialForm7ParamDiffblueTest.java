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

public class LIBORCovarianceModelExponentialForm7ParamDiffblueTest {
  /**
   * Test {@link
   * LIBORCovarianceModelExponentialForm7Param#LIBORCovarianceModelExponentialForm7Param(TimeDiscretization,
   * TimeDiscretization, int)}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm7Param#LIBORCovarianceModelExponentialForm7Param(TimeDiscretization,
   * TimeDiscretization, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCovarianceModelExponentialForm7Param.<init>(TimeDiscretization, TimeDiscretization, int)"
  })
  public void testNewLIBORCovarianceModelExponentialForm7Param() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORCovarianceModelExponentialForm7Param actualLiborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, liborPeriodDiscretization, 3);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborCovarianceModelExponentialForm7Param.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(3, actualLiborCovarianceModelExponentialForm7Param.getNumberOfFactors());
    assertEquals(7, actualLiborCovarianceModelExponentialForm7Param.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborCovarianceModelExponentialForm7Param.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.1d, 0.1d},
        actualLiborCovarianceModelExponentialForm7Param.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelExponentialForm7Param#clone()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelExponentialForm7Param#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCovarianceModelExponentialForm7Param.clone()"})
  public void testClone() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, liborPeriodDiscretization, 3);

    // Act
    Object actualCloneResult = liborCovarianceModelExponentialForm7Param.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization timeDiscretization2 =
        ((LIBORCovarianceModelExponentialForm7Param) actualCloneResult).getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        3, ((LIBORCovarianceModelExponentialForm7Param) actualCloneResult).getNumberOfFactors());
    assertEquals(
        7, ((LIBORCovarianceModelExponentialForm7Param) actualCloneResult).getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        ((LIBORCovarianceModelExponentialForm7Param) actualCloneResult)
            .getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.1d, 0.1d},
        ((LIBORCovarianceModelExponentialForm7Param) actualCloneResult).getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   * with {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(
            new double[] {0.1d, 0.2d, 0.1d, 0.2d, 0.1d, 0.2d, 0.1d, 0.2d});

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {0.1d, 0.2d, 0.1d, 0.2d, 0.1d, 0.2d, 0.1d, 0.2d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
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
   * Test {@link LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   * with {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(
            new double[] {0.2d, 0.2d, 0.1d, 0.2d, 0.1d, 0.2d, 0.1d, 0.2d});

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {0.2d, 0.2d, 0.1d, 0.2d, 0.1d, 0.2d, 0.1d, 0.2d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
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
   * Test {@link LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   * with {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(
            new double[] {0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.2d, 0.1d, 0.2d});

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.2d, 0.1d, 0.2d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
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
   * Test {@link LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   * with {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(
            new double[] {0.1d, 0.2d, 0.1d, 0.2d, 0.2d, 0.2d, 0.1d, 0.2d});

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {0.1d, 0.2d, 0.1d, 0.2d, 0.2d, 0.2d, 0.1d, 0.2d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
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
   * Test {@link LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   * with {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble5() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(
            new double[] {0.1d, 0.2d, 0.1d, 0.2d, 0.1d, 0.1d, 0.1d, 0.2d});

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {0.1d, 0.2d, 0.1d, 0.2d, 0.1d, 0.1d, 0.1d, 0.2d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
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
   * Test {@link LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   * with {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble6() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(
            new double[] {0.1d, 0.1d, 0.2d, 0.2d, 0.1d, 0.2d, 0.1d, 0.2d});

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.2d, 0.2d, 0.1d, 0.2d, 0.1d, 0.2d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
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
   * Test {@link LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   * with {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble7() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(
            new double[] {0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.2d});

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.2d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
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
   * Test {@link LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   * with {@code double[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble8() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelExponentialForm7Param.getCloneWithModifiedParameters(
            new double[] {0.1d, 0.2d, 0.1d, 0.2d, 0.1d, 0.1d, 0.2d, 0.2d});

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {0.1d, 0.2d, 0.1d, 0.2d, 0.1d, 0.1d, 0.2d, 0.2d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
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
   * Test {@link LIBORCovarianceModelExponentialForm7Param#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelExponentialForm7Param#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORCovarianceModelExponentialForm7Param.getParameterAsDouble()"})
  public void testGetParameterAsDouble() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act and Assert
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.1d, 0.1d},
        liborCovarianceModelExponentialForm7Param.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelExponentialForm7Param#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelExponentialForm7Param#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelExponentialForm7Param.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {1.0d, 10.0d, 1.0d, 10.0d});
    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelExponentialForm7Param.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertTrue(actualFactorLoading[1] instanceof Scalar);
    assertTrue(actualFactorLoading[2] instanceof Scalar);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelExponentialForm7Param#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelExponentialForm7Param#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelExponentialForm7Param.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable_thenFirstElementReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelExponentialForm7Param.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertTrue(actualFactorLoading[1] instanceof Scalar);
    assertTrue(actualFactorLoading[2] instanceof Scalar);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelExponentialForm7Param#getFactorLoadingPseudoInverse(int, int,
   * int, RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm7Param#getFactorLoadingPseudoInverse(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableFromDoubleArray LIBORCovarianceModelExponentialForm7Param.getFactorLoadingPseudoInverse(int, int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingPseudoInverse() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            liborCovarianceModelExponentialForm7Param.getFactorLoadingPseudoInverse(
                1, 1, 3, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
  }

  /**
   * Test {@link LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelExponentialForm7Param.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenHashMap() throws CalculationException {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        liborCovarianceModelExponentialForm7Param.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.1d, 0.1d},
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
   * Test {@link LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelExponentialForm7Param#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelExponentialForm7Param.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenNull() throws CalculationException {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param liborCovarianceModelExponentialForm7Param =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        liborCovarianceModelExponentialForm7Param.getCloneWithModifiedData(null);

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof LIBORCovarianceModelExponentialForm7Param);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.1d, 0.1d},
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
