package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
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
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LIBORVolatilityModelTimeHomogenousPiecewiseConstantDiffblueTest {
  /**
   * Test {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelTimeHomogenousPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])"
  })
  public void testNewLIBORVolatilityModelTimeHomogenousPiecewiseConstant() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelTimeHomogenousPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                liborPeriodDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d}));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelTimeHomogenousPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])"
  })
  public void testNewLIBORVolatilityModelTimeHomogenousPiecewiseConstant2() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelTimeHomogenousPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                liborPeriodDiscretization,
                timeToMaturityDiscretization,
                new double[] {10.0d, 1.0d, 10.0d, 1.0d}));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelTimeHomogenousPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[])"
  })
  public void testNewLIBORVolatilityModelTimeHomogenousPiecewiseConstant3() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariable[] volatility = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelTimeHomogenousPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                liborPeriodDiscretization,
                timeToMaturityDiscretization,
                volatility));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelTimeHomogenousPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[])"
  })
  public void testNewLIBORVolatilityModelTimeHomogenousPiecewiseConstant4() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    RandomVariable[] volatility = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelTimeHomogenousPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                liborPeriodDiscretization,
                timeToMaturityDiscretization,
                volatility));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelTimeHomogenousPiecewiseConstant.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[])"
  })
  public void testNewLIBORVolatilityModelTimeHomogenousPiecewiseConstant5() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariable[] volatility =
        new RandomVariable[] {
          randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
        };

    // Act
    LIBORVolatilityModelTimeHomogenousPiecewiseConstant
        actualLiborVolatilityModelTimeHomogenousPiecewiseConstant =
            new LIBORVolatilityModelTimeHomogenousPiecewiseConstant(
                randomVariableFactory,
                timeDiscretization,
                liborPeriodDiscretization,
                timeToMaturityDiscretization,
                volatility);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelTimeHomogenousPiecewiseConstant.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelTimeHomogenousPiecewiseConstant.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertSame(
        volatility, actualLiborVolatilityModelTimeHomogenousPiecewiseConstant.getParameter());
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        actualLiborVolatilityModelTimeHomogenousPiecewiseConstant.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, double[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelTimeHomogenousPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])"
  })
  public void testNewLIBORVolatilityModelTimeHomogenousPiecewiseConstant6() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelTimeHomogenousPiecewiseConstant(
                timeDiscretization,
                liborPeriodDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d}));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, double[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelTimeHomogenousPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, double[])"
  })
  public void testNewLIBORVolatilityModelTimeHomogenousPiecewiseConstant7() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelTimeHomogenousPiecewiseConstant(
                timeDiscretization,
                liborPeriodDiscretization,
                timeToMaturityDiscretization,
                new double[] {10.0d, 1.0d, 10.0d, 1.0d}));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelTimeHomogenousPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[])"
  })
  public void testNewLIBORVolatilityModelTimeHomogenousPiecewiseConstant8() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariable[] volatility = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelTimeHomogenousPiecewiseConstant(
                timeDiscretization,
                liborPeriodDiscretization,
                timeToMaturityDiscretization,
                volatility));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelTimeHomogenousPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[])"
  })
  public void testNewLIBORVolatilityModelTimeHomogenousPiecewiseConstant9() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    RandomVariable[] volatility = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new LIBORVolatilityModelTimeHomogenousPiecewiseConstant(
                timeDiscretization,
                liborPeriodDiscretization,
                timeToMaturityDiscretization,
                volatility));
  }

  /**
   * Test {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTimeHomogenousPiecewiseConstant#LIBORVolatilityModelTimeHomogenousPiecewiseConstant(TimeDiscretization,
   * TimeDiscretization, TimeDiscretization, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelTimeHomogenousPiecewiseConstant.<init>(TimeDiscretization, TimeDiscretization, TimeDiscretization, RandomVariable[])"
  })
  public void testNewLIBORVolatilityModelTimeHomogenousPiecewiseConstant10() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeToMaturityDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariable[] volatility =
        new RandomVariable[] {
          randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
        };

    // Act
    LIBORVolatilityModelTimeHomogenousPiecewiseConstant
        actualLiborVolatilityModelTimeHomogenousPiecewiseConstant =
            new LIBORVolatilityModelTimeHomogenousPiecewiseConstant(
                timeDiscretization,
                liborPeriodDiscretization,
                timeToMaturityDiscretization,
                volatility);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelTimeHomogenousPiecewiseConstant.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelTimeHomogenousPiecewiseConstant.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertSame(
        volatility, actualLiborVolatilityModelTimeHomogenousPiecewiseConstant.getParameter());
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        actualLiborVolatilityModelTimeHomogenousPiecewiseConstant.getParameterAsDouble(),
        0.0);
  }
}
