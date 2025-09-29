package net.finmath.modelling.descriptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import net.finmath.marketdata.model.curves.Curve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.volatilities.CapletVolatilitiesParametric;
import net.finmath.marketdata.model.volatilities.VolatilitySurface;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AnalyticModelDescriptorDiffblueTest {
  /**
   * Test {@link AnalyticModelDescriptor#AnalyticModelDescriptor(LocalDate, Collection,
   * Collection)}.
   *
   * <p>Method under test: {@link AnalyticModelDescriptor#AnalyticModelDescriptor(LocalDate,
   * Collection, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelDescriptor.<init>(LocalDate, Collection, Collection)"})
  public void testNewAnalyticModelDescriptor() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    LinkedHashSet<VolatilitySurface> surfaces = new LinkedHashSet<>();
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);
    surfaces.add(capletVolatilitiesParametric);

    // Act
    AnalyticModelDescriptor actualAnalyticModelDescriptor =
        new AnalyticModelDescriptor(referenceDate, null, surfaces);

    // Assert
    Map<String, VolatilitySurface> volatilitySurfaceMap =
        actualAnalyticModelDescriptor.getVolatilitySurfaceMap();
    assertEquals(1, volatilitySurfaceMap.size());
    assertSame(capletVolatilitiesParametric, volatilitySurfaceMap.get("Name"));
  }

  /**
   * Test {@link AnalyticModelDescriptor#AnalyticModelDescriptor(LocalDate, Collection,
   * Collection)}.
   *
   * <p>Method under test: {@link AnalyticModelDescriptor#AnalyticModelDescriptor(LocalDate,
   * Collection, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelDescriptor.<init>(LocalDate, Collection, Collection)"})
  public void testNewAnalyticModelDescriptor2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    LinkedHashSet<Curve> curves = new LinkedHashSet<>();
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    curves.add(discountCurveFromForwardCurve);

    // Act
    AnalyticModelDescriptor actualAnalyticModelDescriptor =
        new AnalyticModelDescriptor(referenceDate, curves, null);

    // Assert
    Map<String, Curve> curvesMap = actualAnalyticModelDescriptor.getCurvesMap();
    assertEquals(1, curvesMap.size());
    assertSame(
        discountCurveFromForwardCurve,
        curvesMap.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelDescriptor#AnalyticModelDescriptor(LocalDate, Collection,
   * Collection)}.
   *
   * <p>Method under test: {@link AnalyticModelDescriptor#AnalyticModelDescriptor(LocalDate,
   * Collection, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelDescriptor.<init>(LocalDate, Collection, Collection)"})
  public void testNewAnalyticModelDescriptor3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    ArrayList<Curve> curves = new ArrayList<>();
    curves.add(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    curves.add(discountCurveFromForwardCurve);

    // Act
    AnalyticModelDescriptor actualAnalyticModelDescriptor =
        new AnalyticModelDescriptor(referenceDate, curves, new ArrayList<>());

    // Assert
    Map<String, Curve> curvesMap = actualAnalyticModelDescriptor.getCurvesMap();
    assertEquals(1, curvesMap.size());
    assertSame(
        discountCurveFromForwardCurve,
        curvesMap.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelDescriptor#AnalyticModelDescriptor(LocalDate, Collection,
   * Collection)}.
   *
   * <p>Method under test: {@link AnalyticModelDescriptor#AnalyticModelDescriptor(LocalDate,
   * Collection, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelDescriptor.<init>(LocalDate, Collection, Collection)"})
  public void testNewAnalyticModelDescriptor4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Curve> curves = new ArrayList<>();

    ArrayList<VolatilitySurface> surfaces = new ArrayList<>();
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);
    surfaces.add(capletVolatilitiesParametric);
    CapletVolatilitiesParametric capletVolatilitiesParametric2 =
        new CapletVolatilitiesParametric(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d);
    surfaces.add(capletVolatilitiesParametric2);

    // Act
    AnalyticModelDescriptor actualAnalyticModelDescriptor =
        new AnalyticModelDescriptor(referenceDate, curves, surfaces);

    // Assert
    Map<String, VolatilitySurface> volatilitySurfaceMap =
        actualAnalyticModelDescriptor.getVolatilitySurfaceMap();
    assertEquals(1, volatilitySurfaceMap.size());
    assertSame(capletVolatilitiesParametric2, volatilitySurfaceMap.get("Name"));
  }

  /**
   * Test {@link AnalyticModelDescriptor#AnalyticModelDescriptor(LocalDate, Map, Map)}.
   *
   * <p>Method under test: {@link AnalyticModelDescriptor#AnalyticModelDescriptor(LocalDate, Map,
   * Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelDescriptor.<init>(LocalDate, Map, Map)"})
  public void testNewAnalyticModelDescriptor5() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    HashMap<String, Curve> curvesMap = new HashMap<>();

    // Act
    AnalyticModelDescriptor actualAnalyticModelDescriptor =
        new AnalyticModelDescriptor(referenceDate, curvesMap, new HashMap<>());

    // Assert
    assertTrue(actualAnalyticModelDescriptor.getCurvesMap().isEmpty());
    assertTrue(actualAnalyticModelDescriptor.getVolatilitySurfaceMap().isEmpty());
    assertSame(referenceDate, actualAnalyticModelDescriptor.getReferenceDate());
  }

  /**
   * Test {@link AnalyticModelDescriptor#AnalyticModelDescriptor(LocalDate, Collection,
   * Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return CurvesMap Empty.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelDescriptor#AnalyticModelDescriptor(LocalDate,
   * Collection, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelDescriptor.<init>(LocalDate, Collection, Collection)"})
  public void testNewAnalyticModelDescriptor_whenArrayList_thenReturnCurvesMapEmpty() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Curve> curves = new ArrayList<>();

    // Act
    AnalyticModelDescriptor actualAnalyticModelDescriptor =
        new AnalyticModelDescriptor(referenceDate, curves, new ArrayList<>());

    // Assert
    assertTrue(actualAnalyticModelDescriptor.getCurvesMap().isEmpty());
    assertTrue(actualAnalyticModelDescriptor.getVolatilitySurfaceMap().isEmpty());
    assertSame(referenceDate, actualAnalyticModelDescriptor.getReferenceDate());
  }

  /**
   * Test {@link AnalyticModelDescriptor#AnalyticModelDescriptor(LocalDate, Collection,
   * Collection)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return CurvesMap Empty.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelDescriptor#AnalyticModelDescriptor(LocalDate,
   * Collection, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelDescriptor.<init>(LocalDate, Collection, Collection)"})
  public void testNewAnalyticModelDescriptor_whenNull_thenReturnCurvesMapEmpty() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    AnalyticModelDescriptor actualAnalyticModelDescriptor =
        new AnalyticModelDescriptor(referenceDate, (Collection<Curve>) null, null);

    // Assert
    assertTrue(actualAnalyticModelDescriptor.getCurvesMap().isEmpty());
    assertTrue(actualAnalyticModelDescriptor.getVolatilitySurfaceMap().isEmpty());
    assertSame(referenceDate, actualAnalyticModelDescriptor.getReferenceDate());
  }

  /**
   * Test {@link AnalyticModelDescriptor#version()}.
   *
   * <p>Method under test: {@link AnalyticModelDescriptor#version()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Integer AnalyticModelDescriptor.version()"})
  public void testVersion() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Curve> curves = new ArrayList<>();

    AnalyticModelDescriptor analyticModelDescriptor =
        new AnalyticModelDescriptor(referenceDate, curves, new ArrayList<>());

    // Act and Assert
    assertEquals(1, analyticModelDescriptor.version().intValue());
  }

  /**
   * Test {@link AnalyticModelDescriptor#name()}.
   *
   * <p>Method under test: {@link AnalyticModelDescriptor#name()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AnalyticModelDescriptor.name()"})
  public void testName() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Curve> curves = new ArrayList<>();

    AnalyticModelDescriptor analyticModelDescriptor =
        new AnalyticModelDescriptor(referenceDate, curves, new ArrayList<>());

    // Act and Assert
    assertEquals("Analytic model", analyticModelDescriptor.name());
  }

  /**
   * Test {@link AnalyticModelDescriptor#getReferenceDate()}.
   *
   * <p>Method under test: {@link AnalyticModelDescriptor#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate AnalyticModelDescriptor.getReferenceDate()"})
  public void testGetReferenceDate() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Curve> curves = new ArrayList<>();

    AnalyticModelDescriptor analyticModelDescriptor =
        new AnalyticModelDescriptor(referenceDate, curves, new ArrayList<>());

    // Act
    LocalDate actualReferenceDate = analyticModelDescriptor.getReferenceDate();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link AnalyticModelDescriptor#getCurvesMap()}.
   *
   * <p>Method under test: {@link AnalyticModelDescriptor#getCurvesMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map AnalyticModelDescriptor.getCurvesMap()"})
  public void testGetCurvesMap() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Curve> curves = new ArrayList<>();

    AnalyticModelDescriptor analyticModelDescriptor =
        new AnalyticModelDescriptor(referenceDate, curves, new ArrayList<>());

    // Act and Assert
    assertTrue(analyticModelDescriptor.getCurvesMap().isEmpty());
  }

  /**
   * Test {@link AnalyticModelDescriptor#getVolatilitySurfaceMap()}.
   *
   * <p>Method under test: {@link AnalyticModelDescriptor#getVolatilitySurfaceMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map AnalyticModelDescriptor.getVolatilitySurfaceMap()"})
  public void testGetVolatilitySurfaceMap() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ArrayList<Curve> curves = new ArrayList<>();

    AnalyticModelDescriptor analyticModelDescriptor =
        new AnalyticModelDescriptor(referenceDate, curves, new ArrayList<>());

    // Act and Assert
    assertTrue(analyticModelDescriptor.getVolatilitySurfaceMap().isEmpty());
  }
}
