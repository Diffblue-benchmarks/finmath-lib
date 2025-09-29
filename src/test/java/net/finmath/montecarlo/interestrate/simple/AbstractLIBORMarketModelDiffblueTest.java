package net.finmath.montecarlo.interestrate.simple;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.LIBORCovarianceModelBH;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AbstractLIBORMarketModelDiffblueTest {
  /**
   * Test {@link AbstractLIBORMarketModel#getReferenceDate()}.
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.time.LocalDateTime AbstractLIBORMarketModel.getReferenceDate()"})
  public void testGetReferenceDate() {
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
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> simpleLIBORMarketModel.getReferenceDate());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getNumeraire(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable AbstractLIBORMarketModel.getNumeraire(double)"})
  public void testGetNumeraireWithTime_thenAbsReturnRandomVariableFromDoubleArray() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d);
    TenorFromArray timeDiscretization =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretization,
            new TenorFromArray(1.0d, 10, 0.5d),
            10,
            new double[] {1.0d, Double.NaN, 1.0d, Double.NaN},
            covarianceModel2);

    // Act
    RandomVariable actualNumeraire = simpleLIBORMarketModel.getNumeraire(10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualNumeraire.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getNumeraire(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getTime(int)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable AbstractLIBORMarketModel.getNumeraire(double)"})
  public void testGetNumeraireWithTime_thenCallsGetTime() {
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

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);

    // Act
    RandomVariable actualNumeraire = simpleLIBORMarketModel.getNumeraire(10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization).getTime(1);
    verify(timeDiscretization).getTimeIndex(10.0d);
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNumeraire.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualNumeraire.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getNumeraire(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return Average is {@code 2576816.0}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable AbstractLIBORMarketModel.getNumeraire(double)"})
  public void testGetNumeraireWithTime_thenReturnAverageIs25768160() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d);
    TenorFromArray timeDiscretization =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretization,
            new TenorFromArray(1.0d, 10, 1.0d),
            10,
            new double[] {
              10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d
            },
            covarianceModel2);

    // Act
    RandomVariable actualNumeraire = simpleLIBORMarketModel.getNumeraire(10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertEquals(2576816.0d, actualNumeraire.getAverage(), 0.0);
    assertEquals(2576816.0d, actualNumeraire.getMax(), 0.0);
    assertEquals(2576816.0d, actualNumeraire.getMin(), 0.0);
    assertArrayEquals(
        new double[] {
          2576816.0d,
          2576816.0d,
          2576816.0d,
          2576816.0d,
          2576816.0d,
          2576816.0d,
          2576816.0d,
          2576816.0d,
          2576816.0d,
          2576816.0d
        },
        actualNumeraire.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getNumeraire(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return SampleVariance is {@code 1.104129430700252E204}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable AbstractLIBORMarketModel.getNumeraire(double)"})
  public void testGetNumeraireWithTime_thenReturnSampleVarianceIs1104129430700252e204() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(1.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(1.0d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d);
    TenorFromArray timeDiscretization =
        new TenorFromArray(1.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretization,
            new TenorFromArray(1.0d, 10, 1.0d),
            10,
            new double[] {
              10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d
            },
            covarianceModel2);

    // Act
    RandomVariable actualNumeraire = simpleLIBORMarketModel.getNumeraire(10.0d);

    // Assert
    verify(covarianceModel, atLeast(1))
        .getFactorLoading(eq(0), anyInt(), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualNumeraire instanceof RandomVariableFromDoubleArray);
    assertEquals(1.104129430700252E204d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(3.152326898705505E101d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(3.323078876883872E102d, actualNumeraire.getMax(), 0.0);
    assertEquals(3.325195975873807E101d, actualNumeraire.getAverage(), 0.0);
    assertEquals(3.938343295973289E65d, actualNumeraire.getMin(), 0.0);
    assertEquals(9.937164876302268E203d, actualNumeraire.getVariance(), 0.0);
    assertEquals(9.968532929324288E101d, actualNumeraire.getStandardDeviation(), 0.0);
    assertArrayEquals(
        new double[] {
          3.323078876883872E102d,
          3.938343295973289E65d,
          4.570687577793186E86d,
          1.9468945862873365E82d,
          2.3328539603879166E88d,
          3.164044373918679E91d,
          2.1170987766380913E99d,
          7.179288527754519E90d,
          1.678206834041901E92d,
          6.6323415126384415E90d
        },
        actualNumeraire.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getLIBOR(int, int)} with {@code timeIndex}, {@code
   * liborIndex}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getLIBOR(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable AbstractLIBORMarketModel.getLIBOR(int, int)"})
  public void testGetLIBORWithTimeIndexLiborIndex_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {
              1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d
            },
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualLIBOR = simpleLIBORMarketModel.getLIBOR(0, 1);

    // Assert
    assertTrue(actualLIBOR instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBOR.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualLIBOR.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualLIBOR.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualLIBOR.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualLIBOR.getStandardError(), 0.0);
    assertEquals(0.0d, actualLIBOR.getVariance(), 0.0);
    assertEquals(1, actualLIBOR.getTypePriority());
    assertEquals(1, actualLIBOR.size());
    assertEquals(10.0d, actualLIBOR.getAverage(), 0.0);
    assertEquals(10.0d, actualLIBOR.getMax(), 0.0);
    assertEquals(10.0d, actualLIBOR.getMin(), 0.0);
    assertTrue(actualLIBOR.isDeterministic());
    assertArrayEquals(new double[] {10.0d}, actualLIBOR.getRealizations(), 0.0);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getLIBOR(int, int)} with {@code timeIndex}, {@code
   * liborIndex}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getLIBOR(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable AbstractLIBORMarketModel.getLIBOR(int, int)"})
  public void testGetLIBORWithTimeIndexLiborIndex_thenThrowUnsupportedOperationException() {
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

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new UnsupportedOperationException());
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {
              1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d
            },
            covarianceModel2,
            brownianMotion2);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> simpleLIBORMarketModel.getLIBOR(1, 1));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization).getTime(1);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getLIBORs(int)}.
   *
   * <ul>
   *   <li>Then calls {@link AbstractLIBORCovarianceModelParametric#getFactorLoading(int, int,
   *       RandomVariable[])}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getLIBORs(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] AbstractLIBORMarketModel.getLIBORs(int)"})
  public void testGetLIBORs_thenCallsGetFactorLoading() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(1.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(1.0d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {
              1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d
            },
            covarianceModel2,
            brownianMotion2);

    // Act
    RandomVariable[] actualLIBORs = simpleLIBORMarketModel.getLIBORs(1);

    // Assert
    verify(covarianceModel, atLeast(1))
        .getFactorLoading(eq(0), anyInt(), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
    assertTrue(actualLIBORs[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[5] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[6] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[7] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[8] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[9] instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualLIBORs.length);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getLIBORs(int)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getLIBORs(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] AbstractLIBORMarketModel.getLIBORs(int)"})
  public void testGetLIBORs_thenThrowUnsupportedOperationException() {
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

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new UnsupportedOperationException());
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {
              1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d
            },
            covarianceModel2,
            brownianMotion2);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> simpleLIBORMarketModel.getLIBORs(1));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization).getTime(1);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getLIBORs(int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then first element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getLIBORs(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] AbstractLIBORMarketModel.getLIBORs(int)"})
  public void testGetLIBORs_whenZero_thenFirstElementReturnRandomVariableFromDoubleArray() {
    // Arrange
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCovarianceModelBH covarianceModel =
        new LIBORCovarianceModelBH(timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3);
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(covarianceModel, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {
              1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d
            },
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualLIBORs = simpleLIBORMarketModel.getLIBORs(0);

    // Assert
    assertTrue(actualLIBORs[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[5] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[6] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[7] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[8] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLIBORs[9] instanceof RandomVariableFromDoubleArray);
    assertEquals(10, actualLIBORs.length);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getForwardRate(double, double, double)} with {@code time},
   * {@code periodStart}, {@code periodEnd}.
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getForwardRate(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AbstractLIBORMarketModel.getForwardRate(double, double, double)"
  })
  public void testGetForwardRateWithTimePeriodStartPeriodEnd() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 1.0d, true), 1.0d);
    TenorFromArray timeDiscretization = new TenorFromArray(1.0d, 10, 0.5d);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            timeDiscretization,
            new TenorFromArray(1.0d, 10, 0.5d),
            10,
            new double[] {1.0d, Double.NaN, 1.0d, Double.NaN},
            covarianceModel2);

    // Act
    RandomVariable actualForwardRate = simpleLIBORMarketModel.getForwardRate(10.0d, 10.0d, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualForwardRate instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.variance() instanceof RandomVariableFromDoubleArray);
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
        actualForwardRate.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getForwardRate(double, double, double)} with {@code time},
   * {@code periodStart}, {@code periodEnd}.
   *
   * <ul>
   *   <li>Then calls {@link TimeDiscretization#getTimeIndex(double)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getForwardRate(double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AbstractLIBORMarketModel.getForwardRate(double, double, double)"
  })
  public void testGetForwardRateWithTimePeriodStartPeriodEnd_thenCallsGetTimeIndex() {
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

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            brownianMotion2);

    // Act
    RandomVariable actualForwardRate = simpleLIBORMarketModel.getForwardRate(10.0d, 10.0d, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization).getTimeIndex(10.0d);
    assertTrue(actualForwardRate instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualForwardRate.variance() instanceof RandomVariableFromDoubleArray);
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
        actualForwardRate.getRealizations(),
        0.0);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getMonteCarloWeights(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getMonteCarloWeights(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable AbstractLIBORMarketModel.getMonteCarloWeights(double)"})
  public void testGetMonteCarloWeightsWithTime_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(1.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(1.0d);
    when(covarianceModel.getFactorLoading(anyInt(), anyInt(), Mockito.<RandomVariable[]>any()))
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray,
              randomVariableFromDoubleArray2,
              new RandomVariableFromDoubleArray(1.0d)
            });
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getTimeStep(anyInt())).thenReturn(10.0d);
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {
              1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d
            },
            covarianceModel2,
            brownianMotion2);

    // Act
    RandomVariable actualMonteCarloWeights = simpleLIBORMarketModel.getMonteCarloWeights(10.0d);

    // Assert
    verify(covarianceModel, atLeast(1))
        .getFactorLoading(eq(0), anyInt(), (RandomVariable[]) isNull());
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization, atLeast(1)).getTime(anyInt());
    verify(timeDiscretization).getTimeIndex(10.0d);
    verify(timeDiscretization, atLeast(1)).getTimeStep(anyInt());
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
    assertEquals(0.0d, actualMonteCarloWeights.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualMonteCarloWeights.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualMonteCarloWeights.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualMonteCarloWeights.getStandardError(), 0.0);
    assertEquals(0.0d, actualMonteCarloWeights.getVariance(), 0.0);
    assertEquals(0.1d, actualMonteCarloWeights.getAverage(), 0.0);
    assertEquals(0.1d, actualMonteCarloWeights.getMax(), 0.0);
    assertEquals(0.1d, actualMonteCarloWeights.getMin(), 0.0);
    assertEquals(1, actualMonteCarloWeights.getTypePriority());
    assertEquals(1, actualMonteCarloWeights.size());
    assertTrue(actualMonteCarloWeights.isDeterministic());
    assertArrayEquals(new double[] {0.1d}, actualMonteCarloWeights.getRealizations(), 0.0);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getMonteCarloWeights(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getMonteCarloWeights(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable AbstractLIBORMarketModel.getMonteCarloWeights(double)"})
  public void testGetMonteCarloWeightsWithTime_thenThrowUnsupportedOperationException() {
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

    TimeDiscretization timeDiscretization = mock(TimeDiscretization.class);
    when(timeDiscretization.getTime(anyInt())).thenThrow(new UnsupportedOperationException());
    when(timeDiscretization.getNumberOfTimeSteps()).thenReturn(10);
    when(timeDiscretization.getTimeIndex(anyDouble())).thenReturn(1);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    BrownianMotionWithControlVariate brownianMotion2 =
        new BrownianMotionWithControlVariate(brownianMotion);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            new TenorFromArray(10.0d, 10, 0.5d),
            new double[] {
              1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d
            },
            covarianceModel2,
            brownianMotion2);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> simpleLIBORMarketModel.getMonteCarloWeights(10.0d));
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(timeDiscretization, atLeast(1)).getNumberOfTimeSteps();
    verify(timeDiscretization).getTime(1);
    verify(timeDiscretization).getTimeIndex(10.0d);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getNumberOfLibors()}.
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getNumberOfLibors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AbstractLIBORMarketModel.getNumberOfLibors()"})
  public void testGetNumberOfLibors() {
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
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    int actualNumberOfLibors = simpleLIBORMarketModel.getNumberOfLibors();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(10, actualNumberOfLibors);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getLiborPeriod(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getLiborPeriod(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractLIBORMarketModel.getLiborPeriod(int)"})
  public void testGetLiborPeriod_thenReturn105() {
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
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    double actualLiborPeriod = simpleLIBORMarketModel.getLiborPeriod(1);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(10.5d, actualLiborPeriod, 0.0);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getLiborPeriodIndex(double)}.
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getLiborPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AbstractLIBORMarketModel.getLiborPeriodIndex(double)"})
  public void testGetLiborPeriodIndex() {
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
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    int actualLiborPeriodIndex = simpleLIBORMarketModel.getLiborPeriodIndex(10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(0, actualLiborPeriodIndex);
  }

  /**
   * Test {@link AbstractLIBORMarketModel#getLiborPeriodDiscretization()}.
   *
   * <p>Method under test: {@link AbstractLIBORMarketModel#getLiborPeriodDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization AbstractLIBORMarketModel.getLiborPeriodDiscretization()"})
  public void testGetLiborPeriodDiscretization() {
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
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);

    SimpleLIBORMarketModel simpleLIBORMarketModel =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    TimeDiscretization actualLiborPeriodDiscretization =
        simpleLIBORMarketModel.getLiborPeriodDiscretization();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertSame(liborPeriodDiscretization, actualLiborPeriodDiscretization);
  }
}
