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
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class LIBORCovarianceModelStochasticHestonVolatilityDiffblueTest {
  /**
   * Test {@link
   * LIBORCovarianceModelStochasticHestonVolatility#LIBORCovarianceModelStochasticHestonVolatility(AbstractLIBORCovarianceModelParametric,
   * BrownianMotion, double, double, double, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#LIBORCovarianceModelStochasticHestonVolatility(AbstractLIBORCovarianceModelParametric,
   * BrownianMotion, double, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCovarianceModelStochasticHestonVolatility.<init>(AbstractLIBORCovarianceModelParametric, BrownianMotion, double, double, double, boolean)"
  })
  public void testNewLIBORCovarianceModelStochasticHestonVolatility() {
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
    LIBORCovarianceModelStochasticHestonVolatility
        actualLiborCovarianceModelStochasticHestonVolatility =
            new LIBORCovarianceModelStochasticHestonVolatility(
                covarianceModel2,
                new BrownianMotionWithControlVariate(brownianMotion),
                10.0d,
                10.0d,
                10.0d,
                true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    TimeDiscretization liborPeriodDiscretization =
        actualLiborCovarianceModelStochasticHestonVolatility.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(3, actualLiborCovarianceModelStochasticHestonVolatility.getNumberOfFactors());
    assertEquals(4, actualLiborCovarianceModelStochasticHestonVolatility.getParameter().length);
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(
        tenorFromArray2,
        actualLiborCovarianceModelStochasticHestonVolatility.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualLiborCovarianceModelStochasticHestonVolatility.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelStochasticHestonVolatility#LIBORCovarianceModelStochasticHestonVolatility(AbstractLIBORCovarianceModelParametric,
   * BrownianMotion, RandomVariable, RandomVariable, RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#LIBORCovarianceModelStochasticHestonVolatility(AbstractLIBORCovarianceModelParametric,
   * BrownianMotion, RandomVariable, RandomVariable, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORCovarianceModelStochasticHestonVolatility.<init>(AbstractLIBORCovarianceModelParametric, BrownianMotion, RandomVariable, RandomVariable, RandomVariable, boolean)"
  })
  public void testNewLIBORCovarianceModelStochasticHestonVolatility2() {
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
    RandomVariableFromDoubleArray kappa = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray theta = new RandomVariableFromDoubleArray(10.0d);

    // Act
    LIBORCovarianceModelStochasticHestonVolatility
        actualLiborCovarianceModelStochasticHestonVolatility =
            new LIBORCovarianceModelStochasticHestonVolatility(
                covarianceModel2,
                brownianMotion2,
                kappa,
                theta,
                new RandomVariableFromDoubleArray(10.0d),
                true);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    TimeDiscretization liborPeriodDiscretization =
        actualLiborCovarianceModelStochasticHestonVolatility.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(3, actualLiborCovarianceModelStochasticHestonVolatility.getNumberOfFactors());
    assertEquals(4, actualLiborCovarianceModelStochasticHestonVolatility.getParameter().length);
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(
        tenorFromArray2,
        actualLiborCovarianceModelStochasticHestonVolatility.getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualLiborCovarianceModelStochasticHestonVolatility.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameter()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticHestonVolatility#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getParameter()"
  })
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    RandomVariable[] actualParameter =
        liborCovarianceModelStochasticHestonVolatility.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    RandomVariable randomVariable = actualParameter[3];
    assertTrue(randomVariable.invert() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(5, actualParameter.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameter()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticHestonVolatility#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getParameter()"
  })
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    RandomVariable[] actualParameter =
        liborCovarianceModelStochasticHestonVolatility.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertTrue(actualParameter[0] instanceof Scalar);
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[2] instanceof Scalar);
    assertTrue(actualParameter[4] instanceof Scalar);
    assertTrue(actualParameter[5] instanceof Scalar);
    assertEquals(6, actualParameter.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameter()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticHestonVolatility#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getParameter()"
  })
  public void testGetParameter3() {
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel4,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    RandomVariable[] actualParameter =
        liborCovarianceModelStochasticHestonVolatility.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertTrue(actualParameter[0] instanceof Scalar);
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[2] instanceof Scalar);
    assertTrue(actualParameter[4] instanceof Scalar);
    assertTrue(actualParameter[5] instanceof Scalar);
    assertEquals(6, actualParameter.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameter()}.
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticHestonVolatility#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getParameter()"
  })
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel6,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            false);

    // Act
    RandomVariable[] actualParameter =
        liborCovarianceModelStochasticHestonVolatility.getParameter();

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
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameter()}.
   *
   * <ul>
   *   <li>Then fourth element invert return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticHestonVolatility#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getParameter()"
  })
  public void testGetParameter_thenFourthElementInvertReturnScalar() {
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    RandomVariable[] actualParameter =
        liborCovarianceModelStochasticHestonVolatility.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    RandomVariable randomVariable = actualParameter[3];
    assertTrue(randomVariable.invert() instanceof Scalar);
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(5, actualParameter.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameter()}.
   *
   * <ul>
   *   <li>Then seventh element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticHestonVolatility#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getParameter()"
  })
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel6,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    RandomVariable[] actualParameter =
        liborCovarianceModelStochasticHestonVolatility.getParameter();

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
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#clone()}.
   *
   * <ul>
   *   <li>Then return {@link LIBORCovarianceModelStochasticHestonVolatility}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCovarianceModelStochasticHestonVolatility#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORCovarianceModelStochasticHestonVolatility.clone()"})
  public void testClone_thenReturnLIBORCovarianceModelStochasticHestonVolatility() {
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    Object actualCloneResult = liborCovarianceModelStochasticHestonVolatility.clone();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(blendedLocalVolatilityModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(blendedLocalVolatilityModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(blendedLocalVolatilityModel).getTimeDiscretization();
    verify(covarianceModel).clone();
    assertTrue(actualCloneResult instanceof LIBORCovarianceModelStochasticHestonVolatility);
    TimeDiscretization liborPeriodDiscretization =
        ((LIBORCovarianceModelStochasticHestonVolatility) actualCloneResult)
            .getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    assertEquals(
        3,
        ((LIBORCovarianceModelStochasticHestonVolatility) actualCloneResult).getNumberOfFactors());
    assertEquals(
        3,
        ((LIBORCovarianceModelStochasticHestonVolatility) actualCloneResult).getParameter().length);
    assertSame(tenorFromArray, liborPeriodDiscretization);
    assertSame(
        tenorFromArray2,
        ((LIBORCovarianceModelStochasticHestonVolatility) actualCloneResult)
            .getTimeDiscretization());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
        ((LIBORCovarianceModelStochasticHestonVolatility) actualCloneResult).getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <ul>
   *   <li>Then fourth element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelStochasticHestonVolatility.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble_thenFourthElementReturnScalar() {
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelStochasticHestonVolatility.getCloneWithModifiedParameters(
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
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelStochasticHestonVolatility);
    RandomVariable[] parameter = actualCloneWithModifiedParameters.getParameter();
    assertTrue(parameter[3] instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(4, parameter.length);
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
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
   * LIBORCovarianceModelStochasticHestonVolatility#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelStochasticHestonVolatility.getCloneWithModifiedParameters(double[])"
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            false);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelStochasticHestonVolatility.getCloneWithModifiedParameters(
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
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelStochasticHestonVolatility);
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
   * Test {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getCloneWithModifiedParameters(double[])} with
   * {@code double[]}.
   *
   * <ul>
   *   <li>Then return array length is three.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelStochasticHestonVolatility.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParametersWithDouble_thenReturnArrayLengthIsThree() {
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelStochasticHestonVolatility.getCloneWithModifiedParameters(
            new double[] {});

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
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelStochasticHestonVolatility);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedParameters.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization =
        actualCloneWithModifiedParameters.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(3, actualCloneWithModifiedParameters.getParameter().length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d},
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
   * LIBORCovarianceModelStochasticHestonVolatility#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelStochasticHestonVolatility.getCloneWithModifiedParameters(RandomVariable[])"
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            false);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelStochasticHestonVolatility.getCloneWithModifiedParameters(
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
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelStochasticHestonVolatility);
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
   * LIBORCovarianceModelStochasticHestonVolatility#getCloneWithModifiedParameters(RandomVariable[])}
   * with {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>Then return Parameter is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getCloneWithModifiedParameters(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelStochasticHestonVolatility.getCloneWithModifiedParameters(RandomVariable[])"
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            false);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedParameters =
        liborCovarianceModelStochasticHestonVolatility.getCloneWithModifiedParameters(
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
        actualCloneWithModifiedParameters
            instanceof LIBORCovarianceModelStochasticHestonVolatility);
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
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelStochasticHestonVolatility.getParameterAsDouble()"
  })
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticHestonVolatility.getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelStochasticHestonVolatility.getParameterAsDouble()"
  })
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticHestonVolatility.getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelStochasticHestonVolatility.getParameterAsDouble()"
  })
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticHestonVolatility.getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelStochasticHestonVolatility.getParameterAsDouble()"
  })
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel4,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticHestonVolatility.getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelStochasticHestonVolatility.getParameterAsDouble()"
  })
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel3,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticHestonVolatility.getParameterAsDouble();

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
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelStochasticHestonVolatility.getParameterAsDouble()"
  })
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel3,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            false);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticHestonVolatility.getParameterAsDouble();

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
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelStochasticHestonVolatility.getParameterAsDouble()"
  })
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
    RandomVariableFromDoubleArray kappa = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray theta = new RandomVariableFromDoubleArray(10.0d);

    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            brownianMotion2,
            kappa,
            theta,
            new RandomVariableFromDoubleArray(10.0d),
            true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticHestonVolatility.getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d}, actualParameterAsDouble, 0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelStochasticHestonVolatility.getParameterAsDouble()"
  })
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
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion2 =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion3 =
        new BrownianMotionWithControlVariate(brownianMotion2);
    RandomVariableFromDoubleArray kappa = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray theta = new RandomVariableFromDoubleArray(10.0d);

    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel4,
            brownianMotion3,
            kappa,
            theta,
            new RandomVariableFromDoubleArray(10.0d),
            true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticHestonVolatility.getParameterAsDouble();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualParameterAsDouble,
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}.
   *
   * <ul>
   *   <li>Given {@link LocalDate} with {@code 1970} and one and one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelStochasticHestonVolatility.getParameterAsDouble()"
  })
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel6,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    double[] actualParameterAsDouble =
        liborCovarianceModelStochasticHestonVolatility.getParameterAsDouble();

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
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@code 0.2} and {@code 0.05}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] LIBORCovarianceModelStochasticHestonVolatility.getParameterAsDouble()"
  })
  public void testGetParameterAsDouble_thenReturnArrayOfDoubleWith02And005() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelExponentialForm5Param covarianceModel =
        new LIBORCovarianceModelExponentialForm5Param(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariableFromDoubleArray kappa = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray theta = new RandomVariableFromDoubleArray(10.0d);

    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel,
            brownianMotion2,
            kappa,
            theta,
            new RandomVariableFromDoubleArray(10.0d),
            true);

    // Act and Assert
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d, 10.0d, 10.0d, 10.0d},
        liborCovarianceModelStochasticHestonVolatility.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getFactorLoading(int, int, RandomVariable[])"
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

    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticHestonVolatility.getFactorLoading(1, 1, null);

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
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getFactorLoading(int, int, RandomVariable[])"
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

    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, 10.0d, true);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticHestonVolatility.getFactorLoading(
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
    assertEquals(18.695302451570786d, randomVariable.getStandardError(), 0.0);
    assertEquals(3495.1433375570864d, randomVariable.getVariance(), 0.0);
    assertEquals(3883.4925972856518d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(59.11973729269343d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(610.3029838016031d, randomVariable.getMin(), 0.0);
    assertEquals(743.7101966911862d, randomVariable.getAverage(), 0.0);
    assertEquals(838.6518913606147d, randomVariable.getMax(), 0.0);
    assertArrayEquals(
        new double[] {
          726.5637879341994d,
          754.4148899950926d,
          786.8568813409885d,
          720.3073670427483d,
          784.1377239503951d,
          776.9009636431201d,
          610.3029838016031d,
          746.054843871808d,
          692.9106339712927d,
          838.6518913606147d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getFactorLoading(int, int, RandomVariable[])"
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

    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticHestonVolatility.getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertEquals(1.6995725447769392d, randomVariable.getStandardError(), 0.0);
    assertEquals(28.885468349595612d, randomVariable.getVariance(), 0.0);
    assertEquals(32.09496483288402d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(5.3745202901836375d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(55.48209190368652d, randomVariable.getMin(), 0.0);
    assertEquals(67.61001777648926d, randomVariable.getAverage(), 0.0);
    assertEquals(76.24107837677002d, randomVariable.getMax(), 0.0);
    assertArrayEquals(
        new double[] {
          66.05125427246094d,
          68.58317375183105d,
          71.53244018554688d,
          65.48248767852783d,
          71.28524780273438d,
          70.6273603439331d,
          55.48209190368652d,
          67.823166847229d,
          62.99187660217285d,
          76.24107837677002d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getFactorLoading(int, int, RandomVariable[])"
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

    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2, brownianMotion, 10.0d, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticHestonVolatility.getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualFactorLoading.length);
    assertEquals(1.8856333121444662d, randomVariable.getStandardError(), 0.0);
    assertEquals(35.5561298786891d, randomVariable.getVariance(), 0.0);
    assertEquals(39.50681097632122d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(5.962896098263754d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(55.13740721644213d, randomVariable.getMin(), 0.0);
    assertEquals(68.65226834709429d, randomVariable.getAverage(), 0.0);
    assertEquals(78.16822843838453d, randomVariable.getMax(), 0.0);
    assertArrayEquals(
        new double[] {
          66.95023519237918d,
          69.75002851767825d,
          73.0002569019853d,
          66.31996480574584d,
          72.72825706789892d,
          72.00399344103383d,
          55.13740721644213d,
          68.910593845302d,
          63.55371804409293d,
          78.16822843838453d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getFactorLoading(int, int, RandomVariable[])"
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

    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticHestonVolatility.getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    RandomVariable randomVariable = actualFactorLoading[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(0.16995729501427986d, randomVariable.getStandardError(), 0.0);
    assertEquals(0.28885482128570955d, randomVariable.getVariance(), 0.0);
    assertEquals(0.32094980142856616d, randomVariable.getSampleVariance(), 0.0);
    assertEquals(0.5374521572063039d, randomVariable.getStandardDeviation(), 0.0);
    assertEquals(1, actualFactorLoading.length);
    assertEquals(5.548208943650938d, randomVariable.getMin(), 0.0);
    assertEquals(6.761001788101693d, randomVariable.getAverage(), 0.0);
    assertEquals(7.624108103278316d, randomVariable.getMax(), 0.0);
    assertArrayEquals(
        new double[] {
          6.605125344856358d,
          6.858317181773569d,
          7.1532443758271675d,
          6.548248791297712d,
          7.12852476318541d,
          7.062736033119274d,
          5.548208943650938d,
          6.782316762470981d,
          6.299187581557206d,
          7.624108103278316d
        },
        randomVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getFactorLoading(int, int, RandomVariable[])"
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

    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticHestonVolatility.getFactorLoading(1, 1, null);

    // Assert
    verify(covarianceModel).getFactorLoading(eq(1), eq(1), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromFloatArray);
    assertEquals(1, actualFactorLoading.length);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable7() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

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

    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticHestonVolatility.getFactorLoading(1, 1, null);

    // Assert
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
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
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable8() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(new RandomVariable[] {randomVariableAAD});
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 10.0d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticHestonVolatility.getFactorLoading(1, 1, null);

    // Assert
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
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
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable9() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

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
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(1.0d, 10, 0.5d), 3, 1, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(new BrownianMotionWithControlVariate(brownianMotion));

    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticHestonVolatility.getFactorLoading(1, 1, null);

    // Assert
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
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
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int,
   * RandomVariable[])} with {@code int}, {@code int}, {@code RandomVariable[]}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getFactorLoading(int, int, RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORCovarianceModelStochasticHestonVolatility.getFactorLoading(int, int, RandomVariable[])"
  })
  public void testGetFactorLoadingWithIntIntRandomVariable_whenZero() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

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

    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2, brownianMotion2, 10.0d, 10.0d, 10.0d, true);

    // Act
    RandomVariable[] actualFactorLoading =
        liborCovarianceModelStochasticHestonVolatility.getFactorLoading(0, 1, null);

    // Assert
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(covarianceModel).getFactorLoading(eq(0), eq(1), (RandomVariable[]) isNull());
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
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getFactorLoadingPseudoInverse(int,
   * int, int, RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getFactorLoadingPseudoInverse(int, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORCovarianceModelStochasticHestonVolatility.getFactorLoadingPseudoInverse(int, int, int, RandomVariable[])"
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    RandomVariable actualFactorLoadingPseudoInverse =
        liborCovarianceModelStochasticHestonVolatility.getFactorLoadingPseudoInverse(
            1, 1, 3, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertNull(actualFactorLoadingPseudoInverse);
  }

  /**
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>Then sixth element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelStochasticHestonVolatility.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_thenSixthElementReturnScalar()
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel3,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        liborCovarianceModelStochasticHestonVolatility.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(
        actualCloneWithModifiedData instanceof LIBORCovarianceModelStochasticHestonVolatility);
    RandomVariable[] parameter = actualCloneWithModifiedData.getParameter();
    assertTrue(parameter[5] instanceof Scalar);
    assertTrue(parameter[6] instanceof Scalar);
    assertTrue(parameter[7] instanceof Scalar);
    assertTrue(parameter[8] instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(9, parameter.length);
    assertArrayEquals(
        new double[] {0.2d, 0.05d, 0.1d, 0.2d, 0.1d, 10.0d, 10.0d, 10.0d, 10.0d},
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
   * Test {@link LIBORCovarianceModelStochasticHestonVolatility#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return array length is four.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORCovarianceModelStochasticHestonVolatility#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractLIBORCovarianceModelParametric LIBORCovarianceModelStochasticHestonVolatility.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenNull_thenReturnArrayLengthIsFour()
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
    LIBORCovarianceModelStochasticHestonVolatility liborCovarianceModelStochasticHestonVolatility =
        new LIBORCovarianceModelStochasticHestonVolatility(
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion),
            10.0d,
            10.0d,
            10.0d,
            true);

    // Act
    AbstractLIBORCovarianceModelParametric actualCloneWithModifiedData =
        liborCovarianceModelStochasticHestonVolatility.getCloneWithModifiedData(null);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(
        actualCloneWithModifiedData instanceof LIBORCovarianceModelStochasticHestonVolatility);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TenorFromArray);
    assertEquals(4, actualCloneWithModifiedData.getParameter().length);
    assertEquals(liborPeriodDiscretization, timeDiscretization);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
  }
}
