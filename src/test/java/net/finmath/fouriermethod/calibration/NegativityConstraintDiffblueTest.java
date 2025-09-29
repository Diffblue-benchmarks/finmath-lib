package net.finmath.fouriermethod.calibration;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NegativityConstraintDiffblueTest {
  /**
   * Test new {@link NegativityConstraint} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link NegativityConstraint}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NegativityConstraint.<init>()"})
  public void testNewNegativityConstraint() {
    // Arrange and Act
    NegativityConstraint actualNegativityConstraint = new NegativityConstraint();

    // Assert
    assertEquals(0.0d, actualNegativityConstraint.getUpperBound(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualNegativityConstraint.getLowerBound(), 0.0);
  }

  /**
   * Test {@link NegativityConstraint#apply(double)}.
   *
   * <p>Method under test: {@link NegativityConstraint#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double NegativityConstraint.apply(double)"})
  public void testApply() {
    // Arrange, Act and Assert
    assertEquals(-10.0d, new NegativityConstraint().apply(10.0d), 0.0);
  }
}
