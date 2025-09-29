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

public class HestonModelDescriptorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link HestonModelDescriptor#HestonModelDescriptor(LocalDate, Double, DiscountCurve,
   *       DiscountCurve, Double, Double, Double, Double, Double)}
   *   <li>{@link HestonModelDescriptor#getDiscountCurveForDiscountRate()}
   *   <li>{@link HestonModelDescriptor#getDiscountCurveForForwardRate()}
   *   <li>{@link HestonModelDescriptor#getInitialValue()}
   *   <li>{@link HestonModelDescriptor#getKappa()}
   *   <li>{@link HestonModelDescriptor#getReferenceDate()}
   *   <li>{@link HestonModelDescriptor#getRho()}
   *   <li>{@link HestonModelDescriptor#getTheta()}
   *   <li>{@link HestonModelDescriptor#getVolatility()}
   *   <li>{@link HestonModelDescriptor#getXi()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HestonModelDescriptor.<init>(LocalDate, Double, DiscountCurve, DiscountCurve, Double, Double, Double, Double, Double)",
    "DiscountCurve HestonModelDescriptor.getDiscountCurveForDiscountRate()",
    "DiscountCurve HestonModelDescriptor.getDiscountCurveForForwardRate()",
    "Double HestonModelDescriptor.getInitialValue()",
    "Double HestonModelDescriptor.getKappa()",
    "LocalDate HestonModelDescriptor.getReferenceDate()",
    "Double HestonModelDescriptor.getRho()",
    "Double HestonModelDescriptor.getTheta()",
    "Double HestonModelDescriptor.getVolatility()",
    "Double HestonModelDescriptor.getXi()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    HestonModelDescriptor actualHestonModelDescriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    DiscountCurve actualDiscountCurveForDiscountRate =
        actualHestonModelDescriptor.getDiscountCurveForDiscountRate();
    DiscountCurve actualDiscountCurveForForwardRate =
        actualHestonModelDescriptor.getDiscountCurveForForwardRate();
    Double actualInitialValue = actualHestonModelDescriptor.getInitialValue();
    Double actualKappa = actualHestonModelDescriptor.getKappa();
    LocalDate actualReferenceDate = actualHestonModelDescriptor.getReferenceDate();
    Double actualRho = actualHestonModelDescriptor.getRho();
    Double actualTheta = actualHestonModelDescriptor.getTheta();
    Double actualVolatility = actualHestonModelDescriptor.getVolatility();
    Double actualXi = actualHestonModelDescriptor.getXi();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals(10.0d, actualInitialValue.doubleValue(), 0.0);
    assertEquals(10.0d, actualKappa.doubleValue(), 0.0);
    assertEquals(10.0d, actualRho.doubleValue(), 0.0);
    assertEquals(10.0d, actualTheta.doubleValue(), 0.0);
    assertEquals(10.0d, actualVolatility.doubleValue(), 0.0);
    assertEquals(10.0d, actualXi.doubleValue(), 0.0);
    assertSame(discountCurveForDiscountRate, actualDiscountCurveForDiscountRate);
    assertSame(discountCurveForForwardRate, actualDiscountCurveForForwardRate);
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link HestonModelDescriptor#version()}.
   *
   * <p>Method under test: {@link HestonModelDescriptor#version()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Integer HestonModelDescriptor.version()"})
  public void testVersion() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertEquals(
        1,
        new HestonModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d)
            .version()
            .intValue());
  }

  /**
   * Test {@link HestonModelDescriptor#name()}.
   *
   * <p>Method under test: {@link HestonModelDescriptor#name()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String HestonModelDescriptor.name()"})
  public void testName() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertEquals(
        "Single asset Heston model",
        new HestonModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                10.0d)
            .name());
  }
}
