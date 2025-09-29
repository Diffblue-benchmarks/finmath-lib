package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class HullWhiteLocalVolatilityModelDiffblueTest {
  /**
   * Test {@link
   * HullWhiteLocalVolatilityModel#HullWhiteLocalVolatilityModel(AbstractLIBORCovarianceModelParametric,
   * double)}.
   *
   * <p>Method under test: {@link
   * HullWhiteLocalVolatilityModel#HullWhiteLocalVolatilityModel(AbstractLIBORCovarianceModelParametric,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HullWhiteLocalVolatilityModel.<init>(AbstractLIBORCovarianceModelParametric, double)"
  })
  public void testNewHullWhiteLocalVolatilityModel() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    TenorFromArray tenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    when(covarianceModel.getLiborPeriodDiscretization()).thenReturn(tenorFromArray);
    TenorFromArray tenorFromArray2 = new TenorFromArray(10.0d, 10, 0.5d);
    when(covarianceModel.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true);

    // Act
    HullWhiteLocalVolatilityModel actualHullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        actualHullWhiteLocalVolatilityModel.getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof BlendedLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualHullWhiteLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(1, actualHullWhiteLocalVolatilityModel.getParameter().length);
    assertEquals(3, actualHullWhiteLocalVolatilityModel.getNumberOfFactors());
    assertSame(covarianceModel2, baseCovarianceModel);
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(tenorFromArray2, actualHullWhiteLocalVolatilityModel.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d}, actualHullWhiteLocalVolatilityModel.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#clone()}.
   *
   * <ul>
   *   <li>Then return {@link HullWhiteLocalVolatilityModel}.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteLocalVolatilityModel#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object HullWhiteLocalVolatilityModel.clone()"})
  public void testClone_thenReturnHullWhiteLocalVolatilityModel() {
    // Arrange
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        mock(BlendedLocalVolatilityModel.class);
    when(blendedLocalVolatilityModel.getNumberOfFactors()).thenReturn(3);
    TenorFromArray tenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    when(blendedLocalVolatilityModel.getLiborPeriodDiscretization()).thenReturn(tenorFromArray);
    TenorFromArray tenorFromArray2 = new TenorFromArray(10.0d, 10, 0.5d);
    when(blendedLocalVolatilityModel.getTimeDiscretization()).thenReturn(tenorFromArray2);

    BlendedLocalVolatilityModel covarianceModel = mock(BlendedLocalVolatilityModel.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.clone()).thenReturn(blendedLocalVolatilityModel);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Object actualCloneResult = new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d).clone();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(blendedLocalVolatilityModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(blendedLocalVolatilityModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(blendedLocalVolatilityModel).getTimeDiscretization();
    verify(covarianceModel).clone();
    assertTrue(actualCloneResult instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        ((HullWhiteLocalVolatilityModel) actualCloneResult).getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertNull(((HullWhiteLocalVolatilityModel) actualCloneResult).getParameterAsDouble());
    assertEquals(3, ((HullWhiteLocalVolatilityModel) actualCloneResult).getNumberOfFactors());
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(
        tenorFromArray2,
        ((HullWhiteLocalVolatilityModel) actualCloneResult).getTimeDiscretization());
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link HullWhiteLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] HullWhiteLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble() {
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
    double[] actualParameterAsDouble =
        new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link HullWhiteLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] HullWhiteLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter()).thenReturn(new RandomVariable[] {Scalar.of(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    double[] actualParameterAsDouble =
        new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link HullWhiteLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] HullWhiteLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble3() {
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
    double[] actualParameterAsDouble =
        new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link HullWhiteLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] HullWhiteLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble4() {
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
    double[] actualParameterAsDouble =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d).getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * HullWhiteLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric HullWhiteLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble() {
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
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel3, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

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
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * HullWhiteLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric HullWhiteLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble2() {
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
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d),
            10.0d);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel3, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

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
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <ul>
   *   <li>Then return array length is four.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric HullWhiteLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble_thenReturnArrayLengthIsFour() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(covarianceModel, forwardCurve, 10.0d, true);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            randomVariableFactory,
            new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCloneWithModifiedParameters instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(4, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * HullWhiteLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric HullWhiteLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble_thenReturnArrayLengthIsTwo() {
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

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

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
    assertEquals(2, baseCovarianceModel.getParameter().length);
    assertEquals(2, actualCloneWithModifiedParameters.getParameter().length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(
        hullWhiteLocalVolatilityModel,
        ((BlendedLocalVolatilityModel) baseCovarianceModel).getBaseCovarianceModel());
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d, 0.5d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 0.5d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])} with
   * {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link HullWhiteLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {randomVariableFromDoubleArray});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 1.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 1.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        new HullWhiteLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 1.0d), 10.0d)
            .getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(1, actualFactorLoading.length);
    assertSame(randomVariableFromDoubleArray, actualFactorLoading[0]);
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])} with
   * {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link HullWhiteLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 1.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 1.0d, true);
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(covarianceModel3, 1.0d), 10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            1,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertEquals(122210.0d, randomVariable.getAverage(), 0.0);
    assertEquals(122210.0d, randomVariable.getMax(), 0.0);
    assertEquals(122210.0d, randomVariable.getMin(), 0.0);
    assertArrayEquals(new double[] {122210.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])} with
   * {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link HullWhiteLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HullWhiteLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable3() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    LIBORCovarianceModelStochasticHestonVolatility covarianceModel3 =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel3, 1.0d, true);
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(covarianceModel4, 1.0d), 10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hullWhiteLocalVolatilityModel.getFactorLoading(
            1,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#getFactorLoadingPseudoInverse(int, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link HullWhiteLocalVolatilityModel#getFactorLoadingPseudoInverse(int,
   * int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HullWhiteLocalVolatilityModel.getFactorLoadingPseudoInverse(int, int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingPseudoInverse() {
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

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            hullWhiteLocalVolatilityModel.getFactorLoadingPseudoInverse(
                1, 1, 3, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteLocalVolatilityModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric HullWhiteLocalVolatilityModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_thenReturnArrayLengthIsTwo()
      throws CalculationException {
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
    when(covarianceModel2.getCloneWithModifiedData(Mockito.<Map<String, Object>>any()))
        .thenReturn(hullWhiteLocalVolatilityModel);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        hullWhiteLocalVolatilityModel2.getCloneWithModifiedData(new HashMap<>());

    // Assert
    verify(covarianceModel2).getCloneWithModifiedData(isA(Map.class));
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((HullWhiteLocalVolatilityModel) actualCloneWithModifiedData).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedData instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, baseCovarianceModel.getParameter().length);
    assertEquals(2, actualCloneWithModifiedData.getParameter().length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(
        hullWhiteLocalVolatilityModel,
        ((DisplacedLocalVolatilityModel) baseCovarianceModel).getBaseCovarianceModel());
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d, 10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedData.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link HullWhiteLocalVolatilityModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link HullWhiteLocalVolatilityModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric HullWhiteLocalVolatilityModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenNull_thenReturnArrayLengthIsOne()
      throws CalculationException {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d).getCloneWithModifiedData(null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedData instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(1, actualCloneWithModifiedData.getParameter().length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(
        covarianceModel2,
        ((HullWhiteLocalVolatilityModel) actualCloneWithModifiedData).getBaseCovarianceModel());
    assertArrayEquals(
        new double[] {10.0d}, actualCloneWithModifiedData.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }
}
