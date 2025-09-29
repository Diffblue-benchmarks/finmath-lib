package net.finmath.functions;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LinearAlgebraDiffblueTest {
  /**
   * Test {@link LinearAlgebra#diag(double[])}.
   *
   * <p>Method under test: {@link LinearAlgebra#diag(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] LinearAlgebra.diag(double[])"})
  public void testDiag() {
    // Arrange and Act
    double[][] actualDiagResult =
        LinearAlgebra.diag(new double[] {10.0d, 1.0E-10d, 10.0d, 1.0E-10d});

    // Assert
    assertEquals(4, actualDiagResult.length);
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 1.0E-10d}, actualDiagResult[3], 0.0);
    assertArrayEquals(new double[] {0.0d, 0.0d, 10.0d, 0.0d}, actualDiagResult[2], 0.0);
    assertArrayEquals(new double[] {0.0d, 1.0E-10d, 0.0d, 0.0d}, actualDiagResult[1], 0.0);
    assertArrayEquals(new double[] {10.0d, 0.0d, 0.0d, 0.0d}, actualDiagResult[0], 0.0);
  }

  /**
   * Test {@link LinearAlgebra#diag(double[])}.
   *
   * <p>Method under test: {@link LinearAlgebra#diag(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] LinearAlgebra.diag(double[])"})
  public void testDiag2() {
    // Arrange and Act
    double[][] actualDiagResult =
        LinearAlgebra.diag(new double[] {10.0d, 1.0E-10d, 10.0d, 1.0E-10d});

    // Assert
    assertEquals(4, actualDiagResult.length);
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 1.0E-10d}, actualDiagResult[3], 0.0);
    assertArrayEquals(new double[] {0.0d, 0.0d, 10.0d, 0.0d}, actualDiagResult[2], 0.0);
    assertArrayEquals(new double[] {0.0d, 1.0E-10d, 0.0d, 0.0d}, actualDiagResult[1], 0.0);
    assertArrayEquals(new double[] {10.0d, 0.0d, 0.0d, 0.0d}, actualDiagResult[0], 0.0);
  }
}
