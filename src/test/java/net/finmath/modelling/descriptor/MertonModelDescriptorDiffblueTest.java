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

public class MertonModelDescriptorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MertonModelDescriptor#MertonModelDescriptor(LocalDate, Double, DiscountCurve,
   *       DiscountCurve, Double, Double, Double, Double)}
   *   <li>{@link MertonModelDescriptor#getDiscountCurveForDiscountRate()}
   *   <li>{@link MertonModelDescriptor#getDiscountCurveForForwardRate()}
   *   <li>{@link MertonModelDescriptor#getInitialValue()}
   *   <li>{@link MertonModelDescriptor#getJumpIntensity()}
   *   <li>{@link MertonModelDescriptor#getJumpSizeMean()}
   *   <li>{@link MertonModelDescriptor#getJumpSizeStdDev()}
   *   <li>{@link MertonModelDescriptor#getReferenceDate()}
   *   <li>{@link MertonModelDescriptor#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MertonModelDescriptor.<init>(LocalDate, Double, DiscountCurve, DiscountCurve, Double, Double, Double, Double)",
    "DiscountCurve MertonModelDescriptor.getDiscountCurveForDiscountRate()",
    "DiscountCurve MertonModelDescriptor.getDiscountCurveForForwardRate()",
    "Double MertonModelDescriptor.getInitialValue()",
    "Double MertonModelDescriptor.getJumpIntensity()",
    "Double MertonModelDescriptor.getJumpSizeMean()",
    "Double MertonModelDescriptor.getJumpSizeStdDev()",
    "LocalDate MertonModelDescriptor.getReferenceDate()",
    "Double MertonModelDescriptor.getVolatility()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    MertonModelDescriptor actualMertonModelDescriptor =
        new MertonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    DiscountCurve actualDiscountCurveForDiscountRate =
        actualMertonModelDescriptor.getDiscountCurveForDiscountRate();
    DiscountCurve actualDiscountCurveForForwardRate =
        actualMertonModelDescriptor.getDiscountCurveForForwardRate();
    Double actualInitialValue = actualMertonModelDescriptor.getInitialValue();
    Double actualJumpIntensity = actualMertonModelDescriptor.getJumpIntensity();
    Double actualJumpSizeMean = actualMertonModelDescriptor.getJumpSizeMean();
    Double actualJumpSizeStdDev = actualMertonModelDescriptor.getJumpSizeStdDev();
    LocalDate actualReferenceDate = actualMertonModelDescriptor.getReferenceDate();
    Double actualVolatility = actualMertonModelDescriptor.getVolatility();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals(10.0d, actualInitialValue.doubleValue(), 0.0);
    assertEquals(10.0d, actualJumpIntensity.doubleValue(), 0.0);
    assertEquals(10.0d, actualJumpSizeMean.doubleValue(), 0.0);
    assertEquals(10.0d, actualJumpSizeStdDev.doubleValue(), 0.0);
    assertEquals(10.0d, actualVolatility.doubleValue(), 0.0);
    assertSame(discountCurveForDiscountRate, actualDiscountCurveForDiscountRate);
    assertSame(discountCurveForForwardRate, actualDiscountCurveForForwardRate);
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link MertonModelDescriptor#version()}.
   *
   * <p>Method under test: {@link MertonModelDescriptor#version()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Integer MertonModelDescriptor.version()"})
  public void testVersion() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertEquals(
        1,
        new MertonModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                10.0d,
                10.0d,
                10.0d)
            .version()
            .intValue());
  }

  /**
   * Test {@link MertonModelDescriptor#name()}.
   *
   * <p>Method under test: {@link MertonModelDescriptor#name()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String MertonModelDescriptor.name()"})
  public void testName() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertEquals(
        "Single asset Merton Jump Diffusion model",
        new MertonModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                10.0d,
                10.0d,
                10.0d)
            .name());
  }
}
