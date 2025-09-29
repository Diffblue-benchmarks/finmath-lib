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
import net.finmath.exception.CalculationException;
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

public class DigitalOptionDiffblueTest {
  /**
   * Test {@link DigitalOption#DigitalOption(double, double)}.
   *
   * <p>Method under test: {@link DigitalOption#DigitalOption(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DigitalOption.<init>(double, double)"})
  public void testNewDigitalOption() {
    // Arrange and Act
    DigitalOption actualDigitalOption = new DigitalOption(10.0d, 10.0d);

    // Assert
    assertNull(actualDigitalOption.getCurrency());
    assertNull(actualDigitalOption.getNameOfUnderlying());
    assertEquals(0, actualDigitalOption.getUnderlyingIndex().intValue());
    assertEquals(10.0d, actualDigitalOption.getMaturity(), 0.0);
    assertEquals(10.0d, actualDigitalOption.getStrike(), 0.0);
  }

  /**
   * Test {@link DigitalOption#DigitalOption(double, double, int)}.
   *
   * <p>Method under test: {@link DigitalOption#DigitalOption(double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DigitalOption.<init>(double, double, int)"})
  public void testNewDigitalOption2() {
    // Arrange and Act
    DigitalOption actualDigitalOption = new DigitalOption(10.0d, 10.0d, 1);

    // Assert
    assertNull(actualDigitalOption.getCurrency());
    assertNull(actualDigitalOption.getNameOfUnderlying());
    assertEquals(1, actualDigitalOption.getUnderlyingIndex().intValue());
    assertEquals(10.0d, actualDigitalOption.getMaturity(), 0.0);
    assertEquals(10.0d, actualDigitalOption.getStrike(), 0.0);
  }

  /**
   * Test {@link DigitalOption#DigitalOption(String, double, double)}.
   *
   * <p>Method under test: {@link DigitalOption#DigitalOption(String, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DigitalOption.<init>(String, double, double)"})
  public void testNewDigitalOption3() {
    // Arrange and Act
    DigitalOption actualDigitalOption = new DigitalOption("Name Of Underlying", 10.0d, 10.0d);

    // Assert
    assertEquals("Name Of Underlying", actualDigitalOption.getNameOfUnderlying());
    assertNull(actualDigitalOption.getCurrency());
    assertEquals(0, actualDigitalOption.getUnderlyingIndex().intValue());
    assertEquals(10.0d, actualDigitalOption.getMaturity(), 0.0);
    assertEquals(10.0d, actualDigitalOption.getStrike(), 0.0);
  }

  /**
   * Test {@link DigitalOption#getValue(double, AssetModelMonteCarloSimulationModel)} with {@code
   * double}, {@code AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link DigitalOption#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DigitalOption.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    DigitalOption digitalOption = new DigitalOption(10.0d, 10.0d);

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
    RandomVariable actualValue = digitalOption.getValue(10.0d, new MonteCarloAssetModel(process));

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
    assertEquals(1.0d, actualValue.getAverage(), 0.0);
    assertEquals(1.0d, actualValue.getMax(), 0.0);
    assertEquals(1.0d, actualValue.getMin(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DigitalOption#toString()}
   *   <li>{@link DigitalOption#getMaturity()}
   *   <li>{@link DigitalOption#getNameOfUnderlying()}
   *   <li>{@link DigitalOption#getStrike()}
   *   <li>{@link DigitalOption#getUnderlyingIndex()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DigitalOption.getMaturity()",
    "String DigitalOption.getNameOfUnderlying()",
    "double DigitalOption.getStrike()",
    "java.lang.Integer DigitalOption.getUnderlyingIndex()",
    "String DigitalOption.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DigitalOption digitalOption = new DigitalOption(10.0d, 10.0d);

    // Act
    String actualToStringResult = digitalOption.toString();
    double actualMaturity = digitalOption.getMaturity();
    String actualNameOfUnderlying = digitalOption.getNameOfUnderlying();
    double actualStrike = digitalOption.getStrike();

    // Assert
    assertEquals(
        "DigitalOption [maturity=10.0, strike=10.0, underlyingIndex=0, nameOfUnderlying=null]",
        actualToStringResult);
    assertNull(actualNameOfUnderlying);
    assertEquals(0, digitalOption.getUnderlyingIndex().intValue());
    assertEquals(10.0d, actualMaturity, 0.0);
    assertEquals(10.0d, actualStrike, 0.0);
  }
}
