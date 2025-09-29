package net.finmath.marketdata2.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import net.finmath.marketdata2.calibration.ParameterObject;
import net.finmath.marketdata2.model.curves.Curve;
import net.finmath.marketdata2.model.curves.CurveInterpolation;
import net.finmath.marketdata2.model.curves.CurveInterpolation.Builder;
import net.finmath.marketdata2.model.curves.CurveInterpolation.ExtrapolationMethod;
import net.finmath.marketdata2.model.curves.CurveInterpolation.InterpolationEntity;
import net.finmath.marketdata2.model.curves.CurveInterpolation.InterpolationMethod;
import net.finmath.marketdata2.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata2.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata2.model.volatilities.VolatilitySurface;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarExcludingWeekends;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AnalyticModelFromCurvesAndVolsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link
   *       AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(RandomVariableFactory)}
   *   <li>{@link AnalyticModelFromCurvesAndVols#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnalyticModelFromCurvesAndVols.<init>(RandomVariableFactory)",
    "String AnalyticModelFromCurvesAndVols.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "AnalyticModelFromCuvesAndVols: curves=[]",
        new AnalyticModelFromCurvesAndVols(new RandomVariableFloatFactory()).toString());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols()}.
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>()"})
  public void testNewAnalyticModelFromCurvesAndVols() {
    // Arrange and Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    // Assert
    assertTrue(actualAnalyticModelFromCurvesAndVols.getCurves().isEmpty());
    assertTrue(actualAnalyticModelFromCurvesAndVols.getVolatilitySurfaces().isEmpty());
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
  public void testNewAnalyticModelFromCurvesAndVols2() {
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
  public void testNewAnalyticModelFromCurvesAndVols3() {
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
   * Test {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(RandomVariableFactory, Curve[])}.
   *
   * <ul>
   *   <li>Then return Curves size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(RandomVariableFactory, Curve[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(RandomVariableFactory, Curve[])"})
  public void testNewAnalyticModelFromCurvesAndVols_thenReturnCurvesSizeIsOne() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    Curve[] curves = new Curve[] {discountCurveFromForwardCurve};

    // Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(randomVariableFactory, curves);

    // Assert
    Map<String, Curve> curves2 = actualAnalyticModelFromCurvesAndVols.getCurves();
    assertEquals(1, curves2.size());
    assertTrue(actualAnalyticModelFromCurvesAndVols.getVolatilitySurfaces().isEmpty());
    assertSame(
        discountCurveFromForwardCurve,
        curves2.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(Curve[])}.
   *
   * <ul>
   *   <li>Then return Curves size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(Curve[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(Curve[])"})
  public void testNewAnalyticModelFromCurvesAndVols_thenReturnCurvesSizeIsOne2() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    Curve[] curves = new Curve[] {discountCurveFromForwardCurve};

    // Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(curves);

    // Assert
    Map<String, Curve> curves2 = actualAnalyticModelFromCurvesAndVols.getCurves();
    assertEquals(1, curves2.size());
    assertTrue(actualAnalyticModelFromCurvesAndVols.getVolatilitySurfaces().isEmpty());
    assertSame(
        discountCurveFromForwardCurve,
        curves2.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Curves Empty.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.<init>(Collection)"})
  public void testNewAnalyticModelFromCurvesAndVols_whenArrayList_thenReturnCurvesEmpty() {
    // Arrange and Act
    AnalyticModelFromCurvesAndVols actualAnalyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols(new ArrayList<>());

    // Assert
    assertTrue(actualAnalyticModelFromCurvesAndVols.getCurves().isEmpty());
    assertTrue(actualAnalyticModelFromCurvesAndVols.getVolatilitySurfaces().isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#getRandomVariableForConstant(double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticModelFromCurvesAndVols#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable AnalyticModelFromCurvesAndVols.getRandomVariableForConstant(double)"
  })
  public void testGetRandomVariableForConstant_thenReturnScalar() {
    // Arrange and Act
    RandomVariable actualRandomVariableForConstant =
        new AnalyticModelFromCurvesAndVols().getRandomVariableForConstant(10.0d);

    // Assert
    assertTrue(actualRandomVariableForConstant instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.abs() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.cos() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.exp() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.expm1() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.invert() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.isNaN() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.sin() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.sqrt() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.squared() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.variance() instanceof Scalar);
    assertNull(actualRandomVariableForConstant.getRealizations());
    assertNull(actualRandomVariableForConstant.getOperator());
    assertNull(actualRandomVariableForConstant.getRealizationsStream());
    assertEquals(0, actualRandomVariableForConstant.getTypePriority());
    assertEquals(0.0d, actualRandomVariableForConstant.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableForConstant.size());
    assertEquals(10.0d, actualRandomVariableForConstant.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMin(), 0.0);
    assertTrue(actualRandomVariableForConstant.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualRandomVariableForConstant.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualRandomVariableForConstant.expectation();
    assertSame(actualRandomVariableForConstant, actualExpectationResult);
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
    Map<String, Curve> curves = actualAddCurveResult.getCurves();
    assertEquals(1, curves.size());
    assertTrue(actualAddCurveResult.getVolatilitySurfaces().isEmpty());
    assertSame(curve, curves.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurve(String, Curve)} with {@code name}, {@code
   * curve}.
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurve(String, Curve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurve(String, Curve)"})
  public void testAddCurveWithNameCurve() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    AnalyticModel actualAddCurveResult = analyticModelFromCurvesAndVols.addCurve("Name", curve);

    // Assert
    assertTrue(actualAddCurveResult instanceof AnalyticModelFromCurvesAndVols);
    Map<String, Curve> curves = actualAddCurveResult.getCurves();
    assertEquals(1, curves.size());
    assertTrue(actualAddCurveResult.getVolatilitySurfaces().isEmpty());
    assertSame(curve, curves.get("Name"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurves(Curve[])} with {@code Curve[]}.
   *
   * <ul>
   *   <li>Then return {@link AnalyticModelFromCurvesAndVols}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurves(Curve[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurves(Curve[])"})
  public void testAddCurvesWithCurve_thenReturnAnalyticModelFromCurvesAndVols() {
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
    Map<String, Curve> curves = actualAddCurvesResult.getCurves();
    assertEquals(1, curves.size());
    assertTrue(actualAddCurvesResult.getVolatilitySurfaces().isEmpty());
    assertSame(
        discountCurveFromForwardCurve,
        curves.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurves(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Then return Curves size is one.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurves(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurves(Set)"})
  public void testAddCurvesWithSet_thenReturnCurvesSizeIsOne() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    LinkedHashSet<Curve> curves = new LinkedHashSet<>();
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    curves.add(discountCurveFromForwardCurve);

    // Act
    AnalyticModel actualAddCurvesResult = analyticModelFromCurvesAndVols.addCurves(curves);

    // Assert
    assertTrue(actualAddCurvesResult instanceof AnalyticModelFromCurvesAndVols);
    Map<String, Curve> curves2 = actualAddCurvesResult.getCurves();
    assertEquals(1, curves2.size());
    assertSame(
        discountCurveFromForwardCurve,
        curves2.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addCurves(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   *   <li>Then return Curves Empty.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addCurves(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addCurves(Set)"})
  public void testAddCurvesWithSet_whenHashSet_thenReturnCurvesEmpty() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    // Act
    AnalyticModel actualAddCurvesResult = analyticModelFromCurvesAndVols.addCurves(new HashSet<>());

    // Assert
    assertTrue(actualAddCurvesResult instanceof AnalyticModelFromCurvesAndVols);
    assertTrue(actualAddCurvesResult.getCurves().isEmpty());
    assertTrue(actualAddCurvesResult.getVolatilitySurfaces().isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#setCurve(Curve)}.
   *
   * <ul>
   *   <li>Then {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols()} Curves size
   *       is one.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#setCurve(Curve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.setCurve(Curve)"})
  public void testSetCurve_thenAnalyticModelFromCurvesAndVolsCurvesSizeIsOne() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve curve = new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    analyticModelFromCurvesAndVols.setCurve(curve);

    // Assert
    Map<String, Curve> curves = analyticModelFromCurvesAndVols.getCurves();
    assertEquals(1, curves.size());
    assertSame(curve, curves.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#setCurves(Curve[])}.
   *
   * <ul>
   *   <li>Then {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols()} Curves size
   *       is one.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#setCurves(Curve[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelFromCurvesAndVols.setCurves(Curve[])"})
  public void testSetCurves_thenAnalyticModelFromCurvesAndVolsCurvesSizeIsOne() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    analyticModelFromCurvesAndVols.setCurves(new Curve[] {discountCurveFromForwardCurve});

    // Assert
    Map<String, Curve> curves = analyticModelFromCurvesAndVols.getCurves();
    assertEquals(1, curves.size());
    assertSame(
        discountCurveFromForwardCurve,
        curves.get("DiscountCurveFromForwardCurve(Forward Curve Name)"));
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
    "net.finmath.marketdata2.model.curves.DiscountCurveInterface AnalyticModelFromCurvesAndVols.getDiscountCurve(String)"
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
    "net.finmath.marketdata2.model.curves.ForwardCurveInterface AnalyticModelFromCurvesAndVols.getForwardCurve(String)"
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
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>Then return {@link AnalyticModelFromCurvesAndVols}.
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
  public void testAddVolatilitySurface_givenName_thenReturnAnalyticModelFromCurvesAndVols() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    VolatilitySurface volatilitySurface = mock(VolatilitySurface.class);
    when(volatilitySurface.getName()).thenReturn("Name");

    // Act
    AnalyticModel actualAddVolatilitySurfaceResult =
        analyticModelFromCurvesAndVols.addVolatilitySurface(volatilitySurface);

    // Assert
    verify(volatilitySurface).getName();
    assertTrue(actualAddVolatilitySurfaceResult instanceof AnalyticModelFromCurvesAndVols);
    Map<String, VolatilitySurface> volatilitySurfaces =
        actualAddVolatilitySurfaceResult.getVolatilitySurfaces();
    assertEquals(1, volatilitySurfaces.size());
    assertTrue(actualAddVolatilitySurfaceResult.getCurves().isEmpty());
    assertSame(volatilitySurface, volatilitySurfaces.get("Name"));
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurface(VolatilitySurface)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
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
  public void testAddVolatilitySurface_thenThrowIllegalArgumentException() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    VolatilitySurface volatilitySurface = mock(VolatilitySurface.class);
    when(volatilitySurface.getName()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> analyticModelFromCurvesAndVols.addVolatilitySurface(volatilitySurface));
    verify(volatilitySurface).getName();
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Then return VolatilitySurfaces size is one.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurfaces(Set)"})
  public void testAddVolatilitySurfacesWithSet_thenReturnVolatilitySurfacesSizeIsOne() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    VolatilitySurface volatilitySurface = mock(VolatilitySurface.class);
    when(volatilitySurface.getName()).thenReturn("Name");

    LinkedHashSet<VolatilitySurface> volatilitySurfaces = new LinkedHashSet<>();
    volatilitySurfaces.add(volatilitySurface);

    // Act
    AnalyticModel actualAddVolatilitySurfacesResult =
        analyticModelFromCurvesAndVols.addVolatilitySurfaces(volatilitySurfaces);

    // Assert
    verify(volatilitySurface).getName();
    assertTrue(actualAddVolatilitySurfacesResult instanceof AnalyticModelFromCurvesAndVols);
    Map<String, VolatilitySurface> volatilitySurfaces2 =
        actualAddVolatilitySurfacesResult.getVolatilitySurfaces();
    assertEquals(1, volatilitySurfaces2.size());
    assertTrue(volatilitySurfaces2.containsKey("Name"));
    assertTrue(actualAddVolatilitySurfacesResult.getCurves().isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurfaces(Set)"})
  public void testAddVolatilitySurfacesWithSet_thenThrowIllegalArgumentException() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    VolatilitySurface volatilitySurface = mock(VolatilitySurface.class);
    when(volatilitySurface.getName()).thenThrow(new IllegalArgumentException());

    LinkedHashSet<VolatilitySurface> volatilitySurfaces = new LinkedHashSet<>();
    volatilitySurfaces.add(volatilitySurface);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> analyticModelFromCurvesAndVols.addVolatilitySurfaces(volatilitySurfaces));
    verify(volatilitySurface).getName();
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)} with {@code Set}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   *   <li>Then return VolatilitySurfaces Empty.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.addVolatilitySurfaces(Set)"})
  public void testAddVolatilitySurfacesWithSet_whenHashSet_thenReturnVolatilitySurfacesEmpty() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    // Act
    AnalyticModel actualAddVolatilitySurfacesResult =
        analyticModelFromCurvesAndVols.addVolatilitySurfaces(new HashSet<>());

    // Assert
    assertTrue(actualAddVolatilitySurfacesResult instanceof AnalyticModelFromCurvesAndVols);
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
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    VolatilitySurface volatilitySurface = mock(VolatilitySurface.class);
    when(volatilitySurface.getName()).thenReturn("Name");

    // Act
    AnalyticModel actualAddVolatilitySurfacesResult =
        analyticModelFromCurvesAndVols.addVolatilitySurfaces(volatilitySurface);

    // Assert
    verify(volatilitySurface).getName();
    assertTrue(actualAddVolatilitySurfacesResult instanceof AnalyticModelFromCurvesAndVols);
    Map<String, VolatilitySurface> volatilitySurfaces =
        actualAddVolatilitySurfacesResult.getVolatilitySurfaces();
    assertEquals(1, volatilitySurfaces.size());
    assertTrue(volatilitySurfaces.containsKey("Name"));
    assertTrue(actualAddVolatilitySurfacesResult.getCurves().isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#addVolatilitySurfaces(VolatilitySurface[])} with
   * {@code VolatilitySurface[]}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
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
  public void testAddVolatilitySurfacesWithVolatilitySurface_thenThrowIllegalArgumentException() {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    VolatilitySurface volatilitySurface = mock(VolatilitySurface.class);
    when(volatilitySurface.getName()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> analyticModelFromCurvesAndVols.addVolatilitySurfaces(volatilitySurface));
    verify(volatilitySurface).getName();
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#clone()}.
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModelFromCurvesAndVols AnalyticModelFromCurvesAndVols.clone()"})
  public void testClone() {
    // Arrange and Act
    AnalyticModelFromCurvesAndVols actualCloneResult = new AnalyticModelFromCurvesAndVols().clone();

    // Assert
    assertTrue(actualCloneResult.getCurves().isEmpty());
    assertTrue(actualCloneResult.getVolatilitySurfaces().isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#getCloneForParameter(Map)}.
   *
   * <ul>
   *   <li>Then return Curves size is one.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#getCloneForParameter(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.getCloneForParameter(Map)"})
  public void testGetCloneForParameter_thenReturnCurvesSizeIsOne()
      throws CloneNotSupportedException {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    HashMap<ParameterObject, RandomVariable[]> curveParameterPairs = new HashMap<>();
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve(
            "3", referenceDate, "Method getCloneForParameter not supported on an aggregate.");
    curveParameterPairs.put(
        forwardCurveFromDiscountCurve,
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    AnalyticModel actualCloneForParameter =
        analyticModelFromCurvesAndVols.getCloneForParameter(curveParameterPairs);

    // Assert
    assertTrue(actualCloneForParameter instanceof AnalyticModelFromCurvesAndVols);
    Map<String, Curve> curves = actualCloneForParameter.getCurves();
    assertEquals(1, curves.size());
    Curve getResult =
        curves.get(
            "ForwardCurveFromDiscountCurve(3,Method getCloneForParameter not supported on an aggregate.)");
    assertTrue(getResult.getCloneBuilder() instanceof Builder);
    assertTrue(getResult instanceof ForwardCurveFromDiscountCurve);
    assertTrue(
        ((ForwardCurveFromDiscountCurve) getResult).getPaymentBusinessdayCalendar()
            instanceof BusinessdayCalendarExcludingWeekends);
    assertEquals("3", ((ForwardCurveFromDiscountCurve) getResult).getDiscountCurveName());
    assertEquals(
        "ForwardCurveFromDiscountCurve(3,Method getCloneForParameter not supported on an aggregate.)",
        getResult.getName());
    assertEquals(
        "Method getCloneForParameter not supported on an aggregate.",
        ((ForwardCurveFromDiscountCurve) getResult).getPaymentOffsetCode());
    assertNull(getResult.getParameter());
    assertEquals(
        ExtrapolationMethod.CONSTANT,
        ((ForwardCurveFromDiscountCurve) getResult).getExtrapolationMethod());
    assertEquals(
        InterpolationEntity.VALUE,
        ((ForwardCurveFromDiscountCurve) getResult).getInterpolationEntity());
    assertEquals(
        InterpolationMethod.LINEAR,
        ((ForwardCurveFromDiscountCurve) getResult).getInterpolationMethod());
    assertSame(referenceDate, getResult.getReferenceDate());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#getCloneForParameter(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return Curves Empty.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#getCloneForParameter(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.getCloneForParameter(Map)"})
  public void testGetCloneForParameter_whenHashMap_thenReturnCurvesEmpty()
      throws CloneNotSupportedException {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModelFromCurvesAndVols =
        new AnalyticModelFromCurvesAndVols();

    // Act
    AnalyticModel actualCloneForParameter =
        analyticModelFromCurvesAndVols.getCloneForParameter(new HashMap<>());

    // Assert
    assertTrue(actualCloneForParameter instanceof AnalyticModelFromCurvesAndVols);
    assertTrue(actualCloneForParameter.getCurves().isEmpty());
    assertTrue(actualCloneForParameter.getVolatilitySurfaces().isEmpty());
  }

  /**
   * Test {@link AnalyticModelFromCurvesAndVols#getCloneForParameter(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Curves Empty.
   * </ul>
   *
   * <p>Method under test: {@link AnalyticModelFromCurvesAndVols#getCloneForParameter(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel AnalyticModelFromCurvesAndVols.getCloneForParameter(Map)"})
  public void testGetCloneForParameter_whenNull_thenReturnCurvesEmpty()
      throws CloneNotSupportedException {
    // Arrange and Act
    AnalyticModel actualCloneForParameter =
        new AnalyticModelFromCurvesAndVols().getCloneForParameter(null);

    // Assert
    assertTrue(actualCloneForParameter instanceof AnalyticModelFromCurvesAndVols);
    assertTrue(actualCloneForParameter.getCurves().isEmpty());
    assertTrue(actualCloneForParameter.getVolatilitySurfaces().isEmpty());
  }
}
