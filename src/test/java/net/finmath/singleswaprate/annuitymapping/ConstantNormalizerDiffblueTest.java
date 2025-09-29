package net.finmath.singleswaprate.annuitymapping;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ConstantNormalizerDiffblueTest {
  /**
   * Test {@link ConstantNormalizer#getValue(double)}.
   *
   * <p>Method under test: {@link ConstantNormalizer#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ConstantNormalizer.getValue(double)"})
  public void testGetValue() {
    // Arrange, Act and Assert
    assertEquals(1.0d, new ConstantNormalizer().getValue(10.0d), 0.0);
  }

  /**
   * Test {@link ConstantNormalizer#getFirstDerivative(double)}.
   *
   * <p>Method under test: {@link ConstantNormalizer#getFirstDerivative(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ConstantNormalizer.getFirstDerivative(double)"})
  public void testGetFirstDerivative() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new ConstantNormalizer().getFirstDerivative(10.0d), 0.0);
  }

  /**
   * Test {@link ConstantNormalizer#getSecondDerivative(double)}.
   *
   * <p>Method under test: {@link ConstantNormalizer#getSecondDerivative(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ConstantNormalizer.getSecondDerivative(double)"})
  public void testGetSecondDerivative() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new ConstantNormalizer().getSecondDerivative(10.0d), 0.0);
  }
}
