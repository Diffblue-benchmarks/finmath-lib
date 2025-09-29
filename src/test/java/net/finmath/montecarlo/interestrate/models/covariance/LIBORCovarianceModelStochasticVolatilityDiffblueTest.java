package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.RandomVariableLazyEvaluation;
import net.finmath.montecarlo.RandomVariableLazyEvaluationFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwiseFactory;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class LIBORCovarianceModelStochasticVolatilityDiffblueTest {
  /**
   * Test {@link
   * LIBORCovarianceModelStochasticVolatility#LIBORCovarianceModelStochasticVolatility(AbstractLIBORCovarianceModelParametric,
   * BrownianMotion, double, double, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticVolatility#LIBORCovarianceModelStochasticVolatility(AbstractLIBORCovarianceModelParametric,
   * BrownianMotion, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCovarianceModelStochasticVolatility.<init>(AbstractLIBORCovarianceModelParametric, BrownianMotion, double, double, boolean)"
  })
  public void testNewLIBORCovarianceModelStochasticVolatility() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    // Act
    LIBORCovarianceModelStochasticVolatility actualLiborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    TimeDiscretization liborPeriodDiscretization =
        actualLiborCovarianceModelStochasticVolatility.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(3, actualLiborCovarianceModelStochasticVolatility.getNumberOfFactors());
    assertEquals(3, actualLiborCovarianceModelStochasticVolatility.getParameter().length);
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(
        tenorFromArray2, actualLiborCovarianceModelStochasticVolatility.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualLiborCovarianceModelStochasticVolatility.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelStochasticVolatility#LIBORCovarianceModelStochasticVolatility(AbstractLIBORCovarianceModelParametric,
   * BrownianMotion, RandomVariable, RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticVolatility#LIBORCovarianceModelStochasticVolatility(AbstractLIBORCovarianceModelParametric,
   * BrownianMotion, RandomVariable, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCovarianceModelStochasticVolatility.<init>(AbstractLIBORCovarianceModelParametric, BrownianMotion, RandomVariable, RandomVariable, boolean)"
  })
  public void testNewLIBORCovarianceModelStochasticVolatility2() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariableFromDoubleArray nu = new RandomVariableFromDoubleArray(10.0d);

    // Act
    LIBORCovarianceModelStochasticVolatility actualLiborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, nu, new RandomVariableFromDoubleArray(10.0d), true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    TimeDiscretization liborPeriodDiscretization =
        actualLiborCovarianceModelStochasticVolatility.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(3, actualLiborCovarianceModelStochasticVolatility.getNumberOfFactors());
    assertEquals(3, actualLiborCovarianceModelStochasticVolatility.getParameter().length);
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(
        tenorFromArray2, actualLiborCovarianceModelStochasticVolatility.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualLiborCovarianceModelStochasticVolatility.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameter()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORCovarianceModelStochasticVolatility.getParameter()"})
  public void testGetParameter() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    RandomVariable[] actualParameter = liborCovarianceModelStochasticVolatility.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    RandomVariable randomVariable = actualParameter[3];
    assertTrue(randomVariable.isNaN() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(4, actualParameter.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameter()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORCovarianceModelStochasticVolatility.getParameter()"})
  public void testGetParameter2() {
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
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    RandomVariable[] actualParameter = liborCovarianceModelStochasticVolatility.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertTrue(actualParameter[0] instanceof Scalar);
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[2] instanceof Scalar);
    assertTrue(actualParameter[4] instanceof Scalar);
    assertEquals(5, actualParameter.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameter()}.
   *
   * <ul>
   *   <li>Then fourth element NaN return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORCovarianceModelStochasticVolatility.getParameter()"})
  public void testGetParameter_thenFourthElementNaNReturnScalar() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    RandomVariable[] actualParameter = liborCovarianceModelStochasticVolatility.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    RandomVariable randomVariable = actualParameter[3];
    assertTrue(randomVariable.isNaN() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(4, actualParameter.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is five.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORCovarianceModelStochasticVolatility.getParameter()"})
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
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel4,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    RandomVariable[] actualParameter = liborCovarianceModelStochasticVolatility.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertTrue(actualParameter[0] instanceof Scalar);
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[2] instanceof Scalar);
    assertTrue(actualParameter[4] instanceof Scalar);
    assertEquals(5, actualParameter.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameter()}.
   *
   * <ul>
   *   <li>Then return array length is six.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORCovarianceModelStochasticVolatility.getParameter()"})
  public void testGetParameter_thenReturnArrayLengthIsSix() {
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
    HullWhiteLocalVolatilityModel covarianceModel6 =
        new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel6,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            false);

    // Act
    RandomVariable[] actualParameter = liborCovarianceModelStochasticVolatility.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameterAsDouble();
    assertTrue(actualParameter[0] instanceof Scalar);
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[2] instanceof Scalar);
    assertTrue(actualParameter[4] instanceof Scalar);
    assertTrue(actualParameter[5] instanceof Scalar);
    assertEquals(6, actualParameter.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameter()}.
   *
   * <ul>
   *   <li>Then seventh element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] LIBORCovarianceModelStochasticVolatility.getParameter()"})
  public void testGetParameter_thenSeventhElementReturnScalar() {
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
    HullWhiteLocalVolatilityModel covarianceModel6 =
        new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel6,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    RandomVariable[] actualParameter = liborCovarianceModelStochasticVolatility.getParameter();

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
   * Test {@link LIBORCovarianceModelStochasticVolatility#clone()}.
   *
   * <ul>
   *   <li>Then return {@link LIBORCovarianceModelStochasticVolatility}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCovarianceModelStochasticVolatility.clone()"})
  public void testClone_thenReturnLIBORCovarianceModelStochasticVolatility() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    Object actualCloneResult = liborCovarianceModelStochasticVolatility.clone();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(blendedLocalVolatilityModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(blendedLocalVolatilityModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(blendedLocalVolatilityModel).getTimeDiscretization();
    verify(covarianceModel).clone();
    assertTrue(actualCloneResult instanceof LIBORCovarianceModelStochasticVolatility);
    TimeDiscretization liborPeriodDiscretization =
        ((LIBORCovarianceModelStochasticVolatility) actualCloneResult)
            .getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(
        2, ((LIBORCovarianceModelStochasticVolatility) actualCloneResult).getParameter().length);
    assertEquals(
        3, ((LIBORCovarianceModelStochasticVolatility) actualCloneResult).getNumberOfFactors());
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(
        tenorFromArray2,
        ((LIBORCovarianceModelStochasticVolatility) actualCloneResult).getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        ((LIBORCovarianceModelStochasticVolatility) actualCloneResult).getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getCloneWithModifiedParameters(double[])}
   * with {@code double[]}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticVolatility#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelStochasticVolatility.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble_thenReturnArrayLengthIsOne() {
    // Arrange
    BlendedLocalVolatilityModel covarianceModel = mock(BlendedLocalVolatilityModel.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        mock(BlendedLocalVolatilityModel.class);
    when(blendedLocalVolatilityModel.getNumberOfFactors()).thenReturn(3);
    when(blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            Mockito.<RandomVariable[]>any()))
        .thenReturn(hullWhiteLocalVolatilityModel);
    when(blendedLocalVolatilityModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(blendedLocalVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    BlendedLocalVolatilityModel covarianceModel2 = mock(BlendedLocalVolatilityModel.class);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.clone()).thenReturn(blendedLocalVolatilityModel);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            false);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelStochasticVolatility.getCloneWithModifiedParameters(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(blendedLocalVolatilityModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(blendedLocalVolatilityModel).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(blendedLocalVolatilityModel).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel2).clone();
    verify(blendedLocalVolatilityModel).getCloneWithModifiedParameters(isA(RandomVariable[].class));
    assertTrue(
        actualCloneWithModifiedParameters instanceof LIBORCovarianceModelStochasticVolatility);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(1, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getCloneWithModifiedParameters(double[])}
   * with {@code double[]}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticVolatility#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelStochasticVolatility.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble_thenReturnArrayLengthIsTwo() {
    // Arrange
    BlendedLocalVolatilityModel covarianceModel = mock(BlendedLocalVolatilityModel.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    new HullWhiteLocalVolatilityModel(
        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        mock(BlendedLocalVolatilityModel.class);
    when(blendedLocalVolatilityModel.getNumberOfFactors()).thenReturn(3);
    when(blendedLocalVolatilityModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(blendedLocalVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    BlendedLocalVolatilityModel covarianceModel2 = mock(BlendedLocalVolatilityModel.class);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.clone()).thenReturn(blendedLocalVolatilityModel);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelStochasticVolatility.getCloneWithModifiedParameters(new double[] {});

    // Assert
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(blendedLocalVolatilityModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(blendedLocalVolatilityModel).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(blendedLocalVolatilityModel).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel2).clone();
    assertTrue(
        actualCloneWithModifiedParameters instanceof LIBORCovarianceModelStochasticVolatility);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(2, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedParameters.getParameterAsDouble(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getCloneWithModifiedParameters(double[])}
   * with {@code double[]}.
   *
   * <ul>
   *   <li>Then third element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticVolatility#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelStochasticVolatility.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble_thenThirdElementReturnScalar() {
    // Arrange
    BlendedLocalVolatilityModel covarianceModel = mock(BlendedLocalVolatilityModel.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        mock(BlendedLocalVolatilityModel.class);
    when(blendedLocalVolatilityModel.getNumberOfFactors()).thenReturn(3);
    when(blendedLocalVolatilityModel.getCloneWithModifiedParameters(
            Mockito.<RandomVariable[]>any()))
        .thenReturn(hullWhiteLocalVolatilityModel);
    when(blendedLocalVolatilityModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(blendedLocalVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    BlendedLocalVolatilityModel covarianceModel2 = mock(BlendedLocalVolatilityModel.class);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.clone()).thenReturn(blendedLocalVolatilityModel);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelStochasticVolatility.getCloneWithModifiedParameters(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(blendedLocalVolatilityModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(blendedLocalVolatilityModel).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(blendedLocalVolatilityModel).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel2).clone();
    verify(blendedLocalVolatilityModel).getCloneWithModifiedParameters(isA(RandomVariable[].class));
    assertTrue(
        actualCloneWithModifiedParameters instanceof LIBORCovarianceModelStochasticVolatility);
    RandomVariable[] parameter = actualCloneWithModifiedParameters.getParameter();
    assertTrue(parameter[2] instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(3, parameter.length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelStochasticVolatility#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticVolatility#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelStochasticVolatility.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable() {
    // Arrange
    BlendedLocalVolatilityModel covarianceModel = mock(BlendedLocalVolatilityModel.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    new HullWhiteLocalVolatilityModel(
        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        mock(BlendedLocalVolatilityModel.class);
    when(blendedLocalVolatilityModel.getNumberOfFactors()).thenReturn(3);
    when(blendedLocalVolatilityModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(blendedLocalVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    BlendedLocalVolatilityModel covarianceModel2 = mock(BlendedLocalVolatilityModel.class);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.clone()).thenReturn(blendedLocalVolatilityModel);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            false);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelStochasticVolatility.getCloneWithModifiedParameters(
            new RandomVariable[] {});

    // Assert
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(blendedLocalVolatilityModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(blendedLocalVolatilityModel).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(blendedLocalVolatilityModel).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel2).clone();
    assertTrue(
        actualCloneWithModifiedParameters instanceof LIBORCovarianceModelStochasticVolatility);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertNull(actualCloneWithModifiedParameters.getParameter());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelStochasticVolatility#getCloneWithModifiedParameters(RandomVariable[])} with
   * {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then return Parameter is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticVolatility#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelStochasticVolatility.getCloneWithModifiedParameters(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParametersWithRandomVariable_thenReturnParameterIsNull() {
    // Arrange
    BlendedLocalVolatilityModel covarianceModel = mock(BlendedLocalVolatilityModel.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    new HullWhiteLocalVolatilityModel(
        new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    BlendedLocalVolatilityModel blendedLocalVolatilityModel =
        mock(BlendedLocalVolatilityModel.class);
    when(blendedLocalVolatilityModel.getNumberOfFactors()).thenReturn(3);
    when(blendedLocalVolatilityModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(blendedLocalVolatilityModel.getTimeDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    BlendedLocalVolatilityModel covarianceModel2 = mock(BlendedLocalVolatilityModel.class);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.clone()).thenReturn(blendedLocalVolatilityModel);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            false);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelStochasticVolatility.getCloneWithModifiedParameters(
            (RandomVariable[]) null);

    // Assert
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(blendedLocalVolatilityModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(blendedLocalVolatilityModel).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(blendedLocalVolatilityModel).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel2).clone();
    assertTrue(
        actualCloneWithModifiedParameters instanceof LIBORCovarianceModelStochasticVolatility);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertNull(actualCloneWithModifiedParameters.getParameter());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORCovarianceModelStochasticVolatility.getParameterAsDouble()"})
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
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticVolatility.getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORCovarianceModelStochasticVolatility.getParameterAsDouble()"})
  public void testGetParameterAsDouble2() {
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticVolatility.getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORCovarianceModelStochasticVolatility.getParameterAsDouble()"})
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
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticVolatility.getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORCovarianceModelStochasticVolatility.getParameterAsDouble()"})
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
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel4,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticVolatility.getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORCovarianceModelStochasticVolatility.getParameterAsDouble()"})
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel3,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticVolatility.getParameterAsDouble();

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
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORCovarianceModelStochasticVolatility.getParameterAsDouble()"})
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel3,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            false);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticVolatility.getParameterAsDouble();

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
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORCovarianceModelStochasticVolatility.getParameterAsDouble()"})
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
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariableFromDoubleArray nu = new RandomVariableFromDoubleArray(10.0d);

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, nu, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticVolatility.getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORCovarianceModelStochasticVolatility.getParameterAsDouble()"})
  public void testGetParameterAsDouble8() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariableFromDoubleArray nu = new RandomVariableFromDoubleArray(10.0d);

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel, brownianMotion2, nu, new RandomVariableFromDoubleArray(10.0d), true);

    // Act and Assert
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d, 10.0d, 10.0d},
        liborCovarianceModelStochasticVolatility.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORCovarianceModelStochasticVolatility.getParameterAsDouble()"})
  public void testGetParameterAsDouble9() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariableFromDoubleArray nu = new RandomVariableFromDoubleArray(10.0d);

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel, brownianMotion2, nu, new RandomVariableFromDoubleArray(10.0d), false);

    // Act and Assert
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d},
        liborCovarianceModelStochasticVolatility.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}.
   *
   * <ul>
   *   <li>Given {@link LocalDate} with {@code 1970} and one and one.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORCovarianceModelStochasticVolatility.getParameterAsDouble()"})
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
    HullWhiteLocalVolatilityModel covarianceModel6 =
        new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel6,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticVolatility.getParameterAsDouble();

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
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
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
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

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
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
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
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(
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
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
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
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableFloatFactory());
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

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
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
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
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

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
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable5() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {Scalar.of(1.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

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
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable6() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromFloatArray(1.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(randomVariable.average() instanceof RandomVariableFromFloatArray);
    assertTrue(randomVariable.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(randomVariable.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(randomVariable.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(randomVariable.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(randomVariable.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(randomVariable.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(randomVariable.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(randomVariable.variance() instanceof RandomVariableFromFloatArray);
    assertTrue(randomVariable instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable7() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(randomVariableFromDoubleArray);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {randomVariableAAD});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

    // Assert
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertSame(randomVariableFromDoubleArray, randomVariable);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable8() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[2] instanceof RandomVariableFromDoubleArray);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable9() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization2, 3, 10, 42, new RandomVariableFloatFactory());
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromFloatArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromFloatArray);
    assertTrue(actualFactorLoading[2] instanceof RandomVariableFromFloatArray);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable10() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization2, 3, 10, 42, new RandomVariableLazyEvaluationFactory());
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableLazyEvaluation);
    assertTrue(actualFactorLoading[2] instanceof RandomVariableLazyEvaluation);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable11() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization2, 3, 10, 42, new RandomVariableDifferentiableAADFactory());
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFactorLoading[2] instanceof RandomVariableDifferentiableAAD);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable12() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    TenorFromArray timeDiscretization2 = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization2, 3, 10, 42, new RandomVariableDifferentiableAADPathwiseFactory());
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualFactorLoading[2] instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(3, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable13() {
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
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d),
            10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableDifferentiableAADFactory());
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable14() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableFromFloatArray(1.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d),
            10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableDifferentiableAADFactory());
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable15() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {new RandomVariableLazyEvaluation(1.0d)});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d),
            10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableDifferentiableAADFactory());
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.average() instanceof RandomVariableLazyEvaluation);
    assertTrue(randomVariable.expectation() instanceof RandomVariableLazyEvaluation);
    assertTrue(randomVariable.expm1() instanceof RandomVariableLazyEvaluation);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(randomVariable.variance() instanceof RandomVariableLazyEvaluation);
    assertTrue(randomVariable instanceof RandomVariableLazyEvaluation);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable16() {
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
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d),
            10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableDifferentiableAADPathwiseFactory());
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) randomVariable).getAverageAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) randomVariable).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) randomVariable).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    RandomVariable randomVariable2 =
        ((RandomVariableDifferentiableAADPathwise) randomVariable).getRandomVariable();
    assertTrue(randomVariable2 instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) randomVariable)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) randomVariable)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) randomVariable)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(
        ((RandomVariableDifferentiableAADPathwise) randomVariable).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(randomVariable.expectation() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(randomVariable.expm1() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(randomVariable.variance() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAADPathwise);
    assertEquals(1, actualFactorLoading.length);
    assertSame(randomVariable2, randomVariable.getValues());
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable17() {
    // Arrange
    RandomVariableFromFloatArray randomVariableFromFloatArray =
        new RandomVariableFromFloatArray(1.0d);
    ArrayList<RandomVariable> factor1 = new ArrayList<>();
    randomVariableFromFloatArray.addSumProduct(factor1, new ArrayList<>());

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {randomVariableFromFloatArray});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new HullWhiteLocalVolatilityModel(
                new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d),
            10.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableDifferentiableAADFactory());
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(randomVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) randomVariable).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable.variance() instanceof RandomVariableDifferentiableAAD);
    assertTrue(randomVariable instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, actualFactorLoading.length);
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticVolatility#getFactorLoading(int,
   * int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable_thenReturnArrayLengthIsTwo() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticVolatility.getFactorLoading(1, 1, null);

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromDoubleArray);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getFactorLoadingPseudoInverse(int, int,
   * int, RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticVolatility#getFactorLoadingPseudoInverse(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORCovarianceModelStochasticVolatility.getFactorLoadingPseudoInverse(int, int, int, RandomVariable[])"
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
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    RandomVariable actualFactorLoadingPseudoInverse =
        liborCovarianceModelStochasticVolatility.getFactorLoadingPseudoInverse(
            1, 1, 3, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertNull(actualFactorLoadingPseudoInverse);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Then fifth element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticVolatility#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelStochasticVolatility.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_thenFifthElementReturnScalar()
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
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(covarianceModel2, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel3,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        liborCovarianceModelStochasticVolatility.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof LIBORCovarianceModelStochasticVolatility);
    RandomVariable[] parameter = actualCloneWithModifiedData.getParameter();
    assertTrue(parameter[4] instanceof Scalar);
    assertTrue(parameter[5] instanceof Scalar);
    assertTrue(parameter[6] instanceof Scalar);
    assertTrue(parameter[7] instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(8, parameter.length);
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticVolatility#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return array length is three.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticVolatility#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelStochasticVolatility.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenNull_thenReturnArrayLengthIsThree()
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
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    LIBORCovarianceModelStochasticVolatility liborCovarianceModelStochasticVolatility =
        new LIBORCovarianceModelStochasticVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        liborCovarianceModelStochasticVolatility.getCloneWithModifiedData(null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCloneWithModifiedData instanceof LIBORCovarianceModelStochasticVolatility);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(3, actualCloneWithModifiedData.getParameter().length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }
}
