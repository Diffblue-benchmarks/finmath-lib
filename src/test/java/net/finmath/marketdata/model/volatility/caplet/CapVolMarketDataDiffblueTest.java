package net.finmath.marketdata.model.volatility.caplet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CapVolMarketDataDiffblueTest {
  /**
   * Test {@link CapVolMarketData#CapVolMarketData(String, String, String, CapTenorStructure, int[],
   * double[], double[][], double, int, int, int)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CapVolMarketData#CapVolMarketData(String, String, String,
   * CapTenorStructure, int[], double[], double[][], double, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapVolMarketData.<init>(String, String, String, CapTenorStructure, int[], double[], double[][], double, int, int, int)"
  })
  public void testNewCapVolMarketData_thenThrowIllegalArgumentException() {
    // Arrange
    double[][] capVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CapVolMarketData(
                "Index",
                "3",
                "Index Before Change",
                CapTenorStructure.EUR,
                new int[] {1, 0, 1, 0},
                new double[] {10.0d, 0.5d, 10.0d, 0.5d},
                capVolatilities,
                10.0d,
                1,
                1,
                1));
  }

  /**
   * Test {@link CapVolMarketData#CapVolMarketData(String, String, CapTenorStructure, int[],
   * double[], double[][], double, int)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CapVolMarketData#CapVolMarketData(String, String,
   * CapTenorStructure, int[], double[], double[][], double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapVolMarketData.<init>(String, String, CapTenorStructure, int[], double[], double[][], double, int)"
  })
  public void testNewCapVolMarketData_thenThrowIllegalArgumentException2() {
    // Arrange
    double[][] capVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CapVolMarketData(
                "Index",
                "3",
                CapTenorStructure.EUR,
                new int[] {1, 0, 1, 0},
                new double[] {10.0d, 0.5d, 10.0d, 0.5d},
                capVolatilities,
                10.0d,
                1));
  }

  /**
   * Test {@link CapVolMarketData#getOffsetCodeFromIndex(String)}.
   *
   * <p>Method under test: {@link CapVolMarketData#getOffsetCodeFromIndex(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CapVolMarketData.getOffsetCodeFromIndex(String)"})
  public void testGetOffsetCodeFromIndex() {
    // Arrange, Act and Assert
    assertEquals("Index", CapVolMarketData.getOffsetCodeFromIndex("Index"));
  }
}
