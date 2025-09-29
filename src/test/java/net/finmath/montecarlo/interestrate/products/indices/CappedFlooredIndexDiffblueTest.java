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

public class CappedFlooredIndexDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CappedFlooredIndex#CappedFlooredIndex(AbstractIndex, AbstractIndex,
   *       AbstractIndex)}
   *   <li>{@link CappedFlooredIndex#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CappedFlooredIndex.<init>(AbstractIndex, AbstractIndex, AbstractIndex)",
    "String CappedFlooredIndex.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    FixedCoupon index = new FixedCoupon(10.0d);
    FixedCoupon cap = new FixedCoupon(10.0d);

    // Act
    CappedFlooredIndex actualCappedFlooredIndex =
        new CappedFlooredIndex(index, cap, new FixedCoupon(10.0d));

    // Assert
    assertEquals(
        "CappedFlooredIndex [index=FixedCoupon [coupon=RandomVariableFromDoubleArray[ realizations=10.0,"
            + " isDeterministic()=true, filtrationTime=-Infinity, typePriority=1], toString()=AbstractMonteCarloProduct"
            + " [currency=null]], cap=FixedCoupon [coupon=RandomVariableFromDoubleArray[ realizations=10.0,"
            + " isDeterministic()=true, filtrationTime=-Infinity, typePriority=1], toString()=AbstractMonteCarloProduct"
            + " [currency=null]], floor=FixedCoupon [coupon=RandomVariableFromDoubleArray[ realizations=10.0,"
            + " isDeterministic()=true, filtrationTime=-Infinity, typePriority=1], toString()=AbstractMonteCarloProduct"
            + " [currency=null]], toString()=AbstractMonteCarloProduct [currency=null]]",
        actualCappedFlooredIndex.toString());
    assertNull(actualCappedFlooredIndex.getCurrency());
    assertNull(actualCappedFlooredIndex.getName());
  }

  /**
   * Test {@link CappedFlooredIndex#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CappedFlooredIndex.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    FixedCoupon index = new FixedCoupon(10.0d);
    FixedCoupon cap = new FixedCoupon(10.0d);

    CappedFlooredIndex cappedFlooredIndex =
        new CappedFlooredIndex(index, cap, new FixedCoupon(10.0d));

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        cappedFlooredIndex.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

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
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link CappedFlooredIndex#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CappedFlooredIndex.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    CappedFlooredIndex cappedFlooredIndex =
        new CappedFlooredIndex(new FixedCoupon(10.0d), null, null);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        cappedFlooredIndex.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

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
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link CappedFlooredIndex#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CappedFlooredIndex.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    FixedCoupon index = new FixedCoupon(10.0d);
    FixedCoupon cap = new FixedCoupon(10.0d);

    CappedFlooredIndex index2 = new CappedFlooredIndex(index, cap, new FixedCoupon(10.0d));
    FixedCoupon cap2 = new FixedCoupon(10.0d);

    CappedFlooredIndex cappedFlooredIndex =
        new CappedFlooredIndex(index2, cap2, new FixedCoupon(10.0d));

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        cappedFlooredIndex.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

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
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link CappedFlooredIndex#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CappedFlooredIndex.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel4()
      throws CalculationException {
    // Arrange
    FixedCoupon index = new FixedCoupon(10.0d);
    FixedCoupon index2 = new FixedCoupon(10.0d);
    FixedCoupon cap = new FixedCoupon(10.0d);

    CappedFlooredIndex cap2 = new CappedFlooredIndex(index2, cap, new FixedCoupon(10.0d));

    CappedFlooredIndex cappedFlooredIndex =
        new CappedFlooredIndex(index, cap2, new FixedCoupon(10.0d));

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        cappedFlooredIndex.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

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
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link CappedFlooredIndex#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable CappedFlooredIndex.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel5()
      throws CalculationException {
    // Arrange
    FixedCoupon index = new FixedCoupon(10.0d);
    FixedCoupon cap = new FixedCoupon(10.0d);
    FixedCoupon index2 = new FixedCoupon(10.0d);
    FixedCoupon cap2 = new FixedCoupon(10.0d);

    CappedFlooredIndex floor = new CappedFlooredIndex(index2, cap2, new FixedCoupon(10.0d));

    CappedFlooredIndex cappedFlooredIndex = new CappedFlooredIndex(index, cap, floor);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        cappedFlooredIndex.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

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
    assertArrayEquals(new double[] {10.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link CappedFlooredIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set CappedFlooredIndex.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange
    FixedCoupon index = new FixedCoupon(10.0d);
    FixedCoupon cap = new FixedCoupon(10.0d);

    CappedFlooredIndex cappedFlooredIndex =
        new CappedFlooredIndex(index, cap, new FixedCoupon(10.0d));

    // Act and Assert
    assertNull(cappedFlooredIndex.queryUnderlyings());
  }

  /**
   * Test {@link CappedFlooredIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set CappedFlooredIndex.queryUnderlyings()"})
  public void testQueryUnderlyings2() {
    // Arrange
    CappedFlooredIndex cappedFlooredIndex = new CappedFlooredIndex(null, null, null);

    // Act and Assert
    assertNull(cappedFlooredIndex.queryUnderlyings());
  }

  /**
   * Test {@link CappedFlooredIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set CappedFlooredIndex.queryUnderlyings()"})
  public void testQueryUnderlyings3() {
    // Arrange
    AnalyticModelForwardCurveIndex index =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);
    FixedCoupon cap = new FixedCoupon(10.0d);

    CappedFlooredIndex cappedFlooredIndex =
        new CappedFlooredIndex(index, cap, new FixedCoupon(10.0d));

    // Act
    Set<String> actualQueryUnderlyingsResult = cappedFlooredIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }

  /**
   * Test {@link CappedFlooredIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set CappedFlooredIndex.queryUnderlyings()"})
  public void testQueryUnderlyings4() {
    // Arrange
    FixedCoupon index = new FixedCoupon(10.0d);
    FixedCoupon cap = new FixedCoupon(10.0d);

    CappedFlooredIndex index2 = new CappedFlooredIndex(index, cap, new FixedCoupon(10.0d));
    FixedCoupon cap2 = new FixedCoupon(10.0d);

    CappedFlooredIndex cappedFlooredIndex =
        new CappedFlooredIndex(index2, cap2, new FixedCoupon(10.0d));

    // Act and Assert
    assertNull(cappedFlooredIndex.queryUnderlyings());
  }

  /**
   * Test {@link CappedFlooredIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set CappedFlooredIndex.queryUnderlyings()"})
  public void testQueryUnderlyings5() {
    // Arrange
    FixedCoupon index = new FixedCoupon(10.0d);
    AnalyticModelForwardCurveIndex cap =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);

    CappedFlooredIndex cappedFlooredIndex =
        new CappedFlooredIndex(index, cap, new FixedCoupon(10.0d));

    // Act
    Set<String> actualQueryUnderlyingsResult = cappedFlooredIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }

  /**
   * Test {@link CappedFlooredIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set CappedFlooredIndex.queryUnderlyings()"})
  public void testQueryUnderlyings6() {
    // Arrange
    FixedCoupon index = new FixedCoupon(10.0d);
    FixedCoupon index2 = new FixedCoupon(10.0d);
    FixedCoupon cap = new FixedCoupon(10.0d);

    CappedFlooredIndex cap2 = new CappedFlooredIndex(index2, cap, new FixedCoupon(10.0d));

    CappedFlooredIndex cappedFlooredIndex =
        new CappedFlooredIndex(index, cap2, new FixedCoupon(10.0d));

    // Act and Assert
    assertNull(cappedFlooredIndex.queryUnderlyings());
  }

  /**
   * Test {@link CappedFlooredIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set CappedFlooredIndex.queryUnderlyings()"})
  public void testQueryUnderlyings7() {
    // Arrange
    FixedCoupon index = new FixedCoupon(10.0d);
    FixedCoupon cap = new FixedCoupon(10.0d);
    AnalyticModelForwardCurveIndex floor =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);

    CappedFlooredIndex cappedFlooredIndex = new CappedFlooredIndex(index, cap, floor);

    // Act
    Set<String> actualQueryUnderlyingsResult = cappedFlooredIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }

  /**
   * Test {@link CappedFlooredIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set CappedFlooredIndex.queryUnderlyings()"})
  public void testQueryUnderlyings8() {
    // Arrange
    FixedCoupon index = new FixedCoupon(10.0d);
    FixedCoupon cap = new FixedCoupon(10.0d);
    FixedCoupon index2 = new FixedCoupon(10.0d);
    FixedCoupon cap2 = new FixedCoupon(10.0d);

    CappedFlooredIndex floor = new CappedFlooredIndex(index2, cap2, new FixedCoupon(10.0d));

    CappedFlooredIndex cappedFlooredIndex = new CappedFlooredIndex(index, cap, floor);

    // Act and Assert
    assertNull(cappedFlooredIndex.queryUnderlyings());
  }

  /**
   * Test {@link CappedFlooredIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set CappedFlooredIndex.queryUnderlyings()"})
  public void testQueryUnderlyings9() {
    // Arrange
    AnalyticModelForwardCurveIndex index =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);
    AnalyticModelForwardCurveIndex cap =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);

    CappedFlooredIndex cappedFlooredIndex =
        new CappedFlooredIndex(index, cap, new FixedCoupon(10.0d));

    // Act
    Set<String> actualQueryUnderlyingsResult = cappedFlooredIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }

  /**
   * Test {@link CappedFlooredIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link CappedFlooredIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set CappedFlooredIndex.queryUnderlyings()"})
  public void testQueryUnderlyings10() {
    // Arrange
    AnalyticModelForwardCurveIndex index =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);
    FixedCoupon cap = new FixedCoupon(10.0d);
    AnalyticModelForwardCurveIndex floor =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);

    CappedFlooredIndex cappedFlooredIndex = new CappedFlooredIndex(index, cap, floor);

    // Act
    Set<String> actualQueryUnderlyingsResult = cappedFlooredIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }
}
