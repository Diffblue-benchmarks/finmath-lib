package net.finmath.singleswaprate.model.volatilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.Map;
import net.finmath.marketdata.model.volatilities.VolatilitySurface;
import net.finmath.marketdata.model.volatilities.VolatilitySurface.QuotingConvention;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SABRVolatilityCubeSingleSmileDiffblueTest {
  /**
   * Test {@link SABRVolatilityCubeSingleSmile#SABRVolatilityCubeSingleSmile(String, LocalDate,
   * double, double, double, double, double, double)}.
   *
   * <p>Method under test: {@link
   * SABRVolatilityCubeSingleSmile#SABRVolatilityCubeSingleSmile(String, LocalDate, double, double,
   * double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SABRVolatilityCubeSingleSmile.<init>(String, LocalDate, double, double, double, double, double, double)"
  })
  public void testNewSABRVolatilityCubeSingleSmile() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    SABRVolatilityCubeSingleSmile actualSabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals("Name", actualSabrVolatilityCubeSingleSmile.getName());
    Map<String, Object> parameters = actualSabrVolatilityCubeSingleSmile.getParameters();
    assertEquals(8, parameters.size());
    assertEquals(1.0d, ((Double) parameters.get("InherentCorrelationDecay")).doubleValue(), 0.0);
    assertEquals(1.0d, actualSabrVolatilityCubeSingleSmile.getCorrelationDecay(), 0.0);
    assertEquals(1.0d, actualSabrVolatilityCubeSingleSmile.getIborOisDecorrelation(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("DummyUnderlying")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrAlpha")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrDisplacement")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrNu")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrRho")).doubleValue(), 0.0);
    assertSame(referenceDate, actualSabrVolatilityCubeSingleSmile.getReferenceDate());
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#SABRVolatilityCubeSingleSmile(String, LocalDate,
   * double, double, double, double, double, double, double)}.
   *
   * <p>Method under test: {@link
   * SABRVolatilityCubeSingleSmile#SABRVolatilityCubeSingleSmile(String, LocalDate, double, double,
   * double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SABRVolatilityCubeSingleSmile.<init>(String, LocalDate, double, double, double, double, double, double, double)"
  })
  public void testNewSABRVolatilityCubeSingleSmile2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    SABRVolatilityCubeSingleSmile actualSabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals("Name", actualSabrVolatilityCubeSingleSmile.getName());
    assertEquals(1.0d, actualSabrVolatilityCubeSingleSmile.getIborOisDecorrelation(), 0.0);
    Map<String, Object> parameters = actualSabrVolatilityCubeSingleSmile.getParameters();
    assertEquals(8, parameters.size());
    assertEquals(10.0d, ((Double) parameters.get("DummyUnderlying")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("InherentCorrelationDecay")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrAlpha")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrDisplacement")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrNu")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrRho")).doubleValue(), 0.0);
    assertEquals(10.0d, actualSabrVolatilityCubeSingleSmile.getCorrelationDecay(), 0.0);
    assertSame(referenceDate, actualSabrVolatilityCubeSingleSmile.getReferenceDate());
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#SABRVolatilityCubeSingleSmile(String, LocalDate,
   * double, double, double, double, double, double, double, double)}.
   *
   * <p>Method under test: {@link
   * SABRVolatilityCubeSingleSmile#SABRVolatilityCubeSingleSmile(String, LocalDate, double, double,
   * double, double, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SABRVolatilityCubeSingleSmile.<init>(String, LocalDate, double, double, double, double, double, double, double, double)"
  })
  public void testNewSABRVolatilityCubeSingleSmile3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    SABRVolatilityCubeSingleSmile actualSabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals("Name", actualSabrVolatilityCubeSingleSmile.getName());
    Map<String, Object> parameters = actualSabrVolatilityCubeSingleSmile.getParameters();
    assertEquals(8, parameters.size());
    assertEquals(10.0d, ((Double) parameters.get("DummyUnderlying")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("InherentCorrelationDecay")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrAlpha")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrDisplacement")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrNu")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrRho")).doubleValue(), 0.0);
    assertEquals(10.0d, actualSabrVolatilityCubeSingleSmile.getCorrelationDecay(), 0.0);
    assertEquals(10.0d, actualSabrVolatilityCubeSingleSmile.getIborOisDecorrelation(), 0.0);
    assertSame(referenceDate, actualSabrVolatilityCubeSingleSmile.getReferenceDate());
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeSingleSmile#getValue(VolatilityCubeModel,
   * double, double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeSingleSmile.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention() {
    // Arrange
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            sabrVolatilityCubeSingleSmile.getValue(
                new AnalyticModelWithVolatilityCubes(),
                10.0d,
                10.0d,
                10.0d,
                QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeSingleSmile#getValue(VolatilityCubeModel,
   * double, double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeSingleSmile.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention2() {
    // Arrange
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        8.947848533464405E40d,
        sabrVolatilityCubeSingleSmile.getValue(
            new AnalyticModelWithVolatilityCubes(),
            10.0d,
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYNORMAL),
        0.0);
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeSingleSmile#getValue(VolatilityCubeModel,
   * double, double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeSingleSmile.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention3() {
    // Arrange
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 2.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        5.38397222445014E27d,
        sabrVolatilityCubeSingleSmile.getValue(
            new AnalyticModelWithVolatilityCubes(),
            10.0d,
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYNORMAL),
        0.0);
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeSingleSmile#getValue(VolatilityCubeModel,
   * double, double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeSingleSmile.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention4() {
    // Arrange
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 2.0d, 10.0d, 10.0d, 10.0d, 0.0d, 10.0d);

    // Act and Assert
    assertEquals(
        2.4652203612677877E27d,
        sabrVolatilityCubeSingleSmile.getValue(
            new AnalyticModelWithVolatilityCubes(),
            10.0d,
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYNORMAL),
        0.0);
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeSingleSmile#getValue(VolatilityCubeModel,
   * double, double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeSingleSmile.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention5() {
    // Arrange
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 2.0d, 10.0d, 1.0E-10d, 10.0d, 0.0d, 10.0d);

    // Act and Assert
    assertEquals(
        10.000000004436448d,
        sabrVolatilityCubeSingleSmile.getValue(
            new AnalyticModelWithVolatilityCubes(),
            10.0d,
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYNORMAL),
        0.0);
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SABRVolatilityCubeSingleSmile#getValue(VolatilityCubeModel,
   * double, double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeSingleSmile.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention_thenReturnZero() {
    // Arrange
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 1.0E-10d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        sabrVolatilityCubeSingleSmile.getValue(
            new AnalyticModelWithVolatilityCubes(),
            10.0d,
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYNORMAL),
        0.0);
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#getValue(double, double, double, QuotingConvention)}
   * with {@code termination}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeSingleSmile#getValue(double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeSingleSmile.getValue(double, double, double, QuotingConvention)"
  })
  public void testGetValueWithTerminationMaturityStrikeQuotingConvention() {
    // Arrange
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            sabrVolatilityCubeSingleSmile.getValue(
                10.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#getValue(double, double, double, QuotingConvention)}
   * with {@code termination}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeSingleSmile#getValue(double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeSingleSmile.getValue(double, double, double, QuotingConvention)"
  })
  public void testGetValueWithTerminationMaturityStrikeQuotingConvention2() {
    // Arrange
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        8.947848533464405E40d,
        sabrVolatilityCubeSingleSmile.getValue(
            10.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYNORMAL),
        0.0);
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#getValue(double, double, double, QuotingConvention)}
   * with {@code termination}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeSingleSmile#getValue(double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeSingleSmile.getValue(double, double, double, QuotingConvention)"
  })
  public void testGetValueWithTerminationMaturityStrikeQuotingConvention3() {
    // Arrange
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 2.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        5.38397222445014E27d,
        sabrVolatilityCubeSingleSmile.getValue(
            10.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYNORMAL),
        0.0);
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#getValue(double, double, double, QuotingConvention)}
   * with {@code termination}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeSingleSmile#getValue(double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeSingleSmile.getValue(double, double, double, QuotingConvention)"
  })
  public void testGetValueWithTerminationMaturityStrikeQuotingConvention4() {
    // Arrange
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 2.0d, 10.0d, 10.0d, 10.0d, 0.0d, 10.0d);

    // Act and Assert
    assertEquals(
        2.4652203612677877E27d,
        sabrVolatilityCubeSingleSmile.getValue(
            10.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYNORMAL),
        0.0);
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#getValue(double, double, double, QuotingConvention)}
   * with {@code termination}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeSingleSmile#getValue(double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeSingleSmile.getValue(double, double, double, QuotingConvention)"
  })
  public void testGetValueWithTerminationMaturityStrikeQuotingConvention5() {
    // Arrange
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 2.0d, 10.0d, 1.0E-10d, 10.0d, 0.0d, 10.0d);

    // Act and Assert
    assertEquals(
        10.000000004436448d,
        sabrVolatilityCubeSingleSmile.getValue(
            10.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYNORMAL),
        0.0);
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#getValue(double, double, double, QuotingConvention)}
   * with {@code termination}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SABRVolatilityCubeSingleSmile#getValue(double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeSingleSmile.getValue(double, double, double, QuotingConvention)"
  })
  public void testGetValueWithTerminationMaturityStrikeQuotingConvention_thenReturnZero() {
    // Arrange
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 1.0E-10d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        0.0d,
        sabrVolatilityCubeSingleSmile.getValue(
            10.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYNORMAL),
        0.0);
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#clone()}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeSingleSmile#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object SABRVolatilityCubeSingleSmile.clone()"})
  public void testClone() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    Object actualCloneResult = sabrVolatilityCubeSingleSmile.clone();

    // Assert
    assertTrue(actualCloneResult instanceof SABRVolatilityCubeSingleSmile);
    LocalDate referenceDate2 =
        ((SABRVolatilityCubeSingleSmile) actualCloneResult).getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", ((SABRVolatilityCubeSingleSmile) actualCloneResult).getName());
    Map<String, Object> parameters =
        ((SABRVolatilityCubeSingleSmile) actualCloneResult).getParameters();
    assertEquals(8, parameters.size());
    assertEquals(1.0d, ((Double) parameters.get("InherentCorrelationDecay")).doubleValue(), 0.0);
    assertEquals(
        1.0d, ((SABRVolatilityCubeSingleSmile) actualCloneResult).getCorrelationDecay(), 0.0);
    assertEquals(
        1.0d, ((SABRVolatilityCubeSingleSmile) actualCloneResult).getIborOisDecorrelation(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("DummyUnderlying")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrAlpha")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrDisplacement")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrNu")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrRho")).doubleValue(), 0.0);
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SABRVolatilityCubeSingleSmile#toString()}
   *   <li>{@link SABRVolatilityCubeSingleSmile#getCorrelationDecay()}
   *   <li>{@link SABRVolatilityCubeSingleSmile#getIborOisDecorrelation()}
   *   <li>{@link SABRVolatilityCubeSingleSmile#getName()}
   *   <li>{@link SABRVolatilityCubeSingleSmile#getReferenceDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeSingleSmile.getCorrelationDecay()",
    "double SABRVolatilityCubeSingleSmile.getIborOisDecorrelation()",
    "String SABRVolatilityCubeSingleSmile.getName()",
    "LocalDate SABRVolatilityCubeSingleSmile.getReferenceDate()",
    "String SABRVolatilityCubeSingleSmile.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    sabrVolatilityCubeSingleSmile.toString();
    double actualCorrelationDecay = sabrVolatilityCubeSingleSmile.getCorrelationDecay();
    double actualIborOisDecorrelation = sabrVolatilityCubeSingleSmile.getIborOisDecorrelation();
    String actualName = sabrVolatilityCubeSingleSmile.getName();
    LocalDate actualReferenceDate = sabrVolatilityCubeSingleSmile.getReferenceDate();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals("Name", actualName);
    assertEquals(1.0d, actualCorrelationDecay, 0.0);
    assertEquals(1.0d, actualIborOisDecorrelation, 0.0);
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#getParameters()}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeSingleSmile#getParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SABRVolatilityCubeSingleSmile.getParameters()"})
  public void testGetParameters() {
    // Arrange
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    Map<String, Object> actualParameters = sabrVolatilityCubeSingleSmile.getParameters();

    // Assert
    assertEquals(8, actualParameters.size());
    assertEquals(
        1.0d, ((Double) actualParameters.get("InherentCorrelationDecay")).doubleValue(), 0.0);
    assertEquals(1.0d, ((Double) actualParameters.get("iborOisDecorrelation")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) actualParameters.get("DummyUnderlying")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) actualParameters.get("sabrAlpha")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) actualParameters.get("sabrBeta")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) actualParameters.get("sabrDisplacement")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) actualParameters.get("sabrNu")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) actualParameters.get("sabrRho")).doubleValue(), 0.0);
  }

  /**
   * Test {@link SABRVolatilityCubeSingleSmile#getLowestStrike(VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link
   * SABRVolatilityCubeSingleSmile#getLowestStrike(VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SABRVolatilityCubeSingleSmile.getLowestStrike(VolatilityCubeModel)"})
  public void testGetLowestStrike() {
    // Arrange
    SABRVolatilityCubeSingleSmile sabrVolatilityCubeSingleSmile =
        new SABRVolatilityCubeSingleSmile(
            "Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        -10.0d,
        sabrVolatilityCubeSingleSmile.getLowestStrike(new AnalyticModelWithVolatilityCubes()),
        0.0);
  }
}
