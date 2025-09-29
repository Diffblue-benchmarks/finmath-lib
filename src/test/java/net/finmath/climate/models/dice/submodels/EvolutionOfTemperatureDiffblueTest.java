package net.finmath.climate.models.dice.submodels;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Function;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EvolutionOfTemperatureDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link EvolutionOfTemperature#EvolutionOfTemperature(TimeDiscretization, Function,
   *       double)}
   *   <li>{@link EvolutionOfTemperature#getTimeDiscretization()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EvolutionOfTemperature.<init>(TimeDiscretization, Function, double)",
    "TimeDiscretization EvolutionOfTemperature.getTimeDiscretization()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    EvolutionOfTemperature actualEvolutionOfTemperature =
        new EvolutionOfTemperature(timeDiscretization, mock(Function.class), 10.0d);

    // Assert
    assertSame(timeDiscretization, actualEvolutionOfTemperature.getTimeDiscretization());
  }

  /**
   * Test {@link EvolutionOfTemperature#EvolutionOfTemperature(TimeDiscretization)}.
   *
   * <p>Method under test: {@link EvolutionOfTemperature#EvolutionOfTemperature(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvolutionOfTemperature.<init>(TimeDiscretization)"})
  public void testNewEvolutionOfTemperature() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertSame(
        timeDiscretization, new EvolutionOfTemperature(timeDiscretization).getTimeDiscretization());
  }

  /**
   * Test {@link EvolutionOfTemperature#apply(Integer, Temperature2DScalar, Double)} with {@code
   * Integer}, {@code Temperature2DScalar}, {@code Double}.
   *
   * <p>Method under test: {@link EvolutionOfTemperature#apply(Integer, Temperature2DScalar,
   * Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Temperature2DScalar EvolutionOfTemperature.apply(Integer, Temperature2DScalar, Double)"
  })
  public void testApplyWithIntegerTemperature2DScalarDouble() {
    // Arrange
    EvolutionOfTemperature evolutionOfTemperature =
        new EvolutionOfTemperature(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Temperature2DScalar actualApplyResult =
        evolutionOfTemperature.apply(1, new Temperature2DScalar(), 10.0d);

    // Assert
    assertTrue(actualApplyResult.getTemperatureOfAtmosphere() instanceof Scalar);
    assertEquals(0.009067933172313936d, actualApplyResult.getTemperatureOfLandAndOcean(), 0.0);
    assertEquals(
        0.9389153068741453d,
        actualApplyResult.getExpectedTemperatureOfAtmosphere().doubleValue(),
        0.0);
    assertArrayEquals(
        new double[] {0.9389153068741453d, 0.009067933172313936d},
        actualApplyResult.getAsDoubleArray(),
        0.0);
  }
}
