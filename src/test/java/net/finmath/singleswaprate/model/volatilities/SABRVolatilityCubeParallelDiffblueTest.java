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
import net.finmath.singleswaprate.data.DataTable;
import net.finmath.singleswaprate.data.DataTable.TableConvention;
import net.finmath.singleswaprate.data.DataTableLight;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SABRVolatilityCubeParallelDiffblueTest {
  /**
   * Test {@link SABRVolatilityCubeParallel#SABRVolatilityCubeParallel(String, LocalDate, DataTable,
   * double, double, double, double, DataTable, double)}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeParallel#SABRVolatilityCubeParallel(String,
   * LocalDate, DataTable, double, double, double, double, DataTable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SABRVolatilityCubeParallel.<init>(String, LocalDate, DataTable, double, double, double, double, DataTable, double)"
  })
  public void testNewSABRVolatilityCubeParallel() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);

    // Act
    SABRVolatilityCubeParallel actualSabrVolatilityCubeParallel =
        new SABRVolatilityCubeParallel(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Assert
    Map<String, Object> parameters = actualSabrVolatilityCubeParallel.getParameters();
    assertEquals(8, parameters.size());
    assertTrue(parameters.get("baseVolTable") instanceof DataTableLight);
    assertEquals("Name", actualSabrVolatilityCubeParallel.getName());
    assertEquals(1.0d, ((Double) parameters.get("iborOisDecorrelation")).doubleValue(), 0.0);
    assertEquals(1.0d, actualSabrVolatilityCubeParallel.getIborOisDecorrelation(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("Inherent correlationDecay")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrBeta")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrDisplacement")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrRho")).doubleValue(), 0.0);
    assertEquals(10.0d, actualSabrVolatilityCubeParallel.getCorrelationDecay(), 0.0);
    assertSame(referenceDate, actualSabrVolatilityCubeParallel.getReferenceDate());
  }

  /**
   * Test {@link SABRVolatilityCubeParallel#SABRVolatilityCubeParallel(String, LocalDate, DataTable,
   * double, double, double, double, DataTable, double, double)}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeParallel#SABRVolatilityCubeParallel(String,
   * LocalDate, DataTable, double, double, double, double, DataTable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SABRVolatilityCubeParallel.<init>(String, LocalDate, DataTable, double, double, double, double, DataTable, double, double)"
  })
  public void testNewSABRVolatilityCubeParallel2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);

    // Act
    SABRVolatilityCubeParallel actualSabrVolatilityCubeParallel =
        new SABRVolatilityCubeParallel(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d,
            10.0d);

    // Assert
    Map<String, Object> parameters = actualSabrVolatilityCubeParallel.getParameters();
    assertEquals(8, parameters.size());
    assertTrue(parameters.get("baseVolTable") instanceof DataTableLight);
    assertEquals("Name", actualSabrVolatilityCubeParallel.getName());
    assertEquals(10.0d, ((Double) parameters.get("Inherent correlationDecay")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("iborOisDecorrelation")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrBeta")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrDisplacement")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) parameters.get("sabrRho")).doubleValue(), 0.0);
    assertEquals(10.0d, actualSabrVolatilityCubeParallel.getCorrelationDecay(), 0.0);
    assertEquals(10.0d, actualSabrVolatilityCubeParallel.getIborOisDecorrelation(), 0.0);
    assertSame(referenceDate, actualSabrVolatilityCubeParallel.getReferenceDate());
  }

  /**
   * Test {@link SABRVolatilityCubeParallel#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeParallel#getValue(VolatilityCubeModel, double,
   * double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeParallel.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCubeParallel sabrVolatilityCubeParallel =
        new SABRVolatilityCubeParallel(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            sabrVolatilityCubeParallel.getValue(
                new AnalyticModelWithVolatilityCubes(),
                10.0d,
                10.0d,
                10.0d,
                QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link SABRVolatilityCubeParallel#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <ul>
   *   <li>When two.
   * </ul>
   *
   * <p>Method under test: {@link SABRVolatilityCubeParallel#getValue(VolatilityCubeModel, double,
   * double, double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeParallel.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention_whenTwo() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCubeParallel sabrVolatilityCubeParallel =
        new SABRVolatilityCubeParallel(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            sabrVolatilityCubeParallel.getValue(
                new AnalyticModelWithVolatilityCubes(),
                2.0d,
                10.0d,
                10.0d,
                QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link SABRVolatilityCubeParallel#getValue(double, double, double, QuotingConvention)}
   * with {@code termination}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeParallel#getValue(double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeParallel.getValue(double, double, double, QuotingConvention)"
  })
  public void testGetValueWithTerminationMaturityStrikeQuotingConvention() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCubeParallel sabrVolatilityCubeParallel =
        new SABRVolatilityCubeParallel(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            sabrVolatilityCubeParallel.getValue(
                10.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link SABRVolatilityCubeParallel#getValue(double, double, double, QuotingConvention)}
   * with {@code termination}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <ul>
   *   <li>When two.
   * </ul>
   *
   * <p>Method under test: {@link SABRVolatilityCubeParallel#getValue(double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeParallel.getValue(double, double, double, QuotingConvention)"
  })
  public void testGetValueWithTerminationMaturityStrikeQuotingConvention_whenTwo() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCubeParallel sabrVolatilityCubeParallel =
        new SABRVolatilityCubeParallel(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            sabrVolatilityCubeParallel.getValue(
                2.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SABRVolatilityCubeParallel#getCorrelationDecay()}
   *   <li>{@link SABRVolatilityCubeParallel#getIborOisDecorrelation()}
   *   <li>{@link SABRVolatilityCubeParallel#getName()}
   *   <li>{@link SABRVolatilityCubeParallel#getReferenceDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCubeParallel.getCorrelationDecay()",
    "double SABRVolatilityCubeParallel.getIborOisDecorrelation()",
    "String SABRVolatilityCubeParallel.getName()",
    "LocalDate SABRVolatilityCubeParallel.getReferenceDate()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCubeParallel sabrVolatilityCubeParallel =
        new SABRVolatilityCubeParallel(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act
    double actualCorrelationDecay = sabrVolatilityCubeParallel.getCorrelationDecay();
    double actualIborOisDecorrelation = sabrVolatilityCubeParallel.getIborOisDecorrelation();
    String actualName = sabrVolatilityCubeParallel.getName();
    LocalDate actualReferenceDate = sabrVolatilityCubeParallel.getReferenceDate();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals("Name", actualName);
    assertEquals(1.0d, actualIborOisDecorrelation, 0.0);
    assertEquals(10.0d, actualCorrelationDecay, 0.0);
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link SABRVolatilityCubeParallel#getParameters()}.
   *
   * <ul>
   *   <li>Then return size is eight.
   * </ul>
   *
   * <p>Method under test: {@link SABRVolatilityCubeParallel#getParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SABRVolatilityCubeParallel.getParameters()"})
  public void testGetParameters_thenReturnSizeIsEight() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCubeParallel sabrVolatilityCubeParallel =
        new SABRVolatilityCubeParallel(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act
    Map<String, Object> actualParameters = sabrVolatilityCubeParallel.getParameters();

    // Assert
    assertEquals(8, actualParameters.size());
    assertTrue(actualParameters.get("baseVolTable") instanceof DataTableLight);
    assertTrue(actualParameters.get("underlyingTable") instanceof DataTableLight);
    assertEquals(1.0d, ((Double) actualParameters.get("iborOisDecorrelation")).doubleValue(), 0.0);
    assertEquals(
        10.0d, ((Double) actualParameters.get("Inherent correlationDecay")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) actualParameters.get("sabrBeta")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) actualParameters.get("sabrDisplacement")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) actualParameters.get("sabrRho")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) actualParameters.get("sabrVolvol")).doubleValue(), 0.0);
  }

  /**
   * Test {@link SABRVolatilityCubeParallel#getLowestStrike(VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link SABRVolatilityCubeParallel#getLowestStrike(VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SABRVolatilityCubeParallel.getLowestStrike(VolatilityCubeModel)"})
  public void testGetLowestStrike() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCubeParallel sabrVolatilityCubeParallel =
        new SABRVolatilityCubeParallel(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act and Assert
    assertEquals(
        -10.0d,
        sabrVolatilityCubeParallel.getLowestStrike(new AnalyticModelWithVolatilityCubes()),
        0.0);
  }
}
