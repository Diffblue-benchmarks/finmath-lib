package net.finmath.montecarlo.automaticdifferentiation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomVariableDifferentiableDiffblueTest {
  /**
   * Test {@link RandomVariableDifferentiable#getGradient()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiable#getGradient()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map RandomVariableDifferentiable.getGradient()"})
  public void testGetGradient() {
    // Arrange
    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(10.0d);

    // Act and Assert
    assertEquals(1, ofResult.getGradient().size());
  }

  /**
   * Test {@link RandomVariableDifferentiable#getCloneIndependent()}.
   *
   * <p>Method under test: {@link RandomVariableDifferentiable#getCloneIndependent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariableDifferentiable RandomVariableDifferentiable.getCloneIndependent()"
  })
  public void testGetCloneIndependent() {
    // Arrange
    RandomVariableDifferentiableAADPathwise ofResult =
        RandomVariableDifferentiableAADPathwise.of(10.0d);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> ofResult.getCloneIndependent());
  }
}
