package net.finmath.montecarlo.interestrate.products.indices;

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
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.products.ForwardRateVolatilitySurfaceCurvature;
import net.finmath.montecarlo.interestrate.products.components.AbstractProductComponent;
import net.finmath.montecarlo.interestrate.products.components.Choice;
import net.finmath.montecarlo.interestrate.products.components.Numeraire;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LaggedIndexDiffblueTest {
  /**
   * Test {@link LaggedIndex#LaggedIndex(AbstractProductComponent, String, BusinessdayCalendar)}.
   *
   * <ul>
   *   <li>When {@code Fixing Offset Code}.
   * </ul>
   *
   * <p>Method under test: {@link LaggedIndex#LaggedIndex(AbstractProductComponent, String,
   * BusinessdayCalendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LaggedIndex.<init>(AbstractProductComponent, double)",
    "void LaggedIndex.<init>(AbstractProductComponent, String, BusinessdayCalendar)"
  })
  public void testNewLaggedIndex_whenFixingOffsetCode() {
    // Arrange
    Numeraire index = new Numeraire();

    // Act
    LaggedIndex actualLaggedIndex =
        new LaggedIndex(index, "Fixing Offset Code", new BusinessdayCalendarAny());

    // Assert
    assertNull(actualLaggedIndex.getCurrency());
    assertNull(actualLaggedIndex.getName());
  }

  /**
   * Test {@link LaggedIndex#LaggedIndex(AbstractProductComponent, double)}.
   *
   * <ul>
   *   <li>When ten.
   * </ul>
   *
   * <p>Method under test: {@link LaggedIndex#LaggedIndex(AbstractProductComponent, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LaggedIndex.<init>(AbstractProductComponent, double)",
    "void LaggedIndex.<init>(AbstractProductComponent, String, BusinessdayCalendar)"
  })
  public void testNewLaggedIndex_whenTen() {
    // Arrange and Act
    LaggedIndex actualLaggedIndex = new LaggedIndex(new Numeraire(), 10.0d);

    // Assert
    assertNull(actualLaggedIndex.getCurrency());
    assertNull(actualLaggedIndex.getName());
  }

  /**
   * Test {@link LaggedIndex#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LaggedIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set LaggedIndex.queryUnderlyings()"})
  public void testQueryUnderlyings_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new LaggedIndex(new Numeraire(), 10.0d).queryUnderlyings());
  }

  /**
   * Test {@link LaggedIndex#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link LaggedIndex#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LaggedIndex.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature underlying1 =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);
    Choice index = new Choice(10.0d, underlying1, new ForwardRateVolatilitySurfaceCurvature(10.0d));
    LaggedIndex laggedIndex = new LaggedIndex(index, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        laggedIndex.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
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
}
