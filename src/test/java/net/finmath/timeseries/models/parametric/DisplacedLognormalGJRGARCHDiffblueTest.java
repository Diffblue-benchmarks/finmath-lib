package net.finmath.timeseries.models.parametric;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import net.finmath.timeseries.TimeSeries;
import net.finmath.timeseries.TimeSeriesFromArray;
import net.finmath.timeseries.TimeSeriesModelParametric;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DisplacedLognormalGJRGARCHDiffblueTest {
  /**
   * Test {@link DisplacedLognormalGJRGARCH#DisplacedLognormalGJRGARCH(TimeSeries, double, double)}.
   *
   * <p>Method under test: {@link DisplacedLognormalGJRGARCH#DisplacedLognormalGJRGARCH(TimeSeries,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisplacedLognormalGJRGARCH.<init>(TimeSeries, double, double)"})
  public void testNewDisplacedLognormalGJRGARCH() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertArrayEquals(
        new String[] {"omega", "alpha", "beta", "mu", "gamma", "displacement"},
        new DisplacedLognormalGJRGARCH(timeSeries, 10.0d, 10.0d).getParameterNames());
  }

  /**
   * Test {@link DisplacedLognormalGJRGARCH#DisplacedLognormalGJRGARCH(TimeSeries)}.
   *
   * <ul>
   *   <li>Then return BestParameters size is ten.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGJRGARCH#DisplacedLognormalGJRGARCH(TimeSeries)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisplacedLognormalGJRGARCH.<init>(TimeSeries)"})
  public void testNewDisplacedLognormalGJRGARCH_thenReturnBestParametersSizeIsTen() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    DisplacedLognormalGJRGARCH actualDisplacedLognormalGJRGARCH =
        new DisplacedLognormalGJRGARCH(timeSeries);

    // Assert
    Map<String, Object> bestParameters = actualDisplacedLognormalGJRGARCH.getBestParameters();
    assertEquals(10, bestParameters.size());
    assertTrue(bestParameters.containsKey("Alpha"));
    assertTrue(bestParameters.containsKey("Likelihood"));
    assertTrue(bestParameters.containsKey("Mu"));
    assertTrue(bestParameters.containsKey("Szenarios"));
    assertTrue(bestParameters.containsKey("Vol"));
    assertTrue(bestParameters.containsKey("parameters"));
    assertArrayEquals(
        new String[] {"omega", "alpha", "beta", "mu", "gamma", "displacement"},
        actualDisplacedLognormalGJRGARCH.getParameterNames());
  }

  /**
   * Test {@link DisplacedLognormalGJRGARCH#DisplacedLognormalGJRGARCH(TimeSeries, double)}.
   *
   * <ul>
   *   <li>Then return BestParameters size is ten.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGJRGARCH#DisplacedLognormalGJRGARCH(TimeSeries,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisplacedLognormalGJRGARCH.<init>(TimeSeries, double)"})
  public void testNewDisplacedLognormalGJRGARCH_thenReturnBestParametersSizeIsTen2() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    DisplacedLognormalGJRGARCH actualDisplacedLognormalGJRGARCH =
        new DisplacedLognormalGJRGARCH(timeSeries, 10.0d);

    // Assert
    Map<String, Object> bestParameters = actualDisplacedLognormalGJRGARCH.getBestParameters();
    assertEquals(10, bestParameters.size());
    assertTrue(bestParameters.containsKey("Alpha"));
    assertTrue(bestParameters.containsKey("Likelihood"));
    assertTrue(bestParameters.containsKey("Mu"));
    assertTrue(bestParameters.containsKey("Szenarios"));
    assertTrue(bestParameters.containsKey("Vol"));
    assertTrue(bestParameters.containsKey("parameters"));
    assertArrayEquals(
        new String[] {"omega", "alpha", "beta", "mu", "gamma", "displacement"},
        actualDisplacedLognormalGJRGARCH.getParameterNames());
  }

  /**
   * Test {@link DisplacedLognormalGJRGARCH#getLogLikelihoodForParameters(double[])}.
   *
   * <p>Method under test: {@link
   * DisplacedLognormalGJRGARCH#getLogLikelihoodForParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DisplacedLognormalGJRGARCH.getLogLikelihoodForParameters(double[])"})
  public void testGetLogLikelihoodForParameters() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {
              1.0d,
              -1.7976931348623157E308d,
              1.0d,
              -1.7976931348623157E308d,
              1.0d,
              -1.7976931348623157E308d,
              1.0d,
              -1.7976931348623157E308d
            });

    // Act
    double actualLogLikelihoodForParameters =
        new DisplacedLognormalGJRGARCH(timeSeries)
            .getLogLikelihoodForParameters(
                new double[] {
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d
                });

    // Assert
    assertEquals(Double.NaN, actualLogLikelihoodForParameters, 0.0);
  }

  /**
   * Test {@link DisplacedLognormalGJRGARCH#getLogLikelihoodForParameters(double[])}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisplacedLognormalGJRGARCH#getLogLikelihoodForParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DisplacedLognormalGJRGARCH.getLogLikelihoodForParameters(double[])"})
  public void testGetLogLikelihoodForParameters_thenReturnNaN() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double actualLogLikelihoodForParameters =
        new DisplacedLognormalGJRGARCH(timeSeries)
            .getLogLikelihoodForParameters(
                new double[] {
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d
                });

    // Assert
    assertEquals(Double.NaN, actualLogLikelihoodForParameters, 0.0);
  }

  /**
   * Test {@link DisplacedLognormalGJRGARCH#getLastResidualForParameters(double[])}.
   *
   * <ul>
   *   <li>Then return {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGJRGARCH#getLastResidualForParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DisplacedLognormalGJRGARCH.getLastResidualForParameters(double[])"})
  public void testGetLastResidualForParameters_thenReturnNegative_infinity() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {
              1.0d,
              -1.7976931348623157E308d,
              1.0d,
              -1.7976931348623157E308d,
              1.0d,
              -1.7976931348623157E308d,
              1.0d,
              -1.7976931348623157E308d
            });

    // Act
    double actualLastResidualForParameters =
        new DisplacedLognormalGJRGARCH(timeSeries)
            .getLastResidualForParameters(
                new double[] {
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d
                });

    // Assert
    assertEquals(Double.NEGATIVE_INFINITY, actualLastResidualForParameters, 0.0);
  }

  /**
   * Test {@link DisplacedLognormalGJRGARCH#getLastResidualForParameters(double[])}.
   *
   * <ul>
   *   <li>Then return one hundred ten.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGJRGARCH#getLastResidualForParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DisplacedLognormalGJRGARCH.getLastResidualForParameters(double[])"})
  public void testGetLastResidualForParameters_thenReturnOneHundredTen() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double actualLastResidualForParameters =
        new DisplacedLognormalGJRGARCH(timeSeries)
            .getLastResidualForParameters(
                new double[] {
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d
                });

    // Assert
    assertEquals(110.0d, actualLastResidualForParameters, 0.0);
  }

  /**
   * Test {@link DisplacedLognormalGJRGARCH#getSzenarios(double[])}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@link Double#POSITIVE_INFINITY} and {@link
   *       Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGJRGARCH#getSzenarios(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLognormalGJRGARCH.getSzenarios(double[])"})
  public void testGetSzenarios_thenReturnArrayOfDoubleWithPositive_infinityAndNaN() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {
              1.0d,
              -1.7976931348623157E308d,
              1.0d,
              -1.7976931348623157E308d,
              1.0d,
              -1.7976931348623157E308d,
              1.0d,
              -1.7976931348623157E308d
            });

    // Act
    double[] actualSzenarios =
        new DisplacedLognormalGJRGARCH(timeSeries)
            .getSzenarios(
                new double[] {
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d
                });

    // Assert
    assertArrayEquals(
        new double[] {Double.POSITIVE_INFINITY, Double.NaN, Double.NaN}, actualSzenarios, 0.0);
  }

  /**
   * Test {@link DisplacedLognormalGJRGARCH#getSzenarios(double[])}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with zero and zero.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGJRGARCH#getSzenarios(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLognormalGJRGARCH.getSzenarios(double[])"})
  public void testGetSzenarios_thenReturnArrayOfDoubleWithZeroAndZero() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double[] actualSzenarios =
        new DisplacedLognormalGJRGARCH(timeSeries)
            .getSzenarios(
                new double[] {
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d,
                  10.0d,
                  -1.7976931348623157E308d
                });

    // Assert
    assertArrayEquals(new double[] {0.0d, 0.0d, Double.NaN}, actualSzenarios, 0.0);
  }

  /**
   * Test {@link DisplacedLognormalGJRGARCH#getCloneCalibrated(TimeSeries)}.
   *
   * <ul>
   *   <li>Then return {@link DisplacedLognormalGJRGARCH}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGJRGARCH#getCloneCalibrated(TimeSeries)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeSeriesModelParametric DisplacedLognormalGJRGARCH.getCloneCalibrated(TimeSeries)"
  })
  public void testGetCloneCalibrated_thenReturnDisplacedLognormalGJRGARCH() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    DisplacedLognormalGJRGARCH displacedLognormalGJRGARCH =
        new DisplacedLognormalGJRGARCH(timeSeries);
    TimeSeriesFromArray timeSeries2 =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    TimeSeriesModelParametric actualCloneCalibrated =
        displacedLognormalGJRGARCH.getCloneCalibrated(timeSeries2);

    // Assert
    assertTrue(actualCloneCalibrated instanceof DisplacedLognormalGJRGARCH);
    Map<String, Object> bestParameters =
        ((DisplacedLognormalGJRGARCH) actualCloneCalibrated).getBestParameters();
    assertEquals(10, bestParameters.size());
    assertTrue(bestParameters.containsKey("Alpha"));
    assertTrue(bestParameters.containsKey("Likelihood"));
    assertTrue(bestParameters.containsKey("Mu"));
    assertTrue(bestParameters.containsKey("Szenarios"));
    assertTrue(bestParameters.containsKey("Vol"));
    assertTrue(bestParameters.containsKey("parameters"));
    assertArrayEquals(
        new String[] {"omega", "alpha", "beta", "mu", "gamma", "displacement"},
        actualCloneCalibrated.getParameterNames());
  }

  /**
   * Test {@link DisplacedLognormalGJRGARCH#getParameterNames()}.
   *
   * <p>Method under test: {@link DisplacedLognormalGJRGARCH#getParameterNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DisplacedLognormalGJRGARCH.getParameterNames()"})
  public void testGetParameterNames() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertArrayEquals(
        new String[] {"omega", "alpha", "beta", "mu", "gamma", "displacement"},
        new DisplacedLognormalGJRGARCH(timeSeries).getParameterNames());
  }
}
