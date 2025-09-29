package net.finmath.marketdata.model.volatilities;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.volatilities.VolatilitySurface.QuotingConvention;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractVolatilitySurfaceDiffblueTest {
  /**
   * Test {@link AbstractVolatilitySurface#getName()}.
   *
   * <p>Method under test: {@link AbstractVolatilitySurface#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractVolatilitySurface.getName()"})
  public void testGetName() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals("Name", capletVolatilitiesParametric.getName());
  }

  /**
   * Test {@link AbstractVolatilitySurface#getReferenceDate()}.
   *
   * <p>Method under test: {@link AbstractVolatilitySurface#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate AbstractVolatilitySurface.getReferenceDate()"})
  public void testGetReferenceDate() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric("Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    LocalDate actualReferenceDate = capletVolatilitiesParametric.getReferenceDate();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link AbstractVolatilitySurface#clone()}.
   *
   * <p>Method under test: {@link AbstractVolatilitySurface#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AbstractVolatilitySurface.clone()"})
  public void testClone() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric("Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    Object actualCloneResult = capletVolatilitiesParametric.clone();

    // Assert
    assertTrue(actualCloneResult instanceof CapletVolatilitiesParametric);
    LocalDate referenceDate2 =
        ((CapletVolatilitiesParametric) actualCloneResult).getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", ((CapletVolatilitiesParametric) actualCloneResult).getName());
    assertNull(((CapletVolatilitiesParametric) actualCloneResult).getDiscountCurve());
    assertNull(((CapletVolatilitiesParametric) actualCloneResult).getForwardCurve());
    assertNull(((CapletVolatilitiesParametric) actualCloneResult).getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL,
        ((CapletVolatilitiesParametric) actualCloneResult).getQuotingConvention());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        ((CapletVolatilitiesParametric) actualCloneResult).getParameter(),
        0.0);
  }

  /**
   * Test {@link AbstractVolatilitySurface#getQuotingConvention()}.
   *
   * <p>Method under test: {@link AbstractVolatilitySurface#getQuotingConvention()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QuotingConvention AbstractVolatilitySurface.getQuotingConvention()"})
  public void testGetQuotingConvention() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL, capletVolatilitiesParametric.getQuotingConvention());
  }

  /**
   * Test {@link AbstractVolatilitySurface#convertFromTo(AnalyticModel, double, double, double,
   * QuotingConvention, QuotingConvention)} with {@code model}, {@code optionMaturity}, {@code
   * optionStrike}, {@code value}, {@code fromQuotingConvention}, {@code toQuotingConvention}.
   *
   * <p>Method under test: {@link AbstractVolatilitySurface#convertFromTo(AnalyticModel, double,
   * double, double, QuotingConvention, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractVolatilitySurface.convertFromTo(AnalyticModel, double, double, double, QuotingConvention, QuotingConvention)"
  })
  public void
      testConvertFromToWithModelOptionMaturityOptionStrikeValueFromQuotingConventionToQuotingConvention() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        10.0d,
        capletVolatilitiesParametric.convertFromTo(
            new AnalyticModelFromCurvesAndVols(),
            10.0d,
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link AbstractVolatilitySurface#convertFromTo(AnalyticModel, double, double, double,
   * QuotingConvention, QuotingConvention)} with {@code model}, {@code optionMaturity}, {@code
   * optionStrike}, {@code value}, {@code fromQuotingConvention}, {@code toQuotingConvention}.
   *
   * <p>Method under test: {@link AbstractVolatilitySurface#convertFromTo(AnalyticModel, double,
   * double, double, QuotingConvention, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractVolatilitySurface.convertFromTo(AnalyticModel, double, double, double, QuotingConvention, QuotingConvention)"
  })
  public void
      testConvertFromToWithModelOptionMaturityOptionStrikeValueFromQuotingConventionToQuotingConvention2() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolatilitiesParametric.convertFromTo(
                new AnalyticModelFromCurvesAndVols(),
                0.0d,
                0.0d,
                0.0d,
                QuotingConvention.VOLATILITYNORMAL,
                QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link AbstractVolatilitySurface#convertFromTo(AnalyticModel, double, double, double,
   * QuotingConvention, QuotingConvention)} with {@code model}, {@code optionMaturity}, {@code
   * optionStrike}, {@code value}, {@code fromQuotingConvention}, {@code toQuotingConvention}.
   *
   * <p>Method under test: {@link AbstractVolatilitySurface#convertFromTo(AnalyticModel, double,
   * double, double, QuotingConvention, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractVolatilitySurface.convertFromTo(AnalyticModel, double, double, double, QuotingConvention, QuotingConvention)"
  })
  public void
      testConvertFromToWithModelOptionMaturityOptionStrikeValueFromQuotingConventionToQuotingConvention3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name",
            referenceDate,
            null,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolatilitiesParametric.convertFromTo(
                new AnalyticModelFromCurvesAndVols(),
                0.0d,
                0.0d,
                0.0d,
                QuotingConvention.VOLATILITYNORMAL,
                QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link AbstractVolatilitySurface#convertFromTo(double, double, double, QuotingConvention,
   * QuotingConvention)} with {@code optionMaturity}, {@code optionStrike}, {@code value}, {@code
   * fromQuotingConvention}, {@code toQuotingConvention}.
   *
   * <p>Method under test: {@link AbstractVolatilitySurface#convertFromTo(double, double, double,
   * QuotingConvention, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractVolatilitySurface.convertFromTo(double, double, double, QuotingConvention, QuotingConvention)"
  })
  public void
      testConvertFromToWithOptionMaturityOptionStrikeValueFromQuotingConventionToQuotingConvention() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        10.0d,
        capletVolatilitiesParametric.convertFromTo(
            10.0d,
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link AbstractVolatilitySurface#convertFromTo(double, double, double, QuotingConvention,
   * QuotingConvention)} with {@code optionMaturity}, {@code optionStrike}, {@code value}, {@code
   * fromQuotingConvention}, {@code toQuotingConvention}.
   *
   * <p>Method under test: {@link AbstractVolatilitySurface#convertFromTo(double, double, double,
   * QuotingConvention, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractVolatilitySurface.convertFromTo(double, double, double, QuotingConvention, QuotingConvention)"
  })
  public void
      testConvertFromToWithOptionMaturityOptionStrikeValueFromQuotingConventionToQuotingConvention2() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolatilitiesParametric.convertFromTo(
                10.0d,
                10.0d,
                10.0d,
                QuotingConvention.VOLATILITYNORMAL,
                QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link AbstractVolatilitySurface#convertFromTo(double, double, double, QuotingConvention,
   * QuotingConvention)} with {@code optionMaturity}, {@code optionStrike}, {@code value}, {@code
   * fromQuotingConvention}, {@code toQuotingConvention}.
   *
   * <p>Method under test: {@link AbstractVolatilitySurface#convertFromTo(double, double, double,
   * QuotingConvention, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AbstractVolatilitySurface.convertFromTo(double, double, double, QuotingConvention, QuotingConvention)"
  })
  public void
      testConvertFromToWithOptionMaturityOptionStrikeValueFromQuotingConventionToQuotingConvention3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name",
            referenceDate,
            null,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            capletVolatilitiesParametric.convertFromTo(
                10.0d,
                10.0d,
                10.0d,
                QuotingConvention.VOLATILITYNORMAL,
                QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link AbstractVolatilitySurface#getForwardCurve()}.
   *
   * <p>Method under test: {@link AbstractVolatilitySurface#getForwardCurve()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.marketdata.model.curves.ForwardCurve AbstractVolatilitySurface.getForwardCurve()"
  })
  public void testGetForwardCurve() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertNull(capletVolatilitiesParametric.getForwardCurve());
  }

  /**
   * Test {@link AbstractVolatilitySurface#getDiscountCurve()}.
   *
   * <p>Method under test: {@link AbstractVolatilitySurface#getDiscountCurve()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.marketdata.model.curves.DiscountCurve AbstractVolatilitySurface.getDiscountCurve()"
  })
  public void testGetDiscountCurve() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertNull(capletVolatilitiesParametric.getDiscountCurve());
  }

  /**
   * Test {@link AbstractVolatilitySurface#getDaycountConvention()}.
   *
   * <p>Method under test: {@link AbstractVolatilitySurface#getDaycountConvention()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.daycount.DayCountConvention AbstractVolatilitySurface.getDaycountConvention()"
  })
  public void testGetDaycountConvention() {
    // Arrange
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertNull(capletVolatilitiesParametric.getDaycountConvention());
  }
}
