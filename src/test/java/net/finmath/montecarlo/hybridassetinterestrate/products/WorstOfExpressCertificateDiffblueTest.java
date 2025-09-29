package net.finmath.montecarlo.hybridassetinterestrate.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
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
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.modelling.Model;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.hybridassetinterestrate.HybridAssetLIBORModelMonteCarloSimulation;
import net.finmath.montecarlo.hybridassetinterestrate.HybridAssetLIBORModelMonteCarloSimulationFromModels;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WorstOfExpressCertificateDiffblueTest {
  /**
   * Test {@link WorstOfExpressCertificate#WorstOfExpressCertificate(double, double[], double[],
   * double[], double[], double)}.
   *
   * <p>Method under test: {@link WorstOfExpressCertificate#WorstOfExpressCertificate(double,
   * double[], double[], double[], double[], double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WorstOfExpressCertificate.<init>(double, double[], double[], double[], double[], double)"
  })
  public void testNewWorstOfExpressCertificate() {
    // Arrange and Act
    WorstOfExpressCertificate actualWorstOfExpressCertificate =
        new WorstOfExpressCertificate(
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Assert
    assertNull(actualWorstOfExpressCertificate.getValue(10.0d, (Model) null));
  }

  /**
   * Test {@link WorstOfExpressCertificate#getValue(double,
   * HybridAssetLIBORModelMonteCarloSimulation)} with {@code double}, {@code
   * HybridAssetLIBORModelMonteCarloSimulation}.
   *
   * <p>Method under test: {@link WorstOfExpressCertificate#getValue(double,
   * HybridAssetLIBORModelMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double WorstOfExpressCertificate.getValue(double, HybridAssetLIBORModelMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetLIBORModelMonteCarloSimulation()
      throws CalculationException {
    // Arrange
    WorstOfExpressCertificate worstOfExpressCertificate =
        new WorstOfExpressCertificate(
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    HybridAssetLIBORModelMonteCarloSimulationFromModels model =
        mock(HybridAssetLIBORModelMonteCarloSimulationFromModels.class);
    when(model.getAssetValue(anyDouble(), anyInt()))
        .thenThrow(new CalculationException("An error occurred"));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(
        CalculationException.class, () -> worstOfExpressCertificate.getValue(10.0d, model));
    verify(model).getAssetValue(10.0d, 0);
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
  }

  /**
   * Test {@link WorstOfExpressCertificate#getValue(double,
   * HybridAssetLIBORModelMonteCarloSimulation)} with {@code double}, {@code
   * HybridAssetLIBORModelMonteCarloSimulation}.
   *
   * <p>Method under test: {@link WorstOfExpressCertificate#getValue(double,
   * HybridAssetLIBORModelMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double WorstOfExpressCertificate.getValue(double, HybridAssetLIBORModelMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetLIBORModelMonteCarloSimulation2()
      throws CalculationException {
    // Arrange
    WorstOfExpressCertificate worstOfExpressCertificate =
        new WorstOfExpressCertificate(
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    HybridAssetLIBORModelMonteCarloSimulationFromModels model =
        mock(HybridAssetLIBORModelMonteCarloSimulationFromModels.class);
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getAssetValue(anyDouble(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    double actualValue = worstOfExpressCertificate.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getAssetValue(anyDouble(), anyInt());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    assertEquals(220.0d, actualValue, 0.0);
  }

  /**
   * Test {@link WorstOfExpressCertificate#getValue(double,
   * HybridAssetLIBORModelMonteCarloSimulation)} with {@code double}, {@code
   * HybridAssetLIBORModelMonteCarloSimulation}.
   *
   * <p>Method under test: {@link WorstOfExpressCertificate#getValue(double,
   * HybridAssetLIBORModelMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double WorstOfExpressCertificate.getValue(double, HybridAssetLIBORModelMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetLIBORModelMonteCarloSimulation3()
      throws CalculationException {
    // Arrange
    WorstOfExpressCertificate worstOfExpressCertificate =
        new WorstOfExpressCertificate(
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    HybridAssetLIBORModelMonteCarloSimulationFromModels model =
        mock(HybridAssetLIBORModelMonteCarloSimulationFromModels.class);
    when(model.getNumeraire(anyDouble())).thenThrow(new CalculationException("An error occurred"));
    when(model.getAssetValue(anyDouble(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(
        CalculationException.class, () -> worstOfExpressCertificate.getValue(10.0d, model));
    verify(model, atLeast(1)).getAssetValue(eq(10.0d), anyInt());
    verify(model).getNumeraire(10.0d);
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
  }

  /**
   * Test {@link WorstOfExpressCertificate#getValue(double,
   * HybridAssetLIBORModelMonteCarloSimulation)} with {@code double}, {@code
   * HybridAssetLIBORModelMonteCarloSimulation}.
   *
   * <p>Method under test: {@link WorstOfExpressCertificate#getValue(double,
   * HybridAssetLIBORModelMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double WorstOfExpressCertificate.getValue(double, HybridAssetLIBORModelMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetLIBORModelMonteCarloSimulation4()
      throws CalculationException {
    // Arrange
    WorstOfExpressCertificate worstOfExpressCertificate =
        new WorstOfExpressCertificate(
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    HybridAssetLIBORModelMonteCarloSimulationFromModels model =
        mock(HybridAssetLIBORModelMonteCarloSimulationFromModels.class);
    when(model.getNumeraire(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(1.0d));
    when(model.getAssetValue(anyDouble(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    double actualValue = worstOfExpressCertificate.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getAssetValue(anyDouble(), anyInt());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    assertEquals(130.0d, actualValue, 0.0);
  }

  /**
   * Test {@link WorstOfExpressCertificate#getValue(double,
   * HybridAssetLIBORModelMonteCarloSimulation)} with {@code double}, {@code
   * HybridAssetLIBORModelMonteCarloSimulation}.
   *
   * <p>Method under test: {@link WorstOfExpressCertificate#getValue(double,
   * HybridAssetLIBORModelMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double WorstOfExpressCertificate.getValue(double, HybridAssetLIBORModelMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetLIBORModelMonteCarloSimulation5()
      throws CalculationException {
    // Arrange
    WorstOfExpressCertificate worstOfExpressCertificate =
        new WorstOfExpressCertificate(
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    HybridAssetLIBORModelMonteCarloSimulationFromModels model =
        mock(HybridAssetLIBORModelMonteCarloSimulationFromModels.class);
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getAssetValue(anyDouble(), anyInt()))
        .thenReturn(RandomVariableDifferentiableAAD.of(1.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    double actualValue = worstOfExpressCertificate.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getAssetValue(anyDouble(), anyInt());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    assertEquals(110.0d, actualValue, 0.0);
  }

  /**
   * Test {@link WorstOfExpressCertificate#getValue(double,
   * HybridAssetLIBORModelMonteCarloSimulation)} with {@code double}, {@code
   * HybridAssetLIBORModelMonteCarloSimulation}.
   *
   * <p>Method under test: {@link WorstOfExpressCertificate#getValue(double,
   * HybridAssetLIBORModelMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double WorstOfExpressCertificate.getValue(double, HybridAssetLIBORModelMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetLIBORModelMonteCarloSimulation6()
      throws CalculationException {
    // Arrange
    WorstOfExpressCertificate worstOfExpressCertificate =
        new WorstOfExpressCertificate(
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    HybridAssetLIBORModelMonteCarloSimulationFromModels model =
        mock(HybridAssetLIBORModelMonteCarloSimulationFromModels.class);
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getAssetValue(anyDouble(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(1.0d));

    // Act
    double actualValue = worstOfExpressCertificate.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getAssetValue(anyDouble(), anyInt());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    assertEquals(22.0d, actualValue, 0.0);
  }

  /**
   * Test {@link WorstOfExpressCertificate#getValue(double, Model)} with {@code double}, {@code
   * Model}.
   *
   * <p>Method under test: {@link WorstOfExpressCertificate#getValue(double, Model)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object WorstOfExpressCertificate.getValue(double, Model)"})
  public void testGetValueWithDoubleModel() {
    // Arrange
    WorstOfExpressCertificate worstOfExpressCertificate =
        new WorstOfExpressCertificate(
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    // Act and Assert
    assertNull(worstOfExpressCertificate.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }
}
