package net.finmath.singleswaprate.model.volatilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
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

public class StaticVolatilityCubeDiffblueTest {
  /**
   * Test {@link StaticVolatilityCube#StaticVolatilityCube(String, LocalDate, double)}.
   *
   * <p>Method under test: {@link StaticVolatilityCube#StaticVolatilityCube(String, LocalDate,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StaticVolatilityCube.<init>(String, LocalDate, double)"})
  public void testNewStaticVolatilityCube() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    StaticVolatilityCube actualStaticVolatilityCube =
        new StaticVolatilityCube("Name", referenceDate, 10.0d);

    // Assert
    assertEquals("Name", actualStaticVolatilityCube.getName());
    Map<String, Object> parameters = actualStaticVolatilityCube.getParameters();
    assertEquals(3, parameters.size());
    assertEquals(0.0d, ((Double) parameters.get("Inherent correlationDecay")).doubleValue(), 0.0);
    assertEquals(0.0d, actualStaticVolatilityCube.getCorrelationDecay(), 0.0);
    assertEquals(1.0d, ((Double) parameters.get("iborOisDecorrelation")).doubleValue(), 0.0);
    assertEquals(1.0d, actualStaticVolatilityCube.getIborOisDecorrelation(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("value")).doubleValue(), 0.0);
    assertSame(referenceDate, actualStaticVolatilityCube.getReferenceDate());
  }

  /**
   * Test {@link StaticVolatilityCube#StaticVolatilityCube(String, LocalDate, double, double)}.
   *
   * <p>Method under test: {@link StaticVolatilityCube#StaticVolatilityCube(String, LocalDate,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StaticVolatilityCube.<init>(String, LocalDate, double, double)"})
  public void testNewStaticVolatilityCube2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    StaticVolatilityCube actualStaticVolatilityCube =
        new StaticVolatilityCube("Name", referenceDate, 10.0d, 10.0d);

    // Assert
    assertEquals("Name", actualStaticVolatilityCube.getName());
    Map<String, Object> parameters = actualStaticVolatilityCube.getParameters();
    assertEquals(3, parameters.size());
    assertEquals(1.0d, ((Double) parameters.get("iborOisDecorrelation")).doubleValue(), 0.0);
    assertEquals(1.0d, actualStaticVolatilityCube.getIborOisDecorrelation(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("Inherent correlationDecay")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("value")).doubleValue(), 0.0);
    assertEquals(10.0d, actualStaticVolatilityCube.getCorrelationDecay(), 0.0);
    assertSame(referenceDate, actualStaticVolatilityCube.getReferenceDate());
  }

  /**
   * Test {@link StaticVolatilityCube#StaticVolatilityCube(String, LocalDate, double, double,
   * double)}.
   *
   * <p>Method under test: {@link StaticVolatilityCube#StaticVolatilityCube(String, LocalDate,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StaticVolatilityCube.<init>(String, LocalDate, double, double, double)"})
  public void testNewStaticVolatilityCube3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    StaticVolatilityCube actualStaticVolatilityCube =
        new StaticVolatilityCube("Name", referenceDate, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals("Name", actualStaticVolatilityCube.getName());
    Map<String, Object> parameters = actualStaticVolatilityCube.getParameters();
    assertEquals(3, parameters.size());
    assertEquals(10.0d, ((Double) parameters.get("Inherent correlationDecay")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("iborOisDecorrelation")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("value")).doubleValue(), 0.0);
    assertEquals(10.0d, actualStaticVolatilityCube.getCorrelationDecay(), 0.0);
    assertEquals(10.0d, actualStaticVolatilityCube.getIborOisDecorrelation(), 0.0);
    assertSame(referenceDate, actualStaticVolatilityCube.getReferenceDate());
  }

  /**
   * Test {@link StaticVolatilityCube#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link StaticVolatilityCube#getValue(VolatilityCubeModel, double, double,
   * double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double StaticVolatilityCube.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention() {
    // Arrange
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act and Assert
    assertEquals(
        10.0d,
        staticVolatilityCube.getValue(
            new AnalyticModelWithVolatilityCubes(),
            10.0d,
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test {@link StaticVolatilityCube#getValue(double, double, double, QuotingConvention)} with
   * {@code termination}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link StaticVolatilityCube#getValue(double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double StaticVolatilityCube.getValue(double, double, double, QuotingConvention)"
  })
  public void testGetValueWithTerminationMaturityStrikeQuotingConvention() {
    // Arrange
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act and Assert
    assertEquals(
        10.0d,
        staticVolatilityCube.getValue(10.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL),
        0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StaticVolatilityCube#getCorrelationDecay()}
   *   <li>{@link StaticVolatilityCube#getIborOisDecorrelation()}
   *   <li>{@link StaticVolatilityCube#getName()}
   *   <li>{@link StaticVolatilityCube#getReferenceDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double StaticVolatilityCube.getCorrelationDecay()",
    "double StaticVolatilityCube.getIborOisDecorrelation()",
    "String StaticVolatilityCube.getName()",
    "LocalDate StaticVolatilityCube.getReferenceDate()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", referenceDate, 10.0d);

    // Act
    double actualCorrelationDecay = staticVolatilityCube.getCorrelationDecay();
    double actualIborOisDecorrelation = staticVolatilityCube.getIborOisDecorrelation();
    String actualName = staticVolatilityCube.getName();
    LocalDate actualReferenceDate = staticVolatilityCube.getReferenceDate();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals("Name", actualName);
    assertEquals(0.0d, actualCorrelationDecay, 0.0);
    assertEquals(1.0d, actualIborOisDecorrelation, 0.0);
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link StaticVolatilityCube#getParameters()}.
   *
   * <p>Method under test: {@link StaticVolatilityCube#getParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map StaticVolatilityCube.getParameters()"})
  public void testGetParameters() {
    // Arrange
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    Map<String, Object> actualParameters = staticVolatilityCube.getParameters();

    // Assert
    assertEquals(3, actualParameters.size());
    assertEquals(
        0.0d, ((Double) actualParameters.get("Inherent correlationDecay")).doubleValue(), 0.0);
    assertEquals(1.0d, ((Double) actualParameters.get("iborOisDecorrelation")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) actualParameters.get("value")).doubleValue(), 0.0);
  }

  /**
   * Test {@link StaticVolatilityCube#getLowestStrike(VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link StaticVolatilityCube#getLowestStrike(VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double StaticVolatilityCube.getLowestStrike(VolatilityCubeModel)"})
  public void testGetLowestStrike() {
    // Arrange
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act and Assert
    assertEquals(
        Double.NEGATIVE_INFINITY,
        staticVolatilityCube.getLowestStrike(new AnalyticModelWithVolatilityCubes()),
        0.0);
  }
}
