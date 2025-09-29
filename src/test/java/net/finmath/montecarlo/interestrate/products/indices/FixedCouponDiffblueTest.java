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

public class FixedCouponDiffblueTest {
  /**
   * Test {@link FixedCoupon#FixedCoupon(double)}.
   *
   * <p>Method under test: {@link FixedCoupon#FixedCoupon(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FixedCoupon.<init>(double)"})
  public void testNewFixedCoupon() {
    // Arrange and Act
    FixedCoupon actualFixedCoupon = new FixedCoupon(10.0d);

    // Assert
    assertTrue(actualFixedCoupon.getCoupon() instanceof RandomVariableFromDoubleArray);
    assertNull(actualFixedCoupon.getCurrency());
    assertNull(actualFixedCoupon.getName());
  }

  /**
   * Test {@link FixedCoupon#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link FixedCoupon#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable FixedCoupon.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel() {
    // Arrange
    FixedCoupon fixedCoupon = new FixedCoupon(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        fixedCoupon.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

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
    assertEquals(10.0d, actualValue.getAverage(), 0.0);
    assertEquals(10.0d, actualValue.getMax(), 0.0);
    assertEquals(10.0d, actualValue.getMin(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FixedCoupon#toString()}
   *   <li>{@link FixedCoupon#getCoupon()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable FixedCoupon.getCoupon()", "String FixedCoupon.toString()"})
  public void testGettersAndSetters() {
    // Arrange
    FixedCoupon fixedCoupon = new FixedCoupon(10.0d);

    // Act
    String actualToStringResult = fixedCoupon.toString();

    // Assert
    assertTrue(fixedCoupon.getCoupon() instanceof RandomVariableFromDoubleArray);
    assertEquals(
        "FixedCoupon [coupon=RandomVariableFromDoubleArray[ realizations=10.0, isDeterministic()=true,"
            + " filtrationTime=-Infinity, typePriority=1], toString()=AbstractMonteCarloProduct [currency=null]]",
        actualToStringResult);
  }

  /**
   * Test {@link FixedCoupon#queryUnderlyings()}.
   *
   * <p>Method under test: {@link FixedCoupon#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set FixedCoupon.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange, Act and Assert
    assertNull(new FixedCoupon(10.0d).queryUnderlyings());
  }
}
