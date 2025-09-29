package net.finmath.time;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.DoublePredicate;
import java.util.stream.Stream;
import net.finmath.time.TimeDiscretizationFromArray.ShortPeriodLocation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TimeDiscretizationFromArrayDiffblueTest {
  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable, double)}.
   *
   * <ul>
   *   <li>Given {@code 0.5}.
   *   <li>Then return AsArrayList size is two.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Iterable, double)"})
  public void testNewTimeDiscretizationFromArray_given05_thenReturnAsArrayListSizeIsTwo() {
    // Arrange
    ArrayList<Double> times = new ArrayList<>();
    times.add(0.5d);
    times.add(10.0d);

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times, 10.0d);

    // Assert
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(2, asArrayList.size());
    assertEquals(0.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(0.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(10.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(2, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {0.0d, 10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable, double,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@code 0.5}.
   *   <li>Then return AsArrayList size is two.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Iterable, double, boolean)"})
  public void testNewTimeDiscretizationFromArray_given05_thenReturnAsArrayListSizeIsTwo2() {
    // Arrange
    ArrayList<Double> times = new ArrayList<>();
    times.add(0.5d);
    times.add(10.0d);

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times, 10.0d, true);

    // Assert
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(2, asArrayList.size());
    assertEquals(0.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(0.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(10.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(2, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {0.0d, 10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream)}.
   *
   * <ul>
   *   <li>Given {@code 0.5}.
   *   <li>Then return AsArrayList size is two.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Stream)"})
  public void testNewTimeDiscretizationFromArray_given05_thenReturnAsArrayListSizeIsTwo3() {
    // Arrange
    ArrayList<Double> resultDoubleList = new ArrayList<>();
    resultDoubleList.add(0.5d);
    resultDoubleList.add(10.0d);
    Stream<Double> times = resultDoubleList.stream();

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times);

    // Assert
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(2, asArrayList.size());
    assertEquals(0.5d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(0.5d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.5d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(10.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(2, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {0.5d, 10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream, double)}.
   *
   * <ul>
   *   <li>Given {@code 0.5}.
   *   <li>Then return AsArrayList size is two.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Stream, double)"})
  public void testNewTimeDiscretizationFromArray_given05_thenReturnAsArrayListSizeIsTwo4() {
    // Arrange
    ArrayList<Double> resultDoubleList = new ArrayList<>();
    resultDoubleList.add(0.5d);
    resultDoubleList.add(10.0d);
    Stream<Double> times = resultDoubleList.stream();

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times, 10.0d);

    // Assert
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(2, asArrayList.size());
    assertEquals(0.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(0.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(10.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(2, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {0.0d, 10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream, double, boolean)}.
   *
   * <ul>
   *   <li>Given {@code 0.5}.
   *   <li>Then return AsArrayList size is two.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Stream, double, boolean)"})
  public void testNewTimeDiscretizationFromArray_given05_thenReturnAsArrayListSizeIsTwo5() {
    // Arrange
    ArrayList<Double> resultDoubleList = new ArrayList<>();
    resultDoubleList.add(0.5d);
    resultDoubleList.add(10.0d);
    Stream<Double> times = resultDoubleList.stream();

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times, 10.0d, true);

    // Assert
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(2, asArrayList.size());
    assertEquals(0.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(0.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(10.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(2, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {0.0d, 10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable)}.
   *
   * <ul>
   *   <li>Given {@code 0.5}.
   *   <li>Then return iterator next doubleValue is {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Iterable)"})
  public void testNewTimeDiscretizationFromArray_given05_thenReturnIteratorNextDoubleValueIs05() {
    // Arrange
    ArrayList<Double> times = new ArrayList<>();
    times.add(0.5d);
    times.add(10.0d);

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times);

    // Assert
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(0.5d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.5d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(2, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {0.5d, 10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable, boolean)}.
   *
   * <ul>
   *   <li>Given {@code 0.5}.
   *   <li>Then return iterator next doubleValue is {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Iterable, boolean)"})
  public void testNewTimeDiscretizationFromArray_given05_thenReturnIteratorNextDoubleValueIs052() {
    // Arrange
    ArrayList<Double> times = new ArrayList<>();
    times.add(0.5d);
    times.add(10.0d);

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times, true);

    // Assert
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(0.5d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.5d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(2, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {0.5d, 10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Iterable)"})
  public void testNewTimeDiscretizationFromArray_givenTen_thenReturnNumberOfTimeStepsIsZero() {
    // Arrange
    ArrayList<Double> times = new ArrayList<>();
    times.add(10.0d);

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times);

    // Assert
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimes());
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable, double)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Iterable, double)"})
  public void testNewTimeDiscretizationFromArray_givenTen_thenReturnNumberOfTimeStepsIsZero2() {
    // Arrange
    ArrayList<Double> times = new ArrayList<>();
    times.add(10.0d);

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times, 10.0d);

    // Assert
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimes());
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable, double,
   * boolean)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Iterable, double, boolean)"})
  public void testNewTimeDiscretizationFromArray_givenTen_thenReturnNumberOfTimeStepsIsZero3() {
    // Arrange
    ArrayList<Double> times = new ArrayList<>();
    times.add(10.0d);

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times, 10.0d, true);

    // Assert
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimes());
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable, boolean)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Iterable, boolean)"})
  public void testNewTimeDiscretizationFromArray_givenTen_thenReturnNumberOfTimeStepsIsZero4() {
    // Arrange
    ArrayList<Double> times = new ArrayList<>();
    times.add(10.0d);

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times, true);

    // Assert
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimes());
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Stream)"})
  public void testNewTimeDiscretizationFromArray_givenTen_thenReturnNumberOfTimeStepsIsZero5() {
    // Arrange
    ArrayList<Double> resultDoubleList = new ArrayList<>();
    resultDoubleList.add(10.0d);
    Stream<Double> times = resultDoubleList.stream();

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times);

    // Assert
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertEquals(10.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream, double)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Stream, double)"})
  public void testNewTimeDiscretizationFromArray_givenTen_thenReturnNumberOfTimeStepsIsZero6() {
    // Arrange
    ArrayList<Double> resultDoubleList = new ArrayList<>();
    resultDoubleList.add(10.0d);
    Stream<Double> times = resultDoubleList.stream();

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times, 10.0d);

    // Assert
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertEquals(10.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream, double, boolean)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Stream, double, boolean)"})
  public void testNewTimeDiscretizationFromArray_givenTen_thenReturnNumberOfTimeStepsIsZero7() {
    // Arrange
    ArrayList<Double> resultDoubleList = new ArrayList<>();
    resultDoubleList.add(10.0d);
    Stream<Double> times = resultDoubleList.stream();

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times, 10.0d, true);

    // Assert
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertEquals(10.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Double[], double)}.
   *
   * <ul>
   *   <li>Then return AsArrayList size is two.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Double[],
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Double[], double)"})
  public void testNewTimeDiscretizationFromArray_thenReturnAsArrayListSizeIsTwo() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(new Double[] {10.0d, 1.1415525114155251E-4d}, 10.0d);

    // Assert
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(2, asArrayList.size());
    assertEquals(0.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(0.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(10.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(2, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {0.0d, 10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(double, double, double,
   * ShortPeriodLocation)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(double,
   * double, double, ShortPeriodLocation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TimeDiscretizationFromArray.<init>(double, double, double, ShortPeriodLocation)"
  })
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsMinusOne() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(
            10.0d, 1.1415525114155251E-4d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertTrue(actualTimeDiscretizationFromArray.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(double, int, double)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(double,
   * int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(double, int, double)"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsMinusOne2() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, -1, 0.5d);

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertTrue(actualTimeDiscretizationFromArray.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Iterable)"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsMinusOne3() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(new ArrayList<>());

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable, double)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Iterable, double)"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsMinusOne4() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(new ArrayList<>(), 10.0d);

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable, double,
   * boolean)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Iterable, double, boolean)"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsMinusOne5() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(new ArrayList<>(), 10.0d, true);

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable, boolean)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Iterable, boolean)"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsMinusOne6() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(new ArrayList<>(), true);

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Stream)"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsMinusOne7() {
    // Arrange
    ArrayList<Double> resultDoubleList = new ArrayList<>();
    Stream<Double> times = resultDoubleList.stream();

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times);

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertTrue(actualTimeDiscretizationFromArray.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream, double)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Stream, double)"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsMinusOne8() {
    // Arrange
    ArrayList<Double> resultDoubleList = new ArrayList<>();
    Stream<Double> times = resultDoubleList.stream();

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times, 10.0d);

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertTrue(actualTimeDiscretizationFromArray.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream, double, boolean)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Stream, double, boolean)"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsMinusOne9() {
    // Arrange
    ArrayList<Double> resultDoubleList = new ArrayList<>();
    Stream<Double> times = resultDoubleList.stream();

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times, 10.0d, true);

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertTrue(actualTimeDiscretizationFromArray.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(double[])}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(double[])"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsMinusOne10() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray();

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertTrue(actualTimeDiscretizationFromArray.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Double[])}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Double[])"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsMinusOne11() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(new Double[] {});

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertTrue(actualTimeDiscretizationFromArray.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Double[], double)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Double[],
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Double[], double)"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsMinusOne12() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(new Double[] {}, 10.0d);

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertTrue(actualTimeDiscretizationFromArray.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(double[])}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(double[])"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsOne() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(
            10.0d, 1.1415525114155251E-4d, 10.0d, 1.1415525114155251E-4d);

    // Assert
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(2, asArrayList.size());
    assertEquals(1.1415525114155251E-4d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(1.1415525114155251E-4d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(1.1415525114155251E-4d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertEquals(10.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTimeDiscretizationFromArray.getLastTime(), 0.0);
    assertEquals(2, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {1.1415525114155251E-4d, 10.0d},
        actualTimeDiscretizationFromArray.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Double[])}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Double[])"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsOne2() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(new Double[] {10.0d, 1.1415525114155251E-4d});

    // Assert
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(2, asArrayList.size());
    assertEquals(1.1415525114155251E-4d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(1.1415525114155251E-4d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(1.1415525114155251E-4d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertEquals(10.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(2, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {1.1415525114155251E-4d, 10.0d},
        actualTimeDiscretizationFromArray.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(double, double, double,
   * ShortPeriodLocation)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(double,
   * double, double, ShortPeriodLocation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TimeDiscretizationFromArray.<init>(double, double, double, ShortPeriodLocation)"
  })
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsZero() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(
            10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);

    // Assert
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertEquals(10.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Double[])}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Double[])"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsZero2() {
    // Arrange
    Double[] times = new Double[] {10.0d};

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times);

    // Assert
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertEquals(10.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Double[], double)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Double[],
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Double[], double)"})
  public void testNewTimeDiscretizationFromArray_thenReturnNumberOfTimeStepsIsZero3() {
    // Arrange
    Double[] times = new Double[] {10.0d};

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times, 10.0d);

    // Assert
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertEquals(10.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(double, int, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return NumberOfTimeSteps is ten.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(double,
   * int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(double, int, double)"})
  public void testNewTimeDiscretizationFromArray_when05_thenReturnNumberOfTimeStepsIsTen() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);

    // Assert
    assertEquals(10, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(10.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertEquals(11, actualTimeDiscretizationFromArray.getAsArrayList().size());
    assertEquals(11, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertEquals(15.0d, actualTimeDiscretizationFromArray.getLastTime(), 0.0);
    assertTrue(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        actualTimeDiscretizationFromArray.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable, double,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Iterable, double, boolean)"})
  public void testNewTimeDiscretizationFromArray_whenFalse() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(new ArrayList<>(), 10.0d, false);

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Iterable,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Iterable, boolean)"})
  public void testNewTimeDiscretizationFromArray_whenFalse2() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(new ArrayList<>(), false);

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream, double, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(Stream,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeDiscretizationFromArray.<init>(Stream, double, boolean)"})
  public void testNewTimeDiscretizationFromArray_whenFalse3() {
    // Arrange
    ArrayList<Double> resultDoubleList = new ArrayList<>();
    Stream<Double> times = resultDoubleList.stream();

    // Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(times, 10.0d, false);

    // Assert
    assertEquals(-1, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertFalse(actualTimeDiscretizationFromArray.iterator().hasNext());
    assertTrue(actualTimeDiscretizationFromArray.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(double, double, double,
   * ShortPeriodLocation)}.
   *
   * <ul>
   *   <li>When {@link ShortPeriodLocation#SHORT_PERIOD_AT_END}.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#TimeDiscretizationFromArray(double,
   * double, double, ShortPeriodLocation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TimeDiscretizationFromArray.<init>(double, double, double, ShortPeriodLocation)"
  })
  public void testNewTimeDiscretizationFromArray_whenShort_period_at_end() {
    // Arrange and Act
    TimeDiscretizationFromArray actualTimeDiscretizationFromArray =
        new TimeDiscretizationFromArray(
            10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_END);

    // Assert
    assertEquals(0, actualTimeDiscretizationFromArray.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = actualTimeDiscretizationFromArray.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(1, actualTimeDiscretizationFromArray.getNumberOfTimes());
    assertEquals(10.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = actualTimeDiscretizationFromArray.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, actualTimeDiscretizationFromArray.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(
        new double[] {10.0d}, actualTimeDiscretizationFromArray.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#getNumberOfTimes()}.
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#getNumberOfTimes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TimeDiscretizationFromArray.getNumberOfTimes()"})
  public void testGetNumberOfTimes() {
    // Arrange, Act and Assert
    assertEquals(11, new TimeDiscretizationFromArray(10.0d, 10, 0.5d).getNumberOfTimes());
  }

  /**
   * Test {@link TimeDiscretizationFromArray#getNumberOfTimeSteps()}.
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#getNumberOfTimeSteps()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TimeDiscretizationFromArray.getNumberOfTimeSteps()"})
  public void testGetNumberOfTimeSteps() {
    // Arrange, Act and Assert
    assertEquals(10, new TimeDiscretizationFromArray(10.0d, 10, 0.5d).getNumberOfTimeSteps());
  }

  /**
   * Test {@link TimeDiscretizationFromArray#getTime(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TimeDiscretizationFromArray.getTime(int)"})
  public void testGetTime_thenReturn105() {
    // Arrange, Act and Assert
    assertEquals(10.5d, new TimeDiscretizationFromArray(10.0d, 10, 0.5d).getTime(1), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#getTimeStep(int)}.
   *
   * <ul>
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#getTimeStep(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TimeDiscretizationFromArray.getTimeStep(int)"})
  public void testGetTimeStep_thenReturn05() {
    // Arrange, Act and Assert
    assertEquals(0.5d, new TimeDiscretizationFromArray(10.0d, 10, 0.5d).getTimeStep(1), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#getTimeIndex(double)}.
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#getTimeIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TimeDiscretizationFromArray.getTimeIndex(double)"})
  public void testGetTimeIndex() {
    // Arrange, Act and Assert
    assertEquals(0, new TimeDiscretizationFromArray(10.0d, 10, 0.5d).getTimeIndex(10.0d));
  }

  /**
   * Test {@link TimeDiscretizationFromArray#getTimeIndexNearestLessOrEqual(double)}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * TimeDiscretizationFromArray#getTimeIndexNearestLessOrEqual(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TimeDiscretizationFromArray.getTimeIndexNearestLessOrEqual(double)"})
  public void testGetTimeIndexNearestLessOrEqual_thenReturnTen() {
    // Arrange, Act and Assert
    assertEquals(
        10,
        new TimeDiscretizationFromArray(1.1415525114155251E-4d, 10, 0.5d)
            .getTimeIndexNearestLessOrEqual(10.0d));
  }

  /**
   * Test {@link TimeDiscretizationFromArray#getTimeIndexNearestLessOrEqual(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * TimeDiscretizationFromArray#getTimeIndexNearestLessOrEqual(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TimeDiscretizationFromArray.getTimeIndexNearestLessOrEqual(double)"})
  public void testGetTimeIndexNearestLessOrEqual_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0, new TimeDiscretizationFromArray(10.0d, 10, 0.5d).getTimeIndexNearestLessOrEqual(10.0d));
  }

  /**
   * Test {@link TimeDiscretizationFromArray#getTimeIndexNearestGreaterOrEqual(double)}.
   *
   * <ul>
   *   <li>Then return eleven.
   * </ul>
   *
   * <p>Method under test: {@link
   * TimeDiscretizationFromArray#getTimeIndexNearestGreaterOrEqual(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TimeDiscretizationFromArray.getTimeIndexNearestGreaterOrEqual(double)"})
  public void testGetTimeIndexNearestGreaterOrEqual_thenReturnEleven() {
    // Arrange, Act and Assert
    assertEquals(
        11,
        new TimeDiscretizationFromArray(1.1415525114155251E-4d, 10, 0.5d)
            .getTimeIndexNearestGreaterOrEqual(10.0d));
  }

  /**
   * Test {@link TimeDiscretizationFromArray#getTimeIndexNearestGreaterOrEqual(double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * TimeDiscretizationFromArray#getTimeIndexNearestGreaterOrEqual(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TimeDiscretizationFromArray.getTimeIndexNearestGreaterOrEqual(double)"})
  public void testGetTimeIndexNearestGreaterOrEqual_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0,
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d).getTimeIndexNearestGreaterOrEqual(10.0d));
  }

  /**
   * Test {@link TimeDiscretizationFromArray#getAsDoubleArray()}.
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#getAsDoubleArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] TimeDiscretizationFromArray.getAsDoubleArray()"})
  public void testGetAsDoubleArray() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d).getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#getAsArrayList()}.
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#getAsArrayList()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ArrayList TimeDiscretizationFromArray.getAsArrayList()"})
  public void testGetAsArrayList() {
    // Arrange and Act
    ArrayList<Double> actualAsArrayList =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d).getAsArrayList();

    // Assert
    assertEquals(11, actualAsArrayList.size());
    assertEquals(10.0d, actualAsArrayList.get(0).doubleValue(), 0.0);
    assertEquals(10.5d, actualAsArrayList.get(1).doubleValue(), 0.0);
    assertEquals(11.0d, actualAsArrayList.get(2).doubleValue(), 0.0);
    assertEquals(11.5d, actualAsArrayList.get(3).doubleValue(), 0.0);
    assertEquals(12.0d, actualAsArrayList.get(4).doubleValue(), 0.0);
    assertEquals(12.5d, actualAsArrayList.get(5).doubleValue(), 0.0);
    assertEquals(13.0d, actualAsArrayList.get(6).doubleValue(), 0.0);
    assertEquals(13.5d, actualAsArrayList.get(7).doubleValue(), 0.0);
    assertEquals(14.0d, actualAsArrayList.get(8).doubleValue(), 0.0);
    assertEquals(14.5d, actualAsArrayList.get(9).doubleValue(), 0.0);
    assertEquals(15.0d, actualAsArrayList.get(10).doubleValue(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#getTimeShiftedTimeDiscretization(double)}.
   *
   * <p>Method under test: {@link
   * TimeDiscretizationFromArray#getTimeShiftedTimeDiscretization(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization TimeDiscretizationFromArray.getTimeShiftedTimeDiscretization(double)"
  })
  public void testGetTimeShiftedTimeDiscretization() {
    // Arrange
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, -1, 0.5d);

    // Act
    TimeDiscretization actualTimeShiftedTimeDiscretization =
        timeDiscretizationFromArray.getTimeShiftedTimeDiscretization(10.0d);

    // Assert
    assertTrue(actualTimeShiftedTimeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(timeDiscretizationFromArray, actualTimeShiftedTimeDiscretization);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#getTimeShiftedTimeDiscretization(double)}.
   *
   * <ul>
   *   <li>Then return TickSize is {@code 1.1415525114155251E-4}.
   * </ul>
   *
   * <p>Method under test: {@link
   * TimeDiscretizationFromArray#getTimeShiftedTimeDiscretization(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization TimeDiscretizationFromArray.getTimeShiftedTimeDiscretization(double)"
  })
  public void testGetTimeShiftedTimeDiscretization_thenReturnTickSizeIs11415525114155251e4() {
    // Arrange and Act
    TimeDiscretization actualTimeShiftedTimeDiscretization =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d).getTimeShiftedTimeDiscretization(10.0d);

    // Assert
    assertTrue(actualTimeShiftedTimeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(1.1415525114155251E-4d, actualTimeShiftedTimeDiscretization.getTickSize(), 0.0);
    assertEquals(10, actualTimeShiftedTimeDiscretization.getNumberOfTimeSteps());
    assertEquals(11, actualTimeShiftedTimeDiscretization.getAsArrayList().size());
    assertEquals(11, actualTimeShiftedTimeDiscretization.getNumberOfTimes());
    assertEquals(20.0d, actualTimeShiftedTimeDiscretization.getFirstTime(), 0.0);
    assertEquals(25.0d, actualTimeShiftedTimeDiscretization.getLastTime(), 0.0);
    assertTrue(actualTimeShiftedTimeDiscretization.iterator().hasNext());
    assertArrayEquals(
        new double[] {20.0d, 20.5d, 21.0d, 21.5d, 22.0d, 22.5d, 23.0d, 23.5d, 24.0d, 24.5d, 25.0d},
        actualTimeShiftedTimeDiscretization.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#filter(DoublePredicate)}.
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#filter(DoublePredicate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization TimeDiscretizationFromArray.filter(DoublePredicate)"})
  public void testFilter() {
    // Arrange
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);

    DoublePredicate timesToKeep = mock(DoublePredicate.class);
    when(timesToKeep.test(anyDouble())).thenReturn(true);

    // Act
    TimeDiscretization actualFilterResult = timeDiscretizationFromArray.filter(timesToKeep);

    // Assert
    verify(timesToKeep, atLeast(1)).test(anyDouble());
    assertTrue(actualFilterResult instanceof TimeDiscretizationFromArray);
    assertEquals(timeDiscretizationFromArray, actualFilterResult);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#filter(DoublePredicate)}.
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#filter(DoublePredicate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization TimeDiscretizationFromArray.filter(DoublePredicate)"})
  public void testFilter2() {
    // Arrange
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, -1, 0.5d);

    // Act
    TimeDiscretization actualFilterResult =
        timeDiscretizationFromArray.filter(mock(DoublePredicate.class));

    // Assert
    assertTrue(actualFilterResult instanceof TimeDiscretizationFromArray);
    assertEquals(timeDiscretizationFromArray, actualFilterResult);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#union(TimeDiscretization)}.
   *
   * <ul>
   *   <li>Then return {@link TimeDiscretizationFromArray}.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#union(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization TimeDiscretizationFromArray.union(TimeDiscretization)"})
  public void testUnion_thenReturnTimeDiscretizationFromArray() {
    // Arrange
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);

    // Act
    TimeDiscretization actualUnionResult =
        timeDiscretizationFromArray.union(new TenorFromArray(10.0d, 10, 0.5d));

    // Assert
    assertTrue(actualUnionResult instanceof TimeDiscretizationFromArray);
    assertEquals(timeDiscretizationFromArray, actualUnionResult);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#intersect(TimeDiscretization)}.
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#intersect(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization TimeDiscretizationFromArray.intersect(TimeDiscretization)"
  })
  public void testIntersect() {
    // Arrange
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);

    // Act
    TimeDiscretization actualIntersectResult =
        timeDiscretizationFromArray.intersect(new TenorFromArray(10.0d, 10, 0.5d));

    // Assert
    assertTrue(actualIntersectResult instanceof TimeDiscretizationFromArray);
    assertEquals(timeDiscretizationFromArray, actualIntersectResult);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#intersect(TimeDiscretization)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#intersect(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TimeDiscretization TimeDiscretizationFromArray.intersect(TimeDiscretization)"
  })
  public void testIntersect_thenReturnNumberOfTimeStepsIsMinusOne() {
    // Arrange
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(1.1415525114155251E-4d, 10, 0.5d);

    // Act
    TimeDiscretization actualIntersectResult =
        timeDiscretizationFromArray.intersect(new TenorFromArray(10.0d, 10, 0.5d));

    // Assert
    assertTrue(actualIntersectResult instanceof TimeDiscretizationFromArray);
    assertEquals(-1, actualIntersectResult.getNumberOfTimeSteps());
    assertEquals(0, actualIntersectResult.getNumberOfTimes());
    assertEquals(1.1415525114155251E-4d, actualIntersectResult.getTickSize(), 0.0);
    assertFalse(actualIntersectResult.iterator().hasNext());
    assertTrue(actualIntersectResult.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualIntersectResult.getAsDoubleArray(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TimeDiscretizationFromArray#toString()}
   *   <li>{@link TimeDiscretizationFromArray#getTickSize()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double TimeDiscretizationFromArray.getTickSize()",
    "String TimeDiscretizationFromArray.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);

    // Act
    String actualToStringResult = timeDiscretizationFromArray.toString();

    // Assert
    assertEquals(
        "TimeDiscretizationFromArray [timeDiscretizationFromArray=[10.0, 10.5, 11.0, 11.5, 12.0, 12.5, 13.0,"
            + " 13.5, 14.0, 14.5, 15.0], timeTickSize=1.1415525114155251E-4]",
        actualToStringResult);
    assertEquals(1.1415525114155251E-4d, timeDiscretizationFromArray.getTickSize(), 0.0);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#iterator()}.
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#iterator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Iterator TimeDiscretizationFromArray.iterator()"})
  public void testIterator() {
    // Arrange and Act
    Iterator<Double> actualIteratorResult =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d).iterator();

    // Assert
    assertEquals(10.0d, actualIteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.5d, actualIteratorResult.next().doubleValue(), 0.0);
    assertEquals(11.0d, actualIteratorResult.next().doubleValue(), 0.0);
    assertEquals(11.5d, actualIteratorResult.next().doubleValue(), 0.0);
    assertEquals(12.0d, actualIteratorResult.next().doubleValue(), 0.0);
    assertEquals(12.5d, actualIteratorResult.next().doubleValue(), 0.0);
    assertEquals(13.0d, actualIteratorResult.next().doubleValue(), 0.0);
    assertEquals(13.5d, actualIteratorResult.next().doubleValue(), 0.0);
    assertEquals(14.0d, actualIteratorResult.next().doubleValue(), 0.0);
    assertEquals(14.5d, actualIteratorResult.next().doubleValue(), 0.0);
    assertTrue(actualIteratorResult.hasNext());
  }

  /**
   * Test {@link TimeDiscretizationFromArray#equals(Object)}, and {@link
   * TimeDiscretizationFromArray#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TimeDiscretizationFromArray#equals(Object)}
   *   <li>{@link TimeDiscretizationFromArray#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean TimeDiscretizationFromArray.equals(Object)",
    "int TimeDiscretizationFromArray.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);
    TimeDiscretizationFromArray timeDiscretizationFromArray2 =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertEquals(timeDiscretizationFromArray, timeDiscretizationFromArray2);
    assertEquals(timeDiscretizationFromArray.hashCode(), timeDiscretizationFromArray2.hashCode());
  }

  /**
   * Test {@link TimeDiscretizationFromArray#equals(Object)}, and {@link
   * TimeDiscretizationFromArray#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TimeDiscretizationFromArray#equals(Object)}
   *   <li>{@link TimeDiscretizationFromArray#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean TimeDiscretizationFromArray.equals(Object)",
    "int TimeDiscretizationFromArray.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertEquals(timeDiscretizationFromArray, timeDiscretizationFromArray);
    int expectedHashCodeResult = timeDiscretizationFromArray.hashCode();
    assertEquals(expectedHashCodeResult, timeDiscretizationFromArray.hashCode());
  }

  /**
   * Test {@link TimeDiscretizationFromArray#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean TimeDiscretizationFromArray.equals(Object)",
    "int TimeDiscretizationFromArray.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    TenorFromArray tenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);

    // Act and Assert
    assertNotEquals(tenorFromArray, new TimeDiscretizationFromArray(10.0d, 10, 0.5d));
  }

  /**
   * Test {@link TimeDiscretizationFromArray#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean TimeDiscretizationFromArray.equals(Object)",
    "int TimeDiscretizationFromArray.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    TimeDiscretizationFromArray timeDiscretizationFromArray =
        new TimeDiscretizationFromArray(
            10.0d, 10.0d, 0.5d, ShortPeriodLocation.SHORT_PERIOD_AT_START);

    // Act and Assert
    assertNotEquals(timeDiscretizationFromArray, new TimeDiscretizationFromArray(10.0d, 10, 0.5d));
  }

  /**
   * Test {@link TimeDiscretizationFromArray#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean TimeDiscretizationFromArray.equals(Object)",
    "int TimeDiscretizationFromArray.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new TimeDiscretizationFromArray(10.0d, 10, 0.5d), null);
  }

  /**
   * Test {@link TimeDiscretizationFromArray#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretizationFromArray#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean TimeDiscretizationFromArray.equals(Object)",
    "int TimeDiscretizationFromArray.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new TimeDiscretizationFromArray(10.0d, 10, 0.5d),
        "Different type to TimeDiscretizationFromArray");
  }
}
