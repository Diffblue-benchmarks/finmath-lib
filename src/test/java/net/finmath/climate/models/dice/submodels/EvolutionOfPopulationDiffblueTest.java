package net.finmath.climate.models.dice.submodels;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EvolutionOfPopulationDiffblueTest {
  /**
   * Test {@link EvolutionOfPopulation#EvolutionOfPopulation(TimeDiscretization)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then calls {@link TenorFromArray#getTimeStep(int)}.
   * </ul>
   *
   * <p>Method under test: {@link EvolutionOfPopulation#EvolutionOfPopulation(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvolutionOfPopulation.<init>(TimeDiscretization)"})
  public void testNewEvolutionOfPopulation_givenTen_thenCallsGetTimeStep() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);

    // Act
    new EvolutionOfPopulation(timeDiscretization).apply(1);

    // Assert
    verify(timeDiscretization).getTimeStep(1);
  }

  /**
   * Test {@link EvolutionOfPopulation#EvolutionOfPopulation(TimeDiscretization, double, double)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then calls {@link TenorFromArray#getTimeStep(int)}.
   * </ul>
   *
   * <p>Method under test: {@link EvolutionOfPopulation#EvolutionOfPopulation(TimeDiscretization,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvolutionOfPopulation.<init>(TimeDiscretization, double, double)"})
  public void testNewEvolutionOfPopulation_givenTen_thenCallsGetTimeStep2() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);

    // Act
    new EvolutionOfPopulation(timeDiscretization, 10.0d, 10.0d).apply(1);

    // Assert
    verify(timeDiscretization).getTimeStep(1);
  }

  /**
   * Test {@link EvolutionOfPopulation#apply(Integer)} with {@code Integer}.
   *
   * <ul>
   *   <li>Then return apply ten doubleValue is {@code 10.99039625538181}.
   * </ul>
   *
   * <p>Method under test: {@link EvolutionOfPopulation#apply(Integer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.function.Function EvolutionOfPopulation.apply(Integer)"})
  public void testApplyWithInteger_thenReturnApplyTenDoubleValueIs1099039625538181() {
    // Arrange, Act and Assert
    assertEquals(
        10.99039625538181d,
        new EvolutionOfPopulation(new TenorFromArray(10.0d, 10, 0.5d))
            .apply(1)
            .apply(10.0d)
            .doubleValue(),
        0.0);
  }
}
