package net.finmath.timeseries.models.parametric;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import net.finmath.timeseries.HistoricalSimulationModel;
import net.finmath.timeseries.TimeSeries;
import net.finmath.timeseries.TimeSeriesFromArray;
import net.finmath.timeseries.TimeSeriesModelParametric;
import net.finmath.timeseries.TimeSeriesView;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ARMAGARCHDiffblueTest {
  /**
   * Test {@link ARMAGARCH#ARMAGARCH(TimeSeries)}.
   *
   * <p>Method under test: {@link ARMAGARCH#ARMAGARCH(TimeSeries)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ARMAGARCH.<init>(TimeSeries)"})
  public void testNewArmagarch() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    ARMAGARCH actualArmagarch = new ARMAGARCH(timeSeries);

    // Assert
    Map<String, Object> bestParameters = actualArmagarch.getBestParameters();
    assertEquals(15, bestParameters.size());
    assertEquals(-1.1829427073333256d, ((Double) bestParameters.get("Mu")).doubleValue(), 0.0);
    assertEquals(
        0.4998843565074168d, ((Double) bestParameters.get("Quantile=05%")).doubleValue(), 0.0);
    assertEquals(
        0.49988786179414246d, ((Double) bestParameters.get("Quantile=2%")).doubleValue(), 0.0);
    assertEquals(
        0.5000000439484146d, ((Double) bestParameters.get("Quantile=50%")).doubleValue(), 0.0);
    assertEquals(
        1.2224014911497503d, ((Double) bestParameters.get("Likelihood")).doubleValue(), 0.0);
    assertEquals(3.6731104066068537E-8d, ((Double) bestParameters.get("Omega")).doubleValue(), 0.0);
    assertArrayEquals(
        new double[] {
          3.6731104066068537E-8d,
          0.9999999855231231d,
          1.076467435676411E-8d,
          -1.3052036603629182d,
          -1.1829427073333256d,
          -0.6048034105757336d
        },
        actualArmagarch.getParameters(),
        0.0);
    assertArrayEquals(
        new String[] {"omega", "alpha", "beta", "theta", "mu", "phi"},
        actualArmagarch.getParameterNames());
  }

  /**
   * Test {@link ARMAGARCH#getLogLikelihoodForParameters(double[])}.
   *
   * <p>Method under test: {@link ARMAGARCH#getLogLikelihoodForParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ARMAGARCH.getLogLikelihoodForParameters(double[])"})
  public void testGetLogLikelihoodForParameters() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double actualLogLikelihoodForParameters =
        new ARMAGARCH(timeSeries)
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
   * Test {@link ARMAGARCH#getLogLikelihoodForParameters(double[])}.
   *
   * <p>Method under test: {@link ARMAGARCH#getLogLikelihoodForParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ARMAGARCH.getLogLikelihoodForParameters(double[])"})
  public void testGetLogLikelihoodForParameters2() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {0.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double actualLogLikelihoodForParameters =
        new ARMAGARCH(timeSeries)
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
   * Test {@link ARMAGARCH#getLogLikelihoodForParameters(double[])}.
   *
   * <p>Method under test: {@link ARMAGARCH#getLogLikelihoodForParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ARMAGARCH.getLogLikelihoodForParameters(double[])"})
  public void testGetLogLikelihoodForParameters3() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {-0.5d, 0.5d, 10.0d, 0.5d});

    // Act
    double actualLogLikelihoodForParameters =
        new ARMAGARCH(timeSeries)
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
   * Test {@link ARMAGARCH#getLogLikelihoodForParameters(double[])}.
   *
   * <p>Method under test: {@link ARMAGARCH#getLogLikelihoodForParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ARMAGARCH.getLogLikelihoodForParameters(double[])"})
  public void testGetLogLikelihoodForParameters4() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.0d, 10.0d, 0.5d});

    // Act
    double actualLogLikelihoodForParameters =
        new ARMAGARCH(timeSeries)
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
   * Test {@link ARMAGARCH#getLogLikelihoodForParameters(double[])}.
   *
   * <p>Method under test: {@link ARMAGARCH#getLogLikelihoodForParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ARMAGARCH.getLogLikelihoodForParameters(double[])"})
  public void testGetLogLikelihoodForParameters5() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, -0.5d, 10.0d, 0.5d});

    // Act
    double actualLogLikelihoodForParameters =
        new ARMAGARCH(timeSeries)
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
   * Test {@link ARMAGARCH#getLastResidualForParameters(double[])}.
   *
   * <p>Method under test: {@link ARMAGARCH#getLastResidualForParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ARMAGARCH.getLastResidualForParameters(double[])"})
  public void testGetLastResidualForParameters() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {0.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double actualLastResidualForParameters =
        new ARMAGARCH(timeSeries)
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
   * Test {@link ARMAGARCH#getLastResidualForParameters(double[])}.
   *
   * <p>Method under test: {@link ARMAGARCH#getLastResidualForParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ARMAGARCH.getLastResidualForParameters(double[])"})
  public void testGetLastResidualForParameters2() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {-0.5d, 0.5d, 10.0d, 0.5d});

    // Act
    double actualLastResidualForParameters =
        new ARMAGARCH(timeSeries)
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
   * Test {@link ARMAGARCH#getLastResidualForParameters(double[])}.
   *
   * <ul>
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link ARMAGARCH#getLastResidualForParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ARMAGARCH.getLastResidualForParameters(double[])"})
  public void testGetLastResidualForParameters_thenReturnNaN() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double actualLastResidualForParameters =
        new ARMAGARCH(timeSeries)
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
   * Test {@link ARMAGARCH#getSzenarios(double[])}.
   *
   * <p>Method under test: {@link ARMAGARCH#getSzenarios(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ARMAGARCH.getSzenarios(double[])"})
  public void testGetSzenarios() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {0.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double[] actualSzenarios =
        new ARMAGARCH(timeSeries)
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
   * Test {@link ARMAGARCH#getSzenarios(double[])}.
   *
   * <p>Method under test: {@link ARMAGARCH#getSzenarios(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ARMAGARCH.getSzenarios(double[])"})
  public void testGetSzenarios2() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {-0.5d, 0.5d, 10.0d, 0.5d});

    // Act
    double[] actualSzenarios =
        new ARMAGARCH(timeSeries)
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
   * Test {@link ARMAGARCH#getSzenarios(double[])}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@link Double#NaN} and {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link ARMAGARCH#getSzenarios(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ARMAGARCH.getSzenarios(double[])"})
  public void testGetSzenarios_thenReturnArrayOfDoubleWithNaNAndNaN() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double[] actualSzenarios =
        new ARMAGARCH(timeSeries)
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
   * Test {@link ARMAGARCH#getQuantilPredictionsForParameters(double[], double[])}.
   *
   * <p>Method under test: {@link ARMAGARCH#getQuantilPredictionsForParameters(double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ARMAGARCH.getQuantilPredictionsForParameters(double[], double[])"})
  public void testGetQuantilPredictionsForParameters() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {0.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double[] actualQuantilPredictionsForParameters =
        new ARMAGARCH(timeSeries)
            .getQuantilPredictionsForParameters(
                new double[] {
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY
                },
                new double[] {});

    // Assert
    assertArrayEquals(new double[] {}, actualQuantilPredictionsForParameters, 0.0);
  }

  /**
   * Test {@link ARMAGARCH#getQuantilPredictionsForParameters(double[], double[])}.
   *
   * <p>Method under test: {@link ARMAGARCH#getQuantilPredictionsForParameters(double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ARMAGARCH.getQuantilPredictionsForParameters(double[], double[])"})
  public void testGetQuantilPredictionsForParameters2() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {-0.5d, 0.5d, 10.0d, 0.5d});

    // Act
    double[] actualQuantilPredictionsForParameters =
        new ARMAGARCH(timeSeries)
            .getQuantilPredictionsForParameters(
                new double[] {
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY
                },
                new double[] {});

    // Assert
    assertArrayEquals(new double[] {}, actualQuantilPredictionsForParameters, 0.0);
  }

  /**
   * Test {@link ARMAGARCH#getQuantilPredictionsForParameters(double[], double[])}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with ten and ten.
   * </ul>
   *
   * <p>Method under test: {@link ARMAGARCH#getQuantilPredictionsForParameters(double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ARMAGARCH.getQuantilPredictionsForParameters(double[], double[])"})
  public void testGetQuantilPredictionsForParameters_thenReturnArrayOfDoubleWithTenAndTen() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {1.0d, 10.0d, 1.0d, 10.0d}, new double[] {1.0d, 10.0d, 1.0d, 10.0d});

    // Act
    double[] actualQuantilPredictionsForParameters =
        new ARMAGARCH(new TimeSeriesView(timeSeries, 1, 1))
            .getQuantilPredictionsForParameters(
                new double[] {
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY
                },
                new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});

    // Assert
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualQuantilPredictionsForParameters, 0.0);
  }

  /**
   * Test {@link ARMAGARCH#getQuantilPredictionsForParameters(double[], double[])}.
   *
   * <ul>
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link ARMAGARCH#getQuantilPredictionsForParameters(double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ARMAGARCH.getQuantilPredictionsForParameters(double[], double[])"})
  public void testGetQuantilPredictionsForParameters_thenReturnEmptyArrayOfDouble() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double[] actualQuantilPredictionsForParameters =
        new ARMAGARCH(timeSeries)
            .getQuantilPredictionsForParameters(
                new double[] {
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY,
                  10.0d,
                  Double.NEGATIVE_INFINITY
                },
                new double[] {});

    // Assert
    assertArrayEquals(new double[] {}, actualQuantilPredictionsForParameters, 0.0);
  }

  /**
   * Test {@link ARMAGARCH#getBestParameters()}.
   *
   * <ul>
   *   <li>Then return size is fifteen.
   * </ul>
   *
   * <p>Method under test: {@link ARMAGARCH#getBestParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ARMAGARCH.getBestParameters()"})
  public void testGetBestParameters_thenReturnSizeIsFifteen() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {1000.0d, 0.001d, 1000.0d, 0.001d},
            new double[] {1000.0d, 0.001d, 1000.0d, 0.001d});

    // Act
    Map<String, Object> actualBestParameters =
        new ARMAGARCH(new TimeSeriesView(timeSeries, 1, 1)).getBestParameters();

    // Assert
    assertEquals(15, actualBestParameters.size());
    assertEquals(
        -0.14717592771753568d, ((Double) actualBestParameters.get("Theta")).doubleValue(), 0.0);
    assertEquals(
        -0.3177692269172398d, ((Double) actualBestParameters.get("Mu")).doubleValue(), 0.0);
    assertEquals(
        -0.40131555529420465d, ((Double) actualBestParameters.get("Phi")).doubleValue(), 0.0);
    assertEquals(
        -10.033547193458146d, ((Double) actualBestParameters.get("Likelihood")).doubleValue(), 0.0);
    assertEquals(0.001d, ((Double) actualBestParameters.get("Quantile=05%")).doubleValue(), 0.0);
    assertEquals(0.001d, ((Double) actualBestParameters.get("Quantile=1%")).doubleValue(), 0.0);
    assertEquals(0.001d, ((Double) actualBestParameters.get("Quantile=2%")).doubleValue(), 0.0);
    assertEquals(0.001d, ((Double) actualBestParameters.get("Quantile=50%")).doubleValue(), 0.0);
    assertEquals(
        0.3373103342020376d, ((Double) actualBestParameters.get("Beta")).doubleValue(), 0.0);
    assertEquals(
        1.4986064281735574d, ((Double) actualBestParameters.get("Omega")).doubleValue(), 0.0);
    assertEquals(
        13.815510611587666d, ((Double) actualBestParameters.get("Vol")).doubleValue(), 0.0);
  }

  /**
   * Test {@link ARMAGARCH#getCloneCalibrated(TimeSeries)}.
   *
   * <p>Method under test: {@link ARMAGARCH#getCloneCalibrated(TimeSeries)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeSeriesModelParametric ARMAGARCH.getCloneCalibrated(TimeSeries)"})
  public void testGetCloneCalibrated() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    ARMAGARCH armagarch = new ARMAGARCH(timeSeries);
    TimeSeriesFromArray timeSeries2 =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    TimeSeriesModelParametric actualCloneCalibrated = armagarch.getCloneCalibrated(timeSeries2);

    // Assert
    assertTrue(actualCloneCalibrated instanceof ARMAGARCH);
    Map<String, Object> bestParameters = ((ARMAGARCH) actualCloneCalibrated).getBestParameters();
    assertEquals(15, bestParameters.size());
    assertEquals(-1.1829427073333256d, ((Double) bestParameters.get("Mu")).doubleValue(), 0.0);
    assertEquals(
        0.4998843565074168d, ((Double) bestParameters.get("Quantile=05%")).doubleValue(), 0.0);
    assertEquals(
        0.49988786179414246d, ((Double) bestParameters.get("Quantile=2%")).doubleValue(), 0.0);
    assertEquals(
        0.5000000439484146d, ((Double) bestParameters.get("Quantile=50%")).doubleValue(), 0.0);
    assertEquals(
        1.2224014911497503d, ((Double) bestParameters.get("Likelihood")).doubleValue(), 0.0);
    assertEquals(3.6731104066068537E-8d, ((Double) bestParameters.get("Omega")).doubleValue(), 0.0);
    assertArrayEquals(
        new double[] {
          3.6731104066068537E-8d,
          0.9999999855231231d,
          1.076467435676411E-8d,
          -1.3052036603629182d,
          -1.1829427073333256d,
          -0.6048034105757336d
        },
        actualCloneCalibrated.getParameters(),
        0.0);
    assertArrayEquals(
        new String[] {"omega", "alpha", "beta", "theta", "mu", "phi"},
        actualCloneCalibrated.getParameterNames());
  }

  /**
   * Test {@link ARMAGARCH#getCloneWithWindow(int, int)}.
   *
   * <p>Method under test: {@link ARMAGARCH#getCloneWithWindow(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"HistoricalSimulationModel ARMAGARCH.getCloneWithWindow(int, int)"})
  public void testGetCloneWithWindow() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    HistoricalSimulationModel actualCloneWithWindow =
        new ARMAGARCH(timeSeries).getCloneWithWindow(1, 1);

    // Assert
    assertTrue(actualCloneWithWindow instanceof ARMAGARCH);
    Map<String, Object> bestParameters = actualCloneWithWindow.getBestParameters();
    assertEquals(15, bestParameters.size());
    assertEquals(-0.2540122609522881d, ((Double) bestParameters.get("Mu")).doubleValue(), 0.0);
    assertEquals(
        -3.899773793358994d, ((Double) bestParameters.get("Likelihood")).doubleValue(), 0.0);
    assertEquals(0.5d, ((Double) bestParameters.get("Quantile=05%")).doubleValue(), 0.0);
    assertEquals(0.5d, ((Double) bestParameters.get("Quantile=2%")).doubleValue(), 0.0);
    assertEquals(0.5d, ((Double) bestParameters.get("Quantile=50%")).doubleValue(), 0.0);
    assertEquals(0.9748399247905757d, ((Double) bestParameters.get("Omega")).doubleValue(), 0.0);
    assertArrayEquals(
        new double[] {
          0.9748399247905757d,
          0.5625806866750922d,
          0.3287949306731188d,
          0.1796310738057774d,
          -0.2540122609522881d,
          -0.35707426433697476d
        },
        ((ARMAGARCH) actualCloneWithWindow).getParameters(),
        0.0);
    assertArrayEquals(
        new String[] {"omega", "alpha", "beta", "theta", "mu", "phi"},
        ((ARMAGARCH) actualCloneWithWindow).getParameterNames());
  }

  /**
   * Test {@link ARMAGARCH#getParameterNames()}.
   *
   * <p>Method under test: {@link ARMAGARCH#getParameterNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] ARMAGARCH.getParameterNames()"})
  public void testGetParameterNames() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertArrayEquals(
        new String[] {"omega", "alpha", "beta", "theta", "mu", "phi"},
        new ARMAGARCH(timeSeries).getParameterNames());
  }
}
