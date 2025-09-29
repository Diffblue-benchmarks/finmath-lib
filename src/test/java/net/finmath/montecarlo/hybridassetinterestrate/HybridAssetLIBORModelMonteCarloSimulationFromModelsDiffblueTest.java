package net.finmath.montecarlo.hybridassetinterestrate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.interestrate.LIBORModelMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HybridAssetLIBORModelMonteCarloSimulationFromModelsDiffblueTest {
  /**
   * Test {@link
   * HybridAssetLIBORModelMonteCarloSimulationFromModels#HybridAssetLIBORModelMonteCarloSimulationFromModels(LIBORModelMonteCarloSimulationModel,
   * AssetModelMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * HybridAssetLIBORModelMonteCarloSimulationFromModels#HybridAssetLIBORModelMonteCarloSimulationFromModels(LIBORModelMonteCarloSimulationModel,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HybridAssetLIBORModelMonteCarloSimulationFromModels.<init>(LIBORModelMonteCarloSimulationModel, AssetModelMonteCarloSimulationModel)"
  })
  public void testNewHybridAssetLIBORModelMonteCarloSimulationFromModels() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    TenorFromArray tenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);
    LIBORMonteCarloSimulationFromLIBORModel liborSimulation =
        new LIBORMonteCarloSimulationFromLIBORModel(process);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act
    HybridAssetLIBORModelMonteCarloSimulationFromModels
        actualHybridAssetLIBORModelMonteCarloSimulationFromModels =
            new HybridAssetLIBORModelMonteCarloSimulationFromModels(
                liborSimulation, new MonteCarloAssetModel(process2));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(brownianMotion2).getTimeDiscretization();
    BrownianMotion brownianMotion3 =
        actualHybridAssetLIBORModelMonteCarloSimulationFromModels.getBrownianMotion();
    assertTrue(brownianMotion3 instanceof BrownianMotionWithControlVariate);
    MonteCarloProcess process3 =
        actualHybridAssetLIBORModelMonteCarloSimulationFromModels.getProcess();
    assertTrue(process3 instanceof EulerSchemeFromProcessModel);
    TimeDiscretization timeDiscretization =
        actualHybridAssetLIBORModelMonteCarloSimulationFromModels.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertNull(actualHybridAssetLIBORModelMonteCarloSimulationFromModels.getModel());
    assertEquals(0, actualHybridAssetLIBORModelMonteCarloSimulationFromModels.getNumberOfFactors());
    assertEquals(0, actualHybridAssetLIBORModelMonteCarloSimulationFromModels.getNumberOfPaths());
    assertEquals(1, actualHybridAssetLIBORModelMonteCarloSimulationFromModels.getNumberOfAssets());
    assertSame(stochasticDriver, brownianMotion3);
    assertSame(process, process3);
    assertSame(tenorFromArray, timeDiscretization);
  }

  /**
   * Test {@link
   * HybridAssetLIBORModelMonteCarloSimulationFromModels#HybridAssetLIBORModelMonteCarloSimulationFromModels(LIBORModelMonteCarloSimulationModel,
   * AssetModelMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link
   * HybridAssetLIBORModelMonteCarloSimulationFromModels#HybridAssetLIBORModelMonteCarloSimulationFromModels(LIBORModelMonteCarloSimulationModel,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HybridAssetLIBORModelMonteCarloSimulationFromModels.<init>(LIBORModelMonteCarloSimulationModel, AssetModelMonteCarloSimulationModel)"
  })
  public void testNewHybridAssetLIBORModelMonteCarloSimulationFromModels2() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization())
        .thenReturn(
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);
    LIBORMonteCarloSimulationFromLIBORModel liborSimulation =
        new LIBORMonteCarloSimulationFromLIBORModel(process);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new HybridAssetLIBORModelMonteCarloSimulationFromModels(
                liborSimulation, new MonteCarloAssetModel(process2)));
    verify(brownianMotion).getTimeDiscretization();
    verify(brownianMotion2).getTimeDiscretization();
  }

  /**
   * Test {@link
   * HybridAssetLIBORModelMonteCarloSimulationFromModels#HybridAssetLIBORModelMonteCarloSimulationFromModels(LIBORModelMonteCarloSimulationModel,
   * AssetModelMonteCarloSimulationModel, DiscountCurve)}.
   *
   * <p>Method under test: {@link
   * HybridAssetLIBORModelMonteCarloSimulationFromModels#HybridAssetLIBORModelMonteCarloSimulationFromModels(LIBORModelMonteCarloSimulationModel,
   * AssetModelMonteCarloSimulationModel, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HybridAssetLIBORModelMonteCarloSimulationFromModels.<init>(LIBORModelMonteCarloSimulationModel, AssetModelMonteCarloSimulationModel, DiscountCurve)"
  })
  public void testNewHybridAssetLIBORModelMonteCarloSimulationFromModels3() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    TenorFromArray tenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);
    LIBORMonteCarloSimulationFromLIBORModel liborSimulation =
        new LIBORMonteCarloSimulationFromLIBORModel(process);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);
    MonteCarloAssetModel assetSimulation = new MonteCarloAssetModel(process2);

    // Act
    HybridAssetLIBORModelMonteCarloSimulationFromModels
        actualHybridAssetLIBORModelMonteCarloSimulationFromModels =
            new HybridAssetLIBORModelMonteCarloSimulationFromModels(
                liborSimulation,
                assetSimulation,
                new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    verify(brownianMotion2).getTimeDiscretization();
    BrownianMotion brownianMotion3 =
        actualHybridAssetLIBORModelMonteCarloSimulationFromModels.getBrownianMotion();
    assertTrue(brownianMotion3 instanceof BrownianMotionWithControlVariate);
    MonteCarloProcess process3 =
        actualHybridAssetLIBORModelMonteCarloSimulationFromModels.getProcess();
    assertTrue(process3 instanceof EulerSchemeFromProcessModel);
    TimeDiscretization timeDiscretization =
        actualHybridAssetLIBORModelMonteCarloSimulationFromModels.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertNull(actualHybridAssetLIBORModelMonteCarloSimulationFromModels.getModel());
    assertEquals(0, actualHybridAssetLIBORModelMonteCarloSimulationFromModels.getNumberOfFactors());
    assertEquals(0, actualHybridAssetLIBORModelMonteCarloSimulationFromModels.getNumberOfPaths());
    assertEquals(1, actualHybridAssetLIBORModelMonteCarloSimulationFromModels.getNumberOfAssets());
    assertSame(stochasticDriver, brownianMotion3);
    assertSame(process, process3);
    assertSame(tenorFromArray, timeDiscretization);
  }

  /**
   * Test {@link
   * HybridAssetLIBORModelMonteCarloSimulationFromModels#HybridAssetLIBORModelMonteCarloSimulationFromModels(LIBORModelMonteCarloSimulationModel,
   * AssetModelMonteCarloSimulationModel, DiscountCurve)}.
   *
   * <p>Method under test: {@link
   * HybridAssetLIBORModelMonteCarloSimulationFromModels#HybridAssetLIBORModelMonteCarloSimulationFromModels(LIBORModelMonteCarloSimulationModel,
   * AssetModelMonteCarloSimulationModel, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HybridAssetLIBORModelMonteCarloSimulationFromModels.<init>(LIBORModelMonteCarloSimulationModel, AssetModelMonteCarloSimulationModel, DiscountCurve)"
  })
  public void testNewHybridAssetLIBORModelMonteCarloSimulationFromModels4() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization())
        .thenReturn(
            new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);
    LIBORMonteCarloSimulationFromLIBORModel liborSimulation =
        new LIBORMonteCarloSimulationFromLIBORModel(process);

    BrownianMotion brownianMotion2 = mock(BrownianMotion.class);
    when(brownianMotion2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver2 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    EulerSchemeFromProcessModel process2 =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver2);
    MonteCarloAssetModel assetSimulation = new MonteCarloAssetModel(process2);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new HybridAssetLIBORModelMonteCarloSimulationFromModels(
                liborSimulation,
                assetSimulation,
                new DiscountCurveFromForwardCurve("Forward Curve Name")));
    verify(brownianMotion).getTimeDiscretization();
    verify(brownianMotion2).getTimeDiscretization();
  }
}
