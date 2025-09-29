package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ShortRateVolatilityModelPiecewiseConstantDiffblueTest {
  /**
   * Test {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean)}.
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double[], double[], boolean)"
  })
  public void testNewShortRateVolatilityModelPiecewiseConstant() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ShortRateVolatilityModelPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                true));
  }

  /**
   * Test {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean)}.
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double[], double[], boolean)"
  })
  public void testNewShortRateVolatilityModelPiecewiseConstant2() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ShortRateVolatilityModelPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                new TimeDiscretizationFromArray(15.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                true));
  }

  /**
   * Test {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean)}.
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double[], double[], boolean)"
  })
  public void testNewShortRateVolatilityModelPiecewiseConstant3() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 3, 0.5d);
    TenorFromArray volatilityTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    ShortRateVolatilityModelPiecewiseConstant actualShortRateVolatilityModelPiecewiseConstant =
        new ShortRateVolatilityModelPiecewiseConstant(
            randomVariableFactory,
            timeDiscretization,
            volatilityTimeDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualShortRateVolatilityModelPiecewiseConstant.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization volatilityTimeDiscretization2 =
        actualShortRateVolatilityModelPiecewiseConstant.getVolatilityTimeDiscretization();
    assertTrue(volatilityTimeDiscretization2 instanceof TenorFromArray);
    assertEquals(4, actualShortRateVolatilityModelPiecewiseConstant.getParameter().length);
    assertSame(volatilityTimeDiscretization, volatilityTimeDiscretization2);
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d, 1.0d},
        actualShortRateVolatilityModelPiecewiseConstant.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean)}.
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double[], double[], boolean)"
  })
  public void testNewShortRateVolatilityModelPiecewiseConstant4() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 3, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ShortRateVolatilityModelPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {
                  0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d,
                  0.0d, 0.0d, 0.0d, 0.0d, 0.0d
                },
                true));
  }

  /**
   * Test {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)}.
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)"
  })
  public void testNewShortRateVolatilityModelPiecewiseConstant5() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ShortRateVolatilityModelPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                true,
                true));
  }

  /**
   * Test {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)}.
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)"
  })
  public void testNewShortRateVolatilityModelPiecewiseConstant6() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ShortRateVolatilityModelPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                new TimeDiscretizationFromArray(15.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                true,
                true));
  }

  /**
   * Test {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)}.
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)"
  })
  public void testNewShortRateVolatilityModelPiecewiseConstant7() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 3, 0.5d);
    TenorFromArray volatilityTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    ShortRateVolatilityModelPiecewiseConstant actualShortRateVolatilityModelPiecewiseConstant =
        new ShortRateVolatilityModelPiecewiseConstant(
            randomVariableFactory,
            timeDiscretization,
            volatilityTimeDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            true,
            true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualShortRateVolatilityModelPiecewiseConstant.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization volatilityTimeDiscretization2 =
        actualShortRateVolatilityModelPiecewiseConstant.getVolatilityTimeDiscretization();
    assertTrue(volatilityTimeDiscretization2 instanceof TenorFromArray);
    assertEquals(8, actualShortRateVolatilityModelPiecewiseConstant.getParameter().length);
    assertSame(volatilityTimeDiscretization, volatilityTimeDiscretization2);
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d},
        actualShortRateVolatilityModelPiecewiseConstant.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)}.
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)"
  })
  public void testNewShortRateVolatilityModelPiecewiseConstant8() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 3, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ShortRateVolatilityModelPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {
                  0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d,
                  0.0d, 0.0d, 0.0d, 0.0d, 0.0d
                },
                true,
                true));
  }

  /**
   * Test {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, RandomVariable[], RandomVariable[], boolean)}.
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, RandomVariable[], RandomVariable[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, RandomVariable[], RandomVariable[], boolean)"
  })
  public void testNewShortRateVolatilityModelPiecewiseConstant9() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray volatilityTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariable[] volatility = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] meanReversion =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ShortRateVolatilityModelPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                volatilityTimeDiscretization,
                volatility,
                meanReversion,
                true));
  }

  /**
   * Test {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, RandomVariable[], RandomVariable[], boolean)}.
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, RandomVariable[], RandomVariable[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, RandomVariable[], RandomVariable[], boolean)"
  })
  public void testNewShortRateVolatilityModelPiecewiseConstant10() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray volatilityTimeDiscretization =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    RandomVariable[] volatility = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] meanReversion =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    ShortRateVolatilityModelPiecewiseConstant actualShortRateVolatilityModelPiecewiseConstant =
        new ShortRateVolatilityModelPiecewiseConstant(
            randomVariableFactory,
            timeDiscretization,
            volatilityTimeDiscretization,
            volatility,
            meanReversion,
            true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualShortRateVolatilityModelPiecewiseConstant.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization volatilityTimeDiscretization2 =
        actualShortRateVolatilityModelPiecewiseConstant.getVolatilityTimeDiscretization();
    assertTrue(volatilityTimeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualShortRateVolatilityModelPiecewiseConstant.getParameter().length);
    assertSame(timeDiscretization, timeDiscretization2);
    assertSame(volatilityTimeDiscretization, volatilityTimeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d},
        actualShortRateVolatilityModelPiecewiseConstant.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, RandomVariable[], RandomVariable[], boolean, boolean)}.
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, RandomVariable[], RandomVariable[], boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, RandomVariable[], RandomVariable[], boolean, boolean)"
  })
  public void testNewShortRateVolatilityModelPiecewiseConstant11() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray volatilityTimeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariable[] volatility = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] meanReversion =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ShortRateVolatilityModelPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                volatilityTimeDiscretization,
                volatility,
                meanReversion,
                true,
                true));
  }

  /**
   * Test {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, RandomVariable[], RandomVariable[], boolean, boolean)}.
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, RandomVariable[], RandomVariable[], boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, RandomVariable[], RandomVariable[], boolean, boolean)"
  })
  public void testNewShortRateVolatilityModelPiecewiseConstant12() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray volatilityTimeDiscretization =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);
    RandomVariable[] volatility = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] meanReversion =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    ShortRateVolatilityModelPiecewiseConstant actualShortRateVolatilityModelPiecewiseConstant =
        new ShortRateVolatilityModelPiecewiseConstant(
            randomVariableFactory,
            timeDiscretization,
            volatilityTimeDiscretization,
            volatility,
            meanReversion,
            true,
            true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualShortRateVolatilityModelPiecewiseConstant.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    TimeDiscretization volatilityTimeDiscretization2 =
        actualShortRateVolatilityModelPiecewiseConstant.getVolatilityTimeDiscretization();
    assertTrue(volatilityTimeDiscretization2 instanceof TenorFromArray);
    assertEquals(2, actualShortRateVolatilityModelPiecewiseConstant.getParameter().length);
    assertSame(timeDiscretization, timeDiscretization2);
    assertSame(volatilityTimeDiscretization, volatilityTimeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        actualShortRateVolatilityModelPiecewiseConstant.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean)}.
   *
   * <ul>
   *   <li>When empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double[], double[], boolean)"
  })
  public void testNewShortRateVolatilityModelPiecewiseConstant_whenEmptyArrayOfDouble() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 3, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ShortRateVolatilityModelPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {},
                true));
  }

  /**
   * Test {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)}.
   *
   * <ul>
   *   <li>When empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ShortRateVolatilityModelPiecewiseConstant#ShortRateVolatilityModelPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShortRateVolatilityModelPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double[], double[], boolean, boolean)"
  })
  public void testNewShortRateVolatilityModelPiecewiseConstant_whenEmptyArrayOfDouble2() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 3, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ShortRateVolatilityModelPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {},
                true,
                true));
  }
}
