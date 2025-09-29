package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DigitalFloorletDiffblueTest {
  /**
   * Test {@link DigitalFloorlet#DigitalFloorlet(double, double)}.
   *
   * <p>Method under test: {@link DigitalFloorlet#DigitalFloorlet(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DigitalFloorlet.<init>(double, double)"})
  public void testNewDigitalFloorlet() {
    // Arrange, Act and Assert
    assertNull(new DigitalFloorlet(10.0d, 10.0d).getCurrency());
  }

  /**
   * Test {@link DigitalFloorlet#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link DigitalFloorlet#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DigitalFloorlet.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    DigitalFloorlet digitalFloorlet = new DigitalFloorlet(10.0d, 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {
              10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d
            },
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = digitalFloorlet.getValue(10.0d, model);

    // Assert
    verify(covarianceModel, atLeast(1))
        .getFactorLoading(eq(0), anyInt(), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link DigitalFloorlet#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link DigitalFloorlet#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DigitalFloorlet.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    DigitalFloorlet digitalFloorlet = new DigitalFloorlet(10.0d, 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(
            timeDiscretization, 3, 10, 42, new RandomVariableFloatFactory());

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {
              10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d
            },
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = digitalFloorlet.getValue(10.0d, model);

    // Assert
    verify(covarianceModel, atLeast(1))
        .getFactorLoading(eq(0), anyInt(), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link DigitalFloorlet#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link DigitalFloorlet#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DigitalFloorlet.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    DigitalFloorlet digitalFloorlet = new DigitalFloorlet(10.0d, 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {
              1.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d
            },
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = digitalFloorlet.getValue(10.0d, model);

    // Assert
    verify(covarianceModel, atLeast(1))
        .getFactorLoading(eq(0), anyInt(), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertEquals(0.5d, actualValue.getAverage(), 0.0);
    assertEquals(0.5d, actualValue.getMax(), 0.0);
    assertEquals(0.5d, actualValue.getMin(), 0.0);
    assertArrayEquals(
        new double[] {0.5d, 0.5d, 0.5d, 0.5d, 0.5d, 0.5d, 0.5d, 0.5d, 0.5d, 0.5d},
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link DigitalFloorlet#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link DigitalFloorlet#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable DigitalFloorlet.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel_thenReturnSizeIsOne()
      throws CalculationException {
    // Arrange
    DigitalFloorlet digitalFloorlet = new DigitalFloorlet(10.0d, 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(10.0d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 1, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {
              10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d, 10.0d, 0.0d
            },
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = digitalFloorlet.getValue(10.0d, model);

    // Assert
    verify(covarianceModel, atLeast(1))
        .getFactorLoading(eq(0), anyInt(), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualValue.size());
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }
}
