package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.CalibrationProduct;
import net.finmath.montecarlo.interestrate.LIBORMarketModel;
import net.finmath.montecarlo.interestrate.models.LIBORMarketModelFromCovarianceModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric.FutureTaskWithPriority;
import net.finmath.montecarlo.interestrate.products.ForwardRateVolatilitySurfaceCurvature;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AbstractLIBORCovarianceModelParametricDiffblueTest {
  /**
   * Test FutureTaskWithPriority {@link FutureTaskWithPriority#compareTo(FutureTaskWithPriority)}
   * with {@code FutureTaskWithPriority}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link FutureTaskWithPriority#compareTo(FutureTaskWithPriority)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int FutureTaskWithPriority.compareTo(FutureTaskWithPriority)"})
  public void testFutureTaskWithPriorityCompareToWithFutureTaskWithPriority_thenReturnMinusOne() {
    // Arrange
    FutureTaskWithPriority<Object> futureTaskWithPriority =
        new FutureTaskWithPriority<>(mock(Callable.class), 0);

    // Act
    int actualCompareToResult =
        futureTaskWithPriority.compareTo(new FutureTaskWithPriority<>(mock(Callable.class), 1));

    // Assert
    assertEquals(-1, actualCompareToResult);
  }

  /**
   * Test FutureTaskWithPriority {@link FutureTaskWithPriority#compareTo(FutureTaskWithPriority)}
   * with {@code FutureTaskWithPriority}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link FutureTaskWithPriority#compareTo(FutureTaskWithPriority)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int FutureTaskWithPriority.compareTo(FutureTaskWithPriority)"})
  public void testFutureTaskWithPriorityCompareToWithFutureTaskWithPriority_thenReturnOne() {
    // Arrange
    FutureTaskWithPriority<Object> futureTaskWithPriority =
        new FutureTaskWithPriority<>(mock(Callable.class), 1);

    // Act
    int actualCompareToResult =
        futureTaskWithPriority.compareTo(new FutureTaskWithPriority<>(mock(Callable.class), 0));

    // Assert
    assertEquals(1, actualCompareToResult);
  }

  /**
   * Test FutureTaskWithPriority {@link FutureTaskWithPriority#compareTo(FutureTaskWithPriority)}
   * with {@code FutureTaskWithPriority}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link FutureTaskWithPriority#compareTo(FutureTaskWithPriority)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int FutureTaskWithPriority.compareTo(FutureTaskWithPriority)"})
  public void testFutureTaskWithPriorityCompareToWithFutureTaskWithPriority_thenReturnZero() {
    // Arrange
    FutureTaskWithPriority<Object> futureTaskWithPriority =
        new FutureTaskWithPriority<>(mock(Callable.class), 1);

    // Act
    int actualCompareToResult =
        futureTaskWithPriority.compareTo(new FutureTaskWithPriority<>(mock(Callable.class), 1));

    // Assert
    assertEquals(0, actualCompareToResult);
  }

  /**
   * Test FutureTaskWithPriority getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FutureTaskWithPriority#FutureTaskWithPriority(Callable, int)}
   *   <li>{@link FutureTaskWithPriority#getPriority()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FutureTaskWithPriority.<init>(Callable, int)",
    "int FutureTaskWithPriority.getPriority()"
  })
  public void testFutureTaskWithPriorityGettersAndSetters() {
    // Arrange and Act
    FutureTaskWithPriority<Object> actualFutureTaskWithPriority =
        new FutureTaskWithPriority<>(mock(Callable.class), 1);

    // Assert
    assertEquals(1, actualFutureTaskWithPriority.getPriority());
  }

  /**
   * Test {@link AbstractLIBORCovarianceModelParametric#getParameter()}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModelParametric#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] AbstractLIBORCovarianceModelParametric.getParameter()"})
  public void testGetParameter() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter()).thenReturn(new RandomVariable[] {Scalar.of(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    RandomVariable[] actualParameter =
        new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    RandomVariable randomVariable = actualParameter[0];
    RandomVariable cosResult = randomVariable.cos();
    assertTrue(cosResult.abs() instanceof Scalar);
    RandomVariable absResult = randomVariable.abs();
    assertTrue(absResult instanceof Scalar);
    assertTrue(cosResult.cos() instanceof Scalar);
    assertTrue(cosResult instanceof Scalar);
    assertTrue(cosResult.exp() instanceof Scalar);
    assertTrue(cosResult.expm1() instanceof Scalar);
    assertTrue(cosResult.invert() instanceof Scalar);
    assertTrue(cosResult.isNaN() instanceof Scalar);
    assertTrue(cosResult.sin() instanceof Scalar);
    assertTrue(absResult.sqrt() instanceof Scalar);
    assertTrue(cosResult.sqrt() instanceof Scalar);
    assertTrue(absResult.squared() instanceof Scalar);
    assertTrue(absResult.variance() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(2, actualParameter.length);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModelParametric#getParameter()}.
   *
   * <ul>
   *   <li>Then first element cos abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModelParametric#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] AbstractLIBORCovarianceModelParametric.getParameter()"})
  public void testGetParameter_thenFirstElementCosAbsReturnScalar() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    RandomVariable[] actualParameter =
        new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    RandomVariable randomVariable = actualParameter[0];
    RandomVariable cosResult = randomVariable.cos();
    assertTrue(cosResult.abs() instanceof Scalar);
    RandomVariable absResult = randomVariable.abs();
    assertTrue(absResult instanceof Scalar);
    assertTrue(cosResult.cos() instanceof Scalar);
    assertTrue(cosResult instanceof Scalar);
    assertTrue(cosResult.exp() instanceof Scalar);
    assertTrue(cosResult.expm1() instanceof Scalar);
    assertTrue(cosResult.invert() instanceof Scalar);
    assertTrue(cosResult.isNaN() instanceof Scalar);
    assertTrue(cosResult.sin() instanceof Scalar);
    assertTrue(absResult.sqrt() instanceof Scalar);
    assertTrue(cosResult.sqrt() instanceof Scalar);
    assertTrue(absResult.squared() instanceof Scalar);
    assertTrue(absResult.variance() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(2, actualParameter.length);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModelParametric#getParameter()}.
   *
   * <ul>
   *   <li>Then second element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModelParametric#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] AbstractLIBORCovarianceModelParametric.getParameter()"})
  public void testGetParameter_thenSecondElementReturnScalar() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);

    // Act
    RandomVariable[] actualParameter =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d).getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertTrue(actualParameter[0] instanceof Scalar);
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[2] instanceof Scalar);
    assertEquals(3, actualParameter.length);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModelParametric#getParameter()}.
   *
   * <ul>
   *   <li>Then third element abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModelParametric#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] AbstractLIBORCovarianceModelParametric.getParameter()"})
  public void testGetParameter_thenThirdElementAbsReturnScalar() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(covarianceModel.getParameter())
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    RandomVariable[] actualParameter =
        new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    RandomVariable randomVariable = actualParameter[2];
    assertTrue(randomVariable.abs() instanceof Scalar);
    assertTrue(randomVariable.cos() instanceof Scalar);
    assertTrue(randomVariable.exp() instanceof Scalar);
    assertTrue(randomVariable.expm1() instanceof Scalar);
    assertTrue(randomVariable.invert() instanceof Scalar);
    assertTrue(randomVariable.isNaN() instanceof Scalar);
    assertTrue(randomVariable.sin() instanceof Scalar);
    assertTrue(randomVariable.sqrt() instanceof Scalar);
    assertTrue(randomVariable.squared() instanceof Scalar);
    assertTrue(randomVariable.variance() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(3, actualParameter.length);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link
   * AbstractLIBORCovarianceModelParametric#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * AbstractLIBORCovarianceModelParametric#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric AbstractLIBORCovarianceModelParametric.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel2 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.getCloneWithModifiedParameters(Mockito.<RandomVariable[]>any()))
        .thenReturn(hullWhiteLocalVolatilityModel);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        hullWhiteLocalVolatilityModel2.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel2).getCloneWithModifiedParameters(isA(RandomVariable[].class));
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((HullWhiteLocalVolatilityModel) actualCloneWithModifiedParameters)
            .getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(
        hullWhiteLocalVolatilityModel,
        ((BlendedLocalVolatilityModel) baseCovarianceModel).getBaseCovarianceModel());
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(new double[] {10.0d, 10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * AbstractLIBORCovarianceModelParametric#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * AbstractLIBORCovarianceModelParametric#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric AbstractLIBORCovarianceModelParametric.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        hullWhiteLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((HullWhiteLocalVolatilityModel) actualCloneWithModifiedParameters)
            .getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(new double[] {10.0d, 10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * AbstractLIBORCovarianceModelParametric#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * AbstractLIBORCovarianceModelParametric#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric AbstractLIBORCovarianceModelParametric.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable3() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getCloneWithModifiedParameters(Mockito.<RandomVariable[]>any()))
        .thenThrow(new IllegalArgumentException());
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, false);
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            hullWhiteLocalVolatilityModel.getCloneWithModifiedParameters(
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getCloneWithModifiedParameters(isA(RandomVariable[].class));
  }

  /**
   * Test {@link AbstractLIBORCovarianceModelParametric#getCloneCalibrated(LIBORMarketModel,
   * CalibrationProduct[])} with {@code calibrationModel}, {@code calibrationProducts}.
   *
   * <p>Method under test: {@link
   * AbstractLIBORCovarianceModelParametric#getCloneCalibrated(LIBORMarketModel,
   * CalibrationProduct[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric AbstractLIBORCovarianceModelParametric.getCloneCalibrated(LIBORMarketModel, CalibrationProduct[])"
  })
  public void testGetCloneCalibratedWithCalibrationModelCalibrationProducts()
      throws CalculationException {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameter()).thenThrow(new IllegalArgumentException());
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel2 =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d);

    LIBORMarketModelFromCovarianceModel calibrationModel =
        LIBORMarketModelFromCovarianceModel.of(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            covarianceModel3,
            new CalibrationProduct[] {
              new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
            },
            new HashMap<>());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            hullWhiteLocalVolatilityModel.getCloneCalibrated(
                calibrationModel,
                new CalibrationProduct[] {
                  new CalibrationProduct(
                      new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
                }));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
  }

  /**
   * Test {@link AbstractLIBORCovarianceModelParametric#getCloneCalibrated(LIBORMarketModel,
   * CalibrationProduct[])} with {@code calibrationModel}, {@code calibrationProducts}.
   *
   * <p>Method under test: {@link
   * AbstractLIBORCovarianceModelParametric#getCloneCalibrated(LIBORMarketModel,
   * CalibrationProduct[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric AbstractLIBORCovarianceModelParametric.getCloneCalibrated(LIBORMarketModel, CalibrationProduct[])"
  })
  public void testGetCloneCalibratedWithCalibrationModelCalibrationProducts2()
      throws CalculationException {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameter()).thenThrow(new IllegalArgumentException());
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel4 =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel5 =
        new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d);

    LIBORMarketModelFromCovarianceModel calibrationModel =
        LIBORMarketModelFromCovarianceModel.of(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            covarianceModel5,
            new CalibrationProduct[] {
              new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
            },
            new HashMap<>());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            hullWhiteLocalVolatilityModel.getCloneCalibrated(
                calibrationModel,
                new CalibrationProduct[] {
                  new CalibrationProduct(
                      new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
                }));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
  }

  /**
   * Test {@link AbstractLIBORCovarianceModelParametric#getCloneCalibratedLegazy(LIBORMarketModel,
   * CalibrationProduct[], Map)}.
   *
   * <p>Method under test: {@link
   * AbstractLIBORCovarianceModelParametric#getCloneCalibratedLegazy(LIBORMarketModel,
   * CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric AbstractLIBORCovarianceModelParametric.getCloneCalibratedLegazy(LIBORMarketModel, CalibrationProduct[], Map)"
  })
  public void testGetCloneCalibratedLegazy() throws CalculationException {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameter()).thenThrow(new IllegalArgumentException());
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel2 =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d);

    LIBORMarketModelFromCovarianceModel calibrationModel =
        LIBORMarketModelFromCovarianceModel.of(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            covarianceModel3,
            new CalibrationProduct[] {
              new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
            },
            new HashMap<>());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            hullWhiteLocalVolatilityModel.getCloneCalibratedLegazy(
                calibrationModel,
                new CalibrationProduct[] {
                  new CalibrationProduct(
                      new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
                },
                new HashMap<>()));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
  }

  /**
   * Test {@link AbstractLIBORCovarianceModelParametric#getCloneCalibratedLegazy(LIBORMarketModel,
   * CalibrationProduct[], Map)}.
   *
   * <p>Method under test: {@link
   * AbstractLIBORCovarianceModelParametric#getCloneCalibratedLegazy(LIBORMarketModel,
   * CalibrationProduct[], Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric AbstractLIBORCovarianceModelParametric.getCloneCalibratedLegazy(LIBORMarketModel, CalibrationProduct[], Map)"
  })
  public void testGetCloneCalibratedLegazy2() throws CalculationException {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getCloneWithModifiedParameters(Mockito.<RandomVariable[]>any()))
        .thenThrow(new IllegalArgumentException());
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    AnalyticModelFromCurvesAndVols analyticModel = new AnalyticModelFromCurvesAndVols();
    ForwardCurveFromDiscountCurve forwardRateCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel4 =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel5 =
        new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d);

    LIBORMarketModelFromCovarianceModel calibrationModel =
        LIBORMarketModelFromCovarianceModel.of(
            liborPeriodDiscretization,
            analyticModel,
            forwardRateCurve,
            discountCurve,
            randomVariableFactory,
            covarianceModel5,
            new CalibrationProduct[] {
              new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
            },
            new HashMap<>());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            hullWhiteLocalVolatilityModel.getCloneCalibratedLegazy(
                calibrationModel,
                new CalibrationProduct[] {
                  new CalibrationProduct(
                      new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
                },
                new HashMap<>()));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getCloneWithModifiedParameters(isA(RandomVariable[].class));
    verify(covarianceModel).getParameter();
  }

  /**
   * Test {@link AbstractLIBORCovarianceModelParametric#toString()}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModelParametric#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractLIBORCovarianceModelParametric.toString()"})
  public void testToString() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(covarianceModel.getParameter())
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    String actualToStringResult =
        new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d)
            .toString();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertEquals(
        "AbstractLIBORCovarianceModelParametric [getParameter()=[10.0, 10.0, 10.0]]",
        actualToStringResult);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModelParametric#toString()}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModelParametric#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractLIBORCovarianceModelParametric.toString()"})
  public void testToString2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);

    // Act
    String actualToStringResult =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d).toString();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertEquals(
        "AbstractLIBORCovarianceModelParametric [getParameter()=[10.0, 10.0, 10.0]]",
        actualToStringResult);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModelParametric#toString()}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModelParametric#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractLIBORCovarianceModelParametric.toString()"})
  public void testToString3() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter()).thenReturn(new RandomVariable[] {Scalar.of(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);

    // Act
    String actualToStringResult =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d).toString();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertEquals(
        "AbstractLIBORCovarianceModelParametric [getParameter()=[10.0, 10.0, 10.0]]",
        actualToStringResult);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModelParametric#toString()}.
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModelParametric#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractLIBORCovarianceModelParametric.toString()"})
  public void testToString4() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    String actualToStringResult =
        new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d).toString();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertEquals(
        "AbstractLIBORCovarianceModelParametric [getParameter()=[10.0, 10.0, 10.0, 10.0]]",
        actualToStringResult);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModelParametric#toString()}.
   *
   * <ul>
   *   <li>Then return {@code AbstractLIBORCovarianceModelParametric [getParameter()=[10.0, 10.0]]}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModelParametric#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractLIBORCovarianceModelParametric.toString()"})
  public void testToString_thenReturnAbstractLIBORCovarianceModelParametricGetParameter100100() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    String actualToStringResult =
        new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d)
            .toString();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertEquals(
        "AbstractLIBORCovarianceModelParametric [getParameter()=[10.0, 10.0]]",
        actualToStringResult);
  }

  /**
   * Test {@link AbstractLIBORCovarianceModelParametric#toString()}.
   *
   * <ul>
   *   <li>Then return {@code AbstractLIBORCovarianceModelParametric [getParameter()=[10.0, 10.0]]}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORCovarianceModelParametric#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractLIBORCovarianceModelParametric.toString()"})
  public void testToString_thenReturnAbstractLIBORCovarianceModelParametricGetParameter1001002() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter()).thenReturn(new RandomVariable[] {Scalar.of(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    String actualToStringResult =
        new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d)
            .toString();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertEquals(
        "AbstractLIBORCovarianceModelParametric [getParameter()=[10.0, 10.0]]",
        actualToStringResult);
  }
}
