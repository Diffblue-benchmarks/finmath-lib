package net.finmath.singleswaprate.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import net.finmath.marketdata.model.curves.Curve;
import net.finmath.marketdata.model.volatilities.VolatilitySurface;
import net.finmath.singleswaprate.model.volatilities.StaticVolatilityCube;
import net.finmath.singleswaprate.model.volatilities.VolatilityCube;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AnalyticModelWithVolatilityCubesDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return ReferenceDate is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AnalyticModelWithVolatilityCubes#AnalyticModelWithVolatilityCubes()}
   *   <li>{@link AnalyticModelWithVolatilityCubes#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnalyticModelWithVolatilityCubes.<init>()",
    "void AnalyticModelWithVolatilityCubes.<init>(LocalDate)",
    "String AnalyticModelWithVolatilityCubes.toString()"
  })
  public void testGettersAndSetters_thenReturnReferenceDateIsNull() {
    // Arrange and Act
    AnalyticModelWithVolatilityCubes actualAnalyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes();

    // Assert
    assertEquals(
        "EnhancedAnalyticModel: curves=[], volatilitySurfaces=[], volatilityCubes=[]",
        actualAnalyticModelWithVolatilityCubes.toString());
    assertNull(actualAnalyticModelWithVolatilityCubes.getReferenceDate());
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
   *   <li>{@link AnalyticModelWithVolatilityCubes#AnalyticModelWithVolatilityCubes(LocalDate)}
   *   <li>{@link AnalyticModelWithVolatilityCubes#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AnalyticModelWithVolatilityCubes.<init>()",
    "void AnalyticModelWithVolatilityCubes.<init>(LocalDate)",
    "String AnalyticModelWithVolatilityCubes.toString()"
  })
  public void testGettersAndSetters_thenReturnReferenceDateToStringIs19700101() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    AnalyticModelWithVolatilityCubes actualAnalyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes(referenceDate);
    String actualToStringResult = actualAnalyticModelWithVolatilityCubes.toString();

    // Assert
    LocalDate referenceDate2 = actualAnalyticModelWithVolatilityCubes.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals(
        "EnhancedAnalyticModel: curves=[], volatilitySurfaces=[], volatilityCubes=[]",
        actualToStringResult);
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link AnalyticModelWithVolatilityCubes#AnalyticModelWithVolatilityCubes(LocalDate, Map,
   * Map, Map)}.
   *
   * <p>Method under test: {@link
   * AnalyticModelWithVolatilityCubes#AnalyticModelWithVolatilityCubes(LocalDate, Map, Map, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AnalyticModelWithVolatilityCubes.<init>(LocalDate, Map, Map, Map)"})
  public void testNewAnalyticModelWithVolatilityCubes() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    HashMap<String, Curve> curvesMap = new HashMap<>();
    HashMap<String, VolatilitySurface> volatilitySurfaceMap = new HashMap<>();

    // Act
    AnalyticModelWithVolatilityCubes actualAnalyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes(
            referenceDate, curvesMap, volatilitySurfaceMap, new HashMap<>());

    // Assert
    assertTrue(actualAnalyticModelWithVolatilityCubes.getCurves().isEmpty());
    assertTrue(actualAnalyticModelWithVolatilityCubes.getVolatilitySurfaces().isEmpty());
    assertTrue(actualAnalyticModelWithVolatilityCubes.getVolatilityCubes().isEmpty());
    assertTrue(actualAnalyticModelWithVolatilityCubes.getVolatilityCubeNames().isEmpty());
    assertSame(referenceDate, actualAnalyticModelWithVolatilityCubes.getReferenceDate());
  }

  /**
   * Test {@link AnalyticModelWithVolatilityCubes#getVolatilityCube(String)}.
   *
   * <p>Method under test: {@link AnalyticModelWithVolatilityCubes#getVolatilityCube(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VolatilityCube AnalyticModelWithVolatilityCubes.getVolatilityCube(String)"})
  public void testGetVolatilityCube() {
    // Arrange, Act and Assert
    assertNull(new AnalyticModelWithVolatilityCubes().getVolatilityCube("Name"));
  }

  /**
   * Test {@link AnalyticModelWithVolatilityCubes#addVolatilityCube(VolatilityCube)} with {@code
   * volatilityCube}.
   *
   * <p>Method under test: {@link
   * AnalyticModelWithVolatilityCubes#addVolatilityCube(VolatilityCube)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VolatilityCubeModel AnalyticModelWithVolatilityCubes.addVolatilityCube(VolatilityCube)"
  })
  public void testAddVolatilityCubeWithVolatilityCube() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes(referenceDate);
    StaticVolatilityCube volatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    VolatilityCubeModel actualAddVolatilityCubeResult =
        analyticModelWithVolatilityCubes.addVolatilityCube(volatilityCube);

    // Assert
    assertTrue(actualAddVolatilityCubeResult instanceof AnalyticModelWithVolatilityCubes);
    LocalDate referenceDate2 =
        ((AnalyticModelWithVolatilityCubes) actualAddVolatilityCubeResult).getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    Map<String, VolatilityCube> volatilityCubes =
        actualAddVolatilityCubeResult.getVolatilityCubes();
    assertEquals(1, volatilityCubes.size());
    assertSame(volatilityCube, volatilityCubes.get("Name"));
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link AnalyticModelWithVolatilityCubes#addVolatilityCube(VolatilityCube)} with {@code
   * volatilityCube}.
   *
   * <p>Method under test: {@link
   * AnalyticModelWithVolatilityCubes#addVolatilityCube(VolatilityCube)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VolatilityCubeModel AnalyticModelWithVolatilityCubes.addVolatilityCube(VolatilityCube)"
  })
  public void testAddVolatilityCubeWithVolatilityCube2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes(referenceDate);
    StaticVolatilityCube volatilityCube = new StaticVolatilityCube("Name", null, 10.0d);

    // Act
    VolatilityCubeModel actualAddVolatilityCubeResult =
        analyticModelWithVolatilityCubes.addVolatilityCube(volatilityCube);

    // Assert
    assertTrue(actualAddVolatilityCubeResult instanceof AnalyticModelWithVolatilityCubes);
    Map<String, VolatilityCube> volatilityCubes =
        actualAddVolatilityCubeResult.getVolatilityCubes();
    assertEquals(1, volatilityCubes.size());
    VolatilityCube getResult = volatilityCubes.get("Name");
    assertTrue(getResult instanceof StaticVolatilityCube);
    LocalDate referenceDate2 =
        ((AnalyticModelWithVolatilityCubes) actualAddVolatilityCubeResult).getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertNull(getResult.getReferenceDate());
    assertSame(volatilityCube, getResult);
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link AnalyticModelWithVolatilityCubes#addVolatilityCube(String, VolatilityCube)} with
   * {@code volatilityCubeName}, {@code volatilityCube}.
   *
   * <p>Method under test: {@link AnalyticModelWithVolatilityCubes#addVolatilityCube(String,
   * VolatilityCube)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VolatilityCubeModel AnalyticModelWithVolatilityCubes.addVolatilityCube(String, VolatilityCube)"
  })
  public void testAddVolatilityCubeWithVolatilityCubeNameVolatilityCube() {
    // Arrange
    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes();
    StaticVolatilityCube volatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    VolatilityCubeModel actualAddVolatilityCubeResult =
        analyticModelWithVolatilityCubes.addVolatilityCube("Volatility Cube Name", volatilityCube);

    // Assert
    assertTrue(actualAddVolatilityCubeResult instanceof AnalyticModelWithVolatilityCubes);
    assertNull(
        ((AnalyticModelWithVolatilityCubes) actualAddVolatilityCubeResult).getReferenceDate());
    Map<String, VolatilityCube> volatilityCubes =
        actualAddVolatilityCubeResult.getVolatilityCubes();
    assertEquals(1, volatilityCubes.size());
    assertSame(volatilityCube, volatilityCubes.get("Volatility Cube Name"));
  }

  /**
   * Test {@link AnalyticModelWithVolatilityCubes#addVolatilityCube(String, VolatilityCube)} with
   * {@code volatilityCubeName}, {@code volatilityCube}.
   *
   * <p>Method under test: {@link AnalyticModelWithVolatilityCubes#addVolatilityCube(String,
   * VolatilityCube)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VolatilityCubeModel AnalyticModelWithVolatilityCubes.addVolatilityCube(String, VolatilityCube)"
  })
  public void testAddVolatilityCubeWithVolatilityCubeNameVolatilityCube2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes(referenceDate);
    StaticVolatilityCube volatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    VolatilityCubeModel actualAddVolatilityCubeResult =
        analyticModelWithVolatilityCubes.addVolatilityCube("Volatility Cube Name", volatilityCube);

    // Assert
    assertTrue(actualAddVolatilityCubeResult instanceof AnalyticModelWithVolatilityCubes);
    LocalDate referenceDate2 =
        ((AnalyticModelWithVolatilityCubes) actualAddVolatilityCubeResult).getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    Map<String, VolatilityCube> volatilityCubes =
        actualAddVolatilityCubeResult.getVolatilityCubes();
    assertEquals(1, volatilityCubes.size());
    assertSame(volatilityCube, volatilityCubes.get("Volatility Cube Name"));
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link AnalyticModelWithVolatilityCubes#addVolatilityCube(String, VolatilityCube)} with
   * {@code volatilityCubeName}, {@code volatilityCube}.
   *
   * <p>Method under test: {@link AnalyticModelWithVolatilityCubes#addVolatilityCube(String,
   * VolatilityCube)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VolatilityCubeModel AnalyticModelWithVolatilityCubes.addVolatilityCube(String, VolatilityCube)"
  })
  public void testAddVolatilityCubeWithVolatilityCubeNameVolatilityCube3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes(referenceDate);
    StaticVolatilityCube volatilityCube = new StaticVolatilityCube("Name", null, 10.0d);

    // Act
    VolatilityCubeModel actualAddVolatilityCubeResult =
        analyticModelWithVolatilityCubes.addVolatilityCube("Volatility Cube Name", volatilityCube);

    // Assert
    assertTrue(actualAddVolatilityCubeResult instanceof AnalyticModelWithVolatilityCubes);
    Map<String, VolatilityCube> volatilityCubes =
        actualAddVolatilityCubeResult.getVolatilityCubes();
    assertEquals(1, volatilityCubes.size());
    VolatilityCube getResult = volatilityCubes.get("Volatility Cube Name");
    assertTrue(getResult instanceof StaticVolatilityCube);
    LocalDate referenceDate2 =
        ((AnalyticModelWithVolatilityCubes) actualAddVolatilityCubeResult).getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertNull(getResult.getReferenceDate());
    assertSame(volatilityCube, getResult);
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link AnalyticModelWithVolatilityCubes#addVolatilityCube(VolatilityCube)} with {@code
   * volatilityCube}.
   *
   * <ul>
   *   <li>Then return ReferenceDate is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AnalyticModelWithVolatilityCubes#addVolatilityCube(VolatilityCube)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "VolatilityCubeModel AnalyticModelWithVolatilityCubes.addVolatilityCube(VolatilityCube)"
  })
  public void testAddVolatilityCubeWithVolatilityCube_thenReturnReferenceDateIsNull() {
    // Arrange
    AnalyticModelWithVolatilityCubes analyticModelWithVolatilityCubes =
        new AnalyticModelWithVolatilityCubes();
    StaticVolatilityCube volatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    VolatilityCubeModel actualAddVolatilityCubeResult =
        analyticModelWithVolatilityCubes.addVolatilityCube(volatilityCube);

    // Assert
    assertTrue(actualAddVolatilityCubeResult instanceof AnalyticModelWithVolatilityCubes);
    assertNull(
        ((AnalyticModelWithVolatilityCubes) actualAddVolatilityCubeResult).getReferenceDate());
    Map<String, VolatilityCube> volatilityCubes =
        actualAddVolatilityCubeResult.getVolatilityCubes();
    assertEquals(1, volatilityCubes.size());
    assertSame(volatilityCube, volatilityCubes.get("Name"));
  }

  /**
   * Test {@link AnalyticModelWithVolatilityCubes#clone()}.
   *
   * <p>Method under test: {@link AnalyticModelWithVolatilityCubes#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModelWithVolatilityCubes AnalyticModelWithVolatilityCubes.clone()"})
  public void testClone() {
    // Arrange and Act
    AnalyticModelWithVolatilityCubes actualCloneResult =
        new AnalyticModelWithVolatilityCubes().clone();

    // Assert
    assertNull(actualCloneResult.getReferenceDate());
    assertTrue(actualCloneResult.getCurves().isEmpty());
    assertTrue(actualCloneResult.getVolatilitySurfaces().isEmpty());
    assertTrue(actualCloneResult.getVolatilityCubes().isEmpty());
    assertTrue(actualCloneResult.getVolatilityCubeNames().isEmpty());
  }

  /**
   * Test {@link AnalyticModelWithVolatilityCubes#getVolatilityCubeNames()}.
   *
   * <p>Method under test: {@link AnalyticModelWithVolatilityCubes#getVolatilityCubeNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set AnalyticModelWithVolatilityCubes.getVolatilityCubeNames()"})
  public void testGetVolatilityCubeNames() {
    // Arrange, Act and Assert
    assertTrue(new AnalyticModelWithVolatilityCubes().getVolatilityCubeNames().isEmpty());
  }

  /**
   * Test {@link AnalyticModelWithVolatilityCubes#getVolatilityCubes()}.
   *
   * <p>Method under test: {@link AnalyticModelWithVolatilityCubes#getVolatilityCubes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map AnalyticModelWithVolatilityCubes.getVolatilityCubes()"})
  public void testGetVolatilityCubes() {
    // Arrange, Act and Assert
    assertTrue(new AnalyticModelWithVolatilityCubes().getVolatilityCubes().isEmpty());
  }
}
