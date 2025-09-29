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
import java.time.LocalDate;
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
import net.finmath.time.daycount.DayCountConvention;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AccruedInterestDiffblueTest {
  /**
   * Test {@link AccruedInterest#AccruedInterest(String, String, LocalDate, LocalDate, LocalDate,
   * AbstractIndex, Double, DayCountConvention, boolean)}.
   *
   * <p>Method under test: {@link AccruedInterest#AccruedInterest(String, String, LocalDate,
   * LocalDate, LocalDate, AbstractIndex, Double, DayCountConvention, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AccruedInterest.<init>(String, String, LocalDate, LocalDate, LocalDate, AbstractIndex, Double, DayCountConvention, boolean)"
  })
  public void testNewAccruedInterest() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate periodStartDate = LocalDate.of(1970, 1, 1);
    LocalDate periodEndDate = LocalDate.of(1970, 1, 1);
    FixedCoupon index = new FixedCoupon(10.0d);

    // Act
    AccruedInterest actualAccruedInterest =
        new AccruedInterest(
            "Name",
            "GBP",
            referenceDate,
            periodStartDate,
            periodEndDate,
            index,
            10.0d,
            new DayCountConvention_30E_360(true),
            true);

    // Assert
    assertEquals("GBP", actualAccruedInterest.getCurrency());
    assertEquals("Name", actualAccruedInterest.getName());
  }

  /**
   * Test {@link AccruedInterest#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link AccruedInterest#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AccruedInterest.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate periodStartDate = LocalDate.of(1970, 1, 1);
    LocalDate periodEndDate = LocalDate.of(1970, 1, 1);
    FixedCoupon index = new FixedCoupon(10.0d);

    AccruedInterest accruedInterest =
        new AccruedInterest(
            "Name",
            "GBP",
            referenceDate,
            periodStartDate,
            periodEndDate,
            index,
            10.0d,
            new DayCountConvention_30E_360(true),
            true);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        accruedInterest.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

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
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link AccruedInterest#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link AccruedInterest#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AccruedInterest.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate periodStartDate = LocalDate.of(1970, 1, 1);
    LocalDate periodEndDate = LocalDate.of(1970, 1, 1);
    FixedCoupon index = new FixedCoupon(10.0d);

    AccruedInterest accruedInterest =
        new AccruedInterest(
            "Name",
            "GBP",
            referenceDate,
            periodStartDate,
            periodEndDate,
            index,
            10.0d,
            new DayCountConvention_30E_360(true),
            false);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        accruedInterest.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

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
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link AccruedInterest#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link AccruedInterest#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AccruedInterest.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel3()
      throws CalculationException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate periodStartDate = LocalDate.of(1970, 1, 1);
    LocalDate periodEndDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    LocalDate periodStartDate2 = LocalDate.of(1970, 1, 1);
    LocalDate periodEndDate2 = LocalDate.of(1970, 1, 1);
    FixedCoupon index = new FixedCoupon(10.0d);

    AccruedInterest index2 =
        new AccruedInterest(
            "Name",
            "GBP",
            referenceDate2,
            periodStartDate2,
            periodEndDate2,
            index,
            10.0d,
            new DayCountConvention_30E_360(true),
            true);

    AccruedInterest accruedInterest =
        new AccruedInterest(
            "Name",
            "GBP",
            referenceDate,
            periodStartDate,
            periodEndDate,
            index2,
            10.0d,
            new DayCountConvention_30E_360(true),
            true);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        accruedInterest.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

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
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link AccruedInterest#queryUnderlyings()}.
   *
   * <p>Method under test: {@link AccruedInterest#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set AccruedInterest.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate periodStartDate = LocalDate.of(1970, 1, 1);
    LocalDate periodEndDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    LocalDate periodStartDate2 = LocalDate.of(1970, 1, 1);
    LocalDate periodEndDate2 = LocalDate.of(1970, 1, 1);
    FixedCoupon index = new FixedCoupon(10.0d);

    AccruedInterest index2 =
        new AccruedInterest(
            "Name",
            "GBP",
            referenceDate2,
            periodStartDate2,
            periodEndDate2,
            index,
            10.0d,
            new DayCountConvention_30E_360(true),
            true);

    AccruedInterest accruedInterest =
        new AccruedInterest(
            "Name",
            "GBP",
            referenceDate,
            periodStartDate,
            periodEndDate,
            index2,
            10.0d,
            new DayCountConvention_30E_360(true),
            true);

    // Act and Assert
    assertNull(accruedInterest.queryUnderlyings());
  }

  /**
   * Test {@link AccruedInterest#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Given {@link FixedCoupon#FixedCoupon(double)} with coupon is ten.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AccruedInterest#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set AccruedInterest.queryUnderlyings()"})
  public void testQueryUnderlyings_givenFixedCouponWithCouponIsTen_thenReturnNull() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate periodStartDate = LocalDate.of(1970, 1, 1);
    LocalDate periodEndDate = LocalDate.of(1970, 1, 1);
    FixedCoupon index = new FixedCoupon(10.0d);

    AccruedInterest accruedInterest =
        new AccruedInterest(
            "Name",
            "GBP",
            referenceDate,
            periodStartDate,
            periodEndDate,
            index,
            10.0d,
            new DayCountConvention_30E_360(true),
            true);

    // Act and Assert
    assertNull(accruedInterest.queryUnderlyings());
  }

  /**
   * Test {@link AccruedInterest#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link AccruedInterest#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set AccruedInterest.queryUnderlyings()"})
  public void testQueryUnderlyings_thenReturnSizeIsOne() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate periodStartDate = LocalDate.of(1970, 1, 1);
    LocalDate periodEndDate = LocalDate.of(1970, 1, 1);
    AnalyticModelForwardCurveIndex index =
        new AnalyticModelForwardCurveIndex("Name", "Curve Name", 10.0d, 10.0d);

    AccruedInterest accruedInterest =
        new AccruedInterest(
            "Name",
            "GBP",
            referenceDate,
            periodStartDate,
            periodEndDate,
            index,
            10.0d,
            new DayCountConvention_30E_360(true),
            true);

    // Act
    Set<String> actualQueryUnderlyingsResult = accruedInterest.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }
}
