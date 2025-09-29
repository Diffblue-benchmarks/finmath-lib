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

public class EvolutionOfProductivityDiffblueTest {
  /**
   * Test {@link EvolutionOfProductivity#EvolutionOfProductivity(TimeDiscretization)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then calls {@link TenorFromArray#getTime(int)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * EvolutionOfProductivity#EvolutionOfProductivity(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvolutionOfProductivity.<init>(TimeDiscretization)"})
  public void testNewEvolutionOfProductivity_givenTen_thenCallsGetTime() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);

    // Act
    new EvolutionOfProductivity(timeDiscretization).apply(1);

    // Assert
    verify(timeDiscretization).getTime(1);
    verify(timeDiscretization).getTimeStep(1);
  }

  /**
   * Test {@link EvolutionOfProductivity#EvolutionOfProductivity(TimeDiscretization, double,
   * double)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then calls {@link TenorFromArray#getTime(int)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * EvolutionOfProductivity#EvolutionOfProductivity(TimeDiscretization, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvolutionOfProductivity.<init>(TimeDiscretization, double, double)"})
  public void testNewEvolutionOfProductivity_givenTen_thenCallsGetTime2() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);

    // Act
    new EvolutionOfProductivity(timeDiscretization, 10.0d, 10.0d).apply(1);

    // Assert
    verify(timeDiscretization).getTime(1);
    verify(timeDiscretization).getTimeStep(1);
  }

  /**
   * Test {@link EvolutionOfProductivity#apply(Integer)} with {@code Integer}.
   *
   * <ul>
   *   <li>Then return apply ten doubleValue is {@code 10.075126034303297}.
   * </ul>
   *
   * <p>Method under test: {@link EvolutionOfProductivity#apply(Integer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.function.Function EvolutionOfProductivity.apply(Integer)"})
  public void testApplyWithInteger_thenReturnApplyTenDoubleValueIs10075126034303297() {
    // Arrange, Act and Assert
    assertEquals(
        10.075126034303297d,
        new EvolutionOfProductivity(new TenorFromArray(10.0d, 10, 0.5d))
            .apply(1)
            .apply(10.0d)
            .doubleValue(),
        0.0);
  }
}
