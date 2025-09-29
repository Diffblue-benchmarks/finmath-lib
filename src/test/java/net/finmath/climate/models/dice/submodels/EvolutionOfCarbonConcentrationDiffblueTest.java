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

public class EvolutionOfCarbonConcentrationDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link EvolutionOfCarbonConcentration#EvolutionOfCarbonConcentration(TimeDiscretization,
   *       Function)}
   *   <li>{@link EvolutionOfCarbonConcentration#getTimeDiscretization()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EvolutionOfCarbonConcentration.<init>(TimeDiscretization, Function)",
    "TimeDiscretization EvolutionOfCarbonConcentration.getTimeDiscretization()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    EvolutionOfCarbonConcentration actualEvolutionOfCarbonConcentration =
        new EvolutionOfCarbonConcentration(timeDiscretization, mock(Function.class));

    // Assert
    assertSame(timeDiscretization, actualEvolutionOfCarbonConcentration.getTimeDiscretization());
  }

  /**
   * Test {@link EvolutionOfCarbonConcentration#EvolutionOfCarbonConcentration(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * EvolutionOfCarbonConcentration#EvolutionOfCarbonConcentration(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvolutionOfCarbonConcentration.<init>(TimeDiscretization)"})
  public void testNewEvolutionOfCarbonConcentration() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertSame(
        timeDiscretization,
        new EvolutionOfCarbonConcentration(timeDiscretization).getTimeDiscretization());
  }

  /**
   * Test {@link EvolutionOfCarbonConcentration#apply(Integer, CarbonConcentration3DScalar, Double)}
   * with {@code Integer}, {@code CarbonConcentration3DScalar}, {@code Double}.
   *
   * <p>Method under test: {@link EvolutionOfCarbonConcentration#apply(Integer,
   * CarbonConcentration3DScalar, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CarbonConcentration3DScalar EvolutionOfCarbonConcentration.apply(Integer, CarbonConcentration3DScalar, Double)"
  })
  public void testApplyWithIntegerCarbonConcentration3DScalarDouble() {
    // Arrange
    EvolutionOfCarbonConcentration evolutionOfCarbonConcentration =
        new EvolutionOfCarbonConcentration(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    CarbonConcentration3DScalar actualApplyResult =
        evolutionOfCarbonConcentration.apply(1, new CarbonConcentration3DScalar(), 10.0d);

    // Assert
    assertTrue(actualApplyResult.getCarbonConcentrationInAtmosphere() instanceof Scalar);
    assertEquals(1740.0625787909594d, actualApplyResult.getCarbonConcentrationInLowerOcean(), 0.0);
    assertEquals(
        461.34122565201426d, actualApplyResult.getCarbonConcentrationInShallowOcean(), 0.0);
    assertEquals(
        850.9600798996343d,
        actualApplyResult.getExpectedCarbonConcentrationInAtmosphere().doubleValue(),
        0.0);
    assertArrayEquals(
        new double[] {850.9600798996343d, 461.34122565201426d, 1740.0625787909594d},
        actualApplyResult.getAsDoubleArray(),
        0.0);
  }
}
