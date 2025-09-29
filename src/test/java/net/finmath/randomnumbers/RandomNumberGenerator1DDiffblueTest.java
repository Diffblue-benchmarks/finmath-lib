package net.finmath.randomnumbers;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomNumberGenerator1DDiffblueTest {
  /**
   * Test {@link RandomNumberGenerator1D#nextDoubleFast()}.
   *
   * <p>Method under test: {@link RandomNumberGenerator1D#nextDoubleFast()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomNumberGenerator1D.nextDoubleFast()"})
  public void testNextDoubleFast() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new SobolSequence1D().nextDoubleFast(), 0.0);
  }

  /**
   * Test {@link RandomNumberGenerator1D#getDimension()}.
   *
   * <p>Method under test: {@link RandomNumberGenerator1D#getDimension()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RandomNumberGenerator1D.getDimension()"})
  public void testGetDimension() {
    // Arrange, Act and Assert
    assertEquals(1, new HighEntropyRandomNumberGenerator().getDimension());
  }
}
