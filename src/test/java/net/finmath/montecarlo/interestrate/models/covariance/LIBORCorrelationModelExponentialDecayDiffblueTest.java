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
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LIBORCorrelationModelExponentialDecayDiffblueTest {
  /**
   * Test {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double)}.
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCorrelationModelExponentialDecay.<init>(TimeDiscretization, TimeDiscretization, int, double)"
  })
  public void testNewLIBORCorrelationModelExponentialDecay() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORCorrelationModelExponentialDecay actualLiborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, liborPeriodDiscretization, 3, 10.0d);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborCorrelationModelExponentialDecay.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertSame(
        liborPeriodDiscretization,
        actualLiborCorrelationModelExponentialDecay.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double)}.
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCorrelationModelExponentialDecay.<init>(TimeDiscretization, TimeDiscretization, int, double)"
  })
  public void testNewLIBORCorrelationModelExponentialDecay2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 10.0d);

    // Act
    LIBORCorrelationModelExponentialDecay actualLiborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, liborPeriodDiscretization, 3, 10.0d);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborCorrelationModelExponentialDecay.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertSame(
        liborPeriodDiscretization,
        actualLiborCorrelationModelExponentialDecay.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCorrelationModelExponentialDecay.<init>(TimeDiscretization, TimeDiscretization, int, double, boolean)"
  })
  public void testNewLIBORCorrelationModelExponentialDecay3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORCorrelationModelExponentialDecay actualLiborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, liborPeriodDiscretization, 3, 10.0d, true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborCorrelationModelExponentialDecay.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertSame(
        liborPeriodDiscretization,
        actualLiborCorrelationModelExponentialDecay.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d},
        actualLiborCorrelationModelExponentialDecay.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCorrelationModelExponentialDecay.<init>(TimeDiscretization, TimeDiscretization, int, double, boolean)"
  })
  public void testNewLIBORCorrelationModelExponentialDecay4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 10.0d);

    // Act
    LIBORCorrelationModelExponentialDecay actualLiborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, liborPeriodDiscretization, 3, 10.0d, true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborCorrelationModelExponentialDecay.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertSame(
        liborPeriodDiscretization,
        actualLiborCorrelationModelExponentialDecay.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d},
        actualLiborCorrelationModelExponentialDecay.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCorrelationModelExponentialDecay.<init>(TimeDiscretization, TimeDiscretization, int, double, boolean)"
  })
  public void testNewLIBORCorrelationModelExponentialDecay5() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(
            new double[] {-0.12069849283645878d, -1.0d, -0.12069849283645878d, -1.0d});

    // Act
    LIBORCorrelationModelExponentialDecay actualLiborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, liborPeriodDiscretization, 1, Double.NaN, true);

    // Assert
    RandomVariable[] parameter = actualLiborCorrelationModelExponentialDecay.getParameter();
    assertTrue(parameter[0] instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborCorrelationModelExponentialDecay.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualLiborCorrelationModelExponentialDecay.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, parameter.length);
    assertArrayEquals(
        new double[] {Double.NaN},
        actualLiborCorrelationModelExponentialDecay.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {-1.0d, -0.120662100456621d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double, boolean)}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCorrelationModelExponentialDecay.<init>(TimeDiscretization, TimeDiscretization, int, double, boolean)"
  })
  public void testNewLIBORCorrelationModelExponentialDecay_thenFirstElementReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(
            new double[] {-0.12069849283645878d, -1.0d, -0.12069849283645878d, -1.0d});

    // Act
    LIBORCorrelationModelExponentialDecay actualLiborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, liborPeriodDiscretization, 1, 10.0d, true);

    // Assert
    RandomVariable[] parameter = actualLiborCorrelationModelExponentialDecay.getParameter();
    assertTrue(parameter[0] instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization2 =
        actualLiborCorrelationModelExponentialDecay.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization2 instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualLiborCorrelationModelExponentialDecay.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, parameter.length);
    assertArrayEquals(
        new double[] {10.0d},
        actualLiborCorrelationModelExponentialDecay.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {-1.0d, -0.120662100456621d},
        liborPeriodDiscretization2.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double)}.
   *
   * <ul>
   *   <li>Then return NumberOfFactors is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCorrelationModelExponentialDecay.<init>(TimeDiscretization, TimeDiscretization, int, double)"
  })
  public void testNewLIBORCorrelationModelExponentialDecay_thenReturnNumberOfFactorsIsOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(
            new double[] {-0.12069849283645878d, -1.0d, -0.12069849283645878d, -1.0d});

    // Act
    LIBORCorrelationModelExponentialDecay actualLiborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, liborPeriodDiscretization, 1, 10.0d);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborCorrelationModelExponentialDecay.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualLiborCorrelationModelExponentialDecay.getNumberOfFactors());
    assertSame(
        liborPeriodDiscretization,
        actualLiborCorrelationModelExponentialDecay.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double)}.
   *
   * <ul>
   *   <li>Then return NumberOfFactors is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelExponentialDecay#LIBORCorrelationModelExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCorrelationModelExponentialDecay.<init>(TimeDiscretization, TimeDiscretization, int, double)"
  })
  public void testNewLIBORCorrelationModelExponentialDecay_thenReturnNumberOfFactorsIsOne2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(
            new double[] {-0.12069849283645878d, -1.0d, -0.12069849283645878d, -1.0d});

    // Act
    LIBORCorrelationModelExponentialDecay actualLiborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, liborPeriodDiscretization, 1, Double.NaN);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborCorrelationModelExponentialDecay.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualLiborCorrelationModelExponentialDecay.getNumberOfFactors());
    assertSame(
        liborPeriodDiscretization,
        actualLiborCorrelationModelExponentialDecay.getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCorrelationModelExponentialDecay#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelExponentialDecay#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORCorrelationModelExponentialDecay LIBORCorrelationModelExponentialDecay.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    // Act
    LIBORCorrelationModelExponentialDecay actualCloneWithModifiedParameter =
        liborCorrelationModelExponentialDecay.getCloneWithModifiedParameter(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertSame(liborCorrelationModelExponentialDecay, actualCloneWithModifiedParameter);
  }

  /**
   * Test {@link
   * LIBORCorrelationModelExponentialDecay#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelExponentialDecay#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORCorrelationModelExponentialDecay LIBORCorrelationModelExponentialDecay.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, true);

    // Act
    LIBORCorrelationModelExponentialDecay actualCloneWithModifiedParameter =
        liborCorrelationModelExponentialDecay.getCloneWithModifiedParameter(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertSame(liborCorrelationModelExponentialDecay, actualCloneWithModifiedParameter);
  }

  /**
   * Test {@link
   * LIBORCorrelationModelExponentialDecay#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelExponentialDecay#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORCorrelationModelExponentialDecay LIBORCorrelationModelExponentialDecay.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3, 2.0d, true);

    // Act
    LIBORCorrelationModelExponentialDecay actualCloneWithModifiedParameter =
        liborCorrelationModelExponentialDecay.getCloneWithModifiedParameter(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameter.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameter.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
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
   * Test {@link LIBORCorrelationModelExponentialDecay#clone()}.
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCorrelationModelExponentialDecay.clone()"})
  public void testClone() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, liborPeriodDiscretization, 3, 10.0d);

    // Act
    Object actualCloneResult = liborCorrelationModelExponentialDecay.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORCorrelationModelExponentialDecay);
    TimeDiscretization timeDiscretization2 =
        ((LIBORCorrelationModelExponentialDecay) actualCloneResult).getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertSame(
        liborPeriodDiscretization,
        ((LIBORCorrelationModelExponentialDecay) actualCloneResult).getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#clone()}.
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCorrelationModelExponentialDecay.clone()"})
  public void testClone2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 10.0d);

    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, liborPeriodDiscretization, 3, 10.0d);

    // Act
    Object actualCloneResult = liborCorrelationModelExponentialDecay.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORCorrelationModelExponentialDecay);
    TimeDiscretization timeDiscretization2 =
        ((LIBORCorrelationModelExponentialDecay) actualCloneResult).getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertSame(
        liborPeriodDiscretization,
        ((LIBORCorrelationModelExponentialDecay) actualCloneResult).getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#clone()}.
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCorrelationModelExponentialDecay.clone()"})
  public void testClone3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(
            new double[] {-0.12069849283645878d, -1.0d, -0.12069849283645878d, -1.0d});

    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, liborPeriodDiscretization, 1, 10.0d);

    // Act
    Object actualCloneResult = liborCorrelationModelExponentialDecay.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORCorrelationModelExponentialDecay);
    TimeDiscretization timeDiscretization2 =
        ((LIBORCorrelationModelExponentialDecay) actualCloneResult).getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        1, ((LIBORCorrelationModelExponentialDecay) actualCloneResult).getNumberOfFactors());
    assertSame(
        liborPeriodDiscretization,
        ((LIBORCorrelationModelExponentialDecay) actualCloneResult).getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#clone()}.
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCorrelationModelExponentialDecay.clone()"})
  public void testClone4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(
            new double[] {-0.12069849283645878d, -1.0d, -0.12069849283645878d, -1.0d});

    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, liborPeriodDiscretization, 1, Double.NaN);

    // Act
    Object actualCloneResult = liborCorrelationModelExponentialDecay.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORCorrelationModelExponentialDecay);
    TimeDiscretization timeDiscretization2 =
        ((LIBORCorrelationModelExponentialDecay) actualCloneResult).getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        1, ((LIBORCorrelationModelExponentialDecay) actualCloneResult).getNumberOfFactors());
    assertSame(
        liborPeriodDiscretization,
        ((LIBORCorrelationModelExponentialDecay) actualCloneResult).getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#getFactorLoading(int, int, int)}.
   *
   * <ul>
   *   <li>Then return {@code -0.4232258408068884}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#getFactorLoading(int, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelExponentialDecay.getFactorLoading(int, int, int)"
  })
  public void testGetFactorLoading_thenReturn04232258408068884() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d);

    // Act and Assert
    assertEquals(
        -0.4232258408068884d, liborCorrelationModelExponentialDecay.getFactorLoading(1, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#getFactorLoading(int, int, int)}.
   *
   * <ul>
   *   <li>Then return {@code -0.4232258408068884}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#getFactorLoading(int, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelExponentialDecay.getFactorLoading(int, int, int)"
  })
  public void testGetFactorLoading_thenReturn042322584080688842() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d);

    // Act and Assert
    assertEquals(
        -0.4232258408068884d, liborCorrelationModelExponentialDecay.getFactorLoading(3, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#getFactorLoading(int, int, int)}.
   *
   * <ul>
   *   <li>Then return {@code -0.4232258408068884}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#getFactorLoading(int, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelExponentialDecay.getFactorLoading(int, int, int)"
  })
  public void testGetFactorLoading_thenReturn042322584080688843() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10, 10.0d);

    // Act and Assert
    assertEquals(
        -0.4232258408068884d, liborCorrelationModelExponentialDecay.getFactorLoading(0, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#getFactorLoading(int, int, int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code 0.07138992487378489}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#getFactorLoading(int, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelExponentialDecay.getFactorLoading(int, int, int)"
  })
  public void testGetFactorLoading_whenZero_thenReturn007138992487378489() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    // Act and Assert
    assertEquals(
        0.07138992487378489d, liborCorrelationModelExponentialDecay.getFactorLoading(1, 0, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#getCorrelation(int, int, int)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#getCorrelation(int, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LIBORCorrelationModelExponentialDecay.getCorrelation(int, int, int)"})
  public void testGetCorrelation_whenMinusOne_thenReturnOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    // Act and Assert
    assertEquals(1.0d, liborCorrelationModelExponentialDecay.getCorrelation(-1, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#getCorrelation(int, int, int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#getCorrelation(int, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LIBORCorrelationModelExponentialDecay.getCorrelation(int, int, int)"})
  public void testGetCorrelation_whenOne_thenReturnOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    // Act and Assert
    assertEquals(1.0d, liborCorrelationModelExponentialDecay.getCorrelation(1, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#getCorrelation(int, int, int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#getCorrelation(int, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LIBORCorrelationModelExponentialDecay.getCorrelation(int, int, int)"})
  public void testGetCorrelation_whenThree_thenReturnOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    // Act and Assert
    assertEquals(1.0d, liborCorrelationModelExponentialDecay.getCorrelation(3, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#getCorrelation(int, int, int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#getCorrelation(int, int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LIBORCorrelationModelExponentialDecay.getCorrelation(int, int, int)"})
  public void testGetCorrelation_whenZero_thenReturnOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    // Act and Assert
    assertEquals(1.0d, liborCorrelationModelExponentialDecay.getCorrelation(0, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#getNumberOfFactors()}.
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LIBORCorrelationModelExponentialDecay.getNumberOfFactors()"})
  public void testGetNumberOfFactors() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    // Act and Assert
    assertEquals(3, liborCorrelationModelExponentialDecay.getNumberOfFactors());
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#getParameter()}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORCorrelationModelExponentialDecay.getParameter()"})
  public void testGetParameter_thenFirstElementReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, true);

    // Act
    RandomVariable[] actualParameter = liborCorrelationModelExponentialDecay.getParameter();

    // Assert
    assertTrue(actualParameter[0] instanceof Scalar);
    assertEquals(1, actualParameter.length);
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#getParameter()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCorrelationModelExponentialDecay#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORCorrelationModelExponentialDecay.getParameter()"})
  public void testGetParameter_thenReturnNull() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    // Act and Assert
    assertNull(liborCorrelationModelExponentialDecay.getParameter());
  }

  /**
   * Test {@link LIBORCorrelationModelExponentialDecay#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelExponentialDecay#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORCorrelationModel LIBORCorrelationModelExponentialDecay.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, liborPeriodDiscretization, 3, 10.0d);

    // Act
    LIBORCorrelationModel actualCloneWithModifiedData =
        liborCorrelationModelExponentialDecay.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof LIBORCorrelationModelExponentialDecay);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertNull(actualCloneWithModifiedData.getParameter());
    assertEquals(3, actualCloneWithModifiedData.getNumberOfFactors());
    assertSame(
        liborPeriodDiscretization, actualCloneWithModifiedData.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
  }
}
