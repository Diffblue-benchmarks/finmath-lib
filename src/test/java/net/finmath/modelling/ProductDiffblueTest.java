package net.finmath.modelling;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.finitedifference.products.FDMEuropeanCallOption;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ProductDiffblueTest {
  /**
   * Test {@link Product#getValues(double, Model)}.
   *
   * <p>Method under test: {@link Product#getValues(double, Model)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map Product.getValues(double, Model)"})
  public void testGetValues() {
    // Arrange
    FDMEuropeanCallOption fdmEuropeanCallOption = new FDMEuropeanCallOption(10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> fdmEuropeanCallOption.getValues(10.0d, new AnalyticModelFromCurvesAndVols()));
  }
}
