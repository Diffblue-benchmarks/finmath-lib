package net.finmath.singleswaprate.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping;
import net.finmath.singleswaprate.annuitymapping.ConstantNormalizer;
import net.finmath.singleswaprate.annuitymapping.NormalizingFunction;
import net.finmath.singleswaprate.annuitymapping.SimplifiedLinearAnnuityMapping;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NormalizingDummyProductDiffblueTest {
  /**
   * Test {@link NormalizingDummyProduct#NormalizingDummyProduct(Schedule, Schedule, String, String,
   * String, NormalizingFunction)}.
   *
   * <p>Method under test: {@link NormalizingDummyProduct#NormalizingDummyProduct(Schedule,
   * Schedule, String, String, String, NormalizingFunction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NormalizingDummyProduct.<init>(Schedule, Schedule, String, String, String, NormalizingFunction)"
  })
  public void testNewNormalizingDummyProduct() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    NormalizingDummyProduct actualNormalizingDummyProduct =
        new NormalizingDummyProduct(
            fixSchedule,
            floatSchedule,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            new ConstantNormalizer());

    // Assert
    Schedule fixSchedule2 = actualNormalizingDummyProduct.getFixSchedule();
    assertTrue(fixSchedule2 instanceof RegularSchedule);
    Schedule floatSchedule2 = actualNormalizingDummyProduct.getFloatSchedule();
    assertTrue(floatSchedule2 instanceof RegularSchedule);
    assertEquals("3", actualNormalizingDummyProduct.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualNormalizingDummyProduct.getForwardCurveName());
    assertEquals("Volatility Cube Name", actualNormalizingDummyProduct.getVolatilityCubeName());
    assertEquals(-0.15d, actualNormalizingDummyProduct.getIntegrationLowerBound(), 0.0);
    assertEquals(0.15d, actualNormalizingDummyProduct.getIntegrationUpperBound(), 0.0);
    assertEquals(500, actualNormalizingDummyProduct.getIntegrationNumberOfEvaluationPoints());
    assertSame(fixSchedule, fixSchedule2);
    assertSame(floatSchedule, floatSchedule2);
  }

  /**
   * Test {@link NormalizingDummyProduct#payoffFunction(double, AnnuityMapping,
   * VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link NormalizingDummyProduct#payoffFunction(double, AnnuityMapping,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double NormalizingDummyProduct.payoffFunction(double, AnnuityMapping, VolatilityCubeModel)"
  })
  public void testPayoffFunction() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    NormalizingDummyProduct normalizingDummyProduct =
        new NormalizingDummyProduct(
            fixSchedule,
            floatSchedule,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            new ConstantNormalizer());
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        1.0d,
        normalizingDummyProduct.payoffFunction(
            10.0d, annuityMapping, new AnalyticModelWithVolatilityCubes()),
        0.0);
  }

  /**
   * Test {@link NormalizingDummyProduct#hedgeWeight(double, AnnuityMapping, VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link NormalizingDummyProduct#hedgeWeight(double, AnnuityMapping,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double NormalizingDummyProduct.hedgeWeight(double, AnnuityMapping, VolatilityCubeModel)"
  })
  public void testHedgeWeight() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    NormalizingDummyProduct normalizingDummyProduct =
        new NormalizingDummyProduct(
            fixSchedule,
            floatSchedule,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            new ConstantNormalizer());
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        normalizingDummyProduct.hedgeWeight(
            10.0d, annuityMapping, new AnalyticModelWithVolatilityCubes()),
        0.0);
  }

  /**
   * Test {@link NormalizingDummyProduct#singularAddon(double, AnnuityMapping,
   * VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link NormalizingDummyProduct#singularAddon(double, AnnuityMapping,
   * VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double NormalizingDummyProduct.singularAddon(double, AnnuityMapping, VolatilityCubeModel)"
  })
  public void testSingularAddon() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    NormalizingDummyProduct normalizingDummyProduct =
        new NormalizingDummyProduct(
            fixSchedule,
            floatSchedule,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            new ConstantNormalizer());
    SimplifiedLinearAnnuityMapping annuityMapping =
        new SimplifiedLinearAnnuityMapping(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)), 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        normalizingDummyProduct.singularAddon(
            10.0d, annuityMapping, new AnalyticModelWithVolatilityCubes()),
        0.0);
  }

  /**
   * Test {@link NormalizingDummyProduct#buildAnnuityMapping(VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link NormalizingDummyProduct#buildAnnuityMapping(VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnnuityMapping NormalizingDummyProduct.buildAnnuityMapping(VolatilityCubeModel)"
  })
  public void testBuildAnnuityMapping() {
    // Arrange
    RegularSchedule fixSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    RegularSchedule floatSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    NormalizingDummyProduct normalizingDummyProduct =
        new NormalizingDummyProduct(
            fixSchedule,
            floatSchedule,
            "3",
            "Forward Curve Name",
            "Volatility Cube Name",
            new ConstantNormalizer());

    // Act and Assert
    assertNull(normalizingDummyProduct.buildAnnuityMapping(new AnalyticModelWithVolatilityCubes()));
  }
}
