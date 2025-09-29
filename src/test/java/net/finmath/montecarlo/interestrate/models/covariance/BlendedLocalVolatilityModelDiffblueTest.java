package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwiseFactory;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class BlendedLocalVolatilityModelDiffblueTest {
  /**
   * Test {@link BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, double, boolean)}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlendedLocalVolatilityModel.<init>(RandomVariableFactory, AbstractLIBORCovarianceModelParametric, double, boolean)"
  })
  public void testNewBlendedLocalVolatilityModel() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    BlendedLocalVolatilityModel actualBlendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            randomVariableFactory,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable[] parameter = actualBlendedLocalVolatilityModel.getParameter();
    assertTrue(parameter[1] instanceof RandomVariableFromFloatArray);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        actualBlendedLocalVolatilityModel.getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualBlendedLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualBlendedLocalVolatilityModel.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, parameter.length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualBlendedLocalVolatilityModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, double, boolean)}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlendedLocalVolatilityModel.<init>(RandomVariableFactory, AbstractLIBORCovarianceModelParametric, double, boolean)"
  })
  public void testNewBlendedLocalVolatilityModel2() {
    // Arrange
    RandomVariableDifferentiableAADPathwiseFactory randomVariableFactory =
        new RandomVariableDifferentiableAADPathwiseFactory();

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    BlendedLocalVolatilityModel actualBlendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            randomVariableFactory,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable[] parameter = actualBlendedLocalVolatilityModel.getParameter();
    assertTrue(parameter[1] instanceof RandomVariableDifferentiableAADPathwise);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        actualBlendedLocalVolatilityModel.getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualBlendedLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualBlendedLocalVolatilityModel.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, parameter.length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualBlendedLocalVolatilityModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, ForwardCurve, double, boolean)}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, ForwardCurve, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlendedLocalVolatilityModel.<init>(RandomVariableFactory, AbstractLIBORCovarianceModelParametric, ForwardCurve, double, boolean)"
  })
  public void testNewBlendedLocalVolatilityModel3() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    BlendedLocalVolatilityModel actualBlendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            randomVariableFactory, covarianceModel2, forwardCurve, 10.0d, true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable[] parameter = actualBlendedLocalVolatilityModel.getParameter();
    assertTrue(parameter[1] instanceof RandomVariableFromFloatArray);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        actualBlendedLocalVolatilityModel.getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualBlendedLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualBlendedLocalVolatilityModel.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, parameter.length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualBlendedLocalVolatilityModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, ForwardCurve, double, boolean)}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, ForwardCurve, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlendedLocalVolatilityModel.<init>(RandomVariableFactory, AbstractLIBORCovarianceModelParametric, ForwardCurve, double, boolean)"
  })
  public void testNewBlendedLocalVolatilityModel4() {
    // Arrange
    RandomVariableDifferentiableAADPathwiseFactory randomVariableFactory =
        new RandomVariableDifferentiableAADPathwiseFactory();

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    BlendedLocalVolatilityModel actualBlendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            randomVariableFactory, covarianceModel2, forwardCurve, 10.0d, true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable[] parameter = actualBlendedLocalVolatilityModel.getParameter();
    assertTrue(parameter[1] instanceof RandomVariableDifferentiableAADPathwise);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        actualBlendedLocalVolatilityModel.getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualBlendedLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualBlendedLocalVolatilityModel.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, parameter.length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualBlendedLocalVolatilityModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(AbstractLIBORCovarianceModelParametric,
   * double, boolean)}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(AbstractLIBORCovarianceModelParametric,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlendedLocalVolatilityModel.<init>(AbstractLIBORCovarianceModelParametric, double, boolean)"
  })
  public void testNewBlendedLocalVolatilityModel5() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    TenorFromArray tenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    when(covarianceModel.getLiborPeriodDiscretization()).thenReturn(tenorFromArray);
    TenorFromArray tenorFromArray2 = new TenorFromArray(10.0d, 10, 0.5d);
    when(covarianceModel.getTimeDiscretization()).thenReturn(tenorFromArray2);
    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d);

    // Act
    BlendedLocalVolatilityModel actualBlendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        actualBlendedLocalVolatilityModel.getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualBlendedLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(2, actualBlendedLocalVolatilityModel.getParameter().length);
    assertEquals(3, actualBlendedLocalVolatilityModel.getNumberOfFactors());
    assertSame(covarianceModel3, baseCovarianceModel);
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(tenorFromArray2, actualBlendedLocalVolatilityModel.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualBlendedLocalVolatilityModel.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link
   * BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(AbstractLIBORCovarianceModelParametric,
   * ForwardCurve, double, boolean)}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(AbstractLIBORCovarianceModelParametric,
   * ForwardCurve, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlendedLocalVolatilityModel.<init>(AbstractLIBORCovarianceModelParametric, ForwardCurve, double, boolean)"
  })
  public void testNewBlendedLocalVolatilityModel6() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    TenorFromArray tenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    when(covarianceModel.getLiborPeriodDiscretization()).thenReturn(tenorFromArray);
    TenorFromArray tenorFromArray2 = new TenorFromArray(10.0d, 10, 0.5d);
    when(covarianceModel.getTimeDiscretization()).thenReturn(tenorFromArray2);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    BlendedLocalVolatilityModel actualBlendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(covarianceModel2, forwardCurve, 10.0d, true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        actualBlendedLocalVolatilityModel.getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualBlendedLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(2, actualBlendedLocalVolatilityModel.getParameter().length);
    assertEquals(3, actualBlendedLocalVolatilityModel.getNumberOfFactors());
    assertSame(covarianceModel2, baseCovarianceModel);
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(tenorFromArray2, actualBlendedLocalVolatilityModel.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualBlendedLocalVolatilityModel.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link
   * BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(AbstractLIBORCovarianceModelParametric,
   * ForwardCurve, RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(AbstractLIBORCovarianceModelParametric,
   * ForwardCurve, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlendedLocalVolatilityModel.<init>(AbstractLIBORCovarianceModelParametric, ForwardCurve, RandomVariable, boolean)"
  })
  public void testNewBlendedLocalVolatilityModel7() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    TenorFromArray tenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);
    when(covarianceModel.getLiborPeriodDiscretization()).thenReturn(tenorFromArray);
    TenorFromArray tenorFromArray2 = new TenorFromArray(10.0d, 10, 0.5d);
    when(covarianceModel.getTimeDiscretization()).thenReturn(tenorFromArray2);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    BlendedLocalVolatilityModel actualBlendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            covarianceModel2, forwardCurve, new RandomVariableFromDoubleArray(10.0d), true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        actualBlendedLocalVolatilityModel.getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualBlendedLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(2, actualBlendedLocalVolatilityModel.getParameter().length);
    assertEquals(3, actualBlendedLocalVolatilityModel.getNumberOfFactors());
    assertSame(covarianceModel2, baseCovarianceModel);
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(tenorFromArray2, actualBlendedLocalVolatilityModel.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualBlendedLocalVolatilityModel.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, double, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then second element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlendedLocalVolatilityModel.<init>(RandomVariableFactory, AbstractLIBORCovarianceModelParametric, double, boolean)"
  })
  public void testNewBlendedLocalVolatilityModel_whenNull_thenSecondElementReturnScalar() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    BlendedLocalVolatilityModel actualBlendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            null,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        actualBlendedLocalVolatilityModel.getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    RandomVariable[] parameter = actualBlendedLocalVolatilityModel.getParameter();
    assertTrue(parameter[1] instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        actualBlendedLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualBlendedLocalVolatilityModel.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, parameter.length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualBlendedLocalVolatilityModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, ForwardCurve, double, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then second element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#BlendedLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, ForwardCurve, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlendedLocalVolatilityModel.<init>(RandomVariableFactory, AbstractLIBORCovarianceModelParametric, ForwardCurve, double, boolean)"
  })
  public void testNewBlendedLocalVolatilityModel_whenNull_thenSecondElementReturnScalar2() {
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
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    BlendedLocalVolatilityModel actualBlendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(null, covarianceModel2, forwardCurve, 10.0d, true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        actualBlendedLocalVolatilityModel.getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    RandomVariable[] parameter = actualBlendedLocalVolatilityModel.getParameter();
    assertTrue(parameter[1] instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        actualBlendedLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualBlendedLocalVolatilityModel.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, parameter.length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualBlendedLocalVolatilityModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#clone()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object BlendedLocalVolatilityModel.clone()"})
  public void testClone() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    Object actualCloneResult =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d), 10.0d, true)
            .clone();

    // Assert
    assertTrue(actualCloneResult instanceof BlendedLocalVolatilityModel);
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneResult).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneResult).getLiborPeriodDiscretization()
            instanceof TenorFromArray);
    assertEquals(6, ((BlendedLocalVolatilityModel) actualCloneResult).getParameter().length);
    assertArrayEquals(
        new double[] {0.469d, 0.0452d, 0.35d, 0.01d, -0.8918d, 10.0d},
        ((BlendedLocalVolatilityModel) actualCloneResult).getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#clone()}.
   *
   * <ul>
   *   <li>Then BaseCovarianceModel return {@link LIBORCovarianceModelBH}.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object BlendedLocalVolatilityModel.clone()"})
  public void testClone_thenBaseCovarianceModelReturnLIBORCovarianceModelBH() {
    // Arrange
    DisplacedLocalVolatilityModel covarianceModel = mock(DisplacedLocalVolatilityModel.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH liborCovarianceModelBH =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    when(covarianceModel.clone()).thenReturn(liborCovarianceModelBH);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    Object actualCloneResult =
        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true).clone();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).clone();
    assertTrue(actualCloneResult instanceof BlendedLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((BlendedLocalVolatilityModel) actualCloneResult).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof LIBORCovarianceModelBH);
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneResult).getLiborPeriodDiscretization()
            instanceof TenorFromArray);
    assertSame(liborCovarianceModelBH, baseCovarianceModel);
    assertArrayEquals(
        new double[] {0.469d, 0.0452d, 0.35d, 0.01d, -0.8918d, 10.0d},
        ((BlendedLocalVolatilityModel) actualCloneResult).getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#clone()}.
   *
   * <ul>
   *   <li>Then return array length is seven.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object BlendedLocalVolatilityModel.clone()"})
  public void testClone_thenReturnArrayLengthIsSeven() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            randomVariableFactory,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);

    // Act
    Object actualCloneResult = blendedLocalVolatilityModel.clone();

    // Assert
    assertTrue(actualCloneResult instanceof BlendedLocalVolatilityModel);
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneResult).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneResult).getLiborPeriodDiscretization()
            instanceof TenorFromArray);
    assertEquals(7, ((BlendedLocalVolatilityModel) actualCloneResult).getParameter().length);
    assertArrayEquals(
        new double[] {0.469d, 0.0452d, 0.35d, 0.01d, -0.8918d, 10.0d, 10.0d},
        ((BlendedLocalVolatilityModel) actualCloneResult).getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#clone()}.
   *
   * <ul>
   *   <li>Then return ParameterAsDouble is array of {@code double} with {@code 0.2} and {@code
   *       0.05}.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object BlendedLocalVolatilityModel.clone()"})
  public void testClone_thenReturnParameterAsDoubleIsArrayOfDoubleWith02And005() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    // Act
    Object actualCloneResult =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d), 10.0d, true)
            .clone();

    // Assert
    assertTrue(actualCloneResult instanceof BlendedLocalVolatilityModel);
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneResult).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneResult).getLiborPeriodDiscretization()
            instanceof TenorFromArray);
    assertEquals(6, ((BlendedLocalVolatilityModel) actualCloneResult).getParameter().length);
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d, 10.0d},
        ((BlendedLocalVolatilityModel) actualCloneResult).getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    // Act
    RandomVariable[] actualParameter =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d, true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[3] instanceof Scalar);
    assertTrue(actualParameter[4] instanceof Scalar);
    assertEquals(6, actualParameter.length);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromFloatArray(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    RandomVariable[] actualParameter =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true)
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
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter3() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    ExponentialDecayLocalVolatilityModel covarianceModel3 =
        new ExponentialDecayLocalVolatilityModel(
            randomVariableFactory,
            covarianceModel2,
            new RandomVariableFromDoubleArray(10.0d),
            true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
            10.0d);
    DisplacedLocalVolatilityModel covarianceModel5 =
        new DisplacedLocalVolatilityModel(
            covarianceModel4, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    RandomVariable[] actualParameter =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true),
                    10.0d),
                10.0d,
                true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertTrue(actualParameter[6] instanceof Scalar);
    assertTrue(actualParameter[7] instanceof Scalar);
    assertTrue(actualParameter[8] instanceof Scalar);
    assertEquals(9, actualParameter.length);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter4() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, false);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true);
    DisplacedLocalVolatilityModel covarianceModel5 =
        new DisplacedLocalVolatilityModel(
            covarianceModel4, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    RandomVariable[] actualParameter =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true),
                    10.0d),
                10.0d,
                true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertTrue(actualParameter[5] instanceof Scalar);
    assertTrue(actualParameter[6] instanceof Scalar);
    assertTrue(actualParameter[7] instanceof Scalar);
    assertEquals(8, actualParameter.length);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter5() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true);
    DisplacedLocalVolatilityModel covarianceModel5 =
        new DisplacedLocalVolatilityModel(
            covarianceModel4, new RandomVariableFromDoubleArray(10.0d), false);

    // Act
    RandomVariable[] actualParameter =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true),
                    10.0d),
                10.0d,
                true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertTrue(actualParameter[5] instanceof Scalar);
    assertTrue(actualParameter[6] instanceof Scalar);
    assertTrue(actualParameter[7] instanceof Scalar);
    assertEquals(8, actualParameter.length);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter6() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true);
    DisplacedLocalVolatilityModel covarianceModel5 =
        new DisplacedLocalVolatilityModel(
            covarianceModel4, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    RandomVariable[] actualParameter =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, false),
                    10.0d),
                10.0d,
                true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertTrue(actualParameter[5] instanceof Scalar);
    assertTrue(actualParameter[6] instanceof Scalar);
    assertTrue(actualParameter[7] instanceof Scalar);
    assertEquals(8, actualParameter.length);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter7() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, false);
    DisplacedLocalVolatilityModel covarianceModel5 =
        new DisplacedLocalVolatilityModel(
            covarianceModel4, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    RandomVariable[] actualParameter =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true),
                    10.0d),
                10.0d,
                true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertTrue(actualParameter[5] instanceof Scalar);
    assertTrue(actualParameter[6] instanceof Scalar);
    assertTrue(actualParameter[7] instanceof Scalar);
    assertEquals(8, actualParameter.length);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then first element cos abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenFirstElementCosAbsReturnScalar() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter()).thenReturn(null);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    RandomVariable[] actualParameter =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true)
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
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is five.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenReturnArrayLengthIsFive() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    // Act
    RandomVariable[] actualParameter =
        new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true).getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[3] instanceof Scalar);
    assertTrue(actualParameter[4] instanceof Scalar);
    assertEquals(5, actualParameter.length);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is five.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenReturnArrayLengthIsFive2() {
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
    DisplacedLocalVolatilityModel covarianceModel3 =
        new DisplacedLocalVolatilityModel(
            covarianceModel2, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    RandomVariable[] actualParameter =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
                    10.0d),
                10.0d,
                true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[3] instanceof Scalar);
    assertTrue(actualParameter[4] instanceof Scalar);
    assertEquals(5, actualParameter.length);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is four.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenReturnArrayLengthIsFour() {
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
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(
                            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                        10.0d,
                        true),
                    10.0d),
                10.0d,
                true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertTrue(actualParameter[0] instanceof Scalar);
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[3] instanceof Scalar);
    assertEquals(4, actualParameter.length);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is nine.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenReturnArrayLengthIsNine() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true);
    DisplacedLocalVolatilityModel covarianceModel5 =
        new DisplacedLocalVolatilityModel(
            covarianceModel4, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    RandomVariable[] actualParameter =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true),
                    10.0d),
                10.0d,
                true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertTrue(actualParameter[6] instanceof Scalar);
    assertTrue(actualParameter[7] instanceof Scalar);
    assertTrue(actualParameter[8] instanceof Scalar);
    assertEquals(9, actualParameter.length);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is six.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenReturnArrayLengthIsSix() {
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
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true),
            10.0d);
    DisplacedLocalVolatilityModel covarianceModel3 =
        new DisplacedLocalVolatilityModel(
            covarianceModel2, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    RandomVariable[] actualParameter =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
                    10.0d),
                10.0d,
                true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[3] instanceof Scalar);
    assertTrue(actualParameter[4] instanceof Scalar);
    assertEquals(6, actualParameter.length);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then sixth element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenSixthElementReturnScalar() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true);
    DisplacedLocalVolatilityModel covarianceModel4 =
        new DisplacedLocalVolatilityModel(
            covarianceModel3, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    RandomVariable[] actualParameter =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d), 10.0d, true),
                    10.0d),
                10.0d,
                true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertTrue(actualParameter[5] instanceof Scalar);
    assertTrue(actualParameter[6] instanceof Scalar);
    assertTrue(actualParameter[7] instanceof Scalar);
    assertEquals(8, actualParameter.length);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then tenth element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenTenthElementReturnScalar() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    ExponentialDecayLocalVolatilityModel covarianceModel4 =
        new ExponentialDecayLocalVolatilityModel(
            randomVariableFactory,
            covarianceModel3,
            new RandomVariableFromDoubleArray(10.0d),
            true);
    HullWhiteLocalVolatilityModel covarianceModel5 =
        new HullWhiteLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d), 10.0d, true),
            10.0d);
    DisplacedLocalVolatilityModel covarianceModel6 =
        new DisplacedLocalVolatilityModel(
            covarianceModel5, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    RandomVariable[] actualParameter =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(covarianceModel6, 10.0d), 10.0d, true),
                    10.0d),
                10.0d,
                true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertTrue(actualParameter[6] instanceof Scalar);
    assertTrue(actualParameter[7] instanceof Scalar);
    assertTrue(actualParameter[8] instanceof Scalar);
    assertTrue(actualParameter[9] instanceof Scalar);
    assertEquals(10, actualParameter.length);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then third element abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] BlendedLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenThirdElementAbsReturnScalar() {
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
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true)
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
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);

    // Act
    double[] actualParameterAsDouble =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true), 10.0d, true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertArrayEquals(
        new double[] {
          10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d, 10.0d, 10.0d
        },
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, false);

    // Act
    double[] actualParameterAsDouble =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true), 10.0d, true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertArrayEquals(
        new double[] {
          10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d, 10.0d
        },
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble3() {
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
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble4() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter()).thenReturn(null);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    double[] actualParameterAsDouble =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble5() {
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
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(
                                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true),
                                10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble6() {
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
    DisplacedLocalVolatilityModel covarianceModel3 =
        new DisplacedLocalVolatilityModel(
            covarianceModel2, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    double[] actualParameterAsDouble =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble7() {
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
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true),
            10.0d);
    DisplacedLocalVolatilityModel covarianceModel3 =
        new DisplacedLocalVolatilityModel(
            covarianceModel2, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    double[] actualParameterAsDouble =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble8() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    ExponentialDecayLocalVolatilityModel covarianceModel3 =
        new ExponentialDecayLocalVolatilityModel(
            randomVariableFactory,
            covarianceModel2,
            new RandomVariableFromDoubleArray(10.0d),
            true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
            10.0d);
    DisplacedLocalVolatilityModel covarianceModel5 =
        new DisplacedLocalVolatilityModel(
            covarianceModel4, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    double[] actualParameterAsDouble =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertArrayEquals(
        new double[] {
          10.0d,
          Double.NEGATIVE_INFINITY,
          10.0d,
          Double.NEGATIVE_INFINITY,
          10.0d,
          10.0d,
          10.0d,
          10.0d,
          10.0d,
          10.0d
        },
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble9() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true);
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    ExponentialDecayLocalVolatilityModel covarianceModel4 =
        new ExponentialDecayLocalVolatilityModel(
            randomVariableFactory,
            covarianceModel3,
            new RandomVariableFromDoubleArray(10.0d),
            true);
    HullWhiteLocalVolatilityModel covarianceModel5 =
        new HullWhiteLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d), 10.0d, true),
            10.0d);
    DisplacedLocalVolatilityModel covarianceModel6 =
        new DisplacedLocalVolatilityModel(
            covarianceModel5, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    double[] actualParameterAsDouble =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel6, 10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertArrayEquals(
        new double[] {
          10.0d,
          Double.NEGATIVE_INFINITY,
          10.0d,
          Double.NEGATIVE_INFINITY,
          10.0d,
          10.0d,
          10.0d,
          10.0d,
          10.0d,
          10.0d,
          10.0d
        },
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble10() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true);
    DisplacedLocalVolatilityModel covarianceModel4 =
        new DisplacedLocalVolatilityModel(
            covarianceModel3, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    double[] actualParameterAsDouble =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertArrayEquals(
        new double[] {
          10.0d,
          Double.NEGATIVE_INFINITY,
          10.0d,
          Double.NEGATIVE_INFINITY,
          10.0d,
          10.0d,
          10.0d,
          10.0d,
          10.0d
        },
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble11() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true);
    DisplacedLocalVolatilityModel covarianceModel5 =
        new DisplacedLocalVolatilityModel(
            covarianceModel4, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    double[] actualParameterAsDouble =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertArrayEquals(
        new double[] {
          10.0d,
          Double.NEGATIVE_INFINITY,
          10.0d,
          Double.NEGATIVE_INFINITY,
          10.0d,
          10.0d,
          10.0d,
          10.0d,
          10.0d,
          10.0d
        },
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble12() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true);
    DisplacedLocalVolatilityModel covarianceModel5 =
        new DisplacedLocalVolatilityModel(
            covarianceModel4, new RandomVariableFromDoubleArray(10.0d), false);

    // Act
    double[] actualParameterAsDouble =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertArrayEquals(
        new double[] {
          10.0d,
          Double.NEGATIVE_INFINITY,
          10.0d,
          Double.NEGATIVE_INFINITY,
          10.0d,
          10.0d,
          10.0d,
          10.0d,
          10.0d
        },
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble13() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true);
    DisplacedLocalVolatilityModel covarianceModel5 =
        new DisplacedLocalVolatilityModel(
            covarianceModel4, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    double[] actualParameterAsDouble =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d),
                            10.0d,
                            false),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertArrayEquals(
        new double[] {
          10.0d,
          Double.NEGATIVE_INFINITY,
          10.0d,
          Double.NEGATIVE_INFINITY,
          10.0d,
          10.0d,
          10.0d,
          10.0d,
          10.0d
        },
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble14() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, false);
    DisplacedLocalVolatilityModel covarianceModel5 =
        new DisplacedLocalVolatilityModel(
            covarianceModel4, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    double[] actualParameterAsDouble =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertArrayEquals(
        new double[] {
          10.0d,
          Double.NEGATIVE_INFINITY,
          10.0d,
          Double.NEGATIVE_INFINITY,
          10.0d,
          10.0d,
          10.0d,
          10.0d,
          10.0d
        },
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with ten and {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble_thenReturnArrayOfDoubleWithTenAndNegative_infinity() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    // Act
    double[] actualParameterAsDouble =
        new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true).getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertArrayEquals(
        new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d},
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with ten and {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] BlendedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble_thenReturnArrayOfDoubleWithTenAndNegative_infinity2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getParameterAsDouble())
        .thenReturn(
            new double[] {10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    // Act
    double[] actualParameterAsDouble =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d, true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertArrayEquals(
        new double[] {
          10.0d, Double.NEGATIVE_INFINITY, 10.0d, Double.NEGATIVE_INFINITY, 10.0d, 10.0d
        },
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
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
    when(covarianceModel2.getCloneWithModifiedParameters(Mockito.<double[]>any()))
        .thenReturn(hullWhiteLocalVolatilityModel);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel2).getCloneWithModifiedParameters(isA(double[].class));
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel2 =
        ((HullWhiteLocalVolatilityModel) baseCovarianceModel).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel2 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(1, baseCovarianceModel.getParameter().length);
    assertEquals(2, actualCloneWithModifiedParameters.getParameter().length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(hullWhiteLocalVolatilityModel, baseCovarianceModel2);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 0.5d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
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
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, false);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
                10.0d,
                true)
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
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, baseCovarianceModel.getParameter().length);
    assertEquals(3, actualCloneWithModifiedParameters.getParameter().length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d, 10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
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
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble3() {
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
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(
                                new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true),
                                10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel2).getCloneWithModifiedParameters(isA(RandomVariable[].class));
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble4() {
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

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble5() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true),
            10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(6, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble6() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true),
            10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(6, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble7() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    DisplacedLocalVolatilityModel covarianceModel2 =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(6, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble8() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    DisplacedLocalVolatilityModel covarianceModel2 =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, false);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble9() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(
                                new ExponentialDecayLocalVolatilityModel(
                                    new HullWhiteLocalVolatilityModel(
                                        new BlendedLocalVolatilityModel(
                                            covarianceModel, 10.0d, true),
                                        10.0d),
                                    10.0d,
                                    true),
                                10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble10() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(
                                new ExponentialDecayLocalVolatilityModel(
                                    new BlendedLocalVolatilityModel(
                                        new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d),
                                        10.0d,
                                        true),
                                    10.0d,
                                    true),
                                10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(6, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble11() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(
                                new ExponentialDecayLocalVolatilityModel(
                                    new DisplacedLocalVolatilityModel(
                                        new HullWhiteLocalVolatilityModel(
                                            new BlendedLocalVolatilityModel(
                                                covarianceModel, 10.0d, true),
                                            10.0d),
                                        10.0d,
                                        true),
                                    10.0d,
                                    true),
                                10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(6, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble12() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new BlendedLocalVolatilityModel(
                                new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d),
                                10.0d,
                                true),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble13() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new DisplacedLocalVolatilityModel(
                                new HullWhiteLocalVolatilityModel(
                                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true),
                                    10.0d),
                                10.0d,
                                true),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble14() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new DisplacedLocalVolatilityModel(
                                new ExponentialDecayLocalVolatilityModel(
                                    new HullWhiteLocalVolatilityModel(
                                        new BlendedLocalVolatilityModel(
                                            covarianceModel, 10.0d, true),
                                        10.0d),
                                    10.0d,
                                    true),
                                10.0d,
                                true),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(6, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble15() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new DisplacedLocalVolatilityModel(
                                new ExponentialDecayLocalVolatilityModel(
                                    new HullWhiteLocalVolatilityModel(
                                        new BlendedLocalVolatilityModel(
                                            covarianceModel, 10.0d, true),
                                        10.0d),
                                    10.0d,
                                    true),
                                10.0d,
                                false),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble16() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new DisplacedLocalVolatilityModel(
                                new ExponentialDecayLocalVolatilityModel(
                                    new HullWhiteLocalVolatilityModel(
                                        new BlendedLocalVolatilityModel(
                                            covarianceModel, 10.0d, true),
                                        10.0d),
                                    10.0d,
                                    true),
                                10.0d,
                                true),
                            10.0d,
                            false),
                        10.0d),
                    10.0d,
                    true),
                10.0d,
                true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble17() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm volatilityModel =
        new LIBORVolatilityModelTwoParameterExponentialForm(
            timeDiscretization2, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);
    TenorFromArray timeDiscretization3 = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay correlationModel =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization3, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    LIBORCovarianceModelFromVolatilityAndCorrelation covarianceModel =
        new LIBORCovarianceModelFromVolatilityAndCorrelation(
            timeDiscretization, liborPeriodDiscretization, volatilityModel, correlationModel);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(4, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <ul>
   *   <li>Then fourth element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble_thenFourthElementReturnScalar() {
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
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new BlendedLocalVolatilityModel(
                new BlendedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
                10.0d,
                true)
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
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    RandomVariable[] parameter = actualCloneWithModifiedParameters.getParameter();
    assertTrue(parameter[3] instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(3, baseCovarianceModel.getParameter().length);
    assertEquals(4, parameter.length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
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
    when(covarianceModel2.getCloneWithModifiedParameters(Mockito.<double[]>any()))
        .thenReturn(hullWhiteLocalVolatilityModel);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d);
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel2).getCloneWithModifiedParameters(isA(double[].class));
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel2 =
        ((HullWhiteLocalVolatilityModel) baseCovarianceModel).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel2 instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(hullWhiteLocalVolatilityModel, baseCovarianceModel2);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel, 10.0d, true);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d), 10.0d, true);
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(covarianceModel3, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(
        covarianceModel3,
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable3() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    DisplacedLocalVolatilityModel covarianceModel2 =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(
        covarianceModel2,
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable4() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    ExponentialDecayLocalVolatilityModel covarianceModel2 =
        new ExponentialDecayLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(
        covarianceModel2,
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable5() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true);
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d), 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel();
    AbstractLIBORCovarianceModelParametric baseCovarianceModel2 =
        ((HullWhiteLocalVolatilityModel) baseCovarianceModel).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel2 instanceof BlendedLocalVolatilityModel);
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(covarianceModel2, baseCovarianceModel2);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable6() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    DisplacedLocalVolatilityModel covarianceModel2 =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d), 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel();
    AbstractLIBORCovarianceModelParametric baseCovarianceModel2 =
        ((HullWhiteLocalVolatilityModel) baseCovarianceModel).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel2 instanceof DisplacedLocalVolatilityModel);
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(covarianceModel2, baseCovarianceModel2);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d, 10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable7() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d, 10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable8() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters((RandomVariable[]) null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertSame(blendedLocalVolatilityModel, actualCloneWithModifiedParameters);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable9() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertSame(liborPeriodDiscretization, baseCovarianceModel.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, baseCovarianceModel.getTimeDiscretization());
    assertArrayEquals(new double[] {10.0d, 10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable10() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getCloneWithModifiedParameters(Mockito.<RandomVariable[]>any()))
        .thenThrow(new UnsupportedOperationException());
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            blendedLocalVolatilityModel.getCloneWithModifiedParameters(
                new RandomVariable[] {
                  randomVariableFromDoubleArray,
                  randomVariableFromDoubleArray2,
                  new RandomVariableFromDoubleArray(10.0d)
                }));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getCloneWithModifiedParameters(isA(RandomVariable[].class));
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable11() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(
                                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true),
                                10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
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
        new double[] {10.0d, 10.0d, 10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable12() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel, 10.0d, true);
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new BlendedLocalVolatilityModel(
                                new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d),
                                10.0d,
                                true),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable13() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new DisplacedLocalVolatilityModel(
                                new HullWhiteLocalVolatilityModel(
                                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true),
                                    10.0d),
                                10.0d,
                                true),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable14() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new ExponentialDecayLocalVolatilityModel(
                                new HullWhiteLocalVolatilityModel(
                                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true),
                                    10.0d),
                                10.0d,
                                true),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    assertTrue(
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable15() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new ExponentialDecayLocalVolatilityModel(
                                new HullWhiteLocalVolatilityModel(
                                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true),
                                    10.0d),
                                10.0d,
                                true),
                            10.0d,
                            false),
                        10.0d),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
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
        new double[] {10.0d, 10.0d, 10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable16() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel, 10.0d, true);
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d), 10.0d, true),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
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
        new double[] {10.0d, 10.0d, 10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable17() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(
                            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                        10.0d,
                        true),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
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
        new double[] {10.0d, 10.0d, 10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable18() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getCloneWithModifiedParameters(Mockito.<RandomVariable[]>any()))
        .thenThrow(new UnsupportedOperationException());
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(
                            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                        10.0d,
                        false),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            blendedLocalVolatilityModel.getCloneWithModifiedParameters(
                new RandomVariable[] {
                  randomVariableFromDoubleArray,
                  randomVariableFromDoubleArray2,
                  new RandomVariableFromDoubleArray(10.0d)
                }));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getCloneWithModifiedParameters(isA(RandomVariable[].class));
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable19() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel, 10.0d, true);
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new BlendedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d),
                            10.0d,
                            true),
                        10.0d,
                        false),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
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
        new double[] {10.0d, 10.0d, 10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * BlendedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable20() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(
                                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true),
                                10.0d),
                            10.0d,
                            true),
                        10.0d,
                        false),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof BlendedLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((BlendedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
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
        new double[] {10.0d, 10.0d, 10.0d}, baseCovarianceModel.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])} with
   * {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlendedLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    RandomVariable[] actualFactorLoading =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true)
            .getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])} with
   * {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlendedLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
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
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        blendedLocalVolatilityModel.getFactorLoading(
            1,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(1.0d)
            });

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertEquals(110.0d, randomVariable.getAverage(), 0.0);
    assertEquals(110.0d, randomVariable.getMax(), 0.0);
    assertEquals(110.0d, randomVariable.getMin(), 0.0);
    assertArrayEquals(new double[] {110.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])} with
   * {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlendedLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
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

    // Act
    RandomVariable[] actualFactorLoading =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(
                            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                        10.0d,
                        true),
                    10.0d),
                10.0d,
                true)
            .getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])} with
   * {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlendedLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable4() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new DisplacedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        blendedLocalVolatilityModel.getFactorLoading(
            1,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(1.0d)
            });

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertEquals(13310.0d, randomVariable.getAverage(), 0.0);
    assertEquals(13310.0d, randomVariable.getMax(), 0.0);
    assertEquals(13310.0d, randomVariable.getMin(), 0.0);
    assertArrayEquals(new double[] {13310.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])} with
   * {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlendedLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable5() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    RandomVariable[] actualFactorLoading =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(
                            new DisplacedLocalVolatilityModel(
                                new HullWhiteLocalVolatilityModel(
                                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true),
                                    10.0d),
                                10.0d,
                                true),
                            10.0d),
                        10.0d,
                        true),
                    10.0d),
                10.0d,
                true)
            .getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(new double[] {10.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])} with
   * {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlendedLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable6() {
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

    // Act
    RandomVariable[] actualFactorLoading =
        new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(
                            new DisplacedLocalVolatilityModel(
                                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d),
                                10.0d,
                                true),
                            10.0d),
                        10.0d,
                        true),
                    10.0d),
                10.0d,
                true)
            .getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertEquals(1.6995729501427983d, randomVariable.getStandardError(), 0.0);
    assertEquals(28.88548212857095d, randomVariable.getVariance(), 0.0);
    assertEquals(32.094980142856606d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(5.374521572063038d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(55.482089436509376d, randomVariable.getMin(), 0.0);
    assertEquals(67.61001788101693d, randomVariable.getAverage(), 0.0);
    assertEquals(76.24108103278316d, randomVariable.getMax(), 0.0);
    assertArrayEquals(
        new double[] {
          66.05125344856359d,
          68.58317181773569d,
          71.53244375827168d,
          65.48248791297712d,
          71.28524763185409d,
          70.62736033119273d,
          55.482089436509376d,
          67.82316762470981d,
          62.99187581557206d,
          76.24108103278316d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])} with
   * {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] BlendedLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable7() {
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
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new DisplacedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new DisplacedLocalVolatilityModel(
                            new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d),
                            10.0d,
                            true),
                        10.0d),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        blendedLocalVolatilityModel.getFactorLoading(
            1,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(1.0d)
            });

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), isA(RandomVariable[].class));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertEquals(1.0072779643063822E14d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(1.1977527088731222E8d, randomVariable.getAverage(), 0.0);
    assertEquals(1.3506572575551835E8d, randomVariable.getMax(), 0.0);
    assertEquals(3010897.1551279263d, randomVariable.getStandardError(), 0.0);
    assertEquals(9.06550167875744E13d, randomVariable.getVariance(), 0.0);
    assertEquals(9.828990584423196E7d, randomVariable.getMin(), 0.0);
    assertEquals(9521292.81072557d, randomVariable.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {
          1.1701382461059073E8d,
          1.2149927244859965E8d,
          1.2672408759684752E8d,
          1.1600622176960166E8d,
          1.2628616457993507E8d,
          1.2512067709568813E8d,
          9.828990584423196E7d,
          1.2015287866039854E8d,
          1.1159395051171066E8d,
          1.3506572575551835E8d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getFactorLoadingPseudoInverse(int, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getFactorLoadingPseudoInverse(int,
   * int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BlendedLocalVolatilityModel.getFactorLoadingPseudoInverse(int, int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingPseudoInverse() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            blendedLocalVolatilityModel.getFactorLoadingPseudoInverse(
                1, 1, 3, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Then return array length is ten.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_thenReturnArrayLengthIsTen()
      throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    ExponentialDecayLocalVolatilityModel covarianceModel2 =
        new ExponentialDecayLocalVolatilityModel(
            randomVariableFactory, covarianceModel, new RandomVariableFromDoubleArray(10.0d), true);
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(
                            new DisplacedLocalVolatilityModel(
                                new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d),
                                10.0d,
                                true),
                            10.0d),
                        10.0d,
                        true),
                    10.0d),
                10.0d,
                true),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        blendedLocalVolatilityModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        actualCloneWithModifiedData.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(10, actualCloneWithModifiedData.getParameter().length);
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Then return array length is twelve.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_thenReturnArrayLengthIsTwelve()
      throws CalculationException {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm7Param covarianceModel =
        new LIBORCovarianceModelExponentialForm7Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);

    ExponentialDecayLocalVolatilityModel covarianceModel2 =
        new ExponentialDecayLocalVolatilityModel(
            randomVariableFactory, covarianceModel, new RandomVariableFromDoubleArray(10.0d), true);
    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        new BlendedLocalVolatilityModel(
            new BlendedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new DisplacedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(
                            new DisplacedLocalVolatilityModel(
                                new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d),
                                10.0d,
                                true),
                            10.0d),
                        10.0d,
                        true),
                    10.0d),
                10.0d,
                true),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        blendedLocalVolatilityModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        actualCloneWithModifiedData.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(12, actualCloneWithModifiedData.getParameter().length);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.1d, 0.1d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link BlendedLocalVolatilityModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then Displacement return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link BlendedLocalVolatilityModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric BlendedLocalVolatilityModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenNull_thenDisplacementReturnScalar()
      throws CalculationException {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true)
            .getCloneWithModifiedData(null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedData instanceof DisplacedLocalVolatilityModel);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedData).getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedData).getDisplacement()
            instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getTimeDiscretization() instanceof TenorFromArray);
    assertSame(covarianceModel2, baseCovarianceModel);
  }
}
