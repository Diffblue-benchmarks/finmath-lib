package net.finmath.montecarlo.interestrate.models.funding;

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

public class FundingCapacityWithoutMemoryDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FundingCapacityWithoutMemory#FundingCapacityWithoutMemory(String, RandomVariable,
   *       SortedMap)}
   *   <li>{@link FundingCapacityWithoutMemory#getCurrentFundingLevel()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FundingCapacityWithoutMemory.<init>(String, RandomVariable, SortedMap)",
    "RandomVariable FundingCapacityWithoutMemory.getCurrentFundingLevel()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableFromDoubleArray intialCapacity = new RandomVariableFromDoubleArray(10.0d);

    // Act
    FundingCapacityWithoutMemory actualFundingCapacityWithoutMemory =
        new FundingCapacityWithoutMemory("GBP", intialCapacity, new TreeMap<>());
    RandomVariable actualCurrentFundingLevel =
        actualFundingCapacityWithoutMemory.getCurrentFundingLevel();

    // Assert
    assertEquals("GBP", actualFundingCapacityWithoutMemory.getCurrency());
    assertSame(intialCapacity, actualCurrentFundingLevel);
  }

  /**
   * Test {@link FundingCapacityWithoutMemory#getDefaultFactors(double, RandomVariable)}.
   *
   * <p>Method under test: {@link FundingCapacityWithoutMemory#getDefaultFactors(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DefaultFactors FundingCapacityWithoutMemory.getDefaultFactors(double, RandomVariable)"
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
    FundingCapacityWithoutMemory fundingCapacityWithoutMemory =
        new FundingCapacityWithoutMemory("GBP", intialCapacity, new TreeMap<>());

    // Act
    DefaultFactors actualDefaultFactors =
        fundingCapacityWithoutMemory.getDefaultFactors(
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
   * Test {@link FundingCapacityWithoutMemory#getDefaultFactors(double, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableAAD} {@link RandomVariableAAD#cap(double)} throw {@link
   *       IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link FundingCapacityWithoutMemory#getDefaultFactors(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DefaultFactors FundingCapacityWithoutMemory.getDefaultFactors(double, RandomVariable)"
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

    FundingCapacityWithoutMemory fundingCapacityWithoutMemory =
        new FundingCapacityWithoutMemory("GBP", intialCapacity, instantaneouseSurvivalProbability);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            fundingCapacityWithoutMemory.getDefaultFactors(
                10.0d, new RandomVariableFromDoubleArray(10.0d)));
    verify(intialCapacity).add(isA(RandomVariable.class));
    verify(randomVariableAAD).cap(100.0d);
    verify(intialCapacity).cap(isA(RandomVariable.class));
    verify(intialCapacity).floor(isA(RandomVariable.class));
  }

  /**
   * Test {@link FundingCapacityWithoutMemory#getDefaultFactors(double, RandomVariable)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link FundingCapacityWithoutMemory#getDefaultFactors(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DefaultFactors FundingCapacityWithoutMemory.getDefaultFactors(double, RandomVariable)"
  })
  public void testGetDefaultFactors_givenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    RandomVariableFromDoubleArray intialCapacity = new RandomVariableFromDoubleArray(10.0d);
    FundingCapacityWithoutMemory fundingCapacityWithoutMemory =
        new FundingCapacityWithoutMemory("GBP", intialCapacity, new TreeMap<>());

    // Act
    DefaultFactors actualDefaultFactors =
        fundingCapacityWithoutMemory.getDefaultFactors(
            10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualDefaultFactors.getDefaultCompensation() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualDefaultFactors.getSurvivalProbability() instanceof RandomVariableFromDoubleArray);
  }

  /**
   * Test {@link FundingCapacityWithoutMemory#getDefaultFactors(double, RandomVariable)}.
   *
   * <ul>
   *   <li>Then calls {@link RandomVariableAAD#invert()}.
   * </ul>
   *
   * <p>Method under test: {@link FundingCapacityWithoutMemory#getDefaultFactors(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DefaultFactors FundingCapacityWithoutMemory.getDefaultFactors(double, RandomVariable)"
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

    FundingCapacityWithoutMemory fundingCapacityWithoutMemory =
        new FundingCapacityWithoutMemory("GBP", intialCapacity, instantaneouseSurvivalProbability);

    // Act
    DefaultFactors actualDefaultFactors =
        fundingCapacityWithoutMemory.getDefaultFactors(
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
   * Test {@link FundingCapacityWithoutMemory#getDefaultFactors(double, RandomVariable)}.
   *
   * <ul>
   *   <li>When {@code -1.7976931348623157E308}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link FundingCapacityWithoutMemory#getDefaultFactors(double,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DefaultFactors FundingCapacityWithoutMemory.getDefaultFactors(double, RandomVariable)"
  })
  public void testGetDefaultFactors_when17976931348623157e308_thenThrowIllegalStateException() {
    // Arrange
    TreeMap<Double, Double> instantaneouseSurvivalProbability = new TreeMap<>();
    instantaneouseSurvivalProbability.put(10.0d, 10.0d);
    FundingCapacityWithoutMemory fundingCapacityWithoutMemory =
        new FundingCapacityWithoutMemory(
            "GBP", mock(RandomVariableAAD.class), instantaneouseSurvivalProbability);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            fundingCapacityWithoutMemory.getDefaultFactors(
                -1.7976931348623157E308d, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link FundingCapacityWithoutMemory#queryUnderlyings()}.
   *
   * <p>Method under test: {@link FundingCapacityWithoutMemory#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set FundingCapacityWithoutMemory.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange
    RandomVariableFromDoubleArray intialCapacity = new RandomVariableFromDoubleArray(10.0d);
    FundingCapacityWithoutMemory fundingCapacityWithoutMemory =
        new FundingCapacityWithoutMemory("GBP", intialCapacity, new TreeMap<>());

    // Act and Assert
    assertNull(fundingCapacityWithoutMemory.queryUnderlyings());
  }

  /**
   * Test {@link FundingCapacityWithoutMemory#getValue(double,
   * TermStructureMonteCarloSimulationModel)} with {@code double}, {@code
   * TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FundingCapacityWithoutMemory#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FundingCapacityWithoutMemory.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    RandomVariableFromDoubleArray intialCapacity = new RandomVariableFromDoubleArray(10.0d);
    FundingCapacityWithoutMemory fundingCapacityWithoutMemory =
        new FundingCapacityWithoutMemory("GBP", intialCapacity, new TreeMap<>());

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            fundingCapacityWithoutMemory.getValue(
                10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process)));
    verify(brownianMotion).getTimeDiscretization();
  }
}
