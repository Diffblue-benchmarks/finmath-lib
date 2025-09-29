package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.CurveInterpolation.ExtrapolationMethod;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationEntity;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationMethod;
import net.finmath.marketdata.model.curves.PiecewiseCurve.Builder;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarExcludingWeekends;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class PiecewiseCurveDiffblueTest {
  /**
   * Test Builder {@link Builder#Builder(PiecewiseCurve)}.
   *
   * <p>Method under test: {@link Builder#Builder(PiecewiseCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Builder.<init>(PiecewiseCurve)"})
  public void testBuilderNewBuilder() throws CloneNotSupportedException {
    // Arrange
    CurveInterpolation curve = mock(CurveInterpolation.class);
    when(curve.getName()).thenReturn("Name");
    when(curve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);
    when(curve.clone()).thenReturn(curveInterpolation);
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act
    new Builder(piecewiseCurve);

    // Assert
    verify(curve).getName();
    verify(curve).getReferenceDate();
    verify(curve).clone();
  }

  /**
   * Test Builder {@link Builder#Builder(PiecewiseCurve)}.
   *
   * <ul>
   *   <li>Then throw {@link CloneNotSupportedException}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#Builder(PiecewiseCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Builder.<init>(PiecewiseCurve)"})
  public void testBuilderNewBuilder_thenThrowCloneNotSupportedException()
      throws CloneNotSupportedException {
    // Arrange
    CurveInterpolation curve = mock(CurveInterpolation.class);
    when(curve.getName()).thenReturn("Name");
    when(curve.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    when(curve.clone()).thenThrow(new CloneNotSupportedException());
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act and Assert
    assertThrows(CloneNotSupportedException.class, () -> new Builder(piecewiseCurve));
    verify(curve).getName();
    verify(curve).getReferenceDate();
    verify(curve).clone();
  }

  /**
   * Test {@link PiecewiseCurve#PiecewiseCurve(Curve, Curve, double, double)}.
   *
   * <p>Method under test: {@link PiecewiseCurve#PiecewiseCurve(Curve, Curve, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PiecewiseCurve.<init>(Curve, Curve, double, double)"})
  public void testNewPiecewiseCurve() {
    // Arrange
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    PiecewiseCurve actualPiecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Assert
    assertTrue(actualPiecewiseCurve.getFixedPartCurve() instanceof DiscountCurveFromForwardCurve);
    assertEquals(
        "DiscountCurveFromForwardCurve(Forward Curve Name)", actualPiecewiseCurve.getName());
    assertNull(actualPiecewiseCurve.getParameter());
    assertNull(actualPiecewiseCurve.getReferenceDate());
    assertSame(curve, actualPiecewiseCurve.getBaseCurve());
  }

  /**
   * Test {@link PiecewiseCurve#PiecewiseCurve(Curve, Curve, double, double)}.
   *
   * <ul>
   *   <li>Then return ReferenceDate toString is {@code 1970-01-01}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#PiecewiseCurve(Curve, Curve, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PiecewiseCurve.<init>(Curve, Curve, double, double)"})
  public void testNewPiecewiseCurve_thenReturnReferenceDateToStringIs19700101() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveRenormalized curve =
        new DiscountCurveRenormalized(
            "Name", referenceDate, LocalDate.of(1970, 1, 1), "Base Curve Name");

    // Act
    PiecewiseCurve actualPiecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Assert
    Curve fixedPartCurve = actualPiecewiseCurve.getFixedPartCurve();
    assertTrue(fixedPartCurve instanceof DiscountCurveFromForwardCurve);
    LocalDate referenceDate2 = actualPiecewiseCurve.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("DiscountCurveFromForwardCurve(Forward Curve Name)", fixedPartCurve.getName());
    assertEquals("Name", actualPiecewiseCurve.getName());
    assertNull(fixedPartCurve.getParameter());
    assertNull(fixedPartCurve.getReferenceDate());
    assertSame(curve, actualPiecewiseCurve.getBaseCurve());
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link PiecewiseCurve#getParameter()}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] PiecewiseCurve.getParameter()"})
  public void testGetParameter() {
    // Arrange
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve("Forward Curve Name");
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act and Assert
    assertNull(piecewiseCurve.getParameter());
  }

  /**
   * Test {@link PiecewiseCurve#getParameter()}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] PiecewiseCurve.getParameter()"})
  public void testGetParameter2() {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);

    // Act and Assert
    assertNull(forwardCurveWithFixings.getParameter());
  }

  /**
   * Test {@link PiecewiseCurve#setParameter(double[])}.
   *
   * <ul>
   *   <li>Then calls {@link ForwardCurveFromDiscountCurve#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#setParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PiecewiseCurve.setParameter(double[])"})
  public void testSetParameter_thenCallsGetName() {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface = mock(ForwardCurveFromDiscountCurve.class);
    when(curveInterface.getName()).thenReturn("Name");
    when(curveInterface.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    doNothing().when(curveInterface).setParameter(Mockito.<double[]>any());
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);

    // Act
    forwardCurveWithFixings.setParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(curveInterface, atLeast(1)).getName();
    verify(curveInterface, atLeast(1)).getReferenceDate();
    verify(curveInterface).setParameter(isA(double[].class));
  }

  /**
   * Test {@link PiecewiseCurve#getName()}.
   *
   * <ul>
   *   <li>Then return {@code DiscountCurveFromForwardCurve(Forward Curve Name)}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PiecewiseCurve.getName()"})
  public void testGetName_thenReturnDiscountCurveFromForwardCurveForwardCurveName() {
    // Arrange
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve("Forward Curve Name");
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act and Assert
    assertEquals("DiscountCurveFromForwardCurve(Forward Curve Name)", piecewiseCurve.getName());
  }

  /**
   * Test {@link PiecewiseCurve#getName()}.
   *
   * <ul>
   *   <li>Then return {@code ForwardCurveFromDiscountCurve(3,Payment Offset Code)}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PiecewiseCurve.getName()"})
  public void testGetName_thenReturnForwardCurveFromDiscountCurve3PaymentOffsetCode() {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        "ForwardCurveFromDiscountCurve(3,Payment Offset Code)", forwardCurveWithFixings.getName());
  }

  /**
   * Test {@link PiecewiseCurve#getName()}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PiecewiseCurve.getName()"})
  public void testGetName_thenReturnName() {
    // Arrange
    DiscountCurveRenormalized curve =
        new DiscountCurveRenormalized(
            "Name", LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "Base Curve Name");
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act and Assert
    assertEquals("Name", piecewiseCurve.getName());
  }

  /**
   * Test {@link PiecewiseCurve#getReferenceDate()}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate PiecewiseCurve.getReferenceDate()"})
  public void testGetReferenceDate() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveRenormalized curve =
        new DiscountCurveRenormalized(
            "Name", referenceDate, LocalDate.of(1970, 1, 1), "Base Curve Name");
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act
    LocalDate actualReferenceDate = piecewiseCurve.getReferenceDate();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link PiecewiseCurve#getReferenceDate()}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate PiecewiseCurve.getReferenceDate()"})
  public void testGetReferenceDate2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", referenceDate, "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);

    // Act
    LocalDate actualReferenceDate = forwardCurveWithFixings.getReferenceDate();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link PiecewiseCurve#getReferenceDate()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate PiecewiseCurve.getReferenceDate()"})
  public void testGetReferenceDate_thenReturnNull() {
    // Arrange
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve("Forward Curve Name");
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act and Assert
    assertNull(piecewiseCurve.getReferenceDate());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PiecewiseCurve#toString()}
   *   <li>{@link PiecewiseCurve#getBaseCurve()}
   *   <li>{@link PiecewiseCurve#getFixedPartCurve()}
   *   <li>{@link PiecewiseCurve#getFixedPartEndTime()}
   *   <li>{@link PiecewiseCurve#getFixedPartStartTime()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Curve PiecewiseCurve.getBaseCurve()",
    "Builder PiecewiseCurve.getCloneBuilder()",
    "Curve PiecewiseCurve.getFixedPartCurve()",
    "double PiecewiseCurve.getFixedPartEndTime()",
    "double PiecewiseCurve.getFixedPartStartTime()",
    "String PiecewiseCurve.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve fixedPartCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    PiecewiseCurve piecewiseCurve = new PiecewiseCurve(curve, fixedPartCurve, 10.0d, 10.0d);

    // Act
    String actualToStringResult = piecewiseCurve.toString();
    Curve actualBaseCurve = piecewiseCurve.getBaseCurve();
    Curve actualFixedPartCurve = piecewiseCurve.getFixedPartCurve();
    double actualFixedPartEndTime = piecewiseCurve.getFixedPartEndTime();

    // Assert
    assertEquals(
        "ForwardCurveWithFixings [getBaseCurve()=AbstractCurve [name=DiscountCurveFromForwardCurve(Forward"
            + " Curve Name), referenceDate=null], getFixedPartCurve()=AbstractCurve [name=DiscountCurveFromForwardCurve"
            + "(Forward Curve Name), referenceDate=null], getFixedPartStartTime()=10.0, getFixedPartEndTime()=10.0,"
            + " toString()=AbstractCurve [name=DiscountCurveFromForwardCurve(Forward Curve Name), referenceDate"
            + "=null]]",
        actualToStringResult);
    assertEquals(10.0d, actualFixedPartEndTime, 0.0);
    assertEquals(10.0d, piecewiseCurve.getFixedPartStartTime(), 0.0);
    assertSame(curve, actualBaseCurve);
    assertSame(fixedPartCurve, actualFixedPartCurve);
  }

  /**
   * Test {@link PiecewiseCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime() {
    // Arrange
    CurveInterpolation curve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10.0d, piecewiseCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d), 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime2() {
    // Arrange
    CurveInterpolation curve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 0.5d, 10.0d);

    // Act and Assert
    assertEquals(10.0d, piecewiseCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d), 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime3() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve(
            "3", LocalDate.of(1970, 1, 1), "(?<=[0-9|\\.])(?=[A-Z|a-z])");

    ForwardCurveWithFixings forwardCurve =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve(forwardCurve);
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        9.999999999999991d,
        piecewiseCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime4() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    ForwardCurveInterpolation fixedPartCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    ForwardCurveWithFixings forwardCurve =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, -0.5d, 10.0d);
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve(forwardCurve);
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        9.999999999999991d,
        piecewiseCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getValue(double)} with {@code time}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseCurve.getValue(double)"})
  public void testGetValueWithTime() {
    // Arrange
    CurveInterpolation curve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act and Assert
    assertEquals(10.0d, piecewiseCurve.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getValue(double)} with {@code time}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseCurve.getValue(double)"})
  public void testGetValueWithTime2() {
    // Arrange
    CurveInterpolation curve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 0.5d, 10.0d);

    // Act and Assert
    assertEquals(10.0d, piecewiseCurve.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getValue(double)} with {@code time}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseCurve.getValue(double)"})
  public void testGetValueWithTime3() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurve =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve(forwardCurve);
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act and Assert
    assertEquals(9.999999999999991d, piecewiseCurve.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getValue(double)} with {@code time}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseCurve.getValue(double)"})
  public void testGetValueWithTime4() {
    // Arrange
    ForwardCurveInterpolation curveInterface =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    ForwardCurveInterpolation fixedPartCurve =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);

    ForwardCurveWithFixings forwardCurve =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, -0.5d, 10.0d);
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve(forwardCurve);
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act and Assert
    assertEquals(9.999999999999991d, piecewiseCurve.getValue(10.0d), 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve PiecewiseCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter() throws CloneNotSupportedException {
    // Arrange
    CurveInterpolation curve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act
    Curve actualCloneForParameter = piecewiseCurve.getCloneForParameter(new double[] {});

    // Assert
    assertTrue(actualCloneForParameter instanceof PiecewiseCurve);
    assertSame(curve, ((PiecewiseCurve) actualCloneForParameter).getBaseCurve());
    assertArrayEquals(new double[] {}, actualCloneForParameter.getParameter(), 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve PiecewiseCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter2() throws CloneNotSupportedException {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);

    // Act
    Curve actualCloneForParameter =
        forwardCurveWithFixings.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    Curve baseCurve = ((ForwardCurveWithFixings) actualCloneForParameter).getBaseCurve();
    assertTrue(
        ((ForwardCurveWithFixings) baseCurve).getBaseCurve()
            instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve3 = ((ForwardCurveWithFixings) actualCloneForParameter).getFixedPartCurve();
    assertTrue(fixedPartCurve3 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(
        ((ForwardCurveWithFixings) baseCurve).getFixedPartCurve()
            instanceof ForwardCurveFromDiscountCurve);
    assertTrue(actualCloneForParameter instanceof ForwardCurveWithFixings);
    assertTrue(baseCurve instanceof ForwardCurveWithFixings);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve3).getTimes(), 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link PiecewiseCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve PiecewiseCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter3() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", referenceDate, "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface3 =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve3 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface3, fixedPartCurve3, 10.0d, 10.0d);

    // Act
    Curve actualCloneForParameter =
        forwardCurveWithFixings.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    Curve baseCurve = ((ForwardCurveWithFixings) actualCloneForParameter).getBaseCurve();
    Curve baseCurve2 = ((ForwardCurveWithFixings) baseCurve).getBaseCurve();
    assertTrue(
        ((ForwardCurveWithFixings) baseCurve2).getBaseCurve()
            instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve4 = ((ForwardCurveWithFixings) actualCloneForParameter).getFixedPartCurve();
    assertTrue(fixedPartCurve4 instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve5 = ((ForwardCurveWithFixings) baseCurve).getFixedPartCurve();
    assertTrue(fixedPartCurve5 instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve6 = ((ForwardCurveWithFixings) baseCurve2).getFixedPartCurve();
    assertTrue(fixedPartCurve6 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(actualCloneForParameter instanceof ForwardCurveWithFixings);
    assertTrue(baseCurve instanceof ForwardCurveWithFixings);
    assertTrue(baseCurve2 instanceof ForwardCurveWithFixings);
    assertEquals("3", ((ForwardCurveWithFixings) baseCurve2).getDiscountCurveName());
    assertEquals("ForwardCurveFromDiscountCurve(3,Payment Offset Code)", baseCurve2.getName());
    assertNull(baseCurve2.getParameter());
    assertEquals(10.0d, ((ForwardCurveWithFixings) baseCurve2).getFixedPartEndTime(), 0.0);
    assertEquals(10.0d, ((ForwardCurveWithFixings) baseCurve2).getFixedPartStartTime(), 0.0);
    assertSame(fixedPartCurve, fixedPartCurve6);
    assertSame(referenceDate, baseCurve2.getReferenceDate());
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve4).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve5).getTimes(), 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getCloneForParameter(double[])}.
   *
   * <ul>
   *   <li>Then BaseCurve return {@link CurveInterpolation}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve PiecewiseCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter_thenBaseCurveReturnCurveInterpolation()
      throws CloneNotSupportedException {
    // Arrange
    CurveInterpolation curve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act
    Curve actualCloneForParameter =
        piecewiseCurve.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    Curve baseCurve = ((PiecewiseCurve) actualCloneForParameter).getBaseCurve();
    assertTrue(baseCurve instanceof CurveInterpolation);
    assertTrue(actualCloneForParameter instanceof PiecewiseCurve);
    assertArrayEquals(new double[] {}, baseCurve.getParameter(), 0.0);
    assertArrayEquals(new double[] {}, actualCloneForParameter.getParameter(), 0.0);
    assertArrayEquals(new double[] {}, ((CurveInterpolation) baseCurve).getTimes(), 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getCloneForParameter(double[])}.
   *
   * <ul>
   *   <li>Then BaseCurve return {@link DiscountCurveNelsonSiegelSvensson}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve PiecewiseCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter_thenBaseCurveReturnDiscountCurveNelsonSiegelSvensson()
      throws CloneNotSupportedException {
    // Arrange
    DiscountCurveNelsonSiegelSvensson curve =
        new DiscountCurveNelsonSiegelSvensson(
            "Name", LocalDate.of(1970, 1, 1), new double[] {10.0d, 1.0d, 10.0d, 1.0d}, 10.0d);
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act
    Curve actualCloneForParameter =
        piecewiseCurve.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    Curve baseCurve = ((PiecewiseCurve) actualCloneForParameter).getBaseCurve();
    assertTrue(baseCurve instanceof DiscountCurveNelsonSiegelSvensson);
    assertTrue(actualCloneForParameter instanceof PiecewiseCurve);
    assertEquals(10.0d, ((DiscountCurveNelsonSiegelSvensson) baseCurve).getTimeScaling(), 0.0);
    double[] parameter = actualCloneForParameter.getParameter();
    assertSame(parameter, baseCurve.getParameter());
    assertArrayEquals(new double[] {10.0d, 0.5d, 10.0d, 0.5d}, parameter, 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getCloneForParameter(double[])}.
   *
   * <ul>
   *   <li>Then BaseCurve return {@link ForwardCurveFromDiscountCurve}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve PiecewiseCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter_thenBaseCurveReturnForwardCurveFromDiscountCurve()
      throws CloneNotSupportedException {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);

    // Act
    Curve actualCloneForParameter =
        forwardCurveWithFixings.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    Curve baseCurve = ((ForwardCurveWithFixings) actualCloneForParameter).getBaseCurve();
    assertTrue(baseCurve instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve2 = ((ForwardCurveWithFixings) actualCloneForParameter).getFixedPartCurve();
    assertTrue(fixedPartCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(actualCloneForParameter instanceof ForwardCurveWithFixings);
    assertTrue(
        ((ForwardCurveFromDiscountCurve) baseCurve).getPaymentBusinessdayCalendar()
            instanceof BusinessdayCalendarExcludingWeekends);
    assertEquals("3", ((ForwardCurveFromDiscountCurve) baseCurve).getDiscountCurveName());
    assertEquals("ForwardCurveFromDiscountCurve(3,Payment Offset Code)", baseCurve.getName());
    assertEquals(
        "Payment Offset Code", ((ForwardCurveFromDiscountCurve) baseCurve).getPaymentOffsetCode());
    assertNull(baseCurve.getParameter());
    assertEquals(
        ExtrapolationMethod.CONSTANT,
        ((ForwardCurveFromDiscountCurve) baseCurve).getExtrapolationMethod());
    assertEquals(
        InterpolationMethod.LINEAR,
        ((ForwardCurveFromDiscountCurve) baseCurve).getInterpolationMethod());
    assertEquals(
        DateRollConvention.FOLLOWING,
        ((ForwardCurveFromDiscountCurve) baseCurve).getPaymentDateRollConvention());
    assertArrayEquals(new double[] {}, ((ForwardCurveFromDiscountCurve) baseCurve).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve2).getTimes(), 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getCloneForParameter(double[])}.
   *
   * <ul>
   *   <li>Then BaseCurve return {@link ForwardCurveNelsonSiegelSvensson}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve PiecewiseCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter_thenBaseCurveReturnForwardCurveNelsonSiegelSvensson()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    BusinessdayCalendarAny paymentBusinessdayCalendar = new BusinessdayCalendarAny();

    ForwardCurveNelsonSiegelSvensson curveInterface =
        new ForwardCurveNelsonSiegelSvensson(
            "Name",
            referenceDate,
            "Payment Offset Code",
            paymentBusinessdayCalendar,
            DateRollConvention.UNADJUSTED,
            new DayCountConvention_30E_360(true),
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);

    // Act
    Curve actualCloneForParameter =
        forwardCurveWithFixings.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    Curve fixedPartCurve2 = ((ForwardCurveWithFixings) actualCloneForParameter).getFixedPartCurve();
    assertTrue(fixedPartCurve2 instanceof ForwardCurveFromDiscountCurve);
    Curve baseCurve = ((ForwardCurveWithFixings) actualCloneForParameter).getBaseCurve();
    assertTrue(baseCurve instanceof ForwardCurveNelsonSiegelSvensson);
    assertTrue(actualCloneForParameter instanceof ForwardCurveWithFixings);
    assertNull(((ForwardCurveNelsonSiegelSvensson) baseCurve).getDiscountCurveName());
    assertNull(((ForwardCurveWithFixings) actualCloneForParameter).getDiscountCurveName());
    double[] parameter = actualCloneForParameter.getParameter();
    assertSame(parameter, baseCurve.getParameter());
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve2).getTimes(), 0.0);
    assertArrayEquals(new double[] {10.0d, 0.5d, 10.0d, 0.5d}, parameter, 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#getCloneForParameter(double[])}.
   *
   * <ul>
   *   <li>Then throw {@link CloneNotSupportedException}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve PiecewiseCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter_thenThrowCloneNotSupportedException()
      throws CloneNotSupportedException {
    // Arrange
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve("Forward Curve Name");
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        CloneNotSupportedException.class,
        () -> piecewiseCurve.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d}));
  }

  /**
   * Test {@link PiecewiseCurve#clone()}.
   *
   * <ul>
   *   <li>Then BaseCurve BaseCurve BaseCurve return {@link ForwardCurveFromDiscountCurve}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PiecewiseCurve PiecewiseCurve.clone()"})
  public void testClone_thenBaseCurveBaseCurveBaseCurveReturnForwardCurveFromDiscountCurve()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", referenceDate, "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface3 =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve3 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface3, fixedPartCurve3, 10.0d, 10.0d);

    // Act
    ForwardCurveWithFixings actualCloneResult = forwardCurveWithFixings.clone();

    // Assert
    Curve baseCurve = actualCloneResult.getBaseCurve();
    Curve baseCurve2 = ((ForwardCurveWithFixings) baseCurve).getBaseCurve();
    assertTrue(
        ((ForwardCurveWithFixings) baseCurve2).getBaseCurve()
            instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve4 = actualCloneResult.getFixedPartCurve();
    assertTrue(fixedPartCurve4 instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve5 = ((ForwardCurveWithFixings) baseCurve).getFixedPartCurve();
    assertTrue(fixedPartCurve5 instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve6 = ((ForwardCurveWithFixings) baseCurve2).getFixedPartCurve();
    assertTrue(fixedPartCurve6 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(actualCloneResult instanceof ForwardCurveWithFixings);
    assertTrue(baseCurve instanceof ForwardCurveWithFixings);
    assertTrue(baseCurve2 instanceof ForwardCurveWithFixings);
    assertEquals("3", ((ForwardCurveWithFixings) baseCurve2).getDiscountCurveName());
    assertEquals("ForwardCurveFromDiscountCurve(3,Payment Offset Code)", baseCurve2.getName());
    assertNull(baseCurve2.getParameter());
    assertEquals(10.0d, ((ForwardCurveWithFixings) baseCurve2).getFixedPartEndTime(), 0.0);
    assertEquals(10.0d, ((ForwardCurveWithFixings) baseCurve2).getFixedPartStartTime(), 0.0);
    assertSame(fixedPartCurve, fixedPartCurve6);
    assertSame(referenceDate, baseCurve2.getReferenceDate());
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve4).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve5).getTimes(), 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#clone()}.
   *
   * <ul>
   *   <li>Then BaseCurve BaseCurve return {@link ForwardCurveFromDiscountCurve}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PiecewiseCurve PiecewiseCurve.clone()"})
  public void testClone_thenBaseCurveBaseCurveReturnForwardCurveFromDiscountCurve()
      throws CloneNotSupportedException {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings curveInterface2 =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);
    ForwardCurveFromDiscountCurve fixedPartCurve2 =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface2, fixedPartCurve2, 10.0d, 10.0d);

    // Act
    ForwardCurveWithFixings actualCloneResult = forwardCurveWithFixings.clone();

    // Assert
    Curve baseCurve = actualCloneResult.getBaseCurve();
    assertTrue(
        ((ForwardCurveWithFixings) baseCurve).getBaseCurve()
            instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve3 = actualCloneResult.getFixedPartCurve();
    assertTrue(fixedPartCurve3 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(
        ((ForwardCurveWithFixings) baseCurve).getFixedPartCurve()
            instanceof ForwardCurveFromDiscountCurve);
    assertTrue(actualCloneResult instanceof ForwardCurveWithFixings);
    assertTrue(baseCurve instanceof ForwardCurveWithFixings);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve3).getTimes(), 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#clone()}.
   *
   * <ul>
   *   <li>Then BaseCurve return {@link CurveInterpolation}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PiecewiseCurve PiecewiseCurve.clone()"})
  public void testClone_thenBaseCurveReturnCurveInterpolation() throws CloneNotSupportedException {
    // Arrange
    CurveInterpolation curve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act
    PiecewiseCurve actualCloneResult = piecewiseCurve.clone();

    // Assert
    Curve baseCurve = actualCloneResult.getBaseCurve();
    assertTrue(baseCurve instanceof CurveInterpolation);
    assertEquals("Name", baseCurve.getName());
    assertEquals(
        ExtrapolationMethod.DEFAULT, ((CurveInterpolation) baseCurve).getExtrapolationMethod());
    assertEquals(
        InterpolationMethod.PIECEWISE_CONSTANT,
        ((CurveInterpolation) baseCurve).getInterpolationMethod());
    assertArrayEquals(new double[] {}, baseCurve.getParameter(), 0.0);
    assertArrayEquals(new double[] {}, ((CurveInterpolation) baseCurve).getTimes(), 0.0);
    assertArrayEquals(new double[] {}, actualCloneResult.getParameter(), 0.0);
  }

  /**
   * Test {@link PiecewiseCurve#clone()}.
   *
   * <ul>
   *   <li>Then BaseCurve return {@link DiscountCurveFromForwardCurve}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PiecewiseCurve PiecewiseCurve.clone()"})
  public void testClone_thenBaseCurveReturnDiscountCurveFromForwardCurve()
      throws CloneNotSupportedException {
    // Arrange
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve("Forward Curve Name");
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act
    PiecewiseCurve actualCloneResult = piecewiseCurve.clone();

    // Assert
    Curve baseCurve = actualCloneResult.getBaseCurve();
    assertTrue(baseCurve instanceof DiscountCurveFromForwardCurve);
    Curve fixedPartCurve = actualCloneResult.getFixedPartCurve();
    assertTrue(fixedPartCurve instanceof DiscountCurveFromForwardCurve);
    assertEquals("DiscountCurveFromForwardCurve(Forward Curve Name)", actualCloneResult.getName());
    assertNull(actualCloneResult.getReferenceDate());
    assertEquals(curve, baseCurve);
    assertEquals(curve, fixedPartCurve);
  }

  /**
   * Test {@link PiecewiseCurve#clone()}.
   *
   * <ul>
   *   <li>Then BaseCurve return {@link DiscountCurveRenormalized}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PiecewiseCurve PiecewiseCurve.clone()"})
  public void testClone_thenBaseCurveReturnDiscountCurveRenormalized()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveRenormalized curve =
        new DiscountCurveRenormalized(
            "Name", referenceDate, LocalDate.of(1970, 1, 1), "Base Curve Name");
    PiecewiseCurve piecewiseCurve =
        new PiecewiseCurve(
            curve, new DiscountCurveFromForwardCurve("Forward Curve Name"), 10.0d, 10.0d);

    // Act and Assert
    Curve baseCurve = piecewiseCurve.clone().getBaseCurve();
    assertTrue(baseCurve instanceof DiscountCurveRenormalized);
    assertEquals("Name", baseCurve.getName());
    assertSame(referenceDate, baseCurve.getReferenceDate());
  }

  /**
   * Test {@link PiecewiseCurve#clone()}.
   *
   * <ul>
   *   <li>Then BaseCurve return {@link ForwardCurveFromDiscountCurve}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseCurve#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PiecewiseCurve PiecewiseCurve.clone()"})
  public void testClone_thenBaseCurveReturnForwardCurveFromDiscountCurve()
      throws CloneNotSupportedException {
    // Arrange
    ForwardCurveFromDiscountCurve curveInterface =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    ForwardCurveFromDiscountCurve fixedPartCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    ForwardCurveWithFixings forwardCurveWithFixings =
        new ForwardCurveWithFixings(curveInterface, fixedPartCurve, 10.0d, 10.0d);

    // Act
    ForwardCurveWithFixings actualCloneResult = forwardCurveWithFixings.clone();

    // Assert
    Curve baseCurve = actualCloneResult.getBaseCurve();
    assertTrue(baseCurve instanceof ForwardCurveFromDiscountCurve);
    Curve fixedPartCurve2 = actualCloneResult.getFixedPartCurve();
    assertTrue(fixedPartCurve2 instanceof ForwardCurveFromDiscountCurve);
    assertTrue(actualCloneResult instanceof ForwardCurveWithFixings);
    assertTrue(
        ((ForwardCurveFromDiscountCurve) baseCurve).getPaymentBusinessdayCalendar()
            instanceof BusinessdayCalendarExcludingWeekends);
    assertEquals("3", ((ForwardCurveFromDiscountCurve) baseCurve).getDiscountCurveName());
    assertEquals("ForwardCurveFromDiscountCurve(3,Payment Offset Code)", baseCurve.getName());
    assertEquals(
        "Payment Offset Code", ((ForwardCurveFromDiscountCurve) baseCurve).getPaymentOffsetCode());
    assertNull(baseCurve.getParameter());
    assertEquals(
        ExtrapolationMethod.CONSTANT,
        ((ForwardCurveFromDiscountCurve) baseCurve).getExtrapolationMethod());
    assertEquals(
        InterpolationMethod.LINEAR,
        ((ForwardCurveFromDiscountCurve) baseCurve).getInterpolationMethod());
    assertEquals(
        DateRollConvention.FOLLOWING,
        ((ForwardCurveFromDiscountCurve) baseCurve).getPaymentDateRollConvention());
    assertArrayEquals(new double[] {}, ((ForwardCurveFromDiscountCurve) baseCurve).getTimes(), 0.0);
    assertArrayEquals(
        new double[] {}, ((ForwardCurveFromDiscountCurve) fixedPartCurve2).getTimes(), 0.0);
  }
}
