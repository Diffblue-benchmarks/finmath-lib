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
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LIBORCovarianceModelBHDiffblueTest {
  /**
   * Test {@link LIBORCovarianceModelBH#LIBORCovarianceModelBH(TimeDiscretization,
   * TimeDiscretization, int)}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelBH#LIBORCovarianceModelBH(TimeDiscretization,
   * TimeDiscretization, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCovarianceModelBH.<init>(TimeDiscretization, TimeDiscretization, int)"
  })
  public void testNewLIBORCovarianceModelBH() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORCovarianceModelBH actualLiborCovarianceModelBH =
        new LIBORCovarianceModelBH(timeDiscretization, liborPeriodDiscretization, 3);

    // Assert
    TimeDiscretization timeDiscretization2 = actualLiborCovarianceModelBH.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(3, actualLiborCovarianceModelBH.getNumberOfFactors());
    assertEquals(5, actualLiborCovarianceModelBH.getParameter().length);
    assertSame(
        liborPeriodDiscretization, actualLiborCovarianceModelBH.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {0.469d, 0.0452d, 0.35d, 0.01d, -0.8918d},
        actualLiborCovarianceModelBH.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelBH#LIBORCovarianceModelBH(TimeDiscretization,
   * TimeDiscretization, int, double[])}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelBH#LIBORCovarianceModelBH(TimeDiscretization,
   * TimeDiscretization, int, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCovarianceModelBH.<init>(TimeDiscretization, TimeDiscretization, int, double[])"
  })
  public void testNewLIBORCovarianceModelBH2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORCovarianceModelBH actualLiborCovarianceModelBH =
        new LIBORCovarianceModelBH(
            timeDiscretization,
            liborPeriodDiscretization,
            3,
            new double[] {10.0d, -0.8918d, 10.0d, -0.8918d});

    // Assert
    TimeDiscretization timeDiscretization2 = actualLiborCovarianceModelBH.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(3, actualLiborCovarianceModelBH.getNumberOfFactors());
    assertEquals(4, actualLiborCovarianceModelBH.getParameter().length);
    assertSame(
        liborPeriodDiscretization, actualLiborCovarianceModelBH.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, -0.8918d, 10.0d, -0.8918d},
        actualLiborCovarianceModelBH.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelBH#clone()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelBH#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCovarianceModelBH.clone()"})
  public void testClone() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORCovarianceModelBH liborCovarianceModelBH =
        new LIBORCovarianceModelBH(timeDiscretization, liborPeriodDiscretization, 3);

    // Act
    Object actualCloneResult = liborCovarianceModelBH.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORCovarianceModelBH);
    TimeDiscretization timeDiscretization2 =
        ((LIBORCovarianceModelBH) actualCloneResult).getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(3, ((LIBORCovarianceModelBH) actualCloneResult).getNumberOfFactors());
    assertEquals(5, ((LIBORCovarianceModelBH) actualCloneResult).getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        ((LIBORCovarianceModelBH) actualCloneResult).getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {0.469d, 0.0452d, 0.35d, 0.01d, -0.8918d},
        ((LIBORCovarianceModelBH) actualCloneResult).getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelBH#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelBH#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORCovarianceModelBH.getParameterAsDouble()"})
  public void testGetParameterAsDouble() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH liborCovarianceModelBH =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act and Assert
    assertArrayEquals(
        new double[] {0.469d, 0.0452d, 0.35d, 0.01d, -0.8918d},
        liborCovarianceModelBH.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelBH#getFactorLoading(int, int, RandomVariable[])} with {@code
   * int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelBH#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelBH.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH liborCovarianceModelBH =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelBH.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromDoubleArray);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelBH#getFactorLoading(int, int, RandomVariable[])} with {@code
   * int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelBH#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelBH.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable2() {
    // Arrange
    TimeDiscretizationFromArray timeDiscretization =
        new TimeDiscretizationFromArray(1.0d, 10, 0.5d);
    LIBORCovarianceModelBH liborCovarianceModelBH =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelBH.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromDoubleArray);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelBH#getFactorLoadingPseudoInverse(int, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelBH#getFactorLoadingPseudoInverse(int, int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableFromDoubleArray LIBORCovarianceModelBH.getFactorLoadingPseudoInverse(int, int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingPseudoInverse() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH liborCovarianceModelBH =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            liborCovarianceModelBH.getFactorLoadingPseudoInverse(
                1, 1, 3, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
  }

  /**
   * Test {@link LIBORCovarianceModelBH#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelBH#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelBH.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORCovarianceModelBH liborCovarianceModelBH =
        new LIBORCovarianceModelBH(timeDiscretization, liborPeriodDiscretization, 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelBH.getCloneWithModifiedParameters(
            new double[] {10.0d, -0.8918d, 10.0d, -0.8918d});

    // Assert
    assertTrue(actualCloneWithModifiedParameters instanceof LIBORCovarianceModelBH);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(3, actualCloneWithModifiedParameters.getNumberOfFactors());
    assertEquals(4, actualCloneWithModifiedParameters.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, -0.8918d, 10.0d, -0.8918d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelBH#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelBH#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelBH.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenHashMap() throws CalculationException {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH liborCovarianceModelBH =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        liborCovarianceModelBH.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof LIBORCovarianceModelBH);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {0.469d, 0.0452d, 0.35d, 0.01d, -0.8918d},
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
   * Test {@link LIBORCovarianceModelBH#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelBH#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelBH.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenNull() throws CalculationException {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH liborCovarianceModelBH =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        liborCovarianceModelBH.getCloneWithModifiedData(null);

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof LIBORCovarianceModelBH);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {0.469d, 0.0452d, 0.35d, 0.01d, -0.8918d},
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
