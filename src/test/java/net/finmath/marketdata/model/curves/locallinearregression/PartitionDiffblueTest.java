package net.finmath.marketdata.model.curves.locallinearregression;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PartitionDiffblueTest {
  /**
   * Test {@link Partition#Partition(double[])}.
   *
   * <ul>
   *   <li>When array of {@code double} with ten and {@code 0.5}.
   *   <li>Then return Weight is {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link Partition#Partition(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Partition.<init>(double[])"})
  public void testNewPartition_whenArrayOfDoubleWithTenAnd05_thenReturnWeightIs05() {
    // Arrange
    double[] points = new double[] {10.0d, 0.5d, 10.0d, 0.5d};

    // Act
    Partition actualPartition = new Partition(points);

    // Assert
    assertEquals(0.5d, actualPartition.getWeight(), 0.0);
    assertEquals(3, actualPartition.getNumberOfIntervals());
    assertEquals(4, actualPartition.getLength());
    assertArrayEquals(new double[] {0.5d, 5.25d, 10.0d}, actualPartition.getReferencePoints(), 0.0);
    assertArrayEquals(new double[] {0.5d, 0.5d, 10.0d, 10.0d}, actualPartition.getPoints(), 0.0);
    assertArrayEquals(new double[] {0.5d, 0.5d, 10.0d, 10.0d}, points, 0.0);
  }

  /**
   * Test {@link Partition#Partition(double[], double)}.
   *
   * <ul>
   *   <li>When array of {@code double} with ten and {@code 0.5}.
   *   <li>Then return Weight is ten.
   * </ul>
   *
   * <p>Method under test: {@link Partition#Partition(double[], double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Partition.<init>(double[], double)"})
  public void testNewPartition_whenArrayOfDoubleWithTenAnd05_thenReturnWeightIsTen() {
    // Arrange
    double[] points = new double[] {10.0d, 0.5d, 10.0d, 0.5d};

    // Act
    Partition actualPartition = new Partition(points, 10.0d);

    // Assert
    assertEquals(10.0d, actualPartition.getWeight(), 0.0);
    assertEquals(3, actualPartition.getNumberOfIntervals());
    assertEquals(4, actualPartition.getLength());
    assertArrayEquals(new double[] {0.5d, 95.5d, 10.0d}, actualPartition.getReferencePoints(), 0.0);
    assertArrayEquals(new double[] {0.5d, 0.5d, 10.0d, 10.0d}, actualPartition.getPoints(), 0.0);
    assertArrayEquals(new double[] {0.5d, 0.5d, 10.0d, 10.0d}, points, 0.0);
  }

  /**
   * Test {@link Partition#getIntervalNumber(double)}.
   *
   * <ul>
   *   <li>Then return eight.
   * </ul>
   *
   * <p>Method under test: {@link Partition#getIntervalNumber(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int Partition.getIntervalNumber(double)"})
  public void testGetIntervalNumber_thenReturnEight() {
    // Arrange
    Partition partition =
        new Partition(new double[] {0.5d, 2.0d, 0.5d, 2.0d, 0.5d, 2.0d, 0.5d, 2.0d});

    // Act and Assert
    assertEquals(8, partition.getIntervalNumber(2.0d));
  }

  /**
   * Test {@link Partition#getIntervalNumber(double)}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link Partition#getIntervalNumber(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int Partition.getIntervalNumber(double)"})
  public void testGetIntervalNumber_thenReturnThree() {
    // Arrange
    Partition partition = new Partition(new double[] {0.5d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(3, partition.getIntervalNumber(2.0d));
  }

  /**
   * Test {@link Partition#getIntervalNumber(double)}.
   *
   * <ul>
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link Partition#getIntervalNumber(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int Partition.getIntervalNumber(double)"})
  public void testGetIntervalNumber_thenReturnTwo() {
    // Arrange
    Partition partition = new Partition(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(2, partition.getIntervalNumber(2.0d));
  }

  /**
   * Test {@link Partition#getIntervalNumber(double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Partition#getIntervalNumber(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int Partition.getIntervalNumber(double)"})
  public void testGetIntervalNumber_when05_thenReturnZero() {
    // Arrange
    Partition partition = new Partition(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(0, partition.getIntervalNumber(-0.5d));
  }

  /**
   * Test {@link Partition#d(double)}.
   *
   * <ul>
   *   <li>Given {@link Partition#Partition(double[])} with points is array of {@code double} with
   *       {@code 0.5} and {@code 0.5}.
   *   <li>When two.
   *   <li>Then return {@code 5.25}.
   * </ul>
   *
   * <p>Method under test: {@link Partition#d(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Partition.d(double)"})
  public void testD_givenPartitionWithPointsIsArrayOfDoubleWith05And05_whenTwo_thenReturn525() {
    // Arrange
    Partition partition = new Partition(new double[] {0.5d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(5.25d, partition.d(2.0d), 0.0);
  }

  /**
   * Test {@link Partition#d(double)}.
   *
   * <ul>
   *   <li>Given {@link Partition#Partition(double[])} with points is array of {@code double} with
   *       {@code 0.5} and two.
   *   <li>When two.
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link Partition#d(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Partition.d(double)"})
  public void testD_givenPartitionWithPointsIsArrayOfDoubleWith05AndTwo_whenTwo_thenReturnTwo() {
    // Arrange
    Partition partition =
        new Partition(new double[] {0.5d, 2.0d, 0.5d, 2.0d, 0.5d, 2.0d, 0.5d, 2.0d});

    // Act and Assert
    assertEquals(2.0d, partition.d(2.0d), 0.0);
  }

  /**
   * Test {@link Partition#d(double)}.
   *
   * <ul>
   *   <li>Given {@link Partition#Partition(double[])} with points is array of {@code double} with
   *       ten and {@code 0.5}.
   *   <li>When {@code -0.5}.
   *   <li>Then return {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link Partition#d(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Partition.d(double)"})
  public void testD_givenPartitionWithPointsIsArrayOfDoubleWithTenAnd05_when05_thenReturn05() {
    // Arrange
    Partition partition = new Partition(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(-0.5d, partition.d(-0.5d), 0.0);
  }

  /**
   * Test {@link Partition#d(double)}.
   *
   * <ul>
   *   <li>Given {@link Partition#Partition(double[])} with points is array of {@code double} with
   *       ten and {@code 0.5}.
   *   <li>When two.
   *   <li>Then return {@code 5.25}.
   * </ul>
   *
   * <p>Method under test: {@link Partition#d(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Partition.d(double)"})
  public void testD_givenPartitionWithPointsIsArrayOfDoubleWithTenAnd05_whenTwo_thenReturn525() {
    // Arrange
    Partition partition = new Partition(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(5.25d, partition.d(2.0d), 0.0);
  }

  /**
   * Test {@link Partition#getIntervalReferencePoint(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 5.25}.
   * </ul>
   *
   * <p>Method under test: {@link Partition#getIntervalReferencePoint(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Partition.getIntervalReferencePoint(int)"})
  public void testGetIntervalReferencePoint_whenOne_thenReturn525() {
    // Arrange
    Partition partition = new Partition(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(5.25d, partition.getIntervalReferencePoint(1), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Partition#getPoints()}
   *   <li>{@link Partition#getReferencePoints()}
   *   <li>{@link Partition#getWeight()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] Partition.getPoints()",
    "double[] Partition.getReferencePoints()",
    "double Partition.getWeight()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Partition partition = new Partition(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    double[] actualPoints = partition.getPoints();
    double[] actualReferencePoints = partition.getReferencePoints();

    // Assert
    assertEquals(0.5d, partition.getWeight(), 0.0);
    assertArrayEquals(new double[] {0.5d, 5.25d, 10.0d}, actualReferencePoints, 0.0);
    assertArrayEquals(new double[] {0.5d, 0.5d, 10.0d, 10.0d}, actualPoints, 0.0);
  }

  /**
   * Test {@link Partition#getPoint(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link Partition#getPoint(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Partition.getPoint(int)"})
  public void testGetPoint_whenOne_thenReturn05() {
    // Arrange
    Partition partition = new Partition(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(0.5d, partition.getPoint(1), 0.0);
  }

  /**
   * Test {@link Partition#getLength()}.
   *
   * <p>Method under test: {@link Partition#getLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int Partition.getLength()"})
  public void testGetLength() {
    // Arrange
    Partition partition = new Partition(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(4, partition.getLength());
  }

  /**
   * Test {@link Partition#getNumberOfIntervals()}.
   *
   * <p>Method under test: {@link Partition#getNumberOfIntervals()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int Partition.getNumberOfIntervals()"})
  public void testGetNumberOfIntervals() {
    // Arrange
    Partition partition = new Partition(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(3, partition.getNumberOfIntervals());
  }

  /**
   * Test {@link Partition#getIntervalLength(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 9.5}.
   * </ul>
   *
   * <p>Method under test: {@link Partition#getIntervalLength(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Partition.getIntervalLength(int)"})
  public void testGetIntervalLength_whenOne_thenReturn95() {
    // Arrange
    Partition partition = new Partition(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(9.5d, partition.getIntervalLength(1), 0.0);
  }
}
