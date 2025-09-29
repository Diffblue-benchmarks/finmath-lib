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

public class BlackScholesModelDescriptorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BlackScholesModelDescriptor#BlackScholesModelDescriptor(LocalDate, Double,
   *       DiscountCurve, DiscountCurve, Double)}
   *   <li>{@link BlackScholesModelDescriptor#getDiscountCurveForDiscountRate()}
   *   <li>{@link BlackScholesModelDescriptor#getDiscountCurveForForwardRate()}
   *   <li>{@link BlackScholesModelDescriptor#getInitialValue()}
   *   <li>{@link BlackScholesModelDescriptor#getReferenceDate()}
   *   <li>{@link BlackScholesModelDescriptor#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BlackScholesModelDescriptor.<init>(LocalDate, Double, DiscountCurve, DiscountCurve, Double)",
    "DiscountCurve BlackScholesModelDescriptor.getDiscountCurveForDiscountRate()",
    "DiscountCurve BlackScholesModelDescriptor.getDiscountCurveForForwardRate()",
    "Double BlackScholesModelDescriptor.getInitialValue()",
    "LocalDate BlackScholesModelDescriptor.getReferenceDate()",
    "Double BlackScholesModelDescriptor.getVolatility()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    BlackScholesModelDescriptor actualBlackScholesModelDescriptor =
        new BlackScholesModelDescriptor(
            referenceDate, 10.0d, discountCurveForForwardRate, discountCurveForDiscountRate, 10.0d);
    DiscountCurve actualDiscountCurveForDiscountRate =
        actualBlackScholesModelDescriptor.getDiscountCurveForDiscountRate();
    DiscountCurve actualDiscountCurveForForwardRate =
        actualBlackScholesModelDescriptor.getDiscountCurveForForwardRate();
    Double actualInitialValue = actualBlackScholesModelDescriptor.getInitialValue();
    LocalDate actualReferenceDate = actualBlackScholesModelDescriptor.getReferenceDate();
    Double actualVolatility = actualBlackScholesModelDescriptor.getVolatility();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals(10.0d, actualInitialValue.doubleValue(), 0.0);
    assertEquals(10.0d, actualVolatility.doubleValue(), 0.0);
    assertSame(discountCurveForDiscountRate, actualDiscountCurveForDiscountRate);
    assertSame(discountCurveForForwardRate, actualDiscountCurveForForwardRate);
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link BlackScholesModelDescriptor#version()}.
   *
   * <p>Method under test: {@link BlackScholesModelDescriptor#version()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Integer BlackScholesModelDescriptor.version()"})
  public void testVersion() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertEquals(
        1,
        new BlackScholesModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d)
            .version()
            .intValue());
  }

  /**
   * Test {@link BlackScholesModelDescriptor#name()}.
   *
   * <p>Method under test: {@link BlackScholesModelDescriptor#name()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String BlackScholesModelDescriptor.name()"})
  public void testName() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertEquals(
        "Single asset Black Scholes model",
        new BlackScholesModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                10.0d)
            .name());
  }
}
