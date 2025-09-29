package net.finmath.montecarlo.assetderivativevaluation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianBridge;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.models.BlackScholesModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel.Scheme;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MonteCarloBlackScholesModelDiffblueTest {
  /**
   * Test {@link MonteCarloBlackScholesModel#MonteCarloBlackScholesModel(double, double, double,
   * BrownianMotion)}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel#MonteCarloBlackScholesModel(double,
   * double, double, BrownianMotion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloBlackScholesModel.<init>(double, double, double, BrownianMotion)"
  })
  public void testNewMonteCarloBlackScholesModel() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);

    // Act
    MonteCarloBlackScholesModel actualMonteCarloBlackScholesModel =
        new MonteCarloBlackScholesModel(
            10.0d, 10.0d, 10.0d, new BrownianMotionWithControlVariate(brownianMotion));

    // Assert
    assertTrue(
        actualMonteCarloBlackScholesModel.getProcess() instanceof EulerSchemeFromProcessModel);
    TimeDiscretization timeDiscretization2 =
        actualMonteCarloBlackScholesModel.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualMonteCarloBlackScholesModel.getNumberOfAssets());
    assertEquals(10, actualMonteCarloBlackScholesModel.getNumberOfPaths());
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel#MonteCarloBlackScholesModel(TimeDiscretization, int,
   * double, double, double)}.
   *
   * <ul>
   *   <li>Then Process return {@link EulerSchemeFromProcessModel}.
   * </ul>
   *
   * <p>Method under test: {@link
   * MonteCarloBlackScholesModel#MonteCarloBlackScholesModel(TimeDiscretization, int, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloBlackScholesModel.<init>(TimeDiscretization, int, double, double, double)"
  })
  public void testNewMonteCarloBlackScholesModel_thenProcessReturnEulerSchemeFromProcessModel() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    MonteCarloBlackScholesModel actualMonteCarloBlackScholesModel =
        new MonteCarloBlackScholesModel(timeDiscretization, 10, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(
        actualMonteCarloBlackScholesModel.getProcess() instanceof EulerSchemeFromProcessModel);
    TimeDiscretization timeDiscretization2 =
        actualMonteCarloBlackScholesModel.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualMonteCarloBlackScholesModel.getNumberOfAssets());
    assertEquals(10, actualMonteCarloBlackScholesModel.getNumberOfPaths());
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link MonteCarloBlackScholesModel#getAssetValue(double, int)} with {@code time}, {@code
   * assetIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel#getAssetValue(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloBlackScholesModel.getAssetValue(double, int)"})
  public void testGetAssetValueWithTimeAssetIndex_thenThrowIllegalArgumentException()
      throws CalculationException {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new MonteCarloBlackScholesModel(10.0d, 10.0d, 10.0d, brownianMotion2)
                .getAssetValue(10.0d, 1));
  }

  /**
   * Test {@link MonteCarloBlackScholesModel#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MonteCarloBlackScholesModel MonteCarloBlackScholesModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData() {
    // Arrange
    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    MonteCarloBlackScholesModel monteCarloBlackScholesModel =
        new MonteCarloBlackScholesModel(
            10.0d, 10.0d, 10.0d, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    MonteCarloBlackScholesModel actualCloneWithModifiedData =
        monteCarloBlackScholesModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    verify(timeDiscretization, atLeast(1)).getNumberOfTimes();
    verify(timeDiscretization, atLeast(1)).getTime(0);
    MonteCarloProcess process = actualCloneWithModifiedData.getProcess();
    assertTrue(process.getStochasticDriver() instanceof BrownianMotionFromMersenneRandomNumbers);
    assertTrue(process instanceof EulerSchemeFromProcessModel);
    BlackScholesModel model = actualCloneWithModifiedData.getModel();
    assertTrue(model.getRiskFreeRate() instanceof Scalar);
    assertTrue(model.getVolatility() instanceof Scalar);
    assertEquals(1, ((EulerSchemeFromProcessModel) process).getInitialState().length);
    assertSame(model, process.getModel());
  }

  /**
   * Test {@link MonteCarloBlackScholesModel#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MonteCarloBlackScholesModel MonteCarloBlackScholesModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(2.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(2.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    MonteCarloBlackScholesModel monteCarloBlackScholesModel =
        new MonteCarloBlackScholesModel(
            10.0d, 10.0d, 10.0d, new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    MonteCarloBlackScholesModel actualCloneWithModifiedData =
        monteCarloBlackScholesModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    MonteCarloProcess process = actualCloneWithModifiedData.getProcess();
    assertTrue(process instanceof EulerSchemeFromProcessModel);
    assertSame(timeDiscretization, actualCloneWithModifiedData.getTimeDiscretization());
    assertSame(timeDiscretization, process.getTimeDiscretization());
  }

  /**
   * Test {@link MonteCarloBlackScholesModel#getCloneWithModifiedSeed(int)}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AssetModelMonteCarloSimulationModel MonteCarloBlackScholesModel.getCloneWithModifiedSeed(int)"
  })
  public void testGetCloneWithModifiedSeed() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    // Act
    AssetModelMonteCarloSimulationModel actualCloneWithModifiedSeed =
        new MonteCarloBlackScholesModel(
                10.0d, 10.0d, 10.0d, new BrownianMotionWithControlVariate(brownianMotion))
            .getCloneWithModifiedSeed(42);

    // Assert
    MonteCarloProcess process =
        ((MonteCarloBlackScholesModel) actualCloneWithModifiedSeed).getProcess();
    assertTrue(process.getStochasticDriver() instanceof BrownianMotionFromMersenneRandomNumbers);
    assertTrue(actualCloneWithModifiedSeed instanceof MonteCarloBlackScholesModel);
    assertTrue(process instanceof EulerSchemeFromProcessModel);
    BlackScholesModel model =
        ((MonteCarloBlackScholesModel) actualCloneWithModifiedSeed).getModel();
    assertTrue(model.getRiskFreeRate() instanceof Scalar);
    assertTrue(model.getVolatility() instanceof Scalar);
    assertEquals(1, actualCloneWithModifiedSeed.getNumberOfAssets());
    assertEquals(1, model.getNumberOfComponents());
    assertEquals(1, model.getNumberOfFactors());
    assertEquals(1, process.getNumberOfFactors());
    assertEquals(1, process.getNumberOfComponents());
    assertEquals(1, ((EulerSchemeFromProcessModel) process).getInitialState().length);
    assertEquals(10, actualCloneWithModifiedSeed.getNumberOfPaths());
    assertEquals(10, process.getNumberOfPaths());
    assertEquals(Scheme.EULER_FUNCTIONAL, ((EulerSchemeFromProcessModel) process).getScheme());
    assertSame(model, process.getModel());
  }

  /**
   * Test {@link MonteCarloBlackScholesModel#getModel()}.
   *
   * <p>Method under test: {@link MonteCarloBlackScholesModel#getModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BlackScholesModel MonteCarloBlackScholesModel.getModel()"})
  public void testGetModel() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    // Act
    BlackScholesModel actualModel =
        new MonteCarloBlackScholesModel(
                10.0d, 10.0d, 10.0d, new BrownianMotionWithControlVariate(brownianMotion))
            .getModel();

    // Assert
    assertTrue(actualModel.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualModel.getVolatility() instanceof Scalar);
    assertEquals(1, actualModel.getNumberOfComponents());
    assertEquals(1, actualModel.getNumberOfFactors());
  }
}
