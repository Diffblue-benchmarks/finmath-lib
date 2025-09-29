package net.finmath.equities.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SviVolatilitySmileDiffblueTest {
  /**
   * Test {@link SviVolatilitySmile#SviVolatilitySmile(LocalDate, double, double, double, double,
   * double)}.
   *
   * <p>Method under test: {@link SviVolatilitySmile#SviVolatilitySmile(LocalDate, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SviVolatilitySmile.<init>(LocalDate, double, double, double, double, double)"
  })
  public void testNewSviVolatilitySmile() {
    // Arrange
    LocalDate date = LocalDate.of(1970, 1, 1);

    // Act
    SviVolatilitySmile actualSviVolatilitySmile =
        new SviVolatilitySmile(date, 10.0d, 10.0d, 0.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualSviVolatilitySmile.getRho(), 0.0);
    assertEquals(10.0d, actualSviVolatilitySmile.getA(), 0.0);
    assertEquals(10.0d, actualSviVolatilitySmile.getB(), 0.0);
    assertEquals(10.0d, actualSviVolatilitySmile.getM(), 0.0);
    assertEquals(10.0d, actualSviVolatilitySmile.getSigma(), 0.0);
    assertSame(date, actualSviVolatilitySmile.getSmileDate());
  }

  /**
   * Test {@link SviVolatilitySmile#sviTotalVariance(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code -802.0688577586628}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#sviTotalVariance(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SviVolatilitySmile.sviTotalVariance(double, double, double, double, double, double)"
  })
  public void testSviTotalVariance_when05_thenReturn8020688577586628() {
    // Arrange and Act
    double actualSviTotalVarianceResult =
        SviVolatilitySmile.sviTotalVariance(0.5d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-802.0688577586628d, actualSviTotalVarianceResult, 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#sviTotalVariance(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code -755.4637595292629}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#sviTotalVariance(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SviVolatilitySmile.sviTotalVariance(double, double, double, double, double, double)"
  })
  public void testSviTotalVariance_whenOne_thenReturn7554637595292629() {
    // Arrange and Act
    double actualSviTotalVarianceResult =
        SviVolatilitySmile.sviTotalVariance(1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-755.4637595292629d, actualSviTotalVarianceResult, 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#sviTotalVariance(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return one hundred ten.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#sviTotalVariance(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SviVolatilitySmile.sviTotalVariance(double, double, double, double, double, double)"
  })
  public void testSviTotalVariance_whenTen_thenReturnOneHundredTen() {
    // Arrange and Act
    double actualSviTotalVarianceResult =
        SviVolatilitySmile.sviTotalVariance(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(110.0d, actualSviTotalVarianceResult, 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#sviTotalVariance(double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code -661.937515251343}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#sviTotalVariance(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SviVolatilitySmile.sviTotalVariance(double, double, double, double, double, double)"
  })
  public void testSviTotalVariance_whenTwo_thenReturn661937515251343() {
    // Arrange and Act
    double actualSviTotalVarianceResult =
        SviVolatilitySmile.sviTotalVariance(2.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(-661.937515251343d, actualSviTotalVarianceResult, 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#sviVolatility(double, double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#sviVolatility(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SviVolatilitySmile.sviVolatility(double, double, double, double, double, double, double)"
  })
  public void testSviVolatility_when05_thenReturnNaN() {
    // Arrange and Act
    double actualSviVolatilityResult =
        SviVolatilitySmile.sviVolatility(0.5d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualSviVolatilityResult, 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#sviVolatility(double, double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#sviVolatility(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SviVolatilitySmile.sviVolatility(double, double, double, double, double, double, double)"
  })
  public void testSviVolatility_whenOne_thenReturnNaN() {
    // Arrange and Act
    double actualSviVolatilityResult =
        SviVolatilitySmile.sviVolatility(1.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualSviVolatilityResult, 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#sviVolatility(double, double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 3.3166247903554}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#sviVolatility(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SviVolatilitySmile.sviVolatility(double, double, double, double, double, double, double)"
  })
  public void testSviVolatility_whenTen_thenReturn33166247903554() {
    // Arrange and Act
    double actualSviVolatilityResult =
        SviVolatilitySmile.sviVolatility(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(3.3166247903554d, actualSviVolatilityResult, 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#sviVolatility(double, double, double, double, double, double,
   * double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#sviVolatility(double, double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SviVolatilitySmile.sviVolatility(double, double, double, double, double, double, double)"
  })
  public void testSviVolatility_whenTwo_thenReturnNaN() {
    // Arrange and Act
    double actualSviVolatilityResult =
        SviVolatilitySmile.sviVolatility(2.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualSviVolatilityResult, 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#sviInitialGuess(ArrayList, ArrayList)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} add {@code -0.5}.
   *   <li>Then return array of {@code double} with {@link Double#NaN} and zero.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#sviInitialGuess(ArrayList, ArrayList)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SviVolatilitySmile.sviInitialGuess(ArrayList, ArrayList)"})
  public void testSviInitialGuess_whenArrayListAdd05_thenReturnArrayOfDoubleWithNaNAndZero() {
    // Arrange
    ArrayList<Double> logStrikes = new ArrayList<>();
    logStrikes.add(-0.5d);
    logStrikes.add(0.5d);
    logStrikes.add(10.0d);
    logStrikes.add(-0.5d);
    logStrikes.add(0.5d);

    ArrayList<Double> totalVariances = new ArrayList<>();
    totalVariances.add(0.5d);
    totalVariances.add(10.0d);
    totalVariances.add(-0.5d);
    totalVariances.add(0.5d);
    totalVariances.add(10.0d);

    // Act and Assert
    assertArrayEquals(
        new double[] {Double.NaN, 0.0d, Double.POSITIVE_INFINITY, Double.NaN, Double.NaN},
        SviVolatilitySmile.sviInitialGuess(logStrikes, totalVariances),
        0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#getTotalVariance(double)} with {@code logStrike}.
   *
   * <p>Method under test: {@link SviVolatilitySmile#getTotalVariance(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SviVolatilitySmile.getTotalVariance(double)"})
  public void testGetTotalVarianceWithLogStrike() {
    // Arrange
    SviVolatilitySmile sviVolatilitySmile =
        new SviVolatilitySmile(LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(110.0d, sviVolatilitySmile.getTotalVariance(10.0d), 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#getTotalVariance(double, double)} with {@code strike}, {@code
   * forward}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 108.99970834174167}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#getTotalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SviVolatilitySmile.getTotalVariance(double, double)"})
  public void testGetTotalVarianceWithStrikeForward_when05_thenReturn10899970834174167() {
    // Arrange
    SviVolatilitySmile sviVolatilitySmile =
        new SviVolatilitySmile(LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(108.99970834174167d, sviVolatilitySmile.getTotalVariance(0.5d, 10.0d), 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#getTotalVariance(double, double)} with {@code strike}, {@code
   * forward}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 107.02842559851706}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#getTotalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SviVolatilitySmile.getTotalVariance(double, double)"})
  public void testGetTotalVarianceWithStrikeForward_whenOne_thenReturn10702842559851706() {
    // Arrange
    SviVolatilitySmile sviVolatilitySmile =
        new SviVolatilitySmile(LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(107.02842559851706d, sviVolatilitySmile.getTotalVariance(1.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#getTotalVariance(double, double)} with {@code strike}, {@code
   * forward}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 101.42135623730951}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#getTotalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SviVolatilitySmile.getTotalVariance(double, double)"})
  public void testGetTotalVarianceWithStrikeForward_whenTen_thenReturn10142135623730951() {
    // Arrange
    SviVolatilitySmile sviVolatilitySmile =
        new SviVolatilitySmile(LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(101.42135623730951d, sviVolatilitySmile.getTotalVariance(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#getTotalVariance(double, double)} with {@code strike}, {@code
   * forward}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 105.17782427129673}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#getTotalVariance(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SviVolatilitySmile.getTotalVariance(double, double)"})
  public void testGetTotalVarianceWithStrikeForward_whenTwo_thenReturn10517782427129673() {
    // Arrange
    SviVolatilitySmile sviVolatilitySmile =
        new SviVolatilitySmile(LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(105.17782427129673d, sviVolatilitySmile.getTotalVariance(2.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#getVolatility(double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 3.3015103868039195}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#getVolatility(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SviVolatilitySmile.getVolatility(double, double, double)"})
  public void testGetVolatility_when05_thenReturn33015103868039195() {
    // Arrange
    SviVolatilitySmile sviVolatilitySmile =
        new SviVolatilitySmile(LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(3.3015103868039195d, sviVolatilitySmile.getVolatility(0.5d, 10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#getVolatility(double, double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 3.271519915857415}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#getVolatility(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SviVolatilitySmile.getVolatility(double, double, double)"})
  public void testGetVolatility_whenOne_thenReturn3271519915857415() {
    // Arrange
    SviVolatilitySmile sviVolatilitySmile =
        new SviVolatilitySmile(LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(3.271519915857415d, sviVolatilitySmile.getVolatility(1.0d, 10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#getVolatility(double, double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return {@code 3.1846719805548185}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#getVolatility(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SviVolatilitySmile.getVolatility(double, double, double)"})
  public void testGetVolatility_whenTen_thenReturn31846719805548185() {
    // Arrange
    SviVolatilitySmile sviVolatilitySmile =
        new SviVolatilitySmile(LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(3.1846719805548185d, sviVolatilitySmile.getVolatility(10.0d, 10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link SviVolatilitySmile#getVolatility(double, double, double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 3.2431130765253426}.
   * </ul>
   *
   * <p>Method under test: {@link SviVolatilitySmile#getVolatility(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SviVolatilitySmile.getVolatility(double, double, double)"})
  public void testGetVolatility_whenTwo_thenReturn32431130765253426() {
    // Arrange
    SviVolatilitySmile sviVolatilitySmile =
        new SviVolatilitySmile(LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 0.5d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(3.2431130765253426d, sviVolatilitySmile.getVolatility(2.0d, 10.0d, 10.0d), 0.0);
  }
}
