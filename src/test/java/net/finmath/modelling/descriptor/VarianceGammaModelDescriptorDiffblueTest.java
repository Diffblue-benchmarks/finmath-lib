package net.finmath.modelling.descriptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class VarianceGammaModelDescriptorDiffblueTest {
  /**
   * Test {@link VarianceGammaModelDescriptor#VarianceGammaModelDescriptor(LocalDate, Double,
   * DiscountCurve, DiscountCurve, double, double, double)}.
   *
   * <p>Method under test: {@link
   * VarianceGammaModelDescriptor#VarianceGammaModelDescriptor(LocalDate, Double, DiscountCurve,
   * DiscountCurve, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void VarianceGammaModelDescriptor.<init>(LocalDate, Double, DiscountCurve, DiscountCurve, double, double, double)"
  })
  public void testNewVarianceGammaModelDescriptor() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    VarianceGammaModelDescriptor actualVarianceGammaModelDescriptor =
        new VarianceGammaModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            10.0d,
            10.0d,
            10.0d);

    // Assert
    assertEquals(10.0d, actualVarianceGammaModelDescriptor.getInitialValue().doubleValue(), 0.0);
    assertEquals(10.0d, actualVarianceGammaModelDescriptor.getNu(), 0.0);
    assertEquals(10.0d, actualVarianceGammaModelDescriptor.getSigma(), 0.0);
    assertEquals(10.0d, actualVarianceGammaModelDescriptor.getTheta(), 0.0);
    assertSame(
        discountCurveForDiscountRate,
        actualVarianceGammaModelDescriptor.getDiscountCurveForDiscountRate());
    assertSame(
        discountCurveForForwardRate,
        actualVarianceGammaModelDescriptor.getDiscountCurveForForwardRate());
    assertSame(referenceDate, actualVarianceGammaModelDescriptor.getReferenceDate());
  }

  /**
   * Test {@link VarianceGammaModelDescriptor#version()}.
   *
   * <p>Method under test: {@link VarianceGammaModelDescriptor#version()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Integer VarianceGammaModelDescriptor.version()"})
  public void testVersion() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertEquals(
        1,
        new VarianceGammaModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                10.0d,
                10.0d)
            .version()
            .intValue());
  }

  /**
   * Test {@link VarianceGammaModelDescriptor#name()}.
   *
   * <p>Method under test: {@link VarianceGammaModelDescriptor#name()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String VarianceGammaModelDescriptor.name()"})
  public void testName() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertEquals(
        "Single asset Variance Gamma model",
        new VarianceGammaModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                10.0d,
                10.0d)
            .name());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link VarianceGammaModelDescriptor#getDiscountCurveForDiscountRate()}
   *   <li>{@link VarianceGammaModelDescriptor#getDiscountCurveForForwardRate()}
   *   <li>{@link VarianceGammaModelDescriptor#getInitialValue()}
   *   <li>{@link VarianceGammaModelDescriptor#getNu()}
   *   <li>{@link VarianceGammaModelDescriptor#getReferenceDate()}
   *   <li>{@link VarianceGammaModelDescriptor#getSigma()}
   *   <li>{@link VarianceGammaModelDescriptor#getTheta()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DiscountCurve VarianceGammaModelDescriptor.getDiscountCurveForDiscountRate()",
    "DiscountCurve VarianceGammaModelDescriptor.getDiscountCurveForForwardRate()",
    "Double VarianceGammaModelDescriptor.getInitialValue()",
    "double VarianceGammaModelDescriptor.getNu()",
    "LocalDate VarianceGammaModelDescriptor.getReferenceDate()",
    "double VarianceGammaModelDescriptor.getSigma()",
    "double VarianceGammaModelDescriptor.getTheta()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    VarianceGammaModelDescriptor varianceGammaModelDescriptor =
        new VarianceGammaModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            10.0d,
            10.0d,
            10.0d);

    // Act
    DiscountCurve actualDiscountCurveForDiscountRate =
        varianceGammaModelDescriptor.getDiscountCurveForDiscountRate();
    DiscountCurve actualDiscountCurveForForwardRate =
        varianceGammaModelDescriptor.getDiscountCurveForForwardRate();
    Double actualInitialValue = varianceGammaModelDescriptor.getInitialValue();
    double actualNu = varianceGammaModelDescriptor.getNu();
    LocalDate actualReferenceDate = varianceGammaModelDescriptor.getReferenceDate();
    double actualSigma = varianceGammaModelDescriptor.getSigma();
    double actualTheta = varianceGammaModelDescriptor.getTheta();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals(10.0d, actualInitialValue.doubleValue(), 0.0);
    assertEquals(10.0d, actualNu, 0.0);
    assertEquals(10.0d, actualSigma, 0.0);
    assertEquals(10.0d, actualTheta, 0.0);
    assertSame(discountCurveForDiscountRate, actualDiscountCurveForDiscountRate);
    assertSame(discountCurveForForwardRate, actualDiscountCurveForForwardRate);
    assertSame(referenceDate, actualReferenceDate);
  }
}
