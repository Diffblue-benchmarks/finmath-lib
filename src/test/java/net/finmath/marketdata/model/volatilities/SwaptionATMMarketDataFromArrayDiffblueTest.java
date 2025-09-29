package net.finmath.marketdata.model.volatilities;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurve;
import net.finmath.marketdata.model.curves.ForwardCurveFromDiscountCurve;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SwaptionATMMarketDataFromArrayDiffblueTest {
  /**
   * Test {@link SwaptionATMMarketDataFromArray#SwaptionATMMarketDataFromArray(ForwardCurve,
   * DiscountCurve, TimeDiscretization, TimeDiscretization, double, double[][])}.
   *
   * <p>Method under test: {@link
   * SwaptionATMMarketDataFromArray#SwaptionATMMarketDataFromArray(ForwardCurve, DiscountCurve,
   * TimeDiscretization, TimeDiscretization, double, double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionATMMarketDataFromArray.<init>(ForwardCurve, DiscountCurve, TimeDiscretization, TimeDiscretization, double, double[][])"
  })
  public void testNewSwaptionATMMarketDataFromArray() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    DiscountCurveFromForwardCurve discountCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    TenorFromArray optionMatruities = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray tenor = new TenorFromArray(10.0d, 10, 0.5d);
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    // Act
    SwaptionATMMarketDataFromArray actualSwaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            forwardCurve, discountCurve, optionMatruities, tenor, 10.0d, impliedVolatilities);

    // Assert
    assertEquals(10.0d, actualSwaptionATMMarketDataFromArray.getSwapPeriodLength(), 0.0);
    assertSame(optionMatruities, actualSwaptionATMMarketDataFromArray.getOptionMaturities());
    assertSame(tenor, actualSwaptionATMMarketDataFromArray.getTenor());
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#SwaptionATMMarketDataFromArray(ForwardCurve,
   * DiscountCurve, double[], double[], double, double[][])}.
   *
   * <p>Method under test: {@link
   * SwaptionATMMarketDataFromArray#SwaptionATMMarketDataFromArray(ForwardCurve, DiscountCurve,
   * double[], double[], double, double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionATMMarketDataFromArray.<init>(ForwardCurve, DiscountCurve, double[], double[], double, double[][])"
  })
  public void testNewSwaptionATMMarketDataFromArray2() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    // Act
    SwaptionATMMarketDataFromArray actualSwaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            forwardCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            new double[] {},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Assert
    TimeDiscretization optionMaturities =
        actualSwaptionATMMarketDataFromArray.getOptionMaturities();
    assertTrue(optionMaturities instanceof TimeDiscretizationFromArray);
    TimeDiscretization tenor = actualSwaptionATMMarketDataFromArray.getTenor();
    assertTrue(tenor instanceof TimeDiscretizationFromArray);
    assertEquals(-1, optionMaturities.getNumberOfTimeSteps());
    assertEquals(0, optionMaturities.getNumberOfTimes());
    ArrayList<Double> asArrayList = tenor.getAsArrayList();
    assertEquals(2, asArrayList.size());
    assertEquals(0.5d, asArrayList.get(0).doubleValue(), 0.0);
    assertEquals(10.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertFalse(optionMaturities.iterator().hasNext());
    assertTrue(optionMaturities.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, optionMaturities.getAsDoubleArray(), 0.0);
    assertArrayEquals(new double[] {0.5d, 10.0d}, tenor.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#SwaptionATMMarketDataFromArray(double[], double[],
   * double, double[][])}.
   *
   * <p>Method under test: {@link
   * SwaptionATMMarketDataFromArray#SwaptionATMMarketDataFromArray(double[], double[], double,
   * double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwaptionATMMarketDataFromArray.<init>(double[], double[], double, double[][])"
  })
  public void testNewSwaptionATMMarketDataFromArray3() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};

    // Act
    SwaptionATMMarketDataFromArray actualSwaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {}, new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 10.0d, impliedVolatilities);

    // Assert
    TimeDiscretization optionMaturities =
        actualSwaptionATMMarketDataFromArray.getOptionMaturities();
    assertTrue(optionMaturities instanceof TimeDiscretizationFromArray);
    TimeDiscretization tenor = actualSwaptionATMMarketDataFromArray.getTenor();
    assertTrue(tenor instanceof TimeDiscretizationFromArray);
    assertEquals(-1, optionMaturities.getNumberOfTimeSteps());
    assertEquals(0, optionMaturities.getNumberOfTimes());
    ArrayList<Double> asArrayList = tenor.getAsArrayList();
    assertEquals(2, asArrayList.size());
    assertEquals(0.5d, asArrayList.get(0).doubleValue(), 0.0);
    assertEquals(10.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertFalse(optionMaturities.iterator().hasNext());
    assertTrue(optionMaturities.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, optionMaturities.getAsDoubleArray(), 0.0);
    assertArrayEquals(new double[] {0.5d, 10.0d}, tenor.getAsDoubleArray(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SwaptionATMMarketDataFromArray#getOptionMaturities()}
   *   <li>{@link SwaptionATMMarketDataFromArray#getSwapPeriodLength()}
   *   <li>{@link SwaptionATMMarketDataFromArray#getTenor()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization SwaptionATMMarketDataFromArray.getOptionMaturities()",
    "double SwaptionATMMarketDataFromArray.getSwapPeriodLength()",
    "TimeDiscretization SwaptionATMMarketDataFromArray.getTenor()"
  })
  public void testGettersAndSetters() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act
    TimeDiscretization actualOptionMaturities =
        swaptionATMMarketDataFromArray.getOptionMaturities();
    double actualSwapPeriodLength = swaptionATMMarketDataFromArray.getSwapPeriodLength();
    TimeDiscretization actualTenor = swaptionATMMarketDataFromArray.getTenor();

    // Assert
    assertTrue(actualOptionMaturities instanceof TimeDiscretizationFromArray);
    assertTrue(actualTenor instanceof TimeDiscretizationFromArray);
    assertEquals(10.0d, actualSwapPeriodLength, 0.0);
    assertEquals(actualOptionMaturities, actualTenor);
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getValue(double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getValue(double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SwaptionATMMarketDataFromArray.getValue(double, double, double, double)"
  })
  public void testGetValue_when05() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> swaptionATMMarketDataFromArray.getValue(0.5d, 10.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getValue(double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getValue(double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SwaptionATMMarketDataFromArray.getValue(double, double, double, double)"
  })
  public void testGetValue_when052() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> swaptionATMMarketDataFromArray.getValue(-0.5d, 10.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getValue(double, double, double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getValue(double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SwaptionATMMarketDataFromArray.getValue(double, double, double, double)"
  })
  public void testGetValue_whenNaN() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> swaptionATMMarketDataFromArray.getValue(Double.NaN, 10.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getValue(double, double, double, double)}.
   *
   * <ul>
   *   <li>When ten.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getValue(double, double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SwaptionATMMarketDataFromArray.getValue(double, double, double, double)"
  })
  public void testGetValue_whenTen() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> swaptionATMMarketDataFromArray.getValue(10.0d, 10.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getVolatility(double, double)} with {@code
   * optionMatruity}, {@code tenorLength}.
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionATMMarketDataFromArray.getVolatility(double, double)"})
  public void testGetVolatilityWithOptionMatruityTenorLength() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {}, new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 10.0d, impliedVolatilities);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> swaptionATMMarketDataFromArray.getVolatility(10.0d, 10.0d));
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getVolatility(double, double)} with {@code
   * optionMatruity}, {@code tenorLength}.
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionATMMarketDataFromArray.getVolatility(double, double)"})
  public void testGetVolatilityWithOptionMatruityTenorLength2() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {}, 10.0d, impliedVolatilities);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> swaptionATMMarketDataFromArray.getVolatility(10.0d, 10.0d));
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getVolatility(double, double, double, double)} with
   * {@code optionMatruity}, {@code tenorLength}, {@code periodLength}, {@code strike}.
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getVolatility(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SwaptionATMMarketDataFromArray.getVolatility(double, double, double, double)"
  })
  public void testGetVolatilityWithOptionMatruityTenorLengthPeriodLengthStrike() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {}, new double[] {10.0d, 0.5d, 10.0d, 0.5d}, 10.0d, impliedVolatilities);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> swaptionATMMarketDataFromArray.getVolatility(10.0d, 10.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getVolatility(double, double, double, double)} with
   * {@code optionMatruity}, {@code tenorLength}, {@code periodLength}, {@code strike}.
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getVolatility(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SwaptionATMMarketDataFromArray.getVolatility(double, double, double, double)"
  })
  public void testGetVolatilityWithOptionMatruityTenorLengthPeriodLengthStrike2() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {}, 10.0d, impliedVolatilities);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> swaptionATMMarketDataFromArray.getVolatility(10.0d, 10.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getVolatility(double, double, double, double)} with
   * {@code optionMatruity}, {@code tenorLength}, {@code periodLength}, {@code strike}.
   *
   * <ul>
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getVolatility(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SwaptionATMMarketDataFromArray.getVolatility(double, double, double, double)"
  })
  public void testGetVolatilityWithOptionMatruityTenorLengthPeriodLengthStrike_thenReturn05() {
    // Arrange
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[][] {
              new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d}
            });

    // Act and Assert
    assertEquals(
        0.5d, swaptionATMMarketDataFromArray.getVolatility(10.0d, 10.0d, 10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getVolatility(double, double, double, double)} with
   * {@code optionMatruity}, {@code tenorLength}, {@code periodLength}, {@code strike}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getVolatility(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SwaptionATMMarketDataFromArray.getVolatility(double, double, double, double)"
  })
  public void testGetVolatilityWithOptionMatruityTenorLengthPeriodLengthStrike_when05() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act and Assert
    assertEquals(
        0.5d, swaptionATMMarketDataFromArray.getVolatility(0.5d, 10.0d, 10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getVolatility(double, double, double, double)} with
   * {@code optionMatruity}, {@code tenorLength}, {@code periodLength}, {@code strike}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getVolatility(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SwaptionATMMarketDataFromArray.getVolatility(double, double, double, double)"
  })
  public void testGetVolatilityWithOptionMatruityTenorLengthPeriodLengthStrike_when052() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> swaptionATMMarketDataFromArray.getVolatility(-0.5d, 10.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getVolatility(double, double, double, double)} with
   * {@code optionMatruity}, {@code tenorLength}, {@code periodLength}, {@code strike}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getVolatility(double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SwaptionATMMarketDataFromArray.getVolatility(double, double, double, double)"
  })
  public void testGetVolatilityWithOptionMatruityTenorLengthPeriodLengthStrike_whenNaN() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> swaptionATMMarketDataFromArray.getVolatility(Double.NaN, 10.0d, 10.0d, 10.0d));
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getVolatility(double, double)} with {@code
   * optionMatruity}, {@code tenorLength}.
   *
   * <ul>
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionATMMarketDataFromArray.getVolatility(double, double)"})
  public void testGetVolatilityWithOptionMatruityTenorLength_thenReturn05() {
    // Arrange
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[][] {
              new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d}
            });

    // Act and Assert
    assertEquals(0.5d, swaptionATMMarketDataFromArray.getVolatility(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getVolatility(double, double)} with {@code
   * optionMatruity}, {@code tenorLength}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionATMMarketDataFromArray.getVolatility(double, double)"})
  public void testGetVolatilityWithOptionMatruityTenorLength_when05() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> swaptionATMMarketDataFromArray.getVolatility(-0.5d, 10.0d));
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getVolatility(double, double)} with {@code
   * optionMatruity}, {@code tenorLength}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionATMMarketDataFromArray.getVolatility(double, double)"})
  public void testGetVolatilityWithOptionMatruityTenorLength_when05_thenReturn05() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act and Assert
    assertEquals(0.5d, swaptionATMMarketDataFromArray.getVolatility(0.5d, 10.0d), 0.0);
  }

  /**
   * Test {@link SwaptionATMMarketDataFromArray#getVolatility(double, double)} with {@code
   * optionMatruity}, {@code tenorLength}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link SwaptionATMMarketDataFromArray#getVolatility(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwaptionATMMarketDataFromArray.getVolatility(double, double)"})
  public void testGetVolatilityWithOptionMatruityTenorLength_whenNaN() {
    // Arrange
    double[][] impliedVolatilities = new double[][] {new double[] {10.0d, 0.5d, 10.0d, 0.5d}};
    SwaptionATMMarketDataFromArray swaptionATMMarketDataFromArray =
        new SwaptionATMMarketDataFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            impliedVolatilities);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> swaptionATMMarketDataFromArray.getVolatility(Double.NaN, 10.0d));
  }
}
