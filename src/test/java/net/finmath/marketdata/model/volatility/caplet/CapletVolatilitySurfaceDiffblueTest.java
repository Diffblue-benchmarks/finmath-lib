package net.finmath.marketdata.model.volatility.caplet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.marketdata.model.volatilities.VolatilitySurface;
import net.finmath.marketdata.model.volatilities.VolatilitySurface.QuotingConvention;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CapletVolatilitySurfaceDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CapletVolatilitySurface#CapletVolatilitySurface(String, LocalDate, double[][],
   *       double[], double[], ForwardCurve, QuotingConvention, DiscountCurve)}
   *   <li>{@link CapletVolatilitySurface#getDiscountCurve()}
   *   <li>{@link CapletVolatilitySurface#getForwardCurve()}
   *   <li>{@link CapletVolatilitySurface#getName()}
   *   <li>{@link CapletVolatilitySurface#getQuotingConvention()}
   *   <li>{@link CapletVolatilitySurface#getReferenceDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolatilitySurface.<init>(String, LocalDate, double[][], double[], double[], ForwardCurve, QuotingConvention, DiscountCurve)",
    "DiscountCurve CapletVolatilitySurface.getDiscountCurve()",
    "ForwardCurve CapletVolatilitySurface.getForwardCurve()",
    "String CapletVolatilitySurface.getName()",
    "QuotingConvention CapletVolatilitySurface.getQuotingConvention()",
    "LocalDate CapletVolatilitySurface.getReferenceDate()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    double[][] volatilityMatrix = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    CapletVolatilitySurface actualCapletVolatilitySurface =
        new CapletVolatilitySurface(
            "Name",
            referenceDate,
            volatilityMatrix,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            forwardCurve,
            QuotingConvention.VOLATILITYLOGNORMAL,
            discountCurve);
    DiscountCurve actualDiscountCurve = actualCapletVolatilitySurface.getDiscountCurve();
    ForwardCurve actualForwardCurve = actualCapletVolatilitySurface.getForwardCurve();
    String actualName = actualCapletVolatilitySurface.getName();
    QuotingConvention actualQuotingConvention =
        actualCapletVolatilitySurface.getQuotingConvention();
    LocalDate actualReferenceDate = actualCapletVolatilitySurface.getReferenceDate();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals("Name", actualName);
    assertEquals(QuotingConvention.VOLATILITYLOGNORMAL, actualQuotingConvention);
    assertSame(discountCurve, actualDiscountCurve);
    assertSame(forwardCurve, actualForwardCurve);
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link CapletVolatilitySurface#CapletVolatilitySurface(String, LocalDate, double,
   * double[], double[], ForwardCurve, QuotingConvention, DiscountCurve)}.
   *
   * <p>Method under test: {@link CapletVolatilitySurface#CapletVolatilitySurface(String, LocalDate,
   * double, double[], double[], ForwardCurve, QuotingConvention, DiscountCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CapletVolatilitySurface.<init>(String, LocalDate, double, double[], double[], ForwardCurve, QuotingConvention, DiscountCurve)"
  })
  public void testNewCapletVolatilitySurface() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    CapletVolatilitySurface actualCapletVolatilitySurface =
        new CapletVolatilitySurface(
            "Name",
            referenceDate,
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            forwardCurve,
            QuotingConvention.VOLATILITYLOGNORMAL,
            discountCurve);

    // Assert
    DiscountCurve discountCurve2 = actualCapletVolatilitySurface.getDiscountCurve();
    assertTrue(discountCurve2 instanceof DiscountCurveFromForwardCurve);
    ForwardCurve forwardCurve2 = actualCapletVolatilitySurface.getForwardCurve();
    assertTrue(forwardCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertEquals("Name", actualCapletVolatilitySurface.getName());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL,
        actualCapletVolatilitySurface.getQuotingConvention());
    assertSame(discountCurve, discountCurve2);
    assertSame(forwardCurve, forwardCurve2);
    assertSame(referenceDate, actualCapletVolatilitySurface.getReferenceDate());
  }

  /**
   * Test {@link CapletVolatilitySurface#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link CapletVolatilitySurface#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CapletVolatilitySurface.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitySurface capletVolatilitySurface =
        new CapletVolatilitySurface(
            "Name",
            referenceDate,
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            forwardCurve,
            QuotingConvention.VOLATILITYLOGNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        10.0d,
        capletVolatilitySurface.getValue(10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitySurface#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link CapletVolatilitySurface#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CapletVolatilitySurface.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitySurface capletVolatilitySurface =
        new CapletVolatilitySurface(
            "Name",
            referenceDate,
            10.0d,
            new double[] {9.0d, 10.0d, 9.0d, 10.0d, 9.0d, 10.0d, 9.0d, 10.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            forwardCurve,
            QuotingConvention.VOLATILITYLOGNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        10.0d,
        capletVolatilitySurface.getValue(10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitySurface#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link CapletVolatilitySurface#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CapletVolatilitySurface.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitySurface capletVolatilitySurface =
        new CapletVolatilitySurface(
            "Name",
            referenceDate,
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            forwardCurve,
            QuotingConvention.VOLATILITYLOGNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        10.0d,
        capletVolatilitySurface.getValue(10.0d, 9.0d, QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitySurface#getValue(double, double, QuotingConvention)} with {@code
   * maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link CapletVolatilitySurface#getValue(double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double CapletVolatilitySurface.getValue(double, double, QuotingConvention)"})
  public void testGetValueWithMaturityStrikeQuotingConvention4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitySurface capletVolatilitySurface =
        new CapletVolatilitySurface(
            "Name",
            referenceDate,
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {9.0d, 10.0d, 9.0d, 10.0d, 9.0d, 10.0d, 9.0d, 10.0d},
            forwardCurve,
            QuotingConvention.VOLATILITYLOGNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        10.0d,
        capletVolatilitySurface.getValue(10.0d, 9.0d, QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitySurface#getValue(AnalyticModel, double, double, QuotingConvention)}
   * with {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link CapletVolatilitySurface#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitySurface.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitySurface capletVolatilitySurface =
        new CapletVolatilitySurface(
            "Name",
            referenceDate,
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            null,
            QuotingConvention.VOLATILITYNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolatilitySurface.getValue(
                new AnalyticModelFromCurvesAndVols(),
                10.0d,
                10.0d,
                QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link CapletVolatilitySurface#getValue(AnalyticModel, double, double, QuotingConvention)}
   * with {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link CapletVolatilitySurface#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitySurface.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitySurface capletVolatilitySurface =
        new CapletVolatilitySurface(
            "Name",
            referenceDate,
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            forwardCurve,
            QuotingConvention.VOLATILITYNORMAL,
            null);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolatilitySurface.getValue(
                new AnalyticModelFromCurvesAndVols(),
                10.0d,
                10.0d,
                QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link CapletVolatilitySurface#getValue(AnalyticModel, double, double, QuotingConvention)}
   * with {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link CapletVolatilitySurface#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitySurface.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitySurface capletVolatilitySurface =
        new CapletVolatilitySurface(
            "Name",
            referenceDate,
            10.0d,
            new double[] {9.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            forwardCurve,
            QuotingConvention.VOLATILITYLOGNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        10.0d,
        capletVolatilitySurface.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitySurface#getValue(AnalyticModel, double, double, QuotingConvention)}
   * with {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link CapletVolatilitySurface#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitySurface.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveInterpolation forwardCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 9.0d, 10.0d, 9.0d},
            new double[] {10.0d, 9.0d, 10.0d, 9.0d},
            10.0d);

    CapletVolatilitySurface capletVolatilitySurface =
        new CapletVolatilitySurface(
            "Name",
            referenceDate,
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            forwardCurve,
            QuotingConvention.VOLATILITYLOGNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        10.0d,
        capletVolatilitySurface.getValue(
            new AnalyticModelFromCurvesAndVols(),
            9.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitySurface#getValue(AnalyticModel, double, double, QuotingConvention)}
   * with {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilitySurface#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitySurface.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_thenReturnTen() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitySurface capletVolatilitySurface =
        new CapletVolatilitySurface(
            "Name",
            referenceDate,
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            forwardCurve,
            QuotingConvention.VOLATILITYLOGNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        10.0d,
        capletVolatilitySurface.getValue(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitySurface#getValue(AnalyticModel, double, double, QuotingConvention)}
   * with {@code model}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link CapletVolatilitySurface#getValue(AnalyticModel, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitySurface.getValue(AnalyticModel, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelMaturityStrikeQuotingConvention_whenZero_thenReturnZero() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitySurface capletVolatilitySurface =
        new CapletVolatilitySurface(
            "Name",
            referenceDate,
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            forwardCurve,
            QuotingConvention.VOLATILITYLOGNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        0.0d,
        capletVolatilitySurface.getValue(
            new AnalyticModelFromCurvesAndVols(),
            0.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitySurface#convertFromTo(AnalyticModel, double, double, double,
   * QuotingConvention, QuotingConvention)} with {@code model}, {@code optionMaturity}, {@code
   * optionStrike}, {@code value}, {@code fromQuotingConvention}, {@code toQuotingConvention}.
   *
   * <p>Method under test: {@link CapletVolatilitySurface#convertFromTo(AnalyticModel, double,
   * double, double, QuotingConvention, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitySurface.convertFromTo(AnalyticModel, double, double, double, QuotingConvention, QuotingConvention)"
  })
  public void
      testConvertFromToWithModelOptionMaturityOptionStrikeValueFromQuotingConventionToQuotingConvention() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitySurface capletVolatilitySurface =
        new CapletVolatilitySurface(
            "Name",
            referenceDate,
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            forwardCurve,
            QuotingConvention.VOLATILITYLOGNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        10.0d,
        capletVolatilitySurface.convertFromTo(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link CapletVolatilitySurface#convertFromTo(double, double, double, QuotingConvention,
   * QuotingConvention)} with {@code optionMaturity}, {@code optionStrike}, {@code value}, {@code
   * fromQuotingConvention}, {@code toQuotingConvention}.
   *
   * <p>Method under test: {@link CapletVolatilitySurface#convertFromTo(double, double, double,
   * QuotingConvention, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CapletVolatilitySurface.convertFromTo(double, double, double, QuotingConvention, QuotingConvention)"
  })
  public void
      testConvertFromToWithOptionMaturityOptionStrikeValueFromQuotingConventionToQuotingConvention() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    CapletVolatilitySurface capletVolatilitySurface =
        new CapletVolatilitySurface(
            "Name",
            referenceDate,
            10.0d,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            forwardCurve,
            QuotingConvention.VOLATILITYLOGNORMAL,
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertEquals(
        10.0d,
        capletVolatilitySurface.convertFromTo(
            10.0d,
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }
}
