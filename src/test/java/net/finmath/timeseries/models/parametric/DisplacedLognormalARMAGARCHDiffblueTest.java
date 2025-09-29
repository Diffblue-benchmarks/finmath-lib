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

public class DisplacedLognormalARMAGARCHDiffblueTest {
  /**
   * Test {@link DisplacedLognormalARMAGARCH#DisplacedLognormalARMAGARCH(TimeSeries, double,
   * double)}.
   *
   * <p>Method under test: {@link
   * DisplacedLognormalARMAGARCH#DisplacedLognormalARMAGARCH(TimeSeries, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisplacedLognormalARMAGARCH.<init>(TimeSeries, double, double)"})
  public void testNewDisplacedLognormalARMAGARCH() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertArrayEquals(
        new String[] {"omega", "alpha", "beta", "displacement", "theta", "mu"},
        new DisplacedLognormalARMAGARCH(timeSeries, 10.0d, 10.0d).getParameterNames());
  }

  /**
   * Test {@link DisplacedLognormalARMAGARCH#DisplacedLognormalARMAGARCH(TimeSeries)}.
   *
   * <ul>
   *   <li>Then return BestParameters size is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisplacedLognormalARMAGARCH#DisplacedLognormalARMAGARCH(TimeSeries)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisplacedLognormalARMAGARCH.<init>(TimeSeries)"})
  public void testNewDisplacedLognormalARMAGARCH_thenReturnBestParametersSizeIsTen() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    DisplacedLognormalARMAGARCH actualDisplacedLognormalARMAGARCH =
        new DisplacedLognormalARMAGARCH(timeSeries);

    // Assert
    Map<String, Object> bestParameters = actualDisplacedLognormalARMAGARCH.getBestParameters();
    assertEquals(10, bestParameters.size());
    assertTrue(bestParameters.containsKey("Alpha"));
    assertTrue(bestParameters.containsKey("Likelihood"));
    assertTrue(bestParameters.containsKey("Mu"));
    assertTrue(bestParameters.containsKey("Szenarios"));
    assertTrue(bestParameters.containsKey("Vol"));
    assertTrue(bestParameters.containsKey("parameters"));
    assertArrayEquals(
        new String[] {"omega", "alpha", "beta", "displacement", "theta", "mu"},
        actualDisplacedLognormalARMAGARCH.getParameterNames());
  }

  /**
   * Test {@link DisplacedLognormalARMAGARCH#DisplacedLognormalARMAGARCH(TimeSeries, double)}.
   *
   * <ul>
   *   <li>Then return BestParameters size is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisplacedLognormalARMAGARCH#DisplacedLognormalARMAGARCH(TimeSeries, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisplacedLognormalARMAGARCH.<init>(TimeSeries, double)"})
  public void testNewDisplacedLognormalARMAGARCH_thenReturnBestParametersSizeIsTen2() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    DisplacedLognormalARMAGARCH actualDisplacedLognormalARMAGARCH =
        new DisplacedLognormalARMAGARCH(timeSeries, 10.0d);

    // Assert
    Map<String, Object> bestParameters = actualDisplacedLognormalARMAGARCH.getBestParameters();
    assertEquals(10, bestParameters.size());
    assertTrue(bestParameters.containsKey("Alpha"));
    assertTrue(bestParameters.containsKey("Likelihood"));
    assertTrue(bestParameters.containsKey("Mu"));
    assertTrue(bestParameters.containsKey("Szenarios"));
    assertTrue(bestParameters.containsKey("Vol"));
    assertTrue(bestParameters.containsKey("parameters"));
    assertArrayEquals(
        new String[] {"omega", "alpha", "beta", "displacement", "theta", "mu"},
        actualDisplacedLognormalARMAGARCH.getParameterNames());
  }

  /**
   * Test {@link DisplacedLognormalARMAGARCH#getLogLikelihoodForParameters(double[])}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisplacedLognormalARMAGARCH#getLogLikelihoodForParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DisplacedLognormalARMAGARCH.getLogLikelihoodForParameters(double[])"})
  public void testGetLogLikelihoodForParameters_thenReturnNaN() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double actualLogLikelihoodForParameters =
        new DisplacedLognormalARMAGARCH(timeSeries)
            .getLogLikelihoodForParameters(
                new double[] {
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY
                });

    // Assert
    assertEquals(Double.NaN, actualLogLikelihoodForParameters, 0.0);
  }

  /**
   * Test {@link DisplacedLognormalARMAGARCH#getLastResidualForParameters(double[])}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisplacedLognormalARMAGARCH#getLastResidualForParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DisplacedLognormalARMAGARCH.getLastResidualForParameters(double[])"})
  public void testGetLastResidualForParameters_thenReturnNaN() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double actualLastResidualForParameters =
        new DisplacedLognormalARMAGARCH(timeSeries)
            .getLastResidualForParameters(
                new double[] {
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY
                });

    // Assert
    assertEquals(Double.NaN, actualLastResidualForParameters, 0.0);
  }

  /**
   * Test {@link DisplacedLognormalARMAGARCH#getSzenarios(double[])}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@link Double#NaN} and {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalARMAGARCH#getSzenarios(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLognormalARMAGARCH.getSzenarios(double[])"})
  public void testGetSzenarios_thenReturnArrayOfDoubleWithNaNAndNaN() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double[] actualSzenarios =
        new DisplacedLognormalARMAGARCH(timeSeries)
            .getSzenarios(
                new double[] {
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY
                });

    // Assert
    assertArrayEquals(new double[] {Double.NaN, Double.NaN, Double.NaN}, actualSzenarios, 0.0);
  }

  /**
   * Test {@link DisplacedLognormalARMAGARCH#getCloneCalibrated(TimeSeries)}.
   *
   * <ul>
   *   <li>Then return {@link DisplacedLognormalARMAGARCH}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalARMAGARCH#getCloneCalibrated(TimeSeries)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeSeriesModelParametric DisplacedLognormalARMAGARCH.getCloneCalibrated(TimeSeries)"
  })
  public void testGetCloneCalibrated_thenReturnDisplacedLognormalARMAGARCH() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    DisplacedLognormalARMAGARCH displacedLognormalARMAGARCH =
        new DisplacedLognormalARMAGARCH(timeSeries);
    TimeSeriesFromArray timeSeries2 =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    TimeSeriesModelParametric actualCloneCalibrated =
        displacedLognormalARMAGARCH.getCloneCalibrated(timeSeries2);

    // Assert
    assertTrue(actualCloneCalibrated instanceof DisplacedLognormalARMAGARCH);
    Map<String, Object> bestParameters =
        ((DisplacedLognormalARMAGARCH) actualCloneCalibrated).getBestParameters();
    assertEquals(10, bestParameters.size());
    assertTrue(bestParameters.containsKey("Alpha"));
    assertTrue(bestParameters.containsKey("Likelihood"));
    assertTrue(bestParameters.containsKey("Mu"));
    assertTrue(bestParameters.containsKey("Szenarios"));
    assertTrue(bestParameters.containsKey("Vol"));
    assertTrue(bestParameters.containsKey("parameters"));
    assertArrayEquals(
        new String[] {"omega", "alpha", "beta", "displacement", "theta", "mu"},
        actualCloneCalibrated.getParameterNames());
  }

  /**
   * Test {@link DisplacedLognormalARMAGARCH#getParameterNames()}.
   *
   * <p>Method under test: {@link DisplacedLognormalARMAGARCH#getParameterNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DisplacedLognormalARMAGARCH.getParameterNames()"})
  public void testGetParameterNames() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertArrayEquals(
        new String[] {"omega", "alpha", "beta", "displacement", "theta", "mu"},
        new DisplacedLognormalARMAGARCH(timeSeries).getParameterNames());
  }
}
