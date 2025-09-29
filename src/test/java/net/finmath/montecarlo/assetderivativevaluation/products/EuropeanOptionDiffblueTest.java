package net.finmath.montecarlo.assetderivativevaluation.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.modelling.Model;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EuropeanOptionDiffblueTest {
  /**
   * Test {@link EuropeanOption#EuropeanOption(double, double)}.
   *
   * <p>Method under test: {@link EuropeanOption#EuropeanOption(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EuropeanOption.<init>(double, double)"})
  public void testNewEuropeanOption() {
    // Arrange and Act
    EuropeanOption actualEuropeanOption = new EuropeanOption(10.0d, 10.0d);

    // Assert
    assertNull(actualEuropeanOption.getCurrency());
    assertNull(actualEuropeanOption.getNameOfUnderliyng());
    assertEquals(0, actualEuropeanOption.getUnderlyingIndex().intValue());
    assertEquals(10.0d, actualEuropeanOption.getMaturity(), 0.0);
    assertEquals(10.0d, actualEuropeanOption.getStrike(), 0.0);
  }

  /**
   * Test {@link EuropeanOption#EuropeanOption(double, double, double, int)}.
   *
   * <p>Method under test: {@link EuropeanOption#EuropeanOption(double, double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EuropeanOption.<init>(double, double, double, int)"})
  public void testNewEuropeanOption2() {
    // Arrange and Act
    EuropeanOption actualEuropeanOption = new EuropeanOption(10.0d, 10.0d, 10.0d, 1);

    // Assert
    assertNull(actualEuropeanOption.getCurrency());
    assertNull(actualEuropeanOption.getNameOfUnderliyng());
    assertEquals(1, actualEuropeanOption.getUnderlyingIndex().intValue());
    assertEquals(10.0d, actualEuropeanOption.getMaturity(), 0.0);
    assertEquals(10.0d, actualEuropeanOption.getStrike(), 0.0);
  }

  /**
   * Test {@link EuropeanOption#EuropeanOption(double, double, int)}.
   *
   * <p>Method under test: {@link EuropeanOption#EuropeanOption(double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EuropeanOption.<init>(double, double, int)"})
  public void testNewEuropeanOption3() {
    // Arrange and Act
    EuropeanOption actualEuropeanOption = new EuropeanOption(10.0d, 10.0d, 1);

    // Assert
    assertNull(actualEuropeanOption.getCurrency());
    assertNull(actualEuropeanOption.getNameOfUnderliyng());
    assertEquals(1, actualEuropeanOption.getUnderlyingIndex().intValue());
    assertEquals(10.0d, actualEuropeanOption.getMaturity(), 0.0);
    assertEquals(10.0d, actualEuropeanOption.getStrike(), 0.0);
  }

  /**
   * Test {@link EuropeanOption#EuropeanOption(String, double, double)}.
   *
   * <p>Method under test: {@link EuropeanOption#EuropeanOption(String, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EuropeanOption.<init>(String, double, double)"})
  public void testNewEuropeanOption4() {
    // Arrange and Act
    EuropeanOption actualEuropeanOption = new EuropeanOption("Underlying Name", 10.0d, 10.0d);

    // Assert
    assertEquals("Underlying Name", actualEuropeanOption.getNameOfUnderliyng());
    assertNull(actualEuropeanOption.getCurrency());
    assertEquals(0, actualEuropeanOption.getUnderlyingIndex().intValue());
    assertEquals(10.0d, actualEuropeanOption.getMaturity(), 0.0);
    assertEquals(10.0d, actualEuropeanOption.getStrike(), 0.0);
  }

  /**
   * Test {@link EuropeanOption#EuropeanOption(String, double, double, double)}.
   *
   * <p>Method under test: {@link EuropeanOption#EuropeanOption(String, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EuropeanOption.<init>(String, double, double, double)"})
  public void testNewEuropeanOption5() {
    // Arrange and Act
    EuropeanOption actualEuropeanOption =
        new EuropeanOption("Underlying Name", 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals("Underlying Name", actualEuropeanOption.getNameOfUnderliyng());
    assertNull(actualEuropeanOption.getCurrency());
    assertEquals(0, actualEuropeanOption.getUnderlyingIndex().intValue());
    assertEquals(10.0d, actualEuropeanOption.getMaturity(), 0.0);
    assertEquals(10.0d, actualEuropeanOption.getStrike(), 0.0);
  }

  /**
   * Test {@link EuropeanOption#getValue(double, AssetModelMonteCarloSimulationModel)} with {@code
   * double}, {@code AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link EuropeanOption#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable EuropeanOption.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    EuropeanOption europeanOption = new EuropeanOption(10.0d, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualValue = europeanOption.getValue(10.0d, new MonteCarloAssetModel(process));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(1, actualValue.size());
    assertEquals(2.6881171418161355E44d, actualValue.getAverage(), 0.0);
    assertEquals(2.6881171418161355E44d, actualValue.getMax(), 0.0);
    assertEquals(2.6881171418161355E44d, actualValue.getMin(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {2.6881171418161355E44d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link EuropeanOption#getValues(double, Model)} with {@code double}, {@code Model}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOption#getValues(double, Model)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map EuropeanOption.getValues(double, Model)"})
  public void testGetValuesWithDoubleModel_thenReturnSizeIsOne() {
    // Arrange
    EuropeanOption europeanOption = new EuropeanOption(10.0d, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    Map<String, Object> actualValues =
        europeanOption.getValues(10.0d, (Model) new MonteCarloAssetModel(process));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertEquals(1, actualValues.size());
    assertEquals(2.6881171418161355E44d, ((Double) actualValues.get("value")).doubleValue(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link EuropeanOption#toString()}
   *   <li>{@link EuropeanOption#getMaturity()}
   *   <li>{@link EuropeanOption#getNameOfUnderliyng()}
   *   <li>{@link EuropeanOption#getStrike()}
   *   <li>{@link EuropeanOption#getUnderlyingIndex()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double EuropeanOption.getMaturity()",
    "String EuropeanOption.getNameOfUnderliyng()",
    "double EuropeanOption.getStrike()",
    "java.lang.Integer EuropeanOption.getUnderlyingIndex()",
    "String EuropeanOption.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    EuropeanOption europeanOption = new EuropeanOption(10.0d, 10.0d);

    // Act
    String actualToStringResult = europeanOption.toString();
    double actualMaturity = europeanOption.getMaturity();
    String actualNameOfUnderliyng = europeanOption.getNameOfUnderliyng();
    double actualStrike = europeanOption.getStrike();

    // Assert
    assertEquals(
        "EuropeanOption [maturity=10.0, strike=10.0, underlyingIndex=0, nameOfUnderliyng=null]",
        actualToStringResult);
    assertNull(actualNameOfUnderliyng);
    assertEquals(0, europeanOption.getUnderlyingIndex().intValue());
    assertEquals(10.0d, actualMaturity, 0.0);
    assertEquals(10.0d, actualStrike, 0.0);
  }
}
