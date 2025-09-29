package net.finmath.time;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TenorFromArrayDiffblueTest {
  /**
   * Test {@link TenorFromArray#TenorFromArray(LocalDate[], LocalDate)}.
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(LocalDate[], LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(LocalDate[], LocalDate)"})
  public void testNewTenorFromArray() {
    // Arrange and Act
    TenorFromArray actualTenorFromArray =
        new TenorFromArray(
            new LocalDate[] {LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1)},
            LocalDate.of(1970, 1, 1));

    // Assert
    assertEquals(0, actualTenorFromArray.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualTenorFromArray.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(0.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTenorFromArray.iterator();
    assertEquals(0.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.0d, actualTenorFromArray.getFirstTime(), 0.0);
    assertEquals(0.0d, actualTenorFromArray.getLastTime(), 0.0);
    assertEquals(1, actualTenorFromArray.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {0.0d}, actualTenorFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TenorFromArray#TenorFromArray(double[])}.
   *
   * <ul>
   *   <li>Then return AsArrayList size is two.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(double[])"})
  public void testNewTenorFromArray_thenReturnAsArrayListSizeIsTwo() {
    // Arrange and Act
    TenorFromArray actualTenorFromArray =
        new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    ArrayList<Double> asArrayList = actualTenorFromArray.getAsArrayList();
    assertEquals(2, asArrayList.size());
    assertEquals(0.5d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTenorFromArray.iterator();
    assertEquals(0.5d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.5d, actualTenorFromArray.getFirstTime(), 0.0);
    assertEquals(1, actualTenorFromArray.getNumberOfTimeSteps());
    assertEquals(10.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTenorFromArray.getLastTime(), 0.0);
    assertEquals(2, actualTenorFromArray.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {0.5d, 10.0d}, actualTenorFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TenorFromArray#TenorFromArray(Double[])}.
   *
   * <ul>
   *   <li>Then return AsArrayList size is two.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(Double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(Double[])"})
  public void testNewTenorFromArray_thenReturnAsArrayListSizeIsTwo2() {
    // Arrange and Act
    TenorFromArray actualTenorFromArray = new TenorFromArray(new Double[] {10.0d, 0.5d});

    // Assert
    ArrayList<Double> asArrayList = actualTenorFromArray.getAsArrayList();
    assertEquals(2, asArrayList.size());
    assertEquals(0.5d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTenorFromArray.iterator();
    assertEquals(0.5d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.5d, actualTenorFromArray.getFirstTime(), 0.0);
    assertEquals(1, actualTenorFromArray.getNumberOfTimeSteps());
    assertEquals(10.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(2, actualTenorFromArray.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {0.5d, 10.0d}, actualTenorFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TenorFromArray#TenorFromArray(double, double, double, ShortPeriodLocation)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(double, double, double,
   * ShortPeriodLocation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(double, double, double, ShortPeriodLocation)"})
  public void testNewTenorFromArray_thenReturnNumberOfTimeStepsIsMinusOne() {
    // Arrange and Act
    TenorFromArray actualTenorFromArray =
        new TenorFromArray(10.0d, 0.5d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);

    // Assert
    assertEquals(-1, actualTenorFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTenorFromArray.getNumberOfTimes());
    assertFalse(actualTenorFromArray.iterator().hasNext());
    assertTrue(actualTenorFromArray.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualTenorFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TenorFromArray#TenorFromArray(double[])}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(double[])"})
  public void testNewTenorFromArray_thenReturnNumberOfTimeStepsIsMinusOne2() {
    // Arrange and Act
    TenorFromArray actualTenorFromArray = new TenorFromArray(new double[] {});

    // Assert
    assertEquals(-1, actualTenorFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTenorFromArray.getNumberOfTimes());
    assertFalse(actualTenorFromArray.iterator().hasNext());
    assertTrue(actualTenorFromArray.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualTenorFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TenorFromArray#TenorFromArray(Double[])}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(Double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(Double[])"})
  public void testNewTenorFromArray_thenReturnNumberOfTimeStepsIsMinusOne3() {
    // Arrange and Act
    TenorFromArray actualTenorFromArray = new TenorFromArray(new Double[] {});

    // Assert
    assertEquals(-1, actualTenorFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTenorFromArray.getNumberOfTimes());
    assertFalse(actualTenorFromArray.iterator().hasNext());
    assertTrue(actualTenorFromArray.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualTenorFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TenorFromArray#TenorFromArray(LocalDate[], LocalDate)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(LocalDate[], LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(LocalDate[], LocalDate)"})
  public void testNewTenorFromArray_thenReturnNumberOfTimeStepsIsMinusOne4() {
    // Arrange and Act
    TenorFromArray actualTenorFromArray =
        new TenorFromArray(new LocalDate[] {}, LocalDate.of(1970, 1, 1));

    // Assert
    assertEquals(-1, actualTenorFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTenorFromArray.getNumberOfTimes());
    assertFalse(actualTenorFromArray.iterator().hasNext());
    assertTrue(actualTenorFromArray.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualTenorFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TenorFromArray#TenorFromArray(double, int, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return NumberOfTimeSteps is ten.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(double, int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(double, int, double)"})
  public void testNewTenorFromArray_when05_thenReturnNumberOfTimeStepsIsTen() {
    // Arrange and Act
    TenorFromArray actualTenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);

    // Assert
    assertEquals(10, actualTenorFromArray.getNumberOfTimeSteps());
    assertEquals(10.0d, actualTenorFromArray.getFirstTime(), 0.0);
    assertEquals(11, actualTenorFromArray.getAsArrayList().size());
    assertEquals(11, actualTenorFromArray.getNumberOfTimes());
    assertEquals(15.0d, actualTenorFromArray.getLastTime(), 0.0);
    assertTrue(actualTenorFromArray.iterator().hasNext());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        actualTenorFromArray.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link TenorFromArray#TenorFromArray(Double[])}.
   *
   * <ul>
   *   <li>When array of {@link Double} with ten.
   *   <li>Then return NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(Double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(Double[])"})
  public void testNewTenorFromArray_whenArrayOfDoubleWithTen_thenReturnNumberOfTimeStepsIsZero() {
    // Arrange
    Double[] timeDiscretization = new Double[] {10.0d};

    // Act
    TenorFromArray actualTenorFromArray = new TenorFromArray(timeDiscretization);

    // Assert
    assertEquals(0, actualTenorFromArray.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualTenorFromArray.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(1, actualTenorFromArray.getNumberOfTimes());
    assertEquals(10.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTenorFromArray.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTenorFromArray.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {10.0d}, actualTenorFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TenorFromArray#TenorFromArray(LocalDate[], LocalDate)}.
   *
   * <ul>
   *   <li>When array of {@link LocalDate} with {@link LocalDate} with {@code 1970} and one and one.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(LocalDate[], LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(LocalDate[], LocalDate)"})
  public void testNewTenorFromArray_whenArrayOfLocalDateWithLocalDateWith1970AndOneAndOne() {
    // Arrange
    LocalDate[] dates = new LocalDate[] {LocalDate.of(1970, 1, 1)};

    // Act
    TenorFromArray actualTenorFromArray = new TenorFromArray(dates, LocalDate.of(1970, 1, 1));

    // Assert
    assertEquals(0, actualTenorFromArray.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualTenorFromArray.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(0.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTenorFromArray.iterator();
    assertEquals(0.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.0d, actualTenorFromArray.getFirstTime(), 0.0);
    assertEquals(0.0d, actualTenorFromArray.getLastTime(), 0.0);
    assertEquals(1, actualTenorFromArray.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {0.0d}, actualTenorFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TenorFromArray#TenorFromArray(double, int, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(double, int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(double, int, double)"})
  public void testNewTenorFromArray_whenMinusOne_thenReturnNumberOfTimeStepsIsMinusOne() {
    // Arrange and Act
    TenorFromArray actualTenorFromArray = new TenorFromArray(10.0d, -1, 0.5d);

    // Assert
    assertEquals(-1, actualTenorFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTenorFromArray.getNumberOfTimes());
    assertFalse(actualTenorFromArray.iterator().hasNext());
    assertTrue(actualTenorFromArray.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualTenorFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TenorFromArray#TenorFromArray(double, double, double, ShortPeriodLocation)}.
   *
   * <ul>
   *   <li>When {@code SHORT_PERIOD_AT_END}.
   *   <li>Then return AsArrayList size is twenty.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(double, double, double,
   * ShortPeriodLocation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(double, double, double, ShortPeriodLocation)"})
  public void testNewTenorFromArray_whenShortPeriodAtEnd_thenReturnAsArrayListSizeIsTwenty() {
    // Arrange and Act
    TenorFromArray actualTenorFromArray =
        new TenorFromArray(0.5d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_END);

    // Assert
    ArrayList<Double> asArrayList = actualTenorFromArray.getAsArrayList();
    assertEquals(20, asArrayList.size());
    assertEquals(0.5d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTenorFromArray.iterator();
    assertEquals(0.5d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.5d, actualTenorFromArray.getFirstTime(), 0.0);
    assertEquals(1.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertEquals(1.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(1.5d, asArrayList.get(2).doubleValue(), 0.0);
    assertEquals(1.5d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, asArrayList.get(19).doubleValue(), 0.0);
    assertEquals(19, actualTenorFromArray.getNumberOfTimeSteps());
    assertEquals(2.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(2.5d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(20, actualTenorFromArray.getNumberOfTimes());
    assertEquals(9.0d, asArrayList.get(17).doubleValue(), 0.0);
    assertEquals(9.5d, asArrayList.get(18).doubleValue(), 0.0);
    assertTrue(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {
          0.5d, 1.0d, 1.5d, 2.0d, 2.5d, 3.0d, 3.5d, 4.0d, 4.5d, 5.0d, 5.5d, 6.0d, 6.5d, 7.0d, 7.5d,
          8.0d, 8.5d, 9.0d, 9.5d, 10.0d
        },
        actualTenorFromArray.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link TenorFromArray#TenorFromArray(double, double, double, ShortPeriodLocation)}.
   *
   * <ul>
   *   <li>When {@code SHORT_PERIOD_AT_START}.
   *   <li>Then return AsArrayList size is twenty.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(double, double, double,
   * ShortPeriodLocation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(double, double, double, ShortPeriodLocation)"})
  public void testNewTenorFromArray_whenShortPeriodAtStart_thenReturnAsArrayListSizeIsTwenty() {
    // Arrange and Act
    TenorFromArray actualTenorFromArray =
        new TenorFromArray(0.5d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);

    // Assert
    ArrayList<Double> asArrayList = actualTenorFromArray.getAsArrayList();
    assertEquals(20, asArrayList.size());
    assertEquals(0.5d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTenorFromArray.iterator();
    assertEquals(0.5d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.5d, actualTenorFromArray.getFirstTime(), 0.0);
    assertEquals(1.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertEquals(1.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(1.5d, asArrayList.get(2).doubleValue(), 0.0);
    assertEquals(1.5d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, asArrayList.get(19).doubleValue(), 0.0);
    assertEquals(19, actualTenorFromArray.getNumberOfTimeSteps());
    assertEquals(2.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(2.5d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(20, actualTenorFromArray.getNumberOfTimes());
    assertEquals(9.0d, asArrayList.get(17).doubleValue(), 0.0);
    assertEquals(9.5d, asArrayList.get(18).doubleValue(), 0.0);
    assertTrue(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {
          0.5d, 1.0d, 1.5d, 2.0d, 2.5d, 3.0d, 3.5d, 4.0d, 4.5d, 5.0d, 5.5d, 6.0d, 6.5d, 7.0d, 7.5d,
          8.0d, 8.5d, 9.0d, 9.5d, 10.0d
        },
        actualTenorFromArray.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link TenorFromArray#TenorFromArray(double, double, double, ShortPeriodLocation)}.
   *
   * <ul>
   *   <li>When {@code SHORT_PERIOD_AT_START}.
   *   <li>Then return NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(double, double, double,
   * ShortPeriodLocation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(double, double, double, ShortPeriodLocation)"})
  public void testNewTenorFromArray_whenShortPeriodAtStart_thenReturnNumberOfTimeStepsIsZero() {
    // Arrange and Act
    TenorFromArray actualTenorFromArray =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);

    // Assert
    assertEquals(0, actualTenorFromArray.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualTenorFromArray.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(1, actualTenorFromArray.getNumberOfTimes());
    assertEquals(10.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTenorFromArray.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTenorFromArray.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {10.0d}, actualTenorFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TenorFromArray#TenorFromArray(double, double, double, ShortPeriodLocation)}.
   *
   * <ul>
   *   <li>When {@link ShortPeriodLocation#SHORT_PERIOD_AT_END}.
   *   <li>Then return NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#TenorFromArray(double, double, double,
   * ShortPeriodLocation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TenorFromArray.<init>(double, double, double, ShortPeriodLocation)"})
  public void testNewTenorFromArray_whenShort_period_at_end_thenReturnNumberOfTimeStepsIsZero() {
    // Arrange and Act
    TenorFromArray actualTenorFromArray =
        new TenorFromArray(10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_END);

    // Assert
    assertEquals(0, actualTenorFromArray.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualTenorFromArray.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(1, actualTenorFromArray.getNumberOfTimes());
    assertEquals(10.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTenorFromArray.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTenorFromArray.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {10.0d}, actualTenorFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TenorFromArray#getReferenceDate()}.
   *
   * <p>Method under test: {@link TenorFromArray#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate TenorFromArray.getReferenceDate()"})
  public void testGetReferenceDate() {
    // Arrange, Act and Assert
    assertNull(new TenorFromArray(10.0d, 10, 0.5d).getReferenceDate());
  }

  /**
   * Test {@link TenorFromArray#getDaycountFraction(int)}.
   *
   * <ul>
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link TenorFromArray#getDaycountFraction(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TenorFromArray.getDaycountFraction(int)"})
  public void testGetDaycountFraction_thenReturn05() {
    // Arrange, Act and Assert
    assertEquals(0.5d, new TenorFromArray(10.0d, 10, 0.5d).getDaycountFraction(1), 0.0);
  }
}
