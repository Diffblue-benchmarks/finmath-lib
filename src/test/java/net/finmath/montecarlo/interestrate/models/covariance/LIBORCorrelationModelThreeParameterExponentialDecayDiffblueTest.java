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

public class LIBORCorrelationModelThreeParameterExponentialDecayDiffblueTest {
  /**
   * Test {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#LIBORCorrelationModelThreeParameterExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double, double, double, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#LIBORCorrelationModelThreeParameterExponentialDecay(TimeDiscretization,
   * TimeDiscretization, int, double, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCorrelationModelThreeParameterExponentialDecay.<init>(TimeDiscretization, TimeDiscretization, int, double, double, double, boolean)"
  })
  public void testNewLIBORCorrelationModelThreeParameterExponentialDecay() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORCorrelationModelThreeParameterExponentialDecay
        actualLiborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization, liborPeriodDiscretization, 3, 10.0d, 10.0d, 10.0d, true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborCorrelationModelThreeParameterExponentialDecay.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(3, actualLiborCorrelationModelThreeParameterExponentialDecay.getNumberOfFactors());
    assertEquals(
        3, actualLiborCorrelationModelThreeParameterExponentialDecay.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborCorrelationModelThreeParameterExponentialDecay.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualLiborCorrelationModelThreeParameterExponentialDecay.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getParameter()}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCorrelationModelThreeParameterExponentialDecay.getParameter()"
  })
  public void testGetParameter_thenFirstElementReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                3,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    RandomVariable[] actualParameter =
        liborCorrelationModelThreeParameterExponentialDecay.getParameter();

    // Assert
    assertTrue(actualParameter[0] instanceof Scalar);
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[2] instanceof Scalar);
    assertEquals(3, actualParameter.length);
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getParameter()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCorrelationModelThreeParameterExponentialDecay.getParameter()"
  })
  public void testGetParameter_thenReturnNull() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                3,
                10.0d,
                10.0d,
                10.0d,
                false);

    // Act and Assert
    assertNull(liborCorrelationModelThreeParameterExponentialDecay.getParameter());
  }

  /**
   * Test {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORCorrelationModelThreeParameterExponentialDecay LIBORCorrelationModelThreeParameterExponentialDecay.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                3,
                10.0d,
                10.0d,
                10.0d,
                false);

    // Act
    LIBORCorrelationModelThreeParameterExponentialDecay actualCloneWithModifiedParameter =
        liborCorrelationModelThreeParameterExponentialDecay.getCloneWithModifiedParameter(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertSame(
        liborCorrelationModelThreeParameterExponentialDecay, actualCloneWithModifiedParameter);
  }

  /**
   * Test {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORCorrelationModelThreeParameterExponentialDecay LIBORCorrelationModelThreeParameterExponentialDecay.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter_thenFirstElementReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                3,
                10.0d,
                10.0d,
                10.0d,
                true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    LIBORCorrelationModelThreeParameterExponentialDecay actualCloneWithModifiedParameter =
        liborCorrelationModelThreeParameterExponentialDecay.getCloneWithModifiedParameter(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    RandomVariable[] parameter = actualCloneWithModifiedParameter.getParameter();
    assertTrue(parameter[0] instanceof Scalar);
    assertTrue(parameter[1] instanceof Scalar);
    assertTrue(parameter[2] instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameter.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameter.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(3, parameter.length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameter.getParameterAsDouble(),
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
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getFactorLoading(int, int,
   * int)}.
   *
   * <ul>
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getFactorLoading(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelThreeParameterExponentialDecay.getFactorLoading(int, int, int)"
  })
  public void testGetFactorLoading_thenReturn00() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act and Assert
    assertEquals(
        -0.0d, liborCorrelationModelThreeParameterExponentialDecay.getFactorLoading(1, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getFactorLoading(int, int,
   * int)}.
   *
   * <ul>
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getFactorLoading(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelThreeParameterExponentialDecay.getFactorLoading(int, int, int)"
  })
  public void testGetFactorLoading_thenReturn002() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act and Assert
    assertEquals(
        -0.0d, liborCorrelationModelThreeParameterExponentialDecay.getFactorLoading(3, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getFactorLoading(int, int,
   * int)}.
   *
   * <ul>
   *   <li>Then return {@code -1.2029681201141284E-16}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getFactorLoading(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelThreeParameterExponentialDecay.getFactorLoading(int, int, int)"
  })
  public void testGetFactorLoading_thenReturn12029681201141284e16() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                4,
                10.0d,
                0.0d,
                10.0d,
                true);

    // Act and Assert
    assertEquals(
        -1.2029681201141284E-16d,
        liborCorrelationModelThreeParameterExponentialDecay.getFactorLoading(1, 3, 3),
        0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getFactorLoading(int, int,
   * int)}.
   *
   * <ul>
   *   <li>Then return {@code 0.35464082609210534}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getFactorLoading(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelThreeParameterExponentialDecay.getFactorLoading(int, int, int)"
  })
  public void testGetFactorLoading_thenReturn035464082609210534() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10,
                10.0d,
                0.5d,
                10.0d,
                true);

    // Act and Assert
    assertEquals(
        0.35464082609210534d,
        liborCorrelationModelThreeParameterExponentialDecay.getFactorLoading(1, 3, 3),
        0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getFactorLoading(int, int,
   * int)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getFactorLoading(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelThreeParameterExponentialDecay.getFactorLoading(int, int, int)"
  })
  public void testGetFactorLoading_whenTen_thenReturn00() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act and Assert
    assertEquals(
        -0.0d, liborCorrelationModelThreeParameterExponentialDecay.getFactorLoading(10, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getFactorLoading(int, int,
   * int)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getFactorLoading(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelThreeParameterExponentialDecay.getFactorLoading(int, int, int)"
  })
  public void testGetFactorLoading_whenTwo_thenReturn00() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act and Assert
    assertEquals(
        -0.0d, liborCorrelationModelThreeParameterExponentialDecay.getFactorLoading(2, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getCorrelation(int, int, int)}.
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getCorrelation(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelThreeParameterExponentialDecay.getCorrelation(int, int, int)"
  })
  public void testGetCorrelation() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                3,
                10.0d,
                0.0d,
                10.0d,
                true);

    // Act and Assert
    assertEquals(
        1.0d, liborCorrelationModelThreeParameterExponentialDecay.getCorrelation(1, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getCorrelation(int, int, int)}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getCorrelation(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelThreeParameterExponentialDecay.getCorrelation(int, int, int)"
  })
  public void testGetCorrelation_thenReturnOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                3,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act and Assert
    assertEquals(
        1.0d, liborCorrelationModelThreeParameterExponentialDecay.getCorrelation(1, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getCorrelation(int, int, int)}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getCorrelation(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelThreeParameterExponentialDecay.getCorrelation(int, int, int)"
  })
  public void testGetCorrelation_thenReturnOne2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                3,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act and Assert
    assertEquals(
        1.0d, liborCorrelationModelThreeParameterExponentialDecay.getCorrelation(3, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getCorrelation(int, int, int)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getCorrelation(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelThreeParameterExponentialDecay.getCorrelation(int, int, int)"
  })
  public void testGetCorrelation_whenTen_thenReturnOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                3,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act and Assert
    assertEquals(
        1.0d, liborCorrelationModelThreeParameterExponentialDecay.getCorrelation(10, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getCorrelation(int, int, int)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getCorrelation(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORCorrelationModelThreeParameterExponentialDecay.getCorrelation(int, int, int)"
  })
  public void testGetCorrelation_whenTwo_thenReturnOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                3,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act and Assert
    assertEquals(
        1.0d, liborCorrelationModelThreeParameterExponentialDecay.getCorrelation(2, 3, 3), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getNumberOfFactors()}.
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int LIBORCorrelationModelThreeParameterExponentialDecay.getNumberOfFactors()"
  })
  public void testGetNumberOfFactors() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                3,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act and Assert
    assertEquals(3, liborCorrelationModelThreeParameterExponentialDecay.getNumberOfFactors());
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#clone()}.
   *
   * <ul>
   *   <li>Then LiborPeriodDiscretization return {@link TenorFromArray}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCorrelationModelThreeParameterExponentialDecay#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCorrelationModelThreeParameterExponentialDecay.clone()"})
  public void testClone_thenLiborPeriodDiscretizationReturnTenorFromArray() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                3,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    Object actualCloneResult = liborCorrelationModelThreeParameterExponentialDecay.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORCorrelationModelThreeParameterExponentialDecay);
    TimeDiscretization liborPeriodDiscretization =
        ((LIBORCorrelationModelThreeParameterExponentialDecay) actualCloneResult)
            .getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        ((LIBORCorrelationModelThreeParameterExponentialDecay) actualCloneResult)
            .getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        ((LIBORCorrelationModelThreeParameterExponentialDecay) actualCloneResult)
            .getParameterAsDouble(),
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
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#clone()}.
   *
   * <ul>
   *   <li>Then return NumberOfFactors is one.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCorrelationModelThreeParameterExponentialDecay#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCorrelationModelThreeParameterExponentialDecay.clone()"})
  public void testClone_thenReturnNumberOfFactorsIsOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {1.0d, 10.0d, 1.0d, 10.0d});

    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization, liborPeriodDiscretization, 1, 10.0d, 10.0d, 10.0d, true);

    // Act
    Object actualCloneResult = liborCorrelationModelThreeParameterExponentialDecay.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORCorrelationModelThreeParameterExponentialDecay);
    assertEquals(
        1,
        ((LIBORCorrelationModelThreeParameterExponentialDecay) actualCloneResult)
            .getNumberOfFactors());
    assertEquals(
        3,
        ((LIBORCorrelationModelThreeParameterExponentialDecay) actualCloneResult)
            .getParameter()
            .length);
    assertSame(
        liborPeriodDiscretization,
        ((LIBORCorrelationModelThreeParameterExponentialDecay) actualCloneResult)
            .getLiborPeriodDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        ((LIBORCorrelationModelThreeParameterExponentialDecay) actualCloneResult)
            .getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#clone()}.
   *
   * <ul>
   *   <li>Then return ParameterAsDouble is array of {@code double} with ten and zero.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCorrelationModelThreeParameterExponentialDecay#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCorrelationModelThreeParameterExponentialDecay.clone()"})
  public void testClone_thenReturnParameterAsDoubleIsArrayOfDoubleWithTenAndZero() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                3,
                10.0d,
                0.0d,
                10.0d,
                true);

    // Act
    Object actualCloneResult = liborCorrelationModelThreeParameterExponentialDecay.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORCorrelationModelThreeParameterExponentialDecay);
    TimeDiscretization liborPeriodDiscretization =
        ((LIBORCorrelationModelThreeParameterExponentialDecay) actualCloneResult)
            .getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 =
        ((LIBORCorrelationModelThreeParameterExponentialDecay) actualCloneResult)
            .getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {10.0d, 0.0d, 10.0d},
        ((LIBORCorrelationModelThreeParameterExponentialDecay) actualCloneResult)
            .getParameterAsDouble(),
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
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORCorrelationModel LIBORCorrelationModelThreeParameterExponentialDecay.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenHashMap() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                3,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    LIBORCorrelationModel actualCloneWithModifiedData =
        liborCorrelationModelThreeParameterExponentialDecay.getCloneWithModifiedData(
            new HashMap<>());

    // Assert
    assertTrue(
        actualCloneWithModifiedData instanceof LIBORCorrelationModelThreeParameterExponentialDecay);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
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
   * Test {@link LIBORCorrelationModelThreeParameterExponentialDecay#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCorrelationModelThreeParameterExponentialDecay#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORCorrelationModel LIBORCorrelationModelThreeParameterExponentialDecay.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenNull() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelThreeParameterExponentialDecay
        liborCorrelationModelThreeParameterExponentialDecay =
            new LIBORCorrelationModelThreeParameterExponentialDecay(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                3,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    LIBORCorrelationModel actualCloneWithModifiedData =
        liborCorrelationModelThreeParameterExponentialDecay.getCloneWithModifiedData(null);

    // Assert
    assertTrue(
        actualCloneWithModifiedData instanceof LIBORCorrelationModelThreeParameterExponentialDecay);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
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
