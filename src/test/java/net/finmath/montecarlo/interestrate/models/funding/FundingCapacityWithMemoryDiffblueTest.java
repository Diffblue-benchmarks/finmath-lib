package net.finmath.montecarlo.interestrate.models.funding;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.SortedMap;
import java.util.TreeMap;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class FundingCapacityWithMemoryDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FundingCapacityWithMemory#FundingCapacityWithMemory(String, RandomVariable,
   *       SortedMap)}
   *   <li>{@link FundingCapacityWithMemory#getCurrentFundingLevel()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FundingCapacityWithMemory.<init>(String, RandomVariable, SortedMap)",
    "RandomVariable FundingCapacityWithMemory.getCurrentFundingLevel()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableFromDoubleArray intialCapacity = new RandomVariableFromDoubleArray(10.0d);

    // Act
    FundingCapacityWithMemory actualFundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, new TreeMap<>());
    RandomVariable actualCurrentFundingLevel =
        actualFundingCapacityWithMemory.getCurrentFundingLevel();

    // Assert
    assertEquals("GBP", actualFundingCapacityWithMemory.getCurrency());
    assertSame(intialCapacity, actualCurrentFundingLevel);
  }

  /**
   * Test {@link FundingCapacityWithMemory#getDefaultFactors(double, RandomVariable)}.
   *
   * <p>Method under test: {@link FundingCapacityWithMemory#getDefaultFactors(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DefaultFactors FundingCapacityWithMemory.getDefaultFactors(double, RandomVariable)"
  })
  public void testGetDefaultFactors() {
    // Arrange
    RandomVariableAAD intialCapacity = mock(RandomVariableAAD.class);
    when(intialCapacity.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.cap(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(intialCapacity.floor(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, new TreeMap<>());

    // Act
    DefaultFactors actualDefaultFactors =
        fundingCapacityWithMemory.getDefaultFactors(
            10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(intialCapacity).add(isA(RandomVariable.class));
    verify(intialCapacity).cap(isA(RandomVariable.class));
    verify(intialCapacity).floor(isA(RandomVariable.class));
    assertTrue(
        actualDefaultFactors.getDefaultCompensation() instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualDefaultFactors.getSurvivalProbability() instanceof RandomVariableDifferentiableAAD);
  }

  /**
   * Test {@link FundingCapacityWithMemory#getDefaultFactors(double, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableAAD} {@link RandomVariableAAD#cap(double)} throw {@link
   *       IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link FundingCapacityWithMemory#getDefaultFactors(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DefaultFactors FundingCapacityWithMemory.getDefaultFactors(double, RandomVariable)"
  })
  public void testGetDefaultFactors_givenRandomVariableAADCapThrowIllegalStateException() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.cap(anyDouble())).thenThrow(new IllegalStateException());

    RandomVariableAAD intialCapacity = mock(RandomVariableAAD.class);
    when(intialCapacity.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.cap(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.floor(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    TreeMap<Double, Double> instantaneouseSurvivalProbability = new TreeMap<>();
    instantaneouseSurvivalProbability.put(10.0d, 10.0d);

    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, instantaneouseSurvivalProbability);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            fundingCapacityWithMemory.getDefaultFactors(
                10.0d, new RandomVariableFromDoubleArray(10.0d)));
    verify(intialCapacity).add(isA(RandomVariable.class));
    verify(randomVariableAAD).cap(100.0d);
    verify(intialCapacity).cap(isA(RandomVariable.class));
    verify(intialCapacity).floor(isA(RandomVariable.class));
  }

  /**
   * Test {@link FundingCapacityWithMemory#getDefaultFactors(double, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link FundingCapacityWithMemory#getDefaultFactors(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DefaultFactors FundingCapacityWithMemory.getDefaultFactors(double, RandomVariable)"
  })
  public void testGetDefaultFactors_givenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray intialCapacity = new RandomVariableFromDoubleArray(10.0d);
    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, new TreeMap<>());

    // Act
    DefaultFactors actualDefaultFactors =
        fundingCapacityWithMemory.getDefaultFactors(
            10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualDefaultFactors.getDefaultCompensation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultFactors.getSurvivalProbability() instanceof RandomVariableFromDoubleArray);
  }

  /**
   * Test {@link FundingCapacityWithMemory#getDefaultFactors(double, RandomVariable)}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#invert()}.
   * </ul>
   *
   * <p>Method under test: {@link FundingCapacityWithMemory#getDefaultFactors(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DefaultFactors FundingCapacityWithMemory.getDefaultFactors(double, RandomVariable)"
  })
  public void testGetDefaultFactors_thenCallsInvert() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.cap(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.invert()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.cap(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD3.sub(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD2);

    RandomVariableAAD intialCapacity = mock(RandomVariableAAD.class);
    when(intialCapacity.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.cap(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.floor(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD3);

    TreeMap<Double, Double> instantaneouseSurvivalProbability = new TreeMap<>();
    instantaneouseSurvivalProbability.put(10.0d, 10.0d);

    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, instantaneouseSurvivalProbability);

    // Act
    DefaultFactors actualDefaultFactors =
        fundingCapacityWithMemory.getDefaultFactors(
            10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(intialCapacity).add(isA(RandomVariable.class));
    verify(randomVariableAAD3, atLeast(1)).cap(anyDouble());
    verify(randomVariableAAD).cap(1.7976931348623157E308d);
    verify(intialCapacity).cap(isA(RandomVariable.class));
    verify(intialCapacity).floor(isA(RandomVariable.class));
    verify(randomVariableAAD2).invert();
    verify(randomVariableAAD3).sub(isA(RandomVariable.class));
    assertTrue(
        actualDefaultFactors.getDefaultCompensation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultFactors.getSurvivalProbability() instanceof RandomVariableFromDoubleArray);
  }

  /**
   * Test {@link FundingCapacityWithMemory#getDefaultFactors(double, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@code -1.7976931348623157E308}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link FundingCapacityWithMemory#getDefaultFactors(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DefaultFactors FundingCapacityWithMemory.getDefaultFactors(double, RandomVariable)"
  })
  public void testGetDefaultFactors_when17976931348623157e308_thenThrowIllegalStateException() {
    // Arrange
    TreeMap<Double, Double> instantaneouseSurvivalProbability = new TreeMap<>();
    instantaneouseSurvivalProbability.put(10.0d, 10.0d);
    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory(
            "GBP", mock(RandomVariableAAD.class), instantaneouseSurvivalProbability);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            fundingCapacityWithMemory.getDefaultFactors(
                -1.7976931348623157E308d, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link FundingCapacityWithMemory#getDefaultCompensationForRequiredFunding(double,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link
   * FundingCapacityWithMemory#getDefaultCompensationForRequiredFunding(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FundingCapacityWithMemory.getDefaultCompensationForRequiredFunding(double, RandomVariable)"
  })
  public void testGetDefaultCompensationForRequiredFunding() {
    // Arrange
    RandomVariableFromDoubleArray intialCapacity = new RandomVariableFromDoubleArray(10.0d);
    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, new TreeMap<>());

    // Act
    RandomVariable actualDefaultCompensationForRequiredFunding =
        fundingCapacityWithMemory.getDefaultCompensationForRequiredFunding(
            10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualDefaultCompensationForRequiredFunding instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.variance()
            instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualDefaultCompensationForRequiredFunding.getRealizations(), 0.0);
  }

  /**
   * Test {@link FundingCapacityWithMemory#getDefaultCompensationForRequiredFunding(double,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link
   * FundingCapacityWithMemory#getDefaultCompensationForRequiredFunding(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FundingCapacityWithMemory.getDefaultCompensationForRequiredFunding(double, RandomVariable)"
  })
  public void testGetDefaultCompensationForRequiredFunding2() {
    // Arrange
    RandomVariableAAD intialCapacity = mock(RandomVariableAAD.class);
    when(intialCapacity.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.cap(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(intialCapacity.floor(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, new TreeMap<>());

    // Act
    RandomVariable actualDefaultCompensationForRequiredFunding =
        fundingCapacityWithMemory.getDefaultCompensationForRequiredFunding(
            10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(intialCapacity).add(isA(RandomVariable.class));
    verify(intialCapacity).cap(isA(RandomVariable.class));
    verify(intialCapacity).floor(isA(RandomVariable.class));
    assertTrue(
        actualDefaultCompensationForRequiredFunding.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDefaultCompensationForRequiredFunding)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDefaultCompensationForRequiredFunding)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDefaultCompensationForRequiredFunding)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDefaultCompensationForRequiredFunding)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDefaultCompensationForRequiredFunding)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDefaultCompensationForRequiredFunding)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualDefaultCompensationForRequiredFunding)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualDefaultCompensationForRequiredFunding instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualDefaultCompensationForRequiredFunding.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDefaultCompensationForRequiredFunding.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDefaultCompensationForRequiredFunding.getStandardError(), 0.0);
    assertEquals(0.0d, actualDefaultCompensationForRequiredFunding.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualDefaultCompensationForRequiredFunding)
            .getGradient()
            .size());
    assertEquals(1, actualDefaultCompensationForRequiredFunding.size());
    assertEquals(3, actualDefaultCompensationForRequiredFunding.getTypePriority());
    assertTrue(actualDefaultCompensationForRequiredFunding.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualDefaultCompensationForRequiredFunding.getFiltrationTime(),
        0.0);
    assertEquals(Double.NaN, actualDefaultCompensationForRequiredFunding.getAverage(), 0.0);
    assertEquals(Double.NaN, actualDefaultCompensationForRequiredFunding.getMax(), 0.0);
    assertEquals(Double.NaN, actualDefaultCompensationForRequiredFunding.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.NaN},
        actualDefaultCompensationForRequiredFunding.getRealizations(),
        0.0);
  }

  /**
   * Test {@link FundingCapacityWithMemory#getDefaultCompensationForRequiredFunding(double,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link
   * FundingCapacityWithMemory#getDefaultCompensationForRequiredFunding(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FundingCapacityWithMemory.getDefaultCompensationForRequiredFunding(double, RandomVariable)"
  })
  public void testGetDefaultCompensationForRequiredFunding3() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.cap(anyDouble())).thenThrow(new IllegalStateException());

    RandomVariableAAD intialCapacity = mock(RandomVariableAAD.class);
    when(intialCapacity.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.cap(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.floor(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    TreeMap<Double, Double> instantaneouseSurvivalProbability = new TreeMap<>();
    instantaneouseSurvivalProbability.put(10.0d, 10.0d);

    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, instantaneouseSurvivalProbability);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            fundingCapacityWithMemory.getDefaultCompensationForRequiredFunding(
                10.0d, new RandomVariableFromDoubleArray(10.0d)));
    verify(intialCapacity).add(isA(RandomVariable.class));
    verify(randomVariableAAD).cap(100.0d);
    verify(intialCapacity).cap(isA(RandomVariable.class));
    verify(intialCapacity).floor(isA(RandomVariable.class));
  }

  /**
   * Test {@link FundingCapacityWithMemory#getDefaultCompensationForRequiredFunding(double,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#sub(RandomVariable)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * FundingCapacityWithMemory#getDefaultCompensationForRequiredFunding(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FundingCapacityWithMemory.getDefaultCompensationForRequiredFunding(double, RandomVariable)"
  })
  public void testGetDefaultCompensationForRequiredFunding_thenCallsSub() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.cap(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD intialCapacity = mock(RandomVariableAAD.class);
    when(intialCapacity.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.cap(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.floor(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    TreeMap<Double, Double> instantaneouseSurvivalProbability = new TreeMap<>();
    instantaneouseSurvivalProbability.put(10.0d, 10.0d);

    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, instantaneouseSurvivalProbability);

    // Act
    RandomVariable actualDefaultCompensationForRequiredFunding =
        fundingCapacityWithMemory.getDefaultCompensationForRequiredFunding(
            10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(intialCapacity).add(isA(RandomVariable.class));
    verify(randomVariableAAD).cap(100.0d);
    verify(intialCapacity).cap(isA(RandomVariable.class));
    verify(intialCapacity).floor(isA(RandomVariable.class));
    verify(randomVariableAAD).sub(isA(RandomVariable.class));
    assertTrue(
        actualDefaultCompensationForRequiredFunding instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultCompensationForRequiredFunding.variance()
            instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualDefaultCompensationForRequiredFunding.getRealizations(), 0.0);
  }

  /**
   * Test {@link FundingCapacityWithMemory#getDefaultCompensationForRequiredFunding(double,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@code -1.7976931348623157E308}.
   * </ul>
   *
   * <p>Method under test: {@link
   * FundingCapacityWithMemory#getDefaultCompensationForRequiredFunding(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FundingCapacityWithMemory.getDefaultCompensationForRequiredFunding(double, RandomVariable)"
  })
  public void testGetDefaultCompensationForRequiredFunding_when17976931348623157e308() {
    // Arrange
    TreeMap<Double, Double> instantaneouseSurvivalProbability = new TreeMap<>();
    instantaneouseSurvivalProbability.put(10.0d, 10.0d);
    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory(
            "GBP", mock(RandomVariableAAD.class), instantaneouseSurvivalProbability);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            fundingCapacityWithMemory.getDefaultCompensationForRequiredFunding(
                -1.7976931348623157E308d, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link FundingCapacityWithMemory#getSurvivalProbabilityRequiredFunding(double,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link
   * FundingCapacityWithMemory#getSurvivalProbabilityRequiredFunding(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FundingCapacityWithMemory.getSurvivalProbabilityRequiredFunding(double, RandomVariable)"
  })
  public void testGetSurvivalProbabilityRequiredFunding() {
    // Arrange
    RandomVariableFromDoubleArray intialCapacity = new RandomVariableFromDoubleArray(10.0d);
    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, new TreeMap<>());

    // Act
    RandomVariable actualSurvivalProbabilityRequiredFunding =
        fundingCapacityWithMemory.getSurvivalProbabilityRequiredFunding(
            10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSurvivalProbabilityRequiredFunding instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.variance()
            instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualSurvivalProbabilityRequiredFunding.getRealizations(), 0.0);
  }

  /**
   * Test {@link FundingCapacityWithMemory#getSurvivalProbabilityRequiredFunding(double,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link
   * FundingCapacityWithMemory#getSurvivalProbabilityRequiredFunding(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FundingCapacityWithMemory.getSurvivalProbabilityRequiredFunding(double, RandomVariable)"
  })
  public void testGetSurvivalProbabilityRequiredFunding2() {
    // Arrange
    RandomVariableAAD intialCapacity = mock(RandomVariableAAD.class);
    when(intialCapacity.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.cap(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));
    when(intialCapacity.floor(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, new TreeMap<>());

    // Act
    RandomVariable actualSurvivalProbabilityRequiredFunding =
        fundingCapacityWithMemory.getSurvivalProbabilityRequiredFunding(
            10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(intialCapacity).add(isA(RandomVariable.class));
    verify(intialCapacity).cap(isA(RandomVariable.class));
    verify(intialCapacity).floor(isA(RandomVariable.class));
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.getValues()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSurvivalProbabilityRequiredFunding)
                .getCloneIndependent()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSurvivalProbabilityRequiredFunding)
                .getMaxAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSurvivalProbabilityRequiredFunding)
                .getMinAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSurvivalProbabilityRequiredFunding)
                .getSampleVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSurvivalProbabilityRequiredFunding)
                .getStandardDeviationAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSurvivalProbabilityRequiredFunding)
                .getStandardErrorAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        ((RandomVariableDifferentiableAAD) actualSurvivalProbabilityRequiredFunding)
                .getVarianceAsRandomVariableAAD()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualSurvivalProbabilityRequiredFunding instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.expectation()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.expm1()
            instanceof RandomVariableDifferentiableAAD);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.variance()
            instanceof RandomVariableDifferentiableAAD);
    assertEquals(0.0d, actualSurvivalProbabilityRequiredFunding.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualSurvivalProbabilityRequiredFunding.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualSurvivalProbabilityRequiredFunding.getStandardError(), 0.0);
    assertEquals(0.0d, actualSurvivalProbabilityRequiredFunding.getVariance(), 0.0);
    assertEquals(
        1,
        ((RandomVariableDifferentiableAAD) actualSurvivalProbabilityRequiredFunding)
            .getGradient()
            .size());
    assertEquals(1, actualSurvivalProbabilityRequiredFunding.size());
    assertEquals(3, actualSurvivalProbabilityRequiredFunding.getTypePriority());
    assertTrue(actualSurvivalProbabilityRequiredFunding.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualSurvivalProbabilityRequiredFunding.getFiltrationTime(),
        0.0);
    assertEquals(Double.NaN, actualSurvivalProbabilityRequiredFunding.getAverage(), 0.0);
    assertEquals(Double.NaN, actualSurvivalProbabilityRequiredFunding.getMax(), 0.0);
    assertEquals(Double.NaN, actualSurvivalProbabilityRequiredFunding.getMin(), 0.0);
    assertArrayEquals(
        new double[] {Double.NaN}, actualSurvivalProbabilityRequiredFunding.getRealizations(), 0.0);
  }

  /**
   * Test {@link FundingCapacityWithMemory#getSurvivalProbabilityRequiredFunding(double,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link
   * FundingCapacityWithMemory#getSurvivalProbabilityRequiredFunding(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FundingCapacityWithMemory.getSurvivalProbabilityRequiredFunding(double, RandomVariable)"
  })
  public void testGetSurvivalProbabilityRequiredFunding3() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.cap(anyDouble())).thenThrow(new IllegalStateException());

    RandomVariableAAD intialCapacity = mock(RandomVariableAAD.class);
    when(intialCapacity.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.cap(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.floor(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    TreeMap<Double, Double> instantaneouseSurvivalProbability = new TreeMap<>();
    instantaneouseSurvivalProbability.put(10.0d, 10.0d);

    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, instantaneouseSurvivalProbability);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            fundingCapacityWithMemory.getSurvivalProbabilityRequiredFunding(
                10.0d, new RandomVariableFromDoubleArray(10.0d)));
    verify(intialCapacity).add(isA(RandomVariable.class));
    verify(randomVariableAAD).cap(10.0d);
    verify(intialCapacity).cap(isA(RandomVariable.class));
    verify(intialCapacity).floor(isA(RandomVariable.class));
  }

  /**
   * Test {@link FundingCapacityWithMemory#getSurvivalProbabilityRequiredFunding(double,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#sub(RandomVariable)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * FundingCapacityWithMemory#getSurvivalProbabilityRequiredFunding(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FundingCapacityWithMemory.getSurvivalProbabilityRequiredFunding(double, RandomVariable)"
  })
  public void testGetSurvivalProbabilityRequiredFunding_thenCallsSub() {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.cap(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(randomVariableAAD.sub(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableAAD intialCapacity = mock(RandomVariableAAD.class);
    when(intialCapacity.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.cap(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(intialCapacity.floor(Mockito.<RandomVariable>any())).thenReturn(randomVariableAAD);

    TreeMap<Double, Double> instantaneouseSurvivalProbability = new TreeMap<>();
    instantaneouseSurvivalProbability.put(10.0d, 10.0d);

    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, instantaneouseSurvivalProbability);

    // Act
    RandomVariable actualSurvivalProbabilityRequiredFunding =
        fundingCapacityWithMemory.getSurvivalProbabilityRequiredFunding(
            10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    verify(intialCapacity).add(isA(RandomVariable.class));
    verify(randomVariableAAD).cap(10.0d);
    verify(intialCapacity).cap(isA(RandomVariable.class));
    verify(intialCapacity).floor(isA(RandomVariable.class));
    verify(randomVariableAAD).sub(isA(RandomVariable.class));
    assertTrue(actualSurvivalProbabilityRequiredFunding instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualSurvivalProbabilityRequiredFunding.variance()
            instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d}, actualSurvivalProbabilityRequiredFunding.getRealizations(), 0.0);
  }

  /**
   * Test {@link FundingCapacityWithMemory#getSurvivalProbabilityRequiredFunding(double,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>When {@code -1.7976931348623157E308}.
   * </ul>
   *
   * <p>Method under test: {@link
   * FundingCapacityWithMemory#getSurvivalProbabilityRequiredFunding(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FundingCapacityWithMemory.getSurvivalProbabilityRequiredFunding(double, RandomVariable)"
  })
  public void testGetSurvivalProbabilityRequiredFunding_when17976931348623157e308() {
    // Arrange
    TreeMap<Double, Double> instantaneouseSurvivalProbability = new TreeMap<>();
    instantaneouseSurvivalProbability.put(10.0d, 10.0d);
    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory(
            "GBP", mock(RandomVariableAAD.class), instantaneouseSurvivalProbability);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            fundingCapacityWithMemory.getSurvivalProbabilityRequiredFunding(
                -1.7976931348623157E308d, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link FundingCapacityWithMemory#queryUnderlyings()}.
   *
   * <p>Method under test: {@link FundingCapacityWithMemory#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set FundingCapacityWithMemory.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange
    RandomVariableFromDoubleArray intialCapacity = new RandomVariableFromDoubleArray(10.0d);
    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, new TreeMap<>());

    // Act and Assert
    assertNull(fundingCapacityWithMemory.queryUnderlyings());
  }

  /**
   * Test {@link FundingCapacityWithMemory#getValue(double, TermStructureMonteCarloSimulationModel)}
   * with {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FundingCapacityWithMemory#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FundingCapacityWithMemory.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    RandomVariableFromDoubleArray intialCapacity = new RandomVariableFromDoubleArray(10.0d);
    FundingCapacityWithMemory fundingCapacityWithMemory =
        new FundingCapacityWithMemory("GBP", intialCapacity, new TreeMap<>());

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            fundingCapacityWithMemory.getValue(
                10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process)));
    verify(brownianMotion).getTimeDiscretization();
  }
}
