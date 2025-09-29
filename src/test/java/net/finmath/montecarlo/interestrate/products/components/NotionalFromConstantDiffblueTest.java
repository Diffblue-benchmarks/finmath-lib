package net.finmath.montecarlo.interestrate.products.components;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NotionalFromConstantDiffblueTest {
  /**
   * Test {@link NotionalFromConstant#NotionalFromConstant(double)}.
   *
   * <p>Method under test: {@link NotionalFromConstant#NotionalFromConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NotionalFromConstant.<init>(double)"})
  public void testNewNotionalFromConstant() {
    // Arrange, Act and Assert
    assertNull(new NotionalFromConstant(10.0d).getCurrency());
  }

  /**
   * Test {@link NotionalFromConstant#NotionalFromConstant(double, String)}.
   *
   * <p>Method under test: {@link NotionalFromConstant#NotionalFromConstant(double, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NotionalFromConstant.<init>(double, String)"})
  public void testNewNotionalFromConstant2() {
    // Arrange, Act and Assert
    assertEquals("GBP", new NotionalFromConstant(10.0d, "GBP").getCurrency());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NotionalFromConstant#toString()}
   *   <li>{@link NotionalFromConstant#getCurrency()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String NotionalFromConstant.getCurrency()",
    "String NotionalFromConstant.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    NotionalFromConstant notionalFromConstant = new NotionalFromConstant(10.0d);

    // Act
    String actualToStringResult = notionalFromConstant.toString();

    // Assert
    assertEquals(
        "Notional [currency=null, notional=RandomVariableFromDoubleArray[ realizations=10.0, isDeterministic()=true,"
            + " filtrationTime=0.0, typePriority=1]]",
        actualToStringResult);
    assertNull(notionalFromConstant.getCurrency());
  }

  /**
   * Test {@link NotionalFromConstant#getNotionalAtPeriodEnd(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link NotionalFromConstant#getNotionalAtPeriodEnd(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable NotionalFromConstant.getNotionalAtPeriodEnd(AbstractPeriod, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetNotionalAtPeriodEnd() {
    // Arrange
    NotionalFromConstant notionalFromConstant = new NotionalFromConstant(10.0d);
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualNotionalAtPeriodEnd =
        notionalFromConstant.getNotionalAtPeriodEnd(
            period, new LIBORMonteCarloSimulationFromLIBORModel(process));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertTrue(actualNotionalAtPeriodEnd instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodEnd.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualNotionalAtPeriodEnd.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualNotionalAtPeriodEnd.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNotionalAtPeriodEnd.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNotionalAtPeriodEnd.getStandardError(), 0.0);
    assertEquals(0.0d, actualNotionalAtPeriodEnd.getVariance(), 0.0);
    assertEquals(1, actualNotionalAtPeriodEnd.getTypePriority());
    assertEquals(1, actualNotionalAtPeriodEnd.size());
    assertEquals(10.0d, actualNotionalAtPeriodEnd.getAverage(), 0.0);
    assertEquals(10.0d, actualNotionalAtPeriodEnd.getMax(), 0.0);
    assertEquals(10.0d, actualNotionalAtPeriodEnd.getMin(), 0.0);
    assertTrue(actualNotionalAtPeriodEnd.isDeterministic());
    assertArrayEquals(new double[] {10.0d}, actualNotionalAtPeriodEnd.getRealizations(), 0.0);
  }

  /**
   * Test {@link NotionalFromConstant#getNotionalAtPeriodStart(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link NotionalFromConstant#getNotionalAtPeriodStart(AbstractPeriod,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable NotionalFromConstant.getNotionalAtPeriodStart(AbstractPeriod, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetNotionalAtPeriodStart() {
    // Arrange
    NotionalFromConstant notionalFromConstant = new NotionalFromConstant(10.0d);
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualNotionalAtPeriodStart =
        notionalFromConstant.getNotionalAtPeriodStart(
            period, new LIBORMonteCarloSimulationFromLIBORModel(process));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertTrue(actualNotionalAtPeriodStart instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualNotionalAtPeriodStart.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualNotionalAtPeriodStart.getFiltrationTime(), 0.0);
    assertEquals(0.0d, actualNotionalAtPeriodStart.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNotionalAtPeriodStart.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNotionalAtPeriodStart.getStandardError(), 0.0);
    assertEquals(0.0d, actualNotionalAtPeriodStart.getVariance(), 0.0);
    assertEquals(1, actualNotionalAtPeriodStart.getTypePriority());
    assertEquals(1, actualNotionalAtPeriodStart.size());
    assertEquals(10.0d, actualNotionalAtPeriodStart.getAverage(), 0.0);
    assertEquals(10.0d, actualNotionalAtPeriodStart.getMax(), 0.0);
    assertEquals(10.0d, actualNotionalAtPeriodStart.getMin(), 0.0);
    assertTrue(actualNotionalAtPeriodStart.isDeterministic());
    assertArrayEquals(new double[] {10.0d}, actualNotionalAtPeriodStart.getRealizations(), 0.0);
  }
}
