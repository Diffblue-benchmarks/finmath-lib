package net.finmath.marketdata.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import net.finmath.marketdata.model.bond.BondCurve;
import net.finmath.marketdata.model.bond.BondCurve.Type;
import net.finmath.marketdata.model.curves.Curve;
import net.finmath.marketdata.model.curves.CurveFromProductOfCurves;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.volatilities.CapletVolatilitiesParametric;
import net.finmath.marketdata.model.volatilities.VolatilitySurface;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AnalyticModelFromCurvesAndVolsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols()}
   *   <li>{@link AnalyticModelFromCurvesAndVols#toString()}
   *   <li>{@link AnalyticModelFromCurvesAndVols#getReferenceDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnalyticModelFromCurvesAndVols.<init>()",
    "void AnalyticModelFromCurvesAndVols.<init>(LocalDate)",
    "LocalDate AnalyticModelFromCurvesAndVols.getReferenceDate()",
    "String AnalyticModelFromCurvesAndVols.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();
    String actualToStringResult = actualAnalyticModelFromCurvesAndVols.toString();

    // Assert
    assertEquals(
        "AnalyticModelFromCurvesAndVols: referenceDate=null, curves=[], volatilitySurfaces=[]",
        actualToStringResult);
    assertNull(actualAnalyticModelFromCurvesAndVols.getReferenceDate());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return ReferenceDate toString is {@code 1970-01-01}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate)}
   *   <li>{@link AnalyticModelFromCurvesAndVols#toString()}
   *   <li>{@link AnalyticModelFromCurvesAndVols#getReferenceDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnalyticModelFromCurvesAndVols.<init>()",
    "void AnalyticModelFromCurvesAndVols.<init>(LocalDate)",
    "LocalDate AnalyticModelFromCurvesAndVols.getReferenceDate()",
    "String AnalyticModelFromCurvesAndVols.toString()"
  })
  public void testGettersAndSetters_thenReturnReferenceDateToStringIs19700101() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate);
    String actualToStringResult = actualAnalyticModelFromCurvesAndVols.toString();
    LocalDate actualReferenceDate = actualAnalyticModelFromCurvesAndVols.getReferenceDate();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals(
        "AnalyticModelFromCurvesAndVols: referenceDate=1970-01-01, curves=[], volatilitySurfaces=[]",
        actualToStringResult);
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate,
   * Collection)}.
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(LocalDate, Collection)"})
  public void testNewAnalyticModelFromCurvesAndVols() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    LinkedHashSet<Curve> curves = new LinkedHashSet<>();
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    BondCurve bondCurve =
        new BondCurve(
            "Name",
            null,
            referenceCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            Type.DISCOUNTFACTOR_DISCOUNTFACTOR);
    curves.add(bondCurve);

    // Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate, curves);

    // Assert
    Map<String, Curve> curves2 = actualAnalyticModelFromCurvesAndVols.getCurves();
    assertEquals(1, curves2.size());
    assertSame(bondCurve, curves2.get(null));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate,
   * Collection)}.
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(LocalDate, Collection)"})
  public void testNewAnalyticModelFromCurvesAndVols2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    LinkedHashSet<Curve> curves = new LinkedHashSet<>();
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    CurveFromProductOfCurves curveFromProductOfCurves =
        new CurveFromProductOfCurves(
            "Name", referenceDate2, new DiscountCurveFromForwardCurve("Forward Curve Name"));
    curves.add(curveFromProductOfCurves);

    // Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate, curves);

    // Assert
    Map<String, Curve> curves2 = actualAnalyticModelFromCurvesAndVols.getCurves();
    assertEquals(1, curves2.size());
    assertSame(curveFromProductOfCurves, curves2.get("Name"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate,
   * Collection)}.
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(LocalDate, Collection)"})
  public void testNewAnalyticModelFromCurvesAndVols3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    ArrayList<Curve> curves = new ArrayList<>();
    curves.add(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    curves.add(discountCurveFromForwardCurve);

    // Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate, curves);

    // Assert
    Map<String, Curve> curves2 = actualAnalyticModelFromCurvesAndVols.getCurves();
    assertEquals(1, curves2.size());
    assertSame(
        discountCurveFromForwardCurve,
        curves2.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate, Map,
   * Map)}.
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate, Map, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(LocalDate, Map, Map)"})
  public void testNewAnalyticModelFromCurvesAndVols4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    HashMap<String, Curve> curvesMap = new HashMap<>();

    // Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate, curvesMap, new HashMap<>());

    // Assert
    assertTrue(actualAnalyticModelFromCurvesAndVols.getCurves().isEmpty());
    assertTrue(actualAnalyticModelFromCurvesAndVols.getVolatilitySurfaces().isEmpty());
    assertSame(referenceDate, actualAnalyticModelFromCurvesAndVols.getReferenceDate());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate, Curve[])}.
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate, Curve[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(LocalDate, Curve[])"})
  public void testNewAnalyticModelFromCurvesAndVols5() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    Curve[] curves = new Curve[] {discountCurveFromForwardCurve};

    // Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate, curves);

    // Assert
    Map<String, Curve> curves2 = actualAnalyticModelFromCurvesAndVols.getCurves();
    assertEquals(1, curves2.size());
    assertSame(
        discountCurveFromForwardCurve,
        curves2.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate, Curve[])}.
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate, Curve[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(LocalDate, Curve[])"})
  public void testNewAnalyticModelFromCurvesAndVols6() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    CurveFromProductOfCurves curveFromProductOfCurves =
        new CurveFromProductOfCurves(
            "Name", referenceDate2, new DiscountCurveFromForwardCurve("Forward Curve Name"));
    Curve[] curves = new Curve[] {curveFromProductOfCurves};

    // Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate, curves);

    // Assert
    Map<String, Curve> curves2 = actualAnalyticModelFromCurvesAndVols.getCurves();
    assertEquals(1, curves2.size());
    assertSame(curveFromProductOfCurves, curves2.get("Name"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(Collection)}.
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(Collection)"})
  public void testNewAnalyticModelFromCurvesAndVols7() {
    // Arrange
    LinkedHashSet<Curve> curves = new LinkedHashSet<>();
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    curves.add(discountCurveFromForwardCurve);

    // Act and Assert
    Map<String, Curve> curves2 = new AnalyticModelFromCurvesAndVols(curves).getCurves();
    assertEquals(1, curves2.size());
    assertSame(
        discountCurveFromForwardCurve,
        curves2.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(Collection)}.
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(Collection)"})
  public void testNewAnalyticModelFromCurvesAndVols8() {
    // Arrange
    ArrayList<Curve> curves = new ArrayList<>();
    curves.add(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    curves.add(discountCurveFromForwardCurve);

    // Act and Assert
    Map<String, Curve> curves2 = new AnalyticModelFromCurvesAndVols(curves).getCurves();
    assertEquals(1, curves2.size());
    assertSame(
        discountCurveFromForwardCurve,
        curves2.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate,
   * Collection)}.
   *
   * <ul>
   *   <li>Then return ReferenceDate is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(LocalDate, Collection)"})
  public void testNewAnalyticModelFromCurvesAndVols_thenReturnReferenceDateIsNull() {
    // Arrange
    LinkedHashSet<Curve> curves = new LinkedHashSet<>();
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    BondCurve bondCurve =
        new BondCurve(
            "Name",
            null,
            referenceCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            Type.DISCOUNTFACTOR_DISCOUNTFACTOR);
    curves.add(bondCurve);

    // Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(null, curves);

    // Assert
    assertNull(actualAnalyticModelFromCurvesAndVols.getReferenceDate());
    Map<String, Curve> curves2 = actualAnalyticModelFromCurvesAndVols.getCurves();
    assertEquals(1, curves2.size());
    assertSame(bondCurve, curves2.get(null));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(Collection)}.
   *
   * <ul>
   *   <li>Then return ReferenceDate is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(Collection)"})
  public void testNewAnalyticModelFromCurvesAndVols_thenReturnReferenceDateIsNull2() {
    // Arrange and Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(new ArrayList<>());

    // Assert
    assertNull(actualAnalyticModelFromCurvesAndVols.getReferenceDate());
    assertTrue(actualAnalyticModelFromCurvesAndVols.getCurves().isEmpty());
    assertTrue(actualAnalyticModelFromCurvesAndVols.getVolatilitySurfaces().isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(Curve[])}.
   *
   * <ul>
   *   <li>Then return ReferenceDate is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(Curve[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(Curve[])"})
  public void testNewAnalyticModelFromCurvesAndVols_thenReturnReferenceDateIsNull3() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    Curve[] curves = new Curve[] {discountCurveFromForwardCurve};

    // Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(curves);

    // Assert
    assertNull(actualAnalyticModelFromCurvesAndVols.getReferenceDate());
    Map<String, Curve> curves2 = actualAnalyticModelFromCurvesAndVols.getCurves();
    assertEquals(1, curves2.size());
    assertTrue(actualAnalyticModelFromCurvesAndVols.getVolatilitySurfaces().isEmpty());
    assertSame(
        discountCurveFromForwardCurve,
        curves2.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate,
   * Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Curves Empty.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(LocalDate, Collection)"})
  public void testNewAnalyticModelFromCurvesAndVols_whenArrayList_thenReturnCurvesEmpty() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate, new ArrayList<>());

    // Assert
    assertTrue(actualAnalyticModelFromCurvesAndVols.getCurves().isEmpty());
    assertTrue(actualAnalyticModelFromCurvesAndVols.getVolatilitySurfaces().isEmpty());
    assertSame(referenceDate, actualAnalyticModelFromCurvesAndVols.getReferenceDate());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate, Curve[])}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return ReferenceDate is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(LocalDate, Curve[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(LocalDate, Curve[])"})
  public void testNewAnalyticModelFromCurvesAndVols_whenNull_thenReturnReferenceDateIsNull() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    Curve[] curves = new Curve[] {discountCurveFromForwardCurve};

    // Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(null, curves);

    // Assert
    assertNull(actualAnalyticModelFromCurvesAndVols.getReferenceDate());
    Map<String, Curve> curves2 = actualAnalyticModelFromCurvesAndVols.getCurves();
    assertEquals(1, curves2.size());
    assertSame(
        discountCurveFromForwardCurve,
        curves2.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#getCurve(String)}.
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#getCurve(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve AnalyticModelFromCurvesAndVols.getCurve(String)"})
  public void testGetCurve() {
    // Arrange, Act and Assert
    assertNull(new AnalyticModelFromCurvesAndVols().getCurve("Name"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#getCurves()}.
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#getCurves()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map AnalyticModelFromCurvesAndVols.getCurves()"})
  public void testGetCurves() {
    // Arrange, Act and Assert
    assertTrue(new AnalyticModelFromCurvesAndVols().getCurves().isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurve(Curve)} with {@code curve}.
   *
   * <ul>
   *   <li>Then return {@link AnalyticModelFromCurvesAndVols}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurve(Curve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurve(Curve)"})
  public void testAddCurveWithCurve_thenReturnAnalyticModelFromCurvesAndVols() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    AnalyticModel actualAddCurveResult = analyticModelFromCurvesAndVols.addCurve(curve);

    // Assert
    assertTrue(actualAddCurveResult instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) actualAddCurveResult).getReferenceDate());
    Map<String, Curve> curves = actualAddCurveResult.getCurves();
    assertEquals(1, curves.size());
    assertSame(curve, curves.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurve(Curve)} with {@code curve}.
   *
   * <ul>
   *   <li>Then return {@link AnalyticModelWithVolatilityCubes}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurve(Curve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurve(Curve)"})
  public void testAddCurveWithCurve_thenReturnAnalyticModelWithVolatilityCubes() {
    // Arrange
    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes();
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    AnalyticModel actualAddCurveResult = analyticModelWithVolatilityCubes.addCurve(curve);

    // Assert
    assertTrue(actualAddCurveResult instanceof AnalyticModelWithVolatilityCubes);
    assertNull(((AnalyticModelWithVolatilityCubes) actualAddCurveResult).getReferenceDate());
    Map<String, Curve> curves = actualAddCurveResult.getCurves();
    assertEquals(1, curves.size());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualAddCurveResult).getVolatilityCubes().isEmpty());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualAddCurveResult)
            .getVolatilityCubeNames()
            .isEmpty());
    assertSame(curve, curves.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurve(Curve)} with {@code curve}.
   *
   * <ul>
   *   <li>Then return ReferenceDate toString is {@code 1970-01-01}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurve(Curve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurve(Curve)"})
  public void testAddCurveWithCurve_thenReturnReferenceDateToStringIs19700101() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate);
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    BondCurve curve =
        new BondCurve(
            "Name",
            null,
            referenceCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            Type.DISCOUNTFACTOR_DISCOUNTFACTOR);

    // Act
    AnalyticModel actualAddCurveResult = analyticModelFromCurvesAndVols.addCurve(curve);

    // Assert
    assertTrue(actualAddCurveResult instanceof AnalyticModelFromCurvesAndVols);
    LocalDate referenceDate2 =
        ((AnalyticModelFromCurvesAndVols) actualAddCurveResult).getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    Map<String, Curve> curves = actualAddCurveResult.getCurves();
    assertEquals(1, curves.size());
    assertSame(curve, curves.get(null));
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurve(String, Curve)} with {@code name}, {@code
   * curve}.
   *
   * <ul>
   *   <li>Then return {@link AnalyticModelFromCurvesAndVols}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurve(String, Curve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurve(String, Curve)"})
  public void testAddCurveWithNameCurve_thenReturnAnalyticModelFromCurvesAndVols() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    AnalyticModel actualAddCurveResult = analyticModelFromCurvesAndVols.addCurve("Name", curve);

    // Assert
    assertTrue(actualAddCurveResult instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) actualAddCurveResult).getReferenceDate());
    Map<String, Curve> curves = actualAddCurveResult.getCurves();
    assertEquals(1, curves.size());
    assertSame(curve, curves.get("Name"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurve(String, Curve)} with {@code name}, {@code
   * curve}.
   *
   * <ul>
   *   <li>Then return {@link AnalyticModelWithVolatilityCubes}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurve(String, Curve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurve(String, Curve)"})
  public void testAddCurveWithNameCurve_thenReturnAnalyticModelWithVolatilityCubes() {
    // Arrange
    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes();
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    AnalyticModel actualAddCurveResult = analyticModelWithVolatilityCubes.addCurve("Name", curve);

    // Assert
    assertTrue(actualAddCurveResult instanceof AnalyticModelWithVolatilityCubes);
    assertNull(((AnalyticModelWithVolatilityCubes) actualAddCurveResult).getReferenceDate());
    Map<String, Curve> curves = actualAddCurveResult.getCurves();
    assertEquals(1, curves.size());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualAddCurveResult).getVolatilityCubes().isEmpty());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualAddCurveResult)
            .getVolatilityCubeNames()
            .isEmpty());
    assertSame(curve, curves.get("Name"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurve(String, Curve)} with {@code name}, {@code
   * curve}.
   *
   * <ul>
   *   <li>Then return ReferenceDate toString is {@code 1970-01-01}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurve(String, Curve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurve(String, Curve)"})
  public void testAddCurveWithNameCurve_thenReturnReferenceDateToStringIs19700101() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate);
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    BondCurve curve =
        new BondCurve(
            "Name",
            null,
            referenceCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            Type.DISCOUNTFACTOR_DISCOUNTFACTOR);

    // Act
    AnalyticModel actualAddCurveResult = analyticModelFromCurvesAndVols.addCurve("Name", curve);

    // Assert
    assertTrue(actualAddCurveResult instanceof AnalyticModelFromCurvesAndVols);
    LocalDate referenceDate2 =
        ((AnalyticModelFromCurvesAndVols) actualAddCurveResult).getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    Map<String, Curve> curves = actualAddCurveResult.getCurves();
    assertEquals(1, curves.size());
    assertSame(curve, curves.get("Name"));
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurves(Curve[])} with {@code Curve[]}.
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurves(Curve[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurves(Curve[])"})
  public void testAddCurvesWithCurve() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    CurveFromProductOfCurves curveFromProductOfCurves =
        new CurveFromProductOfCurves(
            "Name", referenceDate2, new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    AnalyticModel actualAddCurvesResult =
        analyticModelFromCurvesAndVols.addCurves(curveFromProductOfCurves);

    // Assert
    assertTrue(actualAddCurvesResult instanceof AnalyticModelFromCurvesAndVols);
    Map<String, Curve> curves = actualAddCurvesResult.getCurves();
    assertEquals(1, curves.size());
    assertSame(curveFromProductOfCurves, curves.get("Name"));
    assertSame(
        referenceDate, ((AnalyticModelFromCurvesAndVols) actualAddCurvesResult).getReferenceDate());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurves(Curve[])} with {@code Curve[]}.
   *
   * <ul>
   *   <li>Then return {@link AnalyticModelWithVolatilityCubes}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurves(Curve[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurves(Curve[])"})
  public void testAddCurvesWithCurve_thenReturnAnalyticModelWithVolatilityCubes() {
    // Arrange
    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes();

    // Act
    AnalyticModel actualAddCurvesResult =
        analyticModelWithVolatilityCubes.addCurves(
            new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    assertTrue(actualAddCurvesResult instanceof AnalyticModelWithVolatilityCubes);
    assertNull(((AnalyticModelWithVolatilityCubes) actualAddCurvesResult).getReferenceDate());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualAddCurvesResult).getVolatilityCubes().isEmpty());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualAddCurvesResult)
            .getVolatilityCubeNames()
            .isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurves(Curve[])} with {@code Curve[]}.
   *
   * <ul>
   *   <li>Then return ReferenceDate is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurves(Curve[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurves(Curve[])"})
  public void testAddCurvesWithCurve_thenReturnReferenceDateIsNull() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    AnalyticModel actualAddCurvesResult =
        analyticModelFromCurvesAndVols.addCurves(discountCurveFromForwardCurve);

    // Assert
    assertTrue(actualAddCurvesResult instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) actualAddCurvesResult).getReferenceDate());
    Map<String, Curve> curves = actualAddCurvesResult.getCurves();
    assertEquals(1, curves.size());
    assertSame(
        discountCurveFromForwardCurve,
        curves.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurves(Curve[])} with {@code Curve[]}.
   *
   * <ul>
   *   <li>Then return ReferenceDate toString is {@code 1970-01-01}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurves(Curve[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurves(Curve[])"})
  public void testAddCurvesWithCurve_thenReturnReferenceDateToStringIs19700101() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate);
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    AnalyticModel actualAddCurvesResult =
        analyticModelFromCurvesAndVols.addCurves(discountCurveFromForwardCurve);

    // Assert
    assertTrue(actualAddCurvesResult instanceof AnalyticModelFromCurvesAndVols);
    LocalDate referenceDate2 =
        ((AnalyticModelFromCurvesAndVols) actualAddCurvesResult).getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    Map<String, Curve> curves = actualAddCurvesResult.getCurves();
    assertEquals(1, curves.size());
    assertSame(
        discountCurveFromForwardCurve,
        curves.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurves(Set)} with {@code Set}.
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurves(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurves(Set)"})
  public void testAddCurvesWithSet() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(LocalDate.of(1970, 1, 1));

    LinkedHashSet<Curve> curves = new LinkedHashSet<>();
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    BondCurve bondCurve =
        new BondCurve(
            "Name",
            null,
            referenceCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            Type.DISCOUNTFACTOR_DISCOUNTFACTOR);
    curves.add(bondCurve);

    // Act
    AnalyticModel actualAddCurvesResult = analyticModelFromCurvesAndVols.addCurves(curves);

    // Assert
    assertTrue(actualAddCurvesResult instanceof AnalyticModelFromCurvesAndVols);
    Map<String, Curve> curves2 = actualAddCurvesResult.getCurves();
    assertEquals(1, curves2.size());
    assertSame(bondCurve, curves2.get(null));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurves(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Then Curves {@code null} return {@link BondCurve}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurves(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurves(Set)"})
  public void testAddCurvesWithSet_thenCurvesNullReturnBondCurve() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols((LocalDate) null);

    LinkedHashSet<Curve> curves = new LinkedHashSet<>();
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    BondCurve bondCurve =
        new BondCurve(
            "Name",
            null,
            referenceCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            Type.DISCOUNTFACTOR_DISCOUNTFACTOR);
    curves.add(bondCurve);

    // Act
    AnalyticModel actualAddCurvesResult = analyticModelFromCurvesAndVols.addCurves(curves);

    // Assert
    assertTrue(actualAddCurvesResult instanceof AnalyticModelFromCurvesAndVols);
    Map<String, Curve> curves2 = actualAddCurvesResult.getCurves();
    assertEquals(1, curves2.size());
    Curve getResult = curves2.get(null);
    assertTrue(getResult instanceof BondCurve);
    Curve referenceCurve2 = ((BondCurve) getResult).getReferenceCurve();
    assertTrue(referenceCurve2 instanceof DiscountCurveFromForwardCurve);
    Curve spreadCurve = ((BondCurve) getResult).getSpreadCurve();
    assertTrue(spreadCurve instanceof DiscountCurveFromForwardCurve);
    assertEquals("DiscountCurveFromForwardCurve(Forward Curve Name)", referenceCurve2.getName());
    assertNull(referenceCurve2.getParameter());
    assertNull(referenceCurve2.getReferenceDate());
    assertEquals(referenceCurve2, spreadCurve);
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurves(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Then return {@link AnalyticModelWithVolatilityCubes}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurves(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurves(Set)"})
  public void testAddCurvesWithSet_thenReturnAnalyticModelWithVolatilityCubes() {
    // Arrange
    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes();

    // Act
    AnalyticModel actualAddCurvesResult =
        analyticModelWithVolatilityCubes.addCurves(new HashSet<>());

    // Assert
    assertTrue(actualAddCurvesResult instanceof AnalyticModelWithVolatilityCubes);
    assertTrue(actualAddCurvesResult.getCurves().isEmpty());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualAddCurvesResult).getVolatilityCubes().isEmpty());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualAddCurvesResult)
            .getVolatilityCubeNames()
            .isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurves(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Then return ReferenceDate is {@link LocalDate} with {@code 1970} and one and one.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurves(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurves(Set)"})
  public void testAddCurvesWithSet_thenReturnReferenceDateIsLocalDateWith1970AndOneAndOne() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate);

    LinkedHashSet<Curve> curves = new LinkedHashSet<>();
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    CurveFromProductOfCurves curveFromProductOfCurves =
        new CurveFromProductOfCurves(
            "Name", referenceDate2, new DiscountCurveFromForwardCurve("Forward Curve Name"));
    curves.add(curveFromProductOfCurves);

    // Act
    AnalyticModel actualAddCurvesResult = analyticModelFromCurvesAndVols.addCurves(curves);

    // Assert
    assertTrue(actualAddCurvesResult instanceof AnalyticModelFromCurvesAndVols);
    Map<String, Curve> curves2 = actualAddCurvesResult.getCurves();
    assertEquals(1, curves2.size());
    assertSame(curveFromProductOfCurves, curves2.get("Name"));
    assertSame(
        referenceDate, ((AnalyticModelFromCurvesAndVols) actualAddCurvesResult).getReferenceDate());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurves(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Then return ReferenceDate is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurves(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurves(Set)"})
  public void testAddCurvesWithSet_thenReturnReferenceDateIsNull() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    // Act
    AnalyticModel actualAddCurvesResult = analyticModelFromCurvesAndVols.addCurves(new HashSet<>());

    // Assert
    assertTrue(actualAddCurvesResult instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) actualAddCurvesResult).getReferenceDate());
    assertTrue(actualAddCurvesResult.getCurves().isEmpty());
    assertTrue(actualAddCurvesResult.getVolatilitySurfaces().isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#getDiscountCurve(String)}.
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#getDiscountCurve(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.marketdata.model.curves.DiscountCurve AnalyticModelFromCurvesAndVols.getDiscountCurve(String)"
  })
  public void testGetDiscountCurve() {
    // Arrange, Act and Assert
    assertNull(new AnalyticModelFromCurvesAndVols().getDiscountCurve("3"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#getForwardCurve(String)}.
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#getForwardCurve(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.marketdata.model.curves.ForwardCurve AnalyticModelFromCurvesAndVols.getForwardCurve(String)"
  })
  public void testGetForwardCurve() {
    // Arrange, Act and Assert
    assertNull(new AnalyticModelFromCurvesAndVols().getForwardCurve("Forward Curve Name"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#getVolatilitySurface(String)}.
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#getVolatilitySurface(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VolatilitySurface AnalyticModelFromCurvesAndVols.getVolatilitySurface(String)"
  })
  public void testGetVolatilitySurface() {
    // Arrange, Act and Assert
    assertNull(new AnalyticModelFromCurvesAndVols().getVolatilitySurface("Name"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#getVolatilitySurfaces()}.
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#getVolatilitySurfaces()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map AnalyticModelFromCurvesAndVols.getVolatilitySurfaces()"})
  public void testGetVolatilitySurfaces() {
    // Arrange, Act and Assert
    assertTrue(new AnalyticModelFromCurvesAndVols().getVolatilitySurfaces().isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurface(VolatilitySurface)}.
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#addVolatilitySurface(VolatilitySurface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurface(VolatilitySurface)"
  })
  public void testAddVolatilitySurface() {
    // Arrange
    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes();
    CapletVolatilitiesParametric volatilitySurface =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    AnalyticModel actualAddVolatilitySurfaceResult =
        analyticModelWithVolatilityCubes.addVolatilitySurface(volatilitySurface);

    // Assert
    Map<String, VolatilitySurface> volatilitySurfaces =
        actualAddVolatilitySurfaceResult.getVolatilitySurfaces();
    assertEquals(1, volatilitySurfaces.size());
    VolatilitySurface getResult = volatilitySurfaces.get("Name");
    assertTrue(getResult instanceof CapletVolatilitiesParametric);
    assertTrue(actualAddVolatilitySurfaceResult instanceof AnalyticModelWithVolatilityCubes);
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualAddVolatilitySurfaceResult)
            .getVolatilityCubes()
            .isEmpty());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualAddVolatilitySurfaceResult)
            .getVolatilityCubeNames()
            .isEmpty());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        ((CapletVolatilitiesParametric) getResult).getParameter(),
        0.0);
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurface(VolatilitySurface)}.
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#addVolatilitySurface(VolatilitySurface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurface(VolatilitySurface)"
  })
  public void testAddVolatilitySurface2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate);
    CapletVolatilitiesParametric volatilitySurface =
        new CapletVolatilitiesParametric("Name", null, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    AnalyticModel actualAddVolatilitySurfaceResult =
        analyticModelFromCurvesAndVols.addVolatilitySurface(volatilitySurface);

    // Assert
    assertTrue(actualAddVolatilitySurfaceResult instanceof AnalyticModelFromCurvesAndVols);
    Map<String, VolatilitySurface> volatilitySurfaces =
        actualAddVolatilitySurfaceResult.getVolatilitySurfaces();
    assertEquals(1, volatilitySurfaces.size());
    assertSame(volatilitySurface, volatilitySurfaces.get("Name"));
    assertSame(
        referenceDate,
        ((AnalyticModelFromCurvesAndVols) actualAddVolatilitySurfaceResult).getReferenceDate());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurface(VolatilitySurface)}.
   *
   * <ul>
   *   <li>Then return ReferenceDate is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#addVolatilitySurface(VolatilitySurface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurface(VolatilitySurface)"
  })
  public void testAddVolatilitySurface_thenReturnReferenceDateIsNull() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();
    CapletVolatilitiesParametric volatilitySurface =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    AnalyticModel actualAddVolatilitySurfaceResult =
        analyticModelFromCurvesAndVols.addVolatilitySurface(volatilitySurface);

    // Assert
    assertTrue(actualAddVolatilitySurfaceResult instanceof AnalyticModelFromCurvesAndVols);
    assertNull(
        ((AnalyticModelFromCurvesAndVols) actualAddVolatilitySurfaceResult).getReferenceDate());
    Map<String, VolatilitySurface> volatilitySurfaces =
        actualAddVolatilitySurfaceResult.getVolatilitySurfaces();
    assertEquals(1, volatilitySurfaces.size());
    assertSame(volatilitySurface, volatilitySurfaces.get("Name"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurface(VolatilitySurface)}.
   *
   * <ul>
   *   <li>Then return ReferenceDate toString is {@code 1970-01-01}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#addVolatilitySurface(VolatilitySurface)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurface(VolatilitySurface)"
  })
  public void testAddVolatilitySurface_thenReturnReferenceDateToStringIs19700101() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate);
    CapletVolatilitiesParametric volatilitySurface =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    AnalyticModel actualAddVolatilitySurfaceResult =
        analyticModelFromCurvesAndVols.addVolatilitySurface(volatilitySurface);

    // Assert
    assertTrue(actualAddVolatilitySurfaceResult instanceof AnalyticModelFromCurvesAndVols);
    LocalDate referenceDate2 =
        ((AnalyticModelFromCurvesAndVols) actualAddVolatilitySurfaceResult).getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    Map<String, VolatilitySurface> volatilitySurfaces =
        actualAddVolatilitySurfaceResult.getVolatilitySurfaces();
    assertEquals(1, volatilitySurfaces.size());
    assertSame(volatilitySurface, volatilitySurfaces.get("Name"));
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)} with {@code Set}.
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurfaces(Set)"})
  public void testAddVolatilitySurfacesWithSet() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate);

    HashSet<VolatilitySurface> volatilitySurfaces = new HashSet<>();
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric("Name", null, 10.0d, 10.0d, 10.0d, 10.0d);
    volatilitySurfaces.add(capletVolatilitiesParametric);

    // Act
    AnalyticModel actualAddVolatilitySurfacesResult =
        analyticModelFromCurvesAndVols.addVolatilitySurfaces(volatilitySurfaces);

    // Assert
    assertTrue(actualAddVolatilitySurfacesResult instanceof AnalyticModelFromCurvesAndVols);
    Map<String, VolatilitySurface> volatilitySurfaces2 =
        actualAddVolatilitySurfacesResult.getVolatilitySurfaces();
    assertEquals(1, volatilitySurfaces2.size());
    assertSame(capletVolatilitiesParametric, volatilitySurfaces2.get("Name"));
    assertSame(
        referenceDate,
        ((AnalyticModelFromCurvesAndVols) actualAddVolatilitySurfacesResult).getReferenceDate());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Then return {@link AnalyticModelWithVolatilityCubes}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurfaces(Set)"})
  public void testAddVolatilitySurfacesWithSet_thenReturnAnalyticModelWithVolatilityCubes() {
    // Arrange
    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes();

    // Act
    AnalyticModel actualAddVolatilitySurfacesResult =
        analyticModelWithVolatilityCubes.addVolatilitySurfaces(new HashSet<>());

    // Assert
    assertTrue(actualAddVolatilitySurfacesResult instanceof AnalyticModelWithVolatilityCubes);
    assertTrue(actualAddVolatilitySurfacesResult.getVolatilitySurfaces().isEmpty());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualAddVolatilitySurfacesResult)
            .getVolatilityCubes()
            .isEmpty());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualAddVolatilitySurfacesResult)
            .getVolatilityCubeNames()
            .isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Then return ReferenceDate is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurfaces(Set)"})
  public void testAddVolatilitySurfacesWithSet_thenReturnReferenceDateIsNull() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    HashSet<VolatilitySurface> volatilitySurfaces = new HashSet<>();
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);
    volatilitySurfaces.add(capletVolatilitiesParametric);

    // Act
    AnalyticModel actualAddVolatilitySurfacesResult =
        analyticModelFromCurvesAndVols.addVolatilitySurfaces(volatilitySurfaces);

    // Assert
    assertTrue(actualAddVolatilitySurfacesResult instanceof AnalyticModelFromCurvesAndVols);
    assertNull(
        ((AnalyticModelFromCurvesAndVols) actualAddVolatilitySurfacesResult).getReferenceDate());
    Map<String, VolatilitySurface> volatilitySurfaces2 =
        actualAddVolatilitySurfacesResult.getVolatilitySurfaces();
    assertEquals(1, volatilitySurfaces2.size());
    assertSame(capletVolatilitiesParametric, volatilitySurfaces2.get("Name"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Then return ReferenceDate toString is {@code 1970-01-01}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurfaces(Set)"})
  public void testAddVolatilitySurfacesWithSet_thenReturnReferenceDateToStringIs19700101() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate);

    HashSet<VolatilitySurface> volatilitySurfaces = new HashSet<>();
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);
    volatilitySurfaces.add(capletVolatilitiesParametric);

    // Act
    AnalyticModel actualAddVolatilitySurfacesResult =
        analyticModelFromCurvesAndVols.addVolatilitySurfaces(volatilitySurfaces);

    // Assert
    assertTrue(actualAddVolatilitySurfacesResult instanceof AnalyticModelFromCurvesAndVols);
    LocalDate referenceDate2 =
        ((AnalyticModelFromCurvesAndVols) actualAddVolatilitySurfacesResult).getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    Map<String, VolatilitySurface> volatilitySurfaces2 =
        actualAddVolatilitySurfacesResult.getVolatilitySurfaces();
    assertEquals(1, volatilitySurfaces2.size());
    assertSame(capletVolatilitiesParametric, volatilitySurfaces2.get("Name"));
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   *   <li>Then return Curves Empty.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurfaces(Set)"})
  public void testAddVolatilitySurfacesWithSet_whenHashSet_thenReturnCurvesEmpty() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    // Act
    AnalyticModel actualAddVolatilitySurfacesResult =
        analyticModelFromCurvesAndVols.addVolatilitySurfaces(new HashSet<>());

    // Assert
    assertTrue(actualAddVolatilitySurfacesResult instanceof AnalyticModelFromCurvesAndVols);
    assertNull(
        ((AnalyticModelFromCurvesAndVols) actualAddVolatilitySurfacesResult).getReferenceDate());
    assertTrue(actualAddVolatilitySurfacesResult.getCurves().isEmpty());
    assertTrue(actualAddVolatilitySurfacesResult.getVolatilitySurfaces().isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(VolatilitySurface[])} with
   * {@code VolatilitySurface[]}.
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(VolatilitySurface[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurfaces(VolatilitySurface[])"
  })
  public void testAddVolatilitySurfacesWithVolatilitySurface() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate);
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    AnalyticModel actualAddVolatilitySurfacesResult =
        analyticModelFromCurvesAndVols.addVolatilitySurfaces(capletVolatilitiesParametric);

    // Assert
    assertTrue(actualAddVolatilitySurfacesResult instanceof AnalyticModelFromCurvesAndVols);
    LocalDate referenceDate2 =
        ((AnalyticModelFromCurvesAndVols) actualAddVolatilitySurfacesResult).getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    Map<String, VolatilitySurface> volatilitySurfaces =
        actualAddVolatilitySurfacesResult.getVolatilitySurfaces();
    assertEquals(1, volatilitySurfaces.size());
    assertSame(capletVolatilitiesParametric, volatilitySurfaces.get("Name"));
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(VolatilitySurface[])} with
   * {@code VolatilitySurface[]}.
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(VolatilitySurface[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurfaces(VolatilitySurface[])"
  })
  public void testAddVolatilitySurfacesWithVolatilitySurface2() {
    // Arrange
    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes();
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    AnalyticModel actualAddVolatilitySurfacesResult =
        analyticModelWithVolatilityCubes.addVolatilitySurfaces(capletVolatilitiesParametric);

    // Assert
    Map<String, VolatilitySurface> volatilitySurfaces =
        actualAddVolatilitySurfacesResult.getVolatilitySurfaces();
    assertEquals(1, volatilitySurfaces.size());
    VolatilitySurface getResult = volatilitySurfaces.get("Name");
    assertTrue(getResult instanceof CapletVolatilitiesParametric);
    assertTrue(actualAddVolatilitySurfacesResult instanceof AnalyticModelWithVolatilityCubes);
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualAddVolatilitySurfacesResult)
            .getVolatilityCubes()
            .isEmpty());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualAddVolatilitySurfacesResult)
            .getVolatilityCubeNames()
            .isEmpty());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        ((CapletVolatilitiesParametric) getResult).getParameter(),
        0.0);
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(VolatilitySurface[])} with
   * {@code VolatilitySurface[]}.
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(VolatilitySurface[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurfaces(VolatilitySurface[])"
  })
  public void testAddVolatilitySurfacesWithVolatilitySurface3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(referenceDate);
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric("Name", null, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    AnalyticModel actualAddVolatilitySurfacesResult =
        analyticModelFromCurvesAndVols.addVolatilitySurfaces(capletVolatilitiesParametric);

    // Assert
    assertTrue(actualAddVolatilitySurfacesResult instanceof AnalyticModelFromCurvesAndVols);
    Map<String, VolatilitySurface> volatilitySurfaces =
        actualAddVolatilitySurfacesResult.getVolatilitySurfaces();
    assertEquals(1, volatilitySurfaces.size());
    assertSame(capletVolatilitiesParametric, volatilitySurfaces.get("Name"));
    assertSame(
        referenceDate,
        ((AnalyticModelFromCurvesAndVols) actualAddVolatilitySurfacesResult).getReferenceDate());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(VolatilitySurface[])} with
   * {@code VolatilitySurface[]}.
   *
   * <ul>
   *   <li>Then return ReferenceDate is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(VolatilitySurface[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurfaces(VolatilitySurface[])"
  })
  public void testAddVolatilitySurfacesWithVolatilitySurface_thenReturnReferenceDateIsNull() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    AnalyticModel actualAddVolatilitySurfacesResult =
        analyticModelFromCurvesAndVols.addVolatilitySurfaces(capletVolatilitiesParametric);

    // Assert
    assertTrue(actualAddVolatilitySurfacesResult instanceof AnalyticModelFromCurvesAndVols);
    assertNull(
        ((AnalyticModelFromCurvesAndVols) actualAddVolatilitySurfacesResult).getReferenceDate());
    Map<String, VolatilitySurface> volatilitySurfaces =
        actualAddVolatilitySurfacesResult.getVolatilitySurfaces();
    assertEquals(1, volatilitySurfaces.size());
    assertSame(capletVolatilitiesParametric, volatilitySurfaces.get("Name"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#clone()}.
   *
   * <ul>
   *   <li>Given {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols()}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModelFromCurvesAndVols AnalyticModelFromCurvesAndVols.clone()"})
  public void testClone_givenAnalyticModelFromCurvesAndVols() {
    // Arrange and Act
    AnalyticModelFromCurvesAndVols actualCloneResult = new AnalyticModelFromCurvesAndVols().clone();

    // Assert
    assertNull(actualCloneResult.getReferenceDate());
    assertTrue(actualCloneResult.getCurves().isEmpty());
    assertTrue(actualCloneResult.getVolatilitySurfaces().isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#clone()}.
   *
   * <ul>
   *   <li>Then return {@link AnalyticModelWithVolatilityCubes}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModelFromCurvesAndVols AnalyticModelFromCurvesAndVols.clone()"})
  public void testClone_thenReturnAnalyticModelWithVolatilityCubes() {
    // Arrange and Act
    AnalyticModelWithVolatilityCubes actualCloneResult =
        new AnalyticModelWithVolatilityCubes().clone();

    // Assert
    assertTrue(actualCloneResult instanceof AnalyticModelWithVolatilityCubes);
    assertNull(actualCloneResult.getReferenceDate());
    assertTrue(actualCloneResult.getCurves().isEmpty());
    assertTrue(actualCloneResult.getVolatilitySurfaces().isEmpty());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualCloneResult).getVolatilityCubes().isEmpty());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualCloneResult).getVolatilityCubeNames().isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#getCloneForParameter(Map)}.
   *
   * <ul>
   *   <li>Then return {@link AnalyticModelWithVolatilityCubes}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#getCloneForParameter(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.getCloneForParameter(Map)"})
  public void testGetCloneForParameter_thenReturnAnalyticModelWithVolatilityCubes()
      throws CloneNotSupportedException {
    // Arrange
    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes();

    // Act
    AnalyticModel actualCloneForParameter =
        analyticModelWithVolatilityCubes.getCloneForParameter(new HashMap<>());

    // Assert
    assertTrue(actualCloneForParameter instanceof AnalyticModelWithVolatilityCubes);
    assertNull(((AnalyticModelWithVolatilityCubes) actualCloneForParameter).getReferenceDate());
    assertTrue(actualCloneForParameter.getCurves().isEmpty());
    assertTrue(actualCloneForParameter.getVolatilitySurfaces().isEmpty());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualCloneForParameter)
            .getVolatilityCubes()
            .isEmpty());
    assertTrue(
        ((AnalyticModelWithVolatilityCubes) actualCloneForParameter)
            .getVolatilityCubeNames()
            .isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#getCloneForParameter(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return {@link AnalyticModelFromCurvesAndVols}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#getCloneForParameter(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.getCloneForParameter(Map)"})
  public void testGetCloneForParameter_whenHashMap_thenReturnAnalyticModelFromCurvesAndVols()
      throws CloneNotSupportedException {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    // Act
    AnalyticModel actualCloneForParameter =
        analyticModelFromCurvesAndVols.getCloneForParameter(new HashMap<>());

    // Assert
    assertTrue(actualCloneForParameter instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) actualCloneForParameter).getReferenceDate());
    assertTrue(actualCloneForParameter.getCurves().isEmpty());
    assertTrue(actualCloneForParameter.getVolatilitySurfaces().isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#getCloneForParameter(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link AnalyticModelFromCurvesAndVols}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#getCloneForParameter(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.getCloneForParameter(Map)"})
  public void testGetCloneForParameter_whenNull_thenReturnAnalyticModelFromCurvesAndVols()
      throws CloneNotSupportedException {
    // Arrange and Act
    AnalyticModel actualCloneForParameter =
        new AnalyticModelFromCurvesAndVols().getCloneForParameter(null);

    // Assert
    assertTrue(actualCloneForParameter instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) actualCloneForParameter).getReferenceDate());
    assertTrue(actualCloneForParameter.getCurves().isEmpty());
    assertTrue(actualCloneForParameter.getVolatilitySurfaces().isEmpty());
  }
}
