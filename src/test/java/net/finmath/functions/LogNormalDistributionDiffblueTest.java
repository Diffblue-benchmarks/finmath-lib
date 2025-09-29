package net.finmath.functions;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.functions.LogNormalDistribution.LogNormalDistributionParameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LogNormalDistributionDiffblueTest {
  /**
   * Test {@link LogNormalDistribution#getParametersFromMuAndSigma(double, double)}.
   *
   * <p>Method under test: {@link LogNormalDistribution#getParametersFromMuAndSigma(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LogNormalDistributionParameters LogNormalDistribution.getParametersFromMuAndSigma(double, double)"
  })
  public void testGetParametersFromMuAndSigma() {
    // Arrange and Act
    LogNormalDistributionParameters actualParametersFromMuAndSigma =
        LogNormalDistribution.getParametersFromMuAndSigma(10.0d, 10.0d);

    // Assert
    assertEquals(1.1420073898156842E26d, actualParametersFromMuAndSigma.getMean(), 0.0);
    assertEquals(10.0d, actualParametersFromMuAndSigma.getMu(), 0.0);
    assertEquals(10.0d, actualParametersFromMuAndSigma.getSigma(), 0.0);
    assertEquals(5.920972027664671E47d, actualParametersFromMuAndSigma.getStandardDeviation(), 0.0);
  }

  /**
   * Test {@link LogNormalDistribution#getParametersFromMeanAndStdDev(double, double)}.
   *
   * <p>Method under test: {@link LogNormalDistribution#getParametersFromMeanAndStdDev(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LogNormalDistributionParameters LogNormalDistribution.getParametersFromMeanAndStdDev(double, double)"
  })
  public void testGetParametersFromMeanAndStdDev() {
    // Arrange and Act
    LogNormalDistributionParameters actualParametersFromMeanAndStdDev =
        LogNormalDistribution.getParametersFromMeanAndStdDev(10.0d, 10.0d);

    // Assert
    assertEquals(0.832554611157698d, actualParametersFromMeanAndStdDev.getSigma(), 0.0);
    assertEquals(1.956011502714073d, actualParametersFromMeanAndStdDev.getMu(), 0.0);
    assertEquals(10.0d, actualParametersFromMeanAndStdDev.getMean(), 0.0);
    assertEquals(10.0d, actualParametersFromMeanAndStdDev.getStandardDeviation(), 0.0);
  }

  /**
   * Test {@link LogNormalDistribution#density(double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 0.6274960771159244}.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalDistribution#density(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LogNormalDistribution.density(double)"})
  public void testDensity_when05_thenReturn06274960771159244() {
    // Arrange, Act and Assert
    assertEquals(0.6274960771159244d, LogNormalDistribution.density(0.5d), 0.0);
  }

  /**
   * Test {@link LogNormalDistribution#density(double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalDistribution#density(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LogNormalDistribution.density(double)"})
  public void testDensity_when05_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0.0d, LogNormalDistribution.density(-0.5d), 0.0);
  }

  /**
   * Test {@link LogNormalDistribution#density(double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalDistribution#density(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LogNormalDistribution.density(double)"})
  public void testDensity_whenNaN_thenReturnNaN() {
    // Arrange, Act and Assert
    assertEquals(Double.NaN, LogNormalDistribution.density(Double.NaN), 0.0);
  }

  /**
   * Test {@link LogNormalDistribution#density(double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 0.1568740192789811}.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalDistribution#density(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LogNormalDistribution.density(double)"})
  public void testDensity_whenTwo_thenReturn01568740192789811() {
    // Arrange, Act and Assert
    assertEquals(0.1568740192789811d, LogNormalDistribution.density(2.0d), 0.0);
  }

  /**
   * Test {@link LogNormalDistribution#cumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 0.2441085957855828}.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalDistribution#cumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LogNormalDistribution.cumulativeDistribution(double)"})
  public void testCumulativeDistribution_when05_thenReturn02441085957855828() {
    // Arrange, Act and Assert
    assertEquals(0.2441085957855828d, LogNormalDistribution.cumulativeDistribution(0.5d), 0.0);
  }

  /**
   * Test {@link LogNormalDistribution#cumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalDistribution#cumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LogNormalDistribution.cumulativeDistribution(double)"})
  public void testCumulativeDistribution_when05_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0.0d, LogNormalDistribution.cumulativeDistribution(-0.5d), 0.0);
  }

  /**
   * Test {@link LogNormalDistribution#cumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalDistribution#cumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LogNormalDistribution.cumulativeDistribution(double)"})
  public void testCumulativeDistribution_whenNaN_thenReturnNaN() {
    // Arrange, Act and Assert
    assertEquals(Double.NaN, LogNormalDistribution.cumulativeDistribution(Double.NaN), 0.0);
  }

  /**
   * Test {@link LogNormalDistribution#cumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 0.9893489006582998}.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalDistribution#cumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LogNormalDistribution.cumulativeDistribution(double)"})
  public void testCumulativeDistribution_whenTen_thenReturn09893489006582998() {
    // Arrange, Act and Assert
    assertEquals(0.9893489006582998d, LogNormalDistribution.cumulativeDistribution(10.0d), 0.0);
  }

  /**
   * Test {@link LogNormalDistribution#cumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 0.7558914042144171}.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalDistribution#cumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LogNormalDistribution.cumulativeDistribution(double)"})
  public void testCumulativeDistribution_whenTwo_thenReturn07558914042144171() {
    // Arrange, Act and Assert
    assertEquals(0.7558914042144171d, LogNormalDistribution.cumulativeDistribution(2.0d), 0.0);
  }

  /**
   * Test {@link LogNormalDistribution#inverseCumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 1.0000000000281437}.
   * </ul>
   *
   * <p>Method under test: {@link LogNormalDistribution#inverseCumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LogNormalDistribution.inverseCumulativeDistribution(double)"})
  public void testInverseCumulativeDistribution_when05_thenReturn10000000000281437() {
    // Arrange, Act and Assert
    assertEquals(
        1.0000000000281437d, LogNormalDistribution.inverseCumulativeDistribution(0.5d), 0.0);
  }

  /**
   * Test LogNormalDistributionParameters getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LogNormalDistributionParameters#getMean()}
   *   <li>{@link LogNormalDistributionParameters#getMu()}
   *   <li>{@link LogNormalDistributionParameters#getSigma()}
   *   <li>{@link LogNormalDistributionParameters#getStandardDeviation()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LogNormalDistributionParameters.getMean()",
    "double LogNormalDistributionParameters.getMu()",
    "double LogNormalDistributionParameters.getSigma()",
    "double LogNormalDistributionParameters.getStandardDeviation()"
  })
  public void testLogNormalDistributionParametersGettersAndSetters() {
    // Arrange
    LogNormalDistributionParameters logNormalDistributionParameters =
        new LogNormalDistributionParameters(10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    double actualMean = logNormalDistributionParameters.getMean();
    double actualMu = logNormalDistributionParameters.getMu();
    double actualSigma = logNormalDistributionParameters.getSigma();

    // Assert
    assertEquals(10.0d, actualMean, 0.0);
    assertEquals(10.0d, actualMu, 0.0);
    assertEquals(10.0d, actualSigma, 0.0);
    assertEquals(10.0d, logNormalDistributionParameters.getStandardDeviation(), 0.0);
  }

  /**
   * Test LogNormalDistributionParameters {@link
   * LogNormalDistributionParameters#LogNormalDistributionParameters(double, double, double,
   * double)}.
   *
   * <p>Method under test: {@link
   * LogNormalDistributionParameters#LogNormalDistributionParameters(double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LogNormalDistributionParameters.<init>(double, double, double, double)"})
  public void testLogNormalDistributionParametersNewLogNormalDistributionParameters() {
    // Arrange and Act
    LogNormalDistributionParameters actualLogNormalDistributionParameters =
        new LogNormalDistributionParameters(10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(10.0d, actualLogNormalDistributionParameters.getMean(), 0.0);
    assertEquals(10.0d, actualLogNormalDistributionParameters.getMu(), 0.0);
    assertEquals(10.0d, actualLogNormalDistributionParameters.getSigma(), 0.0);
    assertEquals(10.0d, actualLogNormalDistributionParameters.getStandardDeviation(), 0.0);
  }
}
