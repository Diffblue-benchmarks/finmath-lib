package net.finmath.timeseries.models.parametric;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SimpleHistroricalSimulationDiffblueTest {
  /**
   * Test {@link SimpleHistroricalSimulation#getCloneWithWindow(int, int)}.
   *
   * <p>Method under test: {@link SimpleHistroricalSimulation#getCloneWithWindow(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.timeseries.HistoricalSimulationModel SimpleHistroricalSimulation.getCloneWithWindow(int, int)"
  })
  public void testGetCloneWithWindow() {
    // Arrange
    SimpleHistroricalSimulation simpleHistroricalSimulation =
        new SimpleHistroricalSimulation(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertTrue(
        simpleHistroricalSimulation.getCloneWithWindow(1, 1)
            instanceof SimpleHistroricalSimulation);
  }

  /**
   * Test {@link SimpleHistroricalSimulation#getSzenarios(int)}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@code -2.995732273553991} and {@code
   *       -2.995732273553991}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleHistroricalSimulation#getSzenarios(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SimpleHistroricalSimulation.getSzenarios(int)"})
  public void testGetSzenarios_thenReturnArrayOfDoubleWith2995732273553991And2995732273553991() {
    // Arrange
    SimpleHistroricalSimulation simpleHistroricalSimulation =
        new SimpleHistroricalSimulation(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertArrayEquals(
        new double[] {-2.995732273553991d, -2.995732273553991d, 2.995732273553991d},
        simpleHistroricalSimulation.getSzenarios(1),
        0.0);
  }

  /**
   * Test {@link SimpleHistroricalSimulation#getSzenarios(int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return array of {@code double} with {@code -9.5} and {@code -9.5}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleHistroricalSimulation#getSzenarios(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SimpleHistroricalSimulation.getSzenarios(int)"})
  public void testGetSzenarios_whenThree_thenReturnArrayOfDoubleWith95And95() {
    // Arrange
    SimpleHistroricalSimulation simpleHistroricalSimulation =
        new SimpleHistroricalSimulation(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertArrayEquals(
        new double[] {-9.5d, -9.5d, 9.5d}, simpleHistroricalSimulation.getSzenarios(3), 0.0);
  }

  /**
   * Test {@link SimpleHistroricalSimulation#getQuantilPredictions(int, double[])}.
   *
   * <p>Method under test: {@link SimpleHistroricalSimulation#getQuantilPredictions(int, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SimpleHistroricalSimulation.getQuantilPredictions(int, double[])"})
  public void testGetQuantilPredictions() {
    // Arrange
    SimpleHistroricalSimulation simpleHistroricalSimulation =
        new SimpleHistroricalSimulation(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertArrayEquals(
        new double[] {
          0.025000000000000005d, 0.025000000000000005d, 0.025000000000000005d, 0.025000000000000005d
        },
        simpleHistroricalSimulation.getQuantilPredictions(1, new double[] {0.5d, 0.5d, 0.5d, 0.5d}),
        0.0);
  }

  /**
   * Test {@link SimpleHistroricalSimulation#getQuantilPredictions(int, double[])}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with minus nine and minus nine.
   * </ul>
   *
   * <p>Method under test: {@link SimpleHistroricalSimulation#getQuantilPredictions(int, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SimpleHistroricalSimulation.getQuantilPredictions(int, double[])"})
  public void testGetQuantilPredictions_thenReturnArrayOfDoubleWithMinusNineAndMinusNine() {
    // Arrange
    SimpleHistroricalSimulation simpleHistroricalSimulation =
        new SimpleHistroricalSimulation(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertArrayEquals(
        new double[] {-9.0d, -9.0d, -9.0d, -9.0d},
        simpleHistroricalSimulation.getQuantilPredictions(3, new double[] {0.5d, 0.5d, 0.5d, 0.5d}),
        0.0);
  }

  /**
   * Test {@link SimpleHistroricalSimulation#getQuantilPredictions(int, double[])}.
   *
   * <ul>
   *   <li>When empty array of {@code double}.
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleHistroricalSimulation#getQuantilPredictions(int, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SimpleHistroricalSimulation.getQuantilPredictions(int, double[])"})
  public void testGetQuantilPredictions_whenEmptyArrayOfDouble_thenReturnEmptyArrayOfDouble() {
    // Arrange
    SimpleHistroricalSimulation simpleHistroricalSimulation =
        new SimpleHistroricalSimulation(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertArrayEquals(
        new double[] {},
        simpleHistroricalSimulation.getQuantilPredictions(1, new double[] {}),
        0.0);
  }

  /**
   * Test {@link SimpleHistroricalSimulation#getQuantilPredictions(int, double[])}.
   *
   * <ul>
   *   <li>When empty array of {@code double}.
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleHistroricalSimulation#getQuantilPredictions(int, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SimpleHistroricalSimulation.getQuantilPredictions(int, double[])"})
  public void testGetQuantilPredictions_whenEmptyArrayOfDouble_thenReturnEmptyArrayOfDouble2() {
    // Arrange
    SimpleHistroricalSimulation simpleHistroricalSimulation =
        new SimpleHistroricalSimulation(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertArrayEquals(
        new double[] {},
        simpleHistroricalSimulation.getQuantilPredictions(3, new double[] {}),
        0.0);
  }
}
