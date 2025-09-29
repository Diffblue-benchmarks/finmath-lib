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

public class DisplacedLognormalGARCHDiffblueTest {
  /**
   * Test {@link DisplacedLognormalGARCH#DisplacedLognormalGARCH(double[])}.
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#DisplacedLognormalGARCH(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisplacedLognormalGARCH.<init>(double[])"})
  public void testNewDisplacedLognormalGARCH() {
    // Arrange and Act
    DisplacedLognormalGARCH actualDisplacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Assert
    Map<String, Object> bestParameters = actualDisplacedLognormalGARCH.getBestParameters();
    assertEquals(10, bestParameters.size());
    assertTrue(bestParameters.containsKey("Alpha"));
    assertTrue(bestParameters.containsKey("Likelihood"));
    assertTrue(bestParameters.containsKey("Quantile=5%"));
    assertTrue(bestParameters.containsKey("Quantile=50%"));
    assertTrue(bestParameters.containsKey("Szenarios"));
    assertTrue(bestParameters.containsKey("Vol"));
  }

  /**
   * Test {@link DisplacedLognormalGARCH#DisplacedLognormalGARCH(double[], double)}.
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#DisplacedLognormalGARCH(double[], double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisplacedLognormalGARCH.<init>(double[], double)"})
  public void testNewDisplacedLognormalGARCH2() {
    // Arrange and Act
    DisplacedLognormalGARCH actualDisplacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, 10.0d);

    // Assert
    Map<String, Object> bestParameters = actualDisplacedLognormalGARCH.getBestParameters();
    assertEquals(10, bestParameters.size());
    assertTrue(bestParameters.containsKey("Alpha"));
    assertTrue(bestParameters.containsKey("Likelihood"));
    assertTrue(bestParameters.containsKey("Quantile=5%"));
    assertTrue(bestParameters.containsKey("Quantile=50%"));
    assertTrue(bestParameters.containsKey("Szenarios"));
    assertTrue(bestParameters.containsKey("Vol"));
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getCloneWithWindow(double, int, int)} with {@code
   * lowerBoundDisplacement}, {@code windowIndexStart}, {@code windowIndexEnd}.
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getCloneWithWindow(double, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.timeseries.HistoricalSimulationModel DisplacedLognormalGARCH.getCloneWithWindow(double, int, int)"
  })
  public void testGetCloneWithWindowWithLowerBoundDisplacementWindowIndexStartWindowIndexEnd() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertTrue(
        displacedLognormalGARCH.getCloneWithWindow(10.0d, 1, 1) instanceof DisplacedLognormalGARCH);
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getCloneWithWindow(int, int)} with {@code
   * windowIndexStart}, {@code windowIndexEnd}.
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getCloneWithWindow(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.timeseries.HistoricalSimulationModel DisplacedLognormalGARCH.getCloneWithWindow(int, int)"
  })
  public void testGetCloneWithWindowWithWindowIndexStartWindowIndexEnd() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertTrue(displacedLognormalGARCH.getCloneWithWindow(1, 1) instanceof DisplacedLognormalGARCH);
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getLogLikelihoodForParameters(double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@code -10.459418607701423}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getLogLikelihoodForParameters(double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormalGARCH.getLogLikelihoodForParameters(double, double, double, double)"
  })
  public void testGetLogLikelihoodForParameters_thenReturn10459418607701423() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        -10.459418607701423d,
        displacedLognormalGARCH.getLogLikelihoodForParameters(
            6.283185307179586d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getLogLikelihoodForParameters(double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>Then return {@code -10.464902660732584}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getLogLikelihoodForParameters(double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormalGARCH.getLogLikelihoodForParameters(double, double, double, double)"
  })
  public void testGetLogLikelihoodForParameters_thenReturn10464902660732584() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        -10.464902660732584d,
        displacedLognormalGARCH.getLogLikelihoodForParameters(10.0d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getLogLikelihoodForParameters(double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code -10.451571084849006}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getLogLikelihoodForParameters(double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormalGARCH.getLogLikelihoodForParameters(double, double, double, double)"
  })
  public void testGetLogLikelihoodForParameters_whenOne_thenReturn10451571084849006() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        -10.451571084849006d,
        displacedLognormalGARCH.getLogLikelihoodForParameters(1.0d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getLogLikelihoodForParameters(double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code -10.453061214071123}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getLogLikelihoodForParameters(double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormalGARCH.getLogLikelihoodForParameters(double, double, double, double)"
  })
  public void testGetLogLikelihoodForParameters_whenTwo_thenReturn10453061214071123() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        -10.453061214071123d,
        displacedLognormalGARCH.getLogLikelihoodForParameters(2.0d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getLastResidualForParameters(double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 35076.41408212646}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getLastResidualForParameters(double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormalGARCH.getLastResidualForParameters(double, double, double, double)"
  })
  public void testGetLastResidualForParameters_when05_thenReturn3507641408212646() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        35076.41408212646d,
        displacedLognormalGARCH.getLastResidualForParameters(0.5d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getLastResidualForParameters(double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 35105.598292652765}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getLastResidualForParameters(double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormalGARCH.getLastResidualForParameters(double, double, double, double)"
  })
  public void testGetLastResidualForParameters_whenOne_thenReturn35105598292652765() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        35105.598292652765d,
        displacedLognormalGARCH.getLastResidualForParameters(1.0d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getLastResidualForParameters(double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 35630.91408212646}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getLastResidualForParameters(double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormalGARCH.getLastResidualForParameters(double, double, double, double)"
  })
  public void testGetLastResidualForParameters_whenTen_thenReturn3563091408212646() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        35630.91408212646d,
        displacedLognormalGARCH.getLastResidualForParameters(10.0d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getLastResidualForParameters(double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 35163.966713705406}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getLastResidualForParameters(double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DisplacedLognormalGARCH.getLastResidualForParameters(double, double, double, double)"
  })
  public void testGetLastResidualForParameters_whenTwo_thenReturn35163966713705406() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        35163.966713705406d,
        displacedLognormalGARCH.getLastResidualForParameters(2.0d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getSzenarios(double, double, double, double)}.
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getSzenarios(double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] DisplacedLognormalGARCH.getSzenarios(double, double, double, double)"
  })
  public void testGetSzenarios() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {-0.09456843140425605d, 0.3138820528178208d, Double.NaN},
        displacedLognormalGARCH.getSzenarios(10.0d, 10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getQuantilPredictionsForParameters(double, double, double,
   * double, double[])}.
   *
   * <ul>
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getQuantilPredictionsForParameters(double,
   * double, double, double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] DisplacedLognormalGARCH.getQuantilPredictionsForParameters(double, double, double, double, double[])"
  })
  public void testGetQuantilPredictionsForParameters_thenReturnEmptyArrayOfDouble() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {},
        displacedLognormalGARCH.getQuantilPredictionsForParameters(
            10.0d, 10.0d, 10.0d, 10.0d, new double[] {}),
        0.0);
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getBestParameters()}.
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getBestParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DisplacedLognormalGARCH.getBestParameters()"})
  public void testGetBestParameters() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act
    Map<String, Object> actualBestParameters = displacedLognormalGARCH.getBestParameters();

    // Assert
    assertEquals(10, actualBestParameters.size());
    assertTrue(actualBestParameters.containsKey("Alpha"));
    assertTrue(actualBestParameters.containsKey("Beta"));
    assertTrue(actualBestParameters.containsKey("Displacement"));
    assertTrue(actualBestParameters.containsKey("Likelihood"));
    assertTrue(actualBestParameters.containsKey("Omega"));
    assertTrue(actualBestParameters.containsKey("Quantile=1%"));
    assertTrue(actualBestParameters.containsKey("Quantile=5%"));
    assertTrue(actualBestParameters.containsKey("Quantile=50%"));
    assertTrue(actualBestParameters.containsKey("Szenarios"));
    assertTrue(actualBestParameters.containsKey("Vol"));
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getBestParameters()}.
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getBestParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DisplacedLognormalGARCH.getBestParameters()"})
  public void testGetBestParameters2() {
    // Arrange and Act
    Map<String, Object> actualBestParameters =
        new DisplacedLognormalGARCH(new double[] {1.0d, 0.2d, 1.0d, 0.2d}, 1, 3)
            .getBestParameters();

    // Assert
    assertEquals(10, actualBestParameters.size());
    assertTrue(actualBestParameters.containsKey("Alpha"));
    assertTrue(actualBestParameters.containsKey("Beta"));
    assertTrue(actualBestParameters.containsKey("Displacement"));
    assertTrue(actualBestParameters.containsKey("Likelihood"));
    assertTrue(actualBestParameters.containsKey("Omega"));
    assertTrue(actualBestParameters.containsKey("Quantile=1%"));
    assertTrue(actualBestParameters.containsKey("Quantile=5%"));
    assertTrue(actualBestParameters.containsKey("Quantile=50%"));
    assertTrue(actualBestParameters.containsKey("Szenarios"));
    assertTrue(actualBestParameters.containsKey("Vol"));
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getBestParameters(Map)} with {@code Map}.
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getBestParameters(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DisplacedLognormalGARCH.getBestParameters(Map)"})
  public void testGetBestParametersWithMap() {
    // Arrange and Act
    Map<String, Object> actualBestParameters =
        new DisplacedLognormalGARCH(new double[] {1.0d, 0.2d, 1.0d, 0.2d}, 0, 2)
            .getBestParameters(null);

    // Assert
    assertEquals(10, actualBestParameters.size());
    assertTrue(actualBestParameters.containsKey("Alpha"));
    assertTrue(actualBestParameters.containsKey("Beta"));
    assertTrue(actualBestParameters.containsKey("Displacement"));
    assertTrue(actualBestParameters.containsKey("Likelihood"));
    assertTrue(actualBestParameters.containsKey("Omega"));
    assertTrue(actualBestParameters.containsKey("Quantile=1%"));
    assertTrue(actualBestParameters.containsKey("Quantile=5%"));
    assertTrue(actualBestParameters.containsKey("Quantile=50%"));
    assertTrue(actualBestParameters.containsKey("Szenarios"));
    assertTrue(actualBestParameters.containsKey("Vol"));
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getBestParameters(Map)} with {@code Map}.
   *
   * <ul>
   *   <li>Given {@code Omega}.
   *   <li>When {@link HashMap#HashMap()} {@code Omega} is ten.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getBestParameters(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DisplacedLognormalGARCH.getBestParameters(Map)"})
  public void testGetBestParametersWithMap_givenOmega_whenHashMapOmegaIsTen() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    HashMap<String, Object> guess = new HashMap<>();
    guess.put("Omega", 10.0d);
    guess.put("Alpha", 10.0d);
    guess.put("Beta", 10.0d);
    guess.put("Displacement", 10.0d);

    // Act
    Map<String, Object> actualBestParameters = displacedLognormalGARCH.getBestParameters(guess);

    // Assert
    assertEquals(10, actualBestParameters.size());
    assertTrue(actualBestParameters.containsKey("Alpha"));
    assertTrue(actualBestParameters.containsKey("Beta"));
    assertTrue(actualBestParameters.containsKey("Displacement"));
    assertTrue(actualBestParameters.containsKey("Likelihood"));
    assertTrue(actualBestParameters.containsKey("Omega"));
    assertTrue(actualBestParameters.containsKey("Quantile=1%"));
    assertTrue(actualBestParameters.containsKey("Quantile=5%"));
    assertTrue(actualBestParameters.containsKey("Quantile=50%"));
    assertTrue(actualBestParameters.containsKey("Szenarios"));
    assertTrue(actualBestParameters.containsKey("Vol"));
  }

  /**
   * Test {@link DisplacedLognormalGARCH#getBestParameters(Map)} with {@code Map}.
   *
   * <ul>
   *   <li>Then return size is ten.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLognormalGARCH#getBestParameters(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DisplacedLognormalGARCH.getBestParameters(Map)"})
  public void testGetBestParametersWithMap_thenReturnSizeIsTen() {
    // Arrange
    DisplacedLognormalGARCH displacedLognormalGARCH =
        new DisplacedLognormalGARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act
    Map<String, Object> actualBestParameters = displacedLognormalGARCH.getBestParameters(null);

    // Assert
    assertEquals(10, actualBestParameters.size());
    assertTrue(actualBestParameters.containsKey("Alpha"));
    assertTrue(actualBestParameters.containsKey("Beta"));
    assertTrue(actualBestParameters.containsKey("Displacement"));
    assertTrue(actualBestParameters.containsKey("Likelihood"));
    assertTrue(actualBestParameters.containsKey("Omega"));
    assertTrue(actualBestParameters.containsKey("Quantile=1%"));
    assertTrue(actualBestParameters.containsKey("Quantile=5%"));
    assertTrue(actualBestParameters.containsKey("Quantile=50%"));
    assertTrue(actualBestParameters.containsKey("Szenarios"));
    assertTrue(actualBestParameters.containsKey("Vol"));
  }
}
