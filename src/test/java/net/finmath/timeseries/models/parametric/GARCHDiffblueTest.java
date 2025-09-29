package net.finmath.timeseries.models.parametric;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GARCHDiffblueTest {
  /**
   * Test {@link GARCH#GARCH(double[])}.
   *
   * <p>Method under test: {@link GARCH#GARCH(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GARCH.<init>(double[])"})
  public void testNewGarch() {
    // Arrange and Act
    GARCH actualGarch = new GARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Assert
    Map<String, Object> bestParameters = actualGarch.getBestParameters();
    assertEquals(9, bestParameters.size());
    assertEquals(
        -4.708585590268239d, ((Double) bestParameters.get("Likelihood")).doubleValue(), 0.0);
    assertTrue(bestParameters.containsKey("Alpha"));
    assertTrue(bestParameters.containsKey("Quantile=5%"));
    assertTrue(bestParameters.containsKey("Quantile=50%"));
    assertTrue(bestParameters.containsKey("Szenarios"));
    assertTrue(bestParameters.containsKey("Vol"));
  }

  /**
   * Test {@link GARCH#getLogLikelihoodForParameters(double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code -7.372943250560919}.
   * </ul>
   *
   * <p>Method under test: {@link GARCH#getLogLikelihoodForParameters(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GARCH.getLogLikelihoodForParameters(double, double, double)"})
  public void testGetLogLikelihoodForParameters_thenReturn7372943250560919() {
    // Arrange
    GARCH garch = new GARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        -7.372943250560919d,
        garch.getLogLikelihoodForParameters(6.283185307179586d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link GARCH#getLogLikelihoodForParameters(double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code -7.431862686341068}.
   * </ul>
   *
   * <p>Method under test: {@link GARCH#getLogLikelihoodForParameters(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GARCH.getLogLikelihoodForParameters(double, double, double)"})
  public void testGetLogLikelihoodForParameters_thenReturn7431862686341068() {
    // Arrange
    GARCH garch = new GARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(
        -7.431862686341068d, garch.getLogLikelihoodForParameters(10.0d, 10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link GARCH#getLogLikelihoodForParameters(double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code -7.273724747113077}.
   * </ul>
   *
   * <p>Method under test: {@link GARCH#getLogLikelihoodForParameters(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GARCH.getLogLikelihoodForParameters(double, double, double)"})
  public void testGetLogLikelihoodForParameters_when05_thenReturn7273724747113077() {
    // Arrange
    GARCH garch = new GARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(-7.273724747113077d, garch.getLogLikelihoodForParameters(0.5d, 10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link GARCH#getLogLikelihoodForParameters(double, double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code -7.282704030814941}.
   * </ul>
   *
   * <p>Method under test: {@link GARCH#getLogLikelihoodForParameters(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GARCH.getLogLikelihoodForParameters(double, double, double)"})
  public void testGetLogLikelihoodForParameters_whenOne_thenReturn7282704030814941() {
    // Arrange
    GARCH garch = new GARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(-7.282704030814941d, garch.getLogLikelihoodForParameters(1.0d, 10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link GARCH#getLastResidualForParameters(double, double, double)}.
   *
   * <ul>
   *   <li>Then return {@code 3458.906547844376}.
   * </ul>
   *
   * <p>Method under test: {@link GARCH#getLastResidualForParameters(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GARCH.getLastResidualForParameters(double, double, double)"})
  public void testGetLastResidualForParameters_thenReturn3458906547844376() {
    // Arrange
    GARCH garch = new GARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(3458.906547844376d, garch.getLastResidualForParameters(10.0d, 10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link GARCH#getLastResidualForParameters(double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 2904.4065478443763}.
   * </ul>
   *
   * <p>Method under test: {@link GARCH#getLastResidualForParameters(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GARCH.getLastResidualForParameters(double, double, double)"})
  public void testGetLastResidualForParameters_when05_thenReturn29044065478443763() {
    // Arrange
    GARCH garch = new GARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(2904.4065478443763d, garch.getLastResidualForParameters(0.5d, 10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link GARCH#getLastResidualForParameters(double, double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 2933.5907583706917}.
   * </ul>
   *
   * <p>Method under test: {@link GARCH#getLastResidualForParameters(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GARCH.getLastResidualForParameters(double, double, double)"})
  public void testGetLastResidualForParameters_whenOne_thenReturn29335907583706917() {
    // Arrange
    GARCH garch = new GARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(2933.5907583706917d, garch.getLastResidualForParameters(1.0d, 10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link GARCH#getLastResidualForParameters(double, double, double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 2991.959179423324}.
   * </ul>
   *
   * <p>Method under test: {@link GARCH#getLastResidualForParameters(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GARCH.getLastResidualForParameters(double, double, double)"})
  public void testGetLastResidualForParameters_whenTwo_thenReturn2991959179423324() {
    // Arrange
    GARCH garch = new GARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertEquals(2991.959179423324d, garch.getLastResidualForParameters(2.0d, 10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link GARCH#getSzenarios(double, double, double)}.
   *
   * <p>Method under test: {@link GARCH#getSzenarios(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] GARCH.getSzenarios(double, double, double)"})
  public void testGetSzenarios() {
    // Arrange
    GARCH garch = new GARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {-0.0869902435509698d, 0.2907579825533303d, Double.NaN},
        garch.getSzenarios(10.0d, 10.0d, 10.0d),
        0.0);
  }

  /**
   * Test {@link GARCH#getQuantilPredictionsForParameters(double, double, double, double[])}.
   *
   * <ul>
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link GARCH#getQuantilPredictionsForParameters(double, double, double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] GARCH.getQuantilPredictionsForParameters(double, double, double, double[])"
  })
  public void testGetQuantilPredictionsForParameters_thenReturnEmptyArrayOfDouble() {
    // Arrange
    GARCH garch = new GARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {},
        garch.getQuantilPredictionsForParameters(10.0d, 10.0d, 10.0d, new double[] {}),
        0.0);
  }

  /**
   * Test {@link GARCH#getBestParameters()}.
   *
   * <ul>
   *   <li>Then return {@code Likelihood} doubleValue is {@code -4.708585590268239}.
   * </ul>
   *
   * <p>Method under test: {@link GARCH#getBestParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map GARCH.getBestParameters()"})
  public void testGetBestParameters_thenReturnLikelihoodDoubleValueIs4708585590268239() {
    // Arrange
    GARCH garch = new GARCH(new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act
    Map<String, Object> actualBestParameters = garch.getBestParameters();

    // Assert
    assertEquals(9, actualBestParameters.size());
    assertEquals(
        -4.708585590268239d, ((Double) actualBestParameters.get("Likelihood")).doubleValue(), 0.0);
    assertTrue(actualBestParameters.containsKey("Alpha"));
    assertTrue(actualBestParameters.containsKey("Beta"));
    assertTrue(actualBestParameters.containsKey("Omega"));
    assertTrue(actualBestParameters.containsKey("Quantile=1%"));
    assertTrue(actualBestParameters.containsKey("Quantile=5%"));
    assertTrue(actualBestParameters.containsKey("Quantile=50%"));
    assertTrue(actualBestParameters.containsKey("Szenarios"));
    assertTrue(actualBestParameters.containsKey("Vol"));
  }

  /**
   * Test {@link GARCH#getBestParameters()}.
   *
   * <ul>
   *   <li>Then return {@code Likelihood} doubleValue is {@code -12.287879704395372}.
   * </ul>
   *
   * <p>Method under test: {@link GARCH#getBestParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map GARCH.getBestParameters()"})
  public void testGetBestParameters_thenReturnLikelihoodDoubleValueIs12287879704395372() {
    // Arrange
    GARCH garch = new GARCH(new double[] {1.0d, 0.2d, 1.0d, 0.2d, 1.0d, 0.2d, 1.0d, 0.2d});

    // Act
    Map<String, Object> actualBestParameters = garch.getBestParameters();

    // Assert
    assertEquals(9, actualBestParameters.size());
    assertEquals(
        -12.287879704395372d, ((Double) actualBestParameters.get("Likelihood")).doubleValue(), 0.0);
    assertTrue(actualBestParameters.containsKey("Alpha"));
    assertTrue(actualBestParameters.containsKey("Beta"));
    assertTrue(actualBestParameters.containsKey("Omega"));
    assertTrue(actualBestParameters.containsKey("Quantile=1%"));
    assertTrue(actualBestParameters.containsKey("Quantile=5%"));
    assertTrue(actualBestParameters.containsKey("Quantile=50%"));
    assertTrue(actualBestParameters.containsKey("Szenarios"));
    assertTrue(actualBestParameters.containsKey("Vol"));
  }

  /**
   * Test {@link GARCH#getBestParameters()}.
   *
   * <ul>
   *   <li>Then return {@code Likelihood} doubleValue is {@code -19.867173818522502}.
   * </ul>
   *
   * <p>Method under test: {@link GARCH#getBestParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map GARCH.getBestParameters()"})
  public void testGetBestParameters_thenReturnLikelihoodDoubleValueIs19867173818522502() {
    // Arrange
    GARCH garch =
        new GARCH(
            new double[] {1.0d, 0.2d, 1.0d, 0.2d, 1.0d, 0.2d, 1.0d, 0.2d, 1.0d, 0.2d, 1.0d, 0.2d});

    // Act
    Map<String, Object> actualBestParameters = garch.getBestParameters();

    // Assert
    assertEquals(9, actualBestParameters.size());
    assertEquals(
        -19.867173818522502d, ((Double) actualBestParameters.get("Likelihood")).doubleValue(), 0.0);
    assertTrue(actualBestParameters.containsKey("Alpha"));
    assertTrue(actualBestParameters.containsKey("Beta"));
    assertTrue(actualBestParameters.containsKey("Omega"));
    assertTrue(actualBestParameters.containsKey("Quantile=1%"));
    assertTrue(actualBestParameters.containsKey("Quantile=5%"));
    assertTrue(actualBestParameters.containsKey("Quantile=50%"));
    assertTrue(actualBestParameters.containsKey("Szenarios"));
    assertTrue(actualBestParameters.containsKey("Vol"));
  }
}
