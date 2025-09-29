package net.finmath.fouriermethod.calibration;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UnconstrainedDiffblueTest {
  /**
   * Test new {@link Unconstrained} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link Unconstrained}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Unconstrained.<init>()"})
  public void testNewUnconstrained() {
    // Arrange and Act
    Unconstrained actualUnconstrained = new Unconstrained();

    // Assert
    assertEquals(Double.NEGATIVE_INFINITY, actualUnconstrained.getLowerBound(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualUnconstrained.getUpperBound(), 0.0);
  }

  /**
   * Test {@link Unconstrained#apply(double)}.
   *
   * <p>Method under test: {@link Unconstrained#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Unconstrained.apply(double)"})
  public void testApply() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new Unconstrained().apply(10.0d), 0.0);
  }
}
