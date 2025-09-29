package net.finmath.marketdata.model.volatility.caplet.tenorconversion;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.marketdata.model.curves.ForwardCurveInterpolation;
import net.finmath.marketdata.model.volatility.caplet.CapTenorStructure;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TenorConverterDiffblueTest {
  /**
   * Test {@link TenorConverter#TenorConverter(CorrelationProvider, int, int, double[], double[],
   * double[][], CapTenorStructure, AnalyticModel, String, String, String)}.
   *
   * <ul>
   *   <li>Then calls {@link AnalyticModel#getDiscountCurve(String)}.
   * </ul>
   *
   * <p>Method under test: {@link TenorConverter#TenorConverter(CorrelationProvider, int, int,
   * double[], double[], double[][], CapTenorStructure, AnalyticModel, String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TenorConverter.<init>(CorrelationProvider, int, int, double[], double[], double[][], CapTenorStructure, AnalyticModel, String, String, String)"
  })
  public void testNewTenorConverter_thenCallsGetDiscountCurve() {
    // Arrange
    CorrelationProvider correlationProvider = mock(CorrelationProvider.class);
    double[][] capletVolatilities = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    AnalyticModel analyticModel2 = mock(AnalyticModel.class);
    when(analyticModel2.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(analyticModel2.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);

    // Act
    new TenorConverter(
        correlationProvider,
        1,
        1,
        new double[] {10.0d, 1.0d, 10.0d, 1.0d},
        new double[] {10.0d, 1.0d, 10.0d, 1.0d},
        capletVolatilities,
        CapTenorStructure.EUR,
        analyticModel2,
        "3",
        "Index Old Tenor",
        "Index New Tenor");

    // Assert
    verify(analyticModel2).getDiscountCurve("EUR_3");
    verify(analyticModel2, atLeast(1)).getForwardCurve("Forward_EUR_Index New Tenor");
  }

  /**
   * Test {@link TenorConverter#TenorConverter(CorrelationProvider, int, int, double[], double[],
   * double[][], CapTenorStructure, AnalyticModel, String, String, String)}.
   *
   * <ul>
   *   <li>Then calls {@link AnalyticModel#getDiscountCurve(String)}.
   * </ul>
   *
   * <p>Method under test: {@link TenorConverter#TenorConverter(CorrelationProvider, int, int,
   * double[], double[], double[][], CapTenorStructure, AnalyticModel, String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TenorConverter.<init>(CorrelationProvider, int, int, double[], double[], double[][], CapTenorStructure, AnalyticModel, String, String, String)"
  })
  public void testNewTenorConverter_thenCallsGetDiscountCurve2() {
    // Arrange
    CorrelationProvider correlationProvider = mock(CorrelationProvider.class);
    double[][] capletVolatilities = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    AnalyticModel analyticModel2 = mock(AnalyticModel.class);
    when(analyticModel2.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(analyticModel2.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);

    // Act
    new TenorConverter(
        correlationProvider,
        1,
        1,
        new double[] {10.0d, 1.0d, 10.0d, 1.0d},
        new double[] {10.0d, 1.0d, 10.0d, 1.0d},
        capletVolatilities,
        CapTenorStructure.USD,
        analyticModel2,
        "3",
        "Index Old Tenor",
        "Index New Tenor");

    // Assert
    verify(analyticModel2).getDiscountCurve("USD_3");
    verify(analyticModel2, atLeast(1)).getForwardCurve("Forward_USD_Index New Tenor");
  }

  /**
   * Test {@link TenorConverter#convertTenor()}.
   *
   * <p>Method under test: {@link TenorConverter#convertTenor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] TenorConverter.convertTenor()"})
  public void testConvertTenor() throws CalculationException {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModel2 = mock(AnalyticModelFromCurvesAndVols.class);
    when(analyticModel2.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(analyticModel2.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);
    double[][] capletVolatilities = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    // Act and Assert
    assertThrows(
        CalculationException.class,
        () ->
            new TenorConverter(
                    mock(CorrelationProvider.class),
                    1,
                    1,
                    new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                    new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                    capletVolatilities,
                    CapTenorStructure.EUR,
                    analyticModel2,
                    "3",
                    "Index Old Tenor",
                    "Index New Tenor")
                .convertTenor());
    verify(analyticModel2).getDiscountCurve("EUR_3");
    verify(analyticModel2, atLeast(1)).getForwardCurve("Forward_EUR_Index New Tenor");
  }

  /**
   * Test {@link TenorConverter#convertTenor()}.
   *
   * <p>Method under test: {@link TenorConverter#convertTenor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] TenorConverter.convertTenor()"})
  public void testConvertTenor2() throws CalculationException {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModel2 = mock(AnalyticModelFromCurvesAndVols.class);
    when(analyticModel2.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(analyticModel2.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);
    double[][] capletVolatilities = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    TenorConverter tenorConverter =
        new TenorConverter(
            mock(CorrelationProvider.class),
            Integer.MIN_VALUE,
            1,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            capletVolatilities,
            CapTenorStructure.EUR,
            analyticModel2,
            "3",
            "Index Old Tenor",
            "Index New Tenor");

    // Act and Assert
    assertThrows(CalculationException.class, () -> tenorConverter.convertTenor());
    verify(analyticModel2).getDiscountCurve("EUR_3");
    verify(analyticModel2, atLeast(1)).getForwardCurve("Forward_EUR_Index New Tenor");
  }

  /**
   * Test {@link TenorConverter#convertTenor()}.
   *
   * <p>Method under test: {@link TenorConverter#convertTenor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] TenorConverter.convertTenor()"})
  public void testConvertTenor3() throws CalculationException {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModel2 = mock(AnalyticModelFromCurvesAndVols.class);
    when(analyticModel2.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(analyticModel2.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);
    double[][] capletVolatilities = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    // Act and Assert
    assertThrows(
        CalculationException.class,
        () ->
            new TenorConverter(
                    mock(CorrelationProvider.class),
                    1,
                    Integer.MIN_VALUE,
                    new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                    new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                    capletVolatilities,
                    CapTenorStructure.EUR,
                    analyticModel2,
                    "3",
                    "Index Old Tenor",
                    "Index New Tenor")
                .convertTenor());
    verify(analyticModel2).getDiscountCurve("EUR_3");
    verify(analyticModel2, atLeast(1)).getForwardCurve("Forward_EUR_Index New Tenor");
  }

  /**
   * Test {@link TenorConverter#convertTenor()}.
   *
   * <p>Method under test: {@link TenorConverter#convertTenor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] TenorConverter.convertTenor()"})
  public void testConvertTenor4() throws CalculationException {
    // Arrange
    CorrelationProvider correlationProvider = mock(CorrelationProvider.class);
    when(correlationProvider.getCorrelation(
            anyInt(),
            anyDouble(),
            anyDouble(),
            Mockito.<AnalyticModel>any(),
            Mockito.<String>any()))
        .thenReturn(10.0d);

    AnalyticModelFromCurvesAndVols analyticModel2 = mock(AnalyticModelFromCurvesAndVols.class);
    when(analyticModel2.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    ForwardCurveInterpolation createForwardCurveFromDiscountFactorsResult =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "old and new tenor collide.",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    when(analyticModel2.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromDiscountFactorsResult);

    // Act
    double[][] actualConvertTenorResult =
        new TenorConverter(
                correlationProvider,
                2,
                1,
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[][] {
                  new double[] {10.0d, 1.0d, 10.0d, 1.0d}, new double[] {10.0d, 1.0d, 10.0d, 1.0d}
                },
                CapTenorStructure.EUR,
                analyticModel2,
                "3",
                "Index Old Tenor",
                "Index New Tenor")
            .convertTenor();

    // Assert
    verify(analyticModel2).getDiscountCurve("EUR_3");
    verify(analyticModel2, atLeast(1)).getForwardCurve("Forward_EUR_Index New Tenor");
    verify(correlationProvider, atLeast(1))
        .getCorrelation(eq(1), anyDouble(), anyDouble(), isA(AnalyticModel.class), eq("3"));
    assertEquals(9, actualConvertTenorResult.length);
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualConvertTenorResult[1], 0.0);
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualConvertTenorResult[3], 0.0);
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualConvertTenorResult[5], 0.0);
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualConvertTenorResult[7], 0.0);
    assertArrayEquals(
        new double[] {
          0.15789024243839292d, 1.5789024243839291d, 0.15789024243839292d, 1.5789024243839291d
        },
        actualConvertTenorResult[4],
        0.0);
    assertArrayEquals(
        new double[] {
          0.15789024243839292d, 1.5789024243839291d, 0.15789024243839292d, 1.5789024243839291d
        },
        actualConvertTenorResult[8],
        0.0);
    assertArrayEquals(
        new double[] {
          0.15811388300841897d, 1.5811388300841895d, 0.15811388300841897d, 1.5811388300841895d
        },
        actualConvertTenorResult[0],
        0.0);
    assertArrayEquals(
        new double[] {
          0.15811388300841897d, 1.5811388300841895d, 0.15811388300841897d, 1.5811388300841895d
        },
        actualConvertTenorResult[2],
        0.0);
    assertArrayEquals(
        new double[] {
          0.15811388300841897d, 1.5811388300841895d, 0.15811388300841897d, 1.5811388300841895d
        },
        actualConvertTenorResult[6],
        0.0);
  }

  /**
   * Test {@link TenorConverter#convertTenor()}.
   *
   * <p>Method under test: {@link TenorConverter#convertTenor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] TenorConverter.convertTenor()"})
  public void testConvertTenor5() throws CalculationException {
    // Arrange
    CorrelationProvider correlationProvider = mock(CorrelationProvider.class);
    when(correlationProvider.getCorrelation(
            anyInt(),
            anyDouble(),
            anyDouble(),
            Mockito.<AnalyticModel>any(),
            Mockito.<String>any()))
        .thenReturn(10.0d);

    AnalyticModelFromCurvesAndVols analyticModel2 = mock(AnalyticModelFromCurvesAndVols.class);
    when(analyticModel2.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    ForwardCurveInterpolation createForwardCurveFromDiscountFactorsResult =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "old and new tenor collide.",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    when(analyticModel2.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromDiscountFactorsResult);
    double[][] capletVolatilities = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    // Act
    double[][] actualConvertTenorResult =
        new TenorConverter(
                correlationProvider,
                2,
                1,
                new double[] {Double.NaN, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                capletVolatilities,
                CapTenorStructure.EUR,
                analyticModel2,
                "3",
                "Index Old Tenor",
                "Index New Tenor")
            .convertTenor();

    // Assert
    verify(analyticModel2).getDiscountCurve("EUR_3");
    verify(analyticModel2, atLeast(1)).getForwardCurve("Forward_EUR_Index New Tenor");
    verify(correlationProvider, atLeast(1))
        .getCorrelation(eq(1), anyDouble(), anyDouble(), isA(AnalyticModel.class), eq("3"));
    assertEquals(9, actualConvertTenorResult.length);
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualConvertTenorResult[1], 0.0);
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualConvertTenorResult[3], 0.0);
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualConvertTenorResult[5], 0.0);
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualConvertTenorResult[7], 0.0);
    assertArrayEquals(
        new double[] {
          0.15811388300841897d, 1.5811388300841895d, 0.15811388300841897d, 1.5811388300841895d
        },
        actualConvertTenorResult[0],
        0.0);
    assertArrayEquals(
        new double[] {
          0.15811388300841897d, 1.5811388300841895d, 0.15811388300841897d, 1.5811388300841895d
        },
        actualConvertTenorResult[2],
        0.0);
    assertArrayEquals(
        new double[] {
          0.15811388300841897d, 1.5811388300841895d, 0.15811388300841897d, 1.5811388300841895d
        },
        actualConvertTenorResult[6],
        0.0);
    assertArrayEquals(
        new double[] {
          1.5789024243839291d, 1.5789024243839291d, 1.5789024243839291d, 1.5789024243839291d
        },
        actualConvertTenorResult[4],
        0.0);
    assertArrayEquals(
        new double[] {
          1.5789024243839291d, 1.5789024243839291d, 1.5789024243839291d, 1.5789024243839291d
        },
        actualConvertTenorResult[8],
        0.0);
  }

  /**
   * Test {@link TenorConverter#convertTenor()}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link TenorConverter#convertTenor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] TenorConverter.convertTenor()"})
  public void testConvertTenor_thenReturnArrayLengthIsZero() throws CalculationException {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModel2 = mock(AnalyticModelFromCurvesAndVols.class);
    when(analyticModel2.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(analyticModel2.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);
    double[][] capletVolatilities = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    // Act
    double[][] actualConvertTenorResult =
        new TenorConverter(
                mock(CorrelationProvider.class),
                2,
                6,
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                capletVolatilities,
                CapTenorStructure.EUR,
                analyticModel2,
                "3",
                "Index Old Tenor",
                "Index New Tenor")
            .convertTenor();

    // Assert
    verify(analyticModel2).getDiscountCurve("EUR_3");
    verify(analyticModel2, atLeast(1)).getForwardCurve("Forward_EUR_Index New Tenor");
    assertEquals(0, actualConvertTenorResult.length);
  }

  /**
   * Test {@link TenorConverter#convertTenor()}.
   *
   * <ul>
   *   <li>Then return first element is empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link TenorConverter#convertTenor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] TenorConverter.convertTenor()"})
  public void testConvertTenor_thenReturnFirstElementIsEmptyArrayOfDouble()
      throws CalculationException {
    // Arrange
    AnalyticModelFromCurvesAndVols analyticModel2 = mock(AnalyticModelFromCurvesAndVols.class);
    when(analyticModel2.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    ForwardCurveFromDiscountCurve forwardCurveFromDiscountCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    when(analyticModel2.getForwardCurve(Mockito.<String>any()))
        .thenReturn(forwardCurveFromDiscountCurve);
    double[][] capletVolatilities = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    // Act
    double[][] actualConvertTenorResult =
        new TenorConverter(
                mock(CorrelationProvider.class),
                2,
                1,
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {},
                capletVolatilities,
                CapTenorStructure.EUR,
                analyticModel2,
                "3",
                "Index Old Tenor",
                "Index New Tenor")
            .convertTenor();

    // Assert
    verify(analyticModel2).getDiscountCurve("EUR_3");
    verify(analyticModel2, atLeast(1)).getForwardCurve("Forward_EUR_Index New Tenor");
    assertEquals(9, actualConvertTenorResult.length);
    assertArrayEquals(new double[] {}, actualConvertTenorResult[0], 0.0);
    assertArrayEquals(new double[] {}, actualConvertTenorResult[1], 0.0);
    assertArrayEquals(new double[] {}, actualConvertTenorResult[2], 0.0);
    assertArrayEquals(new double[] {}, actualConvertTenorResult[3], 0.0);
    assertArrayEquals(new double[] {}, actualConvertTenorResult[4], 0.0);
    assertArrayEquals(new double[] {}, actualConvertTenorResult[5], 0.0);
    assertArrayEquals(new double[] {}, actualConvertTenorResult[6], 0.0);
    assertArrayEquals(new double[] {}, actualConvertTenorResult[7], 0.0);
    assertArrayEquals(new double[] {}, actualConvertTenorResult[8], 0.0);
  }

  /**
   * Test {@link TenorConverter#convertTenor()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link TenorConverter#convertTenor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] TenorConverter.convertTenor()"})
  public void testConvertTenor_thenThrowIllegalArgumentException() throws CalculationException {
    // Arrange
    CorrelationProvider correlationProvider = mock(CorrelationProvider.class);
    when(correlationProvider.getCorrelation(
            anyInt(),
            anyDouble(),
            anyDouble(),
            Mockito.<AnalyticModel>any(),
            Mockito.<String>any()))
        .thenThrow(new IllegalArgumentException());

    AnalyticModelFromCurvesAndVols analyticModel2 = mock(AnalyticModelFromCurvesAndVols.class);
    when(analyticModel2.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    ForwardCurveInterpolation createForwardCurveFromDiscountFactorsResult =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "old and new tenor collide.",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    when(analyticModel2.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromDiscountFactorsResult);
    double[][] capletVolatilities = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new TenorConverter(
                    correlationProvider,
                    2,
                    1,
                    new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                    new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                    capletVolatilities,
                    CapTenorStructure.EUR,
                    analyticModel2,
                    "3",
                    "Index Old Tenor",
                    "Index New Tenor")
                .convertTenor());
    verify(analyticModel2).getDiscountCurve("EUR_3");
    verify(analyticModel2, atLeast(1)).getForwardCurve("Forward_EUR_Index New Tenor");
    verify(correlationProvider)
        .getCorrelation(eq(1), eq(10.0d), eq(10.0d), isA(AnalyticModel.class), eq("3"));
  }

  /**
   * Test {@link TenorConverter#convertTenor()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link TenorConverter#convertTenor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] TenorConverter.convertTenor()"})
  public void testConvertTenor_thenThrowIllegalArgumentException2() throws CalculationException {
    // Arrange
    CorrelationProvider correlationProvider = mock(CorrelationProvider.class);
    when(correlationProvider.getCorrelation(
            anyInt(),
            anyDouble(),
            anyDouble(),
            Mockito.<AnalyticModel>any(),
            Mockito.<String>any()))
        .thenThrow(new IllegalArgumentException());

    AnalyticModelFromCurvesAndVols analyticModel2 = mock(AnalyticModelFromCurvesAndVols.class);
    when(analyticModel2.getDiscountCurve(Mockito.<String>any()))
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    ForwardCurveInterpolation createForwardCurveFromDiscountFactorsResult =
        ForwardCurveInterpolation.createForwardCurveFromDiscountFactors(
            "old and new tenor collide.",
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            10.0d);
    when(analyticModel2.getForwardCurve(Mockito.<String>any()))
        .thenReturn(createForwardCurveFromDiscountFactorsResult);
    double[][] capletVolatilities = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new TenorConverter(
                    correlationProvider,
                    2,
                    4,
                    new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                    new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                    capletVolatilities,
                    CapTenorStructure.EUR,
                    analyticModel2,
                    "3",
                    "Index Old Tenor",
                    "Index New Tenor")
                .convertTenor());
    verify(analyticModel2).getDiscountCurve("EUR_3");
    verify(analyticModel2, atLeast(1)).getForwardCurve("Forward_EUR_Index New Tenor");
    verify(correlationProvider)
        .getCorrelation(eq(2), eq(1.0d), eq(1.0d), isA(AnalyticModel.class), eq("3"));
  }
}
