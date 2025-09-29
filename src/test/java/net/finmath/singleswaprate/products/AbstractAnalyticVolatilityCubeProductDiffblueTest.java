package net.finmath.singleswaprate.products;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping.AnnuityMappingType;
import net.finmath.time.RegularSchedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractAnalyticVolatilityCubeProductDiffblueTest {
  /**
   * Test {@link AbstractAnalyticVolatilityCubeProduct#getValue(double, AnalyticModel)} with {@code
   * double}, {@code AnalyticModel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractAnalyticVolatilityCubeProduct#getValue(double,
   * AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractAnalyticVolatilityCubeProduct.getValue(double, AnalyticModel)"
  })
  public void testGetValueWithDoubleAnalyticModel_thenThrowIllegalArgumentException() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> annuityDummyProduct.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }
}
