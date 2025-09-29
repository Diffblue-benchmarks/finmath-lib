package net.finmath.montecarlo.assetderivativevaluation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianBridge;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.IndependentIncrements;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BlackScholesModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BlackScholesModelWithStockNumeraire;
import net.finmath.montecarlo.assetderivativevaluation.models.InhomogeneousDisplacedLognomalModel;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.model.ProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel.Scheme;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class MonteCarloAssetModelDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MonteCarloAssetModel#MonteCarloAssetModel(ProcessModel, MonteCarloProcess)}
   *   <li>{@link MonteCarloAssetModel#toString()}
   *   <li>{@link MonteCarloAssetModel#getModel()}
   *   <li>{@link MonteCarloAssetModel#getNumberOfAssets()}
   *   <li>{@link MonteCarloAssetModel#getProcess()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloAssetModel.<init>(ProcessModel, MonteCarloProcess)",
    "ProcessModel MonteCarloAssetModel.getModel()",
    "int MonteCarloAssetModel.getNumberOfAssets()",
    "MonteCarloProcess MonteCarloAssetModel.getProcess()",
    "java.lang.String MonteCarloAssetModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BachelierModel model2 = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model2, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    MonteCarloAssetModel actualMonteCarloAssetModel = new MonteCarloAssetModel(model, process);
    actualMonteCarloAssetModel.toString();
    ProcessModel actualModel = actualMonteCarloAssetModel.getModel();
    int actualNumberOfAssets = actualMonteCarloAssetModel.getNumberOfAssets();
    MonteCarloProcess actualProcess = actualMonteCarloAssetModel.getProcess();

    // Assert
    assertTrue(actualProcess instanceof EulerSchemeFromProcessModel);
    assertEquals(1, actualNumberOfAssets);
    assertSame(model, actualModel);
    assertSame(process, actualProcess);
  }

  /**
   * Test {@link MonteCarloAssetModel#MonteCarloAssetModel(ProcessModel, IndependentIncrements)}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#MonteCarloAssetModel(ProcessModel,
   * IndependentIncrements)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MonteCarloAssetModel.<init>(ProcessModel, IndependentIncrements)"})
  public void testNewMonteCarloAssetModel() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);

    // Act
    MonteCarloAssetModel actualMonteCarloAssetModel =
        new MonteCarloAssetModel(model, new BrownianMotionWithControlVariate(brownianMotion));

    // Assert
    ProcessModel model2 = actualMonteCarloAssetModel.getModel();
    assertTrue(model2 instanceof BachelierModel);
    assertTrue(actualMonteCarloAssetModel.getProcess() instanceof EulerSchemeFromProcessModel);
    TimeDiscretization timeDiscretization2 = actualMonteCarloAssetModel.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualMonteCarloAssetModel.getNumberOfAssets());
    assertEquals(10, actualMonteCarloAssetModel.getNumberOfPaths());
    assertSame(model, model2);
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link MonteCarloAssetModel#MonteCarloAssetModel(MonteCarloProcess)}.
   *
   * <ul>
   *   <li>Then return NumberOfAssets is one.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#MonteCarloAssetModel(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MonteCarloAssetModel.<init>(MonteCarloProcess)"})
  public void testNewMonteCarloAssetModel_thenReturnNumberOfAssetsIsOne() {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    MonteCarloAssetModel actualMonteCarloAssetModel = new MonteCarloAssetModel(process);

    // Assert
    assertEquals(1, actualMonteCarloAssetModel.getNumberOfAssets());
    assertEquals(10, actualMonteCarloAssetModel.getNumberOfPaths());
    assertSame(model, actualMonteCarloAssetModel.getModel());
    assertSame(process, actualMonteCarloAssetModel.getProcess());
  }

  /**
   * Test {@link MonteCarloAssetModel#getAssetValue(double, int)} with {@code time}, {@code
   * assetIndex}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getAssetValue(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getAssetValue(double, int)"})
  public void testGetAssetValueWithTimeAssetIndex() throws CalculationException {
    // Arrange
    BrownianBridge brownianMotion = mock(BrownianBridge.class);
    when(brownianMotion.getNumberOfPaths()).thenThrow(new IllegalArgumentException());
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new MonteCarloAssetModel(process).getAssetValue(10.0d, 1));
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getTimeDiscretization();
  }

  /**
   * Test {@link MonteCarloAssetModel#getAssetValue(double, int)} with {@code time}, {@code
   * assetIndex}.
   *
   * <ul>
   *   <li>Then calls {@link BrownianBridge#getNumberOfFactors()}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getAssetValue(double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getAssetValue(double, int)"})
  public void testGetAssetValueWithTimeAssetIndex_thenCallsGetNumberOfFactors()
      throws CalculationException {
    // Arrange
    TenorFromArray tenorFromArray = mock(TenorFromArray.class);
    when(tenorFromArray.getTime(anyInt())).thenThrow(new IllegalArgumentException());
    when(tenorFromArray.getNumberOfTimeSteps()).thenReturn(10);
    when(tenorFromArray.getNumberOfTimes()).thenReturn(10);
    when(tenorFromArray.getTimeIndex(anyDouble())).thenReturn(1);

    BrownianBridge brownianMotion = mock(BrownianBridge.class);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new MonteCarloAssetModel(process).getAssetValue(10.0d, 1));
    verify(brownianMotion).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion).getTimeDiscretization();
    verify(tenorFromArray, atLeast(1)).getNumberOfTimeSteps();
    verify(tenorFromArray).getNumberOfTimes();
    verify(tenorFromArray).getTime(0);
    verify(tenorFromArray).getTimeIndex(10.0d);
  }

  /**
   * Test {@link MonteCarloAssetModel#getAssetValue(int, int)} with {@code timeIndex}, {@code
   * assetIndex}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getAssetValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getAssetValue(int, int)"})
  public void testGetAssetValueWithTimeIndexAssetIndex() throws CalculationException {
    // Arrange
    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getModel()).thenReturn(new BachelierModel(10.0d, 10.0d, 10.0d));
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(process.getProcessValue(anyInt(), anyInt())).thenReturn(randomVariableFromDoubleArray);

    // Act
    RandomVariable actualAssetValue = new MonteCarloAssetModel(process).getAssetValue(1, 1);

    // Assert
    verify(process).getProcessValue(1, 1);
    verify(process).getModel();
    assertSame(randomVariableFromDoubleArray, actualAssetValue);
  }

  /**
   * Test {@link MonteCarloAssetModel#getAssetValue(int, int)} with {@code timeIndex}, {@code
   * assetIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getAssetValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getAssetValue(int, int)"})
  public void testGetAssetValueWithTimeIndexAssetIndex_thenThrowIllegalArgumentException()
      throws CalculationException {
    // Arrange
    TenorFromArray timeDiscretization = mock(TenorFromArray.class);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new IllegalArgumentException());
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    when(timeDiscretization.getNumberOfTimes()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            new BachelierModel(1.0d, 1.0d, 1.0d), stochasticDriver, Scheme.EULER);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new MonteCarloAssetModel(process).getAssetValue(1, 1));
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization).getNumberOfTimes();
    verify(timeDiscretization).getTime(0);
  }

  /**
   * Test {@link MonteCarloAssetModel#getNumeraire(double)} with {@code time}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getNumeraire(double)"})
  public void testGetNumeraireWithTime() throws CalculationException {
    // Arrange
    BlackScholesModelWithStockNumeraire model =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge stochasticDriver = new BrownianBridge(generator, start, end);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualNumeraire = new MonteCarloAssetModel(process).getNumeraire(10.0d);

    // Assert
    assertTrue(actualNumeraire instanceof Scalar);
    assertTrue(actualNumeraire.abs() instanceof Scalar);
    assertTrue(actualNumeraire.cos() instanceof Scalar);
    assertTrue(actualNumeraire.exp() instanceof Scalar);
    assertTrue(actualNumeraire.expm1() instanceof Scalar);
    assertTrue(actualNumeraire.invert() instanceof Scalar);
    assertTrue(actualNumeraire.isNaN() instanceof Scalar);
    assertTrue(actualNumeraire.sin() instanceof Scalar);
    assertTrue(actualNumeraire.sqrt() instanceof Scalar);
    assertTrue(actualNumeraire.squared() instanceof Scalar);
    assertTrue(actualNumeraire.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloAssetModel#getNumeraire(double)} with {@code time}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getNumeraire(double)"})
  public void testGetNumeraireWithTime2() throws CalculationException {
    // Arrange
    BlackScholesModelWithStockNumeraire model =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {RandomVariableDifferentiableAAD.of(1.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge stochasticDriver = new BrownianBridge(generator, start, end);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualNumeraire = new MonteCarloAssetModel(process).getNumeraire(10.0d);

    // Assert
    assertTrue(actualNumeraire instanceof Scalar);
    assertTrue(actualNumeraire.abs() instanceof Scalar);
    assertTrue(actualNumeraire.cos() instanceof Scalar);
    assertTrue(actualNumeraire.exp() instanceof Scalar);
    assertTrue(actualNumeraire.expm1() instanceof Scalar);
    assertTrue(actualNumeraire.invert() instanceof Scalar);
    assertTrue(actualNumeraire.isNaN() instanceof Scalar);
    assertTrue(actualNumeraire.sin() instanceof Scalar);
    assertTrue(actualNumeraire.sqrt() instanceof Scalar);
    assertTrue(actualNumeraire.squared() instanceof Scalar);
    assertTrue(actualNumeraire.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloAssetModel#getNumeraire(double)} with {@code time}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getNumeraire(double)"})
  public void testGetNumeraireWithTime3() throws CalculationException {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    randomVariableFromDoubleArray.addSumProduct(factor1, new ArrayList<>());
    ArrayList<RandomVariable> factor12 = new ArrayList<>();
    randomVariableFromDoubleArray.addSumProduct(factor12, new ArrayList<>());
    RandomVariable[] end = new RandomVariable[] {randomVariableFromDoubleArray};
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge stochasticDriver = new BrownianBridge(generator, start, end);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualNumeraire = new MonteCarloAssetModel(process).getNumeraire(10.0d);

    // Assert
    assertTrue(actualNumeraire instanceof Scalar);
    assertTrue(actualNumeraire.abs() instanceof Scalar);
    assertTrue(actualNumeraire.cos() instanceof Scalar);
    assertTrue(actualNumeraire.exp() instanceof Scalar);
    assertTrue(actualNumeraire.expm1() instanceof Scalar);
    assertTrue(actualNumeraire.invert() instanceof Scalar);
    assertTrue(actualNumeraire.isNaN() instanceof Scalar);
    assertTrue(actualNumeraire.sin() instanceof Scalar);
    assertTrue(actualNumeraire.sqrt() instanceof Scalar);
    assertTrue(actualNumeraire.squared() instanceof Scalar);
    assertTrue(actualNumeraire.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloAssetModel#getNumeraire(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return Average is {@code 1.0686474581524436E14}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getNumeraire(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getNumeraire(int)"})
  public void testGetNumeraireWithTimeIndex_thenReturnAverageIs10686474581524436e14()
      throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualNumeraire = new MonteCarloAssetModel(process).getNumeraire(1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(0.0d, actualNumeraire.getVariance(), 0.0);
    assertEquals(1, actualNumeraire.size());
    assertEquals(1.0686474581524436E14d, actualNumeraire.getAverage(), 0.0);
    assertEquals(1.0686474581524436E14d, actualNumeraire.getMax(), 0.0);
    assertEquals(1.0686474581524436E14d, actualNumeraire.getMin(), 0.0);
    assertTrue(actualNumeraire.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualNumeraire.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {1.0686474581524436E14d}, actualNumeraire.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloAssetModel#getNumeraire(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getNumeraire(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getNumeraire(int)"})
  public void testGetNumeraireWithTimeIndex_thenReturnScalar() throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualNumeraire = new MonteCarloAssetModel(process).getNumeraire(1);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof Scalar);
    assertTrue(actualNumeraire.abs() instanceof Scalar);
    assertTrue(actualNumeraire.cos() instanceof Scalar);
    assertTrue(actualNumeraire.exp() instanceof Scalar);
    assertTrue(actualNumeraire.expm1() instanceof Scalar);
    assertTrue(actualNumeraire.invert() instanceof Scalar);
    assertTrue(actualNumeraire.isNaN() instanceof Scalar);
    assertTrue(actualNumeraire.sin() instanceof Scalar);
    assertTrue(actualNumeraire.sqrt() instanceof Scalar);
    assertTrue(actualNumeraire.squared() instanceof Scalar);
    assertTrue(actualNumeraire.variance() instanceof Scalar);
    assertNull(actualNumeraire.getRealizations());
    assertNull(actualNumeraire.getOperator());
    assertNull(actualNumeraire.getRealizationsStream());
    assertEquals(0, actualNumeraire.getTypePriority());
    assertEquals(0.0d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(0.0d, actualNumeraire.getVariance(), 0.0);
    assertEquals(1, actualNumeraire.size());
    assertEquals(3.989519570547216E45d, actualNumeraire.getAverage(), 0.0);
    assertEquals(3.989519570547216E45d, actualNumeraire.getMax(), 0.0);
    assertEquals(3.989519570547216E45d, actualNumeraire.getMin(), 0.0);
    assertTrue(actualNumeraire.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualNumeraire.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloAssetModel#getNumeraire(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return StandardError is {@code 1.2405156997631155E14}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getNumeraire(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getNumeraire(int)"})
  public void testGetNumeraireWithTimeIndex_thenReturnStandardErrorIs12405156997631155e14()
      throws CalculationException {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(1.0d);
    randomVariableFromFloatArray.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)});
    RandomVariable[] end = new RandomVariable[] {randomVariableFromFloatArray};
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge stochasticDriver = new BrownianBridge(generator, start, end);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualNumeraire = new MonteCarloAssetModel(process).getNumeraire(1);

    // Assert
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertEquals(1.2405156997631155E14d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(1.308019515987788E15d, actualNumeraire.getMax(), 0.0);
    assertEquals(1.3116436478823097E14d, actualNumeraire.getAverage(), 0.0);
    assertEquals(1.5388792013587724E29d, actualNumeraire.getVariance(), 0.0);
    assertEquals(1.7098657792875248E29d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(3.922855084449045E14d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(7087.678989200853d, actualNumeraire.getMin(), 0.0);
    assertArrayEquals(
        new double[] {
          1.389216158779279E9d,
          3.525626742759238E10d,
          1.7775178323886016E12d,
          6.831861312778925E8d,
          1.2716241633801511E12d,
          5.244424837144963E11d,
          7087.678989200853d,
          1.3185913825227728E10d,
          3.2824407839667402E7d,
          1.308019515987788E15d
        },
        actualNumeraire.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloAssetModel#getNumeraire(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then return StandardError is {@code 1.00520028286555034E18}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getNumeraire(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getNumeraire(int)"})
  public void testGetNumeraireWithTimeIndex_thenReturnStandardErrorIs100520028286555034e18()
      throws CalculationException {
    // Arrange
    BlackScholesModelWithStockNumeraire model =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge stochasticDriver = new BrownianBridge(generator, start, end);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualNumeraire = new MonteCarloAssetModel(process).getNumeraire(1);

    // Assert
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertEquals(1.00520028286555034E18d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(1.0104276086729827E37d, actualNumeraire.getVariance(), 0.0);
    assertEquals(1.0598991916955654E19d, actualNumeraire.getMax(), 0.0);
    assertEquals(1.06283585618615667E18d, actualNumeraire.getAverage(), 0.0);
    assertEquals(1.1226973429699807E37d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(3.1787223985006659E18d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(5.743205770120779E7d, actualNumeraire.getMin(), 0.0);
    assertArrayEquals(
        new double[] {
          1.1256935128132428E13d,
          2.856844939388233E14d,
          1.4403376178606764E16d,
          5.535914559900415E12d,
          1.0304077320202232E16d,
          4.249601460724665E15d,
          5.743205770120779E7d,
          1.0684656638759761E14d,
          2.659789315977893E11d,
          1.0598991916955654E19d
        },
        actualNumeraire.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloAssetModel#getNumeraire(int)} with {@code timeIndex}.
   *
   * <ul>
   *   <li>Then Values return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getNumeraire(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getNumeraire(int)"})
  public void testGetNumeraireWithTimeIndex_thenValuesReturnRandomVariableFromDoubleArray()
      throws CalculationException {
    // Arrange
    BlackScholesModelWithStockNumeraire model =
        new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {RandomVariableDifferentiableAAD.of(1.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge stochasticDriver = new BrownianBridge(generator, start, end);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualNumeraire = new MonteCarloAssetModel(process).getNumeraire(1);

    // Assert
    assertTrue(actualNumeraire.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualNumeraire).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualNumeraire).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualNumeraire).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualNumeraire).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualNumeraire)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualNumeraire).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualNumeraire).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualNumeraire.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualNumeraire.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualNumeraire.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualNumeraire).getGradient().size());
    assertEquals(10, actualNumeraire.size());
    assertEquals(10.5d, actualNumeraire.getFiltrationTime(), 0.0);
    assertEquals(2.5757454377514617E22d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(3, actualNumeraire.getTypePriority());
    assertEquals(4.6537678368623553E11d, actualNumeraire.getMin(), 0.0);
    assertEquals(6.63446456009747E44d, actualNumeraire.getVariance(), 0.0);
    assertEquals(7.371627288997188E44d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(8.14522225608207E21d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(8.588452105078476E22d, actualNumeraire.getMax(), 0.0);
    assertEquals(8.612248143912867E21d, actualNumeraire.getAverage(), 0.0);
    assertFalse(actualNumeraire.isDeterministic());
    assertArrayEquals(
        new double[] {
          9.1215890110527984E16d,
          2.3149254311932191E18d,
          1.1671176601569062E20d,
          4.4857980294759288E16d,
          8.349480332182474E19d,
          3.4434877294998905E19d,
          4.6537678368623553E11d,
          8.6578669481195853E17d,
          2.1552496057037112E15d,
          8.588452105078476E22d
        },
        actualNumeraire.getRealizations(),
        0.0);
  }

  /**
   * Test {@link MonteCarloAssetModel#getNumeraire(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then calls {@link BrownianMotion#getBrownianIncrement(int, int)}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getNumeraire(double)"})
  public void testGetNumeraireWithTime_thenCallsGetBrownianIncrement() throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            new BlackScholesModelWithStockNumeraire(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualNumeraire = new MonteCarloAssetModel(process).getNumeraire(10.0d);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof Scalar);
    assertTrue(actualNumeraire.abs() instanceof Scalar);
    assertTrue(actualNumeraire.cos() instanceof Scalar);
    assertTrue(actualNumeraire.exp() instanceof Scalar);
    assertTrue(actualNumeraire.expm1() instanceof Scalar);
    assertTrue(actualNumeraire.invert() instanceof Scalar);
    assertTrue(actualNumeraire.isNaN() instanceof Scalar);
    assertTrue(actualNumeraire.sin() instanceof Scalar);
    assertTrue(actualNumeraire.sqrt() instanceof Scalar);
    assertTrue(actualNumeraire.squared() instanceof Scalar);
    assertTrue(actualNumeraire.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloAssetModel#getNumeraire(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return Average is {@code 2.6881171418161356E43}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getNumeraire(double)"})
  public void testGetNumeraireWithTime_thenReturnAverageIs26881171418161356e43()
      throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualNumeraire = new MonteCarloAssetModel(process).getNumeraire(10.0d);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof Scalar);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getAverage(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getMax(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloAssetModel#getMonteCarloWeights(double)} with {@code time}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getMonteCarloWeights(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getMonteCarloWeights(double)"})
  public void testGetMonteCarloWeightsWithTime() throws CalculationException {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge stochasticDriver = new BrownianBridge(generator, start, end);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualMonteCarloWeights =
        new MonteCarloAssetModel(process).getMonteCarloWeights(10.0d);

    // Assert
    assertTrue(actualMonteCarloWeights instanceof Scalar);
    assertTrue(actualMonteCarloWeights.abs() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.cos() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.exp() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.expm1() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.invert() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sin() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.squared() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualMonteCarloWeights.expectation();
    assertSame(actualMonteCarloWeights, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloAssetModel#getMonteCarloWeights(double)} with {@code time}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getMonteCarloWeights(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getMonteCarloWeights(double)"})
  public void testGetMonteCarloWeightsWithTime2() throws CalculationException {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(model, stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable actualMonteCarloWeights =
        new MonteCarloAssetModel(process).getMonteCarloWeights(10.0d);

    // Assert
    assertTrue(actualMonteCarloWeights instanceof Scalar);
    assertTrue(actualMonteCarloWeights.abs() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.cos() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.exp() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.expm1() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.invert() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sin() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.squared() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualMonteCarloWeights.expectation();
    assertSame(actualMonteCarloWeights, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloAssetModel#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex() throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(randomVariableFromDoubleArray);
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualMonteCarloWeights =
        new MonteCarloAssetModel(process).getMonteCarloWeights(1);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertSame(randomVariableFromDoubleArray, actualMonteCarloWeights);
  }

  /**
   * Test {@link MonteCarloAssetModel#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex2() throws CalculationException {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge stochasticDriver = new BrownianBridge(generator, start, end);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable actualMonteCarloWeights =
        new MonteCarloAssetModel(process).getMonteCarloWeights(1);

    // Assert
    assertTrue(actualMonteCarloWeights instanceof Scalar);
    assertTrue(actualMonteCarloWeights.abs() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.cos() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.exp() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.expm1() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.invert() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sin() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.squared() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualMonteCarloWeights.expectation();
    assertSame(actualMonteCarloWeights, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloAssetModel#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex3() throws CalculationException {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(model, stochasticDriver, Scheme.EULER);

    // Act
    RandomVariable actualMonteCarloWeights =
        new MonteCarloAssetModel(process).getMonteCarloWeights(1);

    // Assert
    assertTrue(actualMonteCarloWeights instanceof Scalar);
    assertTrue(actualMonteCarloWeights.abs() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.cos() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.exp() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.expm1() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.invert() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sin() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.squared() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualMonteCarloWeights.expectation();
    assertSame(actualMonteCarloWeights, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloAssetModel#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex4() throws CalculationException {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(model, stochasticDriver, Scheme.PREDICTOR_CORRECTOR);

    // Act
    RandomVariable actualMonteCarloWeights =
        new MonteCarloAssetModel(process).getMonteCarloWeights(1);

    // Assert
    assertTrue(actualMonteCarloWeights instanceof Scalar);
    assertTrue(actualMonteCarloWeights.abs() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.cos() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.exp() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.expm1() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.invert() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sin() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.squared() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualMonteCarloWeights.expectation();
    assertSame(actualMonteCarloWeights, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloAssetModel#getMonteCarloWeights(int)} with {@code timeIndex}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getMonteCarloWeights(int)"})
  public void testGetMonteCarloWeightsWithTimeIndex5() throws CalculationException {
    // Arrange
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, stochasticDriver, Scheme.PREDICTOR_CORRECTOR_FUNCTIONAL);

    // Act
    RandomVariable actualMonteCarloWeights =
        new MonteCarloAssetModel(process).getMonteCarloWeights(1);

    // Assert
    assertTrue(actualMonteCarloWeights instanceof Scalar);
    assertTrue(actualMonteCarloWeights.abs() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.cos() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.exp() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.expm1() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.invert() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sin() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.squared() instanceof Scalar);
    assertTrue(actualMonteCarloWeights.variance() instanceof Scalar);
    RandomVariable actualExpectationResult = actualMonteCarloWeights.expectation();
    assertSame(actualMonteCarloWeights, actualExpectationResult);
  }

  /**
   * Test {@link MonteCarloAssetModel#getMonteCarloWeights(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getMonteCarloWeights(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getMonteCarloWeights(double)"})
  public void testGetMonteCarloWeightsWithTime_thenReturnRandomVariableFromDoubleArray()
      throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualMonteCarloWeights =
        new MonteCarloAssetModel(process).getMonteCarloWeights(10.0d);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualMonteCarloWeights.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloAssetModel#getMonteCarloWeights(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getMonteCarloWeights(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getMonteCarloWeights(double)"})
  public void testGetMonteCarloWeightsWithTime_thenReturnRandomVariableFromDoubleArray2()
      throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    InhomogeneousDisplacedLognomalModel model =
        new InhomogeneousDisplacedLognomalModel(10.0d, 10.0d, 10.0d, 10.0d, true);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);
    MonteCarloAssetModel monteCarloAssetModel =
        new MonteCarloAssetModel(new BachelierModel(1.0d, 1.0d, 1.0d), process);

    // Act
    RandomVariable actualMonteCarloWeights = monteCarloAssetModel.getMonteCarloWeights(10.0d);

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertTrue(actualMonteCarloWeights instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMonteCarloWeights.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {10.0d}, actualMonteCarloWeights.getRealizations(), 0.0);
  }

  /**
   * Test {@link MonteCarloAssetModel#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MonteCarloAssetModel MonteCarloAssetModel.getCloneWithModifiedData(Map)"})
  public void testGetCloneWithModifiedData() throws CalculationException {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);
    MonteCarloAssetModel monteCarloAssetModel = new MonteCarloAssetModel(process);

    // Act
    MonteCarloAssetModel actualCloneWithModifiedData =
        monteCarloAssetModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    MonteCarloProcess process2 = actualCloneWithModifiedData.getProcess();
    IndependentIncrements stochasticDriver2 = process2.getStochasticDriver();
    assertTrue(stochasticDriver2 instanceof BrownianMotionWithControlVariate);
    ProcessModel model = actualCloneWithModifiedData.getModel();
    assertTrue(model instanceof BachelierModel);
    assertTrue(process2 instanceof EulerSchemeFromProcessModel);
    assertTrue(((BachelierModel) model).getInitialValue() instanceof Scalar);
    assertTrue(((BachelierModel) model).getRiskFreeRate() instanceof Scalar);
    assertTrue(((BachelierModel) model).getVolatility() instanceof Scalar);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(0, actualCloneWithModifiedData.getNumberOfPaths());
    assertEquals(0, process2.getNumberOfFactors());
    assertEquals(0, process2.getNumberOfPaths());
    assertEquals(1, model.getNumberOfComponents());
    assertEquals(1, model.getNumberOfFactors());
    assertSame(stochasticDriver, stochasticDriver2);
    assertSame(model, process2.getModel());
    assertSame(timeDiscretization, process2.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link MonteCarloAssetModel#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MonteCarloAssetModel MonteCarloAssetModel.getCloneWithModifiedData(Map)"})
  public void testGetCloneWithModifiedData2() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    MonteCarloBlackScholesModel monteCarloBlackScholesModel =
        new MonteCarloBlackScholesModel(
            10.0d, 10.0d, 10.0d, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    MonteCarloBlackScholesModel actualCloneWithModifiedData =
        monteCarloBlackScholesModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    MonteCarloProcess process = actualCloneWithModifiedData.getProcess();
    assertTrue(process.getStochasticDriver() instanceof BrownianMotionFromMersenneRandomNumbers);
    assertTrue(actualCloneWithModifiedData instanceof MonteCarloBlackScholesModel);
    assertTrue(process instanceof EulerSchemeFromProcessModel);
    BlackScholesModel model = actualCloneWithModifiedData.getModel();
    assertTrue(model.getRiskFreeRate() instanceof Scalar);
    assertTrue(model.getVolatility() instanceof Scalar);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(1, model.getNumberOfComponents());
    assertEquals(1, model.getNumberOfFactors());
    assertEquals(1, process.getNumberOfFactors());
    assertEquals(10, actualCloneWithModifiedData.getNumberOfPaths());
    assertEquals(10, process.getNumberOfPaths());
    assertSame(model, process.getModel());
    assertSame(timeDiscretization, process.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link MonteCarloAssetModel#getCloneWithModifiedSeed(int)}.
   *
   * <ul>
   *   <li>Then return {@link MonteCarloBlackScholesModel}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AssetModelMonteCarloSimulationModel MonteCarloAssetModel.getCloneWithModifiedSeed(int)"
  })
  public void testGetCloneWithModifiedSeed_thenReturnMonteCarloBlackScholesModel() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);

    // Act
    AssetModelMonteCarloSimulationModel actualCloneWithModifiedSeed =
        new MonteCarloBlackScholesModel(
                10.0d, 10.0d, 10.0d, new BrownianMotionWithControlVariate(brownianMotion))
            .getCloneWithModifiedSeed(42);

    // Assert
    assertTrue(actualCloneWithModifiedSeed instanceof MonteCarloBlackScholesModel);
    assertTrue(
        ((MonteCarloBlackScholesModel) actualCloneWithModifiedSeed).getProcess()
            instanceof EulerSchemeFromProcessModel);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedSeed.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(1, actualCloneWithModifiedSeed.getNumberOfAssets());
    assertEquals(10, actualCloneWithModifiedSeed.getNumberOfPaths());
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link MonteCarloAssetModel#getCloneWithModifiedSeed(int)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getCloneWithModifiedSeed(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AssetModelMonteCarloSimulationModel MonteCarloAssetModel.getCloneWithModifiedSeed(int)"
  })
  public void testGetCloneWithModifiedSeed_thenThrowUnsupportedOperationException() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> new MonteCarloAssetModel(process).getCloneWithModifiedSeed(42));
    verify(brownianMotion).getTimeDiscretization();
  }

  /**
   * Test {@link MonteCarloAssetModel#getNumberOfPaths()}.
   *
   * <ul>
   *   <li>Given {@link BrownianMotion} {@link BrownianMotion#getNumberOfPaths()} return ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getNumberOfPaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MonteCarloAssetModel.getNumberOfPaths()"})
  public void testGetNumberOfPaths_givenBrownianMotionGetNumberOfPathsReturnTen_thenReturnTen() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    int actualNumberOfPaths = new MonteCarloAssetModel(process).getNumberOfPaths();

    // Assert
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(10, actualNumberOfPaths);
  }

  /**
   * Test {@link MonteCarloAssetModel#getReferenceDate()}.
   *
   * <ul>
   *   <li>Then return toLocalTime toString is {@code 00:00}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDateTime MonteCarloAssetModel.getReferenceDate()"})
  public void testGetReferenceDate_thenReturnToLocalTimeToStringIs0000() {
    // Arrange
    MonteCarloMultiAssetBlackScholesModel model = mock(MonteCarloMultiAssetBlackScholesModel.class);

    LocalDate ofResult = LocalDate.of(1970, 1, 1);
    when(model.getReferenceDate()).thenReturn(ofResult.atStartOfDay());
    when(model.applyStateSpaceTransformInverse(
            Mockito.<MonteCarloProcess>any(), anyInt(), anyInt(), Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge brownianMotion2 = new BrownianBridge(generator, start, end);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion2));

    // Act
    LocalDateTime actualReferenceDate = new MonteCarloAssetModel(process).getReferenceDate();

    // Assert
    verify(model)
        .applyStateSpaceTransformInverse(
            (MonteCarloProcess) isNull(), eq(0), eq(0), (RandomVariable) isNull());
    verify(model).getReferenceDate();
    assertEquals("00:00", actualReferenceDate.toLocalTime().toString());
    LocalDate toLocalDateResult = actualReferenceDate.toLocalDate();
    assertEquals("1970-01-01", toLocalDateResult.toString());
    assertSame(ofResult, toLocalDateResult);
  }

  /**
   * Test {@link MonteCarloAssetModel#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization MonteCarloAssetModel.getTimeDiscretization()"})
  public void testGetTimeDiscretization() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    TenorFromArray tenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    when(brownianMotion.getTimeDiscretization()).thenReturn(tenorFromArray);
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    TimeDiscretization actualTimeDiscretization =
        new MonteCarloAssetModel(process).getTimeDiscretization();

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertSame(tenorFromArray, actualTimeDiscretization);
  }

  /**
   * Test {@link MonteCarloAssetModel#getTime(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double MonteCarloAssetModel.getTime(int)"})
  public void testGetTime_thenReturn105() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    double actualTime = new MonteCarloAssetModel(process).getTime(1);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(10.5d, actualTime, 0.0);
  }

  /**
   * Test {@link MonteCarloAssetModel#getTimeIndex(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getTimeIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MonteCarloAssetModel.getTimeIndex(double)"})
  public void testGetTimeIndex_thenReturnZero() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    int actualTimeIndex = new MonteCarloAssetModel(process).getTimeIndex(10.0d);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(0, actualTimeIndex);
  }

  /**
   * Test {@link MonteCarloAssetModel#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloAssetModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable MonteCarloAssetModel.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant_thenReturnScalar() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualRandomVariableForConstant =
        new MonteCarloAssetModel(process).getRandomVariableForConstant(10.0d);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertTrue(actualRandomVariableForConstant instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.abs() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.cos() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.exp() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.expm1() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.invert() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.isNaN() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.sin() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.sqrt() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.squared() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.variance() instanceof Scalar);
    assertNull(actualRandomVariableForConstant.getRealizations());
    assertNull(actualRandomVariableForConstant.getOperator());
    assertNull(actualRandomVariableForConstant.getRealizationsStream());
    assertEquals(0, actualRandomVariableForConstant.getTypePriority());
    assertEquals(0.0d, actualRandomVariableForConstant.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableForConstant.size());
    assertEquals(10.0d, actualRandomVariableForConstant.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMin(), 0.0);
    assertTrue(actualRandomVariableForConstant.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualRandomVariableForConstant.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualRandomVariableForConstant.expectation();
    assertSame(actualRandomVariableForConstant, actualExpectationResult);
  }
}
