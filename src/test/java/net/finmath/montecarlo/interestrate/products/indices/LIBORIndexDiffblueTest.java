package net.finmath.montecarlo.interestrate.products.indices;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LIBORIndexDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LIBORIndex#LIBORIndex(String, String, String, BusinessdayCalendar,
   *       DateRollConvention)}
   *   <li>{@link LIBORIndex#toString()}
   *   <li>{@link LIBORIndex#getPeriodLength()}
   *   <li>{@link LIBORIndex#getPeriodStartOffset()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORIndex.<init>(String, String, String, BusinessdayCalendar, DateRollConvention)",
    "double LIBORIndex.getPeriodLength()",
    "double LIBORIndex.getPeriodStartOffset()",
    "String LIBORIndex.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    LIBORIndex actualLiborIndex =
        new LIBORIndex(
            "Name",
            "GBP",
            "Payment Offset Code",
            new BusinessdayCalendarAny(),
            DateRollConvention.UNADJUSTED);
    String actualToStringResult = actualLiborIndex.toString();
    double actualPeriodLength = actualLiborIndex.getPeriodLength();
    double actualPeriodStartOffset = actualLiborIndex.getPeriodStartOffset();

    // Assert
    assertEquals("GBP", actualLiborIndex.getCurrency());
    assertEquals(
        "LIBORIndex [periodStartOffset=0.0, periodLength=NaN, toString()=AbstractMonteCarloProduct"
            + " [currency=GBP]]",
        actualToStringResult);
    assertEquals("Name", actualLiborIndex.getName());
    assertEquals(0.0d, actualPeriodStartOffset, 0.0);
    assertEquals(Double.NaN, actualPeriodLength, 0.0);
  }

  /**
   * Test {@link LIBORIndex#LIBORIndex(double, double)}.
   *
   * <p>Method under test: {@link LIBORIndex#LIBORIndex(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LIBORIndex.<init>(double, double)"})
  public void testNewLIBORIndex() {
    // Arrange and Act
    LIBORIndex actualLiborIndex = new LIBORIndex(10.0d, 10.0d);

    // Assert
    assertNull(actualLiborIndex.getCurrency());
    assertNull(actualLiborIndex.getName());
    assertEquals(10.0d, actualLiborIndex.getPeriodLength(), 0.0);
    assertEquals(10.0d, actualLiborIndex.getPeriodStartOffset(), 0.0);
  }

  /**
   * Test {@link LIBORIndex#LIBORIndex(String, double, double)}.
   *
   * <p>Method under test: {@link LIBORIndex#LIBORIndex(String, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LIBORIndex.<init>(String, double, double)"})
  public void testNewLIBORIndex2() {
    // Arrange and Act
    LIBORIndex actualLiborIndex = new LIBORIndex("Name", 10.0d, 10.0d);

    // Assert
    assertEquals("Name", actualLiborIndex.getName());
    assertNull(actualLiborIndex.getCurrency());
    assertEquals(10.0d, actualLiborIndex.getPeriodLength(), 0.0);
    assertEquals(10.0d, actualLiborIndex.getPeriodStartOffset(), 0.0);
  }

  /**
   * Test {@link LIBORIndex#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link LIBORIndex#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORIndex.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    LIBORIndex liborIndex = new LIBORIndex(10.0d, 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = liborIndex.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
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
    assertEquals(10, actualValue.size());
    assertEquals(10.0d, actualValue.getFiltrationTime(), 0.0);
    assertFalse(actualValue.isDeterministic());
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test {@link LIBORIndex#getPeriodLength(TermStructureMonteCarloSimulationModel, double)} with
   * {@code TermStructureMonteCarloSimulationModel}, {@code double}.
   *
   * <p>Method under test: {@link LIBORIndex#getPeriodLength(TermStructureMonteCarloSimulationModel,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double LIBORIndex.getPeriodLength(TermStructureMonteCarloSimulationModel, double)"
  })
  public void testGetPeriodLengthWithTermStructureMonteCarloSimulationModelDouble() {
    // Arrange
    LIBORIndex liborIndex = new LIBORIndex(10.0d, 10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    double actualPeriodLength =
        liborIndex.getPeriodLength(new LIBORMonteCarloSimulationFromLIBORModel(process), 10.0d);

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(10.0d, actualPeriodLength, 0.0);
  }

  /**
   * Test {@link LIBORIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link LIBORIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set LIBORIndex.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange and Act
    Set<String> actualQueryUnderlyingsResult = new LIBORIndex(10.0d, 10.0d).queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains(null));
  }
}
