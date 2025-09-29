package net.finmath.timeseries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Iterator;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TimeSeriesViewDiffblueTest {
  /**
   * Test {@link TimeSeriesView#TimeSeriesView(TimeSeries, int, int)}.
   *
   * <p>Method under test: {@link TimeSeriesView#TimeSeriesView(TimeSeries, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimeSeriesView.<init>(TimeSeries, int, int)"})
  public void testNewTimeSeriesView() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(1, new TimeSeriesView(timeSeries, 1, 1).getNumberOfTimePoints());
  }

  /**
   * Test {@link TimeSeriesView#getTime(int)}.
   *
   * <ul>
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link TimeSeriesView#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TimeSeriesView.getTime(int)"})
  public void testGetTime_thenReturn05() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(
        0.5d, new TimeSeriesView(new TimeSeriesView(timeSeries, 1, 1), 1, 1).getTime(1), 0.0);
  }

  /**
   * Test {@link TimeSeriesView#getTime(int)}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link TimeSeriesView#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TimeSeriesView.getTime(int)"})
  public void testGetTime_thenReturnTen() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(10.0d, new TimeSeriesView(timeSeries, 1, 1).getTime(1), 0.0);
  }

  /**
   * Test {@link TimeSeriesView#getValue(int)}.
   *
   * <ul>
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link TimeSeriesView#getValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TimeSeriesView.getValue(int)"})
  public void testGetValue_thenReturn05() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(
        0.5d, new TimeSeriesView(new TimeSeriesView(timeSeries, 1, 1), 1, 1).getValue(1), 0.0);
  }

  /**
   * Test {@link TimeSeriesView#getValue(int)}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link TimeSeriesView#getValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TimeSeriesView.getValue(int)"})
  public void testGetValue_thenReturnTen() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(10.0d, new TimeSeriesView(timeSeries, 1, 1).getValue(1), 0.0);
  }

  /**
   * Test {@link TimeSeriesView#getNumberOfTimePoints()}.
   *
   * <p>Method under test: {@link TimeSeriesView#getNumberOfTimePoints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TimeSeriesView.getNumberOfTimePoints()"})
  public void testGetNumberOfTimePoints() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(1, new TimeSeriesView(timeSeries, 1, 1).getNumberOfTimePoints());
  }

  /**
   * Test {@link TimeSeriesView#getValues()}.
   *
   * <p>Method under test: {@link TimeSeriesView#getValues()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Iterable TimeSeriesView.getValues()"})
  public void testGetValues() {
    // Arrange
    TimeSeriesFromArray timeSeries =
        new TimeSeriesFromArray(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d}, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    Iterator<Double> actualIteratorResult =
        new TimeSeriesView(timeSeries, 1, 1).getValues().iterator();

    // Assert
    assertEquals(0.5d, actualIteratorResult.next().doubleValue(), 0.0);
    assertFalse(actualIteratorResult.hasNext());
  }
}
