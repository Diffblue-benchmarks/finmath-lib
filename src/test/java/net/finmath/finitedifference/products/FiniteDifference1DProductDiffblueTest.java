package net.finmath.finitedifference.products;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.modelling.Model;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FiniteDifference1DProductDiffblueTest {
  /**
   * Test {@link FiniteDifference1DProduct#getValue(double, Model)} with {@code double}, {@code
   * Model}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link FiniteDifference1DProduct#getValue(double, Model)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object FiniteDifference1DProduct.getValue(double, Model)"})
  public void testGetValueWithDoubleModel_thenThrowIllegalArgumentException() {
    // Arrange
    FDMEuropeanCallOption fdmEuropeanCallOption = new FDMEuropeanCallOption(10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> fdmEuropeanCallOption.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }
}
