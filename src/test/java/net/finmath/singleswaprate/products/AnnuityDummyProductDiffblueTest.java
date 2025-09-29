package net.finmath.singleswaprate.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping.AnnuityMappingType;
import net.finmath.singleswaprate.annuitymapping.SimplifiedLinearAnnuityMapping;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AnnuityDummyProductDiffblueTest {
  /**
   * Test {@link AnnuityDummyProduct#AnnuityDummyProduct(Schedule, Schedule, String, String, String,
   * AnnuityMappingType)}.
   *
   * <p>Method under test: {@link AnnuityDummyProduct#AnnuityDummyProduct(Schedule, Schedule,
   * String, String, String, AnnuityMapping.AnnuityMappingType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnuityDummyProduct.<init>(Schedule, Schedule, String, String, String, AnnuityMapping.AnnuityMappingType)"
  })
  public void testNewAnnuityDummyProduct() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    AnnuityDummyProduct actualAnnuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            floatSchedule,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            AnnuityMappingType.BASICPITERBARG);

    // Assert
    Schedule fixSchedule2 = actualAnnuityDummyProduct.getFixSchedule();
    assertTrue(fixSchedule2 instanceof RegularSchedule);
    Schedule floatSchedule2 = actualAnnuityDummyProduct.getFloatSchedule();
    assertTrue(floatSchedule2 instanceof RegularSchedule);
    assertEquals("3", actualAnnuityDummyProduct.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualAnnuityDummyProduct.getForwardCurveName());
    assertEquals("Volatility Cube Name", actualAnnuityDummyProduct.getVolatilityCubeName());
    assertEquals(-0.15d, actualAnnuityDummyProduct.getIntegrationLowerBound(), 0.0);
    assertEquals(0.15d, actualAnnuityDummyProduct.getIntegrationUpperBound(), 0.0);
    assertEquals(500, actualAnnuityDummyProduct.getIntegrationNumberOfEvaluationPoints());
    assertSame(fixSchedule, fixSchedule2);
    assertSame(floatSchedule, floatSchedule2);
  }

  /**
   * Test {@link AnnuityDummyProduct#AnnuityDummyProduct(Schedule, Schedule, String, String, String,
   * AnnuityMapping)}.
   *
   * <p>Method under test: {@link AnnuityDummyProduct#AnnuityDummyProduct(Schedule, Schedule,
   * String, String, String, AnnuityMapping)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnnuityDummyProduct.<init>(Schedule, Schedule, String, String, String, AnnuityMapping)"
  })
  public void testNewAnnuityDummyProduct2() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act
    AnnuityDummyProduct actualAnnuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            floatSchedule,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            annuityMapping);

    // Assert
    Schedule fixSchedule2 = actualAnnuityDummyProduct.getFixSchedule();
    assertTrue(fixSchedule2 instanceof RegularSchedule);
    Schedule floatSchedule2 = actualAnnuityDummyProduct.getFloatSchedule();
    assertTrue(floatSchedule2 instanceof RegularSchedule);
    assertEquals("3", actualAnnuityDummyProduct.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualAnnuityDummyProduct.getForwardCurveName());
    assertEquals("Volatility Cube Name", actualAnnuityDummyProduct.getVolatilityCubeName());
    assertEquals(-0.15d, actualAnnuityDummyProduct.getIntegrationLowerBound(), 0.0);
    assertEquals(0.15d, actualAnnuityDummyProduct.getIntegrationUpperBound(), 0.0);
    assertEquals(500, actualAnnuityDummyProduct.getIntegrationNumberOfEvaluationPoints());
    assertSame(fixSchedule, fixSchedule2);
    assertSame(floatSchedule, floatSchedule2);
  }

  /**
   * Test {@link AnnuityDummyProduct#payoffFunction(double, AnnuityMapping, VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link AnnuityDummyProduct#payoffFunction(double, AnnuityMapping,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnnuityDummyProduct.payoffFunction(double, AnnuityMapping, VolatilityCubeModel)"
  })
  public void testPayoffFunction_thenReturnTen() {
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
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        10.0d,
        annuityDummyProduct.payoffFunction(
            10.0d, annuityMapping, new AnalyticModelWithVolatilityCubes()),
        0.0);
  }

  /**
   * Test {@link AnnuityDummyProduct#hedgeWeight(double, AnnuityMapping, VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AnnuityDummyProduct#hedgeWeight(double, AnnuityMapping,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnnuityDummyProduct.hedgeWeight(double, AnnuityMapping, VolatilityCubeModel)"
  })
  public void testHedgeWeight_thenReturnZero() {
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
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        annuityDummyProduct.hedgeWeight(
            10.0d, annuityMapping, new AnalyticModelWithVolatilityCubes()),
        0.0);
  }

  /**
   * Test {@link AnnuityDummyProduct#singularAddon(double, AnnuityMapping, VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link AnnuityDummyProduct#singularAddon(double, AnnuityMapping,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AnnuityDummyProduct.singularAddon(double, AnnuityMapping, VolatilityCubeModel)"
  })
  public void testSingularAddon() {
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
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        annuityDummyProduct.singularAddon(
            10.0d, annuityMapping, new AnalyticModelWithVolatilityCubes()),
        0.0);
  }

  /**
   * Test {@link AnnuityDummyProduct#buildAnnuityMapping(VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link AnnuityDummyProduct#buildAnnuityMapping(VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnnuityMapping AnnuityDummyProduct.buildAnnuityMapping(VolatilityCubeModel)"})
  public void testBuildAnnuityMapping() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(Double.NaN, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(Double.NaN, 10, 0.5d));
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(Double.NaN, 10, 0.5d)),
            Double.NaN,
            Double.NaN,
            Double.NaN);

    AnnuityDummyProduct annuityDummyProduct =
        new AnnuityDummyProduct(
            fixSchedule,
            floatSchedule,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            annuityMapping);

    // Act and Assert
    assertSame(
        annuityMapping,
        annuityDummyProduct.buildAnnuityMapping(new AnalyticModelWithVolatilityCubes()));
  }
}
