package net.finmath.marketdata.model.bond;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.bond.BondCurve.Type;
import net.finmath.marketdata.model.curves.Curve;
import net.finmath.marketdata.model.curves.CurveBuilder;
import net.finmath.marketdata.model.curves.CurveInterpolation;
import net.finmath.marketdata.model.curves.CurveInterpolation.ExtrapolationMethod;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationEntity;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationMethod;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class BondCurveDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BondCurve#BondCurve(String, LocalDate, Curve, Curve, Type)}
   *   <li>{@link BondCurve#setParameter(double[])}
   *   <li>{@link BondCurve#getCloneBuilder()}
   *   <li>{@link BondCurve#getName()}
   *   <li>{@link BondCurve#getParameter()}
   *   <li>{@link BondCurve#getReferenceCurve()}
   *   <li>{@link BondCurve#getReferenceDate()}
   *   <li>{@link BondCurve#getSpreadCurve()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BondCurve.<init>(String, LocalDate, Curve, Curve, Type)",
    "CurveBuilder BondCurve.getCloneBuilder()",
    "String BondCurve.getName()",
    "double[] BondCurve.getParameter()",
    "Curve BondCurve.getReferenceCurve()",
    "LocalDate BondCurve.getReferenceDate()",
    "Curve BondCurve.getSpreadCurve()",
    "void BondCurve.setParameter(double[])"
  })
  public void testGettersAndSetters() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve spreadCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    BondCurve actualBondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.DISCOUNTFACTOR_DISCOUNTFACTOR);
    actualBondCurve.setParameter(new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    CurveBuilder actualCloneBuilder = actualBondCurve.getCloneBuilder();
    String actualName = actualBondCurve.getName();
    double[] actualParameter = actualBondCurve.getParameter();
    Curve actualReferenceCurve = actualBondCurve.getReferenceCurve();
    LocalDate actualReferenceDate = actualBondCurve.getReferenceDate();

    // Assert
    assertNull(actualParameter);
    assertNull(actualName);
    assertNull(actualReferenceDate);
    assertNull(actualCloneBuilder);
    assertSame(referenceCurve, actualReferenceCurve);
    assertSame(spreadCurve, actualBondCurve.getSpreadCurve());
  }

  /**
   * Test {@link BondCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <p>Method under test: {@link BondCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    CurveInterpolation spreadCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.ZERORATE_DISCOUNTFACTOR);

    // Act and Assert
    assertEquals(
        3.720075976020836E-43d,
        bondCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link BondCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <p>Method under test: {@link BondCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    CurveInterpolation spreadCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.DISCOUNTFACTOR_ZERORATE);

    // Act and Assert
    assertEquals(
        3.720075976020836E-43d,
        bondCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link BondCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <ul>
   *   <li>Then return {@code 1.3838965267367376E-87}.
   * </ul>
   *
   * <p>Method under test: {@link BondCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturn13838965267367376e87() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    CurveInterpolation spreadCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve("Name", referenceDate, referenceCurve, spreadCurve, Type.ZERORATE_ZERORATE);

    // Act and Assert
    assertEquals(
        1.3838965267367376E-87d,
        bondCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link BondCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <ul>
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link BondCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturnOneHundred() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    CurveInterpolation spreadCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.DISCOUNTFACTOR_DISCOUNTFACTOR);

    // Act and Assert
    assertEquals(100.0d, bondCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d), 0.0);
  }

  /**
   * Test {@link BondCurve#getValue(double)} with {@code time}.
   *
   * <p>Method under test: {@link BondCurve#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getValue(double)"})
  public void testGetValueWithTime() {
    // Arrange
    Curve spreadCurve = mock(Curve.class);
    when(spreadCurve.getValue(Mockito.<AnalyticModel>any(), anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.ZERORATE_DISCOUNTFACTOR);

    // Act
    double actualValue = bondCurve.getValue(10.0d);

    // Assert
    verify(spreadCurve).getValue(isNull(), eq(10.0d));
    assertEquals(3.720075976020836E-43d, actualValue, 0.0);
  }

  /**
   * Test {@link BondCurve#getValue(double)} with {@code time}.
   *
   * <p>Method under test: {@link BondCurve#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getValue(double)"})
  public void testGetValueWithTime2() {
    // Arrange
    Curve spreadCurve = mock(Curve.class);
    when(spreadCurve.getValue(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.DISCOUNTFACTOR_ZERORATE);

    // Act
    double actualValue = bondCurve.getValue(10.0d);

    // Assert
    verify(spreadCurve).getValue(10.0d);
    assertEquals(3.720075976020836E-43d, actualValue, 0.0);
  }

  /**
   * Test {@link BondCurve#getValue(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return {@code 1.3838965267367376E-87}.
   * </ul>
   *
   * <p>Method under test: {@link BondCurve#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getValue(double)"})
  public void testGetValueWithTime_thenReturn13838965267367376e87() {
    // Arrange
    Curve spreadCurve = mock(Curve.class);
    when(spreadCurve.getValue(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve("Name", referenceDate, referenceCurve, spreadCurve, Type.ZERORATE_ZERORATE);

    // Act
    double actualValue = bondCurve.getValue(10.0d);

    // Assert
    verify(spreadCurve).getValue(10.0d);
    assertEquals(1.3838965267367376E-87d, actualValue, 0.0);
  }

  /**
   * Test {@link BondCurve#getValue(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link BondCurve#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getValue(double)"})
  public void testGetValueWithTime_thenReturnOneHundred() {
    // Arrange
    Curve spreadCurve = mock(Curve.class);
    when(spreadCurve.getValue(Mockito.<AnalyticModel>any(), anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.DISCOUNTFACTOR_DISCOUNTFACTOR);

    // Act
    double actualValue = bondCurve.getValue(10.0d);

    // Assert
    verify(spreadCurve).getValue(isNull(), eq(10.0d));
    assertEquals(100.0d, actualValue, 0.0);
  }

  /**
   * Test {@link BondCurve#getDiscountFactor(AnalyticModel, double)} with {@code model}, {@code
   * time}.
   *
   * <p>Method under test: {@link BondCurve#getDiscountFactor(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getDiscountFactor(AnalyticModel, double)"})
  public void testGetDiscountFactorWithModelTime() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    CurveInterpolation spreadCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.ZERORATE_DISCOUNTFACTOR);

    // Act and Assert
    assertEquals(
        3.720075976020836E-43d,
        bondCurve.getDiscountFactor(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link BondCurve#getDiscountFactor(AnalyticModel, double)} with {@code model}, {@code
   * time}.
   *
   * <p>Method under test: {@link BondCurve#getDiscountFactor(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getDiscountFactor(AnalyticModel, double)"})
  public void testGetDiscountFactorWithModelTime2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    CurveInterpolation spreadCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.DISCOUNTFACTOR_ZERORATE);

    // Act and Assert
    assertEquals(
        3.720075976020836E-43d,
        bondCurve.getDiscountFactor(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link BondCurve#getDiscountFactor(AnalyticModel, double)} with {@code model}, {@code
   * time}.
   *
   * <ul>
   *   <li>Then return {@code 1.3838965267367376E-87}.
   * </ul>
   *
   * <p>Method under test: {@link BondCurve#getDiscountFactor(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getDiscountFactor(AnalyticModel, double)"})
  public void testGetDiscountFactorWithModelTime_thenReturn13838965267367376e87() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    CurveInterpolation spreadCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve("Name", referenceDate, referenceCurve, spreadCurve, Type.ZERORATE_ZERORATE);

    // Act and Assert
    assertEquals(
        1.3838965267367376E-87d,
        bondCurve.getDiscountFactor(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link BondCurve#getDiscountFactor(AnalyticModel, double)} with {@code model}, {@code
   * time}.
   *
   * <ul>
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link BondCurve#getDiscountFactor(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getDiscountFactor(AnalyticModel, double)"})
  public void testGetDiscountFactorWithModelTime_thenReturnOneHundred() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    CurveInterpolation spreadCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.DISCOUNTFACTOR_DISCOUNTFACTOR);

    // Act and Assert
    assertEquals(
        100.0d, bondCurve.getDiscountFactor(new AnalyticModelFromCurvesAndVols(), 10.0d), 0.0);
  }

  /**
   * Test {@link BondCurve#getDiscountFactor(double)} with {@code time}.
   *
   * <p>Method under test: {@link BondCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithTime() {
    // Arrange
    Curve spreadCurve = mock(Curve.class);
    when(spreadCurve.getValue(Mockito.<AnalyticModel>any(), anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.ZERORATE_DISCOUNTFACTOR);

    // Act
    double actualDiscountFactor = bondCurve.getDiscountFactor(10.0d);

    // Assert
    verify(spreadCurve).getValue(isNull(), eq(10.0d));
    assertEquals(3.720075976020836E-43d, actualDiscountFactor, 0.0);
  }

  /**
   * Test {@link BondCurve#getDiscountFactor(double)} with {@code time}.
   *
   * <p>Method under test: {@link BondCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithTime2() {
    // Arrange
    Curve spreadCurve = mock(Curve.class);
    when(spreadCurve.getValue(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.DISCOUNTFACTOR_ZERORATE);

    // Act
    double actualDiscountFactor = bondCurve.getDiscountFactor(10.0d);

    // Assert
    verify(spreadCurve).getValue(10.0d);
    assertEquals(3.720075976020836E-43d, actualDiscountFactor, 0.0);
  }

  /**
   * Test {@link BondCurve#getDiscountFactor(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return {@code 1.3838965267367376E-87}.
   * </ul>
   *
   * <p>Method under test: {@link BondCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithTime_thenReturn13838965267367376e87() {
    // Arrange
    Curve spreadCurve = mock(Curve.class);
    when(spreadCurve.getValue(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve("Name", referenceDate, referenceCurve, spreadCurve, Type.ZERORATE_ZERORATE);

    // Act
    double actualDiscountFactor = bondCurve.getDiscountFactor(10.0d);

    // Assert
    verify(spreadCurve).getValue(10.0d);
    assertEquals(1.3838965267367376E-87d, actualDiscountFactor, 0.0);
  }

  /**
   * Test {@link BondCurve#getDiscountFactor(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link BondCurve#getDiscountFactor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getDiscountFactor(double)"})
  public void testGetDiscountFactorWithTime_thenReturnOneHundred() {
    // Arrange
    Curve spreadCurve = mock(Curve.class);
    when(spreadCurve.getValue(Mockito.<AnalyticModel>any(), anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.DISCOUNTFACTOR_DISCOUNTFACTOR);

    // Act
    double actualDiscountFactor = bondCurve.getDiscountFactor(10.0d);

    // Assert
    verify(spreadCurve).getValue(isNull(), eq(10.0d));
    assertEquals(100.0d, actualDiscountFactor, 0.0);
  }

  /**
   * Test {@link BondCurve#getZeroRate(double)}.
   *
   * <p>Method under test: {@link BondCurve#getZeroRate(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getZeroRate(double)"})
  public void testGetZeroRate() {
    // Arrange
    Curve spreadCurve = mock(Curve.class);
    when(spreadCurve.getValue(Mockito.<AnalyticModel>any(), anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.ZERORATE_DISCOUNTFACTOR);

    // Act
    double actualZeroRate = bondCurve.getZeroRate(10.0d);

    // Assert
    verify(spreadCurve).getValue(isNull(), eq(10.0d));
    assertEquals(9.769741490700595d, actualZeroRate, 0.0);
  }

  /**
   * Test {@link BondCurve#getZeroRate(double)}.
   *
   * <ul>
   *   <li>Then return {@code 9.769741490700595}.
   * </ul>
   *
   * <p>Method under test: {@link BondCurve#getZeroRate(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getZeroRate(double)"})
  public void testGetZeroRate_thenReturn9769741490700595() {
    // Arrange
    Curve spreadCurve = mock(Curve.class);
    when(spreadCurve.getValue(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.DISCOUNTFACTOR_ZERORATE);

    // Act
    double actualZeroRate = bondCurve.getZeroRate(10.0d);

    // Assert
    verify(spreadCurve).getValue(10.0d);
    assertEquals(9.769741490700595d, actualZeroRate, 0.0);
  }

  /**
   * Test {@link BondCurve#getZeroRate(double)}.
   *
   * <ul>
   *   <li>Then return {@code -0.46051701859880917}.
   * </ul>
   *
   * <p>Method under test: {@link BondCurve#getZeroRate(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getZeroRate(double)"})
  public void testGetZeroRate_thenReturn046051701859880917() {
    // Arrange
    Curve spreadCurve = mock(Curve.class);
    when(spreadCurve.getValue(Mockito.<AnalyticModel>any(), anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.DISCOUNTFACTOR_DISCOUNTFACTOR);

    // Act
    double actualZeroRate = bondCurve.getZeroRate(10.0d);

    // Assert
    verify(spreadCurve).getValue(isNull(), eq(10.0d));
    assertEquals(-0.46051701859880917d, actualZeroRate, 0.0);
  }

  /**
   * Test {@link BondCurve#getZeroRate(double)}.
   *
   * <ul>
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link BondCurve#getZeroRate(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getZeroRate(double)"})
  public void testGetZeroRate_thenReturnPositive_infinity() {
    // Arrange
    Curve spreadCurve = mock(Curve.class);
    when(spreadCurve.getValue(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    BondCurve bondCurve =
        new BondCurve(
            "Name", referenceDate, referenceCurve, spreadCurve, Type.DISCOUNTFACTOR_ZERORATE);

    // Act
    double actualZeroRate = bondCurve.getZeroRate(0.0d);

    // Assert
    verify(spreadCurve).getValue(1.0E-14d);
    assertEquals(Double.POSITIVE_INFINITY, actualZeroRate, 0.0);
  }

  /**
   * Test {@link BondCurve#getZeroRate(double)}.
   *
   * <ul>
   *   <li>Then return twenty.
   * </ul>
   *
   * <p>Method under test: {@link BondCurve#getZeroRate(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BondCurve.getZeroRate(double)"})
  public void testGetZeroRate_thenReturnTwenty() {
    // Arrange
    Curve spreadCurve = mock(Curve.class);
    when(spreadCurve.getValue(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation referenceCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            new double[] {10.0d, 0.0d, 10.0d, 0.0d});

    BondCurve bondCurve =
        new BondCurve("Name", referenceDate, referenceCurve, spreadCurve, Type.ZERORATE_ZERORATE);

    // Act
    double actualZeroRate = bondCurve.getZeroRate(10.0d);

    // Assert
    verify(spreadCurve).getValue(10.0d);
    assertEquals(20.0d, actualZeroRate, 0.0);
  }

  /**
   * Test {@link BondCurve#getType()}.
   *
   * <p>Method under test: {@link BondCurve#getType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String BondCurve.getType()"})
  public void testGetType() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BondCurve bondCurve =
        new BondCurve(
            "Name",
            referenceDate,
            referenceCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            Type.DISCOUNTFACTOR_DISCOUNTFACTOR);

    // Act and Assert
    assertEquals("DISCOUNTFACTOR_DISCOUNTFACTOR", bondCurve.getType());
  }
}
