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
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwiseFactory;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DisplacedLocalVolatilityModelDiffblueTest {
  /**
   * Test {@link
   * DisplacedLocalVolatilityModel#DisplacedLocalVolatilityModel(AbstractLIBORCovarianceModelParametric,
   * double, boolean)}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#DisplacedLocalVolatilityModel(AbstractLIBORCovarianceModelParametric,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DisplacedLocalVolatilityModel.<init>(AbstractLIBORCovarianceModelParametric, double, boolean)"
  })
  public void testNewDisplacedLocalVolatilityModel() {
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
    DisplacedLocalVolatilityModel actualDisplacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(covarianceModel2, 10.0d, true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        actualDisplacedLocalVolatilityModel.getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualDisplacedLocalVolatilityModel.getDisplacement() instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        actualDisplacedLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(2, actualDisplacedLocalVolatilityModel.getParameter().length);
    assertEquals(3, actualDisplacedLocalVolatilityModel.getNumberOfFactors());
    assertSame(covarianceModel2, baseCovarianceModel);
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(tenorFromArray2, actualDisplacedLocalVolatilityModel.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        actualDisplacedLocalVolatilityModel.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * DisplacedLocalVolatilityModel#DisplacedLocalVolatilityModel(AbstractLIBORCovarianceModelParametric,
   * RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#DisplacedLocalVolatilityModel(AbstractLIBORCovarianceModelParametric,
   * RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DisplacedLocalVolatilityModel.<init>(AbstractLIBORCovarianceModelParametric, RandomVariable, boolean)"
  })
  public void testNewDisplacedLocalVolatilityModel2() {
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
    RandomVariableFromDoubleArray displacement = new RandomVariableFromDoubleArray(10.0d);

    // Act
    DisplacedLocalVolatilityModel actualDisplacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(covarianceModel2, displacement, true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable displacement2 = actualDisplacedLocalVolatilityModel.getDisplacement();
    assertTrue(displacement2 instanceof RandomVariableFromDoubleArray);
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        actualDisplacedLocalVolatilityModel.getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof HullWhiteLocalVolatilityModel);
    TimeDiscretization liborPeriodDiscretization =
        actualDisplacedLocalVolatilityModel.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(2, actualDisplacedLocalVolatilityModel.getParameter().length);
    assertEquals(3, actualDisplacedLocalVolatilityModel.getNumberOfFactors());
    assertSame(displacement, displacement2);
    assertSame(covarianceModel2, baseCovarianceModel);
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(tenorFromArray2, actualDisplacedLocalVolatilityModel.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        actualDisplacedLocalVolatilityModel.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#clone()}.
   *
   * <ul>
   *   <li>Then return {@link DisplacedLocalVolatilityModel}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DisplacedLocalVolatilityModel.clone()"})
  public void testClone_thenReturnDisplacedLocalVolatilityModel() {
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
        new DisplacedLocalVolatilityModel(covarianceModel, 10.0d, true).clone();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(blendedLocalVolatilityModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(blendedLocalVolatilityModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(blendedLocalVolatilityModel).getTimeDiscretization();
    verify(covarianceModel).clone();
    assertTrue(actualCloneResult instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneResult).getDisplacement() instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        ((DisplacedLocalVolatilityModel) actualCloneResult).getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(1, ((DisplacedLocalVolatilityModel) actualCloneResult).getParameter().length);
    assertEquals(3, ((DisplacedLocalVolatilityModel) actualCloneResult).getNumberOfFactors());
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(
        tenorFromArray2,
        ((DisplacedLocalVolatilityModel) actualCloneResult).getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d},
        ((DisplacedLocalVolatilityModel) actualCloneResult).getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getParameter()}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DisplacedLocalVolatilityModel.getParameter()"})
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
        new DisplacedLocalVolatilityModel(
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
   * Test {@link DisplacedLocalVolatilityModel#getParameter()}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DisplacedLocalVolatilityModel.getParameter()"})
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
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableDifferentiableAADPathwiseFactory(), covarianceModel2, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(covarianceModel3, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    RandomVariable[] actualParameter =
        new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true)
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
   * Test {@link DisplacedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Given {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then return array length is eight.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DisplacedLocalVolatilityModel.getParameter()"})
  public void testGetParameter_givenLocalDateWith1970AndOneAndOne_thenReturnArrayLengthIsEight() {
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
        new HullWhiteLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
            10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    RandomVariable[] actualParameter =
        new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true)
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
   * Test {@link DisplacedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then first element abs abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DisplacedLocalVolatilityModel.getParameter()"})
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
        new DisplacedLocalVolatilityModel(
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
   * Test {@link DisplacedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then fourth element abs return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DisplacedLocalVolatilityModel.getParameter()"})
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
        new DisplacedLocalVolatilityModel(
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
   * Test {@link DisplacedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then ninth element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DisplacedLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenNinthElementReturnScalar() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    RandomVariableFloatFactory randomVariableFactory2 = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    BlendedLocalVolatilityModel covarianceModel2 =
        new BlendedLocalVolatilityModel(
            randomVariableFactory2,
            new DisplacedLocalVolatilityModel(covarianceModel, 10.0d, true),
            10.0d,
            true);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            randomVariableFactory,
            new HullWhiteLocalVolatilityModel(
                new DisplacedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d),
            10.0d,
            true);

    // Act
    RandomVariable[] actualParameter =
        new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true)
            .getParameter();

    // Assert
    assertTrue(actualParameter[7] instanceof Scalar);
    assertTrue(actualParameter[8] instanceof Scalar);
    assertTrue(actualParameter[9] instanceof Scalar);
    assertEquals(10, actualParameter.length);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is eight.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DisplacedLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenReturnArrayLengthIsEight() {
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
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(covarianceModel3, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    RandomVariable[] actualParameter =
        new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true)
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
   * Test {@link DisplacedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is five.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DisplacedLocalVolatilityModel.getParameter()"})
  public void testGetParameter_thenReturnArrayLengthIsFive() {
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
    RandomVariable[] actualParameter =
        new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true)
            .getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[2] instanceof Scalar);
    assertTrue(actualParameter[4] instanceof Scalar);
    assertEquals(5, actualParameter.length);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is seven.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DisplacedLocalVolatilityModel.getParameter()"})
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
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(covarianceModel3, 10.0d, false), 10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    RandomVariable[] actualParameter =
        new DisplacedLocalVolatilityModel(
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
   * Test {@link DisplacedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is seven.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DisplacedLocalVolatilityModel.getParameter()"})
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
    DisplacedLocalVolatilityModel covarianceModel2 =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(covarianceModel3, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    RandomVariable[] actualParameter =
        new DisplacedLocalVolatilityModel(
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
   * Test {@link DisplacedLocalVolatilityModel#getParameter()}.
   *
   * <ul>
   *   <li>Then second element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DisplacedLocalVolatilityModel.getParameter()"})
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
        new DisplacedLocalVolatilityModel(
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
   * Test {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLocalVolatilityModel.getParameterAsDouble()"})
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
        new DisplacedLocalVolatilityModel(
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
   * Test {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLocalVolatilityModel.getParameterAsDouble()"})
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
        new DisplacedLocalVolatilityModel(
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
   * Test {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLocalVolatilityModel.getParameterAsDouble()"})
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
        new DisplacedLocalVolatilityModel(
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
   * Test {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLocalVolatilityModel.getParameterAsDouble()"})
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
        new DisplacedLocalVolatilityModel(
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
   * Test {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLocalVolatilityModel.getParameterAsDouble()"})
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
    double[] actualParameterAsDouble =
        new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true)
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
   * Test {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLocalVolatilityModel.getParameterAsDouble()"})
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
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(covarianceModel3, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    double[] actualParameterAsDouble =
        new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true)
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
          10.0d
        },
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble7() {
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
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(covarianceModel3, 10.0d, false), 10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    double[] actualParameterAsDouble =
        new DisplacedLocalVolatilityModel(
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
   * Test {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble8() {
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            covarianceModel2, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    double[] actualParameterAsDouble = displacedLocalVolatilityModel.getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble9() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    DisplacedLocalVolatilityModel covarianceModel2 =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            covarianceModel2, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    double[] actualParameterAsDouble = displacedLocalVolatilityModel.getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble10() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    DisplacedLocalVolatilityModel covarianceModel2 =
        new DisplacedLocalVolatilityModel(covarianceModel, 10.0d, true);
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            covarianceModel2, new RandomVariableFromDoubleArray(10.0d), true);

    // Act and Assert
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d, 10.0d, 10.0d},
        displacedLocalVolatilityModel.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble11() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    DisplacedLocalVolatilityModel covarianceModel2 =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            covarianceModel2, new RandomVariableFromDoubleArray(10.0d), false);

    // Act
    double[] actualParameterAsDouble = displacedLocalVolatilityModel.getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLocalVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble12() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    DisplacedLocalVolatilityModel covarianceModel2 =
        new DisplacedLocalVolatilityModel(covarianceModel, 10.0d, false);
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            covarianceModel2, new RandomVariableFromDoubleArray(10.0d), true);

    // Act and Assert
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d, 10.0d},
        displacedLocalVolatilityModel.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}.
   *
   * <ul>
   *   <li>Given {@link LocalDate} with {@code 1970} and one and one.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DisplacedLocalVolatilityModel.getParameterAsDouble()"})
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
        new HullWhiteLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
            10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    double[] actualParameterAsDouble =
        new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true)
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
          10.0d
        },
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
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
            new DisplacedLocalVolatilityModel(
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
        new DisplacedLocalVolatilityModel(
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
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble2() {
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
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(covarianceModel2, forwardCurve, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
            10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d), 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(6, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
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
            new DisplacedLocalVolatilityModel(
                new DisplacedLocalVolatilityModel(
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
        new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d), 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
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
            new DisplacedLocalVolatilityModel(
                new DisplacedLocalVolatilityModel(
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
        new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d), 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
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
        new DisplacedLocalVolatilityModel(covarianceModel, 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <ul>
   *   <li>Then return array length is five.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
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
            new DisplacedLocalVolatilityModel(
                new DisplacedLocalVolatilityModel(
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
        new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <ul>
   *   <li>Then return array length is four.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
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
        new DisplacedLocalVolatilityModel(
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
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(4, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <ul>
   *   <li>Then return array length is six.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
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
            new DisplacedLocalVolatilityModel(
                new DisplacedLocalVolatilityModel(
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
        new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d), 10.0d, true)
            .getCloneWithModifiedParameters(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(6, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])} with {@code
   * double[]}.
   *
   * <ul>
   *   <li>Then return array length is three.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(double[])"
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
        new DisplacedLocalVolatilityModel(
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
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(3, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        displacedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(2, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new DisplacedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(
                        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                    10.0d,
                    true),
                10.0d),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        displacedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(3, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(covarianceModel3, 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        displacedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    AbstractLIBORCovarianceModelParametric baseCovarianceModel =
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters)
            .getBaseCovarianceModel();
    assertTrue(baseCovarianceModel instanceof BlendedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertSame(covarianceModel3, baseCovarianceModel);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable4() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        displacedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(3, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable5() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true),
            10.0d,
            false);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        displacedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getDisplacement()
            instanceof Scalar);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(2, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable6() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
                10.0d,
                true),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        displacedLocalVolatilityModel.getCloneWithModifiedParameters((RandomVariable[]) null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertSame(displacedLocalVolatilityModel, actualCloneWithModifiedParameters);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(
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
            displacedLocalVolatilityModel.getCloneWithModifiedParameters(
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
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable8() {
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        displacedLocalVolatilityModel.getCloneWithModifiedParameters(
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
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(4, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable9() {
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, false),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        displacedLocalVolatilityModel.getCloneWithModifiedParameters(
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
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(4, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable10() {
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
            10.0d,
            true);
    Scalar ofResult = Scalar.of(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        displacedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              ofResult, randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable11() {
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
            10.0d,
            true);
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        displacedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromFloatArray,
              randomVariableFromDoubleArray,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable12() {
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(covarianceModel, 10.0d, true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        displacedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof LIBORCovarianceModelFromVolatilityAndCorrelation);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then return array length is five.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable_thenReturnArrayLengthIsFive() {
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d), 10.0d, true),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        displacedLocalVolatilityModel.getCloneWithModifiedParameters(
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
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedParameters).getBaseCovarianceModel()
            instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedParameters.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(5, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * DisplacedLocalVolatilityModel#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable_thenReturnArrayLengthIsOne() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d), 10.0d, true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        displacedLocalVolatilityModel.getCloneWithModifiedParameters(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualCloneWithModifiedParameters instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(1, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])} with
   * {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] DisplacedLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        displacedLocalVolatilityModel.getFactorLoading(
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
    assertEquals(1210.0d, randomVariable.getAverage(), 0.0);
    assertEquals(1210.0d, randomVariable.getMax(), 0.0);
    assertEquals(1210.0d, randomVariable.getMin(), 0.0);
    assertArrayEquals(new double[] {1210.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])} with
   * {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] DisplacedLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new DisplacedLocalVolatilityModel(
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
        displacedLocalVolatilityModel.getFactorLoading(
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
    assertEquals(26620.0d, randomVariable.getAverage(), 0.0);
    assertEquals(26620.0d, randomVariable.getMax(), 0.0);
    assertEquals(26620.0d, randomVariable.getMin(), 0.0);
    assertArrayEquals(new double[] {26620.0d}, randomVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])} with
   * {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] DisplacedLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new DisplacedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(covarianceModel3, 1.0d), 10.0d, true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        displacedLocalVolatilityModel.getFactorLoading(
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
    assertEquals(157376.74067314985d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(1624626.5428798674d, randomVariable.getMin(), 0.0);
    assertEquals(1979756.5435919375d, randomVariable.getAverage(), 0.0);
    assertEquals(2.476743850490386E10d, randomVariable.getVariance(), 0.0);
    assertEquals(2.751937611655984E10d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(2232491.334801956d, randomVariable.getMax(), 0.0);
    assertEquals(49766.89512608141d, randomVariable.getStandardError(), 0.0);
    assertArrayEquals(
        new double[] {
          1934112.8034808387d,
          2008252.4371669362d,
          2094613.0181297113d,
          1917458.211067796d,
          2087374.6211559514d,
          2068110.3652179856d,
          1624626.5428798674d,
          1985997.9943867528d,
          1844528.107631581d,
          2232491.334801956d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getFactorLoading(int, int, RandomVariable[])} with
   * {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getFactorLoading(int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] DisplacedLocalVolatilityModel.getFactorLoading(int, int, RandomVariable[])"
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
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new DisplacedLocalVolatilityModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d),
                10.0d,
                true),
            10.0d);
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new DisplacedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(covarianceModel3, 1.0d), 10.0d, true),
                10.0d),
            10.0d,
            true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        displacedLocalVolatilityModel.getFactorLoading(
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
    assertEquals(1.198744023637348E13d, randomVariable.getVariance(), 0.0);
    assertEquals(1.3319378040414979E13d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(1094871.6927737915d, randomVariable.getStandardError(), 0.0);
    assertEquals(3.574178394335709E7d, randomVariable.getMin(), 0.0);
    assertEquals(3462288.2948092986d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(4.355464395902263E7d, randomVariable.getAverage(), 0.0);
    assertEquals(4.9114809365643054E7d, randomVariable.getMax(), 0.0);
    assertArrayEquals(
        new double[] {
          4.255048167657845E7d,
          4.41815536176726E7d,
          4.6081486398853645E7d,
          4.2184080643491514E7d,
          4.592224166543093E7d,
          4.549842803479569E7d,
          3.574178394335709E7d,
          4.3691955876508564E7d,
          4.057961836789478E7d,
          4.9114809365643054E7d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getFactorLoadingPseudoInverse(int, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getFactorLoadingPseudoInverse(int,
   * int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DisplacedLocalVolatilityModel.getFactorLoadingPseudoInverse(int, int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingPseudoInverse() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d),
            10.0d,
            true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            displacedLocalVolatilityModel.getFactorLoadingPseudoInverse(
                1, 1, 3, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Then return array length is eight.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_thenReturnArrayLengthIsEight()
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new DisplacedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d), 10.0d, true),
                10.0d),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        displacedLocalVolatilityModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedData).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedData).getDisplacement()
            instanceof Scalar);
    assertTrue(
        actualCloneWithModifiedData.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(8, actualCloneWithModifiedData.getParameter().length);
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Then return array length is ten.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_thenReturnArrayLengthIsTen()
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
    DisplacedLocalVolatilityModel displacedLocalVolatilityModel =
        new DisplacedLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new DisplacedLocalVolatilityModel(
                    new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d), 10.0d, true),
                10.0d),
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        displacedLocalVolatilityModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof DisplacedLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedData).getBaseCovarianceModel()
            instanceof HullWhiteLocalVolatilityModel);
    assertTrue(
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedData).getDisplacement()
            instanceof Scalar);
    assertTrue(
        actualCloneWithModifiedData.getLiborPeriodDiscretization() instanceof TenorFromArray);
    assertEquals(10, actualCloneWithModifiedData.getParameter().length);
    assertArrayEquals(
        new double[] {0.1d, 0.1d, 0.1d, 0.2d, 0.1d, 0.1d, 0.1d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link DisplacedLocalVolatilityModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link DisplacedLocalVolatilityModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric DisplacedLocalVolatilityModel.getCloneWithModifiedData(Map)"
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
        new DisplacedLocalVolatilityModel(covarianceModel2, 10.0d, true)
            .getCloneWithModifiedData(null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedData instanceof DisplacedLocalVolatilityModel);
    assertTrue(actualCloneWithModifiedData.getTimeDiscretization() instanceof TenorFromArray);
    assertEquals(2, actualCloneWithModifiedData.getParameter().length);
    assertSame(
        covarianceModel2,
        ((DisplacedLocalVolatilityModel) actualCloneWithModifiedData).getBaseCovarianceModel());
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedData.getParameterAsDouble(), 0.0);
  }
}
