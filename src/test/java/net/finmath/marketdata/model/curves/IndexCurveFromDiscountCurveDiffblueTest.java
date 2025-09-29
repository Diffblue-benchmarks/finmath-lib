package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class IndexCurveFromDiscountCurveDiffblueTest {
  /**
   * Test {@link IndexCurveFromDiscountCurve#IndexCurveFromDiscountCurve(String, double,
   * DiscountCurve)}.
   *
   * <p>Method under test: {@link IndexCurveFromDiscountCurve#IndexCurveFromDiscountCurve(String,
   * double, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void IndexCurveFromDiscountCurve.<init>(String, double, DiscountCurve)"})
  public void testNewIndexCurveFromDiscountCurve() {
    // Arrange and Act
    IndexCurveFromDiscountCurve actualIndexCurveFromDiscountCurve =
        new IndexCurveFromDiscountCurve(
            "Name", 10.0d, new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    assertEquals("Name", actualIndexCurveFromDiscountCurve.getName());
    assertNull(actualIndexCurveFromDiscountCurve.getParameter());
    assertNull(actualIndexCurveFromDiscountCurve.getReferenceDate());
  }

  /**
   * Test {@link IndexCurveFromDiscountCurve#getParameter()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link IndexCurveFromDiscountCurve#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] IndexCurveFromDiscountCurve.getParameter()"})
  public void testGetParameter_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        new IndexCurveFromDiscountCurve(
                "Name", 10.0d, new DiscountCurveFromForwardCurve("Forward Curve Name"))
            .getParameter());
  }

  /**
   * Test {@link IndexCurveFromDiscountCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <ul>
   *   <li>Then return {@code 0.9999999999999998}.
   * </ul>
   *
   * <p>Method under test: {@link IndexCurveFromDiscountCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double IndexCurveFromDiscountCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturn09999999999999998() {
    // Arrange
    DiscountCurveInterpolation discountCurve =
        DiscountCurveInterpolation.createDiscountCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    IndexCurveFromDiscountCurve indexCurveFromDiscountCurve =
        new IndexCurveFromDiscountCurve("Name", 10.0d, discountCurve);

    // Act and Assert
    assertEquals(
        0.9999999999999998d,
        indexCurveFromDiscountCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link IndexCurveFromDiscountCurve#getCloneBuilder()}.
   *
   * <p>Method under test: {@link IndexCurveFromDiscountCurve#getCloneBuilder()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.marketdata.model.curves.CurveBuilder IndexCurveFromDiscountCurve.getCloneBuilder()"
  })
  public void testGetCloneBuilder() throws CloneNotSupportedException {
    // Arrange, Act and Assert
    assertThrows(
        CloneNotSupportedException.class,
        () ->
            new IndexCurveFromDiscountCurve(
                    "Name", 10.0d, new DiscountCurveFromForwardCurve("Forward Curve Name"))
                .getCloneBuilder());
  }
}
