package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractCurveDiffblueTest {
  /**
   * Test {@link AbstractCurve#getName()}.
   *
   * <p>Method under test: {@link AbstractCurve#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractCurve.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals(
        "DiscountCurveFromForwardCurve(Forward Curve Name)",
        new DiscountCurveFromForwardCurve("Forward Curve Name").getName());
  }

  /**
   * Test {@link AbstractCurve#getReferenceDate()}.
   *
   * <p>Method under test: {@link AbstractCurve#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate AbstractCurve.getReferenceDate()"})
  public void testGetReferenceDate() {
    // Arrange, Act and Assert
    assertNull(new DiscountCurveFromForwardCurve("Forward Curve Name").getReferenceDate());
  }

  /**
   * Test {@link AbstractCurve#getValue(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then return {@code 3.651178219040306E-63}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCurve#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractCurve.getValue(double)"})
  public void testGetValueWithDouble_thenReturn3651178219040306e63() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurve =
        new ForwardCurveNelsonSiegelSvensson(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            referenceDate,
            "42",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);

    // Act and Assert
    assertEquals(
        3.651178219040306E-63d,
        new DiscountCurveFromForwardCurve(forwardCurve).getValue(10.0d),
        0.0);
  }

  /**
   * Test {@link AbstractCurve#getValue(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then return {@code 2755.7319223985905}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCurve#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractCurve.getValue(double)"})
  public void testGetValueWithDouble_thenReturn27557319223985905() {
    // Arrange, Act and Assert
    assertEquals(
        2755.7319223985905d,
        new DiscountCurveFromForwardCurve(
                ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
                    "(?<=[0-9|\\.])(?=[A-Z|a-z])",
                    new double[] {1.0d, 10.0d, 1.0d, 10.0d},
                    new double[] {1.0d, 10.0d, 1.0d, 10.0d},
                    1.0d))
            .getValue(10.0d),
        0.0);
  }

  /**
   * Test {@link AbstractCurve#getValues(double[])}.
   *
   * <p>Method under test: {@link AbstractCurve#getValues(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] AbstractCurve.getValues(double[])"})
  public void testGetValues() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson forwardCurve =
        new ForwardCurveNelsonSiegelSvensson(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            referenceDate,
            "42",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d},
            1.0d);

    // Act
    double[] actualValues =
        new DiscountCurveFromForwardCurve(forwardCurve)
            .getValues(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertArrayEquals(
        new double[] {
          3.651178219040306E-63d,
          7.302356438080612E-62d,
          3.651178219040306E-63d,
          7.302356438080612E-62d
        },
        actualValues,
        0.0);
  }

  /**
   * Test {@link AbstractCurve#getValues(double[])}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@code 2755.7319223985905} and one.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCurve#getValues(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] AbstractCurve.getValues(double[])"})
  public void testGetValues_thenReturnArrayOfDoubleWith27557319223985905AndOne() {
    // Arrange and Act
    double[] actualValues =
        new DiscountCurveFromForwardCurve(
                ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
                    "(?<=[0-9|\\.])(?=[A-Z|a-z])",
                    new double[] {1.0d, 10.0d, 1.0d, 10.0d},
                    new double[] {1.0d, 10.0d, 1.0d, 10.0d},
                    1.0d))
            .getValues(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertArrayEquals(
        new double[] {2755.7319223985905d, 1.0d, 2755.7319223985905d, 1.0d}, actualValues, 0.0);
  }

  /**
   * Test {@link AbstractCurve#getValues(double[])}.
   *
   * <ul>
   *   <li>When empty array of {@code double}.
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCurve#getValues(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] AbstractCurve.getValues(double[])"})
  public void testGetValues_whenEmptyArrayOfDouble_thenReturnEmptyArrayOfDouble() {
    // Arrange and Act
    double[] actualValues =
        new DiscountCurveFromForwardCurve("Forward Curve Name").getValues(new double[] {});

    // Assert
    assertArrayEquals(new double[] {}, actualValues, 0.0);
  }

  /**
   * Test {@link AbstractCurve#clone()}.
   *
   * <p>Method under test: {@link AbstractCurve#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AbstractCurve AbstractCurve.clone()"})
  public void testClone() throws CloneNotSupportedException {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    AbstractCurve actualCloneResult = discountCurveFromForwardCurve.clone();

    // Assert
    assertTrue(actualCloneResult instanceof DiscountCurveFromForwardCurve);
    assertEquals(discountCurveFromForwardCurve, actualCloneResult);
  }

  /**
   * Test {@link AbstractCurve#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link AbstractCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.marketdata.model.curves.Curve AbstractCurve.getCloneForParameter(double[])"
  })
  public void testGetCloneForParameter() throws CloneNotSupportedException {
    // Arrange, Act and Assert
    assertThrows(
        CloneNotSupportedException.class,
        () ->
            new DiscountCurveFromForwardCurve("Forward Curve Name")
                .getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d}));
  }

  /**
   * Test {@link AbstractCurve#toString()}.
   *
   * <p>Method under test: {@link AbstractCurve#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractCurve.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "AbstractCurve [name=DiscountCurveFromForwardCurve(Forward Curve Name), referenceDate=null]",
        new DiscountCurveFromForwardCurve("Forward Curve Name").toString());
  }
}
