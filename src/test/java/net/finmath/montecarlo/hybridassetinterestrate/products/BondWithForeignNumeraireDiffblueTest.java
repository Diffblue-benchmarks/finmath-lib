package net.finmath.montecarlo.hybridassetinterestrate.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class BondWithForeignNumeraireDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code 3}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BondWithForeignNumeraire#BondWithForeignNumeraire(String, double)}
   *   <li>{@link BondWithForeignNumeraire#getMaturity()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BondWithForeignNumeraire.<init>(String, double)",
    "void BondWithForeignNumeraire.<init>(LocalDateTime, String, double)",
    "double BondWithForeignNumeraire.getMaturity()"
  })
  public void testGettersAndSetters_when3() {
    // Arrange and Act
    BondWithForeignNumeraire actualBondWithForeignNumeraire =
        new BondWithForeignNumeraire("3", 10.0d);
    double actualMaturity = actualBondWithForeignNumeraire.getMaturity();

    // Assert
    assertNull(actualBondWithForeignNumeraire.getCurrency());
    assertEquals(10.0d, actualMaturity, 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one atStartOfDay.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BondWithForeignNumeraire#BondWithForeignNumeraire(LocalDateTime, String, double)}
   *   <li>{@link BondWithForeignNumeraire#getMaturity()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BondWithForeignNumeraire.<init>(String, double)",
    "void BondWithForeignNumeraire.<init>(LocalDateTime, String, double)",
    "double BondWithForeignNumeraire.getMaturity()"
  })
  public void testGettersAndSetters_whenLocalDateWith1970AndOneAndOneAtStartOfDay() {
    // Arrange and Act
    BondWithForeignNumeraire actualBondWithForeignNumeraire =
        new BondWithForeignNumeraire(LocalDate.of(1970, 1, 1).atStartOfDay(), "3", 10.0d);
    double actualMaturity = actualBondWithForeignNumeraire.getMaturity();

    // Assert
    assertNull(actualBondWithForeignNumeraire.getCurrency());
    assertEquals(10.0d, actualMaturity, 0.0);
  }

  /**
   * Test {@link BondWithForeignNumeraire#getValue(double, HybridAssetMonteCarloSimulation)} with
   * {@code double}, {@code HybridAssetMonteCarloSimulation}.
   *
   * <p>Method under test: {@link BondWithForeignNumeraire#getValue(double,
   * HybridAssetMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BondWithForeignNumeraire.getValue(double, HybridAssetMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetMonteCarloSimulation() throws CalculationException {
    // Arrange
    BondWithForeignNumeraire bondWithForeignNumeraire = new BondWithForeignNumeraire("3", 10.0d);

    HybridAssetMonteCarloSimulation model = mock(HybridAssetMonteCarloSimulation.class);
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(Mockito.<String>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bondWithForeignNumeraire.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getMonteCarloWeights(10.0d);
    verify(model).getRandomVariableForConstant(1.0d);
    verify(model, atLeast(1)).getNumeraire("3", 10.0d);
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
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BondWithForeignNumeraire#getValue(double, HybridAssetMonteCarloSimulation)} with
   * {@code double}, {@code HybridAssetMonteCarloSimulation}.
   *
   * <p>Method under test: {@link BondWithForeignNumeraire#getValue(double,
   * HybridAssetMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BondWithForeignNumeraire.getValue(double, HybridAssetMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetMonteCarloSimulation2() throws CalculationException {
    // Arrange
    BondWithForeignNumeraire bondWithForeignNumeraire =
        new BondWithForeignNumeraire(LocalDate.of(1970, 1, 1).atStartOfDay(), "3", 10.0d);

    HybridAssetMonteCarloSimulation model = mock(HybridAssetMonteCarloSimulation.class);
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(Mockito.<String>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bondWithForeignNumeraire.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getMonteCarloWeights(10.0d);
    verify(model).getRandomVariableForConstant(1.0d);
    verify(model).getReferenceDate();
    verify(model, atLeast(1)).getNumeraire("3", 10.0d);
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
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BondWithForeignNumeraire#getValue(double, HybridAssetMonteCarloSimulation)} with
   * {@code double}, {@code HybridAssetMonteCarloSimulation}.
   *
   * <p>Method under test: {@link BondWithForeignNumeraire#getValue(double,
   * HybridAssetMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BondWithForeignNumeraire.getValue(double, HybridAssetMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetMonteCarloSimulation3() throws CalculationException {
    // Arrange
    BondWithForeignNumeraire bondWithForeignNumeraire =
        new BondWithForeignNumeraire(LocalDate.of(1970, 1, 1).atStartOfDay(), "3", 10.0d);

    HybridAssetMonteCarloSimulation model = mock(HybridAssetMonteCarloSimulation.class);
    when(model.getReferenceDate()).thenThrow(new UnsupportedOperationException());
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(Mockito.<String>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bondWithForeignNumeraire.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getMonteCarloWeights(10.0d);
    verify(model).getRandomVariableForConstant(1.0d);
    verify(model).getReferenceDate();
    verify(model, atLeast(1)).getNumeraire("3", 10.0d);
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
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BondWithForeignNumeraire#getValue(double, HybridAssetMonteCarloSimulation)} with
   * {@code double}, {@code HybridAssetMonteCarloSimulation}.
   *
   * <ul>
   *   <li>Then return Average is {@code 3.1536E7}.
   * </ul>
   *
   * <p>Method under test: {@link BondWithForeignNumeraire#getValue(double,
   * HybridAssetMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BondWithForeignNumeraire.getValue(double, HybridAssetMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetMonteCarloSimulation_thenReturnAverageIs31536e7()
      throws CalculationException {
    // Arrange
    BondWithForeignNumeraire bondWithForeignNumeraire =
        new BondWithForeignNumeraire(LocalDate.of(1970, 1, 1).atStartOfDay(), "3", 10.0d);

    HybridAssetMonteCarloSimulation model = mock(HybridAssetMonteCarloSimulation.class);
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(3.1536E7d));
    when(model.getNumeraire(Mockito.<String>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bondWithForeignNumeraire.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getMonteCarloWeights(10.0d);
    verify(model).getRandomVariableForConstant(1.0d);
    verify(model).getReferenceDate();
    verify(model, atLeast(1)).getNumeraire("3", 10.0d);
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertEquals(3.1536E7d, actualValue.getAverage(), 0.0);
    assertEquals(3.1536E7d, actualValue.getMax(), 0.0);
    assertEquals(3.1536E7d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {3.1536E7d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link BondWithForeignNumeraire#getValue(double, HybridAssetMonteCarloSimulation)} with
   * {@code double}, {@code HybridAssetMonteCarloSimulation}.
   *
   * <ul>
   *   <li>Then return Average is ten.
   * </ul>
   *
   * <p>Method under test: {@link BondWithForeignNumeraire#getValue(double,
   * HybridAssetMonteCarloSimulation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable BondWithForeignNumeraire.getValue(double, HybridAssetMonteCarloSimulation)"
  })
  public void testGetValueWithDoubleHybridAssetMonteCarloSimulation_thenReturnAverageIsTen()
      throws CalculationException {
    // Arrange
    BondWithForeignNumeraire bondWithForeignNumeraire =
        new BondWithForeignNumeraire(LocalDate.of(1970, 1, 1).atStartOfDay(), "3", 10.0d);

    HybridAssetMonteCarloSimulation model = mock(HybridAssetMonteCarloSimulation.class);
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
    when(model.getMonteCarloWeights(anyDouble()))
        .thenReturn(RandomVariableDifferentiableAAD.of(3.1536E7d));
    when(model.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getNumeraire(Mockito.<String>any(), anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    RandomVariable actualValue = bondWithForeignNumeraire.getValue(10.0d, model);

    // Assert
    verify(model, atLeast(1)).getMonteCarloWeights(10.0d);
    verify(model).getRandomVariableForConstant(1.0d);
    verify(model).getReferenceDate();
    verify(model, atLeast(1)).getNumeraire("3", 10.0d);
    assertTrue(actualValue instanceof RandomVariableDifferentiableAAD);
    assertEquals(10.0d, actualValue.getAverage(), 0.0);
    assertEquals(10.0d, actualValue.getMax(), 0.0);
    assertEquals(10.0d, actualValue.getMin(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }
}
