package net.finmath.climate.models.dice.submodels;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EmissionExternalFunctionDiffblueTest {
  /**
   * Test {@link EmissionExternalFunction#EmissionExternalFunction()}.
   *
   * <p>Method under test: {@link EmissionExternalFunction#EmissionExternalFunction()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EmissionExternalFunction.<init>()"})
  public void testNewEmissionExternalFunction() {
    // Arrange, Act and Assert
    assertEquals(2.036385d, new EmissionExternalFunction().apply(10.0d).doubleValue(), 0.0);
  }

  /**
   * Test {@link EmissionExternalFunction#EmissionExternalFunction(double, double)}.
   *
   * <p>Method under test: {@link EmissionExternalFunction#EmissionExternalFunction(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EmissionExternalFunction.<init>(double, double)"})
  public void testNewEmissionExternalFunction2() {
    // Arrange, Act and Assert
    assertEquals(
        3.720075976020836E-43d,
        new EmissionExternalFunction(10.0d, 10.0d).apply(10.0d).doubleValue(),
        0.0);
  }

  /**
   * Test {@link EmissionExternalFunction#apply(Double)} with {@code Double}.
   *
   * <p>Method under test: {@link EmissionExternalFunction#apply(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double EmissionExternalFunction.apply(Double)"})
  public void testApplyWithDouble() {
    // Arrange, Act and Assert
    assertEquals(2.036385d, new EmissionExternalFunction().apply(10.0d).doubleValue(), 0.0);
  }
}
