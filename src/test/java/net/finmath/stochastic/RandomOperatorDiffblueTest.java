package net.finmath.stochastic;

import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomOperatorDiffblueTest {
  /**
   * Test {@link RandomOperator#identity()}.
   *
   * <p>Method under test: {@link RandomOperator#identity()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomOperator RandomOperator.identity()"})
  public void testIdentity() {
    // Arrange and Act
    RandomOperator actualIdentityResult = RandomOperator.identity();
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariable actualApplyResult = actualIdentityResult.apply(randomVariableFromDoubleArray);

    // Assert
    assertSame(randomVariableFromDoubleArray, actualApplyResult);
  }
}
