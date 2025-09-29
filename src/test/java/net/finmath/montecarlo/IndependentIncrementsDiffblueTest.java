package net.finmath.montecarlo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class IndependentIncrementsDiffblueTest {
  /**
   * Test {@link IndependentIncrements#getIncrement(int)} with {@code int}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link IndependentIncrements#getIncrement(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] IndependentIncrements.getIncrement(int)"})
  public void testGetIncrementWithInt_thenFirstElementReturnRandomVariableFromDoubleArray() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    // Act
    RandomVariable[] actualIncrement =
        new BrownianMotionWithControlVariate(brownianMotion).getIncrement(1);

    // Assert
    assertTrue(actualIncrement[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIncrement[2] instanceof RandomVariableFromDoubleArray);
    assertEquals(3, actualIncrement.length);
  }
}
