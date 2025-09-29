package net.finmath.montecarlo.conditionalexpectation;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class LinearRegressionDiffblueTest {
  /**
   * Test {@link LinearRegression#getRegressionCoefficients(RandomVariable)}.
   *
   * <p>Method under test: {@link LinearRegression#getRegressionCoefficients(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LinearRegression.getRegressionCoefficients(RandomVariable)"})
  public void testGetRegressionCoefficients() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    LinearRegression linearRegression =
        new LinearRegression(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });

    // Act
    double[] actualRegressionCoefficients =
        linearRegression.getRegressionCoefficients(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertArrayEquals(
        new double[] {0.009900990099009901d, 0.009900990099009901d},
        actualRegressionCoefficients,
        0.0);
  }

  /**
   * Test {@link LinearRegression#getRegressionCoefficients(RandomVariable)}.
   *
   * <p>Method under test: {@link LinearRegression#getRegressionCoefficients(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LinearRegression.getRegressionCoefficients(RandomVariable)"})
  public void testGetRegressionCoefficients2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    LinearRegression linearRegression =
        new LinearRegression(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Act
    double[] actualRegressionCoefficients =
        linearRegression.getRegressionCoefficients(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertArrayEquals(
        new double[] {0.3333333333333335d, 0.33333333333333337d, 0.3333333333333335d},
        actualRegressionCoefficients,
        0.0);
  }

  /**
   * Test {@link LinearRegression#getRegressionCoefficients(RandomVariable)}.
   *
   * <p>Method under test: {@link LinearRegression#getRegressionCoefficients(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LinearRegression.getRegressionCoefficients(RandomVariable)"})
  public void testGetRegressionCoefficients3() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD2.average()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(0.5d));
    when(randomVariableAAD4.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    LinearRegression linearRegression =
        new LinearRegression(new RandomVariable[] {randomVariableAAD3, randomVariableAAD4});

    // Act
    double[] actualRegressionCoefficients = linearRegression.getRegressionCoefficients(null);

    // Assert
    verify(randomVariableAAD2).average();
    verify(randomVariableAAD2, atLeast(1)).getAverage();
    verify(randomVariableAAD3, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(randomVariableAAD4, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(randomVariableAAD3).squared();
    verify(randomVariableAAD).squared();
    verify(randomVariableAAD4).squared();
    assertArrayEquals(
        new double[] {0.9999999999999998d, 2.220446049250313E-16d},
        actualRegressionCoefficients,
        0.0);
  }

  /**
   * Test {@link LinearRegression#getRegressionCoefficients(RandomVariable)}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@code 0.5} and {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link LinearRegression#getRegressionCoefficients(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LinearRegression.getRegressionCoefficients(RandomVariable)"})
  public void testGetRegressionCoefficients_thenReturnArrayOfDoubleWith05And05() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD2.average()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD4.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    LinearRegression linearRegression =
        new LinearRegression(new RandomVariable[] {randomVariableAAD3, randomVariableAAD4});

    // Act
    double[] actualRegressionCoefficients = linearRegression.getRegressionCoefficients(null);

    // Assert
    verify(randomVariableAAD2).average();
    verify(randomVariableAAD2, atLeast(1)).getAverage();
    verify(randomVariableAAD3, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(randomVariableAAD4, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(randomVariableAAD3).squared();
    verify(randomVariableAAD).squared();
    verify(randomVariableAAD4).squared();
    assertArrayEquals(new double[] {0.5d, 0.5d}, actualRegressionCoefficients, 0.0);
  }

  /**
   * Test {@link LinearRegression#getRegressionCoefficients(RandomVariable)}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@link Double#NaN} and {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link LinearRegression#getRegressionCoefficients(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LinearRegression.getRegressionCoefficients(RandomVariable)"})
  public void testGetRegressionCoefficients_thenReturnArrayOfDoubleWithNaNAndNaN() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD2.average()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY));
    when(randomVariableAAD4.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    LinearRegression linearRegression =
        new LinearRegression(new RandomVariable[] {randomVariableAAD3, randomVariableAAD4});

    // Act
    double[] actualRegressionCoefficients = linearRegression.getRegressionCoefficients(null);

    // Assert
    verify(randomVariableAAD2).average();
    verify(randomVariableAAD2, atLeast(1)).getAverage();
    verify(randomVariableAAD3, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(randomVariableAAD4, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(randomVariableAAD3).squared();
    verify(randomVariableAAD).squared();
    verify(randomVariableAAD4).squared();
    assertArrayEquals(new double[] {Double.NaN, Double.NaN}, actualRegressionCoefficients, 0.0);
  }

  /**
   * Test {@link LinearRegression#getRegressionCoefficients(RandomVariable)}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with one.
   * </ul>
   *
   * <p>Method under test: {@link LinearRegression#getRegressionCoefficients(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LinearRegression.getRegressionCoefficients(RandomVariable)"})
  public void testGetRegressionCoefficients_thenReturnArrayOfDoubleWithOne() {
    // Arrange
    RandomVariable[] basisFunctions =
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    LinearRegression linearRegression = new LinearRegression(basisFunctions);

    // Act
    double[] actualRegressionCoefficients =
        linearRegression.getRegressionCoefficients(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertArrayEquals(new double[] {1.0d}, actualRegressionCoefficients, 0.0);
  }

  /**
   * Test {@link LinearRegression#getRegressionCoefficients(RandomVariable)}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with one and zero.
   * </ul>
   *
   * <p>Method under test: {@link LinearRegression#getRegressionCoefficients(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LinearRegression.getRegressionCoefficients(RandomVariable)"})
  public void testGetRegressionCoefficients_thenReturnArrayOfDoubleWithOneAndZero() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.getAverage()).thenReturn(10.0d);
    when(randomVariableAAD2.average()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.mult(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);
    when(randomVariableAAD3.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD4 = mock(RandomVariableAAD.class);
    when(randomVariableAAD4.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(0.0d));
    when(randomVariableAAD4.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    LinearRegression linearRegression =
        new LinearRegression(new RandomVariable[] {randomVariableAAD3, randomVariableAAD4});

    // Act
    double[] actualRegressionCoefficients = linearRegression.getRegressionCoefficients(null);

    // Assert
    verify(randomVariableAAD2).average();
    verify(randomVariableAAD2, atLeast(1)).getAverage();
    verify(randomVariableAAD3, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(randomVariableAAD4, atLeast(1)).mult(Mockito.<RandomVariable>any());
    verify(randomVariableAAD3).squared();
    verify(randomVariableAAD).squared();
    verify(randomVariableAAD4).squared();
    assertArrayEquals(new double[] {1.0d, 0.0d}, actualRegressionCoefficients, 0.0);
  }

  /**
   * Test {@link LinearRegression#getRegressionCoefficients(RandomVariable)}.
   *
   * <ul>
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link LinearRegression#getRegressionCoefficients(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LinearRegression.getRegressionCoefficients(RandomVariable)"})
  public void testGetRegressionCoefficients_thenReturnEmptyArrayOfDouble() {
    // Arrange
    LinearRegression linearRegression = new LinearRegression(new RandomVariable[] {});

    // Act
    double[] actualRegressionCoefficients =
        linearRegression.getRegressionCoefficients(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertArrayEquals(new double[] {}, actualRegressionCoefficients, 0.0);
  }
}
