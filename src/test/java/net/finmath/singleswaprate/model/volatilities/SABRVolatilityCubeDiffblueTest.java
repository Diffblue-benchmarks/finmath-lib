package net.finmath.singleswaprate.model.volatilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeSet;
import net.finmath.marketdata.model.volatilities.VolatilitySurface;
import net.finmath.marketdata.model.volatilities.VolatilitySurface.QuotingConvention;
import net.finmath.singleswaprate.data.DataTable;
import net.finmath.singleswaprate.data.DataTable.TableConvention;
import net.finmath.singleswaprate.data.DataTableLight;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SABRVolatilityCubeDiffblueTest {
  /**
   * Test {@link SABRVolatilityCube#SABRVolatilityCube(String, LocalDate, DataTable, double, double,
   * DataTable, DataTable, DataTable, double)}.
   *
   * <p>Method under test: {@link SABRVolatilityCube#SABRVolatilityCube(String, LocalDate,
   * DataTable, double, double, DataTable, DataTable, DataTable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SABRVolatilityCube.<init>(String, LocalDate, DataTable, double, double, DataTable, DataTable, DataTable, double)"
  })
  public void testNewSABRVolatilityCube() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight rhoTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight baseVolTable = new DataTableLight("Name", TableConvention.MONTHS);

    // Act
    SABRVolatilityCube actualSabrVolatilityCube =
        new SABRVolatilityCube(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            rhoTable,
            baseVolTable,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Assert
    assertTrue(actualSabrVolatilityCube.getBaseVolTable() instanceof DataTableLight);
    assertTrue(actualSabrVolatilityCube.getRhoTable() instanceof DataTableLight);
    assertTrue(actualSabrVolatilityCube.getUnderlyingTable() instanceof DataTableLight);
    assertTrue(actualSabrVolatilityCube.getVolvolTable() instanceof DataTableLight);
    assertEquals("Name", actualSabrVolatilityCube.getName());
    assertEquals(1.0d, actualSabrVolatilityCube.getIborOisDecorrelation(), 0.0);
    assertEquals(10.0d, actualSabrVolatilityCube.getCorrelationDecay(), 0.0);
    Map<String, Object> parameters = actualSabrVolatilityCube.getParameters();
    assertEquals(8, parameters.size());
    assertTrue(parameters.containsKey("Inherent correlationDecay"));
    assertTrue(parameters.containsKey("baseVolTable"));
    assertTrue(parameters.containsKey("rhoTable"));
    assertTrue(parameters.containsKey("sabrBeta"));
    assertTrue(parameters.containsKey("sabrDisplacement"));
    assertTrue(parameters.containsKey("volvolTable"));
    assertSame(referenceDate, actualSabrVolatilityCube.getReferenceDate());
  }

  /**
   * Test {@link SABRVolatilityCube#SABRVolatilityCube(String, LocalDate, DataTable, double, double,
   * DataTable, DataTable, DataTable, double, double)}.
   *
   * <p>Method under test: {@link SABRVolatilityCube#SABRVolatilityCube(String, LocalDate,
   * DataTable, double, double, DataTable, DataTable, DataTable, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SABRVolatilityCube.<init>(String, LocalDate, DataTable, double, double, DataTable, DataTable, DataTable, double, double)"
  })
  public void testNewSABRVolatilityCube2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight rhoTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight baseVolTable = new DataTableLight("Name", TableConvention.MONTHS);

    // Act
    SABRVolatilityCube actualSabrVolatilityCube =
        new SABRVolatilityCube(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            rhoTable,
            baseVolTable,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d,
            10.0d);

    // Assert
    assertTrue(actualSabrVolatilityCube.getBaseVolTable() instanceof DataTableLight);
    assertTrue(actualSabrVolatilityCube.getRhoTable() instanceof DataTableLight);
    assertTrue(actualSabrVolatilityCube.getUnderlyingTable() instanceof DataTableLight);
    assertTrue(actualSabrVolatilityCube.getVolvolTable() instanceof DataTableLight);
    assertEquals("Name", actualSabrVolatilityCube.getName());
    assertEquals(10.0d, actualSabrVolatilityCube.getCorrelationDecay(), 0.0);
    assertEquals(10.0d, actualSabrVolatilityCube.getIborOisDecorrelation(), 0.0);
    Map<String, Object> parameters = actualSabrVolatilityCube.getParameters();
    assertEquals(8, parameters.size());
    assertTrue(parameters.containsKey("Inherent correlationDecay"));
    assertTrue(parameters.containsKey("baseVolTable"));
    assertTrue(parameters.containsKey("rhoTable"));
    assertTrue(parameters.containsKey("sabrBeta"));
    assertTrue(parameters.containsKey("sabrDisplacement"));
    assertTrue(parameters.containsKey("volvolTable"));
    assertSame(referenceDate, actualSabrVolatilityCube.getReferenceDate());
  }

  /**
   * Test {@link SABRVolatilityCube#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link SABRVolatilityCube#getValue(VolatilityCubeModel, double, double,
   * double, QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCube.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight rhoTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight baseVolTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCube sabrVolatilityCube =
        new SABRVolatilityCube(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            rhoTable,
            baseVolTable,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            sabrVolatilityCube.getValue(
                new AnalyticModelWithVolatilityCubes(),
                10.0d,
                10.0d,
                10.0d,
                QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test {@link SABRVolatilityCube#getValue(double, double, double, QuotingConvention)} with {@code
   * termination}, {@code maturity}, {@code strike}, {@code quotingConvention}.
   *
   * <p>Method under test: {@link SABRVolatilityCube#getValue(double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCube.getValue(double, double, double, QuotingConvention)"
  })
  public void testGetValueWithTerminationMaturityStrikeQuotingConvention() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight rhoTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight baseVolTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCube sabrVolatilityCube =
        new SABRVolatilityCube(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            rhoTable,
            baseVolTable,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            sabrVolatilityCube.getValue(
                10.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SABRVolatilityCube#getCorrelationDecay()}
   *   <li>{@link SABRVolatilityCube#getIborOisDecorrelation()}
   *   <li>{@link SABRVolatilityCube#getName()}
   *   <li>{@link SABRVolatilityCube#getReferenceDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SABRVolatilityCube.getCorrelationDecay()",
    "double SABRVolatilityCube.getIborOisDecorrelation()",
    "String SABRVolatilityCube.getName()",
    "LocalDate SABRVolatilityCube.getReferenceDate()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight rhoTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight baseVolTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCube sabrVolatilityCube =
        new SABRVolatilityCube(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            rhoTable,
            baseVolTable,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act
    double actualCorrelationDecay = sabrVolatilityCube.getCorrelationDecay();
    double actualIborOisDecorrelation = sabrVolatilityCube.getIborOisDecorrelation();
    String actualName = sabrVolatilityCube.getName();
    LocalDate actualReferenceDate = sabrVolatilityCube.getReferenceDate();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals("Name", actualName);
    assertEquals(1.0d, actualIborOisDecorrelation, 0.0);
    assertEquals(10.0d, actualCorrelationDecay, 0.0);
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link SABRVolatilityCube#getParameters()}.
   *
   * <ul>
   *   <li>Then return size is eight.
   * </ul>
   *
   * <p>Method under test: {@link SABRVolatilityCube#getParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SABRVolatilityCube.getParameters()"})
  public void testGetParameters_thenReturnSizeIsEight() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight rhoTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight baseVolTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCube sabrVolatilityCube =
        new SABRVolatilityCube(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            rhoTable,
            baseVolTable,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act
    Map<String, Object> actualParameters = sabrVolatilityCube.getParameters();

    // Assert
    assertEquals(8, actualParameters.size());
    assertTrue(actualParameters.get("baseVolTable") instanceof DataTableLight);
    assertTrue(actualParameters.get("rhoTable") instanceof DataTableLight);
    assertTrue(actualParameters.get("underlyingTable") instanceof DataTableLight);
    assertTrue(actualParameters.get("volvolTable") instanceof DataTableLight);
    assertEquals(1.0d, ((Double) actualParameters.get("iborOisDecorrelation")).doubleValue(), 0.0);
    assertEquals(
        10.0d, ((Double) actualParameters.get("Inherent correlationDecay")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) actualParameters.get("sabrBeta")).doubleValue(), 0.0);
    assertEquals(10.0d, ((Double) actualParameters.get("sabrDisplacement")).doubleValue(), 0.0);
  }

  /**
   * Test {@link SABRVolatilityCube#getLowestStrike(VolatilityCubeModel)}.
   *
   * <p>Method under test: {@link SABRVolatilityCube#getLowestStrike(VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SABRVolatilityCube.getLowestStrike(VolatilityCubeModel)"})
  public void testGetLowestStrike() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight rhoTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight baseVolTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCube sabrVolatilityCube =
        new SABRVolatilityCube(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            rhoTable,
            baseVolTable,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act and Assert
    assertEquals(
        -10.0d, sabrVolatilityCube.getLowestStrike(new AnalyticModelWithVolatilityCubes()), 0.0);
  }

  /**
   * Test {@link SABRVolatilityCube#getUnderlyingTable()}.
   *
   * <ul>
   *   <li>Then return {@link DataTableLight}.
   * </ul>
   *
   * <p>Method under test: {@link SABRVolatilityCube#getUnderlyingTable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTable SABRVolatilityCube.getUnderlyingTable()"})
  public void testGetUnderlyingTable_thenReturnDataTableLight() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight rhoTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight baseVolTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCube sabrVolatilityCube =
        new SABRVolatilityCube(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            rhoTable,
            baseVolTable,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act
    DataTable actualUnderlyingTable = sabrVolatilityCube.getUnderlyingTable();

    // Assert
    assertTrue(actualUnderlyingTable instanceof DataTableLight);
    assertEquals("Name", actualUnderlyingTable.getName());
    assertNull(actualUnderlyingTable.getReferenceDate());
    assertNull(actualUnderlyingTable.getScheduleMetaData());
    assertEquals(0, actualUnderlyingTable.size());
    assertEquals(TableConvention.MONTHS, actualUnderlyingTable.getConvention());
    TreeSet<Integer> maturities = actualUnderlyingTable.getMaturities();
    assertTrue(maturities.isEmpty());
    assertEquals(maturities, actualUnderlyingTable.getTerminations());
  }

  /**
   * Test {@link SABRVolatilityCube#getRhoTable()}.
   *
   * <ul>
   *   <li>Then return {@link DataTableLight}.
   * </ul>
   *
   * <p>Method under test: {@link SABRVolatilityCube#getRhoTable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTable SABRVolatilityCube.getRhoTable()"})
  public void testGetRhoTable_thenReturnDataTableLight() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight rhoTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight baseVolTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCube sabrVolatilityCube =
        new SABRVolatilityCube(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            rhoTable,
            baseVolTable,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act
    DataTable actualRhoTable = sabrVolatilityCube.getRhoTable();

    // Assert
    assertTrue(actualRhoTable instanceof DataTableLight);
    assertEquals("Name", actualRhoTable.getName());
    assertNull(actualRhoTable.getReferenceDate());
    assertNull(actualRhoTable.getScheduleMetaData());
    assertEquals(0, actualRhoTable.size());
    assertEquals(TableConvention.MONTHS, actualRhoTable.getConvention());
    TreeSet<Integer> maturities = actualRhoTable.getMaturities();
    assertTrue(maturities.isEmpty());
    assertEquals(maturities, actualRhoTable.getTerminations());
  }

  /**
   * Test {@link SABRVolatilityCube#getBaseVolTable()}.
   *
   * <ul>
   *   <li>Then return {@link DataTableLight}.
   * </ul>
   *
   * <p>Method under test: {@link SABRVolatilityCube#getBaseVolTable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTable SABRVolatilityCube.getBaseVolTable()"})
  public void testGetBaseVolTable_thenReturnDataTableLight() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight rhoTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight baseVolTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCube sabrVolatilityCube =
        new SABRVolatilityCube(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            rhoTable,
            baseVolTable,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act
    DataTable actualBaseVolTable = sabrVolatilityCube.getBaseVolTable();

    // Assert
    assertTrue(actualBaseVolTable instanceof DataTableLight);
    assertEquals("Name", actualBaseVolTable.getName());
    assertNull(actualBaseVolTable.getReferenceDate());
    assertNull(actualBaseVolTable.getScheduleMetaData());
    assertEquals(0, actualBaseVolTable.size());
    assertEquals(TableConvention.MONTHS, actualBaseVolTable.getConvention());
    TreeSet<Integer> maturities = actualBaseVolTable.getMaturities();
    assertTrue(maturities.isEmpty());
    assertEquals(maturities, actualBaseVolTable.getTerminations());
  }

  /**
   * Test {@link SABRVolatilityCube#getVolvolTable()}.
   *
   * <ul>
   *   <li>Then return {@link DataTableLight}.
   * </ul>
   *
   * <p>Method under test: {@link SABRVolatilityCube#getVolvolTable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTable SABRVolatilityCube.getVolvolTable()"})
  public void testGetVolvolTable_thenReturnDataTableLight() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DataTableLight swapRateTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight rhoTable = new DataTableLight("Name", TableConvention.MONTHS);
    DataTableLight baseVolTable = new DataTableLight("Name", TableConvention.MONTHS);

    SABRVolatilityCube sabrVolatilityCube =
        new SABRVolatilityCube(
            "Name",
            referenceDate,
            swapRateTable,
            10.0d,
            10.0d,
            rhoTable,
            baseVolTable,
            new DataTableLight("Name", TableConvention.MONTHS),
            10.0d);

    // Act
    DataTable actualVolvolTable = sabrVolatilityCube.getVolvolTable();

    // Assert
    assertTrue(actualVolvolTable instanceof DataTableLight);
    assertEquals("Name", actualVolvolTable.getName());
    assertNull(actualVolvolTable.getReferenceDate());
    assertNull(actualVolvolTable.getScheduleMetaData());
    assertEquals(0, actualVolvolTable.size());
    assertEquals(TableConvention.MONTHS, actualVolvolTable.getConvention());
    TreeSet<Integer> maturities = actualVolvolTable.getMaturities();
    assertTrue(maturities.isEmpty());
    assertEquals(maturities, actualVolvolTable.getTerminations());
  }
}
