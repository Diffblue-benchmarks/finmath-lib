package net.finmath.functions;

import static org.junit.Assert.assertArrayEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.optimizer.SolverException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SABRModelDiffblueTest {
  /**
   * Test {@link SABRModel#sabrCalibrateParameterForImpliedNormalVols(double, double, double[],
   * double[])} with {@code underlying}, {@code maturity}, {@code givenStrikes}, {@code
   * givenVolatilities}.
   *
   * <p>Method under test: {@link SABRModel#sabrCalibrateParameterForImpliedNormalVols(double,
   * double, double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] SABRModel.sabrCalibrateParameterForImpliedNormalVols(double, double, double[], double[])"
  })
  public void
      testSabrCalibrateParameterForImpliedNormalVolsWithUnderlyingMaturityGivenStrikesGivenVolatilities()
          throws SolverException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        SABRModel.sabrCalibrateParameterForImpliedNormalVols(
            10.0d,
            10.0d,
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY},
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY}),
        0.0);
  }

  /**
   * Test {@link SABRModel#sabrCalibrateParameterForImpliedNormalVols(double, double, double[],
   * double[])} with {@code underlying}, {@code maturity}, {@code givenStrikes}, {@code
   * givenVolatilities}.
   *
   * <p>Method under test: {@link SABRModel#sabrCalibrateParameterForImpliedNormalVols(double,
   * double, double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] SABRModel.sabrCalibrateParameterForImpliedNormalVols(double, double, double[], double[])"
  })
  public void
      testSabrCalibrateParameterForImpliedNormalVolsWithUnderlyingMaturityGivenStrikesGivenVolatilities2()
          throws SolverException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        SABRModel.sabrCalibrateParameterForImpliedNormalVols(
            1.0d,
            10.0d,
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY},
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY}),
        0.0);
  }

  /**
   * Test {@link SABRModel#sabrCalibrateParameterForImpliedNormalVols(double, double, double[],
   * double[], double[], double[], double[], double[])} with {@code underlying}, {@code maturity},
   * {@code givenStrikes}, {@code givenVolatilities}, {@code parameterInitialValues}, {@code
   * parameterSteps}, {@code parameterLowerBound}, {@code parameterUpperBound}.
   *
   * <p>Method under test: {@link SABRModel#sabrCalibrateParameterForImpliedNormalVols(double,
   * double, double[], double[], double[], double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] SABRModel.sabrCalibrateParameterForImpliedNormalVols(double, double, double[], double[], double[], double[], double[], double[])"
  })
  public void
      testSabrCalibrateParameterForImpliedNormalVolsWithUnderlyingMaturityGivenStrikesGivenVolatilitiesParameterInitialValuesParameterStepsParameterLowerBoundParameterUpperBound()
          throws SolverException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d},
        SABRModel.sabrCalibrateParameterForImpliedNormalVols(
            10.0d,
            10.0d,
            new double[] {},
            new double[] {},
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY},
            new double[] {0.015625d, 10.0d, 0.015625d, 10.0d},
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY},
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY}),
        0.0);
  }

  /**
   * Test {@link SABRModel#sabrCalibrateParameterForImpliedNormalVols(double, double, double[],
   * double[], double[], double[], double[], double[])} with {@code underlying}, {@code maturity},
   * {@code givenStrikes}, {@code givenVolatilities}, {@code parameterInitialValues}, {@code
   * parameterSteps}, {@code parameterLowerBound}, {@code parameterUpperBound}.
   *
   * <p>Method under test: {@link SABRModel#sabrCalibrateParameterForImpliedNormalVols(double,
   * double, double[], double[], double[], double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] SABRModel.sabrCalibrateParameterForImpliedNormalVols(double, double, double[], double[], double[], double[], double[], double[])"
  })
  public void
      testSabrCalibrateParameterForImpliedNormalVolsWithUnderlyingMaturityGivenStrikesGivenVolatilitiesParameterInitialValuesParameterStepsParameterLowerBoundParameterUpperBound2()
          throws SolverException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY},
        SABRModel.sabrCalibrateParameterForImpliedNormalVols(
            10.0d,
            10.0d,
            new double[] {},
            new double[] {0.001d, 3.0d, 0.001d, 3.0d, 0.001d, 3.0d, 0.001d, 3.0d},
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY},
            new double[] {0.015625d, 10.0d, 0.015625d, 10.0d},
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY},
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY}),
        0.0);
  }

  /**
   * Test {@link SABRModel#sabrCalibrateParameterForImpliedNormalVols(double, double, double[],
   * double[], double[], double[], double[], double[])} with {@code underlying}, {@code maturity},
   * {@code givenStrikes}, {@code givenVolatilities}, {@code parameterInitialValues}, {@code
   * parameterSteps}, {@code parameterLowerBound}, {@code parameterUpperBound}.
   *
   * <p>Method under test: {@link SABRModel#sabrCalibrateParameterForImpliedNormalVols(double,
   * double, double[], double[], double[], double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] SABRModel.sabrCalibrateParameterForImpliedNormalVols(double, double, double[], double[], double[], double[], double[], double[])"
  })
  public void
      testSabrCalibrateParameterForImpliedNormalVolsWithUnderlyingMaturityGivenStrikesGivenVolatilitiesParameterInitialValuesParameterStepsParameterLowerBoundParameterUpperBound3()
          throws SolverException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {},
        SABRModel.sabrCalibrateParameterForImpliedNormalVols(
            10.0d,
            10.0d,
            new double[] {},
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY},
            new double[] {},
            new double[] {0.015625d, 10.0d, 0.015625d, 10.0d},
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY},
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY}),
        0.0);
  }

  /**
   * Test {@link SABRModel#sabrCalibrateParameterForImpliedNormalVols(double, double, double[],
   * double[], double[], double[])} with {@code underlying}, {@code maturity}, {@code givenStrikes},
   * {@code givenVolatilities}, {@code parameterLowerBound}, {@code parameterUpperBound}.
   *
   * <p>Method under test: {@link SABRModel#sabrCalibrateParameterForImpliedNormalVols(double,
   * double, double[], double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] SABRModel.sabrCalibrateParameterForImpliedNormalVols(double, double, double[], double[], double[], double[])"
  })
  public void
      testSabrCalibrateParameterForImpliedNormalVolsWithUnderlyingMaturityGivenStrikesGivenVolatilitiesParameterLowerBoundParameterUpperBound()
          throws SolverException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        SABRModel.sabrCalibrateParameterForImpliedNormalVols(
            10.0d,
            10.0d,
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY},
            new double[] {
              0.006d, 0.05d, 0.006d, 0.05d, 0.006d, 0.05d, 0.006d, 0.05d, 0.006d, 0.05d, 0.006d,
              0.05d
            },
            new double[] {0.006d, 0.05d, 0.006d, 0.05d, 0.006d, 0.05d, 0.006d, 0.05d},
            new double[] {0.006d, 0.05d, 0.006d, 0.05d, 0.006d, 0.05d, 0.006d, 0.05d}),
        0.0);
  }

  /**
   * Test {@link SABRModel#sabrCalibrateParameterForImpliedNormalVols(double, double, double[],
   * double[], double[], double[])} with {@code underlying}, {@code maturity}, {@code givenStrikes},
   * {@code givenVolatilities}, {@code parameterLowerBound}, {@code parameterUpperBound}.
   *
   * <p>Method under test: {@link SABRModel#sabrCalibrateParameterForImpliedNormalVols(double,
   * double, double[], double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] SABRModel.sabrCalibrateParameterForImpliedNormalVols(double, double, double[], double[], double[], double[])"
  })
  public void
      testSabrCalibrateParameterForImpliedNormalVolsWithUnderlyingMaturityGivenStrikesGivenVolatilitiesParameterLowerBoundParameterUpperBound2()
          throws SolverException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {0.006d, 0.05d, 0.006d, 0.05d, 0.006d},
        SABRModel.sabrCalibrateParameterForImpliedNormalVols(
            10.0d,
            10.0d,
            new double[] {},
            new double[] {0.006d, 0.05d, 0.006d, 0.05d, 0.006d, 0.05d, 0.006d, 0.05d},
            new double[] {0.006d, 0.05d, 0.006d, 0.05d, 0.006d, 0.05d, 0.006d, 0.05d},
            new double[] {0.006d, 0.05d, 0.006d, 0.05d, 0.006d, 0.05d, 0.006d, 0.05d}),
        0.0);
  }
}
