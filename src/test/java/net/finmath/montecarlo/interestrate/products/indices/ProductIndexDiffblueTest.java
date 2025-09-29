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
import java.util.Set;
import net.finmath.exception.CalculationException;
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

public class ProductIndexDiffblueTest {
  /**
   * Test {@link ProductIndex#ProductIndex(AbstractIndex, AbstractIndex)}.
   *
   * <p>Method under test: {@link ProductIndex#ProductIndex(AbstractIndex, AbstractIndex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProductIndex.<init>(AbstractIndex, AbstractIndex)"})
  public void testNewProductIndex() {
    // Arrange
    FixedCoupon index1 = new FixedCoupon(10.0d);

    // Act
    ProductIndex actualProductIndex = new ProductIndex(index1, new FixedCoupon(10.0d));

    // Assert
    assertNull(actualProductIndex.getCurrency());
    assertNull(actualProductIndex.getName());
  }

  /**
   * Test {@link ProductIndex#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link ProductIndex#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ProductIndex.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    FixedCoupon index1 = new FixedCoupon(10.0d);
    ProductIndex productIndex = new ProductIndex(index1, new FixedCoupon(10.0d));

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        productIndex.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

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
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(1, actualValue.size());
    assertEquals(100.0d, actualValue.getAverage(), 0.0);
    assertEquals(100.0d, actualValue.getMax(), 0.0);
    assertEquals(100.0d, actualValue.getMin(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {100.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link ProductIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link ProductIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set ProductIndex.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange
    FixedCoupon index1 = new FixedCoupon(10.0d);
    AnalyticModelForwardCurveIndex index2 =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);

    ProductIndex productIndex = new ProductIndex(index1, index2);

    // Act
    Set<String> actualQueryUnderlyingsResult = productIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }

  /**
   * Test {@link ProductIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link ProductIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set ProductIndex.queryUnderlyings()"})
  public void testQueryUnderlyings2() {
    // Arrange
    AnalyticModelForwardCurveIndex index1 =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);
    AnalyticModelForwardCurveIndex index2 =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);

    ProductIndex productIndex = new ProductIndex(index1, index2);

    // Act
    Set<String> actualQueryUnderlyingsResult = productIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }

  /**
   * Test {@link ProductIndex#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ProductIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set ProductIndex.queryUnderlyings()"})
  public void testQueryUnderlyings_thenReturnNull() {
    // Arrange
    FixedCoupon index1 = new FixedCoupon(10.0d);
    ProductIndex productIndex = new ProductIndex(index1, new FixedCoupon(10.0d));

    // Act and Assert
    assertNull(productIndex.queryUnderlyings());
  }
}
