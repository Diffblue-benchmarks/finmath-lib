package net.finmath.singleswaprate.model.volatilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.Map;
import net.finmath.marketdata.model.volatilities.VolatilitySurface;
import net.finmath.marketdata.model.volatilities.VolatilitySurface.QuotingConvention;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ScaledVolatilityCubeDiffblueTest {
  /**
   * Test {@link ScaledVolatilityCube#ScaledVolatilityCube(String, LocalDate, String, double,
   * double)}.
   *
   * <p>Method under test: {@link ScaledVolatilityCube#ScaledVolatilityCube(String, LocalDate,
   * String, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ScaledVolatilityCube.<init>(String, LocalDate, String, double, double)"})
  public void testNewScaledVolatilityCube() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    ScaledVolatilityCube actualScaledVolatilityCube =
        new ScaledVolatilityCube("Name", referenceDate, "Reference Cube Name", 10.0d, 10.0d);

    // Assert
    assertEquals("Name", actualScaledVolatilityCube.getName());
    assertEquals("Reference Cube Name", actualScaledVolatilityCube.getReferenceCubeName());
    Map<String, Object> parameters = actualScaledVolatilityCube.getParameters();
    assertEquals(3, parameters.size());
    assertEquals(1.0d, ((Double) parameters.get("iborOisDecorrelation")).doubleValue(), 0.0);
    assertEquals(1.0d, actualScaledVolatilityCube.getIborOisDecorrelation(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("Inherent correlationDecay")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("coefficient")).doubleValue(), 0.0);
    assertEquals(10.0d, actualScaledVolatilityCube.getCorrelationDecay(), 0.0);
    assertSame(referenceDate, actualScaledVolatilityCube.getReferenceDate());
  }

  /**
   * Test {@link ScaledVolatilityCube#ScaledVolatilityCube(String, LocalDate, String, double,
   * double, double)}.
   *
   * <p>Method under test: {@link ScaledVolatilityCube#ScaledVolatilityCube(String, LocalDate,
   * String, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ScaledVolatilityCube.<init>(String, LocalDate, String, double, double, double)"
  })
  public void testNewScaledVolatilityCube2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    ScaledVolatilityCube actualScaledVolatilityCube =
        new ScaledVolatilityCube("Name", referenceDate, "Reference Cube Name", 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals("Name", actualScaledVolatilityCube.getName());
    assertEquals("Reference Cube Name", actualScaledVolatilityCube.getReferenceCubeName());
    Map<String, Object> parameters = actualScaledVolatilityCube.getParameters();
    assertEquals(3, parameters.size());
    assertEquals(10.0d, ((Double) parameters.get("Inherent correlationDecay")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("coefficient")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("iborOisDecorrelation")).doubleValue(), 0.0);
    assertEquals(10.0d, actualScaledVolatilityCube.getCorrelationDecay(), 0.0);
    assertEquals(10.0d, actualScaledVolatilityCube.getIborOisDecorrelation(), 0.0);
    assertSame(referenceDate, actualScaledVolatilityCube.getReferenceDate());
  }

  /**
   * Test {@link ScaledVolatilityCube#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link ScaledVolatilityCube#getValue(VolatilityCubeModel, double, double,
   * double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double ScaledVolatilityCube.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention() {
    // Arrange
    ScaledVolatilityCube scaledVolatilityCube =
        new ScaledVolatilityCube(
            "Name", LocalDate.of(1970, 1, 1), "Reference Cube Name", 10.0d, 10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualValue =
        scaledVolatilityCube.getValue(
            model, 10.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(model).getVolatilityCube("Reference Cube Name");
    assertEquals(100.0d, actualValue, 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ScaledVolatilityCube#getCorrelationDecay()}
   *   <li>{@link ScaledVolatilityCube#getIborOisDecorrelation()}
   *   <li>{@link ScaledVolatilityCube#getName()}
   *   <li>{@link ScaledVolatilityCube#getReferenceCubeName()}
   *   <li>{@link ScaledVolatilityCube#getReferenceDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double ScaledVolatilityCube.getCorrelationDecay()",
    "double ScaledVolatilityCube.getIborOisDecorrelation()",
    "String ScaledVolatilityCube.getName()",
    "String ScaledVolatilityCube.getReferenceCubeName()",
    "LocalDate ScaledVolatilityCube.getReferenceDate()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ScaledVolatilityCube scaledVolatilityCube =
        new ScaledVolatilityCube("Name", referenceDate, "Reference Cube Name", 10.0d, 10.0d);

    // Act
    double actualCorrelationDecay = scaledVolatilityCube.getCorrelationDecay();
    double actualIborOisDecorrelation = scaledVolatilityCube.getIborOisDecorrelation();
    String actualName = scaledVolatilityCube.getName();
    String actualReferenceCubeName = scaledVolatilityCube.getReferenceCubeName();
    LocalDate actualReferenceDate = scaledVolatilityCube.getReferenceDate();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals("Name", actualName);
    assertEquals("Reference Cube Name", actualReferenceCubeName);
    assertEquals(1.0d, actualIborOisDecorrelation, 0.0);
    assertEquals(10.0d, actualCorrelationDecay, 0.0);
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link ScaledVolatilityCube#getParameters()}.
   *
   * <p>Method under test: {@link ScaledVolatilityCube#getParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ScaledVolatilityCube.getParameters()"})
  public void testGetParameters() {
    // Arrange
    ScaledVolatilityCube scaledVolatilityCube =
        new ScaledVolatilityCube(
            "Name", LocalDate.of(1970, 1, 1), "Reference Cube Name", 10.0d, 10.0d);

    // Act
    Map<String, Object> actualParameters = scaledVolatilityCube.getParameters();

    // Assert
    assertEquals(3, actualParameters.size());
    assertEquals(1.0d, ((Double) actualParameters.get("iborOisDecorrelation")).doubleValue(), 0.0);
    assertEquals(
        10.0d, ((Double) actualParameters.get("Inherent correlationDecay")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) actualParameters.get("coefficient")).doubleValue(), 0.0);
  }

  /**
   * Test {@link ScaledVolatilityCube#getLowestStrike(VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then return {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link ScaledVolatilityCube#getLowestStrike(VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ScaledVolatilityCube.getLowestStrike(VolatilityCubeModel)"})
  public void testGetLowestStrike_thenReturnNegative_infinity() {
    // Arrange
    ScaledVolatilityCube scaledVolatilityCube =
        new ScaledVolatilityCube(
            "Name", LocalDate.of(1970, 1, 1), "Reference Cube Name", 10.0d, 10.0d);

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualLowestStrike = scaledVolatilityCube.getLowestStrike(model);

    // Assert
    verify(model).getVolatilityCube("Reference Cube Name");
    assertEquals(Double.NEGATIVE_INFINITY, actualLowestStrike, 0.0);
  }
}
