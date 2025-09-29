package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertArrayEquals;
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
import java.util.HashMap;
import java.util.Map;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.CurveInterpolation.ExtrapolationMethod;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationEntity;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationMethod;
import net.finmath.marketdata.model.curves.SeasonalCurve.Builder;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SeasonalCurveDiffblueTest {
  /**
   * Test Builder {@link Builder#Builder(SeasonalCurve)}.
   *
   * <ul>
   *   <li>Given {@link LocalDate} with {@code 1970} and one and one.
   *   <li>Then calls {@link CurveInterpolation#clone()}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#Builder(SeasonalCurve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Builder.<init>(SeasonalCurve)"})
  public void testBuilderNewBuilder_givenLocalDateWith1970AndOneAndOne_thenCallsClone()
      throws CloneNotSupportedException {
    // Arrange
    CurveInterpolation baseCurve = mock(CurveInterpolation.class);
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);
    when(baseCurve.clone()).thenReturn(curveInterpolation);
    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", LocalDate.of(1970, 1, 1), baseCurve);

    // Act
    new Builder(seasonalCurve);

    // Assert
    verify(baseCurve).clone();
  }

  /**
   * Test {@link SeasonalCurve#SeasonalCurve(String, LocalDate, Curve)}.
   *
   * <p>Method under test: {@link SeasonalCurve#SeasonalCurve(String, LocalDate, Curve)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SeasonalCurve.<init>(String, LocalDate, Curve)",
    "Builder SeasonalCurve.getCloneBuilder()"
  })
  public void testNewSeasonalCurve() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    SeasonalCurve actualSeasonalCurve =
        new SeasonalCurve(
            "Name", referenceDate, new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Assert
    LocalDate referenceDate2 = actualSeasonalCurve.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualSeasonalCurve.getName());
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link SeasonalCurve#SeasonalCurve(String, LocalDate, Map, int)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>When zero.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#SeasonalCurve(String, LocalDate, Map, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SeasonalCurve.<init>(String, LocalDate, Map, int)"})
  public void testNewSeasonalCurve_givenTen_whenZero_thenReturnName() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    HashMap<LocalDate, Double> indexFixings = new HashMap<>();
    indexFixings.put(LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    SeasonalCurve actualSeasonalCurve = new SeasonalCurve("Name", referenceDate, indexFixings, 0);

    // Assert
    assertEquals("Name", actualSeasonalCurve.getName());
    assertSame(referenceDate, actualSeasonalCurve.getReferenceDate());
    assertArrayEquals(new double[] {}, actualSeasonalCurve.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getParameter()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@code 10.000000000000002}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SeasonalCurve.getParameter()"})
  public void testGetParameter_thenReturnArrayOfDoubleWith10000000000000002() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SeasonalCurve seasonalCurve =
        new SeasonalCurve(
            "Name",
            referenceDate,
            DiscountCurveInterpolation.createDiscountCurveFromDiscountFactors(
                "Name",
                LocalDate.of(1970, 1, 1),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new boolean[] {true, false, true, false},
                InterpolationMethod.PIECEWISE_CONSTANT,
                ExtrapolationMethod.DEFAULT,
                InterpolationEntity.LOG_OF_VALUE));

    // Act and Assert
    assertArrayEquals(new double[] {10.000000000000002d}, seasonalCurve.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getParameter()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with one and {@code 10.000000000000002}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SeasonalCurve.getParameter()"})
  public void testGetParameter_thenReturnArrayOfDoubleWithOneAnd10000000000000002() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveInterpolation baseCurve =
        DiscountCurveInterpolation.createDiscountCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act and Assert
    assertArrayEquals(new double[] {1.0d, 10.000000000000002d}, seasonalCurve.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getParameter()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SeasonalCurve.getParameter()"})
  public void testGetParameter_thenReturnArrayOfDoubleWithTen() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SeasonalCurve seasonalCurve =
        new SeasonalCurve(
            "Name",
            referenceDate,
            DiscountCurveInterpolation.createDiscountCurveFromDiscountFactors(
                "Name",
                LocalDate.of(1970, 1, 1),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new boolean[] {true, false, true, false},
                InterpolationMethod.PIECEWISE_CONSTANT,
                ExtrapolationMethod.DEFAULT,
                InterpolationEntity.VALUE));

    // Act and Assert
    assertArrayEquals(new double[] {10.0d}, seasonalCurve.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getParameter()}.
   *
   * <ul>
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SeasonalCurve.getParameter()"})
  public void testGetParameter_thenReturnEmptyArrayOfDouble() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act and Assert
    assertArrayEquals(new double[] {}, seasonalCurve.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getParameter()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SeasonalCurve.getParameter()"})
  public void testGetParameter_thenReturnNull() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SeasonalCurve seasonalCurve =
        new SeasonalCurve(
            "Name", referenceDate, new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertNull(seasonalCurve.getParameter());
  }

  /**
   * Test {@link SeasonalCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <p>Method under test: {@link SeasonalCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SeasonalCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {365.0d, 12.0d, 365.0d, 12.0d},
            new double[] {365.0d, 12.0d, 365.0d, 12.0d});

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act and Assert
    assertEquals(12.0d, seasonalCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <p>Method under test: {@link SeasonalCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SeasonalCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT_RIGHTPOINT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {365.0d, 12.0d, 365.0d, 12.0d},
            new double[] {365.0d, 12.0d, 365.0d, 12.0d});

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act and Assert
    assertEquals(365.0d, seasonalCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <p>Method under test: {@link SeasonalCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SeasonalCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.LINEAR,
            InterpolationEntity.VALUE,
            new double[] {365.0d, 12.0d, 365.0d, 12.0d},
            new double[] {365.0d, 12.0d, 365.0d, 12.0d});

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act and Assert
    assertEquals(
        0.994623655913978d,
        seasonalCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link SeasonalCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <p>Method under test: {@link SeasonalCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SeasonalCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.LOG_OF_VALUE,
            new double[] {365.0d, 12.0d, 365.0d, 12.0d},
            new double[] {365.0d, 12.0d, 365.0d, 12.0d});

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act and Assert
    assertEquals(12.0d, seasonalCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <p>Method under test: {@link SeasonalCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SeasonalCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime5() {
    // Arrange
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.CONSTANT,
            InterpolationEntity.VALUE);
    baseCurve.addPoint(0.5d, 12.0d, false);
    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", LocalDate.of(1970, 1, 1), baseCurve);

    // Act and Assert
    assertEquals(12.0d, seasonalCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <p>Method under test: {@link SeasonalCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SeasonalCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime6() {
    // Arrange
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.CUBIC_SPLINE,
            ExtrapolationMethod.CONSTANT,
            InterpolationEntity.VALUE);
    baseCurve.addPoint(365.0d, 365.0d, true);
    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", LocalDate.of(1970, 1, 1), baseCurve);

    // Act and Assert
    assertEquals(365.0d, seasonalCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <p>Method under test: {@link SeasonalCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SeasonalCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime7() {
    // Arrange
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);
    baseCurve.addPoint(-0.5d, 365.0d, false);
    baseCurve.addPoint(0.5d, 12.0d, false);
    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", LocalDate.of(1970, 1, 1), baseCurve);

    // Act and Assert
    assertEquals(365.0d, seasonalCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <ul>
   *   <li>Then return {@code 0.994623655913978}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SeasonalCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturn0994623655913978() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.CUBIC_SPLINE,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {365.0d, 12.0d, 365.0d, 12.0d},
            new double[] {365.0d, 12.0d, 365.0d, 12.0d});

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act and Assert
    assertEquals(
        0.994623655913978d,
        seasonalCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link SeasonalCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <ul>
   *   <li>Then return {@code 1.228706812947458}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SeasonalCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturn1228706812947458() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveInterpolation baseCurve =
        DiscountCurveInterpolation.createDiscountCurveFromDiscountFactors(
            "Name",
            new double[] {365.0d, 12.0d, 365.0d, 12.0d},
            new double[] {365.0d, 12.0d, 365.0d, 12.0d});

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act and Assert
    assertEquals(
        1.228706812947458d,
        seasonalCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link SeasonalCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <ul>
   *   <li>Then return {@code 0.9946236559139785}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SeasonalCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturn09946236559139785() {
    // Arrange
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.CUBIC_SPLINE,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {365.0d, 12.0d, 365.0d, 12.0d},
            new double[] {365.0d, 12.0d, 365.0d, 12.0d});
    baseCurve.addPoint(1.0d, 1.0d, false);
    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", LocalDate.of(1970, 1, 1), baseCurve);

    // Act and Assert
    assertEquals(
        0.9946236559139785d,
        seasonalCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link SeasonalCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <ul>
   *   <li>Then return {@code 1.0027249356702335}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SeasonalCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturn10027249356702335() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SeasonalCurve seasonalCurve =
        new SeasonalCurve(
            "Name",
            referenceDate,
            new DiscountCurveFromForwardCurve(
                ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
                    "(?<=[0-9|\\.])(?=[A-Z|a-z])",
                    new double[] {365.0d, 12.0d, 365.0d, 12.0d},
                    new double[] {365.0d, 12.0d, 365.0d, 12.0d},
                    365.0d)));

    // Act and Assert
    assertEquals(
        1.0027249356702335d,
        seasonalCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link SeasonalCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <ul>
   *   <li>Then return {@code 12.989247311827956}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SeasonalCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturn12989247311827956() {
    // Arrange
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.LINEAR,
            InterpolationEntity.VALUE);
    baseCurve.addPoint(-0.5d, 10.0d, true);
    baseCurve.addPoint(0.5d, 12.0d, false);
    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", LocalDate.of(1970, 1, 1), baseCurve);

    // Act and Assert
    assertEquals(
        12.989247311827956d,
        seasonalCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link SeasonalCurve#getValue(AnalyticModel, double)} with {@code model}, {@code time}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SeasonalCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime_thenReturnTen() {
    // Arrange
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);
    baseCurve.addPoint(0.5d, 10.0d, true);
    baseCurve.addPoint(12.0d, 12.0d, false);
    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", LocalDate.of(1970, 1, 1), baseCurve);

    // Act and Assert
    assertEquals(10.0d, seasonalCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link SeasonalCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve SeasonalCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act
    Curve actualCloneForParameter =
        seasonalCurve.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCloneForParameter instanceof SeasonalCurve);
    LocalDate referenceDate2 = actualCloneForParameter.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneForParameter.getName());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(new double[] {}, actualCloneForParameter.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link SeasonalCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve SeasonalCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter2() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act
    Curve actualCloneForParameter =
        seasonalCurve.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCloneForParameter instanceof SeasonalCurve);
    LocalDate referenceDate2 = actualCloneForParameter.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneForParameter.getName());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(new double[] {}, actualCloneForParameter.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link SeasonalCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve SeasonalCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter3() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveInterpolation baseCurve =
        DiscountCurveInterpolation.createDiscountCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act
    Curve actualCloneForParameter =
        seasonalCurve.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCloneForParameter instanceof SeasonalCurve);
    LocalDate referenceDate2 = actualCloneForParameter.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneForParameter.getName());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {10.000000000000002d, 0.5d}, actualCloneForParameter.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link SeasonalCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve SeasonalCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter4() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act
    Curve actualCloneForParameter = seasonalCurve.getCloneForParameter(new double[] {});

    // Assert
    assertTrue(actualCloneForParameter instanceof SeasonalCurve);
    LocalDate referenceDate2 = actualCloneForParameter.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneForParameter.getName());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(new double[] {}, actualCloneForParameter.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getCloneForParameter(double[])}.
   *
   * <ul>
   *   <li>Then return Parameter is array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve SeasonalCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter_thenReturnParameterIsArrayOfDoubleWithTen()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SeasonalCurve seasonalCurve =
        new SeasonalCurve(
            "Name",
            referenceDate,
            DiscountCurveInterpolation.createDiscountCurveFromDiscountFactors(
                "Name",
                LocalDate.of(1970, 1, 1),
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new boolean[] {true, false, true, false},
                InterpolationMethod.PIECEWISE_CONSTANT,
                ExtrapolationMethod.DEFAULT,
                InterpolationEntity.VALUE));

    // Act
    Curve actualCloneForParameter =
        seasonalCurve.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCloneForParameter instanceof SeasonalCurve);
    LocalDate referenceDate2 = actualCloneForParameter.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneForParameter.getName());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(new double[] {10.0d}, actualCloneForParameter.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getCloneForParameter(double[])}.
   *
   * <ul>
   *   <li>Then return Parameter is array of {@code double} with ten and {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve SeasonalCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter_thenReturnParameterIsArrayOfDoubleWithTenAnd05()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveNelsonSiegelSvensson baseCurve =
        new DiscountCurveNelsonSiegelSvensson(
            "Name", LocalDate.of(1970, 1, 1), new double[] {10.0d, 1.0d, 10.0d, 1.0d}, 10.0d);

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act
    Curve actualCloneForParameter =
        seasonalCurve.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCloneForParameter instanceof SeasonalCurve);
    LocalDate referenceDate2 = actualCloneForParameter.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneForParameter.getName());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d}, actualCloneForParameter.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#getCloneForParameter(double[])}.
   *
   * <ul>
   *   <li>Then return Parameter is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve SeasonalCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter_thenReturnParameterIsNull()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    ForwardCurveFromDiscountCurve baseCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act
    Curve actualCloneForParameter =
        seasonalCurve.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualCloneForParameter instanceof SeasonalCurve);
    LocalDate referenceDate2 = actualCloneForParameter.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneForParameter.getName());
    assertNull(actualCloneForParameter.getParameter());
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link SeasonalCurve#getCloneForParameter(double[])}.
   *
   * <ul>
   *   <li>Then throw {@link CloneNotSupportedException}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Curve SeasonalCurve.getCloneForParameter(double[])"})
  public void testGetCloneForParameter_thenThrowCloneNotSupportedException()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SeasonalCurve seasonalCurve =
        new SeasonalCurve(
            "Name", referenceDate, new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act and Assert
    assertThrows(
        CloneNotSupportedException.class,
        () -> seasonalCurve.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d}));
  }

  /**
   * Test {@link SeasonalCurve#clone()}.
   *
   * <p>Method under test: {@link SeasonalCurve#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SeasonalCurve SeasonalCurve.clone()"})
  public void testClone() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act
    SeasonalCurve actualCloneResult = seasonalCurve.clone();

    // Assert
    LocalDate referenceDate2 = actualCloneResult.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneResult.getName());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(new double[] {}, actualCloneResult.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#clone()}.
   *
   * <p>Method under test: {@link SeasonalCurve#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SeasonalCurve SeasonalCurve.clone()"})
  public void testClone2() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CurveInterpolation baseCurve =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act
    SeasonalCurve actualCloneResult = seasonalCurve.clone();

    // Assert
    LocalDate referenceDate2 = actualCloneResult.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneResult.getName());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(new double[] {}, actualCloneResult.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#clone()}.
   *
   * <p>Method under test: {@link SeasonalCurve#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SeasonalCurve SeasonalCurve.clone()"})
  public void testClone3() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveRenormalized baseCurve =
        new DiscountCurveRenormalized(
            "Name", LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "Base Curve Name");

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act
    SeasonalCurve actualCloneResult = seasonalCurve.clone();

    // Assert
    LocalDate referenceDate2 = actualCloneResult.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneResult.getName());
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link SeasonalCurve#clone()}.
   *
   * <ul>
   *   <li>Then return Parameter is array of {@code double} with one and {@code 10.000000000000002}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SeasonalCurve SeasonalCurve.clone()"})
  public void testClone_thenReturnParameterIsArrayOfDoubleWithOneAnd10000000000000002()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveInterpolation baseCurve =
        DiscountCurveInterpolation.createDiscountCurveFromDiscountFactors(
            "Name",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act
    SeasonalCurve actualCloneResult = seasonalCurve.clone();

    // Assert
    LocalDate referenceDate2 = actualCloneResult.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneResult.getName());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {1.0d, 10.000000000000002d}, actualCloneResult.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#clone()}.
   *
   * <ul>
   *   <li>Then return Parameter is array of {@code double} with ten and one.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SeasonalCurve SeasonalCurve.clone()"})
  public void testClone_thenReturnParameterIsArrayOfDoubleWithTenAndOne()
      throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveNelsonSiegelSvensson baseCurve =
        new DiscountCurveNelsonSiegelSvensson(
            "Name", LocalDate.of(1970, 1, 1), new double[] {10.0d, 1.0d, 10.0d, 1.0d}, 10.0d);

    SeasonalCurve seasonalCurve = new SeasonalCurve("Name", referenceDate, baseCurve);

    // Act
    SeasonalCurve actualCloneResult = seasonalCurve.clone();

    // Assert
    LocalDate referenceDate2 = actualCloneResult.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneResult.getName());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {10.0d, 1.0d, 10.0d, 1.0d}, actualCloneResult.getParameter(), 0.0);
  }

  /**
   * Test {@link SeasonalCurve#clone()}.
   *
   * <ul>
   *   <li>Then return Parameter is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SeasonalCurve#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SeasonalCurve SeasonalCurve.clone()"})
  public void testClone_thenReturnParameterIsNull() throws CloneNotSupportedException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SeasonalCurve seasonalCurve =
        new SeasonalCurve(
            "Name", referenceDate, new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    SeasonalCurve actualCloneResult = seasonalCurve.clone();

    // Assert
    LocalDate referenceDate2 = actualCloneResult.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneResult.getName());
    assertNull(actualCloneResult.getParameter());
    assertSame(referenceDate, referenceDate2);
  }

  /**
   * Test {@link SeasonalCurve#computeSeasonalAdjustments(double[], int, int)} with {@code
   * realizedCPIValues}, {@code lastMonth}, {@code numberOfYearsToAverage}.
   *
   * <p>Method under test: {@link SeasonalCurve#computeSeasonalAdjustments(double[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SeasonalCurve.computeSeasonalAdjustments(double[], int, int)"})
  public void testComputeSeasonalAdjustmentsWithRealizedCPIValuesLastMonthNumberOfYearsToAverage() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        SeasonalCurve.computeSeasonalAdjustments(new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 1, 0),
        0.0);
  }

  /**
   * Test {@link SeasonalCurve#computeSeasonalAdjustments(LocalDate, Map, int)} with {@code
   * referenceDate}, {@code indexFixings}, {@code numberOfYearsToAverage}.
   *
   * <p>Method under test: {@link SeasonalCurve#computeSeasonalAdjustments(LocalDate, Map, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SeasonalCurve.computeSeasonalAdjustments(LocalDate, Map, int)"})
  public void testComputeSeasonalAdjustmentsWithReferenceDateIndexFixingsNumberOfYearsToAverage() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    HashMap<LocalDate, Double> indexFixings = new HashMap<>();
    indexFixings.put(LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    double[] actualComputeSeasonalAdjustmentsResult =
        SeasonalCurve.computeSeasonalAdjustments(referenceDate, indexFixings, 0);

    // Assert
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualComputeSeasonalAdjustmentsResult,
        0.0);
  }
}
