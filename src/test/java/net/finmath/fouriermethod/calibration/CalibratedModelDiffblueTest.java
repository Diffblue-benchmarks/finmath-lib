package net.finmath.fouriermethod.calibration;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import net.finmath.fouriermethod.calibration.CalibratedModel.OptimizationResult;
import net.finmath.fouriermethod.calibration.models.CalibratableHestonModel;
import net.finmath.fouriermethod.calibration.models.CalibratableMertonModel;
import net.finmath.fouriermethod.calibration.models.CalibratableProcess;
import net.finmath.fouriermethod.models.CharacteristicFunctionModel;
import net.finmath.fouriermethod.models.MertonModel;
import net.finmath.fouriermethod.products.smile.EuropeanOptionSmileByCarrMadan;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.volatilities.OptionSurfaceData;
import net.finmath.marketdata.model.volatilities.VolatilitySurface;
import net.finmath.marketdata.model.volatilities.VolatilitySurface.QuotingConvention;
import net.finmath.modelling.ModelDescriptor;
import net.finmath.modelling.descriptor.HestonModelDescriptor;
import net.finmath.modelling.descriptor.MertonModelDescriptor;
import net.finmath.optimizer.Optimizer;
import net.finmath.optimizer.Optimizer.ObjectiveFunction;
import net.finmath.optimizer.OptimizerFactory;
import net.finmath.optimizer.OptimizerFactoryCMAES;
import net.finmath.optimizer.SolverException;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class CalibratedModelDiffblueTest {
  /**
   * Test {@link CalibratedModel#getCalibration()}.
   *
   * <ul>
   *   <li>Then return Model ModelDescriptor JumpIntensity doubleValue is {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link CalibratedModel#getCalibration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OptimizationResult CalibratedModel.getCalibration()"})
  public void testGetCalibration_thenReturnModelModelDescriptorJumpIntensityDoubleValueIs05()
      throws SolverException {
    // Arrange
    OptionSurfaceData surface = mock(OptionSurfaceData.class);
    when(surface.getQuotingConvention()).thenReturn(QuotingConvention.VOLATILITYLOGNORMAL);
    when(surface.getMaturities()).thenReturn(new double[] {});

    Optimizer optimizer = mock(Optimizer.class);
    when(optimizer.getRootMeanSquaredError()).thenReturn(10.0d);
    when(optimizer.getIterations()).thenReturn(1);
    when(optimizer.getBestFitParameters()).thenReturn(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    doNothing().when(optimizer).run();

    OptimizerFactory optimizerFactory = mock(OptimizerFactory.class);
    when(optimizerFactory.getOptimizer(
            Mockito.<ObjectiveFunction>any(),
            Mockito.<double[]>any(),
            Mockito.<double[]>any(),
            Mockito.<double[]>any(),
            Mockito.<double[]>any(),
            Mockito.<double[]>any()))
        .thenReturn(optimizer);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    CalibratableMertonModel model =
        new CalibratableMertonModel(
            new MertonModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                10.0d,
                10.0d,
                10.0d));
    EuropeanOptionSmileByCarrMadan pricer =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    CalibratedModel calibratedModel =
        new CalibratedModel(
            surface,
            model,
            optimizerFactory,
            pricer,
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act
    OptimizationResult actualCalibration = calibratedModel.getCalibration();

    // Assert
    verify(surface, atLeast(1)).getMaturities();
    verify(surface).getQuotingConvention();
    verify(optimizer, atLeast(1)).getBestFitParameters();
    verify(optimizer).getIterations();
    verify(optimizer).getRootMeanSquaredError();
    verify(optimizer).run();
    verify(optimizerFactory)
        .getOptimizer(
            isA(ObjectiveFunction.class),
            isA(double[].class),
            isA(double[].class),
            isA(double[].class),
            isA(double[].class),
            isA(double[].class));
    CalibratableProcess model2 = actualCalibration.getModel();
    assertTrue(model2 instanceof CalibratableMertonModel);
    CharacteristicFunctionModel characteristicFunctionModel =
        model2.getCharacteristicFunctionModel();
    DiscountCurve discountCurveForDiscountRate =
        ((MertonModel) characteristicFunctionModel).getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = model2.getModelDescriptor();
    assertTrue(modelDescriptor instanceof MertonModelDescriptor);
    assertEquals(
        0.5d, ((MertonModelDescriptor) modelDescriptor).getJumpIntensity().doubleValue(), 0.0);
    assertEquals(
        0.5d, ((MertonModelDescriptor) modelDescriptor).getJumpSizeStdDev().doubleValue(), 0.0);
    assertEquals(0.5d, ((MertonModel) characteristicFunctionModel).getJumpIntensity(), 0.0);
    assertEquals(0.5d, ((MertonModel) characteristicFunctionModel).getJumpSizeStdDev(), 0.0);
    assertEquals(10.0d, actualCalibration.getRootMeanSquaredError(), 0.0);
    assertSame(
        discountCurveForDiscountRate,
        ((MertonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        model2.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d}, actualCalibration.getBestFitParameters(), 0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        model2.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratedModel#getCalibration()}.
   *
   * <ul>
   *   <li>Then return RootMeanSquaredError is zero.
   * </ul>
   *
   * <p>Method under test: {@link CalibratedModel#getCalibration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OptimizationResult CalibratedModel.getCalibration()"})
  public void testGetCalibration_thenReturnRootMeanSquaredErrorIsZero() throws SolverException {
    // Arrange
    OptionSurfaceData surface = mock(OptionSurfaceData.class);
    when(surface.getQuotingConvention()).thenReturn(QuotingConvention.VOLATILITYLOGNORMAL);
    when(surface.getMaturities()).thenReturn(new double[] {});
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    CalibratableMertonModel model =
        new CalibratableMertonModel(
            new MertonModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                10.0d,
                10.0d,
                10.0d));
    OptimizerFactoryCMAES optimizerFactory = new OptimizerFactoryCMAES(10.0d, 3);
    EuropeanOptionSmileByCarrMadan pricer =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    CalibratedModel calibratedModel =
        new CalibratedModel(
            surface,
            model,
            optimizerFactory,
            pricer,
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d});

    // Act
    OptimizationResult actualCalibration = calibratedModel.getCalibration();

    // Assert
    verify(surface, atLeast(1)).getMaturities();
    verify(surface, atLeast(1)).getQuotingConvention();
    CalibratableProcess model2 = actualCalibration.getModel();
    assertTrue(model2 instanceof CalibratableMertonModel);
    CharacteristicFunctionModel characteristicFunctionModel =
        model2.getCharacteristicFunctionModel();
    DiscountCurve discountCurveForDiscountRate =
        ((MertonModel) characteristicFunctionModel).getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = model2.getModelDescriptor();
    assertTrue(modelDescriptor instanceof MertonModelDescriptor);
    assertEquals(0.0d, actualCalibration.getRootMeanSquaredError(), 0.0);
    assertEquals(
        2.0d, ((MertonModelDescriptor) modelDescriptor).getJumpIntensity().doubleValue(), 0.0);
    assertEquals(
        2.0d, ((MertonModelDescriptor) modelDescriptor).getJumpSizeStdDev().doubleValue(), 0.0);
    assertEquals(2.0d, ((MertonModel) characteristicFunctionModel).getJumpIntensity(), 0.0);
    assertEquals(2.0d, ((MertonModel) characteristicFunctionModel).getJumpSizeStdDev(), 0.0);
    assertSame(
        discountCurveForDiscountRate,
        ((MertonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        model2.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualCalibration.getBestFitParameters(), 0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        model2.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test OptimizationResult getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link OptimizationResult#getBestFitParameters()}
   *   <li>{@link OptimizationResult#getCalibrationOutput()}
   *   <li>{@link OptimizationResult#getIterations()}
   *   <li>{@link OptimizationResult#getModel()}
   *   <li>{@link OptimizationResult#getRootMeanSquaredError()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] OptimizationResult.getBestFitParameters()",
    "ArrayList OptimizationResult.getCalibrationOutput()",
    "int OptimizationResult.getIterations()",
    "CalibratableProcess OptimizationResult.getModel()",
    "double OptimizationResult.getRootMeanSquaredError()"
  })
  public void testOptimizationResultGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    CalibratableHestonModel model =
        new CalibratableHestonModel(
            new HestonModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d));
    ArrayList<String> calibrationOutput = new ArrayList<>();

    OptimizationResult optimizationResult =
        new OptimizationResult(
            model, new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 1, 10.0d, calibrationOutput);

    // Act
    double[] actualBestFitParameters = optimizationResult.getBestFitParameters();
    ArrayList<String> actualCalibrationOutput = optimizationResult.getCalibrationOutput();
    int actualIterations = optimizationResult.getIterations();
    CalibratableProcess actualModel = optimizationResult.getModel();

    // Assert
    assertEquals(1, actualIterations);
    assertEquals(10.0d, optimizationResult.getRootMeanSquaredError(), 0.0);
    assertSame(calibrationOutput, actualCalibrationOutput);
    assertSame(model, actualModel);
    assertArrayEquals(new double[] {10.0d, 0.5d, 10.0d, 0.5d}, actualBestFitParameters, 0.0);
  }

  /**
   * Test OptimizationResult {@link OptimizationResult#OptimizationResult(CalibratableProcess,
   * double[], int, double, ArrayList)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizationResult#OptimizationResult(CalibratableProcess,
   * double[], int, double, ArrayList)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationResult.<init>(CalibratableProcess, double[], int, double, ArrayList)"
  })
  public void testOptimizationResultNewOptimizationResult_given42_whenArrayListAdd42() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    CalibratableHestonModel model =
        new CalibratableHestonModel(
            new HestonModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d));

    ArrayList<String> calibrationOutput = new ArrayList<>();
    calibrationOutput.add("42");
    calibrationOutput.add("foo");

    // Act
    OptimizationResult actualOptimizationResult =
        new OptimizationResult(
            model, new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 1, 10.0d, calibrationOutput);

    // Assert
    assertEquals(1, actualOptimizationResult.getIterations());
    assertEquals(10.0d, actualOptimizationResult.getRootMeanSquaredError(), 0.0);
    assertSame(calibrationOutput, actualOptimizationResult.getCalibrationOutput());
    assertSame(model, actualOptimizationResult.getModel());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualOptimizationResult.getBestFitParameters(),
        0.0);
  }

  /**
   * Test OptimizationResult {@link OptimizationResult#OptimizationResult(CalibratableProcess,
   * double[], int, double, ArrayList)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizationResult#OptimizationResult(CalibratableProcess,
   * double[], int, double, ArrayList)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationResult.<init>(CalibratableProcess, double[], int, double, ArrayList)"
  })
  public void testOptimizationResultNewOptimizationResult_givenFoo_whenArrayListAddFoo() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    CalibratableHestonModel model =
        new CalibratableHestonModel(
            new HestonModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d));

    ArrayList<String> calibrationOutput = new ArrayList<>();
    calibrationOutput.add("foo");

    // Act
    OptimizationResult actualOptimizationResult =
        new OptimizationResult(
            model, new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 1, 10.0d, calibrationOutput);

    // Assert
    assertEquals(1, actualOptimizationResult.getIterations());
    assertEquals(10.0d, actualOptimizationResult.getRootMeanSquaredError(), 0.0);
    assertSame(calibrationOutput, actualOptimizationResult.getCalibrationOutput());
    assertSame(model, actualOptimizationResult.getModel());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualOptimizationResult.getBestFitParameters(),
        0.0);
  }

  /**
   * Test OptimizationResult {@link OptimizationResult#OptimizationResult(CalibratableProcess,
   * double[], int, double, ArrayList)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link OptimizationResult#OptimizationResult(CalibratableProcess,
   * double[], int, double, ArrayList)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptimizationResult.<init>(CalibratableProcess, double[], int, double, ArrayList)"
  })
  public void testOptimizationResultNewOptimizationResult_whenArrayList() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    CalibratableHestonModel model =
        new CalibratableHestonModel(
            new HestonModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d));
    ArrayList<String> calibrationOutput = new ArrayList<>();

    // Act
    OptimizationResult actualOptimizationResult =
        new OptimizationResult(
            model, new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 1, 10.0d, calibrationOutput);

    // Assert
    assertEquals(1, actualOptimizationResult.getIterations());
    assertEquals(10.0d, actualOptimizationResult.getRootMeanSquaredError(), 0.0);
    assertSame(calibrationOutput, actualOptimizationResult.getCalibrationOutput());
    assertSame(model, actualOptimizationResult.getModel());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualOptimizationResult.getBestFitParameters(),
        0.0);
  }
}
