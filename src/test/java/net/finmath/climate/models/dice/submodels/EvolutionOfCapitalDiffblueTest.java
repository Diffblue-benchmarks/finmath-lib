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

public class EvolutionOfCapitalDiffblueTest {
  /**
   * Test {@link EvolutionOfCapital#EvolutionOfCapital(TimeDiscretization)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then calls {@link TenorFromArray#getTimeStep(int)}.
   * </ul>
   *
   * <p>Method under test: {@link EvolutionOfCapital#EvolutionOfCapital(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvolutionOfCapital.<init>(TimeDiscretization)"})
  public void testNewEvolutionOfCapital_givenTen_thenCallsGetTimeStep() {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);

    // Act
    new EvolutionOfCapital(timeDiscretization).apply(1);

    // Assert
    verify(timeDiscretization).getTimeStep(1);
  }

  /**
   * Test {@link EvolutionOfCapital#apply(Integer)} with {@code Integer}.
   *
   * <ul>
   *   <li>Then return apply ten and ten doubleValue is {@code 14.486832980505138}.
   * </ul>
   *
   * <p>Method under test: {@link EvolutionOfCapital#apply(Integer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.function.BiFunction EvolutionOfCapital.apply(Integer)"})
  public void testApplyWithInteger_thenReturnApplyTenAndTenDoubleValueIs14486832980505138() {
    // Arrange, Act and Assert
    assertEquals(
        14.486832980505138d,
        new EvolutionOfCapital(new TenorFromArray(10.0d, 10, 0.5d))
            .apply(1)
            .apply(10.0d, 10.0d)
            .doubleValue(),
        0.0);
  }
}
