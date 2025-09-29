package net.finmath.randomnumbers;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SobolSequence1DDiffblueTest {
  /**
   * Test new {@link SobolSequence1D} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SobolSequence1D}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SobolSequence1D.<init>()"})
  public void testNewSobolSequence1D() {
    // Arrange and Act
    SobolSequence1D actualSobolSequence1D = new SobolSequence1D();

    // Assert
    assertEquals(0.0d, actualSobolSequence1D.nextDouble(), 0.0);
    assertEquals(1, actualSobolSequence1D.getDimension());
  }

  /**
   * Test {@link SobolSequence1D#nextDouble()}.
   *
   * <p>Method under test: {@link SobolSequence1D#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SobolSequence1D.nextDouble()"})
  public void testNextDouble() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new SobolSequence1D().nextDouble(), 0.0);
  }
}
