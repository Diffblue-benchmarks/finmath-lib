package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TermStructureTenorTimeScalingPicewiseConstantDiffblueTest {
  /**
   * Test {@link
   * TermStructureTenorTimeScalingPicewiseConstant#TermStructureTenorTimeScalingPicewiseConstant(TimeDiscretization,
   * double[])}.
   *
   * <p>Method under test: {@link
   * TermStructureTenorTimeScalingPicewiseConstant#TermStructureTenorTimeScalingPicewiseConstant(TimeDiscretization,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TermStructureTenorTimeScalingPicewiseConstant.<init>(TimeDiscretization, double[])"
  })
  public void testNewTermStructureTenorTimeScalingPicewiseConstant() {
    // Arrange and Act
    TermStructureTenorTimeScalingPicewiseConstant
        actualTermStructureTenorTimeScalingPicewiseConstant =
            new TermStructureTenorTimeScalingPicewiseConstant(
                new TenorFromArray(-0.9d, -0.9d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START),
                new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    // Assert
    assertArrayEquals(
        new double[] {}, actualTermStructureTenorTimeScalingPicewiseConstant.getParameter(), 0.0);
  }

  /**
   * Test {@link
   * TermStructureTenorTimeScalingPicewiseConstant#TermStructureTenorTimeScalingPicewiseConstant(TimeDiscretization,
   * double[])}.
   *
   * <p>Method under test: {@link
   * TermStructureTenorTimeScalingPicewiseConstant#TermStructureTenorTimeScalingPicewiseConstant(TimeDiscretization,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TermStructureTenorTimeScalingPicewiseConstant.<init>(TimeDiscretization, double[])"
  })
  public void testNewTermStructureTenorTimeScalingPicewiseConstant2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {-0.9d, 9.0d, -0.9d, 9.0d});

    // Act
    TermStructureTenorTimeScalingPicewiseConstant
        actualTermStructureTenorTimeScalingPicewiseConstant =
            new TermStructureTenorTimeScalingPicewiseConstant(
                timeDiscretization, new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    // Assert
    assertArrayEquals(
        new double[] {0.09d},
        actualTermStructureTenorTimeScalingPicewiseConstant.getParameter(),
        0.0);
  }

  /**
   * Test {@link TermStructureTenorTimeScalingPicewiseConstant#getScaledTenorTime(double, double)}.
   *
   * <ul>
   *   <li>Then return ninety-five.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructureTenorTimeScalingPicewiseConstant#getScaledTenorTime(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double TermStructureTenorTimeScalingPicewiseConstant.getScaledTenorTime(double, double)"
  })
  public void testGetScaledTenorTime_thenReturnNinetyFive() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    TermStructureTenorTimeScalingPicewiseConstant termStructureTenorTimeScalingPicewiseConstant =
        new TermStructureTenorTimeScalingPicewiseConstant(
            timeDiscretization, new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    // Act and Assert
    assertEquals(
        95.0d, termStructureTenorTimeScalingPicewiseConstant.getScaledTenorTime(0.5d, 10.0d), 0.0);
  }

  /**
   * Test {@link TermStructureTenorTimeScalingPicewiseConstant#getScaledTenorTime(double, double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructureTenorTimeScalingPicewiseConstant#getScaledTenorTime(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double TermStructureTenorTimeScalingPicewiseConstant.getScaledTenorTime(double, double)"
  })
  public void testGetScaledTenorTime_thenReturnZero() {
    // Arrange
    TermStructureTenorTimeScalingPicewiseConstant termStructureTenorTimeScalingPicewiseConstant =
        new TermStructureTenorTimeScalingPicewiseConstant(
            new TenorFromArray(10.0d, 3, 0.5d), new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    // Act and Assert
    assertEquals(
        0.0d, termStructureTenorTimeScalingPicewiseConstant.getScaledTenorTime(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link TermStructureTenorTimeScalingPicewiseConstant#getScaledTenorTime(double, double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructureTenorTimeScalingPicewiseConstant#getScaledTenorTime(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double TermStructureTenorTimeScalingPicewiseConstant.getScaledTenorTime(double, double)"
  })
  public void testGetScaledTenorTime_thenReturnZero2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    TermStructureTenorTimeScalingPicewiseConstant termStructureTenorTimeScalingPicewiseConstant =
        new TermStructureTenorTimeScalingPicewiseConstant(
            timeDiscretization, new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    // Act and Assert
    assertEquals(
        0.0d, termStructureTenorTimeScalingPicewiseConstant.getScaledTenorTime(0.5d, 0.5d), 0.0);
  }

  /**
   * Test {@link TermStructureTenorTimeScalingPicewiseConstant#getScaledTenorTime(double, double)}.
   *
   * <ul>
   *   <li>When nine.
   *   <li>Then return eighty-five.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructureTenorTimeScalingPicewiseConstant#getScaledTenorTime(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double TermStructureTenorTimeScalingPicewiseConstant.getScaledTenorTime(double, double)"
  })
  public void testGetScaledTenorTime_whenNine_thenReturnEightyFive() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    TermStructureTenorTimeScalingPicewiseConstant termStructureTenorTimeScalingPicewiseConstant =
        new TermStructureTenorTimeScalingPicewiseConstant(
            timeDiscretization, new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    // Act and Assert
    assertEquals(
        85.0d, termStructureTenorTimeScalingPicewiseConstant.getScaledTenorTime(9.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link TermStructureTenorTimeScalingPicewiseConstant#getScaledTenorTime(double, double)}.
   *
   * <ul>
   *   <li>When nine.
   *   <li>Then return eighty-five.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructureTenorTimeScalingPicewiseConstant#getScaledTenorTime(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double TermStructureTenorTimeScalingPicewiseConstant.getScaledTenorTime(double, double)"
  })
  public void testGetScaledTenorTime_whenNine_thenReturnEightyFive2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    TermStructureTenorTimeScalingPicewiseConstant termStructureTenorTimeScalingPicewiseConstant =
        new TermStructureTenorTimeScalingPicewiseConstant(
            timeDiscretization, new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    // Act and Assert
    assertEquals(
        85.0d, termStructureTenorTimeScalingPicewiseConstant.getScaledTenorTime(0.5d, 9.0d), 0.0);
  }

  /**
   * Test {@link
   * TermStructureTenorTimeScalingPicewiseConstant#getCloneWithModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link
   * TermStructureTenorTimeScalingPicewiseConstant#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TermStructureTenorTimeScaling TermStructureTenorTimeScalingPicewiseConstant.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParameters() {
    // Arrange
    TermStructureTenorTimeScalingPicewiseConstant termStructureTenorTimeScalingPicewiseConstant =
        new TermStructureTenorTimeScalingPicewiseConstant(
            new TenorFromArray(10.0d, 1, 0.5d), new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    // Act
    TermStructureTenorTimeScaling actualCloneWithModifiedParameters =
        termStructureTenorTimeScalingPicewiseConstant.getCloneWithModifiedParameters(
            new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    // Assert
    assertTrue(
        actualCloneWithModifiedParameters instanceof TermStructureTenorTimeScalingPicewiseConstant);
    assertArrayEquals(new double[] {0.09d}, actualCloneWithModifiedParameters.getParameter(), 0.0);
  }

  /**
   * Test {@link TermStructureTenorTimeScalingPicewiseConstant#getParameter()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@code 0.09}.
   * </ul>
   *
   * <p>Method under test: {@link TermStructureTenorTimeScalingPicewiseConstant#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] TermStructureTenorTimeScalingPicewiseConstant.getParameter()"})
  public void testGetParameter_thenReturnArrayOfDoubleWith009() {
    // Arrange
    TermStructureTenorTimeScalingPicewiseConstant termStructureTenorTimeScalingPicewiseConstant =
        new TermStructureTenorTimeScalingPicewiseConstant(
            new TenorFromArray(10.0d, 1, 0.5d), new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    // Act and Assert
    assertArrayEquals(
        new double[] {0.09d}, termStructureTenorTimeScalingPicewiseConstant.getParameter(), 0.0);
  }

  /**
   * Test {@link TermStructureTenorTimeScalingPicewiseConstant#clone()}.
   *
   * <ul>
   *   <li>Then return {@link TermStructureTenorTimeScalingPicewiseConstant}.
   * </ul>
   *
   * <p>Method under test: {@link TermStructureTenorTimeScalingPicewiseConstant#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TermStructureTenorTimeScaling TermStructureTenorTimeScalingPicewiseConstant.clone()"
  })
  public void testClone_thenReturnTermStructureTenorTimeScalingPicewiseConstant() {
    // Arrange
    TermStructureTenorTimeScalingPicewiseConstant termStructureTenorTimeScalingPicewiseConstant =
        new TermStructureTenorTimeScalingPicewiseConstant(
            new TenorFromArray(10.0d, 1, 0.5d), new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    // Act
    TermStructureTenorTimeScaling actualCloneResult =
        termStructureTenorTimeScalingPicewiseConstant.clone();

    // Assert
    assertTrue(actualCloneResult instanceof TermStructureTenorTimeScalingPicewiseConstant);
    assertSame(termStructureTenorTimeScalingPicewiseConstant, actualCloneResult);
    assertArrayEquals(new double[] {0.09d}, actualCloneResult.getParameter(), 0.0);
  }
}
