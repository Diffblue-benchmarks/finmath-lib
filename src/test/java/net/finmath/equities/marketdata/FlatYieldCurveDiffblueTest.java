package net.finmath.equities.marketdata;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.curves.CurveInterpolation;
import net.finmath.marketdata.model.curves.CurveInterpolation.Builder;
import net.finmath.marketdata.model.curves.CurveInterpolation.ExtrapolationMethod;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationEntity;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationMethod;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.time.daycount.DayCountConvention;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FlatYieldCurveDiffblueTest {
  /**
   * Test {@link FlatYieldCurve#FlatYieldCurve(LocalDate, double, DayCountConvention)}.
   *
   * <p>Method under test: {@link FlatYieldCurve#FlatYieldCurve(LocalDate, double,
   * DayCountConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FlatYieldCurve.<init>(LocalDate, double, DayCountConvention)"})
  public void testNewFlatYieldCurve() throws CloneNotSupportedException {
    // Arrange
    LocalDate curveDate = LocalDate.of(1970, 1, 1);

    // Act
    FlatYieldCurve actualFlatYieldCurve =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true));

    // Assert
    DiscountCurveInterpolation discountCurveInterpolation = actualFlatYieldCurve.baseCurve;
    assertTrue(discountCurveInterpolation.getCloneBuilder() instanceof Builder);
    assertTrue(actualFlatYieldCurve.dayCounter instanceof DayCountConvention_30E_360);
    assertEquals("1970-01-01", actualFlatYieldCurve.referenceDate.toString());
    assertEquals("NONE", discountCurveInterpolation.getName());
    assertEquals(1, discountCurveInterpolation.getPoints().size());
    assertEquals(1, actualFlatYieldCurve.discountDates.length);
    assertEquals(ExtrapolationMethod.CONSTANT, discountCurveInterpolation.getExtrapolationMethod());
    assertEquals(
        InterpolationEntity.LOG_OF_VALUE_PER_TIME,
        discountCurveInterpolation.getInterpolationEntity());
    assertEquals(InterpolationMethod.LINEAR, discountCurveInterpolation.getInterpolationMethod());
    assertSame(actualFlatYieldCurve.referenceDate, discountCurveInterpolation.getReferenceDate());
    assertArrayEquals(new double[] {}, discountCurveInterpolation.getParameter(), 0.0);
    assertArrayEquals(new double[] {100.0d}, discountCurveInterpolation.getTimes(), 0.0);
  }

  /**
   * Test {@link FlatYieldCurve#rollToDate(LocalDate)}.
   *
   * <ul>
   *   <li>Then {@link YieldCurve#baseCurve} CloneBuilder return {@link Builder}.
   * </ul>
   *
   * <p>Method under test: {@link FlatYieldCurve#rollToDate(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FlatYieldCurve FlatYieldCurve.rollToDate(LocalDate)"})
  public void testRollToDate_thenBaseCurveCloneBuilderReturnBuilder()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate curveDate = LocalDate.ofYearDay(1, 1);

    // Act
    FlatYieldCurve actualRollToDateResult =
        new FlatYieldCurve(curveDate, 10.0d, new DayCountConvention_30E_360(true))
            .rollToDate(LocalDate.of(1970, 1, 1));

    // Assert
    DiscountCurveInterpolation discountCurveInterpolation = actualRollToDateResult.baseCurve;
    assertTrue(discountCurveInterpolation.getCloneBuilder() instanceof Builder);
    assertTrue(actualRollToDateResult.dayCounter instanceof DayCountConvention_30E_360);
    assertEquals("1970-01-01", actualRollToDateResult.referenceDate.toString());
    assertEquals("NONE", discountCurveInterpolation.getName());
    assertEquals(1, discountCurveInterpolation.getPoints().size());
    assertEquals(1, actualRollToDateResult.discountDates.length);
    assertEquals(ExtrapolationMethod.CONSTANT, discountCurveInterpolation.getExtrapolationMethod());
    assertEquals(
        InterpolationEntity.LOG_OF_VALUE_PER_TIME,
        discountCurveInterpolation.getInterpolationEntity());
    assertEquals(InterpolationMethod.LINEAR, discountCurveInterpolation.getInterpolationMethod());
    assertSame(actualRollToDateResult.referenceDate, discountCurveInterpolation.getReferenceDate());
    assertArrayEquals(new double[] {}, discountCurveInterpolation.getParameter(), 0.0);
    assertArrayEquals(new double[] {100.0d}, discountCurveInterpolation.getTimes(), 0.0);
  }
}
