package net.finmath.montecarlo.hybridassetinterestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.hybridassetinterestrate.HybridAssetMonteCarloSimulation;
import net.finmath.montecarlo.hybridassetinterestrate.RiskFactorID;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ForwardRateAgreementGeneralizedDiffblueTest {
  /**
   * Test {@link ForwardRateAgreementGeneralized#ForwardRateAgreementGeneralized(String, double,
   * double, double)}.
   *
   * <p>Method under test: {@link
   * ForwardRateAgreementGeneralized#ForwardRateAgreementGeneralized(String, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ForwardRateAgreementGeneralized.<init>(String, double, double, double)"})
  public void testNewForwardRateAgreementGeneralized() {
    // Arrange and Act
    ForwardRateAgreementGeneralized actualForwardRateAgreementGeneralized =
        new ForwardRateAgreementGeneralized("GBP", 10.0d, 10.0d, 10.0d);

    // Assert
    assertNull(actualForwardRateAgreementGeneralized.getCurrency());
  }

  /**
   * Test {@link ForwardRateAgreementGeneralized#ForwardRateAgreementGeneralized(LocalDateTime,
   * String, double, double, double)}.
   *
   * <p>Method under test: {@link
   * ForwardRateAgreementGeneralized#ForwardRateAgreementGeneralized(LocalDateTime, String, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ForwardRateAgreementGeneralized.<init>(LocalDateTime, String, double, double, double)"
  })
  public void testNewForwardRateAgreementGeneralized2() {
    // Arrange and Act
    ForwardRateAgreementGeneralized actualForwardRateAgreementGeneralized =
        new ForwardRateAgreementGeneralized(
            LocalDate.of(1970, 1, 1).atStartOfDay(), "Curve", 10.0d, 10.0d, 10.0d);

    // Assert
    assertNull(actualForwardRateAgreementGeneralized.getCurrency());
  }

  /**
   * Test {@link ForwardRateAgreementGeneralized#ForwardRateAgreementGeneralized(LocalDateTime,
   * String, double, double, double, RandomVariable, RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link
   * ForwardRateAgreementGeneralized#ForwardRateAgreementGeneralized(LocalDateTime, String, double,
   * double, double, RandomVariable, RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ForwardRateAgreementGeneralized.<init>(LocalDateTime, String, double, double, double, RandomVariable, RandomVariable, RandomVariable)"
  })
  public void testNewForwardRateAgreementGeneralized3() {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    RandomVariableFromDoubleArray spread = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray cap = new RandomVariableFromDoubleArray(10.0d);

    // Act
    ForwardRateAgreementGeneralized actualForwardRateAgreementGeneralized =
        new ForwardRateAgreementGeneralized(
            referenceDate,
            "GBP",
            10.0d,
            10.0d,
            10.0d,
            spread,
            cap,
            new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertNull(actualForwardRateAgreementGeneralized.getCurrency());
  }

  /**
   * Test {@link ForwardRateAgreementGeneralized#getValue(double, HybridAssetMonteCarloSimulation)}
   * with {@code double}, {@code HybridAssetMonteCarloSimulation}.
   *
   * <p>Method under test: {@link ForwardRateAgreementGeneralized#getValue(double,
   * HybridAssetMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ForwardRateAgreementGeneralized.getValue(double, HybridAssetMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetMonteCarloSimulation() throws CalculationException {
    // Arrange
    ForwardRateAgreementGeneralized forwardRateAgreementGeneralized =
        new ForwardRateAgreementGeneralized("GBP", 10.0d, 10.0d, 10.0d);

    HybridAssetMonteCarloSimulation model = mock(HybridAssetMonteCarloSimulation.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getValue(Mockito.<RiskFactorID>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = forwardRateAgreementGeneralized.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getMonteCarloWeights(10.0d);
    verify(model, atLeast(1)).getNumeraire(10.0d);
    verify(model, atLeast(1)).getValue(Mockito.<RiskFactorID>any(), eq(10.0d));
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
    assertArrayEquals(new double[] {100.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link ForwardRateAgreementGeneralized#getValue(double, HybridAssetMonteCarloSimulation)}
   * with {@code double}, {@code HybridAssetMonteCarloSimulation}.
   *
   * <p>Method under test: {@link ForwardRateAgreementGeneralized#getValue(double,
   * HybridAssetMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ForwardRateAgreementGeneralized.getValue(double, HybridAssetMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetMonteCarloSimulation2() throws CalculationException {
    // Arrange
    ForwardRateAgreementGeneralized forwardRateAgreementGeneralized =
        new ForwardRateAgreementGeneralized(
            LocalDate.of(1970, 1, 1).atStartOfDay(), "Curve", 10.0d, 10.0d, 10.0d);

    HybridAssetMonteCarloSimulation model = mock(HybridAssetMonteCarloSimulation.class);
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getValue(Mockito.<RiskFactorID>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = forwardRateAgreementGeneralized.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getMonteCarloWeights(10.0d);
    verify(model).getReferenceDate();
    verify(model, atLeast(1)).getNumeraire(10.0d);
    verify(model, atLeast(1)).getValue(Mockito.<RiskFactorID>any(), eq(10.0d));
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
    assertArrayEquals(new double[] {100.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link ForwardRateAgreementGeneralized#getValue(double, HybridAssetMonteCarloSimulation)}
   * with {@code double}, {@code HybridAssetMonteCarloSimulation}.
   *
   * <p>Method under test: {@link ForwardRateAgreementGeneralized#getValue(double,
   * HybridAssetMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ForwardRateAgreementGeneralized.getValue(double, HybridAssetMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetMonteCarloSimulation3() throws CalculationException {
    // Arrange
    ForwardRateAgreementGeneralized forwardRateAgreementGeneralized =
        new ForwardRateAgreementGeneralized(
            LocalDate.of(1970, 1, 1).atStartOfDay(), "Curve", 10.0d, 10.0d, 10.0d);

    HybridAssetMonteCarloSimulation model = mock(HybridAssetMonteCarloSimulation.class);
    when(model.getReferenceDate()).thenThrow(new UnsupportedOperationException());
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getValue(Mockito.<RiskFactorID>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = forwardRateAgreementGeneralized.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getMonteCarloWeights(10.0d);
    verify(model).getReferenceDate();
    verify(model, atLeast(1)).getNumeraire(10.0d);
    verify(model, atLeast(1)).getValue(Mockito.<RiskFactorID>any(), eq(10.0d));
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
    assertArrayEquals(new double[] {100.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link ForwardRateAgreementGeneralized#getValue(double, HybridAssetMonteCarloSimulation)}
   * with {@code double}, {@code HybridAssetMonteCarloSimulation}.
   *
   * <p>Method under test: {@link ForwardRateAgreementGeneralized#getValue(double,
   * HybridAssetMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ForwardRateAgreementGeneralized.getValue(double, HybridAssetMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetMonteCarloSimulation4() throws CalculationException {
    // Arrange
    LocalDateTime referenceDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    RandomVariableFromDoubleArray spread = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray cap = new RandomVariableFromDoubleArray(10.0d);

    ForwardRateAgreementGeneralized forwardRateAgreementGeneralized =
        new ForwardRateAgreementGeneralized(
            referenceDate,
            "GBP",
            10.0d,
            10.0d,
            10.0d,
            spread,
            cap,
            new RandomVariableFromDoubleArray(10.0d));

    HybridAssetMonteCarloSimulation model = mock(HybridAssetMonteCarloSimulation.class);
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getValue(Mockito.<RiskFactorID>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = forwardRateAgreementGeneralized.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getMonteCarloWeights(10.0d);
    verify(model).getReferenceDate();
    verify(model, atLeast(1)).getNumeraire(10.0d);
    verify(model, atLeast(1)).getValue(Mockito.<RiskFactorID>any(), eq(10.0d));
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
    assertArrayEquals(new double[] {100.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link ForwardRateAgreementGeneralized#getValue(double, HybridAssetMonteCarloSimulation)}
   * with {@code double}, {@code HybridAssetMonteCarloSimulation}.
   *
   * <p>Method under test: {@link ForwardRateAgreementGeneralized#getValue(double,
   * HybridAssetMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ForwardRateAgreementGeneralized.getValue(double, HybridAssetMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetMonteCarloSimulation5() throws CalculationException {
    // Arrange
    ForwardRateAgreementGeneralized forwardRateAgreementGeneralized =
        new ForwardRateAgreementGeneralized(
            LocalDate.of(1970, 1, 1).atStartOfDay(), "Curve", 10.0d, 10.0d, 10.0d);

    HybridAssetMonteCarloSimulation model = mock(HybridAssetMonteCarloSimulation.class);
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(3.1536E7d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getValue(Mockito.<RiskFactorID>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = forwardRateAgreementGeneralized.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getMonteCarloWeights(10.0d);
    verify(model).getReferenceDate();
    verify(model, atLeast(1)).getNumeraire(10.0d);
    verify(model, atLeast(1)).getValue(Mockito.<RiskFactorID>any(), eq(10.0d));
    assertTrue(actualValue.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
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
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualValue).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link ForwardRateAgreementGeneralized#getValue(double, HybridAssetMonteCarloSimulation)}
   * with {@code double}, {@code HybridAssetMonteCarloSimulation}.
   *
   * <p>Method under test: {@link ForwardRateAgreementGeneralized#getValue(double,
   * HybridAssetMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ForwardRateAgreementGeneralized.getValue(double, HybridAssetMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetMonteCarloSimulation6() throws CalculationException {
    // Arrange
    ForwardRateAgreementGeneralized forwardRateAgreementGeneralized =
        new ForwardRateAgreementGeneralized(
            LocalDate.of(1970, 1, 1).atStartOfDay(), "Curve", 10.0d, 10.0d, 10.0d);

    HybridAssetMonteCarloSimulation model = mock(HybridAssetMonteCarloSimulation.class);
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(3.1536E7d));
    when(model.getValue(Mockito.<RiskFactorID>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = forwardRateAgreementGeneralized.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getMonteCarloWeights(10.0d);
    verify(model).getReferenceDate();
    verify(model, atLeast(1)).getNumeraire(10.0d);
    verify(model, atLeast(1)).getValue(Mockito.<RiskFactorID>any(), eq(10.0d));
    assertTrue(actualValue.getValues() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
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
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.expectation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.expm1() instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualValue.variance() instanceof RandomVariableDifferentiableAAD);
    assertEquals(1, ((RandomVariableDifferentiableAAD) actualValue).getGradient().size());
    assertArrayEquals(new double[] {100.0d}, actualValue.getRealizations(), 0.0);
  }
}
