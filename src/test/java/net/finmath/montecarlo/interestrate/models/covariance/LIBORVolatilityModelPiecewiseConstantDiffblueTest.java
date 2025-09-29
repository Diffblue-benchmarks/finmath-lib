package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
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

public class LIBORVolatilityModelPiecewiseConstantDiffblueTest {
  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[],
   * boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[], boolean)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TimeDiscretizationFromArray liborPeriodDiscretization =
        new TimeDiscretizationFromArray(15.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                liborPeriodDiscretization,
                simulationTimeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                true));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[],
   * boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[], boolean)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant2() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelPiecewiseConstant actualLiborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            randomVariableFactory,
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            timeToMaturityDiscretization,
            new double[] {},
            true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(0, actualLiborVolatilityModelPiecewiseConstant.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelPiecewiseConstant.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertSame(
        simulationTimeDiscretization,
        actualLiborVolatilityModelPiecewiseConstant.getSimulationTimeDiscretization());
    assertSame(
        timeToMaturityDiscretization,
        actualLiborVolatilityModelPiecewiseConstant.getTimeToMaturityDiscretization());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[][],
   * boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[][],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[][], boolean)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant3() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    // Act
    LIBORVolatilityModelPiecewiseConstant actualLiborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            randomVariableFactory,
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            volatility,
            true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization simulationTimeDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getSimulationTimeDiscretization();
    assertTrue(simulationTimeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeToMaturityDiscretization =
        actualLiborVolatilityModelPiecewiseConstant.getTimeToMaturityDiscretization();
    assertTrue(timeToMaturityDiscretization instanceof TenorFromArray);
    assertEquals(0, actualLiborVolatilityModelPiecewiseConstant.getParameter().length);
    assertEquals(timeDiscretization2, simulationTimeDiscretization2);
    assertEquals(timeDiscretization2, timeToMaturityDiscretization);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelPiecewiseConstant.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelPiecewiseConstant actualLiborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization simulationTimeDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getSimulationTimeDiscretization();
    assertTrue(simulationTimeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeToMaturityDiscretization =
        actualLiborVolatilityModelPiecewiseConstant.getTimeToMaturityDiscretization();
    assertTrue(timeToMaturityDiscretization instanceof TenorFromArray);
    assertEquals(0, actualLiborVolatilityModelPiecewiseConstant.getParameter().length);
    assertEquals(timeDiscretization2, simulationTimeDiscretization2);
    assertEquals(timeDiscretization2, timeToMaturityDiscretization);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelPiecewiseConstant.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant5() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TimeDiscretizationFromArray liborPeriodDiscretization =
        new TimeDiscretizationFromArray(15.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelPiecewiseConstant actualLiborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);

    // Assert
    assertTrue(
        actualLiborVolatilityModelPiecewiseConstant.getSimulationTimeDiscretization()
            instanceof TenorFromArray);
    assertTrue(
        actualLiborVolatilityModelPiecewiseConstant.getTimeToMaturityDiscretization()
            instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TimeDiscretizationFromArray);
    assertEquals(1, actualLiborVolatilityModelPiecewiseConstant.getParameter().length);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
    assertArrayEquals(
        new double[] {10.0d},
        actualLiborVolatilityModelPiecewiseConstant.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double, boolean)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant6() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelPiecewiseConstant actualLiborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d,
            true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization simulationTimeDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getSimulationTimeDiscretization();
    assertTrue(simulationTimeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeToMaturityDiscretization =
        actualLiborVolatilityModelPiecewiseConstant.getTimeToMaturityDiscretization();
    assertTrue(timeToMaturityDiscretization instanceof TenorFromArray);
    assertEquals(0, actualLiborVolatilityModelPiecewiseConstant.getParameter().length);
    assertEquals(timeDiscretization2, simulationTimeDiscretization2);
    assertEquals(timeDiscretization2, timeToMaturityDiscretization);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelPiecewiseConstant.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double, boolean)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant7() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TimeDiscretizationFromArray liborPeriodDiscretization =
        new TimeDiscretizationFromArray(15.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelPiecewiseConstant actualLiborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d,
            true);

    // Assert
    assertTrue(
        actualLiborVolatilityModelPiecewiseConstant.getSimulationTimeDiscretization()
            instanceof TenorFromArray);
    assertTrue(
        actualLiborVolatilityModelPiecewiseConstant.getTimeToMaturityDiscretization()
            instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TimeDiscretizationFromArray);
    assertEquals(1, actualLiborVolatilityModelPiecewiseConstant.getParameter().length);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
    assertArrayEquals(
        new double[] {10.0d},
        actualLiborVolatilityModelPiecewiseConstant.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant8() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TimeDiscretizationFromArray liborPeriodDiscretization =
        new TimeDiscretizationFromArray(15.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelPiecewiseConstant(
                timeDiscretization,
                liborPeriodDiscretization,
                simulationTimeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d}));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant9() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelPiecewiseConstant actualLiborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            timeToMaturityDiscretization,
            new double[] {});

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(0, actualLiborVolatilityModelPiecewiseConstant.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelPiecewiseConstant.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertSame(
        simulationTimeDiscretization,
        actualLiborVolatilityModelPiecewiseConstant.getSimulationTimeDiscretization());
    assertSame(
        timeToMaturityDiscretization,
        actualLiborVolatilityModelPiecewiseConstant.getTimeToMaturityDiscretization());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[], boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[], boolean)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant10() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TimeDiscretizationFromArray liborPeriodDiscretization =
        new TimeDiscretizationFromArray(15.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelPiecewiseConstant(
                timeDiscretization,
                liborPeriodDiscretization,
                simulationTimeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                true));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[], boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[], boolean)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant11() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelPiecewiseConstant actualLiborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            timeToMaturityDiscretization,
            new double[] {},
            true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(0, actualLiborVolatilityModelPiecewiseConstant.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelPiecewiseConstant.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertSame(
        simulationTimeDiscretization,
        actualLiborVolatilityModelPiecewiseConstant.getSimulationTimeDiscretization());
    assertSame(
        timeToMaturityDiscretization,
        actualLiborVolatilityModelPiecewiseConstant.getTimeToMaturityDiscretization());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[], boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[], boolean)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant12() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariable[] volatility = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    LIBORVolatilityModelPiecewiseConstant actualLiborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            timeToMaturityDiscretization,
            volatility,
            true);

    // Assert
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization simulationTimeDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getSimulationTimeDiscretization();
    assertTrue(simulationTimeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeToMaturityDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getTimeToMaturityDiscretization();
    assertTrue(timeToMaturityDiscretization2 instanceof TenorFromArray);
    assertEquals(timeDiscretization2, liborPeriodDiscretization2);
    assertEquals(timeDiscretization2, simulationTimeDiscretization2);
    assertEquals(timeDiscretization2, timeToMaturityDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[], boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[], boolean)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant13() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TimeDiscretizationFromArray liborPeriodDiscretization =
        new TimeDiscretizationFromArray(15.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariable[] volatility = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    LIBORVolatilityModelPiecewiseConstant actualLiborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            timeToMaturityDiscretization,
            volatility,
            true);

    // Assert
    assertTrue(
        actualLiborVolatilityModelPiecewiseConstant.getSimulationTimeDiscretization()
            instanceof TenorFromArray);
    assertTrue(
        actualLiborVolatilityModelPiecewiseConstant.getTimeToMaturityDiscretization()
            instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TimeDiscretizationFromArray);
    assertEquals(1, actualLiborVolatilityModelPiecewiseConstant.getParameter().length);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
    assertArrayEquals(
        new double[] {10.0d},
        actualLiborVolatilityModelPiecewiseConstant.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[][],
   * boolean)}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[][],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[][], boolean)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant_thenReturnArrayLengthIsOne() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {15.0d, 20.0d, 15.0d, 20.0d});
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] volatility = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    // Act
    LIBORVolatilityModelPiecewiseConstant actualLiborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            randomVariableFactory,
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            volatility,
            true);

    // Assert
    assertTrue(
        actualLiborVolatilityModelPiecewiseConstant.getSimulationTimeDiscretization()
            instanceof TenorFromArray);
    assertTrue(
        actualLiborVolatilityModelPiecewiseConstant.getTimeToMaturityDiscretization()
            instanceof TenorFromArray);
    assertEquals(1, actualLiborVolatilityModelPiecewiseConstant.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelPiecewiseConstant.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d},
        actualLiborVolatilityModelPiecewiseConstant.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[],
   * boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[], boolean)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant_thenThrowIllegalArgumentException() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                liborPeriodDiscretization,
                simulationTimeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                true));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant_thenThrowIllegalArgumentException2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelPiecewiseConstant(
                timeDiscretization,
                liborPeriodDiscretization,
                simulationTimeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d}));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[], boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[], boolean)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant_thenThrowIllegalArgumentException3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelPiecewiseConstant(
                timeDiscretization,
                liborPeriodDiscretization,
                simulationTimeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                true));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[], boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[], boolean)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant_thenThrowIllegalArgumentException4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(15.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelPiecewiseConstant(
                timeDiscretization,
                liborPeriodDiscretization,
                simulationTimeDiscretization,
                timeToMaturityDiscretization,
                new RandomVariable[] {
                  randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(15.0d)
                },
                true));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[], boolean)}.
   *
   * <ul>
   *   <li>When empty array of {@link RandomVariable}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#LIBORVolatilityModelPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[], boolean)"
  })
  public void testNewLIBORVolatilityModelPiecewiseConstant_whenEmptyArrayOfRandomVariable() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelPiecewiseConstant actualLiborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            new RandomVariable[] {},
            true);

    // Assert
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization simulationTimeDiscretization2 =
        actualLiborVolatilityModelPiecewiseConstant.getSimulationTimeDiscretization();
    assertTrue(simulationTimeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeToMaturityDiscretization =
        actualLiborVolatilityModelPiecewiseConstant.getTimeToMaturityDiscretization();
    assertTrue(timeToMaturityDiscretization instanceof TenorFromArray);
    assertEquals(timeDiscretization2, liborPeriodDiscretization2);
    assertEquals(timeDiscretization2, simulationTimeDiscretization2);
    assertEquals(timeDiscretization2, timeToMaturityDiscretization);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelPiecewiseConstant#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModelPiecewiseConstant#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORVolatilityModelPiecewiseConstant.getParameter()"})
  public void testGetParameter_thenReturnArrayLengthIsZero() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant liborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);

    // Act and Assert
    assertEquals(0, liborVolatilityModelPiecewiseConstant.getParameter().length);
  }

  /**
   * Test {@link LIBORVolatilityModelPiecewiseConstant#getParameter()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModelPiecewiseConstant#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORVolatilityModelPiecewiseConstant.getParameter()"})
  public void testGetParameter_thenReturnNull() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant liborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d,
            false);

    // Act and Assert
    assertNull(liborVolatilityModelPiecewiseConstant.getParameter());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModel LIBORVolatilityModelPiecewiseConstant.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant liborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);

    // Act
    LIBORVolatilityModel actualCloneWithModifiedParameter =
        liborVolatilityModelPiecewiseConstant.getCloneWithModifiedParameter(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualCloneWithModifiedParameter instanceof LIBORVolatilityModelPiecewiseConstant);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedParameter.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameter.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization simulationTimeDiscretization2 =
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneWithModifiedParameter)
            .getSimulationTimeDiscretization();
    assertTrue(simulationTimeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeToMaturityDiscretization =
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneWithModifiedParameter)
            .getTimeToMaturityDiscretization();
    assertTrue(timeToMaturityDiscretization instanceof TenorFromArray);
    assertEquals(timeDiscretization2, simulationTimeDiscretization2);
    assertEquals(timeDiscretization2, timeToMaturityDiscretization);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModel LIBORVolatilityModelPiecewiseConstant.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TimeDiscretizationFromArray liborPeriodDiscretization =
        new TimeDiscretizationFromArray(15.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant liborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);

    // Act
    LIBORVolatilityModel actualCloneWithModifiedParameter =
        liborVolatilityModelPiecewiseConstant.getCloneWithModifiedParameter(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualCloneWithModifiedParameter instanceof LIBORVolatilityModelPiecewiseConstant);
    assertTrue(
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneWithModifiedParameter)
                .getSimulationTimeDiscretization()
            instanceof TenorFromArray);
    assertTrue(
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneWithModifiedParameter)
                .getTimeToMaturityDiscretization()
            instanceof TenorFromArray);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedParameter.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TimeDiscretizationFromArray);
    assertEquals(1, actualCloneWithModifiedParameter.getParameter().length);
    assertSame(liborPeriodDiscretization, liborPeriodDiscretization2);
    assertArrayEquals(
        new double[] {10.0d}, actualCloneWithModifiedParameter.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModel LIBORVolatilityModelPiecewiseConstant.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter_thenThrowIllegalArgumentException() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant liborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(15.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            liborVolatilityModelPiecewiseConstant.getCloneWithModifiedParameter(
                new RandomVariable[] {
                  randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(15.0d)
                }));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelPiecewiseConstant#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <ul>
   *   <li>When empty array of {@link RandomVariable}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModel LIBORVolatilityModelPiecewiseConstant.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter_whenEmptyArrayOfRandomVariable() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant liborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);

    // Act
    LIBORVolatilityModel actualCloneWithModifiedParameter =
        liborVolatilityModelPiecewiseConstant.getCloneWithModifiedParameter(
            new RandomVariable[] {});

    // Assert
    assertTrue(actualCloneWithModifiedParameter instanceof LIBORVolatilityModelPiecewiseConstant);
    TimeDiscretization liborPeriodDiscretization2 =
        actualCloneWithModifiedParameter.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameter.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization simulationTimeDiscretization2 =
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneWithModifiedParameter)
            .getSimulationTimeDiscretization();
    assertTrue(simulationTimeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeToMaturityDiscretization =
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneWithModifiedParameter)
            .getTimeToMaturityDiscretization();
    assertTrue(timeToMaturityDiscretization instanceof TenorFromArray);
    assertEquals(timeDiscretization2, simulationTimeDiscretization2);
    assertEquals(timeDiscretization2, timeToMaturityDiscretization);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelPiecewiseConstant#getVolatility(int, int)}.
   *
   * <p>Method under test: {@link LIBORVolatilityModelPiecewiseConstant#getVolatility(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelPiecewiseConstant.getVolatility(int, int)"
  })
  public void testGetVolatility() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant liborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(0.5d, 0.5d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            10.0d);

    // Act
    RandomVariable actualVolatility = liborVolatilityModelPiecewiseConstant.getVolatility(1, 1);

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
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link LIBORVolatilityModelPiecewiseConstant#getVolatility(int, int)}.
   *
   * <p>Method under test: {@link LIBORVolatilityModelPiecewiseConstant#getVolatility(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelPiecewiseConstant.getVolatility(int, int)"
  })
  public void testGetVolatility2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(0.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant liborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(0.5d, 0.5d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
            10.0d);

    // Act
    RandomVariable actualVolatility = liborVolatilityModelPiecewiseConstant.getVolatility(1, 1);

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
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link LIBORVolatilityModelPiecewiseConstant#getVolatility(int, int)}.
   *
   * <ul>
   *   <li>Then abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModelPiecewiseConstant#getVolatility(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelPiecewiseConstant.getVolatility(int, int)"
  })
  public void testGetVolatility_thenAbsReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization =
        new TenorFromArray(0.5d, 0.5d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);

    LIBORVolatilityModelPiecewiseConstant liborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);

    // Act
    RandomVariable actualVolatility = liborVolatilityModelPiecewiseConstant.getVolatility(1, 1);

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
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link LIBORVolatilityModelPiecewiseConstant#getVolatility(int, int)}.
   *
   * <ul>
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModelPiecewiseConstant#getVolatility(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelPiecewiseConstant.getVolatility(int, int)"
  })
  public void testGetVolatility_thenReturnAverageIsZero() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant liborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);

    // Act
    RandomVariable actualVolatility = liborVolatilityModelPiecewiseConstant.getVolatility(1, 1);

    // Assert
    assertTrue(actualVolatility instanceof Scalar);
    assertEquals(0.0d, actualVolatility.getAverage(), 0.0);
    assertEquals(0.0d, actualVolatility.getMax(), 0.0);
    assertEquals(0.0d, actualVolatility.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link LIBORVolatilityModelPiecewiseConstant#clone()}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModelPiecewiseConstant#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORVolatilityModelPiecewiseConstant.clone()"})
  public void testClone_thenReturnArrayLengthIsOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {15.0d, 20.0d, 15.0d, 20.0d});
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant liborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);

    // Act
    Object actualCloneResult = liborVolatilityModelPiecewiseConstant.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORVolatilityModelPiecewiseConstant);
    assertTrue(
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneResult)
                .getSimulationTimeDiscretization()
            instanceof TenorFromArray);
    assertTrue(
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneResult)
                .getTimeToMaturityDiscretization()
            instanceof TenorFromArray);
    assertEquals(
        1, ((LIBORVolatilityModelPiecewiseConstant) actualCloneResult).getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneResult).getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d},
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneResult).getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelPiecewiseConstant#clone()}.
   *
   * <ul>
   *   <li>Then TimeDiscretization return {@link TenorFromArray}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModelPiecewiseConstant#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORVolatilityModelPiecewiseConstant.clone()"})
  public void testClone_thenTimeDiscretizationReturnTenorFromArray() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant liborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            new TenorFromArray(10.0d, 10, 0.5d),
            10.0d);

    // Act
    Object actualCloneResult = liborVolatilityModelPiecewiseConstant.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORVolatilityModelPiecewiseConstant);
    TimeDiscretization timeDiscretization2 =
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneResult).getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization simulationTimeDiscretization2 =
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneResult)
            .getSimulationTimeDiscretization();
    assertTrue(simulationTimeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeToMaturityDiscretization =
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneResult)
            .getTimeToMaturityDiscretization();
    assertTrue(timeToMaturityDiscretization instanceof TenorFromArray);
    assertEquals(
        0, ((LIBORVolatilityModelPiecewiseConstant) actualCloneResult).getParameter().length);
    assertEquals(timeDiscretization2, simulationTimeDiscretization2);
    assertEquals(timeDiscretization2, timeToMaturityDiscretization);
    assertSame(
        liborPeriodDiscretization,
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneResult).getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LIBORVolatilityModelPiecewiseConstant#getSimulationTimeDiscretization()}
   *   <li>{@link LIBORVolatilityModelPiecewiseConstant#getTimeToMaturityDiscretization()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization LIBORVolatilityModelPiecewiseConstant.getSimulationTimeDiscretization()",
    "TimeDiscretization LIBORVolatilityModelPiecewiseConstant.getTimeToMaturityDiscretization()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant liborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            timeToMaturityDiscretization,
            10.0d);

    // Act
    TimeDiscretization actualSimulationTimeDiscretization =
        liborVolatilityModelPiecewiseConstant.getSimulationTimeDiscretization();

    // Assert
    assertSame(simulationTimeDiscretization, actualSimulationTimeDiscretization);
    assertSame(
        timeToMaturityDiscretization,
        liborVolatilityModelPiecewiseConstant.getTimeToMaturityDiscretization());
  }

  /**
   * Test {@link LIBORVolatilityModelPiecewiseConstant#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Then return {@link LIBORVolatilityModelPiecewiseConstant}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelPiecewiseConstant#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModel LIBORVolatilityModelPiecewiseConstant.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_thenReturnLIBORVolatilityModelPiecewiseConstant() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray simulationTimeDiscretization = new TenorFromArray(new double[] {});
    TenorFromArray timeToMaturityDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelPiecewiseConstant liborVolatilityModelPiecewiseConstant =
        new LIBORVolatilityModelPiecewiseConstant(
            timeDiscretization,
            liborPeriodDiscretization,
            simulationTimeDiscretization,
            timeToMaturityDiscretization,
            10.0d);

    // Act
    LIBORVolatilityModel actualCloneWithModifiedData =
        liborVolatilityModelPiecewiseConstant.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof LIBORVolatilityModelPiecewiseConstant);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization simulationTimeDiscretization2 =
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneWithModifiedData)
            .getSimulationTimeDiscretization();
    assertTrue(simulationTimeDiscretization2 instanceof TenorFromArray);
    assertEquals(0, actualCloneWithModifiedData.getParameter().length);
    assertSame(simulationTimeDiscretization, simulationTimeDiscretization2);
    assertSame(
        liborPeriodDiscretization, actualCloneWithModifiedData.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertSame(
        timeToMaturityDiscretization,
        ((LIBORVolatilityModelPiecewiseConstant) actualCloneWithModifiedData)
            .getTimeToMaturityDiscretization());
  }
}
