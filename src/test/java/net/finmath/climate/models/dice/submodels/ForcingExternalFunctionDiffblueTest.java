package net.finmath.climate.models.dice.submodels;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ForcingExternalFunctionDiffblueTest {
  /**
   * Test {@link ForcingExternalFunction#ForcingExternalFunction()}.
   *
   * <p>Method under test: {@link ForcingExternalFunction#ForcingExternalFunction()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ForcingExternalFunction.<init>()"})
  public void testNewForcingExternalFunction() {
    // Arrange, Act and Assert
    assertEquals(
        0.5588235294117647d, new ForcingExternalFunction().apply(10.0d).doubleValue(), 0.0);
  }

  /**
   * Test {@link ForcingExternalFunction#ForcingExternalFunction(double, double)}.
   *
   * <p>Method under test: {@link ForcingExternalFunction#ForcingExternalFunction(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ForcingExternalFunction.<init>(double, double)"})
  public void testNewForcingExternalFunction2() {
    // Arrange, Act and Assert
    assertEquals(1.0d, new ForcingExternalFunction(10.0d, 10.0d).apply(10.0d).doubleValue(), 0.0);
  }

  /**
   * Test {@link ForcingExternalFunction#apply(Double)} with {@code Double}.
   *
   * <p>Method under test: {@link ForcingExternalFunction#apply(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double ForcingExternalFunction.apply(Double)"})
  public void testApplyWithDouble() {
    // Arrange, Act and Assert
    assertEquals(
        0.5588235294117647d, new ForcingExternalFunction().apply(10.0d).doubleValue(), 0.0);
  }
}
