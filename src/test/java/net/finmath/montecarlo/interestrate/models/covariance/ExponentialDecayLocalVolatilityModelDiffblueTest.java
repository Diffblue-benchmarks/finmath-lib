package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
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
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromArrayFactory;
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

public class ExponentialDecayLocalVolatilityModelDiffblueTest {
  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#ExponentialDecayLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, double, boolean)}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#ExponentialDecayLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExponentialDecayLocalVolatilityModel.<init>(RandomVariableFactory, AbstractLIBORCovarianceModelParametric, double, boolean)"
  })
  public void testNewExponentialDecayLocalVolatilityModel() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    ExponentialDecayLocalVolatilityModel actualExponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            randomVariableFactory,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        actualExponentialDecayLocalVolatilityModel.getDisplacement()
            instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualExponentialDecayLocalVolatilityModel.getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualExponentialDecayLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualExponentialDecayLocalVolatilityModel.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, actualExponentialDecayLocalVolatilityModel.getParameter().length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        actualExponentialDecayLocalVolatilityModel.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#ExponentialDecayLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, double, boolean)}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#ExponentialDecayLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExponentialDecayLocalVolatilityModel.<init>(RandomVariableFactory, AbstractLIBORCovarianceModelParametric, double, boolean)"
  })
  public void testNewExponentialDecayLocalVolatilityModel2() {
    // Arrange
    RandomVariableFromArrayFactory randomVariableFactory = new RandomVariableFromArrayFactory(true);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    ExponentialDecayLocalVolatilityModel actualExponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            randomVariableFactory,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        actualExponentialDecayLocalVolatilityModel.getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualExponentialDecayLocalVolatilityModel.getDisplacement() instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        actualExponentialDecayLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualExponentialDecayLocalVolatilityModel.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, actualExponentialDecayLocalVolatilityModel.getParameter().length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        actualExponentialDecayLocalVolatilityModel.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#ExponentialDecayLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, double, boolean)}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#ExponentialDecayLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExponentialDecayLocalVolatilityModel.<init>(RandomVariableFactory, AbstractLIBORCovarianceModelParametric, double, boolean)"
  })
  public void testNewExponentialDecayLocalVolatilityModel3() {
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
    ExponentialDecayLocalVolatilityModel actualExponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            randomVariableFactory,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        actualExponentialDecayLocalVolatilityModel.getDisplacement()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        actualExponentialDecayLocalVolatilityModel.getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualExponentialDecayLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualExponentialDecayLocalVolatilityModel.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, actualExponentialDecayLocalVolatilityModel.getParameter().length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        actualExponentialDecayLocalVolatilityModel.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#ExponentialDecayLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#ExponentialDecayLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExponentialDecayLocalVolatilityModel.<init>(RandomVariableFactory, AbstractLIBORCovarianceModelParametric, RandomVariable, boolean)"
  })
  public void testNewExponentialDecayLocalVolatilityModel4() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();

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
    RandomVariableFromDoubleArray decay = new RandomVariableFromDoubleArray(10.0d);

    // Act
    ExponentialDecayLocalVolatilityModel actualExponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            randomVariableFactory, covarianceModel2, decay, true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable displacement = actualExponentialDecayLocalVolatilityModel.getDisplacement();
    assertTrue(displacement instanceof RandomVariableFromDoubleArray);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        actualExponentialDecayLocalVolatilityModel.getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualExponentialDecayLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(2, actualExponentialDecayLocalVolatilityModel.getParameter().length);
    assertEquals(3, actualExponentialDecayLocalVolatilityModel.getNumberOfFactors());
    assertSame(decay, displacement);
    assertSame(covarianceModel2, baseCovarianceModel);
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(tenorFromArray2, actualExponentialDecayLocalVolatilityModel.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        actualExponentialDecayLocalVolatilityModel.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#ExponentialDecayLocalVolatilityModel(AbstractLIBORCovarianceModelParametric,
   * double, boolean)}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#ExponentialDecayLocalVolatilityModel(AbstractLIBORCovarianceModelParametric,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExponentialDecayLocalVolatilityModel.<init>(AbstractLIBORCovarianceModelParametric, double, boolean)"
  })
  public void testNewExponentialDecayLocalVolatilityModel5() {
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

    // Act
    ExponentialDecayLocalVolatilityModel actualExponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(covarianceModel2, 10.0d, true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        actualExponentialDecayLocalVolatilityModel.getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualExponentialDecayLocalVolatilityModel.getDisplacement() instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        actualExponentialDecayLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(2, actualExponentialDecayLocalVolatilityModel.getParameter().length);
    assertEquals(3, actualExponentialDecayLocalVolatilityModel.getNumberOfFactors());
    assertSame(covarianceModel2, baseCovarianceModel);
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(tenorFromArray2, actualExponentialDecayLocalVolatilityModel.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        actualExponentialDecayLocalVolatilityModel.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#ExponentialDecayLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, double, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#ExponentialDecayLocalVolatilityModel(RandomVariableFactory,
   * AbstractLIBORCovarianceModelParametric, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExponentialDecayLocalVolatilityModel.<init>(RandomVariableFactory, AbstractLIBORCovarianceModelParametric, double, boolean)"
  })
  public void testNewExponentialDecayLocalVolatilityModel_whenNull() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    ExponentialDecayLocalVolatilityModel actualExponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            null,
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        actualExponentialDecayLocalVolatilityModel.getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualExponentialDecayLocalVolatilityModel.getDisplacement() instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        actualExponentialDecayLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualExponentialDecayLocalVolatilityModel.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, actualExponentialDecayLocalVolatilityModel.getParameter().length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        actualExponentialDecayLocalVolatilityModel.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#clone()}.
   *
   * <ul>
   *   <li>Then return {@link ExponentialDecayLocalVolatilityModel}.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ExponentialDecayLocalVolatilityModel.clone()"})
  public void testClone_thenReturnExponentialDecayLocalVolatilityModel() {
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
    Object actualCloneResult =
        new ExponentialDecayLocalVolatilityModel(covarianceModel, 10.0d, true).clone();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(blendedLocalVolatilityModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(blendedLocalVolatilityModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(blendedLocalVolatilityModel).getTimeDiscretization();
    verify(covarianceModel).clone();
    assertTrue(actualCloneResult instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneResult).getDisplacement()
            instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        ((ExponentialDecayLocalVolatilityModel) actualCloneResult).getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(
        1, ((ExponentialDecayLocalVolatilityModel) actualCloneResult).getParameter().length);
    assertEquals(
        3, ((ExponentialDecayLocalVolatilityModel) actualCloneResult).getNumberOfFactors());
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(
        tenorFromArray2,
        ((ExponentialDecayLocalVolatilityModel) actualCloneResult).getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d},
        ((ExponentialDecayLocalVolatilityModel) actualCloneResult).getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameter()}.
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ExponentialDecayLocalVolatilityModel.getParameter()"})
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
        new ExponentialDecayLocalVolatilityModel(
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
    RandomVariable absResult = randomVariable.abs();
    assertTrue(absResult.abs() instanceof Scalar);
    assertTrue(absResult instanceof Scalar);
    assertTrue(absResult.cos() instanceof Scalar);
    assertTrue(absResult.exp() instanceof Scalar);
    assertTrue(absResult.expm1() instanceof Scalar);
    assertTrue(absResult.invert() instanceof Scalar);
    assertTrue(absResult.isNaN() instanceof Scalar);
    assertTrue(absResult.sin() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(3, actualParameter.length);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameter()}.
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ExponentialDecayLocalVolatilityModel.getParameter()"})
  public void testGetParameter2() {
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
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(covarianceModel2, forwardCurve, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    RandomVariable[] actualParameter =
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, false)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[2] instanceof Scalar);
    assertTrue(actualParameter[4] instanceof Scalar);
    assertTrue(actualParameter[5] instanceof Scalar);
    assertEquals(6, actualParameter.length);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then eighth element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ExponentialDecayLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenEighthElementReturnScalar() {
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
    ExponentialDecayLocalVolatilityModel covarianceModel3 =
        new ExponentialDecayLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    ExponentialDecayLocalVolatilityModel covarianceModel4 =
        new ExponentialDecayLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
                10.0d),
            10.0d,
            true);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(covarianceModel4, forwardCurve, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel6 =
        new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d);
    BlendedLocalVolatilityModel covarianceModel7 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel6, 10.0d, true);

    // Act
    RandomVariable[] actualParameter =
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel7, 10.0d), 10.0d, true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertTrue(actualParameter[4] instanceof Scalar);
    assertTrue(actualParameter[5] instanceof Scalar);
    assertTrue(actualParameter[6] instanceof Scalar);
    assertTrue(actualParameter[7] instanceof Scalar);
    assertEquals(8, actualParameter.length);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then eleventh element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ExponentialDecayLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenEleventhElementReturnScalar() {
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
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    ExponentialDecayLocalVolatilityModel covarianceModel5 =
        new ExponentialDecayLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);
    ExponentialDecayLocalVolatilityModel covarianceModel6 =
        new ExponentialDecayLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true),
                10.0d),
            10.0d,
            true);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    BlendedLocalVolatilityModel covarianceModel7 =
        new BlendedLocalVolatilityModel(covarianceModel6, forwardCurve, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel8 =
        new HullWhiteLocalVolatilityModel(covarianceModel7, 10.0d);
    BlendedLocalVolatilityModel covarianceModel9 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel8, 10.0d, true);

    // Act
    RandomVariable[] actualParameter =
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel9, 10.0d), 10.0d, true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertTrue(actualParameter[10] instanceof Scalar);
    assertTrue(actualParameter[8] instanceof Scalar);
    assertTrue(actualParameter[9] instanceof Scalar);
    assertEquals(11, actualParameter.length);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then first element abs abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ExponentialDecayLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenFirstElementAbsAbsReturnScalar() {
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
        new ExponentialDecayLocalVolatilityModel(
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
    RandomVariable absResult = randomVariable.abs();
    assertTrue(absResult.abs() instanceof Scalar);
    assertTrue(absResult instanceof Scalar);
    assertTrue(absResult.cos() instanceof Scalar);
    assertTrue(absResult.exp() instanceof Scalar);
    assertTrue(absResult.expm1() instanceof Scalar);
    assertTrue(absResult.invert() instanceof Scalar);
    assertTrue(absResult.isNaN() instanceof Scalar);
    assertTrue(absResult.sin() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(3, actualParameter.length);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then fourth element abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ExponentialDecayLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenFourthElementAbsReturnScalar() {
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
        new ExponentialDecayLocalVolatilityModel(
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
    RandomVariable randomVariable = actualParameter[3];
    assertTrue(randomVariable.abs() instanceof Scalar);
    assertTrue(randomVariable.cos() instanceof Scalar);
    assertTrue(randomVariable.exp() instanceof Scalar);
    assertTrue(randomVariable.expm1() instanceof Scalar);
    assertTrue(randomVariable.invert() instanceof Scalar);
    assertTrue(randomVariable.isNaN() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(4, actualParameter.length);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then fourth element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ExponentialDecayLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenFourthElementReturnScalar() {
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
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[2] instanceof Scalar);
    assertTrue(actualParameter[3] instanceof Scalar);
    assertEquals(4, actualParameter.length);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is seven.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ExponentialDecayLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenReturnArrayLengthIsSeven() {
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
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(covarianceModel2, forwardCurve, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    RandomVariable[] actualParameter =
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertTrue(actualParameter[4] instanceof Scalar);
    assertTrue(actualParameter[5] instanceof Scalar);
    assertTrue(actualParameter[6] instanceof Scalar);
    assertEquals(7, actualParameter.length);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is seven.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ExponentialDecayLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenReturnArrayLengthIsSeven2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    ExponentialDecayLocalVolatilityModel covarianceModel2 =
        new ExponentialDecayLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(covarianceModel2, forwardCurve, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    RandomVariable[] actualParameter =
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertTrue(actualParameter[4] instanceof Scalar);
    assertTrue(actualParameter[5] instanceof Scalar);
    assertTrue(actualParameter[6] instanceof Scalar);
    assertEquals(7, actualParameter.length);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is six.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ExponentialDecayLocalVolatilityModel.getParameter()"})
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
    ExponentialDecayLocalVolatilityModel covarianceModel2 =
        new ExponentialDecayLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(covarianceModel2, forwardCurve, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    RandomVariable[] actualParameter =
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[2] instanceof Scalar);
    assertTrue(actualParameter[4] instanceof Scalar);
    assertTrue(actualParameter[5] instanceof Scalar);
    assertEquals(6, actualParameter.length);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ExponentialDecayLocalVolatilityModel.getParameterAsDouble()"})
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
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
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
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ExponentialDecayLocalVolatilityModel.getParameterAsDouble()"})
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
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
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
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ExponentialDecayLocalVolatilityModel.getParameterAsDouble()"})
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
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
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
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ExponentialDecayLocalVolatilityModel.getParameterAsDouble()"})
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
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true)
            .getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ExponentialDecayLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble5() {
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
        new ExponentialDecayLocalVolatilityModel(covarianceModel3, 10.0d, true)
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
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ExponentialDecayLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble6() {
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
        new ExponentialDecayLocalVolatilityModel(covarianceModel3, 10.0d, false)
            .getParameterAsDouble();

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
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <ul>
   *   <li>Given {@link LocalDate} with {@code 1970} and one and one.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ExponentialDecayLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble_givenLocalDateWith1970AndOneAndOne() {
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
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(covarianceModel2, forwardCurve, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    double[] actualParameterAsDouble =
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true)
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
   * Test {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ExponentialDecayLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble_givenRandomVariableFromDoubleArrayWithValueIsTen() {
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

    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            randomVariableFactory,
            covarianceModel2,
            new RandomVariableFromDoubleArray(10.0d),
            true);

    // Act
    double[] actualParameterAsDouble = exponentialDecayLocalVolatilityModel.getParameterAsDouble();

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
   * Test {@link ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
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
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d),
                10.0d,
                true),
            10.0d);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel3, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d), 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel2).getCloneWithModifiedParameters(isA(RandomVariable[].class));
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble2() {
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
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(covarianceModel2, forwardCurve, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
            10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(6, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble3() {
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
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d), 10.0d, true),
                    10.0d,
                    false),
                10.0d,
                true),
            10.0d);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel3, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d), 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble4() {
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
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d), 10.0d, true),
                    10.0d,
                    true),
                10.0d,
                false),
            10.0d);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel3, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d), 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble5() {
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
        new ExponentialDecayLocalVolatilityModel(covarianceModel, 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <ul>
   *   <li>Then return array length is five.
   * </ul>
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble_thenReturnArrayLengthIsFive() {
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
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                    10.0d,
                    true),
                10.0d,
                true),
            10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <ul>
   *   <li>Then return array length is four.
   * </ul>
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble_thenReturnArrayLengthIsFour() {
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
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d), 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel2).getCloneWithModifiedParameters(isA(RandomVariable[].class));
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(4, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <ul>
   *   <li>Then return array length is six.
   * </ul>
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble_thenReturnArrayLengthIsSix() {
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
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(
                        new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d), 10.0d, true),
                    10.0d,
                    true),
                10.0d,
                true),
            10.0d);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel3, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d), 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(6, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <ul>
   *   <li>Then return array length is three.
   * </ul>
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble_thenReturnArrayLengthIsThree() {
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
        new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d),
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
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(3, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        exponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(2, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
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
        exponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(3, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable3() {
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
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(covarianceModel3, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        exponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
            .getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertSame(covarianceModel3, baseCovarianceModel);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable4() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        exponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(3, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable5() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true),
            10.0d,
            false);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        exponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters).getDisplacement()
            instanceof Scalar);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(2, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable6() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        exponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(
            (RandomVariable[]) null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertSame(exponentialDecayLocalVolatilityModel, actualCloneWithModifiedParameters);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable7() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getCloneWithModifiedParameters(Mockito.<RandomVariable[]>any()))
        .thenThrow(new UnsupportedOperationException());
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true),
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
            exponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(
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
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable8() {
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
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(covarianceModel, 10.0d, true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        exponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then return array length is five.
   * </ul>
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable_thenReturnArrayLengthIsFive() {
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
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        exponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then return array length is four.
   * </ul>
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable_thenReturnArrayLengthIsFour() {
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
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            new ExponentialDecayLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        exponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedParameters)
                .getBaseCovarianceModel()
            instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(4, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable_thenReturnArrayLengthIsOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d), 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        exponentialDecayLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualCloneWithModifiedParameters instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(1, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])}
   * with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ExponentialDecayLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
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
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        exponentialDecayLocalVolatilityModel.getFactorLoading(
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
   * Test {@link ExponentialDecayLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])}
   * with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ExponentialDecayLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
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
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        exponentialDecayLocalVolatilityModel.getFactorLoading(
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
    assertEquals(220.0d, randomVariable.getAverage(), 0.0);
    assertEquals(220.0d, randomVariable.getMax(), 0.0);
    assertEquals(220.0d, randomVariable.getMin(), 0.0);
    assertArrayEquals(new double[] {220.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])}
   * with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ExponentialDecayLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(covarianceModel, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        exponentialDecayLocalVolatilityModel.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromDoubleArray);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])}
   * with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ExponentialDecayLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable_thenFirstElementReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(covarianceModel, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        exponentialDecayLocalVolatilityModel.getFactorLoading(
            1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertTrue(actualFactorLoading[1] instanceof Scalar);
    assertTrue(actualFactorLoading[2] instanceof Scalar);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])}
   * with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link ExponentialDecayLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] ExponentialDecayLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable_thenReturnArrayLengthIsOne() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    ExponentialDecayLocalVolatilityModel covarianceModel2 =
        new ExponentialDecayLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d),
            10.0d,
            true);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 1.0d, true);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    LIBORCovarianceModelStochasticHestonVolatility covarianceModel4 =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel3,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(covarianceModel4, 1.0d), 10.0d, true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        exponentialDecayLocalVolatilityModel.getFactorLoading(
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
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getFactorLoadingPseudoInverse(int, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getFactorLoadingPseudoInverse(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ExponentialDecayLocalVolatilityModel.getFactorLoadingPseudoInverse(int, int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingPseudoInverse() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            exponentialDecayLocalVolatilityModel.getFactorLoadingPseudoInverse(
                1, 1, 3, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Then LiborPeriodDiscretization return {@link TenorFromArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_thenLiborPeriodDiscretizationReturnTenorFromArray()
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
    ExponentialDecayLocalVolatilityModel exponentialDecayLocalVolatilityModel =
        new ExponentialDecayLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new ExponentialDecayLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d), 10.0d, true),
                10.0d),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        exponentialDecayLocalVolatilityModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(
        actualCloneWithModifiedData.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(8, actualCloneWithModifiedData.getParameter().length);
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link ExponentialDecayLocalVolatilityModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * ExponentialDecayLocalVolatilityModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric ExponentialDecayLocalVolatilityModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenNull_thenReturnArrayLengthIsTwo()
      throws CalculationException {
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

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        new ExponentialDecayLocalVolatilityModel(covarianceModel2, 10.0d, true)
            .getCloneWithModifiedData(null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedData instanceof ExponentialDecayLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedData.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(2, actualCloneWithModifiedData.getParameter().length);
    assertSame(
        covarianceModel2,
        ((ExponentialDecayLocalVolatilityModel) actualCloneWithModifiedData)
            .getBaseCovarianceModel());
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedData.getParameterAsDouble(), 0.0);
  }
}
