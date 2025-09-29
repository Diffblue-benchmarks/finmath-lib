package net.finmath.fouriermethod.calibration;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PositivityConstraintDiffblueTest {
  /**
   * Test new {@link PositivityConstraint} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link PositivityConstraint}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PositivityConstraint.<init>()"})
  public void testNewPositivityConstraint() {
    // Arrange and Act
    PositivityConstraint actualPositivityConstraint = new PositivityConstraint();

    // Assert
    assertEquals(0.0d, actualPositivityConstraint.getLowerBound(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, actualPositivityConstraint.getUpperBound(), 0.0);
  }

  /**
   * Test {@link PositivityConstraint#apply(double)}.
   *
   * <p>Method under test: {@link PositivityConstraint#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PositivityConstraint.apply(double)"})
  public void testApply() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new PositivityConstraint().apply(10.0d), 0.0);
  }
}
