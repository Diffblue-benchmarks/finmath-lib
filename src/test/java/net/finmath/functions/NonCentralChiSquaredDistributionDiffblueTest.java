package net.finmath.functions;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NonCentralChiSquaredDistributionDiffblueTest {
  /**
   * Test {@link NonCentralChiSquaredDistribution#NonCentralChiSquaredDistribution(double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-10}.
   *   <li>Then return NonCentrality is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * NonCentralChiSquaredDistribution#NonCentralChiSquaredDistribution(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NonCentralChiSquaredDistribution.<init>(double, double)"})
  public void testNewNonCentralChiSquaredDistribution_when10e10_thenReturnNonCentralityIsZero() {
    // Arrange and Act
    NonCentralChiSquaredDistribution actualNonCentralChiSquaredDistribution =
        new NonCentralChiSquaredDistribution(1.0E-10d, 0.0d);

    // Assert
    assertEquals(0.0d, actualNonCentralChiSquaredDistribution.getNonCentrality(), 0.0);
    assertEquals(1.0E-10d, actualNonCentralChiSquaredDistribution.getDegreesOfFreedom(), 0.0);
  }

  /**
   * Test {@link NonCentralChiSquaredDistribution#NonCentralChiSquaredDistribution(double, double)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-16}.
   *   <li>Then return NonCentrality is {@code 1.0E-16}.
   * </ul>
   *
   * <p>Method under test: {@link
   * NonCentralChiSquaredDistribution#NonCentralChiSquaredDistribution(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NonCentralChiSquaredDistribution.<init>(double, double)"})
  public void testNewNonCentralChiSquaredDistribution_when10e16_thenReturnNonCentralityIs10e16() {
    // Arrange and Act
    NonCentralChiSquaredDistribution actualNonCentralChiSquaredDistribution =
        new NonCentralChiSquaredDistribution(10.0d, 1.0E-16d);

    // Assert
    assertEquals(1.0E-16d, actualNonCentralChiSquaredDistribution.getNonCentrality(), 0.0);
    assertEquals(10.0d, actualNonCentralChiSquaredDistribution.getDegreesOfFreedom(), 0.0);
  }

  /**
   * Test {@link NonCentralChiSquaredDistribution#NonCentralChiSquaredDistribution(double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return NonCentrality is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * NonCentralChiSquaredDistribution#NonCentralChiSquaredDistribution(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NonCentralChiSquaredDistribution.<init>(double, double)"})
  public void testNewNonCentralChiSquaredDistribution_whenTen_thenReturnNonCentralityIsTen() {
    // Arrange and Act
    NonCentralChiSquaredDistribution actualNonCentralChiSquaredDistribution =
        new NonCentralChiSquaredDistribution(10.0d, 10.0d);

    // Assert
    assertEquals(10.0d, actualNonCentralChiSquaredDistribution.getDegreesOfFreedom(), 0.0);
    assertEquals(10.0d, actualNonCentralChiSquaredDistribution.getNonCentrality(), 0.0);
  }

  /**
   * Test {@link NonCentralChiSquaredDistribution#NonCentralChiSquaredDistribution(double, double)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return NonCentrality is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * NonCentralChiSquaredDistribution#NonCentralChiSquaredDistribution(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NonCentralChiSquaredDistribution.<init>(double, double)"})
  public void testNewNonCentralChiSquaredDistribution_whenTwo_thenReturnNonCentralityIsTwo() {
    // Arrange and Act
    NonCentralChiSquaredDistribution actualNonCentralChiSquaredDistribution =
        new NonCentralChiSquaredDistribution(10.0d, 2.0d);

    // Assert
    assertEquals(10.0d, actualNonCentralChiSquaredDistribution.getDegreesOfFreedom(), 0.0);
    assertEquals(2.0d, actualNonCentralChiSquaredDistribution.getNonCentrality(), 0.0);
  }

  /**
   * Test {@link NonCentralChiSquaredDistribution#cumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>Then return {@code 0.02334994522935559}.
   * </ul>
   *
   * <p>Method under test: {@link NonCentralChiSquaredDistribution#cumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NonCentralChiSquaredDistribution.cumulativeDistribution(double)"})
  public void testCumulativeDistribution_thenReturn002334994522935559() {
    // Arrange, Act and Assert
    assertEquals(
        0.02334994522935559d,
        new NonCentralChiSquaredDistribution(2.0d, 10.0d).cumulativeDistribution(2.0d),
        0.0);
  }

  /**
   * Test {@link NonCentralChiSquaredDistribution#cumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>Then return {@code 0.04022410025816638}.
   * </ul>
   *
   * <p>Method under test: {@link NonCentralChiSquaredDistribution#cumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NonCentralChiSquaredDistribution.cumulativeDistribution(double)"})
  public void testCumulativeDistribution_thenReturn004022410025816638() {
    // Arrange, Act and Assert
    assertEquals(
        0.04022410025816638d,
        new NonCentralChiSquaredDistribution(1.0d, 10.0d).cumulativeDistribution(2.0d),
        0.0);
  }

  /**
   * Test {@link NonCentralChiSquaredDistribution#cumulativeDistribution(double)}.
   *
   * <ul>
   *   <li>Then return {@code 5.3345981573870075E-5}.
   * </ul>
   *
   * <p>Method under test: {@link NonCentralChiSquaredDistribution#cumulativeDistribution(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NonCentralChiSquaredDistribution.cumulativeDistribution(double)"})
  public void testCumulativeDistribution_thenReturn53345981573870075e5() {
    // Arrange, Act and Assert
    assertEquals(
        5.3345981573870075E-5d,
        new NonCentralChiSquaredDistribution(10.0d, 10.0d).cumulativeDistribution(2.0d),
        0.0);
  }

  /**
   * Test {@link NonCentralChiSquaredDistribution#getDegreesOfFreedom()}.
   *
   * <p>Method under test: {@link NonCentralChiSquaredDistribution#getDegreesOfFreedom()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NonCentralChiSquaredDistribution.getDegreesOfFreedom()"})
  public void testGetDegreesOfFreedom() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d, new NonCentralChiSquaredDistribution(10.0d, 10.0d).getDegreesOfFreedom(), 0.0);
  }

  /**
   * Test {@link NonCentralChiSquaredDistribution#getNonCentrality()}.
   *
   * <p>Method under test: {@link NonCentralChiSquaredDistribution#getNonCentrality()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NonCentralChiSquaredDistribution.getNonCentrality()"})
  public void testGetNonCentrality() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new NonCentralChiSquaredDistribution(10.0d, 10.0d).getNonCentrality(), 0.0);
  }
}
