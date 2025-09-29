package net.finmath.fouriermethod.calibration;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoundConstraintDiffblueTest {
  /**
   * Test {@link BoundConstraint#BoundConstraint(double, double)}.
   *
   * <p>Method under test: {@link BoundConstraint#BoundConstraint(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BoundConstraint.<init>(double, double)"})
  public void testNewBoundConstraint() {
    // Arrange and Act
    BoundConstraint actualBoundConstraint = new BoundConstraint(10.0d, 10.0d);

    // Assert
    assertEquals(10.0d, actualBoundConstraint.getLowerBound(), 0.0);
    assertEquals(10.0d, actualBoundConstraint.getUpperBound(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BoundConstraint#getLowerBound()}
   *   <li>{@link BoundConstraint#getUpperBound()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BoundConstraint.getLowerBound()",
    "double BoundConstraint.getUpperBound()"
  })
  public void testGettersAndSetters() {
    // Arrange
    BoundConstraint boundConstraint = new BoundConstraint(10.0d, 10.0d);

    // Act
    double actualLowerBound = boundConstraint.getLowerBound();

    // Assert
    assertEquals(10.0d, actualLowerBound, 0.0);
    assertEquals(10.0d, boundConstraint.getUpperBound(), 0.0);
  }

  /**
   * Test {@link BoundConstraint#apply(double)}.
   *
   * <ul>
   *   <li>Then return {@code 9.999568720247327}.
   * </ul>
   *
   * <p>Method under test: {@link BoundConstraint#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BoundConstraint.apply(double)"})
  public void testApply_thenReturn9999568720247327() {
    // Arrange, Act and Assert
    assertEquals(9.999568720247327d, new BoundConstraint(10.0d, 0.5d).apply(10.0d), 0.0);
  }

  /**
   * Test {@link BoundConstraint#apply(double)}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BoundConstraint#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BoundConstraint.apply(double)"})
  public void testApply_thenReturnTen() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new BoundConstraint(10.0d, 10.0d).apply(10.0d), 0.0);
  }

  /**
   * Test {@link BoundConstraint#apply(double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BoundConstraint#apply(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BoundConstraint.apply(double)"})
  public void testApply_when05_thenReturnTen() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new BoundConstraint(10.0d, 10.0d).apply(0.5d), 0.0);
  }
}
