package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CurveFactoryDiffblueTest {
  /**
   * Test {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer,
   * Map, String, String)}.
   *
   * <p>Method under test: {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate,
   * Map, Map, Integer, Map, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Curve CurveFactory.createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer, Map, String, String)"
  })
  public void testCreateIndexCurveWithSeasonality() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    HashMap<LocalDate, Double> indexFixings = new HashMap<>();
    indexFixings.put(LocalDate.of(1970, 1, 1), 10.0d);

    HashMap<String, Double> seasonalityAdjustments = new HashMap<>();
    seasonalityAdjustments.put(
        "Specified seasonal factors and seasonal averaging at the same time.", 365.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            CurveFactory.createIndexCurveWithSeasonality(
                "Name",
                referenceDate,
                indexFixings,
                seasonalityAdjustments,
                10,
                new HashMap<>(),
                "Forwards Fixing Lag",
                "Forwards Fixing Type"));
  }

  /**
   * Test {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer,
   * Map, String, String)}.
   *
   * <p>Method under test: {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate,
   * Map, Map, Integer, Map, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Curve CurveFactory.createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer, Map, String, String)"
  })
  public void testCreateIndexCurveWithSeasonality2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    HashMap<LocalDate, Double> indexFixings = new HashMap<>();
    indexFixings.put(LocalDate.of(1970, 1, 1), 365.0d);
    indexFixings.put(LocalDate.of(1970, 1, 1), 10.0d);
    HashMap<String, Double> seasonalityAdjustments = new HashMap<>();

    HashMap<LocalDate, Double> annualizedZeroRates = new HashMap<>();
    annualizedZeroRates.put(LocalDate.of(1970, 1, 1), Double.NaN);

    // Act
    Curve actualCreateIndexCurveWithSeasonalityResult =
        CurveFactory.createIndexCurveWithSeasonality(
            "Name",
            referenceDate,
            indexFixings,
            seasonalityAdjustments,
            null,
            annualizedZeroRates,
            null,
            "Forwards Fixing Type");

    // Assert
    Curve fixedPartCurve =
        ((PiecewiseCurve) actualCreateIndexCurveWithSeasonalityResult).getFixedPartCurve();
    assertTrue(fixedPartCurve instanceof CurveInterpolation);
    Curve baseCurve = ((PiecewiseCurve) actualCreateIndexCurveWithSeasonalityResult).getBaseCurve();
    assertTrue(baseCurve instanceof IndexCurveFromDiscountCurve);
    assertTrue(actualCreateIndexCurveWithSeasonalityResult instanceof PiecewiseCurve);
    assertArrayEquals(new double[] {}, baseCurve.getParameter(), 0.0);
    assertArrayEquals(new double[] {}, fixedPartCurve.getParameter(), 0.0);
    assertArrayEquals(
        new double[] {}, actualCreateIndexCurveWithSeasonalityResult.getParameter(), 0.0);
    assertArrayEquals(new double[] {0.0d}, ((CurveInterpolation) fixedPartCurve).getTimes(), 0.0);
  }

  /**
   * Test {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer,
   * Map, String, String)}.
   *
   * <ul>
   *   <li>Then FixedPartCurve return {@link CurveInterpolation}.
   * </ul>
   *
   * <p>Method under test: {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate,
   * Map, Map, Integer, Map, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Curve CurveFactory.createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer, Map, String, String)"
  })
  public void testCreateIndexCurveWithSeasonality_thenFixedPartCurveReturnCurveInterpolation() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    HashMap<LocalDate, Double> indexFixings = new HashMap<>();
    indexFixings.put(LocalDate.of(1970, 1, 1), 10.0d);
    HashMap<String, Double> seasonalityAdjustments = new HashMap<>();

    HashMap<LocalDate, Double> annualizedZeroRates = new HashMap<>();
    annualizedZeroRates.put(LocalDate.of(1970, 1, 1), Double.NaN);

    // Act
    Curve actualCreateIndexCurveWithSeasonalityResult =
        CurveFactory.createIndexCurveWithSeasonality(
            "Name",
            referenceDate,
            indexFixings,
            seasonalityAdjustments,
            null,
            annualizedZeroRates,
            null,
            "Forwards Fixing Type");

    // Assert
    Curve fixedPartCurve =
        ((PiecewiseCurve) actualCreateIndexCurveWithSeasonalityResult).getFixedPartCurve();
    assertTrue(fixedPartCurve instanceof CurveInterpolation);
    Curve baseCurve = ((PiecewiseCurve) actualCreateIndexCurveWithSeasonalityResult).getBaseCurve();
    assertTrue(baseCurve instanceof IndexCurveFromDiscountCurve);
    assertTrue(actualCreateIndexCurveWithSeasonalityResult instanceof PiecewiseCurve);
    assertArrayEquals(new double[] {}, baseCurve.getParameter(), 0.0);
    assertArrayEquals(new double[] {}, fixedPartCurve.getParameter(), 0.0);
    assertArrayEquals(
        new double[] {}, actualCreateIndexCurveWithSeasonalityResult.getParameter(), 0.0);
    assertArrayEquals(new double[] {0.0d}, ((CurveInterpolation) fixedPartCurve).getTimes(), 0.0);
  }

  /**
   * Test {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer,
   * Map, String, String)}.
   *
   * <ul>
   *   <li>Then FixedPartCurve return {@link CurveInterpolation}.
   * </ul>
   *
   * <p>Method under test: {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate,
   * Map, Map, Integer, Map, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Curve CurveFactory.createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer, Map, String, String)"
  })
  public void testCreateIndexCurveWithSeasonality_thenFixedPartCurveReturnCurveInterpolation2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    HashMap<LocalDate, Double> indexFixings = new HashMap<>();
    indexFixings.put(LocalDate.of(1970, 1, 1), 10.0d);
    HashMap<String, Double> seasonalityAdjustments = new HashMap<>();

    HashMap<LocalDate, Double> annualizedZeroRates = new HashMap<>();
    annualizedZeroRates.put(LocalDate.of(1970, 1, 1), Double.NaN);

    // Act
    Curve actualCreateIndexCurveWithSeasonalityResult =
        CurveFactory.createIndexCurveWithSeasonality(
            "Name",
            referenceDate,
            indexFixings,
            seasonalityAdjustments,
            null,
            annualizedZeroRates,
            "Forwards Fixing Lag",
            null);

    // Assert
    Curve fixedPartCurve =
        ((PiecewiseCurve) actualCreateIndexCurveWithSeasonalityResult).getFixedPartCurve();
    assertTrue(fixedPartCurve instanceof CurveInterpolation);
    Curve baseCurve = ((PiecewiseCurve) actualCreateIndexCurveWithSeasonalityResult).getBaseCurve();
    assertTrue(baseCurve instanceof IndexCurveFromDiscountCurve);
    assertTrue(actualCreateIndexCurveWithSeasonalityResult instanceof PiecewiseCurve);
    assertArrayEquals(new double[] {}, baseCurve.getParameter(), 0.0);
    assertArrayEquals(new double[] {}, fixedPartCurve.getParameter(), 0.0);
    assertArrayEquals(
        new double[] {}, actualCreateIndexCurveWithSeasonalityResult.getParameter(), 0.0);
    assertArrayEquals(new double[] {0.0d}, ((CurveInterpolation) fixedPartCurve).getTimes(), 0.0);
  }

  /**
   * Test {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer,
   * Map, String, String)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate,
   * Map, Map, Integer, Map, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Curve CurveFactory.createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer, Map, String, String)"
  })
  public void testCreateIndexCurveWithSeasonality_thenThrowIllegalArgumentException() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    HashMap<LocalDate, Double> indexFixings = new HashMap<>();
    indexFixings.put(LocalDate.of(1970, 1, 1), 10.0d);
    HashMap<String, Double> seasonalityAdjustments = new HashMap<>();

    HashMap<LocalDate, Double> annualizedZeroRates = new HashMap<>();
    annualizedZeroRates.put(LocalDate.of(1970, 1, 1), Double.NaN);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            CurveFactory.createIndexCurveWithSeasonality(
                "Name",
                referenceDate,
                indexFixings,
                seasonalityAdjustments,
                null,
                annualizedZeroRates,
                "Forwards Fixing Lag",
                "Forwards Fixing Type"));
  }

  /**
   * Test {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer,
   * Map, String, String)}.
   *
   * <ul>
   *   <li>When {@code endOfMonth}.
   * </ul>
   *
   * <p>Method under test: {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate,
   * Map, Map, Integer, Map, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Curve CurveFactory.createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer, Map, String, String)"
  })
  public void testCreateIndexCurveWithSeasonality_whenEndOfMonth() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    HashMap<LocalDate, Double> indexFixings = new HashMap<>();
    indexFixings.put(LocalDate.of(1970, 1, 1), 10.0d);
    HashMap<String, Double> seasonalityAdjustments = new HashMap<>();

    HashMap<LocalDate, Double> annualizedZeroRates = new HashMap<>();
    annualizedZeroRates.put(LocalDate.of(1970, 1, 1), Double.NaN);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            CurveFactory.createIndexCurveWithSeasonality(
                "Name",
                referenceDate,
                indexFixings,
                seasonalityAdjustments,
                null,
                annualizedZeroRates,
                "Forwards Fixing Lag",
                "endOfMonth"));
  }

  /**
   * Test {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer,
   * Map, String, String)}.
   *
   * <ul>
   *   <li>When now.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate,
   * Map, Map, Integer, Map, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Curve CurveFactory.createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer, Map, String, String)"
  })
  public void testCreateIndexCurveWithSeasonality_whenNow_thenThrowIllegalArgumentException() {
    // Arrange
    LocalDate referenceDate = LocalDate.now();

    HashMap<LocalDate, Double> indexFixings = new HashMap<>();
    indexFixings.put(LocalDate.of(1970, 1, 1), 10.0d);
    HashMap<String, Double> seasonalityAdjustments = new HashMap<>();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            CurveFactory.createIndexCurveWithSeasonality(
                "Name",
                referenceDate,
                indexFixings,
                seasonalityAdjustments,
                10,
                new HashMap<>(),
                "Forwards Fixing Lag",
                "Forwards Fixing Type"));
  }

  /**
   * Test {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer,
   * Map, String, String)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CurveFactory#createIndexCurveWithSeasonality(String, LocalDate,
   * Map, Map, Integer, Map, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Curve CurveFactory.createIndexCurveWithSeasonality(String, LocalDate, Map, Map, Integer, Map, String, String)"
  })
  public void testCreateIndexCurveWithSeasonality_whenTen_thenThrowIllegalArgumentException() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    HashMap<LocalDate, Double> indexFixings = new HashMap<>();
    indexFixings.put(LocalDate.of(1970, 1, 1), 10.0d);
    HashMap<String, Double> seasonalityAdjustments = new HashMap<>();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            CurveFactory.createIndexCurveWithSeasonality(
                "Name",
                referenceDate,
                indexFixings,
                seasonalityAdjustments,
                10,
                new HashMap<>(),
                "Forwards Fixing Lag",
                "Forwards Fixing Type"));
  }
}
