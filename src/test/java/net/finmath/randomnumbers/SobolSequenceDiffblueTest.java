package net.finmath.randomnumbers;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SobolSequenceDiffblueTest {
  /**
   * Test {@link SobolSequence#getNext()}.
   *
   * <p>Method under test: {@link SobolSequence#getNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SobolSequence.getNext()"})
  public void testGetNext() {
    // Arrange, Act and Assert
    assertArrayEquals(new double[] {0.0d}, new SobolSequence(1).getNext(), 0.0);
  }

  /**
   * Test {@link SobolSequence#getDimension()}.
   *
   * <p>Method under test: {@link SobolSequence#getDimension()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SobolSequence.getDimension()"})
  public void testGetDimension() {
    // Arrange, Act and Assert
    assertEquals(1, new SobolSequence(1).getDimension());
  }
}
