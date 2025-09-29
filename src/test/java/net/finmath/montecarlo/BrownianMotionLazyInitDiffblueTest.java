package net.finmath.montecarlo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BrownianMotionLazyInitDiffblueTest {
  /**
   * Test {@link BrownianMotionLazyInit#BrownianMotionLazyInit(TimeDiscretization, int, int, int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return NumberOfPaths is ten.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotionLazyInit#BrownianMotionLazyInit(TimeDiscretization,
   * int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BrownianMotionLazyInit.<init>(TimeDiscretization, int, int, int)"})
  public void testNewBrownianMotionLazyInit_whenThree_thenReturnNumberOfPathsIsTen() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    BrownianMotionLazyInit actualBrownianMotionLazyInit =
        new BrownianMotionLazyInit(timeDiscretization, 3, 10, 42);

    // Assert
    assertEquals(10, actualBrownianMotionLazyInit.getNumberOfPaths());
    assertEquals(3, actualBrownianMotionLazyInit.getNumberOfFactors());
    assertEquals(42, actualBrownianMotionLazyInit.getSeed());
    assertSame(timeDiscretization, actualBrownianMotionLazyInit.getTimeDiscretization());
  }

  /**
   * Test {@link BrownianMotionLazyInit#BrownianMotionLazyInit(TimeDiscretization, int, int, int,
   * RandomVariableFactory)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return NumberOfPaths is ten.
   * </ul>
   *
   * <p>Method under test: {@link BrownianMotionLazyInit#BrownianMotionLazyInit(TimeDiscretization,
   * int, int, int, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BrownianMotionLazyInit.<init>(TimeDiscretization, int, int, int, RandomVariableFactory)"
  })
  public void testNewBrownianMotionLazyInit_whenThree_thenReturnNumberOfPathsIsTen2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    BrownianMotionLazyInit actualBrownianMotionLazyInit =
        new BrownianMotionLazyInit(timeDiscretization, 3, 10, 42, new RandomVariableFloatFactory());

    // Assert
    assertEquals(10, actualBrownianMotionLazyInit.getNumberOfPaths());
    assertEquals(3, actualBrownianMotionLazyInit.getNumberOfFactors());
    assertEquals(42, actualBrownianMotionLazyInit.getSeed());
    assertSame(timeDiscretization, actualBrownianMotionLazyInit.getTimeDiscretization());
  }
}
