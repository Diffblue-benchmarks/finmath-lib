package net.finmath.timeseries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Iterator;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TimeSeriesFromArrayDiffblueTest {
  /**
   * Test {@link TimeSeriesFromArray#TimeSeriesFromArray(double[], double[])}.
   *
   * <p>Method under test: {@link TimeSeriesFromArray#TimeSeriesFromArray(double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeSeriesFromArray.<init>(double[], double[])"})
  public void testNewTimeSeriesFromArray() {
    // Arrange and Act
    TimeSeriesFromArray actualTimeSeriesFromArray =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertEquals(4, actualTimeSeriesFromArray.getNumberOfTimePoints());
  }

  /**
   * Test {@link TimeSeriesFromArray#getTime(int)}.
   *
   * <ul>
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link TimeSeriesFromArray#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TimeSeriesFromArray.getTime(int)"})
  public void testGetTime_thenReturn05() {
    // Arrange
    TimeSeriesFromArray timeSeriesFromArray =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(0.5d, timeSeriesFromArray.getTime(1), 0.0);
  }

  /**
   * Test {@link TimeSeriesFromArray#getValue(int)}.
   *
   * <ul>
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link TimeSeriesFromArray#getValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TimeSeriesFromArray.getValue(int)"})
  public void testGetValue_thenReturn05() {
    // Arrange
    TimeSeriesFromArray timeSeriesFromArray =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(0.5d, timeSeriesFromArray.getValue(1), 0.0);
  }

  /**
   * Test {@link TimeSeriesFromArray#getNumberOfTimePoints()}.
   *
   * <p>Method under test: {@link TimeSeriesFromArray#getNumberOfTimePoints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TimeSeriesFromArray.getNumberOfTimePoints()"})
  public void testGetNumberOfTimePoints() {
    // Arrange
    TimeSeriesFromArray timeSeriesFromArray =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(4, timeSeriesFromArray.getNumberOfTimePoints());
  }

  /**
   * Test {@link TimeSeriesFromArray#getValues()}.
   *
   * <p>Method under test: {@link TimeSeriesFromArray#getValues()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Iterable TimeSeriesFromArray.getValues()"})
  public void testGetValues() {
    // Arrange
    TimeSeriesFromArray timeSeriesFromArray =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    Iterator<Double> actualIteratorResult = timeSeriesFromArray.getValues().iterator();

    // Assert
    Double nextResult = actualIteratorResult.next();
    Double nextResult2 = actualIteratorResult.next();
    Double nextResult3 = actualIteratorResult.next();
    Double nextResult4 = actualIteratorResult.next();
    boolean actualHasNextResult = actualIteratorResult.hasNext();
    assertEquals(0.5d, nextResult2.doubleValue(), 0.0);
    assertEquals(0.5d, nextResult4.doubleValue(), 0.0);
    assertEquals(10.0d, nextResult.doubleValue(), 0.0);
    assertEquals(10.0d, nextResult3.doubleValue(), 0.0);
    assertFalse(actualHasNextResult);
  }
}
