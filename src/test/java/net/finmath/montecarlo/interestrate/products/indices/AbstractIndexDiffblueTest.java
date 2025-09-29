package net.finmath.montecarlo.interestrate.products.indices;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractIndexDiffblueTest {
  /**
   * Test {@link AbstractIndex#getName()}.
   *
   * <p>Method under test: {@link AbstractIndex#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractIndex.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertNull(new FixedCoupon(10.0d).getName());
  }
}
