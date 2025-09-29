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
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
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

public class ForwardCurveIndexDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ForwardCurveIndex#ForwardCurveIndex(ForwardCurve)}
   *   <li>{@link ForwardCurveIndex#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ForwardCurveIndex.<init>(ForwardCurve)",
    "java.lang.String ForwardCurveIndex.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    ForwardCurveIndex actualForwardCurveIndex = new ForwardCurveIndex(forwardCurve);

    // Assert
    assertEquals(
        "ForwardCurveIndex [forwardCurve=ForwardCurveFromDiscountCurve [AbstractForwardCurve [CurveFromInterp"
            + "olationPoints [points=[], pointsBeingParameters=[], interpolationMethod=LINEAR, extrapolationMethod=CONSTANT,"
            + " interpolationEntity=VALUE, rationalFunctionInterpolation=null, toString()=AbstractCurve [name"
            + "=ForwardCurveFromDiscountCurve(3,Payment Offset Code), referenceDate=1970-01-01],\n"
            + "], discountCurveName=3, paymentOffsetCode=Payment Offset Code, paymentBusinessdayCalendar=Businessda"
            + "yCalendarExcludingWeekends [baseCalendar=null], paymentDateRollConvention=FOLLOWING], referenceDisco"
            + "untCurveForForwardsName=3, daycountScaling=1.0, periodOffset=0.0]]",
        actualForwardCurveIndex.toString());
    assertNull(actualForwardCurveIndex.getCurrency());
    assertNull(actualForwardCurveIndex.getName());
  }

  /**
   * Test {@link ForwardCurveIndex#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link ForwardCurveIndex#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable ForwardCurveIndex.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel() {
    // Arrange
    ForwardCurveIndex forwardCurveIndex =
        new ForwardCurveIndex(
            ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
                "(?<=[0-9|\\.])(?=[A-Z|a-z])",
                new double[] {365.0d, 10.0d, 365.0d, 10.0d},
                new double[] {365.0d, 10.0d, 365.0d, 10.0d},
                365.0d));

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        forwardCurveIndex.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

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
    assertEquals(-0.0027397260273972603d, actualValue.getAverage(), 0.0);
    assertEquals(-0.0027397260273972603d, actualValue.getMax(), 0.0);
    assertEquals(-0.0027397260273972603d, actualValue.getMin(), 0.0);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {-0.0027397260273972603d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link ForwardCurveIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link ForwardCurveIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set ForwardCurveIndex.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act and Assert
    assertNull(new ForwardCurveIndex(forwardCurve).queryUnderlyings());
  }
}
