package net.finmath.montecarlo.assetderivativevaluation.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.interestrate.models.funding.DefaultFactors;
import net.finmath.montecarlo.interestrate.models.funding.FundingCapacity;
import net.finmath.montecarlo.model.ProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ForwardAgreementWithFundingRequirementDiffblueTest {
  /**
   * Test {@link
   * ForwardAgreementWithFundingRequirement#ForwardAgreementWithFundingRequirement(double, double)}.
   *
   * <p>Method under test: {@link
   * ForwardAgreementWithFundingRequirement#ForwardAgreementWithFundingRequirement(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ForwardAgreementWithFundingRequirement.<init>(double, double)"})
  public void testNewForwardAgreementWithFundingRequirement() {
    // Arrange, Act and Assert
    assertNull(new ForwardAgreementWithFundingRequirement(10.0d, 10.0d).getCurrency());
  }

  /**
   * Test {@link
   * ForwardAgreementWithFundingRequirement#ForwardAgreementWithFundingRequirement(double, double,
   * int, FundingCapacity)}.
   *
   * <p>Method under test: {@link
   * ForwardAgreementWithFundingRequirement#ForwardAgreementWithFundingRequirement(double, double,
   * int, FundingCapacity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ForwardAgreementWithFundingRequirement.<init>(double, double, int, FundingCapacity)"
  })
  public void testNewForwardAgreementWithFundingRequirement2() {
    // Arrange, Act and Assert
    assertNull(
        new ForwardAgreementWithFundingRequirement(10.0d, 10.0d, 1, mock(FundingCapacity.class))
            .getCurrency());
  }

  /**
   * Test {@link
   * ForwardAgreementWithFundingRequirement#ForwardAgreementWithFundingRequirement(String, double,
   * double, FundingCapacity)}.
   *
   * <p>Method under test: {@link
   * ForwardAgreementWithFundingRequirement#ForwardAgreementWithFundingRequirement(String, double,
   * double, FundingCapacity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ForwardAgreementWithFundingRequirement.<init>(String, double, double, FundingCapacity)"
  })
  public void testNewForwardAgreementWithFundingRequirement3() {
    // Arrange, Act and Assert
    assertNull(
        new ForwardAgreementWithFundingRequirement(
                "Underlying Name", 10.0d, 10.0d, mock(FundingCapacity.class))
            .getCurrency());
  }

  /**
   * Test {@link ForwardAgreementWithFundingRequirement#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link ForwardAgreementWithFundingRequirement#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ForwardAgreementWithFundingRequirement.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    FundingCapacity fundingCapacity = mock(FundingCapacity.class);
    RandomVariableFromDoubleArray survivalProbability = new RandomVariableFromDoubleArray(10.0d);
    DefaultFactors defaultFactors =
        new DefaultFactors(survivalProbability, new RandomVariableFromDoubleArray(10.0d));
    when(fundingCapacity.getDefaultFactors(anyDouble(), Mockito.<RandomVariable>any()))
        .thenReturn(defaultFactors);
    ForwardAgreementWithFundingRequirement forwardAgreementWithFundingRequirement =
        new ForwardAgreementWithFundingRequirement(10.0d, 10.0d, 1, fundingCapacity);

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getMonteCarloWeights(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(process.getTimeIndex(anyDouble())).thenReturn(1);
    when(process.getModel()).thenReturn(new BachelierModel(10.0d, 10.0d, 10.0d));
    when(process.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue =
        forwardAgreementWithFundingRequirement.getValue(10.0d, new MonteCarloAssetModel(process));

    // Assert
    verify(fundingCapacity).getDefaultFactors(eq(10.0d), isA(RandomVariable.class));
    verify(process, atLeast(1)).getMonteCarloWeights(1);
    verify(process).getProcessValue(1, 1);
    verify(process).getModel();
    verify(process, atLeast(1)).getTimeIndex(10.0d);
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
    assertEquals(0.0d, actualValue.getAverage(), 0.0);
    assertEquals(0.0d, actualValue.getMax(), 0.0);
    assertEquals(0.0d, actualValue.getMin(), 0.0);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link ForwardAgreementWithFundingRequirement#getValue(double,
   * AssetModelMonteCarloSimulationModel)} with {@code double}, {@code
   * AssetModelMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link ForwardAgreementWithFundingRequirement#getValue(double,
   * AssetModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ForwardAgreementWithFundingRequirement.getValue(double, AssetModelMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleAssetModelMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    FundingCapacity fundingCapacity = mock(FundingCapacity.class);
    RandomVariableFromDoubleArray survivalProbability = new RandomVariableFromDoubleArray(10.0d);
    DefaultFactors defaultFactors =
        new DefaultFactors(survivalProbability, RandomVariableDifferentiableAAD.of(10.0d));
    when(fundingCapacity.getDefaultFactors(anyDouble(), Mockito.<RandomVariable>any()))
        .thenReturn(defaultFactors);
    ForwardAgreementWithFundingRequirement forwardAgreementWithFundingRequirement =
        new ForwardAgreementWithFundingRequirement(10.0d, 10.0d, 1, fundingCapacity);

    ProcessModel processModel = mock(ProcessModel.class);
    when(processModel.getNumeraire(Mockito.<MonteCarloProcess>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    EulerSchemeFromProcessModel process = mock(EulerSchemeFromProcessModel.class);
    when(process.getMonteCarloWeights(anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(process.getTimeIndex(anyDouble())).thenReturn(1);
    when(process.getModel()).thenReturn(processModel);
    when(process.getProcessValue(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue =
        forwardAgreementWithFundingRequirement.getValue(10.0d, new MonteCarloAssetModel(process));

    // Assert
    verify(fundingCapacity).getDefaultFactors(eq(10.0d), isA(RandomVariable.class));
    verify(processModel, atLeast(1)).getNumeraire(isA(MonteCarloProcess.class), eq(10.0d));
    verify(process, atLeast(1)).getMonteCarloWeights(1);
    verify(process).getProcessValue(1, 1);
    verify(process).getModel();
    verify(process, atLeast(1)).getTimeIndex(10.0d);
    assertTrue(actualValue.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualValue).getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualValue.getAverage(), 0.0);
    assertEquals(0.0d, actualValue.getMax(), 0.0);
    assertEquals(0.0d, actualValue.getMin(), 0.0);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualValue).getGradient().size());
    assertEquals(1, actualValue.size());
    assertEquals(3, actualValue.getTypePriority());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }
}
