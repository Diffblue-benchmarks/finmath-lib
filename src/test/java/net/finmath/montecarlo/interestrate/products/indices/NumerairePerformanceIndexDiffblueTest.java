package net.finmath.montecarlo.interestrate.products.indices;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.Set;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.curves.DiscountCurveFromProductOfCurves;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.HullWhiteModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import net.finmath.time.daycount.DayCountConvention;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class NumerairePerformanceIndexDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NumerairePerformanceIndex#NumerairePerformanceIndex(String, String, String,
   *       BusinessdayCalendar, DateRollConvention, DayCountConvention)}
   *   <li>{@link NumerairePerformanceIndex#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NumerairePerformanceIndex.<init>(String, String, String, BusinessdayCalendar, DateRollConvention, DayCountConvention)",
    "String NumerairePerformanceIndex.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    // Act
    NumerairePerformanceIndex actualNumerairePerformanceIndex =
        new NumerairePerformanceIndex(
            "Name",
            "GBP",
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true));
    actualNumerairePerformanceIndex.toString();

    // Assert
    assertEquals("GBP", actualNumerairePerformanceIndex.getCurrency());
    assertEquals("Name", actualNumerairePerformanceIndex.getName());
  }

  /**
   * Test {@link NumerairePerformanceIndex#getValue(double, TermStructureMonteCarloSimulationModel)}
   * with {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link NumerairePerformanceIndex#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable NumerairePerformanceIndex.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();
    NumerairePerformanceIndex numerairePerformanceIndex =
        new NumerairePerformanceIndex(
            "Name",
            "GBP",
            "42",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true));

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getNumeraire(anyDouble())).thenThrow(new CalculationException("An error occurred"));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act and Assert
    assertThrows(
        CalculationException.class, () -> numerairePerformanceIndex.getValue(10.0d, model));
    verify(model).getNumeraire(10.0d);
    verify(model).getReferenceDate();
  }

  /**
   * Test {@link NumerairePerformanceIndex#getValue(double, TermStructureMonteCarloSimulationModel)}
   * with {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link NumerairePerformanceIndex#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable NumerairePerformanceIndex.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel2()
      throws CalculationException {
    // Arrange
    BusinessdayCalendarAny paymentBusinessdayCalendar = mock(BusinessdayCalendarAny.class);
    when(paymentBusinessdayCalendar.getAdjustedDate(
            Mockito.<LocalDate>any(), Mockito.<String>any(), Mockito.<DateRollConvention>any()))
        .thenReturn(LocalDate.of(1970, 1, 1));
    NumerairePerformanceIndex numerairePerformanceIndex =
        new NumerairePerformanceIndex(
            "Name",
            "GBP",
            "42",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true));

    HullWhiteModel hullWhiteModel = mock(HullWhiteModel.class);
    DiscountCurveFromProductOfCurves discountCurveFromProductOfCurves =
        new DiscountCurveFromProductOfCurves("Name", LocalDate.of(1970, 1, 1), "Curve Names");
    when(hullWhiteModel.getDiscountCurve()).thenReturn(discountCurveFromProductOfCurves);

    LIBORMonteCarloSimulationFromLIBORModel model =
        mock(LIBORMonteCarloSimulationFromLIBORModel.class);
    when(model.getModel()).thenReturn(hullWhiteModel);
    when(model.getNumeraire(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(model.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    RandomVariable actualValue = numerairePerformanceIndex.getValue(10.0d, model);

    // Assert
    verify(model).getModel();
    verify(model, atLeast(1)).getNumeraire(anyDouble());
    verify(model).getReferenceDate();
    verify(hullWhiteModel).getDiscountCurve();
    verify(paymentBusinessdayCalendar)
        .getAdjustedDate(isA(LocalDate.class), eq("42"), eq(DateRollConvention.UNADJUSTED));
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
    assertEquals(-0.0d, actualValue.getAverage(), 0.0);
    assertEquals(-0.0d, actualValue.getMax(), 0.0);
    assertEquals(-0.0d, actualValue.getMin(), 0.0);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {-0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link NumerairePerformanceIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link NumerairePerformanceIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set NumerairePerformanceIndex.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();
    NumerairePerformanceIndex numerairePerformanceIndex =
        new NumerairePerformanceIndex(
            "Name",
            "GBP",
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true));

    // Act
    Set<String> actualQueryUnderlyingsResult = numerairePerformanceIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }
}
