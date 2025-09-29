package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.CurveInterpolation.ExtrapolationMethod;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationEntity;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationMethod;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CurveFromProductOfCurvesDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CurveFromProductOfCurves#CurveFromProductOfCurves(String, LocalDate, Curve[])}
   *   <li>{@link CurveFromProductOfCurves#setParameter(double[])}
   *   <li>{@link CurveFromProductOfCurves#getParameter()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CurveFromProductOfCurves.<init>(String, LocalDate, Curve[])",
    "double[] CurveFromProductOfCurves.getParameter()",
    "void CurveFromProductOfCurves.setParameter(double[])"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    CurveFromProductOfCurves actualCurveFromProductOfCurves =
        new CurveFromProductOfCurves(
            "Name", referenceDate, new DiscountCurveFromForwardCurve("Forward Curve Name"));
    actualCurveFromProductOfCurves.setParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    double[] actualParameter = actualCurveFromProductOfCurves.getParameter();

    // Assert
    LocalDate referenceDate2 = actualCurveFromProductOfCurves.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCurveFromProductOfCurves.getName());
    assertNull(actualParameter);
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link CurveFromProductOfCurves#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link CurveFromProductOfCurves#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CurveFromProductOfCurves.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturnTen() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d});

    CurveFromProductOfCurves curveFromProductOfCurves =
        new CurveFromProductOfCurves("Name", referenceDate, curveInterpolation);

    // Act and Assert
    assertEquals(
        10.0d, curveFromProductOfCurves.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d), 0.0);
  }

  /**
   * Test {@link CurveFromProductOfCurves#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link CurveFromProductOfCurves#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CurveFromProductOfCurves.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturnTen2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {1.0d, 10.0d, 1.0d, 10.0d},
            new double[] {1.0d, 10.0d, 1.0d, 10.0d});

    CurveFromProductOfCurves curveFromProductOfCurves =
        new CurveFromProductOfCurves("Name", referenceDate2, curveInterpolation);

    CurveFromProductOfCurves curveFromProductOfCurves2 =
        new CurveFromProductOfCurves("Name", referenceDate, curveFromProductOfCurves);

    // Act and Assert
    assertEquals(
        10.0d,
        curveFromProductOfCurves2.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link CurveFromProductOfCurves#getCloneBuilder()}.
   *
   * <p>Method under test: {@link CurveFromProductOfCurves#getCloneBuilder()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CurveBuilder CurveFromProductOfCurves.getCloneBuilder()"})
  public void testGetCloneBuilder() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveFromProductOfCurves curveFromProductOfCurves =
        new CurveFromProductOfCurves(
            "Name", referenceDate, new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertThrows(
        CloneNotSupportedException.class, () -> curveFromProductOfCurves.getCloneBuilder());
  }
}
