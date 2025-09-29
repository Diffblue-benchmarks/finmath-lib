package net.finmath.randomnumbers;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MersenneTwisterDiffblueTest {
  /**
   * Test {@link MersenneTwister#MersenneTwister()}.
   *
   * <p>Method under test: {@link MersenneTwister#MersenneTwister()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MersenneTwister.<init>()"})
  public void testNewMersenneTwister() {
    // Arrange, Act and Assert
    assertEquals(1, new MersenneTwister().getDimension());
  }

  /**
   * Test {@link MersenneTwister#MersenneTwister(long)}.
   *
   * <p>Method under test: {@link MersenneTwister#MersenneTwister(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MersenneTwister.<init>(long)"})
  public void testNewMersenneTwister2() {
    // Arrange, Act and Assert
    assertEquals(1, new MersenneTwister(42L).getDimension());
  }
}
