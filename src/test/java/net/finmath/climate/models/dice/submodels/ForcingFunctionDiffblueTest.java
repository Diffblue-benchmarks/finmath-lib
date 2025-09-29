package net.finmath.climate.models.dice.submodels;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ForcingFunctionDiffblueTest {
  /**
   * Test {@link ForcingFunction#apply(CarbonConcentration3DScalar, Double)} with {@code
   * CarbonConcentration3DScalar}, {@code Double}.
   *
   * <p>Method under test: {@link ForcingFunction#apply(CarbonConcentration3DScalar, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double ForcingFunction.apply(CarbonConcentration3DScalar, Double)"})
  public void testApplyWithCarbonConcentration3DScalarDouble() {
    // Arrange
    ForcingFunction forcingFunction = new ForcingFunction();

    // Act and Assert
    assertEquals(
        11.963395500676427d,
        forcingFunction.apply(new CarbonConcentration3DScalar(), 10.0d).doubleValue(),
        0.0);
  }
}
