package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SimpleCappedFlooredFloatingRateBondDiffblueTest {
  /**
   * Test {@link SimpleCappedFlooredFloatingRateBond#SimpleCappedFlooredFloatingRateBond(String,
   * double[], double[], double[], double[], double[], double)}.
   *
   * <p>Method under test: {@link
   * SimpleCappedFlooredFloatingRateBond#SimpleCappedFlooredFloatingRateBond(String, double[],
   * double[], double[], double[], double[], double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleCappedFlooredFloatingRateBond.<init>(String, double[], double[], double[], double[], double[], double)"
  })
  public void testNewSimpleCappedFlooredFloatingRateBond() {
    // Arrange and Act
    SimpleCappedFlooredFloatingRateBond actualSimpleCappedFlooredFloatingRateBond =
        new SimpleCappedFlooredFloatingRateBond(
            "GBP",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d);

    // Assert
    assertEquals("GBP", actualSimpleCappedFlooredFloatingRateBond.getCurrency());
  }

  /**
   * Test {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleCappedFlooredFloatingRateBond.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    SimpleCappedFlooredFloatingRateBond simpleCappedFlooredFloatingRateBond =
        new SimpleCappedFlooredFloatingRateBond(
            "GBP",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenThrow(new CalculationException("An error occurred"));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(
        CalculationException.class,
        () -> simpleCappedFlooredFloatingRateBond.getValue(10.0d, model));
    verify(model).getForwardRate(10.0d, 10.0d, 10.0d);
    verify(model).getRandomVariableForConstant(0.0d);
  }

  /**
   * Test {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleCappedFlooredFloatingRateBond.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    SimpleCappedFlooredFloatingRateBond simpleCappedFlooredFloatingRateBond =
        new SimpleCappedFlooredFloatingRateBond(
            "GBP",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = simpleCappedFlooredFloatingRateBond.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
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
    assertArrayEquals(new double[] {20.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleCappedFlooredFloatingRateBond.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    SimpleCappedFlooredFloatingRateBond simpleCappedFlooredFloatingRateBond =
        new SimpleCappedFlooredFloatingRateBond(
            "GBP",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getNumeraire(anyDouble())).thenThrow(new CalculationException("An error occurred"));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertThrows(
        CalculationException.class,
        () -> simpleCappedFlooredFloatingRateBond.getValue(10.0d, model));
    verify(model).getForwardRate(10.0d, 10.0d, 10.0d);
    verify(model).getNumeraire(10.0d);
    verify(model).getRandomVariableForConstant(0.0d);
  }

  /**
   * Test {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleCappedFlooredFloatingRateBond.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel4()
      throws CalculationException {
    // Arrange
    SimpleCappedFlooredFloatingRateBond simpleCappedFlooredFloatingRateBond =
        new SimpleCappedFlooredFloatingRateBond(
            "GBP",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = simpleCappedFlooredFloatingRateBond.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
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
    assertArrayEquals(new double[] {20.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleCappedFlooredFloatingRateBond.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel5()
      throws CalculationException {
    // Arrange
    SimpleCappedFlooredFloatingRateBond simpleCappedFlooredFloatingRateBond =
        new SimpleCappedFlooredFloatingRateBond(
            "GBP",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = simpleCappedFlooredFloatingRateBond.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
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
    assertArrayEquals(new double[] {20.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleCappedFlooredFloatingRateBond.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel6()
      throws CalculationException {
    // Arrange
    SimpleCappedFlooredFloatingRateBond simpleCappedFlooredFloatingRateBond =
        new SimpleCappedFlooredFloatingRateBond(
            "GBP",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            10.0d);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = simpleCappedFlooredFloatingRateBond.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
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
    assertArrayEquals(new double[] {20.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleCappedFlooredFloatingRateBond.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel7()
      throws CalculationException {
    // Arrange
    SimpleCappedFlooredFloatingRateBond simpleCappedFlooredFloatingRateBond =
        new SimpleCappedFlooredFloatingRateBond(
            "GBP",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(1.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = simpleCappedFlooredFloatingRateBond.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertEquals(110.0d, actualValue.getAverage(), 0.0);
    assertEquals(110.0d, actualValue.getMax(), 0.0);
    assertEquals(110.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {110.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleCappedFlooredFloatingRateBond.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel8()
      throws CalculationException {
    // Arrange
    SimpleCappedFlooredFloatingRateBond simpleCappedFlooredFloatingRateBond =
        new SimpleCappedFlooredFloatingRateBond(
            "GBP",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(1.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = simpleCappedFlooredFloatingRateBond.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertEquals(11.0d, actualValue.getAverage(), 0.0);
    assertEquals(11.0d, actualValue.getMax(), 0.0);
    assertEquals(11.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {11.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link SimpleCappedFlooredFloatingRateBond#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable SimpleCappedFlooredFloatingRateBond.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel9()
      throws CalculationException {
    // Arrange
    SimpleCappedFlooredFloatingRateBond simpleCappedFlooredFloatingRateBond =
        new SimpleCappedFlooredFloatingRateBond(
            "GBP",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d);

    Scalar scalar = mock(Scalar.class);
    when(scalar.cap(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.floor(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.sub(anyDouble())).thenReturn(scalar2);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getForwardRate(anyDouble(), anyDouble(), anyDouble())).thenReturn(scalar3);
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = simpleCappedFlooredFloatingRateBond.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getForwardRate(anyDouble(), anyDouble(), anyDouble());
    verify(model, atLeast(1)).getMonteCarloWeights(anyDouble());
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model, atLeast(1)).getRandomVariableForConstant(anyDouble());
    verify(scalar, atLeast(1)).cap(anyDouble());
    verify(scalar2, atLeast(1)).floor(anyDouble());
    verify(scalar3, atLeast(1)).sub(anyDouble());
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertEquals(20.0d, actualValue.getAverage(), 0.0);
    assertEquals(20.0d, actualValue.getMax(), 0.0);
    assertEquals(20.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {20.0d}, actualValue.getRealizations(), 0.0);
  }
}
