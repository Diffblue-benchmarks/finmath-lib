package net.finmath.timeseries.models.parametric;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DisplacedLognormalDiffblueTest {
  /**
   * Test {@link DisplacedLognormal#DisplacedLognormal(double[])}.
   *
   * <p>Method under test: {@link DisplacedLognormal#DisplacedLognormal(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisplacedLognormal.<init>(double[])"})
  public void testNewDisplacedLognormal() {
    // Arrange and Act
    DisplacedLognormal actualDisplacedLognormal =
        new DisplacedLognormal(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Assert
    Map<String, Object> bestParameters = actualDisplacedLognormal.getBestParameters();
    assertEquals(9, bestParameters.size());
    assertTrue(bestParameters.containsKey("Alpha"));
    assertTrue(bestParameters.containsKey("Likelihood"));
    assertTrue(bestParameters.containsKey("Omega"));
    assertTrue(bestParameters.containsKey("Quantile=5%"));
    assertTrue(bestParameters.containsKey("Quantile=50%"));
    assertTrue(bestParameters.containsKey("Vol"));
  }

  /**
   * Test {@link DisplacedLognormal#DisplacedLognormal(double[], double)}.
   *
   * <p>Method under test: {@link DisplacedLognormal#DisplacedLognormal(double[], double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisplacedLognormal.<init>(double[], double)"})
  public void testNewDisplacedLognormal2() {
    // Arrange and Act
    DisplacedLognormal actualDisplacedLognormal =
        new DisplacedLognormal(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, 10.0d);

    // Assert
    Map<String, Object> bestParameters = actualDisplacedLognormal.getBestParameters();
    assertEquals(9, bestParameters.size());
    assertTrue(bestParameters.containsKey("Alpha"));
    assertTrue(bestParameters.containsKey("Likelihood"));
    assertTrue(bestParameters.containsKey("Omega"));
    assertTrue(bestParameters.containsKey("Quantile=5%"));
    assertTrue(bestParameters.containsKey("Quantile=50%"));
    assertTrue(bestParameters.containsKey("Vol"));
  }

  /**
   * Test {@link DisplacedLognormal#getCloneWithWindow(double, int, int)} with {@code
   * lowerBoundDisplacement}, {@code windowIndexStart}, {@code windowIndexEnd}.
   *
   * <p>Method under test: {@link DisplacedLognormal#getCloneWithWindow(double, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.timeseries.HistoricalSimulationModel DisplacedLognormal.getCloneWithWindow(double, int, int)"
  })
  public void testGetCloneWithWindowWithLowerBoundDisplacementWindowIndexStartWindowIndexEnd() {
    // Arrange
    DisplacedLognormal displacedLognormal =
        new DisplacedLognormal(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertTrue(displacedLognormal.getCloneWithWindow(10.0d, 1, 1) instanceof DisplacedLognormal);
  }

  /**
   * Test {@link DisplacedLognormal#getCloneWithWindow(int, int)} with {@code windowIndexStart},
   * {@code windowIndexEnd}.
   *
   * <p>Method under test: {@link DisplacedLognormal#getCloneWithWindow(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.timeseries.HistoricalSimulationModel DisplacedLognormal.getCloneWithWindow(int, int)"
  })
  public void testGetCloneWithWindowWithWindowIndexStartWindowIndexEnd() {
    // Arrange
    DisplacedLognormal displacedLognormal =
        new DisplacedLognormal(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertTrue(displacedLognormal.getCloneWithWindow(1, 1) instanceof DisplacedLognormal);
  }

  /**
   * Test {@link DisplacedLognormal#getLogLikelihoodForParameters(double, double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code -7.988535430663601}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormal#getLogLikelihoodForParameters(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormal.getLogLikelihoodForParameters(double, double, double, double)"
  })
  public void testGetLogLikelihoodForParameters_thenReturn7988535430663601() {
    // Arrange
    DisplacedLognormal displacedLognormal =
        new DisplacedLognormal(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        -7.988535430663601d,
        displacedLognormal.getLogLikelihoodForParameters(10.0d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormal#getLogLikelihoodForParameters(double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 6.283185307179586}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormal#getLogLikelihoodForParameters(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormal.getLogLikelihoodForParameters(double, double, double, double)"
  })
  public void testGetLogLikelihoodForParameters_when6283185307179586() {
    // Arrange
    DisplacedLognormal displacedLognormal =
        new DisplacedLognormal(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        -7.988535430663601d,
        displacedLognormal.getLogLikelihoodForParameters(6.283185307179586d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormal#getLogLikelihoodForParameters(double, double, double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code -7.988535430663601}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormal#getLogLikelihoodForParameters(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormal.getLogLikelihoodForParameters(double, double, double, double)"
  })
  public void testGetLogLikelihoodForParameters_whenOne_thenReturn7988535430663601() {
    // Arrange
    DisplacedLognormal displacedLognormal =
        new DisplacedLognormal(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        -7.988535430663601d,
        displacedLognormal.getLogLikelihoodForParameters(1.0d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormal#getLogLikelihoodForParameters(double, double, double, double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code -7.988535430663601}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormal#getLogLikelihoodForParameters(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormal.getLogLikelihoodForParameters(double, double, double, double)"
  })
  public void testGetLogLikelihoodForParameters_whenTwo_thenReturn7988535430663601() {
    // Arrange
    DisplacedLognormal displacedLognormal =
        new DisplacedLognormal(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        -7.988535430663601d,
        displacedLognormal.getLogLikelihoodForParameters(2.0d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormal#getLastResidualForParameters(double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 35076.41408212646}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormal#getLastResidualForParameters(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormal.getLastResidualForParameters(double, double, double, double)"
  })
  public void testGetLastResidualForParameters_when05_thenReturn3507641408212646() {
    // Arrange
    DisplacedLognormal displacedLognormal =
        new DisplacedLognormal(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        35076.41408212646d,
        displacedLognormal.getLastResidualForParameters(0.5d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormal#getLastResidualForParameters(double, double, double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 35105.598292652765}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormal#getLastResidualForParameters(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormal.getLastResidualForParameters(double, double, double, double)"
  })
  public void testGetLastResidualForParameters_whenOne_thenReturn35105598292652765() {
    // Arrange
    DisplacedLognormal displacedLognormal =
        new DisplacedLognormal(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        35105.598292652765d,
        displacedLognormal.getLastResidualForParameters(1.0d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormal#getLastResidualForParameters(double, double, double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 35630.91408212646}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormal#getLastResidualForParameters(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormal.getLastResidualForParameters(double, double, double, double)"
  })
  public void testGetLastResidualForParameters_whenTen_thenReturn3563091408212646() {
    // Arrange
    DisplacedLognormal displacedLognormal =
        new DisplacedLognormal(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        35630.91408212646d,
        displacedLognormal.getLastResidualForParameters(10.0d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormal#getLastResidualForParameters(double, double, double, double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 35163.966713705406}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormal#getLastResidualForParameters(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormal.getLastResidualForParameters(double, double, double, double)"
  })
  public void testGetLastResidualForParameters_whenTwo_thenReturn35163966713705406() {
    // Arrange
    DisplacedLognormal displacedLognormal =
        new DisplacedLognormal(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        35163.966713705406d,
        displacedLognormal.getLastResidualForParameters(2.0d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormal#getQuantilPredictionsForParameters(double, double, double,
   * double, double[])}.
   *
   * <ul>
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormal#getQuantilPredictionsForParameters(double,
   * double, double, double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] DisplacedLognormal.getQuantilPredictionsForParameters(double, double, double, double, double[])"
  })
  public void testGetQuantilPredictionsForParameters_thenReturnEmptyArrayOfDouble() {
    // Arrange
    DisplacedLognormal displacedLognormal =
        new DisplacedLognormal(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {},
        displacedLognormal.getQuantilPredictionsForParameters(
            10.0d, 10.0d, 10.0d, 10.0d, new double[] {}),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormal#getBestParameters(Map)} with {@code Map}.
   *
   * <ul>
   *   <li>Given {@code Omega}.
   *   <li>Then return size is nine.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormal#getBestParameters(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DisplacedLognormal.getBestParameters(Map)"})
  public void testGetBestParametersWithMap_givenOmega_thenReturnSizeIsNine() {
    // Arrange
    DisplacedLognormal displacedLognormal =
        new DisplacedLognormal(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    HashMap<String, Object> guess = new HashMap<>();
    guess.put("Omega", 10.0d);
    guess.put("Alpha", 10.0d);
    guess.put("Beta", 10.0d);
    guess.put("Displacement", 10.0d);

    // Act
    Map<String, Object> actualBestParameters = displacedLognormal.getBestParameters(guess);

    // Assert
    assertEquals(9, actualBestParameters.size());
    assertEquals(
        -7.6229650851953625d, ((Double) actualBestParameters.get("Likelihood")).doubleValue(), 0.0);
    assertTrue(actualBestParameters.containsKey("Alpha"));
    assertTrue(actualBestParameters.containsKey("Beta"));
    assertTrue(actualBestParameters.containsKey("Displacement"));
    assertTrue(actualBestParameters.containsKey("Omega"));
    assertTrue(actualBestParameters.containsKey("Quantile=1%"));
    assertTrue(actualBestParameters.containsKey("Quantile=5%"));
    assertTrue(actualBestParameters.containsKey("Quantile=50%"));
    assertTrue(actualBestParameters.containsKey("Vol"));
  }

  /**
   * Test {@link DisplacedLognormal#getBestParameters()}.
   *
   * <ul>
   *   <li>Then return size is nine.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormal#getBestParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DisplacedLognormal.getBestParameters()"})
  public void testGetBestParameters_thenReturnSizeIsNine() {
    // Arrange and Act
    Map<String, Object> actualBestParameters =
        new DisplacedLognormal(new double[] {1.0d, 0.2d, 1.0d, 0.2d}, 1, 3).getBestParameters();

    // Assert
    assertEquals(9, actualBestParameters.size());
    assertEquals(
        -0.24444444444444446d,
        ((Double) actualBestParameters.get("Quantile=50%")).doubleValue(),
        0.0);
    assertEquals(
        -1.3644444444444446d,
        ((Double) actualBestParameters.get("Quantile=5%")).doubleValue(),
        0.0);
    assertEquals(-1.464d, ((Double) actualBestParameters.get("Quantile=1%")).doubleValue(), 0.0);
    assertEquals(
        -1.9599122644203368d, ((Double) actualBestParameters.get("Likelihood")).doubleValue(), 0.0);
    assertEquals(0.8d, ((Double) actualBestParameters.get("Displacement")).doubleValue(), 0.0);
    assertTrue(actualBestParameters.containsKey("Alpha"));
    assertTrue(actualBestParameters.containsKey("Beta"));
    assertTrue(actualBestParameters.containsKey("Omega"));
    assertTrue(actualBestParameters.containsKey("Vol"));
  }
}
